package photoAlbum.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import photoAlbum.model.Photo;
import photoAlbum.model.User;
import photoAlbum.view.AdminController;
import photoAlbum.view.LoginController;
import photoAlbum.view.NewUserDialogController;

public class PhotoAlbum extends Application{

	private Stage mainStage;
	private BorderPane loginRoot, adminRoot, newUserRoot;
	private AnchorPane loginAnchor, adminAnchor, newUserAnchor;
	private Scene login, admin, newUser;
	
	private List<User> userList = new ArrayList<User>();
	private ObservableList<User> userData = FXCollections.observableArrayList();
	
	public PhotoAlbum(){
		
		userList.add(new User("Kevin", "tall"));
		userList.add(new User("Bob", "short"));
		userList.add(new User("Gru", "nose"));
		userList.add(new User("Stuart", "eye"));
		userList.add(new User("Dave", "small"));
		
		userList.get(2).newAlbum("Noses");
		userList.get(2).newAlbum("Evil");
		userList.get(2).newAlbum("The Girls");
		
		userList.get(2).getAlbums().get(0).setPhotoCount(5);
		userList.get(2).getAlbums().get(0).addPhoto(new Photo(new Image("http://g-ecx.images-amazon.com/images/G/01/aplusautomation/vendorimages/65fa961e-8f22-4fe6-a420-3c3c26dd2953._CB289161999__SL300__.jpg")));
		userList.get(2).getAlbums().get(0).getPhotos().get(0).setCaption("MINIONS!");
		userList.get(2).updateAlbums(userList.get(2).getAlbums());
		
		
		
		for (User i : userList) 
			 userData.add(i);
		
	}
	
	
	public void start(Stage mainStage) throws Exception {
		this.mainStage = mainStage;
		this.mainStage.setTitle("Photo Album Login");
		this.mainStage.setResizable(false);
		
		initRootLayout();
		
		showLogin();
		
		
		
	}
	
	public void initRootLayout(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/RootLayout.fxml"));
			loginRoot = (BorderPane) loader.load();
			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/RootLayout.fxml"));
			adminRoot = (BorderPane) loader2.load();
			
			
			


			
			
			FXMLLoader loader5 = new FXMLLoader();
			loader5.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/RootLayout.fxml"));
			newUserRoot = (BorderPane) loader5.load();
			
			
			
			login = new Scene(loginRoot);
			admin = new Scene(adminRoot);
			
			
			newUser = new Scene(newUserRoot);
			
			
            mainStage.setScene(login);
            mainStage.show();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	 public void showLogin() {
		 try {

			 FXMLLoader loader = new FXMLLoader();
			 
			 loader.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/Login.fxml"));
			 loginAnchor = (AnchorPane) loader.load();
			 
			 FXMLLoader loader2 = new FXMLLoader();
			 loader2.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/Admin.fxml"));
			 adminAnchor = (AnchorPane) loader2.load();
			 
			 
			 
			 
			 
			 FXMLLoader loader5 = new FXMLLoader();
			 loader5.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/AddUser.fxml"));
			 newUserAnchor = (AnchorPane) loader5.load();
			 
			 
			 
			 loginRoot.setCenter(loginAnchor);
			 adminRoot.setCenter(adminAnchor);
			 newUserRoot.setCenter(newUserAnchor);
			 

			 //login   
			 LoginController controller;
			 controller = loader.getController();
	         controller.setMainApp(this);
	         
	         //admin
	         AdminController adminController;
	         adminController = loader2.getController();
	         adminController.setMainApp(this);
	         
	         //New User dialog
	         NewUserDialogController newUserdialogController;
	         newUserdialogController = loader5.getController();
	         newUserdialogController.setMainApp(this);
	         
	         //New Album dialog
	         
	            
	        } catch (Exception e){
	        	e.printStackTrace();
	        }
	        
	        
	 }
	 
	 public Stage getStage(){
		 return mainStage;
	 }
	 
	 public Scene getScene(String scene){
		 
		 if(scene.equalsIgnoreCase("login"))
			 return login;
		 if(scene.equalsIgnoreCase("admin"))
			 return admin;
		 return newUser;
					
		 
	 }
	 
	 public List<User> getUsers(){
		 return userList;
	 }
	 public ObservableList<User> getObservableList(){
		 return userData;		 
	 }
	 
	 public void updateUsers(List<User> users){
		 
		 userList = users;
		 userData.clear();
		 for (User i : userList) 
			 userData.add(i);
		 
	 }
	 
	 
	 
	 public static void main(String[] args) throws IOException {
			launch(args);
		}
	 
	
	
	
	
	
}
