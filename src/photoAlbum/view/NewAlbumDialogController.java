package photoAlbum.view;

import java.util.List;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.User;

public class NewAlbumDialogController {
	
	private PhotoAlbum photoAlbum;
	
	private User activeUser;
	
	@FXML
	private Label albumExists;
	@FXML
	private Label blankAlbum;
	@FXML
	private TextField albumName;
	@FXML
	private Button newAlbumAdd;
	@FXML
	private Button newAlbumCancel;
	
	@FXML
	public void handleNewAlbum(Event e){
		
		albumExists.setVisible(false);
		blankAlbum.setVisible(false);
		
		User temp = activeUser;
		List<Album> albums = temp.getAlbums();
		String name = albumName.getText().trim();
		
		boolean success = true;
		
		if(name != null && !name.isEmpty()){
			Album newAlbum = new Album(name);
			for(int x = 0; x < albums.size(); x++){
				if(newAlbum.equals(albums.get(x))){
					albumExists.setVisible(true);
					albumName.requestFocus();
					success = false;
					break;
				}
			}
			
		}
		else{
			blankAlbum.setVisible(true);
			success = false;
		}
		
		if(success){
			activeUser.newAlbum(name);
			albumName.clear();
			albumName.requestFocus();
			activeUser.updateAlbums(activeUser.getAlbums());
			Stage stage = (Stage) newAlbumAdd.getScene().getWindow();
			stage.close();
		}

		
		
	}
	
	@FXML
	public void handleCancel(Event e){
		
		Stage stage = (Stage) newAlbumCancel.getScene().getWindow();;
		albumName.requestFocus();
		albumExists.setVisible(false);
		blankAlbum.setVisible(false);
		albumName.clear();
		stage.close();
		
	}
	
	public void setMainApp(PhotoAlbum photoAlbum, User username) {
		
		this.photoAlbum = photoAlbum;
		activeUser = username;
		
	}
}
