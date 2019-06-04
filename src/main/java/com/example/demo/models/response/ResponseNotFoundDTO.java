package com.example.demo.models.response;

import java.util.List;

/**
 * Réponse spécifique pour les données non trouvées.
 */
public class ResponseNotFoundDTO extends ResponseDTO {

    private List<Object> data;

    public ResponseNotFoundDTO() {
        super();
    }

    public ResponseNotFoundDTO(StatusJSEND status, List<Object> data) {
        super(status);
        this.data = data;
    }

    public ResponseNotFoundDTO(StatusJSEND status, String message, List<Object> data) {
        super(status, message);
        this.data = data;
    }
    public ResponseNotFoundDTO(StatusJSEND status, String code, String message, List<Object> data) {
        super(status, code, message);
        this.data = data;
    }


    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

}
