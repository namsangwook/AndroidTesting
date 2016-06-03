/*
 * com.btb.tbag.nonsync.NeoHttpConnector
 *
 * Created on 2010. 3. 31.
 * 
 * Copyright (c) 2002-2010 BTBSolution Co., Ltd. All Rights Reserved.
 */

package com.btb.tcloud.common.network.protocol;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.btb.test.support.LogUtil;

public class HttpHelper {
	/** Android Debugging Message 를 위한 Tag명 설정 */
	public final static String LOG_TAG = HttpHelper.class.getSimpleName();

	private HashMap<String, String> headerMap_ = new HashMap<String, String>(); 
	private HashMap<String, String> paramMap_ = new HashMap<String, String>(); 
	
	public boolean addParameter(String name, String value, boolean doURLEncode) {
		if (doURLEncode) {
			try {
				value = URLEncoder.encode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				LogUtil.e(LOG_TAG, e.getMessage());
			}
		}
		paramMap_.put(name, value);
		LogUtil.d(LOG_TAG, "Parameter value that was set : " +paramMap_.get(name));
		return true;
	}
	
	public boolean removeParameter(String name) {
		return (paramMap_.remove(name)!=null);
	}

	private String getParam() {
		String ret = "";
		Set<String> set = paramMap_.keySet();
		Iterator<String> keys = set.iterator();
		String strAmp = "";
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = paramMap_.get(key);
			ret += strAmp + key + "=" + value;
			strAmp = "&";
		}
		return ret;
	}

	public void addHeader(String name, String value) {
		headerMap_.put(name, value);
	}
	
	private String getHeader() {
		String ret = "";
		Set<String> set = headerMap_.keySet();
		Iterator<String> keys = set.iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = headerMap_.get(key);
			ret += key + ":" + value + "\r\n";
		}
		return ret;
	}
	
	public String  makeHttpHeader(String topHeader) {
    	String strSendData = new String();
    	strSendData += topHeader + "\r\n";	
    	strSendData += getHeader();
    	strSendData += "\r\n";	
    	return strSendData;
	}
	
	public String makeHttpHeader() {
		return makeHttpHeader("HTTP/1.1 200 OK");
	}
}
