package com.exam.helper;

public class UserNotFoundException extends Exception{

    public  UserNotFoundException(){
        super("User with this username Not found in DB !! try with an another username");
    }
    public UserNotFoundException(String msg){
        super(msg);
    }
}
