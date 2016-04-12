package photoAlbum.view;

import java.util.List;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.User;

public class NewUserDialogController {
	
	private PhotoAlbum photoAlbum;
	
	private List<User> userList;
	
	//New User
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private TextField passwordCheck;
	@FXML
	private Button ok;
	@FXML
	private Button newUserCancel;
	@FXML
	private Label passwords;
	@FXML
	private Label exists;
	@FXML
	private Label other;
	
	//New Album

	
	public NewUserDialogController(){
		
	}
	
	@FXML
	public void initialize(){
		
	}
	
	
	
	
	@FXML
	private void handleAdd(Event e){
		
		userList = photoAlbum.getUsers();
		
		exists.setVisible(false);
		other.setVisible(false);
		passwords.setVisible(false);
		
		boolean success = true;
		
		String passwordField = password.getText(); 
		String passwordCheckField = passwordCheck.getText();
		String usernameField = username.getText();
		if((passwordField != null && !passwordField.isEmpty() && !passwordField.trim().isEmpty()) &&
				(passwordCheckField != null && !passwordCheckField.isEmpty() && !passwordCheckField.trim().isEmpty())&&
				(usernameField != null && !usernameField.isEmpty() && !usernameField.trim().isEmpty())){
			if(passwordField.equals(passwordCheckField)){
				User newUser = new User(usernameField, passwordField);
				for(int x = 0; x < userList.size(); x++){
					if(newUser.equals(userList.get(x))){
						exists.setVisible(true);
						username.requestFocus();
						success = false;
						break;
					}
				}
				if(success){
					userList.add(new User(usernameField, passwordField));
					photoAlbum.updateUsers(userList);
					username.clear();
					password.clear();
					passwordCheck.clear();
					username.requestFocus();
					Stage stage = (Stage) ok.getScene().getWindow();
					stage.close();
					
				}
				
			}
			else{
				passwords.setVisible(true);
				password.requestFocus();
			}
				
		}
		else{
			other.setVisible(true);
		}
	}
	@FXML
	private void handleCancel(Event e){
		Stage stage;
			username.requestFocus();
			exists.setVisible(false);
			other.setVisible(false);
			passwords.setVisible(false);
			username.clear();
			password.clear();
			passwordCheck.clear();
			stage = (Stage) newUserCancel.getScene().getWindow();
			stage.close();
		
		
		
		
	}
	
	
	
	public void setMainApp(PhotoAlbum photoAlbum) {
		
		this.photoAlbum = photoAlbum;
		userList = photoAlbum.getUsers();
		
	}
	
	
}
