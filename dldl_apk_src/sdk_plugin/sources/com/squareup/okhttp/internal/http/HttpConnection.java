package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Connection;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class HttpConnection {
    private static final String CRLF = "\r\n";
    private static final int ON_IDLE_CLOSE = 2;
    private static final int ON_IDLE_HOLD = 0;
    private static final int ON_IDLE_POOL = 1;
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    private final Connection connection;
    private final ConnectionPool pool;
    private final BufferedSink sink;
    private final Socket socket;
    private final BufferedSource source;
    private static final byte[] HEX_DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    private static final byte[] FINAL_CHUNK = {48, 13, 10, 13, 10};
    private int state = 0;
    private int onIdle = 0;

    public HttpConnection(ConnectionPool connectionPool, Connection connection, Socket socket) throws IOException {
        this.pool = connectionPool;
        this.connection = connection;
        this.socket = socket;
        this.source = Okio.buffer(Okio.source(socket));
        this.sink = Okio.buffer(Okio.sink(socket));
    }

    public void setTimeouts(int i, int i2) {
        if (i != 0) {
            this.source.timeout().timeout(i, TimeUnit.MILLISECONDS);
        }
        if (i2 != 0) {
            this.sink.timeout().timeout(i2, TimeUnit.MILLISECONDS);
        }
    }

    public void poolOnIdle() {
        this.onIdle = 1;
        if (this.state == 0) {
            this.onIdle = 0;
            Internal.instance.recycle(this.pool, this.connection);
        }
    }

    public void closeOnIdle() throws IOException {
        this.onIdle = 2;
        if (this.state == 0) {
            this.state = 6;
            this.connection.getSocket().close();
        }
    }

    public boolean isClosed() {
        return this.state == 6;
    }

    public void closeIfOwnedBy(Object obj) throws IOException {
        Internal.instance.closeIfOwnedBy(this.connection, obj);
    }

    public void flush() throws IOException {
        this.sink.flush();
    }

    public long bufferSize() {
        return this.source.buffer().size();
    }

    public boolean isReadable() {
        try {
            int soTimeout = this.socket.getSoTimeout();
            try {
                this.socket.setSoTimeout(1);
                return !this.source.exhausted();
            } finally {
                this.socket.setSoTimeout(soTimeout);
            }
        } catch (SocketTimeoutException unused) {
            return true;
        } catch (IOException unused2) {
            return false;
        }
    }

    public void writeRequest(Headers headers, String str) throws IOException {
        if (this.state != 0) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.sink.writeUtf8(str).writeUtf8(CRLF);
        for (int i = 0; i < headers.size(); i++) {
            this.sink.writeUtf8(headers.name(i)).writeUtf8(": ").writeUtf8(headers.value(i)).writeUtf8(CRLF);
        }
        this.sink.writeUtf8(CRLF);
        this.state = 1;
    }

    public Response.Builder readResponse() throws IOException {
        StatusLine statusLine;
        Response.Builder builderMessage;
        int i = this.state;
        if (i != 1 && i != 3) {
            throw new IllegalStateException("state: " + this.state);
        }
        do {
            statusLine = StatusLine.parse(this.source.readUtf8LineStrict());
            builderMessage = new Response.Builder().protocol(statusLine.protocol).code(statusLine.code).message(statusLine.message);
            Headers.Builder builder = new Headers.Builder();
            readHeaders(builder);
            builder.add(OkHeaders.SELECTED_PROTOCOL, statusLine.protocol.toString());
            builderMessage.headers(builder.build());
        } while (statusLine.code == 100);
        this.state = 4;
        return builderMessage;
    }

    public void readHeaders(Headers.Builder builder) throws IOException {
        while (true) {
            String utf8LineStrict = this.source.readUtf8LineStrict();
            if (utf8LineStrict.length() == 0) {
                return;
            } else {
                Internal.instance.addLine(builder, utf8LineStrict);
            }
        }
    }

    public boolean discard(Source source, int i) {
        try {
            int soTimeout = this.socket.getSoTimeout();
            this.socket.setSoTimeout(i);
            try {
                return Util.skipAll(source, i);
            } finally {
                this.socket.setSoTimeout(soTimeout);
            }
        } catch (IOException unused) {
            return false;
        }
    }

    public Sink newChunkedSink() {
        if (this.state != 1) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 2;
        return new ChunkedSink();
    }

    public Sink newFixedLengthSink(long j) {
        if (this.state != 1) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 2;
        return new FixedLengthSink(j);
    }

    public void writeRequestBody(RetryableSink retryableSink) throws IOException {
        if (this.state != 1) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 3;
        retryableSink.writeToSocket(this.sink);
    }

    public Source newFixedLengthSource(CacheRequest cacheRequest, long j) throws IOException {
        if (this.state != 4) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 5;
        return new FixedLengthSource(cacheRequest, j);
    }

    public void emptyResponseBody() throws IOException {
        newFixedLengthSource(null, 0L);
    }

    public Source newChunkedSource(CacheRequest cacheRequest, HttpEngine httpEngine) throws IOException {
        if (this.state != 4) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 5;
        return new ChunkedSource(cacheRequest, httpEngine);
    }

    public Source newUnknownLengthSource(CacheRequest cacheRequest) throws IOException {
        if (this.state != 4) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 5;
        return new UnknownLengthSource(cacheRequest);
    }

    private final class FixedLengthSink implements Sink {
        private long bytesRemaining;
        private boolean closed;

        private FixedLengthSink(long j) {
            this.bytesRemaining = j;
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return HttpConnection.this.sink.timeout();
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            Util.checkOffsetAndCount(buffer.size(), 0L, j);
            if (j <= this.bytesRemaining) {
                HttpConnection.this.sink.write(buffer, j);
                this.bytesRemaining -= j;
                return;
            }
            throw new ProtocolException("expected " + this.bytesRemaining + " bytes but received " + j);
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (this.closed) {
                return;
            }
            HttpConnection.this.sink.flush();
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.bytesRemaining <= 0) {
                HttpConnection.this.state = 3;
                return;
            }
            throw new ProtocolException("unexpected end of stream");
        }
    }

    private final class ChunkedSink implements Sink {
        private boolean closed;
        private final byte[] hex;

        private ChunkedSink() {
            this.hex = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 10};
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return HttpConnection.this.sink.timeout();
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (j == 0) {
                return;
            }
            writeHex(j);
            HttpConnection.this.sink.write(buffer, j);
            HttpConnection.this.sink.writeUtf8(HttpConnection.CRLF);
        }

        @Override // okio.Sink, java.io.Flushable
        public synchronized void flush() throws IOException {
            if (this.closed) {
                return;
            }
            HttpConnection.this.sink.flush();
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            HttpConnection.this.sink.write(HttpConnection.FINAL_CHUNK);
            HttpConnection.this.state = 3;
        }

        private void writeHex(long j) throws IOException {
            int i = 16;
            do {
                i--;
                this.hex[i] = HttpConnection.HEX_DIGITS[(int) (15 & j)];
                j >>>= 4;
            } while (j != 0);
            BufferedSink bufferedSink = HttpConnection.this.sink;
            byte[] bArr = this.hex;
            bufferedSink.write(bArr, i, bArr.length - i);
        }
    }

    private class AbstractSource {
        protected final OutputStream cacheBody;
        private final CacheRequest cacheRequest;
        protected boolean closed;

        AbstractSource(CacheRequest cacheRequest) throws IOException {
            OutputStream body = cacheRequest != null ? cacheRequest.getBody() : null;
            cacheRequest = body == null ? null : cacheRequest;
            this.cacheBody = body;
            this.cacheRequest = cacheRequest;
        }

        protected final void cacheWrite(Buffer buffer, long j) throws IOException {
            OutputStream outputStream = this.cacheBody;
            if (outputStream != null) {
                buffer.copyTo(outputStream, buffer.size() - j, j);
            }
        }

        protected final void endOfInput(boolean z) throws IOException {
            if (HttpConnection.this.state != 5) {
                throw new IllegalStateException("state: " + HttpConnection.this.state);
            }
            if (this.cacheRequest != null) {
                this.cacheBody.close();
            }
            HttpConnection.this.state = 0;
            if (!z || HttpConnection.this.onIdle != 1) {
                if (HttpConnection.this.onIdle == 2) {
                    HttpConnection.this.state = 6;
                    HttpConnection.this.connection.getSocket().close();
                    return;
                }
                return;
            }
            HttpConnection.this.onIdle = 0;
            Internal.instance.recycle(HttpConnection.this.pool, HttpConnection.this.connection);
        }

        protected final void unexpectedEndOfInput() {
            CacheRequest cacheRequest = this.cacheRequest;
            if (cacheRequest != null) {
                cacheRequest.abort();
            }
            Util.closeQuietly(HttpConnection.this.connection.getSocket());
            HttpConnection.this.state = 6;
        }
    }

    private class FixedLengthSource extends AbstractSource implements Source {
        private long bytesRemaining;

        public FixedLengthSource(CacheRequest cacheRequest, long j) throws IOException {
            super(cacheRequest);
            this.bytesRemaining = j;
            if (j == 0) {
                endOfInput(true);
            }
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (this.bytesRemaining == 0) {
                return -1L;
            }
            long j2 = HttpConnection.this.source.read(buffer, Math.min(this.bytesRemaining, j));
            if (j2 == -1) {
                unexpectedEndOfInput();
                throw new ProtocolException("unexpected end of stream");
            }
            this.bytesRemaining -= j2;
            cacheWrite(buffer, j2);
            if (this.bytesRemaining == 0) {
                endOfInput(true);
            }
            return j2;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return HttpConnection.this.source.timeout();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (this.bytesRemaining != 0 && !HttpConnection.this.discard(this, 100)) {
                unexpectedEndOfInput();
            }
            this.closed = true;
        }
    }

    private class ChunkedSource extends AbstractSource implements Source {
        private static final int NO_CHUNK_YET = -1;
        private int bytesRemainingInChunk;
        private boolean hasMoreChunks;
        private final HttpEngine httpEngine;

        ChunkedSource(CacheRequest cacheRequest, HttpEngine httpEngine) throws IOException {
            super(cacheRequest);
            this.bytesRemainingInChunk = -1;
            this.hasMoreChunks = true;
            this.httpEngine = httpEngine;
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (!this.hasMoreChunks) {
                return -1L;
            }
            int i = this.bytesRemainingInChunk;
            if (i == 0 || i == -1) {
                readChunkSize();
                if (!this.hasMoreChunks) {
                    return -1L;
                }
            }
            long j2 = HttpConnection.this.source.read(buffer, Math.min(j, this.bytesRemainingInChunk));
            if (j2 == -1) {
                unexpectedEndOfInput();
                throw new IOException("unexpected end of stream");
            }
            this.bytesRemainingInChunk = (int) (((long) this.bytesRemainingInChunk) - j2);
            cacheWrite(buffer, j2);
            return j2;
        }

        private void readChunkSize() throws IOException {
            if (this.bytesRemainingInChunk != -1) {
                HttpConnection.this.source.readUtf8LineStrict();
            }
            String utf8LineStrict = HttpConnection.this.source.readUtf8LineStrict();
            int iIndexOf = utf8LineStrict.indexOf(";");
            if (iIndexOf != -1) {
                utf8LineStrict = utf8LineStrict.substring(0, iIndexOf);
            }
            try {
                int i = Integer.parseInt(utf8LineStrict.trim(), 16);
                this.bytesRemainingInChunk = i;
                if (i == 0) {
                    this.hasMoreChunks = false;
                    Headers.Builder builder = new Headers.Builder();
                    HttpConnection.this.readHeaders(builder);
                    this.httpEngine.receiveHeaders(builder.build());
                    endOfInput(true);
                }
            } catch (NumberFormatException unused) {
                throw new ProtocolException("Expected a hex chunk size but was " + utf8LineStrict);
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return HttpConnection.this.source.timeout();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (this.hasMoreChunks && !HttpConnection.this.discard(this, 100)) {
                unexpectedEndOfInput();
            }
            this.closed = true;
        }
    }

    class UnknownLengthSource extends AbstractSource implements Source {
        private boolean inputExhausted;

        UnknownLengthSource(CacheRequest cacheRequest) throws IOException {
            super(cacheRequest);
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
            long j2 = HttpConnection.this.source.read(buffer, j);
            if (j2 == -1) {
                this.inputExhausted = true;
                endOfInput(false);
                return -1L;
            }
            cacheWrite(buffer, j2);
            return j2;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return HttpConnection.this.source.timeout();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (!this.inputExhausted) {
                unexpectedEndOfInput();
            }
            this.closed = true;
        }
    }
}
