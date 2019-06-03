package com.example.demo.models.response;

//https://github.com/omniti-labs/jsend

public class ResponseDTO {

    private StatusJSEND status;

    private String message;

    ResponseDTO(){
        super();
    }
    ResponseDTO(StatusJSEND status){
        this.status = status;
    }

    public ResponseDTO(StatusJSEND status, String message) {
        this.status = status;
        this.message = message;
    }

    public StatusJSEND getStatus() {
        return status;
    }

    public void setStatus(StatusJSEND status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
