package photoAlbum.application;
/**
 * @author Christopher Shibles
 * @author Randy Mester
 */

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import photoAlbum.model.*;
import photoAlbum.view.LoginController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class PhotoAlbum extends Application {
	
	private Stage primaryStage;
    private BorderPane rootLayout;


    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PhotoAlbum");

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
            

            LoginController controller = loader.getController();
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
	
	
	
	
	public boolean UserLogin(User temp, List<User> users){
		
		for(int x = 0; x < users.size(); x++){
			
			if(users.get(x).equals(temp)){
				return true;
			}
		}
		return false;
		
	}
	
	
	
	
}
