package photoAlbum.model;
/**
 * @author Christopher Shibles
 * @author Randy Mester
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Photo implements Serializable{

	private final StringProperty caption;
	private final ObjectProperty<LocalDate> date;
	List<StringProperty>tags = new ArrayList<StringProperty>();
	
	public Photo(String caption){
		this.caption = new SimpleStringProperty(caption);
		this.date = new SimpleObjectProperty<LocalDate>(null);
		
	}
	
	
}
