package photoAlbum.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Photo {

	private transient final StringProperty captionProp;
	private transient final StringProperty tagsProp;
	private transient final ObjectProperty<Image> photoProp;
	

	private Image image;
	
	private String caption;
	
	private List<String> tagsList;
	
	
	public Photo(Image image){
		
		caption = "";
		captionProp = new SimpleStringProperty("");
		tagsProp = new SimpleStringProperty("");
		tagsList = new ArrayList<String>();
		this.image = image;
		photoProp = new SimpleObjectProperty<Image>(image);
		
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
	}
	
	public List<String> getTags(){
		return tagsList;
	}
	
	public Image getImage(){
		return image;
	}
	
	@Override
	public String toString(){
		return caption + " " + tagsList;
	}
	
}
