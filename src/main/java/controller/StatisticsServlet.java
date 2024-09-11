package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import modele.Depense;
import modele.Utilisateur;

import dao.BudgetDAOImpl;
import dao.DepenseDAOImpl;

import java.io.IOException;
import java.util.List;

public class StatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BudgetDAOImpl budgetDAO = new BudgetDAOImpl();
	DepenseDAOImpl depensedao=new DepenseDAOImpl();
    public StatisticsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		if(session.getAttribute("user")==null) {
			response.sendRedirect("HomeServlet");		}
		else {
			Utilisateur user=(Utilisateur) session.getAttribute("user");
			
			 List<Depense> expenses = depensedao.getAllExpenses(user.getId_utilisateur());
			    
			    // Set the expense data as an attribute in the request object
			 		request.setAttribute("expenses", expenses);
			    
			    // Forward the request to the JSP page
			    request.getRequestDispatcher("statistic.jsp").forward(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
