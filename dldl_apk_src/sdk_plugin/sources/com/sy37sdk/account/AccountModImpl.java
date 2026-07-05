package com.sy37sdk.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.social.sdk.SocialApi;
import com.sq.eventbus.core.EventBus;
import com.sq.tool.logger.SQLog;
import com.sq.websocket_engine.WebSocketEngine;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.eventbus.OnActivityResultEvent;
import com.sqwan.common.eventbus.SActiveEvent;
import com.sqwan.common.mod.account.IAccountChangeListener;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mod.account.IAuthResultListener;
import com.sqwan.common.mod.account.IBackToGameLoginListener;
import com.sqwan.common.mod.account.IBindWxListener;
import com.sqwan.common.mod.account.ILoginListener;
import com.sqwan.common.mod.account.IScreenshotListener;
import com.sqwan.common.mod.liveshow.LiveRadioEngine;
import com.sqwan.common.mod.liveshow.LiveshowEngine;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.UrlUtils;
import com.sqwan.common.util.ZipString;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.account.age.AppropriateAgeManager;
import com.sy37sdk.account.auth.AuthManager;
import com.sy37sdk.account.auth.floatview.AuthCountDownManager;
import com.sy37sdk.account.binding.GameBindingManager;
import com.sy37sdk.account.controller.AbstractLoginController;
import com.sy37sdk.account.controller.UIVersionManager;
import com.sy37sdk.account.eventbus.EventBusIndex;
import com.sy37sdk.account.face.FaceVerifyManager;
import com.sy37sdk.account.floatview.FloatViewDataManager;
import com.sy37sdk.account.floatview.LoginInfoUtil;
import com.sy37sdk.account.floatview.MenuConfig;
import com.sy37sdk.account.floatview.SqBaseFloatView;
import com.sy37sdk.account.floatview.SqFloatViewManager;
import com.sy37sdk.account.floatview.data.RedDot;
import com.sy37sdk.account.floatview.redpacket.RedPacketManager;
import com.sy37sdk.account.pop.LoginPopupDialogManager;
import com.sy37sdk.account.screenshot.ScreenshotManager;
import com.sy37sdk.account.uagree.UAgreeManager;
import com.sy37sdk.account.util.SocialAccountUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountModImpl implements ILoginListener, IAccountMod {
    private static final String TAG = "【Login Mod】";
    public static boolean isSQLoginSuccess;
    private IAccountChangeListener accountChangeListener;
    private IBackToGameLoginListener backToGameLoginListener;
    public IAuthResultListener iAuthResultListener;
    private boolean isSQSubmitRole = false;
    private Context mContext;
    private ILoginListener mListener;

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void modifyPassword() {
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void onConfigurationChanged(Configuration configuration) {
    }

    public AccountModImpl(Context context) {
        this.mContext = context;
        EventBus.getDefault().addIndex(new EventBusIndex());
        EventBus.getDefault().register(this);
        SocialApi.init(context);
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void login(ILoginListener iLoginListener) {
        this.mListener = iLoginListener;
        SqFloatViewManager.getInstance().dismissFloatView();
        LiveshowEngine.getInstance().leaveLiveshowRoom(null, null);
        LiveRadioEngine.getInstance().leaveLiveRadioRoom(null, null);
        AuthCountDownManager.getInstance().stopAuthCdTask();
        AbstractLoginController loginController = UIVersionManager.getInstance(this.mContext).getLoginController();
        SQLog.d("【Login Mod】login: " + loginController.getClass().getSimpleName());
        loginController.login(this);
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void logout() {
        isSQLoginSuccess = false;
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void changeAccount(ILoginListener iLoginListener) {
        this.mListener = iLoginListener;
        SqFloatViewManager.getInstance().dismissFloatView();
        LiveshowEngine.getInstance().leaveLiveshowRoom(null, null);
        LiveRadioEngine.getInstance().leaveLiveRadioRoom(null, null);
        AuthCountDownManager.getInstance().stopAuthCdTask();
        UIVersionManager.getInstance(this.mContext).getLoginController().showLoginDialog(this);
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void showLoginView(ILoginListener iLoginListener) {
        this.mListener = iLoginListener;
        UIVersionManager.getInstance(this.mContext).getLoginController().showLoginDialog(this);
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public String getToken() {
        return AccountCache.getToken(this.mContext);
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public String getUid() {
        return AccountCache.getUserid(this.mContext);
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public String getUname() {
        return AccountCache.getUsername(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handOtherConfig() {
        String loginData = AccountLogic.getInstance(this.mContext).getLoginData();
        try {
            JSONObject jSONObject = new JSONObject(loginData);
            if (jSONObject.has("oauthinfo")) {
                LoginInfoUtil.setOauthNickName(jSONObject.getString("oauthinfo"), this.mContext);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("解析登录配置失败");
            BuglessAction.reportCatchException(e, loginData, 19);
        }
    }

    public void onSActiveEvent(SActiveEvent sActiveEvent) {
        AppropriateAgeManager.getInstance().refreshConfig();
        String data = sActiveEvent.getData();
        SQLog.v("【Login Mod】onSActiveEvent data=" + data);
        try {
            JSONObject jSONObject = new JSONObject(data);
            if (jSONObject.has("c")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("c");
                if (jSONObject2.has("version")) {
                    UIVersionManager.getInstance(this.mContext).initVersion(jSONObject2.getString("version"));
                }
            }
            AccountConfig.praseConfig(data);
        } catch (JSONException e) {
            LogUtil.e("account mod parse s active data error!");
            e.printStackTrace();
        }
        LoginTriggerManager.getInstance().startQueryLoginTrigger();
    }

    public void onActivityResult(OnActivityResultEvent onActivityResultEvent) {
        int requestCode = onActivityResultEvent.getRequestCode();
        int resultCode = onActivityResultEvent.getResultCode();
        Intent intent = onActivityResultEvent.getIntent();
        SQLog.v("【Login Mod】onActivityResult code=" + requestCode);
        SocialApi.getInstance().onActivityResult(requestCode, resultCode, intent);
        FaceVerifyManager.getInstance(this.mContext).onActivityResult(requestCode, resultCode, intent);
    }

    @Override // com.sqwan.common.mod.account.ILoginListener
    public void onSuccess(final Map<String, String> map) {
        SQLog.d("【Login Mod】登录成功, 触发实名认证检查");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.request_pcheck);
        SqTrackActionManager2.getInstance().flush();
        AuthManager.getInstance(this.mContext).requestAuthConfig(new SQResultListener() { // from class: com.sy37sdk.account.AccountModImpl.1
            public void onSuccess(Bundle bundle) {
                SQLog.i("【Login Mod】实名认证检查成功, " + bundle);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.request_pcheck_succ);
                SqTrackActionManager2.getInstance().flush();
                AccountModImpl.this.setSubmitRole(false);
                AccountModImpl.this.handOtherConfig();
                AuthCountDownManager.getInstance().release();
                FloatViewDataManager.getInstance().clearRedDot();
                SqFloatViewManager.getInstance().requestFloatWindowConfig(AccountModImpl.this.mContext);
                FloatViewDataManager.getInstance().requestAvatarList();
                AccountModImpl.isSQLoginSuccess = true;
                if (AccountModImpl.this.mListener != null) {
                    AccountModImpl.this.mListener.onSuccess(map);
                }
                LoginPopupDialogManager.getInstance().handlePopup(AccountModImpl.this.mContext);
                WebSocketEngine.getInstance().close();
                WebSocketEngine.getInstance().open();
                SqFloatViewManager.getInstance().bindRedDot();
            }

            public void onFailture(int i, String str) {
                SQLog.e("【Login Mod】实名认证检查失败, code=" + i + ", msg=" + str);
                HashMap map2 = new HashMap();
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append("");
                map2.put(SqTrackKey.fail_code, sb.toString());
                map2.put(SqTrackKey.reason_fail, str);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.request_pcheck_fail, map2);
            }
        });
    }

    @Override // com.sqwan.common.mod.account.ILoginListener
    public void onFailure(int i, String str) {
        ILoginListener iLoginListener = this.mListener;
        if (iLoginListener != null) {
            iLoginListener.onFailure(i, str);
        }
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void saveAccount(String str, String str2) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUname(str);
        userInfo.setUpwd(ZipString.json2ZipString(str2));
        AccountTools.setAccountToFile(this.mContext, userInfo);
    }

    void accountChanged(Map<String, String> map) {
        this.accountChangeListener.accountChanged(map);
        AuthCountDownManager.getInstance().release();
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void backToGameLogin() {
        this.backToGameLoginListener.backToGameLogin();
        AuthCountDownManager.getInstance().release();
        SqTrackActionManager2.getInstance().trackActionCPTest(SqTrackAction2.BACK_TO_GAME_LOGIN);
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void webEnLogin(boolean z) {
        GameBindingManager.getInstance().setIsLogin(false);
        SqFloatViewManager.getInstance().dismissFloatView();
        LiveshowEngine.getInstance().leaveLiveshowRoom(null, null);
        LiveRadioEngine.getInstance().leaveLiveRadioRoom(null, null);
        WebSocketEngine.getInstance().close();
        if (this.backToGameLoginListener != null && !z) {
            LogUtil.w("回到游戏登录界面的监听|不为空，现在回到游戏的登录界面");
            backToGameLogin();
        } else {
            if (this.accountChangeListener == null) {
                ToastUtil.showToast(this.mContext, "切换账号错误，请联系客服【10002】");
                return;
            }
            if (isSkipSQChangeAccountLogin(this.mContext)) {
                accountChanged(null);
            } else if (isSQLoginSuccess) {
                showLoginView(new ILoginListener() { // from class: com.sy37sdk.account.AccountModImpl.2
                    @Override // com.sqwan.common.mod.account.ILoginListener
                    public void onSuccess(Map<String, String> map) {
                        AccountModImpl.this.accountChanged(map);
                    }

                    @Override // com.sqwan.common.mod.account.ILoginListener
                    public void onFailure(int i, String str) {
                        if (SqFloatViewManager.getInstance().isShowFloat()) {
                            SqFloatViewManager.getInstance().showFloatView((Activity) AccountModImpl.this.mContext);
                        }
                    }
                });
            } else {
                ToastUtil.showToast(this.mContext, "您还未登录！");
            }
        }
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void setAccountChangeListener(IAccountChangeListener iAccountChangeListener) {
        this.accountChangeListener = iAccountChangeListener;
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void setBackToGameLoginListener(IBackToGameLoginListener iBackToGameLoginListener) {
        this.backToGameLoginListener = iBackToGameLoginListener;
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void setScreenshotListener(IScreenshotListener iScreenshotListener) {
        ScreenshotManager.getInstance(this.mContext).setScreenshotListener(iScreenshotListener);
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void wxBind(IBindWxListener iBindWxListener) {
        SocialAccountUtil.socialBindAuthorize((Activity) this.mContext, iBindWxListener);
    }

    public static boolean isSkipSQChangeAccountLogin(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                return false;
            }
            String string = applicationInfo.metaData.getString("SQwanSkipSwitchLogin");
            System.out.println("是否跳过切换账号登录框：" + string);
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            return "yes".equals(string);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void showUAgreement() {
        UAgreeManager.getInstance().showUAgree();
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void submitRoleInfo(Map<String, String> map) {
        setSubmitRole(true);
        RedPacketManager.getInstance().submitRoleInfos(this.mContext, map);
        AuthCountDownManager.getInstance().startReportAuth();
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void onResume() {
        SqFloatViewManager.getInstance().onResume();
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void onPause() {
        SqFloatViewManager.getInstance().onPause();
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void setAuthResultListener(IAuthResultListener iAuthResultListener) {
        this.iAuthResultListener = iAuthResultListener;
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public IAuthResultListener getAuthResultListener() {
        return this.iAuthResultListener;
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void showAgeAppropriate() {
        AppropriateAgeManager.getInstance().showAppropriateAgeDialog(this.mContext);
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public boolean hasSubmitRole() {
        return this.isSQSubmitRole;
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void setSubmitRole(boolean z) {
        this.isSQSubmitRole = z;
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void showFloatMenu() {
        SqBaseFloatView sqBaseFloatView = SqFloatViewManager.getInstance().floatView;
        if (sqBaseFloatView != null) {
            sqBaseFloatView.setWindowVisibility(0);
            sqBaseFloatView.resetView(false);
        }
        SqFloatViewManager.getInstance().showFloatMenu();
    }

    @Override // com.sqwan.common.mod.account.IAccountMod
    public void redDotCalled(String str) {
        List<MenuConfig> menuConfigs;
        RedDot redDotByKey = FloatViewDataManager.getInstance().getRedDotByKey(str);
        if (SqFloatViewManager.getInstance().config == null || (menuConfigs = SqFloatViewManager.getInstance().config.getMenuConfigs()) == null) {
            return;
        }
        MenuConfig menuConfig = null;
        Iterator<MenuConfig> it = menuConfigs.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            MenuConfig next = it.next();
            if (TextUtils.equals(str, next.title)) {
                menuConfig = next;
                break;
            }
        }
        if (menuConfig == null || !menuConfig.needRedDot()) {
            return;
        }
        String valueFromUrlStrByParamName = UrlUtils.readValueFromUrlStrByParamName(menuConfig.openUrl, "page_uuid");
        if (redDotByKey == null || redDotByKey.getNum() <= 0) {
            return;
        }
        SqFloatViewManager.getInstance().redDotCalled(valueFromUrlStrByParamName, str, this.mContext);
        FloatViewDataManager.getInstance().clearRedDotByKey(str);
        SqFloatViewManager.getInstance().showFloatItemRedDot();
        if (SqFloatViewManager.getInstance().floatView != null) {
            SqFloatViewManager.getInstance().floatView.showRedDot(FloatViewDataManager.getInstance().hasRedDot());
        }
    }
}
