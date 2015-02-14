package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logic.Program;
import maincontol.ProgramNavigator;

public class TestFinish implements Initializable{

	@FXML
	private TextField txtTime;
	@FXML
	private TextField txtScore;
	@FXML
	private Button btnRestart;
	@FXML
	private Button btnFinish;
	@FXML
	public void restartTest(ActionEvent event){
		ProgramNavigator.loadPane(ProgramNavigator.TEST_SETTINGS);
	}
	@FXML
	public void finish(ActionEvent event){
		if(Program.checkConnection() && Program.testProgressController.getSaveScore() && Program.user.isSignedIn() && 
				Program.user.getChosenDictionary().getName().equals(Program.user.getLocalDictionary()))
			Program.testProgressController.submitScore();
		ProgramNavigator.loadPane(ProgramNavigator.MENU);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		long millis = Program.testProgressController.getTime();
		long second = (millis / 1000) % 60;
		long minute = (millis / (1000 * 60)) % 60;
		long hour = (millis / (1000 * 60 * 60)) % 24;
		String time = String.format("%02d:%02d:%02d", hour, minute, second);
		txtTime.setText(time);	
		txtScore.setText(String.valueOf(
				Math.round( Program.testProgressController.getScore() * 100.0 ) / 100.0));		
	}

}
