package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Parameters;
import dao.DeleteDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete-servlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("DELETE".equals(request.getMethod())) {
            doDELETE(request, response);
        } else {
            super.service(request, response);
        }
    }

    protected void doDELETE(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // DELETE メソッドに対する処理をここに書く
        int id = Integer.parseInt(request.getParameter(Parameters.TODO_ID));

        DeleteDAO dao = new DeleteDAO();
        try {
            dao.deleteTodo(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete todo.");
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }


	/**
	 * リクエストパラメータの"id"を条件にレコードを削除
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータからTodoのIDを取得
		int id = Integer.parseInt(request.getParameter(Parameters.TODO_ID));

		DeleteDAO dao = new DeleteDAO();

		try {
			dao.deleteTodo(id);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		response.sendRedirect("list-servlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
