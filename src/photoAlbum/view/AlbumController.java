package photoAlbum.view;

import java.util.List;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.Photo;
import photoAlbum.model.User;

public class AlbumController {

	private int index;
	
	private User activeUser;
	
	private Album activeAlbum;
	
	private PhotoAlbum photoAlbum;
	
	@FXML
	private MenuBar menuBar;
	@FXML
	private Menu menuFile;
	@FXML
	private MenuItem closeAlbum;
	@FXML
	private MenuItem logout;
	@FXML
	private MenuItem exit;
	@FXML
	private Menu menuEdit;
	@FXML
	private MenuItem editPhoto;
	@FXML
	private MenuItem addPhoto;
	@FXML
	private MenuItem removePhoto;
	@FXML
	private Menu search;
	@FXML
	private MenuItem searchPhotos;
	@FXML
	private Button next;
	@FXML
	private Button last;
	@FXML
	private ImageView mainView;
	@FXML
    private TableView<Photo> imageTable;
    @FXML
    private TableColumn<Photo, Image>imageColumn;
    @FXML
    private TableColumn<Photo, String>captionColumn;
    
    private List<Photo> photoList;
	
	
	
	public AlbumController(){
		
	}
	
	@FXML
	public void initialize(){
		
		//imageColumn.setCellValueFactory(cellData -> cellData.getValue().photoProp());
		captionColumn.setCellValueFactory(cellData -> cellData.getValue().captionProperty());
		
		imageTable.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> displayImage(newValue));
	}
	
	@FXML
	private void handleExit(Event e){
		
		//System.out.println("handleExit");
		System.exit(1);
		
	}
	@FXML
	private void handleLogout(Event e){
		
		//System.out.println(photoAlbum);
		photoAlbum.getStage().setScene(photoAlbum.getScene("login"));
		photoAlbum.getStage().setTitle("Photo Album Login");
		
	}
	@FXML
	private void handleCloseAlbum(Event e){
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
	        userController.setMainApp(photoAlbum, activeUser, index);
	        
	        photoAlbum.getStage().setScene(user);
			photoAlbum.getStage().setTitle(activeUser.getUsername()+" Album List");
			
		} catch(Exception exc){
			exc.printStackTrace();
		}
		
	}
	
	public void displayImage(Photo photo){
		
		mainView.setImage(photo.getImage());
		
	}
	
	
	public void setAlbum(User user, Album album, PhotoAlbum photoAlbum, int index) {
		
		this.index = index;
		this.photoAlbum = photoAlbum;
		activeAlbum = album;
		activeUser = user;
		
		photoList = album.getPhotos();
		imageTable.setItems(album.getObservableList());
		
	}
}
