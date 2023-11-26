package Resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
    
    protected void writeHeaderOnly() throws FileNotFoundException {
        driver.writeHeaderOnly(filePathName, headerNames);
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

    public ArrayList<ArrayList<String>> pullAllItems() throws IOException{    
        return driver.readItems(filePathName);
    }

    public void pushAllItems(ArrayList<ArrayList<String>> Items) throws IOException {
        // writeHeaderOnly();
        driver.addMultipleString(Items, filePathName, headerNames);
    }

    public void addItem(Object objeto) throws FileNotFoundException {
        driver.addObject(objeto, filePathName, headerNames);
    }

    public void removeItemByIndex(int targetIndex) throws IOException {
        ArrayList<ArrayList<String>> csvFileData = removeStringByIndex(pullAllItems(), targetIndex);
        // writeHeaderOnly();
        driver.addMultipleString(csvFileData, filePathName, headerNames);
    }

    public int searchItemByAttributeValue(String atributo, Object value) throws IOException {
        int column_index = this.driver.attributeColumn(atributo, this.headerNames);
        ArrayList<ArrayList<String>> items = pullAllItems();
        int target_index = 0;
        for (ArrayList<String> item : items) {
            if (item.get(column_index).equals(value.toString())) {
                return target_index;
            }
            target_index += 1;
        }
        return -1;
    }

    public void removeItemByAttributeValue(String atributo, Object value) throws IOException {
        int i = searchItemByAttributeValue(atributo, value);
        if (i != -1) {
            removeItemByIndex(i);
        } else {
            System.out.println(atributo + " with value " + value + " not found");
        }
    }


}
