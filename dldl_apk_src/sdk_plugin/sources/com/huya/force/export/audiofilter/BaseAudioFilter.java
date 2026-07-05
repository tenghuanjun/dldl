package com.huya.force.export.audiofilter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseAudioFilter {

    public enum FilterType {
        kRecordStudioFilter,
        kKtvFilter
    }

    public abstract void init();

    public abstract void process(byte[] bArr, byte[] bArr2);

    public abstract void setFilterType(FilterType filterType);

    public abstract void uninit();

    public BaseAudioFilter(AudioFilterInput audioFilterInput) {
    }
}
