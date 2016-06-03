package com.skt.tbagplus;

import android.test.ActivityInstrumentationTestCase2;

import com.btb.tcloud.login.FakeExceptionController;

public class TcloudIntroTest extends 
				ActivityInstrumentationTestCase2<TcloudIntro> {

	private TcloudIntro mActivity;

	public TcloudIntroTest() {
		super("com.skt.tbagplus", TcloudIntro.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
        mActivity = getActivity();
		Global.setNormalStart(true);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public void testLoginException() throws Exception {
		NetworkExceptionManager manager = mActivity.getLoginExceptionManager();
		
		MemberExceptionController controller = mActivity.getLoginExceptionController();

		// IdPasswdFail
		ResultHeaderCode code = ResultHeaderCode.RESPONSE_CODE_NOT_EXIST_ID;
		assertTrue(manager.handle(controller, code));
		FakeExceptionController fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessIdPasswdFailException);
		

		code = ResultHeaderCode.RESPONSE_CODE_WRONG_PASSWD;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessIdPasswdFailException);

		// WaitJoinApprove
		code = ResultHeaderCode.RESPONSE_CODE_WAIT_JOIN_APPROVE;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessWaitJoinApproveException);

	}
	
	
}
