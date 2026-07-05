package com.google.zxing.decoding;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.R;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.camera.CameraManager;
import com.google.zxing.camera.PlanarYUVLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.util.Hashtable;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class DecodeHandler extends Handler {
    private static final String TAG = "DecodeHandler";
    private final CaptureActivity activity;
    private final MultiFormatReader multiFormatReader = new MultiFormatReader();

    DecodeHandler(CaptureActivity captureActivity, Hashtable<DecodeHintType, Object> hashtable) {
        this.multiFormatReader.setHints(hashtable);
        this.activity = captureActivity;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        if (i == R.id.decode) {
            decode((byte[]) message.obj, message.arg1, message.arg2);
        } else if (i == R.id.quit) {
            Looper.myLooper().quit();
        }
    }

    private void decode(byte[] bArr, int i, int i2) {
        Result resultDecodeWithState;
        long jCurrentTimeMillis = System.currentTimeMillis();
        byte[] bArr2 = new byte[bArr.length];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                bArr2[(((i4 * i2) + i2) - i3) - 1] = bArr[(i3 * i) + i4];
            }
        }
        PlanarYUVLuminanceSource planarYUVLuminanceSourceBuildLuminanceSource = CameraManager.get().buildLuminanceSource(bArr2, i2, i);
        try {
            resultDecodeWithState = this.multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSourceBuildLuminanceSource)));
            this.multiFormatReader.reset();
        } catch (ReaderException unused) {
            this.multiFormatReader.reset();
            resultDecodeWithState = null;
        } catch (Throwable th) {
            this.multiFormatReader.reset();
            throw th;
        }
        if (resultDecodeWithState != null) {
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            Log.d(TAG, "Found barcode (" + (jCurrentTimeMillis2 - jCurrentTimeMillis) + " ms):\n" + resultDecodeWithState.toString());
            Message messageObtain = Message.obtain(this.activity.getHandler(), R.id.decode_succeeded, resultDecodeWithState);
            Bundle bundle = new Bundle();
            bundle.putParcelable(DecodeThread.BARCODE_BITMAP, planarYUVLuminanceSourceBuildLuminanceSource.renderCroppedGreyscaleBitmap());
            messageObtain.setData(bundle);
            messageObtain.sendToTarget();
            return;
        }
        Message.obtain(this.activity.getHandler(), R.id.decode_failed).sendToTarget();
    }
}
