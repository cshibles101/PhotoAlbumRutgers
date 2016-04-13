package photoAlbum.view;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.Photo;
import photoAlbum.model.User;

public class EditPhotoController {
	
	private Photo photo;
	private Album activeAlbum;
	private User activeUser;
	private PhotoAlbum photoAlbum;
	private int photoIndex;
	
	private String caption;
	private ArrayList<String> tags;
	private Album destAlbum;
	
	@FXML
	TextField captionfield;
	
	@FXML
	TextField tagField;
	
	@FXML
	ChoiceBox<Album> albumChoice;
	
	@FXML
	Button okBtn;
	
	@FXML
	Button cancelBtn;
	
	@FXML
	public void handleOk(Event e){
		
	}
	
	@FXML
	public void handleCancel(Event e){
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}
	
	
	public void setMainApp(Photo photo, Album activeAlbum, User activeUser, PhotoAlbum photoAlbum, int photoIndex){
		this.photo = photo;
		this.activeAlbum = activeAlbum;
		this.activeUser = activeUser;
		this.photoAlbum = photoAlbum;
		this.photoIndex = photoIndex;
	}
	
	
	
}
