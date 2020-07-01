package com.techelevator.tenmo.services;

import java.math.BigDecimal;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.User;

public class AccountService {
	public static String AUTH_TOKEN = "";
	private final String BASE_URL;
	public RestTemplate restTemplate = new RestTemplate();

	public AccountService(String url) {
		BASE_URL = url;
	}

	
	public BigDecimal getBalance(AuthenticatedUser user) {
		AUTH_TOKEN = user.getToken();
		BigDecimal balance = new BigDecimal("0.00");
		Account account = new Account();
		try {
			account = restTemplate
					.exchange(BASE_URL + "account/balance", HttpMethod.GET, makeAccountEntity(user), Account.class).getBody();
		} catch (RestClientResponseException ex) {
			// TODO account service exception class
			// throw new AccountServiceException(ex.getRawStatusCode() + " : " +
			// ex.getResponseBodyAsString());
		}
		return account.getAccountBalance();
	}

	private HttpEntity<AuthenticatedUser> makeAccountEntity(AuthenticatedUser user) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity<AuthenticatedUser> entity = new HttpEntity<>(user, headers);
		return entity;
	}

	private HttpEntity makeAuthEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	}

}
