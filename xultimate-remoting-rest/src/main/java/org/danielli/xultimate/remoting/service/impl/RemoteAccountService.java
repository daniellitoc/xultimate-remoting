package org.danielli.xultimate.remoting.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.danielli.xultimate.remoting.dto.Account;
import org.danielli.xultimate.remoting.service.AccountService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service("remoteAccountService")
public class RemoteAccountService implements AccountService {

	@Resource(name = "restTemplate")
	private RestTemplate restTemplate;
	
	private HttpHeaders getHttpHeaders() {
		HttpHeaders entityHeaders = new HttpHeaders();
		entityHeaders.setContentType(MediaType.valueOf("application/json;UTF-8"));
		entityHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return entityHeaders;
	}
	
	
	@Override
	public void insertAccount(Account account) {
		HttpEntity<Account> httpEntity = new HttpEntity<Account>(account, getHttpHeaders());
		ResponseEntity<Account> responseEntity = restTemplate.exchange("http://localhost:8082/xultimate-remoting-rest/remoting/accounts", HttpMethod.POST, httpEntity, Account.class);
		Assert.isTrue(account.getName().equals(responseEntity.getBody().getName()));
	}

	@Override
	public List<Account> getAccounts(String name) {
		HttpEntity<Object> httpEntity = new HttpEntity<>(getHttpHeaders());
		ResponseEntity<Account[]> entity = restTemplate.exchange("http://localhost:8082/xultimate-remoting-rest/remoting/accounts?name={name}", HttpMethod.GET, httpEntity, Account[].class, name);
		if (entity.getBody() == null)
			return new ArrayList<>(0);
		return Arrays.asList(entity.getBody());
	}

	@Override
	public List<Account> getAllAccounts() {
		HttpEntity<Object> httpEntity = new HttpEntity<>(getHttpHeaders());
		ResponseEntity<Account[]> entity = restTemplate.exchange("http://localhost:8082/xultimate-remoting-rest/remoting/accounts", HttpMethod.GET, httpEntity, Account[].class);
		if (entity.getBody() == null)
			return new ArrayList<>(0);
		return Arrays.asList(entity.getBody());
	}

}
