package photoAlbum.application;
/**
 * @author Christopher Shibles
 * @author Randy Mester
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import photoAlbum.model.*;
import photoAlbum.view.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class PhotoAlbum extends Application {
	
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    static ObservableList<User> users = FXCollections.observableArrayList();
    

    public PhotoAlbum(){
    	
    	users.add(new User("Queen Latifah","QL"));
		users.add(new User("Trump", "Jackass"));
		users.add(new User("Sara Bareilles", "Brave Enough"));
		
    	
    }
    
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Photo Album Login");

        initRootLayout();

        showLogin();
    }


    public void initRootLayout() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();


            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showLogin() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PhotoAlbum.class.getResource("/photoAlbum/view/Login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            rootLayout.setCenter(login);
            

            Controller controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
	public static void main(String[] args) throws IOException {
		
		launch(args);

		
	}
	
	public ObservableList<User> getList(){
		return users;
	}
	
	public Stage getStage(){
		return primaryStage;
	}
	
}
