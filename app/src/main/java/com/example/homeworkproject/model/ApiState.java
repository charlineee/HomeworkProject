package com.example.homeworkproject.model;

import androidx.annotation.Nullable;

public class ApiState<T> {

    public static Status status;

    public ApiState(Status status) {
        ApiState.status = status;
    }

    public static <T> ApiState<T> success(){
        return new ApiState<>(Status.SUCCESS);
    }

    public static <T> ApiState<T> error(){
        return new ApiState<>(Status.ERROR);
    }

    public static <T> ApiState<T> loading(){
        return new ApiState<>(Status.LOADING);
    }

    public enum Status {
        SUCCESS, ERROR, LOADING;


    }

}
