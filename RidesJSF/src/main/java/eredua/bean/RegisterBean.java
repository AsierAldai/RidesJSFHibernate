package eredua.bean;

import java.io.Serializable;

import businessLogic.BLFacade;
import domain.Driver;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("register")
@ApplicationScoped	
public class RegisterBean implements Serializable {
	
	private String izena;
	private String email;
	private String pasahitza;
	
	BLFacade facadeBL=FacadeBean.getBusinessLogic();

	
	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public String homeEraman() {
		return "home";
	}
	
	public Driver register(){
		Driver itzul = null;
		try {
			itzul = facadeBL.register(this.izena, this.email, this.pasahitza);
			if (itzul == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Driver could not be created.", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Driver successfully created.", null));
            }
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}