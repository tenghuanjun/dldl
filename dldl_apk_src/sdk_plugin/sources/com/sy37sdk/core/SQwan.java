package com.sy37sdk.core;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.social.sdk.SocialApi;
import com.sq.eventbus.core.EventBus;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.dialog.CommonAlertDialog;
import com.sqwan.common.eventbus.SActiveEvent;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.config.ConfigManager;
import com.sy37sdk.utils.Util;
import com.sy37sdk.utils.ViewController;
import com.sy37sdk.widget.ExitDialog;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQwan implements SQSdkInterface, IError {
    private static SQwan instance = null;
    public static boolean isLoad = true;

    @Deprecated
    public static boolean isSQLoginSuccess = false;
    private static byte[] lock = new byte[0];

    @Deprecated
    public static final String sdkVersion = "3.6.0";
    private Context context;
    private boolean isInit = false;
    private boolean isInitResponse = false;
    private ViewGroup mDecorView;
    private View mView;
    private RequestManager rManager;
    private SQwanManager wan;

    @Override // com.sy37sdk.core.SQSdkInterface
    public void onStop() {
    }

    private SQwan() {
    }

    public static SQwan getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SQwan();
                }
            }
        }
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
        System.out.println("--SQ setContext--");
        if (this.wan != null) {
            SQwanManager.sqContext = context;
        }
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void init(Context context, String str, final SQResultListener sQResultListener) {
        this.context = context;
        this.wan = new SQwanManager(context, str, sQResultListener);
        this.rManager = new RequestManager(this.context);
        SocialApi.init(this.context);
        this.rManager.initRequst(new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.core.SQwan.1
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                SQwan.this.isInitResponse = true;
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                String string;
                String string2;
                SQwan.this.isInitResponse = true;
                try {
                    SQwan.this.isInit = true;
                    JSONObject jSONObject2 = new JSONObject(jSONObject.getString("c"));
                    SQwan.this.readSplashConfig(jSONObject.optString("e"));
                    if (jSONObject2.has("tt")) {
                        boolean zHas = jSONObject2.getJSONObject("tt").has("img");
                        boolean zHas2 = jSONObject2.getJSONObject("tt").has("u");
                        boolean zHas3 = jSONObject2.getJSONObject("tt").has(SqConstants.DPGN);
                        String string3 = "";
                        if (zHas && zHas2 && zHas3) {
                            string3 = jSONObject2.getJSONObject("tt").getString("img");
                            string2 = jSONObject2.getJSONObject("tt").getString("u");
                            string = jSONObject2.getJSONObject("tt").getString(SqConstants.DPGN);
                        } else {
                            System.out.println("初始化传入的tt参数为空");
                            string = "";
                            string2 = string;
                        }
                        IConfig.exitImgPath = string3;
                        IConfig.exitUrl = string2;
                        IConfig.exitDpgn = string;
                    }
                    if (!jSONObject2.has(SqConstants.SCODE)) {
                        ConfigManager.getInstance(SQwan.this.context).setLessFunctionCode(0);
                    } else {
                        ConfigManager.getInstance(SQwan.this.context).setLessFunctionCode(jSONObject2.optInt(SqConstants.SCODE, 0));
                    }
                    EventBus.getDefault().post(new SActiveEvent(jSONObject.toString()));
                    sQResultListener.onSuccess(new Bundle());
                } catch (Exception e) {
                    e.printStackTrace();
                    HashMap map = new HashMap();
                    map.put(SqTrackKey.fail_code, "204");
                    map.put(SqTrackKey.reason_fail, "s层解析错误 " + e.getMessage());
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.sdk_init_fail, map);
                    BuglessAction.reportCatchException(e, jSONObject.toString(), 5);
                    sQResultListener.onFailture(203, "数据异常，初始化失败");
                    SQwan.this.isInit = false;
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                sQResultListener.onFailture(203, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readSplashConfig(String str) {
        final String string;
        if (TextUtils.isEmpty(str)) {
            string = null;
        } else {
            try {
                string = new JSONObject(str).getString("flash_screen_img");
            } catch (JSONException e) {
                e.printStackTrace();
                string = null;
            }
        }
        ((Activity) this.context).runOnUiThread(new Runnable() { // from class: com.sy37sdk.core.SQwan.2
            @Override // java.lang.Runnable
            public void run() {
                SQwan.this.showSplash(string);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void showSplash(java.lang.String r4) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 1
            if (r0 != 0) goto L8
            goto L3f
        L8:
            android.content.Context r0 = r3.context
            boolean r0 = com.sy37sdk.utils.Util.getIsSpecialSDK(r0)
            if (r0 == 0) goto L3e
            java.lang.String r0 = "是简版"
            com.sqwan.common.util.LogUtil.d(r0)
            android.content.Context r0 = r3.context
            java.lang.String r2 = "multiconfig"
            java.util.Properties r0 = com.sqwan.common.util.AssetsUtils.readProperties(r0, r2)
            if (r0 == 0) goto L3e
            java.lang.String r2 = "isShowSplashWhenSCut"
            java.lang.String r0 = r0.getProperty(r2)
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L39
            java.lang.String r2 = "1"
            boolean r0 = android.text.TextUtils.equals(r2, r0)
            if (r0 == 0) goto L39
            java.lang.String r0 = "开启了简版显示闪屏"
            com.sqwan.common.util.LogUtil.d(r0)
            goto L3f
        L39:
            java.lang.String r0 = "未设置简版显示闪屏或不显示闪屏"
            com.sqwan.common.util.LogUtil.d(r0)
        L3e:
            r1 = 0
        L3f:
            if (r1 == 0) goto L4f
            com.sy37sdk.views.SplashDialog r0 = new com.sy37sdk.views.SplashDialog
            android.content.Context r1 = r3.context
            r2 = 3
            r0.<init>(r1, r2)
            r0.setImgUrl(r4)
            r0.show()
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy37sdk.core.SQwan.showSplash(java.lang.String):void");
    }

    private void checkUrlNeedUpdate(String str) throws Exception {
        LogUtil.e("checkUrlNeedUpdate");
        LogUtil.e(str);
        JSONObject jSONObject = new JSONObject(str);
        for (String str2 : INewUrl.urls.keySet()) {
            if (!jSONObject.isNull(str2)) {
                String string = jSONObject.getString(str2);
                if ("login".equals(str2)) {
                    INewUrl.LOGIN = string;
                }
                if ("reg".equals(str2)) {
                    INewUrl.REG = string;
                }
                if (INewUrl.KEY_S_FREG.equals(str2)) {
                    INewUrl.REG_FAST = string;
                }
                if (INewUrl.KEY_S_IMSG.equals(str2)) {
                    INewUrl.IMSG = string;
                }
                if (INewUrl.KEY_S_GWI.equals(str2)) {
                    INewUrl.GWI = string;
                }
                if (INewUrl.KEY_S_ART.equals(str2)) {
                    INewUrl.ART = string;
                }
                if (INewUrl.KEY_S_CARD.equals(str2)) {
                    INewUrl.CARD = string;
                }
                if (INewUrl.KEY_S_GCARD.equals(str2)) {
                    INewUrl.GCARD = string;
                }
                if (INewUrl.KEY_S_PUSH.equals(str2)) {
                    INewUrl.PUSH = string;
                }
                if (INewUrl.KEY_S_GWA.equals(str2)) {
                    INewUrl.GWA = string;
                }
                if (INewUrl.KEY_S_KF.equals(str2)) {
                    INewUrl.KEFU = string;
                }
                if (INewUrl.KEY_S_OSL.equals(str2)) {
                    INewUrl.OSL = string;
                }
                if (INewUrl.KEY_S_ICARD.equals(str2)) {
                    INewUrl.ICARD = string;
                }
                if (INewUrl.KEY_S_BBS.equals(str2)) {
                    INewUrl.BBS = string;
                }
                if (INewUrl.KEY_S_SHOP.equals(str2)) {
                    INewUrl.SHOP = string;
                }
                if (INewUrl.KEY_S_IWT.equals(str2)) {
                    INewUrl.IWT = string;
                }
                if (INewUrl.KEY_S_SPRO.equals(str2)) {
                    INewUrl.SPRO = string;
                }
                if (INewUrl.KEY_S_CPWD.equals(str2)) {
                    INewUrl.CPWD = string;
                }
                if (INewUrl.KEY_S_PFP.equals(str2)) {
                    INewUrl.PFP = string;
                }
                if (INewUrl.KEY_S_MFP.equals(str2)) {
                    INewUrl.MFP = string;
                }
                if (INewUrl.KEY_S_SPV.equals(str2)) {
                    INewUrl.SPV = string;
                }
                if (INewUrl.KEY_S_BP.equals(str2)) {
                    INewUrl.BP = string;
                }
                if (INewUrl.KEY_S_ICARD.equals(str2)) {
                    INewUrl.ICARD = string;
                }
                if (INewUrl.KEY_S_BM.equals(str2)) {
                    INewUrl.BM = string;
                }
                if ("mscode".equals(str2)) {
                    INewUrl.MSCODE = string;
                }
                if ("mreg".equals(str2)) {
                    INewUrl.MREG = string;
                }
                if ("mreg_res".equals(str2)) {
                    INewUrl.MREG_RES = string;
                }
                if ("uagree".equals(str2)) {
                    INewUrl.USER_AGREE = string;
                }
                if ("resetPwd".equals(str2)) {
                    INewUrl.FORGET_PWD = string;
                }
            }
        }
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void changeAccount(Context context, final SQResultListener sQResultListener) {
        this.wan.changeAccount(new SQResultListener() { // from class: com.sy37sdk.core.SQwan.3
            @Override // com.sy37sdk.core.SQResultListener
            public void onSuccess(Bundle bundle) {
                sQResultListener.onSuccess(bundle);
            }

            @Override // com.sy37sdk.core.SQResultListener
            public void onFailture(int i, String str) {
                sQResultListener.onFailture(i, str);
            }
        });
    }

    public void showLoginView(SQResultListener sQResultListener) {
        this.wan.showSQLoginView(sQResultListener);
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void setSwitchAccountListener(SQResultListener sQResultListener) {
        this.wan.setSwitchAccountListener(sQResultListener);
    }

    public void setBackToGameLoginListener(SQResultListener sQResultListener) {
        this.wan.setBackToGameLoginListener(sQResultListener);
    }

    public void setScreenshotListener(SQScreenshotListener sQScreenshotListener) {
        this.wan.setScreenshotListener(sQScreenshotListener);
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void login(Context context, final SQResultListener sQResultListener) {
        this.wan.login(new SQResultListener() { // from class: com.sy37sdk.core.SQwan.4
            @Override // com.sy37sdk.core.SQResultListener
            public void onSuccess(Bundle bundle) {
                sQResultListener.onSuccess(bundle);
            }

            @Override // com.sy37sdk.core.SQResultListener
            public void onFailture(int i, String str) {
                sQResultListener.onFailture(i, str);
            }
        });
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void onResume() {
        ((IAccountMod) ModHelper.get(IAccountMod.class)).onResume();
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void onPause() {
        ((IAccountMod) ModHelper.get(IAccountMod.class)).onPause();
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void onConfigurationChanged(Configuration configuration) {
        ((IAccountMod) ModHelper.get(IAccountMod.class)).onConfigurationChanged(configuration);
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void logout(Context context, final SQResultListener sQResultListener) {
        if (ConfigManager.getInstance(context).isLessFunction() || ConfigManager.getInstance(context).isSplashSDK()) {
            LogUtil.i("show original exit dialog");
            showOriginalExit(context, sQResultListener);
        } else {
            LogUtil.i("show sq exit dialog");
            showExitDialog(new ExitDialog.ExitCallBack() { // from class: com.sy37sdk.core.SQwan.5
                @Override // com.sy37sdk.widget.ExitDialog.ExitCallBack
                public void exit() {
                    ((IAccountMod) ModHelper.get(IAccountMod.class)).logout();
                    sQResultListener.onSuccess(new Bundle());
                }
            });
        }
    }

    private void showOriginalExit(final Context context, final SQResultListener sQResultListener) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.sy37sdk.core.SQwan.6
            @Override // java.lang.Runnable
            public void run() {
                new CommonAlertDialog.Builder(context).setTitle("您确定退出游戏吗？").setPositiveButton("确定", new View.OnClickListener() { // from class: com.sy37sdk.core.SQwan.6.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ((IAccountMod) ModHelper.get(IAccountMod.class)).logout();
                        SQwan.this.wan.logout();
                        sQResultListener.onSuccess(new Bundle());
                    }
                }).setNegativeButton("取消", null).show();
            }
        });
    }

    private void showExitDialog(final ExitDialog.ExitCallBack exitCallBack) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.sy37sdk.core.SQwan.7
            @Override // java.lang.Runnable
            public void run() {
                ExitDialog exitDialog = new ExitDialog(SQwan.this.context, exitCallBack);
                exitDialog.setUrl(IConfig.exitUrl);
                exitDialog.setPkn(IConfig.exitDpgn);
                exitDialog.setImagePath(IConfig.exitImgPath);
                exitDialog.setCanceledOnTouchOutside(true);
                exitDialog.setCancelable(true);
                exitDialog.show();
            }
        });
    }

    public void pay(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, float f, int i2, String str8, SQResultListener sQResultListener) {
        this.wan.pay(context, str, str2, str3, str4, str5, str6, str7, i, f, i2, str8, sQResultListener);
    }

    public void payWeb(Context context) {
        if (Util.getToken(context) == null || "".equals(Util.getToken(context))) {
            ViewController.showToast(context, "您的登录状态已过期，请重新登录【20001】");
        } else {
            this.wan.outPay(context, "", "", "", "", "", "", "", 0, 0.0f, 10, "", 1, 1, null);
        }
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void pay(Context context, String str, String str2, String str3, float f, String str4, SQResultListener sQResultListener) {
        pay(context, str, "", "", str2, str3, "", "", 0, f, 0, str4, sQResultListener);
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void submitRoleInfo(HashMap<String, String> map) {
        ((IAccountMod) ModHelper.get(IAccountMod.class)).submitRoleInfo(map);
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void showUAgreement(Context context) {
        ((IAccountMod) ModHelper.get(IAccountMod.class)).showUAgreement();
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void setAuthResultListener(SQResultListener sQResultListener) {
        this.wan.setAuthResultListener(sQResultListener);
    }

    @Override // com.sy37sdk.core.SQSdkInterface
    public void showAgeAppropriate(Context context) {
        ((IAccountMod) ModHelper.get(IAccountMod.class)).showAgeAppropriate();
    }
}
