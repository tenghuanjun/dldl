package com.squareup.okhttp.internal.spdy;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class SpdyStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    long bytesLeftInWriteWindow;
    private final SpdyConnection connection;
    private final int id;
    private final List<Header> requestHeaders;
    private List<Header> responseHeaders;
    final SpdyDataSink sink;
    private final SpdyDataSource source;
    long unacknowledgedBytesRead = 0;
    private long readTimeoutMillis = 0;
    private final SpdyTimeout readTimeout = new SpdyTimeout();
    private final SpdyTimeout writeTimeout = new SpdyTimeout();
    private ErrorCode errorCode = null;

    SpdyStream(int i, SpdyConnection spdyConnection, boolean z, boolean z2, List<Header> list) {
        if (spdyConnection == null) {
            throw new NullPointerException("connection == null");
        }
        if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        }
        this.id = i;
        this.connection = spdyConnection;
        this.bytesLeftInWriteWindow = spdyConnection.peerSettings.getInitialWindowSize(65536);
        this.source = new SpdyDataSource(spdyConnection.okHttpSettings.getInitialWindowSize(65536));
        this.sink = new SpdyDataSink();
        this.source.finished = z2;
        this.sink.finished = z;
        this.requestHeaders = list;
    }

    public int getId() {
        return this.id;
    }

    public synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        if ((this.source.finished || this.source.closed) && (this.sink.finished || this.sink.closed)) {
            if (this.responseHeaders != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isLocallyInitiated() {
        return this.connection.client == ((this.id & 1) == 1);
    }

    public SpdyConnection getConnection() {
        return this.connection;
    }

    public List<Header> getRequestHeaders() {
        return this.requestHeaders;
    }

    public synchronized List<Header> getResponseHeaders() throws IOException {
        this.readTimeout.enter();
        while (this.responseHeaders == null && this.errorCode == null) {
            try {
                waitForIo();
            } catch (Throwable th) {
                this.readTimeout.exitAndThrowIfTimedOut();
                throw th;
            }
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        if (this.responseHeaders == null) {
            throw new IOException("stream was reset: " + this.errorCode);
        }
        return this.responseHeaders;
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public void reply(List<Header> list, boolean z) throws IOException {
        boolean z2 = false;
        synchronized (this) {
            try {
                if (list == null) {
                    throw new NullPointerException("responseHeaders == null");
                }
                if (this.responseHeaders != null) {
                    throw new IllegalStateException("reply already sent");
                }
                this.responseHeaders = list;
                if (!z) {
                    this.sink.finished = true;
                    z2 = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.connection.writeSynReply(this.id, z2, list);
        if (z2) {
            this.connection.flush();
        }
    }

    public Timeout readTimeout() {
        return this.readTimeout;
    }

    public Timeout writeTimeout() {
        return this.writeTimeout;
    }

    public Source getSource() {
        return this.source;
    }

    public Sink getSink() {
        synchronized (this) {
            if (this.responseHeaders == null && !isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.sink;
    }

    public void close(ErrorCode errorCode) throws IOException {
        if (closeInternal(errorCode)) {
            this.connection.writeSynReset(this.id, errorCode);
        }
    }

    public void closeLater(ErrorCode errorCode) {
        if (closeInternal(errorCode)) {
            this.connection.writeSynResetLater(this.id, errorCode);
        }
    }

    private boolean closeInternal(ErrorCode errorCode) {
        synchronized (this) {
            if (this.errorCode != null) {
                return false;
            }
            if (this.source.finished && this.sink.finished) {
                return false;
            }
            this.errorCode = errorCode;
            notifyAll();
            this.connection.removeStream(this.id);
            return true;
        }
    }

    void receiveHeaders(List<Header> list, HeadersMode headersMode) {
        ErrorCode errorCode = null;
        boolean zIsOpen = true;
        synchronized (this) {
            if (this.responseHeaders == null) {
                if (headersMode.failIfHeadersAbsent()) {
                    errorCode = ErrorCode.PROTOCOL_ERROR;
                } else {
                    this.responseHeaders = list;
                    zIsOpen = isOpen();
                    notifyAll();
                }
            } else if (headersMode.failIfHeadersPresent()) {
                errorCode = ErrorCode.STREAM_IN_USE;
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.responseHeaders);
                arrayList.addAll(list);
                this.responseHeaders = arrayList;
            }
        }
        if (errorCode != null) {
            closeLater(errorCode);
        } else {
            if (zIsOpen) {
                return;
            }
            this.connection.removeStream(this.id);
        }
    }

    void receiveData(BufferedSource bufferedSource, int i) throws IOException {
        this.source.receive(bufferedSource, i);
    }

    void receiveFin() {
        boolean zIsOpen;
        synchronized (this) {
            this.source.finished = true;
            zIsOpen = isOpen();
            notifyAll();
        }
        if (zIsOpen) {
            return;
        }
        this.connection.removeStream(this.id);
    }

    synchronized void receiveRstStream(ErrorCode errorCode) {
        if (this.errorCode == null) {
            this.errorCode = errorCode;
            notifyAll();
        }
    }

    private final class SpdyDataSource implements Source {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean closed;
        private boolean finished;
        private final long maxByteCount;
        private final Buffer readBuffer;
        private final Buffer receiveBuffer;

        private SpdyDataSource(long j) {
            this.receiveBuffer = new Buffer();
            this.readBuffer = new Buffer();
            this.maxByteCount = j;
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            synchronized (SpdyStream.this) {
                waitUntilReadable();
                checkNotClosed();
                if (this.readBuffer.size() == 0) {
                    return -1L;
                }
                long j2 = this.readBuffer.read(buffer, Math.min(j, this.readBuffer.size()));
                SpdyStream.this.unacknowledgedBytesRead += j2;
                if (SpdyStream.this.unacknowledgedBytesRead >= SpdyStream.this.connection.okHttpSettings.getInitialWindowSize(65536) / 2) {
                    SpdyStream.this.connection.writeWindowUpdateLater(SpdyStream.this.id, SpdyStream.this.unacknowledgedBytesRead);
                    SpdyStream.this.unacknowledgedBytesRead = 0L;
                }
                synchronized (SpdyStream.this.connection) {
                    SpdyStream.this.connection.unacknowledgedBytesRead += j2;
                    if (SpdyStream.this.connection.unacknowledgedBytesRead >= SpdyStream.this.connection.okHttpSettings.getInitialWindowSize(65536) / 2) {
                        SpdyStream.this.connection.writeWindowUpdateLater(0, SpdyStream.this.connection.unacknowledgedBytesRead);
                        SpdyStream.this.connection.unacknowledgedBytesRead = 0L;
                    }
                }
                return j2;
            }
        }

        private void waitUntilReadable() throws IOException {
            SpdyStream.this.readTimeout.enter();
            while (this.readBuffer.size() == 0 && !this.finished && !this.closed && SpdyStream.this.errorCode == null) {
                try {
                    SpdyStream.this.waitForIo();
                } finally {
                    SpdyStream.this.readTimeout.exitAndThrowIfTimedOut();
                }
            }
        }

        void receive(BufferedSource bufferedSource, long j) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            while (j > 0) {
                synchronized (SpdyStream.this) {
                    z = this.finished;
                    z2 = true;
                    z3 = this.readBuffer.size() + j > this.maxByteCount;
                }
                if (z3) {
                    bufferedSource.skip(j);
                    SpdyStream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    bufferedSource.skip(j);
                    return;
                }
                long j2 = bufferedSource.read(this.receiveBuffer, j);
                if (j2 == -1) {
                    throw new EOFException();
                }
                j -= j2;
                synchronized (SpdyStream.this) {
                    if (this.readBuffer.size() != 0) {
                        z2 = false;
                    }
                    this.readBuffer.writeAll(this.receiveBuffer);
                    if (z2) {
                        SpdyStream.this.notifyAll();
                    }
                }
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return SpdyStream.this.readTimeout;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (SpdyStream.this) {
                this.closed = true;
                this.readBuffer.clear();
                SpdyStream.this.notifyAll();
            }
            SpdyStream.this.cancelStreamIfNecessary();
        }

        private void checkNotClosed() throws IOException {
            if (!this.closed) {
                if (SpdyStream.this.errorCode == null) {
                    return;
                }
                throw new IOException("stream was reset: " + SpdyStream.this.errorCode);
            }
            throw new IOException("stream closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelStreamIfNecessary() throws IOException {
        boolean z;
        boolean zIsOpen;
        synchronized (this) {
            z = !this.source.finished && this.source.closed && (this.sink.finished || this.sink.closed);
            zIsOpen = isOpen();
        }
        if (z) {
            close(ErrorCode.CANCEL);
        } else {
            if (zIsOpen) {
                return;
            }
            this.connection.removeStream(this.id);
        }
    }

    final class SpdyDataSink implements Sink {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean closed;
        private boolean finished;

        SpdyDataSink() {
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            long jMin;
            while (j > 0) {
                synchronized (SpdyStream.this) {
                    SpdyStream.this.writeTimeout.enter();
                    while (SpdyStream.this.bytesLeftInWriteWindow <= 0 && !this.finished && !this.closed && SpdyStream.this.errorCode == null) {
                        try {
                            SpdyStream.this.waitForIo();
                        } finally {
                        }
                    }
                    SpdyStream.this.writeTimeout.exitAndThrowIfTimedOut();
                    SpdyStream.this.checkOutNotClosed();
                    jMin = Math.min(SpdyStream.this.bytesLeftInWriteWindow, j);
                    SpdyStream.this.bytesLeftInWriteWindow -= jMin;
                }
                j -= jMin;
                SpdyStream.this.connection.writeData(SpdyStream.this.id, false, buffer, jMin);
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            synchronized (SpdyStream.this) {
                SpdyStream.this.checkOutNotClosed();
            }
            SpdyStream.this.connection.flush();
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return SpdyStream.this.writeTimeout;
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (SpdyStream.this) {
                if (this.closed) {
                    return;
                }
                if (!SpdyStream.this.sink.finished) {
                    SpdyStream.this.connection.writeData(SpdyStream.this.id, true, null, 0L);
                }
                synchronized (SpdyStream.this) {
                    this.closed = true;
                }
                SpdyStream.this.connection.flush();
                SpdyStream.this.cancelStreamIfNecessary();
            }
        }
    }

    void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkOutNotClosed() throws IOException {
        if (!this.sink.closed) {
            if (this.sink.finished) {
                throw new IOException("stream finished");
            }
            if (this.errorCode == null) {
                return;
            }
            throw new IOException("stream was reset: " + this.errorCode);
        }
        throw new IOException("stream closed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForIo() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }

    class SpdyTimeout extends AsyncTimeout {
        SpdyTimeout() {
        }

        @Override // okio.AsyncTimeout
        protected void timedOut() {
            SpdyStream.this.closeLater(ErrorCode.CANCEL);
        }

        public void exitAndThrowIfTimedOut() throws InterruptedIOException {
            if (exit()) {
                throw new InterruptedIOException("timeout");
            }
        }
    }
}
