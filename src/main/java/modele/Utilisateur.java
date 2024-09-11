package modele;

public class Utilisateur {
	private int id_utilisateur;
	private int id_devise;
	private String nom;
	private String prenom;
	private String email;
	private String mdps;
	public int getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public int getId_devise() {
		return id_devise;
	}
	public void setId_devise(int id_devise) {
		this.id_devise = id_devise;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdps() {
		return mdps;
	}
	public void setMdps(String mdps) {
		this.mdps = mdps;
	}
	public Utilisateur(int id_utilisateur, int id_devise, String nom, String prenom, String email, String mdps) {
		super();
		this.id_utilisateur = id_utilisateur;
		this.id_devise = id_devise;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdps = mdps;
	}
	public Utilisateur() {
	}
	@Override
	public String toString() {
		return "Utilisateur [id_utilisateur=" + id_utilisateur + ", id_devise=" + id_devise + ", nom=" + nom
				+ ", prenom=" + prenom + ", email=" + email + ", mdps=" + mdps + "]";
	}

}
