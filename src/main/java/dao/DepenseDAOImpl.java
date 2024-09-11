package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modele.Depense;
import util.Utilitaire;

public class DepenseDAOImpl implements DepenseDAO{

	

	public static List<Depense> findAll() {
		List<Depense> depenses=new ArrayList<Depense>();
		Depense depense =new Depense();
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement("SELECT * FROM Depense ");
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				depense =new Depense();
				depense.setId_depense(res.getInt("id_depense"));
				depense.setId_budget(res.getInt("id_budget"));
				depense.setId_categorie(res.getInt("id_categorie"));
				depense.setId_utilisateur(res.getInt("id_utilisateur"));
				depense.setMontant(res.getDouble("montant"));
				depense.setRepetition(res.getString("repetition"));
				depenses.add(depense);
			}
			connection.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Erreur dans la classe DepenseDaoImp dans la methode findById" +e.toString());
		}
		return depenses;
	}

	@Override
	public int save(Depense depense) {
		int res=0;
		Connection connection=Utilitaire.getConnection();
	try {
		PreparedStatement ps=connection.prepareStatement("INSERT INTO Depense (id_categorie,id_utilisateur,id_budget,montant,repetion,date_depanse) VALUES (?,?,?,?,?,?)");
		ps.setInt(1, depense.getId_categorie());
		ps.setInt(2, depense.getId_utilisateur());
		ps.setInt(3,depense.getId_budget());
		ps.setDouble(4, depense.getMontant());
		ps.setString(5, depense.getRepetition());
		ps.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
		res=ps.executeUpdate();
		
		connection.close();
		ps.close();
	} catch (SQLException e) {
		System.out.println("Erreur dans la classe DepenseDaoImp dans la methode save" +e.toString());
	}
	return res;
	}
	public int ajouterDepense(int id_utilisateur,int id_categorie,double montant,String repition,int id_budget) {
		int res=0;
		String query="INSERT INTO Depense(id_categorie,id_utilisateur,id_budget,montant,repetition,date_depense) Values (?,?,?,?,?,?)";
		
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(2, id_utilisateur);
			ps.setInt(1, id_categorie);
			ps.setInt(3, id_budget);
			ps.setDouble(4, montant);
			ps.setString(5, repition);
			ps.setDate(6, java.sql.Date.valueOf(LocalDate.now())); 
			res=ps.executeUpdate();
			
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+"ajouter depense "+e.toString());
		}
		
		return res;
	}


	public int SupprimerDepenseUtilisateur(int id_depense,int id_utilisateur) {
		int res=0;
		String query="DELETE FROM Depense where id_utilisateur=? and id_depense=?";
		
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, id_utilisateur);
			ps.setInt(2, id_depense);
			
			
			res=ps.executeUpdate();
			
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+"supprimer depense "+e.toString());
		}
		
		return res;
	}

	public List<Depense> getDepenseUtilisateur(int id_utilisateur){
	    List<Depense> depenses = new ArrayList<Depense>();
	    
	    Connection connection = Utilitaire.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("SELECT d.*, c.nom_categorie, d.repetition FROM Depense d, Categorie c WHERE d.id_categorie = c.id_categorie AND d.id_utilisateur=?");
	        ps.setInt(1, id_utilisateur);
	        ResultSet res = ps.executeQuery();
	        while(res.next()) {
	            Depense depense = new Depense();
	            depense.setId_depense(res.getInt("id_depense"));
	            depense.setId_budget(res.getInt("id_budget"));
	            depense.setId_categorie(res.getInt("id_categorie"));
	            depense.setId_utilisateur(res.getInt("id_utilisateur"));
	            depense.setMontant(res.getDouble("montant"));
	            Date sqlDate = res.getDate("date_depense");
	            if (sqlDate != null) {
	                java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
	                Instant instant = utilDate.toInstant();
	                ZoneId zoneId = ZoneId.systemDefault();
	                LocalDate localDate = instant.atZone(zoneId).toLocalDate();
	                depense.setDateDepense(localDate);
	            } else {
	                // Handle the case when date is null
	                // For example, set a default date or throw an exception
	            }
	            depense.setNom_categorie(res.getString("nom_categorie")); 
	            
	            depense.setRepetition(res.getString("repetition"));
	            depenses.add(depense);
	        }
	        ps.close();
	        connection.close();
	        
	        
	    } catch (SQLException e) {
	        System.out.println("Erreur "+getClass()+" getDepenseUtilisateur "+e.toString());
	    }
	    
	    return depenses;
	}
	
	
	public List<Depense> getAllExpenses(int id_utilisateur) {
	    List<Depense> depenses = new ArrayList<Depense>();
	    Connection connection = Utilitaire.getConnection();
	    
	    try {
	        String str = "SELECT * FROM historique_depense INNER JOIN depense ON depense.id_depense = historique_depense.id_depense INNER JOIN categorie ON depense.id_categorie = categorie.id_categorie WHERE depense.id_utilisateur = ? AND MONTH(depense.date_depense) = MONTH(CURRENT_DATE()) AND YEAR(depense.date_depense) = YEAR(CURRENT_DATE()) ORDER BY depense.date_depense ASC;";
	        PreparedStatement ps = connection.prepareStatement(str);
	        ps.setInt(1, id_utilisateur);
	        ResultSet res = ps.executeQuery();
	        while (res.next()) {
	            Depense depense = new Depense();
	            depense.setId_depense(res.getInt("id_depense"));
	            depense.setNom_categorie(res.getString("nom_categorie"));
	            depense.setId_utilisateur(res.getInt("id_utilisateur"));
	            depense.setMontant(res.getDouble("montant"));
	            LocalDate dateDepense = res.getDate("date_depense").toLocalDate();
	            if (dateDepense.getMonthValue() == YearMonth.now().getMonthValue() && dateDepense.getYear() == YearMonth.now().getYear()) {
	                depense.setDateDepense(dateDepense);
	                
	                depenses.add(depense);
	            }
	        }
	        connection.close();
	        ps.close();
	    } catch (SQLException e) {
	        System.out.println("Erreur dans la classe DepenseDaoImp dans la methode findById" + e.toString());
	    }
	    return depenses;
	}

	public int modifierDepense(Depense depense) {
		String query = "UPDATE Depense SET id_categorie = ?, id_budget = ?, montant = ?,repetition = ? WHERE id_depense = ?";
	    int res = 0;
	    Connection connection = Utilitaire.getConnection();
	    try {
	        // Check if id_categorie exists
	        String checkQuery = "SELECT COUNT(*) FROM categorie WHERE id_categorie = ?";
	        PreparedStatement checkPs = connection.prepareStatement(checkQuery);
	        checkPs.setInt(1, depense.getId_categorie());
	        ResultSet rs = checkPs.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            // Proceed with update if id_categorie exists
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setInt(1, depense.getId_categorie());
	            ps.setInt(2, depense.getId_budget());
	            ps.setDouble(3, depense.getMontant());
	            ps.setString(4, depense.getRepetition());
	            ps.setInt(5, depense.getId_depense());
	           
	            res = ps.executeUpdate();
	            ps.close();
	        } else {
	            System.out.println("Erreur: id_categorie does not exist");
	        }
	        checkPs.close();
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Erreur " + getClass() + " Modifier depense " + e.toString());
	    }
	    return res;
		
	}

}
