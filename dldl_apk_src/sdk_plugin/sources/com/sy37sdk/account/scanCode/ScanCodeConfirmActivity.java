package com.sy37sdk.account.scanCode;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.plugin.standard.BaseActivity;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.net.risk.RiskWebActivity;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.AccountCache;
import java.util.HashMap;
import notchtools.geek.com.notchtools.NotchTools;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ScanCodeConfirmActivity extends BaseActivity {
    public static final String KEY_QRCODE = "key_qrcode";
    private String mQrCode;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(getContext(), "sy37_scan_code_confirm_activity"));
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.sy37sdk.account.scanCode.ScanCodeConfirmActivity.1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                StatusBarUtil.hideSystemUI(ScanCodeConfirmActivity.this.getWindow());
                int statusHeight = NotchTools.getFullScreenTools().getStatusHeight(ScanCodeConfirmActivity.this.getWindow());
                ScanCodeConfirmActivity scanCodeConfirmActivity = ScanCodeConfirmActivity.this;
                View viewFindViewById = scanCodeConfirmActivity.findViewById(SqResUtils.getId(scanCodeConfirmActivity.getContext(), "title_bar"));
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewFindViewById.getLayoutParams();
                layoutParams.topMargin = statusHeight;
                viewFindViewById.setLayoutParams(layoutParams);
            }
        });
        this.mQrCode = getIntent().getStringExtra(KEY_QRCODE);
        TextView textView = (TextView) findViewById(SqResUtils.getId(getContext(), "tv_login"));
        TextView textView2 = (TextView) findViewById(SqResUtils.getId(getContext(), "tv_cancel"));
        ((ImageView) findViewById(SqResUtils.getId(getContext(), "iv_back"))).setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.scanCode.-$$Lambda$ScanCodeConfirmActivity$SB9PRNbCH7a8areZfepSo7dFdXc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0$ScanCodeConfirmActivity(view);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.scanCode.-$$Lambda$ScanCodeConfirmActivity$ibaSAlL-_-D5jzXbHRU3LQ-ahsE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$1$ScanCodeConfirmActivity(view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.scanCode.-$$Lambda$ScanCodeConfirmActivity$sYGBvvbjZreaAZ9Ht2_JbAcwAqc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$2$ScanCodeConfirmActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$ScanCodeConfirmActivity(View view) {
        cancelLogin();
    }

    public /* synthetic */ void lambda$onCreate$1$ScanCodeConfirmActivity(View view) {
        confirmLogin();
    }

    public /* synthetic */ void lambda$onCreate$2$ScanCodeConfirmActivity(View view) {
        cancelLogin();
    }

    private void confirmLogin() {
        LogUtil.i(buildPrefixLog("确认授权"));
        final HashMap map = new HashMap();
        map.put("confirm_result", "1");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.QRCODE_CONFIRM_SUCC, map);
        ScanCodeRequest.confirmAuth(AccountCache.getToken(getContext()), this.mQrCode, new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.scanCode.ScanCodeConfirmActivity.2
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.QRCODE_LOGIN_SUCC, map);
                ScanCodeConfirmActivity.this.finish();
            }

            @Override // com.sq.tool.network.SqHttpCallback.SimpleSqHttpCallback, com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                super.onResponseStateError(i, i2, str, str2);
                ScanCodeConfirmActivity.this.trackScanLoginFail(str);
                LogUtil.d(ScanCodeConfirmActivity.this.buildPrefixLog("确认授权失败 onResponseStateError msg: " + str + " state " + i2));
                ToastUtil.showToast(str);
                ScanCodeConfirmActivity.this.finish();
                ScanCodeConfirmActivity.this.startScanCodeActivity();
            }

            @Override // com.sq.tool.network.SqHttpCallback.SimpleSqHttpCallback, com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                super.onFailure(i, str, volleyError);
                ScanCodeConfirmActivity.this.trackScanLoginFail(str);
                LogUtil.w(ScanCodeConfirmActivity.this.buildPrefixLog("确认授权失败 onFailure msg: " + str + " state " + i));
                ToastUtil.showToast(ScanCodeConfirmActivity.this.getContext(), str);
            }
        });
    }

    private void cancelLogin() {
        LogUtil.i(buildPrefixLog("取消授权"));
        HashMap map = new HashMap();
        map.put("confirm_result", "2");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.QRCODE_CONFIRM_SUCC, map);
        trackScanLoginFail("取消登录");
        ScanCodeRequest.cancelAuth(AccountCache.getToken(getContext()), this.mQrCode, new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.scanCode.ScanCodeConfirmActivity.3
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                ScanCodeConfirmActivity.this.finish();
            }

            @Override // com.sq.tool.network.SqHttpCallback.SimpleSqHttpCallback, com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                super.onResponseStateError(i, i2, str, str2);
                LogUtil.d(ScanCodeConfirmActivity.this.buildPrefixLog("取消授权失败 onResponseStateError msg: " + str + " state " + i2));
                ScanCodeConfirmActivity.this.finish();
            }

            @Override // com.sq.tool.network.SqHttpCallback.SimpleSqHttpCallback, com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                super.onFailure(i, str, volleyError);
                LogUtil.w(ScanCodeConfirmActivity.this.buildPrefixLog("取消授权 onFailure msg: " + str + " state " + i));
                ScanCodeConfirmActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScanCodeActivity() {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setClass(getContext(), ScanCodeCameraActivity.class);
        intent.putExtra(RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION, "portrait");
        getContext().startActivity(intent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            cancelLogin();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String buildPrefixLog(String str) {
        return "【ScanCode】" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackScanLoginFail(String str) {
        HashMap map = new HashMap();
        map.put(SqTrackKey.reason_fail, str);
        map.put("scene", "授权登录失败");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.QRCODE_LOGIN_FAIL, map);
    }
}
