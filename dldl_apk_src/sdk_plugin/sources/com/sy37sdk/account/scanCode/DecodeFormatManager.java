package com.sy37sdk.account.scanCode;

import com.google.zxing.BarcodeFormat;
import java.util.Vector;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public final class DecodeFormatManager {
    public static final Vector<BarcodeFormat> DATA_MATRIX_FORMATS;
    public static final Vector<BarcodeFormat> ONE_D_FORMATS;
    public static final Vector<BarcodeFormat> PRODUCT_FORMATS;
    public static final Vector<BarcodeFormat> QR_CODE_FORMATS;

    static {
        Vector<BarcodeFormat> vector = new Vector<>(5);
        PRODUCT_FORMATS = vector;
        vector.add(BarcodeFormat.UPC_A);
        PRODUCT_FORMATS.add(BarcodeFormat.UPC_E);
        PRODUCT_FORMATS.add(BarcodeFormat.EAN_13);
        PRODUCT_FORMATS.add(BarcodeFormat.EAN_8);
        PRODUCT_FORMATS.add(BarcodeFormat.RSS_14);
        Vector<BarcodeFormat> vector2 = new Vector<>(PRODUCT_FORMATS.size() + 4);
        ONE_D_FORMATS = vector2;
        vector2.addAll(PRODUCT_FORMATS);
        ONE_D_FORMATS.add(BarcodeFormat.CODE_39);
        ONE_D_FORMATS.add(BarcodeFormat.CODE_93);
        ONE_D_FORMATS.add(BarcodeFormat.CODE_128);
        ONE_D_FORMATS.add(BarcodeFormat.ITF);
        Vector<BarcodeFormat> vector3 = new Vector<>(1);
        QR_CODE_FORMATS = vector3;
        vector3.add(BarcodeFormat.QR_CODE);
        Vector<BarcodeFormat> vector4 = new Vector<>(1);
        DATA_MATRIX_FORMATS = vector4;
        vector4.add(BarcodeFormat.DATA_MATRIX);
    }

    private DecodeFormatManager() {
    }
}
