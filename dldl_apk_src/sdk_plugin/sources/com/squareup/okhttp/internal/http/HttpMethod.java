package com.squareup.okhttp.internal.http;

import com.android.volley.toolbox.HttpClientStack;
import com.sq.tools.constant.ToolsConsent;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class HttpMethod {
    public static final Set<String> METHODS = new LinkedHashSet(Arrays.asList("OPTIONS", "GET", "HEAD", "POST", "PUT", ToolsConsent.HTTP_DELETE, "TRACE", HttpClientStack.HttpPatch.METHOD_NAME));

    public static boolean invalidatesCache(String str) {
        return str.equals("POST") || str.equals(HttpClientStack.HttpPatch.METHOD_NAME) || str.equals("PUT") || str.equals(ToolsConsent.HTTP_DELETE);
    }

    public static boolean hasRequestBody(String str) {
        return str.equals("POST") || str.equals("PUT") || str.equals(HttpClientStack.HttpPatch.METHOD_NAME) || str.equals(ToolsConsent.HTTP_DELETE);
    }

    private HttpMethod() {
    }
}
