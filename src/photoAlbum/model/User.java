package photoAlbum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User implements Serializable{

	private static final long serialVersionUID = 7911496313893371710L;
	
	private transient final StringProperty usernameProp;
	private transient ObservableList<Album> userAlbums = FXCollections.observableArrayList();
	
	
	private final String username;
	private final String password;
	private List<Album> albums = new ArrayList<Album>();
	
	

	public User(String username, String password){
		this.username = username;
		this.password = password;
		usernameProp = new SimpleStringProperty(username);
	}
	
	public String getUsername(){
		
		return username;
	}
	public StringProperty usernameProperty(){
		return usernameProp;
	}
	
	protected String getPassword(){
		return password;
	}


	@Override
	public boolean equals(Object o){
		if(o instanceof User){
			return this.getUsername().equals(((User)o).getUsername());
		}
		return false;
	}
	
	public boolean loginMatch(String username, String password){
		
		return (password.equals(this.password) && username.equals(this.username));
		
	}
	public String toString(){
		return username;
	}
	
	public void newAlbum(String name){
		albums.add(new Album(name));
		userAlbums.add(new Album(name));
	}
	
	public void deleteAlbum(Album album){
		albums.remove(album);
		userAlbums.remove(album);
	}
	
	public void addAlbum(Album album){
		albums.add(album);
		userAlbums.add(album);
	}
	
	public ObservableList<Album> getObservableList(){
		 return userAlbums;		 
	 }
	
	public List<Album> getAlbums(){
		return albums;
	}
	
	public void updateAlbums(List<Album> albums){
		this.albums = albums;
		userAlbums.clear();
		for (Album i : this.albums) 
			 userAlbums.add(i);
	}

}
