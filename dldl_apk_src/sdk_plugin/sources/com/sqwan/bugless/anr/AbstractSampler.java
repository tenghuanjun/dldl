package com.sqwan.bugless.anr;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
abstract class AbstractSampler {
    protected long mSampleInterval;

    abstract void doSample();

    public AbstractSampler(long sampleInterval) {
    }
}
