package photoAlbum.view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.Photo;
import photoAlbum.model.User;

public class EditPhotoController {
	
	private Photo photo;
	private Album activeAlbum;
	private User activeUser;
	private PhotoAlbum photoAlbum;
	
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
	
	
	public void handleOk(Event e){
		
	}
	
	public void handleCancel(Event e){
		
	}
	
	
	public void setMainApp(Photo photo, Album activeAlbum, User activeUser, PhotoAlbum photoAlbum){
		this.photo = photo;
		this.activeAlbum = activeAlbum;
		this.activeUser = activeUser;
		this.photoAlbum = photoAlbum;
	}
	
	
	
}
