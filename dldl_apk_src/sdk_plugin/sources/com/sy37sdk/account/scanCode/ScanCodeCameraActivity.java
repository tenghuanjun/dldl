package com.sy37sdk.account.scanCode;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import com.google.zxing.Result;
import com.plugin.standard.BaseActivity;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.net.risk.RiskWebActivity;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.PermissionHelper;
import com.sqwan.common.util.SpUtils;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.UserInfo;
import com.sy37sdk.account.face.ui.CameraPermissionDialog;
import com.sy37sdk.account.scanCode.ICameraOperation;
import com.sy37sdk.account.scanCode.ZxingDecoder;
import com.sy37sdk.account.view.base.view.ScannerView;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ScanCodeCameraActivity extends BaseActivity {
    private ZxingDecoder mDecoder;
    private ICameraOperation mICameraOperation;
    private ImageView mIvScanRect;
    private View mLoadingView;
    private final String[] permission = {"android.permission.CAMERA"};
    private final String[] permissionDesc = {"相机权限：用于账号授权登录验证用户身份"};
    private CameraPermissionDialog permissionDialog;
    protected ScannerView scannerView;
    private TextureView surfaceView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(getContext(), "sy37_scan_code_activity"));
        this.mLoadingView = findViewById(SqResUtils.getId(getContext(), "rl_loading_img"));
        this.surfaceView = (TextureView) findViewById(SqResUtils.getId(getContext(), "scan_activity_preview"));
        this.scannerView = (ScannerView) findViewById(SqResUtils.getId(getContext(), "scan_activity_mask"));
        this.mIvScanRect = (ImageView) findViewById(SqResUtils.getId(getContext(), "iv_scan_rect"));
        ((ImageView) findViewById(SqResUtils.getId(getContext(), "iv_back"))).setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.scanCode.-$$Lambda$ScanCodeCameraActivity$Nj_jn6yBR73RP7hz0Dyrev7smxA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0$ScanCodeCameraActivity(view);
            }
        });
        this.mICameraOperation = new Camera2(getContext());
        this.mDecoder = new ZxingDecoder(this.surfaceView, new ZxingDecoder.ResultCallback() { // from class: com.sy37sdk.account.scanCode.ScanCodeCameraActivity.1
            @Override // com.sy37sdk.account.scanCode.ZxingDecoder.ResultCallback
            public void onResult(Result result) {
                LogUtil.i(ScanCodeCameraActivity.this.buildPrefixLog("result " + result));
                ScanCodeCameraActivity.this.mDecoder.pause();
                ScanCodeCameraActivity.this.scanCodeResult(result);
            }
        });
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.QRCODE_SCAN_PAGE);
    }

    public /* synthetic */ void lambda$onCreate$0$ScanCodeCameraActivity(View view) {
        finish();
    }

    public void onResume() {
        super.onResume();
        this.surfaceView.post(new Runnable() { // from class: com.sy37sdk.account.scanCode.ScanCodeCameraActivity.2
            @Override // java.lang.Runnable
            public void run() {
                ScanCodeCameraActivity.this.openCameraWithPermission();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openCamera() {
        LogUtil.i(buildPrefixLog("打开相机"));
        this.scannerView.setFraming(new Rect(this.mIvScanRect.getLeft(), this.mIvScanRect.getTop(), this.mIvScanRect.getRight(), this.mIvScanRect.getBottom()));
        this.mICameraOperation.openCamera(this.surfaceView, this.mDecoder, new ICameraOperation.OpenFailCallback() { // from class: com.sy37sdk.account.scanCode.-$$Lambda$ScanCodeCameraActivity$Bv62AdMth0WlUD3Jl14WFjvrYk0
            @Override // com.sy37sdk.account.scanCode.ICameraOperation.OpenFailCallback
            public final void onFail(String str) {
                this.f$0.lambda$openCamera$1$ScanCodeCameraActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$openCamera$1$ScanCodeCameraActivity(String str) {
        ToastUtil.showToast(str);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openCameraWithPermission() {
        if (!PermissionHelper.getInstance().checkPermissions(this.permission)) {
            final boolean z = SpUtils.get(getContext()).getBoolean("sq_" + this.permission[0], false);
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.QRCODE_SCAN_PERMISSION);
            PermissionHelper.getInstance().requestPermissions(getContext(), this.permission, this.permissionDesc, 1110, new PermissionHelper.PermissionCallback() { // from class: com.sy37sdk.account.scanCode.-$$Lambda$ScanCodeCameraActivity$RGHzIBqIUYrsXfAil3ueFLHmvsE
                @Override // com.sqwan.common.util.PermissionHelper.PermissionCallback
                public final void onRequestPermissionsResult(String[] strArr, int[] iArr) {
                    this.f$0.lambda$openCameraWithPermission$2$ScanCodeCameraActivity(z, strArr, iArr);
                }
            });
            return;
        }
        this.surfaceView.post(new Runnable() { // from class: com.sy37sdk.account.scanCode.-$$Lambda$ScanCodeCameraActivity$rec_iX5f5L8Ntb_FAPslQogAAro
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.openCamera();
            }
        });
    }

    public /* synthetic */ void lambda$openCameraWithPermission$2$ScanCodeCameraActivity(boolean z, String[] strArr, int[] iArr) {
        int length = iArr.length;
        boolean z2 = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (iArr[i] != 0) {
                z2 = true;
                break;
            }
            i++;
        }
        if (z2) {
            LogUtil.w(buildPrefixLog("拒绝相机权限"));
            if (!z) {
                trackScanPermission("2");
            }
            showPermissionTipDialog();
            return;
        }
        trackScanPermission("1");
    }

    private void showPermissionTipDialog() {
        if (this.permissionDialog == null) {
            CameraPermissionDialog cameraPermissionDialog = new CameraPermissionDialog(getContext());
            this.permissionDialog = cameraPermissionDialog;
            cameraPermissionDialog.setCancelListener(new CameraPermissionDialog.CancelListener() { // from class: com.sy37sdk.account.scanCode.-$$Lambda$ScanCodeCameraActivity$tDzk0cQKpon2OH_QSo6bv9xOVss
                @Override // com.sy37sdk.account.face.ui.CameraPermissionDialog.CancelListener
                public final void onCancel() {
                    this.f$0.lambda$showPermissionTipDialog$3$ScanCodeCameraActivity();
                }
            });
            this.permissionDialog.setConfirmListener(new CameraPermissionDialog.ConfirmListener() { // from class: com.sy37sdk.account.scanCode.-$$Lambda$ScanCodeCameraActivity$Aadqc6uPOFMzauh8cDs2mc8mxwc
                @Override // com.sy37sdk.account.face.ui.CameraPermissionDialog.ConfirmListener
                public final void onConfirm() {
                    this.f$0.lambda$showPermissionTipDialog$4$ScanCodeCameraActivity();
                }
            });
        }
        this.permissionDialog.show();
    }

    public /* synthetic */ void lambda$showPermissionTipDialog$3$ScanCodeCameraActivity() {
        LogUtil.v(buildPrefixLog("取消前往相机权限设置"));
        trackScanPermission("4");
        finish();
    }

    public /* synthetic */ void lambda$showPermissionTipDialog$4$ScanCodeCameraActivity() {
        trackScanPermission("3");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scanCodeResult(final Result result) {
        LogUtil.d(buildPrefixLog("授权登录请求 二维码内容：" + result.getText()));
        this.mLoadingView.setVisibility(0);
        UserInfo userInfo = AccountCache.getUserInfo(getContext());
        if (userInfo == null) {
            LogUtil.d(buildPrefixLog("授权登录请求 用户信息为空，不请求"));
        } else {
            ScanCodeRequest.notifyScan(userInfo, result.getText(), new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.scanCode.ScanCodeCameraActivity.3
                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(JSONObject jSONObject) {
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.QRCODE_SCAN_SUCC);
                    ScanCodeCameraActivity.this.mLoadingView.setVisibility(8);
                    LogUtil.d(ScanCodeCameraActivity.this.buildPrefixLog("授权登录请求 onSuccess ：" + jSONObject.toString()));
                    ScanCodeCameraActivity.this.startConfirmActivity(result.getText());
                    ScanCodeCameraActivity.this.finish();
                }

                @Override // com.sq.tool.network.SqHttpCallback.SimpleSqHttpCallback, com.sdk.sq.net.SqRequestCallback
                public void onResponseStateError(int i, int i2, String str, String str2) {
                    super.onResponseStateError(i, i2, str, str2);
                    LogUtil.w(ScanCodeCameraActivity.this.buildPrefixLog("授权登录请求失败 onResponseStateError onResponseStateError msg: " + str + " state " + i2));
                    ScanCodeCameraActivity.this.trackScanFail(str);
                    ScanCodeCameraActivity.this.mLoadingView.setVisibility(8);
                    ToastUtil.showToast(str);
                    ScanCodeCameraActivity.this.mDecoder.resume();
                }

                @Override // com.sq.tool.network.SqHttpCallback.SimpleSqHttpCallback, com.sq.tool.network.SqHttpCallback
                public void onFailure(int i, String str, VolleyError volleyError) {
                    super.onFailure(i, str, volleyError);
                    LogUtil.w(ScanCodeCameraActivity.this.buildPrefixLog("授权登录请求失败 onFailure msg: " + str + " state " + i));
                    ScanCodeCameraActivity.this.trackScanFail(str);
                    ScanCodeCameraActivity.this.mLoadingView.setVisibility(8);
                    ToastUtil.showToast(str);
                    ScanCodeCameraActivity.this.mDecoder.resume();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startConfirmActivity(String str) {
        Intent intent = new Intent();
        intent.setClass(getContext(), ScanCodeConfirmActivity.class);
        intent.putExtra(ScanCodeConfirmActivity.KEY_QRCODE, str);
        intent.putExtra(RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION, "portrait");
        getContext().startActivity(intent);
    }

    public void onPause() {
        super.onPause();
        LogUtil.i(buildPrefixLog("关闭相机"));
        this.mICameraOperation.closeCamera();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PermissionHelper.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String buildPrefixLog(String str) {
        return "【ScanCode】" + str;
    }

    private void trackScanPermission(String str) {
        HashMap map = new HashMap();
        map.put("confirm_result", str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.QRCODE_SCAN_AUTH, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackScanFail(String str) {
        HashMap map = new HashMap();
        map.put(SqTrackKey.reason_fail, str);
        map.put("scene", "扫码失败");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.QRCODE_LOGIN_FAIL, map);
    }
}
