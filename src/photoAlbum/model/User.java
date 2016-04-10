package photoAlbum.model;
/**
 * @author Christopher Shibles
 * @author Randy Mester
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User implements Serializable{
	
	private final StringProperty username;
	private final String password;
	ObservableList<Album> albums = FXCollections.observableArrayList();
	
	public User(String username){
		
		this.username = new SimpleStringProperty(username);
		this.password = "password";
	}
	
	public User(String username, String password){
		
		this.username = new SimpleStringProperty(username);
		this.password = password;
		
	}
	
	public String getUsername() {
        return username.get();
    }
	
	public void setUsername(String name){
		this.username.set(name);
	}
	
	private String getPassword(){
		return password;
	}
	
	public ObservableList<Album> getAlbums(){
		return albums;
	}
	
	public boolean equals(Object obj){
		
		if(obj instanceof User){
			if(this.getUsername().equals(((User) obj).getUsername()) && this.getPassword().equals(((User) obj).getPassword())){
				return true;
			}
		}
		
		return false;
		
	}
	
	public String toString(){
		return getUsername();
	}
	
}
