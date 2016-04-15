/**
 * @author Christopher Shibles
 * @author Randy Mester
 */
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
/**
 * Controls the album renaming window
 *
 */
public class RenameController {
	
	private PhotoAlbum photoAlbum;
	
	private Album album;
	
	private User activeUser;
	
	private String oldName;
	
	@FXML
	private TextField albumName;
	@FXML
	private Button change;
	@FXML
	private Button cancel;
	@FXML
	private Label exists;
	@FXML
	private Label valid;
	
	/**
	 * Constructor for controller
	 */
	public RenameController(){
		
	}
	/**
	 * Changes album name
	 * @param e
	 */
	@FXML
	private void handleChange(Event e){
		
		exists.setVisible(false);
		valid.setVisible(false);
		
		User temp = activeUser;
		List<Album> albums = temp.getAlbums();
		String name = albumName.getText().trim();
		
		Album holder = null;
		
		for(int x = 0; x < albums.size(); x++){
			if(albums.get(x).getName().equals(oldName))
				holder = albums.get(x);
			
		}
		
		
		
		boolean success = true;
		
		if(name != null && !name.isEmpty()){
			if(album.getName().equals(name)){
				albumName.requestFocus();
				Stage stage = (Stage) change.getScene().getWindow();
				stage.close();
			}
			else{
				Album newAlbum = new Album(name);
				for(int x = 0; x < albums.size(); x++){
					if(newAlbum.equals(albums.get(x))){
						exists.setVisible(true);
						albumName.requestFocus();
						success = false;
						break;
					}
				}
			}
		} else{
			valid.setVisible(true);
			success = false;
		}
		
		if(success){
			holder.setName(name);
			albumName.requestFocus();
			activeUser.updateAlbums(albums);
			Stage stage = (Stage) change.getScene().getWindow();
			stage.close();
		}
		
		
	}
	
	/**
	 * Cancels album name change
	 * @param e
	 */
	@FXML
	private void handleCancel(Event e){
		Stage stage;
		albumName.requestFocus();
		exists.setVisible(false);
		stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
	/**
	 * Sets selected album
	 * @param user
	 * @param name
	 * @param photoAlbum
	 */
	public void setAlbum(User user, Album name, PhotoAlbum photoAlbum) {
		
		this.photoAlbum = photoAlbum;
		activeUser = user;
		this.album = name;
		oldName = album.getName();
		albumName.setText(album.getName());
	
	}
	
	
}
