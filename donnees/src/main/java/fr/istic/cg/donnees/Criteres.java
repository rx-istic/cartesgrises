package fr.istic.cg.donnees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class Criteres<T> {


	protected HashMap<String, String> criteres;
	protected ArrayList<String> goodCle;

	public Criteres(){
		criteres = new HashMap<String, String>();
		goodCle = new ArrayList<String>();
	}

	public void addCritere(String cle, String valeur){
		if(checkCle(cle)){
			criteres.put(cle, valeur);
		}else{
			System.out.println("Mauvaise clé entrée !");//TODO a retirer
		}
	}

	protected boolean checkCle(String cleEntree){
		for(String s : goodCle){
			if(s.equals(cleEntree)){
				return true;
			}
		}
		return false;
	}

	public int getCount(){
		return criteres.size();
	}

	/*
	public String getCritere(int i){
		return "";// criteres.;
	}*/


	public List<Predicate> getCriteres(CriteriaBuilder cb, Root<T> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		for(String cle : goodCle){
			if(criteres.containsKey(cle)){
				predicates.add(cb.equal(root.get(cle), criteres.get(cle)));
			}
		}
		return predicates;
	}

}
