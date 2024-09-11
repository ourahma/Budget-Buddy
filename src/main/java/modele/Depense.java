package modele;

import java.time.LocalDate;

public class Depense {
	private int id_depense;
	private int id_utilisateur;
	private int id_categorie;
	private int id_budget;
	private double montant;
	private String repetition;
	private LocalDate dateDepense;
	private String nom_categorie;
	
	
	public String getNom_categorie() {
		return nom_categorie;
	}
	public void setNom_categorie(String nom_categorie) {
		this.nom_categorie = nom_categorie;
	}
	public Depense(int id_depense, int id_utilisateur, int id_categorie, int id_budget, double montant,
			String repetition, LocalDate dateDepense) {
		super();
		this.id_depense = id_depense;
		this.id_utilisateur = id_utilisateur;
		this.id_categorie = id_categorie;
		this.id_budget = id_budget;
		this.montant = montant;
		this.repetition = repetition;
		this.dateDepense = dateDepense;
	}
	public LocalDate getDateDepense() {
		return dateDepense;
	}
	public void setDateDepense(LocalDate dateDepense) {
		this.dateDepense = dateDepense;
	}
	public int getId_depense() {
		return id_depense;
	}
	public void setId_depense(int id_depense) {
		this.id_depense = id_depense;
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
	public int getId_budget() {
		return id_budget;
	}
	public void setId_budget(int id_budget) {
		this.id_budget = id_budget;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getRepetition() {
		return repetition;
	}
	public void setRepetition(String repetition) {
		this.repetition = repetition;
	}
	
	public Depense() {
	}
	@Override
	public String toString() {
		return "Depense [id_depense=" + id_depense + ", id_utilisateur=" + id_utilisateur + ", id_categorie="
				+ id_categorie + ", id_budget=" + id_budget + ", montant=" + montant + ", repetition=" + repetition
				+ ", dateDepense=" + dateDepense + "]";
	}
	
	
}
