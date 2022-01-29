package Staff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import application.rooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Room_DB {
	Connection conn = null;

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

	public static ObservableList<rooms> getDatarooms() {
		Connection conn = ConnectDb();
		ObservableList<rooms> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from room");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new rooms(rs.getInt("room_id"), rs.getString("type"),rs.getString("price")+"$",
						rs.getString("status")));
			}
		} catch (Exception e) {

		}
		return list;

	}
	
	public static ObservableList<room_status> getBooking(int t) {
		Connection conn = ConnectDb();
		ObservableList<room_status> list = FXCollections.observableArrayList();
		conn = Room_DB.ConnectDb();
		try {

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select check_in_date,check_out_date from reservation where room_id = '" +t + "' ");
			
			ResultSet rs = (ResultSet) ps.executeQuery();
			while (rs.next()) {
				if(LocalDate.parse(rs.getString("check_out_date")).isBefore(LocalDate.now()) == false) {
				list.add(new room_status(rs.getString("check_in_date"),rs.getString("check_out_date")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	
}
