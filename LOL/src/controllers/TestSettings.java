package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import logic.Program;
import maincontol.ProgramNavigator;

public class TestSettings implements Initializable{

	@FXML
	private ChoiceBox<String> chbType;
	@FXML
	private CheckBox cbSaveScore;
	@FXML
	private Pane pnSettings;
	@FXML
	private Button btnStart;
	@FXML
	private void startTest(ActionEvent event){
		Integer index = chbType.getSelectionModel().getSelectedIndex();
		if(index != -1){
			Program.testProgressController.setTestMode(index);
			Program.testProgressController.setSaveScore(cbSaveScore.selectedProperty().getValue());
			ProgramNavigator.loadPane(ProgramNavigator.TEST_VIEW);
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> types = FXCollections.observableArrayList();
		types.add("Random language");
		types.add(Program.languageController.getLanguage1Local() + " - " + 
				Program.languageController.getLanguage2Local());
		types.add(Program.languageController.getLanguage2Local() + " - " + 
				Program.languageController.getLanguage1Local());
		chbType.setItems(types);
	}
	
}
