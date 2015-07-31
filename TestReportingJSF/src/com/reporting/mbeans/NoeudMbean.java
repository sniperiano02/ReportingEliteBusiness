package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.inputtext.InputText;

import com.reporting.metier.entities.GuiUser;
import com.reporting.metier.entities.Noeud;
import com.reporting.metier.interfaces.NoeudRemote;


@ManagedBean(name="noeud_bean")
@ViewScoped
public class NoeudMbean {
	
	
	@EJB
	private NoeudRemote noeudService;
	
	private List<Noeud> liste_noeuds= new ArrayList<>();
	
	private Noeud noeud;
	private Noeud noeud1;
	
	private InputText p;
	private InputText p1;
	
	public InputText getP1() {
		return p1;
	}
	public void setP1(InputText p1) {
		this.p1 = p1;
	}
	
	public InputText getP() {
		return p;
	}
	public void setP(InputText p) {
		this.p = p;
	}
	
	
	public List<Noeud> getListe_noeuds() {
		return liste_noeuds;
	}
	public void setListe_noeuds(List<Noeud> liste_noeuds) {
		this.liste_noeuds = liste_noeuds;
	}
	public void setNoeud(Noeud noeud) {
		this.noeud = noeud;
	}
	public void setNoeud1(Noeud noeud1) {
		this.noeud1 = noeud1;
	}
	public Noeud getNoeud() {
		return noeud;
	}
	public Noeud getNoeud1() {
		return noeud1;
	}
	@PostConstruct
	public void init(){
		noeud= new Noeud();
		liste_noeuds= noeudService.getNoeudNoms();
		noeud1= new Noeud();
		
	}
	
	public void addNoeud(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		GuiUser u=  (GuiUser) session.getAttribute("user.account");
		String s =  (String) session.getAttribute("username");
		System.out.println(u.getUName());
		System.out.println(s);
		noeud.setNomUtilisateur(u.getUName());
		noeudService.PersistNoeud(noeud);
		liste_noeuds= noeudService.getNoeudNoms();
		noeud= new Noeud();
	}
public void DeleteNoeud(){
		noeudService.deleteNoeud(noeud1);
		liste_noeuds= noeudService.getNoeudNoms();
	}
public void updateNoeud(){
	noeudService.updateNoeud(noeud1);
	liste_noeuds= noeudService.getNoeudNoms();
}
public void checkId(){
	for(int i=0;i<liste_noeuds.size();i++){
		if(liste_noeuds.get(i).getCodeNoeud().equals(noeud.getCodeNoeud())){
			FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(p.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, " ID Dupliqué", null));
        
		}
	}

	
}


public void checkId1(){
	for(int i=0;i<liste_noeuds.size();i++){
		if(liste_noeuds.get(i).getCodeNoeud().equals(noeud1.getCodeNoeud())){
			FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(p1.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, " ID Dupliqué", null));
        
		}
	}

	
}

}
