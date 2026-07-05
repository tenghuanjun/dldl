package com.sq.webview.permission;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import com.sq.webview.util.WebLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PermissionFragment extends Fragment {
    private static final String REQUEST_CODE = "request_code";
    private static final List<Integer> REQUEST_CODE_ARRAY = new ArrayList();
    private static final String REQUEST_PERMISSIONS = "request_permissions";
    public static final String TAG = "【Permission】";
    private OnPermissionCallback mCallBack;
    private boolean mRequestFlag;

    public static void launch(Activity activity, ArrayList<String> permissions, OnPermissionCallback callback) {
        int iNextInt;
        PermissionFragment permissionFragment = new PermissionFragment();
        Bundle bundle = new Bundle();
        do {
            iNextInt = new Random().nextInt((int) Math.pow(2.0d, 8.0d));
        } while (REQUEST_CODE_ARRAY.contains(Integer.valueOf(iNextInt)));
        REQUEST_CODE_ARRAY.add(Integer.valueOf(iNextInt));
        bundle.putInt(REQUEST_CODE, iNextInt);
        bundle.putStringArrayList(REQUEST_PERMISSIONS, permissions);
        permissionFragment.setArguments(bundle);
        permissionFragment.setRequestFlag(true);
        permissionFragment.setRetainInstance(true);
        permissionFragment.setCallback(callback);
        permissionFragment.attachActivity(activity);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestSpecialPermission();
    }

    public void setRequestFlag(boolean flag) {
        this.mRequestFlag = flag;
    }

    public void setCallback(OnPermissionCallback callback) {
        this.mCallBack = callback;
    }

    public void attachActivity(Activity activity) {
        activity.getFragmentManager().beginTransaction().add(this, toString()).commitAllowingStateLoss();
    }

    public void detachActivity(Activity activity) {
        activity.getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mRequestFlag) {
            return;
        }
        detachActivity(getActivity());
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mCallBack = null;
    }

    public void requestSpecialPermission() {
        WebLogUtil.i("【Permission】", "requestSpecialPermission");
        Bundle arguments = getArguments();
        Activity activity = getActivity();
        if (arguments == null || activity == null) {
            WebLogUtil.e("【Permission】", "arguments或者activity为空");
            return;
        }
        ArrayList<String> stringArrayList = arguments.getStringArrayList(REQUEST_PERMISSIONS);
        if (stringArrayList == null || stringArrayList.isEmpty()) {
            WebLogUtil.e("【Permission】", "allPermissions为空");
            return;
        }
        String[] strArr = (String[]) stringArrayList.toArray(new String[0]);
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(strArr, getArguments().getInt(REQUEST_CODE));
        }
    }

    @Override // android.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions == null || grantResults == null || permissions.length == 0 || grantResults.length == 0) {
            return;
        }
        Bundle arguments = getArguments();
        Activity activity = getActivity();
        if (activity == null || arguments == null || requestCode != arguments.getInt(REQUEST_CODE)) {
            return;
        }
        ArrayList arrayListAsArrayList = PermissionUtil.asArrayList(permissions);
        REQUEST_CODE_ARRAY.remove(Integer.valueOf(requestCode));
        detachActivity(activity);
        List<String> grantedPermissions = PermissionUtil.getGrantedPermissions(arrayListAsArrayList, grantResults);
        if (grantedPermissions.size() == arrayListAsArrayList.size()) {
            this.mCallBack.onGranted(grantedPermissions, true);
            return;
        }
        List<String> deniedPermissions = PermissionUtil.getDeniedPermissions(arrayListAsArrayList, grantResults);
        if (!grantedPermissions.isEmpty()) {
            this.mCallBack.onGranted(grantedPermissions, false);
        } else {
            this.mCallBack.onDenied(deniedPermissions, false);
        }
    }
}
