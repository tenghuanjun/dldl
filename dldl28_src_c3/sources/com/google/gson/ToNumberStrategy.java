package com.google.gson;

import com.google.gson.stream.JsonReader;
import java.io.IOException;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface ToNumberStrategy {
    Number readNumber(JsonReader jsonReader) throws IOException;
}
