package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class splashController implements Initializable {

	@FXML
	private AnchorPane rootpane;
	
	@FXML 
	private MediaView media;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		new splashScreen().start();
		
		MediaPlayer mediaplayer = new MediaPlayer(new Media(this.getClass().getResource("Loading circle icon on white background ( 338 X 640 ).mp4").toExternalForm()));
		mediaplayer.setAutoPlay(true);
		
		media.setMediaPlayer(mediaplayer);
		
		
		
	}
	
	class splashScreen extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(3000);
				
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Parent root = null;
						try {
							root =  FXMLLoader.load(getClass().getResource("Login.fxml"));
						} catch (IOException e) {
							// TODO: handle exception
							Logger.getLogger(splashController.class.getName(), null).log(Level.SEVERE,null, e);
						}
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.setTitle("Login");
						stage.setScene(scene);
						stage.show();
						
						 rootpane.getScene().getWindow().hide();
							
					}
				});
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}

	
