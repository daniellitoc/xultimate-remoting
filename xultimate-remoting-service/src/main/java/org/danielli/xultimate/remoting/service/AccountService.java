package org.danielli.xultimate.remoting.service;

import java.util.List;

import org.danielli.xultimate.remoting.domain.Account;

public interface AccountService {
	void insertAccount(Account account);
	
	List<Account> getAccounts(String name);
	
	List<Account> getAllAccounts();
}
