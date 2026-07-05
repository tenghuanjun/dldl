package com.social.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.social.sdk.common.listener.OnAuthListener;
import com.social.sdk.common.listener.ShareListener;
import com.social.sdk.common.util.LogUtils;
import com.social.sdk.platform.PlatformConfig;
import com.social.sdk.platform.PlatformType;
import com.social.sdk.share.media.IShareMedia;
import com.social.sdk.sso.SSOHandler;
import com.social.sdk.sso.qq.QQHandler;
import com.social.sdk.sso.wechat.WechatHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class SocialApi {
    private static SocialApi instance;
    public static byte[] lock = new byte[0];
    private static Context mContext;
    private Map<PlatformType, SSOHandler> ssoHandlerMap = new HashMap();

    private SocialApi() {
    }

    public static SocialApi getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SocialApi();
                }
            }
        }
        return instance;
    }

    public static void init(Context context) {
        LogUtils.i("SocialApi init");
        mContext = context.getApplicationContext();
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                initQQ(applicationInfo);
                initWechat(applicationInfo);
                return;
            }
            LogUtils.i("未配置meta-data参数，初始化social失败");
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("初始化social失败");
        }
    }

    private static void initQQ(ApplicationInfo applicationInfo) {
        String string = applicationInfo.metaData.getString("qq_appid");
        if (TextUtils.isEmpty(string)) {
            LogUtils.i("未配置qq appid");
            return;
        }
        String strReplace = string.replace("tencent", "");
        if (!TextUtils.isEmpty(strReplace)) {
            PlatformConfig.setQQ(strReplace);
        } else {
            LogUtils.i("未配置qq appid");
        }
    }

    private static void initWechat(ApplicationInfo applicationInfo) {
        String string = applicationInfo.metaData.getString("wx_appid");
        if (!TextUtils.isEmpty(string)) {
            String string2 = applicationInfo.metaData.getString("wx_appkey");
            if (TextUtils.isEmpty(string2)) {
                LogUtils.i("未配置微信appkey,将无法使用二维码登录");
            }
            PlatformConfig.setWechat(string, string2);
            return;
        }
        LogUtils.i("未配置微信appid");
    }

    /* JADX INFO: renamed from: com.social.sdk.SocialApi$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$social$sdk$platform$PlatformType;

        static {
            int[] iArr = new int[PlatformType.values().length];
            $SwitchMap$com$social$sdk$platform$PlatformType = iArr;
            try {
                iArr[PlatformType.QQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$social$sdk$platform$PlatformType[PlatformType.QZONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$social$sdk$platform$PlatformType[PlatformType.WECHAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$social$sdk$platform$PlatformType[PlatformType.WECHAT_CIRCLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public SSOHandler getSSOHandler(PlatformType platformType) {
        if (this.ssoHandlerMap.get(platformType) == null) {
            int i = AnonymousClass1.$SwitchMap$com$social$sdk$platform$PlatformType[platformType.ordinal()];
            if (i == 1) {
                this.ssoHandlerMap.put(PlatformType.QQ, new QQHandler());
            } else if (i == 2) {
                this.ssoHandlerMap.put(PlatformType.QZONE, new QQHandler());
            } else if (i == 3) {
                this.ssoHandlerMap.put(PlatformType.WECHAT, new WechatHandler());
            } else if (i == 4) {
                this.ssoHandlerMap.put(PlatformType.WECHAT_CIRCLE, getSSOHandler(PlatformType.WECHAT));
            }
        }
        return this.ssoHandlerMap.get(platformType);
    }

    public void authorize(Activity activity, PlatformType platformType, OnAuthListener onAuthListener) {
        LogUtils.i("SocialApi authorize");
        SSOHandler sSOHandler = getSSOHandler(platformType);
        sSOHandler.onCreate(mContext, PlatformConfig.getPlatformConfig(platformType));
        sSOHandler.authorize(activity, onAuthListener);
    }

    public void share(Activity activity, PlatformType platformType, IShareMedia iShareMedia, ShareListener shareListener) {
        LogUtils.i("SocialApi share");
        SSOHandler sSOHandler = getSSOHandler(platformType);
        sSOHandler.onCreate(mContext, PlatformConfig.getPlatformConfig(platformType));
        sSOHandler.share(activity, iShareMedia, shareListener);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtils.i("SocialApi onActivityResult");
        Iterator<Map.Entry<PlatformType, SSOHandler>> it = this.ssoHandlerMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().onActivityResult(i, i2, intent);
        }
    }

    public boolean isInstall(PlatformType platformType) {
        LogUtils.i("SocialApi isInstall");
        SSOHandler sSOHandler = getSSOHandler(platformType);
        sSOHandler.onCreate(mContext, PlatformConfig.getPlatformConfig(platformType));
        return sSOHandler.isInstall();
    }
}
