package dao;

import java.util.List;

import modele.Budget;
import modele.Depense;

public interface BudgetDAO {
	public void updateBudget(List<Budget> budgets, List<Depense> expenses);
	public List<Budget> getBudgetUtilisateur(int id_budgetuser);
	public int Addbudget(int id_categoris,int id_utilisateur,String nom_budget,double montant,String periode,int id_devise) ;
	public int modifierBudget(Budget budget);
	public int SupprimerBudget(int id_budget);
	public List<Budget> getAllBudgetData(int id_utilisateur);
	
}
