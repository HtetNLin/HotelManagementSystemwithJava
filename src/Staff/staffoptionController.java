package Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import application.*;

public class staffoptionController implements Initializable {
	
	

    @FXML
    private Button S_btnRoomStatus;

    @FXML
    private Button S_btnReservation;

    @FXML
    private Button S_btnCustomer;

    @FXML
    private Button btnSwitch;


	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		btnSwitch.setOnAction(e->{
			try {
				sceneSwitch(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		S_btnReservation.setOnAction(e->{
			try {
				sceneReservation(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		S_btnRoomStatus.setOnAction(e->{
			try {
				sceneRoomstatus(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		S_btnCustomer.setOnAction(e->{
			try {
				sceneCustomer(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	private void sceneSwitch(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));

		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) btnSwitch.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Login");
		window.sizeToScene();
		window.centerOnScreen();
	}
	private void sceneRoomstatus(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("roomStatus.fxml"));

		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) S_btnRoomStatus.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Room status");
		window.sizeToScene();
		window.centerOnScreen();
	}
	private void sceneCustomer(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("customer.fxml"));

		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) S_btnCustomer.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Customer");
		window.sizeToScene();
		window.centerOnScreen();
	}
	

	private void sceneReservation(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("reservation.fxml"));

		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) S_btnReservation.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Reservation");
		window.sizeToScene();
		window.centerOnScreen();
	}
	

}
