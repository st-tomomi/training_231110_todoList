package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DBConnection;
import model.Todo;

public class TodoListDAO {

	/**
	 * ToDoの一覧を取得する
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Todo> getTodoList() throws ClassNotFoundException, SQLException {
		//返却用List
		List<Todo> todoList = new ArrayList<>();
		//SQL
		String sql = "SELECT id, title, duedate, completiondate, memotext, status FROM TODO";

		//DBに接続
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//SQLを実行しResultSetの変数に結果を格納する
			ResultSet res = pstmt.executeQuery();
			//SQL実行結果をリストに格納
			while (res.next()) {
				int id = res.getInt("id");
				String title = res.getString("title");
				Date duedate = res.getDate("duedate");
				Date completiondate = res.getDate("completiondate");
				String memoText = res.getString("memotext");
				int status = res.getInt("status");

//				memoText = (memoText != null) ? memoText : "";

				todoList.add(new Todo(id, title, duedate, completiondate, memoText, status));
			}
		}

		return todoList;
	}

}
