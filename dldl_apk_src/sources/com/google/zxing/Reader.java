package com.google.zxing;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface Reader {
    Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException;

    Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    void reset();
}
