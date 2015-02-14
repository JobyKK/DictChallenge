package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import logic.FTPLoader;
import logic.Program;
import maincontol.ProgramNavigator;

public class DictionaryLoader implements Initializable {
		@FXML
		private ListView<String> lvDictList;
		@FXML
		private Button btnBack;
		@FXML
		private Button btnLoad;
		@FXML
		private Button btnRefresh;
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			lvDictList.setItems(Program.getDictionaries());
			boolean isFolder = false;
			File[] filed = new File(".").listFiles();
				for(File f:filed)
					if(f.getName() == "dictionaries")
						isFolder = true;
				if(!isFolder){
					File dir = new File("dictionaries");
					dir.mkdir();
				}
		}
		//dictionary load
		@FXML
		private void loadDictionary(ActionEvent event){
			Integer index = lvDictList.getSelectionModel().getSelectedIndex();
			// here may be part that can add "loaded" to item
			if(index != -1)
			if(FTPLoader.loadDictionary(index));
				Program.user.chooseDictionary(index);
		}
		@FXML
		private void refteshList(ActionEvent event){
			lvDictList.setItems(Program.getDictionaries());
		}
		@FXML
		private void back(ActionEvent event){
			ProgramNavigator.loadPane(ProgramNavigator.MENU);
		}
}
