package com.alexeymiroshnikov.graduation.util.exception;

public class VoteTimeViolationException extends RuntimeException{
    public VoteTimeViolationException(String message) {
        super(message);
    }
}
