package com.sqwan.common.mod.share;

import com.parameters.share.ShareMessage;
import com.sqwan.common.mod.IModBase;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IShareMod extends IModBase {
    void share(ShareMessage shareMessage, IShareResultListener iShareResultListener);
}
