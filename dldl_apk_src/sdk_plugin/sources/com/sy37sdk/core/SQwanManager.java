package com.sy37sdk.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountChangeListener;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mod.account.IAuthResultListener;
import com.sqwan.common.mod.account.IBackToGameLoginListener;
import com.sqwan.common.mod.account.ILoginListener;
import com.sqwan.common.mod.account.IScreenshotListener;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SDKError;
import com.sqwan.msdk.BaseSQwanCore;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.utils.Util;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQwanManager implements IError {
    private static final String TAG = "【SQManager】";
    public static SQResultListener back2GameLoginListener;
    public static SQScreenshotListener mScreenshotListener;
    public static boolean mmInit;
    public static Context sqContext;
    public static SQResultListener switchAccountListener;
    public SQResultListener mAuthResultListener;
    public SQResultListener payListener;

    public void logout() {
    }

    public SQwanManager(Context context, String str, SQResultListener sQResultListener) {
        sqContext = context;
        Util.setAppKey(context, str);
    }

    public void setAuthResultListener(SQResultListener sQResultListener) {
        this.mAuthResultListener = sQResultListener;
        ((IAccountMod) ModHelper.get(IAccountMod.class)).setAuthResultListener(new IAuthResultListener() { // from class: com.sy37sdk.core.SQwanManager.1
            @Override // com.sqwan.common.mod.account.IAuthResultListener
            public void onAuthResult(boolean z) {
                if (SQwanManager.this.mAuthResultListener != null) {
                    if (z) {
                        SQwanManager.this.mAuthResultListener.onSuccess(new Bundle());
                        return;
                    }
                    HashMap map = new HashMap();
                    map.put(SqTrackKey.logout_type, "防沉迷踢下线退出");
                    map.put("login_type", "phone".equals(AccountCache.getLoginType(SQwanManager.sqContext)) ? "2" : "1");
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.LOGOUT_SUCC, map);
                    SQwanManager.this.mAuthResultListener.onFailture(0, "");
                }
            }
        });
    }

    public void setSwitchAccountListener(final SQResultListener sQResultListener) {
        switchAccountListener = sQResultListener;
        ((IAccountMod) ModHelper.get(IAccountMod.class)).setAccountChangeListener(new IAccountChangeListener() { // from class: com.sy37sdk.core.SQwanManager.2
            @Override // com.sqwan.common.mod.account.IAccountChangeListener
            public void accountChanged(Map<String, String> map) {
                SQResultListener sQResultListener2 = sQResultListener;
                if (sQResultListener2 != null) {
                    sQResultListener2.onSuccess(SQwanManager.this.convertMapToBundle(map));
                    SqTrackActionManager2.getInstance().trackActionCPTest(SqTrackAction2.SWITCH_ACCOUNT_SUCCESS);
                }
            }
        });
    }

    public void setBackToGameLoginListener(final SQResultListener sQResultListener) {
        back2GameLoginListener = sQResultListener;
        ((IAccountMod) ModHelper.get(IAccountMod.class)).setBackToGameLoginListener(new IBackToGameLoginListener() { // from class: com.sy37sdk.core.SQwanManager.3
            @Override // com.sqwan.common.mod.account.IBackToGameLoginListener
            public void backToGameLogin() {
                SQResultListener sQResultListener2 = sQResultListener;
                if (sQResultListener2 != null) {
                    sQResultListener2.onSuccess(new Bundle());
                }
            }
        });
    }

    public void setScreenshotListener(final SQScreenshotListener sQScreenshotListener) {
        mScreenshotListener = sQScreenshotListener;
        ((IAccountMod) ModHelper.get(IAccountMod.class)).setScreenshotListener(new IScreenshotListener() { // from class: com.sy37sdk.core.SQwanManager.4
            @Override // com.sqwan.common.mod.account.IScreenshotListener
            public Bitmap createScreenshot() {
                SQScreenshotListener sQScreenshotListener2 = sQScreenshotListener;
                if (sQScreenshotListener2 != null) {
                    return sQScreenshotListener2.createScreenshot();
                }
                return null;
            }
        });
    }

    public void setCurrentContext(Context context) {
        sqContext = context;
    }

    public void changeAccount(final SQResultListener sQResultListener) {
        if (Util.isSkipSQChangeAccountLogin(sqContext)) {
            LogUtil.v("SQWanManager changeAccount SkipSQChange");
            sQResultListener.onSuccess(new Bundle());
        } else {
            Util.setLoginType(sqContext, "sq");
            ((IAccountMod) ModHelper.get(IAccountMod.class)).changeAccount(new ILoginListener() { // from class: com.sy37sdk.core.SQwanManager.5
                @Override // com.sqwan.common.mod.account.ILoginListener
                public void onSuccess(Map<String, String> map) {
                    sQResultListener.onSuccess(SQwanManager.this.convertMapToBundle(map));
                }

                @Override // com.sqwan.common.mod.account.ILoginListener
                public void onFailure(int i, String str) {
                    sQResultListener.onFailture(i, str);
                }
            });
        }
    }

    public void showSQLoginView(final SQResultListener sQResultListener) {
        ((IAccountMod) ModHelper.get(IAccountMod.class)).showLoginView(new ILoginListener() { // from class: com.sy37sdk.core.SQwanManager.6
            @Override // com.sqwan.common.mod.account.ILoginListener
            public void onSuccess(Map<String, String> map) {
                sQResultListener.onSuccess(SQwanManager.this.convertMapToBundle(map));
            }

            @Override // com.sqwan.common.mod.account.ILoginListener
            public void onFailure(int i, String str) {
                sQResultListener.onFailture(i, str);
            }
        });
    }

    public void login(final SQResultListener sQResultListener) {
        if (sQResultListener == null) {
            SQLog.w("【SQManager】未设置callback, 忽略");
        } else if (TextUtils.isEmpty(Util.getAppKey(sqContext))) {
            SQLog.e("【SQManager】未设置app key, 登录失败");
            sQResultListener.onFailture(SDKError.PARAMS_ERROR_NULL.code, SDKError.PARAMS_ERROR_NULL.message);
        } else {
            ((IAccountMod) ModHelper.get(IAccountMod.class)).login(new ILoginListener() { // from class: com.sy37sdk.core.SQwanManager.7
                @Override // com.sqwan.common.mod.account.ILoginListener
                public void onSuccess(Map<String, String> map) {
                    sQResultListener.onSuccess(SQwanManager.this.convertMapToBundle(map));
                }

                @Override // com.sqwan.common.mod.account.ILoginListener
                public void onFailure(int i, String str) {
                    sQResultListener.onFailture(i, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle convertMapToBundle(Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
        }
        return bundle;
    }

    public void read(SQResultListener sQResultListener, Bundle bundle) {
        String userid = Util.getUserid(sqContext);
        String username = Util.getUsername(sqContext);
        String token = Util.getToken(sqContext);
        if (token.equals("")) {
            if (sQResultListener != null) {
                sQResultListener.onFailture(201, "No User Logined");
            }
        } else {
            if (sQResultListener == null || bundle == null) {
                return;
            }
            bundle.putString(BaseSQwanCore.LOGIN_KEY_USERID, userid);
            bundle.putString(BaseSQwanCore.LOGIN_KEY_USERNAME, username);
            bundle.putString("token", token);
            sQResultListener.onSuccess(bundle);
        }
    }

    public void pay(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, float f, int i2, String str8, SQResultListener sQResultListener) {
        this.payListener = sQResultListener;
        outPay(context, str, str2, str3, str4, str5, str6, str7, i, f, i2, str8, 1, 0, sQResultListener);
    }

    public void outPay(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, float f, int i2, String str8, int i3, int i4, SQResultListener sQResultListener) {
        if (sQResultListener != null && "".equals(str4)) {
            LogUtil.w("outPay 区服为空");
            sQResultListener.onFailture(204, "区服ID不能为空");
        } else if ("".equals(Util.getToken(context)) && sQResultListener != null) {
            LogUtil.w("outPay 尚未登录");
            sQResultListener.onFailture(201, "尚未登录，请登录");
        } else if (sQResultListener != null) {
            SQLog.e("不支持outPay");
            sQResultListener.onFailture(-1, "不支持(-2)");
        }
    }
}
