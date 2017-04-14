package com.starwars.selenium.data;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Mandar Abhyankar on 4/14/2017.
 * Updated by Mandar Abhyankar on 4/14/2017.
 * <p>
 * This class contains methods to retrieve data from a CSV file.
 */

public class AutoDataHandler {


    private static String line;
    private static HashMap<String, Integer> setHeader = new HashMap<String, Integer>();
    private static String[] splitData;
    private static File dataFile;
    private static String columnIdentifier;

    private static final String DEFAULT_SCENARIO_HEADER_NAME = "ScenarioName";

    /**
     * This method parses the data file and stores data in a HashMap for retrieval.
     *
     * @param CSVFile
     * @param ScenarioName
     * @throws IOException
     * @throws IndexOutOfBoundsException
     */
    public void parseDataFile(File CSVFile, String ScenarioName) throws IOException, IndexOutOfBoundsException {
        FileReader fileReader = new FileReader(CSVFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith(DEFAULT_SCENARIO_HEADER_NAME)) {
                String[] splitHeader = line.split(",");
                for (int column = 1; column < splitHeader.length; column++) {
                    setHeader.put(splitHeader[column], column);
                }
            }
            if (line.startsWith(ScenarioName)) {
                splitData = line.split(",");
            }
        }
        bufferedReader.close();
    }

    /**
     * This method returns the data corresponding to the column name.
     *
     * @param columnName
     * @return
     */
    public String getData(String columnName) {
        return splitData[setHeader.get(columnName)];
    }

    /**
     * This method calls the parser method passes the required parameters
     * @param filePath
     * @param colIdentifier
     */
    public void DataHandler(String filePath, String colIdentifier) {
        dataFile = new File(filePath);
        columnIdentifier = colIdentifier;

        try {
            AutoDataHandler dataHandler = new AutoDataHandler();
            dataHandler.parseDataFile(dataFile, columnIdentifier);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //setHeader.clear();
        //line = null;
        //splitData = null;

    }

}
