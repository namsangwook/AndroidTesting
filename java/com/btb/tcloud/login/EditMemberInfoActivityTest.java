package com.btb.tcloud.login;

import android.test.ActivityInstrumentationTestCase2;

public class EditMemberInfoActivityTest extends
		ActivityInstrumentationTestCase2<EditMemberInfoActivity> {

	private EditMemberInfoActivity mActivity;
//	private AccountManager mManager;
	
	public EditMemberInfoActivityTest() {
		super("com.skt.tbagplus", EditMemberInfoActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Global.setNormalStart(true);
        mActivity = getActivity();
//        getInstrumentation().waitForIdleSync();
//		mManager = new AccountManager();        
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	// 비밀번호 변경 
	public void testMemberModifyUserPw() throws Exception {
		NetworkExceptionManager manager = mActivity.getEditMemberInfoExceptionManager();
		manager.addExceptionHandler(new NetworkExceptionHandlers.NewMemberPasswordError());
		
		
		MemberExceptionController controller = mActivity.getCustomMemberExceptionController();

		// NewMemberPasswordError
		assertTrue(manager.handle(controller
									, ResultHeaderCode.RESPONSE_CODE_PASSWORD_LENGTH_ERROR));
		FakeExceptionController fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
							, ResultHeaderCode.RESPONSE_CODE_PASSWORD_LENGTH_ERROR));
		assertTrue(fake.isProcessPasswdInvalidErrorException);

		
		assertTrue(manager.handle(controller
									, ResultHeaderCode.RESPONSE_CODE_PASSWORD_ONLY_NUMBER_ERROR));
		fake = new FakeExceptionController(mActivity);
		assertTrue(manager.handle(fake
							, ResultHeaderCode.RESPONSE_CODE_PASSWORD_ONLY_NUMBER_ERROR));
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

		
		assertFalse(manager.handle(controller
									, ResultHeaderCode.RESPONSE_CODE_NOT_EXIST_ID));
		fake = new FakeExceptionController(mActivity);
		assertFalse(manager.handle(fake
							, ResultHeaderCode.RESPONSE_CODE_NOT_EXIST_ID));
		assertFalse(fake.isProcessPasswdInvalidErrorException);
	}
}
