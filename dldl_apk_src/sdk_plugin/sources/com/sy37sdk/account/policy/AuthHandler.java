package com.sy37sdk.account.policy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import com.sqwan.base.BaseEnginHandler;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackPage;
import com.sqwan.common.util.BusinessUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.PermissionHelper;
import com.sqwan.common.util.SpUtils;
import com.sqwan.common.util.task.Task;
import com.sy37sdk.account.activebefore.ActiveBeforeManager;
import com.sy37sdk.account.policy.view.AuthBaseDialog;
import com.sy37sdk.account.policy.view.PermissionDialog;
import com.sy37sdk.account.policy.view.UserAuthPolicyDesDialog;
import com.sy37sdk.account.policy.view.UserAuthTipsDialog;
import com.sy37sdk.account.policy.view.UserHistorySearchDialog;
import com.sy37sdk.account.trackaction.PageExposureTrackManager;
import com.sy37sdk.account.uagree.UAgreeManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AuthHandler extends BaseEnginHandler {
    private static final String SP_AUTH_HANDLE = "sq_auth_handle";
    public static final String disAgreeUserHistorySearchPermission = "disAgreeUserHistorySearchPermission";
    private static final AuthHandler ourInstance = new AuthHandler();
    private PermissionHelper.PermissionCallback mPermissionCallback = new PermissionHelper.PermissionCallback() { // from class: com.sy37sdk.account.policy.AuthHandler.7
        @Override // com.sqwan.common.util.PermissionHelper.PermissionCallback
        public void onRequestPermissionsResult(String[] strArr, int[] iArr) {
            Activity activityCheckValid = AuthHandler.this.checkValid();
            if (activityCheckValid == null) {
                return;
            }
            LogUtil.i("mPermissionCallback", "sd卡，sim卡权限回调");
            for (int i = 0; i < iArr.length; i++) {
                if (iArr[i] != 0) {
                    LogUtil.i("mPermissionCallback", strArr[i] + " 权限未申请");
                    boolean zShouldShowRequestPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(activityCheckValid, strArr[i]);
                    LogUtil.i("mPermissionCallback", "权限是否被禁止: " + zShouldShowRequestPermissionRationale);
                    if (!zShouldShowRequestPermissionRationale) {
                        AuthHandler.this.handlePermissionSuccess();
                        BusinessUtil.setAuthPermission(AuthHandler.this.context, true);
                        return;
                    }
                } else {
                    LogUtil.i("mPermissionCallback", strArr[i] + " 权限审核通过");
                }
            }
            AuthHandler.this.handlePermissionSuccess();
            BusinessUtil.setAuthPermission(AuthHandler.this.context, true);
            SqTrackActionManager2.getInstance().trackActionCPTest(SqTrackAction2.PERMISSION_CALLBACK);
        }
    };
    private PermissionCallback permissionCallback;
    private PermissionDialog permissionDialog;
    private UserAuthPolicyDesDialog userAuthPolicyDesDialog;
    private UserAuthTipsDialog userAuthTipsDialog;
    private UserHistorySearchDialog userHistorySearchDialog;

    public interface AgreeCallback {
        void invoke(boolean z);
    }

    public interface PermissionCallback {
        void invoke();
    }

    public static AuthHandler getInstance() {
        return ourInstance;
    }

    private AuthHandler() {
    }

    public void showPermissionDialog(String[] strArr, final AuthBaseDialog.ClickCallback clickCallback) {
        Activity activityCheckValid = checkValid();
        if (activityCheckValid == null) {
            return;
        }
        if (TextUtils.isEmpty(PermissionHelper.getInstance().checkPermission(strArr[0]) ? "android.permission.WRITE_EXTERNAL_STORAGE" : "android.permission.READ_PHONE_STATE")) {
            return;
        }
        if (this.permissionDialog == null) {
            this.permissionDialog = new PermissionDialog(activityCheckValid);
        }
        this.permissionDialog.setClickCallback(new AuthBaseDialog.ClickCallback() { // from class: com.sy37sdk.account.policy.AuthHandler.1
            @Override // com.sy37sdk.account.policy.view.AuthBaseDialog.ClickCallback
            public void onClickCancel() {
                AuthHandler.this.exit();
            }

            @Override // com.sy37sdk.account.policy.view.AuthBaseDialog.ClickCallback
            public void onClickOk() {
                AuthBaseDialog.ClickCallback clickCallback2 = clickCallback;
                if (clickCallback2 != null) {
                    clickCallback2.onClickOk();
                }
                AuthHandler.this.permissionDialog.dismiss();
            }
        });
        if (this.permissionDialog.isShowing()) {
            return;
        }
        this.permissionDialog.show();
    }

    public void showPreviewDialog(final AgreeCallback agreeCallback) {
        LogUtil.i(this.TAG, "showPreviewDialog");
        final Activity activityCheckValid = checkValid();
        if (activityCheckValid == null) {
            LogUtil.e(this.TAG, "showPreviewDialog return");
            return;
        }
        if (!UAgreeManager.getInstance().needShow()) {
            LogUtil.i(this.TAG, "needShow");
            if (agreeCallback != null) {
                agreeCallback.invoke(false);
                return;
            }
            return;
        }
        if (this.userAuthPolicyDesDialog == null) {
            UserAuthPolicyDesDialog userAuthPolicyDesDialog = new UserAuthPolicyDesDialog(activityCheckValid);
            this.userAuthPolicyDesDialog = userAuthPolicyDesDialog;
            userAuthPolicyDesDialog.setClickCallback(new AuthBaseDialog.ClickCallback() { // from class: com.sy37sdk.account.policy.AuthHandler.2
                @Override // com.sy37sdk.account.policy.view.AuthBaseDialog.ClickCallback
                public void onClickCancel() {
                    AuthHandler.this.userAuthPolicyDesDialog.dismiss();
                    AuthHandler.this.userAuthTipsDialog.show();
                }

                @Override // com.sy37sdk.account.policy.view.AuthBaseDialog.ClickCallback
                public void onClickOk() {
                    AuthHandler.this.userAuthPolicyDesDialog.dismiss();
                    UAgreeManager.getInstance().update();
                    requestPermissionScene();
                }

                /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
                private void requestPermissionScene() {
                    String sceneDesc = ActiveBeforeManager.getInstance().permissionInfo.getSceneDesc();
                    if (sceneDesc == null) {
                        AuthHandler.this.handlePermissionSuccess();
                        return;
                    }
                    byte b = -1;
                    switch (sceneDesc.hashCode()) {
                        case 48:
                            if (sceneDesc.equals("0")) {
                                b = 2;
                            }
                            break;
                        case 49:
                            if (sceneDesc.equals("1")) {
                                b = 0;
                            }
                            break;
                        case 50:
                            if (sceneDesc.equals("2")) {
                                b = 1;
                            }
                            break;
                    }
                    if (b == 0) {
                        AuthHandler.this.showHistorySearchDialog(activityCheckValid, agreeCallback);
                        return;
                    }
                    if (b == 1 || b == 2) {
                        LogUtil.i(AuthHandler.this.TAG, "直接进游戏");
                        AgreeCallback agreeCallback2 = agreeCallback;
                        if (agreeCallback2 != null) {
                            agreeCallback2.invoke(true);
                        }
                    }
                }
            });
        }
        try {
            this.userAuthPolicyDesDialog.show();
        } catch (Exception e) {
            LogUtil.e(this.TAG, "个人信息保护指引弹窗失败", e);
            BuglessAction.reportCatchException(e, "个人信息保护指引弹窗失败", 101);
        }
        if (this.userAuthTipsDialog == null) {
            UserAuthTipsDialog userAuthTipsDialog = new UserAuthTipsDialog(activityCheckValid);
            this.userAuthTipsDialog = userAuthTipsDialog;
            userAuthTipsDialog.setClickCallback(new AuthBaseDialog.ClickCallback() { // from class: com.sy37sdk.account.policy.AuthHandler.3
                @Override // com.sy37sdk.account.policy.view.AuthBaseDialog.ClickCallback
                public void onClickCancel() {
                }

                @Override // com.sy37sdk.account.policy.view.AuthBaseDialog.ClickCallback
                public void onClickOk() {
                    AuthHandler.this.userAuthTipsDialog.dismiss();
                    AuthHandler.this.userAuthPolicyDesDialog.show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showHistorySearchDialog(Activity activity, final AgreeCallback agreeCallback) {
        if (this.userHistorySearchDialog == null) {
            UserHistorySearchDialog userHistorySearchDialog = new UserHistorySearchDialog(activity);
            this.userHistorySearchDialog = userHistorySearchDialog;
            userHistorySearchDialog.setClickCallback(new AuthBaseDialog.ClickCallback() { // from class: com.sy37sdk.account.policy.AuthHandler.4
                @Override // com.sy37sdk.account.policy.view.AuthBaseDialog.ClickCallback
                public void onClickCancel() {
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.PERMISSION_SCENE_FORBIDDEN);
                    AuthHandler.this.handlePermissionSuccess();
                    if (AuthHandler.this.userAuthPolicyDesDialog != null) {
                        AuthHandler.this.userHistorySearchDialog.dismiss();
                    }
                    SpUtils.get(AuthHandler.this.context).put(AuthHandler.disAgreeUserHistorySearchPermission, true);
                }

                @Override // com.sy37sdk.account.policy.view.AuthBaseDialog.ClickCallback
                public void onClickOk() {
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.PERMISSION_SCENE_GRANTED);
                    AgreeCallback agreeCallback2 = agreeCallback;
                    if (agreeCallback2 != null) {
                        agreeCallback2.invoke(true);
                    }
                    if (AuthHandler.this.userAuthPolicyDesDialog != null) {
                        AuthHandler.this.userHistorySearchDialog.dismiss();
                    }
                    SpUtils.get(AuthHandler.this.context).put(AuthHandler.disAgreeUserHistorySearchPermission, false);
                }
            });
        }
        PageExposureTrackManager.track(SqTrackPage.SqTrackViewId.permission_scene, SqTrackPage.SqTrackViewName.permission_scene);
        this.userHistorySearchDialog.show();
    }

    public void checkPermission(PermissionCallback permissionCallback) {
        this.permissionCallback = permissionCallback;
        Task.post(new Runnable() { // from class: com.sy37sdk.account.policy.AuthHandler.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.i(AuthHandler.this.TAG, "showPreviewDialog");
                AuthHandler.this.showPreviewDialog(new AgreeCallback() { // from class: com.sy37sdk.account.policy.AuthHandler.5.1
                    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
                    @Override // com.sy37sdk.account.policy.AuthHandler.AgreeCallback
                    public void invoke(boolean z) {
                        LogUtil.i(AuthHandler.this.TAG, "showPreviewDialog invoke");
                        String sceneDesc = ActiveBeforeManager.getInstance().permissionInfo.getSceneDesc();
                        if (sceneDesc == null) {
                            AuthHandler.this.handlePermissionSuccess();
                            return;
                        }
                        byte b = -1;
                        switch (sceneDesc.hashCode()) {
                            case 48:
                                if (sceneDesc.equals("0")) {
                                    b = 2;
                                }
                                break;
                            case 49:
                                if (sceneDesc.equals("1")) {
                                    b = 1;
                                }
                                break;
                            case 50:
                                if (sceneDesc.equals("2")) {
                                    b = 0;
                                }
                                break;
                        }
                        if (b == 0 || b == 1) {
                            if (BusinessUtil.getAuthPermission(AuthHandler.this.context)) {
                                AuthHandler.this.handlePermissionSuccess();
                                return;
                            } else {
                                AuthHandler.this.checkPermission(z, false);
                                return;
                            }
                        }
                        AuthHandler.this.handlePermissionSuccess();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestPermissions() {
        PermissionHelper.getInstance().requestPermissions(PermissionHelper.mInitPermissions, PermissionHelper.DEFAULT_PERMISSIONS_DESC, 1110, this.mPermissionCallback);
        SqTrackActionManager2.getInstance().trackActionCPTest(SqTrackAction2.REQUEST_PERMISSION);
    }

    public void checkPermission(boolean z, boolean z2) {
        LogUtil.i(this.TAG, "checkPermission checkDirect " + z + " fromActivityResult " + z2);
        if (PermissionHelper.getInstance().checkPermissions(PermissionHelper.mInitPermissions)) {
            LogUtil.i(this.TAG, "权限申请完毕，初始化开始");
            handlePermissionSuccess();
            return;
        }
        LogUtil.i(this.TAG, "权限申请开始");
        if (z) {
            requestPermissions();
            return;
        }
        if (z2 && checkPermissionForbidden()) {
            requestPermissions();
        } else if (checkPermissionForbidden()) {
            requestPermissions();
        } else {
            getInstance().showPermissionDialog(PermissionHelper.mInitPermissions, new AuthBaseDialog.ClickCallbackAdapter() { // from class: com.sy37sdk.account.policy.AuthHandler.6
                @Override // com.sy37sdk.account.policy.view.AuthBaseDialog.ClickCallbackAdapter, com.sy37sdk.account.policy.view.AuthBaseDialog.ClickCallback
                public void onClickOk() {
                    AuthHandler.this.requestPermissions();
                }
            });
        }
    }

    private void goSetting(Activity activity) {
        LogUtil.i(this.TAG, "goSetting");
        activity.startActivityForResult(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + this.context.getPackageName())), PermissionHelper.SETTING_REQUEST_CODE);
    }

    private boolean checkPermissionForbidden() {
        Activity activityCheckValid = checkValid();
        boolean z = true;
        for (String str : PermissionHelper.mInitPermissions) {
            if (activityCheckValid != null) {
                boolean z2 = !ActivityCompat.shouldShowRequestPermissionRationale(activityCheckValid, str);
                LogUtil.i(this.TAG, "mInitPermission " + str + " " + z);
                z = z && z2;
            }
        }
        LogUtil.i(this.TAG, "checkPermissionForbidden " + z);
        return z;
    }

    public boolean getIsPlayWithoutPermission() {
        return !ActiveBeforeManager.getInstance().permissionInfo.isNecessary();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePermissionSuccess() {
        PermissionCallback permissionCallback = this.permissionCallback;
        if (permissionCallback != null) {
            permissionCallback.invoke();
        }
    }

    public void setAuthHandle() {
        SpUtils.get(this.context).put(SP_AUTH_HANDLE, true);
    }

    public boolean isAuthHandle() {
        return SpUtils.get(this.context).getBoolean(SP_AUTH_HANDLE);
    }
}
