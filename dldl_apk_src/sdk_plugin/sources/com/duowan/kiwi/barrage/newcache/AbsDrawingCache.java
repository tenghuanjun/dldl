package com.duowan.kiwi.barrage.newcache;

import android.graphics.Bitmap;
import android.os.Build;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class AbsDrawingCache<CONTENT> {
    private OnFreeCallback mCallback;
    private final CONTENT mContent;
    private final int mHeight;
    private AtomicInteger mReferenceCount = new AtomicInteger(0);
    private final int mSize;
    private final int mWidth;

    public interface OnFreeCallback {
        void onFree();
    }

    abstract CONTENT createDrawingContent(Bitmap bitmap);

    public abstract boolean isHoldingBitmap();

    AbsDrawingCache(Bitmap bitmap) {
        this.mContent = createDrawingContent(bitmap);
        this.mWidth = bitmap.getWidth();
        this.mHeight = bitmap.getHeight();
        this.mSize = getBitmapSize(bitmap);
    }

    public CONTENT getContent() {
        return this.mContent;
    }

    public int getReferenceCount() {
        return this.mReferenceCount.get();
    }

    public void increaseReferenceCount() {
        this.mReferenceCount.incrementAndGet();
    }

    public void decreaseReferenceCount() {
        OnFreeCallback onFreeCallback;
        if (this.mReferenceCount.decrementAndGet() != 0 || (onFreeCallback = this.mCallback) == null) {
            return;
        }
        onFreeCallback.onFree();
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getByteCount() {
        return this.mSize;
    }

    public static class ViewDrawingCache extends AbsDrawingCache<Bitmap> {
        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.duowan.kiwi.barrage.newcache.AbsDrawingCache
        public Bitmap createDrawingContent(Bitmap bitmap) {
            return bitmap;
        }

        @Override // com.duowan.kiwi.barrage.newcache.AbsDrawingCache
        public boolean isHoldingBitmap() {
            return true;
        }

        public ViewDrawingCache(Bitmap bitmap) {
            super(bitmap);
        }
    }

    public static class GLDrawingCache extends AbsDrawingCache<ByteBuffer> {
        @Override // com.duowan.kiwi.barrage.newcache.AbsDrawingCache
        public boolean isHoldingBitmap() {
            return false;
        }

        public GLDrawingCache(Bitmap bitmap) {
            super(bitmap);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.duowan.kiwi.barrage.newcache.AbsDrawingCache
        public ByteBuffer createDrawingContent(Bitmap bitmap) {
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(getBitmapSize(bitmap));
            byteBufferAllocateDirect.clear();
            bitmap.copyPixelsToBuffer(byteBufferAllocateDirect);
            byteBufferAllocateDirect.position(0);
            return byteBufferAllocateDirect;
        }
    }

    public void setCallback(OnFreeCallback onFreeCallback) {
        this.mCallback = onFreeCallback;
    }

    static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT < 19) {
            return bitmap.getByteCount();
        }
        return bitmap.getAllocationByteCount();
    }
}
