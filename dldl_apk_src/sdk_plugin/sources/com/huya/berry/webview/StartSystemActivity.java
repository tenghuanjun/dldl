package com.huya.berry.webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StartSystemActivity {
    public static final int CAMERA = 1001;
    public static final int CROP = 1002;
    public static final int GET_IMAGE = 1000;

    public static void camera(Activity activity, Uri uri) {
        try {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", uri);
            activity.startActivityForResult(intent, 1001);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getImage(Activity activity) {
        try {
            activity.startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void crop(Activity activity, Intent intent) {
        try {
            intent.setAction("com.android.camera.action.CROP");
            activity.startActivityForResult(intent, 1002);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wifiSetting(Activity activity) {
        startExternalActivity(activity, new Intent("android.settings.WIFI_SETTINGS"));
    }

    public static void web(Activity activity, String str) {
        startExternalActivity(activity, new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public static void startExternalActivity(Activity activity, String str) {
        try {
            startExternalActivity(activity, new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            L.error(activity, "can not open uri(%s) : %s", str, e);
        }
    }

    public static void startExternalActivity(Activity activity, Intent intent) {
        try {
            intent.setFlags(268435456);
            activity.startActivity(intent);
        } catch (Exception e) {
            L.error(activity, "can not open uri(%s) : %s", intent.getData(), e);
        }
    }

    public static void startSetting(Activity activity) {
        activity.startActivity(new Intent("android.settings.SETTINGS"));
    }
}
