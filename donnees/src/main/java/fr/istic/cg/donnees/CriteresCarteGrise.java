package fr.istic.cg.donnees;



public class CriteresCarteGrise extends Criteres{
	public static String IMMATRICULATION_CLE = "immatriculation";
	public static String REF_VEHICULE_CLE = "refVehicule";

	
	public CriteresCarteGrise(){
		super();
		
		goodCle.add(IMMATRICULATION_CLE);
		goodCle.add(REF_VEHICULE_CLE);
	}
}
