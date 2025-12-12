package domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Car implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String plate;
	private int seats;
	@ManyToOne
	@JoinColumn(name = "driver_car")
	private Driver driver;
	
	public Car() {
		super();
	}
	
	public Car(String plate, int seats, Driver driver) {
		this.plate = plate;
		this.seats = seats;
		this.driver = driver;
	}
	
	public String getPlate() {
		return plate;
	}
	
	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	public int getSeats() {
		return seats;
	}
	
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	public Driver getDriver() {
		return driver;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}	
}