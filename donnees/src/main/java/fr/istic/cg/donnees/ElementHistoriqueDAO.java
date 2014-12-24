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
		em.remove(em.find(ElementHistorique.class, e.getId()));
		return true;
	}

	public List<ElementHistorique> search(Criteres<ElementHistorique> myCriteres) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ElementHistorique> q = cb.createQuery(ElementHistorique.class);
		Root<ElementHistorique> root = q.from(ElementHistorique.class);
		
		
		if(myCriteres == null || myCriteres.getCount() == 0){
			q.select(root);
		}else{
			List<Predicate> predicates = myCriteres.getCriteres(cb, root);
			q.select(root).where(predicates.toArray(new Predicate[]{}));
		}

		TypedQuery<ElementHistorique> query = em.createQuery(q);
		
		List<ElementHistorique> results = query.getResultList();
		
		return results;
	}

}
