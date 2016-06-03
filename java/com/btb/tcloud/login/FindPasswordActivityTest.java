package com.btb.tcloud.login;

import android.test.ActivityInstrumentationTestCase2;

public class FindPasswordActivityTest extends 
				ActivityInstrumentationTestCase2<FindPasswordActivity> {

	private FindPasswordActivity mActivity;

	public FindPasswordActivityTest() {
		super("com.skt.tbagplus", FindPasswordActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
        mActivity = getActivity();
		Global.setNormalStart(true);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRegisterMember() throws Exception {
		NetworkExceptionManager manager = mActivity.getFindPasswdExceptionManager();
		MemberExceptionController controller = mActivity.getCustomMemberExceptionController();

		// NoContents
		ResultHeaderCode code = ResultHeaderCode.RESPONSE_CODE_NO_CONTENTS;
		assertTrue(manager.handle(controller, code));
		FakeExceptionController fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessNoContentsException);
		
		// UserFailIdpWrongEmail
		code = ResultHeaderCode.RESPONSE_CODE_USER_FAIL_IDP_2201_WRONG_EMAIL;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessUserFailIdpWrongEmailException);		
	}
	
}
