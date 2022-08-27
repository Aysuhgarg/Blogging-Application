package com.ayush.blog.exceptions;

import com.ayush.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ApiResponse> resoursenotfoundexception( ResourseNotFoundException e)
    {
        String message=e.getMessage();
        ApiResponse apiResponse =new ApiResponse(message,"false");
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        HashMap<String,String> res =new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error)-> {
            String fieldName= ((FieldError)error).getField();
            String message = error.getDefaultMessage();

            res.put(fieldName,message);
        });
        return new ResponseEntity<HashMap<String,String>>(res,HttpStatus.BAD_REQUEST);
    }


}
