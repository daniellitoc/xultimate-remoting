package org.danielli.xultimate.remoting.cxf;

import java.util.List;

import javax.jws.WebService;

import org.danielli.xultimate.remoting.domain.Account;

@WebService
public interface AccountServiceEndpoint {
	
	void insertAccount(Account acc);
	List<Account> getAccounts(String name);
	List<Account> getAllAccounts();

}
