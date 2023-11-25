package Resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Repository {
    private Driver driver;
    private String filePathName;
    private String[] headerNames;
    

    public Repository(Driver driver, String filePathName, String[] headerNames) {
        this.driver = driver;
        this.filePathName = filePathName;
        this.headerNames = headerNames;
    }

    private void writeHeaderOnly() throws FileNotFoundException {
        driver.writeHeaderOnly(filePathName, headerNames);
    }

    private ArrayList<ArrayList<String>> readItems() throws IOException{    
        return driver.readItems(filePathName);
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

    public void addItem(Object objeto) throws FileNotFoundException {
        driver.addObject(objeto, filePathName, headerNames);
    }

    public void removeItemByIndex(int targetIndex) throws IOException {
        ArrayList<ArrayList<String>> csvFileData = removeStringByIndex(readItems(), targetIndex);
        writeHeaderOnly();
        driver.addMultipleString(csvFileData, filePathName, headerNames);
    }

    public void removeItemByIndexa(int targetIndex) throws IOException {
        ArrayList<ArrayList<String>> csvFileData = removeStringByIndex(readItems(), targetIndex);
        writeHeaderOnly();
        driver.addMultipleString(csvFileData, filePathName, headerNames);
    }


}
