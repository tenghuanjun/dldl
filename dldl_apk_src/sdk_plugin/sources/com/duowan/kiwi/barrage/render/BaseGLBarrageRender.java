package com.duowan.kiwi.barrage.render;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.duowan.ark.util.glutils.tools.Camera;
import com.duowan.ark.util.glutils.tools.VBO;
import com.duowan.ark.util.glutils.utils.CatchError;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.newcache.AbsDrawingCache;
import com.duowan.kiwi.barrage.render.draw.BulletBuilder;
import com.duowan.kiwi.barrage.render.shader.BarrageShader;
import com.duowan.kiwi.barrage.trace.AbsTrace;
import com.duowan.kiwi.barrage.utils.GLCoordinate;
import com.duowan.kiwi.barrage.view.IGLBarrageView;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
abstract class BaseGLBarrageRender extends AbsBarrageRender<GLBulletTrace, ByteBuffer> {
    private static final int Stride = 20;
    private static final int TexOffset = 12;
    private VBO mDrawListVBO;
    private float[] mModelMatrix;
    private VBO mPosVBO;

    public BaseGLBarrageRender(IGLBarrageView iGLBarrageView, int i, boolean z, int i2, float f) {
        super(iGLBarrageView, i, z, i2, f, BarrageConfig.DEFAULT_BARRAGE_SIZE);
        this.mPosVBO = null;
        this.mDrawListVBO = null;
        this.mModelMatrix = new float[16];
    }

    public GLBulletTrace createGLAnimation(Bitmap bitmap) {
        return new GLBulletTrace(bitmap);
    }

    public void initHolderGL() {
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(770, 771);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(12);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        ShortBuffer shortBufferAsShortBuffer = byteBufferAllocateDirect.asShortBuffer();
        shortBufferAsShortBuffer.put(new short[]{0, 1, 2, 2, 3, 0});
        shortBufferAsShortBuffer.position(0);
        this.mDrawListVBO = new VBO(34963, 12, shortBufferAsShortBuffer, 35044);
        ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(80);
        byteBufferAllocateDirect2.order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer = byteBufferAllocateDirect2.asFloatBuffer();
        floatBufferAsFloatBuffer.put(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        floatBufferAsFloatBuffer.position(0);
        this.mPosVBO = new VBO(34962, 80, floatBufferAsFloatBuffer, 35044);
    }

    public synchronized boolean drawFrames(BarrageShader barrageShader, Camera camera, float f) {
        recycleUnusedFrame();
        calculateBarrage(f);
        drawCurrentFrame(barrageShader, camera);
        return isEmpty();
    }

    protected void drawCurrentFrame(BarrageShader barrageShader, Camera camera) {
        GLES20.glBlendFunc(770, 771);
        ArrayList<GLBulletTrace> animations = getAnimations();
        int size = animations.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            GLBulletTrace gLBulletTrace = animations.get(size);
            if (gLBulletTrace.mExplosive != 2 && gLBulletTrace.mExplosive != 3) {
                drawFrame(barrageShader, camera, gLBulletTrace);
            }
        }
        for (int size2 = animations.size() - 1; size2 >= 0; size2--) {
            GLBulletTrace gLBulletTrace2 = animations.get(size2);
            if (gLBulletTrace2.mExplosive == 2) {
                drawFrame(barrageShader, camera, gLBulletTrace2);
            }
        }
        for (int size3 = animations.size() - 1; size3 >= 0; size3--) {
            GLBulletTrace gLBulletTrace3 = animations.get(size3);
            if (gLBulletTrace3.mExplosive == 3) {
                drawFrame(barrageShader, camera, gLBulletTrace3);
            }
        }
    }

    protected void drawEmpty(BarrageShader barrageShader, Camera camera) {
        camera.pressShutter(barrageShader.getProjectionViewMatrixHandle(), barrageShader.getModelMatrixHandle(), this.mModelMatrix);
        GLES20.glBindBuffer(34962, this.mPosVBO.getId());
        GLES20.glVertexAttribPointer(barrageShader.getPosHandle(), 3, 5126, false, 20, 0);
        GLES20.glVertexAttribPointer(barrageShader.getTexHandle(), 2, 5126, false, 20, 12);
        GLES20.glBindBuffer(34963, this.mDrawListVBO.getId());
        GLES20.glDrawElements(4, 6, 5123, 0);
        GLES20.glBindBuffer(34962, 0);
    }

    private void drawFrame(BarrageShader barrageShader, Camera camera, GLBulletTrace gLBulletTrace) {
        Matrix.setIdentityM(this.mModelMatrix, 0);
        Matrix.translateM(this.mModelMatrix, 0, gLBulletTrace.mCurrentFrame.mX, gLBulletTrace.mCurrentFrame.mY, 0.0f);
        Matrix.scaleM(this.mModelMatrix, 0, gLBulletTrace.mCurrentFrame.mScaleX * gLBulletTrace.mGLWidth, gLBulletTrace.mCurrentFrame.mScaleY * gLBulletTrace.mGLHeight, 1.0f);
        camera.pressShutter(barrageShader.getProjectionViewMatrixHandle(), barrageShader.getModelMatrixHandle(), this.mModelMatrix);
        GLES20.glBindTexture(3553, gLBulletTrace.mTextureId);
        GLES20.glBindBuffer(34962, this.mPosVBO.getId());
        GLES20.glVertexAttribPointer(barrageShader.getPosHandle(), 3, 5126, false, 20, 0);
        GLES20.glVertexAttribPointer(barrageShader.getTexHandle(), 2, 5126, false, 20, 12);
        GLES20.glUniform1f(barrageShader.getAlphaHanler(), gLBulletTrace.mCurrentFrame.mAlpha);
        GLES20.glUniform1i(barrageShader.getTextureSampler(), 0);
        GLES20.glBindBuffer(34963, this.mDrawListVBO.getId());
        GLES20.glDrawElements(4, 6, 5123, 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindBuffer(34962, 0);
    }

    public void delete() {
        ceaseFire(true);
        VBO vbo = this.mDrawListVBO;
        if (vbo != null) {
            vbo.delete();
            this.mDrawListVBO = null;
        }
        VBO vbo2 = this.mPosVBO;
        if (vbo2 != null) {
            vbo2.delete();
            this.mPosVBO = null;
        }
    }

    public void stencilDraw(BarrageShader barrageShader, Camera camera, GLBulletTrace gLBulletTrace) {
        GLES20.glBlendFunc(0, 771);
        drawFrame(barrageShader, camera, gLBulletTrace);
    }

    public static class GLBulletTrace extends AbsTrace {
        private BulletBuilder.Bullet<ByteBuffer> mBufferBullet;
        public float mGLWidth = 1.0f;
        public float mGLHeight = 1.0f;

        public GLBulletTrace(ByteBuffer byteBuffer, int i, int i2) {
            initAnimation(byteBuffer, i, i2);
        }

        public GLBulletTrace(Bitmap bitmap) {
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(bitmap.getByteCount());
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
            bitmap.copyPixelsToBuffer(byteBufferAllocateDirect);
            byteBufferAllocateDirect.position(0);
            this.mUseBitmap = true;
            initAnimation(byteBufferAllocateDirect, bitmap.getWidth(), bitmap.getHeight());
        }

        public GLBulletTrace(BulletBuilder.Bullet<ByteBuffer> bullet, int i) {
            this.mBufferBullet = bullet;
            exploreBullet(bullet);
            this.mTarget = i;
            initAnimation(bullet.getCacheObject().getContent(), bullet.getPixelsWidth(), bullet.getPixelsHeight());
        }

        public void initAnimation(ByteBuffer byteBuffer, int i, int i2) {
            this.mGLWidth = GLCoordinate.toGLUnit(i);
            this.mGLHeight = GLCoordinate.toGLUnit(i2);
            createTexture(byteBuffer, i, i2);
        }

        private void createTexture(ByteBuffer byteBuffer, int i, int i2) {
            int[] iArr = new int[1];
            GLES20.glGenTextures(1, iArr, 0);
            this.mTextureId = iArr[0];
            GLES20.glBindTexture(3553, this.mTextureId);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            byteBuffer.position(0);
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, byteBuffer);
            GLES20.glBindTexture(3553, 0);
            CatchError.catchError("GLAnimation create textrue");
        }

        @Override // com.duowan.kiwi.barrage.trace.AbsTrace
        public void recycle() {
            GLES20.glDeleteTextures(1, new int[]{this.mTextureId}, 0);
            CatchError.catchError("GLAnimation delete texture");
            this.mTextureId = -1;
            BulletBuilder.Bullet<ByteBuffer> bullet = this.mBufferBullet;
            if (bullet != null) {
                bullet.getCacheObject().decreaseReferenceCount();
            }
        }

        @Override // com.duowan.kiwi.barrage.trace.AbsTrace
        public AbsTrace x(float f, float f2) {
            this.mHolds[0][0] = GLCoordinate.toGLPositionX(f);
            this.mHolds[0][1] = GLCoordinate.toGLPositionX(f2);
            return this;
        }

        @Override // com.duowan.kiwi.barrage.trace.AbsTrace
        public AbsTrace y(float f, float f2) {
            this.mHolds[1][0] = GLCoordinate.toGLPositionY(f);
            this.mHolds[1][1] = GLCoordinate.toGLPositionY(f2);
            return this;
        }

        public void setGLXY(int i, int i2) {
            this.mCurrentFrame.mX = GLCoordinate.toGLPositionX(i);
            this.mCurrentFrame.mY = GLCoordinate.toGLPositionY(i2);
        }

        public void setGLScaleXY(float f, float f2) {
            this.mCurrentFrame.mScaleX = f;
            this.mCurrentFrame.mScaleY = f2;
        }
    }

    @Override // com.duowan.kiwi.barrage.newcache.DrawingFactory.BuildMachine
    public AbsDrawingCache<ByteBuffer> createDrawingCache(Bitmap bitmap) {
        return new AbsDrawingCache.GLDrawingCache(bitmap);
    }
}
