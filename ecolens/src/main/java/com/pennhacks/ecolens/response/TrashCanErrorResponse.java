package com.pennhacks.ecolens.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class TrashCanErrorResponse {
    @JsonFormat
    private ZonedDateTime timeStamp;
    private int statusCode;
    private String path;
    private String message;

    public TrashCanErrorResponse(ZonedDateTime timeStamp,
                                 int statusCode, String path, String message) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.path = path;
        this.message = message;
    }
}
