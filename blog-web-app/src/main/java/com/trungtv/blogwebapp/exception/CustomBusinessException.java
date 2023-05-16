package com.trungtv.blogwebapp.exception;



import com.trungtv.blogwebapp.util.ExceptionUtil;
import com.trungtv.blogwebapp.util.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomBusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    private int code;

    public CustomBusinessException(String errorCode, String... args) {
        this.code = ExceptionUtil.getCodeError(errorCode);
        this.message = MessageUtils.getMessage(ExceptionUtil.getMessageError(errorCode));
    }

    public CustomBusinessException(String errorCode) {
        this.code = ExceptionUtil.getCodeError(errorCode);
        this.message = MessageUtils.getMessage(ExceptionUtil.getMessageError(errorCode));
    }


}
