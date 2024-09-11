package dao;

import java.util.List;

import modele.Objectif;

public interface ObjectifDao {
	public int retraitObjectif(int id_objectif, double d) ;
	public int modifierObjectif(Objectif objectif) ;
	public int SupprimerObjectifUtilisateur(int id_objectif);
	public int deposittoObjectif(int id_utilisateur, int id_objectif, double montantdepot) ;
	public List<Objectif> getObjectif(int id_utilisateur);
	public int ajouterObjectif(Objectif objectif);
}
