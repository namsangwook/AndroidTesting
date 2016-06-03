package com.btb.tcloud.contact.model;

import android.test.AndroidTestCase;

public class ContactDuplicateConfirmDataTest extends AndroidTestCase {
	private static final String LOG_TAG = ContactDuplicateConfirmDataTest.class.getSimpleName();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSomething() throws Exception {
		ContactDuplicateConfirmData data = new ContactDuplicateConfirmData();
		
		assertEquals("checkable은 false 여야 한다", false, data.getCheckable());
	}


}
