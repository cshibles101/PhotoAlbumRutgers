/**
 * @author Christopher Shibles
 * @author Randy Mester
 */
package photoAlbum.view;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.Photo;
import photoAlbum.model.Tag;
import photoAlbum.model.User;
/**
 * Controller for photo search window
 *
 */
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
	@FXML
	private VBox mainBox;
	@FXML
	private ImageView mainView;
	
	private ObservableList<Photo> photoList = FXCollections.observableArrayList();
	@FXML
    private ListView<Photo> thumbnails;
	
	
	/**
	 * Sets up list of photo thumbnails
	 */
	@FXML
	public void initialize(){
		
		thumbnails.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> displayImage(newValue));
		
	}
	/**
	 * Displays selected photo in main view
	 * @param photo
	 */
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
		albumLabel.setText("Album: "+photo.getAlbum());
		
		}
	}
	/**
	 * Sets up cell of list cell factory
	 *
	 */
	static class newPhotoCell extends ListCell<Photo> {
        @Override
        public void updateItem(Photo item, boolean empty) {
            super.updateItem(item, empty);
            VBox box = new VBox();
            if (item != null) {
            	double width = 0, height = 0;
            	ImageView view = new ImageView(item.getImage());
            	view.setPreserveRatio(true);
            	double picW = item.getImage().getWidth(), picH = item.getImage().getHeight();
            	if(picW > picH){
            		width = 50;
            	}
            	else{
            		height = 50;
            	}
            	
           	 	view.setFitWidth(width);
           	 	view.setFitHeight(height);
           	 	
           	 	Label label = new Label(item.getCaption());
           	 	label.setMinWidth(100);
           	 	label.setMaxWidth(100);
           	 	label.setAlignment(Pos.CENTER);
           	 	
           	 	item.setCaptionLabel(label);
           	 	
            	box.getChildren().addAll(view, label);
            	box.setAlignment(Pos.CENTER);
            	setGraphic(box);
            }
        }
    }
	/**
	 * Checks if date search or tag search are selected,
	 * then performs allotted search. Tag search looks
	 * through photos with specified tag. Date search 
	 * looks through photos from between the two allotted
	 * dates.
	 * @param e
	 */
	@FXML
	public void handleSearch(Event e){
		
		
		if (tagRadio.isSelected()){
			String tag;
			
			if(tagTxt.getText() != null && !tagTxt.getText().trim().isEmpty()){
				photoList.clear();
				
				tag = tagTxt.getText().toLowerCase().trim();
				
				Tag searched = activeUser.getHash().get(tag);
				
				if(searched != null){
					for(Photo p: searched.getPhotos()){
						photoList.add(p);
					}
					
					thumbnails.setItems(photoList);
					thumbnails.setCellFactory(new Callback<ListView<Photo>, 
				            ListCell<Photo>>() {
				                @Override 
				                public ListCell<Photo> call(ListView<Photo> list) {
				                    return new newPhotoCell();
				                }
				            }
				        );
					
					thumbnails.requestFocus();
					thumbnails.getSelectionModel().selectFirst();
					thumbnails.getFocusModel().focus(0);
					noImages.setVisible(false);
				}
				else{
					noImages.setVisible(true);
				}
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
		//search by date
		else{
			if((fromDate.getValue() != null && toDate.getValue() != null) 
					&& fromDate.getValue().compareTo(toDate.getValue()) < 1){
				Date firstDate = Date.from(fromDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				Date secondDate = Date.from(toDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(Date.from(toDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				cal.set(Calendar.HOUR_OF_DAY,23);
				cal.set(Calendar.MINUTE,59);
				cal.set(Calendar.SECOND,59);
				cal.set(Calendar.MILLISECOND,999);

				secondDate = cal.getTime();
				
				if(fromDate.getValue() != null && toDate.getValue() != null){
					photoList.clear();					
					
					for(Album album:activeUser.getAlbums()){
						for(Photo photo:album.getPhotos()){
							Date photoDate = photo.getDate().getTime();
							
							if(photoDate.compareTo(firstDate) > 0 && photoDate.compareTo(secondDate) < 0){
								photoList.add(photo);
							}
						}
					}
					
					thumbnails.setItems(photoList);
					thumbnails.setCellFactory(new Callback<ListView<Photo>, 
				            ListCell<Photo>>() {
				                @Override 
				                public ListCell<Photo> call(ListView<Photo> list) {
				                    return new newPhotoCell();
				                }
				            }
				        );
					
					thumbnails.requestFocus();
					thumbnails.getSelectionModel().selectFirst();
					thumbnails.getFocusModel().focus(0);
					noImages.setVisible(false);
					
				}
			
			
				
				
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
		if(thumbnails.getSelectionModel().getSelectedItem()==null){
			mainView.setImage(null);
			captionLabel.setText("Caption");
			dateLabel.setText("Date");
			tagsLabel.setText("Tags");
			albumLabel.setText("Album");
		}
		
		
	}
	
	/**
	 * Opens a create new album window that creates
	 * a new album out of all photos in search list.
	 * @param e
	 */
	@FXML
	public void handleCreate(Event e){
		
		int numberOfAlbums = activeUser.getAlbums().size();
		
		if(photoList.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(photoAlbum.getStage());
            alert.setTitle("No Photos");
            alert.setHeaderText("There are no photos in your search.");
            alert.showAndWait();
		}    
		else{
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
				
			}catch(Exception exc){
				exc.printStackTrace();
			}
			
			if(numberOfAlbums != activeUser.getAlbums().size()){
				for(Photo photo:photoList){
					activeUser.getAlbums().get(activeUser.getAlbums().size()-1).addPhoto(photo);
				}
			}
		}
	}
	
	/**
	 * Closes search window and returns to album window
	 * @param e
	 */
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
	/**
	 * Selects next photo in list of photos
	 * @param e
	 */
	@FXML
	public void handleNext(Event e){
		if(!photoList.isEmpty()){
			int index = thumbnails.getSelectionModel().getSelectedIndex();
			if(index+1 == photoList.size()){
				thumbnails.requestFocus();
				thumbnails.getSelectionModel().selectFirst();
				thumbnails.getFocusModel().focus(0);
			}
			else{
				thumbnails.requestFocus();
				thumbnails.getSelectionModel().select(index+1);
				thumbnails.getFocusModel().focus(index+1);
			}
		}
	}
	/**
	 * Selects previous photo in list of photos
	 * @param e
	 */
	@FXML
	public void handleLast(Event e){
		
		if(!photoList.isEmpty()){
			int index = thumbnails.getSelectionModel().getSelectedIndex();
			if(index == 0){
				thumbnails.requestFocus();
				thumbnails.getSelectionModel().selectLast();
				thumbnails.getFocusModel().focus(photoList.size()-1);
			}
			else{
				thumbnails.requestFocus();
				thumbnails.getSelectionModel().select(index-1);
				thumbnails.getFocusModel().focus(index-1);
			}
		}
		
	}
	
	/**
	 * Sets the active album and user and well as main view
	 * @param activeAlbum
	 * @param activeUser
	 * @param photoAlbum
	 * @param stage
	 */
	public void setMainApp(Album activeAlbum, User activeUser, PhotoAlbum photoAlbum, Stage stage){
		
		this.activeAlbum = activeAlbum;
		this.activeUser = activeUser;
		this.photoAlbum = photoAlbum;
		this.stage = stage;
		
		mainView = new ImageView();
		mainView.setPreserveRatio(true);
		mainView.maxHeight(mainBox.getMaxHeight());
		mainView.maxWidth(mainBox.getMaxWidth());
		mainBox.getChildren().add(mainView);
		mainBox.setAlignment(Pos.CENTER);
		
	}

}
