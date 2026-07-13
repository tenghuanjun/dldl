package com.mobile.auth.gatewayauth;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.BuildConfig;
import com.mobile.auth.gatewayauth.manager.compat.ResultCodeProcessor;
import com.mobile.auth.gatewayauth.model.LoginPhoneInfo;
import com.mobile.auth.gatewayauth.model.UStruct;
import com.mobile.auth.gatewayauth.utils.g;
import com.nirvana.tools.core.ExecutorManager;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class PhoneNumberAuthHelper {
    public static final int SERVICE_TYPE_AUTH = 1;
    public static final int SERVICE_TYPE_LOGIN = 2;
    protected static volatile PhoneNumberAuthHelper a;
    private TokenResultListener b;
    private d c;
    private ResultCodeProcessor d = new com.mobile.auth.gatewayauth.manager.compat.a();
    private PhoneNumberAuthHelperProxy e;

    private PhoneNumberAuthHelper(Context context, TokenResultListener tokenResultListener) {
        this.e = PhoneNumberAuthHelperProxy.getInstance(context, tokenResultListener);
        this.b = tokenResultListener;
        this.c = new d(context, this.e.b(), this.e.a(), this);
    }

    static /* synthetic */ ResultCodeProcessor a(PhoneNumberAuthHelper phoneNumberAuthHelper) {
        try {
            return phoneNumberAuthHelper.d;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    private boolean a(long j, final String str, final ResultCodeProcessor resultCodeProcessor, final TokenResultListener tokenResultListener, LoginPhoneInfo loginPhoneInfo, final String str2) {
        if (loginPhoneInfo != null) {
            try {
                if (!TextUtils.isEmpty(loginPhoneInfo.getPhoneNumber())) {
                    try {
                        this.c.a(j, loginPhoneInfo.getPhoneNumber(), str, resultCodeProcessor, new e() { // from class: com.mobile.auth.gatewayauth.PhoneNumberAuthHelper.3
                            @Override // com.mobile.auth.gatewayauth.e
                            public void a(String str3) {
                                try {
                                    PhoneNumberAuthHelper.b(PhoneNumberAuthHelper.this).a(false, true, Constant.CODE_ERROR_START_AUTH_PAGE_FAIL, "唤起授权页失败", com.mobile.auth.gatewayauth.utils.a.a(Constant.CODE_ERROR_START_AUTH_PAGE_FAIL, "唤起授权页失败"), str, null, tokenResultListener, resultCodeProcessor, str2);
                                } catch (Throwable th) {
                                    ExceptionProcessor.processException(th);
                                }
                            }

                            @Override // com.mobile.auth.gatewayauth.e
                            public void a(String str3, String str4) {
                                try {
                                    PhoneNumberAuthHelper.b(PhoneNumberAuthHelper.this).a(true, false, Constant.CODE_START_AUTH_PAGE_SUCCESS, "唤起授权页成功", "", str, null, tokenResultListener, resultCodeProcessor, str2);
                                } catch (Throwable th) {
                                    ExceptionProcessor.processException(th);
                                }
                            }
                        });
                        return true;
                    } catch (Throwable th) {
                        th = th;
                        ExceptionProcessor.processException(th);
                        return false;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return false;
    }

    static /* synthetic */ boolean a(PhoneNumberAuthHelper phoneNumberAuthHelper, long j, String str, ResultCodeProcessor resultCodeProcessor, TokenResultListener tokenResultListener, LoginPhoneInfo loginPhoneInfo, String str2) {
        try {
            return phoneNumberAuthHelper.a(j, str, resultCodeProcessor, tokenResultListener, loginPhoneInfo, str2);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    static /* synthetic */ PhoneNumberAuthHelperProxy b(PhoneNumberAuthHelper phoneNumberAuthHelper) {
        try {
            return phoneNumberAuthHelper.e;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    static /* synthetic */ d c(PhoneNumberAuthHelper phoneNumberAuthHelper) {
        try {
            return phoneNumberAuthHelper.c;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    static /* synthetic */ TokenResultListener d(PhoneNumberAuthHelper phoneNumberAuthHelper) {
        try {
            return phoneNumberAuthHelper.b;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static PhoneNumberAuthHelper getInstance(Context context, TokenResultListener tokenResultListener) {
        try {
            if (a == null && context != null) {
                synchronized (PhoneNumberAuthHelper.class) {
                    if (a == null) {
                        a = new PhoneNumberAuthHelper(context, tokenResultListener);
                    }
                }
            }
            a.setAuthListener(tokenResultListener);
            return a;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static String getVersion() {
        return BuildConfig.VERSION_NAME;
    }

    void a(long j, TokenResultListener tokenResultListener, ResultCodeProcessor resultCodeProcessor) {
        try {
            this.e.a(j, tokenResultListener, resultCodeProcessor);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void accelerateLoginPage(int i, PreLoginResultListener preLoginResultListener) {
        try {
            this.e.accelerateLoginPage(i, preLoginResultListener, this.c.C());
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void accelerateVerify(int i, PreLoginResultListener preLoginResultListener) {
        try {
            this.e.accelerateVerify(i, preLoginResultListener);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void addAuthRegistViewConfig(String str, AuthRegisterViewConfig authRegisterViewConfig) {
        try {
            this.c.a(str, authRegisterViewConfig);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void addAuthRegisterXmlConfig(AuthRegisterXmlConfig authRegisterXmlConfig) {
        try {
            this.c.a(authRegisterXmlConfig);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void addPrivacyAuthRegistViewConfig(String str, AuthRegisterViewConfig authRegisterViewConfig) {
        try {
            this.c.b(str, authRegisterViewConfig);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void addPrivacyRegisterXmlConfig(AuthRegisterXmlConfig authRegisterXmlConfig) {
        try {
            this.c.b(authRegisterXmlConfig);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void checkBoxAnimationStart() {
        try {
            this.c.l();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void checkEnvAvailable(int i) {
        try {
            this.e.checkEnvAvailable(i, this.b);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    @Deprecated
    public boolean checkEnvAvailable() {
        try {
            return this.e.checkEnvAvailable();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public void clearPreInfo() {
        try {
            this.e.clearPreInfo();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void closeAuthPageReturnBack(boolean z) {
        try {
            d dVar = this.c;
            if (dVar != null) {
                dVar.a(z);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void expandAuthPageCheckedScope(boolean z) {
        try {
            d dVar = this.c;
            if (dVar != null) {
                dVar.c(z);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public String getCurrentCarrierName() {
        try {
            return this.e.getCurrentCarrierName();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public void getLoginToken(final Context context, final int i) {
        try {
            ExecutorManager.getInstance().scheduleFuture(new g.a() { // from class: com.mobile.auth.gatewayauth.PhoneNumberAuthHelper.2
                @Override // com.mobile.auth.gatewayauth.utils.g.a
                protected void a() {
                    try {
                        if (context instanceof Activity) {
                            PhoneNumberAuthHelper.c(PhoneNumberAuthHelper.this).b((Activity) context);
                        }
                        final String strC = PhoneNumberAuthHelper.b(PhoneNumberAuthHelper.this).a().c();
                        final String strJ = PhoneNumberAuthHelper.b(PhoneNumberAuthHelper.this).b().j();
                        if (!com.mobile.auth.gatewayauth.utils.d.a().b()) {
                            PhoneNumberAuthHelper.b(PhoneNumberAuthHelper.this).a(true, false, Constant.CODE_ERROR_START_AUTH_PAGE_FAIL, Constant.MSG_ERROR_AUTHPAGE_FAIL, "", strC, null, PhoneNumberAuthHelper.d(PhoneNumberAuthHelper.this), PhoneNumberAuthHelper.a(PhoneNumberAuthHelper.this), strJ);
                        } else {
                            com.mobile.auth.gatewayauth.utils.d.a().a(false);
                            PhoneNumberAuthHelper.b(PhoneNumberAuthHelper.this).getLoginMaskPhone(i, strC, new OnLoginPhoneListener() { // from class: com.mobile.auth.gatewayauth.PhoneNumberAuthHelper.2.1
                                @Override // com.mobile.auth.gatewayauth.OnLoginPhoneListener
                                public void onGetFailed(String str) {
                                    try {
                                        com.mobile.auth.gatewayauth.utils.d.a().a(true);
                                        if (PhoneNumberAuthHelper.d(PhoneNumberAuthHelper.this) != null) {
                                            PhoneNumberAuthHelper.d(PhoneNumberAuthHelper.this).onTokenFailed(str);
                                        }
                                    } catch (Throwable th) {
                                        ExceptionProcessor.processException(th);
                                    }
                                }

                                @Override // com.mobile.auth.gatewayauth.OnLoginPhoneListener
                                public void onGetLoginPhone(LoginPhoneInfo loginPhoneInfo) {
                                    try {
                                        PhoneNumberAuthHelper.a(PhoneNumberAuthHelper.this, i, strC, PhoneNumberAuthHelper.a(PhoneNumberAuthHelper.this), PhoneNumberAuthHelper.d(PhoneNumberAuthHelper.this), loginPhoneInfo, strJ);
                                    } catch (Throwable th) {
                                        ExceptionProcessor.processException(th);
                                    }
                                }
                            }, false, true, strJ);
                        }
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public PnsReporter getReporter() {
        try {
            return this.e.getReporter();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public void getVerifyToken(int i) {
        try {
            this.e.getVerifyToken(i, this.b);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void hideLoginLoading() {
        try {
            this.c.j();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void keepAllPageHideNavigationBar() {
        try {
            d dVar = this.c;
            if (dVar != null) {
                dVar.c();
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void keepAuthPageLandscapeFullSreen(boolean z) {
        try {
            d dVar = this.c;
            if (dVar != null) {
                dVar.b(z);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void openUserPage(Class<?> cls, int i, int i2) {
        try {
            this.c.a(cls, i, i2);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void privacyAnimationStart() {
        try {
            this.c.k();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void prohibitUseUtdid() {
        try {
            this.e.prohibitUseUtdid();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public boolean queryCheckBoxIsChecked() {
        try {
            return this.c.m();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public void quitLoginPage() {
        try {
            final long jCurrentTimeMillis = System.currentTimeMillis();
            this.c.a(this.d);
            this.c.n();
            com.mobile.auth.gatewayauth.utils.d.a().a(true);
            final long jCurrentTimeMillis2 = System.currentTimeMillis();
            ExecutorManager.getInstance().scheduleFuture(new Runnable() { // from class: com.mobile.auth.gatewayauth.PhoneNumberAuthHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        PhoneNumberAuthHelper.b(PhoneNumberAuthHelper.this).c().a(PhoneNumberAuthHelper.b(PhoneNumberAuthHelper.this).b().b("", Constant.ACTION_SDK_QUIT_AUTH_PAGE, UStruct.newUStruct().startTime(jCurrentTimeMillis).endTime(jCurrentTimeMillis2).build(), PhoneNumberAuthHelper.a(PhoneNumberAuthHelper.this).getApiLevel()), 2);
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void quitPrivacyPage() {
        try {
            this.c.z();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void removeAuthRegisterViewConfig() {
        try {
            this.c.v();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void removeAuthRegisterXmlConfig() {
        try {
            this.c.y();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void removePrivacyAuthRegisterViewConfig() {
        try {
            this.c.w();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void removePrivacyRegisterXmlConfig() {
        try {
            this.c.x();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void setActivityResultListener(ActivityResultListener activityResultListener) {
        try {
            this.c.a(activityResultListener);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void setAuthListener(TokenResultListener tokenResultListener) {
        try {
            this.b = tokenResultListener;
            this.e.setAuthListener(tokenResultListener);
            this.c.a(tokenResultListener);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void setAuthPageUseDayLight(boolean z) {
        try {
            this.c.e(z);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void setAuthSDKInfo(String str) {
        try {
            this.e.setAuthSDKInfo(str);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void setAuthUIConfig(AuthUIConfig authUIConfig) {
        try {
            this.c.a(authUIConfig);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void setProtocolChecked(boolean z) {
        try {
            this.c.f(z);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void setUIClickListener(AuthUIControlClickListener authUIControlClickListener) {
        try {
            this.c.a(authUIControlClickListener);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public void userControlAuthPageCancel() {
        try {
            d dVar = this.c;
            if (dVar != null) {
                dVar.d(true);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }
}
