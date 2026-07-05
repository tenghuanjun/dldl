package com.sqwan.msdk.share.wx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.utils.ShareUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQWXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static boolean isSQWXShare;
    private boolean isCreate = false;
    private Bundle shareBundle;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        try {
            Bundle extras = getIntent().getExtras();
            this.shareBundle = extras;
            if (extras == null || !extras.containsKey("shareUrl")) {
                return;
            }
            LogUtil.w("---onCreate share--- ture");
            setContentView(ShareUtil.onCreate(this, this.shareBundle));
            this.isCreate = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && this.isCreate) {
            this.isCreate = false;
            ShareUtil.onWindowFocusChanged(this);
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public void onReq(BaseReq baseReq) {
        baseReq.getType();
    }

    public void onResp(BaseResp baseResp) {
        LogUtil.w("---onResp share--- ture");
        ShareUtil.onResp(baseResp.errCode, this);
        LogUtil.w("---onResp Login--- go");
        finish();
    }
}
