package photoAlbum.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.Photo;
import photoAlbum.model.User;

public class AlbumController {

	private int index;
	
	private User activeUser;
	
	private Album activeAlbum;
	
	private PhotoAlbum photoAlbum;
	
	@FXML
	private MenuBar menuBar;
	@FXML
	private Menu menuFile;
	@FXML
	private MenuItem closeAlbum;
	@FXML
	private MenuItem logout;
	@FXML
	private MenuItem exit;
	@FXML
	private Menu menuEdit;
	@FXML
	private MenuItem editPhoto;
	@FXML
	private MenuItem addPhoto;
	@FXML
	private MenuItem removePhoto;
	@FXML
	private Menu search;
	@FXML
	private MenuItem searchPhotos;
	@FXML
	private Button next;
	@FXML
	private Button last;
	@FXML
    private ListView<Photo> thumbnails;
	@FXML
	private SplitPane splitPane;
	@FXML
	private VBox mainBox;
	@FXML
	private ImageView mainView;
	@FXML
	private Label captionLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label tagsLabel;
	
    private List<Photo> photoList;
	
	
	
	public AlbumController(){
		
	}
	
	@FXML
	public void initialize(){
		
		thumbnails.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> displayImage(newValue));
		
	}
	
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
            	double ratio = 0;
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
	
	@FXML
	private void handleExit(Event e){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure you want to exit?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.exit(1);
		}
		
	}
	@FXML
	private void handleLogout(Event e){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			photoAlbum.getStage().setScene(photoAlbum.getScene("login"));
			photoAlbum.getStage().setTitle("Photo Album Login");
		}
		
	}
	@FXML
	private void handleCloseAlbum(Event e){
		try{
			FXMLLoader loader1 = new FXMLLoader();
			loader1.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/RootLayout.fxml"));
			BorderPane userRoot = (BorderPane) loader1.load();
			
			Scene user = new Scene(userRoot);
			
			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/AlbumList.fxml"));
			AnchorPane userAnchor = (AnchorPane) loader2.load();
			
	
			userRoot.setCenter(userAnchor);
			
			//user
	        UserController userController;
	        userController = loader2.getController();
	        userController.setMainApp(photoAlbum, activeUser, index);
	        
	        photoAlbum.getStage().setScene(user);
			photoAlbum.getStage().setTitle(activeUser.getUsername()+" Album List");
			
		} catch(Exception exc){
			exc.printStackTrace();
		}
		
	}
	
	@FXML
	public void handleAddPhoto(Event e){
		Image image = null;
		Calendar temp = Calendar.getInstance();
		
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open Resource File");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		 File selectedFile = fileChooser.showOpenDialog(photoAlbum.getStage());
		 
		 


		 if(selectedFile != null){
			 try {
				 BufferedImage bufferedImage = ImageIO.read(selectedFile);
				 image = SwingFXUtils.toFXImage(bufferedImage, null);
				} catch (IOException ex) {
				    
				}
			 temp.setTimeInMillis(selectedFile.lastModified());
			 temp.set(Calendar.MILLISECOND, 0);
			 temp.set(Calendar.SECOND, 0);
			 temp.set(Calendar.MINUTE, 0);
			 temp.set(Calendar.HOUR, 0);
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			 
			 Photo addedPhoto = new Photo(image, temp, sdf.format(temp.getTime()));
			 
			 activeAlbum.addPhoto(addedPhoto);
			 
			 thumbnails.setCellFactory(new Callback<ListView<Photo>, 
			            ListCell<Photo>>() {
			                @Override 
			                public ListCell<Photo> call(ListView<Photo> list) {
			                    return new newPhotoCell();
			                }
			            }
			        );
			thumbnails.requestFocus();
			thumbnails.getSelectionModel().selectLast();
			thumbnails.getFocusModel().focus(photoList.size()-1);
			
			try{
				FXMLLoader loader1 = new FXMLLoader();
				loader1.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/RootLayout.fxml"));
				BorderPane editPhotoRoot = (BorderPane) loader1.load();
				
				Scene editPhoto = new Scene(editPhotoRoot);
				
				FXMLLoader loader2 = new FXMLLoader();
				loader2.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/EditPhoto.fxml"));
				AnchorPane editPhotoAnchor = (AnchorPane) loader2.load();
				
				editPhotoRoot.setCenter(editPhotoAnchor);
				
				EditPhotoController editPhotoController;
				editPhotoController = loader2.getController();
				editPhotoController.setMainApp(addedPhoto, activeAlbum, activeUser, photoAlbum, index);
		        
		        Stage dialog = new Stage();
		        
		        dialog.setScene(editPhoto);
	            dialog.setTitle("Add Photo Details");
	            dialog.showAndWait();
	            if(addedPhoto.getTagsString() != null)
	            	tagsLabel.setText("Tags: "+addedPhoto.getTagsString());
	            captionLabel.setText(addedPhoto.getCaption());
	            
	            if(thumbnails.getSelectionModel().getSelectedItem()==null){
					mainView.setImage(null);
					captionLabel.setText("Caption");
					dateLabel.setText("Date");
					tagsLabel.setText("Tags");
				}
				else{
					thumbnails.requestFocus();
					thumbnails.getSelectionModel().select(index);
					thumbnails.getFocusModel().focus(index);
				}
	        
			}catch(Exception exc){
				exc.printStackTrace();
			}
		 }
	 }
	
	@FXML
	public void handleEditPhoto(Event e){
		
		
		int index = thumbnails.getSelectionModel().getSelectedIndex();
		
		if(index > -1){
		Photo photo = thumbnails.getSelectionModel().getSelectedItem();
			try{
				FXMLLoader loader1 = new FXMLLoader();
				loader1.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/RootLayout.fxml"));
				BorderPane editPhotoRoot = (BorderPane) loader1.load();
				
				Scene editPhoto = new Scene(editPhotoRoot);
				
				FXMLLoader loader2 = new FXMLLoader();
				loader2.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/EditPhoto.fxml"));
				AnchorPane editPhotoAnchor = (AnchorPane) loader2.load();
				
				editPhotoRoot.setCenter(editPhotoAnchor);
				
				EditPhotoController editPhotoController;
				editPhotoController = loader2.getController();
				editPhotoController.setMainApp(photo, activeAlbum, activeUser, photoAlbum, index);
		        
		        Stage dialog = new Stage();
		        
		        dialog.setScene(editPhoto);
	            dialog.setTitle("Edit Photo");
	            dialog.showAndWait();
	            if(photo.getTagsString() != null)
	            	tagsLabel.setText("Tags: "+photo.getTagsString());
	            captionLabel.setText(photo.getCaption());
	        
			}catch(Exception exc){
				exc.printStackTrace();
			}
			
			if(thumbnails.getSelectionModel().getSelectedItem()==null){
				mainView.setImage(null);
			}
			
		}
		
		activeAlbum.updateAlbum(activeAlbum.getPhotos());
	}
	
	@FXML
	public void handleDeletePhoto(Event e){
		
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
		
		}
	}
	
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
	
	
	public void setAlbum(User user, Album album, PhotoAlbum photoAlbum, int index) {
		
		this.index = index;
		this.photoAlbum = photoAlbum;
		activeAlbum = album;
		activeUser = user;
		
		photoList = album.getPhotos();
		thumbnails.setItems(album.getObservableList());
		
		mainView = new ImageView();
		mainView.setPreserveRatio(true);
		mainView.maxHeight(mainBox.getMaxHeight());
		mainView.maxWidth(mainBox.getMaxWidth());
		mainBox.getChildren().add(mainView);
		mainBox.setAlignment(Pos.CENTER);
		
		if(!album.getObservableList().isEmpty()){
			thumbnails.requestFocus();
			thumbnails.getSelectionModel().select(0);
			thumbnails.getFocusModel().focus(0);
		}
		
		thumbnails.setCellFactory(new Callback<ListView<Photo>, 
	            ListCell<Photo>>() {
	                @Override 
	                public ListCell<Photo> call(ListView<Photo> list) {
	                    return new newPhotoCell();
	                }
	            }
	        );
		
	}
}
