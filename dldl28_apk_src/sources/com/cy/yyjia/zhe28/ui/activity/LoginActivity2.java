package com.cy.yyjia.zhe28.ui.activity;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import androidx.autofill.HintConstants;
import androidx.core.view.ViewCompat;
import com.cy.yyjia.zhe28.BuildConfig;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.FastDialog;
import com.cy.yyjia.zhe28.databinding.ActivityLoginBinding;
import com.cy.yyjia.zhe28.domain.LoginChangeBean;
import com.cy.yyjia.zhe28.domain.LoginResult;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.WxLoginBean;
import com.cy.yyjia.zhe28.domain.WxLoginResult;
import com.cy.yyjia.zhe28.domain.WxUserBean;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.google.gson.Gson;
import com.lzy.okgo.cookie.SerializableCookie;
import com.mobile.auth.gatewayauth.AuthUIConfig;
import com.mobile.auth.gatewayauth.AuthUIControlClickListener;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.TokenResultListener;
import com.mobile.auth.gatewayauth.model.TokenRet;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: LoginActivity2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u0006\u0010\u001b\u001a\u00020\u0019J\u0006\u0010\u001c\u001a\u00020\u0019J\u0006\u0010\u001d\u001a\u00020\u0019J\u000e\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u00020\u0019H\u0016J\u0010\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u0019H\u0014J\u000e\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020 J\u000e\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u0013J\u000e\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020,R#\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006-"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/LoginActivity2;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityLoginBinding;", "Landroid/view/View$OnClickListener;", "()V", "api", "Lcom/tencent/mm/opensdk/openapi/IWXAPI;", "kotlin.jvm.PlatformType", "getApi", "()Lcom/tencent/mm/opensdk/openapi/IWXAPI;", "api$delegate", "Lkotlin/Lazy;", "authHelper", "Lcom/mobile/auth/gatewayauth/PhoneNumberAuthHelper;", "getAuthHelper", "()Lcom/mobile/auth/gatewayauth/PhoneNumberAuthHelper;", "setAuthHelper", "(Lcom/mobile/auth/gatewayauth/PhoneNumberAuthHelper;)V", "loginType", "", "getLoginType", "()Ljava/lang/String;", "setLoginType", "(Ljava/lang/String;)V", "cancelLogin", "", "init", "initFastLogin", "initLoginUI", "login", "loginSuccess", "it", "Lcom/cy/yyjia/zhe28/domain/LoginResult;", "onBackPressed", "onClick", "v", "Landroid/view/View;", "onResume", "setCookie", "result", "tokenLogin", "token", "wxLogin", "wxLoginBean", "Lcom/cy/yyjia/zhe28/domain/WxLoginBean;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class LoginActivity2 extends BaseActivity<ActivityLoginBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: api$delegate, reason: from kotlin metadata */
    private final Lazy api;
    public PhoneNumberAuthHelper authHelper;
    private String loginType;

    public LoginActivity2() {
        super(R.layout.activity_login, 2);
        this.loginType = "acccount";
        this.api = LazyKt.lazy(new Function0<IWXAPI>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2$api$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final IWXAPI invoke() {
                return WXAPIFactory.createWXAPI(this.this$0, Constant.INSTANCE.getWxAppId(), true);
            }
        });
    }

    public static final /* synthetic */ ActivityLoginBinding access$getMBinding(LoginActivity2 loginActivity2) {
        return loginActivity2.getMBinding();
    }

    public final String getLoginType() {
        return this.loginType;
    }

    public final void setLoginType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.loginType = str;
    }

    private final IWXAPI getApi() {
        return (IWXAPI) this.api.getValue();
    }

    public final PhoneNumberAuthHelper getAuthHelper() {
        PhoneNumberAuthHelper phoneNumberAuthHelper = this.authHelper;
        if (phoneNumberAuthHelper != null) {
            return phoneNumberAuthHelper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("authHelper");
        return null;
    }

    public final void setAuthHelper(PhoneNumberAuthHelper phoneNumberAuthHelper) {
        Intrinsics.checkNotNullParameter(phoneNumberAuthHelper, "<set-?>");
        this.authHelper = phoneNumberAuthHelper;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setBackClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity2.init$lambda$0(this.f$0, view);
            }
        });
        Constant.INSTANCE.logout(this);
        getMBinding().setCheck(false);
        getMBinding().setPhone(false);
        SharedPreferences sharedPreferences = getSharedPreferences("user", 0);
        getMBinding().setUsername(sharedPreferences.getString(HintConstants.AUTOFILL_HINT_USERNAME, ""));
        getMBinding().setPassword(sharedPreferences.getString(HintConstants.AUTOFILL_HINT_PASSWORD, ""));
        initFastLogin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(LoginActivity2 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.cancelLogin();
    }

    public final void initFastLogin() {
        String string = getString(R.string.ali_app_secret);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        LoginActivity2 loginActivity2 = this;
        PhoneNumberAuthHelper phoneNumberAuthHelper = PhoneNumberAuthHelper.getInstance(loginActivity2, new TokenResultListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2$initFastLogin$resultListener$1
            @Override // com.mobile.auth.gatewayauth.TokenResultListener
            public void onTokenSuccess(String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                try {
                    TokenRet tokenRetFromJson = TokenRet.fromJson(s);
                    if (Intrinsics.areEqual(ResultCode.CODE_START_AUTHPAGE_SUCCESS, tokenRetFromJson.getCode())) {
                        this.this$0.log("唤起授权页成功：" + s);
                    }
                    if (Intrinsics.areEqual("600000", tokenRetFromJson.getCode())) {
                        LoginActivity2 loginActivity22 = this.this$0;
                        String token = tokenRetFromJson.getToken();
                        Intrinsics.checkNotNullExpressionValue(token, "getToken(...)");
                        loginActivity22.tokenLogin(token);
                        this.this$0.getAuthHelper().setAuthListener(null);
                        this.this$0.getAuthHelper().quitLoginPage();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LoginActivity2 loginActivity23 = this.this$0;
                    String localizedMessage = e.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    loginActivity23.toast(localizedMessage);
                }
            }

            @Override // com.mobile.auth.gatewayauth.TokenResultListener
            public void onTokenFailed(String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                this.this$0.log(s);
                this.this$0.getAuthHelper().quitLoginPage();
            }
        });
        Intrinsics.checkNotNullExpressionValue(phoneNumberAuthHelper, "getInstance(...)");
        setAuthHelper(phoneNumberAuthHelper);
        getAuthHelper().getReporter().setLoggerEnable(true);
        getAuthHelper().setAuthSDKInfo(string);
        getAuthHelper().checkEnvAvailable(2);
        initLoginUI();
        getAuthHelper().getLoginToken(loginActivity2, 5000);
    }

    public final void initLoginUI() {
        getAuthHelper().setAuthPageUseDayLight(false);
        getAuthHelper().setUIClickListener(new AuthUIControlClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2$$ExternalSyntheticLambda1
            @Override // com.mobile.auth.gatewayauth.AuthUIControlClickListener
            public final void onClick(String str, Context context, String str2) {
                LoginActivity2.initLoginUI$lambda$1(this.f$0, str, context, str2);
            }
        });
        getAuthHelper().setAuthUIConfig(new AuthUIConfig.Builder().setAppPrivacyOne("用户协议", "https://www.28zhe.com/help/agreement.htm").setAppPrivacyTwo("隐私政策", "https://www.28zhe.com/help/privacyPolicy.htm").setAppPrivacyColor(-7829368, Color.parseColor("#F86938")).setPrivacyState(false).setSwitchAccHidden(true).setLogBtnToastHidden(false).setStatusBarColor(0).setStatusBarUIFlag(1).setLightColor(false).setProtocolAction("com.cy.yyjia.zhw28.web").setNavColor(-1).setNavTextColor(ViewCompat.MEASURED_STATE_MASK).setWebNavTextSizeDp(20).setLogBtnBackgroundPath("bg_button").setSwitchAccHidden(false).create());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final void initLoginUI$lambda$1(LoginActivity2 this$0, String str, Context context, String str2) {
        JSONObject jSONObject;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            jSONObject = !TextUtils.isEmpty(str2) ? new JSONObject(str2) : null;
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
        }
        if (str != null) {
            switch (str.hashCode()) {
                case 1620409945:
                    if (str.equals(ResultCode.CODE_ERROR_USER_CANCEL)) {
                        this$0.getAuthHelper().quitLoginPage();
                        break;
                    }
                    break;
                case 1620409946:
                    if (str.equals(ResultCode.CODE_ERROR_USER_SWITCH)) {
                        this$0.getAuthHelper().quitLoginPage();
                        break;
                    }
                    break;
                case 1620409947:
                    if (str.equals(ResultCode.CODE_ERROR_USER_LOGIN_BTN)) {
                        Intrinsics.checkNotNull(jSONObject);
                        if (!jSONObject.optBoolean("isChecked")) {
                            this$0.toast("同意服务条款才可以登录");
                        }
                        break;
                    }
                    break;
                case 1620409948:
                    if (str.equals(ResultCode.CODE_ERROR_USER_CHECKBOX)) {
                        Intrinsics.checkNotNull(jSONObject);
                        this$0.log("checkbox状态变为" + jSONObject.optBoolean("isChecked"));
                        break;
                    }
                    break;
                case 1620409949:
                    if (str.equals(ResultCode.CODE_ERROR_USER_PROTOCOL_CONTROL)) {
                        Intrinsics.checkNotNull(jSONObject);
                        this$0.log("点击协议，name: " + jSONObject.optString("name") + ", url: " + jSONObject.optString("url"));
                        break;
                    }
                    break;
            }
        }
    }

    public final void login() {
        if (getMBinding().getPhone()) {
            Repository repository = Repository.INSTANCE;
            String username = getMBinding().getUsername();
            Intrinsics.checkNotNull(username);
            String yzm = getMBinding().getYzm();
            Intrinsics.checkNotNull(yzm);
            repository.codeLogin(username, yzm, new Function1<LoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.login.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LoginResult loginResult) {
                    invoke2(loginResult);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LoginResult it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    LoginActivity2.this.loginSuccess(it);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.login.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    LoginActivity2.this.netFail(it);
                }
            });
            return;
        }
        Repository repository2 = Repository.INSTANCE;
        String username2 = getMBinding().getUsername();
        Intrinsics.checkNotNull(username2);
        String password = getMBinding().getPassword();
        Intrinsics.checkNotNull(password);
        repository2.login(username2, password, new Function1<LoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.login.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoginResult loginResult) {
                invoke2(loginResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoginResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LoginActivity2.this.loginSuccess(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.login.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LoginActivity2.this.netFail(it);
            }
        });
    }

    public final void tokenLogin(String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        Repository.INSTANCE.fastLogin(token, new Function1<LoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.tokenLogin.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoginResult loginResult) {
                invoke2(loginResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoginResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LoginActivity2.this.setLoginType("token");
                LoginActivity2.this.loginSuccess(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.tokenLogin.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LoginActivity2.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.btn_code /* 2131361956 */:
                String username = getMBinding().getUsername();
                Intrinsics.checkNotNull(username);
                if (!TextUtils.isEmpty(username)) {
                    Repository repository = Repository.INSTANCE;
                    String username2 = getMBinding().getUsername();
                    Intrinsics.checkNotNull(username2);
                    repository.getVerifyCode("login", username2, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.onClick.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                            invoke2(result);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Result it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            LoginActivity2.this.toast(it.getMsg());
                        }
                    }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.onClick.2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                            invoke2(exc);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Exception it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            LoginActivity2.access$getMBinding(LoginActivity2.this).btnCode.resetState();
                            LoginActivity2.this.netFail(it);
                        }
                    });
                } else {
                    toast("请输入手机号");
                }
                break;
            case R.id.ll_confirm /* 2131362251 */:
                getMBinding().setCheck(!getMBinding().getCheck());
                break;
            case R.id.tv_change /* 2131362668 */:
                getMBinding().setPhone(!getMBinding().getPhone());
                if (getMBinding().getPhone()) {
                    this.loginType = HintConstants.AUTOFILL_HINT_PHONE;
                } else {
                    this.loginType = "account";
                }
                break;
            case R.id.tv_fast /* 2131362698 */:
                getAuthHelper().getLoginToken(this, 5000);
                break;
            case R.id.tv_forget /* 2131362706 */:
                startActivity(new Intent(this, (Class<?>) ChangePasswordActivity.class).putExtra("forget", true));
                break;
            case R.id.tv_login /* 2131362721 */:
                if (getMBinding().getCheck()) {
                    login();
                } else {
                    toast("请先阅读并同意用户协议隐私政策");
                }
                break;
            case R.id.tv_privacy /* 2131362751 */:
                Util.openProtocol(this, "隐私政策", "privacyPolicy");
                break;
            case R.id.tv_user /* 2131362804 */:
                Util.openProtocol(this, "用户协议", "userAgreement");
                break;
            case R.id.tv_wx /* 2131362809 */:
                if (getMBinding().getCheck()) {
                    if (getApi().isWXAppInstalled()) {
                        SendAuth.Req req = new SendAuth.Req();
                        req.scope = "snsapi_userinfo";
                        req.state = "28zhe";
                        getApi().sendReq(req);
                    } else {
                        toast("请先安装微信");
                    }
                } else {
                    toast("请先阅读并同意用户协议隐私政策");
                }
                break;
        }
    }

    public final void wxLogin(WxLoginBean wxLoginBean) {
        Intrinsics.checkNotNullParameter(wxLoginBean, "wxLoginBean");
        Repository.INSTANCE.getWxAccessToken(wxLoginBean.getCode(), new Function1<WxLoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.wxLogin.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WxLoginResult wxLoginResult) {
                invoke2(wxLoginResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WxLoginResult result1) {
                Intrinsics.checkNotNullParameter(result1, "result1");
                if (result1.getRefresh_token() != null) {
                    Repository repository = Repository.INSTANCE;
                    String refresh_token = result1.getRefresh_token();
                    final LoginActivity2 loginActivity2 = LoginActivity2.this;
                    Function1<WxLoginResult, Unit> function1 = new Function1<WxLoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.wxLogin.1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(WxLoginResult wxLoginResult) {
                            invoke2(wxLoginResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(WxLoginResult result2) {
                            Intrinsics.checkNotNullParameter(result2, "result2");
                            if (result2.getAccess_token() != null) {
                                Repository repository2 = Repository.INSTANCE;
                                String access_token = result2.getAccess_token();
                                String openid = result2.getOpenid();
                                Intrinsics.checkNotNull(openid);
                                final LoginActivity2 loginActivity22 = loginActivity2;
                                Function1<WxUserBean, Unit> function12 = new Function1<WxUserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.wxLogin.1.1.1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(WxUserBean wxUserBean) {
                                        invoke2(wxUserBean);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(WxUserBean wxUser) {
                                        Intrinsics.checkNotNullParameter(wxUser, "wxUser");
                                        Repository repository3 = Repository.INSTANCE;
                                        String openid2 = wxUser.getOpenid();
                                        Intrinsics.checkNotNull(openid2);
                                        String nickname = wxUser.getNickname();
                                        Intrinsics.checkNotNull(nickname);
                                        String headimgurl = wxUser.getHeadimgurl();
                                        Intrinsics.checkNotNull(headimgurl);
                                        String unionid = wxUser.getUnionid();
                                        Intrinsics.checkNotNull(unionid);
                                        final LoginActivity2 loginActivity23 = loginActivity22;
                                        Function1<LoginResult, Unit> function13 = new Function1<LoginResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.wxLogin.1.1.1.1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(LoginResult loginResult) {
                                                invoke2(loginResult);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(LoginResult it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                loginActivity23.setLoginType("wx");
                                                loginActivity23.loginSuccess(it);
                                            }
                                        };
                                        final LoginActivity2 loginActivity24 = loginActivity22;
                                        repository3.wxLogin(openid2, nickname, headimgurl, unionid, function13, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.wxLogin.1.1.1.2
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                                                invoke2(exc);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Exception it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                loginActivity24.netFail(it);
                                            }
                                        });
                                    }
                                };
                                final LoginActivity2 loginActivity23 = loginActivity2;
                                repository2.getWxUser(access_token, openid, function12, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.wxLogin.1.1.2
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                                        invoke2(exc);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Exception it) {
                                        Intrinsics.checkNotNullParameter(it, "it");
                                        loginActivity23.netFail(it);
                                    }
                                });
                                return;
                            }
                            LoginActivity2 loginActivity24 = loginActivity2;
                            String errmsg = result2.getErrmsg();
                            Intrinsics.checkNotNull(errmsg);
                            loginActivity24.toast(errmsg);
                        }
                    };
                    final LoginActivity2 loginActivity22 = LoginActivity2.this;
                    repository.refreshWxAccessToken(refresh_token, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.wxLogin.1.2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                            invoke2(exc);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Exception it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            loginActivity22.netFail(it);
                        }
                    });
                    return;
                }
                LoginActivity2 loginActivity23 = LoginActivity2.this;
                String errmsg = result1.getErrmsg();
                Intrinsics.checkNotNull(errmsg);
                loginActivity23.toast(errmsg);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2.wxLogin.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LoginActivity2.this.netFail(it);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(Constant.INSTANCE.getWxLoginCode())) {
            return;
        }
        wxLogin(new WxLoginBean(Constant.INSTANCE.getWxLoginCode()));
    }

    public final void loginSuccess(LoginResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ContentValues contentValues = new ContentValues();
        contentValues.put("str", it.getToken());
        getContentResolver().insert(Uri.parse("content://com.cy.yyjia.zhe28.loginprovider/login"), contentValues);
        final Constant constant = Constant.INSTANCE;
        constant.setWxLoginCode("");
        setCookie(it);
        constant.setLogged(true);
        constant.setShowMainAd(true);
        constant.setToken(it.getToken());
        constant.setUsername(it.getUser().getUserName());
        constant.setId(it.getUser().getUid());
        constant.setCookieString(it.getCookie().toString());
        Integer unset_status = it.getUser().getUnset_status();
        if (unset_status != null && unset_status.intValue() == 0) {
            EventBus.getDefault().post(new LoginChangeBean());
            SharedPreferences.Editor editorEdit = getSharedPreferences("user", 0).edit();
            editorEdit.putBoolean("logged", true);
            editorEdit.putInt("id", constant.getId());
            editorEdit.putString(HintConstants.AUTOFILL_HINT_USERNAME, constant.getUsername());
            editorEdit.putString(HintConstants.AUTOFILL_HINT_PASSWORD, getMBinding().getPassword());
            editorEdit.putString("token", constant.getToken());
            editorEdit.putString(SerializableCookie.COOKIE, constant.getCookieString());
            editorEdit.putString("loginType", this.loginType);
            editorEdit.putString(HintConstants.AUTOFILL_HINT_PHONE, it.getUser().getTelphone());
            editorEdit.putString("idCard", it.getUser().getIdCard());
            editorEdit.putString("realName", it.getUser().getRealName());
            editorEdit.putString("age", it.getUser().getAge());
            editorEdit.commit();
            if (getIntent().getStringExtra("url") != null) {
                Util.openWebWithLogin(getMContext(), getIntent().getStringExtra("title"), getIntent().getStringExtra("url"));
            }
            String stringExtra = getIntent().getStringExtra("next");
            if (stringExtra != null) {
                startActivity(new Intent().setComponent(new ComponentName(BuildConfig.APPLICATION_ID, stringExtra)));
            }
            setResult(1001, new Intent().putExtra("token", constant.getToken()));
            finish();
            return;
        }
        Integer unset_status2 = it.getUser().getUnset_status();
        if (unset_status2 != null && unset_status2.intValue() == 1) {
            new FastDialog(getMContext()).setContentView(R.layout.dialog_cancellation_tips).setOnClickListener(R.id.tv_go, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2$$ExternalSyntheticLambda2
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    LoginActivity2.loginSuccess$lambda$6$lambda$3(this.f$0, baseDialog, view);
                }
            }).setOnClickListener(R.id.tv_cancel, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2$$ExternalSyntheticLambda3
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    baseDialog.dismiss();
                }
            }).addOnDismissListener(new BaseDialog.OnDismissListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2$$ExternalSyntheticLambda4
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnDismissListener
                public final void onDismiss(BaseDialog baseDialog) {
                    LoginActivity2.loginSuccess$lambda$6$lambda$5(constant, this, baseDialog);
                }
            }).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loginSuccess$lambda$6$lambda$3(final LoginActivity2 this$0, final BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Repository.INSTANCE.cancelUnsetAccount(new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2$loginSuccess$1$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                invoke2(result);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Result result) {
                Intrinsics.checkNotNullParameter(result, "result");
                this.this$0.toast(result.getMsg());
                if (result.getCode() == 200) {
                    baseDialog.dismiss();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2$loginSuccess$1$2$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                this.this$0.netFail(e);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loginSuccess$lambda$6$lambda$5(Constant this_run, LoginActivity2 this$0, BaseDialog baseDialog) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_run.logout(this$0.getMContext());
    }

    public final void setCookie(LoginResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookie();
        for (LoginResult.CookieBean cookieBean : result.getCookie()) {
            final String str = StringsKt.trim((CharSequence) cookieBean.getName()).toString() + "=" + StringsKt.trim((CharSequence) cookieBean.getValue()).toString();
            cookieManager.setCookie(NetUtil.BASE_URL3, str, new ValueCallback() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity2$$ExternalSyntheticLambda5
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    LoginActivity2.setCookie$lambda$7(this.f$0, str, (Boolean) obj);
                }
            });
        }
        cookieManager.setCookie(NetUtil.BASE_URL3, "payversion=2");
        cookieManager.setCookie(NetUtil.BASE_URL3, "wancms=" + new Gson().toJson(result));
        cookieManager.flush();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setCookie$lambda$7(LoginActivity2 this$0, String cookie, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cookie, "$cookie");
        this$0.log("setCookie:" + cookie + "  " + bool);
    }

    public final void cancelLogin() {
        setResult(7892);
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        cancelLogin();
    }
}
