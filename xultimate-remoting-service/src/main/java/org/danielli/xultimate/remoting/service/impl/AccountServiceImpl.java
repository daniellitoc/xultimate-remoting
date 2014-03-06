package org.danielli.xultimate.remoting.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.danielli.xultimate.remoting.dto.Account;
import org.danielli.xultimate.remoting.service.AccountService;
import org.springframework.stereotype.Component;

@Component("accountService")
public class AccountServiceImpl implements AccountService {
	
	private Map<String, List<Account>> accountMap = new ConcurrentHashMap<String, List<Account>>();
	
	@Override
	public void insertAccount(Account account) {
		String name = account.getName();
		synchronized (accountMap) {
			List<Account> accountList = accountMap.get(name);
			if (accountList == null) {
				accountList = new ArrayList<>();
				accountMap.put(name, accountList);
			}
			accountList.add(account);
		}
		
		
	}

	@Override
	public List<Account> getAccounts(String name) {
		return accountMap.get(name);
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> allAccounts = new ArrayList<>();
		synchronized (accountMap) {
			for (List<Account> accounts : accountMap.values()) {
				allAccounts.addAll(accounts);
			}
		}
		return allAccounts;
	}

}
