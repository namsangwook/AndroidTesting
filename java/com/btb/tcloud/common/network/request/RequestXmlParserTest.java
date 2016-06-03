/**
 * 
 */
package com.btb.tcloud.common.network.request;

import android.test.AndroidTestCase;


// TestCase 는 위에서 아래로 순서대로 호출되지 않는다. 
public class RequestXmlParserTest extends AndroidTestCase {
	private static final String LOG_TAG = RequestXmlParserTest.class.getSimpleName();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}


	public void testRequestXmlParserCreate() throws Exception {
		final RequestXmlParser requestXmlParser = new RequestXmlParser();
		requestXmlParser.init(getContext().getResources().openRawResource(R.raw.tcloud_protocol));
		assertNotNull(requestXmlParser.getServerInfo());
		assertNotNull(requestXmlParser.getRequestInfos());
	}
}
