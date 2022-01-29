package application;

import java.sql.*;

public class Login_DB {

	public static Connection createConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem","root","");
	}
	
	
	public static Member loginCheck(String n) {
		String sql = "Select * from login where username = ?";
		try (Connection con = createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ps.setString(1, n);
	
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Member m = new Member(rs.getString("username"),rs.getString("password"),rs.getString("type"));
				return m;
			}
		}catch(Exception e) {
			System.out.println("Error:" + e);
		}
		return null;
	}
}
