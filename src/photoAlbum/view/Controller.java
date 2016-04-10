package photoAlbum.view;

import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
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
	private Button login;
	@FXML
	private Button exit;
	@FXML
	private Label incorrect;
	@FXML
	private MenuBar menuBar;
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
	private void handleLogin(ActionEvent event){
		incorrect.setVisible(false);
		if(usernameField.getText().equals("Admin")){
			if(!passwordField.getText().equals(""))
				incorrect.setVisible(true);
			else{
				try{
					
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/photoAlbum/view/Admin.fxml"));
		            Parent root1;
					root1 = (Parent) fxmlLoader.load();
		            Scene adminScene = new Scene(root1);
		            Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		            
		            adminStage.setScene(adminScene);
		            adminStage.setTitle("Admin");
		            adminStage.show();
		            
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
				Scene userScene = new Scene(root1);
	            Stage userStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            
	            userStage.setScene(userScene);
	            userStage.setTitle(usernameField.getText()+" Photo Albums");
	            userStage.show();
				
				System.out.println(photoAlbum);
	            
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


		System.out.println(photoAlbum);
		
		System.exit(0);
	}
	
	@FXML
	private void handleLogout(ActionEvent event){
		
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/photoAlbum/view/Login.fxml"));
	        Parent root1;
			root1 = (Parent) fxmlLoader.load();
			Scene loginScene = new Scene(root1);
            Stage loginStage = (Stage) menuBar.getScene().getWindow();
            
            loginStage.setScene(loginScene);
            loginStage.setTitle("Photo Album Login");
            loginStage.show();
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
