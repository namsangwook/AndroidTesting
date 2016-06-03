package com.btb.tcloud.login;

import android.test.ActivityInstrumentationTestCase2;

public class FindIdActivityTest extends 
				ActivityInstrumentationTestCase2<FindIdActivity> {

	private FindIdActivity mActivity;

	public FindIdActivityTest() {
		super("com.skt.tbagplus", FindIdActivity.class);
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
		NetworkExceptionManager manager = mActivity.getFindIdExceptionManager();
		MemberExceptionController controller = mActivity.getCustomMemberExceptionController();

		// NoContents
		ResultHeaderCode code = ResultHeaderCode.RESPONSE_CODE_NO_CONTENTS;
		assertTrue(manager.handle(controller, code));
		FakeExceptionController fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessNoContentsException);
	}
	
}
