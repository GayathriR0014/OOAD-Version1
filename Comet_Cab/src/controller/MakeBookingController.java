
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Booking;
import model.CabType;
import model.Location;
import model.CardDetails;

import service.BookingService;
import service.BookingServiceImpl;
import model.Place;

 // Servlet implementation class Login
 
@WebServlet("/MakeBookingController")
public class MakeBookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MakeBookingController() {}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pickLoc = request.getParameter("pick");
		System.out.println("pickup "+pickLoc);
		String dropLoc = request.getParameter("drop");
		System.out.println("drop "+dropLoc);
		String netId = request.getParameter("netId");
		System.out.println("netID "+netId);
		String cab = request.getParameter("cab");
		System.out.println("cab "+cab);
				
		float eFare;
		BookingService bookingService = new BookingServiceImpl();
		CabType cabType = CabType.valueOf(cab.trim());
		Location location = new Location(Place.valueOf(pickLoc.trim()),
				Place.valueOf(dropLoc.trim()));
		eFare = bookingService.estimateFare(location, cabType, netId); 
		
		String confirmation;
		confirmation=bookingService.makeBooking(netId, location, eFare,cabType.name());
		
	/*	if (confirmation // to be continued
		request.setAttribute("message", "Hello "+"Test");
		request.setAttribute("netId", "Test");
		request.getRequestDispatcher("welcome.jsp").forward(request, response);*/
			
		String res1 = confirmation;
		response.setContentType("application/text");
		response.getWriter().print(res1); 
	
	}
}

