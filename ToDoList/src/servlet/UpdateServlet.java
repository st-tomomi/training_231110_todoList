package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Parameters;
import dao.UpdateDAO;
import model.Todo;
import util.MyUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update-servlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * リクエストパラメータの"id"を条件にテーブル内を検索
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータからTodoのIDを取得
		int id = Integer.parseInt(request.getParameter(Parameters.TODO_ID));

		UpdateDAO dao = new UpdateDAO();
		Todo todo = new Todo();

		try {
			todo = dao.getTodo(id);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		request.setAttribute("todo", todo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Edittask.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter(Parameters.TODO_ID));
		String title = request.getParameter(Parameters.TITLE);
		Date duedate = Date.valueOf(request.getParameter(Parameters.DUEDATE));
		Date completiondate = null;

		if(request.getParameter(Parameters.COMPDATE) != null) {
			Date.valueOf(request.getParameter(Parameters.COMPDATE));
		}

		int status = MyUtil.getChackBoxStatus(request.getParameter(Parameters.STATUS));

		//ログ
		Logger.getLogger(UpdateServlet.class.getName()).info("id : " + request.getParameter(Parameters.TODO_ID));
		Logger.getLogger(UpdateServlet.class.getName()).info("title : " + request.getParameter(Parameters.TITLE));
		Logger.getLogger(UpdateServlet.class.getName()).info("duedate : " + request.getParameter(Parameters.DUEDATE));
		Logger.getLogger(UpdateServlet.class.getName()).info("completiondate : " + request.getParameter(Parameters.COMPDATE));
		Logger.getLogger(UpdateServlet.class.getName()).info("status : " + request.getParameter(Parameters.STATUS));

		//Todo更新
		UpdateDAO dao = new UpdateDAO();
		try {
			dao.updateTodo(id, title, duedate, completiondate, status);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		response.sendRedirect("list-servlet");

	}

}
