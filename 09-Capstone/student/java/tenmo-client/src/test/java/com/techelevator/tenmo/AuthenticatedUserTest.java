package com.techelevator.tenmo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.User;

public class AuthenticatedUserTest {

	AuthenticatedUser test = new AuthenticatedUser();
	
	@Test
	public void token_returns_string() {
		String token = "string";
		test.setToken(token);
		assertEquals(test.getToken(), token);
	}
	@Test
	public void user_returns_user() {
		User user = new User();
		test.setUser(user);
		assertEquals(test.getUser(), user);
	}

}
