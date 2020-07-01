package com.techelevator.tenmo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuthorityTest {
	
	Authority test = new Authority("test");

	@Test
	public void name_returns_name() {
		String name = "name";
		test.setName(name);
		assertEquals(test.getName(), name);
	}

}
