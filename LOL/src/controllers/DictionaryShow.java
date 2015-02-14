package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import logic.Program;
import logic.Words;
import maincontol.ProgramNavigator;

public class DictionaryShow implements Initializable{

	@FXML
	private TableView<Words> tblWords;
	@FXML
	private TableColumn<Words, String> colLanguage1;
	@FXML
	private TableColumn<Words, String> colLanguage2;
	@FXML
	private Button back;
	@FXML
	private Button refresh;
	@FXML
	private void refreshTable(ActionEvent event){
		
	}
	@FXML
	private void back(ActionEvent event){
		ProgramNavigator.loadPane(ProgramNavigator.DICTIONARY_VIEW);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		tblWords.setItems(Program.user.getDictionaryWords());
		colLanguage1.setText(Program.languageController.getLanguage1Local());
		colLanguage2.setText(Program.languageController.getLanguage2Local());
		colLanguage1.setCellValueFactory(cellData -> cellData.getValue().getWord1());
		colLanguage2.setCellValueFactory(cellData -> cellData.getValue().getWord2());
	}

}
