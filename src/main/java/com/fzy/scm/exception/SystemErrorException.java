package com.fzy.scm.exception;

import com.fzy.scm.entity.enums.RestCode;
import lombok.Data;

import java.util.Optional;

/**
 * @program: SystemErrorException
 * @description: 异常内部异常
 * @author: fzy
 * @date: 2019/03/17 09:01:29
 **/
@Data
public class SystemErrorException extends BaseException {

    public SystemErrorException(String message) {
        super(Optional.ofNullable(message).orElse(RestCode.SYS_ERROR_EXCEPTION.getMessage()));
        this.code = RestCode.SYS_ERROR_EXCEPTION.getCode();
    }
}
