package com.duowan.auk.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.duowan.auk.util.L;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HeartPresentView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "HeartPresentView";
    private static Paint mPaint;
    private Runnable mDrawRunnable;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private int[] mIds;
    private Random mRandom;
    private int mUniqueId;
    private List<HeartView> mViewList;

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public HeartPresentView(Context context) {
        super(context);
        this.mDrawRunnable = new Runnable() { // from class: com.duowan.auk.ui.HeartPresentView.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    HeartPresentView.this.doDraw();
                    synchronized (HeartPresentView.this) {
                        if (HeartPresentView.this.mViewList.isEmpty()) {
                            L.info(HeartPresentView.TAG, "[mDrawRunnable]list is empty, break;");
                            return;
                        }
                    }
                }
            }
        };
        this.mViewList = new ArrayList();
        this.mIds = new int[0];
        this.mRandom = new Random();
        this.mUniqueId = -1;
        init();
    }

    public HeartPresentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDrawRunnable = new Runnable() { // from class: com.duowan.auk.ui.HeartPresentView.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    HeartPresentView.this.doDraw();
                    synchronized (HeartPresentView.this) {
                        if (HeartPresentView.this.mViewList.isEmpty()) {
                            L.info(HeartPresentView.TAG, "[mDrawRunnable]list is empty, break;");
                            return;
                        }
                    }
                }
            }
        };
        this.mViewList = new ArrayList();
        this.mIds = new int[0];
        this.mRandom = new Random();
        this.mUniqueId = -1;
        init();
    }

    public HeartPresentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDrawRunnable = new Runnable() { // from class: com.duowan.auk.ui.HeartPresentView.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    HeartPresentView.this.doDraw();
                    synchronized (HeartPresentView.this) {
                        if (HeartPresentView.this.mViewList.isEmpty()) {
                            L.info(HeartPresentView.TAG, "[mDrawRunnable]list is empty, break;");
                            return;
                        }
                    }
                }
            }
        };
        this.mViewList = new ArrayList();
        this.mIds = new int[0];
        this.mRandom = new Random();
        this.mUniqueId = -1;
        init();
    }

    static {
        Paint paint = new Paint();
        mPaint = paint;
        paint.setTextSize(40.0f);
    }

    private void init() {
        if (Build.VERSION.SDK_INT > 14) {
            setZOrderMediaOverlay(true);
        }
        getHolder().addCallback(this);
        getHolder().setFormat(-3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        HandlerThread handlerThread = new HandlerThread("HeartPrezViewThread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.mHandlerThread.getLooper());
        this.mHandler = handler;
        handler.post(this.mDrawRunnable);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mHandlerThread.quit();
        this.mHandler = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDraw() {
        Canvas canvasLockCanvas;
        SurfaceHolder holder = getHolder();
        try {
            canvasLockCanvas = holder.lockCanvas();
        } catch (Exception e) {
            e.printStackTrace();
            canvasLockCanvas = null;
        }
        if (canvasLockCanvas != null) {
            canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            doDrawHeartView(canvasLockCanvas);
            holder.unlockCanvasAndPost(canvasLockCanvas);
        }
    }

    public void setResourceId(int[] iArr) {
        this.mIds = iArr;
    }

    private Bitmap getRandomBmp() {
        int iNextInt;
        do {
            iNextInt = this.mRandom.nextInt(this.mIds.length);
        } while (iNextInt == this.mUniqueId);
        return getBitmap(iNextInt);
    }

    private Bitmap getBitmap(int i) {
        return BitmapFactory.decodeResource(getResources(), this.mIds[i]);
    }

    public void add() {
        add(new HeartView(getRandomBmp(), getWidth(), getHeight()));
    }

    public void addUnique() {
        if (this.mUniqueId == -1) {
            this.mUniqueId = this.mRandom.nextInt(this.mIds.length);
        }
        add(new HeartView(getBitmap(this.mUniqueId), getWidth(), getHeight()));
    }

    private synchronized void add(HeartView heartView) {
        this.mViewList.add(heartView);
        if (this.mViewList.size() == 1) {
            L.info(TAG, "[add]size == 1, post runnable");
            this.mHandler.post(this.mDrawRunnable);
        }
    }

    private void doDrawHeartView(Canvas canvas) {
        ArrayList arrayList;
        synchronized (this) {
            checkViewBounds(this.mViewList);
            arrayList = new ArrayList(this.mViewList);
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((HeartView) it.next()).draw(canvas, jUptimeMillis);
        }
    }

    private synchronized void checkViewBounds(List<HeartView> list) {
        int i = 0;
        while (i < list.size()) {
            HeartView heartView = list.get(i);
            if (heartView.isExceedBounds()) {
                list.remove(heartView);
                i--;
            }
            i++;
        }
    }
}
