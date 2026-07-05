package com.squareup.okhttp.internal.spdy;

import com.huya.statistics.core.StatisticsContent;
import com.squareup.okhttp.internal.BitArray;
import com.sqwan.liveshow.huya.SqR;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
final class HpackDraft07 {
    private static final int PREFIX_4_BITS = 15;
    private static final int PREFIX_6_BITS = 63;
    private static final int PREFIX_7_BITS = 127;
    private static final Header[] STATIC_HEADER_TABLE = {new Header(Header.TARGET_AUTHORITY, ""), new Header(Header.TARGET_METHOD, "GET"), new Header(Header.TARGET_METHOD, "POST"), new Header(Header.TARGET_PATH, "/"), new Header(Header.TARGET_PATH, "/index.html"), new Header(Header.TARGET_SCHEME, IDataSource.SCHEME_HTTP_TAG), new Header(Header.TARGET_SCHEME, IDataSource.SCHEME_HTTPS_TAG), new Header(Header.RESPONSE_STATUS, "200"), new Header(Header.RESPONSE_STATUS, "204"), new Header(Header.RESPONSE_STATUS, "206"), new Header(Header.RESPONSE_STATUS, "304"), new Header(Header.RESPONSE_STATUS, "400"), new Header(Header.RESPONSE_STATUS, "404"), new Header(Header.RESPONSE_STATUS, "500"), new Header("accept-charset", ""), new Header("accept-encoding", ""), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header(StatisticsContent.FROM, ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header("location", ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header(SqR.string.refresh, ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")};
    private static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX = nameToFirstIndex();

    private HpackDraft07() {
    }

    static final class Reader {
        private int maxHeaderTableByteCount;
        private int maxHeaderTableByteCountSetting;
        int nextHeaderIndex;
        private final BufferedSource source;
        private final List<Header> emittedHeaders = new ArrayList();
        Header[] headerTable = new Header[8];
        int headerCount = 0;
        BitArray referencedHeaders = new BitArray.FixedCapacity();
        BitArray emittedReferencedHeaders = new BitArray.FixedCapacity();
        int headerTableByteCount = 0;

        Reader(int i, Source source) {
            this.nextHeaderIndex = r0.length - 1;
            this.maxHeaderTableByteCountSetting = i;
            this.maxHeaderTableByteCount = i;
            this.source = Okio.buffer(source);
        }

        int maxHeaderTableByteCount() {
            return this.maxHeaderTableByteCount;
        }

        void maxHeaderTableByteCountSetting(int i) {
            this.maxHeaderTableByteCountSetting = i;
            this.maxHeaderTableByteCount = i;
            adjustHeaderTableByteCount();
        }

        private void adjustHeaderTableByteCount() {
            int i = this.maxHeaderTableByteCount;
            int i2 = this.headerTableByteCount;
            if (i < i2) {
                if (i == 0) {
                    clearHeaderTable();
                } else {
                    evictToRecoverBytes(i2 - i);
                }
            }
        }

        private void clearHeaderTable() {
            clearReferenceSet();
            Arrays.fill(this.headerTable, (Object) null);
            this.nextHeaderIndex = this.headerTable.length - 1;
            this.headerCount = 0;
            this.headerTableByteCount = 0;
        }

        private int evictToRecoverBytes(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.headerTable.length;
                while (true) {
                    length--;
                    if (length < this.nextHeaderIndex || i <= 0) {
                        break;
                    }
                    i -= this.headerTable[length].hpackSize;
                    this.headerTableByteCount -= this.headerTable[length].hpackSize;
                    this.headerCount--;
                    i2++;
                }
                this.referencedHeaders.shiftLeft(i2);
                this.emittedReferencedHeaders.shiftLeft(i2);
                Header[] headerArr = this.headerTable;
                int i3 = this.nextHeaderIndex;
                System.arraycopy(headerArr, i3 + 1, headerArr, i3 + 1 + i2, this.headerCount);
                this.nextHeaderIndex += i2;
            }
            return i2;
        }

        void readHeaders() throws IOException {
            while (!this.source.exhausted()) {
                int i = this.source.readByte() & 255;
                if (i == 128) {
                    throw new IOException("index == 0");
                }
                if ((i & 128) == 128) {
                    readIndexedHeader(readInt(i, 127) - 1);
                } else if (i == 64) {
                    readLiteralHeaderWithIncrementalIndexingNewName();
                } else if ((i & 64) == 64) {
                    readLiteralHeaderWithIncrementalIndexingIndexedName(readInt(i, 63) - 1);
                } else if ((i & 32) == 32) {
                    if ((i & 16) != 16) {
                        int i2 = readInt(i, 15);
                        this.maxHeaderTableByteCount = i2;
                        if (i2 < 0 || i2 > this.maxHeaderTableByteCountSetting) {
                            throw new IOException("Invalid header table byte count " + this.maxHeaderTableByteCount);
                        }
                        adjustHeaderTableByteCount();
                    } else {
                        if ((i & 15) != 0) {
                            throw new IOException("Invalid header table state change " + i);
                        }
                        clearReferenceSet();
                    }
                } else if (i == 16 || i == 0) {
                    readLiteralHeaderWithoutIndexingNewName();
                } else {
                    readLiteralHeaderWithoutIndexingIndexedName(readInt(i, 15) - 1);
                }
            }
        }

        private void clearReferenceSet() {
            this.referencedHeaders.clear();
            this.emittedReferencedHeaders.clear();
        }

        void emitReferenceSet() {
            int length = this.headerTable.length;
            while (true) {
                length--;
                if (length == this.nextHeaderIndex) {
                    return;
                }
                if (this.referencedHeaders.get(length) && !this.emittedReferencedHeaders.get(length)) {
                    this.emittedHeaders.add(this.headerTable[length]);
                }
            }
        }

        List<Header> getAndReset() {
            ArrayList arrayList = new ArrayList(this.emittedHeaders);
            this.emittedHeaders.clear();
            this.emittedReferencedHeaders.clear();
            return arrayList;
        }

        private void readIndexedHeader(int i) throws IOException {
            if (isStaticHeader(i)) {
                int i2 = i - this.headerCount;
                if (i2 <= HpackDraft07.STATIC_HEADER_TABLE.length - 1) {
                    Header header = HpackDraft07.STATIC_HEADER_TABLE[i2];
                    if (this.maxHeaderTableByteCount == 0) {
                        this.emittedHeaders.add(header);
                        return;
                    } else {
                        insertIntoHeaderTable(-1, header);
                        return;
                    }
                }
                throw new IOException("Header index too large " + (i2 + 1));
            }
            int iHeaderTableIndex = headerTableIndex(i);
            if (!this.referencedHeaders.get(iHeaderTableIndex)) {
                this.emittedHeaders.add(this.headerTable[iHeaderTableIndex]);
                this.emittedReferencedHeaders.set(iHeaderTableIndex);
            }
            this.referencedHeaders.toggle(iHeaderTableIndex);
        }

        private int headerTableIndex(int i) {
            return this.nextHeaderIndex + 1 + i;
        }

        private void readLiteralHeaderWithoutIndexingIndexedName(int i) throws IOException {
            this.emittedHeaders.add(new Header(getName(i), readByteString()));
        }

        private void readLiteralHeaderWithoutIndexingNewName() throws IOException {
            this.emittedHeaders.add(new Header(HpackDraft07.checkLowercase(readByteString()), readByteString()));
        }

        private void readLiteralHeaderWithIncrementalIndexingIndexedName(int i) throws IOException {
            insertIntoHeaderTable(-1, new Header(getName(i), readByteString()));
        }

        private void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
            insertIntoHeaderTable(-1, new Header(HpackDraft07.checkLowercase(readByteString()), readByteString()));
        }

        private ByteString getName(int i) {
            if (isStaticHeader(i)) {
                return HpackDraft07.STATIC_HEADER_TABLE[i - this.headerCount].name;
            }
            return this.headerTable[headerTableIndex(i)].name;
        }

        private boolean isStaticHeader(int i) {
            return i >= this.headerCount;
        }

        private void insertIntoHeaderTable(int i, Header header) {
            int i2 = header.hpackSize;
            if (i != -1) {
                i2 -= this.headerTable[headerTableIndex(i)].hpackSize;
            }
            int i3 = this.maxHeaderTableByteCount;
            if (i2 > i3) {
                clearHeaderTable();
                this.emittedHeaders.add(header);
                return;
            }
            int iEvictToRecoverBytes = evictToRecoverBytes((this.headerTableByteCount + i2) - i3);
            if (i == -1) {
                int i4 = this.headerCount + 1;
                Header[] headerArr = this.headerTable;
                if (i4 > headerArr.length) {
                    int length = headerArr.length * 2;
                    Header[] headerArr2 = new Header[length];
                    System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                    if (length == 64) {
                        this.referencedHeaders = ((BitArray.FixedCapacity) this.referencedHeaders).toVariableCapacity();
                        this.emittedReferencedHeaders = ((BitArray.FixedCapacity) this.emittedReferencedHeaders).toVariableCapacity();
                    }
                    this.referencedHeaders.shiftLeft(this.headerTable.length);
                    this.emittedReferencedHeaders.shiftLeft(this.headerTable.length);
                    this.nextHeaderIndex = this.headerTable.length - 1;
                    this.headerTable = headerArr2;
                }
                int i5 = this.nextHeaderIndex;
                this.nextHeaderIndex = i5 - 1;
                this.referencedHeaders.set(i5);
                this.headerTable[i5] = header;
                this.headerCount++;
            } else {
                int iHeaderTableIndex = i + headerTableIndex(i) + iEvictToRecoverBytes;
                this.referencedHeaders.set(iHeaderTableIndex);
                this.headerTable[iHeaderTableIndex] = header;
            }
            this.headerTableByteCount += i2;
        }

        private int readByte() throws IOException {
            return this.source.readByte() & 255;
        }

        int readInt(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int i5 = readByte();
                if ((i5 & 128) == 0) {
                    return i2 + (i5 << i4);
                }
                i2 += (i5 & 127) << i4;
                i4 += 7;
            }
        }

        ByteString readByteString() throws IOException {
            int i = readByte();
            boolean z = (i & 128) == 128;
            int i2 = readInt(i, 127);
            if (z) {
                return ByteString.of(Huffman.get().decode(this.source.readByteArray(i2)));
            }
            return this.source.readByteString(i2);
        }
    }

    private static Map<ByteString, Integer> nameToFirstIndex() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(STATIC_HEADER_TABLE.length);
        int i = 0;
        while (true) {
            Header[] headerArr = STATIC_HEADER_TABLE;
            if (i < headerArr.length) {
                if (!linkedHashMap.containsKey(headerArr[i].name)) {
                    linkedHashMap.put(STATIC_HEADER_TABLE[i].name, Integer.valueOf(i));
                }
                i++;
            } else {
                return Collections.unmodifiableMap(linkedHashMap);
            }
        }
    }

    static final class Writer {
        private final Buffer out;

        Writer(Buffer buffer) {
            this.out = buffer;
        }

        void writeHeaders(List<Header> list) throws IOException {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ByteString asciiLowercase = list.get(i).name.toAsciiLowercase();
                Integer num = (Integer) HpackDraft07.NAME_TO_FIRST_INDEX.get(asciiLowercase);
                if (num != null) {
                    writeInt(num.intValue() + 1, 15, 0);
                    writeByteString(list.get(i).value);
                } else {
                    this.out.writeByte(0);
                    writeByteString(asciiLowercase);
                    writeByteString(list.get(i).value);
                }
            }
        }

        void writeInt(int i, int i2, int i3) throws IOException {
            if (i < i2) {
                this.out.writeByte(i | i3);
                return;
            }
            this.out.writeByte(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.out.writeByte(128 | (i4 & 127));
                i4 >>>= 7;
            }
            this.out.writeByte(i4);
        }

        void writeByteString(ByteString byteString) throws IOException {
            writeInt(byteString.size(), 127, 0);
            this.out.write(byteString);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteString checkLowercase(ByteString byteString) throws IOException {
        int size = byteString.size();
        for (int i = 0; i < size; i++) {
            byte b = byteString.getByte(i);
            if (b >= 65 && b <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.utf8());
            }
        }
        return byteString;
    }
}
