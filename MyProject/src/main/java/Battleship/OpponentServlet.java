package Battleship;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OpponentServlet
 */
@WebServlet("/OpponentServlet")
public class OpponentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpponentServlet() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// PLAIN TEXT VERSION
//		int x = Integer.parseInt(request.getParameter("x"));
//        int y = Integer.parseInt(request.getParameter("y"));

        // handle the attack given the coordinates
        Opponent.gotOpp = true;
        
        Coordinates c = MainGame.opp.getOpponentAttack();
        int x = c.x;
        int y = c.y;
        
        int status = MainGame.opp.setOpponentAttack(c);
        PrintWriter pw = response.getWriter();

        if (status == 2) {
            // sunken
            pw.print(2);
        } else if (status == 3) {
            // attacked empty
            pw.print(3);
        } else if (status == 4) {
            // attacked occupied
            pw.print(4);
        } else if (status == 5) {
        	pw.print(5);
        }
        pw.flush();
	}*/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		Opponent.gotOpp = true;
		
		Coordinates c = MainGame.opp.getOpponentAttack();
	    int x = c.x;
	    int y = c.y;
	    
	    int status = MainGame.opp.setOpponentAttack(c);
	    PrintWriter pw = response.getWriter();
	    pw.print(status + "" + x + "" + y); // Example: "312" where 3=status, 1=x, 2=y
	    pw.flush();
	}


}
