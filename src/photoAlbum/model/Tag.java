/**
 * @author Christopher Shibles
 * @author Randy Mester
 */
package photoAlbum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Tag object that contains information on a list of photos that include this tag
 *
 */
public class Tag implements Serializable{

	private static final long serialVersionUID = 2049901245790788187L;

	private String tag;
	private List<Photo> photos = new ArrayList<Photo>();
	/**
	 * Instantiates tag object
	 * @param tag
	 * @param photo
	 */
	public Tag(String tag, Photo photo){
		this.tag = tag;
		photos.add(photo);
	}
	/**
	 * Refers to name of tag
	 * @return this tag
	 */
	public String getTag(){
		return tag;
	}
	/**
	 * Refers to list of photos with this tag
	 * @return list of photos with this tag
	 */
	public List<Photo> getPhotos(){
		return photos;
	}
	/**
	 * Adds photo to list of photos with this tag
	 * @param photo
	 */
	public void addPhoto(Photo photo){
		photos.add(photo);
	}
	/**
	 * Deletes photo from list of photos with this tag
	 * @param photo
	 */
	public void deletePhoto(Photo photo){
		photos.remove(photo);
	}
	
	
}
