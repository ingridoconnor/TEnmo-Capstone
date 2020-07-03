package com.techelevator.tenmo.model;

// Server side

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	User test = new User();

	@Test
	public void id_returns_66() {
		long id = 66;
		test.setId(id);
		assertEquals(test.getId(), id);
	}
	@Test
	public void username_returns_vessel() {
		String username = "vessel";
		test.setUsername(username);
		assertEquals(test.getUsername(), username);
		
	}
	@Test
	public void password_returns_suregoodokay() {
		String password = "suregoodokay";
		test.setPassword(password);
		assertEquals(test.getPassword(), password);
		
	}
	@Test
	public void activated_returns_false() {
		test.setActivated(false);
		assertFalse(test.isActivated());
	}
	@Test
	public void activiated_returns_true() {
		test.setActivated(true);
		assertTrue(test.isActivated());
	}

}
