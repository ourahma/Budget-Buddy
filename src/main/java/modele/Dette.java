package modele;

import java.util.Date;

public class Dette {
	private int id_dette;
	private int id_utilisateur;
	private String nom_dette;
	private String description;
	private double montant;
	private Date date;
	private double intialAmount;
	private boolean completed;
	
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public int getId_dette() {
		return id_dette;
	}
	public void setId_dette(int id_dette) {
		this.id_dette = id_dette;
	}
	public int getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public String getNom_dette() {
		return nom_dette;
	}
	public void setNom_dette(String nom_dette) {
		this.nom_dette = nom_dette;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getIntialAmount() {
		return intialAmount;
	}
	public void setIntialAmount(double intialAmount) {
		this.intialAmount = intialAmount;
	}
	
	public Dette() {
	}
	@Override
	public String toString() {
		return "Dette [id_dette=" + id_dette + ", id_utilisateur=" + id_utilisateur + ", nom_dette=" + nom_dette
				+ ", description=" + description + ", montant=" + montant + ", date=" + date + ", intialAmount="
				+ intialAmount + "]";
	}
	
	
}
