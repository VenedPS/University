package ua.com.foxminded.university.exception;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleNoSuchElementException(RuntimeException exception, WebRequest request) {
		logger.error("Failed to find the requested element", exception);
		return handleExceptionInternal(
				exception, 
				exception.getMessage(), 
				new HttpHeaders(), 
				HttpStatus.NOT_FOUND, 
				request);
    }
	
	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleIncorrectResultSizeDataAccessException(RuntimeException exception, WebRequest request) {
		logger.error("Database connection error", exception);
		return handleExceptionInternal(
				exception, 
				exception.getMessage(), 
				new HttpHeaders(), 
				HttpStatus.BAD_REQUEST, 
				request);
	}

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		logger.error("Validation faild", exception);
		List<String> details = new ArrayList<>();
		for (ObjectError error : exception.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		return handleExceptionInternal(
				exception, 
				details, 
				new HttpHeaders(), 
				HttpStatus.BAD_REQUEST, 
				request);
	}
	
	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handlePSQLException(Exception exception, WebRequest request) {
		logger.error("Database connection error", exception);
		return handleExceptionInternal(
				exception, 
				"Database connection error!", 
				new HttpHeaders(), 
				HttpStatus.INTERNAL_SERVER_ERROR, 
				request);
	}
	
}
