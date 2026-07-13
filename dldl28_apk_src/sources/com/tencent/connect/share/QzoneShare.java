package com.tencent.connect.share;

import android.content.Context;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
public class QzoneShare extends BaseApi {
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final String SHARE_TO_QZONE_EXTMAP = "extMap";
    public static final String SHARE_TO_QZONE_KEY_TYPE = "req_type";
    public static final int SHARE_TO_QZONE_TYPE_IMAGE = 5;
    public static final int SHARE_TO_QZONE_TYPE_IMAGE_TEXT = 1;
    public static final int SHARE_TO_QZONE_TYPE_MINI_PROGRAM = 7;
    public static final int SHARE_TO_QZONE_TYPE_NO_TYPE = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1012a;
    private boolean d;
    private boolean e;
    private boolean f;
    public String mViaShareQzoneType;

    @Override // com.tencent.connect.common.BaseApi
    public void releaseResource() {
    }

    public QzoneShare(Context context, QQToken qQToken) {
        super(qQToken);
        this.mViaShareQzoneType = "";
        this.f1012a = true;
        this.d = false;
        this.e = false;
        this.f = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x02d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void shareToQzone(android.app.Activity r29, android.os.Bundle r30, com.tencent.tauth.IUiListener r31) {
        /*
            Method dump skipped, instruction units count: 1081
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.share.QzoneShare.shareToQzone(android.app.Activity, android.os.Bundle, com.tencent.tauth.IUiListener):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0376  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(android.app.Activity r25, android.os.Bundle r26, com.tencent.tauth.IUiListener r27) {
        /*
            Method dump skipped, instruction units count: 922
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.share.QzoneShare.b(android.app.Activity, android.os.Bundle, com.tencent.tauth.IUiListener):void");
    }
}
