package photoAlbum.view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
	private Label limits;
	
	
	@FXML
	public void initialize(){
		
	}
	
	
	@FXML
	public void handleOk(Event e){
		limits.setVisible(false);
		if(tagField.getText() != null)
			tagSplit = tagField.getText().trim().split(",");
			
		
		
		if(captionField.getText().length() > 50 ||(tagField.getText() != null && tagSplit.length > 5)){
			limits.setVisible(true);
		}
		else{
			photo.getTags().clear();
			photo.setCaption(captionField.getText());
			if(tagField.getText() != null){
				photo.setTagsString(tagField.getText().trim());
				for(String s:tagSplit){
					if(!s.isEmpty())
						photo.addTag(s.trim());
				}
			}
		photo.setCaption(captionField.getText());
		
		tagSplit = tagField.getText().split(",");
		for(String s:tagSplit){

			if(!s.isEmpty())
			photo.addTag(s.trim());
			
		destAlbum = albumChoice.getSelectionModel().getSelectedItem();	
			
		if(destAlbum == null);
		else if (destAlbum.toString().equals(activeAlbum.toString()));
		else {
			destAlbum.addPhoto(photo);
			activeAlbum.deletePhoto(photoIndex);
		}
		Stage stage = (Stage) okBtn.getScene().getWindow();
		stage.close();

		}
		}

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

		albumChoice.setItems(activeUser.getObservableList());


		
		captionField.setText(photo.getCaption());
		tagField.setText(photo.getTagsString());
		

		albumChoice.setItems(activeUser.getObservableList());


	}
	
	
	
}
