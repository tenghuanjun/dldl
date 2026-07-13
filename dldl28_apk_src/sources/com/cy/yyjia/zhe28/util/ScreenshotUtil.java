package com.cy.yyjia.zhe28.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class ScreenshotUtil {
    public static File saveScreenshotFromView(View view, Activity context) {
        view.setDrawingCacheEnabled(true);
        File fileSaveImageToGallery = saveImageToGallery(view.getDrawingCache(), context, "temp.png");
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
        return fileSaveImageToGallery;
    }

    public static File saveImageToGallery(Bitmap bmp, Activity context, String name) {
        File file = new File(context.getCacheDir().getPath() + "/pic");
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file, name);
        if (file2.exists()) {
            file2.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file2;
    }

    public static String getDCIM() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return "";
        }
        String str = Environment.getExternalStorageDirectory().getPath() + "/dcim/";
        if (new File(str).exists()) {
            return str;
        }
        String str2 = Environment.getExternalStorageDirectory().getPath() + "/DCIM";
        File file = new File(str2);
        return (file.exists() || file.mkdirs()) ? str2 : "";
    }
}
