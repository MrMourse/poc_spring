package com.example.demo.models.response;

public enum StatusJSEND {

    //All went well, and (usually) some data was returned.
    SUCCESS ("success"),

    //There was a problem with the data submitted,
    //or some pre-condition of the API call wasn't satisfied
    FAIL ("fail"),

    //An error occurred in processing the request, i.e. an exception was thrown
    error ("error");

    private final String name;

    //Constructeur
    StatusJSEND(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
