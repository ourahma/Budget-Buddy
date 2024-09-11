package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Objectif;
import util.Utilitaire;

public class ObjectifDAOImpl implements ObjectifDao{
	public int ajouterObjectif(Objectif objectif) {
		int res=0;
		String query="INSERT INTO Objectif (id_utilisateur,nom_objectif,montant,date_limite,intialAmount) Values (?,?,?,?,?)";
		
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, objectif.getId_utilisateur());
			
			ps.setString(2, objectif.getNom_objectif());
			ps.setDouble(3, objectif.getMontant());
			ps.setInt(4, 0);
			ps.setDouble(5, 0);
	        java.util.Date utilDate = objectif.getDate_limite();
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	        ps.setDate(4, sqlDate);

		 
			res=ps.executeUpdate();
			
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+"ajouter objectif "+e.toString());
		}
		
		return res;
	}
	public List<Objectif> getObjectif(int id_utilisateur){
		List<Objectif> objectifs=new ArrayList<Objectif>();
		Objectif objectif=new Objectif();
		String query="SELECT * FROM Objectif WHERE id_utilisateur=?";
		
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, id_utilisateur);
			
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				objectif.setId_objectif(res.getInt("id_objectif"));
				objectif.setDate_limite(res.getDate("date_limite"));
				objectif.setId_utilisateur(res.getInt("id_utilisateur"));
				objectif.setMontant(res.getDouble("montant"));
				objectif.setNom_objectif(res.getString("nom_objectif"));
				objectif.setIntialAmout(res.getDouble("intialAmount"));
				objectif.setCompleted(res.getBoolean("completed"));
				objectifs.add(objectif);
				objectif =new Objectif();
			}
			
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+"ajouter objectif "+e.toString());
		}
		
		return objectifs;
		
	}


	public int deposittoObjectif(int id_utilisateur, int id_objectif, double montantdepot) {
		String query = "UPDATE objectif SET intialAmount =intialAmount+ ? WHERE id_objectif = ?";
	    int res = 0;
	    Connection connection = Utilitaire.getConnection();
	    try {
	        
	        
	        PreparedStatement checkPs = connection.prepareStatement(query);
	        checkPs.setDouble(1, montantdepot);
	        checkPs.setInt(2, id_objectif);
	        res = checkPs.executeUpdate();
	   
	        checkPs.close();
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Erreur " + getClass() + " DEPOSIT " + e.toString());
	    }
	    return res;
	}


	public int SupprimerObjectifUtilisateur(int id_objectif) {
		String query = "DELETE FROM objectif WHERE id_objectif = ?";
	    int res = 0;
	    Connection connection = Utilitaire.getConnection();
	    try {
	        
	        
	        PreparedStatement checkPs = connection.prepareStatement(query);
	        checkPs.setDouble(1, id_objectif);
	        
	        res = checkPs.executeUpdate();
	   
	        checkPs.close();
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Erreur " + getClass() + " SupprimerObjectifUtilisateur " + e.toString());
	    }
	    return res;
	}


	public int modifierObjectif(Objectif objectif) {
		int res=0;
		String query="Update  Objectif SET nom_objectif=?,montant=?,date_limite=? WHERE id_objectif=?";
		
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, objectif.getNom_objectif());
			
			ps.setDouble(2, objectif.getMontant());
			
	        java.util.Date utilDate = objectif.getDate_limite();
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	        ps.setDate(3, sqlDate);
	        ps.setInt(4, objectif.getId_objectif());
		 
			res=ps.executeUpdate();
			
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+" modifier objectif "+e.toString());
		}
		
		return res;
	}


	public int retraitObjectif(int id_objectif, double d) {
		int res=0;
		String query="Update  Objectif SET intialAmount=? WHERE id_objectif=?";
		
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setDouble(1, d);	
			ps.setInt(2, id_objectif);
			res=ps.executeUpdate();
			
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+" retrait "+e.toString());
		}
		
		return res;
	}
	
}
