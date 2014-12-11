package fr.istic.cg.donnees;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
		em.remove(em.find(Particulier.class,p.getId()));
		return true;
	}

	public List<Particulier> search(Criteres<Particulier> myCriteres) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Particulier> q = cb.createQuery(Particulier.class);
		Root<Particulier> root = q.from(Particulier.class);
		
		
		if(myCriteres == null || myCriteres.getCount() == 0){
			q.select(root);
		}else{
			List<Predicate> predicates = myCriteres.getCriteres(cb, root);
			q.select(root).where(predicates.toArray(new Predicate[]{}));
		}

		TypedQuery<Particulier> query = em.createQuery(q);
		
		List<Particulier> results = query.getResultList();
		
		return results;
	}

}
