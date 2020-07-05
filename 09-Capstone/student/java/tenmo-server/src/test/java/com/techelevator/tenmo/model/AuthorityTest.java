package com.techelevator.tenmo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class AuthorityTest {
	
	private Authority test;
	private String INITIAL = "test";

	@Before
	public void setup() {
		test = new Authority(INITIAL);
	}
	
	@Test
	public void name_returns_name() {
		String name = "name";
		test.setName(name);
		assertEquals(test.getName(), name);
	}
	
	@Test
	public void same_memory_address_means_equal() {
		Authority tester2 = test;
		assertEquals(test, tester2);
	}
	
	@Test
	public void not_same_class_means_not_equal() {
		String notAuthority = INITIAL;
		assertNotEquals(test, notAuthority);
	}
	
	@Test
	public void null_authority_not_equal() {
		Authority nullAuthority = null;
		assertNotEquals(test, nullAuthority);
	}
	
	@Test
	public void different_memory_slot_same_contents_still_equal() {
		Authority test2 = new Authority(INITIAL);
		assertEquals(test, test2);
	}
	
	@Test
	public void different_name_not_equal() {
		Authority test2 = new Authority(INITIAL + " 2");
		assertNotEquals(test, test2);
	}
	
	@Test
	public void to_String_prints_JSON_format() {
		String expected = "Authority{name=" + INITIAL + "}";
		assertEquals(expected,test.toString());
	}

}
