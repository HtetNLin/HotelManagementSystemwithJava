package Staff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import application.employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer_DB {
	
	Connection conn = null;

	public static Connection ConnectDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotelmanagementsystem", "root", "");
			
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}

	public static ObservableList<customer> getCustomer() {
		Connection conn = ConnectDb();
		ObservableList<customer> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from customer");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new customer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
			}
		} catch (Exception e) {
			System.out.println(e);

		}
		return list;

	}
}
