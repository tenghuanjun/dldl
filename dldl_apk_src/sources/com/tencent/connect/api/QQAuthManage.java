package com.tencent.connect.api;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.auth.c;
import com.tencent.connect.common.BaseApi;
import com.tencent.open.apireq.BaseResp;
import com.tencent.open.apireq.IApiCallback;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.i;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class QQAuthManage extends BaseApi {

    /* JADX INFO: compiled from: ProGuard */
    public static class Resp extends BaseResp {
    }

    public QQAuthManage(c cVar, QQToken qQToken) {
        super(cVar, qQToken);
    }

    public void gotoManagePage(final Activity activity, final IApiCallback iApiCallback) {
        SLog.i("QQAuthManage", "gotoManagePage");
        final Resp resp = new Resp();
        int iA = a(activity);
        if (iA != 0) {
            resp.setCode(iA);
            iApiCallback.onResp(resp);
        } else {
            if (!this.c.isSessionValid() || this.c.getOpenId() == null) {
                SLog.i("QQAuthManage", "gotoManagePage: not login");
                resp.setCode(BaseResp.CODE_NOT_LOGIN);
                iApiCallback.onResp(resp);
                return;
            }
            this.b.a(new IUiListener() { // from class: com.tencent.connect.api.QQAuthManage.1
                @Override // com.tencent.tauth.IUiListener
                public void onCancel() {
                }

                @Override // com.tencent.tauth.IUiListener
                public void onWarning(int i) {
                }

                @Override // com.tencent.tauth.IUiListener
                public void onComplete(Object obj) {
                    if (obj instanceof JSONObject) {
                        if (((JSONObject) obj).optInt("ret", -1) == 0) {
                            QQAuthManage.this.a(activity, iApiCallback);
                            return;
                        }
                        SLog.i("QQAuthManage", "gotoManagePage: checkLogin not login");
                        resp.setCode(BaseResp.CODE_NOT_LOGIN);
                        iApiCallback.onResp(resp);
                    }
                }

                @Override // com.tencent.tauth.IUiListener
                public void onError(UiError uiError) {
                    resp.setCode(uiError.errorCode);
                    resp.setErrorMsg(uiError.errorMessage);
                    SLog.e("QQAuthManage", "gotoManagePage: checkLogin error. " + resp);
                    iApiCallback.onResp(resp);
                }
            });
        }
    }

    private int a(Activity activity) {
        if (!i.c(activity)) {
            SLog.i("QQAuthManage", "gotoManagePage: not installed all qq");
            return -1000;
        }
        if (!i.b(activity)) {
            SLog.i("QQAuthManage", "gotoManagePage: only support mobile qq");
            return BaseResp.CODE_UNSUPPORTED_BRANCH;
        }
        if (i.c(activity, "8.6.0") >= 0) {
            return 0;
        }
        SLog.i("QQAuthManage", "gotoManagePage: low version");
        return BaseResp.CODE_QQ_LOW_VERSION;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, IApiCallback iApiCallback) {
        SLog.i("QQAuthManage", "doGotoMangePage");
        StringBuilder sb = new StringBuilder("mqqapi://opensdk/open_auth_manage");
        a(sb, activity);
        Intent intent = new Intent();
        intent.setData(Uri.parse(sb.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        intent.setPackage("com.tencent.mobileqq");
        intent.setFlags(335544320);
        activity.startActivity(intent);
        iApiCallback.onResp(new Resp());
    }
}
