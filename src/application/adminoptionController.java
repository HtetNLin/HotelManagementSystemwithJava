package application;

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

public class adminoptionController implements Initializable {


    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnRoom;

    @FXML
    private Button btnSwitch;
    
    @FXML
    private Button btnDepartment;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		btnEmployee.setOnAction(e->{
			try {
				sceneEmployee(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnRoom.setOnAction(e->{
			try {
				sceneRoom(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnSwitch.setOnAction(e->{
			try {
				sceneSwitch(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnDepartment.setOnAction(e->{
			try {
				sceneDepartment(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	private void sceneEmployee(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("employee.fxml"));

		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) btnEmployee.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Employee");
		window.sizeToScene();
		window.centerOnScreen();
	}
	
	private void sceneRoom(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("roomdetail.fxml"));

		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) btnRoom.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Room");
		window.sizeToScene();
		window.centerOnScreen();
	}
	
	public void sceneSwitch(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) btnSwitch.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Login");
		window.sizeToScene();
		window.centerOnScreen();
	}
	
	public void sceneDepartment(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("department.fxml"));
		
		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) btnSwitch.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Department");
		window.sizeToScene();
		window.centerOnScreen();
	}
	
	
	
}
