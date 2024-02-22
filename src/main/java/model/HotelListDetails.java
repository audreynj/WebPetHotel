package model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * Audrey N. Johnston - ajohnston10
 * CIS175
 * Feb 19, 2024
 */


@Entity
//@Table(name=automatic)
public class HotelListDetails {
	@Id
	@GeneratedValue
	private int id;

	private String listName;
	private LocalDate bookingDate;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private Hotel hotel;

	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<Pet> listOfPets;
	
	public HotelListDetails() {
		super();
	}
	public HotelListDetails(int id, String listName, LocalDate bookingDate, Hotel hotel, List<Pet> listOfPets) {
		this.id = id;
		this.listName = listName;
		this.bookingDate = bookingDate;
		this.hotel = hotel;
		this.listOfPets = listOfPets;
	}
	public HotelListDetails(String ListName, LocalDate bookingDate, Hotel hotel, List<Pet> listOfPets) {
		this.listName = ListName;
		this.bookingDate = bookingDate;
		this.hotel = hotel;
		this.listOfPets = listOfPets;
	}
	public HotelListDetails(String ListName, LocalDate bookingDate, Hotel hotel) {
		this.listName = ListName;
		this.bookingDate = bookingDate;
		this.hotel = hotel;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public List<Pet> getListOfPets() {
		return listOfPets;
	}
	public void setListOfPets(List<Pet> listOfItems) {
		this.listOfPets = listOfItems;
	}
	
	@Override
	public String toString() {
		String msgFirst = "Details [id=" + id + 
		         ", listName=" + listName +
		         ", bookingDate=" + bookingDate +
		         ", " + hotel;
 
		return  msgFirst + ", " + listOfPets + "]";			       
	}
}
