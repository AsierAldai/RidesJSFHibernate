package eredua.bean;

import java.io.Serializable;
import java.util.Date;
import businessLogic.BLFacade;
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
	
	BLFacade facadeBL=FacadeBean.getBusinessLogic();

	@PostConstruct
	public void init() {
	    dMail = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userEmail");
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
	
	public String menuEraman() {
		return "menu";
	}
	
	public String createRide() {
		System.out.println("DEBUG createRide(): nondik=" + nondik + " nora=" + nora + " data=" + data + " eserleku=" + eserleku + " prezio=" + prezio);
        try {
            Ride r = facadeBL.createRide(nondik, nora, data, eserleku, prezio, dMail);
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
