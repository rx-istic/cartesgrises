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
public class MetierTestModification {

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
	
}
