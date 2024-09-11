package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modele.Budget;
import modele.Categorie;
import modele.Devise;
import modele.Utilisateur;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.AuthenticationDao;
import dao.UtilisateurActionsDao;



public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AuthenticationDao dao=new AuthenticationDao();
	UtilisateurActionsDao daouser=new UtilisateurActionsDao();
    public AuthenticationServlet() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String path = request.getServletPath();
		if(session.getAttribute("user")==null){
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else if(path.equals("/AuthenticationServletRegister")) {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}else if (path.equals("/AuthenticationServlet")||path.equals("/AuthenticationServletLogin")){
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if(path.equals("/Logout")) {
			session.setAttribute("success", "Déconnection avec succées");
			session.invalidate();
			response.sendRedirect("HomeServlet");
		}else if(path.equals("/UpdateProfile")) {
			Utilisateur user=(Utilisateur) session.getAttribute("user");
			
			List<Devise> devises=daouser.getAllDevise();
			Devise deviseuser=daouser.getDeviseUtilisateur(user.getId_utilisateur(),user.getId_devise());
			request.setAttribute("deviseuser", deviseuser);
			request.setAttribute("devises", devises);
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		
			
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		String path = request.getServletPath();
		if(path.equals("/AuthenticationServletRegister")){
			String nom = request.getParameter("nom");
	        String prenom = request.getParameter("prenom");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        String cpassword = request.getParameter("cpassword");

	        
	        if (!password.equals(cpassword)) {
	            request.setAttribute("error", "Les mots de passe ne correspondent pas.");
	            request.getRequestDispatcher("register.jsp").forward(request, response);
	            return;
	        }

	        
	        Utilisateur user = new Utilisateur();
	        user.setNom(nom);
	        user.setPrenom(prenom);
	        user.setEmail(email);
	        user.setMdps(password);

	        
	        Utilisateur userRegistered = dao.Register(user);

	        if (userRegistered!=null) {
	        	
	            session.setAttribute("user", user);
	            response.sendRedirect("HomeServlet");
	        } else {
	            
	            request.setAttribute("errorMessage", "Échec de l'inscription. Veuillez réessayer.");
	            request.getRequestDispatcher("register.jsp").forward(request, response);
	        }
		}else if(path.equals("/AuthenticationServletLogin") ){
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			Utilisateur user=dao.login(email, password);
			if(user.getNom()!=null && user.getPrenom()!=null) {
				session.setAttribute("success", "Vous avez fait votre authentification avec succées");
				
				session.setAttribute("user", user);
				
				response.sendRedirect("HomeServlet");
			}else {
				session.setAttribute("error", "Votre mot de passe ou votre email est incorrect");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else if(path.equals("/UpdateProfile")){
			
            Utilisateur user=(Utilisateur) session.getAttribute("user");
            user.setNom(request.getParameter("nom"));
            user.setPrenom(request.getParameter("prenom"));
            user.setMdps(request.getParameter("mdps"));
            int res=dao.UpdateProfile(user);
            if(res!=0) {
            	session.setAttribute("success", "Votre profille est mis à jour avec succés ");
            	response.sendRedirect("UpdateProfile");
            }else {
            	session.setAttribute("error", "Erreur de modification de profile ");
            	response.sendRedirect("UpdateProfile");
            }
		}else if(path.equals("/ChangeDevise")) {
		
			int devise=Integer.parseInt(request.getParameter("devise"));
			
			Utilisateur user=(Utilisateur) session.getAttribute("user");
			user.setId_devise(devise);
			int res=dao.chagerDevise(devise,user.getId_utilisateur());
            if(res!=0) {
            	session.setAttribute("success", "Devise changé avec succé ");
            	response.sendRedirect("UpdateProfile");
            }else {
            	session.setAttribute("error", "Erreur lors de changement de devise ");
            	response.sendRedirect("UpdateProfile");
            }
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
