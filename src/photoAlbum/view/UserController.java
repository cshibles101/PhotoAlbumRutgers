/**
 * @author Christopher Shibles
 * @author Randy Mester
 */
package photoAlbum.view;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.User;
/**
 * Controller that controls the album list window for a user
 *
 */
public class UserController {

	private PhotoAlbum photoAlbum;	
	
	private User activeUser;
	
	@FXML
	private Label albumName;
	@FXML
	private Label photoCount;
	@FXML
	private Label newDate;
	@FXML
	private Label oldDate;
	@FXML
	private MenuBar menuBar;
	@FXML
	private Menu menuFile;
	@FXML
	private MenuItem logout;
	@FXML
	private MenuItem exit;
	@FXML
	private Menu menuEdit;
	@FXML
	private MenuItem newAlbum;
	@FXML
	private MenuItem rename;
	@FXML
	private MenuItem delete;
	@FXML
	private Button open;
	
	@FXML
    private TableView<Album> albumTable;
    @FXML
    private TableColumn<Album, String> albumColumn;
    
	
	
	public UserController(){
		
	}
	/**
	 * Sets up album listview
	 */
	@FXML
	private void initialize(){
		albumColumn.setCellValueFactory(cellData -> cellData.getValue().albumNameProperty());
		
		showAlbumDetails(null);
		
		albumTable.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showAlbumDetails(newValue));
	}
	
	/**
	 * Sets labels in window to reflect album's information
	 * @param album
	 */
	private void showAlbumDetails(Album album) {
	    if (album != null) {
	        albumName.setText(album.getName());
	        albumName.setAlignment(Pos.CENTER);
	        photoCount.setText(Integer.toString(album.getPhotoCount()));
	        photoCount.setAlignment(Pos.CENTER);
	        if(album.getNew() != null){
		        newDate.setText(album.getNew().getDateString());
		        newDate.setAlignment(Pos.CENTER);
		        oldDate.setText(album.getOld().getDateString());
		        oldDate.setAlignment(Pos.CENTER);
	        }

	    } else {
	    	albumName.setText("");
	        photoCount.setText("");
	        newDate.setText("");
	        oldDate.setText("");
	    }
	}

	/**
	 * Asks if user wants to exit program.
	 * Program closes if user clicks ok and returns
	 * to album list window if user clicks cancel.
	 * @param e
	 */
	@FXML
	private void handleExit(Event e){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure you want to exit?");
		alert.setContentText("Would you like to save your changes?");
		ButtonType save = new ButtonType("Save");
		ButtonType dontSave = new ButtonType("Don't Save");
		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(save, dontSave, cancel);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == save){
			photoAlbum.Serialize();
			System.exit(1);
		}
		if (result.get() == cancel){}
		else{
			System.exit(1);
		}
		
	}
	/**
	 * Asks if user wants to logout.
	 * Returns to login window if user clicks ok
	 * and returns to user's album list if user
	 * clicks cancel.
	 * @param e
	 */
	@FXML
	private void handleLogout(Event e){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Would you like to save your changes?");
		ButtonType save = new ButtonType("Save");
		ButtonType dontSave = new ButtonType("Don't Save");
		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(save, dontSave, cancel);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == save){
			photoAlbum.Serialize();
			photoAlbum.getStage().setScene(photoAlbum.getScene("login"));
			photoAlbum.getStage().setTitle("Photo Album Login");
		}
		if (result.get() == cancel){}
		else{
			photoAlbum.getStage().setScene(photoAlbum.getScene("login"));
			photoAlbum.getStage().setTitle("Photo Album Login");
		}
		
	}
	/**
	 * Opens NewAlbum window.
	 * @param e
	 */
	@FXML
	private void handleNewAlbum(Event e){
		
		
		try{
			FXMLLoader loader1 = new FXMLLoader();
			loader1.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/RootLayout.fxml"));
			BorderPane newAlbumRoot = (BorderPane) loader1.load();
			
			Scene newAlbum = new Scene(newAlbumRoot);
			
			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/NewAlbum.fxml"));
			AnchorPane newAlbumAnchor = (AnchorPane) loader2.load();
			
			newAlbumRoot.setCenter(newAlbumAnchor);
			
			NewAlbumDialogController newAblumDialogController;
	        newAblumDialogController = loader2.getController();
	        newAblumDialogController.setMainApp(photoAlbum, activeUser);
	        
	        Stage dialog = new Stage();
	        
	        dialog.setScene(newAlbum);
            dialog.setTitle("New Album Info");
            dialog.showAndWait();
            
            if(!activeUser.getAlbums().isEmpty()){
	            albumTable.requestFocus();
				albumTable.getSelectionModel().select(activeUser.getAlbums().size()-1);
				albumTable.getFocusModel().focus(activeUser.getAlbums().size()-1);
				showAlbumDetails(albumTable.getItems().get(activeUser.getAlbums().size()-1));
            }
			
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		
	}
	
	/**
	 * Asks user if they want to delete a selected album.
	 * If user clicks ok, album is delete. If user clicks
	 * cancel, user returns to user album list.
	 * @param e
	 */
	@FXML
	private void handleDelete(Event e){
		
		int selectedIndex = albumTable.getSelectionModel().getSelectedIndex();
		List<Album> list = activeUser.getAlbums();
		
		if(selectedIndex > -1){
			Alert delete = new Alert(AlertType.CONFIRMATION);
			delete.setTitle("Delete");
			delete.setHeaderText("Are you sure you want to delete this album?");
			delete.setContentText("Album to be deleted: "+list.get(selectedIndex).getName());
			Optional<ButtonType> result = delete.showAndWait();
			if(result.get() == ButtonType.OK){
				for(int x = 0; x < list.size(); x++){
					if(list.get(x).equals(albumTable.getItems().get(selectedIndex))){
						list.remove(x);
						
						break;
					}
				}
				
				albumTable.getItems().remove(selectedIndex);
				
			
				activeUser.updateAlbums(list);
				if(list.size() > 0){
					if(selectedIndex < list.size()){
						albumTable.requestFocus();
						albumTable.getSelectionModel().select(selectedIndex);
						albumTable.getFocusModel().focus(selectedIndex);
						showAlbumDetails(albumTable.getItems().get(selectedIndex));
					}
					else{
						albumTable.requestFocus();
						albumTable.getSelectionModel().select(selectedIndex-1);
						albumTable.getFocusModel().focus(selectedIndex-1);
						showAlbumDetails(albumTable.getItems().get(selectedIndex-1));
					}
				}
			}
		}
		else if(activeUser.getAlbums().isEmpty()){
			Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(photoAlbum.getStage());
            alert.setTitle("No Albums");
            alert.setHeaderText("There are no albums to delete");
            alert.showAndWait();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(photoAlbum.getStage());
            alert.setTitle("No Album Selected");
            alert.setHeaderText("You do not have an album selected");
            alert.setContentText("Please select an album to delete.");
            alert.showAndWait();
			
		}
	}
	/**
	 * Opens window to rename selected album
	 * @param e
	 */
	@FXML
	public void handleRename(Event e){
			
		int selectedIndex = albumTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex > -1){
			try{
				FXMLLoader loader1 = new FXMLLoader();
				loader1.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/RootLayout.fxml"));
				BorderPane renameRoot = (BorderPane) loader1.load();
				
				Scene rename = new Scene(renameRoot);
				
				FXMLLoader loader2 = new FXMLLoader();
				loader2.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/Rename.fxml"));
				AnchorPane renameAnchor = (AnchorPane) loader2.load();
				
		
				renameRoot.setCenter(renameAnchor);
				
		        RenameController renameController;
		        renameController = loader2.getController();
		        renameController.setAlbum(activeUser, albumTable.getItems().get(selectedIndex), photoAlbum);
		        
		        //System.out.println(albumTable.getItems().get(selectedIndex));
		        
		        Stage dialog = new Stage();
	            
	            dialog.setScene(rename);
	            dialog.setTitle("Rename Album");
	            dialog.showAndWait();
	            
	            albumTable.requestFocus();
				albumTable.getSelectionModel().select(selectedIndex);
				albumTable.getFocusModel().focus(selectedIndex);
	            showAlbumDetails(albumTable.getItems().get(selectedIndex));
		        
				
			} catch(Exception exc){
				exc.printStackTrace();
			}
		} else if(activeUser.getAlbums().isEmpty()){
			Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(photoAlbum.getStage());
            alert.setTitle("No Albums");
            alert.setHeaderText("There are no albums to edit");
            alert.showAndWait();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(photoAlbum.getStage());
            alert.setTitle("No Album Selected");
            alert.setHeaderText("You do not have an album selected");
            alert.setContentText("Please select an album to rename.");
            alert.showAndWait();
		}
			
			
	}
			
	
	/**
	 * Opens selected album in album window.
	 * @param e
	 */
	@FXML
	public void handleOpen(Event e){
		
		int selectedIndex = albumTable.getSelectionModel().getSelectedIndex();
		List<Album> list = activeUser.getAlbums();
		int listIndex = 0;
		
		if(selectedIndex > -1){
			for(int x = 0; x < list.size(); x++){
				if(list.get(x).equals(albumTable.getItems().get(selectedIndex))){
					listIndex = x;
					break;
				}
			}
			
		
		
			try{
				FXMLLoader loader1 = new FXMLLoader();
				loader1.setLocation(PhotoAlbum.class.getResource("/photoalbum/view/RootLayout.fxml"));
				BorderPane albumRoot = (BorderPane) loader1.load();
				
				Scene album = new Scene(albumRoot);
				
				FXMLLoader loader2 = new FXMLLoader();
				loader2.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/Album.fxml"));
				AnchorPane albumAnchor = (AnchorPane) loader2.load();
				
	
				albumRoot.setCenter(albumAnchor);
				
				//album
		        AlbumController albumController;
		        albumController = loader2.getController();
		        albumController.setAlbum(activeUser, activeUser.getAlbums().get(listIndex), photoAlbum, selectedIndex);
		        
		        photoAlbum.getStage().setScene(album);
				photoAlbum.getStage().setTitle("Album View: "+activeUser.getAlbums().get(listIndex).getName());
			
			}catch(Exception exc){
				exc.printStackTrace();
			}
		
		}
		else if(list.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(photoAlbum.getStage());
            alert.setTitle("No Albums");
            alert.setHeaderText("There are no albums to open");
            alert.setContentText("Please add a new album to open.");

            alert.showAndWait();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(photoAlbum.getStage());
            alert.setTitle("No Album Selected");
            alert.setHeaderText("You do not have an album selected");
            alert.setContentText("Please select an album to open.");

            alert.showAndWait();
		}
		
	}
	
	
	/**
	 public void sortAlbumList()
	    {
	    	Comparator<Album> c = (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName());
	    	
	    	activeUser.getObservableList().sort(c);
	    }
	*/
	/**
	 * Sets user to active user and album list to user's albums
	 * @param photoAlbum
	 * @param username
	 * @param index
	 */
	public void setMainApp(PhotoAlbum photoAlbum, User username, int index) {
		
		this.photoAlbum = photoAlbum;
		activeUser = username;
		albumTable.setItems(activeUser.getObservableList());
		if(!activeUser.getObservableList().isEmpty()){
			albumTable.requestFocus();
			albumTable.getSelectionModel().select(index);
			albumTable.getFocusModel().focus(index);
		}
		
	}
}
