package com.huya.mtp.utils.gl.render.director;

import com.huya.mtp.utils.gl.camera.KGLAbsCamera;
import com.huya.mtp.utils.gl.core.KGLCoordinate;
import com.huya.mtp.utils.gl.render.scene.KGLAbsScene;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class KGLAbsDirector<T extends KGLAbsScene> {
    protected T mActiveScene;
    protected KGLAbsCamera mCamera;
    protected KGLCoordinate mCoordinate;
    protected List<T> mScenes;

    protected KGLAbsDirector(KGLAbsCamera kGLAbsCamera, KGLCoordinate kGLCoordinate) {
        initDirector(kGLAbsCamera, kGLCoordinate);
    }

    public void addScene(T t) {
        this.mScenes.add(t);
    }

    public void removeScene(T t) {
        this.mScenes.remove(t);
        if (t == this.mActiveScene) {
            this.mActiveScene = null;
        }
    }

    public void removeScene(int i) {
        for (T t : this.mScenes) {
            if (t.getId() == i) {
                removeScene(t);
                return;
            }
        }
    }

    public void activeScene(int i) {
        this.mActiveScene = null;
        for (T t : this.mScenes) {
            if (t.getId() == i) {
                this.mActiveScene = t;
                return;
            }
        }
    }

    public void onWindowChanged(int i, int i2) {
        float f = i;
        float f2 = i2;
        float f3 = (1.0f * f) / f2;
        this.mCoordinate.setLeft(-f3);
        this.mCoordinate.setRight(f3);
        this.mCoordinate.setWorldSize(f, f2);
        this.mCoordinate.setWorldUnit(f2);
        this.mCoordinate.setGLUnit(2.0f);
        this.mCamera.setViewPort(0, 0, i, i2);
        this.mCamera.lookAt(this.mCoordinate);
        this.mCamera.sharp(this.mCoordinate);
    }

    public void drawScene() {
        T t = this.mActiveScene;
        if (t != null) {
            t.render(this.mCoordinate, this.mCamera);
        }
    }

    public final T getActiveScene() {
        return this.mActiveScene;
    }

    public final void release() {
        Iterator<T> it = this.mScenes.iterator();
        while (it.hasNext()) {
            it.next().release();
        }
        this.mScenes.clear();
        this.mActiveScene = null;
    }

    protected void initDirector(KGLAbsCamera kGLAbsCamera, KGLCoordinate kGLCoordinate) {
        this.mScenes = new ArrayList();
        this.mActiveScene = null;
        this.mCamera = kGLAbsCamera;
        this.mCoordinate = kGLCoordinate;
    }
}
