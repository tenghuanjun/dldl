package androidx.core.net;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public class ParseException extends RuntimeException {

    @NonNull
    public final String response;

    ParseException(@NonNull String str) {
        super(str);
        this.response = str;
    }
}
