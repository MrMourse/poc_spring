package com.example.demo.models.response;

/**
 * Réponse de l'Api basée sur https://github.com/omniti-labs/jsend
 */
public class ResponseDTO {

    private StatusJSEND status;

    private String errorCode;

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

    public ResponseDTO(StatusJSEND status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
