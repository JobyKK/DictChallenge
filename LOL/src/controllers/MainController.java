package controllers;
import logic.Program;
import maincontol.ProgramNavigator;
import model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
 
/**
* Main controller class for the entire layout.
*/
public class MainController{

	@FXML
	private StackPane vistaHolder;
	@FXML
	private AnchorPane mainPane;
	@FXML
	private Button btnMenu;
	@FXML
	private MenuButton btnsAccount;
	@FXML
	private void showMenu(ActionEvent event) {
		ProgramNavigator.loadPane(ProgramNavigator.MENU);
	}
	@FXML
	private void logIn(ActionEvent event){
		ProgramNavigator.loadPane(ProgramNavigator.START_PANE);
	}
	/**
	* Replaces the vista displayed in the vista holder with a new vista.
	*
	* @param node the vista node to be swapped in.
	*/
	public void setPane(Node node) {
		mainPane.getChildren().setAll(node);
	}
 
}