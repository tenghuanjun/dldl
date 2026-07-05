package com.huya.mtp.utils.gl.render.scene;

import com.huya.mtp.utils.gl.camera.KGLAbsCamera;
import com.huya.mtp.utils.gl.core.KGLCoordinate;
import com.huya.mtp.utils.gl.unit.KGLAbsUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class KGLAbsScene<T extends KGLAbsUnit> {
    protected List<T> mActiveUnits;
    private int mId;
    protected List<T> mUnits;

    public abstract void activateUnit(float f, float f2);

    public abstract void deactivateUnit(float f, float f2);

    protected abstract void releaseScene();

    protected KGLAbsScene(int i) {
        this.mId = i;
        initScene();
    }

    public void addUnit(T t) {
        this.mUnits.add(t);
    }

    public void insertUnit(T t, int i) {
        this.mUnits.add(i, t);
    }

    public void removeUnit(T t) {
        this.mUnits.remove(t);
        this.mActiveUnits.remove(t);
    }

    public void render(KGLCoordinate kGLCoordinate, KGLAbsCamera kGLAbsCamera) {
        Iterator<T> it = this.mUnits.iterator();
        while (it.hasNext()) {
            it.next().render(kGLCoordinate, kGLAbsCamera);
        }
    }

    public final int getId() {
        return this.mId;
    }

    public final List<T> getActiveUnits() {
        return this.mActiveUnits;
    }

    public final void deactivateUnits() {
        this.mActiveUnits.clear();
    }

    public final void release() {
        Iterator<T> it = this.mUnits.iterator();
        while (it.hasNext()) {
            KGLAbsUnit.delete(it.next());
        }
        this.mUnits.clear();
        this.mActiveUnits.clear();
        releaseScene();
    }

    protected void initScene() {
        this.mUnits = new ArrayList();
        this.mActiveUnits = new ArrayList();
    }
}
