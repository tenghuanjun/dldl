package com.bumptech.glide.load.engine.bitmap_recycle;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
interface ArrayAdapterInterface<T> {
    int getArrayLength(T t);

    int getElementSizeInBytes();

    String getTag();

    T newArray(int i);
}
