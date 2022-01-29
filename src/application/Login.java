package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class Login implements Initializable {
		
	  @FXML
	    private TextField username;

	    @FXML
	    private PasswordField password;
	    
	    @FXML
	    private Button Login;
	    
	    @FXML
	    private Button Login2;

	    @FXML
	    private Button btn_register;
	    
	    

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
			
			Login.setOnAction(e->{
			
				String name = username.getText();
				String psw = password.getText();
				String ad = "Admin";
				
				if(name.isEmpty() && psw.isEmpty()) {
					new Alert(AlertType.WARNING,"Name and Password should not be empty",ButtonType.OK).showAndWait();
				}
				
				else if(name.isEmpty()) {
					new Alert(AlertType.WARNING,"Name should not be empty",ButtonType.OK).showAndWait();
					username.requestFocus();
					return;
				}
				
				else if(psw.isEmpty()) {
					new Alert(AlertType.WARNING,"Password should not be empty",ButtonType.OK).showAndWait();
					password.requestFocus();
					return;
					
				}else {
					Member m = Login_DB.loginCheck(username.getText());
					if(m != null) {
						
						if(psw.equals(m.getPassword())) {
							if(ad.equals(m.gettype())) {
								try {
									changesceneA(e);
								} catch (Exception e2) {
									// TODO: handle exception
								}
							}else {
								new Alert(AlertType.WARNING,"Invalid Username And Password",ButtonType.OK).showAndWait();
							}
						}
						else {
							new Alert(AlertType.WARNING,"Invalid Password",ButtonType.OK).showAndWait();

						}
//					Main main = new Main();
					
					}else {
						new Alert(AlertType.WARNING,"There is no registration with this username",ButtonType.OK).showAndWait();

					}
					
				}
				
				
				
				
			});
			Login2.setOnAction(e->{
				
				String name = username.getText();
				String psw = password.getText();
				String ad = "Admin";
				
				if(name.isEmpty() && psw.isEmpty()) {
					new Alert(AlertType.WARNING,"Name and Password should not be empty",ButtonType.OK).showAndWait();
				}else if(name.isEmpty()) {
					new Alert(AlertType.WARNING,"Name should not be empty",ButtonType.OK).showAndWait();
					username.requestFocus();
					return;
				}
				
				else if(psw.isEmpty()) {
					new Alert(AlertType.WARNING,"Password should not be empty",ButtonType.OK).showAndWait();
					password.requestFocus();
					return;
					
				}else {

					Member m = Login_DB.loginCheck(username.getText());
					if(m != null) {
						
						if(psw.equals(m.getPassword())) {
							if("Staff".equals(m.gettype())) {
								try {
									changesceneS(e);
								} catch (Exception e2) {
									// TODO: handle exception
								}
							}else {
								new Alert(AlertType.WARNING,"Invalid UserName and Password",ButtonType.OK).showAndWait();
							}
						}
						else {
							new Alert(AlertType.WARNING,"Invalid Password",ButtonType.OK).showAndWait();

						}
//					Main main = new Main();
						
					}else {
						new Alert(AlertType.WARNING,"There is no registration with this username",ButtonType.OK).showAndWait();
					}
				}
				
				
				
				
			});
			
			btn_register.setOnAction(e->{
				try {
					changesceneR(e);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			});
		}
		
		
		
		private void changesceneA(ActionEvent event)throws IOException{
			Parent root = FXMLLoader.load(getClass().getResource("adminOption.fxml"));

			Scene scene2 = new Scene(root);
			
			Stage window = (Stage) Login.getScene().getWindow();
			window.setResizable(false);
			window.setTitle("Admin Option");
			window.setScene(scene2);
			window.sizeToScene();
			window.centerOnScreen();
		}
		private void changesceneR(ActionEvent event)throws IOException{
			Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
			
			Scene scene2 = new Scene(root);
			
			Stage window = (Stage) Login.getScene().getWindow();
			window.setResizable(false);
			window.setTitle("Register");
			window.setScene(scene2);
			window.sizeToScene();
			window.centerOnScreen();
		}
		private void changesceneS(ActionEvent event)throws IOException{
			Parent root = FXMLLoader.load(getClass().getResource("/Staff/staffOption.fxml"));
			
			Scene scene2 = new Scene(root);
			
			Stage window = (Stage) Login.getScene().getWindow();
			window.setResizable(false);
			window.setTitle("Staff Option");
			window.setScene(scene2);
			window.sizeToScene();
			window.centerOnScreen();
		}
		
		
		
		
}
