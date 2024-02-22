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
 * Servlet implementation class CreateNewHotelListServlet
 */
@WebServlet("/createNewHotelListServlet")
public class CreateNewHotelListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewHotelListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PetHelper ph = new PetHelper();
		String listName = request.getParameter("listName");
		System.out.println("List Name: " + listName);
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");

		String hotelName = request.getParameter("hotelName");

		LocalDate ld;
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		}catch (NumberFormatException ex){
			ld = LocalDate.now();
		}
		
		String[] selectedPets = request.getParameterValues("allPetsToAdd");
		List<Pet> selectedPetsInList = new ArrayList<Pet>();
		
		if(selectedPets != null && selectedPets.length > 0){
			for(int i = 0; i<selectedPets.length; i++) {
				System.out.println(selectedPets[i]);
				Pet c = ph.searchForPetById(Integer.parseInt(selectedPets[i]));
				selectedPetsInList.add(c);
			}
		}
		
		Hotel hotel = new Hotel(hotelName);
		HotelListDetails hld = new HotelListDetails(listName, ld, hotel);
		hld.setListOfPets(selectedPetsInList);
		HotelListDetailsHelper slh = new HotelListDetailsHelper();
		slh.insertNewListDetails(hld);
		
		System.out.println("Success!");
		System.out.println(hld.toString());
		
		getServletContext().getRequestDispatcher("/viewAllHotelListsServlet").forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
