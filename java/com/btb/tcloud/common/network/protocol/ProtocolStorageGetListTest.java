/**
 * 
 */
package com.btb.tcloud.common.network.protocol;

import java.io.UnsupportedEncodingException;

import com.btb.test.support.LogUtil;

public class ProtocolStorageGetListTest extends ProtocolTestBase {
	private static final String LOG_TAG = ProtocolStorageGetListTest.class.getSimpleName();
	
	private static final String TEST_URI = "http://127.0.0.1:10000/storage/getList.do";

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
		ProtocolStorageGetList protocol = ProtocolFactory.makeStorageGetListProtocol();
		tcloudServer.setResponseMaker(new NormalResponse());
		protocol.setUri(TEST_URI);
		boolean result = protocol.request("test_path", OrderValue.REGIST, OrderType.DESC, ImgType.URL);
		assertEquals(2, protocol.getTotalCnt());
		assertEquals(2, protocol.getResultCnt());
		assertTrue(result);
	}

	public void testHttpResponseError() throws Exception {
		ProtocolStorageGetList protocol = ProtocolFactory.makeStorageGetListProtocol();
		tcloudServer.setResponseMaker(new WrongHttpResponse());
		protocol.setUri(TEST_URI);
		boolean result = protocol.request("test_path", OrderValue.REGIST, OrderType.DESC, ImgType.URL);
		assertFalse(result);
		LogUtil.e(LOG_TAG, protocol.getResponse().getResponseDesc());
		assertEquals(ResultHeaderCode.HTTP_COMM_ERROR.getCode()
							, protocol.getResponse().getResponseCode());
		Util.processServerError(protocol);
	}
	
	public void testXmlError() throws Exception {
		ProtocolStorageGetList protocol = ProtocolFactory.makeStorageGetListProtocol();
		tcloudServer.setResponseMaker(new WrongXmlResponse());
		protocol.setUri(TEST_URI);
		boolean result = protocol.request("test_path", OrderValue.REGIST, OrderType.DESC, ImgType.URL);
		assertFalse(result);
		LogUtil.e(LOG_TAG, protocol.getResponse().getResponseDesc());

		assertEquals(ResultHeaderCode.XML_PARSING_ERROR.getCode()
							, protocol.getResponse().getResponseCode());
		Util.processServerError(protocol);
	}

	public void testInvalidDataError() throws Exception {
		ProtocolStorageGetList protocol = ProtocolFactory.makeStorageGetListProtocol();
		tcloudServer.setResponseMaker(new InvalidDataResponse());
		protocol.setUri(TEST_URI);
		boolean result = protocol.request("test_path", OrderValue.REGIST, OrderType.DESC, ImgType.URL);
		assertFalse(result);
		LogUtil.e(LOG_TAG, protocol.getResponse().getResponseDesc());

		assertEquals(ResultHeaderCode.XML_PARSING_ERROR.getCode()
							, protocol.getResponse().getResponseCode());
		
		Util.processServerError(protocol);
	}
	
	private static class NormalResponse implements HttpResponseMaker {
		private static final String XML = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<result>\r\n"
				+ "<header>\r\n"
					+ "<code>200</code>\r\n"
					+ "<message>RESPONSE_CODE_OK</message>\r\n"
					+ "<resultTotal>2</resultTotal>\r\n"
					+ "<resultCount>2</resultCount>\r\n"
					+ "<resultPage>1</resultPage>\r\n"
				+ "</header>\r\n"
				+ "<body>\r\n"
					+ "<ListObjectsResponse>\r\n"
						+ "<APP_VER>\r\n"
							+ "<ObjectID>4d9b0298a512d68404e0b94364b84804e27a671ac9b2</ObjectID>\r\n"
							+ "<ObjectPath>/S003/내 폰 사진 보관함/</ObjectPath>\r\n"
							+ "<ObjectType>directory</ObjectType>\r\n"
						+ "</APP_VER>\r\n"
						+ "<APP_VER>\r\n"
							+ "<ObjectID>4d9b0298a512d64304d9b1b6e6438804e295c920f12c</ObjectID>\r\n"
							+ "<ObjectPath>/S003/1M_IMAGE(4).bmp</ObjectPath>\r\n"
							+ "<ObjectType>regular</ObjectType>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>uid</Name>\r\n"
								+ "<Value>46810</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>thumbnailYn</Name>\r\n"
								+ "<Value>Y</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>atime</Name>\r\n"
								+ "<Value>2011-07-22T11:19:12Z</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>mtime</Name>\r\n"
								+ "<Value>2011-07-22T11:19:10Z</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>nlink</Name>\r\n"
								+ "<Value>0</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>policyname</Name>\r\n"
								+ "<Value>default</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>type</Name>\r\n"
								+ "<Value>regular</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>ctime</Name>\r\n"
								+ "<Value>2011-07-22T11:19:12Z</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>size</Name>\r\n"
								+ "<Value>1164390</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>thumbnail</Name>\r\n"
								+ "<Value>http://img.tbagplus.com/thumbnail/2011/07/22/4d/2c/4d9b0298a512d64304d9b1b6e6438804e295c920f12c.jpg\r\n"
								+ "</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>pid_4d9b0298a412d64304d9b1b6e4e04904e27a671a5584</Name>\r\n"
								+ "<Value></Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>itime</Name>\r\n"
								+ "<Value>2011-07-22T11:18:40Z</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>gid</Name>\r\n"
								+ "<Value>apache</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>uid_46810</Name>\r\n"
								+ "<Value>46810</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>objectid</Name>\r\n"
								+ "<Value>4d9b0298a512d64304d9b1b6e6438804e295c920f12c</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>objname</Name>\r\n"
								+ "<Value>1M_IMAGE(4).bmp</Value>\r\n"
							+ "</Metadata>\r\n"
						+ "</APP_VER>\r\n"
					+ "</ListObjectsResponse>\r\n"
				+ "</body>\r\n"
			+ "</result>";
		
		@Override
		public String getResponse() {
			HttpHelper httpHelper = new HttpHelper();
			httpHelper.addHeader("Date", 				"Mon, 25 Jul 2011 03:01:38 GMT");
			httpHelper.addHeader("Server", 				"Apache");
			httpHelper.addHeader("Content-Language",	"ko-KR");
			httpHelper.addHeader("Content-Length", 		String.valueOf(getContentLength()));
			httpHelper.addHeader("Keep-Alive", 			"timeout=10, max=2000");
			httpHelper.addHeader("Connection", 			"Keep-Alive");
			httpHelper.addHeader("Content-Type", 		"text/xml;charset=UTF-8");
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

	private static class WrongXmlResponse extends NormalResponse {
		private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<result>\r\n"
				+ "<header>\r\n"
					+ "<code>200</code>\r\n"
					+ "<message>RESPONSE_CODE_OK</message>\r\n"
					+ "<resultTotal>2</resultTotal>\r\n"
					+ "<resultCount>2</resultCount>\r\n"
					+ "<resultPage>1</resultPage>\r\n"
				+ "</header>\r\n"
				+ "<body>\r\n"
					+ "<ListObjectsResponse>\r\n"
						+ "<APP_VER>\r\n"
							+ "<ObjectID>4d9b0298a512d68404e0b94364b84804e27a671ac9b2</ObjectID>\r\n"
							+ "<ObjectPath>/S003/내 폰 사진 보관함/</ObjectPath>\r\n"
							+ "<ObjectType>directory</ObjectType>\r\n"
						+ "</APP_VER>\r\n"
						+ "<APP_VER>\r\n"
							+ "<ObjectID>4d9b0298a512d64304d9b1b6e6438804e295c920f12c</ObjectID>\r\n"
							+ "<ObjectPath>/S003/1M_IMAGE(4).bmp</ObjectPath>\r\n"
							+ "<ObjectType>regular</ObjectType>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>uid</Name>\r\n"
								+ "<Value>46810</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>thumbnailYn</Name>\r\n"
								+ "<Value>Y</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>atime</Name>\r\n"
								+ "<Value>2011-07-22T11:19:12Z</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>mtime</Name>\r\n"
								+ "<Value>2011-07-22T11:19:10Z</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>nlink</Name>\r\n"
								+ "<Value>0</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>policyname</Name>\r\n"
								+ "<Value>default</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>type</Name>\r\n"
								+ "<Value>regular</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>ctime</Name>\r\n"
								+ "<Value>2011-07-22T11:19:12Z</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>size</Name>\r\n"
								+ "<Value>1164390</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>thumbnail</Name>\r\n"
								+ "<Value>http://img.tbagplus.com/thumbnail/2011/07/22/4d/2c/4d9b0298a512d64304d9b1b6e6438804e295c920f12c.jpg\r\n"
								+ "</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>pid_4d9b0298a412d64304d9b1b6e4e04904e27a671a5584</Name>\r\n"
								+ "<Value></Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>itime</Name>\r\n"
								+ "<Value>2011-07-22T11:18:40Z</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>gid</Name>\r\n"
								+ "<Value>apache</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>uid_46810</Name>\r\n"
								+ "<Value>46810</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>objectid</Name>\r\n"
								+ "<Value>4d9b0298a512d64304d9b1b6e6438804e295c920f12c</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>objname</Name>\r\n"
								+ "<Value>1M_IMAGE(4).bmp</Value>\r\n"
							+ "</Metadata>\r\n"
						+ "</APP_VER>\r\n"
					+ "</ListObjectsResponse>\r\n"
				+ "</body>\r\n";

		@Override
		protected String getData() {
			return XML;
		}
	}

	private static class InvalidDataResponse extends NormalResponse {
		private static final String XML = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<result>\r\n"
				+ "<header>\r\n"
					+ "<code>200</code>\r\n"
					+ "<message>RESPONSE_CODE_OK</message>\r\n"
					+ "<resultTotal>에러다.</resultTotal>\r\n"
					+ "<resultCount>2</resultCount>\r\n"
					+ "<resultPage>1</resultPage>\r\n"
				+ "</header>\r\n"
				+ "<body>\r\n"
					+ "<ListObjectsResponse>\r\n"
						+ "<APP_VER>\r\n"
							+ "<ObjectID>4d9b0298a512d68404e0b94364b84804e27a671ac9b2</ObjectID>\r\n"
							+ "<ObjectPath>/S003/내 폰 사진 보관함/</ObjectPath>\r\n"
							+ "<ObjectType>directory</ObjectType>\r\n"
						+ "</APP_VER>\r\n"
						+ "<APP_VER>\r\n"
							+ "<ObjectID>4d9b0298a512d64304d9b1b6e6438804e295c920f12c</ObjectID>\r\n"
							+ "<ObjectPath>/S003/1M_IMAGE(4).bmp</ObjectPath>\r\n"
							+ "<ObjectType>regular</ObjectType>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>uid</Name>\r\n"
								+ "<Value>46810</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>thumbnailYn</Name>\r\n"
								+ "<Value>Y</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>atime</Name>\r\n"
								+ "<Value>2011-07-22T11:19:12Z</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>mtime</Name>\r\n"
								+ "<Value>2011-07-22T11:19:10Z</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>nlink</Name>\r\n"
								+ "<Value>0</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>policyname</Name>\r\n"
								+ "<Value>default</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>type</Name>\r\n"
								+ "<Value>regular</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>ctime</Name>\r\n"
								+ "<Value>2011-07-22T11:19:12Z</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>size</Name>\r\n"
								+ "<Value>1164390</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>thumbnail</Name>\r\n"
								+ "<Value>http://img.tbagplus.com/thumbnail/2011/07/22/4d/2c/4d9b0298a512d64304d9b1b6e6438804e295c920f12c.jpg\r\n"
								+ "</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>pid_4d9b0298a412d64304d9b1b6e4e04904e27a671a5584</Name>\r\n"
								+ "<Value></Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>itime</Name>\r\n"
								+ "<Value>2011-07-22T11:18:40Z</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>gid</Name>\r\n"
								+ "<Value>apache</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>uid_46810</Name>\r\n"
								+ "<Value>46810</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>objectid</Name>\r\n"
								+ "<Value>4d9b0298a512d64304d9b1b6e6438804e295c920f12c</Value>\r\n"
							+ "</Metadata>\r\n"
							+ "<Metadata>\r\n"
								+ "<Name>objname</Name>\r\n"
								+ "<Value>1M_IMAGE(4).bmp</Value>\r\n"
							+ "</Metadata>\r\n"
						+ "</APP_VER>\r\n"
					+ "</ListObjectsResponse>\r\n"
				+ "</body>\r\n"
			+ "</result>";

		@Override
		protected String getData() {
			return XML;
		}
	}
	
	private static class WrongHttpResponse extends NormalResponse {
		@Override
		public String getResponse() {
			HttpHelper httpHelper = new HttpHelper();
			httpHelper.addHeader("Server", 				"Apache-Coyote/1.1");
			httpHelper.addHeader("tcd-sessionid", 		"C54DB576E3CCF2439BED1928E0DAFC27.tchsSvr11");
			httpHelper.addHeader("tcd-authkey", 		"xnqcwntrilsmnpuupptf");
			httpHelper.addHeader("Content-Type", 		"text/xml;charset=UTF-8");
			httpHelper.addHeader("Content-Language",	"ko-KR");
			httpHelper.addHeader("Content-Length", 		String.valueOf(getData().length()));
			httpHelper.addHeader("Date", 				"Thu, 07 Jul 2011 02:55:48 GMT");
			String header = httpHelper.makeHttpHeader("HTTP/1.1 404 FILE NOT FOUND");
			String content = header + getData();
			return content;
		}
		
		@Override
		protected String getData() {
			return "Not Found. wow";
		}
	}
}
