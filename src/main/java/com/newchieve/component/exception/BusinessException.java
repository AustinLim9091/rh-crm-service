package com.newchieve.component.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.http.HttpStatus;

/**
 * @ClassName BusinessException
 * @Description 基础运行时异常类
 * @Author michael
 * @Date 2023/6/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    /**
     * 异常代码
     */
    private HttpStatus status;

    public BusinessException(HttpStatus status) {
        super(status.getReasonPhrase());
        this.status = status;
    }

    public BusinessException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public BusinessException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

}
