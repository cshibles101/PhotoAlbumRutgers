package photoAlbum.view;

import java.awt.TextField;

import javafx.fxml.FXML;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.*;

public class LoginController {
	
	private PhotoAlbum photoAlbum;
	
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	
	public LoginController() {
		
	}
	
	@FXML
	private void handleLogin(){
		
		//User temp = new User(usernameField.getText(), passwordField.getText());

		
	}
	
	@FXML
	private void handleExit(){
		
		
	}

	public void setMainApp(PhotoAlbum photoAlbum) {
		this.photoAlbum = photoAlbum;
		
		
	}
	

}
