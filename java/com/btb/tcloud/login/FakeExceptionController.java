package com.btb.tcloud.login;

import android.app.Activity;

public class FakeExceptionController extends MemberExceptionController {
	public boolean isProcessPasswdInvalidErrorException = false;
	public boolean isProcessAlreadyServiceJoinException = false;
	public boolean isProcessEmailAlreadyUseException = false;
	public boolean isProcessNgcpFailException = false;
	public boolean isProcessUnsupportDeviceException = false;
	public boolean isProcessMdnAlreadyUseException = false;
	public boolean isProcessAlreadyJoinException = false;
	public boolean isProcessBadRequestDataException = false;
	public boolean isProcessResponseUndefineException = false;
	public boolean isProcessNotExistIdException = false;
	public boolean isProcessStorageCreateFailException = false;
	public boolean isProcessDisapprovalIDException = false;
	public boolean isProcessPhoneAuthKeyFailException = false;
	public boolean isProcessIdpResponseCodeFailException = false;
	public boolean isProcessNoContentsException = false;
	public boolean isProcessUserFailIdpWrongEmailException = false;
	public boolean isProcessIdPasswdFailException = false;
	public boolean isProcessWaitJoinApproveException = false;

	public FakeExceptionController(Activity activity) {
		super(activity);
	}
	
	@Override
	protected boolean processPasswdInvalidError(ResultHeaderCode code) {
		isProcessPasswdInvalidErrorException  = true;
		switch (code) {			
		case RESPONSE_CODE_PASSWORD_LENGTH_ERROR:  //비밀번호 길이 제약
		case RESPONSE_CODE_PASSWORD_ONLY_NUMBER_ERROR: //비밀번호가 숫자로만 구성
		case RESPONSE_CODE_PASSWORD_ONLY_CHAR_ERROR: //비밀번호가 문자로만 구성
		case RESPONSE_CODE_PASSWORD_ID_ERROR: //비밀번호와 ID가 같음
		case RESPONSE_CODE_PASSWORD_ONLY_SPECIAL_CHAR_ERROR: // 비밀번호가 특수문자로만 구성됨
		case RESPONSE_CODE_WRONG_PASSWD:  //비밀번호가 불일치 합니다
			
			return true;
		}
		return false;
	}
	
	@Override
	protected boolean processAlreadyServiceJoinException(ResultHeaderCode code) {
		isProcessAlreadyServiceJoinException  = true;
		return true;
	}
	
	@Override
	public boolean processEmailAlreadyUseException() {
		isProcessEmailAlreadyUseException  = true;
		return true;
	}

	@Override
	public boolean processNgcpFailException() {
		isProcessNgcpFailException  = true;
		return true;
	}
	
	@Override
	public boolean processUnsupportDeviceException() {
		isProcessUnsupportDeviceException  = true;
		return true;
	}
	
	@Override
	public boolean processMdnAlreadyUseException() {
		isProcessMdnAlreadyUseException  = true;
		return true;
	}
	
	@Override
	public boolean processAlreadyJoinException() {
		isProcessAlreadyJoinException = true;
		return true;
	}
	
	@Override
	public boolean processBadRequestDataException() {
		isProcessBadRequestDataException = true;
		return true;
	}
	
	@Override
	public boolean processResponseUndefineException() {
		isProcessResponseUndefineException = true;
		return true;
	}
	
	@Override
	public boolean processNotExistIdException() {
		isProcessNotExistIdException = true;
		return true;
	}
	
	@Override
	public boolean processStorageCreateFailException(ResultHeaderCode code) {
		isProcessStorageCreateFailException = true;
		return true;
	}
	
	@Override
	public boolean processDisapprovalIDException() {
		isProcessDisapprovalIDException = true;
		return true;
	}
	@Override
	protected boolean processPhoneAuthKeyFailException() {
		isProcessPhoneAuthKeyFailException = true;
		return true;
	}
	
	protected boolean processIdpResponseCodeFailException() {
		isProcessIdpResponseCodeFailException = true;
		return true;
	}
	
	protected boolean processNoContentsException() {
		isProcessNoContentsException = true;
		return true;
	}
	
	protected boolean processUserFailIdpWrongEmailException() {
		isProcessUserFailIdpWrongEmailException = true;
		return true;
	}
	
	protected boolean processIdPasswdFailException(ResultHeaderCode code) {
		isProcessIdPasswdFailException = true;
		return true;
	}
	
	protected boolean processWaitJoinApproveException() {
		isProcessWaitJoinApproveException = true;
		return true;
	}
}
