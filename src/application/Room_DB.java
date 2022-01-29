package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

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
}
