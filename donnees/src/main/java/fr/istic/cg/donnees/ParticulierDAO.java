package fr.istic.cg.donnees;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import fr.istic.cg.persistance.Particulier;

public class ParticulierDAO implements  BaseDAO<Particulier> {
	
	@PersistenceContext
    private EntityManager em;
	
	static private ParticulierDAO mySelf;
	static public ParticulierDAO getInstance(){
		return mySelf;
	}
	
	public ParticulierDAO(){
		mySelf = this;
	}

	@Transactional
	public boolean save(Particulier p) {
		em.persist(p);
		
		return true;
	}

	@Transactional
	public boolean update(Particulier p) {
		em.merge(p);
		return true;
	}

	@Transactional
	public Particulier get(String identifiant) {
		Particulier p = em.find(Particulier.class, Integer.parseInt(identifiant) );
		return p;
	}

	@Transactional
	public boolean delete(Particulier p) {
		em.detach(p);
		return true;
	}

	public List<Particulier> search(Criteres<Particulier> myCriteres) {
		// TODO Auto-generated method stub
		return null;
	}

}
