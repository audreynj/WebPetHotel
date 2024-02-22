package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HotelListDetails;
import model.Pet;
import model.Hotel;

/**
 * Servlet implementation class EditHotelListServlet
 */
@WebServlet("/editHotelListDetailsServlet")
public class EditHotelListDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditHotelListDetailsServlet() {
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
		// TODO Auto-generated method stub
				HotelListDetailsHelper hldh = new HotelListDetailsHelper();
				PetHelper ph = new PetHelper();
				HotelHelper hh = new HotelHelper();
				
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				HotelListDetails listToUpdate = hldh.searchForListDetailsById(tempId);

				String newListName = request.getParameter("listName");

				String month = request.getParameter("month");
				String day = request.getParameter("day");
				String year = request.getParameter("year");
				
				String hotelName = request.getParameter("hotelName");
				//find our add the new shopper
				Hotel newShopper = hh.findHotel(hotelName);

				LocalDate ld;
				try {
					ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
				} catch (NumberFormatException ex) {
					ld = LocalDate.now();
				}

				try {
					//items are selected in list to add
					String[] selectedPets = request.getParameterValues("allPetsToAdd");
					List<Pet> selectedPetsInList = new ArrayList<Pet>();

					for (int i = 0; i < selectedPets.length; i++) {
						System.out.println(selectedPets[i]);
						Pet c = ph.searchForPetById(Integer.parseInt(selectedPets[i]));
						selectedPetsInList.add(c);
					}
					listToUpdate.setListOfPets(selectedPetsInList);
				} catch (NullPointerException ex) {
					// no items selected in list - set to an empty list
					List<Pet> selectedPetsInList = new ArrayList<Pet>();
					listToUpdate.setListOfPets(selectedPetsInList);
				}

				listToUpdate.setListName(newListName);
				listToUpdate.setBookingDate(ld);
				listToUpdate.setHotel(newShopper);

				hldh.updateList(listToUpdate);

				getServletContext().getRequestDispatcher("/viewAllHotelListsServlet").forward(request, response);
	}

}
