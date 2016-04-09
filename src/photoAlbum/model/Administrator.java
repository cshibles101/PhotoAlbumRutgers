package photoAlbum.model;
/**
 * @author Christopher Shibles
 * @author Randy Mester
 */

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Administrator implements Serializable{

	private final StringProperty username;
	private final String password;
	
	public Administrator(){
		
		this.username = new SimpleStringProperty("Admin");
		this.password = "AdMiN2016";
		
	}
	
	public User AddNewUser(String username, String password){
		
		
		return null;
	}
	
	public boolean DeleteUser(User user){
		
		
		return false;
	}
	
}
