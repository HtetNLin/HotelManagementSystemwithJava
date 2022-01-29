package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Department_DB {


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

	public static ObservableList<department> getDatarooms() {
		Connection conn = ConnectDb();
		ObservableList<department> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from employee_department");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new department(rs.getInt(1), rs.getString(2)));
			}
		} catch (Exception e) {

		}
		return list;

	}
}
