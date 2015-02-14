package controllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.Program;
import logic.Words;
import maincontol.ProgramNavigator;

public class TestView implements Initializable{

	private ObservableList<Words> leftWords = FXCollections.observableArrayList();
	private Integer mode;
	private Random  random = new Random();
	private int tuple;
	private int wordId;
	private int tries = 0;
	private long startTime;
	private long endTime;
	
	@FXML
	private Button btnNext;
	@FXML
	private Button btnSaveProgress;
	@FXML
	private Button btnSubmit;
	@FXML
	private TextField txtCount;
	@FXML
	private TextArea txtTranslation;
	@FXML
	private TextArea txtWord;
	@FXML
	public void nextWord(ActionEvent event){
		txtWord.setText(randomWord());
		tries++;
	}
	@FXML
	public void submitWord(ActionEvent event){
		if(leftWords.get(tuple).getWord((wordId == 0) ? 1 : 0).toLowerCase()
				.equals(txtTranslation.getText().toLowerCase())){
			leftWords.remove(tuple);
			if(leftWords.size() == 0){
				endTime = System.currentTimeMillis();
				int n = Program.user.getDictionaryWords().size();
				Program.testProgressController.setScore(((float)n/(n + tries)));
				Program.testProgressController.setTime(endTime - startTime);
				ProgramNavigator.loadPane(ProgramNavigator.RESULT);
			}else{
				txtWord.setText(randomWord());
				txtCount.setText(String.valueOf(leftWords.size()));
			}
		}else{
			txtWord.setText(randomWord());
			txtTranslation.setText("");
			tries += 2;
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		leftWords = FXCollections.observableArrayList(Program.user.getDictionaryWords());
		mode = Program.testProgressController.getTestMode();
		txtCount.setText(String.valueOf(leftWords.size()));
		txtWord.setText(randomWord());
		startTime = System.currentTimeMillis();
	}
	private String randomWord(){
		switch (mode) {
		case 0:
			tuple = random.nextInt(leftWords.size());
			wordId = random.nextInt(2);
			return leftWords.get(tuple).getWord(wordId);
		case 1:
			tuple = random.nextInt(leftWords.size());
			wordId = 0;
			return leftWords.get(tuple).getWord(wordId);
		case 2:
			tuple = random.nextInt(leftWords.size());
			wordId = 1;
			return leftWords.get(tuple).getWord(wordId);
		}
		return null;
	}
}
