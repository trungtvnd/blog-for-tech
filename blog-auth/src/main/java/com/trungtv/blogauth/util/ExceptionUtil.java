package com.trungtv.blogauth.util;

public class ExceptionUtil {

    public static String getMessageError(String errorCode) {
        return  errorCode.split("-")[1];
    }

    public static int getCodeError(String errorCode) {
        return Integer.parseInt(errorCode.split("-")[0]);

    }
}
