package com.newchieve.component.exception;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;
import com.newchieve.component.entity.R;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BindException.class)
	public ResponseEntity<R> bindExceptionHandler(BindException e) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		Set<String> errors = fieldErrors.stream().map(o -> o.getDefaultMessage()).collect(Collectors.toSet());
		return ResponseEntity.badRequest().body(R.fail(errors));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<R> constraintViolationExceptionHandler(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
		Set<String> errors = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
		return ResponseEntity.badRequest().body(R.fail(errors));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<R> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		Set<String> errors = fieldErrors.stream().map(o -> o.getDefaultMessage()).collect(Collectors.toSet());
		return ResponseEntity.badRequest().body(R.fail(errors));
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<R> businessExceptionHandler(BusinessException e) {
		return ResponseEntity
				.status(e.getStatus())
				.body(R.fail(e.getMessage()));
	}

	// 处理以上处理不了的其他异常
	@ExceptionHandler(Exception.class)
	public ResponseEntity<R> exceptionHandler(Exception e) {
		log.error("exceptionHandler. e.msg: {}, e.stacktrace: {}", e.getMessage(), e.getStackTrace());
		e.printStackTrace();
		R<?> body = R.builder()
				.success(false)
				.errors(Set.of(e.getMessage()))
				.build();
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(body);
	}

}
