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

	private transient final StringProperty captionProp;
	private transient final StringProperty tagsProp;
	private transient final ObjectProperty<Image> photoProp;
	
	private Label captionLabel;

	private Image image;
	
	private String caption;
	
	private List<String> tagsList;
	
	private String tagsString;
	
	private final Calendar photoDate;
	
	private final String photoDateString;
	
	
	public Photo(Image image, Calendar photoDate, String photoDateString){
		
		caption = "";
		captionProp = new SimpleStringProperty("");
		tagsProp = new SimpleStringProperty("");
		tagsList = new ArrayList<String>();
		this.image = image;
		photoProp = new SimpleObjectProperty<Image>(image);
		this.photoDate = photoDate;
		this.photoDateString = photoDateString;
		
	}
	
	public StringProperty captionProperty(){
		return captionProp;
	}
	
	public StringProperty tagsProperty(){
		return tagsProp;
	}
	
	public ObjectProperty<Image> photoProp(){
		return photoProp;
	}
	
	public String getCaption(){
		return caption;
	}
	
	public void setCaption(String caption){
		this.caption = caption;
		captionProp.set(caption);
		captionLabel.setText(caption);
	}
	
	public List<String> getTags(){
		return tagsList;
	}
	
	public void addTag(String tag){
		tagsList.add(tag);
	}
	
	public void setTagsString(String string){
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
	
	@Override
	public String toString(){
		return caption + " " + tagsList;
	}
	
	
}
