package dao;

import java.util.List;

import modele.Dette;

public interface DetteDAO {
	public int ajouterDette(Dette dette);
	public int SupprimerDetteUtilisateur(int id) ;
	public int modifierDette(Dette dette);
	public int ajouterMontantDette(int id_utilisateur, int id_dette, double montantdette);
	public List<Dette> getDetteUtilisateur(int id_utilisateur);
}
