package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Parameters;
import dao.TodoInsertDAO;
import util.MyUtil;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert-servlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * Todoをデータベースに登録するためのサーブレット
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String title = (String) request.getParameter(Parameters.TITLE);
		//リクエストパラメータではString型になっているのでDateへ変換
		Date duedate = Date.valueOf(request.getParameter(Parameters.DUEDATE));
		Date completiondate = null;
		String memoText = (String) MyUtil.getValueOrDefault(request.getParameter(Parameters.MEMO), "");

		if(request.getParameter(Parameters.COMPDATE) != null) {
			completiondate = Date.valueOf(request.getParameter(Parameters.COMPDATE));
		}

		int status = MyUtil.getChackBoxStatus(request.getParameter(Parameters.STATUS));

		TodoInsertDAO dao = new TodoInsertDAO();

		//ログ
		Logger.getLogger(UpdateServlet.class.getName()).info("id : " + request.getParameter(Parameters.TODO_ID));
		Logger.getLogger(UpdateServlet.class.getName()).info("title : " + request.getParameter(Parameters.TITLE));
		Logger.getLogger(UpdateServlet.class.getName()).info("duedate : " + request.getParameter(Parameters.DUEDATE));
		Logger.getLogger(UpdateServlet.class.getName()).info("completiondate : " + request.getParameter(Parameters.COMPDATE));
		Logger.getLogger(UpdateServlet.class.getName()).info("status : " + request.getParameter(Parameters.STATUS));

		try {
			dao.insertTodo(title, duedate, completiondate, memoText, status);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		response.sendRedirect("list-servlet");
	}

}
