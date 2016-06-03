package com.btb.test.support;

import android.util.Log;

public class LogUtil {
	private static final String TEST_LOG_TAG = "TCLOUD_TEST";

	
	private static String makeVerboseLog(String msg) {
        Thread current = Thread.currentThread();
        long tid = current.getId();
        StackTraceElement[] stack = current.getStackTrace();
        String methodName = stack[4].getMethodName();
        // Prepend current thread ID and name of calling method to the message.
        String strVerbose = "[" + tid + "] [" + methodName + "] ";
	
		StringBuffer b = new StringBuffer();
		b.append(strVerbose);
		b.append("[");
		b.append(msg);
		b.append("]");
		
		return b.toString();
	}
		
	
	public static void v(String tag, String msg) {
		Log.v(TEST_LOG_TAG, makeVerboseLog(msg));
	}
	
	public static void d(String tag, String msg) {
		Log.d(TEST_LOG_TAG, makeVerboseLog(msg));
	}
	
	public static void i(String tag, String msg) {
		Log.i(TEST_LOG_TAG, makeVerboseLog(msg));
	}

	public static void w(String tag, String msg) {
		Log.w(TEST_LOG_TAG, makeVerboseLog(msg));
	}

	public static void e(String tag, String msg) {
		Log.e(TEST_LOG_TAG, makeVerboseLog(msg));
	}
}
