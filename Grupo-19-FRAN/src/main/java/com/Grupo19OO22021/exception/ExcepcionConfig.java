package com.Grupo19OO22021.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
public class ExcepcionConfig {
	
	@ExceptionHandler(UsuarioExistenteException.class)
	public ResponseEntity<?> posicionExistenteException(UsuarioExistenteException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
}
