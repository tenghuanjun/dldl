package com.sq.diagnostic.assistant.http.entity;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UploadLogRequest {
    public static final int TYPE_LOG_RETRIEVAL = 1;
    public static final int TYPE_LOG_USER = 0;
    public String contact;
    public String logEndDate;
    public String logStartDate;
    public int updateLogType;
    public String uploadReason;
}
