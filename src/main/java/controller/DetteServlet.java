package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modele.Dette;
import modele.Utilisateur;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import dao.DetteDaoImpl;

public class DetteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DetteDaoImpl dao=new DetteDaoImpl();
    
    public DetteServlet() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/Portefeuille");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		HttpSession session=request.getSession();
		Utilisateur user=(Utilisateur) session.getAttribute("user");
		if(session.getAttribute("user")==null) {
			response.sendRedirect("/HomeServlet");
		}else {
			if(path.equals("/AddDetteServlet")) {
				/*Map<String, String> parameters = new HashMap<>();
	            Enumeration<String> parameterNames = request.getParameterNames();
	            while (parameterNames.hasMoreElements()) {
	                String paramName = parameterNames.nextElement();
	                String paramValue = request.getParameter(paramName);
	                parameters.put(paramName, paramValue);
	                System.out.println("Parameter Name: " + paramName + ", Value: " + paramValue);
	            }*/
	            Dette dette=new Dette();
	            dette.setId_utilisateur(user.getId_utilisateur());
	            dette.setNom_dette(request.getParameter("nom_dette"));
	            dette.setDescription(request.getParameter("description"));
	            dette.setId_utilisateur(user.getId_utilisateur());
	            dette.setMontant(Double.parseDouble(request.getParameter("montant")));
	            String dateLimiteStr = request.getParameter("date_limite");
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            try {
					Date date_limite = sdf.parse(dateLimiteStr);
					dette.setDate(date_limite);
					
					
				} catch (ParseException e) {
					session.setAttribute("error", e.toString());
				}
	            int res=dao.ajouterDette(dette);
	            if(res!=0) {
                	session.setAttribute("success", "Dette ajouté avec succés ");
                	response.sendRedirect("Portefeuille");
                }else {
                	session.setAttribute("error", "Erreur lors d'ajout de dette ");
                	response.sendRedirect("Portefeuille");
                }
			}else if(path.equals("/ModifierDetteServlet")) {
				/*Map<String, String> parameters = new HashMap<>();
	            Enumeration<String> parameterNames = request.getParameterNames();
	            while (parameterNames.hasMoreElements()) {
	                String paramName = parameterNames.nextElement();
	                String paramValue = request.getParameter(paramName);
	                parameters.put(paramName, paramValue);
	                System.out.println("Parameter Name: " + paramName + ", Value: " + paramValue);
	            }*/
	            Dette dette=new Dette();
	            dette.setId_dette(Integer.parseInt(request.getParameter("id_dette")));
	            dette.setNom_dette(request.getParameter("nom_dette"));
	            dette.setDescription(request.getParameter("description"));
	            dette.setMontant(Double.parseDouble(request.getParameter("montant")));
	            dette.setIntialAmount(Double.parseDouble(request.getParameter("modify_initialAmount")));
	            if(dette.getIntialAmount()>dette.getMontant()) {
	            	session.setAttribute("error", "Le montant de dette est inférieur à celui que vous avez ajouté.  ");
                	response.sendRedirect("Portefeuille");
	            }else {
	            	String dateLimiteStr = request.getParameter("date_limite");
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		            try {
						Date date_limite = sdf.parse(dateLimiteStr);
						dette.setDate(date_limite);
						
						
					} catch (ParseException e) {
						session.setAttribute("error", e.toString());
					}
					int res=dao.modifierDette(dette);
					 if(res!=0) {
		                	session.setAttribute("success", "Dette modifié avec succés ");
		                	response.sendRedirect("Portefeuille");
		                }else {
		                	session.setAttribute("error", "Erreur lors de modification de dette ");
		                	response.sendRedirect("Portefeuille");
		                }
	            }
	            
			}else if(path.equals("/SupprimerDetteServlet")) {
				int id_dette=Integer.parseInt(request.getParameter("id_dette"));
				int res=dao.SupprimerDetteUtilisateur(id_dette);
				 if(res!=0) {
	                	session.setAttribute("success", "Dette supprimé avec succés ");
	                	response.sendRedirect("Portefeuille");
	                }else {
	                	session.setAttribute("error", "Erreur lors de supression de dette ");
	                	response.sendRedirect("Portefeuille");
	                }
				
			}else if(path.equals("/AjouerMontant")) {
				
	            int id_dette=Integer.parseInt(request.getParameter("id_dette"));
	            double montant=Double.parseDouble(request.getParameter("montant"));
	            int res=dao.ajouterMontantDette(user.getId_utilisateur(), id_dette, montant);
	            if(res!=0) {
                	session.setAttribute("success", "Montant ajouté avec succés ");
                	response.sendRedirect("Portefeuille");
                }else {
                	session.setAttribute("error", "Erreur lors d'ajout de montant dette ");
                	response.sendRedirect("Portefeuille");
                }
			}
		}
		
	}

}
