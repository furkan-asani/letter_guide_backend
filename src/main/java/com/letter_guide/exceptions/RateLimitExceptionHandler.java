package com.letter_guide.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.letter_guide.aspect.RateLimitAspect.RateLimitException;


@ControllerAdvice
public class RateLimitExceptionHandler {

   @ExceptionHandler(value = RateLimitException.class)
   public ResponseEntity<Object> handleRateLimitException(RateLimitException ex) {
      return new ResponseEntity<>("You have exceeded the rate limit", HttpStatus.TOO_MANY_REQUESTS);
   }
}
