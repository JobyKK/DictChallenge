package maincontol;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import controllers.MainController;
 

public class ProgramNavigator {
 
	public static final String START_PANE =      "/views/start_view.fxml";
	public static final String MENU =            "/views/menu_view.fxml";
	public static final String MAIN =            "/views/main.fxml";
	public static final String TEST_VIEW =       "/views/test_view.fxml";
	public static final String TEST_SETTINGS =   "/views/test_settings.fxml";
	public static final String RESULT = 		 "/views/result.fxml";
	public static final String DICTIONARY_SHOW = "/views/dictionary_show.fxml";
	public static final String DICTIONARY_VIEW = "/views/dictionary_view.fxml";
	public static final String DICTIONARY_LOAD = "/views/dictionary_load.fxml";
	
	private static MainController mainController;
 

	public static void setMainController(MainController mainController) {
		ProgramNavigator.mainController = mainController;
	}
	 
	public static void loadPane(String fxml) {
		try {
			mainController.setPane(
					FXMLLoader.load(
							ProgramNavigator.class.getResource(
									fxml
									)
							)
					);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
}