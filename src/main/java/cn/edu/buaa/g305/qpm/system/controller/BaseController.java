package cn.edu.buaa.g305.qpm.system.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;


public class BaseController {
	protected static Logger logger = Logger.getGlobal();
	//处理命名重复的错误
	@ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException ex) {
		logger.log(Level.INFO,"- - -NameDuplicateKey:"+ex.getMessage());
        return new ResponseEntity<String>("NameDuplicateKey", HttpStatus.BAD_REQUEST);
    }
	//处理一般的badrequest的错误
	@ExceptionHandler(ServletRequestBindingException.class)
	public ResponseEntity<String> handleServletRequestBindingException(ServletRequestBindingException ex) {
        
		logger.log(Level.INFO,"- - -BadRequest:"+ex.getMessage());
        return new ResponseEntity<String>("BadRequest", HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        
		logger.log(Level.INFO,"- - -SourceNotFound:"+ex.getMessage());
        return new ResponseEntity<String>("SourceNotFound", HttpStatus.NOT_FOUND);
    }

}
