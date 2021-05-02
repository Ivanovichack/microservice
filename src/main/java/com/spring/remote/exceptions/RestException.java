package com.spring.remote.exceptions;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.tags.form.ErrorsTag;

import com.spring.remote.model.ApiError;


@ControllerAdvice
public class RestException extends ExceptionHandlerExceptionResolver{
	
	//cuando el tipo HTTP no es correcto
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<ApiError> methodNotSupportedException(HttpRequestMethodNotSupportedException ex){
		ApiError error = new ApiError();
		error.setMensaje("HTTP " + ex.getMethod() + " NO soportado. Debe ser " +ex.getSupportedMethods()[0] + " para el endpoint solicitado");		
		error.setDetalles(Arrays.asList(""+ex));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	//cuando el tipo de datos enviado no es aceptado
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	public ResponseEntity<ApiError> notAcceptableMediaTypeHandler(HttpMediaTypeNotSupportedException ex) {
		ApiError error = new ApiError();
		error.setMensaje(ex.getMessage());
		error.setDetalles(Arrays.asList(""+ex));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	//cuando no existe el header requerido
	@ExceptionHandler({ MissingRequestHeaderException.class })
	public ResponseEntity<ApiError> MissingRequestHeaderException(MissingRequestHeaderException ex) {
		ApiError error = new ApiError();
		error.setMensaje("No existe " + ex.getParameter().getParameterName() + " en el header");		
		error.setDetalles(Arrays.asList(""+ex));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	//cuando el body no es correcto
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiError> handleAllOtherErrors(HttpMessageNotReadableException ex) {
		ApiError error = new ApiError();		
		error.setMensaje(ex.getMessage());
		error.setDetalles(Arrays.asList(""+ex));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	//cuando @Valid tiene errores en validacion del body
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		ApiError error = new ApiError();
		error.setMensaje(ex.getMessage());
		error.setDetalles(Arrays.asList(""+ex));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	//cuando valores del body estan vacios o no existen
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiError> ConstraintViolationException(ConstraintViolationException ex) {
		ApiError error = new ApiError();
		error.setMensaje("Body no valido. Validar datos");
		ex.getConstraintViolations().stream().forEach(v -> {
			v.getPropertyPath().forEach(e ->{
				if(e.getKind() == ElementKind.PROPERTY)
					error.addDetalle(e.getName()+ " debe existir y no estar vacio");				
			});						
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	//cuando el tipo de dato del header  no coincide
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiError> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		ApiError error = new ApiError();
		error.setMensaje("El header " + ex.getName() + " debe der de tipo " + ex.getRequiredType());
		error.setDetalles(Arrays.asList(""+ex));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	//cuando el path no existe
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ApiError> noHandlerFoundException(NoHandlerFoundException ex) {
		ApiError error = new ApiError();
		error.setMensaje("Ruta " + ex.getRequestURL() + " no existe");
		error.setDetalles(Arrays.asList(""+ex));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	//cuando hay un error SQL
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ApiError> exception(SQLException ex){
		ApiError error = new ApiError();
		error.setMensaje(ex.getMessage());
		error.setDetalles(Arrays.asList(""+ex));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
