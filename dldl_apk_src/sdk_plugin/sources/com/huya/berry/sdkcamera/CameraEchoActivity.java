package com.huya.berry.sdkcamera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.asignal.SignalCenter;
import com.duowan.auk.asignal.notify.PropertySet;
import com.duowan.auk.module.ArkProperties;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.sdkcamera.event.CameraCallback;
import com.huya.berry.sdkcamera.event.CameraInterface;
import com.huya.berry.sdkcamera.event.CameraListener;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CameraEchoActivity extends Activity implements SurfaceHolder.Callback {
    private static final String TAG = CameraEchoActivity.class.getSimpleName();
    private static CameraListener sListener;
    private boolean mIsNetBreak;
    private boolean mIsPausePreview;
    private boolean mIsResume;
    private ImageView mIvSwitchCamera;
    private int mPreviewHeight;
    private int mPreviewWidth;
    private Surface mSurface;
    private SurfaceView mSvCamera;

    public static void startActivity(Context context, CameraListener cameraListener) {
        Intent intent = new Intent(context, (Class<?>) CameraEchoActivity.class);
        sListener = cameraListener;
        context.startActivity(intent);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(!SdkProperties.isLandscape.get().booleanValue() ? 1 : 0);
        setContentView(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_camera_echo_dialog));
        SignalCenter.register(this);
        initView();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        L.info(TAG, "CameraPreview onResume");
        this.mIsResume = true;
        ArkUtils.send(new CameraInterface.onResume());
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mIsResume = false;
        L.info(TAG, "CameraPreview onPause");
        ArkUtils.send(new CameraInterface.onPause());
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        L.info(TAG, "CameraPreview onStart");
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        L.info(TAG, "CameraPreview onStop");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        SignalCenter.unregister(this);
        super.onDestroy();
    }

    private void initView() {
        this.mSvCamera = (SurfaceView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.sv_camera));
        this.mIvSwitchCamera = (ImageView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.iv_switch_camera));
        this.mSvCamera.getHolder().addCallback(this);
        this.mIvSwitchCamera.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkcamera.CameraEchoActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SdkProperties.isPushStreamOk.get().booleanValue()) {
                    ArkUtils.send(new CameraInterface.onSwitchCamera());
                }
            }
        });
    }

    private void pausePreview() {
        if (this.mSurface == null || !SdkProperties.isLiving.get().booleanValue()) {
            return;
        }
        this.mIsPausePreview = true;
        sListener.onSurfaceDestroy();
    }

    private static void setListener(CameraListener cameraListener) {
        sListener = cameraListener;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        L.info(TAG, "CameraPreview surfaceCreated");
        this.mSurface = surfaceHolder.getSurface();
        if (this.mIsPausePreview) {
            SurfaceView surfaceView = this.mSvCamera;
            int width = this.mPreviewWidth;
            if (width == 0) {
                width = surfaceView.getWidth();
            }
            int height = this.mPreviewHeight;
            if (height == 0) {
                height = this.mSvCamera.getHeight();
            }
            ArkUtils.send(new CameraInterface.onResumePreview(surfaceView, width, height));
            this.mIsPausePreview = false;
        } else {
            SurfaceView surfaceView2 = this.mSvCamera;
            int width2 = this.mPreviewWidth;
            if (width2 == 0) {
                width2 = surfaceView2.getWidth();
            }
            int height2 = this.mPreviewHeight;
            if (height2 == 0) {
                height2 = this.mSvCamera.getHeight();
            }
            ArkUtils.send(new CameraInterface.GetPreviewSurface(surfaceView2, width2, height2));
        }
        L.info(TAG, "CameraPreview surfaceCreated + getWidth " + this.mSvCamera.getWidth() + " getHeight " + this.mSvCamera.getHeight());
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        L.info(TAG, "CameraPreview surfaceChanged:" + i2 + "," + i3);
        this.mPreviewWidth = i2;
        this.mPreviewHeight = i3;
        ArkUtils.send(new CameraInterface.OnUpdatePreviewSize(i2, i3));
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        L.info(TAG, "CameraPreview surfaceDestroyed");
        pausePreview();
        ArkUtils.send(new CameraInterface.onSurfaceDestroyed());
        L.info(TAG, "CameraPreview surface == null");
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        L.info(TAG, "CameraPreview onBackPressed");
        if (SdkProperties.isLiving.get().booleanValue()) {
            ArkUtils.send(new CommonEvent.onBackPress());
        } else {
            super.onBackPressed();
        }
    }

    @IASlot(executorID = 1)
    public void onFinishPreview(CameraCallback.onFinishPreview onfinishpreview) {
        L.info(TAG, "CameraPreview onPreviewStop:" + this.mIsPausePreview);
        if (this.mIsPausePreview || isDestroyed()) {
            return;
        }
        finish();
    }

    @IASlot(mark = {ArkProperties.MarkNetworkAvailable})
    public void onNetworkChange(PropertySet<Boolean> propertySet) {
        L.info(TAG, "onNetworkChange: " + propertySet.oldValue + " &&& " + propertySet.newValue);
        if (propertySet.oldValue.booleanValue() && !propertySet.newValue.booleanValue()) {
            this.mIsNetBreak = true;
        } else {
            this.mIsNetBreak = false;
        }
    }
}
