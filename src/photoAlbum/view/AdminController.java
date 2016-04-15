/**
 * @author Christopher Shibles
 * @author Randy Mester
 */
package photoAlbum.view;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.User;
/**
 * Controller for admin window
 *
 */
public class AdminController {
	
	private PhotoAlbum photoAlbum;	
	
	@FXML
	private Menu menuFile;
	@FXML
	private MenuBar menuBar;
	@FXML
	private MenuItem exit;
	@FXML
	private MenuItem logout;
	@FXML
	private Button delete;
	@FXML
	private Button add;
	@FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> userColumn;
    
    private List<User> userList;
	
	
	/**
	 * Constructor
	 */
	public AdminController(){
		
	}
	/**
	 * Initializer
	 */
	@FXML
	private void initialize(){
		userColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
	}
	/**
	 * Closes program
	 * @param e
	 */
	@FXML
	private void handleExit(Event e){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure you want to exit?");
		alert.setContentText("Would you like to save your changes?");
		ButtonType save = new ButtonType("Save");
		ButtonType dontSave = new ButtonType("Don't Save");
		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(save, dontSave, cancel);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == save){
			photoAlbum.Serialize();
			System.exit(1);
		}

		if (result.get() == cancel){}
		else{
			System.exit(1);
		}

	}
	/**
	 * Logs admin out and returns to login screen	
	 */
	@FXML
	private void handleLogout(Event e){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Would you like to save your changes?");
		ButtonType save = new ButtonType("Save");
		ButtonType dontSave = new ButtonType("Don't Save");
		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(save, dontSave, cancel);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == save){
			photoAlbum.Serialize();
			photoAlbum.getStage().setScene(photoAlbum.getScene("login"));
			photoAlbum.getStage().setTitle("Photo Album Login");
		}
		if (result.get() == cancel)
		{}
		else{
			photoAlbum.getStage().setScene(photoAlbum.getScene("login"));
			photoAlbum.getStage().setTitle("Photo Album Login");
		}
		
	}
	/**
	 * Deletes user from user list
	 * @param e
	 */
	@FXML
	private void handleDelete(Event e){
		
		int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
		if(userList.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR);
	        alert.initOwner(photoAlbum.getStage());
	        alert.setTitle("No Users");
	        alert.setHeaderText("There are no users to delete");

	        alert.showAndWait();
		}
		else if(selectedIndex > -1){
			Alert delete = new Alert(AlertType.CONFIRMATION);
			delete.setTitle("Delete");
			delete.setHeaderText("Are you sure you want to delete "+userTable.getItems().get(selectedIndex).getUsername()+"?");
			Optional<ButtonType> result = delete.showAndWait();
			if(result.get() == ButtonType.OK){
				for(int x = 0; x < userList.size(); x++){
					if(userList.get(x).equals(userTable.getItems().get(selectedIndex))){
						userList.remove(x);
						
						break;
					}
				}
				userTable.getItems().remove(selectedIndex);
				photoAlbum.updateUsers(userList);
				if(userList.size() > 0){
					if(selectedIndex < userList.size()){
						userTable.requestFocus();
						userTable.getSelectionModel().select(selectedIndex);
						userTable.getFocusModel().focus(selectedIndex);
					}
					else{
						userTable.requestFocus();
						userTable.getSelectionModel().select(selectedIndex-1);
						userTable.getFocusModel().focus(selectedIndex-1);
					}
				}
			}
		} else{
			Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(photoAlbum.getStage());
            alert.setTitle("No User Selected");
            alert.setHeaderText("No user selected");
            alert.setContentText("Please select a user you wish to delete.");

            alert.showAndWait();
		}
	}
	/**
	 * Adds new user and associated password to userlist
	 * @param e
	 */
	@FXML
	private void handleNew(Event e){
		
		try{
			
            Stage dialog = new Stage();
            
            dialog.setScene(photoAlbum.getScene("newUser"));
            dialog.setTitle("New User Info");
            dialog.showAndWait();
            
            userTable.requestFocus();
			userTable.getSelectionModel().select(userList.size()-1);
			userTable.getFocusModel().focus(userList.size()-1);

	        sortUserList();
            
            
		}catch(Exception exc){
			exc.printStackTrace();
		}
		
	}
	/**
	 * Sorts user list
	 */
	public void sortUserList()
    {
    	Comparator<User> c = (s1, s2) -> s1.getUsername().compareToIgnoreCase(s2.getUsername());
    	
    	photoAlbum.getObservableList().sort(c);
    }
	
	/**
	 * Sets user list in window
	 * @param photoAlbum
	 */
	public void setMainApp(PhotoAlbum photoAlbum) {
		
		this.photoAlbum = photoAlbum;
		userList = photoAlbum.getUsers();
		userTable.setItems(photoAlbum.getObservableList());
        sortUserList();
		if(!photoAlbum.getObservableList().isEmpty()){
			userTable.requestFocus();
			userTable.getSelectionModel().select(0);
			userTable.getFocusModel().focus(0);
		}

		
	}
	
	
}
