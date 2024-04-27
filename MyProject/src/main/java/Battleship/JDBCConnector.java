package Battleship;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnector {
	
    public static String getSQLPassword() {
    	String p = "033104erika";
    	
    	//didn't use this function, it couldn't find the file, so I deleted the file as well
    	//p = getSQLPasswordFromTextFile();
    	
    	return p;
    }
    
    public static String getSQLPasswordFromTextFile() {
    	String p = "";
    	try {
    		BufferedReader br = new BufferedReader(new FileReader("033104erika.txt"));
    		p = br.readLine();
    	    br.close();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
    	return p;
    }

    
	
	public static int registerUser(String username, String email, String password) {
        try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    System.out.println("Driver loaded successfully!");
		} catch (ClassNotFoundException e) {
		    System.out.println("Driver not found!");
		    e.printStackTrace();
		}
        Connection conn = null;
        Statement st = null;
		ResultSet rs = null;

        int userID = -1;

        try {
        	conn = DriverManager.getConnection("jdbc:mysql://localhost/Battleship?user=root&password=" + getSQLPassword());
                        // switch password when needed^^
        	
        	System.out.println("username:" + username);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Users WHERE username='" + username + "'");
			
			
			if (!rs.next()) {
				// username not in use
				st.close();
				rs.close();
				
				st = conn.createStatement();
				rs = st.executeQuery("SELECT * FROM Users WHERE email='" + email + "'");
				if (!rs.next()) {

					// email not in use
					rs.close();

					// wins and losses default to zero when a new user is created
                    int count = st.executeUpdate("INSERT INTO Users (username, password, email, wins, losses) VALUES ('" + username + "', '" + password + "', '" + email + "', 0, 0)");
					if (count > 0) {
					    System.out.println("Insert successful, rows affected: " + count);
					} else {
					    System.out.println("Insert failed, no rows affected.");
					}
					rs = st.executeQuery("SELECT LAST_INSERT_ID()");
					rs.next();
					userID = rs.getInt(1);
				} else {
					// email is taken
					System.out.println("Email is taken!");
					userID = -2;
				}
			}
			else {
				System.out.println("Username is taken!");
			}
		} catch (SQLException e) {
			System.out.println("SQLException in registerUser. ");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("sql: " + e.getMessage());
			}
		}
		
		return userID;
	}

	public static int loginUser(String username, String password) {
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    System.out.println("Driver loaded successfully!");
		} catch (ClassNotFoundException e) {
		    System.out.println("Driver not found!");
		    e.printStackTrace();
		}

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		boolean success = false;
		int userID = -1;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Battleship?user=root&password=" + getSQLPassword());
						// switch password when needed^^
            st = conn.createStatement();
            rs = st.executeQuery("SELECT password, user_id FROM Users WHERE username='" + username + "'");
            if (rs.next()) {
            	String pwd = rs.getString("password");
            	// ensure passwords match
            	if (password != null && pwd.equals(password)) {
            		success = true;
            		userID = rs.getInt("user_id");
            		System.out.println("Successful login for user with ID: " + userID);
            	}
            }
		} catch (SQLException e) {
			System.out.println("Exception occured when attempting to log in.");
	        e.printStackTrace();
		} finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException on close: " + e.getMessage());
            }
        }
		
		return userID;
	}

	// update win
	public static boolean updateWin(String username) {
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    System.out.println("Driver loaded successfully!");
		} catch (ClassNotFoundException e) {
		    System.out.println("Driver not found!");
		    e.printStackTrace();
		}
        Connection conn = null;
        Statement st = null;
		ResultSet rs = null;
		boolean success = false;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Battleship?user=root&password=" + getSQLPassword());
                        // switch password when needed^^
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Users WHERE username='" + username + "'");

			if (!rs.next()) {
				int wins = rs.getInt("wins");
				wins++;
				String update = "UPDATE Users SET wins = " + wins + " WHERE username = '" + username + "'";
				int updated = st.executeUpdate(update);
				if (updated > 0) {
					conn.commit();
					success = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Exception occurred when attempting to update balance.");
			e.printStackTrace();
		} finally {
			try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}

		return success;

	}

	// update losses
	public static boolean updateLoss(String username) {
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    System.out.println("Driver loaded successfully!");
		} catch (ClassNotFoundException e) {
		    System.out.println("Driver not found!");
		    e.printStackTrace();
		}
        Connection conn = null;
        Statement st = null;
		ResultSet rs = null;
		boolean success = false;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Battleship?user=root&password=" + getSQLPassword());
                        // switch password when needed^^
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Users WHERE username='" + username + "'");

			if (!rs.next()) {
				int losses = rs.getInt("losses");
				losses++;
				String update = "UPDATE Users SET losses = " + losses + " WHERE username = '" + username + "'";
				int updated = st.executeUpdate(update);
				if (updated > 0) {
					conn.commit();
					success = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Exception occurred when attempting to update balance.");
			e.printStackTrace();
		} finally {
			try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}

		return success;
	}
}