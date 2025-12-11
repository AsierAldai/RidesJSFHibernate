package eredua.bean;

import java.io.Serializable;

import businessLogic.BLFacade;
import domain.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("car")
@SessionScoped
public class CarBean implements Serializable {
	
	private String plate;
	private int seats;
	private String dMail;
	
	BLFacade facadeBL=FacadeBean.getBusinessLogic();
	
	@PostConstruct
	public void init() {
	    dMail = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userEmail");
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

	public String menuEraman() {
		return "menu";
	}
	
	public String addCar() {
		System.out.println("DEBUG createRide(): plate=" + plate + " seats=" + seats);
		try { 
			Car c = facadeBL.addCar(plate, seats, dMail);
            if (c == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Car couldn't be added.", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Car successfully added.", null));
            }
        }catch (Exception e){
        	FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "This car is already attached to a driver.", null));
        }finally {
        	return null;
        }
	}
	
	
	
}