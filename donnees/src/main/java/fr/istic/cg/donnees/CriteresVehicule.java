package fr.istic.cg.donnees;



public class CriteresVehicule extends Criteres{
	/*critères valides pour la recherche*/
	public static String NUMSERIE_CLE = "numSerie";
	public static String MARQUE_CLE = "marque";
	public static String MODELE_CLE = "modele";
	public static String TYPE_CLE = "type";

	
	public CriteresVehicule(){
		super();
		
		goodCle.add(NUMSERIE_CLE);
		goodCle.add(MARQUE_CLE);
		goodCle.add(MODELE_CLE);
		goodCle.add(TYPE_CLE);
	}
}
