package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

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
		int status = 0;

		if(request.getParameter(Parameters.COMPDATE) != null) {
			completiondate = Date.valueOf(request.getParameter(Parameters.COMPDATE));
		}
		if(request.getParameter(Parameters.STATUS) != null) {
			status = Integer.parseInt(request.getParameter(Parameters.STATUS));
		}

		TodoInsertDAO dao = new TodoInsertDAO();
		try {
			dao.insertTodo(title, duedate, completiondate, memoText, status);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		response.sendRedirect("list-servlet");
	}

}
