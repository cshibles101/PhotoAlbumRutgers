package photoAlbum.view;


import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.User;

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
	
	@FXML
	private void initialize(){
		
	}
	
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
		
	}
	@FXML
	private void handleExit(Event e){
		
		//System.out.println("handleExit");
		System.exit(1);
		
	}
	
	public void setMainApp(PhotoAlbum photoAlbum) {
		
		this.photoAlbum = photoAlbum;
		userList = photoAlbum.getUsers();
		
	}
}
