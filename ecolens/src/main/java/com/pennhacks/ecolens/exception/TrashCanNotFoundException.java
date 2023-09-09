package com.pennhacks.ecolens.exception;

public class TrashCanNotFoundException extends RuntimeException {
    public TrashCanNotFoundException(){

    }

    public TrashCanNotFoundException(String message){
        super(message);
    }
}
