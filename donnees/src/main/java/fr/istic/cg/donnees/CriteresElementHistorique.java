package fr.istic.cg.donnees;



public class CriteresElementHistorique extends Criteres{
	public static String ID_CLE = "id";
	public static String REFPROPRIETAIRE_CLE = "refProrietaire";
	public static String DATE_DEBUT_CLE = "dateDebut";
	public static String DATE_FIN_CLE = "dateFin";

	
	public CriteresElementHistorique(){
		super();
		
		goodCle.add(ID_CLE);
		goodCle.add(REFPROPRIETAIRE_CLE);
		goodCle.add(DATE_DEBUT_CLE);
		goodCle.add(DATE_FIN_CLE);
	}
}
