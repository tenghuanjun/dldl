package com.google.zxing.decoding;

import android.content.Intent;
import android.net.Uri;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.decoding.Intents;
import com.igexin.push.core.b;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class DecodeFormatManager {
    static final Vector<BarcodeFormat> DATA_MATRIX_FORMATS;
    static final Vector<BarcodeFormat> ONE_D_FORMATS;
    static final Vector<BarcodeFormat> QR_CODE_FORMATS;
    private static final Pattern COMMA_PATTERN = Pattern.compile(b.aj);
    static final Vector<BarcodeFormat> PRODUCT_FORMATS = new Vector<>(5);

    static {
        PRODUCT_FORMATS.add(BarcodeFormat.UPC_A);
        PRODUCT_FORMATS.add(BarcodeFormat.UPC_E);
        PRODUCT_FORMATS.add(BarcodeFormat.EAN_13);
        PRODUCT_FORMATS.add(BarcodeFormat.EAN_8);
        ONE_D_FORMATS = new Vector<>(PRODUCT_FORMATS.size() + 4);
        ONE_D_FORMATS.addAll(PRODUCT_FORMATS);
        ONE_D_FORMATS.add(BarcodeFormat.CODE_39);
        ONE_D_FORMATS.add(BarcodeFormat.CODE_93);
        ONE_D_FORMATS.add(BarcodeFormat.CODE_128);
        ONE_D_FORMATS.add(BarcodeFormat.ITF);
        QR_CODE_FORMATS = new Vector<>(1);
        QR_CODE_FORMATS.add(BarcodeFormat.QR_CODE);
        DATA_MATRIX_FORMATS = new Vector<>(1);
        DATA_MATRIX_FORMATS.add(BarcodeFormat.DATA_MATRIX);
    }

    private DecodeFormatManager() {
    }

    static Vector<BarcodeFormat> parseDecodeFormats(Intent intent) {
        String stringExtra = intent.getStringExtra(Intents.Scan.SCAN_FORMATS);
        return parseDecodeFormats(stringExtra != null ? Arrays.asList(COMMA_PATTERN.split(stringExtra)) : null, intent.getStringExtra(Intents.Scan.MODE));
    }

    static Vector<BarcodeFormat> parseDecodeFormats(Uri uri) {
        List<String> queryParameters = uri.getQueryParameters(Intents.Scan.SCAN_FORMATS);
        if (queryParameters != null && queryParameters.size() == 1 && queryParameters.get(0) != null) {
            queryParameters = Arrays.asList(COMMA_PATTERN.split(queryParameters.get(0)));
        }
        return parseDecodeFormats(queryParameters, uri.getQueryParameter(Intents.Scan.MODE));
    }

    private static Vector<BarcodeFormat> parseDecodeFormats(Iterable<String> iterable, String str) {
        if (iterable != null) {
            Vector<BarcodeFormat> vector = new Vector<>();
            try {
                Iterator<String> it = iterable.iterator();
                while (it.hasNext()) {
                    vector.add(BarcodeFormat.valueOf(it.next()));
                }
                return vector;
            } catch (IllegalArgumentException unused) {
            }
        }
        if (str == null) {
            return null;
        }
        if (Intents.Scan.PRODUCT_MODE.equals(str)) {
            return PRODUCT_FORMATS;
        }
        if (Intents.Scan.QR_CODE_MODE.equals(str)) {
            return QR_CODE_FORMATS;
        }
        if (Intents.Scan.DATA_MATRIX_MODE.equals(str)) {
            return DATA_MATRIX_FORMATS;
        }
        if (Intents.Scan.ONE_D_MODE.equals(str)) {
            return ONE_D_FORMATS;
        }
        return null;
    }
}
