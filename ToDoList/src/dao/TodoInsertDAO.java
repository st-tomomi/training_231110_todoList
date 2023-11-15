package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DBConnection;

/**
 * ToDoをDBに登録する
 * @author tomomisato
 *
 */
public class TodoInsertDAO {

	public int insertTodo(String title, Date deadline, String memoText, int status)
		throws ClassNotFoundException, SQLException {

		int rowsUpdated = 0;

		String sql = "INSERT INTO todo(title, deadline) VALUES(?, ?)";

		//DBに接続してToDoを登録
		try(Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, title);
			pstmt.setDate(2, deadline);
			rowsUpdated = pstmt.executeUpdate();
			System.out.println( "Updated rows:" + rowsUpdated );
		} catch (SQLException e) {
			System.err.println("Update failed.");
		}
		return rowsUpdated;
	}

}
