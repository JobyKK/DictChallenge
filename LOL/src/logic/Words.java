package logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Words {
	private String word1;
	private String word2;
	private String lang1;
	private String lang2;
	
	public Words(String w1, String w2, String l1, String l2){
		word1 = w1;
		word2 = w2;
		lang1 = l1;
		lang2 = l2;
	}
	public Words(String w1, String w2){
		word1 = w1;
		word2 = w2;
	}
	public String getWord(int i){
		if(i == 0)
			return word1;
		else 
			return word2;
	}
	public StringProperty getWord1(){
		return new SimpleStringProperty(word1);
	}
	public StringProperty getWord2(){
		return new SimpleStringProperty(word2);
	}
	public String getLanguage1(){
		return lang1;
	}
	public String getLanguage2(){
		return lang2;
	}
}
