/**
 * 
 */
package com.btb.tcloud.common.network.protocol;

import java.io.UnsupportedEncodingException;
import java.util.EventListener;

public class NonExistProtocolTest extends ProtocolTestBase {
	private static final String LOG_TAG = NonExistProtocolTest.class.getSimpleName();
	
	private static final String TEST_URI = "http://127.0.0.1:10000/noway/noway.do";

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
	
	public void testConnectToServer() throws Exception {
		ProtocolNoway protocol = new ProtocolNoway();
		tcloudServer.setResponseMaker(new NormalResponse());
		try {
			boolean result = protocol.request();
		} catch(Exception e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}

	private static class NormalResponse implements HttpResponseMaker {
		private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<result>\r\n"
			+ "<header>\r\n"
			+ "<code>200</code>\r\n"
			+ "<message><![CDATA[RESPONSE_CODE_OK]]></message>\r\n"
			+ "</header>\r\n"
			+ "<body>\r\n"
			+ "</body>\r\n"
			+ "</result>";
		
		@Override
		public String getResponse() {
			HttpHelper httpHelper = new HttpHelper();
			httpHelper.addHeader("Server", 				"Apache-Coyote/1.1");
			httpHelper.addHeader("tcd-sessionid", 		"C54DB576E3CCF2439BED1928E0DAFC27.tchsSvr11");
			httpHelper.addHeader("tcd-authkey", 		"xnqcwntrilsmnpuupptf");
			httpHelper.addHeader("Content-Type", 		"text/xml;charset=UTF-8");
			httpHelper.addHeader("Content-Language",	"ko-KR");
			httpHelper.addHeader("Content-Length", 		String.valueOf(getContentLength()));
			httpHelper.addHeader("Date", 				"Thu, 07 Jul 2011 02:55:48 GMT");
			String header = httpHelper.makeHttpHeader();
			String content = header + getData();
			return content;
		}

		protected String getData() {
			return XML;
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
	
	
}

class ProtocolNoway extends BaseProtocol {

	public enum Type implements RequestBase.ParamEnumBase {
		XML;
		@Override
		public int getParamIndex() {
			return 0;
		}

		@Override
		public int getFixedValueIndex() {
			return ordinal();
		}

		@Override
		public String getValue() {
			return null;
		}
	}

	// --------------------------------------------------------------------------------------------
	// 응답 리스너, 요청 도구, 생성자

	ProtocolNoway() {
		super(ProtocolType.NORMAL, "noway/noway.do");
	}

	// --------------------------------------------------------------------------------------------
	// 파라미터 설정
	private void setParamType(Type type) {
		mRequest.setParam(type);
	}

	// ============================================================================================
	// 응답 객체
	private static class ResponseGetVersion extends ResponseBase {
		public static class InfoGetVersion extends ResponseBase.InfoBase {
		}

		public ResponseGetVersion(final HttpResponseWrapper httpResponseWrapper) {
			super(httpResponseWrapper, InfoGetVersion.class);
		}

		@Override
		public InfoGetVersion getInfoObject() {
			return (InfoGetVersion) getSuperInfoObject();
		}
	}

	// --------------------------------------------------------------------------------------------
	// Block, NonBlock 요청
	public boolean request() {
		setParamType(Type.XML);
		mResponse = new ResponseGetVersion(mRequest.requestBlock());
		return mResponse.isSuccess();
	}

	public void request(final EventListener eventListener) {
		setParamType(Type.XML);
		mRequest.requestNonBlock(new ResponseByNonBlockListener() {
			@Override
			public void onResponse(final HttpResponseWrapper httpResponseWrapper) {
				mResponse = new ResponseGetVersion(httpResponseWrapper);
				eventListener.onResult(mResponse);
			}
		});
	}

}
