package com.asix.demo.apidata;

public class ApiResponse {

    private final String ResponseCode;
    private final String ResponseMessage;
    private final Object ResponseResult;

    public ApiResponse(String responseCode, String responseMessage, Object responseResult) {
        this.ResponseCode = responseCode;
        this.ResponseMessage = responseMessage;
        this.ResponseResult = responseResult;
    }

    public ApiResponse(String responseCode, String responseMessage) {
        this.ResponseCode = responseCode;
        this.ResponseMessage = responseMessage;
        this.ResponseResult = "";
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public Object getResponseResult() {
        return ResponseResult;
    }
}
