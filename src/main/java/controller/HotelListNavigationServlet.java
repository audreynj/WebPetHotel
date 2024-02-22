package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HotelListDetails;

/**
 * Servlet implementation class HotelListNavigationServlet
 */
@WebServlet("/hotelListNavigationServlet")
public class HotelListNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelListNavigationServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HotelListDetailsHelper hldh = new HotelListDetailsHelper();
		PetHelper ph = new PetHelper();
		String act = request.getParameter("doThisToList");

		if (act == null) {
			// no button has been selected
			getServletContext().getRequestDispatcher("/viewAllHotelListsServlet").forward(request, response);

		} else if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				HotelListDetails listToDelete = hldh.searchForListDetailsById(tempId);
				hldh.deleteList(listToDelete);

			} catch (NumberFormatException e) {
				System.out.println("Forgot to click a button");
			} finally {
				getServletContext().getRequestDispatcher("/viewAllHotelListsServlet").forward(request, response);
			}

		} else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				HotelListDetails listToEdit = hldh.searchForListDetailsById(tempId);
				request.setAttribute("listToEdit", listToEdit);
				
				request.setAttribute("month", listToEdit.getBookingDate().getMonthValue());
				request.setAttribute("day", listToEdit.getBookingDate().getDayOfMonth());
				request.setAttribute("year", listToEdit.getBookingDate().getYear());
				PetHelper hldhForPets = new PetHelper();
				
				request.setAttribute("allPets", hldhForPets.showAllPets());
							
				if(hldhForPets.showAllPets().isEmpty()){
					request.setAttribute("allPets", " ");
				}
				getServletContext().getRequestDispatcher("/edit-hotel-list.jsp").forward(request, response);	
			} catch (NumberFormatException e) {
				getServletContext().getRequestDispatcher("/viewAllHotelListsServlet").forward(request, response);
			} 

		} else if (act.equals("add")) {
			request.setAttribute("allPets", ph.showAllPets());
			if(ph.showAllPets().isEmpty()) {
				request.setAttribute("allPets", " ");
			}
			
			getServletContext().getRequestDispatcher("/new-hotel-list.jsp").forward(request, response);
		}
	}

}
