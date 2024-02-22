package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.HotelListDetails;

/**
 * Audrey N. Johnston - ajohnston10
 * CIS175
 * Feb 20, 2024
 */
public class HotelListDetailsHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebPetHotel");

	public void insertNewListDetails(HotelListDetails s) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<HotelListDetails> getLists(){
		EntityManager em = emfactory.createEntityManager();
		List<HotelListDetails> allDetails = em.createQuery("SELECT d FROM HotelListDetails d").getResultList();
		return allDetails;
	}

	public HotelListDetails searchForListDetailsById(Integer tempId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		HotelListDetails found = em.find(HotelListDetails.class, tempId);
		em.close();
		return found;
	}

	public void deleteList(HotelListDetails toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<HotelListDetails> typedQuery = em.createQuery("SELECT detail from HotelListDetails detail where detail.id= :selectedId", HotelListDetails.class);
		
		//substitute parameter with actual data from toDelete pet information
		typedQuery.setParameter("selectedId", toDelete.getId());
		
		typedQuery.setMaxResults(1);
		
		//get result and save it into a new list pet information
		HotelListDetails result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void updateList(HotelListDetails toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
}
