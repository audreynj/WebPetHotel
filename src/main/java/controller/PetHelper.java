package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Pets;

/**
 * Audrey N. Johnston - ajohnston10
 * CIS175
 * Feb 12, 2024
 */

public class PetHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebPetHotel");
	
	public void insertPet(Pets p) {
		EntityManager em = emfactory.createEntityManager();	
		em.getTransaction().begin();	
		em.persist(p);	
		em.getTransaction().commit();	
		em.close();	
	}
	
	public List<Pets> showAllPets(){
		EntityManager em = emfactory.createEntityManager();	
		List<Pets> allPets = em.createQuery("SELECT i FROM Pets i").getResultList();	
		return allPets;	
	}
	
	public void deletePetInformation(Pets toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pets> typedQuery = em.createQuery("select p from Pets p where p.type = :selectedType and p.name = :selectedName and p.owner = :selectedOwner", Pets.class);
	
		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedType", toDelete.getType());	
		typedQuery.setParameter("selectedName",	toDelete.getName());	
		typedQuery.setParameter("selectedOwner", toDelete.getOwner());	

		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		Pets result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);	
		em.getTransaction().commit();	
		em.close();	
	}
	
	public Pets searchForPetById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Pets found = em.find(Pets.class, idToEdit);
		em.close();
		return found;
	}
	
	public void updatePetImformation(Pets toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Pets> searchForPetByType(String petType){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pets> typedQuery = em.createQuery("select p from Pets p where p.type = :selectedType", Pets.class);
		typedQuery.setParameter("selectedType", petType);
		
		List<Pets> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}
	
	public List<Pets> searchForPetByName(String petName){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pets> typedQuery = em.createQuery("select p from Pets p where p.name = :selectedName", Pets.class);
		typedQuery.setParameter("selectedName", petName);
		
		List<Pets> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}
	
	public List<Pets> searchForPetByOwner(String petOwner){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pets> typedQuery = em.createQuery("select p from Pets p where p.owner = :selectedOwner", Pets.class);
		typedQuery.setParameter("selectedOwner", petOwner);
		
		List<Pets> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}
	
	
	
	public void cleanUp() {
		emfactory.close();
	}
}
