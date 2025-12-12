package eredua.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import businessLogic.BLFacade;
import domain.Car;
import domain.Ride;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("createRide")
@RequestScoped	
public class CreateBean implements Serializable {
	private String nondik;
	private String nora;
	private Date data;
	private int eserleku;
	private float prezio;
	private String dMail;
	private List<Car> carList;
	private Car selectedCar;
	private String selectedCarPlate;
	
	BLFacade facadeBL=FacadeBean.getBusinessLogic();

	@PostConstruct
	public void init() {
	    dMail = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userEmail");
	    carList = facadeBL.getCarPlates(dMail);
	    if (!carList.isEmpty()) {
	        selectedCar = carList.get(0);
	        selectedCarPlate = selectedCar.getPlate();
	        eserleku = selectedCar.getSeats()-1;
	    }
	}
	
	public Date getData() {
	return data;
	}
	
	public void setData(Date data) {
	this.data = data;
	}

	public String getNondik() {
		return nondik;
	}

	public void setNondik(String nondik) {
		this.nondik = nondik;
	}

	public String getNora() {
		return nora;
	}

	public void setNora(String nora) {
		this.nora = nora;
	}
	
	public int getEserleku() {
		return eserleku;
	}

	public void setEserleku(int eserleku) {
		this.eserleku = eserleku;
	}

	public float getPrezio() {
		return prezio;
	}

	public void setPrezio(float prezio) {
		this.prezio = prezio;
	}
	
	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

	public Car getSelectedCar() {
		return selectedCar;
	}

	public String getSelectedCarPlate() {
		return selectedCarPlate;
	}

	public void setSelectedCarPlate(String selectedCarPlate) {
		this.selectedCarPlate = selectedCarPlate;
		if (selectedCarPlate != null) {
	        for (Car c : carList) {
	            if (c.getPlate().equals(selectedCarPlate)) {
	                this.selectedCar = c;
	                this.eserleku = c.getSeats()-1;
	                break;
	            }
	        }
	    }
	}

	public void setSelectedCar(Car selectedCar) {
		this.selectedCar = selectedCar;
		if (selectedCar != null) {
			this.eserleku = selectedCar.getSeats();
		}
	}

	public String menuEraman() {
		return "menu";
	}
	
	public List<String> getCarPlates(){
		List<String> plateList = new Vector<String>();
		for (Car c : carList) {
			plateList.add(c.getPlate());
		}
		return plateList;
	}

	
	public String createRide() {
		System.out.println("DEBUG createRide(): nondik=" + nondik + " nora=" + nora + " data=" + data + " eserleku=" + eserleku + " prezio=" + prezio);
        try {
            Ride r = facadeBL.createRide(nondik, nora, data, eserleku, prezio, dMail, selectedCar);
            if (r == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Ride couldn't be created.", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Ride successfully created.", null));
            }
        } catch (RideMustBeLaterThanTodayException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date must be later than today.", null));
        } catch (RideAlreadyExistException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "That ride already exists.", null));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error when creating the ride.", null));
        }
        return null;
    }
}
