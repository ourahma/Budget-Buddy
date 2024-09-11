package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modele.Depense;

import java.io.IOException;
import java.util.List;

import dao.DepenseDAO;
import dao.DepenseDAOImpl;

public class HistoriqueDepensesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public HistoriqueDepensesServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Depense> depenses = DepenseDAOImpl.findAll();
        request.setAttribute("depenses", depenses);
        request.getRequestDispatcher("historique.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
