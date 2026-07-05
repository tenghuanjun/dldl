package com.google.zxing.oned.rss.expanded.decoders;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
abstract class DecodedObject {
    private final int newPosition;

    DecodedObject(int i) {
        this.newPosition = i;
    }

    final int getNewPosition() {
        return this.newPosition;
    }
}
