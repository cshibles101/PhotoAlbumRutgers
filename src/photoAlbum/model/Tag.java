package photoAlbum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tag implements Serializable{

	private static final long serialVersionUID = 2049901245790788187L;

	private String tag;
	private List<Photo> photos = new ArrayList<Photo>();
	
	public Tag(String tag, Photo photo){
		this.tag = tag;
		photos.add(photo);
	}
	
	public String getTag(){
		return tag;
	}
	
	public List<Photo> getPhotos(){
		return photos;
	}
	
	public void addPhoto(Photo photo){
		photos.add(photo);
	}
	
	public void deletePhoto(Photo photo){
		photos.remove(photo);
	}
	
	
}
