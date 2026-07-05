package com.duowan.kiwi.barrage.render;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import com.duowan.ark.ArkValue;
import com.duowan.ark.util.glutils.tools.Camera;
import com.duowan.ark.util.glutils.utils.CatchError;
import com.duowan.kiwi.barrage.GunPowder;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.config.BarrageLog;
import com.duowan.kiwi.barrage.config.GLBarrageAdapter;
import com.duowan.kiwi.barrage.newcache.AbsDrawingCache;
import com.duowan.kiwi.barrage.render.AbsBarrageRender;
import com.duowan.kiwi.barrage.render.BaseGLBarrageRender;
import com.duowan.kiwi.barrage.render.draw.BulletBuilder;
import com.duowan.kiwi.barrage.render.shader.BarrageShader;
import com.duowan.kiwi.barrage.stencil.StencilManager;
import com.duowan.kiwi.barrage.trace.AbsTrace;
import com.duowan.kiwi.barrage.utils.GLCoordinate;
import com.duowan.kiwi.barrage.view.IGLBarrageView;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GLBarrageRender extends BaseGLBarrageRender implements GLSurfaceView.Renderer {
    private static final String TAG = "[Barrage]render";
    private IGLBarrageView mBarrageView;
    private float mBlue;
    private Camera mCamera;
    private int mDrawIndex;
    private int mLastIndex;
    private long mLastTime;
    private AtomicInteger mOrientation;
    private BaseGLBarrageRender.GLBulletTrace mRectStencilGLAnimation;
    private BarrageShader mShader;
    private SmoothDeltaTime mSmoothDelta;
    private BaseGLBarrageRender.GLBulletTrace mStencilGLAnimation;
    private boolean mSurfaceChanged;

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public long getCurrentTime() {
        return 0L;
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public boolean isStop() {
        return false;
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void notifyDispSizeChanged(int i, int i2) {
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void calculateBarrage(float f) {
        super.calculateBarrage(f);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void ceaseFire(boolean z, boolean z2) {
        super.ceaseFire(z, z2);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void cleanQueue(boolean z) {
        super.cleanQueue(z);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void clearAnimations(AbsBarrageRender.OnRemoveAnimMatcher onRemoveAnimMatcher) {
        super.clearAnimations(onRemoveAnimMatcher);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ void clearCanvas() {
        super.clearCanvas();
    }

    @Override // com.duowan.kiwi.barrage.render.BaseGLBarrageRender, com.duowan.kiwi.barrage.newcache.DrawingFactory.BuildMachine
    public /* bridge */ /* synthetic */ AbsDrawingCache createDrawingCache(Bitmap bitmap) {
        return super.createDrawingCache(bitmap);
    }

    @Override // com.duowan.kiwi.barrage.render.BaseGLBarrageRender
    public /* bridge */ /* synthetic */ BaseGLBarrageRender.GLBulletTrace createGLAnimation(Bitmap bitmap) {
        return super.createGLAnimation(bitmap);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // com.duowan.kiwi.barrage.render.BaseGLBarrageRender
    public /* bridge */ /* synthetic */ boolean drawFrames(BarrageShader barrageShader, Camera camera, float f) {
        return super.drawFrames(barrageShader, camera, f);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ float getAlpha() {
        return super.getAlpha();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ ArrayList getAnimations() {
        return super.getAnimations();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ int getBarrageType() {
        return super.getBarrageType();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ int getFixedLine() {
        return super.getFixedLine();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ int getLineSpace() {
        return super.getLineSpace();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ float getScale() {
        return super.getScale();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ BulletBuilder getShellBuilder() {
        return super.getShellBuilder();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ int getSpaceX() {
        return super.getSpaceX();
    }

    @Override // com.duowan.kiwi.barrage.render.BaseGLBarrageRender
    public /* bridge */ /* synthetic */ void initHolderGL() {
        super.initHolderGL();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ boolean isBarrageOn() {
        return super.isBarrageOn();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ boolean isBarrageRenderOn() {
        return super.isBarrageRenderOn();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ boolean isFixedQueue() {
        return super.isFixedQueue();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ void offer(GunPowder gunPowder, int i) {
        super.offer(gunPowder, i);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ void onBarrageSizeChanged(int i) {
        super.onBarrageSizeChanged(i);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void pollAnimationsEnd(ArrayList arrayList) {
        super.pollAnimationsEnd(arrayList);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void setAlpha(float f) {
        super.setAlpha(f);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ void setAutoIncrease(int i, boolean z) {
        super.setAutoIncrease(i, z);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ void setBarrageType(int i) {
        super.setBarrageType(i);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void setRect(int i, int i2, int i3, int i4) {
        super.setRect(i, i2, i3, i4);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    @Override // com.duowan.kiwi.barrage.render.BaseGLBarrageRender
    public /* bridge */ /* synthetic */ void stencilDraw(BarrageShader barrageShader, Camera camera, BaseGLBarrageRender.GLBulletTrace gLBulletTrace) {
        super.stencilDraw(barrageShader, camera, gLBulletTrace);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    public GLBarrageRender(IGLBarrageView iGLBarrageView, int i, boolean z, int i2, float f) {
        super(iGLBarrageView, i, z, i2, f);
        this.mBlue = 1.0f;
        this.mSurfaceChanged = false;
        this.mDrawIndex = 1;
        this.mLastTime = 0L;
        this.mLastIndex = 0;
        this.mBarrageView = iGLBarrageView;
        JSONObject jSONObjectData = ArkValue.gArkExtConfig.data();
        this.mBlue = (jSONObjectData == null || !jSONObjectData.has("GLBarrage_Blue")) ? 0.0f : this.mBlue;
        this.mOrientation = new AtomicInteger(i2);
        BarrageLog.info("[Barrage]render", "init mBarrageOn false, mOrientation 0");
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        BarrageLog.info("[Barrage]render", "render created");
        if (this.mShader != null) {
            float f = this.mBlue;
            GLES20.glClearColor(0.0f, 0.0f, f, f);
            GLES20.glClear(16640);
        }
        delete();
        initRender();
        resetStencil();
        resetRectStencil();
        StencilManager.getInstance().reset();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        BarrageLog.info("[Barrage]render", "render changed width %d height %d orientation %d barrage type %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.mOrientation.get()), Integer.valueOf(getBarrageType()));
        setRect(0, 0, i, i2);
        GLCoordinate.setWorldSize(i, i2);
        GLES20.glViewport(0, 0, i, i2);
        float f = (i * 1.0f) / i2;
        this.mCamera.sharpFocusing(-f, f);
        ceaseFire(true, false);
        setBarrageType(getBarrageType());
        this.mSurfaceChanged = true;
        resetSmooth();
        resetStencil();
        resetRectStencil();
        StencilManager.getInstance().reset();
    }

    private void printRefreshFPS() {
        if (BarrageConfig.isBarrageRefreshPrint()) {
            this.mDrawIndex++;
            if (this.mLastTime == 0 || System.currentTimeMillis() - this.mLastTime <= 1000) {
                if (this.mLastTime == 0) {
                    this.mLastTime = System.currentTimeMillis();
                    this.mLastIndex = this.mDrawIndex;
                    return;
                }
                return;
            }
            this.mLastTime = System.currentTimeMillis();
            this.mBarrageView.showToast("" + (this.mDrawIndex - this.mLastIndex));
            BarrageLog.info("[Barrage]render", "framefps: %d", Integer.valueOf(this.mDrawIndex - this.mLastIndex));
            this.mLastIndex = this.mDrawIndex;
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        printRefreshFPS();
        float f = this.mBlue;
        GLES20.glClearColor(0.0f, 0.0f, f, f);
        GLES20.glClear(16640);
        if (this.mBarrageView.isNeedClearEnable() && GLBarrageAdapter.needClear()) {
            BarrageLog.info("[Barrage]render", "needClear");
            GLBarrageAdapter.setHasClean();
            return;
        }
        if (!this.mSurfaceChanged && isBarrageRenderOn()) {
            boolean zDrawFrames = drawFrames(this.mShader, this.mCamera, this.mSmoothDelta.getSmoothDelta());
            if (this.mBarrageView.isStencilEnable() && BarrageConfig.getAntiBlockNowStatus() && StencilManager.getInstance().hasData()) {
                stencilDraw();
            } else {
                resetStencil();
            }
            if (StencilManager.getInstance().hasRectStencil()) {
                stencilRectDraw();
            } else {
                resetRectStencil();
            }
            if (zDrawFrames) {
                BarrageLog.info("[Barrage]render", "onDrawFrame switchRender false");
                this.mBarrageView.switchRender(false);
            }
            this.mSmoothDelta.calcSmoothDelta();
            this.mSmoothDelta.recordRenderingTime();
            CatchError.catchError("barrage render draw frame");
        }
        this.mSurfaceChanged = false;
    }

    private void resetRectStencil() {
        BaseGLBarrageRender.GLBulletTrace gLBulletTrace = this.mRectStencilGLAnimation;
        if (gLBulletTrace != null) {
            gLBulletTrace.recycle();
            this.mRectStencilGLAnimation = null;
        }
    }

    private void stencilRectDraw() {
        ByteBuffer rectByteBuffer;
        if (this.mRectStencilGLAnimation == null && (rectByteBuffer = StencilManager.getInstance().getRectByteBuffer()) != null) {
            this.mRectStencilGLAnimation = createRectStencilGLAnimation(rectByteBuffer);
        }
        BaseGLBarrageRender.GLBulletTrace gLBulletTrace = this.mRectStencilGLAnimation;
        if (gLBulletTrace != null) {
            stencilDraw(this.mShader, this.mCamera, gLBulletTrace);
        }
    }

    private BaseGLBarrageRender.GLBulletTrace createRectStencilGLAnimation(ByteBuffer byteBuffer) {
        int screenWidth = StencilManager.getInstance().getScreenWidth();
        int screenHeight = StencilManager.getInstance().getScreenHeight();
        BaseGLBarrageRender.GLBulletTrace gLBulletTrace = new BaseGLBarrageRender.GLBulletTrace(byteBuffer, StencilManager.DRAW_AREA_WIDTH, StencilManager.DRAW_AREA_HEIGHT);
        gLBulletTrace.setGLXY(0, 0);
        gLBulletTrace.setGLScaleXY(screenWidth / 540.0f, screenHeight / 324.0f);
        return gLBulletTrace;
    }

    private void resetStencil() {
        BaseGLBarrageRender.GLBulletTrace gLBulletTrace = this.mStencilGLAnimation;
        if (gLBulletTrace != null) {
            gLBulletTrace.recycle();
            this.mStencilGLAnimation = null;
        }
    }

    private void stencilDraw() {
        ByteBuffer stencilData = StencilManager.getInstance().getStencilData();
        if (stencilData != null) {
            BaseGLBarrageRender.GLBulletTrace gLBulletTrace = this.mStencilGLAnimation;
            if (gLBulletTrace != null) {
                gLBulletTrace.recycle();
            }
            this.mStencilGLAnimation = createStencilGLAnimation(stencilData);
            StencilManager.getInstance().recycleByteBuffer(stencilData);
        }
        StencilManager.getInstance().activateStencilDraw();
        BaseGLBarrageRender.GLBulletTrace gLBulletTrace2 = this.mStencilGLAnimation;
        if (gLBulletTrace2 != null) {
            stencilDraw(this.mShader, this.mCamera, gLBulletTrace2);
        }
    }

    private BaseGLBarrageRender.GLBulletTrace createStencilGLAnimation(ByteBuffer byteBuffer) {
        int videoWidth = StencilManager.getInstance().getVideoWidth();
        int videoHeight = StencilManager.getInstance().getVideoHeight();
        int originalPointX = StencilManager.getInstance().getOriginalPointX();
        int originalPointY = StencilManager.getInstance().getOriginalPointY();
        BaseGLBarrageRender.GLBulletTrace gLBulletTrace = new BaseGLBarrageRender.GLBulletTrace(byteBuffer, StencilManager.DRAW_AREA_WIDTH, StencilManager.DRAW_AREA_HEIGHT);
        gLBulletTrace.setGLXY(originalPointX, originalPointY);
        gLBulletTrace.setGLScaleXY(videoWidth / 540.0f, videoHeight / 324.0f);
        return gLBulletTrace;
    }

    public void resume() {
        SmoothDeltaTime smoothDeltaTime = this.mSmoothDelta;
        if (smoothDeltaTime != null) {
            smoothDeltaTime.reset();
        }
    }

    @Override // com.duowan.kiwi.barrage.render.BaseGLBarrageRender
    public void delete() {
        super.delete();
        BarrageShader barrageShader = this.mShader;
        if (barrageShader != null) {
            barrageShader.destroy();
            this.mShader = null;
        }
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public void ceaseFire(boolean z) {
        resetStencil();
        resetRectStencil();
        super.ceaseFire(z);
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void onRequireMarqueeInSurface(Bitmap bitmap, float f, long j) {
        createGLAnimation(bitmap).x(f, -bitmap.getWidth()).y(0.0f, 0.0f).duration(j).start(this);
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void setBarrageAlpha(float f) {
        setAlpha(f);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public void setOrientation(int i, boolean z) {
        super.setOrientation(i, z);
        this.mOrientation.set(i);
    }

    private void initRender() {
        BarrageShader barrageShader = new BarrageShader();
        this.mShader = barrageShader;
        barrageShader.use();
        Camera camera = new Camera(1.0f, -1.0f, 1.0f, 2.0f, -1.0f);
        this.mCamera = camera;
        camera.setUp();
        GLES20.glEnableVertexAttribArray(this.mShader.getPosHandle());
        GLES20.glEnableVertexAttribArray(this.mShader.getTexHandle());
        initHolderGL();
        this.mSmoothDelta = new SmoothDeltaTime();
        CatchError.catchError("barrage render init");
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public void setBarrageRenderOn(boolean z) {
        super.setBarrageRenderOn(z);
        GLBarrageAdapter.setRenderOn(z);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    protected AbsTrace createTrace(BulletBuilder.Bullet<ByteBuffer> bullet, int i) {
        return new BaseGLBarrageRender.GLBulletTrace(bullet, i);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    protected float toCustomWorldPositionX(float f) {
        return GLCoordinate.toWorldPositionX(f);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    protected float toCustomWorldPositionY(float f) {
        return GLCoordinate.toWorldPositionY(f);
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void resetSmooth() {
        SmoothDeltaTime smoothDeltaTime = this.mSmoothDelta;
        if (smoothDeltaTime != null) {
            smoothDeltaTime.reset();
        }
    }
}
