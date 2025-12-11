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

	/*
	public String egiaztatu() {
		if (izena.length()!=pasahitza.length()){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: izenaren eta pasahitzaren luzera desberdinak dira."));
			return null;
		}
		if (izena.equals("pirata"))
			return "error";
		else
			return "ok";
	}
	
	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Data aukeratua: "+event.getObject()));
	}
	
	public ridesBean() {
	}
	
	
	public void onEventSelect(SelectEvent event) {
		this.mota=(ErabiltzailearenMota)event.getObject();
		// Egia esan, selection="#{login.mota}" atributuarekin ere lortzen da
		FacesContext.getCurrentInstance().addMessage("nireForm:mezuak",
		 new FacesMessage("Erabiltzailearen mota (taula):"+mota.getKodea()+"/"+mota.getErabMota()));}
		 */
}
