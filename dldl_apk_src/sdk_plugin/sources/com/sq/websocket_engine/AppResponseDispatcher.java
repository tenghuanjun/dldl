package com.sq.websocket_engine;

import com.google.sqgson.Gson;
import com.sq.libwebsocket.SimpleDispatcher;
import com.sq.libwebsocket.dispatcher.ResponseDelivery;
import com.sq.libwebsocket.response.ErrorResponse;
import com.sq.libwebsocket.response.Response;
import com.sq.libwebsocket.response.ResponseFactory;
import com.sy37sdk.account.alifast.FastLoginConstants;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AppResponseDispatcher extends SimpleDispatcher {
    public static final int CODE_ERROR = 12;
    public static final int JSON_ERROR = 11;

    @Override // com.sq.libwebsocket.dispatcher.IResponseDispatcher
    public void onMessage(String str, ResponseDelivery responseDelivery) {
        try {
            CommonResponseEntity commonResponseEntity = (CommonResponseEntity) new Gson().fromJson(str, CommonResponseEntity.class);
            if (commonResponseEntity.getCode() >= 1000 && commonResponseEntity.getCode() < 2000) {
                responseDelivery.onMessage(str, commonResponseEntity);
            } else {
                ErrorResponse errorResponseCreateErrorResponse = ResponseFactory.createErrorResponse();
                errorResponseCreateErrorResponse.setErrorCode(12);
                Response responseCreateTextResponse = ResponseFactory.createTextResponse();
                responseCreateTextResponse.setResponseData(str);
                errorResponseCreateErrorResponse.setResponseData(responseCreateTextResponse);
                errorResponseCreateErrorResponse.setReserved(commonResponseEntity);
                onSendDataError(errorResponseCreateErrorResponse, responseDelivery);
            }
        } catch (Exception e) {
            ErrorResponse errorResponseCreateErrorResponse2 = ResponseFactory.createErrorResponse();
            Response responseCreateTextResponse2 = ResponseFactory.createTextResponse();
            responseCreateTextResponse2.setResponseData(str);
            errorResponseCreateErrorResponse2.setResponseData(responseCreateTextResponse2);
            errorResponseCreateErrorResponse2.setErrorCode(11);
            errorResponseCreateErrorResponse2.setCause(e);
            onSendDataError(errorResponseCreateErrorResponse2, responseDelivery);
        }
    }

    @Override // com.sq.libwebsocket.dispatcher.IResponseDispatcher
    public void onSendDataError(ErrorResponse errorResponse, ResponseDelivery responseDelivery) {
        int errorCode = errorResponse.getErrorCode();
        if (errorCode == 0) {
            errorResponse.setDescription("网络错误");
        } else if (errorCode == 1) {
            errorResponse.setDescription(FastLoginConstants.MESSAGE.FAILURE_VERIFY_FAIL_UNKNOWN);
        } else if (errorCode == 2) {
            errorResponse.setDescription("连接未初始化");
        } else if (errorCode == 11) {
            errorResponse.setDescription("数据格式异常");
        } else if (errorCode == 12) {
            errorResponse.setDescription("响应码错误");
        }
        responseDelivery.onSendDataError(errorResponse);
    }
}
