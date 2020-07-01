package com.techelevator.tenmo.services;

import java.math.BigDecimal;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


import com.techelevator.tenmo.models.User;



public class AccountService {
	public static String AUTH_TOKEN = "";
    private final String BASE_URL;
    public RestTemplate restTemplate = new RestTemplate();
    
    
    public AccountService(String url) {
        BASE_URL = url;
    }
   public BigDecimal getBalance(User user) {
	   BigDecimal account = null;
	   try {
		   account = restTemplate.exchange(BASE_URL + "/" + user.getId() + "/balance", HttpMethod.GET, makeAuthEntity(), BigDecimal.class).getBody();
	   }catch (RestClientResponseException ex) {
		   //TODO account service exception class
           //throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
       } return account;
   }

	
    private HttpEntity<User> makeAccountEntity(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return entity;
    }

   
    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }

	
}
    


