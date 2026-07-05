package com.sqwan.common.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.sqwan.base.ActivityResultListener;
import com.sqwan.base.BaseEnginHandler;
import com.sqwan.base.EventDispatcher;
import com.sqwan.base.L;
import com.sqwan.common.dialog.MessageDialog;
import com.sqwan.common.dialog.PermissionDescDialog;
import com.sqwan.common.eventbus.OnActivityResultEvent;
import com.sqwan.common.mvp.BaseDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PermissionHelper extends BaseEnginHandler {
    public static final int APPLICATION_DETAILS_SETTINGS_REQUEST_CODE = 1024;
    public static final int SETTING_REQUEST_CODE = 2121;
    public static final int SQ_REQUEST_PERMISSION_CODE = 1110;
    private static PermissionHelper sInstance;
    private String currentPermission;
    private PermissionCallback currentPermissionCallback;
    private int currentRequestCode;
    private Context mContext;
    private PermissionDescDialog permissionDescDialog;
    public static final String[] DEFAULT_PERMISSIONS_DESC = {"权限说明\n读取设备唯一标识用于保护账号安全", "权限说明\n实现账号、图片的缓存和使用，图片保存与分享"};
    public static String[] mInitPermissions = {"android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private List<String> needRequestPermissions = new ArrayList();
    private List<String> permissionDesc = new ArrayList();
    private int currentPermissionPos = 0;
    private List<Integer> mGrantResults = new ArrayList();
    private Map<Integer, PermissionCallback> mCallbackMaps = new HashMap();

    public interface OnPermissionPageCallback {

        /* JADX INFO: renamed from: com.sqwan.common.util.PermissionHelper$OnPermissionPageCallback$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onDenied(OnPermissionPageCallback onPermissionPageCallback) {
            }
        }

        void onDenied();

        void onGranted();
    }

    public interface PermissionCallback {
        void onRequestPermissionsResult(String[] strArr, int[] iArr);
    }

    private PermissionHelper() {
    }

    public static PermissionHelper getInstance() {
        if (sInstance == null) {
            sInstance = new PermissionHelper();
        }
        return sInstance;
    }

    public boolean checkPermission(String str) {
        return ContextCompat.checkSelfPermission(this.context, str) == 0;
    }

    public boolean isPermissionForbiden(String str) {
        Activity activityCheckValid = checkValid();
        if (activityCheckValid != null) {
            return ActivityCompat.shouldShowRequestPermissionRationale(activityCheckValid, str);
        }
        return false;
    }

    public void requestPermissions(String[] strArr, int i, PermissionCallback permissionCallback) {
        this.mCallbackMaps.put(Integer.valueOf(i), permissionCallback);
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!checkPermission(str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.size() > 0) {
            String[] strArr2 = new String[arrayList.size()];
            Activity activityCheckValid = checkValid();
            if (activityCheckValid != null) {
                ActivityCompat.requestPermissions(activityCheckValid, (String[]) arrayList.toArray(strArr2), i);
            }
        }
    }

    private void clear() {
        this.mCallbackMaps.clear();
        this.permissionDesc.clear();
        this.needRequestPermissions.clear();
        this.mGrantResults.clear();
        this.currentPermissionPos = 0;
    }

    public void requestPermissions(String[] strArr, String[] strArr2, int i, PermissionCallback permissionCallback) {
        requestPermissions(L.getActivity(), strArr, strArr2, i, permissionCallback);
    }

    public void requestPermissions(Context context, String[] strArr, String[] strArr2, int i, PermissionCallback permissionCallback) {
        this.mContext = context;
        clear();
        this.mCallbackMaps.put(Integer.valueOf(i), permissionCallback);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str = strArr[i2];
            if (!checkPermission(str)) {
                if (!SpUtils.get(L.getActivity()).getBoolean("sq_" + str, false)) {
                    arrayList.add(str);
                    this.permissionDesc.add(strArr2[i2]);
                }
            }
        }
        if (arrayList.size() > 0) {
            this.needRequestPermissions = arrayList;
            this.currentRequestCode = i;
            this.currentPermissionCallback = permissionCallback;
            requestPermission(this.currentPermissionPos);
            return;
        }
        PermissionCallback permissionCallback2 = this.mCallbackMaps.get(Integer.valueOf(i));
        if (permissionCallback2 != null) {
            int[] iArr = new int[strArr.length];
            for (int i3 = 0; i3 < strArr.length; i3++) {
                iArr[i3] = ContextCompat.checkSelfPermission(context, strArr[i3]);
            }
            permissionCallback2.onRequestPermissionsResult(strArr, iArr);
            return;
        }
        LogUtil.d("权限回调==null： " + i);
    }

    private void requestPermissions(String str, String str2, int i, PermissionCallback permissionCallback) {
        Context context = this.mContext;
        if (context instanceof Activity) {
            PermissionDescDialog permissionDescDialog = new PermissionDescDialog(this.mContext);
            this.permissionDescDialog = permissionDescDialog;
            permissionDescDialog.setDesc(str2);
            this.permissionDescDialog.show();
            this.currentPermission = str;
            ActivityCompat.requestPermissions((Activity) context, new String[]{str}, i);
        }
    }

    private void requestPermission(int i) {
        requestPermissions(this.needRequestPermissions.get(i), this.permissionDesc.get(i), this.currentRequestCode, this.currentPermissionCallback);
    }

    public boolean checkPermissions(String[] strArr) {
        for (String str : strArr) {
            if (!checkPermission(str)) {
                return false;
            }
        }
        return true;
    }

    public void showPermissionDialog(final Context context, String str) {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setTitle("权限申请").setMessage(str + ",我们保证权限获取仅用于必要功能").setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.sqwan.common.util.PermissionHelper.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ((Activity) context).startActivityForResult(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + context.getPackageName())), PermissionHelper.SETTING_REQUEST_CODE);
                dialogInterface.dismiss();
            }
        }).create();
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.show();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (iArr.length > 0) {
            for (int i2 : iArr) {
                this.mGrantResults.add(Integer.valueOf(i2));
            }
        }
        SpUtils.get(L.getActivity()).put("sq_" + this.currentPermission, true);
        PermissionDescDialog permissionDescDialog = this.permissionDescDialog;
        if (permissionDescDialog != null) {
            permissionDescDialog.dismiss();
        }
        int i3 = this.currentPermissionPos + 1;
        this.currentPermissionPos = i3;
        if (i3 < this.needRequestPermissions.size()) {
            requestPermission(this.currentPermissionPos);
            return;
        }
        PermissionCallback permissionCallback = this.mCallbackMaps.get(Integer.valueOf(i));
        if (permissionCallback != null) {
            LogUtil.d("权限回调： " + i);
            int[] iArr2 = new int[this.mGrantResults.size()];
            for (int i4 = 0; i4 < this.mGrantResults.size(); i4++) {
                iArr2[i4] = this.mGrantResults.get(i4).intValue();
            }
            permissionCallback.onRequestPermissionsResult((String[]) this.needRequestPermissions.toArray(new String[0]), iArr2);
            return;
        }
        LogUtil.d("权限回调==null： " + i);
    }

    public void showPermissionGuideDialog(final Activity activity, final String[] strArr, String str, final OnPermissionPageCallback onPermissionPageCallback) {
        MessageDialog dialogListener = new MessageDialog(activity).setDialogTitle("授权提醒").setDialogMessage(str).setDialogCancel("").setDialogListener(new MessageDialog.OnListener() { // from class: com.sqwan.common.util.-$$Lambda$PermissionHelper$yWLv3FGBjeQniZZcTQfz2NtoJIk
            @Override // com.sqwan.common.dialog.MessageDialog.OnListener
            public /* synthetic */ void onCancel(BaseDialog baseDialog) {
                MessageDialog.OnListener.CC.$default$onCancel(this, baseDialog);
            }

            @Override // com.sqwan.common.dialog.MessageDialog.OnListener
            public final void onConfirm(BaseDialog baseDialog) {
                this.f$0.lambda$showPermissionGuideDialog$0$PermissionHelper(activity, strArr, onPermissionPageCallback, baseDialog);
            }
        });
        dialogListener.setCancelable(false);
        dialogListener.show();
    }

    public /* synthetic */ void lambda$showPermissionGuideDialog$0$PermissionHelper(Activity activity, final String[] strArr, final OnPermissionPageCallback onPermissionPageCallback, BaseDialog baseDialog) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, 1024);
        EventDispatcher.getInstance().addActivityResultListener(new ActivityResultListener() { // from class: com.sqwan.common.util.PermissionHelper.2
            @Override // com.sqwan.base.ActivityResultListener
            public void onResult(OnActivityResultEvent onActivityResultEvent) {
                if (onActivityResultEvent.getRequestCode() != 1024) {
                    return;
                }
                EventDispatcher.getInstance().removeActivityResultListener(this);
                String[] strArr2 = strArr;
                int length = strArr2.length;
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = true;
                        break;
                    }
                    if (ContextCompat.checkSelfPermission(PermissionHelper.this.context, strArr2[i]) == -1) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    onPermissionPageCallback.onGranted();
                } else {
                    onPermissionPageCallback.onDenied();
                }
            }
        });
    }
}
