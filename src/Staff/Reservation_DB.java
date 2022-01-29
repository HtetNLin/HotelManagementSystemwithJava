package Staff;

import java.sql.DriverManager;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Reservation_DB {
	Connection conn = null;

	public static void main(String[] args) {
		System.out.println(getDatausers());
	}

	public static Connection ConnectDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/hotelmanagementsystem", "root",
					"");
		
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public static ObservableList<reservation> getDatausers() {
		Connection conn = ConnectDb();
		ObservableList<reservation> list = FXCollections.observableArrayList();
		try {

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from reservation");
			ResultSet rs = (ResultSet) ps.executeQuery();
			while (rs.next()) {
				
				
				list.add(new reservation(rs.getInt(1),rs.getInt(2), rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getString(8)+"$"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
