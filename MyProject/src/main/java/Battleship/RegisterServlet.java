package Battleship;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameters
		String newUsername = request.getParameter("username");
		
		System.out.println("newUsername: " + newUsername);
		String newPassword = request.getParameter("password");
		String newEmail = request.getParameter("email");
		
		int userID = JDBCConnector.registerUser(newUsername, newEmail, newPassword);
		// Generate response and call DAO
		response.setContentType("application/json");
		response.getWriter().print("{\"ok\": " + userID  + "}");
		response.getWriter().flush();
		response.getWriter().close();
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	public static void main(String args[]) {
		JDBCConnector.registerUser("twenty", "chicken", "broman");
		JDBCConnector.registerUser("twenty", "email", "broman");
		JDBCConnector.registerUser("fifty", "chicken", "broman");
		JDBCConnector.registerUser("unique", "email", "pwd");
		JDBCConnector.loginUser("twenty", "wrongPassowrd");
		JDBCConnector.loginUser("unique", "pwd");
		JDBCConnector.loginUser("twenty", "broman");
		
	}

}
