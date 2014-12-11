package fr.istic.cg.metier.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.istic.cg.donnees.CriteresSociete;
import fr.istic.cg.donnees.CriteresVehicule;
import fr.istic.cg.metier.Creation;
import fr.istic.cg.metier.Modification;
import fr.istic.cg.metier.Recherche;
import fr.istic.cg.metier.Suppression;
import fr.istic.cg.persistance.Societe;
import fr.istic.cg.persistance.Vehicule;

@ContextConfiguration("/fr/istic/cg/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MetierTestRecherche {

	@Autowired
	Creation c;
	
	@Autowired
	Modification modif;
	
	@Autowired
	Recherche rec;
	
	@Autowired
	Suppression suppr;
	
	
/*
	@Test
	public void testChercher(){
		
		//System.err.println(c);
	}*/
	
	
/*	private Recherche composantATester;
	private CarteGriseDAO bouchonCGDAO;
	private VehiculeDAO bouchonVehiculeDAO;
	//private ReferentielDAO bouchonRefDAO;
	
	@Before
	public void setup(){
		bouchonCGDAO = Mockito.mock(CarteGriseDAO.class);
		bouchonVehiculeDAO = Mockito.mock(VehiculeDAO.class);
		composantATester = new Recherche();
	}
	
	@Test
	public void testChercher(){
		//Mockito.when(bouchonCGDAO.get("12-abc-34")).thenReturn(new CarteGrise());
		
		/*
		Criteres critere = new Criteres();
		List<CarteGrise> liste = composantATester.chercherCG(critere);
		Assert.assertEquals(0, liste.size());*/
		/*
		CriteresVehicule critere = new CriteresVehicule();
		List<Vehicule> liste = composantATester.chercherVehicule(critere);
		Assert.assertEquals(0, liste.size());
		
		
		Vehicule v = new Vehicule();
    	v.setNumSerie("ABC");
    	v.setMarque("XX");
		
		bouchonVehiculeDAO.save(v);
		
		List<Vehicule> liste2 = composantATester.chercherVehicule(critere);
		Assert.assertEquals(1, liste2.size());
		
	}*/
	
	@Test
	@Transactional
	public void testCreation(){
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
	public void testRechercheSociete(){
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
    	
	}
	
	
	@Test
	@Transactional
	public void testModifierSociete(){
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
		
    	total.setNumSiret("31337");
    	modif.societe(total);
    	
		CriteresSociete cs = new CriteresSociete();
		
		cs.addCritere(CriteresSociete.NUMSIRET_CLE, "31337");
		List<Societe> liste = rec.chercherSociete(cs);
		
		Assert.assertEquals(1, liste.size());	
    	
	}
	
	
	@Test
	@Transactional
	public void testSupprimerSociete(){
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
		
    	
    	suppr.societe(total);
    	
		CriteresSociete cs = new CriteresSociete();
		List<Societe> liste = rec.chercherSociete(cs);
		
		Assert.assertEquals(1, liste.size());	
    	
	}
}
