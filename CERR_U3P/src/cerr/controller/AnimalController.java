package cerr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cerr.dao.AnimalDAOImpl;
import cerr.model.Animal;





/**
 * Servlet implementation class AnimalController
 */
@WebServlet("/AnimalController")
public class AnimalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Animal animal; //Creamos el objeto
	private List<Animal> animals; //Lista de animales
	private AnimalDAOImpl animalDAO; //Modificamos la lista
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalController() {
        super();
        animal = new Animal();
        animals = new ArrayList<Animal>();
        animalDAO = new AnimalDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btn_save") != null) {
			animal.setName(request.getParameter("name"));
			animal.setHome(request.getParameter("home"));
			try {
			animal.setAge(Integer.parseInt(request.getParameter("age")));
			}catch(Exception e) {
				animal.setAge(5);
			}
			if(animal.getId()==0L) {
				animalDAO.createAnimal(animal);
			} else {
				animalDAO.updateAnimal(animal);
			}
			animals = animalDAO.readAllAnimals();
			request.setAttribute("animals", animals);
			request.getRequestDispatcher("animal_list.jsp").forward(request, response);
		} else if (request.getParameter("btn_new") != null) {
			animal = new Animal();
			request.getRequestDispatcher("animal_form.jsp").forward(request, response);
		}else if(request.getParameter("btn_edit")!=null) {
			try {
				Long id = Long.parseLong(request.getParameter("id"));
				animal = animalDAO.readAnimal(id);
			}catch(Exception e) {
				animal = new Animal();
			}
			request.setAttribute("animal", animal);
			request.getRequestDispatcher("animal_form.jsp").forward(request, response);
		}else if(request.getParameter("btn_delete")!=null) {
			try {
				Long id = Long.parseLong(request.getParameter("id"));
				animalDAO.deleteAnimal(id);
				animals = animalDAO.readAllAnimals();
			}catch(Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("animals", animals);
			request.getRequestDispatcher("animal_list.jsp").forward(request, response);
		}else {
			animals = animalDAO.readAllAnimals();
			System.out.println(animals);
			request.setAttribute("animals", animals);
			request.getRequestDispatcher("animal_list.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
