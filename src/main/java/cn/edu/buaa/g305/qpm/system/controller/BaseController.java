package cn.edu.buaa.g305.qpm.system.controller;

import java.util.logging.Logger;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class BaseController {
	protected static Logger logger = Logger.getGlobal();
	@ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleIOException(DuplicateKeyException ex) {
        
        return new ResponseEntity<String>("NameDuplicateKey", HttpStatus.BAD_REQUEST);
    }

}
