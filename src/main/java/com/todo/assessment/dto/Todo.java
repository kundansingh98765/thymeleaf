package com.todo.assessment.dto;

import lombok.Data;

@Data
public class Todo {

    public Long id;
    public String tittle;
    public Boolean isCompleted;
}
