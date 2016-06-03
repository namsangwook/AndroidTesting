/**
 * 
 */
package com.btb.test.support;

import junit.framework.Test;
import junit.framework.TestSuite;
import android.test.AndroidTestCase;

import com.btb.tcloud.contact.model.ContactDuplicateConfirmDataTest;

public class KIKIMTests extends AndroidTestCase {
	private static final String LOG_TAG = KIKIMTests.class.getSimpleName();

	public static Test suite() {
		final TestSuite suite = new TestSuite();
		suite.addTestSuite(ContactDuplicateConfirmDataTest.class);
		return suite;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
