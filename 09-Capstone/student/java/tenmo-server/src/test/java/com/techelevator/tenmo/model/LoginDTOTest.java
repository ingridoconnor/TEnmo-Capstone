package com.techelevator.tenmo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginDTOTest {
	
	LoginDTO test = new LoginDTO();

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

}
