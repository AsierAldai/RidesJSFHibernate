package eredua.bean;

import java.io.Serializable;

import businessLogic.BLFacade;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("login")
@SessionScoped
public class LoginBean implements Serializable {
	
	private String email;
	private String pasahitza;
	
	BLFacade facadeBL=FacadeBean.getBusinessLogic();
	
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
	
	public String login() {
		String em = facadeBL.login(email, pasahitza);
		if (em.equals("notfound")) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Driver not found with those credentials.", null));
        }
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userEmail", email);
		
		return em;
	}
}