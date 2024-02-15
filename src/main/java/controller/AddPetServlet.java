package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pets;

/**
 * Servlet implementation class AddPetServlet
 */
@WebServlet("/addPetServlet")
public class AddPetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String owner = request.getParameter("owner");
		if (type.isEmpty() || name.isEmpty() || owner.isEmpty() || type == null || name == null || owner==null) {
			getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		} else {
			Pets p = new Pets(type, name, owner);
			PetHelper ph = new PetHelper();
			ph.insertPet(p);

			getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}
	}

}
