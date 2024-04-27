package Battleship;

import javax.servlet.annotation.WebServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Servlet implementation class CoordinateServlet
 */
@WebServlet("/CoordinateServlet")
public class CoordinateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean started = false;
    /**
     * Default constructor. 
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(!started) {
			System.out.println("starting game");
			MainGame.startGame();
			started = true;
		}
        
        // PLAIN TEXT VERSION
		int x = Integer.parseInt(request.getParameter("x"));
        int y = Integer.parseInt(request.getParameter("y"));

        /*Gson gson = new Gson();
        BufferedReader reader = req.getReader();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        int x = jsonObject.get("x").getAsInt();
        int y = jsonObject.get("y").getAsInt();*/

        Coordinates c = new Coordinates(x, y);

        // handle the attack given the coordinates
        User.got = true;
        
        int status = MainGame.user.getUserAttack(c);
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
	}

}