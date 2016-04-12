package photoAlbum.view;

import java.util.Comparator;
import java.util.List;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.User;

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
	
	@FXML
	private void initialize(){
		albumColumn.setCellValueFactory(cellData -> cellData.getValue().albumNameProperty());
		
		showAlbumDetails(null);
		
		albumTable.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showAlbumDetails(newValue));
	}
	
	
	private void showAlbumDetails(Album album) {
	    if (album != null) {
	        albumName.setText(album.getName());
	        albumName.setAlignment(Pos.CENTER);
	        photoCount.setText(Integer.toString(album.getPhotoCount()));
	        photoCount.setAlignment(Pos.CENTER);
	        newDate.setText(album.getNew());
	        newDate.setAlignment(Pos.CENTER);
	        oldDate.setText(album.getOld());
	        oldDate.setAlignment(Pos.CENTER);

	    } else {
	    	albumName.setText("");
	        photoCount.setText("");
	        newDate.setText("");
	        oldDate.setText("");
	    }
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
            
            albumTable.requestFocus();
			albumTable.getSelectionModel().select(activeUser.getAlbums().size()-1);
			albumTable.getFocusModel().focus(activeUser.getAlbums().size()-1);
			showAlbumDetails(albumTable.getItems().get(activeUser.getAlbums().size()-1));
			
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
		
	}
	
	@FXML
	private void handleDelete(Event e){
		
		int selectedIndex = albumTable.getSelectionModel().getSelectedIndex();
		List<Album> list = activeUser.getAlbums();
		
		if(selectedIndex > -1){
			for(int x = 0; x < list.size(); x++){
				if(list.get(x).equals(albumTable.getItems().get(selectedIndex))){
					list.remove(x);
					
					break;
				}
			}
			
			albumTable.getItems().remove(selectedIndex);
			
		}
		
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
	
	@FXML
	public void handleRename(Event e){
			
		int selectedIndex = albumTable.getSelectionModel().getSelectedIndex();
		
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
	}
	
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
	
	
	/**
	 public void sortAlbumList()
	    {
	    	Comparator<Album> c = (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName());
	    	
	    	activeUser.getObservableList().sort(c);
	    }
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
