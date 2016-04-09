package photoAlbum.model;
/**
 * @author Christopher Shibles
 * @author Randy Mester
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Album implements Serializable{

	private final StringProperty name;
	private final IntegerProperty photoCount;
	private final ObjectProperty<LocalDate> oldest;
	private final ObjectProperty<LocalDate> newest;
	List<ObjectProperty<Photo>> photos = new ArrayList<ObjectProperty<Photo>>(); 
	
	public Album(String name){
		
		this.name = new SimpleStringProperty(name);
		this.photoCount = new SimpleIntegerProperty(0);
		this.oldest = new SimpleObjectProperty<LocalDate>(null);
		this.newest = new SimpleObjectProperty<LocalDate>(null);
		
	}
	
}
