package com.huya.force.videohardencode;

import android.os.Handler;
import com.huya.force.common.VideoEncodeType;
import com.huya.force.export.videoencode.VideoEncodeInput;
import com.huya.force.gles.EglCore;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HardVideoEncodeInput extends VideoEncodeInput {
    EglCore mEglCore;
    Handler mEglHander;

    public HardVideoEncodeInput(int i, int i2, int i3, int i4, VideoEncodeInput.BitrateMode bitrateMode, VideoEncodeType videoEncodeType) {
        this(null, null, i, i2, i3, i4, bitrateMode, videoEncodeType);
    }

    public HardVideoEncodeInput(Handler handler, EglCore eglCore, int i, int i2, int i3, int i4, VideoEncodeInput.BitrateMode bitrateMode, VideoEncodeType videoEncodeType) {
        super(i, i2, i3, i4, bitrateMode, videoEncodeType);
        this.mEglHander = handler;
        this.mEglCore = eglCore;
    }

    public EglCore getEglCore() {
        return this.mEglCore;
    }

    public void setEglCore(EglCore eglCore) {
        this.mEglCore = eglCore;
    }

    public Handler getEglHander() {
        return this.mEglHander;
    }

    public void setEglHander(Handler handler) {
        this.mEglHander = handler;
    }
}
