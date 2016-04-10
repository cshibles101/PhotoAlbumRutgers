package photoAlbum.view;

import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.User;

public class Controller {
	
	private PhotoAlbum photoAlbum;
	
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Label incorrect;
	@FXML
	private ListView<User> userList = new ListView<User>();
	@FXML
	private ListView<Album> userAlbums = new ListView<Album>();
	
	
	
	public Controller() {
		
	}
	
	@FXML
    private void initialize() 
    {
		
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
		            photoAlbum.getStage().setTitle("Admin - User List");
		            photoAlbum.getStage().setScene(new Scene(root1));
					}
		            catch (IOException e) {
						e.printStackTrace();
					}
				
				
				
				
				
			}
		}else{
		
		User temp = new User(usernameField.getText(), passwordField.getText());
		
		List<User> users = photoAlbum.getList();
		boolean loginSuccess = false;
		int num = users.size();
		int index = 0;
		while(index < num){
			
			if(temp.equals(users.get(index))){
				try{
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/photoAlbum/view/AlbumList.fxml"));
	            Parent root1;
				root1 = (Parent) fxmlLoader.load();
				photoAlbum.getStage().setTitle(usernameField.getText()+" Photo Albums");
				photoAlbum.getStage().setScene(new Scene(root1));
	            
	            //userAlbums.setItems(temp.getAlbums());
	            
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
	
	@FXML
	private void handleLogout(){
		
		
		
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/photoAlbum/view/Login.fxml"));
	        Parent root1;
			root1 = (Parent) fxmlLoader.load();
			photoAlbum.getStage().setTitle("Photo Album Login");
			photoAlbum.getStage().setScene(new Scene(root1));
		}
		catch(IOException e){
			
		}
	}
	@FXML
	private void handleDeleteAlbum(){
		int selectedIndex = userAlbums.getSelectionModel().getSelectedIndex();
		
		if(selectedIndex > -1){
			userAlbums.getItems().remove(selectedIndex);
		}
		
	}
	

	public void setMainApp(PhotoAlbum photoAlbum) {
		this.photoAlbum = photoAlbum;
		userList.setItems(photoAlbum.getList());
	}
	

}
