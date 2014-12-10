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
	
	
	@Transactional
	public List<Societe> search(Criteres<Societe> myCriteres) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Societe> q = cb.createQuery(Societe.class);
		Root<Societe> root = q.from(Societe.class);
		
		
		//q.select(c).where(cb.equal(c.get("marque"), "Wayne"));
		if(myCriteres == null || myCriteres.getCount() == 0){
			q.select(root);
		}else{
			List<Predicate> predicates = myCriteres.getCriteres(cb, root);
			q.select(root).where(predicates.toArray(new Predicate[]{}));
		}

		TypedQuery<Societe> query = em.createQuery(q);
		
		List<Societe> results = query.getResultList();
		
		return results;
	}

}
