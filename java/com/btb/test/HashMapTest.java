package com.btb.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class HashMapTest extends TestCase {

	public HashMapTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDifferentValueAdd() throws Exception {
		Map<String, String> stringMap = new HashMap<String, String>();
		stringMap.put("1", "test1");
		stringMap.put("2", "test2");
		assertEquals(2, stringMap.size());
	}

	public void testSameValueAdd() throws Exception {
		Map<String, String> stringMap = new HashMap<String, String>();
		stringMap.put("1", "test1");
		stringMap.put("1", "test2");
		assertEquals(1, stringMap.size());
	}
	
}
