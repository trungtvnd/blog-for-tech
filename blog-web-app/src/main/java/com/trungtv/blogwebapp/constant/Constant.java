package com.trungtv.blogwebapp.constant;

public class Constant {
    private Constant() {
        //
    }

    public static class RedisConstant {
        public static String KEY_REGISTER = "KEY_REGISTER";
        public static String KEY_FORGOT_PASSWORD = "KEY_FORGOT_PASSWORD";
    }

    public static class RegularExpressionValidate {
        private RegularExpressionValidate() {
            //
        }

        private static final String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    }

}
