package eredua.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import businessLogic.BLFacade;
import domain.Ride;
import jakarta.annotation.PostConstruct;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("queryRide")
@ViewScoped	
public class QueryBean implements Serializable {
	
	private String selectedCity;
	private String arrivedCity;
	private Date data;
	private int eserleku;
	private List<Ride> rideList;
	
	BLFacade facadeBL=FacadeBean.getBusinessLogic();
	
	@PostConstruct
	public void init() {
	    List<String> departCities = facadeBL.getDepartCities();
	    if (!departCities.isEmpty()) {
	        selectedCity = departCities.get(0);
	        List<String> arrivals = facadeBL.getDestinationCities(selectedCity);
	        if (!arrivals.isEmpty()) {
	            arrivedCity = arrivals.get(0);
	        }
	    }
	    updateRides();
	}

	
	public String getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}
	
	public String getArrivedCity() {
		return arrivedCity;
	}

	public void setArrivedCity(String arrivedCity) {
		this.arrivedCity = arrivedCity;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		if (data != null) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(data);
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        this.data = cal.getTime();
	    } else {
	        this.data = null;
	    }
	}

	public int getEserleku() {
		return eserleku;
	}

	public void setEserleku(int eserleku) {
		this.eserleku = eserleku;
	}

	public String menuEraman() {
		return "menu";
	}
	
	public List<String> getDepartCities(){
		return facadeBL.getDepartCities();
	}
	
	public List<String> getArrivalCities(){
		return facadeBL.getDestinationCities(selectedCity);
	}
	
	public List<Ride> getRideList() {
		return rideList;
	}

	public void setRides(List<Ride> rideList) {
		this.rideList = rideList;
	}
	
	public void updateArrivalCities(AjaxBehaviorEvent event) {
	    List<String> arrivals = facadeBL.getDestinationCities(selectedCity);
	    
	    if (arrivals != null && !arrivals.isEmpty()) {
	        arrivedCity = arrivals.get(0);
	    } else {
	        arrivedCity = null;
	    }
	    updateRides(); 
	}

	public void updateRides() {
	    if (selectedCity != null && arrivedCity != null && data != null) {
	        rideList = facadeBL.getRides(selectedCity, arrivedCity, data);
	    } else {
	        rideList = new ArrayList<>();
	    }
	}
}