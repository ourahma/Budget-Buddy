package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modele.Budget;
import modele.Categorie;
import modele.Depense;
import modele.Devise;
import modele.Utilisateur;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import dao.BudgetDAOImpl;
import dao.DepenseDAOImpl;
import dao.UtilisateurActionsDao;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurActionsDao daouser=new UtilisateurActionsDao();
	BudgetDAOImpl budgetdao=new BudgetDAOImpl();
	DepenseDAOImpl depensedao=new DepenseDAOImpl();
    public HomeServlet() {
        super();
      
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();
    	if(session.getAttribute("user")==null) {
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    	}else {
    		List<Categorie> categories=daouser.getAllCategories();
    		List<Devise> devises=daouser.getAllDevise();
    		Utilisateur user=(Utilisateur) session.getAttribute("user");
    		List<Budget> budgets=budgetdao.getBudgetUtilisateur(user.getId_utilisateur());
    		List<Depense> depenses=depensedao.getDepenseUtilisateur(user.getId_utilisateur());
    		Devise deviseuser=daouser.getDeviseUtilisateur(user.getId_utilisateur(),user.getId_devise());
    		request.setAttribute("budgets", budgets);
    		request.setAttribute("categories", categories);
    		request.setAttribute("deviseuser", deviseuser);
    		request.setAttribute("depenses", depenses);
    		request.setAttribute("devises", devises);
    		request.setAttribute("page", "home");
    		request.getRequestDispatcher("home.jsp").forward(request, response);
    	}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
