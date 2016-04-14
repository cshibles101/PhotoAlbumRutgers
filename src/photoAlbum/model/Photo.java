package photoAlbum.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class Photo {
	
	private Label captionLabel;

	private Image image;
	
	private String caption;
	
	private List<String> tagsList;
	
	private String tagsString;
	
	private final Calendar photoDate;
	
	private final String photoDateString;
	
	private String album;
	
	
	public Photo(Image image, Calendar photoDate, String photoDateString){
		
		caption = "";
		tagsList = new ArrayList<String>();
		this.image = image;
		this.photoDate = photoDate;
		this.photoDateString = photoDateString;
		
	}
	
	public String getCaption(){
		return caption;
	}
	
	public void setCaption(String caption){
		this.caption = caption;
		captionLabel.setText(caption);
	}
	
	public Label getCaptionLabel(){
		return captionLabel;
	}
	
	public List<String> getTags(){
		return tagsList;
	}
	
	public void addTag(String tag){
		tagsList.add(tag);
	}
	
	public void setTagsString(String string){
		tagsList.clear();
		tagsString = string;
	}
	
	public String getTagsString(){
		return tagsString;
	}
	
	public Image getImage(){
		return image;
	}
	
	public Calendar getDate(){
		return photoDate;
	}
	
	public String getDateString(){
		return photoDateString;
	}
	
	public void setCaptionLabel(Label caption){
		captionLabel = caption;
	}
	
	public void setAlbum(String name){
		album = name;
	}
	
	public String getAlbum(){
		return album;
	}
	
	@Override
	public String toString(){
		return caption + " " + tagsList;
	}
	
	
}
