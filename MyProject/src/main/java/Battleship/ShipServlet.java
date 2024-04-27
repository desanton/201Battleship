package Battleship;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class ShipServlet
 */
@WebServlet("/ShipServlet")
public class ShipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
        
        // PLAIN TEXT VERSION
		int x = Integer.parseInt(request.getParameter("x"));
        int y = Integer.parseInt(request.getParameter("y"));

        /*Gson gson = new Gson();
        BufferedReader reader = req.getReader();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        int x = jsonObject.get("x").getAsInt();
        int y = jsonObject.get("y").getAsInt();*/

        // retrieve coordinates
        Coordinates c = new Coordinates(x, y);
        //Opponent opponent = new Opponent();
        // get the ship based on the coordinates
        Ship ship = MainGame.opp.getShip(c);
        // get the list of coordinates also part of that ship
        List<Coordinates> sunkenShip = ship.getShipCoordinates(ship.type);

        PrintWriter pw = response.getWriter();

        for (Coordinates coord : sunkenShip) {
            // print each coordinate in the ship
            pw.println(coord.x + ", " + coord.y);
            // new line after each coordinates
            pw.println();
        }
        pw.flush();

        // note for frontend: when the js receives this code, take each line
        // const coords = ship.split('\n\n');
        // coords.forEach(ship =>
        // 
        //)

        
	}

}