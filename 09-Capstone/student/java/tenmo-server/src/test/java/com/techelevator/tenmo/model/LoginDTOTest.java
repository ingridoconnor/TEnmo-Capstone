package com.techelevator.tenmo.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LoginDTOTest {
	
	private LoginDTO test;
	private static final String USERNAME = "GrumpyCat";
	private static final String PASSWORD = "I don't want no stinkin cheeseburger";
	
	@Before
	public void setup() {
		test = new LoginDTO();
		test.setUsername(USERNAME);
		test.setPassword(PASSWORD);
	}

	@Test
	public void username_returns_XxWalter2013xX() {
		String username = "XxWalter2013xX";
		test.setUsername(username);
		assertEquals(test.getUsername(), username);
	}
	
	@Test
	public void password_returns_iheartsocks123(){
		String password = "iheartsocks123";
		test.setPassword(password);
		assertEquals(test.getPassword(), password);
	}
	
	@Test
	public void to_String_returns_JSON_format() {
		String expected = "LoginDTO{username='" + USERNAME + "', password='" + PASSWORD + "'}";
		assertEquals(expected, test.toString());
	}

}
