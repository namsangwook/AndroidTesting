/**
 * 
 */
package com.btb.test.support;

import junit.framework.Test;
import junit.framework.TestSuite;
import android.test.AndroidTestCase;

import com.btb.tcloud.common.network.protocol.ProtocolGetVersionTest;
import com.btb.tcloud.common.network.protocol.ProtocolLoginTest;
import com.btb.tcloud.common.network.protocol.SessionIdTest;

public class NeoxTests extends AndroidTestCase {
	private static final String LOG_TAG = NeoxTests.class.getSimpleName();

	public static Test suite() {
		final TestSuite suite = new TestSuite();
		suite.addTestSuite(ProtocolLoginTest.class);
		suite.addTestSuite(ProtocolGetVersionTest.class);
		suite.addTestSuite(SessionIdTest.class);
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
