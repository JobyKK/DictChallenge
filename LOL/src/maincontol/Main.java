package maincontol;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import controllers.MainController;
import logic.Program;
 
/**
* Main application class.
*/
public class Main extends Application {
 
@Override
public void start(Stage stage) throws Exception{
	stage.setTitle("DictChallenge");
	stage.setResizable(false);//set resize!!!
	stage.setScene(
			createScene(
					loadMainPane()
					)
			);
 
	stage.show();
}
 
/**
* Loads the main fxml layout.
* Sets up the vista switching VistaNavigator.
* Loads the first vista into the fxml layout.
*
* @return the loaded pane.
* @throws IOException if the pane could not be loaded.
*/
private Pane loadMainPane() throws IOException {
	FXMLLoader loader = new FXMLLoader();
 
	System.out.println("Suffer");
	
	Pane mainPane = (Pane) loader.load(
			getClass().getResourceAsStream(
					ProgramNavigator.MAIN
					)
			);
 
	MainController mainController = loader.getController();
 
	ProgramNavigator.setMainController(mainController);
	ProgramNavigator.loadPane(ProgramNavigator.START_PANE);
 
	return mainPane;
}
 
/**
* Creates the main application scene.
* @param mainPane the main application layout.
* @return the created scene.
*/
private Scene createScene(Pane mainPane) {
	Scene scene = new Scene(mainPane,600,450);
 
	scene.getStylesheets().setAll(
			getClass().getResource("/views/vista.css").toExternalForm()
			);
 
	return scene;
}
 
public static void main(String[] args) {
	new Program();
	launch(args);
	System.out.println("EXIT");
	Program.closeSessionFactory();
	Platform.exit();
}
}