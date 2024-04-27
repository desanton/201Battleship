package Battleship;

public class LoginRegisterTests {
	
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
