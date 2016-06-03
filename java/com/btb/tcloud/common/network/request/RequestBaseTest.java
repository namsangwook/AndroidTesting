/**
 * 
 */
package com.btb.tcloud.common.network.request;

import android.test.AndroidTestCase;


// TestCase 는 위에서 아래로 순서대로 호출되지 않는다. 
public class RequestBaseTest extends AndroidTestCase {
	private static final String LOG_TAG = RequestBaseTest.class.getSimpleName();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}


	public void testRequestBaseCreate() throws Exception {
		RequestBase request = new MyRequestBase("testRequestType", MethodType.POST);
		assertNotNull(RequestBase.getRequestManager());

	}
	
	
	private static class MyRequestBase extends RequestBase {
		protected MyRequestBase(String requestType, MethodType methodType) {
			super(requestType, methodType);
		}
	}
}
