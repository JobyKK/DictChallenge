package logic;

/** Class for operations with languages*/
public class LanguageController {

	private static String language1Net;
	private static String language2Net;
	private static String language1Local;
	private static String language2Local;
	
	public static String getLanguage1Net(){
		return language1Net;
	}
	public static String getLanguage2Net(){
		return language2Net;
	}
	
	public void setLanguage1Local(String language){
		language1Local = language;
	}
	public void setLanguage2Local(String language){
		language2Local = language;
	}
	
	public static void setLanguage1Net(String language){
		language1Net = language;
	}
	public static void setLanguage2Net(String language){
		language2Net = language;
	}
	
	public String getLanguage1Local(){
		return language1Local;
	}
	public String getLanguage2Local(){
		return language2Local;
	}
	
}
