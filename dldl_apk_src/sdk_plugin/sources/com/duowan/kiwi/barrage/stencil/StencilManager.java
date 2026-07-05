package com.duowan.kiwi.barrage.stencil;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.duowan.ark.bind.DependencyProperty;
import com.duowan.ark.util.ThreadUtils;
import com.duowan.kiwi.barrage.config.BarrageContext;
import com.duowan.kiwi.barrage.config.BarrageLog;
import java.nio.ByteBuffer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class StencilManager {
    public static final int DRAW_AREA_HEIGHT = 324;
    public static final int DRAW_AREA_WIDTH = 540;
    public static final int MAX_STENCIL_DRAW_TIMES_PER_SECOND = 12;
    private static final int MSG_CLEAR_RECT_STENCIL = 5;
    private static final int MSG_CLOSE_STENCIL = 1;
    private static final int MSG_DRAW_RECT_STENCIL = 4;
    private static final int MSG_DRAW_STENCIL = 0;
    private static final int MSG_NOT_RECEIVE_STENCIL_DELAY = 2;
    public static final int STENCIL_DRAW_INTENAL_TIME = 83;
    private static final String TAG = StencilManager.class.getSimpleName();
    private static final StencilManager ourInstance = new StencilManager();
    private Handler mHandler;
    private int mScreenHeight;
    private int mScreenWidth;
    private StencilPainter mStencilPainter;
    private int mVideoHeight;
    private int mVideoWidth;
    private long mLastStencilDrawTime = 0;
    private Queue<ByteBuffer> mEmptyBuffers = new ConcurrentLinkedQueue();
    private Queue<ByteBuffer> mFullBuffers = new ConcurrentLinkedQueue();
    private ByteBuffer mRectByteBuffer = null;
    private Object mBufferLock = new Object();
    private volatile DependencyProperty<Boolean> mHasData = new DependencyProperty<>(false);
    private byte[] mNewestData = null;
    private byte[] mSpecifiedData = null;
    private int mOriginalPointX = 0;
    private int mOriginalPointY = 0;
    private boolean mReceiveFlag = true;

    public static StencilManager getInstance() {
        return ourInstance;
    }

    private StencilManager() {
        WindowManager windowManager = (WindowManager) BarrageContext.gContext.getSystemService("window");
        if (windowManager == null) {
            return;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager.getDefaultDisplay() == null) {
            return;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels > displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels;
        this.mScreenWidth = i;
        this.mVideoWidth = i;
        int i2 = displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels;
        this.mScreenHeight = i2;
        this.mVideoHeight = i2;
        this.mStencilPainter = new StencilPainter(DRAW_AREA_WIDTH, DRAW_AREA_HEIGHT);
    }

    public void reset() {
        BarrageLog.info(TAG, "reset data");
        ByteBuffer byteBufferPoll = this.mFullBuffers.poll();
        while (byteBufferPoll != null) {
            this.mEmptyBuffers.offer(byteBufferPoll);
            byteBufferPoll = this.mFullBuffers.poll();
        }
    }

    public void openReceiveStencil() {
        this.mReceiveFlag = true;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(2);
        }
    }

    public void closeStencil() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(2, 1000L);
        }
    }

    public void closeStenciDirectly() {
        this.mHasData.set(false);
    }

    public void setData(byte[] bArr) {
        if (bArr == null && hasData()) {
            reset();
        }
        if (bArr != null) {
            this.mHasData.set(true);
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.removeMessages(1);
            }
        } else if (this.mHandler != null && hasData()) {
            this.mHandler.sendEmptyMessageDelayed(1, 1000L);
        }
        this.mNewestData = bArr;
    }

    public void setData(byte[] bArr, int i, int i2, int i3, int i4) {
        if (this.mReceiveFlag) {
            setData(bArr);
            this.mOriginalPointX = i;
            this.mOriginalPointY = i2;
            this.mVideoWidth = i3;
            this.mVideoHeight = i4;
        }
    }

    public boolean hasData() {
        return this.mHasData.get().booleanValue();
    }

    public DependencyProperty.Entity<Boolean> getDataFlagEntity() {
        return this.mHasData.getEntity();
    }

    public ByteBuffer getStencilData() {
        return this.mFullBuffers.poll();
    }

    public void recycleByteBuffer(ByteBuffer byteBuffer) {
        this.mEmptyBuffers.offer(byteBuffer);
    }

    public int getScreenWidth() {
        return this.mScreenWidth;
    }

    public int getScreenHeight() {
        return this.mScreenHeight;
    }

    public void activateStencilDraw() {
        byte[] bArr;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.mLastStencilDrawTime >= 83 && (bArr = this.mNewestData) != null) {
            this.mSpecifiedData = bArr;
            this.mNewestData = null;
            if (this.mHandler == null) {
                initHandler();
            }
            this.mHandler.sendEmptyMessage(0);
            this.mLastStencilDrawTime = jCurrentTimeMillis;
        }
    }

    private void initHandler() {
        this.mHandler = ThreadUtils.newThreadHandler("BarrageStencil", new Handler.Callback() { // from class: com.duowan.kiwi.barrage.stencil.StencilManager.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    byte[] bArr = null;
                    if (StencilManager.this.mSpecifiedData != null) {
                        byte[] bArr2 = StencilManager.this.mSpecifiedData;
                        StencilManager.this.mSpecifiedData = null;
                        bArr = bArr2;
                    }
                    if (bArr != null) {
                        StencilManager.this.parseData(bArr);
                    }
                } else if (i == 1) {
                    BarrageLog.debug(StencilManager.TAG, "closed stencil");
                    StencilManager.this.mHasData.set(false);
                } else if (i == 2) {
                    StencilManager.this.mHasData.set(false);
                    StencilManager.this.mReceiveFlag = false;
                }
                return false;
            }
        });
        this.mEmptyBuffers.offer(ByteBuffer.allocateDirect(699840));
        this.mEmptyBuffers.offer(ByteBuffer.allocateDirect(699840));
    }

    public synchronized void setRect(Rect rect) {
        this.mStencilPainter.setRect(rect);
        if (rect == null) {
            if (this.mRectByteBuffer != null) {
                this.mRectByteBuffer.clear();
            }
        } else {
            drawRectStencil();
        }
    }

    public boolean hasRectStencil() {
        return this.mStencilPainter.hasRectStencil();
    }

    private synchronized void drawRectStencil() {
        Bitmap bitmapDrawRectStencil = this.mStencilPainter.drawRectStencil();
        if (bitmapDrawRectStencil == null) {
            return;
        }
        if (this.mRectByteBuffer == null) {
            this.mRectByteBuffer = ByteBuffer.allocateDirect(699840);
        }
        this.mRectByteBuffer.clear();
        bitmapDrawRectStencil.copyPixelsToBuffer(this.mRectByteBuffer);
        this.mRectByteBuffer.position(0);
    }

    public synchronized ByteBuffer getRectByteBuffer() {
        return this.mRectByteBuffer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseData(byte[] bArr) {
        ByteBuffer byteBufferPoll = this.mEmptyBuffers.poll();
        if (byteBufferPoll == null) {
            return;
        }
        Bitmap bitmapDrawStencil = this.mStencilPainter.drawStencil(bArr);
        if (bitmapDrawStencil == null) {
            this.mEmptyBuffers.offer(byteBufferPoll);
            return;
        }
        byteBufferPoll.clear();
        bitmapDrawStencil.copyPixelsToBuffer(byteBufferPoll);
        byteBufferPoll.position(0);
        this.mFullBuffers.offer(byteBufferPoll);
    }

    public int getOriginalPointX() {
        return this.mOriginalPointX;
    }

    public int getOriginalPointY() {
        return this.mOriginalPointY;
    }

    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public int getVideoHeight() {
        return this.mVideoHeight;
    }
}
