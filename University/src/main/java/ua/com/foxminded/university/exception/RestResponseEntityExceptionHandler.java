package ua.com.foxminded.university.exception;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	
	@ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(RuntimeException exception, WebRequest request) {
		logger.error("Failed to find the requested element", exception);
		String bodyOfResponse = exception.getMessage();
		return handleExceptionInternal(
				exception, 
				bodyOfResponse, 
				new HttpHeaders(), 
				HttpStatus.NOT_FOUND, 
				request);
    }
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Object> handleMethodArgumentNotValidException(Exception exception, WebRequest request) {
////	public ResponseEntity<Object> handleMethodArgumentNotValidException(
////			MethodArgumentNotValidException exception,
////			HttpHeaders headers, 
////			HttpStatus status, 
////			WebRequest request) {
//		
////		logger.error("Failed to find the requested element", exception);
////		String bodyOfResponse = exception.getMessage();
//		return handleExceptionInternal(
//				exception, 
//				"", 
//				new HttpHeaders(), 
//				HttpStatus.BAD_REQUEST, 
//				request);
//	}

//	private ResponseEntity<Object> buildErrorResponse(
//			Exception exception, 
//			String message, 
//			HttpStatus httpStatus,
//			WebRequest request) {
//		
//		ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
//		if (printStackTrace && isTraceOn(request)) {
//			errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
//		}
//		return ResponseEntity.status(httpStatus).body(errorResponse);
//	}
}