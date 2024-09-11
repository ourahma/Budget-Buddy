package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modele.Budget;
import modele.Categorie;
import modele.Depense;
import modele.Devise;
import modele.Utilisateur;
import util.Utilitaire;;

public class UtilisateurActionsDao {
	public List<Categorie> getAllCategories() {
		String query="Select * FROM Categorie";
		Categorie categorie=new Categorie();
		List<Categorie> categories =new ArrayList<>();
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				categorie=new Categorie();
				categorie.setId_categorie(res.getInt("id_categorie"));
				categorie.setNom_categorie(res.getString("nom_categorie"));
				categories.add(categorie);
			}
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+"getAllCategorie "+e.toString());
		}
		
		return categories;
	}
	
	
	
	public List<Devise> getAllDevise() {
		String query="Select * FROM Devise";
		Devise devise=new Devise();
		List<Devise> devises =new ArrayList<>();
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				devise=new Devise();
				devise.setId_devise(res.getInt("id_devise"));
				devise.setAnnotation(res.getString("annotation"));
				devise.setPays(res.getString("pays"));
				devise.setSymbole(res.getString("symbole"));
				devises.add(devise);
			}
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+"getAllCategorie "+e.toString());
		}
		
		return devises;
	}
	
	

	public Devise getDeviseUtilisateur(int id_utilisateur,int id_devise) {
		Devise devise=new Devise();
		Connection connection = Utilitaire.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Devise,Utilisateur where utilisateur.id_devise=devise.id_devise and id_utilisateur=? ");
	       
	        ps.setInt(1, id_utilisateur);
	        ResultSet res = ps.executeQuery();
	        while(res.next()) {
	           devise.setAnnotation(res.getString("annotation"));
	           devise.setPays(res.getString("pays"));
	           devise.setSymbole(res.getString("symbole"));
	           devise.setId_devise(res.getInt("id_devise"));
	        }
	        ps.close();
	        connection.close();
	        
	        
	    } catch (SQLException e) {
	        System.out.println("Erreur "+getClass()+" getDepenseUtilisateur "+e.toString());
	    }
	    
	    return devise;
	}

}
