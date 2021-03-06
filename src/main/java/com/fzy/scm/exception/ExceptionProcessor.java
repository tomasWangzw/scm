package com.fzy.scm.exception;

import com.fzy.scm.entity.enums.RestCode;
import com.fzy.scm.entity.rest.Result;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;


/**
 * @program: ExceptionHandler
 * @description: 权限异常处理
 * @author: fzy
 * @date: 2019/03/17 08:54:32
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionProcessor {
    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result handleException(Exception e){
        // 打印堆栈信息
        log.error(ExceptionUtils.getStackTrace(e));
        return Result.failure(RestCode.SYS_ERROR_EXCEPTION);
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @returns
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validationBodyException(MethodArgumentNotValidException e){
        HashSet<String> set= Sets.newHashSet();
        e.getBindingResult().getAllErrors().forEach(p->set.add(p.getDefaultMessage()));
        return Result.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(),set);
    }

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler({SystemErrorException.class})
    @ResponseStatus(value = HttpStatus.OK)
    public Result baseException(BaseException e) {
        // 打印堆栈信息
        log.error(ExceptionUtils.getStackTrace(e));
        return Result.failure(e.getCode(),e.getMessage());
    }

}
