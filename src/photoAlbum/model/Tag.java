package photoAlbum.model;

import java.util.ArrayList;
import java.util.List;

public class Tag {

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
