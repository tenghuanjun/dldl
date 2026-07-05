package com.huya.mtp.utils.gl.texture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.alibaba.fastjson.asm.Opcodes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class KGLDensityBitmap {
    private static final Bitmap.Config FORMAT = Bitmap.Config.ARGB_8888;
    private static final int PIXEL_SIZE = 4;
    private static final String TAG = "KGLDensityBitmap";
    private ByteBuffer mBuffer;
    private int mHeight;
    private int mWidth;

    public int getGLBufferType() {
        return 5121;
    }

    public int getGLFormat() {
        return 6408;
    }

    public int getGLInternalFormat() {
        return 6408;
    }

    public enum DPI {
        LDPI(120),
        MDPI(Opcodes.IF_ICMPNE),
        HDPI(240),
        XHDPI(320);

        private int mDpi;

        DPI(int i) {
            this.mDpi = i;
        }
    }

    public static KGLDensityBitmap decode(Context context, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = FORMAT;
        return fromBitmap(context, i, options);
    }

    public static KGLDensityBitmap decode(Context context, int i, DPI dpi) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = FORMAT;
        options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        options.inScreenDensity = options.inTargetDensity;
        options.inDensity = dpi.mDpi;
        return fromBitmap(context, i, options);
    }

    private static KGLDensityBitmap fromBitmap(Context context, int i, BitmapFactory.Options options) {
        try {
            return fromBitmap(BitmapFactory.decodeResource(context.getResources(), i, options));
        } catch (Exception e) {
            Log.e(TAG, "to kgl density bitmap error " + e.toString());
            return null;
        }
    }

    public static KGLDensityBitmap fromBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            Log.e(TAG, "decode bitmap null ");
            return null;
        }
        if (bitmap.isRecycled()) {
            Log.e(TAG, "bitmap recycled");
            return null;
        }
        KGLDensityBitmap kGLDensityBitmap = new KGLDensityBitmap(bitmap);
        bitmap.recycle();
        return kGLDensityBitmap;
    }

    public ByteBuffer getBuffer() {
        this.mBuffer.position(0);
        return this.mBuffer;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    private KGLDensityBitmap(Bitmap bitmap) {
        this.mWidth = bitmap.getWidth();
        int height = bitmap.getHeight();
        this.mHeight = height;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(this.mWidth * height * 4);
        this.mBuffer = byteBufferAllocateDirect;
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        bitmap.copyPixelsToBuffer(this.mBuffer);
        this.mBuffer.position(0);
    }
}
