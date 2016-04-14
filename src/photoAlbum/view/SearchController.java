package photoAlbum.view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.User;

public class SearchController {
	
	private Album activeAlbum;
	private User activeUser;
	private PhotoAlbum photoAlbum;
	

	@FXML
	TextField tagTxt;
	@FXML
	DatePicker fromDate;
	@FXML
	DatePicker toDate;
	@FXML
	Button searchBtn;
	@FXML
	RadioButton tagRadio;
	@FXML
	RadioButton dateRadio;
	@FXML
	Button lastBtn;
	@FXML
	Button nextBtn;
	@FXML
	MenuBar menuBar;
	@FXML
	Menu file;
	@FXML
	Menu create;
	@FXML
	MenuItem menuAlbum;
	@FXML
	MenuItem menuClose;

	@FXML
	public void initialize(){
		
	}
	
	@FXML
	public void handleClose(Event e){
		Stage stage = (Stage) menuBar.getScene().getWindow();
		stage.close();
	}
	
	
	
	public void setMainApp(Album activeAlbum, User activeUser, PhotoAlbum photoAlbum){
		
		this.activeAlbum = activeAlbum;
		this.activeUser = activeUser;
		this.photoAlbum = photoAlbum;
		
	}

}
