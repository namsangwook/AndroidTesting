/**
 * 
 */
package com.btb.test.support;

import java.util.Timer;
import java.util.TimerTask;

import junit.framework.Assert;
import android.test.AndroidTestCase;


// TestCase 는 위에서 아래로 순서대로 호출되지 않는다. 
public class AndroidTestCaseTemplate extends AndroidTestCase {
	private static final String LOG_TAG = AndroidTestCaseTemplate.class.getSimpleName();

	@Override
	protected void setUp() throws Exception {
		LogUtil.i(LOG_TAG, "setUp() called");
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		LogUtil.i(LOG_TAG, "tearDown() called");
		super.tearDown();
	}

	public void testTimer() throws Exception {
		LogUtil.i(LOG_TAG, "testTimer() called");
		final Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("timer called!!!");
				synchronized (AndroidTestCaseTemplate.this) {
					AndroidTestCaseTemplate.this.notifyAll();
				}
			}
		}, 1000);

		synchronized (this) {
			try {
				wait();
			}
			catch (final InterruptedException e) {
				e.printStackTrace();
				Assert.fail("timer interrupted!!!");
			}
		}

		Assert.assertTrue(true);
	}
}
