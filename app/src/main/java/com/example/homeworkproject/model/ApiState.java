package com.example.homeworkproject.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiState<T> {
    public static Status status;
    public final T data;


    public ApiState(Status status, T data, String message) {
        ApiState.status = status;
        this.data = data;

        this.message = message;
    }


    @SerializedName("Message")
    @Expose
    private final String message;

    public static <T> ApiState<T> success(@NonNull T data){
        return new ApiState<>(Status.SUCCESS, data, null);
    }

    public static <T> ApiState<T> error(String message, @Nullable String data){
        return new ApiState<>(Status.ERROR, (T) data, message);
    }

    public static <T> ApiState<T> loading(@Nullable T data){
        return new ApiState<>(Status.LOADING, data, null);
    }

    public enum Status {
        SUCCESS, ERROR, LOADING
    }

}
