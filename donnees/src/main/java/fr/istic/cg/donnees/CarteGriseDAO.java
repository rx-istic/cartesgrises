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

import fr.istic.cg.persistance.CarteGrise;


public class CarteGriseDAO implements BaseDAO<CarteGrise>{
	
	@PersistenceContext
    private EntityManager em;
	
	static private CarteGriseDAO mySelf;
	static public CarteGriseDAO getInstance(){
		return mySelf;
	}
	
	
	public CarteGriseDAO(){
		mySelf = this;
	}

	@Transactional
	public boolean save(CarteGrise cg) {
		em.persist(cg);
		return true;
	}

	@Transactional
	public boolean update(CarteGrise cg) {
		em.merge(cg);
		return true;
	}

	@Transactional
	public CarteGrise get(String identifiant) {
		
		return em.find(CarteGrise.class, identifiant );
	}

	@Transactional
	public boolean delete(CarteGrise cg) {
		em.remove(em.find(CarteGrise.class, cg.getImmatriculation()));
		return true;
	}

	@Transactional
	public List<CarteGrise> search(Criteres<CarteGrise> myCriteres) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<CarteGrise> q = cb.createQuery(CarteGrise.class);
		Root<CarteGrise> root = q.from(CarteGrise.class);
		
		
		if(myCriteres == null || myCriteres.getCount() == 0){
			q.select(root);
		}else{
			List<Predicate> predicates = myCriteres.getCriteres(cb, root);
			q.select(root).where(predicates.toArray(new Predicate[]{}));
		}

		TypedQuery<CarteGrise> query = em.createQuery(q);
		
		List<CarteGrise> results = query.getResultList();
		
		return results;
	}
	
}
