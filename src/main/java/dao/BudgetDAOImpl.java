package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modele.Budget;
import modele.Depense;
import modele.Objectif;
import util.Utilitaire;

public class BudgetDAOImpl implements BudgetDAO{
	

	public List<Budget> getAllBudgetData(int id_utilisateur) {
		        List<Budget> budgetData = new ArrayList<>();
		        Connection connection = null;
		        PreparedStatement stmt = null;
		        ResultSet rs = null;

		        try {
		            // Establish database connection
		            connection = Utilitaire.getConnection();
		            
		            // SQL query to fetch data from Budget and calculate total expenses from BudgetHistory
		            String sql = "SELECT b.id_budget, b.nom_budget, b.montant, COALESCE(SUM(d.montant), 0) AS total_expenses " +
		                         "FROM Budget b " +
		                         "LEFT JOIN Depense d ON b.id_budget = d.id_budget and d.id_utilisateur=? " +
		                         "GROUP BY b.id_budget";
		            
		            stmt = connection.prepareStatement(sql);
		            stmt.setInt(1, id_utilisateur);
		            rs = stmt.executeQuery();

		            // Process the results
		            while (rs.next()) {
		                int idBudget = rs.getInt("id_budget");
		                String nomBudget = rs.getString("nom_budget");
		                double initialAmount = rs.getDouble("montant");
		                double totalExpenses = rs.getDouble("total_expenses");
		                double remainingAmount = initialAmount - totalExpenses;

		                // Create a Budget object with the retrieved data
		                Budget budget = new Budget(idBudget, nomBudget, initialAmount, remainingAmount);
		                budgetData.add(budget);
		            }
		            stmt.close();
			          connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		            // Handle database errors
		        } 

		        return budgetData;
		    }
	

	@Override
	public void updateBudget(List<Budget> budgets, List<Depense> expenses) {
		// TODO Auto-generated method stub
		
	}
	public List<Budget> getBudgetUtilisateur(int id_budgetuser) {
		String query="Select * FROM Budget where id_utilisateur=?";
		List<Budget> budgets=new ArrayList<Budget>();
		Budget budget=new Budget();
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, id_budgetuser);
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				budget=new Budget();
				budget.setId_budget(res.getInt("id_budget"));
				budget.setId_categorie(res.getInt("id_categorie"));
				budget.setId_utilisateur(res.getInt("id_utilisateur"));
				budget.setMontant(res.getDouble("montant"));
				budget.setRemaining_montant(res.getDouble("remaining_montant"));
				budget.setNom_budget(res.getString("nom_budget"));
				budget.setPeriode(res.getString("periode"));
				budgets.add(budget);
			}
			ps.close();
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+" getBudgetUtilisateur "+e.toString());
		}
		
		return budgets;
	}
	
	public int Addbudget(int id_categoris,int id_utilisateur,String nom_budget,double montant,String periode,int id_devise) {
		String query="INSERT INTO Budget(id_categorie,id_utilisateur,nom_budget,montant,remaining_montant,periode,date_creation) Values (?,?,?,?,?,?,?)";
		int res=0;
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(2, id_utilisateur);
			ps.setInt(1, id_categoris);
			ps.setString(3, nom_budget);
			ps.setDouble(4, montant);
			ps.setDouble(5, montant);
			ps.setString(6, periode);
			ps.setDate(7, java.sql.Date.valueOf(LocalDate.now())); 
			res=ps.executeUpdate();
			query="UPDATE UTILISATEUR SET id_devise=? where id_utilisateur=?";
			ps=connection.prepareStatement(query);
			ps.setInt(1, id_devise);
			ps.setInt(2, id_utilisateur);
			res=ps.executeUpdate();
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+"getAllCategorie "+e.toString());
		}
		
		return res;
	}
	
	



	public int modifierBudget(Budget budget) {
	    String query = "UPDATE Budget SET id_categorie = ?, nom_budget = ?, montant = ?, remaining_montant=?,periode = ? WHERE id_budget = ?";
	    int res = 0;
	    Connection connection = Utilitaire.getConnection();
	    try {
	        // Check if id_categorie exists
	        String checkQuery = "SELECT COUNT(*) FROM categorie WHERE id_categorie = ?";
	        PreparedStatement checkPs = connection.prepareStatement(checkQuery);
	        checkPs.setInt(1, budget.getId_categorie());
	        ResultSet rs = checkPs.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            // Proceed with update if id_categorie exists
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setInt(1, budget.getId_categorie());
	            ps.setString(2, budget.getNom_budget());
	            ps.setDouble(3, budget.getMontant());
	            ps.setDouble(4, budget.getMontant());
	            ps.setString(5, budget.getPeriode());
	            ps.setInt(6, budget.getId_budget());
	            res = ps.executeUpdate();
	            ps.close();
	        } else {
	            System.out.println("Erreur: id_categorie does not exist");
	        }
	        checkPs.close();
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Erreur " + getClass() + " Modifier budget " + e.toString());
	    }
	    return res;
	}



	public int SupprimerBudget(int id_budget) {
		int res=0;
		String query="DELETE FROM Budget where  id_budget=?";
		
		Connection connection=Utilitaire.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, id_budget);
			res=ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur "+getClass()+"supprimer budget "+e.toString());
		}
		
		return res;
	}



	

}
