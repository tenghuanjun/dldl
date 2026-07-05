package com.aliyun.aliyunface.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.aliyun.aliyunface.camera.CameraData;
import com.aliyun.aliyunface.camera.CameraSurfaceView;
import com.aliyun.aliyunface.camera.ICameraCallback;
import com.aliyun.aliyunface.camera.ICameraInterface;
import com.aliyun.aliyunface.camera.ICameraTakePicture;
import com.aliyun.aliyunface.ui.overlay.CommAlertOverlay;
import com.aliyun.aliyunface.ui.widget.RectMaskView;
import com.aliyun.aliyunface.utils.MiscUtil;
import com.aliyun.aliyunocr.R;
import com.jiguang.h5.PermissionUtils;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class OcrTakePhotoActivity extends Activity implements ICameraCallback {
    private CameraSurfaceView mCameraSurfaceView = null;
    private FrameLayout screenMainFrame = null;
    private Bitmap takenPicture = null;
    private Bitmap roiPicture = null;
    private double cameraPreviewWidth = 0.0d;
    private double cameraPreviewHeight = 0.0d;
    private boolean isFlashEnabled = false;
    private int UI_MSG_FINISH_TAKE_PHOTO = 1000;
    private Handler uiHandler = new Handler(new Handler.Callback() { // from class: com.aliyun.aliyunface.ui.OcrTakePhotoActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message != null && OcrTakePhotoActivity.this.UI_MSG_FINISH_TAKE_PHOTO == message.what) {
                OcrTakePhotoActivity.this.finish();
            }
            return false;
        }
    });

    @Override // com.aliyun.aliyunface.camera.ICameraCallback
    public void onError(int i) {
    }

    @Override // com.aliyun.aliyunface.camera.ICameraCallback
    public void onPreviewFrame(CameraData cameraData) {
    }

    @Override // com.aliyun.aliyunface.camera.ICameraCallback
    public void onSurfaceCreated() {
    }

    @Override // com.aliyun.aliyunface.camera.ICameraCallback
    public void onSurfaceDestroyed() {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ocr_take_photo);
        boolean z = false;
        if (Build.VERSION.SDK_INT < 23 || checkSelfPermission(PermissionUtils.PERMISSION_CAMERA) == 0) {
            z = true;
        } else {
            CommAlertOverlay commAlertOverlay = (CommAlertOverlay) findViewById(R.id.ocr_exit_alert_overlay);
            commAlertOverlay.setButtonType(false);
            commAlertOverlay.setTitleText(getString(R.string.ocr_camera_permission_warm_tip), false);
            commAlertOverlay.setMessageText(getString(R.string.ocr_camera_permission_not_granted), false);
            commAlertOverlay.setConfirmText(getString(R.string.message_box_btn_retry_exit), false);
            commAlertOverlay.setVisibility(0);
            commAlertOverlay.setCommAlertOverlayListener(new CommAlertOverlay.CommAlertOverlayListener() { // from class: com.aliyun.aliyunface.ui.OcrTakePhotoActivity.2
                @Override // com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.CommAlertOverlayListener
                public void onCancel() {
                }

                @Override // com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.CommAlertOverlayListener
                public void onConfirm() {
                    OcrTakePhotoActivity.this.uiHandler.sendEmptyMessage(OcrTakePhotoActivity.this.UI_MSG_FINISH_TAKE_PHOTO);
                }
            });
        }
        if (z) {
            init();
        }
    }

    private void init() {
        CameraSurfaceView cameraSurfaceView = (CameraSurfaceView) findViewById(R.id.ocr_take_photo_surface_view);
        this.mCameraSurfaceView = cameraSurfaceView;
        cameraSurfaceView.init(this, false, false, null);
        this.mCameraSurfaceView.setCameraCallback(this);
        final ImageView imageView = (ImageView) findViewById(R.id.ocr_do_take_picture);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.OcrTakePhotoActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    imageView.setEnabled(false);
                    OcrTakePhotoActivity.this.onClickTakePhoto();
                }
            });
        }
        final ImageView imageView2 = (ImageView) findViewById(R.id.ocr_take_photo_confirm);
        if (imageView2 != null) {
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.OcrTakePhotoActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    imageView2.setEnabled(false);
                    OcrTakePhotoActivity.this.onClickConfirmPhoto();
                }
            });
        }
        final ImageView imageView3 = (ImageView) findViewById(R.id.ocr_take_photo_retry);
        if (imageView3 != null) {
            imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.OcrTakePhotoActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    imageView3.setEnabled(false);
                    OcrTakePhotoActivity.this.onClickRetry();
                }
            });
        }
        View viewFindViewById = findViewById(R.id.ocr_take_photo_close);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.OcrTakePhotoActivity.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OcrTakePhotoActivity.this.setResult(-1, null);
                    OcrTakePhotoActivity.this.finish();
                }
            });
        }
        View viewFindViewById2 = findViewById(R.id.ocr_take_photo_shark);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.aliyun.aliyunface.ui.OcrTakePhotoActivity.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OcrTakePhotoActivity.this.isFlashEnabled = !r2.isFlashEnabled;
                    OcrTakePhotoActivity.this.mCameraSurfaceView.enableTakePhotoFlash(OcrTakePhotoActivity.this.isFlashEnabled);
                    ImageView imageView4 = (ImageView) OcrTakePhotoActivity.this.findViewById(R.id.ocr_close_shark_img);
                    if (imageView4 != null) {
                        if (OcrTakePhotoActivity.this.isFlashEnabled) {
                            imageView4.setImageResource(R.mipmap.ocr_open_shark);
                        } else {
                            imageView4.setImageResource(R.mipmap.ocr_close_shark);
                        }
                    }
                }
            });
        }
        Intent intent = getIntent();
        if (intent == null || !intent.hasExtra("takePhotoFront")) {
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("takePhotoFront", false);
        TextView textView = (TextView) findViewById(R.id.ocr_take_photo_rect_frame_tips);
        if (textView != null) {
            if (booleanExtra) {
                textView.setText(R.string.ocr_take_photo_front_tips);
            } else {
                textView.setText(R.string.ocr_take_photo_back_tips);
            }
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.uiHandler.post(new Runnable() { // from class: com.aliyun.aliyunface.ui.OcrTakePhotoActivity.8
            @Override // java.lang.Runnable
            public void run() {
                if (Build.VERSION.SDK_INT < 23 || OcrTakePhotoActivity.this.checkSelfPermission(PermissionUtils.PERMISSION_CAMERA) == 0) {
                    return;
                }
                CommAlertOverlay commAlertOverlay = (CommAlertOverlay) OcrTakePhotoActivity.this.findViewById(R.id.ocr_exit_alert_overlay);
                commAlertOverlay.setButtonType(false);
                commAlertOverlay.setTitleText(OcrTakePhotoActivity.this.getString(R.string.ocr_camera_permission_warm_tip), false);
                commAlertOverlay.setMessageText(OcrTakePhotoActivity.this.getString(R.string.ocr_camera_permission_not_granted), false);
                commAlertOverlay.setConfirmText(OcrTakePhotoActivity.this.getString(R.string.message_box_btn_retry_exit), false);
                commAlertOverlay.setVisibility(0);
                commAlertOverlay.setCommAlertOverlayListener(new CommAlertOverlay.CommAlertOverlayListener() { // from class: com.aliyun.aliyunface.ui.OcrTakePhotoActivity.8.1
                    @Override // com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.CommAlertOverlayListener
                    public void onCancel() {
                    }

                    @Override // com.aliyun.aliyunface.ui.overlay.CommAlertOverlay.CommAlertOverlayListener
                    public void onConfirm() {
                        OcrTakePhotoActivity.this.uiHandler.sendEmptyMessage(OcrTakePhotoActivity.this.UI_MSG_FINISH_TAKE_PHOTO);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickRetry() {
        showTakenPicure(false);
        enableTakePhotoButtons(true);
    }

    private void enableTakePhotoButtons(boolean z) {
        ImageView imageView = (ImageView) findViewById(R.id.ocr_do_take_picture);
        if (imageView != null) {
            imageView.setEnabled(z);
        }
        ImageView imageView2 = (ImageView) findViewById(R.id.ocr_take_photo_retry);
        if (imageView2 != null) {
            imageView2.setEnabled(z);
        }
        ImageView imageView3 = (ImageView) findViewById(R.id.ocr_take_photo_confirm);
        if (imageView3 != null) {
            imageView3.setEnabled(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickConfirmPhoto() {
        Intent intent = new Intent();
        intent.putExtra("roiPicture", MiscUtil.bitmap2Bytes(this.roiPicture));
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cropROIImage() {
        RectMaskView rectMaskView = (RectMaskView) findViewById(R.id.ocr_take_photo_rect_mask);
        int width = rectMaskView.getWidth();
        int height = rectMaskView.getHeight();
        int rectLeft = (int) rectMaskView.getRectLeft();
        int rectTop = (int) rectMaskView.getRectTop();
        int rectWidth = (int) rectMaskView.getRectWidth();
        int rectHeigth = (int) rectMaskView.getRectHeigth();
        RectF rectF = new RectF();
        float f = width;
        rectF.left = rectLeft / f;
        float f2 = height;
        rectF.top = rectTop / f2;
        rectF.right = rectF.left + (rectWidth / f);
        rectF.bottom = rectF.top + (rectHeigth / f2);
        this.roiPicture = MiscUtil.scaleBitmap(MiscUtil.cropBitmap(this.takenPicture, rectF), 800);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTakenPicure(boolean z) {
        ImageView imageView = (ImageView) findViewById(R.id.ocr_do_take_picture);
        View viewFindViewById = findViewById(R.id.ocr_take_photo_button_retry_confirm);
        ImageView imageView2 = (ImageView) findViewById(R.id.ocr_photo_rect);
        RectMaskView rectMaskView = (RectMaskView) findViewById(R.id.ocr_take_photo_rect_mask);
        ImageView imageView3 = (ImageView) findViewById(R.id.ocr_taken_picture_img);
        if (!z) {
            if (imageView3 != null) {
                imageView3.setVisibility(4);
            }
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (viewFindViewById != null) {
                viewFindViewById.setVisibility(4);
            }
            if (imageView2 != null) {
                imageView2.setVisibility(0);
            }
            if (rectMaskView != null) {
                rectMaskView.setAlpha(0.8f);
                return;
            }
            return;
        }
        if (imageView3 != null) {
            imageView3.setImageBitmap(this.takenPicture);
            imageView3.setVisibility(0);
        }
        if (imageView != null) {
            imageView.setVisibility(4);
        }
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(0);
        }
        if (imageView2 != null) {
            imageView2.setVisibility(4);
        }
        if (rectMaskView != null) {
            rectMaskView.setAlpha(1.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickTakePhoto() {
        ICameraInterface cameraInterface;
        CameraSurfaceView cameraSurfaceView = this.mCameraSurfaceView;
        if (cameraSurfaceView == null || (cameraInterface = cameraSurfaceView.getCameraInterface()) == null) {
            return;
        }
        cameraInterface.takePhoto(new ICameraTakePicture() { // from class: com.aliyun.aliyunface.ui.OcrTakePhotoActivity.9
            @Override // com.aliyun.aliyunface.camera.ICameraTakePicture
            public void onTakenPicture(Bitmap bitmap) {
                OcrTakePhotoActivity.this.takenPicture = bitmap;
                OcrTakePhotoActivity.this.showTakenPicure(true);
                OcrTakePhotoActivity.this.cropROIImage();
            }
        });
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            initUILayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRectMaskUI() {
        int height;
        int width;
        int i;
        int height2;
        View viewFindViewById = findViewById(R.id.take_photo_screen_frame);
        int top = 0;
        if (viewFindViewById != null) {
            width = viewFindViewById.getWidth();
            height = viewFindViewById.getHeight();
            double d = this.cameraPreviewHeight / this.cameraPreviewWidth;
            double d2 = height;
            double d3 = width;
            if (d < d2 / d3) {
                width = (int) (d2 / d);
            } else {
                height = (int) (d3 * d);
            }
        } else {
            height = 0;
            width = 0;
        }
        ViewGroup.LayoutParams layoutParams = this.mCameraSurfaceView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        this.mCameraSurfaceView.setLayoutParams(layoutParams);
        adjustPictureViews(width, height);
        ImageView imageView = (ImageView) findViewById(R.id.ocr_photo_rect);
        if (imageView != null) {
            int width2 = imageView.getWidth();
            height2 = imageView.getHeight();
            top = imageView.getTop();
            i = width2;
        } else {
            i = 0;
            height2 = 0;
        }
        int dimension = (int) getResources().getDimension(R.dimen.comm_margin_size_30);
        int dimension2 = (int) (top + getResources().getDimension(R.dimen.comm_margin_size_30));
        RectMaskView rectMaskView = (RectMaskView) findViewById(R.id.ocr_take_photo_rect_mask);
        if (rectMaskView != null) {
            rectMaskView.setRectTop(dimension2);
            rectMaskView.setRectLeft(dimension);
            rectMaskView.setRectWidth(i);
            rectMaskView.setRectHeight(height2);
            rectMaskView.setRectRoundCx(1);
            rectMaskView.invalidate();
        }
    }

    private void initUILayout() {
        ImageView imageView = (ImageView) findViewById(R.id.ocr_photo_rect);
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = (int) (imageView.getWidth() * 0.6330275f);
            imageView.setLayoutParams(layoutParams);
            imageView.post(new Runnable() { // from class: com.aliyun.aliyunface.ui.OcrTakePhotoActivity.10
                @Override // java.lang.Runnable
                public void run() {
                    OcrTakePhotoActivity.this.initRectMaskUI();
                }
            });
        }
    }

    @Override // com.aliyun.aliyunface.camera.ICameraCallback
    public void onSurfaceChanged(double d, double d2) {
        this.cameraPreviewWidth = d;
        this.cameraPreviewHeight = d2;
        CameraSurfaceView cameraSurfaceView = this.mCameraSurfaceView;
        if (cameraSurfaceView != null) {
            if (d < d2) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) cameraSurfaceView.getLayoutParams();
                layoutParams.width = this.mCameraSurfaceView.getWidth();
                layoutParams.height = (int) ((((double) layoutParams.width) / (d * 1.0d)) * d2);
                this.mCameraSurfaceView.setLayoutParams(layoutParams);
                this.mCameraSurfaceView.setBackgroundColor(0);
                return;
            }
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) cameraSurfaceView.getLayoutParams();
            layoutParams2.height = this.mCameraSurfaceView.getHeight();
            layoutParams2.width = (int) ((((double) layoutParams2.height) / (d2 * 1.0d)) * d);
            this.mCameraSurfaceView.setLayoutParams(layoutParams2);
            this.mCameraSurfaceView.setBackgroundColor(0);
        }
    }

    private void adjustPictureViews(int i, int i2) {
        ImageView imageView = (ImageView) findViewById(R.id.ocr_taken_picture_img);
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = i;
            layoutParams.height = i2;
            imageView.setLayoutParams(layoutParams);
        }
        RectMaskView rectMaskView = (RectMaskView) findViewById(R.id.ocr_take_photo_rect_mask);
        if (rectMaskView != null) {
            ViewGroup.LayoutParams layoutParams2 = rectMaskView.getLayoutParams();
            layoutParams2.width = i;
            layoutParams2.height = i2;
            rectMaskView.setLayoutParams(layoutParams2);
        }
    }
}
