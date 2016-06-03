/**
 * 
 */
package com.btb.tcloud.common.network.protocol;

import java.net.URL;

import android.test.ActivityInstrumentationTestCase2;

public abstract class ProtocolTestBase2 extends 
				ActivityInstrumentationTestCase2<MainActivity> {
	private static final String LOG_TAG = ProtocolTestBase2.class.getSimpleName();
	private static final String TEST_URI = "http://127.0.0.1:10000/fake.do";

	protected MainActivity mActivity;

	public ProtocolTestBase2() {
		super("com.skt.tbagplus", MainActivity.class);
	}
	

	SocketService tcloudService = null;
	MockServerBase tcloudServer = null;
	
	public abstract String getUri();
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Global.setNormalStart(true);
//		mActivity = getActivity();
//		getInstrumentation().waitForIdleSync();
//		AbsHttpRequest.init();		
//		RequestBase.init(mActivity.getApplicationContext().getResources());

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
