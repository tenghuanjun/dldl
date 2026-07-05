package com.google.zxing.oned.rss.expanded.decoders;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
abstract class DecodedObject {
    private final int newPosition;

    DecodedObject(int i) {
        this.newPosition = i;
    }

    final int getNewPosition() {
        return this.newPosition;
    }
}
