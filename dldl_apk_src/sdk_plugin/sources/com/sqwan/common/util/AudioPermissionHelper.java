package com.sqwan.common.util;

import android.app.Activity;
import android.webkit.PermissionRequest;
import com.sqwan.base.ActivityResultListener;
import com.sqwan.base.EventDispatcher;
import com.sqwan.common.dialog.AudioPermissionDialog;
import com.sqwan.common.eventbus.OnActivityResultEvent;
import com.sqwan.common.util.PermissionSimpleHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AudioPermissionHelper {
    public static final String[] AUDIO_PERMISSION = {"android.permission.RECORD_AUDIO"};
    public static final String AUDIO_PERMISSION_BY_RECORD = "申请麦克风权限：用于客服AI语音功能";
    public static final String AUDIO_PERMISSION_NAME = "麦克风权限";
    public static final int SQ_AUDIO_REQUEST_PERMISSION_CODE = 2025;

    public static void requestAudioPermission(final Activity activity, final PermissionRequest permissionRequest) {
        boolean zIsPermissionForbiden = PermissionHelper.getInstance().isPermissionForbiden(AUDIO_PERMISSION[0]);
        LogUtil.i("isPermissionForbidden:" + zIsPermissionForbiden);
        if (zIsPermissionForbiden) {
            showDialog(activity, permissionRequest);
        } else {
            PermissionSimpleHelper.requestPermission(AUDIO_PERMISSION, AUDIO_PERMISSION_NAME, AUDIO_PERMISSION_BY_RECORD, new PermissionSimpleHelper.OnPermissionCallback() { // from class: com.sqwan.common.util.AudioPermissionHelper.1
                @Override // com.sqwan.common.util.PermissionSimpleHelper.OnPermissionCallback
                public void onGranted() {
                    LogUtil.i("AudioPermissionHelper.AUDIO_PERMISSION is granted");
                    PermissionRequest permissionRequest2 = permissionRequest;
                    permissionRequest2.grant(permissionRequest2.getResources());
                }

                @Override // com.sqwan.common.util.PermissionSimpleHelper.OnPermissionCallback
                public void onDenied() {
                    LogUtil.i("AudioPermissionHelper.AUDIO_PERMISSION is denied");
                    AudioPermissionHelper.showDialog(activity, permissionRequest);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showDialog(Activity activity, final PermissionRequest permissionRequest) {
        final AudioPermissionDialog audioPermissionDialog = new AudioPermissionDialog(activity);
        audioPermissionDialog.setConfirmListener(new AudioPermissionDialog.ConfirmListener() { // from class: com.sqwan.common.util.AudioPermissionHelper.2
            @Override // com.sqwan.common.dialog.AudioPermissionDialog.ConfirmListener
            public void onConfirm() {
                EventDispatcher.getInstance().addActivityResultListener(new ActivityResultListener() { // from class: com.sqwan.common.util.AudioPermissionHelper.2.1
                    @Override // com.sqwan.base.ActivityResultListener
                    public void onResult(OnActivityResultEvent onActivityResultEvent) {
                        if (onActivityResultEvent.getRequestCode() != 2025) {
                            return;
                        }
                        EventDispatcher.getInstance().removeActivityResultListener(this);
                        boolean zCheckPermission = PermissionHelper.getInstance().checkPermission(AudioPermissionHelper.AUDIO_PERMISSION[0]);
                        LogUtil.i("AudioPermissionHelper onResult isHasAudioPermission = " + zCheckPermission);
                        if (zCheckPermission) {
                            permissionRequest.grant(permissionRequest.getResources());
                        } else {
                            audioPermissionDialog.show();
                        }
                    }
                });
            }
        });
        audioPermissionDialog.setCancelListener(new AudioPermissionDialog.CancelListener() { // from class: com.sqwan.common.util.AudioPermissionHelper.3
            @Override // com.sqwan.common.dialog.AudioPermissionDialog.CancelListener
            public void onCancel() {
                permissionRequest.deny();
            }
        });
        audioPermissionDialog.show();
    }
}
