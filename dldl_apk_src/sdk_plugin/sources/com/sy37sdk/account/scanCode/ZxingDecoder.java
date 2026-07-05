package com.sy37sdk.account.scanCode;

import android.graphics.Rect;
import android.util.Size;
import android.view.TextureView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.account.scanCode.ICameraOperation;
import java.util.EnumMap;
import java.util.EnumSet;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ZxingDecoder implements ICameraOperation.FrameCallback {
    private boolean mPause = false;
    private final ResultCallback mResultCallback;
    private final TextureView mTextureView;

    public interface ResultCallback {
        void onResult(Result result);
    }

    public ZxingDecoder(TextureView textureView, ResultCallback resultCallback) {
        this.mTextureView = textureView;
        this.mResultCallback = resultCallback;
    }

    @Override // com.sy37sdk.account.scanCode.ICameraOperation.FrameCallback
    public boolean onFrameAvailable(byte[] bArr, int i, int i2, Size size) {
        Rect framingRectInPreview = getFramingRectInPreview(this.mTextureView.getWidth(), this.mTextureView.getHeight(), size);
        MultiFormatReader multiFormatReader = getMultiFormatReader();
        if (framingRectInPreview.left + framingRectInPreview.width() > i || framingRectInPreview.top + framingRectInPreview.height() > i2) {
            LogUtil.w("【ScanCode】帧数据异常 left :" + framingRectInPreview.left + " ，width :" + framingRectInPreview.width() + " top :" + framingRectInPreview.top + " ，height :" + framingRectInPreview.height());
            StringBuilder sb = new StringBuilder();
            sb.append("【ScanCode】帧数据异常 imageWidth :");
            sb.append(i);
            sb.append(" ，imageHeight :");
            sb.append(i2);
            LogUtil.w(sb.toString());
            framingRectInPreview.right = i - framingRectInPreview.left;
            framingRectInPreview.bottom = i2 - framingRectInPreview.top;
        }
        try {
            Result resultDecodeWithState = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(new PlanarYUVLuminanceSource(bArr, i, i2, framingRectInPreview.left, framingRectInPreview.top, framingRectInPreview.width(), framingRectInPreview.height(), false))));
            if (this.mResultCallback != null && !this.mPause) {
                LogUtil.d("【ScanCode】解码成功 " + resultDecodeWithState);
                this.mResultCallback.onResult(resultDecodeWithState);
                multiFormatReader.reset();
                return false;
            }
        } catch (ReaderException unused) {
        } catch (Throwable th) {
            multiFormatReader.reset();
            throw th;
        }
        multiFormatReader.reset();
        return true;
    }

    private MultiFormatReader getMultiFormatReader() {
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        EnumSet enumSetNoneOf = EnumSet.noneOf(BarcodeFormat.class);
        enumSetNoneOf.addAll(DecodeFormatManager.ONE_D_FORMATS);
        enumSetNoneOf.addAll(DecodeFormatManager.QR_CODE_FORMATS);
        enumSetNoneOf.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        enumMap.put(DecodeHintType.POSSIBLE_FORMATS, enumSetNoneOf);
        enumMap.put(DecodeHintType.CHARACTER_SET, "UTF8");
        multiFormatReader.setHints(enumMap);
        return multiFormatReader;
    }

    private Rect getFramingRectInPreview(int i, int i2, Size size) {
        Rect rect = new Rect(new Rect(0, 0, ((i * 3) / 4) + 0, ((i2 * 3) / 4) + 0));
        rect.left = (rect.left * size.getWidth()) / i;
        rect.right = (rect.right * size.getWidth()) / i;
        rect.top = (rect.top * size.getHeight()) / i2;
        rect.bottom = (rect.bottom * size.getHeight()) / i2;
        return rect;
    }

    public void resume() {
        this.mPause = false;
    }

    public void pause() {
        this.mPause = true;
    }
}
