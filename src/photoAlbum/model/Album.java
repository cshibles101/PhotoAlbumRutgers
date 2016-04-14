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
	private transient final IntegerProperty photoCountProp;
	
	
	
	private String albumName;
	private int photoCount;
	private String oldestPhoto;
	private String newestPhoto;
	private List<Photo> photos = new ArrayList<Photo>();
	private ObservableList<Photo> photoData = FXCollections.observableArrayList();
	
	
	public Album(String name){
		
		this.albumName = name;
		photoCount = 0;
		albumNameProp = new SimpleStringProperty(name);
		photoCountProp = new SimpleIntegerProperty(photoCount);
		oldestPhoto = "";
		newestPhoto = "";
		
	}
	
	
	
	public StringProperty albumNameProperty(){
		return albumNameProp;
	}
	
	public IntegerProperty photoCountProperty(){
		return photoCountProp;
	}
	
	public String getName(){
		return albumName;
	}
	
	public int getPhotoCount(){
		return photoCount;
	}
	
	public String getOld(){
		return oldestPhoto;
	}
	
	public String getNew(){
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
	}
	
	public void deletePhoto(int index){
		
	}
	
	public void updateAlbum(List<Photo> photos){
		this.photos = photos;
		photoData.clear();
		for (Photo i : this.photos) 
			 photoData.add(i);
		
	}

}
