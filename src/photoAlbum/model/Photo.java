/**
 * @author Christopher Shibles
 * @author Randy Mester
 */
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
/**
 * Photo object with information on the photo's date, caption, tags, and associated album.
 * Also includes actual photo image.
 *
 */
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
	
	/**
	 * Creates an instance of a photo and initializes it with the associated image and date of photo.
	 * @param image
	 * @param photoDate
	 * @param photoDateString
	 */
	public Photo(Image image, Calendar photoDate, String photoDateString){
		
		caption = "";
		tagsList = new ArrayList<String>();
		this.image = image;
		this.photoDate = photoDate;
		this.photoDateString = photoDateString;
		
	}
	/**
	 * Refers to photo's caption
	 * @return photo's caption
	 */
	public String getCaption(){
		return caption;
	}
	/**
	 * Sets photo's caption
	 * @param caption
	 */
	public void setCaption(String caption){
		this.caption = caption;
		captionLabel.setText(caption);
	}

	/**
	 * Sets caption label to caption
	 */
	public void loadCaption(){
		if(captionLabel == null){
			captionLabel = new Label();
		}
		captionLabel.setText(caption);
	}
	
/**
 * Refers to photo's caption label
 * @return photo's caption label
 */
	public Label getCaptionLabel(){
		return captionLabel;
	}
	/**
	 * Refers to photo's associated list of tags
	 * @return list of tags
	 */
	public List<String> getTags(){
		return tagsList;
	}
	/**
	 * Adds tag to photo's list of tags
	 * @param tag
	 */
	public void addTag(String tag){
		tagsList.add(tag);
	}
	/**
	 * Clear's photo's list of tags and resets it with given string of tags
	 * @param string
	 */
	public void setTagsString(String string){
		tagsList.clear();
		tagsString = string;
	}
	/**
	 * Refers to photo's tags in one big string form
	 * @return photo tags in a single string
	 */
	public String getTagsString(){
		return tagsString;
	}
	/**
	 * Refers to photo's image
	 * @return photo image
	 */
	public Image getImage(){
		return image;
	}
	/**
	 * Refers to date photo was last modified
	 * @return photo's date
	 */
	public Calendar getDate(){
		return photoDate;
	}
	/**
	 * Refers to photo's date in string form
	 * @return photo's date in a string
	 */
	public String getDateString(){
		return photoDateString;
	}
	/**
	 * Sets photo's caption label
	 * @param caption
	 */
	public void setCaptionLabel(Label caption){
		captionLabel = caption;
	}
	/**
	 * Sets name of photo's associated album
	 * @param name
	 */
	public void setAlbum(String name){
		album = name;
	}
	/**
	 * Gets name of photo's associated album
	 * @return
	 */
	public String getAlbum(){
		return album;
	}
	/**
	 * Sets photo's image's directory path location
	 * @param path
	 */
	public void setPath(String path){
		imgPath = path;
	}
	/**
	 * Gets photo's image's directory path location 
	 * @return image's path
	 */
	public String getPath(){
		return imgPath;
	}

	/**
	 * Loads image from file path
	 */
	public void loadImage(){
		 try {
			 File file = new File(imgPath);
			 BufferedImage bufferedImage = ImageIO.read(file);
			 image = SwingFXUtils.toFXImage(bufferedImage, null);
			} catch (IOException ex) {
			    ex.printStackTrace();
			}
	}

	/**
	 * Returns photo's caption and list of tags in string form
	 */
	@Override
	public String toString(){
		return caption + " " + tagsList;
	}
	
	
}
