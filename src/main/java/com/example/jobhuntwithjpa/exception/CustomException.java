package com.example.jobhuntwithjpa.exception;


public class CustomException extends RuntimeException {

    //객체를 구분할 떄!! serialvseionUID


    public CustomException(String message) {
        super(message);
    }

}
