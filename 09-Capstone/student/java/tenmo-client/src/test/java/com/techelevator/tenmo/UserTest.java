package com.techelevator.tenmo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.User;

// Client
public class UserTest {
	
	private User tester;

	@Before
	public void setUp() throws Exception {
		tester = new User();
	}

	@Test
	public void id_returns_66() {
		Integer id = 66;
		tester.setId(id);
		assertEquals(id, tester.getId());
	}
	
	@Test
	public void username_returns_vessel() {
		String username = "vessel";
		tester.setUsername(username);
		assertEquals(tester.getUsername(), username);
	}
	
	// Test equals
	// same exact memory slot
	@Test
	public void same_memory_address_means_equal() {
		User tester2 = tester;
		assertEquals(tester, tester2);
	}
	
	// not the same class
	@Test
	public void not_same_class_means_not_equal() {
		Account notUser = new Account();
		assertNotEquals(tester, notUser);
	}
	
	@Test
	public void null_user_not_equal() {
		User nullUser = null;
		assertNotEquals(tester, nullUser);
	}
	
	// holds same info
	@Test
	public void different_memory_same_contents_still_equal() {
		int testId = 10;
		String testUsername = "test";
		tester.setId(testId);
		tester.setUsername(testUsername);
		User anotherTester = new User();
		anotherTester.setId(testId);
		anotherTester.setUsername(testUsername);
		assertNotEquals(tester.hashCode(), anotherTester.hashCode());
		assertEquals(tester, anotherTester);
	}
	
	// Test toString
	@Test
	public void toString_prints_out_JSON_format() {
		int testId = 10;
		String testUsername = "test";
		tester.setId(testId);
		tester.setUsername(testUsername);
		String expected = "User{id=" + testId + ", username='" + testUsername + "'}";
		assertEquals(expected, tester.toString());
	}

}
