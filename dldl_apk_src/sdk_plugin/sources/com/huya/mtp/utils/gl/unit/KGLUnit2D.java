package com.huya.mtp.utils.gl.unit;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.huya.mtp.utils.gl.camera.KGLAbsCamera;
import com.huya.mtp.utils.gl.core.KGLCoordinate;
import com.huya.mtp.utils.gl.program.KGLAbsProgram;
import com.huya.mtp.utils.gl.texture.KGLDensityBitmap;
import com.huya.mtp.utils.gl.texture.KGLDrawOrder2D;
import com.huya.mtp.utils.gl.texture.KGLTexture2D;
import com.huya.mtp.utils.gl.texture.KGLTextureRect2D;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class KGLUnit2D extends KGLAbsUnit {
    private float mAlpha;
    protected KGLDrawOrder2D mDrawOrder;
    private float mHeight;
    private boolean mMatrixChanged;
    protected float[] mModelMatrix;
    private float mPosX;
    private float mPosY;
    protected KGLTextureRect2D mRect;
    private float mScaleSceneX;
    private float mScaleSceneY;
    private float mScaleX;
    private float mScaleY;
    protected KGLTexture2D mTexture;
    private float mWidth;

    public static KGLUnit2D create(KGLUnitParent2D kGLUnitParent2D, KGLDensityBitmap kGLDensityBitmap, KGLTextureRect2D kGLTextureRect2D, KGLDrawOrder2D kGLDrawOrder2D) {
        return create(kGLUnitParent2D, kGLDensityBitmap, kGLTextureRect2D, kGLDrawOrder2D, kGLDensityBitmap == null ? 0.0f : kGLDensityBitmap.getWidth(), kGLDensityBitmap == null ? 0.0f : kGLDensityBitmap.getHeight());
    }

    public static KGLUnit2D create(KGLUnitParent2D kGLUnitParent2D, KGLTexture2D kGLTexture2D, KGLTextureRect2D kGLTextureRect2D, KGLDrawOrder2D kGLDrawOrder2D) {
        return create(kGLUnitParent2D, kGLTexture2D, kGLTextureRect2D, kGLDrawOrder2D, kGLTexture2D == null ? 0.0f : kGLTexture2D.getWidth(), kGLTexture2D == null ? 0.0f : kGLTexture2D.getHeight());
    }

    public static KGLUnit2D create(KGLUnitParent2D kGLUnitParent2D, KGLDensityBitmap kGLDensityBitmap, KGLTextureRect2D kGLTextureRect2D, KGLDrawOrder2D kGLDrawOrder2D, float f, float f2) {
        return create(kGLUnitParent2D, kGLDensityBitmap == null ? null : KGLTexture2D.create(kGLDensityBitmap), kGLTextureRect2D, kGLDrawOrder2D, f, f2);
    }

    public static KGLUnit2D create(KGLUnitParent2D kGLUnitParent2D, KGLTexture2D kGLTexture2D, KGLTextureRect2D kGLTextureRect2D, KGLDrawOrder2D kGLDrawOrder2D, float f, float f2) {
        KGLUnit2D kGLUnit2D = new KGLUnit2D(kGLUnitParent2D, kGLTexture2D, kGLTextureRect2D, kGLDrawOrder2D, f, f2);
        return !kGLUnit2D.isValid() ? (KGLUnit2D) delete(kGLUnit2D) : kGLUnit2D;
    }

    protected KGLUnit2D(KGLUnitParent2D kGLUnitParent2D, KGLTexture2D kGLTexture2D, KGLTextureRect2D kGLTextureRect2D, KGLDrawOrder2D kGLDrawOrder2D, float f, float f2) {
        super(kGLUnitParent2D);
        this.mModelMatrix = new float[16];
        this.mWidth = 0.0f;
        this.mHeight = 0.0f;
        this.mPosX = 0.0f;
        this.mPosY = 0.0f;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mScaleSceneX = 1.0f;
        this.mScaleSceneY = 1.0f;
        this.mAlpha = 1.0f;
        this.mWidth = f;
        this.mHeight = f2;
        if (kGLTexture2D != null) {
            this.mTexture = (KGLTexture2D) kGLTexture2D.reference(this.mTexture, kGLTexture2D);
        }
        kGLTextureRect2D = kGLTextureRect2D == null ? KGLTextureRect2D.createRect2D() : kGLTextureRect2D;
        this.mRect = (KGLTextureRect2D) kGLTextureRect2D.reference(this.mRect, kGLTextureRect2D);
        kGLDrawOrder2D = kGLDrawOrder2D == null ? KGLDrawOrder2D.createDrawOrder2D() : kGLDrawOrder2D;
        this.mDrawOrder = (KGLDrawOrder2D) kGLDrawOrder2D.reference(this.mDrawOrder, kGLDrawOrder2D);
        this.mMatrixChanged = true;
    }

    public void setWidth(float f) {
        this.mWidth = f;
        invalidate();
    }

    public void setHeight(float f) {
        this.mHeight = f;
        invalidate();
    }

    public float getWidth() {
        return this.mWidth;
    }

    public float getHeight() {
        return this.mHeight;
    }

    public void setPosX(float f) {
        this.mPosX = f;
        invalidate();
    }

    public void setPosY(float f) {
        this.mPosY = f;
        invalidate();
    }

    public float getPosX() {
        return this.mPosX;
    }

    public float getPosY() {
        return this.mPosY;
    }

    public void setScaleX(float f) {
        this.mScaleX = f;
        calcScaleInScene();
        invalidate();
    }

    public void setScaleY(float f) {
        this.mScaleY = f;
        calcScaleInScene();
        invalidate();
    }

    public float getScaleX() {
        return this.mScaleX;
    }

    public float getScaleY() {
        return this.mScaleY;
    }

    public void setAlpha(float f) {
        this.mAlpha = f;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public float[] getLocationInScene() {
        float[] fArr = {getPosX(), getPosY()};
        KGLIUnitParent parent = getParent();
        while (parent instanceof KGLUnitParent2D) {
            KGLUnitParent2D kGLUnitParent2D = (KGLUnitParent2D) parent;
            fArr[0] = fArr[0] + kGLUnitParent2D.getPosX();
            fArr[1] = fArr[1] + kGLUnitParent2D.getPosY();
            parent = kGLUnitParent2D.getParent();
        }
        return fArr;
    }

    public void setTexture(KGLTexture2D kGLTexture2D) {
        this.mTexture = (KGLTexture2D) kGLTexture2D.reference(this.mTexture, kGLTexture2D);
    }

    public void setTexture(KGLDensityBitmap kGLDensityBitmap) {
        setTexture(KGLTexture2D.create(kGLDensityBitmap));
    }

    public float getScaledWidth() {
        return getScaleSceneX() * getWidth();
    }

    public float getScaledHeight() {
        return getScaleSceneY() * getHeight();
    }

    protected float getScaleSceneX() {
        return this.mScaleSceneX;
    }

    protected float getScaleSceneY() {
        return this.mScaleSceneY;
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLAbsUnit
    public void invalidate() {
        this.mMatrixChanged = true;
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLAbsUnit
    public void validate() {
        this.mMatrixChanged = false;
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsGLObject
    public boolean isValid() {
        KGLTextureRect2D kGLTextureRect2D;
        KGLDrawOrder2D kGLDrawOrder2D;
        KGLTexture2D kGLTexture2D = this.mTexture;
        return (kGLTexture2D == null || kGLTexture2D.isValid()) && (kGLTextureRect2D = this.mRect) != null && kGLTextureRect2D.isValid() && (kGLDrawOrder2D = this.mDrawOrder) != null && kGLDrawOrder2D.isValid();
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLAbsUnit
    public void render(KGLCoordinate kGLCoordinate, KGLAbsCamera kGLAbsCamera) {
        doModelMatrix(kGLCoordinate);
        KGLIUnitParent parent = getParent();
        if ((parent instanceof KGLUnitParent2D) && parent.isClipChildren()) {
            KGLUnitParent2D kGLUnitParent2D = (KGLUnitParent2D) parent;
            if (kGLUnitParent2D.getClipRectRight() < kGLUnitParent2D.getClipRectLeft() || kGLUnitParent2D.getClipRectBottom() < kGLUnitParent2D.getClipRectTop()) {
                return;
            }
            GLES20.glEnable(3089);
            GLES20.glScissor((int) kGLUnitParent2D.getClipRectLeft(), (int) (kGLCoordinate.getWorldHeight() - kGLUnitParent2D.getClipRectBottom()), (int) ((kGLUnitParent2D.getClipRectRight() - kGLUnitParent2D.getClipRectLeft()) + 1.0f), (int) ((kGLUnitParent2D.getClipRectBottom() - kGLUnitParent2D.getClipRectTop()) + 1.0f));
            draw(getProgram(), kGLAbsCamera);
            GLES20.glDisable(3089);
            return;
        }
        draw(getProgram(), kGLAbsCamera);
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLAbsUnit
    protected void doModelMatrix(KGLCoordinate kGLCoordinate) {
        if (isInvalid()) {
            calcScaleInScene();
            float[] locationInScene = getLocationInScene();
            locationInScene[0] = locationInScene[0] + ((getWidth() * getScaleSceneX()) / 2.0f);
            locationInScene[1] = locationInScene[1] + ((getHeight() * getScaleSceneY()) / 2.0f);
            float gLSize = kGLCoordinate.toGLSize(this.mWidth) / kGLCoordinate.getGLUnit();
            float gLSize2 = kGLCoordinate.toGLSize(this.mHeight) / kGLCoordinate.getGLUnit();
            Matrix.setIdentityM(this.mModelMatrix, 0);
            Matrix.translateM(this.mModelMatrix, 0, kGLCoordinate.toGLPositionX(locationInScene[0]), kGLCoordinate.toGLPositionY(locationInScene[1]), 0.0f);
            Matrix.scaleM(this.mModelMatrix, 0, gLSize * getScaleSceneX(), gLSize2 * getScaleSceneY(), 0.0f);
            this.mMatrixChanged = false;
        }
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLAbsUnit
    protected void draw(KGLAbsProgram kGLAbsProgram, KGLAbsCamera kGLAbsCamera) {
        kGLAbsProgram.bind();
        kGLAbsProgram.setMVPMatrix(this.mModelMatrix, kGLAbsCamera.getCurrentMatrix(), kGLAbsCamera.getViewMatrix(), kGLAbsCamera.getProjectionMatrix());
        GLES20.glUniform1f(kGLAbsProgram.getAlphaHandle(), this.mAlpha);
        this.mRect.bind();
        GLES20.glVertexAttribPointer(kGLAbsProgram.getPositionHandle(), 2, 5126, false, this.mRect.getStride(), 0);
        GLES20.glVertexAttribPointer(kGLAbsProgram.getTextureHandle(), 2, 5126, false, this.mRect.getStride(), this.mRect.getTexOffset());
        GLES20.glActiveTexture(33984);
        this.mTexture.bind();
        GLES20.glUniform1i(kGLAbsProgram.getTextureSampler(), 0);
        this.mDrawOrder.bind();
        GLES20.glDrawElements(4, 6, 5123, 0);
        this.mDrawOrder.unBind();
        this.mTexture.unBind();
        this.mRect.unBind();
        kGLAbsProgram.unBind();
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLAbsUnit
    public boolean isInvalid() {
        return this.mMatrixChanged;
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLAbsUnit, com.huya.mtp.utils.gl.core.KGLAbsGLObject
    protected void deleteGLObject() {
        KGLTextureRect2D kGLTextureRect2D = this.mRect;
        if (kGLTextureRect2D != null) {
            kGLTextureRect2D.release();
            this.mRect = null;
        }
        KGLDrawOrder2D kGLDrawOrder2D = this.mDrawOrder;
        if (kGLDrawOrder2D != null) {
            kGLDrawOrder2D.release();
            this.mDrawOrder = null;
        }
        KGLTexture2D kGLTexture2D = this.mTexture;
        if (kGLTexture2D != null) {
            kGLTexture2D.release();
            this.mTexture = null;
        }
        super.deleteGLObject();
    }

    protected void calcScaleInScene() {
        this.mScaleSceneX = getScaleX();
        this.mScaleSceneY = getScaleY();
        KGLIUnitParent parent = getParent();
        while (parent instanceof KGLUnitParent2D) {
            KGLUnitParent2D kGLUnitParent2D = (KGLUnitParent2D) parent;
            this.mScaleSceneX *= kGLUnitParent2D.getScaleX();
            this.mScaleSceneY *= kGLUnitParent2D.getScaleY();
            parent = kGLUnitParent2D.getParent();
        }
    }
}
