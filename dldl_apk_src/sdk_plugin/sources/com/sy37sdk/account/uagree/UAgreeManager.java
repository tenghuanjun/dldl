package com.sy37sdk.account.uagree;

import android.content.Context;
import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
import com.sqwan.base.BaseEnginHandler;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.common.webview.SQWebViewDialog;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.policy.view.PolicyDialog;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UAgreeManager extends BaseEnginHandler {
    public static final int OPERA_LOGIN = 1;
    public static final int OPERA_REGISTER = 2;
    private static final int TYPE_CLOSE = 0;
    private static final int TYPE_LOGIN_ONLY = 2;
    private static final int TYPE_LOGIN_REG = 3;
    private static final int TYPE_REG_ONLY = 1;
    private static volatile UAgreeManager instance;
    private int type = 0;
    private int version = -1;

    private UAgreeManager() {
    }

    @Override // com.sqwan.base.BaseEnginHandler
    public void init(Context context) {
        super.init(context);
        this.context = checkValid();
    }

    public static UAgreeManager getInstance() {
        if (instance == null) {
            synchronized (UAgreeManager.class) {
                if (instance == null) {
                    instance = new UAgreeManager();
                }
            }
        }
        return instance;
    }

    public void initConfig(String str) {
        LogUtil.i("int uagree config:" + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("url_protocol", "");
            String strOptString2 = jSONObject.optString("url_policy", "");
            String strOptString3 = jSONObject.optString("url_interim", "");
            UAgreeCacheHelper.setUrlProtocol(this.context, strOptString);
            UAgreeCacheHelper.setUrlPolicy(this.context, strOptString2);
            UAgreeCacheHelper.setUrlInterim(this.context, strOptString3);
            if (jSONObject.has("uagree_config")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("uagree_config");
                this.type = jSONObject2.optInt("type", 0);
                this.version = jSONObject2.optInt("version", 0);
            }
        } catch (JSONException e) {
            LogUtil.i("解析用户隐私协议配置失败");
            e.printStackTrace();
        }
    }

    public boolean needShow(int i, String str) {
        int version = UAgreeCacheHelper.getVersion(this.context, "permission");
        int i2 = this.version;
        return i2 > version || i2 == -1;
    }

    public void refreshVersion(String str) {
        UAgreeCacheHelper.setVersion(this.context, str, this.version);
    }

    public void showUAgree() {
        SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(this.context);
        sQWebViewDialog.setUrl(generateUAgreeUrl() + "&isAgree=true");
        sQWebViewDialog.show();
    }

    public void showUserProtocol(Context context) {
        SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(context);
        sQWebViewDialog.setUrl(UAgreeCacheHelper.getUrlProtocol(context) + "&isAgree=true");
        sQWebViewDialog.show();
    }

    public void showPolicy(Context context) {
        SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(context);
        sQWebViewDialog.setUrl(UAgreeCacheHelper.getUrlPolicy(context) + "&isAgree=true");
        sQWebViewDialog.show();
    }

    public void showLoginPolicyAlert(PolicyDialog.ConfirmCallback confirmCallback) {
        new PolicyDialog(this.context, confirmCallback).show();
    }

    public void showUAgreeToast() {
        new ToastUtil.Builder(this.context).setBackgroundColor(Color.parseColor("#BB000000")).addText("请勾选同意").addText("用户协议", new ForegroundColorSpan(this.context.getResources().getColor(SqResUtils.getColorId(this.context, "sy37_reg_protocol_txt_color")))).addText("与").addText("隐私政策", new ForegroundColorSpan(this.context.getResources().getColor(SqResUtils.getColorId(this.context, "sy37_reg_protocol_txt_color")))).addText("再进入游戏").show();
    }

    private String generateUAgreeUrl() {
        return addBaseParams(this.context, UAgreeCacheHelper.getUrlInterim(this.context));
    }

    public static String addBaseParams(Context context, String str) {
        SQAppConfig sQAppConfig = ConfigManager.getInstance(context).getSQAppConfig();
        String str2 = "gid=" + sQAppConfig.getGameid() + "&pid=" + sQAppConfig.getPartner() + "&dev=" + DevLogic.getInstance(context).getValue() + "&token=" + AccountCache.getToken(context) + "&sversion=" + VersionUtil.sdkVersion + "&refer=" + sQAppConfig.getRefer();
        if (str.contains("?")) {
            return str + "&" + str2;
        }
        return str + "?" + str2;
    }

    public boolean needShow() {
        return needShow(1, "permission");
    }

    public void update() {
        UAgreeCacheHelper.setVersion(this.context, "permission", this.version);
    }

    public boolean isFirstCheck() {
        return UAgreeCacheHelper.getVersion(this.context, "permission") == 0;
    }
}
