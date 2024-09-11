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
import modele.Dette;
import modele.Devise;
import modele.Objectif;
import modele.Utilisateur;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.BudgetDAOImpl;
import dao.DepenseDAOImpl;
import dao.DetteDaoImpl;
import dao.ObjectifDAOImpl;
import dao.UtilisateurActionsDao;

public class Portefeuille extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BudgetDAOImpl budgetDAO = new BudgetDAOImpl();
	DetteDaoImpl dettedao=new DetteDaoImpl();
	UtilisateurActionsDao daouser=new UtilisateurActionsDao();
	ObjectifDAOImpl dao=new ObjectifDAOImpl();
	DepenseDAOImpl depensedao=new DepenseDAOImpl();
    public Portefeuille() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
    	if(session.getAttribute("user")==null) {
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    	}else {
    		
    		Utilisateur user=(Utilisateur) session.getAttribute("user");
    		
    		List<Budget> budgetData = budgetDAO.getAllBudgetData(user.getId_utilisateur());
    		List<Categorie> categories=daouser.getAllCategories();
    		List<Devise> devises=daouser.getAllDevise();
    		
    		List<Budget> budgets=budgetDAO.getBudgetUtilisateur(user.getId_utilisateur());
    		for (Budget budget : budgets) {
				System.out.println(budget);
				System.out.println(user);
    		}
    		List<Depense> depenses=depensedao.getDepenseUtilisateur(user.getId_utilisateur());
    		Devise deviseuser=daouser.getDeviseUtilisateur(user.getId_utilisateur(),user.getId_devise());
    		List<Objectif> objectifs=dao.getObjectif(user.getId_utilisateur());
    		List<Dette> dettes=dettedao.getDetteUtilisateur(user.getId_utilisateur());
    	
    		// Current date for comparison
            Date today = new Date();
            for (Dette dette : dettes) {
				dette.setCompleted(dette.getMontant()==dette.getIntialAmount()||dette.getDate().before(today));
			}
            for (Objectif objectif : objectifs) {
                objectif.setCompleted(objectif.getMontant() == objectif.getIntialAmout()||objectif.getDate_limite().before(today));
                
            }
            request.setAttribute("deviseuser", deviseuser);
    		request.setAttribute("page", "wallet");
    		request.setAttribute("budgets", budgets);
    		request.setAttribute("categories", categories);
    		request.setAttribute("depenses", depenses);
    		request.setAttribute("devises", devises);
    		request.setAttribute("objectifs", objectifs);
            request.setAttribute("budgetData", budgetData);
            request.setAttribute("dettes", dettes);
    		request.getRequestDispatcher("wallet.jsp").forward(request, response);
    	}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
