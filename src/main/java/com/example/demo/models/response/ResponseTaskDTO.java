package com.example.demo.models.response;

import com.example.demo.models.task.TaskDTO;

import java.util.List;

public class ResponseTaskDTO extends ResponseDTO {

    private List<TaskDTO> data;

    public ResponseTaskDTO() {
        super();
    }

    public ResponseTaskDTO(StatusJSEND status, List<TaskDTO> data) {
        super(status);
        this.data = data;
    }

    public ResponseTaskDTO(StatusJSEND status, String message, List<TaskDTO> data) {
        super(status, message);
        this.data = data;
    }

    public List<TaskDTO> getData() {
        return data;
    }

    public void setData(List<TaskDTO> data) {
        this.data = data;
    }
}
