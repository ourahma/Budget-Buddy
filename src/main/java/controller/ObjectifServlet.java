package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modele.Objectif;
import modele.Utilisateur;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import dao.BudgetDAOImpl;
import dao.ObjectifDAOImpl;

public class ObjectifServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ObjectifDAOImpl dao=new ObjectifDAOImpl();
    public ObjectifServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String path = request.getServletPath();
	    HttpSession session = request.getSession();
	    Utilisateur user = (Utilisateur) session.getAttribute("user");
	    if (path.equals("/AjouterObjectif")) {
	        
	        if (is_authenticated(request)) {
	            
	           /* Map<String, String> parameters = new HashMap<>();
	            Enumeration<String> parameterNames = request.getParameterNames();
	            while (parameterNames.hasMoreElements()) {
	                String paramName = parameterNames.nextElement();
	                String paramValue = request.getParameter(paramName);
	                parameters.put(paramName, paramValue);
	                System.out.println("Parameter Name: " + paramName + ", Value: " + paramValue);
	            }*/
	            
	            String nom_objectif = request.getParameter("nom_objectif");
	            double montant = Double.parseDouble(request.getParameter("montant"));
	            String dateLimiteStr = request.getParameter("date_limite");
	            
	            int id_utilisateur = user.getId_utilisateur();
	            
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            try {
	                Date date_limite = sdf.parse(dateLimiteStr);
	               
	                Date today = new Date();
	                if (date_limite.before(today)) {
	                    request.setAttribute("error", "Vous devez saisir une date après la date d'aujoud'hui! ");
	                    response.sendRedirect("Portefeuille");
	                    return;
	                }
	                Objectif objectif=new Objectif();
	                objectif.setDate_limite(date_limite);
	                objectif.setId_utilisateur(id_utilisateur);
	                objectif.setMontant(montant);
	                objectif.setNom_objectif(nom_objectif);
	                
	                int res=dao.ajouterObjectif(objectif);
	                if(res!=0) {
	                	session.setAttribute("success", "Objectif ajouté avec succés ");
	                	response.sendRedirect("Portefeuille");
	                }else {
	                	session.setAttribute("error", "Erreur lors de l'ajour d'objectif ");
	                	response.sendRedirect("Portefeuille");
	                }
	                
	            } catch (ParseException e) {
	            	request.setAttribute("error", "Une erreur lors de la conversion de la date ");
	                e.printStackTrace();
	            }
	        }
	    }else if(path.equals("/AjouterDepot")) {
	    	
	    	int id_objectif=Integer.parseInt(request.getParameter("id_objectif"));
	    	double montantdepot=Double.parseDouble(request.getParameter("montant_depot"));
	    	double objectifmontant=Double.parseDouble(request.getParameter("objectif_montant"));
	    	if(objectifmontant<montantdepot) {
	    		session.setAttribute("error", "Saisissez un montant de dépôt inférieure à celui d'objectif ");
            	response.sendRedirect("Portefeuille");
	    	}else {
	    		int res=dao.deposittoObjectif(user.getId_utilisateur(),id_objectif,montantdepot);
		    	if(res!=0) {
		    		session.setAttribute("success", "Dépôt ajouté avec succés ");
	            	response.sendRedirect("Portefeuille");
		    	}else {
		    		session.setAttribute("error", "Erreur lors de l'ajour de dépôt ");
	            	response.sendRedirect("Portefeuille");
		    	}
	    	}
	    	
	    }else if (path.equals("/SupprimerObjectif")) {
	    	int id_objectif=Integer.parseInt(request.getParameter("id_objectif"));
	    	int res=dao.SupprimerObjectifUtilisateur(id_objectif);
		    	if(res!=0) {
		    		session.setAttribute("success", "Objectif suppprimé avec succés ");
	            	response.sendRedirect("Portefeuille");
		    	}else {
		    		session.setAttribute("error", "Erreur lors de la suppression d'Objectif ");
	            	response.sendRedirect("Portefeuille");
		    	}
	    }else if(path.equals("/ModifierObjectif")) {
	    	/*Map<String, String> parameters = new HashMap<>();
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = request.getParameter(paramName);
                parameters.put(paramName, paramValue);
                System.out.println("Parameter Name: " + paramName + ", Value: " + paramValue);
            }*/
            String nom_objectif=request.getParameter("nom_objectif");
            double montant =Double.parseDouble(request.getParameter("montant"));
            int id_objectif=Integer.parseInt(request.getParameter("id_objectif"));
            String dateLimiteStr = request.getParameter("date_limite");
            double initialAmount = Double.parseDouble(request.getParameter("initial_amount"));

            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date_limite = sdf.parse(dateLimiteStr);
               
                Date today = new Date();
                if (date_limite.before(today)) {
                    session.setAttribute("error", "Vous devez saisir une date après la date d'aujoud'hui! ");
                    response.sendRedirect("Portefeuille");
                    return;
                }
                if (montant < initialAmount) { 
                    session.setAttribute("error", "Le nouveau montant est inférieur au montant de dépôt "+initialAmount+". Veuillez d'abord effectuer un retrait.");
                    response.sendRedirect("Portefeuille");
                    return;
                }
                Objectif objectif=new Objectif();
                objectif.setId_objectif(id_objectif);
                objectif.setDate_limite(date_limite);
                objectif.setMontant(montant);
                objectif.setNom_objectif(nom_objectif);
                
                int res=dao.modifierObjectif(objectif);
                if(res!=0) {
                	session.setAttribute("success", "Objectif modifié avec succés ");
                	response.sendRedirect("Portefeuille");
                }else {
                	session.setAttribute("error", "Erreur lors de modification d'objectif ");
                	response.sendRedirect("Portefeuille");
                }
	    }catch (Exception e) {
	    	session.setAttribute("error", e.toString());
        	response.sendRedirect("Portefeuille");
		}}else if(path.equals("/AjouterRetrait")) {
			/*Map<String, String> parameters = new HashMap<>();
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = request.getParameter(paramName);
                parameters.put(paramName, paramValue);
                System.out.println("Parameter Name: " + paramName + ", Value: " + paramValue);
            }*/
            int id_objectif=Integer.parseInt(request.getParameter("id_objectif"));
            double montant_retrait=Double.parseDouble(request.getParameter("montant_retrait"));
            double montant_total=Double.parseDouble(request.getParameter("montant_total"));
            double montant_initial=Double.parseDouble(request.getParameter("montant_initial"));
            if(montant_retrait>montant_total) {
            	session.setAttribute("error", "Vous ne pouvez pas retraiter un montant supérieur à celui d'objectif ");
            	response.sendRedirect("Portefeuille");
            }else if(montant_retrait>montant_initial) {
            	session.setAttribute("error", "Vous ne pouvez pas retraiter un montant supérieur "+montant_initial);
            	response.sendRedirect("Portefeuille");
            }else {
            	int res=dao.retraitObjectif(id_objectif,montant_initial-montant_retrait);
            	 if(res!=0) {
                 	session.setAttribute("success", "retrai fait avec succés ");
                 	response.sendRedirect("Portefeuille");
                 }else {
                 	session.setAttribute("error", "Erreur lors de Retrait");
                 	response.sendRedirect("Portefeuille");
                 }
            }
		}
	    else {
	    	request.getRequestDispatcher("home.jsp").forward(request, response);
	    }
	}
	public boolean is_authenticated(HttpServletRequest request) {
		HttpSession session=request.getSession();
		if(session.getAttribute("user")!=null) {
			return true;
		}else {
			return false;
		}
	}

}
