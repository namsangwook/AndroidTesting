package com.btb.tcloud.common.util;

import android.test.AndroidTestCase;

import com.btb.test.support.LogUtil;


public class UtilTest extends AndroidTestCase {
	private static final String LOG_TAG = UtilTest.class.getSimpleName();
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testPixelToDp() throws Exception {
		float pixel = 480;
		float dp = Util.pxToDp(getContext(), pixel);
		assertEquals((float)320, dp);
	}

	public void testDpToPixel() throws Exception {
		float dp = 320;
		float pixel = Util.dpToPx(getContext(), dp);
		assertEquals((float)480, pixel);
	}
	
	public static String getFileSizeStr(final long total) {
		String str = null;

		if (total >= 1024) {
			final long kbSize = total / 1024;
			if (kbSize < 1000) { // KB 단위 표시 
				str = ((kbSize > 0) ? kbSize : 1) + "KB";
			} else {
				final int mbInt = (int) (kbSize / 1024.0f * 100.0f);
				float mbSize = mbInt / 100.0f;
				if (mbSize < 1000.0f) {	// MB 단위 표시
					if (mbSize > 10) {
						mbSize = ((int) (mbSize * 10.0f)) / 10.0f;
					}
					if (mbSize > 100) {
						str = (int)mbSize + "MB";
					} 
					else {
						if(mbSize%1.0 > 0) {
							str = mbSize + "MB";
						}
						else {
							str = (int)mbSize + "MB";
						}
					}
				} else {  // GB 단위 표시
					final int gbInt = (int) (kbSize / 1024.0f / 1024.0f * 100.0f);
					float gbSize = gbInt / 100.0f;
					if (gbSize > 10) {
						gbSize = ((int) (gbSize * 10.0f)) / 10.0f;
					}
					if (gbSize > 100) {
						str = (int)gbSize + "GB";
					} 
					else {
						if(gbSize%1.0 > 0) {
							str = gbSize + "GB";
						}
						else {
							str = (int)gbSize + "GB";
						}
					}
				}
			}
		}
		else
		{
			return str = "0KB";
		}
		return str;
	}	
	public void testGetFileSizeStr() throws Exception {
		long size = 1023;
		String fileSize = Util.getFileSizeStr(size);
		assertEquals("0KB", fileSize);
		
		size = 1024;
		fileSize = Util.getFileSizeStr(size);
		assertEquals("1KB", fileSize);
		assertTrue(!"0KB".equals(fileSize));

		size = 1024 * 540;
		fileSize = Util.getFileSizeStr(size);
		assertEquals("540KB", fileSize);

		size = 1024 * 999;
		fileSize = Util.getFileSizeStr(size);
		assertEquals("999KB", fileSize);

		size = 1024 * 1024;
		fileSize = Util.getFileSizeStr(size);
		assertEquals("1MB", fileSize);

		size = 1024 * 1024 * 2;
		fileSize = Util.getFileSizeStr(size);
		assertEquals("2MB", fileSize);

		size = 1024 * 1024 * 2 + 1;
		fileSize = Util.getFileSizeStr(size);
		assertEquals("2MB", fileSize);

		size = 1024 * 1024 * 2 + 512 * 1024;
		fileSize = Util.getFileSizeStr(size);
		assertEquals("2.5MB", fileSize);

		size = 1024 * 1024 * 2 + 500 * 1024;
		fileSize = Util.getFileSizeStr(size);
		LogUtil.e(LOG_TAG, "filesize : " + fileSize);
		assertEquals("2.48MB", fileSize);
		
		size = 1024 * 1024 * 10;
		fileSize = Util.getFileSizeStr(size);
		assertEquals("10MB", fileSize);

		size = 1024 * 1024 * 100;
		fileSize = Util.getFileSizeStr(size);
		LogUtil.e(LOG_TAG, "filesize : " + fileSize);
		assertEquals("100MB", fileSize);
		
		size = 1024 * 1024 * 999;
		fileSize = Util.getFileSizeStr(size);
		assertEquals("999MB", fileSize);

		size = 1024 * 1024 * 1024;
		fileSize = Util.getFileSizeStr(size);
		LogUtil.e(LOG_TAG, "filesize : " + fileSize);
		assertEquals("1GB", fileSize);

		size = 1024 * 1024 * 1024 + 512 * 1024 * 1024;
		fileSize = Util.getFileSizeStr(size);
		LogUtil.e(LOG_TAG, "filesize : " + fileSize);
		assertEquals("1.5GB", fileSize);
		
		size = 1024 * 1024 * 1024 * 10L;
		fileSize = Util.getFileSizeStr(size);
		LogUtil.e(LOG_TAG, "filesize : " + fileSize);
		assertEquals("10GB", fileSize);
		
	}
	
	
}
