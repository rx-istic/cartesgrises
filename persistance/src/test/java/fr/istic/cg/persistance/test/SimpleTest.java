package fr.istic.cg.persistance.test;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.istic.cg.persistance.CarteGrise;
import fr.istic.cg.persistance.ElementHistorique;
import fr.istic.cg.persistance.Particulier;
import fr.istic.cg.persistance.Proprietaire;
import fr.istic.cg.persistance.Societe;
import fr.istic.cg.persistance.Vehicule;

@ContextConfiguration("/fr/istic/cg/applicationContext-persistence.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleTest {

    @PersistenceContext
    private EntityManager em;
    
    @Before
    public void setup() {
    	
    }
    
    @Test
    @Transactional
    public void testMarque() {
        assertTrue(em.isOpen());
    }

    @Test
    @Transactional
    public void testCreationVehicule() {
    	Vehicule v = new Vehicule();
    	v.setNumSerie("ABC");
    	v.setMarque("XX");
    	em.persist(v);
    	Vehicule v2 = em.find(Vehicule.class, "ABC");
    	assertEquals("XX", v2.getMarque());
    	assertEquals("ABC", v2.getNumSerie());
    }
    
    @Test
    @Transactional
    public void testCreationProprioParticulier() {
    	Particulier michel = new Particulier();
    	michel.setAdresse("3, rue Saint Malo");
    	michel.setNom("Mabel");
    	michel.setPrenom("Michel");
    	
    	em.persist(michel);
    	
    	Particulier kevin = new Particulier();
    	kevin.setAdresse("12, place Tuning");
    	kevin.setNom("Peujo");
    	kevin.setPrenom("Kevin");
    	
    	em.persist(kevin);
    	Proprietaire p2 = em.find(Particulier.class, michel.getId() );
    	
    	assertEquals("Mabel", ((Particulier)p2).getNom());
    	assertEquals("Michel", ((Particulier) p2).getPrenom());
    	assertEquals("3, rue Saint Malo", ((Particulier) p2).getAdresse());
    	
    }
    
    @Test
    @Transactional
    public void testCreationProprioSociete() {
    	Societe aviva = new Societe();
    	aviva.setAdresse("impasse Bernard Madoff");
    	aviva.setRaisonSociale("Aviva SA");
    	aviva.setNumSiret("1324");
    	
    	em.persist(aviva);
    	
    	Societe p = em.find(Societe.class, aviva.getId() );
    	
    	assertEquals("impasse Bernard Madoff", ((Societe) p).getAdresse());
    	assertEquals("1324", ((Societe)p).getNumSiret());
    	assertEquals("Aviva SA", ((Societe) p).getRaisonSociale());
    	
    	
    }
    
    @Test(expected=PersistenceException.class)
    @Transactional
    public void testUniciteProprioSociete() {
    	Societe aviva = new Societe();
    	aviva.setAdresse("impasse Bernard Madoff");
    	aviva.setRaisonSociale("Aviva SA");
    	aviva.setNumSiret("1324");
    	
    	em.persist(aviva);
    	
    	Societe carglass = new Societe();
    	carglass.setAdresse("Allee les bleus");
    	carglass.setRaisonSociale("Carglass");
    	carglass.setNumSiret("1324");
    	
    	em.persist(carglass);
    	
    }
    
    @Test
    @Transactional
    public void testCreationElementHistorique() {
    	
    	Societe carglass = new Societe();
    	carglass.setAdresse("Allee les bleus");
    	carglass.setRaisonSociale("Carglass");
    	carglass.setNumSiret("1324");
    	
    	em.persist(carglass);
    	
    	ElementHistorique elh = new ElementHistorique();
    	elh.setDateDebut(new Date(1997, 12, 25));
    	Date now = new Date(System.currentTimeMillis());
    	elh.setDateFin(now);
    	elh.setRefProrietaire(carglass);
    	em.persist(elh);
    	
    	ElementHistorique el2 = em.find(ElementHistorique.class, elh.getId() );
    	
    	assertEquals(new Date(1997, 12, 25), el2.getDateDebut());
    	assertEquals(now, el2.getDateFin());
    	assertEquals(carglass, el2.getRefProrietaire());
    	
    	
    }
    
    @Test
    @Transactional
    public void testCreationCarteGrise() {
    	Vehicule v = new Vehicule();
    	v.setNumSerie("ABC");
    	v.setMarque("XX");
    	em.persist(v);
    	
    	
    	Societe carglass = new Societe();
    	carglass.setAdresse("Allee les bleus");
    	carglass.setRaisonSociale("Carglass");
    	carglass.setNumSiret("1324");
    	
    	em.persist(carglass);
    	
    	ElementHistorique elh = new ElementHistorique();
    	elh.setDateDebut(new Date(1997, 12, 25));
    	Date now = new Date(System.currentTimeMillis());
    	elh.setDateFin(now);
    	elh.setRefProrietaire(carglass);
    	em.persist(elh);
    	
    	CarteGrise cg = new CarteGrise();
    	cg.setImmatriculation("123az45");
    	cg.setRefVehicule(v);
    	cg.addHistorique(elh);
    	
    	em.persist(cg);
    	
    	CarteGrise cg2 = em.find(CarteGrise.class, cg.getImmatriculation() );
    	
    	assertEquals("123az45", cg2.getImmatriculation());
    	assertEquals(v, cg2.getRefVehicule());
    	assertTrue(cg2.getHistorique().size() == 1);
    	assertTrue(cg2.getHistorique().contains(elh));
    	
    	
    }
    
}
