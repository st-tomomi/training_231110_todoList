package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection()
		throws SQLException, ClassNotFoundException {

		//DBのURL
		final String URL = "jdbc:mysql://localhost/todoList";
		//ユーザ
		final String USER = "root";
		//パスワード
		final String PASSWORD = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		//DBに接続
		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

		return con;
	}

}
