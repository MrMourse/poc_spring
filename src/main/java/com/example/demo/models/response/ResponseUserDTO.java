package com.example.demo.models.response;

import com.example.demo.models.user.UserDTO;

import java.util.List;

public class ResponseUserDTO extends ResponseDTO {

    private List<UserDTO> data;

    public ResponseUserDTO() {
        super();
    }

    public ResponseUserDTO(StatusJSEND status, List<UserDTO> data) {
        super(status);
        this.data = data;
    }

    public ResponseUserDTO(StatusJSEND status, String message, List<UserDTO> data) {
        super(status, message);
        this.data = data;
    }

    public List<UserDTO> getData() {
        return data;
    }

    public void setData(List<UserDTO> data) {
        this.data = data;
    }
}
