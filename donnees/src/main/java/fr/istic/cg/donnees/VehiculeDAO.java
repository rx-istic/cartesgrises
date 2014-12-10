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

import fr.istic.cg.persistance.Vehicule;

public class VehiculeDAO implements BaseDAO<Vehicule> {



	@PersistenceContext
	private EntityManager em;

	static private VehiculeDAO mySelf;
	static public VehiculeDAO getInstance(){
		return mySelf;
	}

	public VehiculeDAO(){
		mySelf = this;
	}

	@Transactional
	public boolean save(Vehicule v) {
		em.persist(v);

		return true;
	}

	@Transactional
	public boolean update(Vehicule v) {
		em.merge(v);
		return true;
	}

	@Transactional
	public Vehicule get(String identifiant) {
		Vehicule v = em.find(Vehicule.class, identifiant );
		return v;
	}

	@Transactional
	public boolean delete(Vehicule v) {
		em.detach(v);
		return true;
	}

	@Transactional
	public List<Vehicule> search(Criteres<Vehicule> myCriteres) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Vehicule> q = cb.createQuery(Vehicule.class);
		Root<Vehicule> root = q.from(Vehicule.class);
		
		
		
		//q.select(c).where(cb.equal(c.get("marque"), "Wayne"));
		if(myCriteres == null || myCriteres.getCount() == 0){
			q.select(root);
		}else{
			List<Predicate> predicates = myCriteres.getCriteres(cb, root);
			q.select(root).where(predicates.toArray(new Predicate[]{}));
		}

		TypedQuery<Vehicule> query = em.createQuery(q);
		
		List<Vehicule> results = query.getResultList();
		
		return results;

		/*
		try {
			//String strQuery = "SELECT v FROM Vehicule v WHERE p.numSerie = ?1";
			String strQuery = "SELECT v FROM Vehicule v";
			TypedQuery<Vehicule> query = em.createQuery(strQuery, Vehicule.class);		
			//query.setParameter(1, id);

			return query.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Vehicule>();
		}*/


	}

}
