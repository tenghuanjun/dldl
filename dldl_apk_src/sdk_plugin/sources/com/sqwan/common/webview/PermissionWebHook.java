package com.sqwan.common.webview;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.webkit.PermissionRequest;
import com.sq.webview.SimpleWebHook;
import com.sqwan.common.util.AudioPermissionHelper;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ViewUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PermissionWebHook extends SimpleWebHook {
    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPermissionRequest(PermissionRequest permissionRequest) {
        ArrayList<String> arrayList = new ArrayList();
        String[] resources = permissionRequest.getResources();
        if (resources == null) {
            permissionRequest.deny();
            return;
        }
        for (String str : resources) {
            if ("android.webkit.resource.VIDEO_CAPTURE".equals(str)) {
                arrayList.add("android.permission.CAMERA");
            } else if ("android.webkit.resource.AUDIO_CAPTURE".equals(str)) {
                arrayList.add("android.permission.RECORD_AUDIO");
            } else {
                permissionRequest.deny();
                return;
            }
        }
        if (arrayList.isEmpty()) {
            permissionRequest.deny();
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        for (String str2 : arrayList) {
            if (ContextCompat.checkSelfPermission(this.mWebView.getContext(), str2) == -1) {
                arrayList2.add(str2);
            }
        }
        if (arrayList2.isEmpty()) {
            LogUtil.i("PermissionWebHook is grant");
            permissionRequest.grant(resources);
            return;
        }
        if (arrayList2.contains("android.permission.RECORD_AUDIO")) {
            Activity activity = ViewUtils.getActivity(this.mWebView);
            if (activity == null) {
                LogUtil.i("PermissionWebHook.AUDIO_PERMISSION is denied, Activity = null");
                permissionRequest.deny();
                return;
            } else {
                LogUtil.i("PermissionWebHook.AUDIO_PERMISSION is requesting");
                AudioPermissionHelper.requestAudioPermission(activity, permissionRequest);
                return;
            }
        }
        LogUtil.i("PermissionWebHook is denied");
        permissionRequest.deny();
    }
}
