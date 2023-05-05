package com.trungtv.blogauth.constant;

public class ConstantErrorCode {
    private ConstantErrorCode() {
        //
    }

    public static class ErrorCode {
        private ErrorCode() {
            //
        }

        public static final String ENTITY_NOT_FOUND = "4001-not.found.entity.by.id";
        public static final String LOGIN_ERROR = "4002-login.error";
    }

    public static class ValidateErrorCode {
        private ValidateErrorCode() {
            //
        }

        public static final String EMAIL_MUST_NOT_BE_NULL = "4101-email.must.not.be.null";
        public static final String USERNAME_MUST_NOT_BE_NULL = "4102-username.must.not.be.null";
        public static final String PASSWORD_MUST_NOT_BE_NULL = "4103-password.must.not.be.null";
        public static final String PASSWORD_WRONG_FORMAT = "4104-password.wrong.format";
        public static final String OTP_WRONG = "4105-otp.wrong.or.expired";
        public static final String OTP_NOT_EXISTED = "4106-otp.does.not.existed";

        public static final String VALIDATION_ERROR = "6001-error.validation";
    }
}
