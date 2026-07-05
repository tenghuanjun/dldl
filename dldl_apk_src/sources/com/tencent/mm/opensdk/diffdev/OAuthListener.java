package com.tencent.mm.opensdk.diffdev;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface OAuthListener {
    void onAuthFinish(OAuthErrCode oAuthErrCode, String str);

    void onAuthGotQrcode(String str, byte[] bArr);

    void onQrcodeScanned();
}
