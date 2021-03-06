/**
 * @author Christopher Shibles
 * @author Randy Mester
 */
package photoAlbum.application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import photoAlbum.model.Album;
import photoAlbum.model.Photo;
import photoAlbum.model.User;
import photoAlbum.view.AdminController;
import photoAlbum.view.LoginController;
import photoAlbum.view.NewUserDialogController;
/**
 * This class implements a photo album.
 * 
 *
 */
public class PhotoAlbum extends Application{

	static Stage mainStage;
	private BorderPane loginRoot, adminRoot, newUserRoot;
	private AnchorPane loginAnchor, adminAnchor, newUserAnchor;
	private Scene login, admin, newUser;
	
	private List<User> userList = new ArrayList<User>();
	private ObservableList<User> userData = FXCollections.observableArrayList();
	/**
	 * Initializes an instance of photo album with example users and previously saved users.
	 */
	public PhotoAlbum(){
		
		
		
	}
	
	/**
	 * Starts photo album program and sets up login window.
	 */
	public void start(Stage mainStage) throws Exception {
		this.mainStage = mainStage;
		this.mainStage.setTitle("Photo Album Login");
		this.mainStage.setResizable(false);
		
		
		
		initRootLayout();
		
		showLogin();
		
		
		
		
		
	}
	/**
	 * Sets up window pane and loads login, admin, and newUser root.
	 * Sets scene.
	 */
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
	/**
	 * Opens login window and sets login controller.
	 * Loads anchor panes for login, admin, and newUser.
	 */
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
	 /**
	  * Refers to mainStage
	  * @return mainStage
	  */
	 public Stage getStage(){
		 return mainStage;
	 }
	 /**
	  * Refers to login if scene is set to login
	  * and admin if scene is set to admin.
	  * @param scene
	  * @return newUser
	  */
	 public Scene getScene(String scene){
		 
		 if(scene.equalsIgnoreCase("login"))
			 return login;
		 if(scene.equalsIgnoreCase("admin"))
			 return admin;
		 return newUser;
					
		 
	 }
	 /**
	  * Refers to list of all users.
	  * @return list of all users
	  */
	 public List<User> getUsers(){
		 return userList;
	 }
	 /**
	  * Refers to observableList of all users.
	  * @return userList in observable list form
	  */
	 public ObservableList<User> getObservableList(){
		 return userData;		 
	 }
	 /**
	  * Clears user observable list and resets it based on user list.
	  * @param users
	  */
	 public void updateUsers(List<User> users){
		 
		 userList = users;
		 userData.clear();
		 for (User i : userList) 
			 userData.add(i);
		 
	 }
	 
	 
	 public void Serialize(){
		 
		 int count = userList.size();
		 try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("persist.ser", false);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeInt(count);
	         for(int x = 0; x < count; x++){
	        	 out.writeObject(userList.get(x));
	        	 out.writeInt(userList.get(x).getAlbums().size());
	        	 int count2 = userList.get(x).getAlbums().size();
	        	 for(int y = 0; y < count2; y++){
	        		 out.writeInt(userList.get(x).getAlbums().get(y).getPhotoCount());
	        	 }
	         }        
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		 
		 
	 }
	 
	 public void readBack(){
		 
		 try
	      {
	         FileInputStream fileIn = new FileInputStream("persist.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         int count = in.readInt(); 
	         for(int x = 0; x < count; x++){
	        	 try {
					userList.add((User)in.readObject());
					User temp = userList.get(x);
					temp.setStringProp();
					temp.updateAlbums(temp.getAlbums());
					int count2 = in.readInt();
					for(int y = 0; y < count2; y++){
						Album tempAlbum = temp.getAlbums().get(y);
						tempAlbum.setStringProp();
						tempAlbum.updateAlbum(tempAlbum.getPhotos());
						int count3 = in.readInt();
						for(int z = 0; z < count3; z++){
							Photo tempPhoto = tempAlbum.getPhotos().get(z);
							tempPhoto.loadCaption();
							tempPhoto.loadImage();
						}
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	        	 
	        	 
	         }
	         in.close();
	         fileIn.close();
	      }catch(IOException i){
	      }
		 
	 }
	 
	 /**
	  * Main
	  * @param args
	  * @throws IOException
	  */
	 public static void main(String[] args) throws IOException {
		 	
		
		 
		 
		launch(args);
			
	}
	
	
	
	
	
	
}
