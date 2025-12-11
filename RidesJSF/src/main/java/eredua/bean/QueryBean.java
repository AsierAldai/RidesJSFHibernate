package eredua.bean;

import java.io.Serializable;
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
		this.data = data;
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
	
	public List<Ride> saveRides(){
		this.rideList = facadeBL.getRides(selectedCity, arrivedCity, data);
		return rideList;
	}
	
	public void updateRides() {
		List<Ride> itzul = saveRides();
	}
	
	public void onChange(AjaxBehaviorEvent ev) {
		List<String> arrivals = getArrivalCities();
		updateRides();
	}
}