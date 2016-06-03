package com.btb.tcloud.login;

import android.test.ActivityInstrumentationTestCase2;

public class PhoneAuthActivityTest extends 
				ActivityInstrumentationTestCase2<PhoneAuthActivity> {

	private PhoneAuthActivity mActivity;

	public PhoneAuthActivityTest() {
		super("com.skt.tbagplus", PhoneAuthActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Global.setNormalStart(true);
        mActivity = getActivity();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCertifyMdn() throws Exception {
		NetworkExceptionManager manager = mActivity.getCertifyMdnExceptionManager();
		MemberExceptionController controller = mActivity.getCertifyMdnExceptionController();

		// MdnAlreadyUse
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_MDN_ALREAD_USE));
		FakeExceptionController fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_MDN_ALREAD_USE));
		assertTrue(fake.isProcessMdnAlreadyUseException);
		
		
		// UnsupportDevice
		ResultHeaderCode code = ResultHeaderCode.RESPONSE_CODE_UAPS_NOT_SKT_USER;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessUnsupportDeviceException);
		
		code = ResultHeaderCode.RESPONSE_CODE_USER_UNSUPPORTED_PRODUCT;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessUnsupportDeviceException);

		code = ResultHeaderCode.RESPONSE_CODE_USER_UNSUPPORTED_DEVICE;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessUnsupportDeviceException);
		
		
		// PhoneAuthKeyFail
		code = ResultHeaderCode.RESPONSE_CODE_PHONE_AUTH_KEY_FAIL;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessPhoneAuthKeyFailException);
		
	}
}
