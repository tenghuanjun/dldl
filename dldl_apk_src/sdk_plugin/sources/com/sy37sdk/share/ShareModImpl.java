package com.sy37sdk.share;

import android.content.Context;
import com.parameters.share.ShareMessage;
import com.sqwan.common.mod.share.IShareMod;
import com.sqwan.common.mod.share.IShareResultListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ShareModImpl implements IShareMod {
    private final Context mContext;

    public ShareModImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.sqwan.common.mod.share.IShareMod
    public void share(ShareMessage shareMessage, IShareResultListener iShareResultListener) {
        ShareCoreManager.getInstance(this.mContext).share(shareMessage, iShareResultListener);
    }
}
