package com.sy37sdk.core;

import android.content.Context;
import com.sq.tool.network.SignInterceptor;
import com.sq.tool.network.SignV2Interceptor;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sq.tools.Logger;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.FakeActive;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.data.cache.DevManager;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.request.CommonParamsV2;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.AppUtils;
import com.sqwan.msdk.SQReportCore;
import com.sy37sdk.utils.Util;
import com.sy37sdk.widget.ProgressDialog;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RequestManager {
    private Context mContext;
    private ProgressDialog waitDialog;

    public RequestManager(Context context) {
        this.mContext = context;
    }

    public void initRequst(final SqHttpCallback<JSONObject> sqHttpCallback) {
        String mDev = SQReportCore.getInstance().getMDev();
        boolean z = this.mContext.getResources().getConfiguration().orientation == 2;
        final String str = INewUrl.INIT + "?t=" + System.currentTimeMillis() + "";
        SqRequest sqRequestAddParam = SqRequest.of(str).signV2(null).addParam("type", z ? "1" : "2");
        if (mDev == null) {
            mDev = "";
        }
        final SqRequest sqRequestAddParamsTransformer = sqRequestAddParam.addParam("mdev", mDev).addParam("display_name", AppUtils.getAppName(this.mContext)).addParam("android_id", DevManager.getAndroidId(DevLogic.getInstance(this.mContext).isAuthCheck())).addParamsTransformer(new CommonParamsV2());
        sqRequestAddParamsTransformer.post(new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.core.RequestManager.1
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                if (sqHttpCallback2 != null) {
                    sqHttpCallback2.onResponseStateError(i, i2, str2, str3);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                FakeActive fakeActive = FakeActive.INSTANCE;
                Context context = RequestManager.this.mContext;
                String responseStr = getResponseStr();
                FakeActive.INSTANCE.getClass();
                fakeActive.saveContent(context, responseStr, "sq_s_fake_active_content");
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                if (sqHttpCallback2 != null) {
                    sqHttpCallback2.onSuccess(jSONObject);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                HashMap map = new HashMap();
                map.put(SqTrackKey.fail_code, i + "");
                map.put(SqTrackKey.reason_fail, str2);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.sdk_init_fail, map);
                if (sqHttpCallback == null) {
                    return;
                }
                FakeActive fakeActive = FakeActive.INSTANCE;
                Context context = RequestManager.this.mContext;
                FakeActive.INSTANCE.getClass();
                String strRequireContent = fakeActive.requireContent(context, "sq_s_fake_active_content", str, sqRequestAddParamsTransformer.getRequestParams(), SignInterceptor.SignVersion.V2);
                JSONObject jSONObject = null;
                try {
                    jSONObject = new JSONObject(strRequireContent).getJSONObject(FunctionRouter.KEY_DATA);
                } catch (Exception unused) {
                }
                if (jSONObject != null) {
                    Logger.info("本地s层激活有缓存, 本地激活成功", new Object[0]);
                    sqHttpCallback.onSuccess(jSONObject);
                    return;
                }
                try {
                    Logger.warning("本地s层激活未读到缓存, 返回默认结果", new Object[0]);
                    sqHttpCallback.onSuccess(buildFakeData());
                } catch (Exception unused2) {
                    Logger.error("默认结果构建失败, s层激活失败", new Object[0]);
                    sqHttpCallback.onFailure(i, "网络异常，请稍候再试", volleyError);
                }
            }

            private JSONObject buildFakeData() throws Exception {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("c", new JSONObject());
                return jSONObject;
            }
        });
    }

    public void loginRequest(String str, String str2, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uname", str);
        map.put(SqConstants.UPWD, str2);
        post(INewUrl.LOGIN, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str).append(str2), requestCallBack, z);
    }

    public void registRequest(String str, String str2, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uname", str);
        map.put(SqConstants.UPWD, str2);
        post(INewUrl.REG, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str).append(str2), requestCallBack, z);
    }

    public void fastRegistRequest(RequestCallBack requestCallBack, boolean z) {
        post(INewUrl.REG_FAST, null, SignInterceptor.SignVersion.V2, null, requestCallBack, z);
    }

    public void updatePassWordRequest(String str, String str2, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Util.getToken(this.mContext));
        map.put("oupwd", str);
        map.put("nupwd", str2);
        post(INewUrl.CPWD, map, SignInterceptor.SignVersion.V2, null, requestCallBack, z);
    }

    public void findAccountByPhoneRequest(String str, String str2, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uname", str);
        map.put("phone", str2);
        post(INewUrl.PFP, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str).append(str2), requestCallBack, z);
    }

    public void findAccountByEmailRequest(String str, String str2, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uname", str);
        map.put("email", str2);
        post(INewUrl.MFP, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str).append(str2), requestCallBack, z);
    }

    public void getVerfyCodeRequest(String str, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Util.getToken(this.mContext));
        map.put("phone", str);
        post(INewUrl.SPV, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str), requestCallBack, z);
    }

    public void bindByPhoneRequest(String str, String str2, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Util.getToken(this.mContext));
        map.put("phone", str);
        map.put("vcode", str2);
        post(INewUrl.BP, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str).append(str2), requestCallBack, z);
    }

    public void bindByEmailRequest(String str, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Util.getToken(this.mContext));
        map.put("email", str);
        post(INewUrl.BM, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str), requestCallBack, z);
    }

    public void modifyUserinfoRequest(String str, String str2, String str3, String str4, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Util.getToken(this.mContext));
        map.put("sex", str);
        map.put("nick", str2);
        map.put("birth", str3);
        map.put("phone", str4);
        post(INewUrl.SPRO, map, SignInterceptor.SignVersion.V2, null, requestCallBack, z);
    }

    public void getMyWalletInfoRequest(RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Util.getToken(this.mContext));
        post(INewUrl.IWT, map, SignInterceptor.SignVersion.V2, null, requestCallBack, z);
    }

    public void getMyGiftsRequest(int i, int i2, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Util.getToken(this.mContext));
        map.put("pno", "" + i);
        map.put("psize", "" + i2);
        post(INewUrl.ICARD, map, SignInterceptor.SignVersion.V2, null, requestCallBack, z);
    }

    public void getOpenServersRequest(int i, int i2, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Util.getToken(this.mContext));
        map.put("pno", "" + i);
        map.put("psize", "" + i2);
        post(INewUrl.OSL, map, SignInterceptor.SignVersion.V2, null, requestCallBack, z);
    }

    public void getServiceInfoRequest(RequestCallBack requestCallBack, boolean z) {
        post(INewUrl.GWI, null, SignInterceptor.SignVersion.V2, null, requestCallBack, z);
    }

    public void getGiftCardRequest(String str, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Util.getToken(this.mContext));
        map.put("rid", str);
        post(INewUrl.GCARD, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str), requestCallBack, z);
    }

    public void getListOfGiftsRequest(int i, int i2, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("pno", "" + i);
        map.put("psize", "" + i2);
        post(INewUrl.CARD, map, SignInterceptor.SignVersion.V2, null, requestCallBack, z);
    }

    public void getListOfArtsRequest(String str, int i, int i2, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "" + str);
        map.put("pno", "" + i);
        map.put("psize", "" + i2);
        post(INewUrl.ART, map, SignInterceptor.SignVersion.V2, null, requestCallBack, z);
    }

    public void pushRequest(RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Util.getToken(this.mContext));
        post(INewUrl.PUSH, map, SignInterceptor.SignVersion.V2, null, requestCallBack, z);
    }

    public void getVerifyCodeRequest(String str, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uname", str);
        post(INewUrl.MSCODE, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str), requestCallBack, z);
    }

    public void phoneNumRegRequest(String str, String str2, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uname", str);
        map.put(SqConstants.SCODE, str2);
        post(INewUrl.MREG, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str).append(str2), requestCallBack, z);
    }

    public void queryMsgRegResultRequest(String str, RequestCallBack requestCallBack, boolean z) {
        HashMap<String, String> map = new HashMap<>();
        map.put("vsign", str);
        post(INewUrl.MREG_RES, map, SignInterceptor.SignVersion.V2, new SignV2Interceptor.SignExt(str), requestCallBack, z);
    }

    private void post(String str, HashMap<String, String> map, SignInterceptor.SignVersion signVersion, SignV2Interceptor.SignExt signExt, final RequestCallBack requestCallBack, boolean z) {
        if (z) {
            if (this.waitDialog == null) {
                ProgressDialog progressDialog = new ProgressDialog(this.mContext);
                this.waitDialog = progressDialog;
                progressDialog.setCancelable(false);
            }
            ProgressDialog progressDialog2 = this.waitDialog;
            if (progressDialog2 != null && !progressDialog2.isShowing()) {
                this.waitDialog.show();
            }
        }
        SqRequest.of(str).sign(signVersion, signExt).formParams(map).addParamsTransformer(new SCommonParam()).post(new SqHttpCallback<String>() { // from class: com.sy37sdk.core.RequestManager.2
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str2) {
                if (RequestManager.this.waitDialog != null && RequestManager.this.waitDialog.isShowing()) {
                    RequestManager.this.waitDialog.dismiss();
                }
                RequestCallBack requestCallBack2 = requestCallBack;
                if (requestCallBack2 != null) {
                    requestCallBack2.onRequestSuccess(getResponseStr());
                }
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                if (RequestManager.this.waitDialog != null && RequestManager.this.waitDialog.isShowing()) {
                    RequestManager.this.waitDialog.dismiss();
                }
                RequestCallBack requestCallBack2 = requestCallBack;
                if (requestCallBack2 != null) {
                    requestCallBack2.onRequestSuccess(getResponseStr());
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                if (RequestManager.this.waitDialog != null && RequestManager.this.waitDialog.isShowing()) {
                    RequestManager.this.waitDialog.dismiss();
                }
                RequestCallBack requestCallBack2 = requestCallBack;
                if (requestCallBack2 != null) {
                    requestCallBack2.onRequestError(i, "网络异常，请稍候再试");
                }
            }
        }, String.class);
    }
}
