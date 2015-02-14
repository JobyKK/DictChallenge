package logic;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Dictionary;
import model.Statistics;
import model.User;

public class UserData extends User{

	private String localDictionary;
	private Dictionary chosenDictionary;
	private Statistics statistics;
	/**Array of downloaded dictionaries from user account*/
	private ArrayList<Dictionary> dictionaries;
	private Boolean isSignedIn;
	private ObservableList<Words> dictionaryWords = FXCollections.observableArrayList();
	
	public UserData(User u){
		super(u.getId(), u.getFirstName(), u.getSecondName(), u.getNick(),
				u.getEmail(), u.getPassword(), u.getSignUpDate(), u.getStatusid());
		isSignedIn = true;
	}
	
	public UserData() {
		super();
		isSignedIn = false;
		statistics = null;
	}

	public void setDictionaries(ArrayList<Dictionary> dicts){
		dictionaries = dicts;
	}
	public String getLocalDictionary(){
		return localDictionary;
	}
	public void setLocalDictionary(String name){
		localDictionary = name;
	}
	public ArrayList<Dictionary> getDictionaries(){
		return dictionaries;
	}
	
	public Dictionary getChosenDictionary(){
		return chosenDictionary;
	}
	public void setChosenDictionary(Dictionary chosenDictionary){
		this.chosenDictionary = chosenDictionary;
	}
	
	public void chooseDictionary(int index){
		chosenDictionary = dictionaries.get(index);
	}
	
	public void setDictionaryWords(ObservableList<Words> words){
		dictionaryWords = words;
	}
	
	public ObservableList<Words> getDictionaryWords(){
		return dictionaryWords;
	}

	public void setSignedIn(boolean signin){
		isSignedIn = signin;
	}
	public Boolean isSignedIn(){
		return isSignedIn;
	}

	
	public void setStatistics(Statistics stat){
		statistics = stat;
	}
	public Statistics getStatistics(){
		return statistics;
	}
}
