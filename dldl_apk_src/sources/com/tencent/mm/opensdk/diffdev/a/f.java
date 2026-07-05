package com.tencent.mm.opensdk.diffdev.a;

import android.os.AsyncTask;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.tencent.mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.mm.opensdk.diffdev.OAuthListener;
import com.tencent.mm.opensdk.utils.Log;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
final class f extends AsyncTask<Void, Void, a> {
    private OAuthListener k;
    private String n;
    private int t;
    private String url;

    static class a {
        public OAuthErrCode m;
        public String u;
        public int v;

        a() {
        }

        public static a b(byte[] bArr) {
            OAuthErrCode oAuthErrCode;
            String str;
            OAuthErrCode oAuthErrCode2;
            a aVar = new a();
            Log.d("MicroMsg.SDK.NoopingResult", "star parse NoopingResult");
            if (bArr != null && bArr.length != 0) {
                try {
                } catch (Exception e) {
                    str = String.format("parse fail, build String fail, ex = %s", e.getMessage());
                }
                try {
                    JSONObject jSONObject = new JSONObject(new String(bArr, "utf-8"));
                    int i = jSONObject.getInt("wx_errcode");
                    aVar.v = i;
                    Log.d("MicroMsg.SDK.NoopingResult", String.format("nooping uuidStatusCode = %d", Integer.valueOf(i)));
                    int i2 = aVar.v;
                    if (i2 == 408) {
                        oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_OK;
                    } else if (i2 != 500) {
                        switch (i2) {
                            case TTAdConstant.DEEPLINK_UNAVAILABLE_CODE /* 402 */:
                                oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_Timeout;
                                break;
                            case TTAdConstant.DEEPLINK_FALLBACK_TYPE_ERROR_CODE /* 403 */:
                                oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_Cancel;
                                break;
                            case TTAdConstant.SDK_NOT_SUPPORT_LIVE_MATE_CODE /* 404 */:
                                oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_OK;
                                break;
                            case TTAdConstant.LANDING_PAGE_TYPE_CODE /* 405 */:
                                aVar.m = OAuthErrCode.WechatAuth_Err_OK;
                                aVar.u = jSONObject.getString("wx_code");
                                return aVar;
                            default:
                                oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_NormalErr;
                                break;
                        }
                    } else {
                        oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_NormalErr;
                    }
                    aVar.m = oAuthErrCode2;
                    return aVar;
                } catch (Exception e2) {
                    str = String.format("parse json fail, ex = %s", e2.getMessage());
                    Log.e("MicroMsg.SDK.NoopingResult", str);
                    oAuthErrCode = OAuthErrCode.WechatAuth_Err_NormalErr;
                    aVar.m = oAuthErrCode;
                    return aVar;
                }
            }
            Log.e("MicroMsg.SDK.NoopingResult", "parse fail, buf is null");
            oAuthErrCode = OAuthErrCode.WechatAuth_Err_NetworkErr;
            aVar.m = oAuthErrCode;
            return aVar;
        }
    }

    public f(String str, OAuthListener oAuthListener) {
        this.n = str;
        this.k = oAuthListener;
        this.url = String.format("https://long.open.weixin.qq.com/connect/l/qrconnect?f=json&uuid=%s", str);
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ a doInBackground(Void[] voidArr) throws Throwable {
        a aVar;
        OAuthErrCode oAuthErrCode;
        String str;
        Thread.currentThread().setName("OpenSdkNoopingTask");
        String str2 = this.n;
        if (str2 == null || str2.length() == 0) {
            Log.e("MicroMsg.SDK.NoopingTask", "run fail, uuid is null");
            aVar = new a();
            oAuthErrCode = OAuthErrCode.WechatAuth_Err_NormalErr;
        } else {
            Log.i("MicroMsg.SDK.NoopingTask", "doInBackground start " + isCancelled());
            while (!isCancelled()) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.url);
                if (this.t == 0) {
                    str = "";
                } else {
                    str = "&last=" + this.t;
                }
                sb.append(str);
                String string = sb.toString();
                long jCurrentTimeMillis = System.currentTimeMillis();
                byte[] bArrA = e.a(string);
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                a aVarB = a.b(bArrA);
                Log.d("MicroMsg.SDK.NoopingTask", String.format("nooping, url = %s, errCode = %s, uuidStatusCode = %d, time consumed = %d(ms)", string, aVarB.m.toString(), Integer.valueOf(aVarB.v), Long.valueOf(jCurrentTimeMillis2 - jCurrentTimeMillis)));
                if (aVarB.m != OAuthErrCode.WechatAuth_Err_OK) {
                    Log.e("MicroMsg.SDK.NoopingTask", String.format("nooping fail, errCode = %s, uuidStatusCode = %d", aVarB.m.toString(), Integer.valueOf(aVarB.v)));
                    return aVarB;
                }
                this.t = aVarB.v;
                if (aVarB.v == g.UUID_SCANED.getCode()) {
                    this.k.onQrcodeScanned();
                } else if (aVarB.v != g.UUID_KEEP_CONNECT.getCode() && aVarB.v == g.UUID_CONFIRM.getCode()) {
                    if (aVarB.u == null || aVarB.u.length() == 0) {
                        Log.e("MicroMsg.SDK.NoopingTask", "nooping fail, confirm with an empty code!!!");
                        aVarB.m = OAuthErrCode.WechatAuth_Err_NormalErr;
                    }
                    return aVarB;
                }
            }
            Log.i("MicroMsg.SDK.NoopingTask", "IDiffDevOAuth.stopAuth / detach invoked");
            aVar = new a();
            oAuthErrCode = OAuthErrCode.WechatAuth_Err_Auth_Stopped;
        }
        aVar.m = oAuthErrCode;
        return aVar;
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(a aVar) {
        a aVar2 = aVar;
        this.k.onAuthFinish(aVar2.m, aVar2.u);
    }
}
