package com.github.jomardev25.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.jomardev25.dto.ErrorResponseDto;
import com.github.jomardev25.utils.StringUtils;

import io.jsonwebtoken.MalformedJwtException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorResponseDto> handleAPIException(APIException ex, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(new Date(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorResponseDto, ex.getStatus());
    }

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex, WebRequest webRequest){
		 ErrorResponseDto errorResponseDto = new ErrorResponseDto(new Date(), ex.getMessage(), webRequest.getDescription(false));
	     return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String field = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			errors.put(StringUtils.camelToSnake(field), message);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
}
