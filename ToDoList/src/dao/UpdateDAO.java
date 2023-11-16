package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;
import model.Todo;

/**
 * Todoの内容を更新する
 * @author tomomisato
 *
 */
public class UpdateDAO {

	public int updateTodo(int id, String title, Date duedate)
		throws SQLException, ClassNotFoundException {

		int rowsUpdated = 0;

		String sql = "UPDATE todo SET title = ?, duedate = ? WHERE id = ?";

		try(Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, title);
			pstmt.setDate(2, duedate);
			pstmt.setInt(3, id);
			rowsUpdated = pstmt.executeUpdate();
		}

		return rowsUpdated;
	}

	/**
	 * Todoを1件取得
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Todo getTodo(int id) throws SQLException, ClassNotFoundException {
		Todo todo = new Todo();
		String sql = "SELECT * FROM todo WHERE id = ?";

		//DBに接続してToDoを検索
		try(Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setInt(1, id);
				ResultSet res = pstmt.executeQuery();

				while(res.next()) {
					todo.setId(res.getInt("id"));
					todo.setTitle(res.getString("title"));
					todo.setDuedate(res.getDate("duedate"));

					//改修案
//					todo.setMemoText(res.getString("memoText"));
//					todo.setStatus(res.getInt("status"));
				}
		}
		return todo;

	}
}
