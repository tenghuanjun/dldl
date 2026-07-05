package com.huya.component.login.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.component.login.LoginInfo;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.R;
import com.huya.component.login.api.LoginEvent;
import com.huya.component.login.api.LoginInterface;
import com.huya.component.login.api.OnThirdAuthCallback;
import com.huya.mtp.utils.FP;
import com.huya.mtp.utils.ResourceUtils;
import com.huya.mtp.utils.StringUtils;
import com.huya.mtp.utils.ThreadUtils;
import com.huyaudbunify.bean.ThirdLoginOption;
import com.sqwan.common.constants.SqConstants;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yy.open.OnUIListener;
import com.yy.open.UIError;
import com.yy.open.YYOpenSDK;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ThirdLogin {
    private static final String TAG = "ThirdLogin";
    private volatile boolean mInit = false;
    private YYOpenSDK mYYOpenSDK;

    private void init(Activity activity) {
        if (this.mInit) {
            return;
        }
        this.mInit = true;
        String metaValue = ResourceUtils.getMetaValue(activity, "QQ_APP_ID", "");
        if (!StringUtils.isNullOrEmpty(metaValue)) {
            metaValue = metaValue.replace("QQ_APP_ID", "");
        }
        String metaValue2 = ResourceUtils.getMetaValue(activity, "QQ_APP_KEY", "");
        if (FP.empty(metaValue) || FP.empty(metaValue2)) {
            L.error(TAG, "no qq app id or no qq app key");
        } else {
            PlatformConfig.setQQZone(metaValue, metaValue2);
        }
        String metaValue3 = ResourceUtils.getMetaValue(activity, "WX_APP_ID", "");
        String metaValue4 = ResourceUtils.getMetaValue(activity, "WX_SECRET", "");
        if (FP.empty(metaValue3) || FP.empty(metaValue4)) {
            L.error(TAG, "no wx app id or no wx secret");
        } else {
            PlatformConfig.setWeixin(metaValue3, metaValue4);
        }
        String metaValue5 = ResourceUtils.getMetaValue(activity, "SINA_APP_ID", "");
        if (!StringUtils.isNullOrEmpty(metaValue5)) {
            metaValue5 = metaValue5.replace("SINA_APP_ID", "");
        }
        String metaValue6 = ResourceUtils.getMetaValue(activity, "SINA_SECRET", "");
        if (FP.empty(metaValue5) || FP.empty(metaValue6)) {
            L.error(TAG, "no sina app id or no wx secret");
        } else {
            L.info(TAG, "setSinaWeibo sina %s,sinaSecret %s", metaValue5, metaValue6);
            PlatformConfig.setSinaWeibo(metaValue5, metaValue6, "http://sns.whalecloud.com/sina2/callback");
        }
        UMShareAPI.get(ArkValue.gContext).getHandler(SHARE_MEDIA.QQ);
    }

    private void initYyLogin(Context context) {
        if (this.mYYOpenSDK == null) {
            this.mYYOpenSDK = YYOpenSDK.createInstance(context, ResourceUtils.getMetaValue(context, "YY_APP_ID", ""));
        }
    }

    void login(Activity activity, LoginInfo.LoginType loginType, OnThirdAuthCallback onThirdAuthCallback) {
        L.info(TAG, "LoginInfo.LoginType:" + loginType);
        if (loginType == LoginInfo.LoginType.TYPE_YY) {
            onYYLogin(activity);
            return;
        }
        init(activity);
        UMShareAPI uMShareAPI = UMShareAPI.get(activity);
        boolean z = false;
        if (onThirdAuthCallback == null) {
            onThirdAuthCallback = getOnThirdAuthCallback(loginType.value);
            z = true;
        }
        SHARE_MEDIA share_mediaThirdResConvertShare = thirdResConvertShare(loginType.value);
        UMAuthListener uMAuthListener = getUMAuthListener(onThirdAuthCallback, z);
        L.info(TAG, "login start");
        if (uMShareAPI.isAuthorize(activity, share_mediaThirdResConvertShare)) {
            L.info(TAG, "已经有第三方授权，先删除授权");
            uMShareAPI.deleteOauth(activity, share_mediaThirdResConvertShare, getLoginOutUMAuthListener(activity, uMAuthListener));
        } else {
            onDoOauthVerify(activity, share_mediaThirdResConvertShare, uMAuthListener);
        }
        LoginProperties.cancelParams.set(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDoOauthVerify(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        UMShareAPI.get(activity).getPlatformInfo(activity, share_media, uMAuthListener);
        L.info(TAG, "onDoOauthVerify");
    }

    private OnThirdAuthCallback getOnThirdAuthCallback(final int i) {
        return new OnThirdAuthCallback() { // from class: com.huya.component.login.module.ThirdLogin.1
            @Override // com.huya.component.login.api.OnThirdAuthCallback
            public void onAuthSuccess(String str, String str2, String str3, String str4, String str5) {
                L.info(ThirdLogin.TAG, "onAuthSuccess accessToken %s,openId %s,unionId %s,refreshToken %s,expiration %s", str, str2, str3, str4, str5);
                ThirdLogin thirdLogin = ThirdLogin.this;
                LoginInfo.LoginType loginTypeTypeConvertThirdRes = thirdLogin.typeConvertThirdRes(thirdLogin.thirdResConvertShare(i));
                ThirdLogin.this.thirdLogin(loginTypeTypeConvertThirdRes.value, str, loginTypeTypeConvertThirdRes.appKey, str2, "1");
            }

            @Override // com.huya.component.login.api.OnThirdAuthCallback
            public void onAuthFailed() {
                L.info(ThirdLogin.TAG, "onAuthFailed ...");
                LoginProperties.cancelParams.set(false);
                ThirdLogin.this.delayLoginFail();
            }
        };
    }

    private UMAuthListener getLoginOutUMAuthListener(final Activity activity, final UMAuthListener uMAuthListener) {
        return new UMAuthListener() { // from class: com.huya.component.login.module.ThirdLogin.2
            private void onCompleteDoOauthVerify(SHARE_MEDIA share_media) {
                ThirdLogin.this.onDoOauthVerify(activity, share_media, uMAuthListener);
            }

            public void onStart(SHARE_MEDIA share_media) {
                L.info(ThirdLogin.TAG, "Loginout onStart:" + share_media);
            }

            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                L.info(ThirdLogin.TAG, "Loginout onComplete:" + map);
                onCompleteDoOauthVerify(share_media);
            }

            public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
                L.info(ThirdLogin.TAG, "Loginout onError:" + th);
                onCompleteDoOauthVerify(share_media);
            }

            public void onCancel(SHARE_MEDIA share_media, int i) {
                L.info(ThirdLogin.TAG, "Loginout onCancel:" + i);
                onCompleteDoOauthVerify(share_media);
            }
        };
    }

    private UMAuthListener getUMAuthListener(final OnThirdAuthCallback onThirdAuthCallback, final boolean z) {
        return new UMAuthListener() { // from class: com.huya.component.login.module.ThirdLogin.3
            public void onStart(SHARE_MEDIA share_media) {
                L.info(ThirdLogin.TAG, "onStart");
            }

            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                if (map == null) {
                    onError(share_media, i, new NullPointerException("map may not be null"));
                    return;
                }
                L.info(ThirdLogin.TAG, "onComplete SHARE_MEDIA %s,map %s", share_media, map);
                ThirdParser parser = ParserFactory.getParser(share_media);
                if (z) {
                    LoginProperties.cancelParams.set(false);
                }
                OnThirdAuthCallback onThirdAuthCallback2 = onThirdAuthCallback;
                if (onThirdAuthCallback2 != null) {
                    onThirdAuthCallback2.onAuthSuccess(parser.getAccessToken(map), parser.getPartnerId(map), parser.getUnionId(map), parser.getRefreshToken(map), parser.getExpiration(map));
                }
            }

            public void onError(SHARE_MEDIA share_media, int i, Throwable th) {
                L.error(ThirdLogin.TAG, "login error %s", th.toString());
                OnThirdAuthCallback onThirdAuthCallback2 = onThirdAuthCallback;
                if (onThirdAuthCallback2 != null) {
                    onThirdAuthCallback2.onAuthFailed();
                }
            }

            public void onCancel(SHARE_MEDIA share_media, int i) {
                L.info(ThirdLogin.TAG, String.format(Locale.CHINA, "login cancel, i=%d", Integer.valueOf(i)));
                OnThirdAuthCallback onThirdAuthCallback2 = onThirdAuthCallback;
                if (onThirdAuthCallback2 != null) {
                    onThirdAuthCallback2.onAuthFailed();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void thirdLogin(int i, String str, String str2, String str3, String str4) {
        ArkUtils.send(new LoginInterface.ThirdPartyLogin(i, str, str2, str3, str4));
    }

    private void onYYLogin(Activity activity) {
        initYyLogin(activity);
        this.mYYOpenSDK.authorize(activity, new OnUIListener() { // from class: com.huya.component.login.module.ThirdLogin.4
            public void onComplete(JSONObject jSONObject) {
                L.info(ThirdLogin.TAG, "onYYLogin onComplete:" + jSONObject);
                if (jSONObject.has(SqConstants.OPEN_ID)) {
                    try {
                        String string = jSONObject.getString(SqConstants.OPEN_ID);
                        String string2 = jSONObject.getString("access_code");
                        ThirdLoginOption thirdLoginOption = new ThirdLoginOption();
                        thirdLoginOption.setPartnerUid(string);
                        thirdLoginOption.setOauthType("0");
                        ThirdLogin.this.thirdLogin(LoginInfo.LoginType.TYPE_YY.value, string2, "", string, "0");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (jSONObject.has("token")) {
                    try {
                        String string3 = jSONObject.getString("token");
                        if (TextUtils.isEmpty(string3)) {
                            ThirdLogin.this.delayLoginFail();
                        } else {
                            ThirdLoginOption thirdLoginOption2 = new ThirdLoginOption();
                            thirdLoginOption2.setPartnerUid("");
                            thirdLoginOption2.setOauthType("0");
                            ThirdLogin.this.thirdLogin(LoginInfo.LoginType.TYPE_YY.value, string3, "", "", "0");
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                } else {
                    ThirdLogin.this.delayLoginFail();
                }
                LoginProperties.cancelParams.set(false);
            }

            public void onError(UIError uIError) {
                L.error(ThirdLogin.TAG, "onYYLogin onError:%d,%s", Integer.valueOf(uIError.code), uIError.desc);
                ThirdLogin.this.delayLoginFail();
                LoginProperties.cancelParams.set(false);
            }

            public void onCancel() {
                L.error(ThirdLogin.TAG, "onCancel...");
                ArkUtils.send(new LoginEvent.LoginFail(LoginEvent.LoginFail.Reason.Cancel, ArkValue.gContext.getString(R.string.third_login_cancel)));
                LoginProperties.cancelParams.set(false);
            }
        });
        L.info(TAG, "yy login start");
        LoginProperties.cancelParams.set(true);
    }

    /* JADX INFO: renamed from: com.huya.component.login.module.ThirdLogin$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA;

        static {
            int[] iArr = new int[SHARE_MEDIA.values().length];
            $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA = iArr;
            try {
                iArr[SHARE_MEDIA.WEIXIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA[SHARE_MEDIA.QQ.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA[SHARE_MEDIA.SINA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LoginInfo.LoginType typeConvertThirdRes(SHARE_MEDIA share_media) {
        LoginInfo.LoginType loginType = LoginInfo.LoginType.TYPE_QQ;
        int i = AnonymousClass7.$SwitchMap$com$umeng$socialize$bean$SHARE_MEDIA[share_media.ordinal()];
        if (i == 1) {
            LoginInfo.LoginType loginType2 = LoginInfo.LoginType.TYPE_WE_CHAT;
            loginType2.appKey = ResourceUtils.getMetaValue(ArkValue.gContext, "WX_APP_ID", "");
            return loginType2;
        }
        if (i == 2) {
            LoginInfo.LoginType loginType3 = LoginInfo.LoginType.TYPE_QQ;
            loginType3.appKey = ResourceUtils.getMetaValue(ArkValue.gContext, "QQ_APP_ID", "");
            return loginType3;
        }
        if (i == 3) {
            LoginInfo.LoginType loginType4 = LoginInfo.LoginType.TYPE_WEI_BO;
            loginType4.appKey = ResourceUtils.getMetaValue(ArkValue.gContext, "SINA_APP_ID", "");
            return loginType4;
        }
        ArkUtils.crashIfDebug("unknown login type", new Object[0]);
        return loginType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SHARE_MEDIA thirdResConvertShare(int i) {
        SHARE_MEDIA share_media = SHARE_MEDIA.QQ;
        if (i == LoginInfo.LoginType.TYPE_QQ.value) {
            return SHARE_MEDIA.QQ;
        }
        if (i == LoginInfo.LoginType.TYPE_WEI_BO.value) {
            return SHARE_MEDIA.SINA;
        }
        if (i == LoginInfo.LoginType.TYPE_WE_CHAT.value) {
            return SHARE_MEDIA.WEIXIN;
        }
        ArkUtils.crashIfDebug("unknown login type", new Object[0]);
        return share_media;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginFail() {
        ArkUtils.send(new LoginEvent.LoginFail(LoginEvent.LoginFail.Reason.Unknown, ArkValue.gContext.getString(R.string.third_login_fail)));
    }

    public void delayLoginFail() {
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.huya.component.login.module.ThirdLogin.5
            @Override // java.lang.Runnable
            public void run() {
                ThirdLogin.this.loginFail();
            }
        }, 1000L);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        YYOpenSDK yYOpenSDK;
        if (intent == null) {
            return;
        }
        L.info(TAG, "onActivityResult:" + intent.getStringExtra("resjson"));
        if (intent == null || (yYOpenSDK = this.mYYOpenSDK) == null) {
            return;
        }
        yYOpenSDK.handleActivityResult(i, i2, intent, new OnUIListener() { // from class: com.huya.component.login.module.ThirdLogin.6
            public void onComplete(JSONObject jSONObject) {
                L.info(ThirdLogin.TAG, "onComplete:" + jSONObject);
            }

            public void onError(UIError uIError) {
                L.info(ThirdLogin.TAG, "onError:" + uIError);
            }

            public void onCancel() {
                L.info(ThirdLogin.TAG, "onCancel...");
            }
        });
    }
}
