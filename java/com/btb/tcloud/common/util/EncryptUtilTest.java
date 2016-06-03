package com.btb.tcloud.common.util;

import junit.framework.TestCase;

import com.btb.test.support.LogUtil;

public class EncryptUtilTest extends TestCase {

	private static final String LOT_TAG = EncryptUtilTest.class.getSimpleName();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testAesEncrypt() throws Exception {
		final String key = "TD15IG21EQG9M27i";
		String testString = "abcdef1234!@나가수";
		String encrypted = EncryptUtil.aesEncode(key.getBytes(), testString);
		LogUtil.e(LOT_TAG, "encrypted : " + encrypted);
		assertNotNull(encrypted);
		String decrypted = EncryptUtil.aesDecode(key.getBytes(), encrypted);
		assertEquals(testString, decrypted);
		LogUtil.e(LOT_TAG, "decrypted : " + decrypted);
	}

	public void testAes128Encrypt() throws Exception {
		final TimeStamp ts = new TimeStamp();
		ts.start();
		String testString = "abcdef1234!@나가수";
		String encrypted = EncryptUtil.aesEncode(testString);
		LogUtil.e(LOT_TAG, "encrypted : " + encrypted);
		assertNotNull(encrypted);
		assertNotSame(testString, encrypted);
		String decrypted = EncryptUtil.aesDecode(encrypted);
		assertEquals(testString, decrypted);
		LogUtil.e(LOT_TAG, "decrypted : " + decrypted);
		ts.stop();
		LogUtil.e(LOT_TAG, "Encrypt Time (" + ts.elapsedMilliSeconds() + ") ms");
	}

	public void testAesEncryptNullString() throws Exception {
		
		final String key = "TD15IG21EQG9M27i";
		String testString = "";
		String encrypted = EncryptUtil.aesEncode(key.getBytes(), testString);
		LogUtil.e(LOT_TAG, "encrypted : " + encrypted);
		assertNotNull(encrypted);
		String decrypted = EncryptUtil.aesDecode(key.getBytes(), encrypted);
		assertEquals(testString, decrypted);
		LogUtil.e(LOT_TAG, "decrypted : " + decrypted);
		
	}

	public void testAesEncryptSize() throws Exception {
		final String key = "TD15IG21EQG9M27i";
		String testString = "abcdef1234!@나가수";
		String encrypted = EncryptUtil.aesEncode(key.getBytes(), testString);
		assertNotNull(encrypted);

		float percentage = (float)encrypted.length() / (float)testString.length();
		LogUtil.e(LOT_TAG, "size increase : " + percentage * 100 + " %");
	}

//	public void testAesEncryptSpeed() throws Exception {
//		final String key = "TD15IG21EQG9M27i";
//		String testString = "abcdef1234!@나가수";
//		
//		final TimeStamp ts = new TimeStamp();
//		ts.start();
//		
//		for(int i= 0;i<1000 * 10;i++) {
//			EncryptUtil.aesEncode(key, testString);
//		}
//		ts.stop();
//		LogUtil.e(LOT_TAG, "Encrypt Time (" + ts.elapsedMilliSeconds() + ") ms");
//	}
//
//	public void testAesDecryptSpeed() throws Exception {
//		final String key = "TD15IG21EQG9M27i";
//		String testString = "abcdef1234!@나가수";
//		String encrypted = EncryptUtil.aesEncode(key, testString);
//		
//		final TimeStamp ts = new TimeStamp();
//		ts.start();
//		
//		for(int i= 0;i<1000 * 10;i++) {
//			EncryptUtil.aesDecode(key, encrypted);
//		}
//		ts.stop();
//		LogUtil.e(LOT_TAG, "Decrypt Time (" + ts.elapsedMilliSeconds() + ") ms");
//	}
	
}
