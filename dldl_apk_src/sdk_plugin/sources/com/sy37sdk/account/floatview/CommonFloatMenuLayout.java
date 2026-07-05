package com.sy37sdk.account.floatview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.parameters.bean.MiniProgramBean;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sq.tools.Logger;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mod.liveshow.LiveRadioEngine;
import com.sqwan.common.mod.liveshow.LiveshowEngine;
import com.sqwan.common.net.sq.CommonUrlConstant;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.track.SqTrackUtil;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.UrlUtils;
import com.sqwan.common.webview.SQWebViewDialog;
import com.sqwan.msdk.config.ConfigManager;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.floatview.PersonModifyDialog;
import com.sy37sdk.account.floatview.data.RedDot;
import com.sy37sdk.account.floatview.request.bean.FloatUserInfo;
import com.sy37sdk.account.floatview.ui.FloatItemAdapter;
import com.sy37sdk.account.floatview.ui.MenuUIConfig;
import com.sy37sdk.account.screenshot.ScreenshotManager;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CommonFloatMenuLayout extends LinearLayout {
    private List<MenuConfig> configs;
    private FloatItemAdapter floatItemAdapter;
    private RecyclerView functionParent;
    private ImageView ivEdit;
    private ImageView ivHead;
    private ImageView ivLevel;
    private final Context mContext;
    private MenuUIConfig mUIConfig;
    private boolean needUpdate;
    private OnMenuItemClickListener onMenuItemClickListener;
    private UpdateRedDotCallback onUpdateRedDotCallback;
    private View personLayout;
    private View personPanel;
    private List<RedDot> redDots;
    private TextView tvChangeAccount;
    private TextView tvNick;
    private TextView tvUserCenter;
    private String userCenter;
    private View view;

    public interface OnMenuItemClickListener {
        void onMenuItemClick(MenuConfig menuConfig);
    }

    public interface UpdateRedDotCallback {
        void onUpdateRedDotRemind();
    }

    public CommonFloatMenuLayout(Context context) {
        this(context, null, 0);
    }

    public CommonFloatMenuLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommonFloatMenuLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.redDots = new ArrayList();
        this.needUpdate = false;
        this.mContext = context;
        initView();
    }

    public void setConfigs(List<MenuConfig> list) {
        this.configs = list;
        this.floatItemAdapter.setDataList(list);
    }

    public void setUserCenter(String str) {
        this.userCenter = str;
        View view = this.personLayout;
        if (view != null) {
            view.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        }
    }

    public void setUIConfig(MenuUIConfig menuUIConfig) {
        this.mUIConfig = menuUIConfig;
        loadBackgroundImg();
        setTextColor(this.tvNick, menuUIConfig.mNicknameColor);
        setTextColor(this.tvUserCenter, menuUIConfig.mUserCenterColor);
        this.floatItemAdapter.setTitleColor(menuUIConfig.mTitleColor);
    }

    private void setTextColor(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            textView.setTextColor(Color.parseColor(str));
        } catch (Exception e) {
            e.printStackTrace();
            BuglessAction.reportCatchException(e, "解析颜色错误 textColor : " + str + " textView : " + ((Object) textView.getText()), 999);
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && this.needUpdate && this.configs != null) {
            FloatViewDataManager.getInstance().requestRedDot(this.configs, new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.1
                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(JSONObject jSONObject) {
                    try {
                        FloatViewDataManager.getInstance().saveRedDot(RedDot.getRedDots(jSONObject.optString("res_data")));
                        CommonFloatMenuLayout.this.refreshRedDot(FloatViewDataManager.getInstance().getRedDotCache());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.sq.tool.network.SqHttpCallback.SimpleSqHttpCallback, com.sq.tool.network.SqHttpCallback
                public void onFailure(int i, String str, VolleyError volleyError) {
                    super.onFailure(i, str, volleyError);
                    CommonFloatMenuLayout.this.refreshRedDot(FloatViewDataManager.getInstance().getRedDotCache());
                }
            });
            UpdateRedDotCallback updateRedDotCallback = this.onUpdateRedDotCallback;
            if (updateRedDotCallback != null) {
                updateRedDotCallback.onUpdateRedDotRemind();
            }
            this.needUpdate = false;
        }
    }

    private void initView() {
        this.view = LayoutInflater.from(this.mContext).inflate(SqResUtils.getLayoutId(this.mContext, "sysq_common_float_menu"), (ViewGroup) this, true);
        initPersonPanel();
        RecyclerView recyclerView = (RecyclerView) findViewById(SqResUtils.getId(this.mContext, "rv_menu_layout"));
        this.functionParent = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 4));
        FloatItemAdapter floatItemAdapter = new FloatItemAdapter(this.mContext);
        this.floatItemAdapter = floatItemAdapter;
        this.functionParent.setAdapter(floatItemAdapter);
        this.floatItemAdapter.setItemClickListener(new FloatItemAdapter.ItemClickListener() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.2
            @Override // com.sy37sdk.account.floatview.ui.FloatItemAdapter.ItemClickListener
            public void ItemOnClick(final MenuConfig menuConfig) {
                String str = menuConfig.title;
                final RedDot redDotByKey = FloatViewDataManager.getInstance().getRedDotByKey(str);
                if (menuConfig.needRedDot()) {
                    String valueFromUrlStrByParamName = UrlUtils.readValueFromUrlStrByParamName(menuConfig.openUrl, "page_uuid");
                    if (redDotByKey != null && redDotByKey.getNum() > 0) {
                        SqFloatViewManager.getInstance().redDotCalled(valueFromUrlStrByParamName, str, CommonFloatMenuLayout.this.mContext);
                    }
                }
                if (CommonFloatMenuLayout.this.onMenuItemClickListener != null) {
                    CommonFloatMenuLayout.this.onMenuItemClickListener.onMenuItemClick(menuConfig);
                }
                if ("1".equals(menuConfig.openType)) {
                    CommonFloatMenuLayout.this.showHalfWebView(menuConfig);
                } else if ("2".equals(menuConfig.openType)) {
                    CommonFloatMenuLayout commonFloatMenuLayout = CommonFloatMenuLayout.this;
                    commonFloatMenuLayout.jumpBrowser(AppUtils.constructWebUrlParam(commonFloatMenuLayout.mContext, menuConfig.openUrl));
                } else if ("3".equals(menuConfig.openType)) {
                    CommonFloatMenuLayout.this.callMethod(menuConfig);
                } else if ("4".equals(menuConfig.openType)) {
                    CommonFloatMenuLayout.this.showWebView(menuConfig);
                }
                CommonFloatMenuLayout.this.needUpdate = true;
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.ball_entry_access, new HashMap<String, String>() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.2.1
                    {
                        String str2;
                        put(SqTrackKey.ball_id, menuConfig.id);
                        put(SqTrackKey.ball_name, menuConfig.title);
                        put(SqTrackKey.ball_url, menuConfig.openUrl);
                        if (redDotByKey != null) {
                            str2 = redDotByKey.getNum() + "";
                        } else {
                            str2 = "0";
                        }
                        put(SqTrackKey.entry_red_dot, str2);
                    }
                });
            }
        });
    }

    private void loadBackgroundImg() {
        ImageView imageView = (ImageView) findViewById(SqResUtils.getId(this.mContext, "iv_background"));
        MenuUIConfig menuUIConfig = this.mUIConfig;
        if (menuUIConfig == null || TextUtils.isEmpty(menuUIConfig.mBackgroundUrl)) {
            imageView.setVisibility(8);
            return;
        }
        try {
            imageView.setVisibility(0);
            new AsyncImageLoader(this.mContext).loadDrawable(this.mUIConfig.mBackgroundUrl, imageView, new AsyncImageLoader.ImageCallback() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.3
                @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
                public void imageLoaded(Bitmap bitmap, ImageView imageView2, String str) {
                    imageView2.setImageBitmap(bitmap);
                }
            });
        } catch (Exception e) {
            LogUtil.e("loadBackImg", e.getMessage());
        }
    }

    public void refreshRedDot(List<RedDot> list) {
        this.redDots = list;
        Context context = this.mContext;
        if (context == null || !(context instanceof Activity)) {
            return;
        }
        ((Activity) context).runOnUiThread(new Runnable() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.4
            @Override // java.lang.Runnable
            public void run() {
                CommonFloatMenuLayout.this.floatItemAdapter.setRedDots(CommonFloatMenuLayout.this.redDots);
            }
        });
    }

    private void initPersonPanel() {
        if (this.view == null) {
            return;
        }
        this.ivHead = (ImageView) findViewById(SqResUtils.getId(this.mContext, "iv_head"));
        this.tvNick = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_nick"));
        this.ivLevel = (ImageView) findViewById(SqResUtils.getId(this.mContext, "iv_level"));
        this.ivEdit = (ImageView) findViewById(SqResUtils.getId(this.mContext, "iv_edit"));
        this.personPanel = findViewById(SqResUtils.getId(this.mContext, "person_panel"));
        this.tvUserCenter = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_user_center"));
        this.personLayout = findViewById(SqResUtils.getId(this.mContext, "person_layout"));
        this.tvChangeAccount = (TextView) findViewById(SqResUtils.getId(this.mContext, "tv_change_account"));
        this.personLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(CommonFloatMenuLayout.this.userCenter)) {
                    return;
                }
                AppUtils.toSQWebUrl(CommonFloatMenuLayout.this.mContext, CommonFloatMenuLayout.this.userCenter, "帐户");
            }
        });
        this.ivHead.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.modifyPersonInfo, SqTrackBtn.SqTrackBtnExt.modifyPersonInfo);
                PersonModifyDialog personModifyDialog = new PersonModifyDialog(CommonFloatMenuLayout.this.mContext);
                personModifyDialog.setModifyPersonInfoListener(new PersonModifyDialog.ModifyPersonInfoListener() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.6.1
                    @Override // com.sy37sdk.account.floatview.PersonModifyDialog.ModifyPersonInfoListener
                    public void modify() {
                        CommonFloatMenuLayout.this.setPersonInfo();
                    }
                });
                personModifyDialog.show();
            }
        });
        this.tvNick.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(CommonFloatMenuLayout.this.userCenter)) {
                    return;
                }
                AppUtils.toSQWebUrl(CommonFloatMenuLayout.this.mContext, CommonFloatMenuLayout.this.userCenter, "帐户");
            }
        });
        this.tvChangeAccount.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonFloatMenuLayout.this.performAccountSwitch();
            }
        });
        if (ConfigManager.getInstance(this.mContext).isSqSDK()) {
            this.personPanel.setVisibility(0);
            setPersonInfo();
        } else {
            this.personPanel.setVisibility(8);
        }
    }

    public void setPersonInfo() {
        if (ConfigManager.getInstance(this.mContext).isSqSDK()) {
            FloatUserInfo floatUserInfo = FloatViewDataCacheHelper.getFloatUserInfo();
            if (floatUserInfo == null) {
                this.tvNick.setText(AccountCache.getUsername(this.mContext));
                return;
            }
            new AsyncImageLoader(this.mContext).loadDrawable(floatUserInfo.getAvatar(), this.ivHead, new AsyncImageLoader.ImageCallback() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.9
                @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
                public void imageLoaded(Bitmap bitmap, ImageView imageView, String str) {
                    CommonFloatMenuLayout.this.ivHead.setImageBitmap(bitmap);
                }
            });
            new AsyncImageLoader(this.mContext).loadDrawable(floatUserInfo.getLevel(), this.ivLevel, new AsyncImageLoader.ImageCallback() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.10
                @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
                public void imageLoaded(Bitmap bitmap, ImageView imageView, String str) {
                    CommonFloatMenuLayout.this.ivLevel.setImageBitmap(bitmap);
                }
            });
            this.tvNick.setText(floatUserInfo.getNickName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWebView(MenuConfig menuConfig) {
        Logger.info("show web dialog", new Object[0]);
        AppUtils.toSQWebUrl(getContext(), menuConfig.openUrl, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showHalfWebView(MenuConfig menuConfig) {
        Logger.info("show half web dialog", new Object[0]);
        SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(getContext());
        sQWebViewDialog.setWebViewBackgroundColor(-1);
        sQWebViewDialog.setUrl(AppUtils.constructWebUrlParam(this.mContext, menuConfig.openUrl));
        sQWebViewDialog.setPortraitHeightWeight(60);
        sQWebViewDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpBrowser(String str) {
        if (TextUtils.isEmpty(str)) {
            ToastUtil.showToast(getContext(), "主人, 此url无效");
            return;
        }
        try {
            this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            Logger.error("Jump Browser exception, url: %s", str, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callMethod(MenuConfig menuConfig) {
        String str = menuConfig.openUrl;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.equals(SdkMethod.SCREEN_SHOT.value)) {
            ScreenshotManager.getInstance(this.mContext).screenShot();
        } else if (str.equals(SdkMethod.CHANGE_ACCOUNT.value)) {
            performAccountSwitch();
        } else if (str.equals(SdkMethod.SKIP_MINI_APPLET.value)) {
            jumpToMiniProgram(menuConfig.sdk_method_value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performAccountSwitch() {
        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.changeAccount, SqTrackBtn.SqTrackBtnExt.CHANGE_ACCOUNT_FLOAT_VIEW);
        HashMap map = new HashMap();
        map.put(SqTrackKey.logout_type, SqTrackBtn.SqTrackBtnExt.CHANGE_ACCOUNT_FLOAT_VIEW);
        map.put("login_type", AccountCache.getLoginType(this.mContext));
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.LOGOUT_SUCC, map);
        switchAccount();
    }

    private void switchAccount() {
        LiveshowEngine.getInstance().leaveLiveshowRoom(null, null);
        LiveRadioEngine.getInstance().leaveLiveRadioRoom(null, null);
        SqFloatViewManager.getInstance().dismissFloatView();
        ((IAccountMod) ModHelper.get(IAccountMod.class)).webEnLogin(true);
    }

    public void jumpToMiniProgram(String str) {
        Logger.info("jumpToMiniProgram", "flag = " + str);
        SqRequest.of(CommonUrlConstant.SKIP_APPLET_INFO_URL).signV3().addParam("token", ((IAccountMod) ModHelper.get(IAccountMod.class)).getToken()).addParam("flag_id", str).addParam(SqConstants.DSID, SqTrackUtil.getServerid(this.mContext)).addParam(SqConstants.DSNAME, SqTrackUtil.getServerName(this.mContext)).addParam(SqConstants.DRID, SqTrackUtil.getRoleid(this.mContext)).addParam(SqConstants.DRNAME, SqTrackUtil.getRolename(this.mContext)).addParam(SqConstants.DRLEVEL, SqTrackUtil.getRolelevel(this.mContext)).addParamsTransformer(new CommonParamsV3()).get(new SqHttpCallback<String>() { // from class: com.sy37sdk.account.floatview.CommonFloatMenuLayout.11
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                ToastUtil.showToast(CommonFloatMenuLayout.this.getContext(), "无法跳转到微信小程序：" + str2);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str2) {
                if (!SqTrackUtil.checkAppInstalled(CommonFloatMenuLayout.this.getContext(), "com.tencent.mm")) {
                    ToastUtil.showToast(CommonFloatMenuLayout.this.getContext(), "检测到手机没有安装微信，请安装微信后重试");
                    return;
                }
                MiniProgramBean toObject = MiniProgramBean.parseToObject(str2);
                int i = toObject.skipType;
                if (i == 1) {
                    IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(CommonFloatMenuLayout.this.getContext(), toObject.appId);
                    WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                    req.userName = toObject.miniProgramId;
                    req.path = toObject.miniProgramPath;
                    req.miniprogramType = 0;
                    iwxapiCreateWXAPI.sendReq(req);
                    return;
                }
                if (i == 2) {
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.addFlags(268435456);
                        intent.setData(Uri.parse(toObject.schemeUrl));
                        CommonFloatMenuLayout.this.getContext().startActivity(intent);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtil.showToast(CommonFloatMenuLayout.this.getContext(), "微信小程序跳转失败：" + toObject.schemeUrl);
                        return;
                    }
                }
                ToastUtil.showToast(CommonFloatMenuLayout.this.getContext(), "不支持该类型跳转到微信小程序：" + toObject.skipType);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                ToastUtil.showToast(CommonFloatMenuLayout.this.getContext(), str2);
            }
        }, String.class);
    }

    public enum SdkMethod {
        SCREEN_SHOT("screen_shot"),
        CHANGE_ACCOUNT("change_account"),
        SKIP_MINI_APPLET("skip_mini_applet");

        public final String value;

        SdkMethod(String str) {
            this.value = str;
        }
    }

    public void setUpdateRedCallback(UpdateRedDotCallback updateRedDotCallback) {
        this.onUpdateRedDotCallback = updateRedDotCallback;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }
}
