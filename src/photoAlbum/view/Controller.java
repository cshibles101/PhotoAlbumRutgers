package photoAlbum.view;

import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.User;

public class Controller {
	
	private PhotoAlbum photoAlbum;
	
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Label incorrect;
	
	public Controller() {
		
		
		
	}
	
	@FXML
	private void handleLogin(){
		incorrect.setVisible(false);
		if(usernameField.getText().equals("Admin")){
			if(!passwordField.getText().equals("AdMiN2016"))
				incorrect.setVisible(true);
			else{
				try{
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/photoAlbum/view/Admin.fxml"));
		            Parent root1;
					root1 = (Parent) fxmlLoader.load();
		            PhotoAlbum.primaryStage.setTitle("Admin - User List");
		            PhotoAlbum.primaryStage.setScene(new Scene(root1));
		            
		            
		            
					}
		            catch (IOException e) {
						e.printStackTrace();
					}
			}
		}else{
		
		User temp = new User(usernameField.getText(), passwordField.getText());
		
		List<User> users = PhotoAlbum.getList();
		boolean loginSuccess = false;
		int num = users.size();
		int index = 0;
		while(index < num){
			
			if(temp.equals(users.get(index))){
				try{
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/photoAlbum/view/AlbumList.fxml"));
	            Parent root1;
				root1 = (Parent) fxmlLoader.load();
	            PhotoAlbum.primaryStage.setTitle(usernameField.getText()+" Photo Albums");
	            PhotoAlbum.primaryStage.setScene(new Scene(root1));
	            
	            
				}
	            catch (IOException e) {
					e.printStackTrace();
				}
				
				loginSuccess = true;
				
			}
			index++;
			
		}
		if(!loginSuccess){
			incorrect.setVisible(true);
		}
		}
	}
	
	@FXML
	private void handleExit(){
	
		System.exit(0);
		
	}
	
	

	public void setMainApp(PhotoAlbum photoAlbum) {
		this.photoAlbum = photoAlbum;
		
		
		
	}
	

}
