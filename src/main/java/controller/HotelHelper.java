package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Hotel;

/**
 * Audrey N. Johnston - ajohnston10
 * CIS175
 * Feb 19, 2024
 */

public class HotelHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebPetHotel");

	public void insertHotel(Hotel h) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(h);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Hotel> showAllHotels(){
		EntityManager em = emfactory.createEntityManager();
		List<Hotel> allHotels = em.createQuery("SELECT h from Hotels h").getResultList();
		return allHotels;
	}
	
	public Hotel findHotel(String nameToLookUp) {

		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Hotel> typedQuery = em.createQuery("select h from Hotel h where h.hotelName = :selectedName",Hotel.class);
		typedQuery.setParameter("selectedName", nameToLookUp);
		typedQuery.setMaxResults(1);
		Hotel foundHotel;
		try {
			foundHotel = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			foundHotel = new Hotel(nameToLookUp);
		}
		em.close();

		return foundHotel;
	}
}