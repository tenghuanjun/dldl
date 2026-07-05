package com.google.zxing.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.R;
import com.google.zxing.Result;
import com.google.zxing.camera.CameraManager;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.decoding.CaptureActivityHandler;
import com.google.zxing.decoding.InactivityTimer;
import com.google.zxing.decoding.RGBLuminanceSource;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.util.BitmapUtil;
import com.google.zxing.util.Constant;
import com.google.zxing.view.ViewfinderView;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class CaptureActivity extends Activity implements SurfaceHolder.Callback {
    private static final float BEEP_VOLUME = 0.1f;
    private static final int REQUEST_CODE_SCAN_GALLERY = 100;
    private static final long VIBRATE_DURATION = 200;
    private ImageButton back;
    private Button btnAlbum;
    private ImageButton btnFlash;
    private String characterSet;
    private Vector<BarcodeFormat> decodeFormats;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private InactivityTimer inactivityTimer;
    private ProgressDialog mProgress;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private Bitmap scanBitmap;
    private boolean vibrate;
    private ViewfinderView viewfinderView;
    private boolean isFlashOn = false;
    private View.OnClickListener albumOnClick = new View.OnClickListener() { // from class: com.google.zxing.activity.CaptureActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            CaptureActivity.this.startActivityForResult(intent, 100);
        }
    };
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() { // from class: com.google.zxing.activity.CaptureActivity.4
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };
    private View.OnClickListener flashListener = new View.OnClickListener() { // from class: com.google.zxing.activity.CaptureActivity.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                if (CameraManager.get().setFlashLight(!CaptureActivity.this.isFlashOn)) {
                    if (CaptureActivity.this.isFlashOn) {
                        CaptureActivity.this.btnFlash.setImageResource(R.drawable.flash_off);
                        CaptureActivity.this.isFlashOn = false;
                        return;
                    } else {
                        CaptureActivity.this.btnFlash.setImageResource(R.drawable.flash_on);
                        CaptureActivity.this.isFlashOn = true;
                        return;
                    }
                }
                Toast.makeText(CaptureActivity.this, R.string.note_no_flashlight, 0).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scanner);
        CameraManager.init(getApplication());
        this.viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_content);
        this.back = (ImageButton) findViewById(R.id.btn_back);
        this.back.setOnClickListener(new View.OnClickListener() { // from class: com.google.zxing.activity.CaptureActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CaptureActivity.this.finish();
            }
        });
        this.btnFlash = (ImageButton) findViewById(R.id.btn_flash);
        this.btnFlash.setOnClickListener(this.flashListener);
        this.btnAlbum = (Button) findViewById(R.id.btn_album);
        this.btnAlbum.setOnClickListener(this.albumOnClick);
        this.hasSurface = false;
        this.inactivityTimer = new InactivityTimer(this);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && i == 100) {
            handleAlbumPic(intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    private void handleAlbumPic(Intent intent) {
        final Uri data = intent.getData();
        this.mProgress = new ProgressDialog(this);
        this.mProgress.setMessage("正在扫描...");
        this.mProgress.setCancelable(false);
        this.mProgress.show();
        runOnUiThread(new Runnable() { // from class: com.google.zxing.activity.CaptureActivity.3
            @Override // java.lang.Runnable
            public void run() {
                Result resultScanningImage = CaptureActivity.this.scanningImage(data);
                CaptureActivity.this.mProgress.dismiss();
                if (resultScanningImage != null) {
                    Intent intent2 = new Intent();
                    Bundle extras = CaptureActivity.this.getIntent().getExtras();
                    if (extras == null) {
                        extras = new Bundle();
                    }
                    extras.putString(Constant.INTENT_EXTRA_KEY_QR_SCAN, resultScanningImage.getText());
                    intent2.putExtras(extras);
                    CaptureActivity.this.setResult(-1, intent2);
                    CaptureActivity.this.finish();
                    return;
                }
                Toast.makeText(CaptureActivity.this, R.string.note_identify_failed, 0).show();
            }
        });
    }

    public Result scanningImage(Uri uri) {
        if (uri == null) {
            return null;
        }
        Hashtable hashtable = new Hashtable();
        hashtable.put(DecodeHintType.CHARACTER_SET, "UTF8");
        this.scanBitmap = BitmapUtil.decodeUri(this, uri, TTAdConstant.SHOW_POLL_TIME_SPLASH_DEFAULT, TTAdConstant.SHOW_POLL_TIME_SPLASH_DEFAULT);
        try {
            return new QRCodeReader().decode(new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(this.scanBitmap))), hashtable);
        } catch (ChecksumException e) {
            e.printStackTrace();
            return null;
        } catch (FormatException e2) {
            e2.printStackTrace();
            return null;
        } catch (NotFoundException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        SurfaceHolder holder = ((SurfaceView) findViewById(R.id.scanner_view)).getHolder();
        if (this.hasSurface) {
            initCamera(holder);
        } else {
            holder.addCallback(this);
            holder.setType(3);
        }
        this.decodeFormats = null;
        this.characterSet = null;
        this.playBeep = true;
        if (((AudioManager) getSystemService("audio")).getRingerMode() != 2) {
            this.playBeep = false;
        }
        initBeepSound();
        this.vibrate = true;
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.quitSynchronously();
            this.handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.inactivityTimer.shutdown();
        super.onDestroy();
    }

    public void handleDecode(Result result, Bitmap bitmap) {
        this.inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String text = result.getText();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, R.string.note_scan_failed, 0).show();
        } else {
            Intent intent = new Intent();
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putString(Constant.INTENT_EXTRA_KEY_QR_SCAN, text);
            intent.putExtras(extras);
            setResult(-1, intent);
        }
        finish();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
            if (this.handler == null) {
                this.handler = new CaptureActivityHandler(this, this.decodeFormats, this.characterSet);
            }
        } catch (IOException unused) {
        } catch (RuntimeException unused2) {
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.hasSurface) {
            return;
        }
        this.hasSurface = true;
        initCamera(surfaceHolder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.hasSurface = false;
    }

    public ViewfinderView getViewfinderView() {
        return this.viewfinderView;
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void drawViewfinder() {
        this.viewfinderView.drawViewfinder();
    }

    private void initBeepSound() {
        if (this.playBeep && this.mediaPlayer == null) {
            setVolumeControlStream(3);
            this.mediaPlayer = new MediaPlayer();
            this.mediaPlayer.setAudioStreamType(3);
            this.mediaPlayer.setOnCompletionListener(this.beepListener);
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = getResources().openRawResourceFd(R.raw.beep);
            try {
                this.mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
                assetFileDescriptorOpenRawResourceFd.close();
                this.mediaPlayer.setVolume(0.1f, 0.1f);
                this.mediaPlayer.prepare();
            } catch (IOException unused) {
                this.mediaPlayer = null;
            }
        }
    }

    private void playBeepSoundAndVibrate() {
        MediaPlayer mediaPlayer;
        if (this.playBeep && (mediaPlayer = this.mediaPlayer) != null) {
            mediaPlayer.start();
        }
        if (this.vibrate) {
            ((Vibrator) getSystemService("vibrator")).vibrate(200L);
        }
    }
}
