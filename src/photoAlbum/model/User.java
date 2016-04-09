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

public class User implements Serializable{
	
	private final StringProperty username;
	private final String password;
	List<Album> albums = new ArrayList<Album>();

	
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
	
	public boolean equals(Object obj){
		
		if(obj instanceof User){
			if(username.equals(((User) obj).username) && password.equals(((User) obj).password)){
				return true;
			}
		}
		
		return false;
		
	}
	
}
