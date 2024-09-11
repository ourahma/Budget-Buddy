package modele;

import java.util.Date;

public class Objectif {
	private int id_objectif;
	private int id_utilisateur;
	private String nom_objectif;
	private double montant;
	private Date date_limite;
	private int id_categorie;
	private double intialAmout;
	private boolean completed;
	
	public int getId_objectif() {
		return id_objectif;
	}
	public void setId_objectif(int id_objectif) {
		this.id_objectif = id_objectif;
	}
	public int getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public String getNom_objectif() {
		return nom_objectif;
	}
	public void setNom_objectif(String nom_objectif) {
		this.nom_objectif = nom_objectif;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Date getDate_limite() {
		return date_limite;
	}
	public void setDate_limite(Date date_limite) {
		this.date_limite = date_limite;
	}
	public Objectif(int id_objectif, int id_utilisateur, String nom_objectif, double montant, Date date_limite) {
		super();
		this.id_objectif = id_objectif;
		this.id_utilisateur = id_utilisateur;
		this.nom_objectif = nom_objectif;
		this.montant = montant;
		this.date_limite = date_limite;
	}
	
	public int getId_categorie() {
		return id_categorie;
	}
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}
	
	public double getIntialAmout() {
		return intialAmout;
	}
	public void setIntialAmout(double intialAmout) {
		this.intialAmout = intialAmout;
	}
	public Objectif() {
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	@Override
	public String toString() {
		return "Objectif [id_objectif=" + id_objectif + ", id_utilisateur=" + id_utilisateur + ", nom_objectif="
				+ nom_objectif + ", montant=" + montant + ", date_limite=" + date_limite + ", id_categorie="
				+ id_categorie + ", intialAmout=" + intialAmout + ", completed=" + completed + "]";
	}
	
	
	
}
