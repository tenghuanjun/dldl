package com.sy37sdk.account.alifast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.LoginAuthActivity;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.mod.account.ILoginListener;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.track.SqTrackPage;
import com.sqwan.common.util.CheckClassUtils;
import com.sqwan.common.util.SDKError;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.LoginTractionManager;
import com.sy37sdk.account.UserInfo;
import com.sy37sdk.account.alifast.FastLoginConstants;
import com.sy37sdk.account.alifast.FastLoginManager;
import com.sy37sdk.account.entrance.EntranceManager;
import com.sy37sdk.account.trackaction.PageExposureTrackManager;
import com.sy37sdk.account.util.AccountUtil;
import com.sy37sdk.account.view.LoginSkinHelper;
import com.sy37sdk.account.view.base.manager.AccountLoginAttachManager;
import com.sy37sdk.account.view.uifast.AccountLoginDialog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountLoginManager {
    private static final String TAG = "【Login Manager】";
    private static AccountLoginManager instance;
    private final Application.ActivityLifecycleCallbacks activityListener = new Application.ActivityLifecycleCallbacks() { // from class: com.sy37sdk.account.alifast.AccountLoginManager.7
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (AccountLoginManager.this.isLoginAuthActivityExist() && (activity instanceof LoginAuthActivity)) {
                SQLog.v("【Login Manager】阿里闪验登录授权页面onActivityCreated: " + activity);
                AccountLoginManager.this.loginAuthActivity = activity;
                FastLoginManager.getInstance(AccountLoginManager.this.mContext).setActivity((Activity) AccountLoginManager.this.loginAuthActivity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            if (AccountLoginManager.this.isLoginAuthActivityExist() && (activity instanceof LoginAuthActivity)) {
                SQLog.d("【Login Manager】阿里闪验登录授权页面onActivityResumed: " + activity);
                AccountLoginManager.this.loginAuthActivityShow = true;
                try {
                    new Handler().postDelayed(new Runnable() { // from class: com.sy37sdk.account.alifast.AccountLoginManager.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SQLog.d("【Login Manager】往闪验授权页面插入自定义UI");
                            if (AccountLoginManager.this.attachManager == null) {
                                AccountLoginManager.this.attachManager = new AccountLoginAttachManager(AccountLoginManager.this.loginAuthActivity);
                            }
                            if (!AccountLoginManager.this.attachManager.isShown()) {
                                AccountLoginManager.this.attachManager.attachView();
                            }
                            PageExposureTrackManager.track("view01", SqTrackPage.SqTrackViewName.ali_fast);
                        }
                    }, 500L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (AccountLoginManager.this.isLoginAuthActivityExist() && (activity instanceof LoginAuthActivity)) {
                AccountLoginManager.this.loginAuthActivityShow = false;
                AccountLoginManager.this.unRegisterLoginAuthActivityListener();
                AccountLoginManager.this.attachManager = null;
            }
        }
    };
    private AccountLoginAttachManager attachManager;
    private Context loginAuthActivity;
    private boolean loginAuthActivityShow;
    private ILoginListener loginListener;
    private Context mContext;

    private AccountLoginManager(Context context) {
        this.mContext = context;
    }

    public static AccountLoginManager getInstance(Context context) {
        if (instance == null) {
            synchronized (AccountLoginManager.class) {
                if (instance == null) {
                    instance = new AccountLoginManager(context);
                }
            }
        }
        return instance;
    }

    public void login(final ILoginListener iLoginListener) {
        LoginSkinHelper.init();
        this.loginListener = iLoginListener;
        final UserInfo lastUserInfo = AccountUtil.getLastUserInfo(this.mContext);
        AccountUtil.setNotSupportFast(false);
        if (!AccountUtil.checkToFastLogin()) {
            AccountUtil.setCanFastBack(false);
        }
        if (lastUserInfo != null && !TextUtils.isEmpty(lastUserInfo.getUname()) && !AccountUtil.checkToFastLogin()) {
            SQLog.i("【Login Manager】存在历史账号: " + lastUserInfo);
            startAccountLoginDialog(iLoginListener);
            return;
        }
        if (EntranceManager.getInstance().supportWxEntrance(this.mContext) && EntranceManager.getInstance().getWxLoginUIVersion() == 1 && !AccountUtil.checkToFastLogin()) {
            SQLog.w("【Login Manager】开启了微信登录, 不走闪验");
            startAccountLoginDialog(iLoginListener);
            return;
        }
        if (AccountUtil.checkToFastLogin()) {
            AccountUtil.setToFastLogin(false);
        }
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.invoke_ali_fast_login);
        try {
            if (!isLoginAuthActivityExist()) {
                SQLog.w("【Login Manager】不满足闪验版本, 跳过闪验登录");
                HashMap map = new HashMap();
                map.put(SqTrackKey.fail_code, FastLoginConstants.Code.FAILURE_NOT_SUPPORT);
                map.put(SqTrackKey.reason_fail, "不满足闪验版本");
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.ali_fast_login_fail, map);
                startAccountLoginDialog(iLoginListener);
                return;
            }
            registerLoginAuthActivityListener();
            LoginTractionManager.trackInvoke("2", "3");
            SQLog.d("【Login Manager】触发闪验登录");
            FastLoginManager.getInstance(this.mContext).doFastVerifyLogin(new FastLoginManager.FastLoginListener() { // from class: com.sy37sdk.account.alifast.AccountLoginManager.1
                @Override // com.sy37sdk.account.alifast.FastLoginManager.FastLoginListener
                public void onFastLoginSuccess(Map<String, String> map2) {
                    SQLog.d("【Login Manager】闪验登录成功, " + map2);
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.ali_fast_login_succ);
                    if (AccountLoginManager.this.loginListener != null) {
                        AccountLoginManager.this.loginListener.onSuccess(map2);
                    }
                }

                @Override // com.sy37sdk.account.alifast.FastLoginManager.FastLoginListener
                public void onFastLoginFail(Bundle bundle) {
                    UserInfo userInfo;
                    UserInfo userInfo2;
                    UserInfo userInfo3;
                    UserInfo userInfo4;
                    if (bundle != null && !TextUtils.isEmpty(bundle.getString("code"))) {
                        String string = bundle.getString("code");
                        String string2 = bundle.getString("msg");
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("code", string);
                            jSONObject.put("msg", string2);
                            jSONObject.toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        HashMap map2 = new HashMap();
                        map2.put(SqTrackKey.fail_code, string);
                        map2.put(SqTrackKey.reason_fail, string2);
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.ali_fast_login_fail, map2);
                        LoginTractionManager.trackFail("2", "3", string, string2);
                        if (string.equals("5")) {
                            SQLog.w("【Login Manager】闪验取消登录");
                            AccountLoginManager.this.loginListener.onFailure(SDKError.ACCOUNT_LOGIN_CANCEL.code, SDKError.ACCOUNT_LOGIN_CANCEL.message);
                            return;
                        }
                        if (string.equals(FastLoginConstants.Code.FAILURE_CLICK_OTHER_WAY)) {
                            SQLog.w("【Login Manager】闪验切换登录方式");
                            AccountLoginManager.this.startAccountLoginDialog(true, iLoginListener);
                            return;
                        }
                        if (string.equals(FastLoginConstants.Code.FAILURE_CLICK_ACCOUNT_LOGIN)) {
                            SQLog.w("【Login Manager】闪验点击账号密码登录");
                            AccountLoginManager.this.startAccountLoginDialogWithAccountPage(iLoginListener);
                            return;
                        }
                        if (string.equals(FastLoginConstants.Code.FAILURE_CLICK_BACK)) {
                            SQLog.w("【Login Manager】闪验返回登录");
                            AccountLoginManager.this.startAccountLoginDialog(iLoginListener);
                            return;
                        }
                        if (string.equals(FastLoginConstants.Code.FAILURE_NOT_SUPPORT)) {
                            SQLog.w("【Login Manager】版本不支持闪验");
                            AccountUtil.setNotSupportFast(true);
                            if (!EntranceManager.getInstance().isWxLoginEntrance() || (userInfo4 = lastUserInfo) == null || TextUtils.isEmpty(userInfo4.getUname())) {
                                AccountLoginManager.this.startPhoneLoginDialogWithBack(iLoginListener);
                                return;
                            } else {
                                AccountUtil.setToWeiChatLogin(true);
                                AccountLoginManager.this.startWeiChatLoginDialogWithBack(iLoginListener);
                                return;
                            }
                        }
                        if (string.equals("600012")) {
                            SQLog.w("【Login Manager】闪验应用无效");
                            AccountUtil.setNotSupportFast(true);
                            if (!EntranceManager.getInstance().isWxLoginEntrance() || (userInfo3 = lastUserInfo) == null || TextUtils.isEmpty(userInfo3.getUname())) {
                                AccountLoginManager.this.startPhoneLoginDialogWithBack(iLoginListener);
                                return;
                            } else {
                                AccountUtil.setToWeiChatLogin(true);
                                AccountLoginManager.this.startWeiChatLoginDialogWithBack(iLoginListener);
                                return;
                            }
                        }
                        if (string.equals("600011")) {
                            SQLog.w("【Login Manager】闪验应用无效");
                            if (AccountLoginManager.this.loginAuthActivityShow) {
                                SQLog.w("【Login Manager】阿里授权页已展示，需要关闭");
                                ToastUtil.showToast("游戏版本过低，请重新下载最新包体或者选择其他方式登录");
                                return;
                            }
                            AccountUtil.setNotSupportFast(true);
                            if (!EntranceManager.getInstance().isWxLoginEntrance() || (userInfo2 = lastUserInfo) == null || TextUtils.isEmpty(userInfo2.getUname())) {
                                AccountLoginManager.this.startPhoneLoginDialogWithBack(iLoginListener);
                                return;
                            } else {
                                AccountUtil.setToWeiChatLogin(true);
                                AccountLoginManager.this.startWeiChatLoginDialogWithBack(iLoginListener);
                                return;
                            }
                        }
                        SQLog.w("【Login Manager】闪验登录失败, code=" + string + ", msg=" + string2);
                        if (!AccountLoginManager.this.loginAuthActivityShow) {
                            SQLog.w("【Login Manager】阿里授权页未展示, 走37登录");
                            AccountUtil.setNotSupportFast(true);
                            if (!EntranceManager.getInstance().isWxLoginEntrance() || (userInfo = lastUserInfo) == null || TextUtils.isEmpty(userInfo.getUname())) {
                                AccountLoginManager.this.startPhoneLoginDialogWithBack(iLoginListener);
                                return;
                            } else {
                                AccountUtil.setToWeiChatLogin(true);
                                AccountLoginManager.this.startWeiChatLoginDialogWithBack(iLoginListener);
                                return;
                            }
                        }
                        SQLog.w("【Login Manager】阿里授权页已展示, 忽略");
                        return;
                    }
                    SQLog.w("【Login Manager】闪验登录失败, " + bundle);
                    AccountLoginManager.this.startAccountLoginDialog(iLoginListener);
                }

                @Override // com.sy37sdk.account.alifast.FastLoginManager.FastLoginListener
                public void onFastRelease() {
                    if (AccountLoginManager.this.attachManager != null) {
                        AccountLoginManager.this.attachManager.detachView();
                    }
                }

                @Override // com.sy37sdk.account.alifast.FastLoginManager.FastLoginListener
                public void onVerifyAccount(boolean z) {
                    if (z) {
                        AccountLoginManager.this.login(iLoginListener);
                    }
                }
            }, new FastLoginManager.WeChatLoginListener() { // from class: com.sy37sdk.account.alifast.AccountLoginManager.2
                @Override // com.sy37sdk.account.alifast.FastLoginManager.WeChatLoginListener
                public void onWeChatLoginSuccess(Map<String, String> map2) {
                    SQLog.d("【Login Manager】微信登录成功, " + map2);
                    LoginTractionManager.track("8", map2);
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.login_wechat_succ);
                    if (AccountLoginManager.this.loginListener != null) {
                        AccountLoginManager.this.loginListener.onSuccess(map2);
                    }
                }

                @Override // com.sy37sdk.account.alifast.FastLoginManager.WeChatLoginListener
                public void onWeChatLoginFail(int i, String str) {
                    SQLog.e("【Login Manager】微信登录失败: " + i + ", " + str);
                    HashMap map2 = new HashMap();
                    map2.put(SqTrackKey.fail_code, String.valueOf(i));
                    map2.put(SqTrackKey.reason_fail, str);
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.login_wechat_fail, map2);
                    LoginTractionManager.trackWechatFail(str);
                    if (AccountLoginManager.this.loginListener != null) {
                        AccountLoginManager.this.loginListener.onFailure(i, str);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            SQLog.e("【Login Manager】调起闪验异常", e);
            HashMap map2 = new HashMap();
            map2.put(SqTrackKey.fail_code, FastLoginConstants.Code.FAILURE_VERIFY_FAIL_UNKNOWN);
            map2.put(SqTrackKey.reason_fail, "未知错误 " + e.getMessage());
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.ali_fast_login_fail, map2);
            unRegisterLoginAuthActivityListener();
            startAccountLoginDialog(iLoginListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAccountLoginDialog(ILoginListener iLoginListener) {
        startAccountLoginDialog(false, iLoginListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAccountLoginDialog(boolean z, final ILoginListener iLoginListener) {
        AccountLoginDialog accountLoginDialog = new AccountLoginDialog(this.mContext, iLoginListener);
        if (z) {
            FastLoginManager.getInstance(this.mContext).quitLoginPage();
            accountLoginDialog.setOnAccountDialogCloseListener(new AccountLoginDialog.OnAccountDialogCloseListener() { // from class: com.sy37sdk.account.alifast.AccountLoginManager.3
                @Override // com.sy37sdk.account.view.uifast.AccountLoginDialog.OnAccountDialogCloseListener
                public void onClose() {
                }

                @Override // com.sy37sdk.account.view.uifast.AccountLoginDialog.OnAccountDialogCloseListener
                public void onDismiss() {
                    AccountUtil.setToFastLogin(true);
                    AccountLoginManager.this.login(iLoginListener);
                }
            });
        }
        accountLoginDialog.setFromAliFastLogin(z);
        accountLoginDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startWeiChatLoginDialogWithBack(final ILoginListener iLoginListener) {
        AccountLoginDialog accountLoginDialog = new AccountLoginDialog(this.mContext, iLoginListener);
        accountLoginDialog.setOnAccountDialogCloseListener(new AccountLoginDialog.OnAccountDialogCloseListener() { // from class: com.sy37sdk.account.alifast.AccountLoginManager.4
            @Override // com.sy37sdk.account.view.uifast.AccountLoginDialog.OnAccountDialogCloseListener
            public void onClose() {
            }

            @Override // com.sy37sdk.account.view.uifast.AccountLoginDialog.OnAccountDialogCloseListener
            public void onDismiss() {
                AccountLoginManager.this.login(iLoginListener);
            }
        });
        accountLoginDialog.setCanBack(true);
        accountLoginDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPhoneLoginDialogWithBack(final ILoginListener iLoginListener) {
        AccountLoginDialog accountLoginDialog = new AccountLoginDialog(this.mContext, iLoginListener);
        accountLoginDialog.setOnAccountDialogCloseListener(new AccountLoginDialog.OnAccountDialogCloseListener() { // from class: com.sy37sdk.account.alifast.AccountLoginManager.5
            @Override // com.sy37sdk.account.view.uifast.AccountLoginDialog.OnAccountDialogCloseListener
            public void onClose() {
            }

            @Override // com.sy37sdk.account.view.uifast.AccountLoginDialog.OnAccountDialogCloseListener
            public void onDismiss() {
                AccountLoginManager.this.login(iLoginListener);
            }
        });
        accountLoginDialog.setCanBack(true);
        accountLoginDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAccountLoginDialogWithAccountPage(final ILoginListener iLoginListener) {
        AccountLoginDialog accountLoginDialog = new AccountLoginDialog(this.mContext, iLoginListener);
        FastLoginManager.getInstance(this.mContext).quitLoginPage();
        accountLoginDialog.setOnAccountDialogCloseListener(new AccountLoginDialog.OnAccountDialogCloseListener() { // from class: com.sy37sdk.account.alifast.AccountLoginManager.6
            @Override // com.sy37sdk.account.view.uifast.AccountLoginDialog.OnAccountDialogCloseListener
            public void onClose() {
            }

            @Override // com.sy37sdk.account.view.uifast.AccountLoginDialog.OnAccountDialogCloseListener
            public void onDismiss() {
                AccountUtil.setToFastLogin(true);
                AccountLoginManager.this.login(iLoginListener);
            }
        });
        accountLoginDialog.setFromAliFastLogin(true);
        accountLoginDialog.show();
        accountLoginDialog.onSwitch(1, null);
    }

    private void registerLoginAuthActivityListener() {
        SQContextWrapper.getActivity().getApplication().registerActivityLifecycleCallbacks(this.activityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unRegisterLoginAuthActivityListener() {
        SQContextWrapper.getActivity().getApplication().unregisterActivityLifecycleCallbacks(this.activityListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLoginAuthActivityExist() {
        return CheckClassUtils.classExist("com.mobile.auth.gatewayauth.LoginAuthActivity");
    }
}
