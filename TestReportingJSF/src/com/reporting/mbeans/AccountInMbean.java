package com.reporting.mbeans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;

import com.reporting.metier.entities.AccountIn;
import com.reporting.metier.interfaces.AccountINRemote;

@ManagedBean(name="account_in")
@ViewScoped
public class AccountInMbean implements Serializable {
	
	
	@EJB
	private AccountINRemote accountIn_Service;
	
	
	private InputText p ;
	
	
	public InputText getP() {
		return p;
	}
	public void setP(InputText p) {
		this.p = p;
	}
	
	private List<AccountIn> liste_accountIn = new ArrayList<>();
	
	private AccountIn account;
	
	private AccountIn account1;
	
	
	@PostConstruct
	public void  init(){
		
		liste_accountIn= accountIn_Service.getAllAccountsIn();
		account = new AccountIn();
		account1= new AccountIn();
	}
	
	public void createAccountIn(){
		account.setDateModif(new Timestamp(new Date().getTime()));
		
			accountIn_Service.createAccountIn(account);
			liste_accountIn= accountIn_Service.getAllAccountsIn();
		
		
		
	}
	
	public void supprimer_account(){
		
		accountIn_Service.deleteAccountIn(account1);
		liste_accountIn= accountIn_Service.getAllAccountsIn();
	}
	
	public void modifier_account(){
		account1.setDateModif(new Timestamp(new Date().getTime()));
		accountIn_Service.updateAccountIn(account1);
		liste_accountIn= accountIn_Service.getAllAccountsIn();
	}
	
	public void checkId(){
		for(int i=0;i<liste_accountIn.size();i++){
			if(liste_accountIn.get(i).getAccountId().equals(account.getAccountId())){
				FacesContext context = FacesContext.getCurrentInstance();
	            context.addMessage(p.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, " ID Dupliqué", null));
	        
			}
		}

		
	}
	
	
	public AccountIn getAccount() {
		return account;
	}
	public AccountIn getAccount1() {
		return account1;
	}
	public void setAccount(AccountIn account) {
		this.account = account;
	}
	public void setAccount1(AccountIn account1) {
		this.account1 = account1;
	}
	public List<AccountIn> getListe_accountIn() {
		return liste_accountIn;
	}
	public void setListe_accountIn(List<AccountIn> liste_accountIn) {
		this.liste_accountIn = liste_accountIn;
	}

}
