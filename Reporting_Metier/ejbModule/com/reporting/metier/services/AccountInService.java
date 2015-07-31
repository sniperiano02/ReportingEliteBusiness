package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.AccountIn;
import com.reporting.metier.interfaces.AccountINRemote;


@Stateless
public class AccountInService implements AccountINRemote {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<AccountIn> getAllAccountsIn() {
		// TODO Auto-generated method stub
		return em.createQuery("Select a From AccountIn a").getResultList();
	}

	@Override
	public void createAccountIn(AccountIn account) {
		em.persist(account);
	}

	@Override
	public void updateAccountIn(AccountIn account) {
		em.merge(account);
		
	}

	@Override
	public void deleteAccountIn(AccountIn account) {
		em.remove(em.contains(account) ? account : em.merge(account));
		
	}

}
