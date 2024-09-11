package modele;

public class Devise {
	private int id_devise;
	private String annotation;
	private String pays;
	private String symbole;
	public int getId_devise() {
		return id_devise;
	}
	public void setId_devise(int id_devise) {
		this.id_devise = id_devise;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getSymbole() {
		return symbole;
	}
	public void setSymbole(String symbole) {
		this.symbole = symbole;
	}
	public Devise(int id_devise, String annotation, String pays, String symbole) {
		super();
		this.id_devise = id_devise;
		this.annotation = annotation;
		this.pays = pays;
		this.symbole = symbole;
	}
	
	public Devise() {
	}
	@Override
	public String toString() {
		return "Devise [id_devise=" + id_devise + ", annotation=" + annotation + ", pays=" + pays + ", symbole="
				+ symbole + "]";
	}
	
}
