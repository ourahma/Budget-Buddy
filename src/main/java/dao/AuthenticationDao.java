package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import modele.Utilisateur;
import util.Utilitaire;

public class AuthenticationDao {

	public Utilisateur login(String email,String password) {
		Utilisateur user=new Utilisateur();
		Connection connection=Utilitaire.getConnection();
		String query="SELECT * FROM utilisateur WHERE email=? and mdps=?";
		try {
			PreparedStatement st=connection.prepareStatement(query);
			st.setString(1, email);
			st.setString(2, password);
			ResultSet result=st.executeQuery();
			while(result.next()) {
				user.setId_utilisateur(result.getInt("id_utilisateur"));
				user.setEmail(result.getString("email"));
				user.setPrenom(result.getString("prenom"));
				user.setNom(result.getString("nom"));
				user.setId_devise(result.getInt("id_devise"));
				user.setMdps(result.getString("mdps"));
				
			}
			st.close();
			connection.close();
			return user;
			
		} catch (SQLException e) {
			System.out.println("Erreur dans AuthenticationDao methode login "+e.toString());
			return null;
		}
	}
	public Utilisateur Register(Utilisateur user) {
		
		Connection connection=Utilitaire.getConnection();
		String query="INSERT INTO utilisateur (nom,prenom,email,mdps) VALUES(?,?,?,?)";
		try {
			PreparedStatement st=connection.prepareStatement(query);
			st.setString(1,user.getNom() );
			st.setString(2, user.getPrenom());
			st.setString(3, user.getEmail());
			st.setString(4, user.getMdps());
			int result=st.executeUpdate();
			query="SELECT MAX(id_utilisateur) AS dernier_id FROM Utilisateur";
			st=connection.prepareStatement(query);
			//executer la nouvelle requete
			ResultSet r=st.executeQuery();
			
			if(r.next()) {
				//modifier la valeur d'ibjet
				int id=r.getInt("dernier_id");
				user.setId_utilisateur(id);
			}
			
		
			
			st.close();
			connection.close();
			return user;
			
		} catch (SQLException e) {
			System.out.println("Erreur dans AuthenticationDao methode register "+e.toString());
			return null;
		}
	}
	public int chagerDevise(int devise,int id_utilisateur) {
		String query = "Update Utilisateur set id_devise= ? where id_utilisateur=?";
	    int res = 0;
	    Connection connection = Utilitaire.getConnection();
	    try {
	        
	        
	        PreparedStatement checkPs = connection.prepareStatement(query);
	        checkPs.setInt(1, devise);
	        checkPs.setInt(2, id_utilisateur);
	        
	        res = checkPs.executeUpdate();
	   
	        checkPs.close();
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Erreur " + getClass() + " changer devise utilisareur" + e.toString());
	    }
	    return res;
	}
	public int UpdateProfile(Utilisateur user) {
		String query = "Update Utilisateur set nom= ? ,prenom=? , mdps=? where id_utilisateur=?";
	    int res = 0;
	    Connection connection = Utilitaire.getConnection();
	    try {
	        
	        
	        PreparedStatement checkPs = connection.prepareStatement(query);
	        checkPs.setString(1, user.getNom());
	        checkPs.setString(2, user.getPrenom());
	        checkPs.setString(3, user.getMdps());
	        checkPs.setInt(4, user.getId_utilisateur());
	        
	        res = checkPs.executeUpdate();
	   
	        checkPs.close();
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Erreur " + getClass() + " changer profile utilisareur" + e.toString());
	    }
	    return res;
	}
}
