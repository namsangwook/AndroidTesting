/**
 * 
 */
package com.btb.tcloud.common.network.protocol;


public class SessionIdTest extends ProtocolTestBase {
	private static final String LOG_TAG = SessionIdTest.class.getSimpleName();
	
	private static final String TEST_URI = "http://127.0.0.1:10000/login/login.do";
	private static final String SESSION_ID = "C54DB576E3CCF2439BED1928E0DAFC27.tchsSvr11";
	private static final String AUTH_KEY = "xnqcwntrilsmnpuupptf";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Global.setNormalStart(true);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Override
	public String getUri() {
		return TEST_URI;
	}
	
	public void testSessionKeyIsKept() throws Exception {
		ProtocolLogin protocol = ProtocolFactory.makeLoginProtocol();
		tcloudServer.setResponseMaker(new NormalResponse());
		protocol.setUri(TEST_URI);
		boolean result = protocol.request("test", "test1234"
										, Util.getClientVersion(MainApplication.getContext()));
		assertTrue(result);

		ProtocolGetVersion protocolGetVersion = ProtocolFactory.makeCheckVersionProtocol();
		
		RequestBase request = protocolGetVersion.getRequest();
		assertTrue(request instanceof RequestNormal);
		
//		RequestNormal requestNormal = (RequestNormal)request;
		assertEquals(SESSION_ID, RequestNormal.getSessionId());  
		assertEquals(AUTH_KEY, RequestNormal.getAuthkey());  
	
	}

	private static class NormalResponse implements HttpResponseMaker {
		private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<result>\r\n"
			+ "<header>\r\n"
			+ "<code>200</code>\r\n"
			+ "<message><![CDATA[RESPONSE_CODE_OK]]></message>\r\n"
			+ "</header>\r\n"
			+ "<body>\r\n"
			+ "<memberType><![CDATA[2]]></memberType>\r\n"
			+ "<modDt><![CDATA[20110512133327]]></modDt>\r\n"
			+ "<regDt><![CDATA[20110422225457]]></regDt>\r\n"
			+ "<loginFailureCount><![CDATA[]]>\r\n"
			+ "</loginFailureCount>\r\n"
			+ "<memNo><![CDATA[2616]]></memNo>\r\n"
			+ "<mdn><![CDATA[01020950209]]></mdn>\r\n"
			+ "<emailCertStat><![CDATA[Y]]></emailCertStat>\r\n"
			+ "<drmMemCtsTermPassYn><![CDATA[N]]></drmMemCtsTermPassYn>\r\n"
			+ "<drmMemAddTermPassYn><![CDATA[N]]></drmMemAddTermPassYn>\r\n"
			+ "<ssStatus><![CDATA[1]]></ssStatus>\r\n"
			+ "<ssResult><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><response><result><command>SMTSY_AUTH</command><code>0</code><errorMsg>SUCCESS</errorMsg></result><body><sessionKey>2425671336901303</sessionKey><userAuthKey></userAuthKey><cpnsIp>220.103.219.140</cpnsIp><cpnsPort>8190</cpnsPort><memInfo><mobileNo></mobileNo></memInfo></body></response>]]></ssResult>\r\n"
			+ "<spList><![CDATA[]]>\r\n"
			+ "</spList>\r\n"
			+ "<firstLoginYn><![CDATA[N]]></firstLoginYn>\r\n"
			+ "<newClauseYn><![CDATA[Y]]></newClauseYn>\r\n"
			+ "</body>\r\n"
			+ "</result>";
		
		@Override
		public String getResponse() {
			HttpHelper httpHelper = new HttpHelper();
			httpHelper.addHeader("Server", 				"Apache-Coyote/1.1");
			httpHelper.addHeader("tcd-sessionid", 		SESSION_ID);
			httpHelper.addHeader("tcd-authkey", 		AUTH_KEY);
			httpHelper.addHeader("Content-Type", 		"text/xml;charset=UTF-8");
			httpHelper.addHeader("Content-Language",	"ko-KR");
			httpHelper.addHeader("Content-Length", 		String.valueOf(getData().length()));
			httpHelper.addHeader("Date", 				"Thu, 07 Jul 2011 02:55:48 GMT");
			String header = httpHelper.makeHttpHeader();
			String content = header + getData();
			return content;
		}

		protected String getData() {
			return XML;
		}
	}
}
