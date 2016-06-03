package com.btb.tcloud.login;

import android.test.ActivityInstrumentationTestCase2;

public class RegisterIdpMemberActivityTest extends 
				ActivityInstrumentationTestCase2<RegisterIdpMemberActivity> {

	private RegisterIdpMemberActivity mActivity;

	public RegisterIdpMemberActivityTest() {
		super("com.skt.tbagplus", RegisterIdpMemberActivity.class);
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
		NetworkExceptionManager manager = mActivity.getRegisterMemberExceptionManager();
		MemberExceptionController controller = mActivity.getCustomMemberExceptionController();

		// AlreadyServiceJoin
		assertTrue(manager.handle(controller
					, ResultHeaderCode.RESPONSE_CODE_ALREADY_JOIN));

		FakeExceptionController fake = new FakeExceptionController(mActivity);
		manager.handle(fake, ResultHeaderCode.RESPONSE_CODE_ALREADY_JOIN);
		assertTrue(fake.isProcessAlreadyServiceJoinException);
		
		assertTrue(manager.handle(controller
					, ResultHeaderCode.RESPONSE_CODE_USER_UNIQUE_ID));

		fake = new FakeExceptionController(mActivity);
		manager.handle(fake, ResultHeaderCode.RESPONSE_CODE_USER_UNIQUE_ID);
		assertTrue(fake.isProcessAlreadyServiceJoinException);
		
		// EmailAlreadyUse
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_EMAIL_ALREAD_USE));

		fake = new FakeExceptionController(mActivity);
		manager.handle(fake, ResultHeaderCode.RESPONSE_CODE_EMAIL_ALREAD_USE);
		assertTrue(fake.isProcessEmailAlreadyUseException);
		
		// NgcpFail
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_USER_FAIL_NGCP));

		fake = new FakeExceptionController(mActivity);
		manager.handle(fake, ResultHeaderCode.RESPONSE_CODE_USER_FAIL_NGCP);
		assertTrue(fake.isProcessNgcpFailException);
		
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
		
		// MdnAlreadyUse
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_MDN_ALREAD_USE));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_MDN_ALREAD_USE));
		assertTrue(fake.isProcessMdnAlreadyUseException);
		
		// AlreadyJoin
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_USER_FAIL_IDP_MEMBER));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_USER_FAIL_IDP_MEMBER));
		assertTrue(fake.isProcessAlreadyJoinException);
		
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_USER_UNIQUE_EMAIL));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_USER_UNIQUE_EMAIL));
		assertTrue(fake.isProcessAlreadyJoinException);
		
		// StorageCreateFail
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_USER_STORAGE_CREATE_FAIL));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_USER_STORAGE_CREATE_FAIL));
		assertTrue(fake.isProcessStorageCreateFailException);
		
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_USER_REG_MEMBER_FAIL));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_USER_REG_MEMBER_FAIL));
		assertTrue(fake.isProcessStorageCreateFailException);
		
		// DisapprovalID
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_DISAPPROVAL_ID));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_DISAPPROVAL_ID));
		assertTrue(fake.isProcessDisapprovalIDException);
	}
	
}
