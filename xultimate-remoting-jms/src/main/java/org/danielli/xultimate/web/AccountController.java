package org.danielli.xultimate.web;

import javax.annotation.Resource;

import org.danielli.xultimate.remoting.service.CheckingAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/accounts")
public class AccountController {
	
	@Resource(name = "clientCheckingAccountService")
	public CheckingAccountService accountService;
	
	@RequestMapping(value = "/{accountId}", method = { RequestMethod.GET })
	public String cancelAccount(@PathVariable Long accountId, ModelMap modelMap) {
		accountService.cancelAccount(accountId);
		return "account_cancel_success";
	}
}
