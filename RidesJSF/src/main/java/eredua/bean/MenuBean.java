package eredua.bean;

import java.io.Serializable;

import businessLogic.BLFacade;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("menu")
@ApplicationScoped	
public class MenuBean implements Serializable {
	
	BLFacade facadeBL=FacadeBean.getBusinessLogic();

	
	public String createEraman() {
		return "create";
	}
	
	public String queryEraman() {
		return "query";
	}
	
	public String logout() {
		return "logout";
	}
	
	public String carEraman() {
		return "car";
	}
}
