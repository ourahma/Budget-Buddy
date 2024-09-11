package dao;

import java.util.List;

import modele.Depense;

public interface DepenseDAO {
	List<Depense> getAllExpenses(int id);
	int SupprimerDepenseUtilisateur(int id_depense,int id_utilisateur);
    int save(Depense depense);
    public int modifierDepense(Depense depense);
   
}
