package com.techelevator.tenmo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

// Server side

import org.junit.Test;

public class UserTest {

	private User test;
	private static final long ID_NUMBER = (long)(Math.random() * 100);
	private static final String USERNAME = "Tester";
	private static final String PASSWORD = "Much secure";
	private static final String AUTHORITIES = "Such authorities";
	
	@Before
	public void setup() {
		test = new User();
	}

	
	@Test
	public void id_returns_66() {
		long id = 66;
		test.setId(id);
		assertEquals((long)id, (long)test.getId());
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
	
	@Test
	public void detailed_constructor_works_as_expected() {
		test.setId(ID_NUMBER);
		test.setUsername(USERNAME);
		test.setPassword(PASSWORD);
		test.setActivated(true);
		User actual = new User(ID_NUMBER, USERNAME, PASSWORD, AUTHORITIES);
		assertEquals(test, actual);
		test.setAuthorities(AUTHORITIES);
		assertNotEquals(test.hashCode(), actual.hashCode());
		actual.setAuthorities(AUTHORITIES);
		assertEquals(test.hashCode(), actual.hashCode());
	}

}
