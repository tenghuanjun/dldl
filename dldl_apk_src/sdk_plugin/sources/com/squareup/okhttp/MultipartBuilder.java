package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import okio.BufferedSink;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class MultipartBuilder {
    private final String boundary;
    private final List<RequestBody> partBodies;
    private final List<Headers> partHeaders;
    private MediaType type;
    public static final MediaType MIXED = MediaType.parse("multipart/mixed");
    public static final MediaType ALTERNATIVE = MediaType.parse("multipart/alternative");
    public static final MediaType DIGEST = MediaType.parse("multipart/digest");
    public static final MediaType PARALLEL = MediaType.parse("multipart/parallel");
    public static final MediaType FORM = MediaType.parse("multipart/form-data");

    public MultipartBuilder() {
        this(UUID.randomUUID().toString());
    }

    public MultipartBuilder(String str) {
        this.type = MIXED;
        this.partHeaders = new ArrayList();
        this.partBodies = new ArrayList();
        this.boundary = str;
    }

    public MultipartBuilder type(MediaType mediaType) {
        if (mediaType == null) {
            throw new NullPointerException("type == null");
        }
        if (!mediaType.type().equals("multipart")) {
            throw new IllegalArgumentException("multipart != " + mediaType);
        }
        this.type = mediaType;
        return this;
    }

    public MultipartBuilder addPart(RequestBody requestBody) {
        return addPart(null, requestBody);
    }

    public MultipartBuilder addPart(Headers headers, RequestBody requestBody) {
        if (requestBody == null) {
            throw new NullPointerException("body == null");
        }
        if (headers != null && headers.get("Content-Type") != null) {
            throw new IllegalArgumentException("Unexpected header: Content-Type");
        }
        if (headers != null && headers.get("Content-Length") != null) {
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }
        this.partHeaders.add(headers);
        this.partBodies.add(requestBody);
        return this;
    }

    public RequestBody build() {
        if (this.partHeaders.isEmpty()) {
            throw new IllegalStateException("Multipart body must have at least one part.");
        }
        return new MultipartRequestBody(this.type, this.boundary, this.partHeaders, this.partBodies);
    }

    private static final class MultipartRequestBody extends RequestBody {
        private final String boundary;
        private final MediaType contentType;
        private final List<RequestBody> partBodies;
        private final List<Headers> partHeaders;

        public MultipartRequestBody(MediaType mediaType, String str, List<Headers> list, List<RequestBody> list2) {
            if (mediaType == null) {
                throw new NullPointerException("type == null");
            }
            this.boundary = str;
            this.contentType = MediaType.parse(mediaType + "; boundary=" + str);
            this.partHeaders = Util.immutableList(list);
            this.partBodies = Util.immutableList(list2);
        }

        @Override // com.squareup.okhttp.RequestBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override // com.squareup.okhttp.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            byte[] bytes = this.boundary.getBytes("UTF-8");
            int i = 0;
            boolean z = true;
            while (i < this.partHeaders.size()) {
                Headers headers = this.partHeaders.get(i);
                RequestBody requestBody = this.partBodies.get(i);
                writeBoundary(bufferedSink, bytes, z, false);
                writePart(bufferedSink, headers, requestBody);
                i++;
                z = false;
            }
            writeBoundary(bufferedSink, bytes, false, true);
        }

        private static void writeBoundary(BufferedSink bufferedSink, byte[] bArr, boolean z, boolean z2) throws IOException {
            if (!z) {
                bufferedSink.writeUtf8("\r\n");
            }
            bufferedSink.writeUtf8("--");
            bufferedSink.write(bArr);
            if (z2) {
                bufferedSink.writeUtf8("--");
            } else {
                bufferedSink.writeUtf8("\r\n");
            }
        }

        private void writePart(BufferedSink bufferedSink, Headers headers, RequestBody requestBody) throws IOException {
            if (headers != null) {
                for (int i = 0; i < headers.size(); i++) {
                    bufferedSink.writeUtf8(headers.name(i)).writeUtf8(": ").writeUtf8(headers.value(i)).writeUtf8("\r\n");
                }
            }
            MediaType mediaTypeContentType = requestBody.contentType();
            if (mediaTypeContentType != null) {
                bufferedSink.writeUtf8("Content-Type: ").writeUtf8(mediaTypeContentType.toString()).writeUtf8("\r\n");
            }
            long jContentLength = requestBody.contentLength();
            if (jContentLength != -1) {
                bufferedSink.writeUtf8("Content-Length: ").writeUtf8(Long.toString(jContentLength)).writeUtf8("\r\n");
            }
            bufferedSink.writeUtf8("\r\n");
            requestBody.writeTo(bufferedSink);
        }
    }
}
