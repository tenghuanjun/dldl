package com.sy37sdk.account.controller;

import android.content.Context;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.account.bean.UIVersion;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UIVersionManager {
    private static UIVersionManager instance;
    private Context mContext;
    private AbstractLoginController mLoginController;
    private UIVersion uiVersion = new UIVersion();

    private UIVersionManager(Context context) {
        this.mContext = context;
    }

    public static UIVersionManager getInstance(Context context) {
        if (instance == null) {
            synchronized (UIVersionManager.class) {
                if (instance == null) {
                    instance = new UIVersionManager(context);
                }
            }
        }
        return instance;
    }

    public void initVersion(String str) {
        this.uiVersion = UIVersion.fromJson(str);
        LogUtil.e("init ui version " + this.uiVersion.toString());
        this.mLoginController = new FastVerifyController(this.mContext);
    }

    public AbstractLoginController getLoginController() {
        if (this.mLoginController == null) {
            this.mLoginController = new FastVerifyController(this.mContext);
        }
        return this.mLoginController;
    }

    public boolean oneKeyRegOpen() {
        return this.uiVersion.getFregister() != 0;
    }
}
