package fr.istic.cg.donnees;


public interface  BaseDAO<T> {
	
	public boolean save(T cg);
	public boolean update(T cg);
	public T get(String identifiant);
	//public Collection<T> query(Criteres criteres); //TODO ??? a revoir
	public boolean delete(T cg);
}
