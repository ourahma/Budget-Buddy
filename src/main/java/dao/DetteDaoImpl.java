package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Dette;
import modele.Objectif;
import util.Utilitaire;

public class DetteDaoImpl implements DetteDAO {

	public int ajouterDette(Dette dette) {
		int res=0;
		String query="INSERT INTO Dette (id_utilisateur,nom_dette,description,montant,date,initialAmount) Values (?,?,?,?,?,?)";
		
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, dette.getId_utilisateur());
			
			ps.setString(2, dette.getNom_dette());
			ps.setString(3, dette.getDescription());
			ps.setDouble(4, dette.getMontant());
			ps.setDouble(6, 0);
	        java.util.Date utilDate = dette.getDate();
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	        ps.setDate(5, sqlDate);

		 
			res=ps.executeUpdate();
			
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur "+getClass()+" ajouter dette "+e.toString());
		}
		
		return res;
	}
	public int SupprimerDetteUtilisateur(int id) {
		String query = "DELETE FROM Dette WHERE id_dette= ?";
	    int res = 0;
	    Connection connection = Utilitaire.getConnection();
	    try {
	        
	        
	        PreparedStatement checkPs = connection.prepareStatement(query);
	        checkPs.setDouble(1, id);
	        
	        res = checkPs.executeUpdate();
	   
	        checkPs.close();
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Erreur " + getClass() + " SupprimerdetteUtilisateur " + e.toString());
	    }
	    return res;
	}
	public int modifierDette(Dette dette) {
		int res=0;
		String query="Update  Dette SET nom_dette=?,montant=?,description=?,date=? WHERE id_dette=?";
		
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, dette.getNom_dette());
			
			ps.setDouble(2, dette.getMontant());
			
	        java.util.Date utilDate = dette.getDate();
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	        ps.setDate(4, sqlDate);
	        ps.setString(3, dette.getDescription());
	        ps.setInt(5, dette.getId_dette());
		 
			res=ps.executeUpdate();
			
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+" modifier dette "+e.toString());
		}
		
		return res;
	}
	public int ajouterMontantDette(int id_utilisateur, int id_dette, double montantdette) {
		String query = "UPDATE Dette SET initialAmount =initialAmount+ ? WHERE id_dette = ?";
	    int res = 0;
	    Connection connection = Utilitaire.getConnection();
	    try {
	        
	        
	        PreparedStatement checkPs = connection.prepareStatement(query);
	        checkPs.setDouble(1, montantdette);
	        checkPs.setInt(2, id_dette);
	        res = checkPs.executeUpdate();
	   
	        checkPs.close();
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Erreur " + getClass() + " ajouterMontantDette " + e.toString());
	    }
	    return res;
	}
	public List<Dette> getDetteUtilisateur(int id_utilisateur) {
		List<Dette> dettes=new ArrayList<Dette>();
		Dette dette=new Dette();
		String query="SELECT * FROM Dette WHERE id_utilisateur=?";
		
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, id_utilisateur);
			
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				dette.setDate(res.getDate("date"));
				dette.setDescription(res.getString("description"));
				dette.setId_dette(res.getInt("id_dette"));
				dette.setId_utilisateur(res.getInt("id_utilisateur"));
				dette.setIntialAmount(res.getDouble("initialAmount"));
				dette.setMontant(res.getDouble("montant"));
				dette.setNom_dette(res.getString("nom_dette"));
				dettes.add(dette);
				dette=new Dette();
			}
			
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+"get dettes utilisateur "+e.toString());
		}
		
		return dettes;
	}

}


