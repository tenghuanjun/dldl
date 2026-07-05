package com.huya.mtp.utils.gl.unit;

import com.huya.mtp.utils.gl.camera.KGLAbsCamera;
import com.huya.mtp.utils.gl.core.KGLAbsGLObject;
import com.huya.mtp.utils.gl.core.KGLCoordinate;
import com.huya.mtp.utils.gl.program.KGLAbsProgram;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class KGLAbsUnit extends KGLAbsGLObject {
    private KGLIUnitParent mParent = null;
    private KGLAbsProgram mProgram = null;

    protected abstract void doModelMatrix(KGLCoordinate kGLCoordinate);

    protected abstract void draw(KGLAbsProgram kGLAbsProgram, KGLAbsCamera kGLAbsCamera);

    public abstract void invalidate();

    public abstract boolean isInvalid();

    public abstract void validate();

    protected KGLAbsUnit(KGLIUnitParent kGLIUnitParent) {
        init(kGLIUnitParent);
    }

    public final KGLIUnitParent getParent() {
        return this.mParent;
    }

    protected void setParent(KGLIUnitParent kGLIUnitParent) {
        this.mParent = kGLIUnitParent;
    }

    protected void init(KGLIUnitParent kGLIUnitParent) {
        if (kGLIUnitParent != null) {
            kGLIUnitParent.addChild(this);
        }
    }

    public void render(KGLCoordinate kGLCoordinate, KGLAbsCamera kGLAbsCamera) {
        doModelMatrix(kGLCoordinate);
        draw(this.mProgram, kGLAbsCamera);
    }

    public void setProgram(KGLAbsProgram kGLAbsProgram) {
        this.mProgram = (KGLAbsProgram) kGLAbsProgram.reference(this.mProgram, kGLAbsProgram);
    }

    protected KGLAbsProgram getProgram() {
        return this.mProgram;
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsGLObject
    protected void deleteGLObject() {
        this.mParent = null;
        KGLAbsProgram kGLAbsProgram = this.mProgram;
        if (kGLAbsProgram != null) {
            kGLAbsProgram.release();
            this.mProgram = null;
        }
    }
}
