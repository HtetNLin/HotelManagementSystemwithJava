package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class registerController implements Initializable {
	

    @FXML
    private AnchorPane parent;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button Signup;

    @FXML
    private Button Login;

    @FXML
    private ComboBox<String> staffname_box;

    @FXML
    private PasswordField password1;
    
    ObservableList<String> listd;

	int index = -1;
	int I_D = 0;

	static Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public void Add() {
		String sql = "insert into login (login_id,type,username,password)values(?,?,?,?)";

			try {
				conn = Employee_DB.ConnectDb();
				pst = conn.prepareStatement(sql);
				pst.setInt(1, I_D);
				pst.setString(2,"Staff");
				pst.setString(3, username.getText());
				pst.setString(4, password.getText());
				
				pst.execute();
				refreshdata();
				new Alert(AlertType.INFORMATION,"Successfully Registered",ButtonType.OK).showAndWait();

			} catch (Exception e) {
				new Alert(AlertType.ERROR,"Invalid Input"+e,ButtonType.OK).showAndWait();
			}
	}
    
    public void refreshdata() {
    	I_D = Main.ID_INC("login", "login");
		listd = FXCollections.observableArrayList("receptionist staff Name");
		staffname_box.setItems(listd);
		staffname_box.getSelectionModel().selectFirst();

		staffname_box.setOnMouseClicked(e -> {
			staffname_box.getSelectionModel().getSelectedItem().toString();
			conn = Employee_DB.ConnectDb();
			try {

				PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select name from employee where department_id = 9");
				ResultSet rs = (ResultSet) ps.executeQuery();
				while (rs.next()) {

					listd.add(rs.getString("name"));
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
    }
	private void sceneLogin(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Scene scene2 = new Scene(root);
		
		Stage window = (Stage) Login.getScene().getWindow();
		window.setScene(scene2);
		window.setTitle("Admin Option");
		window.sizeToScene();
		window.centerOnScreen();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		refreshdata();
		
		Signup.setOnAction(e->{
			if(password.getText() .equals(password1.getText())) {
				Add();
			}else {
				new Alert(AlertType.WARNING,"Password must be same!",ButtonType.OK).showAndWait();
			}
		});
		
		Login.setOnAction(e->{
			try {
				sceneLogin(e);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
		
		
	}
	
	
	
	

}
