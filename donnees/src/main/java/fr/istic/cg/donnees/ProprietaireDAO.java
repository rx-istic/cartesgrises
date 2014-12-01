package fr.istic.cg.donnees;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import fr.istic.cg.persistance.Proprietaire;

@Deprecated
public class ProprietaireDAO implements  BaseDAO<Proprietaire> {
	
	@PersistenceContext
    private EntityManager em;
	
	static private ProprietaireDAO mySelf;
	static public ProprietaireDAO getInstance(){
		return mySelf;
	}
	
	public ProprietaireDAO(){
		mySelf = this;
	}

	@Transactional
	public boolean save(Proprietaire cg) {
		em.persist(cg);
		
		return true;
	}

	@Transactional
	public boolean update(Proprietaire cg) {
		em.merge(cg);
		return false;
	}

	@Transactional
	public Proprietaire get(String identifiant) {
		Proprietaire p = em.find(Proprietaire.class, Integer.parseInt(identifiant) );
		return p;
	}

	@Transactional
	public boolean delete(Proprietaire cg) {
		em.detach(cg);
		return true;
	}

}
