package com.techelevator.tenmo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.techelevator.tenmo.models.UserCredentials;

public class UserCredentialsTest {

	UserCredentials test = new UserCredentials("name", "password");


	@Test
	public void password_returns_password() {
		assertEquals(test.getPassword(), "password");
	}
	@Test
	public void username_returns_name() {
		assertEquals(test.getUsername(), "name");
	}
}