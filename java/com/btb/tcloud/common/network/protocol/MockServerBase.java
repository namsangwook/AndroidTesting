package com.btb.tcloud.common.network.protocol;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.btb.test.support.LogUtil;

public class MockServerBase implements SocketServer {
	private static final String LOG_TAG = MockServerBase.class.getSimpleName();
	private HttpResponseMaker responseMaker;

	public MockServerBase(HttpResponseMaker responseMaker) {
		this.responseMaker = responseMaker;
	}
	
	public void setResponseMaker(HttpResponseMaker responseMaker) {
		this.responseMaker = responseMaker;
	}
	
	@Override
	public void serve(Socket s) {
		try {
			InputStream inStream = new BufferedInputStream(s.getInputStream());
			OutputStream outStream = new BufferedOutputStream(s.getOutputStream());
			byte[] buffer = new byte[1024];
			int cnt = inStream.read(buffer);
			String request = new String(buffer, 0, cnt, "utf-8");
			byte[] response = responseMaker.getResponse().getBytes("utf-8");
//			byte[] response = responseMaker.getResponse().getBytes("utf-8");
			String test = new String(response, "utf-8");
//			String test = new String(response, "euc-kr");
			LogUtil.e(LOG_TAG, test);
			outStream.write(response);
			outStream.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}