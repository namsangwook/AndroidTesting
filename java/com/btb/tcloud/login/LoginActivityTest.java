package com.btb.tcloud.login;

import android.test.ActivityInstrumentationTestCase2;

public class LoginActivityTest extends 
				ActivityInstrumentationTestCase2<LoginActivity> {

	private LoginActivity mActivity;

	public LoginActivityTest() {
		super("com.skt.tbagplus", LoginActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Global.setNormalStart(true);
        mActivity = getActivity();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCheckMdn() throws Exception {
		NetworkExceptionManager manager = mActivity.getCheckMdnExceptionManager();
		
		MemberExceptionController controller = mActivity.getLoginExceptionController();

		
		// MdnAlreadyUse
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_MDN_ALREAD_USE));
		FakeExceptionController fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_MDN_ALREAD_USE));
		assertTrue(fake.isProcessMdnAlreadyUseException);
		
		// UnsupportDevice
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_UAPS_NOT_SKT_USER));
		fake = new FakeExceptionController(mActivity);
		manager.handle(fake, ResultHeaderCode.RESPONSE_CODE_UAPS_NOT_SKT_USER);
		assertTrue(fake.isProcessUnsupportDeviceException);
		
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_USER_UNSUPPORTED_PRODUCT));
		fake = new FakeExceptionController(mActivity);
		manager.handle(fake, ResultHeaderCode.RESPONSE_CODE_USER_UNSUPPORTED_PRODUCT);
		assertTrue(fake.isProcessUnsupportDeviceException);
		
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_USER_UNSUPPORTED_DEVICE));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_USER_UNSUPPORTED_DEVICE));
		assertTrue(fake.isProcessUnsupportDeviceException);

		// IdpResponseCodeFail
		ResultHeaderCode code = ResultHeaderCode.IDP_RESPONSE_CODE_FAIL;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessIdpResponseCodeFailException);
		
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
		
		
		// AlreadyJoin
		code = ResultHeaderCode.RESPONSE_CODE_USER_FAIL_IDP_MEMBER;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessAlreadyJoinException);
		
	
		// UnsupportDevice
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_UAPS_NOT_SKT_USER));
		fake = new FakeExceptionController(mActivity);
		manager.handle(fake, ResultHeaderCode.RESPONSE_CODE_UAPS_NOT_SKT_USER);
		assertTrue(fake.isProcessUnsupportDeviceException);
		
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_USER_UNSUPPORTED_PRODUCT));
		fake = new FakeExceptionController(mActivity);
		manager.handle(fake, ResultHeaderCode.RESPONSE_CODE_USER_UNSUPPORTED_PRODUCT);
		assertTrue(fake.isProcessUnsupportDeviceException);
		
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_USER_UNSUPPORTED_DEVICE));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_USER_UNSUPPORTED_DEVICE));
		assertTrue(fake.isProcessUnsupportDeviceException);
		
		
		// AlreadyServiceJoin
		mActivity.setServiceUseInfo("test");
		code = ResultHeaderCode.RESPONSE_CODE_ALREADY_JOIN;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessAlreadyServiceJoinException);

		code = ResultHeaderCode.RESPONSE_CODE_USER_UNIQUE_ID;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessAlreadyServiceJoinException);
		
		
		// PhoneAuthKeyFail
		code = ResultHeaderCode.RESPONSE_CODE_PHONE_AUTH_KEY_FAIL;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessPhoneAuthKeyFailException);
		
		// DisapprovalID
		code = ResultHeaderCode.RESPONSE_CODE_DISAPPROVAL_ID;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessDisapprovalIDException);
		
	}
	
	
}
