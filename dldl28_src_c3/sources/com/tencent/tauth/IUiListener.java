package com.tencent.tauth;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface IUiListener {
    void onCancel();

    void onComplete(Object obj);

    void onError(UiError uiError);

    void onWarning(int i);
}
