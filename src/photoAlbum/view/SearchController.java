package photoAlbum.view;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.Photo;
import photoAlbum.model.User;

public class SearchController {
	
	private Album activeAlbum;
	private User activeUser;
	private PhotoAlbum photoAlbum;
	private Stage stage;
	

	@FXML
	private TextField tagTxt;
	@FXML
	private DatePicker fromDate;
	@FXML
	private DatePicker toDate;
	@FXML
	private Button searchBtn;
	@FXML
	private RadioButton tagRadio;
	@FXML
	private RadioButton dateRadio;
	@FXML
	private Button lastBtn;
	@FXML
	private Button nextBtn;
	@FXML
	private MenuBar menuBar;
	@FXML
	private Menu file;
	@FXML
	private Menu create;
	@FXML
	private MenuItem menuAlbum;
	@FXML
	private MenuItem menuClose;
	@FXML
	private Label noImages;
	@FXML
	private Label captionLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label tagsLabel;
	@FXML
	private Label albumLabel;
	
	private VBox mainBox;
	@FXML
	private ImageView mainView;
	
	private ObservableList<Photo> photoList;
	@FXML
    private ListView<Photo> thumbnails;
	
	

	@FXML
	public void initialize(){
		
		thumbnails.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> displayImage(newValue));
		
	}
	
	public void displayImage(Photo photo){
		if(thumbnails.getSelectionModel().getSelectedItem()==null)
		{}else{
		double width = photo.getImage().getWidth(), height = photo.getImage().getHeight();
		//System.out.println(width+" "+height);
		if(width > 585 && height > 366){
			if(height/width > 366/585){
				mainView.setFitWidth(width*(366/height));
				mainView.setFitHeight(366);
				//System.out.println("line 215 "+width*(366/height)+" "+366);
			}
			else{
				mainView.setFitWidth(585);
				mainView.setFitHeight(height*(585/width));
				//System.out.println("line 220 "+5855+" "+height*(585/width));
			}
		}
		else if(width > 585){
			mainView.setFitWidth(585);
			mainView.setFitHeight(height*(585/width));
			//System.out.println("line 226 "+5855+" "+height*(585/width));
		}
		else if(height > 366){
			mainView.setFitWidth(width*(366/height));
			mainView.setFitHeight(366);
			//System.out.println("line 231 "+width*(366/height)+" "+336);
		} 
		else{
			mainView.setFitWidth(width);
			mainView.setFitHeight(height);
			//System.out.println("line 236 "+width+" "+height);
		}
		
		mainView.setImage(photo.getImage());
		if(photo.getTagsString() != null)
			tagsLabel.setText("Tags: "+photo.getTagsString());
		
		captionLabel.setText(photo.getCaption());
		dateLabel.setText(photo.getDateString());
		dateLabel.setAlignment(Pos.CENTER);
		albumLabel.setText(photo.getAlbum());
		
		}
	}
	
	@FXML
	public void handleSearch(Event e){
		
		if (tagRadio.isSelected()){
			String[] tagSplit;
			
			if(tagTxt.getText() != null && !tagTxt.getText().trim().isEmpty()){
				tagSplit = tagTxt.getText().trim().split(",");
				
				List<Album> list = activeUser.getAlbums();
				
				for(Album album: list){
					List<Photo> photos = album.getPhotos();
					for(Photo photo: photos){
						boolean found = false;
						for(String tag: photo.getTags()){
							for(int x = 0; x < tagSplit.length; x++){
								if(tag.equalsIgnoreCase(tagSplit[x])){
									photoList.add(photo);
								}
								
							}
							if(found)
								break;
						}
						
						
					}
				}
				
				thumbnails.setItems(photoList);
				
				
				
				
			}
			else{
				Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(stage);
	            alert.setTitle("No proper search");
	            alert.setHeaderText("Tags for search are not proper");
	            alert.setContentText("Please enter tag(s), separating by commas");
	
	            alert.showAndWait();
			}
			
		}
		else{
			if((fromDate.getValue() != null && toDate.getValue() != null) 
					&& fromDate.getValue().compareTo(toDate.getValue()) < 1){
				
				
				
				
			}
			else{
				Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(stage);
	            alert.setTitle("No proper search");
	            alert.setHeaderText("Dates for search are not proper");
	            alert.setContentText("Please choose both a start date and an end date"
	            		+ ". The To Date has to be the same or later than the From Date.");
	
	            alert.showAndWait();
			}
			
		}
		
		
		
	}
	
	
	
	
	
	@FXML
	public void handleClose(Event e){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Close");
		alert.setHeaderText("Are you sure you want to close your search?");
		alert.setContentText("You will lose your current search, but any Albums created have been stored.");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			Stage stage = (Stage) menuBar.getScene().getWindow();
			stage.close();
		}
	}
	
	
	
	public void setMainApp(Album activeAlbum, User activeUser, PhotoAlbum photoAlbum, Stage stage){
		
		this.activeAlbum = activeAlbum;
		this.activeUser = activeUser;
		this.photoAlbum = photoAlbum;
		noImages.setVisible(true);
		this.stage = stage;
		
	}

}
