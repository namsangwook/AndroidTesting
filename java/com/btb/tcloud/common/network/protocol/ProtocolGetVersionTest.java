/**
 * 
 */
package com.btb.tcloud.common.network.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import android.content.Context;

public class ProtocolGetVersionTest extends ProtocolTestBase2 {
	private static final String LOG_TAG = ProtocolGetVersionTest.class.getSimpleName();
	
	private static final String TEST_URI = "http://127.0.0.1:10000/app/getVersion.do";
//	static Context mContext;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
//		mContext = getContext();
//		Global.setNormalStart(true);
//		mContext = mActivity.getApplicationContext();
//		extractAsset();
	}

	@Override
	protected void tearDown() throws Exception {
//		removeAsset();
		super.tearDown();
	}

	@Override
	public String getUri() {
		return TEST_URI;
	}	
	
	public void testConnectToServer() throws Exception {
		ProtocolGetVersion protocol = ProtocolFactory.makeCheckVersionProtocol();
		tcloudServer.setResponseMaker(new NormalResponse(getInstrumentation().getContext()));
		protocol.setUri(TEST_URI);
		boolean result = protocol.request();
		assertTrue(result);
	}

	public void testXmlError() throws Exception {
		ProtocolGetVersion protocol = ProtocolFactory.makeCheckVersionProtocol();
		tcloudServer.setResponseMaker(new WrongXmlResponse(getInstrumentation().getContext()));
		protocol.setUri(TEST_URI);
		boolean result = protocol.request();
		assertFalse(result);
		assertEquals(ResultHeaderCode.XML_PARSING_ERROR.getCode()
							, protocol.getResponse().getResponseCode());
		
		Util.processServerError(protocol);
	}
	
//	public void testAssets() throws Exception {
//        InputStream in 
//        	= getInstrumentation().getContext()
//        							.getAssets()
//        							.open("protocol_get_version_normal.xml", AssetManager.ACCESS_BUFFER);
//        assertNotNull(in);
//        int len;
//        StringBuffer buffer = new StringBuffer();
//        byte[] buf = new byte[2048];
//        while ((len = in.read(buf)) >= 0 ) {
//        	LogUtil.e(LOG_TAG, buf.toString());
//        	buffer.append(buf.toString());
//        }
//        LogUtil.e(LOG_TAG, buffer.toString());
//        in.close();
//	}
//	
//    private static final String SDCARD_BINDINGS_TEST_HTML = "/sdcard/tcloud/test.xml";
//	
//	protected void extractAsset() throws Exception {
//        InputStream in 
//        	= getInstrumentation().getContext()
//        							.getAssets()
//        							.open("protocol_get_version_normal.xml");
//        assertNotNull(in);
//        OutputStream out = new FileOutputStream(SDCARD_BINDINGS_TEST_HTML);
//
//        byte[] buf = new byte[2048];
//        int len;
//
//        while ((len = in.read(buf)) >= 0 ) {
//            out.write(buf, 0, len);
//        }
//        out.close();
//        in.close();
//	}
//	
//    protected void removeAsset(){
//        File fileToDelete = new File(SDCARD_BINDINGS_TEST_HTML);
//        fileToDelete.delete();
//    }
	


	private static class NormalResponse implements HttpResponseMaker {
		private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<result>\r\n"
			+ "<header>\r\n"
			+ "<code>200</code>\r\n"
			+ "<message>RESPONSE_CODE_OK</message>\r\n"
			+ "<version>1.4</version>\r\n"
			+ "<url><![CDATA[http://vm.btbsolution.co.kr/tcloud/real_server/Tcloud.apk]]></url>\r\n"
			+ "</header>\r\n"
			+ "</result>";		
		
		Context context;
		
		public NormalResponse(Context context) {
			this.context = context;
		}

		@Override
		public String getResponse() {
			HttpHelper httpHelper = new HttpHelper();
			httpHelper.addHeader("Date", 				"Thu, 07 Jul 2011 02:55:48 GMT");
			httpHelper.addHeader("Server", 				"Apache");
			httpHelper.addHeader("Set-Cookie",			"JSESSIONID=C54DB576E3CCF2439BED1928E0DAFC27.tchsSvr11; Path=/");
			httpHelper.addHeader("Content-Language",	"ko-KR");
			httpHelper.addHeader("Content-Length", 		String.valueOf(getContentLength()));
			httpHelper.addHeader("Keep-Alive", 			"timeout=10, max=2000");
			httpHelper.addHeader("Connection", 			"Keep-Alive");
			httpHelper.addHeader("Content-Type", 		"text/xml;charset=UTF-8");
			String header = httpHelper.makeHttpHeader();
			String content = header + getData();
			return content;
		}

		public String getResponseFromAsserts(String xmlPath) {
	        InputStream in;
	        StringBuffer buffer = new StringBuffer();
			try {
				in = context.getAssets().open(xmlPath);
				if(in == null) {
					throw new RuntimeException("asset open failed : " + xmlPath);
				}
		        int len;
		        byte[] buf = new byte[2048];
		        while ((len = in.read(buf)) >= 0 ) {
		        	buffer.append(new String(buf, "utf-8").trim());
		        }
//		        LogUtil.e(LOG_TAG, buffer.toString());
//		        LogUtil.e(LOG_TAG, "length : " + buffer.length());
		        in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
	        return buffer.toString();
		}
		
		protected String getData() {
//			return XML;
			return getResponseFromAsserts("protocol_get_version_normal.xml");
		}
		
		protected int getContentLength() {
			int length = 0;
			try {
				length = getData().getBytes("utf-8").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return length;
		}
	}

	private static class WrongXmlResponse extends NormalResponse {
		public WrongXmlResponse(Context context) {
			super(context);
		}

//		private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
//			+ "<result>\r\n"
//			+ "<header>\r\n"
//			+ "<code>200</code>\r\n"
//			+ "<message>RESPONSE_CODE_OK</message>\r\n"
//			+ "<version>1.4</version>\r\n"
//			+ "<url><![CDATA[http://vm.btbsolution.co.kr/tcloud/real_server/Tcloud.apk]]></url>\r\n"
//			+ "</header>";

		@Override
		protected String getData() {
//			return XML;
			return getResponseFromAsserts("protocol_get_version_wrong_response.xml");
		}
		
		
		
	}

}
