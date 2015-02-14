package logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CSVParser {
	/**
	 * @param fileToParse - path to the file
	 * @param number - number of language*/
	public static ObservableList<Words> getWords(String fileToParse){
		String[] language = new String[2];
		ObservableList<Words> list = FXCollections.observableArrayList();
        BufferedReader fileReader = null;
        boolean isLanguage = true;
        final String DELIMITER = ",";
        try{
            String line = "";
            fileReader = new BufferedReader(new InputStreamReader(
            	    new FileInputStream(fileToParse), "UTF-8"));
            while ((line = fileReader.readLine()) != null)
            if(!isLanguage){
                String[] tokens = line.split(DELIMITER);
                Charset.forName("UTF-8").encode(tokens[1]);
                Charset.forName("UTF-8").encode(tokens[0]);
                list.add(new Words(tokens[0], tokens[1], language[0], language[1]));
            }else{
            	isLanguage = false;
            	String langline = "";
            	BufferedReader langfileReader = new BufferedReader(new FileReader(fileToParse));
            	langline = langfileReader.readLine();
                String[] tokens = langline.split(DELIMITER);
                	language = tokens;
                langfileReader.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
	}
}