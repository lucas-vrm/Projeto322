package Resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class Driver {
    public Driver() {
    }

    private int indiceHeader(String headerName, String headerNames[]) {
        for (int i = 0; i < headerNames.length; i++) {
            if (headerNames[i] == headerName) {
                return i;
            }
        }
        System.out.println("Header " + headerName + " not found");
        return 0;
    }

    private Object[] emptyCsvOutput(String headerNames[], int headerLenght) {
        Object[] csvOutput = new Object[headerLenght];
        for(int i = 0; i < headerLenght; i++) {
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
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }

    private String listToSingleString(Object inputObject) {
        List<?> inputList = convertObjectToList(inputObject);
        
        String output = "";
        int listSize = inputList.size();
        for(int i = 0; i < listSize - 1; i++) {
            output += Objects.toString(inputList.get(i), null) + "|";
        }
        output += inputList.get(listSize-1) + ",";
        return output;
        
    }

    private Object[] getObjectAttributeValues(Object objeto, String headerNames[]) {
        Object[] atributeValues = emptyCsvOutput(headerNames, headerNames.length);

        Class<?> classeAtual = objeto.getClass();
        atributeValues[0] = getObjectClassName(classeAtual.getName());
        
        while (classeAtual != null) {
            Field[] atributos = classeAtual.getDeclaredFields();
            for (Field atributo : atributos) {
                try {
                    atributo.setAccessible(true);
                    Object value = atributo.get(objeto);

                    String attributeName = atributo.getName();
                    String attributeType = atributo.getType().getName();
                    if ( attributeType == "java.util.ArrayList" && value != null) {
                        atributeValues[indiceHeader(attributeName, headerNames)] = listToSingleString(value);
                    } else {
                        atributeValues[indiceHeader(attributeName, headerNames)] = value;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            classeAtual = classeAtual.getSuperclass();
        }
        return atributeValues;
    }

    private void writeLine(Object[] csvOutput, PrintWriter out) {
        int headerLenght = csvOutput.length;
        for(int i = 0; i < headerLenght - 1; i++) {
            out.write(csvOutput[i] + ",");
        }
        out.write(csvOutput[headerLenght-1] + "\n");
    }
    
    private void writeLine(ArrayList<String> csvOutput, PrintWriter out) {
        int headerLenght = csvOutput.size();
        for(int i = 0; i < headerLenght - 1; i++) {
            out.write(csvOutput.get(i) + ",");
        }
        out.write(csvOutput.get(headerLenght-1) + "\n");
    }

    public void writeHeaderOnly(String pathname, String[] headerNames) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new File(pathname));
        writeLine(headerNames, out);
        out.close();
    }

    public void addObject(Object objeto, String pathname, String[] headerNames) throws FileNotFoundException{
        try(FileWriter fw = new FileWriter(pathname, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
            Object[] csvOutput = getObjectAttributeValues(objeto, headerNames);
            writeLine(csvOutput, out);
            out.close();
        } catch (IOException e) {
            //Write Exception Handling
        }
    }

    public void addMultipleObject(ArrayList<Object> objetos, String pathname, String[] headerNames) throws FileNotFoundException{
        try(FileWriter fw = new FileWriter(pathname, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
            for (Object objeto : objetos) {
                Object[] csvOutput = getObjectAttributeValues(objeto, headerNames);
                writeLine(csvOutput, out);
            }
            out.close();
        } catch (IOException e) {
            //Write Exception Handling
        }
    }

    public void addString(ArrayList<String> csvRow, String pathname, String[] headerNames) throws FileNotFoundException{
        try(FileWriter fw = new FileWriter(pathname, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
            writeLine(csvRow, out);
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public void addMultipleString(ArrayList<ArrayList<String>> csvMultipleRow, String pathname, String[] headerNames) throws FileNotFoundException{
        try(FileWriter fw = new FileWriter(pathname, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
            for (ArrayList<String> csvRow : csvMultipleRow) {
                writeLine(csvRow, out);
            }
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
    
    public ArrayList<ArrayList<String>> readItems(String pathname) throws FileNotFoundException, IOException{
        ArrayList<ArrayList<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathname))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(new ArrayList<>(Arrays.asList(values)));
            }
        }
        return records; 
    }
    
}
