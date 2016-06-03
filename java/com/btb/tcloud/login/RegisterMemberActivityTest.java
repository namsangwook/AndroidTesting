package com.btb.tcloud.login;

import android.test.ActivityInstrumentationTestCase2;

public class RegisterMemberActivityTest extends 
				ActivityInstrumentationTestCase2<RegisterMemberActivity> {

	private RegisterMemberActivity mActivity;

	public RegisterMemberActivityTest() {
		super("com.skt.tbagplus", RegisterMemberActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
        mActivity = getActivity();
		Global.setNormalStart(true);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCheckDuplicatedId() throws Exception {
		NetworkExceptionManager manager = mActivity.getCheckDuplicatedIdExceptionManager();
		MemberExceptionController controller = mActivity.getCustomMemberExceptionController();
		
		// AlreadyJoin
		ResultHeaderCode code = ResultHeaderCode.RESPONSE_CODE_USER_FAIL_IDP_MEMBER;
		assertTrue(manager.handle(controller, code));
		FakeExceptionController fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessAlreadyJoinException);

		code = ResultHeaderCode.RESPONSE_CODE_USER_UNIQUE_EMAIL;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessAlreadyJoinException);
		
		// AlreadyServiceJoin
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

		// DisapprovalID
		code = ResultHeaderCode.RESPONSE_CODE_DISAPPROVAL_ID;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessDisapprovalIDException);
	}
	
	public void testRegisterMember() throws Exception {
		NetworkExceptionManager manager = mActivity.getRegisterMemberExceptionManager();
		MemberExceptionController controller = mActivity.getCustomMemberExceptionController();
		
		// AlreadyJoin
		ResultHeaderCode code = ResultHeaderCode.RESPONSE_CODE_USER_FAIL_IDP_MEMBER;
		assertTrue(manager.handle(controller, code));
		FakeExceptionController fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessAlreadyJoinException);

		code = ResultHeaderCode.RESPONSE_CODE_USER_UNIQUE_EMAIL;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessAlreadyJoinException);
		
		// AlreadyServiceJoin
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

		// DisapprovalID
		code = ResultHeaderCode.RESPONSE_CODE_DISAPPROVAL_ID;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessDisapprovalIDException);
		
		
		// UnsupportDevice
		code = ResultHeaderCode.RESPONSE_CODE_UAPS_NOT_SKT_USER;
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
		
		
		// NewMemberPasswordError
		code = ResultHeaderCode.RESPONSE_CODE_PASSWORD_LENGTH_ERROR;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessPasswdInvalidErrorException);
		
		code = ResultHeaderCode.RESPONSE_CODE_PASSWORD_ONLY_NUMBER_ERROR;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessPasswdInvalidErrorException);
		
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_PASSWORD_ONLY_CHAR_ERROR));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_PASSWORD_ONLY_CHAR_ERROR));
		assertTrue(fake.isProcessPasswdInvalidErrorException);
		
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_PASSWORD_ID_ERROR));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_PASSWORD_ID_ERROR));
		assertTrue(fake.isProcessPasswdInvalidErrorException);
		
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_PASSWORD_ONLY_SPECIAL_CHAR_ERROR));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_PASSWORD_ONLY_SPECIAL_CHAR_ERROR));
		assertTrue(fake.isProcessPasswdInvalidErrorException);
		
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_WRONG_PASSWD));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_WRONG_PASSWD));
		assertTrue(fake.isProcessPasswdInvalidErrorException);
		
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
		
		
		// PhoneAuthKeyFail
		code = ResultHeaderCode.RESPONSE_CODE_PHONE_AUTH_KEY_FAIL;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake, code));
		assertTrue(fake.isProcessPhoneAuthKeyFailException);
		
		
		// EmailAlreadyUse
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_EMAIL_ALREAD_USE));

		fake = new FakeExceptionController(mActivity);
		manager.handle(fake, ResultHeaderCode.RESPONSE_CODE_EMAIL_ALREAD_USE);
		assertTrue(fake.isProcessEmailAlreadyUseException);
		
		// NgcpFail
		code = ResultHeaderCode.RESPONSE_CODE_USER_FAIL_NGCP;
		assertTrue(manager.handle(controller, code));
		fake = new FakeExceptionController(mActivity);
		manager.handle(fake, code);
		assertTrue(fake.isProcessNgcpFailException);
		
		
		// MdnAlreadyUse
		assertTrue(manager.handle(controller
				, ResultHeaderCode.RESPONSE_CODE_MDN_ALREAD_USE));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
				, ResultHeaderCode.RESPONSE_CODE_MDN_ALREAD_USE));
		assertTrue(fake.isProcessMdnAlreadyUseException);
	}
}
