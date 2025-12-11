package eredua.bean;

import java.io.Serializable;

import businessLogic.BLFacade;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("home")
@ApplicationScoped	
public class HomeBean implements Serializable {
	
	BLFacade facadeBL=FacadeBean.getBusinessLogic();

	
	public String registerEraman() {
		return "register";
	}
	
	public String loginEraman() {
		return "login";
	}
}