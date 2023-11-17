package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;

public class LoginDAO {
	public String LoginAuthenticate(String id, String password) throws SQLException, ClassNotFoundException {

        String resId = "";

        String sql ="SELECT id FROM users WHERE id=? AND password=?";

        // DBに接続しユーザー情報を取得する
        try (Connection con = DBConnection.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            //idどｐａｓｓｗｏｒｄを設定する
        	pstmt.setString(1, id);
            pstmt.setString(2, password);

            // SQLを実行する
            ResultSet res = pstmt.executeQuery();

            // idとパスワードが一致するユーザーが存在した場合
            if(res.next()) {
                // 返却用変数にDBから取得したIDを設定
                resId = res.getString("id");
            }
        }
        return resId;
    }

}
