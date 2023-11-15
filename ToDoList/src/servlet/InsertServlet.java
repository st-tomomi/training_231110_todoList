package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoInsertDAO;

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

		String title = (String) request.getParameter("title");
		//リクエストパラメータではString型になっているのでDateへ変換
		Date deadline = Date.valueOf(request.getParameter("deadline"));

		TodoInsertDAO dao = new TodoInsertDAO();
		try {
			dao.insertTodo(title, deadline, "", 0);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		response.sendRedirect("list-servlet");
	}

}
