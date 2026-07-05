package com.huya.mtp.utils.gl.render.scene;

import com.huya.mtp.utils.gl.texture.KGLDrawOrder2D;
import com.huya.mtp.utils.gl.texture.KGLTextureRect2D;
import com.huya.mtp.utils.gl.unit.KGLUnit2D;
import com.huya.mtp.utils.gl.unit.KGLUnitParent2D;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class KGLScene2D extends KGLAbsScene<KGLUnit2D> {
    private KGLDrawOrder2D mDefaultDrawOrder2D;
    private KGLTextureRect2D mDefaultTextureRect2D;

    public KGLScene2D(int i) {
        super(i);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.huya.mtp.utils.gl.render.scene.KGLAbsScene
    public final void activateUnit(float f, float f2) {
        float[] fArr = new float[2];
        for (T t : this.mActiveUnits) {
            getLocationInUnit(t, f, f2, fArr);
            if (contains(t, fArr[0], fArr[1])) {
                return;
            }
        }
        for (int size = this.mUnits.size() - 1; size >= 0; size--) {
            KGLUnit2D kGLUnit2D = (KGLUnit2D) this.mUnits.get(size);
            getLocationInUnit(kGLUnit2D, f, f2, fArr);
            if (contains(kGLUnit2D, fArr[0], fArr[1])) {
                KGLUnit2D kGLUnit2D2 = kGLUnit2D;
                while (kGLUnit2D instanceof KGLUnitParent2D) {
                    fArr[0] = fArr[0] - kGLUnit2D.getPosX();
                    fArr[1] = fArr[1] - kGLUnit2D.getPosY();
                    List<KGLUnit2D> children = ((KGLUnitParent2D) kGLUnit2D).getChildren();
                    KGLUnit2D kGLUnit2D3 = null;
                    int size2 = children.size() - 1;
                    while (true) {
                        if (size2 < 0) {
                            break;
                        }
                        if (contains(children.get(size2), fArr[0], fArr[1])) {
                            kGLUnit2D3 = children.get(size2);
                            break;
                        }
                        size2--;
                    }
                    kGLUnit2D2 = kGLUnit2D;
                    kGLUnit2D = kGLUnit2D3;
                }
                List<T> list = this.mActiveUnits;
                if (kGLUnit2D == null) {
                    kGLUnit2D = kGLUnit2D2;
                }
                list.add(kGLUnit2D);
                return;
            }
        }
    }

    @Override // com.huya.mtp.utils.gl.render.scene.KGLAbsScene
    public final void deactivateUnit(float f, float f2) {
        float[] fArr = new float[2];
        for (T t : this.mActiveUnits) {
            getLocationInUnit(t, f, f2, fArr);
            if (contains(t, fArr[0], fArr[1])) {
                this.mActiveUnits.remove(t);
                return;
            }
        }
    }

    public KGLTextureRect2D getDefaultTextureRect2D() {
        return this.mDefaultTextureRect2D;
    }

    public KGLDrawOrder2D getDefaultDrawOrder2D() {
        return this.mDefaultDrawOrder2D;
    }

    @Override // com.huya.mtp.utils.gl.render.scene.KGLAbsScene
    protected void initScene() {
        super.initScene();
        KGLTextureRect2D kGLTextureRect2DCreateRect2D = KGLTextureRect2D.createRect2D();
        this.mDefaultTextureRect2D = kGLTextureRect2DCreateRect2D;
        if (kGLTextureRect2DCreateRect2D == null) {
            throw new RuntimeException("init scene error default texture rect failed");
        }
        KGLDrawOrder2D kGLDrawOrder2DCreateDrawOrder2D = KGLDrawOrder2D.createDrawOrder2D();
        this.mDefaultDrawOrder2D = kGLDrawOrder2DCreateDrawOrder2D;
        if (kGLDrawOrder2DCreateDrawOrder2D == null) {
            throw new RuntimeException("init scene error default draw order failed");
        }
        this.mDefaultTextureRect2D.retain();
        this.mDefaultDrawOrder2D.retain();
    }

    @Override // com.huya.mtp.utils.gl.render.scene.KGLAbsScene
    protected void releaseScene() {
        KGLTextureRect2D kGLTextureRect2D = this.mDefaultTextureRect2D;
        if (kGLTextureRect2D != null) {
            kGLTextureRect2D.dispose();
            this.mDefaultTextureRect2D = null;
        }
        KGLDrawOrder2D kGLDrawOrder2D = this.mDefaultDrawOrder2D;
        if (kGLDrawOrder2D != null) {
            kGLDrawOrder2D.dispose();
            this.mDefaultDrawOrder2D = null;
        }
    }

    private boolean contains(KGLUnit2D kGLUnit2D, float f, float f2) {
        return kGLUnit2D.getPosX() <= f && kGLUnit2D.getPosY() <= f2 && kGLUnit2D.getPosX() + kGLUnit2D.getScaledWidth() >= f && kGLUnit2D.getPosY() + kGLUnit2D.getScaledHeight() >= f2;
    }

    private void getLocationInUnit(KGLUnit2D kGLUnit2D, float f, float f2, float[] fArr) {
        fArr[0] = f;
        fArr[1] = f2;
        Object parent = kGLUnit2D.getParent();
        while (parent instanceof KGLUnitParent2D) {
            KGLUnit2D kGLUnit2D2 = (KGLUnit2D) parent;
            fArr[0] = fArr[0] - kGLUnit2D2.getPosX();
            fArr[1] = fArr[1] - kGLUnit2D2.getPosY();
            parent = kGLUnit2D2.getParent();
        }
    }
}
