package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.AccountIn;


@Remote
public interface AccountINRemote {
	
	
	public List<AccountIn> getAllAccountsIn();
	
	public void createAccountIn(AccountIn account);
	public void updateAccountIn(AccountIn account);
	
	public void deleteAccountIn(AccountIn account);

}
