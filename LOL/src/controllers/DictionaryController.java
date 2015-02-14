package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import logic.CSVParser;
import logic.Program;
import maincontol.ProgramNavigator;

public class DictionaryController implements Initializable{

	ObservableList<String> items;
	
	@FXML
	private Button btnShow;
	@FXML
	private Button btnTest;
	@FXML
	private Button btnDelete;
	//For test
	@FXML
	private ListView<String> lvDictionaries;
	//User chooses dictionary from ListView
	@FXML
	private void getTest(ActionEvent event){
		Integer index = lvDictionaries.getSelectionModel().getSelectedIndex();
		if(index != -1){
			Program.user.setLocalDictionary(items.get(index));
			Program.user.setDictionaryWords(CSVParser.getWords("dictionaries/"
					+ lvDictionaries.getSelectionModel().getSelectedItem() + ".csv"));
			Program.languageController.setLanguage1Local(Program.user.getDictionaryWords().get(0).getLanguage1());
			Program.languageController.setLanguage2Local(Program.user.getDictionaryWords().get(0).getLanguage2());
			ProgramNavigator.loadPane(ProgramNavigator.TEST_SETTINGS);
		}
	}
	@FXML
	private void showDictionary(ActionEvent event){
		Integer index = lvDictionaries.getSelectionModel().getSelectedIndex();
		if(index != -1){
			Program.user.setDictionaryWords(CSVParser.getWords("dictionaries/"
					+ lvDictionaries.getSelectionModel().getSelectedItem() + ".csv"));
			Program.languageController.setLanguage1Local(Program.user.getDictionaryWords().get(0).getLanguage1());
			Program.languageController.setLanguage2Local(Program.user.getDictionaryWords().get(0).getLanguage2());
			ProgramNavigator.loadPane(ProgramNavigator.DICTIONARY_SHOW);
		}
	}
	
	@FXML
	private void deleteDictionary(ActionEvent event){
		String fileName = lvDictionaries.getSelectionModel().getSelectedItem();
		try{
			File file = new File("dictionaries/" + fileName + ".csv");
			if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
		}catch(Exception e){
			e.printStackTrace();
		}
		initialize(null, null);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		items = FXCollections.observableArrayList();
		boolean isFolder = false;
		File[] filed = new File(".").listFiles();
			for(File f:filed)
				if(f.getName() == "dictionaries")
					isFolder = true;
			if(!isFolder){
				File dir = new File("dictionaries");
				dir.mkdir();
			}
			
		File[] files = new File("dictionaries").listFiles();
		for(File file : files)
			if(file.isFile() && file.getName().split("\\.")[1].equals("csv~")){
					System.out.println(file.getName());
					System.out.println(file.getName().split("\\.")[1]);
					items.add(file.getName().split("\\.")[0]);
				}
		lvDictionaries.setItems(items);
	}

	
}
