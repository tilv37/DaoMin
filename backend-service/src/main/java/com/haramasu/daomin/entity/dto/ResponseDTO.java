package com.haramasu.daomin.entity.dto;

import java.util.Date;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
public class ResponseDTO<T> {
    private int httpCode;

    private boolean status;

    private String msg;

    private Date timestamp;

    T data;

    public ResponseDTO() {
    }

    public ResponseDTO(int httpCode, boolean status, String msg) {
        this.httpCode = httpCode;
        this.status = status;
        this.msg = msg;
    }

    public ResponseDTO(int httpCode, boolean status, String msg, T data) {
        this.httpCode = httpCode;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void resetBody(int httpCode, boolean status, String msg) {
        this.httpCode = httpCode;
        this.status = status;
        this.msg = msg;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public static <V> ResponseDTO<V> success(V data){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setStatus(true);
        responseDTO.setHttpCode(200);
        responseDTO.setData(data);
        responseDTO.setMsg("success");
        responseDTO.setTimestamp(new Date());
        return responseDTO;
    }

    public static ResponseDTO success(){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setStatus(true);
        responseDTO.setHttpCode(200);
        responseDTO.setMsg("success");
        responseDTO.setTimestamp(new Date());
        return responseDTO;
    }

    public static <V> ResponseDTO<V> error(int httpCode,String msg){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setStatus(false);
        responseDTO.setHttpCode(httpCode);
        responseDTO.setMsg(msg);
        responseDTO.setTimestamp(new Date());
        return responseDTO;
    }

    public static <V> ResponseDTO<V> error(String msg){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setStatus(false);
        responseDTO.setMsg(msg);
        responseDTO.setTimestamp(new Date());
        return responseDTO;
    }
}
