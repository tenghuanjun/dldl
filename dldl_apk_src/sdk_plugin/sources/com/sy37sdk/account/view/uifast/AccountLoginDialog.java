package com.sy37sdk.account.view.uifast;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sq.sdk.tool.util.DisplayUtil;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sq.tools.Logger;
import com.sqwan.common.mod.account.ILoginListener;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.NavigationUtils;
import com.sqwan.common.util.SDKError;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.widget.BaseViewPager;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.AccountLogic;
import com.sy37sdk.account.AccountTools;
import com.sy37sdk.account.UserInfo;
import com.sy37sdk.account.age.AppropriateAge;
import com.sy37sdk.account.age.AppropriateAgeCacheHelper;
import com.sy37sdk.account.age.AppropriateAgeManager;
import com.sy37sdk.account.controller.UIVersionManager;
import com.sy37sdk.account.entrance.EntranceManager;
import com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter;
import com.sy37sdk.account.util.AccountUtil;
import com.sy37sdk.account.view.IRegSuccessDialog;
import com.sy37sdk.account.view.uifast.adapter.AccountPageAdapter;
import com.sy37sdk.account.view.uifast.presenter.AccountLoginPresenter;
import com.sy37sdk.account.view.uifast.presenter.HistoryAccountPresenter;
import com.sy37sdk.account.view.uifast.presenter.MultiAccountSelectPresenter;
import com.sy37sdk.account.view.uifast.presenter.PhonePresenter;
import com.sy37sdk.account.view.uifast.presenter.VerifyCodePresenter;
import com.sy37sdk.account.view.uifast.presenter.WechatLoginPresenter;
import com.sy37sdk.account.view.uifast.presenter.WechatLoginV2Presenter;
import com.sy37sdk.account.view.uifast.switcher.PageSwitcher;
import com.sy37sdk.account.view.uifast.view.AccountLoginView;
import com.sy37sdk.account.view.uifast.view.HistoryAccountView;
import com.sy37sdk.account.view.uifast.view.MultiSelectView;
import com.sy37sdk.account.view.uifast.view.PhoneView;
import com.sy37sdk.account.view.uifast.view.VerifyCodeView;
import com.sy37sdk.account.view.uifast.view.WechatLoginV2View;
import com.sy37sdk.account.view.uifast.view.WechatLoginView;
import com.sy37sdk.core.INewUrl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountLoginDialog extends BaseDialog implements ILoginDialog {
    private OnAccountDialogCloseListener accountDialogCloseListener;
    private AccountLoginPresenter accountLoginPresenter;
    private boolean canBack;
    private boolean dismiss;
    private HistoryAccountPresenter historyAccountPresenter;
    private boolean isFromAliFastLogin;
    private ImageView ivAppropriateAge;
    private ILoginListener loginListener;
    private RelativeLayout loginParentLayout;
    private AccountPageAdapter mAccountPageAdapter;
    private List<BaseAccountPagerPresenter> mAccountPagePresenterList;
    private PageSwitcher mAccountPageSwitcher;
    private Bundle mBundle;
    private Context mContext;
    private int mFromIndex;
    private MultiAccountSelectPresenter mMultiAccountSelectPresenter;
    private int mToIndex;
    private int navigationBarHeight;
    int orientation;
    private PhonePresenter phonePresenter;
    int statusBarHeight;
    private VerifyCodePresenter verifyCodePresenter;
    private BaseViewPager viewPager;
    private Stack<Integer> viewStacks;
    private WechatLoginPresenter wechatLoginPresenter;
    private WechatLoginV2Presenter wechatLoginV2Presenter;

    public interface OnAccountDialogCloseListener {
        void onClose();

        void onDismiss();
    }

    public AccountLoginDialog(Context context, ILoginListener iLoginListener) {
        super(context);
        this.isFromAliFastLogin = false;
        this.canBack = false;
        this.mContext = context;
        this.loginListener = iLoginListener;
    }

    public void setFromAliFastLogin(boolean z) {
        this.isFromAliFastLogin = z;
    }

    public void setCanBack(boolean z) {
        this.canBack = z;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(this.mContext, "sysq_dialog_login"));
        initView();
    }

    private void initPageViewList() {
        this.mAccountPagePresenterList = new ArrayList();
        PhonePresenter phonePresenter = new PhonePresenter(this.mContext, new PhoneView(this.mContext, this));
        this.phonePresenter = phonePresenter;
        phonePresenter.setAccountPageSwitcher(this.mAccountPageSwitcher);
        this.phonePresenter.setLoginListener(this.loginListener);
        this.mAccountPagePresenterList.add(0, this.phonePresenter);
        AccountLoginPresenter accountLoginPresenter = new AccountLoginPresenter(this.mContext, new AccountLoginView(this.mContext, this));
        this.accountLoginPresenter = accountLoginPresenter;
        accountLoginPresenter.setAccountPageSwitcher(this.mAccountPageSwitcher);
        this.accountLoginPresenter.setLoginListener(this.loginListener);
        this.mAccountPagePresenterList.add(1, this.accountLoginPresenter);
        HistoryAccountPresenter historyAccountPresenter = new HistoryAccountPresenter(this.mContext, new HistoryAccountView(this.mContext, this, this.loginListener));
        this.historyAccountPresenter = historyAccountPresenter;
        historyAccountPresenter.setAccountPageSwitcher(this.mAccountPageSwitcher);
        this.historyAccountPresenter.setLoginListener(this.loginListener);
        this.mAccountPagePresenterList.add(2, this.historyAccountPresenter);
        VerifyCodePresenter verifyCodePresenter = new VerifyCodePresenter(this.mContext, new VerifyCodeView(this.mContext, this));
        this.verifyCodePresenter = verifyCodePresenter;
        verifyCodePresenter.setAccountPageSwitcher(this.mAccountPageSwitcher);
        this.verifyCodePresenter.setLoginListener(this.loginListener);
        this.mAccountPagePresenterList.add(3, this.verifyCodePresenter);
        WechatLoginPresenter wechatLoginPresenter = new WechatLoginPresenter(this.mContext, new WechatLoginView(this.mContext, this, this.loginListener));
        this.wechatLoginPresenter = wechatLoginPresenter;
        wechatLoginPresenter.setAccountPageSwitcher(this.mAccountPageSwitcher);
        this.wechatLoginPresenter.setLoginListener(this.loginListener);
        this.mAccountPagePresenterList.add(4, this.wechatLoginPresenter);
        WechatLoginV2Presenter wechatLoginV2Presenter = new WechatLoginV2Presenter(this.mContext, new WechatLoginV2View(this.mContext, this));
        this.wechatLoginV2Presenter = wechatLoginV2Presenter;
        wechatLoginV2Presenter.setAccountPageSwitcher(this.mAccountPageSwitcher);
        this.wechatLoginV2Presenter.setLoginListener(this.loginListener);
        this.mAccountPagePresenterList.add(5, this.wechatLoginV2Presenter);
        MultiAccountSelectPresenter multiAccountSelectPresenter = new MultiAccountSelectPresenter(this.mContext, new MultiSelectView(this.mContext, this));
        this.mMultiAccountSelectPresenter = multiAccountSelectPresenter;
        multiAccountSelectPresenter.setAccountPageSwitcher(this.mAccountPageSwitcher);
        this.mMultiAccountSelectPresenter.setLoginListener(this.loginListener);
        this.mAccountPagePresenterList.add(6, this.mMultiAccountSelectPresenter);
    }

    private void initView() {
        this.viewStacks = new Stack<>();
        BaseViewPager baseViewPager = (BaseViewPager) findViewById(getIdByName("account_dialog_content", SqTrackCommonKey.id));
        this.viewPager = baseViewPager;
        baseViewPager.setPagingEnabled(false);
        this.mAccountPageSwitcher = new PageSwitcher(this.viewPager);
        initPageViewList();
        this.viewPager.setOffscreenPageLimit(this.mAccountPagePresenterList.size());
        this.mAccountPageAdapter = new AccountPageAdapter(this.mAccountPagePresenterList);
        this.mAccountPageSwitcher.setPageScrollListener(new PageSwitcher.IPageScrollListener() { // from class: com.sy37sdk.account.view.uifast.AccountLoginDialog.1
            @Override // com.sy37sdk.account.view.uifast.switcher.PageSwitcher.IPageScrollListener
            public void scroll(int i, int i2, Bundle bundle) {
                AccountLoginDialog.this.mFromIndex = i;
                AccountLoginDialog.this.mToIndex = i2;
                AccountLoginDialog.this.mBundle = bundle;
                LogUtil.i("scroll 页面切换 from " + AccountLoginDialog.this.mFromIndex + " to " + AccountLoginDialog.this.mToIndex);
            }
        });
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sy37sdk.account.view.uifast.AccountLoginDialog.2
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                List<UserInfo> allUserInfo;
                LogUtil.i(INewUrl.KEY_S_PUSH);
                if (AccountLoginDialog.this.viewStacks.isEmpty() || ((Integer) AccountLoginDialog.this.viewStacks.peek()).intValue() != AccountLoginDialog.this.mToIndex) {
                    if (!AccountLoginDialog.this.viewStacks.isEmpty() && ((Integer) AccountLoginDialog.this.viewStacks.peek()).intValue() == 2 && ((allUserInfo = AccountUtil.getAllUserInfo(AccountLoginDialog.this.getContext())) == null || allUserInfo.isEmpty())) {
                        LogUtil.i("账号列表空，pop历史账号列表页");
                        AccountLoginDialog.this.viewStacks.pop();
                    }
                    AccountLoginDialog.this.viewStacks.push(Integer.valueOf(AccountLoginDialog.this.mToIndex));
                    LogUtil.i("push mToIndex" + AccountLoginDialog.this.mToIndex);
                }
                ((BaseAccountPagerPresenter) AccountLoginDialog.this.mAccountPagePresenterList.get(AccountLoginDialog.this.mToIndex)).onSwitched(AccountLoginDialog.this.mFromIndex, AccountLoginDialog.this.mToIndex, AccountLoginDialog.this.mBundle);
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                LogUtil.i("onPageSelected " + i);
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
                LogUtil.i("onPageScrollStateChanged");
            }
        });
        this.viewPager.setAdapter(this.mAccountPageAdapter);
        List<UserInfo> accountFromFile = AccountTools.getAccountFromFile(this.mContext);
        if (EntranceManager.getInstance().supportWxEntrance(this.mContext) && !isFromAliFastLogin()) {
            UserInfo userInfo = AccountCache.getUserInfo(this.mContext);
            boolean z = userInfo != null && TextUtils.equals(userInfo.getLoginType(), "3") && UserInfo.wechatLoginFail;
            if (DeviceUtils.isFirstInvokeLogin(this.mContext) || accountFromFile == null || accountFromFile.isEmpty() || z || AccountUtil.checkToWeiChatLogin()) {
                if (z && !AccountUtil.checkToWeiChatLogin()) {
                    Logger.info("需要返回到历史账号", new Object[0]);
                    this.viewStacks.push(2);
                }
                AccountUtil.setToWeiChatLogin(false);
                if (AccountUtil.checkNotSupportFast()) {
                    Logger.info("从闪验失败跳转来，去手机号登录页", new Object[0]);
                    AccountUtil.setNotSupportFast(false);
                    this.mAccountPageSwitcher.onSwitch(0);
                } else if (EntranceManager.getInstance().getWxLoginUIVersion() == 2) {
                    Logger.info("展示微信登录V2", new Object[0]);
                    this.mAccountPageSwitcher.onSwitch(0);
                } else {
                    Logger.info("展示微信登录V1", new Object[0]);
                    this.mAccountPageSwitcher.onSwitch(4);
                }
            } else {
                Logger.info("其他场景展示历史账号", new Object[0]);
                this.mAccountPageSwitcher.onSwitch(2);
            }
        } else if (accountFromFile == null || accountFromFile.isEmpty()) {
            Logger.info("无历史账号则显示手机登录页面", new Object[0]);
            this.mAccountPageSwitcher.onSwitch(0);
            this.canBack = false;
        } else if (isFromAliFastLogin()) {
            Logger.info("有历史账号，但是从闪验跳转其他登录，跳转到手机号登录", new Object[0]);
            this.mAccountPageSwitcher.onSwitch(0);
        } else if (AccountUtil.checkNotSupportFast()) {
            Logger.info("有历史账号，但是从其他登录方式跳到闪验，但闪验不支持的版本跳转其他登录，跳转到手机号登录", new Object[0]);
            AccountUtil.setNotSupportFast(false);
            this.mAccountPageSwitcher.onSwitch(0);
        } else {
            Logger.info("有历史账号则显示历史账号页", new Object[0]);
            this.mAccountPageSwitcher.onSwitch(2);
        }
        UserInfo.wechatLoginFail = false;
        this.navigationBarHeight = NavigationUtils.getNavigationBarHeight(this.mContext);
        this.statusBarHeight = StatusBarUtil.getStatusBarHeight(this.mContext);
        LogUtil.i("状态栏高度=" + this.statusBarHeight + " 导航栏高度=" + this.navigationBarHeight);
        this.orientation = this.mContext.getResources().getConfiguration().orientation;
        setAttachViewLocate();
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(getIdByName("login_parent", SqTrackCommonKey.id));
        this.loginParentLayout = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.AccountLoginDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StatusBarUtil.hideSystemKeyBoard(AccountLoginDialog.this.mContext, AccountLoginDialog.this.loginParentLayout);
            }
        });
        initAppropriateAgeView();
    }

    private void setAttachViewLocate() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById(getIdByName("attach_view", SqTrackCommonKey.id)).getLayoutParams();
        if (this.orientation == 2) {
            layoutParams.setMargins(0, 0, this.navigationBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f), DisplayUtil.dip2px(this.mContext, 6.0f));
        } else {
            layoutParams.setMargins(0, 0, DisplayUtil.dip2px(this.mContext, 12.0f), this.navigationBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f));
        }
    }

    private void initAppropriateAgeView() {
        if (AppropriateAgeCacheHelper.getAppropriateAge(this.mContext) == null) {
            Logger.info("登录页，适龄图标无缓存，发起请求", new Object[0]);
            AppropriateAgeManager.getInstance().refreshConfig(new SQResultListener() { // from class: com.sy37sdk.account.view.uifast.AccountLoginDialog.4
                public void onFailture(int i, String str) {
                }

                public void onSuccess(Bundle bundle) {
                    AccountLoginDialog.this.showAgeAppropriate();
                }
            });
        } else {
            Logger.info("登录页，适龄图标有缓存，直接显示", new Object[0]);
            showAgeAppropriate();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        this.dismiss = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAgeAppropriate() {
        AppropriateAge appropriateAge;
        List<String> timing;
        if (this.dismiss || (appropriateAge = AppropriateAgeCacheHelper.getAppropriateAge(this.mContext)) == null || !appropriateAge.isStatus() || (timing = appropriateAge.getTiming()) == null || !timing.contains("1")) {
            return;
        }
        this.ivAppropriateAge = new ImageView(this.mContext);
        int iMin = Math.min(DisplayUtil.getScreenWidth(this.mContext), DisplayUtil.getScreenHeight(this.mContext)) / 10;
        int iDip2px = DisplayUtil.dip2px(this.mContext, 20.0f);
        int iDip2px2 = DisplayUtil.dip2px(this.mContext, 20.0f);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iMin, (iMin * 62) / 48);
        int location = appropriateAge.getLocation();
        if (location == 2) {
            layoutParams.addRule(10);
            layoutParams.addRule(11);
            if (this.orientation == 2) {
                layoutParams.setMargins(0, iDip2px2, this.navigationBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f), 0);
            } else {
                layoutParams.setMargins(0, iDip2px2, iDip2px, 0);
            }
        } else if (location == 3) {
            layoutParams.addRule(12);
            layoutParams.addRule(9);
            if (this.orientation == 2) {
                layoutParams.setMargins(this.statusBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f), 0, 0, this.statusBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f));
            } else {
                layoutParams.setMargins(iDip2px, 0, 0, this.navigationBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f));
            }
        } else if (location == 4) {
            layoutParams.addRule(11);
            layoutParams.addRule(12);
            if (this.orientation == 2) {
                layoutParams.setMargins(0, 0, this.navigationBarHeight + DisplayUtil.dip2px(this.mContext, 18.0f), iDip2px2 + DisplayUtil.dip2px(this.mContext, 55.0f));
            } else {
                layoutParams.setMargins(0, 0, iDip2px, this.navigationBarHeight + DisplayUtil.dip2px(this.mContext, 80.0f));
            }
        } else {
            layoutParams.addRule(9);
            layoutParams.addRule(10);
            if (this.orientation == 2) {
                layoutParams.setMargins(iDip2px, iDip2px2, 0, 0);
            } else {
                layoutParams.setMargins(iDip2px, this.statusBarHeight + DisplayUtil.dip2px(this.mContext, 10.0f), 0, 0);
            }
        }
        this.ivAppropriateAge.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.ivAppropriateAge.setLayoutParams(layoutParams);
        this.loginParentLayout.addView(this.ivAppropriateAge);
        new AsyncImageLoader(getContext()).loadDrawable(appropriateAge.getIcon(), this.ivAppropriateAge, new AsyncImageLoader.ImageCallback() { // from class: com.sy37sdk.account.view.uifast.AccountLoginDialog.5
            @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
            public void imageLoaded(Bitmap bitmap, ImageView imageView, String str) {
                imageView.setImageBitmap(bitmap);
                LogUtil.i("适龄图片加载成功");
            }
        });
        this.ivAppropriateAge.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.AccountLoginDialog.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppropriateAgeManager.getInstance().showAppropriateAgeDialog(AccountLoginDialog.this.mContext);
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.ILoginDialog
    public void closeAccountDialog() {
        dismiss();
        OnAccountDialogCloseListener onAccountDialogCloseListener = this.accountDialogCloseListener;
        if (onAccountDialogCloseListener != null) {
            onAccountDialogCloseListener.onClose();
        }
        ILoginListener iLoginListener = this.loginListener;
        if (iLoginListener != null) {
            iLoginListener.onFailure(SDKError.ACCOUNT_LOGIN_CANCEL.code, SDKError.ACCOUNT_LOGIN_CANCEL.message);
        }
    }

    @Override // com.sy37sdk.account.view.uifast.ILoginDialog
    public void dismissAccountDialog() {
        dismiss();
        OnAccountDialogCloseListener onAccountDialogCloseListener = this.accountDialogCloseListener;
        if (onAccountDialogCloseListener != null) {
            onAccountDialogCloseListener.onClose();
        }
    }

    @Override // com.sy37sdk.account.view.uifast.ILoginDialog
    public void onSwitch(int i, Bundle bundle) {
        this.mAccountPageSwitcher.onSwitch(i, bundle);
    }

    @Override // com.sy37sdk.account.view.uifast.ILoginDialog
    public void loginSuccess(Map<String, String> map) {
        OnAccountDialogCloseListener onAccountDialogCloseListener = this.accountDialogCloseListener;
        if (onAccountDialogCloseListener != null) {
            onAccountDialogCloseListener.onClose();
        }
        ILoginListener iLoginListener = this.loginListener;
        if (iLoginListener != null) {
            iLoginListener.onSuccess(map);
        }
        dismiss();
    }

    @Override // com.sy37sdk.account.view.uifast.ILoginDialog
    public void accountRegSuccess(final Map<String, String> map) {
        OnAccountDialogCloseListener onAccountDialogCloseListener = this.accountDialogCloseListener;
        if (onAccountDialogCloseListener != null) {
            onAccountDialogCloseListener.onClose();
        }
        String username = AccountCache.getUsername(getContext());
        String password = AccountCache.getPassword(getContext());
        if (username.equals(AccountCache.getAutoName(getContext())) && AccountCache.getAutoState(getContext())) {
            UIVersionManager.getInstance(getContext()).getLoginController().showRegSuccessDialog(username, password, AccountLogic.getInstance(getContext()).getQrCodeInfo(), new IRegSuccessDialog.EnterGameListener() { // from class: com.sy37sdk.account.view.uifast.AccountLoginDialog.7
                @Override // com.sy37sdk.account.view.IRegSuccessDialog.EnterGameListener
                public void enterGame() {
                    if (AccountLoginDialog.this.loginListener != null) {
                        AccountLoginDialog.this.loginListener.onSuccess(map);
                    }
                }

                @Override // com.sy37sdk.account.view.IRegSuccessDialog.EnterGameListener
                public void cancel() {
                    if (AccountLoginDialog.this.loginListener != null) {
                        AccountLoginDialog.this.loginListener.onFailure(SDKError.ACCOUNT_LOGIN_CANCEL.code, SDKError.ACCOUNT_LOGIN_CANCEL.message);
                    }
                }
            });
            AccountCache.setAutoName(this.mContext, "");
            AccountCache.setAutoPassword(this.mContext, "");
        } else {
            ILoginListener iLoginListener = this.loginListener;
            if (iLoginListener != null) {
                iLoginListener.onSuccess(map);
            }
        }
        dismiss();
    }

    @Override // com.sy37sdk.account.view.uifast.ILoginDialog
    public void goBack() {
        LogUtil.i("goBack");
        if (canGoBack()) {
            if (this.viewStacks.size() > 1) {
                LogUtil.i(this.viewStacks.pop() + "出栈");
                Integer numPeek = this.viewStacks.peek();
                LogUtil.i("跳转至  " + numPeek);
                onSwitch(numPeek.intValue(), null);
                return;
            }
            OnAccountDialogCloseListener onAccountDialogCloseListener = this.accountDialogCloseListener;
            if (onAccountDialogCloseListener != null) {
                onAccountDialogCloseListener.onDismiss();
            }
            dismiss();
        }
    }

    @Override // com.sy37sdk.account.view.uifast.ILoginDialog
    public boolean canGoBack() {
        printStackViews();
        return this.viewStacks.size() > 1 || this.isFromAliFastLogin || this.canBack;
    }

    @Override // com.sy37sdk.account.view.uifast.ILoginDialog
    public void printStackViews() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.viewStacks.size(); i++) {
            sb.append(this.viewStacks.get(i));
            sb.append(" -> ");
        }
        LogUtil.i("view堆栈：" + sb.toString());
    }

    @Override // com.sy37sdk.account.view.uifast.ILoginDialog
    public boolean isFromAliFastLogin() {
        return this.isFromAliFastLogin;
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        if (canGoBack()) {
            this.mAccountPagePresenterList.get(this.mToIndex).onBackPressed();
            return;
        }
        OnAccountDialogCloseListener onAccountDialogCloseListener = this.accountDialogCloseListener;
        if (onAccountDialogCloseListener != null) {
            onAccountDialogCloseListener.onClose();
        }
        ILoginListener iLoginListener = this.loginListener;
        if (iLoginListener != null) {
            iLoginListener.onFailure(SDKError.ACCOUNT_LOGIN_CANCEL.code, SDKError.ACCOUNT_LOGIN_CANCEL.message);
        }
        super.onBackPressed();
    }

    public void setOnAccountDialogCloseListener(OnAccountDialogCloseListener onAccountDialogCloseListener) {
        this.accountDialogCloseListener = onAccountDialogCloseListener;
    }
}
