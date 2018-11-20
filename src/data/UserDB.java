package data;

import java.sql.*;

import business.User;

public class UserDB {
	public static int insert(User user) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		
		String query
				= "INSERT INTO User (Email, FirstName, LastName) "
				+ " VALUES (? , ?, ?)";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getEmploymentStatus());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

}
