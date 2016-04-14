package photoAlbum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Album implements Serializable{
	
	private static final long serialVersionUID = -5223084188453703541L;

	private transient final StringProperty albumNameProp;
	
	private String albumName;
	private int photoCount;
	private Photo oldestPhoto;
	private Photo newestPhoto;
	private List<Photo> photos = new ArrayList<Photo>();
	private ObservableList<Photo> photoData = FXCollections.observableArrayList();
	
	
	public Album(String name){
		
		this.albumName = name;
		photoCount = 0;
		albumNameProp = new SimpleStringProperty(name);
		oldestPhoto = null;
		newestPhoto = null;
		
	}
	
	
	
	public StringProperty albumNameProperty(){
		return albumNameProp;
	}
	
	
	public String getName(){
		return albumName;
	}
	
	public int getPhotoCount(){
		return photoCount;
	}
	
	public Photo getOld(){
		return oldestPhoto;
	}
	
	public Photo getNew(){
		return newestPhoto;
	}
	
	public void setName(String name){
		albumName = name;
		albumNameProp.set(name);
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof Album){
			return this.getName().equals(((Album)o).getName());
		}
		return false;
	}
	
	public List<Photo> getPhotos(){
		return photos;
	}
	
	public ObservableList<Photo> getObservableList(){
		return photoData;
	}
	
	public String toString(){
		return albumName;
	}
	
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
	
	public void updateAlbum(List<Photo> photos){
		this.photos = photos;
		photoData.clear();
		for (Photo i : this.photos) 
			 photoData.add(i);
		
	}

}
