package photoAlbum.view;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
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
	
	private String[] tagSplit;
	private Album destAlbum;
	
	@FXML
	private TextArea captionField;
	
	@FXML
	private TextArea tagField;
	
	@FXML
	private ChoiceBox<Album> albumChoice;
	
	@FXML
	private Button okBtn;
	
	@FXML
	private Button cancelBtn;
	
	@FXML
	public void handleOk(Event e){
		
		photo.setCaption(captionField.getText());
		
		tagSplit = tagField.getSelectedText().split(",");
		for(String s:tagSplit){
			photo.addTag(s);
		
		System.out.println(photo.toString());
		
		
		
		Stage stage = (Stage) okBtn.getScene().getWindow();
		stage.close();
			
		}
		
		
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