package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.Resource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ResourceEncoder<T> extends Encoder<Resource<T>> {
    EncodeStrategy getEncodeStrategy(Options options);
}
