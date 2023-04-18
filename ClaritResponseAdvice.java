package com.clarit.hs.service.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Common Exception handler for all type of service layer exceptions
 * 
 * Created by mnachiappan on Jan 8, 2023.
 *
 */
@ControllerAdvice
@RestController
public class ClaritResponseAdvice extends ResponseEntityExceptionHandler {

	public static final Logger logger = LoggerFactory.getLogger(ClaritResponseAdvice.class);
	@ExceptionHandler({ItemNotFoundException.class})
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(ItemNotFoundException ex, WebRequest request) {
		logger.error("not found - {}",ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		logger.error("un identified User - {}",ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler({UnAuthorizedException.class})
	public final ResponseEntity<ErrorDetails> handleUnauthorizedException(UnAuthorizedException ex, WebRequest request) {
		logger.error(" Un Authorized User - {}",ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler({ForbiddenException.class})
	public final ResponseEntity<ErrorDetails> handleForbiddenException(ForbiddenException ex, WebRequest request) {
		logger.error(" details will be correctly - {}",ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}


	/*public ResponseEntity<Object>  handleMethodArgumentNotValid (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String FieldName = ((FieldError) error).getField();
			String Message = error.getDefaultMessage();
			errors.put(FieldName, Message);

		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}*/

}