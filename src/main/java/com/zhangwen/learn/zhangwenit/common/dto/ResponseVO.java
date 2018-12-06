package com.zhangwen.learn.zhangwenit.common.dto;

import com.zhangwen.learn.zhangwenit.common.enums.ResultCode;

import java.io.Serializable;

/**
 * @ClassName ResponseVO
 * @Description
 * @Author XuQiuliang
 * @Date 2018/11/1 4:55 PM
 * @Version 1.0
 **/
public class ResponseVO implements Serializable {

    private ResponseHeader head;

    private Object data;

    public ResponseVO() {
        this.head = new ResponseHeader(0, "");
    }

    public ResponseVO(ResultCode resultCode) {
        this.head = new ResponseHeader(resultCode);
    }

    public ResponseVO(Integer errCode, String errMsg) {
        this.head = new ResponseHeader(errCode, errMsg);
    }

    public ResponseVO(ResultCode resultCode, Object data) {
        this.head = new ResponseHeader(resultCode);
        this.data = data;
    }

    public ResponseVO(Object data, Integer errCode, String errMsg) {
        this.head = new ResponseHeader(errCode, errMsg);
        this.data = data;
    }

    public void setErrCode(Integer errCode) {
        head.setErrCode(errCode);
    }

    public void setErrMsg(String errMsg) {
        head.setErrMsg(errMsg);
    }

    public ResponseHeader getHead() {
        return this.head;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseVO buildSuccess() {
        return new ResponseVO(ResultCode.SUCCESS);
    }

    public static ResponseVO buildSuccess(Object data) {
        ResponseVO responseVO = buildSuccess();
        responseVO.setData(data);
        return responseVO;
    }

    public static ResponseVO buildError(int errCode, String errMsg) {
        ResponseVO responseVO = new ResponseVO(errCode, errMsg);
        return responseVO;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "head=" + head +
                ", data=" + data +
                '}';
    }
}
