package fr.istic.cg.donnees;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import fr.istic.cg.persistance.Societe;

public class SocieteDAO implements  BaseDAO<Societe> {
	
	@PersistenceContext
    private EntityManager em;
	
	static private SocieteDAO mySelf;
	static public SocieteDAO getInstance(){
		return mySelf;
	}
	
	public SocieteDAO(){
		mySelf = this;
	}

	@Transactional
	public boolean save(Societe s) {
		em.persist(s);
		
		return true;
	}

	@Transactional
	public boolean update(Societe s) {
		em.merge(s);
		return true;
	}

	@Transactional
	public Societe get(String identifiant) {
		Societe p = em.find(Societe.class, Integer.parseInt(identifiant) );
		return p;
	}

	@Transactional
	public boolean delete(Societe s) {
		em.detach(s);
		return true;
	}

}
