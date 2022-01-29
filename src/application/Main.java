package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Staff.*;

public class Main extends Application {
	
	public Stage login_stage;
	@Override
	public void start(Stage st) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Splash.fxml"));
			Scene scene = new Scene(root);
		
			 
		
			st.setScene(scene);
			st.show();
			st.getIcons().add(new Image("/HotelManagementSystem/src/application/IMG_0473.JPG"));
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public static int  ID_INC(String table_name,String field_name) {
		int I_D = 0;
		Connection conn = ConnectDb();
		
		try {
			PreparedStatement ps = conn.prepareStatement("select MAX("+field_name+"_id) from "+table_name);
					
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				I_D = rs.getInt(1);
				I_D++;
			}else {
				I_D = 1;
			}
			
		}catch(Exception e) {
			
		}
		
		return I_D;
	}
	
	 public static String charRemoveAt(String str, int p) {  
         return str.substring(0, p) + str.substring(p + 1);  
      }  
	

	public static Connection ConnectDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotelmanagementsystem", "root", "");
			// JOptionPane.showMessageDialog(null, "Connection success");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
