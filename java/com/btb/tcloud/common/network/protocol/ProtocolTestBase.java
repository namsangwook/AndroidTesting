/**
 * 
 */
package com.btb.tcloud.common.network.protocol;

import java.net.URL;

import android.test.AndroidTestCase;

public abstract class ProtocolTestBase extends AndroidTestCase {
	private static final String LOG_TAG = ProtocolTestBase.class.getSimpleName();
	
	private static final String TEST_URI = "http://127.0.0.1:10000/fake.do";

	SocketService tcloudService = null;
	MockServerBase tcloudServer = null;
	
	public abstract String getUri();
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
//		AbsHttpRequest.init();		
//		RequestBase.init();

		URL url = new URL(TEST_URI);
		int port = url.getPort();
		tcloudServer = new MockServerBase(null);
		tcloudService = new SocketService(port, tcloudServer);
		Util.sleep(100);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		tcloudService.close();
	}
}
