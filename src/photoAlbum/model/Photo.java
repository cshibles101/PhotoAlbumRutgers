package photoAlbum.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class Photo implements Serializable{
	
	private static final long serialVersionUID = -5678015363905864572L;

	private transient Image image;
	
	private transient Label captionLabel;
	
	private String imgPath;
	
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
	
	public void loadCaption(){
		if(captionLabel == null){
			captionLabel = new Label();
		}
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
	
	public void setPath(String path){
		imgPath = path;
	}
	
	public String getPath(){
		return imgPath;
	}
	
	public void loadImage(){
		 try {
			 File file = new File(imgPath);
			 BufferedImage bufferedImage = ImageIO.read(file);
			 image = SwingFXUtils.toFXImage(bufferedImage, null);
			} catch (IOException ex) {
			    ex.printStackTrace();
			}
	}
	
	@Override
	public String toString(){
		return caption + " " + tagsList;
	}
	
	
}
