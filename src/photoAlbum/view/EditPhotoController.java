package photoAlbum.view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
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
	TextArea captionField;
	
	@FXML
	TextArea tagField;
	
	@FXML
	private ChoiceBox<Album> albumChoice;
	
	@FXML
	private Button okBtn;
	
	@FXML
	private Button cancelBtn;
	
	
	@FXML
	public void initialize(){
		
	}
	
	
	@FXML
	public void handleOk(Event e){
		
		
		photo.setCaption(captionField.getText());
		
		tagSplit = tagField.getText().split(",");
		for(String s:tagSplit){
<<<<<<< HEAD
			photo.addTag(s);
			
		destAlbum = albumChoice.getSelectionModel().getSelectedItem();	
			
		if(destAlbum == null);
		else if (destAlbum.toString().equals(activeAlbum.toString()));
		else {
			destAlbum.addPhoto(photo);
			activeAlbum.deletePhoto(photoIndex);
=======
			if(!s.isEmpty())
				photo.addTag(s.trim());
>>>>>>> 1725feeff19d57d36fe183584edcf8f50aea8848
		}
		
		Stage stage = (Stage) okBtn.getScene().getWindow();
		stage.close();
			
<<<<<<< HEAD
		}		
=======
		
		
		
>>>>>>> 1725feeff19d57d36fe183584edcf8f50aea8848
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
<<<<<<< HEAD
		albumChoice.setItems(activeUser.getObservableList());
=======
		albumChoice = new ChoiceBox<Album>(activeUser.getObservableList());
>>>>>>> 1725feeff19d57d36fe183584edcf8f50aea8848
	}
	
	
	
}
