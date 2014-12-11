package fr.istic.cg.metier.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.istic.cg.donnees.CriteresCarteGrise;
import fr.istic.cg.donnees.CriteresElementHistorique;
import fr.istic.cg.donnees.CriteresParticulier;
import fr.istic.cg.donnees.CriteresSociete;
import fr.istic.cg.donnees.CriteresVehicule;
import fr.istic.cg.metier.Creation;
import fr.istic.cg.metier.Modification;
import fr.istic.cg.metier.Recherche;
import fr.istic.cg.metier.Suppression;
import fr.istic.cg.persistance.CarteGrise;
import fr.istic.cg.persistance.ElementHistorique;
import fr.istic.cg.persistance.Particulier;
import fr.istic.cg.persistance.Societe;
import fr.istic.cg.persistance.Vehicule;

@ContextConfiguration("/fr/istic/cg/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MetierTestCreation {

	@Autowired
	Creation c;

	@Autowired
	Modification modif;

	@Autowired
	Recherche rec;

	@Autowired
	Suppression suppr;


	@Test
	@Transactional
	public void testCreationVehicule(){
		Vehicule v = new Vehicule();
		v.setNumSerie("ABC");
		v.setMarque("XX");

		c.vehicule(v);

		CriteresVehicule cv = new CriteresVehicule();
		cv.addCritere(CriteresVehicule.NUMSERIE_CLE, "ABC");

		List<Vehicule> liste = rec.chercherVehicule(cv);

		Assert.assertEquals(1, liste.size());


		Vehicule v2 = liste.get(0);
		assertEquals("XX", v2.getMarque());
		assertEquals("ABC", v2.getNumSerie());

		Vehicule v3 = new Vehicule();
		v3.setNumSerie("ABCD");
		v3.setMarque("XX");

		Vehicule v4 = new Vehicule();
		v4.setNumSerie("ABCDE");
		v4.setMarque("YY");

		c.vehicule(v3);
		c.vehicule(v4);

		CriteresVehicule cv2 = new CriteresVehicule();
		cv2.addCritere(CriteresVehicule.MARQUE_CLE, "XX");
		List<Vehicule> liste2 = rec.chercherVehicule(cv2);
		Assert.assertEquals(2, liste2.size());

		CriteresVehicule cv3 = new CriteresVehicule();
		cv3.addCritere(CriteresVehicule.MARQUE_CLE, "YY");
		List<Vehicule> liste3 = rec.chercherVehicule(cv3);
		Assert.assertEquals(1, liste3.size());

		CriteresVehicule cv4 = new CriteresVehicule();
		cv4.addCritere(CriteresVehicule.MARQUE_CLE, "XX");
		cv4.addCritere(CriteresVehicule.NUMSERIE_CLE, "ABCD");
		List<Vehicule> liste4 = rec.chercherVehicule(cv4);
		Assert.assertEquals(1, liste4.size());


	}

	@Test
	@Transactional
	public void testCreationSociete(){
		Societe aviva = new Societe();
		aviva.setAdresse("impasse Bernard Madoff");
		aviva.setRaisonSociale("Aviva SA");
		aviva.setNumSiret("1324");

		c.societe(aviva);

		Societe total = new Societe();
		total.setAdresse("Luxembourg");
		total.setRaisonSociale("TOTAL SA");
		total.setNumSiret("852147");

		c.societe(total);

		CriteresSociete cs = new CriteresSociete();

		List<Societe> liste = rec.chercherSociete(cs);

		Assert.assertEquals(2, liste.size());	

		cs.addCritere(CriteresSociete.NUMSIRET_CLE, "852147");

		List<Societe> liste2 = rec.chercherSociete(cs);

		Assert.assertEquals(1, liste2.size());


	}
	
	@Test
	@Transactional
	public void testCreationParticulier(){
		Particulier batman = new Particulier();
		batman.setAdresse("Gotham");
		batman.setNom("Wayne");
		batman.setPrenom("Bruce");
		c.particulier(batman);

		Particulier superman = new Particulier();
		superman.setAdresse("Metropolis");
		superman.setNom("Kent");
		superman.setPrenom("Clark");
		c.particulier(superman);


		CriteresParticulier cp = new CriteresParticulier();
		List<Particulier> liste = rec.chercherParticulier(cp);

		Assert.assertEquals(2, liste.size());	

		cp.addCritere(CriteresParticulier.NOM_CLE, "Kent");

		List<Particulier> liste2 = rec.chercherParticulier(cp);

		Assert.assertEquals(1, liste2.size());
	}
	
	@Test
	@Transactional
	public void testCreationElementHistorique(){
		Particulier batman = new Particulier();
		batman.setAdresse("Gotham");
		batman.setNom("Wayne");
		batman.setPrenom("Bruce");
		c.particulier(batman);
		
		Particulier superman = new Particulier();
		superman.setAdresse("Metropolis");
		superman.setNom("Kent");
		superman.setPrenom("Clark");
		c.particulier(superman);
		
		ElementHistorique elh = new ElementHistorique();
    	elh.setDateDebut(new Date(1997, 12, 25));
    	Date now = new Date(System.currentTimeMillis());
    	elh.setDateFin(now);
    	elh.setRefProrietaire(batman);
    	
    	c.elementHistorique(elh);
    	
    	
    	CriteresElementHistorique ceh = new CriteresElementHistorique();
    	List<ElementHistorique> liste = rec.chercherElementHistorique(ceh);
    	Assert.assertEquals(1, liste.size());
    	if(liste.size()<1)
    		return;
    	
    	ElementHistorique el2 = liste.get(0);
    	
    	assertEquals(new Date(1997, 12, 25), el2.getDateDebut());
    	assertEquals(now, el2.getDateFin());
    	assertEquals(batman, el2.getRefProrietaire());
    	
    	
    	ElementHistorique elh3 = new ElementHistorique();
    	elh3.setDateDebut(new Date(1998, 11, 15));
    	elh3.setDateFin(new Date(1999, 10, 14));
    	elh3.setRefProrietaire(superman);
    	c.elementHistorique(elh3);
    	
    	liste = rec.chercherElementHistorique(ceh);
    	Assert.assertEquals(2, liste.size());
    	
    	//TODO fine tune research for elementhistorique
    	/*
    	ceh.addCritere(CriteresElementHistorique.DATE_FIN_CLE);
    	liste = rec.chercherElementHistorique(ceh);
    	Assert.assertEquals(1, liste.size());
    	
    	if(liste.size()<1)
    		return;
    	
    	ElementHistorique el4 = liste.get(0);
    	assertEquals(new Date(1998, 11, 15), el4.getDateDebut());
    	assertEquals(new Date(1999, 10, 14), el4.getDateFin());
    	assertEquals(batman, el4.getRefProrietaire());*/
	}
	
	@Test
	@Transactional
	public void testCreationCarteGrise(){
		Particulier batman = new Particulier();
		batman.setAdresse("Gotham");
		batman.setNom("Wayne");
		batman.setPrenom("Bruce");
		c.particulier(batman);
		
		Vehicule v = new Vehicule();
		v.setNumSerie("31337");
		v.setMarque("Waynes Motors");
		v.setType("Justicier");
		v.setModele("Batmobile");

		c.vehicule(v);
		
		ElementHistorique elh = new ElementHistorique();
    	elh.setDateDebut(new Date(1997, 12, 25));
    	Date now = new Date(System.currentTimeMillis());
    	elh.setDateFin(now);
    	elh.setRefProrietaire(batman);
    	
    	c.elementHistorique(elh);
    	
    	
    	CarteGrise cg = new CarteGrise();
    	cg.setImmatriculation("D4RK_KN1G7H");
    	cg.setRefVehicule(v);
    	cg.addHistorique(elh);
    	
    	c.carteGrise(cg);
    	
    	CriteresCarteGrise ccg = new CriteresCarteGrise();
    	List<CarteGrise> liste = rec.chercherCarteGrise(ccg);
    	
    	Assert.assertEquals(1, liste.size());
    	
    	if(liste.size()<1)
    		return;
    	
    	CarteGrise cg2 = liste.get(0);
    	assertEquals(cg.getImmatriculation(), cg2.getImmatriculation());
    	assertEquals(cg.getRefVehicule(), cg.getRefVehicule());

	}
}
