package com.huya.mtp.utils.gl.unit;

import com.huya.mtp.utils.gl.camera.KGLAbsCamera;
import com.huya.mtp.utils.gl.core.KGLAbsGLObject;
import com.huya.mtp.utils.gl.core.KGLCoordinate;
import com.huya.mtp.utils.gl.texture.KGLDensityBitmap;
import com.huya.mtp.utils.gl.texture.KGLDrawOrder2D;
import com.huya.mtp.utils.gl.texture.KGLTexture2D;
import com.huya.mtp.utils.gl.texture.KGLTextureRect2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class KGLUnitParent2D extends KGLUnit2D implements KGLIUnitParent {
    private List<KGLUnit2D> mChildren;
    private boolean mClipChildren;
    private float mClipRectBottom;
    private float mClipRectLeft;
    private float mClipRectRight;
    private float mClipRectTop;

    public static KGLUnitParent2D create(KGLUnitParent2D kGLUnitParent2D, KGLDensityBitmap kGLDensityBitmap, KGLTextureRect2D kGLTextureRect2D, KGLDrawOrder2D kGLDrawOrder2D) {
        return create(kGLUnitParent2D, kGLDensityBitmap, kGLTextureRect2D, kGLDrawOrder2D, kGLDensityBitmap == null ? 0.0f : kGLDensityBitmap.getWidth(), kGLDensityBitmap == null ? 0.0f : kGLDensityBitmap.getHeight());
    }

    public static KGLUnitParent2D create(KGLUnitParent2D kGLUnitParent2D, KGLTexture2D kGLTexture2D, KGLTextureRect2D kGLTextureRect2D, KGLDrawOrder2D kGLDrawOrder2D) {
        return create(kGLUnitParent2D, kGLTexture2D, kGLTextureRect2D, kGLDrawOrder2D, kGLTexture2D == null ? 0.0f : kGLTexture2D.getWidth(), kGLTexture2D == null ? 0.0f : kGLTexture2D.getHeight());
    }

    public static KGLUnitParent2D create(KGLUnitParent2D kGLUnitParent2D, KGLDensityBitmap kGLDensityBitmap, KGLTextureRect2D kGLTextureRect2D, KGLDrawOrder2D kGLDrawOrder2D, float f, float f2) {
        return create(kGLUnitParent2D, kGLDensityBitmap == null ? null : KGLTexture2D.create(kGLDensityBitmap), kGLTextureRect2D, kGLDrawOrder2D, f, f2);
    }

    public static KGLUnitParent2D create(KGLUnitParent2D kGLUnitParent2D, KGLTexture2D kGLTexture2D, KGLTextureRect2D kGLTextureRect2D, KGLDrawOrder2D kGLDrawOrder2D, float f, float f2) {
        KGLUnitParent2D kGLUnitParent2D2 = new KGLUnitParent2D(kGLUnitParent2D, kGLTexture2D, kGLTextureRect2D, kGLDrawOrder2D, f, f2);
        return !kGLUnitParent2D2.isValid() ? (KGLUnitParent2D) delete(kGLUnitParent2D2) : kGLUnitParent2D2;
    }

    protected KGLUnitParent2D(KGLUnitParent2D kGLUnitParent2D, KGLTexture2D kGLTexture2D, KGLTextureRect2D kGLTextureRect2D, KGLDrawOrder2D kGLDrawOrder2D, float f, float f2) {
        super(kGLUnitParent2D, kGLTexture2D, kGLTextureRect2D, kGLDrawOrder2D, f, f2);
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLIUnitParent
    public void setClipChildren(boolean z) {
        this.mClipChildren = z;
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLIUnitParent
    public boolean isClipChildren() {
        return this.mClipChildren;
    }

    public List<KGLUnit2D> getChildren() {
        return this.mChildren;
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLIUnitParent
    public void addChild(KGLAbsUnit kGLAbsUnit) throws RuntimeException {
        KGLIUnitParent parent = kGLAbsUnit.getParent();
        if (parent != null) {
            throw new RuntimeException("ready has parent " + parent);
        }
        if (!(kGLAbsUnit instanceof KGLUnit2D)) {
            throw new RuntimeException("child not KGLUnit2D " + kGLAbsUnit.getClass().getName());
        }
        this.mChildren.add((KGLUnit2D) kGLAbsUnit);
        kGLAbsUnit.setParent(this);
        kGLAbsUnit.invalidate();
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLIUnitParent
    public void removeChild(KGLAbsUnit kGLAbsUnit) throws RuntimeException {
        KGLIUnitParent parent = kGLAbsUnit.getParent();
        if (parent == null || this != parent) {
            throw new RuntimeException("no child's parent " + parent);
        }
        if (!(kGLAbsUnit instanceof KGLUnit2D)) {
            throw new RuntimeException("child not KGLUnit2D " + kGLAbsUnit.getClass().getName());
        }
        this.mChildren.remove(kGLAbsUnit);
        kGLAbsUnit.setParent(null);
        kGLAbsUnit.invalidate();
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLUnit2D, com.huya.mtp.utils.gl.unit.KGLAbsUnit
    public void invalidate() {
        super.invalidate();
        float[] locationInScene = getLocationInScene();
        this.mClipRectLeft = locationInScene[0];
        this.mClipRectTop = locationInScene[1];
        this.mClipRectRight = getClipRectLeft() + getScaledWidth();
        this.mClipRectBottom = getClipRectTop() + getScaledHeight();
        if (this.mClipChildren && (getParent() instanceof KGLUnitParent2D)) {
            KGLUnitParent2D kGLUnitParent2D = (KGLUnitParent2D) getParent();
            if (getClipRectLeft() < kGLUnitParent2D.getClipRectLeft()) {
                this.mClipRectLeft = kGLUnitParent2D.getClipRectLeft();
            }
            if (getClipRectTop() < kGLUnitParent2D.getClipRectTop()) {
                this.mClipRectTop = kGLUnitParent2D.getClipRectTop();
            }
            if (getClipRectRight() > kGLUnitParent2D.getClipRectRight()) {
                this.mClipRectRight = kGLUnitParent2D.getClipRectRight();
            }
            if (getClipRectBottom() > kGLUnitParent2D.getClipRectBottom()) {
                this.mClipRectBottom = kGLUnitParent2D.getClipRectBottom();
            }
        }
        Iterator<KGLUnit2D> it = this.mChildren.iterator();
        while (it.hasNext()) {
            it.next().invalidate();
        }
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLUnit2D, com.huya.mtp.utils.gl.unit.KGLAbsUnit
    public void render(KGLCoordinate kGLCoordinate, KGLAbsCamera kGLAbsCamera) {
        super.render(kGLCoordinate, kGLAbsCamera);
        Iterator<KGLUnit2D> it = this.mChildren.iterator();
        while (it.hasNext()) {
            it.next().render(kGLCoordinate, kGLAbsCamera);
        }
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLUnit2D, com.huya.mtp.utils.gl.unit.KGLAbsUnit, com.huya.mtp.utils.gl.core.KGLAbsGLObject
    protected void deleteGLObject() {
        Iterator<KGLUnit2D> it = this.mChildren.iterator();
        while (it.hasNext()) {
            KGLAbsGLObject.delete(it.next());
        }
        this.mChildren.clear();
        super.deleteGLObject();
    }

    @Override // com.huya.mtp.utils.gl.unit.KGLAbsUnit
    protected void init(KGLIUnitParent kGLIUnitParent) {
        this.mChildren = new ArrayList();
        this.mClipChildren = true;
        this.mClipRectLeft = 0.0f;
        this.mClipRectTop = 0.0f;
        this.mClipRectRight = 0.0f;
        this.mClipRectBottom = 0.0f;
        super.init(kGLIUnitParent);
    }

    protected float getClipRectLeft() {
        return this.mClipRectLeft;
    }

    protected float getClipRectTop() {
        return this.mClipRectTop;
    }

    protected float getClipRectRight() {
        return this.mClipRectRight;
    }

    protected float getClipRectBottom() {
        return this.mClipRectBottom;
    }
}
