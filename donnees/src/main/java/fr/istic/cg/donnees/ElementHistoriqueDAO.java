package fr.istic.cg.donnees;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import fr.istic.cg.persistance.ElementHistorique;

public class ElementHistoriqueDAO implements BaseDAO<ElementHistorique> {
	
	@PersistenceContext
    private EntityManager em;
	
	static private ElementHistoriqueDAO mySelf;
	static public ElementHistoriqueDAO getInstance(){
		return mySelf;
	}
	
	public ElementHistoriqueDAO(){
		mySelf = this;
	}
	@Transactional
	public boolean save(ElementHistorique e) {
		em.persist(e);
		
		return true;
	}

	@Transactional
	public boolean update(ElementHistorique e) {
		em.merge(e);
		return true;
	}

	@Transactional
	public ElementHistorique get(String identifiant) {
		ElementHistorique el = em.find(ElementHistorique.class, Integer.parseInt(identifiant) );
		return el;
	}

	@Transactional
	public boolean delete(ElementHistorique e) {
		em.detach(e);
		return true;
	}

}