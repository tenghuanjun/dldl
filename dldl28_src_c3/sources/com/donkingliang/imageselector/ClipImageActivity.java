package com.donkingliang.imageselector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import com.donkingliang.imageselector.entry.RequestConfig;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageUtil;
import com.donkingliang.imageselector.utils.StringUtils;
import com.donkingliang.imageselector.utils.VersionUtils;
import com.donkingliang.imageselector.view.ClipImageView;
import com.lzy.okgo.model.Priority;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ClipImageActivity extends Activity {
    private FrameLayout btnBack;
    private FrameLayout btnConfirm;
    private float cropRatio;
    private ClipImageView imageView;
    private boolean isCameraImage;
    private int mRequestCode;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(1);
        setContentView(R.layout.activity_clip_image);
        RequestConfig requestConfig = (RequestConfig) getIntent().getParcelableExtra(ImageSelector.KEY_CONFIG);
        this.mRequestCode = requestConfig.requestCode;
        requestConfig.isSingle = true;
        requestConfig.maxSelectCount = 0;
        this.cropRatio = requestConfig.cropRatio;
        setStatusBarColor();
        ImageSelectorActivity.openActivity(this, this.mRequestCode, requestConfig);
        initView();
    }

    private void setStatusBarColor() {
        if (VersionUtils.isAndroidL()) {
            Window window = getWindow();
            window.addFlags(Priority.BG_LOW);
            window.setStatusBarColor(Color.parseColor("#373c3d"));
        }
    }

    private void initView() {
        this.imageView = (ClipImageView) findViewById(R.id.process_img);
        this.btnConfirm = (FrameLayout) findViewById(R.id.btn_confirm);
        this.btnBack = (FrameLayout) findViewById(R.id.btn_back);
        this.btnConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.ClipImageActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws Throwable {
                if (ClipImageActivity.this.imageView.getDrawable() != null) {
                    ClipImageActivity.this.btnConfirm.setEnabled(false);
                    ClipImageActivity clipImageActivity = ClipImageActivity.this;
                    clipImageActivity.confirm(clipImageActivity.imageView.clipImage());
                }
            }
        });
        this.btnBack.setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.ClipImageActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ClipImageActivity.this.finish();
            }
        });
        this.imageView.setRatio(this.cropRatio);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && i == this.mRequestCode) {
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            this.isCameraImage = intent.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false);
            Bitmap bitmapDecodeSampledBitmapFromFile = ImageUtil.decodeSampledBitmapFromFile(this, stringArrayListExtra.get(0), 720, 1080);
            if (bitmapDecodeSampledBitmapFromFile != null) {
                this.imageView.setBitmapData(bitmapDecodeSampledBitmapFromFile);
                return;
            } else {
                finish();
                return;
            }
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void confirm(Bitmap bitmap) throws Throwable {
        String strSaveImage;
        if (bitmap != null) {
            strSaveImage = ImageUtil.saveImage(bitmap, ImageUtil.getImageCacheDir(this), DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.getDefault())).toString());
            bitmap.recycle();
        } else {
            strSaveImage = null;
        }
        if (StringUtils.isNotEmptyString(strSaveImage)) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(strSaveImage);
            Intent intent = new Intent();
            intent.putStringArrayListExtra(ImageSelector.SELECT_RESULT, arrayList);
            intent.putExtra(ImageSelector.IS_CAMERA_IMAGE, this.isCameraImage);
            setResult(-1, intent);
        }
        finish();
    }

    public static void openActivity(Activity activity, int i, RequestConfig requestConfig) {
        Intent intent = new Intent(activity, (Class<?>) ClipImageActivity.class);
        intent.putExtra(ImageSelector.KEY_CONFIG, requestConfig);
        activity.startActivityForResult(intent, i);
    }

    public static void openActivity(Fragment fragment, int i, RequestConfig requestConfig) {
        Intent intent = new Intent((Context) fragment.getActivity(), (Class<?>) ClipImageActivity.class);
        intent.putExtra(ImageSelector.KEY_CONFIG, requestConfig);
        fragment.startActivityForResult(intent, i);
    }

    public static void openActivity(android.app.Fragment fragment, int i, RequestConfig requestConfig) {
        Intent intent = new Intent(fragment.getActivity(), (Class<?>) ClipImageActivity.class);
        intent.putExtra(ImageSelector.KEY_CONFIG, requestConfig);
        fragment.startActivityForResult(intent, i);
    }
}
