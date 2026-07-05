package com.sqwan.common.web;

import android.content.Intent;
import android.os.Bundle;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.host.PluginActivity;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class UserProtocolWebPluginActivity extends PluginActivity {
    @Override // com.plugin.core.ProxyPluginActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        if (intent == null) {
            intent = new Intent();
        }
        intent.putExtra(TTDownloadField.TT_ACTIVITY, "com.sqwan.common.web.UserProtocolWebActivity");
        super.onCreate(bundle);
    }
}
