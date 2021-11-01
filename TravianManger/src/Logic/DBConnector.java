package Logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {

	public static Connection createConnection() {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://pei17y9c5bpuh987.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/zqjblm9o4197vh18",
					"y29xcg59bixo8i34", "ijfobwfkxwncagi3");
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// st
	// expected params- fullName, email
	public static boolean insertToTable(List<Object> params, Connection con) {

		try {
			String query = " insert into users (username, password, fullName, email)" + " values (?, ?,?,?)";
			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, (String) params.get(0));
			preparedStmt.setString(2, (String) params.get(1));
			preparedStmt.setString(3, (String) params.get(2));
			preparedStmt.setString(4, (String) params.get(3));
			// execute the prepared statement
			preparedStmt.execute();
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public static boolean insertToTableMore(List<Object> params, Connection con) {

		try {
			String query = " UPDATE users set site=? ,server=? ,siteurl=? ,username_travian=?, password_travian=?, rememberMe=? where username=?;";
			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, (String) params.get(0));
			preparedStmt.setString(2, (String) params.get(1));
			preparedStmt.setString(3, (String) params.get(2));
			preparedStmt.setString(4, (String) params.get(3));
			preparedStmt.setString(5, (String) params.get(4));
			preparedStmt.setString(6, (String) params.get(5));
			preparedStmt.setString(7, (String) params.get(6));
			// execute the prepared statement
			preparedStmt.execute();
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	// expected params- email
	public static int getNumberOfUsers(List<Object> params, Connection con) {
		String query = "SELECT  COUNT(*) AS total FROM users WHERE username=? and password=?";
		// create the java statement
		PreparedStatement st;
		try {
			st = con.prepareStatement(query);
			st.setString(1, (String) params.get(0));
			st.setString(2, (String) params.get(1));
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery();
			rs.next();
			return rs.getInt("total");
		} catch (SQLException e) {

			e.printStackTrace();
			return -1;
		}

	}

	public static List<String> rememberMe(List<Object> params, Connection con) {
		try {
			String query = "SELECT  *  FROM users WHERE username=? and password=? and rememberMe='yes'";
			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, (String) params.get(0));
			preparedStmt.setString(2, (String) params.get(1));
			// execute the prepared statement
			ResultSet rs = preparedStmt.executeQuery();
			boolean flag = rs.next();
			if (flag) {
				List<String> details = new ArrayList<String>();
				details.add(rs.getString(6));
				details.add(rs.getString(7));
				details.add(rs.getString(8));
				details.add(rs.getString(9));
				details.add(rs.getString(10));
				details.add(rs.getString(11));

				return details;
			}
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
		return null;

	}

	public static boolean SetReadMe(List<Object> params, Connection con) {

		try {
			String query = " UPDATE users set readme='yes' where username=?;";
			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, (String) params.get(0));
			// execute the prepared statement
			preparedStmt.execute();
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public static boolean getreadMe(List<Object> params, Connection con) {
		String query = "SELECT  COUNT(*) AS total FROM users WHERE username=? and readme='yes'";
		// create the java statement
		PreparedStatement st;
		try {
			st = con.prepareStatement(query);
			st.setString(1, (String) params.get(0));
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery();
			rs.next();
			if(rs.getInt("total")>0)
				return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
		return false;
	}
}
