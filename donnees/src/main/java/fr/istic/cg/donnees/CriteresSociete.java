package fr.istic.cg.donnees;



public class CriteresSociete extends Criteres{
	/*critères valides pour la recherche*/
	public static String ID_CLE = "id";
	public static String ADRESSE_CLE = "adresse";
	public static String RAISON_SOCIALE_CLE = "raisonSociale";
	public static String NUMSIRET_CLE = "numSiret";

	
	public CriteresSociete(){
		super();
		
		goodCle.add(ID_CLE);
		goodCle.add(ADRESSE_CLE);
		goodCle.add(RAISON_SOCIALE_CLE);
		goodCle.add(NUMSIRET_CLE);
	}
}
