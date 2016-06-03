package com.btb.test;

import junit.framework.TestCase;

public class CallByReferenceTest extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testStringCallByReference() throws Exception {
		String var = "origin";
		stringCallByReference(var);
		assertEquals("origin", var);
	}
	
	private void stringCallByReference(String ref) {
		ref = "change";
	}

	public void testIntegerCallByReference() throws Exception {
		int var = 0;
		integerCallByReference(var);
		assertEquals(0, var);
	}
	
	private void integerCallByReference(int ref) {
		ref = 1;
	}
	
}
