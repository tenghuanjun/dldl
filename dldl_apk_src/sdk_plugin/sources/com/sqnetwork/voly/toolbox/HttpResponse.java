package com.sqnetwork.voly.toolbox;

import com.sqnetwork.voly.Header;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class HttpResponse {
    private final InputStream mContent;
    private final int mContentLength;
    private final List<Header> mHeaders;
    private final int mStatusCode;

    public HttpResponse(int statusCode, List<Header> headers) {
        this(statusCode, headers, -1, null);
    }

    public HttpResponse(int statusCode, List<Header> headers, int contentLength, InputStream content) {
        this.mStatusCode = statusCode;
        this.mHeaders = headers;
        this.mContentLength = contentLength;
        this.mContent = content;
    }

    public final int getStatusCode() {
        return this.mStatusCode;
    }

    public final List<Header> getHeaders() {
        return Collections.unmodifiableList(this.mHeaders);
    }

    public final int getContentLength() {
        return this.mContentLength;
    }

    public final InputStream getContent() {
        return this.mContent;
    }
}
