package modele;

import java.time.LocalDate;
import java.util.Date;

public class Budget {
	private int id_budget;
	private int id_utilisateur;
	private int id_categorie;
	private String nom_budget;
	private double montant;
	private String periode;
	private LocalDate dateCreation;
	private  double remaining_montant;
	
	public int getId_budget() {
		return id_budget;
	}
	public void setId_budget(int id_budget) {
		this.id_budget = id_budget;
	}
	public int getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public int getId_categorie() {
		return id_categorie;
	}
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}
	public String getNom_budget() {
		return nom_budget;
	}
	public void setNom_budget(String nom_budget) {
		this.nom_budget = nom_budget;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getPeriode() {
		return periode;
	}
	public void setPeriode(String periode) {
		this.periode = periode;
	}
	
	
	public LocalDate getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public Budget(int id_budget, int id_utilisateur, int id_categorie, String nom_budget, double montant,
			String periode, LocalDate dateCreation) {
		super();
		this.id_budget = id_budget;
		this.id_utilisateur = id_utilisateur;
		this.id_categorie = id_categorie;
		this.nom_budget = nom_budget;
		this.montant = montant;
		this.periode = periode;
		this.dateCreation = dateCreation;
	}
	public Budget() {
		
	}
	public Budget(int idBudget, String nomBudget, double initialAmount, double remainingAmount) {
		this.id_budget=idBudget;
		this.nom_budget=nomBudget;
		this.montant=initialAmount;
		this.remaining_montant=remainingAmount;
	}
	
	  public double getRemaining_montant() {  
	        return remaining_montant;
	    }

	    public void setRemaining_montant(double remaining_montant) {  
	        this.remaining_montant = remaining_montant;
	    }
	@Override
	public String toString() {
		return "Budget [id_budget=" + id_budget + ", id_utilisateur=" + id_utilisateur + ", id_categorie="
				+ id_categorie + ", nom_budget=" + nom_budget + ", montant=" + montant + ", periode=" + periode
				+ ", dateCreation=" + dateCreation + ", remainingAmount=" + remaining_montant + "]";
	}
	
	
	
}
