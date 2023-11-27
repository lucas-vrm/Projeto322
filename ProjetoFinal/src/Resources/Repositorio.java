package Resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Repositorio {
    private Driver driver;

    private String filePathName;
    private String[] headerNames;

    public Repositorio(Driver driver, String filePathName, String[] headerNames) {
        this.driver = driver;
        this.filePathName = filePathName;
        this.headerNames = headerNames;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String[] getHeaderNames() {
        return headerNames;
    }

    public void setHeaderNames(String[] headerNames) {
        this.headerNames = headerNames;
    }
    
    public void writeHeaderOnly() throws FileNotFoundException {
        driver.writeHeaderOnly(filePathName, headerNames);
    }

    public void eraseLines() throws FileNotFoundException {
        driver.eraseLines(filePathName);
    }

    private ArrayList<ArrayList<String>> removeStringByIndex(ArrayList<ArrayList<String>> stringList, int targetIndex) {
        ArrayList<ArrayList<String>> outputList = new ArrayList<>();
        for(int i = 0; i < stringList.size(); i++) {
            if (i != targetIndex) {
                outputList.add(stringList.get(i));
            }
        }
        return outputList;
    }

    public Map<String, String> getObjectMapByAttribute(String AtributeName, String AttributeValue) {
        String[] headernames = getHeaderNames();
        int attribute_column = this.driver.attributeColumn(AtributeName, headernames);
        ArrayList<ArrayList<String>> items = null;
        Map<String, String> objectMap = new HashMap<>();

        try {
            items = pullAllItems();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
        for (ArrayList<String> item : items) {
            if (item.get(attribute_column).equals(AttributeValue)) {
                int i = 0;
                for(String headername : headernames) {
                    objectMap.put(headername, item.get(i));
                    i++;
                }
                return objectMap;
            }
        }
        return null;
    }

    public ArrayList<Map<String, String>> getAllObjectsMapBySameAttribute(String AtributeName, String AttributeValue) {
        String[] headernames = getHeaderNames();
        int attribute_column = this.driver.attributeColumn(AtributeName, headernames);
        ArrayList<Map<String, String>> allObjectMaps = new ArrayList<>();
        ArrayList<ArrayList<String>> items = null;

        try {
            items = pullAllItems();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
        for (ArrayList<String> item : items) {
            if (item.get(attribute_column).equals(AttributeValue)) {
                Map<String, String> tempObjectMap = new HashMap<>();
                int i = 0;
                for(String headername : headernames) {
                    tempObjectMap.put(headername, item.get(i));
                    i++;
                }
                allObjectMaps.add(tempObjectMap);
            }
        }
        return allObjectMaps;
    }

    public ArrayList<ArrayList<String>> pullAllItems() throws IOException{    
        return driver.readItems(this.filePathName);
    }

    public void pushAllItems(ArrayList<ArrayList<String>> Items) throws IOException {
        // writeHeaderOnly();
        driver.addMultipleString(Items, this.filePathName, this.headerNames);
    }

    public void addItem(Object objeto) throws FileNotFoundException {
        driver.addObject(objeto, this.filePathName, this.headerNames);
    }

    public void removeItemByIndex(int targetIndex) throws IOException {
        ArrayList<ArrayList<String>> csvFileData = removeStringByIndex(pullAllItems(), targetIndex);
        eraseLines();
        driver.addMultipleString(csvFileData, this.filePathName, this.headerNames);
    }
    
    public int searchItemByStringValue(String atributo, String value) throws IOException {
        int column_index = this.driver.attributeColumn(atributo, this.headerNames);
        ArrayList<ArrayList<String>> items = pullAllItems();
        int target_index = 0;
        for (ArrayList<String> item : items) {
            //System.out.println(item);
            if (item.get(column_index).equals(value)) {
                return target_index;
            }
            target_index += 1;
        }
        return -1;
    }

    public ArrayList<ArrayList<String>> getItemsByStringValue(String atributo, String value) throws IOException {
        int column_index = this.driver.attributeColumn(atributo, this.headerNames);
        ArrayList<ArrayList<String>> items = pullAllItems();
        ArrayList<ArrayList<String>> output = new ArrayList<>();
        for (ArrayList<String> item : items) {
            if (item.get(column_index).equals(value)) {
                output.add(item);
            }
        }
        return output;
    }

    public void removeItemByStringValue(String atributo, String value) throws IOException {
        int i = searchItemByStringValue(atributo, value);
        if (i != -1) {
            removeItemByIndex(i);
        } else {
            System.out.println(atributo + " with value " + value + " not found");
        }
    }


}
