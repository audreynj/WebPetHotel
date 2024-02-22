package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Audrey N. Johnston - ajohnston10
 * CIS175
 * Feb 19, 2024
 */

@Entity
@Table(name="hotel")

public class Hotel {

	@Id
	@GeneratedValue
	private int id;
	private String hotelName;
	
	public Hotel() {
		super();
	}
	public Hotel(int id, String hotelName) {
		super();
		this.id = id;
		this.hotelName = hotelName;
	}
	
	public Hotel(String hotelName) {
		super();
		this.hotelName = hotelName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHotelName() {
		return hotelName;
    }
	public void setHotelName(String hotelName) {
		this.hotelName= hotelName;
	}
	
	@Override
	public String toString() {
			return "Hotel [id=" + id + ", hotelName=" + hotelName + "]";
	}
	
	
}
