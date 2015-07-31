package com.reporting.mbeans;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.reporting.metier.entities.GuiTabsGroup;
import com.reporting.metier.entities.GuiUser;
import com.reporting.metier.interfaces.userRemote;


@ManagedBean(name="login_bean")
@SessionScoped
public class LoginMbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private userRemote userService;
	private GuiUser user;
	
	
	
	public GuiUser getUser() {
		return user;
	}
	public void setUser(GuiUser user) {
		this.user = user;
	}
	
	private boolean loggedIn= false;
	private List<GuiTabsGroup> tabs = new ArrayList<>();
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public void setTabs(List<GuiTabsGroup> tabs) {
		this.tabs = tabs;
	}
	
	public List<GuiTabsGroup> getTabs() {
		return tabs;
	}
	private boolean administration = false;
	
	private boolean prepaye = false;
	
	private boolean postpaye = false;
	private boolean interco_inter = false;
	private boolean interco_national = false;
	private boolean roa_in = false;
	private boolean roa_out = false;
	private boolean smart = false;
	private boolean fraude = false;
	private boolean KPI = false;
	
	

	public boolean isAdministration() {
		return administration;
	}
	public void setAdministration(boolean administration) {
		this.administration = administration;
	}
	public boolean isPrepaye() {
		return prepaye;
	}
	public void setPrepaye(boolean prepaye) {
		this.prepaye = prepaye;
	}
	public boolean isPostpaye() {
		return postpaye;
	}
	public void setPostpaye(boolean postpaye) {
		this.postpaye = postpaye;
	}
	public boolean isInterco_inter() {
		return interco_inter;
	}
	public void setInterco_inter(boolean interco_inter) {
		this.interco_inter = interco_inter;
	}
	public boolean isInterco_national() {
		return interco_national;
	}
	public void setInterco_national(boolean interco_national) {
		this.interco_national = interco_national;
	}
	public boolean isRoa_in() {
		return roa_in;
	}
	public void setRoa_in(boolean roa_in) {
		this.roa_in = roa_in;
	}
	public boolean isRoa_out() {
		return roa_out;
	}
	public void setRoa_out(boolean roa_out) {
		this.roa_out = roa_out;
	}
	public boolean isSmart() {
		return smart;
	}
	public void setSmart(boolean smart) {
		this.smart = smart;
	}
	public boolean isFraude() {
		return fraude;
	}
	public void setFraude(boolean fraude) {
		this.fraude = fraude;
	}
	public boolean isKPI() {
		return KPI;
	}
	public void setKPI(boolean kPI) {
		KPI = kPI;
	}
	@PostConstruct
	public void init(){
		
		user = new GuiUser();
	}
	public String getEncodedPassword(String key) {
		  byte[] uniqueKey = key.getBytes();
		  byte[] hash = null;
		  try {
			hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		  } catch (NoSuchAlgorithmException e) {
			throw new Error("no MD5 support in this VM");
		  }
		  StringBuffer hashString = new StringBuffer();
		  for ( int i = 0; i < hash.length; ++i ) {
			String hex = Integer.toHexString(hash[i]);
			if ( hex.length() == 1 ) {
			  hashString.append('0');
			  hashString.append(hex.charAt(hex.length()-1));
			} else {
			  hashString.append(hex.substring(hex.length()-2));
			}
		  }
		  return hashString.toString();
		}
	
	public String authenticate() throws IOException{
		String navigateTo=null;
		String password=getEncodedPassword(user.getUPwd());
		GuiUser found = userService.authenticate(user.getULogin(),
				password);
		if (found != null) {
			user = found;
			loggedIn = true;
		
			  HttpSession session = Util.getSession();
			  session.setAttribute("user.account",found);
			  session.setAttribute("username",found.getNomUtilisateur());
			 tabs = user.getUser_group().getTab_groups();
			 
			 for(int i=0;i<tabs.size();i++){
				 if(tabs.get(i).getTabName().equals("Mobile prépayé")){
					 prepaye=true;
				 }
if(tabs.get(i).getTabName().equals("Administration")){
					administration=true; 
				 }
if(tabs.get(i).getTabName().equals("Mobile postpayé")){
	 postpaye=true;
}

if(tabs.get(i).getTabName().equals("International")){
	 interco_inter=true;
}
if(tabs.get(i).getTabName().equals("Interco National")){
	 interco_national=true;
}
if(tabs.get(i).getTabName().equals("Roaming IN")){
	 roa_in=true;
}
if(tabs.get(i).getTabName().equals("Roaming OUT")){
	 roa_out=true;
}
if(tabs.get(i).getTabName().equals("SMART Closing")){
	 smart=true;
}
if(tabs.get(i).getTabName().equals("Fraude")){
	 fraude=true;
}
if(tabs.get(i).getTabName().equals("KPIs")){
	 KPI = true;
}
			 }
			
			
				FacesContext.getCurrentInstance().getExternalContext().redirect("/TestReportingJSF/welcome.jsf");
			

		} else {
			FacesContext.getCurrentInstance().addMessage(
					"@form",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Mot de passe Ou Login Erroné!!","Veuillez Réssayer"));
		}
		return navigateTo;
		
	}
	
	public void logout() throws IOException {
		
	      HttpSession session = Util.getSession();
	      session.invalidate();
	     
			FacesContext.getCurrentInstance().getExternalContext().redirect("/TestReportingJSF/login.jsf");
		
	   }
	
	
	
	
	
	
	
	
	

}
