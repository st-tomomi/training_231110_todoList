package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Parameters;
import constant.SessionInfo;
import dao.LoginDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータからｉｄとpasswordを取得
		String id = request.getParameter(Parameters.LOGIN_ID);
	    String password = request.getParameter(Parameters.LOGIN_PASSWORD);

	    // ログインユーザーのid
	    String loginId = "";

	    LoginDAO dao = new LoginDAO();

	    // DBに接続し、ログイン認証を行う
	    try {
	        loginId = dao.LoginAuthenticate(id, password);
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    //成功でTodo一覧、失敗でログイン画面を表示
	    String path = "";
	    if (loginId != "") {
	    	HttpSession session = request.getSession();
	    	session.setAttribute(SessionInfo.LOGIN_USER_ID, loginId);
	    	path = "list-servlet";
	    } else {
	    	path ="/WEB-INF/jsp/Login.jsp";
	    }

	    request.getRequestDispatcher(path).forward(request, response);


	}

}
