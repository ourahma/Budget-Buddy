package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modele.Budget;
import modele.Depense;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import dao.UtilisateurActionsDao;
import dao.BudgetDAOImpl;
import dao.DepenseDAOImpl;

public class UtilisateurActionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurActionsDao daouser=new UtilisateurActionsDao();
	DepenseDAOImpl daoudepense=new DepenseDAOImpl();
	BudgetDAOImpl budgetdao=new BudgetDAOImpl();
    public UtilisateurActionsServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		HttpSession session = request.getSession();
		if(path.equals("/SupprimerBudget")) {
	        int id_budget=Integer.parseInt(request.getParameter("id_budget"));
	        int res=budgetdao.SupprimerBudget(id_budget);
	        if(res!=0) {
				session.setAttribute("success", "Budget suprrimé avec succés");
				
			}else {
				session.setAttribute("error", "Error de supression");
				
			}
			response.sendRedirect("HomeServlet");
		
		}else {
			response.sendRedirect("HomeServlet");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		HttpSession session = request.getSession();
		if(path.equals("/AjouterBudget")) {
			String nomBudget=request.getParameter("nom_budget");
			double montant=Double.parseDouble(request.getParameter("montant"));
			String periode=request.getParameter("periode");
			int id_categorie=Integer.parseInt(request.getParameter("id_categorie"));
			int id_utilisateur=Integer.parseInt(request.getParameter("id_utilisateur"));
			int id_devise=Integer.parseInt(request.getParameter("id_devise"));
			int res=budgetdao.Addbudget(id_categorie, id_utilisateur, nomBudget, montant, periode,id_devise);
			
			if(res!=0) {
				session.setAttribute("success", "Vous avez ajouter un budget avec succés");
				
			}else {
				session.setAttribute("error", "Vous avez ajouter un budget avec succés");
				
			}
			response.sendRedirect("HomeServlet");
		}else if(path.equals("/AjouterDepenseServlet")) {
			
			int id_budget=Integer.parseInt(request.getParameter("id_budget"));
			double montant=Double.parseDouble(request.getParameter("montant"));
			String repition=request.getParameter("repetition");
			int id_categorie=Integer.parseInt(request.getParameter("id_categorie"));
			int id_utilisateur=Integer.parseInt(request.getParameter("id_utilisateur"));
			int resu=daoudepense.ajouterDepense(id_utilisateur,id_categorie,montant,repition,id_budget);
			
			if(resu!=0) {
				session.setAttribute("success","Votre Dépense est ajoutée avec succés");
				
			}else {
				session.setAttribute("error","Erreur lors d'ajout de dépense");
				
			}
			response.sendRedirect("HomeServlet");
		}else if (path.equals("/SupprimerDepense")) {
			
			int id_depense=Integer.parseInt(request.getParameter("id_depense"));
			int id_utilisateur=Integer.parseInt(request.getParameter("id_utilisateur"));
			int res=daoudepense.SupprimerDepenseUtilisateur(id_depense,id_utilisateur);
			
			System.out.println(res);
			if(res==1) {
				session.setAttribute("success","Votre Dépense est suprimée avec succés");
				
			}else {
				session.setAttribute("error","Erreur lors d'ajout de dépense");
			}
			response.sendRedirect("HomeServlet");
		}else if(path.equals("/ModifierBudget")) {
			 
			 int id_budget=Integer.parseInt(request.getParameter("id_budget"));
			 int id_devise=Integer.parseInt(request.getParameter("id_devise"));
			 int id_categorie=Integer.parseInt(request.getParameter("id_categorie"));
			 int id_utilisateur=Integer.parseInt(request.getParameter("id_utilisateur"));
			 String nom_budget=request.getParameter("nom_budget");
			 double montant=Double.parseDouble(request.getParameter("montant"));
			 String periode=request.getParameter("periode");

			 Budget budget=new Budget(id_budget, id_utilisateur, id_categorie, nom_budget, montant, periode, LocalDate.now());
			 
			 int res=budgetdao.modifierBudget(budget);
			 if(res==1) {
				 session.setAttribute("success", "Budget Modifier avec succés");
			 }else {
				 session.setAttribute("error", "Erreur lors de  Modification de Budget");
			 }
			 response.sendRedirect("HomeServlet");
		}else if(path.equals("/ModifierDepense")) {
				Map<String, String> parameters = new HashMap<>();
		        Enumeration<String> parameterNames = request.getParameterNames();
		        while (parameterNames.hasMoreElements()) {
		             String paramName = parameterNames.nextElement();
		             String paramValue = request.getParameter(paramName);
		             parameters.put(paramName, paramValue);
		             System.out.println("Parameter Name: " + paramName + ", Value: " + paramValue);
		        }
		        Depense depense=new Depense();
		        depense.setId_categorie(Integer.parseInt(request.getParameter("id_categorie")));
		        depense.setId_budget(Integer.parseInt(request.getParameter("budgetId")));
		        depense.setId_depense(Integer.parseInt(request.getParameter("id_depense")));
		        depense.setMontant(Double.parseDouble(request.getParameter("montant")));
		        depense.setRepetition(request.getParameter("periode"));
		        int res=daoudepense.modifierDepense(depense);
				 if(res==1) {
					 session.setAttribute("success", "Depense Modifier avec succés");
				 }else {
					 session.setAttribute("error", "Erreur lors de  Modification de depense");
				 }
				 response.sendRedirect("HomeServlet");
		}
	}

}
