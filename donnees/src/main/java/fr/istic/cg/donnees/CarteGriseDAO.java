package fr.istic.cg.donnees;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import fr.istic.cg.persistance.CarteGrise;

@Component
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

	public boolean save(CarteGrise cg) {
		em.persist(cg);
		return true;
	}

	public boolean update(CarteGrise cg) {
		em.merge(cg);
		return true;
	}

	public CarteGrise get(String identifiant) {
		
		return em.find(CarteGrise.class, identifiant );
	}

	public boolean delete(CarteGrise cg) {
		em.detach(cg);
		return true;
	}

	public List<CarteGrise> search(Criteres<CarteGrise> myCriteres) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
