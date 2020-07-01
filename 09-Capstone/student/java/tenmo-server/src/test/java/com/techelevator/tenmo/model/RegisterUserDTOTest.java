package com.techelevator.tenmo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class RegisterUserDTOTest {
	
	RegisterUserDTO test = new RegisterUserDTO();

	@Test
	public void username_returns_bootleggreg94() {
		String username = "bootleggreg94";
		test.setUsername(username);
		assertEquals(test.getUsername(), username);
	}
	@Test
	public void password_returns_turtles() {
		String password = "turtles";
		test.setPassword(password);
		assertEquals(test.getPassword(), password);
	}

}
