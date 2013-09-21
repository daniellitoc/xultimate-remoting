package org.danielli.xultimate.remoting.service.impl;

import org.danielli.xultimate.remoting.service.CheckingAccountService;
import org.springframework.stereotype.Component;

@Component("simpleCheckingAccountService")
public class SimpleCheckingAccountService implements CheckingAccountService {

	@Override
	public void cancelAccount(Long accountId) {
		System.out.println("Cancelling account [" + accountId + "]");
	}

}
