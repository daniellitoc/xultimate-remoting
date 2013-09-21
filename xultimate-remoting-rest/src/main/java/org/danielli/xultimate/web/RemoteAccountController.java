package org.danielli.xultimate.web;

import java.util.List;

import javax.annotation.Resource;

import org.danielli.xultimate.remoting.domain.Account;
import org.danielli.xultimate.remoting.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/remoting/accounts")
public class RemoteAccountController {
	
	@Resource(name = "accountService")
	public AccountService accountService;
	
	@RequestMapping(method = { RequestMethod.POST })
	@ResponseBody
	public Account doInsertAccount(@RequestBody Account account) {
		accountService.insertAccount(account);
		return account;
	}
	
	@RequestMapping(method = { RequestMethod.GET })
	@ResponseBody
	public List<Account> getAccounts(Account account) {
		if (account == null || account.getName() == null) {
			return accountService.getAllAccounts();
		} else {
			return accountService.getAccounts(account.getName());
		}
	}
}
