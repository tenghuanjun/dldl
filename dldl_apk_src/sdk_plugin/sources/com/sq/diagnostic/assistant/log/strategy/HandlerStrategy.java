package com.sq.diagnostic.assistant.log.strategy;

import com.sq.diagnostic.assistant.http.entity.UploadLogRequest;
import com.sq.diagnostic.assistant.log.ILogCallBack;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class HandlerStrategy {
    protected String logDateEnd;
    protected String logDateStart;
    protected HandlerStrategy nextHandler;
    protected String zipLogDirPath;

    public abstract boolean process(UploadLogRequest uploadLogRequest, ILogCallBack<?> iLogCallBack);

    public HandlerStrategy(String str, String str2, String str3) {
        this.logDateStart = str;
        this.logDateEnd = str2;
        this.zipLogDirPath = str3;
    }

    public void performProcess(UploadLogRequest uploadLogRequest, ILogCallBack<?> iLogCallBack) {
        HandlerStrategy handlerStrategy;
        if (process(uploadLogRequest, iLogCallBack) || (handlerStrategy = this.nextHandler) == null) {
            return;
        }
        handlerStrategy.performProcess(uploadLogRequest, iLogCallBack);
    }

    public void setNextHandler(HandlerStrategy handlerStrategy) {
        this.nextHandler = handlerStrategy;
    }
}
