package Resources;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import Model.Destino;

public class Driver {
    public Driver() {
    }

    private int indexOfHeader(String headerName, String[] headerNames) {
        int i;
        for (i = 0; i < headerNames.length; i++) {
            if (headerNames[i].equals(headerName)) {
                //System.out.println("Header found" + i);
                return i;
            }
        }

        System.out.println("Header " + headerName + " not found (index " + i + ")");
        System.exit(1);
        return 0;
    }

    private Object[] emptyCsvOutput(String[] headerNames, int headerLength) {
        Object[] csvOutput = new Object[headerLength];
        for (int i = 0; i < headerLength; i++) {
            csvOutput[i] = "empty";
        }
        return csvOutput;
    }

    private String getObjectClassName(String fullName) {
        int lastDotIndex = fullName.lastIndexOf(".");
        return fullName.substring(lastDotIndex + 1);
    }

    private static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }

    private String listToSingleString(Object inputObject) {
        List<?> inputList = convertObjectToList(inputObject);

        StringBuilder output = new StringBuilder();
        int listSize = inputList.size();
        if (listSize == 0) {
            return "empty";
        } else {
            for (int i = 0; i < listSize - 1; i++) {
                output.append(Objects.toString(inputList.get(i), null)).append("|");
            }
            output.append(inputList.get(listSize - 1)).append(",");
            return output.toString();
        }
    }

    private Object[] getObjectAttributeValues(Object objeto, String headerNames[]) {
        Object[] attributeValues = emptyCsvOutput(headerNames, headerNames.length);

        Class<?> currentClass = objeto.getClass();
        attributeValues[0] = getObjectClassName(currentClass.getName());

        while (currentClass != null) {
            Field[] attributes = currentClass.getDeclaredFields();
            for (Field attribute : attributes) {
                try {
                    attribute.setAccessible(true);
                    Object value = attribute.get(objeto);

                    String attributeName = attribute.getName();
                    String attributeType = attribute.getType().getName();
                    //System.out.println("attributeType = " + attributeType);
                    if ("Model.Destino".equals(attributeType) && value != null) {
                        Destino temp = (Destino) value;
                        attributeValues[indexOfHeader(attributeName, headerNames)] = temp.getNome();

                    } else if ("java.util.Date".equals(attributeType) && value != null) {
                        Date currentDate = (Date) value;
                        LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        attributeValues[indexOfHeader(attributeName, headerNames)] = (String) localDate.format(formatter);

                    } else if ("java.util.ArrayList".equals(attributeType) && value != null) {
                        attributeValues[indexOfHeader(attributeName, headerNames)] = listToSingleString(value);
                    } else {
                        attributeValues[indexOfHeader(attributeName, headerNames)] = value;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        return attributeValues;
    }

    private String[] getObjectAttributeNames(Object objeto) throws IllegalAccessException {
        ArrayList<String> headerNames = new ArrayList<>();

        Class<?> currentClass = objeto.getClass();

        while (currentClass != null) {
            Field[] attributes = currentClass.getDeclaredFields();
            for (Field attribute : attributes) {
                attribute.setAccessible(true);
                headerNames.add(attribute.getName());
            }
            currentClass = currentClass.getSuperclass();
        }

        // Convertendo para array de String
        return headerNames.toArray(new String[0]);
    }

    private void writeLine(List<?> csvOutput, PrintWriter out) {
        int headerLength = csvOutput.size();
        for (int i = 0; i < headerLength - 1; i++) {
            out.write(Objects.toString(csvOutput.get(i), null) + ",");
        }
        out.write(Objects.toString(csvOutput.get(headerLength - 1), null) + "\n");
    }

    void eraseLines(String pathname) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(new File(pathname))) {
            out.write("");
        }
    }

    public String[] getObjectHeaderNames(Object objeto) throws IllegalAccessException {
        return getObjectAttributeNames(objeto);
    }

    public void writeHeaderOnly(String pathname, String[] headerNames) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(new File(pathname))) {
            writeLine(Arrays.asList(headerNames), out);
        }
    }

    public void addObject(Object objeto, String pathname, String[] headerNames) throws FileNotFoundException {
        try (FileWriter fw = new FileWriter(pathname, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            Object[] csvOutput = getObjectAttributeValues(objeto, headerNames);
            writeLine(Arrays.asList(csvOutput), out);
        } catch (IOException e) {
            //Write Exception Handling
            e.printStackTrace();
        }
    }

    public void addMultipleObject(ArrayList<Object> objetos, String pathname, String[] headerNames) throws FileNotFoundException {
        try (FileWriter fw = new FileWriter(pathname, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Object objeto : objetos) {
                Object[] csvOutput = getObjectAttributeValues(objeto, headerNames);
                writeLine(Arrays.asList(csvOutput), out);
            }
        } catch (IOException e) {
            //Write Exception Handling
            e.printStackTrace();
        }
    }

    public void addString(ArrayList<String> csvRow, String pathname, String[] headerNames) throws FileNotFoundException {
        try (FileWriter fw = new FileWriter(pathname, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            writeLine(csvRow, out);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
            e.printStackTrace();
        }
    }

    public void addMultipleString(ArrayList<ArrayList<String>> csvMultipleRow, String pathname, String[] headerNames) throws FileNotFoundException {
        try (FileWriter fw = new FileWriter(pathname, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (ArrayList<String> csvRow : csvMultipleRow) {
                writeLine(csvRow, out);
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> readItems(String pathname) throws FileNotFoundException, IOException {
        ArrayList<ArrayList<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathname))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(new ArrayList<>(Arrays.asList(values)));
            }
        }
        return records;
    }

    public int attributeColumn(String headerName, String[] headerNames) {
        return indexOfHeader(headerName, headerNames);
    }

}
