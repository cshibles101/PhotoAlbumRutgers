package photoAlbum.view;

import photoAlbum.application.PhotoAlbum;
import photoAlbum.model.Album;
import photoAlbum.model.Photo;
import photoAlbum.model.User;

public class SearchController {
	
	private Album activeAlbum;
	private User activeUser;
	private PhotoAlbum photoAlbum;
	
	
	public void initialize(){
		
	}
	
	public void setMainApp(Album activeAlbum, User activeUser, PhotoAlbum photoAlbum){
		
		this.activeAlbum = activeAlbum;
		this.activeUser = activeUser;
		this.photoAlbum = photoAlbum;
		
	}

}
