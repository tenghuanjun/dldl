package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.spdy.ErrorCode;
import com.squareup.okhttp.internal.spdy.Header;
import com.squareup.okhttp.internal.spdy.SpdyConnection;
import com.squareup.okhttp.internal.spdy.SpdyStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.ByteString;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class SpdyTransport implements Transport {
    private final HttpEngine httpEngine;
    private final SpdyConnection spdyConnection;
    private SpdyStream stream;
    private static final List<ByteString> SPDY_3_PROHIBITED_HEADERS = Util.immutableList(ByteString.encodeUtf8("connection"), ByteString.encodeUtf8("host"), ByteString.encodeUtf8("keep-alive"), ByteString.encodeUtf8("proxy-connection"), ByteString.encodeUtf8("transfer-encoding"));
    private static final List<ByteString> HTTP_2_PROHIBITED_HEADERS = Util.immutableList(ByteString.encodeUtf8("connection"), ByteString.encodeUtf8("host"), ByteString.encodeUtf8("keep-alive"), ByteString.encodeUtf8("proxy-connection"), ByteString.encodeUtf8("te"), ByteString.encodeUtf8("transfer-encoding"), ByteString.encodeUtf8("encoding"), ByteString.encodeUtf8("upgrade"));

    @Override // com.squareup.okhttp.internal.http.Transport
    public boolean canReuseConnection() {
        return true;
    }

    @Override // com.squareup.okhttp.internal.http.Transport
    public void emptyTransferStream() {
    }

    @Override // com.squareup.okhttp.internal.http.Transport
    public void releaseConnectionOnIdle() {
    }

    public SpdyTransport(HttpEngine httpEngine, SpdyConnection spdyConnection) {
        this.httpEngine = httpEngine;
        this.spdyConnection = spdyConnection;
    }

    @Override // com.squareup.okhttp.internal.http.Transport
    public Sink createRequestBody(Request request) throws IOException {
        writeRequestHeaders(request);
        return this.stream.getSink();
    }

    @Override // com.squareup.okhttp.internal.http.Transport
    public void writeRequestHeaders(Request request) throws IOException {
        if (this.stream != null) {
            return;
        }
        this.httpEngine.writingRequestHeaders();
        boolean zHasRequestBody = this.httpEngine.hasRequestBody();
        String strVersion = RequestLine.version(this.httpEngine.getConnection().getProtocol());
        SpdyConnection spdyConnection = this.spdyConnection;
        SpdyStream spdyStreamNewStream = spdyConnection.newStream(writeNameValueBlock(request, spdyConnection.getProtocol(), strVersion), zHasRequestBody, true);
        this.stream = spdyStreamNewStream;
        spdyStreamNewStream.readTimeout().timeout(this.httpEngine.client.getReadTimeout(), TimeUnit.MILLISECONDS);
    }

    @Override // com.squareup.okhttp.internal.http.Transport
    public void writeRequestBody(RetryableSink retryableSink) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.squareup.okhttp.internal.http.Transport
    public void flushRequest() throws IOException {
        this.stream.getSink().close();
    }

    @Override // com.squareup.okhttp.internal.http.Transport
    public Response.Builder readResponseHeaders() throws IOException {
        return readNameValueBlock(this.stream.getResponseHeaders(), this.spdyConnection.getProtocol());
    }

    public static List<Header> writeNameValueBlock(Request request, Protocol protocol, String str) {
        Headers headers = request.headers();
        ArrayList arrayList = new ArrayList(headers.size() + 10);
        arrayList.add(new Header(Header.TARGET_METHOD, request.method()));
        arrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url())));
        String strHostHeader = HttpEngine.hostHeader(request.url());
        if (Protocol.SPDY_3 == protocol) {
            arrayList.add(new Header(Header.VERSION, str));
            arrayList.add(new Header(Header.TARGET_HOST, strHostHeader));
        } else if (Protocol.HTTP_2 == protocol) {
            arrayList.add(new Header(Header.TARGET_AUTHORITY, strHostHeader));
        } else {
            throw new AssertionError();
        }
        arrayList.add(new Header(Header.TARGET_SCHEME, request.url().getProtocol()));
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (int i = 0; i < headers.size(); i++) {
            ByteString byteStringEncodeUtf8 = ByteString.encodeUtf8(headers.name(i).toLowerCase(Locale.US));
            String strValue = headers.value(i);
            if (!isProhibitedHeader(protocol, byteStringEncodeUtf8) && !byteStringEncodeUtf8.equals(Header.TARGET_METHOD) && !byteStringEncodeUtf8.equals(Header.TARGET_PATH) && !byteStringEncodeUtf8.equals(Header.TARGET_SCHEME) && !byteStringEncodeUtf8.equals(Header.TARGET_AUTHORITY) && !byteStringEncodeUtf8.equals(Header.TARGET_HOST) && !byteStringEncodeUtf8.equals(Header.VERSION)) {
                if (linkedHashSet.add(byteStringEncodeUtf8)) {
                    arrayList.add(new Header(byteStringEncodeUtf8, strValue));
                } else {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            break;
                        }
                        if (((Header) arrayList.get(i2)).name.equals(byteStringEncodeUtf8)) {
                            arrayList.set(i2, new Header(byteStringEncodeUtf8, joinOnNull(((Header) arrayList.get(i2)).value.utf8(), strValue)));
                            break;
                        }
                        i2++;
                    }
                }
            }
        }
        return arrayList;
    }

    private static String joinOnNull(String str, String str2) {
        return str + (char) 0 + str2;
    }

    public static Response.Builder readNameValueBlock(List<Header> list, Protocol protocol) throws IOException {
        Headers.Builder builder = new Headers.Builder();
        builder.set(OkHeaders.SELECTED_PROTOCOL, protocol.toString());
        String str = null;
        String str2 = "HTTP/1.1";
        for (int i = 0; i < list.size(); i++) {
            ByteString byteString = list.get(i).name;
            String strUtf8 = list.get(i).value.utf8();
            int i2 = 0;
            while (i2 < strUtf8.length()) {
                int iIndexOf = strUtf8.indexOf(0, i2);
                if (iIndexOf == -1) {
                    iIndexOf = strUtf8.length();
                }
                String strSubstring = strUtf8.substring(i2, iIndexOf);
                if (byteString.equals(Header.RESPONSE_STATUS)) {
                    str = strSubstring;
                } else if (byteString.equals(Header.VERSION)) {
                    str2 = strSubstring;
                } else if (!isProhibitedHeader(protocol, byteString)) {
                    builder.add(byteString.utf8(), strSubstring);
                }
                i2 = iIndexOf + 1;
            }
        }
        if (str == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        if (str2 == null) {
            throw new ProtocolException("Expected ':version' header not present");
        }
        StatusLine statusLine = StatusLine.parse(str2 + " " + str);
        return new Response.Builder().protocol(statusLine.protocol).code(statusLine.code).message(statusLine.message).headers(builder.build());
    }

    @Override // com.squareup.okhttp.internal.http.Transport
    public Source getTransferStream(CacheRequest cacheRequest) throws IOException {
        return new SpdySource(this.stream, cacheRequest);
    }

    @Override // com.squareup.okhttp.internal.http.Transport
    public void disconnect(HttpEngine httpEngine) throws IOException {
        this.stream.close(ErrorCode.CANCEL);
    }

    private static boolean isProhibitedHeader(Protocol protocol, ByteString byteString) {
        if (protocol == Protocol.SPDY_3) {
            return SPDY_3_PROHIBITED_HEADERS.contains(byteString);
        }
        if (protocol == Protocol.HTTP_2) {
            return HTTP_2_PROHIBITED_HEADERS.contains(byteString);
        }
        throw new AssertionError(protocol);
    }

    private static class SpdySource implements Source {
        private final OutputStream cacheBody;
        private final CacheRequest cacheRequest;
        private boolean closed;
        private boolean inputExhausted;
        private final Source source;
        private final SpdyStream stream;

        SpdySource(SpdyStream spdyStream, CacheRequest cacheRequest) throws IOException {
            this.stream = spdyStream;
            this.source = spdyStream.getSource();
            OutputStream body = cacheRequest != null ? cacheRequest.getBody() : null;
            cacheRequest = body == null ? null : cacheRequest;
            this.cacheBody = body;
            this.cacheRequest = cacheRequest;
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (this.inputExhausted) {
                return -1L;
            }
            long j2 = this.source.read(buffer, j);
            if (j2 == -1) {
                this.inputExhausted = true;
                if (this.cacheRequest != null) {
                    this.cacheBody.close();
                }
                return -1L;
            }
            OutputStream outputStream = this.cacheBody;
            if (outputStream != null) {
                buffer.copyTo(outputStream, buffer.size() - j2, j2);
            }
            return j2;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.source.timeout();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (!this.inputExhausted && this.cacheBody != null) {
                discardStream();
            }
            this.closed = true;
            if (this.inputExhausted) {
                return;
            }
            this.stream.closeLater(ErrorCode.CANCEL);
            CacheRequest cacheRequest = this.cacheRequest;
            if (cacheRequest != null) {
                cacheRequest.abort();
            }
        }

        private boolean discardStream() {
            boolean z;
            long jTimeoutNanos = this.stream.readTimeout().timeoutNanos();
            this.stream.readTimeout().timeout(100L, TimeUnit.MILLISECONDS);
            try {
                Util.skipAll(this, 100);
                z = true;
            } catch (IOException unused) {
                z = false;
            } catch (Throwable th) {
                this.stream.readTimeout().timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
                throw th;
            }
            this.stream.readTimeout().timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
            return z;
        }
    }
}
