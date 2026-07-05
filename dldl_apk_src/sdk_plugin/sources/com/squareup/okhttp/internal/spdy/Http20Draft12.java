package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.spdy.FrameReader;
import com.squareup.okhttp.internal.spdy.HpackDraft07;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.UShort;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class Http20Draft12 implements Variant {
    static final byte FLAG_ACK = 1;
    static final byte FLAG_COMPRESSED = 32;
    static final byte FLAG_END_HEADERS = 4;
    static final byte FLAG_END_PUSH_PROMISE = 4;
    static final byte FLAG_END_SEGMENT = 2;
    static final byte FLAG_END_STREAM = 1;
    static final byte FLAG_NONE = 0;
    static final byte FLAG_PAD_HIGH = 16;
    static final byte FLAG_PAD_LOW = 8;
    static final byte FLAG_PRIORITY = 32;
    static final int MAX_FRAME_SIZE = 16383;
    static final byte TYPE_ALTSVC = 10;
    static final byte TYPE_BLOCKED = 11;
    static final byte TYPE_CONTINUATION = 9;
    static final byte TYPE_DATA = 0;
    static final byte TYPE_GOAWAY = 7;
    static final byte TYPE_HEADERS = 1;
    static final byte TYPE_PING = 6;
    static final byte TYPE_PRIORITY = 2;
    static final byte TYPE_PUSH_PROMISE = 5;
    static final byte TYPE_RST_STREAM = 3;
    static final byte TYPE_SETTINGS = 4;
    static final byte TYPE_WINDOW_UPDATE = 8;
    private static final Logger logger = Logger.getLogger(Http20Draft12.class.getName());
    private static final ByteString CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    @Override // com.squareup.okhttp.internal.spdy.Variant
    public int maxFrameSize() {
        return MAX_FRAME_SIZE;
    }

    @Override // com.squareup.okhttp.internal.spdy.Variant
    public Protocol getProtocol() {
        return Protocol.HTTP_2;
    }

    @Override // com.squareup.okhttp.internal.spdy.Variant
    public FrameReader newReader(BufferedSource bufferedSource, boolean z) {
        return new Reader(bufferedSource, 4096, z);
    }

    @Override // com.squareup.okhttp.internal.spdy.Variant
    public FrameWriter newWriter(BufferedSink bufferedSink, boolean z) {
        return new Writer(bufferedSink, z);
    }

    static final class Reader implements FrameReader {
        private final boolean client;
        private final ContinuationSource continuation;
        final HpackDraft07.Reader hpackReader;
        private final BufferedSource source;

        Reader(BufferedSource bufferedSource, int i, boolean z) {
            this.source = bufferedSource;
            this.client = z;
            ContinuationSource continuationSource = new ContinuationSource(bufferedSource);
            this.continuation = continuationSource;
            this.hpackReader = new HpackDraft07.Reader(i, continuationSource);
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameReader
        public void readConnectionPreface() throws IOException {
            if (this.client) {
                return;
            }
            ByteString byteString = this.source.readByteString(Http20Draft12.CONNECTION_PREFACE.size());
            if (Http20Draft12.logger.isLoggable(Level.FINE)) {
                Http20Draft12.logger.fine(String.format("<< CONNECTION %s", byteString.hex()));
            }
            if (!Http20Draft12.CONNECTION_PREFACE.equals(byteString)) {
                throw Http20Draft12.ioException("Expected a connection header but was %s", byteString.utf8());
            }
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameReader
        public boolean nextFrame(FrameReader.Handler handler) throws IOException {
            try {
                int i = this.source.readInt();
                short s = (short) ((1073676288 & i) >> 16);
                byte b = (byte) ((65280 & i) >> 8);
                byte b2 = (byte) (i & 255);
                int i2 = this.source.readInt() & Integer.MAX_VALUE;
                if (Http20Draft12.logger.isLoggable(Level.FINE)) {
                    Http20Draft12.logger.fine(FrameLogger.formatHeader(true, i2, s, b, b2));
                }
                switch (b) {
                    case 0:
                        readData(handler, s, b2, i2);
                        return true;
                    case 1:
                        readHeaders(handler, s, b2, i2);
                        return true;
                    case 2:
                        readPriority(handler, s, b2, i2);
                        return true;
                    case 3:
                        readRstStream(handler, s, b2, i2);
                        return true;
                    case 4:
                        readSettings(handler, s, b2, i2);
                        return true;
                    case 5:
                        readPushPromise(handler, s, b2, i2);
                        return true;
                    case 6:
                        readPing(handler, s, b2, i2);
                        return true;
                    case 7:
                        readGoAway(handler, s, b2, i2);
                        return true;
                    case 8:
                        readWindowUpdate(handler, s, b2, i2);
                        return true;
                    case 9:
                    default:
                        throw Http20Draft12.ioException("PROTOCOL_ERROR: unknown frame type %s", Byte.valueOf(b));
                    case 10:
                        readAlternateService(handler, s, b2, i2);
                        return true;
                    case 11:
                        if (s != 0) {
                            throw Http20Draft12.ioException("TYPE_BLOCKED length != 0: %s", Short.valueOf(s));
                        }
                        return true;
                }
            } catch (IOException unused) {
                return false;
            }
        }

        private void readHeaders(FrameReader.Handler handler, short s, byte b, int i) throws IOException {
            if (i != 0) {
                boolean z = (b & 1) != 0;
                short padding = Http20Draft12.readPadding(this.source, b);
                if ((b & 32) != 0) {
                    readPriority(handler, i);
                    s = (short) (s - 5);
                }
                handler.headers(false, z, i, -1, readHeaderBlock(Http20Draft12.lengthWithoutPadding(s, b, padding), padding, b, i), HeadersMode.HTTP_20_HEADERS);
                return;
            }
            throw Http20Draft12.ioException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
        }

        private List<Header> readHeaderBlock(short s, short s2, byte b, int i) throws IOException {
            ContinuationSource continuationSource = this.continuation;
            continuationSource.left = s;
            continuationSource.length = s;
            this.continuation.padding = s2;
            this.continuation.flags = b;
            this.continuation.streamId = i;
            this.hpackReader.readHeaders();
            this.hpackReader.emitReferenceSet();
            return this.hpackReader.getAndReset();
        }

        private void readData(FrameReader.Handler handler, short s, byte b, int i) throws IOException {
            boolean z = (b & 1) != 0;
            if (!((b & 32) != 0)) {
                short padding = Http20Draft12.readPadding(this.source, b);
                handler.data(z, i, this.source, Http20Draft12.lengthWithoutPadding(s, b, padding));
                this.source.skip(padding);
                return;
            }
            throw Http20Draft12.ioException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }

        private void readPriority(FrameReader.Handler handler, short s, byte b, int i) throws IOException {
            if (s != 5) {
                throw Http20Draft12.ioException("TYPE_PRIORITY length: %d != 5", Short.valueOf(s));
            }
            if (i == 0) {
                throw Http20Draft12.ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
            }
            readPriority(handler, i);
        }

        private void readPriority(FrameReader.Handler handler, int i) throws IOException {
            int i2 = this.source.readInt();
            handler.priority(i, i2 & Integer.MAX_VALUE, (this.source.readByte() & 255) + 1, (Integer.MIN_VALUE & i2) != 0);
        }

        private void readRstStream(FrameReader.Handler handler, short s, byte b, int i) throws IOException {
            if (s != 4) {
                throw Http20Draft12.ioException("TYPE_RST_STREAM length: %d != 4", Short.valueOf(s));
            }
            if (i == 0) {
                throw Http20Draft12.ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
            }
            int i2 = this.source.readInt();
            ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(i2);
            if (errorCodeFromHttp2 == null) {
                throw Http20Draft12.ioException("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(i2));
            }
            handler.rstStream(i, errorCodeFromHttp2);
        }

        private void readSettings(FrameReader.Handler handler, short s, byte b, int i) throws IOException {
            if (i != 0) {
                throw Http20Draft12.ioException("TYPE_SETTINGS streamId != 0", new Object[0]);
            }
            if ((b & 1) != 0) {
                if (s != 0) {
                    throw Http20Draft12.ioException("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                }
                handler.ackSettings();
                return;
            }
            if (s % 5 != 0) {
                throw Http20Draft12.ioException("TYPE_SETTINGS length %% 5 != 0: %s", Short.valueOf(s));
            }
            Settings settings = new Settings();
            for (int i2 = 0; i2 < s; i2 += 5) {
                byte b2 = this.source.readByte();
                int i3 = this.source.readInt();
                if (b2 != 1) {
                    if (b2 == 2) {
                        if (i3 != 0 && i3 != 1) {
                            throw Http20Draft12.ioException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                        }
                    } else if (b2 == 3) {
                        b2 = 4;
                    } else if (b2 == 4) {
                        b2 = 7;
                        if (i3 < 0) {
                            throw Http20Draft12.ioException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                        }
                    } else if (b2 != 5) {
                        throw Http20Draft12.ioException("PROTOCOL_ERROR invalid settings id: %s", Integer.valueOf(b2));
                    }
                }
                settings.set(b2, 0, i3);
            }
            handler.settings(false, settings);
            if (settings.getHeaderTableSize() >= 0) {
                this.hpackReader.maxHeaderTableByteCountSetting(settings.getHeaderTableSize());
            }
        }

        private void readPushPromise(FrameReader.Handler handler, short s, byte b, int i) throws IOException {
            if (i != 0) {
                handler.pushPromise(i, this.source.readInt() & Integer.MAX_VALUE, readHeaderBlock((short) (s - 4), Http20Draft12.readPadding(this.source, b), b, i));
                return;
            }
            throw Http20Draft12.ioException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }

        private void readPing(FrameReader.Handler handler, short s, byte b, int i) throws IOException {
            if (s != 8) {
                throw Http20Draft12.ioException("TYPE_PING length != 8: %s", Short.valueOf(s));
            }
            if (i != 0) {
                throw Http20Draft12.ioException("TYPE_PING streamId != 0", new Object[0]);
            }
            handler.ping((b & 1) != 0, this.source.readInt(), this.source.readInt());
        }

        private void readGoAway(FrameReader.Handler handler, short s, byte b, int i) throws IOException {
            if (s < 8) {
                throw Http20Draft12.ioException("TYPE_GOAWAY length < 8: %s", Short.valueOf(s));
            }
            if (i != 0) {
                throw Http20Draft12.ioException("TYPE_GOAWAY streamId != 0", new Object[0]);
            }
            int i2 = this.source.readInt();
            int i3 = this.source.readInt();
            int i4 = s - 8;
            ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(i3);
            if (errorCodeFromHttp2 == null) {
                throw Http20Draft12.ioException("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(i3));
            }
            ByteString byteString = ByteString.EMPTY;
            if (i4 > 0) {
                byteString = this.source.readByteString(i4);
            }
            handler.goAway(i2, errorCodeFromHttp2, byteString);
        }

        private void readWindowUpdate(FrameReader.Handler handler, short s, byte b, int i) throws IOException {
            if (s != 4) {
                throw Http20Draft12.ioException("TYPE_WINDOW_UPDATE length !=4: %s", Short.valueOf(s));
            }
            long j = ((long) this.source.readInt()) & 2147483647L;
            if (j == 0) {
                throw Http20Draft12.ioException("windowSizeIncrement was 0", Long.valueOf(j));
            }
            handler.windowUpdate(i, j);
        }

        private void readAlternateService(FrameReader.Handler handler, short s, byte b, int i) throws IOException {
            long j = ((long) this.source.readInt()) & 4294967295L;
            int i2 = this.source.readShort() & UShort.MAX_VALUE;
            this.source.readByte();
            handler.alternateService(i, this.source.readUtf8(((s - 9) - r1) - r2), this.source.readByteString(this.source.readByte() & 255), this.source.readUtf8(this.source.readByte() & 255), i2, j);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.source.close();
        }
    }

    static final class Writer implements FrameWriter {
        private final boolean client;
        private boolean closed;
        private final Buffer hpackBuffer;
        private final HpackDraft07.Writer hpackWriter;
        private final BufferedSink sink;

        Writer(BufferedSink bufferedSink, boolean z) {
            this.sink = bufferedSink;
            this.client = z;
            Buffer buffer = new Buffer();
            this.hpackBuffer = buffer;
            this.hpackWriter = new HpackDraft07.Writer(buffer);
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void flush() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            this.sink.flush();
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void ackSettings() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            frameHeader(0, 0, (byte) 4, (byte) 1);
            this.sink.flush();
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void connectionPreface() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            if (this.client) {
                if (Http20Draft12.logger.isLoggable(Level.FINE)) {
                    Http20Draft12.logger.fine(String.format(">> CONNECTION %s", Http20Draft12.CONNECTION_PREFACE.hex()));
                }
                this.sink.write(Http20Draft12.CONNECTION_PREFACE.toByteArray());
                this.sink.flush();
            }
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void synStream(boolean z, boolean z2, int i, int i2, List<Header> list) throws IOException {
            try {
                if (z2) {
                    throw new UnsupportedOperationException();
                }
                if (this.closed) {
                    throw new IOException("closed");
                }
                headers(z, i, list);
            } catch (Throwable th) {
                throw th;
            }
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void synReply(boolean z, int i, List<Header> list) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            headers(z, i, list);
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void headers(int i, List<Header> list) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            headers(false, i, list);
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void pushPromise(int i, int i2, List<Header> list) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            if (this.hpackBuffer.size() != 0) {
                throw new IllegalStateException();
            }
            this.hpackWriter.writeHeaders(list);
            long size = this.hpackBuffer.size();
            int iMin = (int) Math.min(16379L, size);
            long j = iMin;
            frameHeader(i, iMin + 4, (byte) 5, size == j ? (byte) 4 : (byte) 0);
            this.sink.writeInt(i2 & Integer.MAX_VALUE);
            this.sink.write(this.hpackBuffer, j);
            if (size > j) {
                writeContinuationFrames(i, size - j);
            }
        }

        void headers(boolean z, int i, List<Header> list) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            if (this.hpackBuffer.size() != 0) {
                throw new IllegalStateException();
            }
            this.hpackWriter.writeHeaders(list);
            long size = this.hpackBuffer.size();
            int iMin = (int) Math.min(16383L, size);
            long j = iMin;
            byte b = size == j ? (byte) 4 : (byte) 0;
            if (z) {
                b = (byte) (b | 1);
            }
            frameHeader(i, iMin, (byte) 1, b);
            this.sink.write(this.hpackBuffer, j);
            if (size > j) {
                writeContinuationFrames(i, size - j);
            }
        }

        private void writeContinuationFrames(int i, long j) throws IOException {
            while (j > 0) {
                int iMin = (int) Math.min(16383L, j);
                long j2 = iMin;
                j -= j2;
                frameHeader(i, iMin, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
                this.sink.write(this.hpackBuffer, j2);
            }
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void rstStream(int i, ErrorCode errorCode) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            if (errorCode.spdyRstCode == -1) {
                throw new IllegalArgumentException();
            }
            frameHeader(i, 4, (byte) 3, (byte) 0);
            this.sink.writeInt(errorCode.httpCode);
            this.sink.flush();
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void data(boolean z, int i, Buffer buffer) throws IOException {
            data(z, i, buffer, (int) buffer.size());
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void data(boolean z, int i, Buffer buffer, int i2) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            dataFrame(i, z ? (byte) 1 : (byte) 0, buffer, i2);
        }

        void dataFrame(int i, byte b, Buffer buffer, int i2) throws IOException {
            frameHeader(i, i2, (byte) 0, b);
            if (i2 > 0) {
                this.sink.write(buffer, i2);
            }
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void settings(Settings settings) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            int i = 0;
            frameHeader(0, settings.size() * 5, (byte) 4, (byte) 0);
            while (i < 10) {
                if (settings.isSet(i)) {
                    this.sink.writeByte(i == 4 ? 3 : i == 7 ? 4 : i);
                    this.sink.writeInt(settings.get(i));
                }
                i++;
            }
            this.sink.flush();
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void ping(boolean z, int i, int i2) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            frameHeader(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
            this.sink.writeInt(i);
            this.sink.writeInt(i2);
            this.sink.flush();
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void goAway(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            if (errorCode.httpCode == -1) {
                throw Http20Draft12.illegalArgument("errorCode.httpCode == -1", new Object[0]);
            }
            frameHeader(0, bArr.length + 8, (byte) 7, (byte) 0);
            this.sink.writeInt(i);
            this.sink.writeInt(errorCode.httpCode);
            if (bArr.length > 0) {
                this.sink.write(bArr);
            }
            this.sink.flush();
        }

        @Override // com.squareup.okhttp.internal.spdy.FrameWriter
        public synchronized void windowUpdate(int i, long j) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            if (j == 0 || j > 2147483647L) {
                throw Http20Draft12.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            }
            frameHeader(i, 4, (byte) 8, (byte) 0);
            this.sink.writeInt((int) j);
            this.sink.flush();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            this.closed = true;
            this.sink.close();
        }

        void frameHeader(int i, int i2, byte b, byte b2) throws IOException {
            if (Http20Draft12.logger.isLoggable(Level.FINE)) {
                Http20Draft12.logger.fine(FrameLogger.formatHeader(false, i, i2, b, b2));
            }
            if (i2 > Http20Draft12.MAX_FRAME_SIZE) {
                throw Http20Draft12.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(Http20Draft12.MAX_FRAME_SIZE), Integer.valueOf(i2));
            }
            if ((Integer.MIN_VALUE & i) != 0) {
                throw Http20Draft12.illegalArgument("reserved bit set: %s", Integer.valueOf(i));
            }
            this.sink.writeInt(((i2 & Http20Draft12.MAX_FRAME_SIZE) << 16) | ((b & 255) << 8) | (b2 & 255));
            this.sink.writeInt(i & Integer.MAX_VALUE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IllegalArgumentException illegalArgument(String str, Object... objArr) {
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IOException ioException(String str, Object... objArr) throws IOException {
        throw new IOException(String.format(str, objArr));
    }

    static final class ContinuationSource implements Source {
        byte flags;
        short left;
        short length;
        short padding;
        private final BufferedSource source;
        int streamId;

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public ContinuationSource(BufferedSource bufferedSource) {
            this.source = bufferedSource;
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            while (true) {
                short s = this.left;
                if (s == 0) {
                    this.source.skip(this.padding);
                    this.padding = (short) 0;
                    if ((this.flags & 4) != 0) {
                        return -1L;
                    }
                    readContinuationHeader();
                } else {
                    long j2 = this.source.read(buffer, Math.min(j, s));
                    if (j2 == -1) {
                        return -1L;
                    }
                    this.left = (short) (((long) this.left) - j2);
                    return j2;
                }
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.source.timeout();
        }

        private void readContinuationHeader() throws IOException {
            int i = this.streamId;
            int i2 = this.source.readInt();
            int i3 = this.source.readInt();
            this.length = (short) ((1073676288 & i2) >> 16);
            byte b = (byte) ((65280 & i2) >> 8);
            this.flags = (byte) (i2 & 255);
            if (Http20Draft12.logger.isLoggable(Level.FINE)) {
                Http20Draft12.logger.fine(FrameLogger.formatHeader(true, this.streamId, this.length, b, this.flags));
            }
            short padding = Http20Draft12.readPadding(this.source, this.flags);
            this.padding = padding;
            short sLengthWithoutPadding = Http20Draft12.lengthWithoutPadding(this.length, this.flags, padding);
            this.left = sLengthWithoutPadding;
            this.length = sLengthWithoutPadding;
            int i4 = Integer.MAX_VALUE & i3;
            this.streamId = i4;
            if (b != 9) {
                throw Http20Draft12.ioException("%s != TYPE_CONTINUATION", Byte.valueOf(b));
            }
            if (i4 != i) {
                throw Http20Draft12.ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static short readPadding(BufferedSource bufferedSource, byte b) throws IOException {
        int i;
        int i2 = b & FLAG_PAD_HIGH;
        if (i2 != 0 && (b & 8) == 0) {
            throw ioException("PROTOCOL_ERROR FLAG_PAD_HIGH set without FLAG_PAD_LOW", new Object[0]);
        }
        if (i2 != 0) {
            i = bufferedSource.readShort() & UShort.MAX_VALUE;
        } else {
            i = (b & 8) != 0 ? bufferedSource.readByte() & 255 : 0;
        }
        if (i <= MAX_FRAME_SIZE) {
            return (short) i;
        }
        throw ioException("PROTOCOL_ERROR padding > %d: %d", Integer.valueOf(MAX_FRAME_SIZE), Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0011  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static short lengthWithoutPadding(short r1, byte r2, short r3) throws java.io.IOException {
        /*
            r0 = r2 & 16
            if (r0 == 0) goto L8
            int r1 = r1 + (-2)
        L6:
            short r1 = (short) r1
            goto Lf
        L8:
            r2 = r2 & 8
            if (r2 == 0) goto Lf
            int r1 = r1 + (-1)
            goto L6
        Lf:
            if (r3 > r1) goto L14
            int r1 = r1 - r3
            short r1 = (short) r1
            return r1
        L14:
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r0 = 0
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            r2[r0] = r3
            java.lang.Short r1 = java.lang.Short.valueOf(r1)
            r3 = 1
            r2[r3] = r1
            java.lang.String r1 = "PROTOCOL_ERROR padding %s > remaining length %s"
            java.io.IOException r1 = ioException(r1, r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.spdy.Http20Draft12.lengthWithoutPadding(short, byte, short):short");
    }

    static final class FrameLogger {
        private static final String[] TYPES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION", "ALTSVC", "BLOCKED"};
        private static final String[] FLAGS = new String[64];
        private static final String[] BINARY = new String[256];

        FrameLogger() {
        }

        static String formatHeader(boolean z, int i, int i2, byte b, byte b2) {
            String[] strArr = TYPES;
            String str = b < strArr.length ? strArr[b] : String.format("0x%02x", Byte.valueOf(b));
            String flags = formatFlags(b, b2);
            Object[] objArr = new Object[5];
            objArr[0] = z ? "<<" : ">>";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = Integer.valueOf(i2);
            objArr[3] = str;
            objArr[4] = flags;
            return String.format("%s 0x%08x %5d %-13s %s", objArr);
        }

        static String formatFlags(byte b, byte b2) {
            if (b2 == 0) {
                return "";
            }
            switch (b) {
                case 2:
                case 3:
                case 7:
                case 8:
                case 10:
                case 11:
                    return BINARY[b2];
                case 4:
                case 6:
                    return b2 == 1 ? "ACK" : BINARY[b2];
                case 5:
                case 9:
                default:
                    String[] strArr = FLAGS;
                    String str = b2 < strArr.length ? strArr[b2] : BINARY[b2];
                    if (b != 5 || (b2 & 4) == 0) {
                        return (b != 0 || (b2 & 32) == 0) ? str : str.replace("PRIORITY", "COMPRESSED");
                    }
                    return str.replace("HEADERS", "PUSH_PROMISE");
            }
        }

        static {
            int i = 0;
            while (true) {
                String[] strArr = BINARY;
                if (i >= strArr.length) {
                    break;
                }
                strArr[i] = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
                i++;
            }
            String[] strArr2 = FLAGS;
            strArr2[0] = "";
            strArr2[1] = "END_STREAM";
            int i2 = 2;
            strArr2[2] = "END_SEGMENT";
            strArr2[3] = "END_STREAM|END_SEGMENT";
            int[] iArr = {1, 2, 3};
            strArr2[8] = "PAD_LOW";
            strArr2[24] = "PAD_LOW|PAD_HIGH";
            int[] iArr2 = {8, 24};
            for (int i3 = 0; i3 < 3; i3++) {
                int i4 = iArr[i3];
                for (int i5 = 0; i5 < 2; i5++) {
                    int i6 = iArr2[i5];
                    FLAGS[i4 | i6] = FLAGS[i4] + '|' + FLAGS[i6];
                }
            }
            String[] strArr3 = FLAGS;
            strArr3[4] = "END_HEADERS";
            strArr3[32] = "PRIORITY";
            strArr3[36] = "END_HEADERS|PRIORITY";
            int[] iArr3 = {4, 32, 36};
            int i7 = 0;
            while (i7 < 3) {
                int i8 = iArr3[i7];
                int i9 = 0;
                while (i9 < 3) {
                    int i10 = iArr[i9];
                    int i11 = i10 | i8;
                    FLAGS[i11] = FLAGS[i10] + '|' + FLAGS[i8];
                    int i12 = 0;
                    while (i12 < i2) {
                        int i13 = iArr2[i12];
                        FLAGS[i11 | i13] = FLAGS[i10] + '|' + FLAGS[i8] + '|' + FLAGS[i13];
                        i12++;
                        i2 = 2;
                    }
                    i9++;
                    i2 = 2;
                }
                i7++;
                i2 = 2;
            }
            int i14 = 0;
            while (true) {
                String[] strArr4 = FLAGS;
                if (i14 >= strArr4.length) {
                    return;
                }
                if (strArr4[i14] == null) {
                    strArr4[i14] = BINARY[i14];
                }
                i14++;
            }
        }
    }
}
