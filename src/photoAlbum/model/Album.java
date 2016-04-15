/**
 * @author Christopher Shibles
 * @author Randy Mester
 */
package photoAlbum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Album object that contains list of photos and information on the number of photos, oldest photo, and
 * newest photo
 *
 */
public class Album implements Serializable{
	
	private static final long serialVersionUID = -5223084188453703541L;

	private transient StringProperty albumNameProp;
	
	private String albumName;
	private int photoCount;
	private Photo oldestPhoto;
	private Photo newestPhoto;
	private List<Photo> photos = new ArrayList<Photo>();
	private transient ObservableList<Photo> photoData = FXCollections.observableArrayList();
	
	/**
	 * Creates instance of album and gives it a name
	 * @param name
	 */
	public Album(String name){
		
		this.albumName = name;
		photoCount = 0;
		albumNameProp = new SimpleStringProperty(name);
		oldestPhoto = null;
		newestPhoto = null;
		
	}
	
	
	/**
	 * Refers to album name in string property form
	 * @return album's name as string property
	 */
	public StringProperty albumNameProperty(){
		return albumNameProp;
	}
	
	/**
	 * Sets album name as a string property
	 */
	public void setStringProp(){
		albumNameProp = new SimpleStringProperty(albumName);
	}

	/**
 	* Refers to name of album
 	* @return album name
 	*/
	public String getName(){
		return albumName;
	}
	/**
	 * Refers to amount of photos in album
	 * @return number of photos in album
	 */
	public int getPhotoCount(){
		return photoCount;
	}
	/**
	 * Refers to oldest photo in album
	 * @return oldest photo in album
	 */
	public Photo getOld(){
		return oldestPhoto;
	}
	/**
	 * Refers to newest photo in album
	 * @return newest photo in album
	 */
	public Photo getNew(){
		return newestPhoto;
	}
	/**
	 * Sets album name and name property
	 * @param name
	 */
	public void setName(String name){
		albumName = name;
		albumNameProp.set(name);
	}
	/**
	 * Checks if two albums are the same album
	 * @return Returns true if albums are the same and false if they are not
	 */
	@Override
	public boolean equals(Object o){
		if(o instanceof Album){
			return this.getName().equals(((Album)o).getName());
		}
		return false;
	}
	/**
	 * Refers to album's list of photos
	 * @return album's list of photos
	 */
	public List<Photo> getPhotos(){
		return photos;
	}
	/**
	 * Refers to album's observable list of photos
	 * @return observableList of photos
	 */
	public ObservableList<Photo> getObservableList(){
		return photoData;
	}
	/**
	 * Refers to album's name
	 */
	public String toString(){
		return albumName;
	}
	/**
	 * Adds photo to album's list and observable list of photos
	 * @param photo
	 */
	public void addPhoto(Photo photo){
		photos.add(photo);
		photoData.add(photo);
		photoCount++;
		photo.setAlbum(albumName);
		
		if(getNew() != null){
			if(getNew().getDate().compareTo(photo.getDate()) <= 0){
				newestPhoto = photo;
			}
		}
		else{
			newestPhoto = photo;
		}
		if(getOld() != null){
			if(getOld().getDate().compareTo(photo.getDate()) >= 0){
				oldestPhoto = photo;
			}
		}
		else{
			oldestPhoto = photo;
		}
		
	}
	/**
	 * Deletes photo at index from album's photo list and observable list
	 * @param index
	 */
	public void deletePhoto(int index){
		Photo temp = photos.get(index);
		photos.remove(index);
		photoData.remove(index);
		photoCount--;
		if(photoCount > 0){
			if(newestPhoto == temp){
				int x = 1;
				index = 0;
				while(x < photos.size()){
					if(photos.get(index).getDate().compareTo(photos.get(x).getDate()) < 0){
						index = x;
					}
					x++;
				}
				newestPhoto = photos.get(index);
			}
		
			if(oldestPhoto == temp){
				int x = 1;
				index = 0;
				while(x < photos.size()){
					if(photos.get(index).getDate().compareTo(photos.get(x).getDate()) > 0){
						index = x;
					}
					x++;
				}
				oldestPhoto = photos.get(index);
			}
		}
		else{
			newestPhoto = null;
			oldestPhoto = null;
		}
		
		
		
		
	}
	/**
	 * Resets observable list of photos based off list of photos
	 * @param photos
	 */
	public void updateAlbum(List<Photo> photos){
		if(photoData == null){
			photoData = FXCollections.observableArrayList();
		}
		this.photos = photos;
		photoData.clear();
		for (Photo i : this.photos) 
			 photoData.add(i);
		
	}
	
	public void loadImages(){
		for(Photo photo: photos){
			photo.loadImage();
		}
	}

}
