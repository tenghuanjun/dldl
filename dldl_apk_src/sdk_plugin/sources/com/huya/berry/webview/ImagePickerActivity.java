package com.huya.berry.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ImagePickerActivity extends Activity {
    private static final String CAMERA_JPG = "yy_camera.jpg";
    private static final String CROP_JPG = "yy_camera_crop.jpg";
    public static final String KEY_CROP_HEIGHT = "key_crop_height";
    public static final String KEY_CROP_URI = "key_crop_uri";
    public static final String KEY_CROP_WIDTH = "key_crop_width";
    public static final String KEY_NEED_CROP = "key_need_crop";
    public static final String KEY_PICKTYPE = "key_picktype";
    public static int PICTYPE_ALBUM = 2;
    public static int PICTYPE_CAMERA = 1;
    private Uri mCameraUri;
    private Uri mCropUri;
    private Uri mImageUri;
    private int mPickType = PICTYPE_CAMERA;
    private boolean mNeedCrop = true;
    private int mCropWidth = 0;
    private int mCropHeight = 0;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        init();
        Intent intent = getIntent();
        if (intent != null) {
            this.mPickType = intent.getIntExtra(KEY_PICKTYPE, this.mPickType);
            this.mNeedCrop = intent.getBooleanExtra(KEY_NEED_CROP, this.mNeedCrop);
            this.mCropWidth = intent.getIntExtra(KEY_CROP_WIDTH, this.mCropWidth);
            this.mCropHeight = intent.getIntExtra(KEY_CROP_HEIGHT, this.mCropHeight);
        }
        int i = this.mPickType;
        if (i == PICTYPE_CAMERA) {
            onCamera();
        } else if (i == PICTYPE_ALBUM) {
            onAlbum();
        }
    }

    private void init() {
        File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        if (externalFilesDir == null) {
            String strReplace = Environment.getExternalStorageDirectory().getPath().replace("0", "1");
            this.mCameraUri = Uri.fromFile(new File(strReplace, CAMERA_JPG));
            this.mCropUri = Uri.fromFile(new File(strReplace, CROP_JPG));
        } else {
            this.mCameraUri = Uri.fromFile(new File(externalFilesDir, CAMERA_JPG));
            this.mCropUri = Uri.fromFile(new File(externalFilesDir, CROP_JPG));
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            finish();
        }
        switch (i) {
            case 1000:
                if (intent != null) {
                    startCropActivityOrFinish(intent);
                } else {
                    finish();
                }
                break;
            case 1001:
                Intent intent2 = new Intent();
                intent2.setData(this.mCameraUri);
                startCropActivityOrFinish(intent2);
                break;
            case 1002:
                if (intent != null) {
                    intent.setData(this.mCropUri);
                }
                finish(intent);
                break;
        }
    }

    public void finish(Intent intent) {
        setResult(-1, intent);
        super.finish();
    }

    public void onCamera() {
        StartSystemActivity.camera(this, this.mCameraUri);
    }

    public void onAlbum() {
        StartSystemActivity.getImage(this);
    }

    private void startCropActivityOrFinish(Intent intent) {
        if (this.mNeedCrop) {
            Uri data = intent.getData();
            Intent intent2 = new Intent();
            intent2.setDataAndType(data, "image/*");
            intent2.putExtra("crop", "true");
            if (Build.MANUFACTURER.contains("HUAWEI")) {
                intent2.putExtra("aspectX", 9998);
                intent2.putExtra("aspectY", 9999);
            } else {
                intent2.putExtra("aspectX", 1);
                intent2.putExtra("aspectY", 1);
            }
            int i = this.mCropWidth;
            if (i == 0) {
                i = 320;
            }
            intent2.putExtra("outputX", i);
            int i2 = this.mCropHeight;
            intent2.putExtra("outputY", i2 != 0 ? i2 : 320);
            intent2.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent2.putExtra("return-data", false);
            intent2.putExtra("output", this.mCropUri);
            StartSystemActivity.crop(this, intent2);
            return;
        }
        finish(intent);
    }
}
