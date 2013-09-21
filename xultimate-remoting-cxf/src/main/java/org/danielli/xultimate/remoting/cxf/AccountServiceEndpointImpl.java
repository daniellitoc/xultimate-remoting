package org.danielli.xultimate.remoting.cxf;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.danielli.xultimate.remoting.domain.Account;
import org.danielli.xultimate.remoting.service.AccountService;
import org.springframework.stereotype.Component;

@WebService(endpointInterface = "org.danielli.xultimate.remoting.cxf.AccountServiceEndpoint")
@Component("accountServiceEndpoint")
public class AccountServiceEndpointImpl implements AccountServiceEndpoint {
	@Resource(name = "accountService")
	private AccountService biz;
	
	public void insertAccount(Account acc) {
		biz.insertAccount(acc);
	}
	
	public List<Account> getAccounts(String name) {
		return biz.getAccounts(name);
	}
	
	public List<Account> getAllAccounts() {
		return biz.getAllAccounts();
	}
}
