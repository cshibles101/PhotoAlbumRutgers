/**
 * @author Christopher Shibles
 * @author Randy Mester
 */
package photoAlbum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * User class that contains username and password, all of user's albums, and lists of photos in the
 * user's albums that contain certain tags.
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = 7911496313893371710L;
	
	private transient final StringProperty usernameProp;
	private transient ObservableList<Album> userAlbums = FXCollections.observableArrayList();
	
	
	private final String username;
	private final String password;
	private List<Album> albums = new ArrayList<Album>();
	private List<Tag> tagList = new ArrayList<Tag>();
	private HashMap<String, Tag> tags = new HashMap<String, Tag>();
	
	
	/**
	 * Initializes user object, setting its username and password.
	 * @param username
	 * @param password
	 */
	public User(String username, String password){
		this.username = username;
		this.password = password;
		usernameProp = new SimpleStringProperty(username);
	}
	/**
	 * Refers to username
	 * @return username
	 */
	public String getUsername(){
		
		return username;
	}
	/**
	 * Refers username as a string property
	 * @return username as string property
	 */
	public StringProperty usernameProperty(){
		return usernameProp;
	}
	/**
	 * Refers to user's password
	 * @return user's password
	 */
	protected String getPassword(){
		return password;
	}

	/**
	 * Checks if two users are the same user
	 */
	@Override
	public boolean equals(Object o){
		if(o instanceof User){
			return this.getUsername().equals(((User)o).getUsername());
		}
		return false;
	}
	/**
	 * Checks if a password and username are matched in a user object
	 * @param username
	 * @param password
	 * @return true if the username and password pair exist, false if a username and password don't match
	 */
	public boolean loginMatch(String username, String password){
		
		return (password.equals(this.password) && username.equals(this.username));
		
	}
	/**
	 * Returns the username
	 */
	public String toString(){
		return username;
	}
	/**
	 * Creates a new album for the user and adds it to the user's list of albums
	 * @param name
	 */
	public void newAlbum(String name){
		albums.add(new Album(name));
		userAlbums.add(new Album(name));
	}
	/**
	 * Deletes a user's album
	 * @param album
	 */
	public void deleteAlbum(Album album){
		albums.remove(album);
		userAlbums.remove(album);
	}
	/**
	 * Adds an existing album to list of user's albums
	 * @param album
	 */
	public void addAlbum(Album album){
		albums.add(album);
		userAlbums.add(album);
	}
	/**
	 * Refers to user's albums in observable list form
	 * @return a list of user's albums in observableList form
	 */
	public ObservableList<Album> getObservableList(){
		 return userAlbums;		 
	 }
	/**
	 * Refers to a user's albums in a list
	 * @return a list of user's albums
	 */
	public List<Album> getAlbums(){
		return albums;
	}
	/**
	 * Clears observableList of albums, then resets it based on user's list of albums
	 * @param albums
	 */
	public void updateAlbums(List<Album> albums){
		this.albums = albums;
		userAlbums.clear();
		for (Album i : this.albums) 
			 userAlbums.add(i);
	}
	/**
	 * Refers to a hashmap of tags that occur in a user's albums
	 * @return hashmap of tags
	 */
	public HashMap<String, Tag> getHash(){
		return tags;
	}
	/**
	 * Refers to list of tags that occur in a user's albums
	 * @return list of tags
	 */
	public List<Tag> getTags(){
		return tagList;
	}
	/**
	 * Adds tag to list of tags that occur in a user's albums
	 * @param tag
	 */
	public void addTag(Tag tag){
		tagList.add(tag);
	}
	/**
	 * Deletes tag from list of tags that occur in a user's albums
	 * @param tag
	 */
	public void deleteTag(Tag tag){
		tagList.remove(tag);
	}


}
