package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.model.ErrorResponseDTO;

import customexeption.FieldRequiedExeption;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	// bắt lỗi chia cho 0
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Object> handleArithmeticException(ArithmeticException ex, WebRequest request) {

		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();

		errorResponseDTO.setError(ex.getMessage());
		List<String> detailsList = new ArrayList<String>();
		detailsList.add("Loi khong chia dc cho khong!!");
		errorResponseDTO.setDetail(detailsList);
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@ExceptionHandler(FieldRequiedExeption.class)
	public ResponseEntity<Object> handleValueInputException(FieldRequiedExeption ex, WebRequest request) {

		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();

		errorResponseDTO.setError(ex.getMessage());
		List<String> detailsList = new ArrayList<String>();
		detailsList.add("Check lại name hoặc number of basement đi bởi vì đang bị null đó!!!");
		errorResponseDTO.setDetail(detailsList);
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_GATEWAY);
	}
	
	
	
}
