package org.danielli.xultimate.remoting.service;

import java.util.List;

import javax.jws.WebService;

import org.danielli.xultimate.remoting.dto.Account;

@WebService
public interface AccountServiceEndpoint {
	
	void insertAccount(Account acc);
	List<Account> getAccounts(String name);
	List<Account> getAllAccounts();

}
