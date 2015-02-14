package controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import logic.Program;
import maincontol.ProgramNavigator;

public class MenuController implements Initializable{

	@FXML
	private AnchorPane menuPane;
	@FXML
	private VBox boxButtons;
	/*@FXML
	private Button btnTest;*/
	@FXML
	private Button btnViewDictionaries;
	@FXML
	private Button btnLoadDictionary;
	@FXML
	private Button btnCreateDictionary;
	@FXML
	private ListView<String> lvStatistics;
	@FXML
	private void exitProgram(ActionEvent event){
		Program.closeSessionFactory();
		Platform.exit();
	}
	@Override
	public void initialize(java.net.URL arg0, java.util.ResourceBundle arg1) {
		if(Program.isConnectedToInternet())
			lvStatistics.setItems(Program.getStatistics());
	}
	@FXML
	private void viewDictionaries(ActionEvent event) {
		ProgramNavigator.loadPane(ProgramNavigator.DICTIONARY_VIEW);
		}
	@FXML
	private void loadDictionary(ActionEvent event) {
		if(Program.user.isSignedIn())
			Program.initDictionaries();
			if(Program.checkConnection())
				ProgramNavigator.loadPane(ProgramNavigator.DICTIONARY_LOAD);
		}
	@FXML
	private void createDictionary(ActionEvent event) {
		//ProgramNavigator.loadPane(ProgramNavigator.DICTIONARY_VIEW);
		}
}