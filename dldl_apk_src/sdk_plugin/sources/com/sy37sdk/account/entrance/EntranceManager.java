package com.sy37sdk.account.entrance;

import android.content.Context;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sq.tool.sqtools.detector.DevicesFingerprint;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.SpUtils;
import com.sqwan.msdk.config.MultiConfigManager;
import com.sy37sdk.account.UrlConstant;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class EntranceManager {
    private static final String KEY_ACCOUNT_LOGIN_STATE = "account_login_state";
    private static final String KEY_QRCODE_SCAN_STATE = "qrcode_scan_state";
    private static final String KEY_QUICK_REG_STATE = "quick_reg_state";
    private static final String KEY_UNAME_REG_STATE = "uname_reg_state";
    private static final String KEY_WX_LOGIN_STATE = "wx_login_state";
    private static final String KEY_WX_LOGIN_UI_VERSION = "wx_login_ui_version";
    public static final int WX_LOGIN_UI_VERSION_V1 = 1;
    public static final int WX_LOGIN_UI_VERSION_V2 = 2;
    private static EntranceManager instance;
    private boolean accountLoginEntrance = true;
    private boolean uNameRegEntrance = false;
    private boolean quickRegEntrance = false;
    private boolean wxLoginEntrance = MultiConfigManager.getInstance().isWechat();
    private int wxLoginUIVersion = 1;
    private int qrcodeScanState = 0;

    private EntranceManager() {
    }

    public static EntranceManager getInstance() {
        if (instance == null) {
            synchronized (EntranceManager.class) {
                if (instance == null) {
                    instance = new EntranceManager();
                }
            }
        }
        return instance;
    }

    public void requestEntranceConfig(final Context context) {
        LogUtil.i("请求sdk入口配置");
        this.accountLoginEntrance = SpUtils.get(context).getBoolean(KEY_ACCOUNT_LOGIN_STATE, this.accountLoginEntrance);
        this.uNameRegEntrance = SpUtils.get(context).getBoolean(KEY_UNAME_REG_STATE, this.uNameRegEntrance);
        this.quickRegEntrance = SpUtils.get(context).getBoolean(KEY_QUICK_REG_STATE, this.quickRegEntrance);
        this.wxLoginEntrance = SpUtils.get(context).getBoolean(KEY_WX_LOGIN_STATE, this.wxLoginEntrance);
        SqRequest.of(UrlConstant.URL_REG_ENTRANCE).signV3().addHeader("D-Token", DevicesFingerprint.getDevToken(SQContextWrapper.getActivity())).addParamsTransformer(new CommonParamsV3()).post(new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.entrance.EntranceManager.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                if (!jSONObject.has(EntranceManager.KEY_ACCOUNT_LOGIN_STATE)) {
                    EntranceManager.this.accountLoginEntrance = true;
                    SpUtils.get(context).remove(EntranceManager.KEY_ACCOUNT_LOGIN_STATE);
                } else {
                    EntranceManager.this.accountLoginEntrance = jSONObject.optInt(EntranceManager.KEY_ACCOUNT_LOGIN_STATE) == 1;
                    SpUtils.get(context).put(EntranceManager.KEY_ACCOUNT_LOGIN_STATE, EntranceManager.this.accountLoginEntrance);
                }
                if (!jSONObject.has(EntranceManager.KEY_UNAME_REG_STATE)) {
                    EntranceManager.this.uNameRegEntrance = false;
                    SpUtils.get(context).remove(EntranceManager.KEY_UNAME_REG_STATE);
                } else {
                    EntranceManager.this.uNameRegEntrance = jSONObject.optInt(EntranceManager.KEY_UNAME_REG_STATE) == 1;
                    SpUtils.get(context).put(EntranceManager.KEY_UNAME_REG_STATE, EntranceManager.this.uNameRegEntrance);
                }
                if (!jSONObject.has(EntranceManager.KEY_QUICK_REG_STATE)) {
                    EntranceManager.this.quickRegEntrance = false;
                    SpUtils.get(context).remove(EntranceManager.KEY_QUICK_REG_STATE);
                } else {
                    EntranceManager.this.quickRegEntrance = jSONObject.optInt(EntranceManager.KEY_QUICK_REG_STATE) == 1;
                    SpUtils.get(context).put(EntranceManager.KEY_QUICK_REG_STATE, EntranceManager.this.quickRegEntrance);
                }
                if (jSONObject.has(EntranceManager.KEY_WX_LOGIN_STATE)) {
                    EntranceManager.this.wxLoginEntrance = jSONObject.optInt(EntranceManager.KEY_WX_LOGIN_STATE) == 1;
                    SpUtils.get(context).put(EntranceManager.KEY_WX_LOGIN_STATE, EntranceManager.this.wxLoginEntrance);
                }
                if (jSONObject.has(EntranceManager.KEY_WX_LOGIN_UI_VERSION)) {
                    EntranceManager.this.wxLoginUIVersion = jSONObject.optInt(EntranceManager.KEY_WX_LOGIN_UI_VERSION, 1);
                }
                if (jSONObject.has(EntranceManager.KEY_QRCODE_SCAN_STATE)) {
                    EntranceManager entranceManager = EntranceManager.this;
                    entranceManager.qrcodeScanState = jSONObject.optInt(EntranceManager.KEY_QRCODE_SCAN_STATE, entranceManager.qrcodeScanState);
                }
            }
        });
    }

    public boolean isUNameRegEntrance() {
        return this.uNameRegEntrance;
    }

    public boolean isQuickRegEntrance() {
        return this.quickRegEntrance;
    }

    public boolean isAccountLoginEntrance() {
        return this.accountLoginEntrance;
    }

    public boolean isWxLoginEntrance() {
        return this.wxLoginEntrance;
    }

    public boolean isSupportScanLogin() {
        return this.qrcodeScanState == 1;
    }

    public int getWxLoginUIVersion() {
        return this.wxLoginUIVersion;
    }

    public boolean supportWxEntrance(Context context) {
        return isWxLoginEntrance() && MultiConfigManager.getInstance().supportWx(context);
    }
}
