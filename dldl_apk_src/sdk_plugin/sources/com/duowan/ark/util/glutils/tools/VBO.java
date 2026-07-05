package com.duowan.ark.util.glutils.tools;

import android.opengl.GLES20;
import com.duowan.ark.util.glutils.utils.CatchError;
import java.nio.Buffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class VBO {
    private int mID;

    public VBO(int i, int i2, Buffer buffer, int i3) {
        this.mID = -1;
        int[] iArr = new int[1];
        GLES20.glGenBuffers(1, iArr, 0);
        int i4 = iArr[0];
        this.mID = i4;
        GLES20.glBindBuffer(i, i4);
        GLES20.glBufferData(i, i2, buffer, i3);
        GLES20.glBindBuffer(i, 0);
        CatchError.catchError("VBO");
    }

    public int getId() {
        return this.mID;
    }

    public void subBuffer(int i, int i2, Buffer buffer) {
        GLES20.glBindBuffer(i, this.mID);
        GLES20.glBufferSubData(i, 0, i2, buffer);
        CatchError.catchError("subBuffer");
        GLES20.glBindBuffer(i, 0);
    }

    public void delete() {
        int i = this.mID;
        if (-1 != i) {
            GLES20.glDeleteBuffers(1, new int[]{i}, 0);
            CatchError.catchError("deleteVBOBuffer");
            this.mID = -1;
        }
    }
}
