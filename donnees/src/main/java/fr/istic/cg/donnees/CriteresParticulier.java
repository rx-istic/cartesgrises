package fr.istic.cg.donnees;



public class CriteresParticulier extends Criteres{
	/*critères valides pour la recherche*/
	public static String ID_CLE = "id";
	public static String ADRESSE_CLE = "adresse";
	public static String NOM_CLE = "nom";
	public static String PRENOM_CLE = "prenom";

	
	public CriteresParticulier(){
		super();
		
		goodCle.add(ID_CLE);
		goodCle.add(ADRESSE_CLE);
		goodCle.add(NOM_CLE);
		goodCle.add(PRENOM_CLE);
	}
}
