/**
 * @author Christopher Shibles
 * @author Randy Mester
 */
package photoAlbum.view;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.User;
/**
 * Controller for login window
 *
 */
public class LoginController {
	
	private PhotoAlbum photoAlbum;
	@FXML
	private Label loginLabel;
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button login;
	@FXML
	private Button Exit;
	
	private List<User> userList;
	
	public LoginController(){
		
	}
	/**
	 * Initializer
	 */
	@FXML
	private void initialize(){
		
	}
	/**
	 * Checks if username and password match. If they do, user is 
	 * taken to their list of albums.
	 * @param e
	 */
	@FXML
	private void handleLogin(Event e){
		loginLabel.setVisible(false);
		String uField = usernameField.getText();
		String pField = passwordField.getText();
		
		if(uField != null && uField.equals("Admin")){
			if(pField != null && pField.equals("Password")){
				loginLabel.setVisible(false);
				photoAlbum.getStage().setScene(photoAlbum.getScene("admin"));
				photoAlbum.getStage().setTitle("Admin");
				usernameField.requestFocus();
				passwordField.clear();
			}
			else{
				loginLabel.setVisible(true);
				passwordField.clear();
			}
		}
		else{
			
			boolean logSuc = false;
			
			for(int x = 0; x < userList.size(); x++){
				if(userList.get(x).loginMatch(uField, pField)){
					logSuc = true; 
					try{
						FXMLLoader loader1 = new FXMLLoader();
						loader1.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/RootLayout.fxml"));
						BorderPane userRoot = (BorderPane) loader1.load();
						
						Scene user = new Scene(userRoot);
						
						FXMLLoader loader2 = new FXMLLoader();
						loader2.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/AlbumList.fxml"));
						AnchorPane userAnchor = (AnchorPane) loader2.load();
						

						userRoot.setCenter(userAnchor);
						
						//user
				        UserController userController;
				        userController = loader2.getController();
				        userController.setMainApp(photoAlbum, userList.get(x), 0);
				        
				        photoAlbum.getStage().setScene(user);
						photoAlbum.getStage().setTitle(uField+" Album List");
					
					}catch(Exception exc){
						exc.printStackTrace();
					}
					
					
					usernameField.requestFocus();
					passwordField.clear();
					break;
				}
			}
			if(!logSuc){
				loginLabel.setVisible(true);
				passwordField.clear();
				usernameField.requestFocus();
			}
		}
	/**
	 * Exits program	
	 */
	}
	@FXML
	private void handleExit(Event e){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure you want to exit?");
		alert.setContentText("All work done will be saved upon exit.");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			photoAlbum.Serialize();
			System.exit(1);
		}
		
	}
	/**
	 * Sets user list
	 * @param photoAlbum
	 */
	public void setMainApp(PhotoAlbum photoAlbum) {
		
		photoAlbum.readBack();
		this.photoAlbum = photoAlbum;
		userList = photoAlbum.getUsers();
		photoAlbum.updateUsers(userList);
		
		
	}
}
