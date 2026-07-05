package com.aliyun.aliyunface.api;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface ZIMUICustomListener {
    String onAlertCancelButton(String str);

    String onAlertMessage(String str);

    String onAlertOKButton(String str);

    String onAlertTitle(String str);

    boolean onIsPageScanCloseImageLeft();

    int onPageScanCloseImage();
}
