/**
 * @author Christopher Shibles
 * @author Randy Mester
 */
package photoAlbum.view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.Photo;
import photoAlbum.model.Tag;
import photoAlbum.model.User;
/**
 * Controller for photo editing window
 *
 */
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
	
	
	/**
	 * Initializer
	 */
	@FXML
	public void initialize(){
		
	}
	
	/**
	 * Sets designated caption and tags to selected photo.
	 * If selected album is different than photo's album,
	 * photo moves to new album.
	 * @param e
	 */
	@FXML
	public void handleOk(Event e){
		limits.setVisible(false);
		if(tagField.getText() != null)
			tagSplit = tagField.getText().trim().toLowerCase().split(",| ");
			
		
		
		if(captionField.getText().length() > 50 ||(tagField.getText() != null && tagSplit.length > 5)){
			limits.setVisible(true);
		}
		else{
			if(!photo.getTags().isEmpty()){	
				for(String s: photo.getTags()){
					activeUser.getHash().get(s).deletePhoto(photo);
				}
			}
			photo.getTags().clear();
			
			photo.setCaption(captionField.getText());
			if(tagField.getText() != null){
				photo.setTagsString(tagField.getText().trim());
				for(String s:tagSplit){
					if(!s.isEmpty()){
						if(activeUser.getHash().get(s) != null){
							activeUser.getHash().get(s).deletePhoto(photo);
							activeUser.getHash().get(s).addPhoto(photo);
							
						}
						else{
							activeUser.getHash().put(s, new Tag(s, photo));
							
						}
						photo.getTags().add(s);
					}
				}
			}
			
			photo.setCaption(captionField.getText());
				
			destAlbum = albumChoice.getSelectionModel().getSelectedItem();	
				
			if(destAlbum == null || destAlbum.toString().equals(activeAlbum.toString()));
			else {
				destAlbum.addPhoto(photo);
				activeAlbum.deletePhoto(photoIndex);
			}
			Stage stage = (Stage) okBtn.getScene().getWindow();
			stage.close();
	
			
		}

			
	}
	
	/**
	 * Returns to album window
	 * @param e
	 */
	@FXML
	public void handleCancel(Event e){
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Sets selected photo, active user, active album, and index
	 * of photo in album's list of photos
	 * @param photo
	 * @param activeAlbum
	 * @param activeUser
	 * @param photoAlbum
	 * @param photoIndex
	 */
	public void setMainApp(Photo photo, Album activeAlbum, User activeUser, PhotoAlbum photoAlbum, int photoIndex){
		this.photo = photo;
		this.activeAlbum = activeAlbum;
		this.activeUser = activeUser;
		this.photoAlbum = photoAlbum;
		this.photoIndex = photoIndex;
		albumChoice.setItems(activeUser.getObservableList());
		
		captionField.setText(photo.getCaption());
		tagField.setText(photo.getTagsString());


	}
	
	
	
}
