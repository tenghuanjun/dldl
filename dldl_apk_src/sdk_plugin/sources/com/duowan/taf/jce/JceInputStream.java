package com.duowan.taf.jce;

import com.duowan.taf.jce.dynamic.HeadData;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class JceInputStream {
    private ByteBuffer bs;
    protected String sServerEncoding = "UTF-8";

    public static void main(String[] strArr) {
    }

    public JceInputStream() {
    }

    public JceInputStream(ByteBuffer byteBuffer) {
        this.bs = byteBuffer;
    }

    public JceInputStream(byte[] bArr) {
        this.bs = ByteBuffer.wrap(bArr);
    }

    public JceInputStream(byte[] bArr, int i) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        this.bs = byteBufferWrap;
        byteBufferWrap.position(i);
    }

    public void warp(byte[] bArr) {
        ByteBuffer byteBuffer = this.bs;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
        this.bs = ByteBuffer.wrap(bArr);
    }

    public static int readHead(HeadData headData, ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        headData.type = (byte) (b & 15);
        headData.tag = (b & 240) >> 4;
        if (headData.tag != 15) {
            return 1;
        }
        headData.tag = byteBuffer.get();
        return 2;
    }

    public void readHead(HeadData headData) {
        readHead(headData, this.bs);
    }

    private int peakHead(HeadData headData) {
        return readHead(headData, this.bs.duplicate());
    }

    private void skip(int i) {
        ByteBuffer byteBuffer = this.bs;
        byteBuffer.position(byteBuffer.position() + i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0019, code lost:
    
        if (r6 != r0.tag) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001b, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x001f, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean skipToTag(int r6) {
        /*
            r5 = this;
            com.duowan.taf.jce.dynamic.HeadData r0 = com.duowan.taf.jce.dynamic.HeadData.obtain()
        L4:
            r1 = 0
            int r2 = r5.peakHead(r0)     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L2e
            byte r3 = r0.type     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L2e
            r4 = 11
            if (r3 != r4) goto L13
            com.duowan.taf.jce.dynamic.HeadData.revert(r0)
            return r1
        L13:
            int r3 = r0.tag     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L2e java.lang.Throwable -> L2e
            if (r6 > r3) goto L20
            int r2 = r0.tag     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L2e java.lang.Throwable -> L2e
            if (r6 != r2) goto L1c
            r1 = 1
        L1c:
            com.duowan.taf.jce.dynamic.HeadData.revert(r0)
            return r1
        L20:
            r5.skip(r2)     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L2e java.lang.Throwable -> L2e
            byte r2 = r0.type     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L2e java.lang.Throwable -> L2e
            r5.skipField(r2)     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L2e java.lang.Throwable -> L2e
            goto L4
        L29:
            r6 = move-exception
            com.duowan.taf.jce.dynamic.HeadData.revert(r0)
            throw r6
        L2e:
            com.duowan.taf.jce.dynamic.HeadData.revert(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.taf.jce.JceInputStream.skipToTag(int):boolean");
    }

    public void skipToStructEnd() {
        HeadData headDataObtain = HeadData.obtain();
        do {
            readHead(headDataObtain);
            skipField(headDataObtain.type);
        } while (headDataObtain.type != 11);
        HeadData.revert(headDataObtain);
    }

    private void skipField() {
        HeadData headDataObtain = HeadData.obtain();
        readHead(headDataObtain);
        skipField(headDataObtain.type);
        HeadData.revert(headDataObtain);
    }

    private void skipField(byte b) {
        int i = 0;
        switch (b) {
            case 0:
                skip(1);
                return;
            case 1:
                skip(2);
                return;
            case 2:
                skip(4);
                return;
            case 3:
                skip(8);
                return;
            case 4:
                skip(4);
                return;
            case 5:
                skip(8);
                return;
            case 6:
                int i2 = this.bs.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                skip(i2);
                return;
            case 7:
                skip(this.bs.getInt());
                return;
            case 8:
                int i3 = read(0, 0, true);
                while (i < i3 * 2) {
                    skipField();
                    i++;
                }
                return;
            case 9:
                int i4 = read(0, 0, true);
                while (i < i4) {
                    skipField();
                    i++;
                }
                return;
            case 10:
                skipToStructEnd();
                return;
            case 11:
            case 12:
                return;
            case 13:
                HeadData headDataObtain = HeadData.obtain();
                try {
                    readHead(headDataObtain);
                    if (headDataObtain.type != 0) {
                        throw new JceDecodeException("skipField with invalid type, type value: " + ((int) b) + ", " + ((int) headDataObtain.type));
                    }
                    HeadData.revert(headDataObtain);
                    skip(read(0, 0, true));
                    return;
                } catch (Throwable th) {
                    HeadData.revert(headDataObtain);
                    throw th;
                }
            default:
                throw new JceDecodeException("invalid type.");
        }
    }

    public boolean read(boolean z, int i, boolean z2) {
        return ((byte) read(0, i, z2)) != 0;
    }

    public byte read(byte b, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return b;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            byte b2 = headDataObtain.type;
            if (b2 == 0) {
                return this.bs.get();
            }
            if (b2 != 12) {
                throw new JceDecodeException("type mismatch.");
            }
            return (byte) 0;
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public short read(short s, int i, boolean z) {
        short s2;
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return s;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            byte b = headDataObtain.type;
            if (b == 0) {
                s2 = this.bs.get();
            } else {
                if (b == 1) {
                    return this.bs.getShort();
                }
                if (b != 12) {
                    throw new JceDecodeException("type mismatch.");
                }
                s2 = 0;
            }
            return s2;
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public int read(int i, int i2, boolean z) {
        if (!skipToTag(i2)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return i;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            byte b = headDataObtain.type;
            if (b == 0) {
                return this.bs.get();
            }
            if (b == 1) {
                return this.bs.getShort();
            }
            if (b == 2) {
                return this.bs.getInt();
            }
            if (b == 12) {
                return 0;
            }
            throw new JceDecodeException("type mismatch.");
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public long read(long j, int i, boolean z) {
        int i2;
        long j2;
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return j;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            byte b = headDataObtain.type;
            if (b == 0) {
                i2 = this.bs.get();
            } else if (b == 1) {
                i2 = this.bs.getShort();
            } else {
                if (b != 2) {
                    if (b == 3) {
                        return this.bs.getLong();
                    }
                    if (b != 12) {
                        throw new JceDecodeException("type mismatch.");
                    }
                    j2 = 0;
                    return j2;
                }
                i2 = this.bs.getInt();
            }
            j2 = i2;
            return j2;
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public float read(float f, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return f;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            byte b = headDataObtain.type;
            if (b == 4) {
                return this.bs.getFloat();
            }
            if (b != 12) {
                throw new JceDecodeException("type mismatch.");
            }
            return 0.0f;
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public double read(double d, int i, boolean z) {
        double d2;
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return d;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            byte b = headDataObtain.type;
            if (b == 4) {
                d2 = this.bs.getFloat();
            } else {
                if (b == 5) {
                    return this.bs.getDouble();
                }
                if (b != 12) {
                    throw new JceDecodeException("type mismatch.");
                }
                d2 = 0.0d;
            }
            return d2;
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public String readByteString(String str, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return str;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            byte b = headDataObtain.type;
            if (b == 6) {
                int i2 = this.bs.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                byte[] bArr = new byte[i2];
                this.bs.get(bArr);
                return HexUtil.bytes2HexStr(bArr);
            }
            if (b == 7) {
                int i3 = this.bs.getInt();
                if (i3 > 104857600) {
                    throw new JceDecodeException("String too long: " + i3);
                }
                if (i3 < 0) {
                    throw new JceDecodeException("String too long: " + i3);
                }
                byte[] bArr2 = new byte[i3];
                this.bs.get(bArr2);
                return HexUtil.bytes2HexStr(bArr2);
            }
            throw new JceDecodeException("type mismatch.");
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public String read(String str, int i, boolean z) {
        String str2;
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return str;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            byte b = headDataObtain.type;
            if (b == 6) {
                int i2 = this.bs.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                byte[] bArr = new byte[i2];
                this.bs.get(bArr);
                try {
                    str2 = new String(bArr, this.sServerEncoding);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(bArr);
                }
            } else if (b == 7) {
                int i3 = this.bs.getInt();
                if (i3 > 104857600 || i3 < 0) {
                    throw new JceDecodeException("String too long: " + i3);
                }
                byte[] bArr2 = new byte[i3];
                this.bs.get(bArr2);
                try {
                    str2 = new String(bArr2, this.sServerEncoding);
                } catch (UnsupportedEncodingException unused2) {
                    str2 = new String(bArr2);
                }
            } else {
                throw new JceDecodeException("type mismatch.");
            }
            HeadData.revert(headDataObtain);
            return str2;
        } catch (Throwable th) {
            HeadData.revert(headDataObtain);
            throw th;
        }
    }

    public String readString(int i, boolean z) {
        String str;
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return null;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            byte b = headDataObtain.type;
            if (b == 6) {
                int i2 = this.bs.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                byte[] bArr = new byte[i2];
                this.bs.get(bArr);
                try {
                    str = new String(bArr, this.sServerEncoding);
                } catch (UnsupportedEncodingException unused) {
                    str = new String(bArr);
                }
            } else if (b == 7) {
                int i3 = this.bs.getInt();
                if (i3 > 104857600 || i3 < 0) {
                    throw new JceDecodeException("String too long: " + i3);
                }
                byte[] bArr2 = new byte[i3];
                this.bs.get(bArr2);
                try {
                    str = new String(bArr2, this.sServerEncoding);
                } catch (UnsupportedEncodingException unused2) {
                    str = new String(bArr2);
                }
            } else {
                throw new JceDecodeException("type mismatch.");
            }
            return str;
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public String[] read(String[] strArr, int i, boolean z) {
        return (String[]) readArray(strArr, i, z);
    }

    public Map<String, String> readStringMap(int i, boolean z) {
        HashMap map = new HashMap();
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return map;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            if (headDataObtain.type == 8) {
                int i2 = read(0, 0, true);
                if (i2 < 0) {
                    throw new JceDecodeException("size invalid: " + i2);
                }
                for (int i3 = 0; i3 < i2; i3++) {
                    map.put(readString(0, true), readString(1, true));
                }
                return map;
            }
            throw new JceDecodeException("type mismatch.");
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public <K, V> HashMap<K, V> readMap(Map<K, V> map, int i, boolean z) {
        return (HashMap) readMap(new HashMap(), map, i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> Map<K, V> readMap(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Map.Entry<K, V> next = map2.entrySet().iterator().next();
        K key = next.getKey();
        V value = next.getValue();
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return map;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            if (headDataObtain.type == 8) {
                int i2 = read(0, 0, true);
                if (i2 < 0) {
                    throw new JceDecodeException("size invalid: " + i2);
                }
                for (int i3 = 0; i3 < i2; i3++) {
                    map.put(read(key, 0, true), read(value, 1, true));
                }
                return map;
            }
            throw new JceDecodeException("type mismatch.");
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00df, code lost:
    
        r11 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00e3, code lost:
    
        throw r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List readList(int r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.taf.jce.JceInputStream.readList(int, boolean):java.util.List");
    }

    public boolean[] read(boolean[] zArr, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return null;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            if (headDataObtain.type == 9) {
                int i2 = read(0, 0, true);
                if (i2 < 0) {
                    throw new JceDecodeException("size invalid: " + i2);
                }
                boolean[] zArr2 = new boolean[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    zArr2[i3] = read(zArr2[0], 0, true);
                }
                return zArr2;
            }
            throw new JceDecodeException("type mismatch.");
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public byte[] read(byte[] bArr, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return null;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            byte b = headDataObtain.type;
            if (b == 9) {
                int i2 = read(0, 0, true);
                if (i2 < 0) {
                    throw new JceDecodeException("size invalid: " + i2);
                }
                byte[] bArr2 = new byte[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    bArr2[i3] = read(bArr2[0], 0, true);
                }
                return bArr2;
            }
            if (b == 13) {
                headDataObtain = HeadData.obtain();
                readHead(headDataObtain);
                if (headDataObtain.type != 0) {
                    throw new JceDecodeException("type mismatch, tag: " + i + ", type: " + ((int) headDataObtain.type) + ", " + ((int) headDataObtain.type));
                }
                int i4 = read(0, 0, true);
                if (i4 < 0) {
                    throw new JceDecodeException("invalid size, tag: " + i + ", type: " + ((int) headDataObtain.type) + ", " + ((int) headDataObtain.type) + ", size: " + i4);
                }
                byte[] bArr3 = new byte[i4];
                this.bs.get(bArr3);
                HeadData.revert(headDataObtain);
                return bArr3;
            }
            throw new JceDecodeException("type mismatch.");
        } catch (Throwable th) {
            throw th;
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public short[] read(short[] sArr, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return null;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            if (headDataObtain.type == 9) {
                int i2 = read(0, 0, true);
                if (i2 < 0) {
                    throw new JceDecodeException("size invalid: " + i2);
                }
                short[] sArr2 = new short[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    sArr2[i3] = read(sArr2[0], 0, true);
                }
                return sArr2;
            }
            throw new JceDecodeException("type mismatch.");
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public int[] read(int[] iArr, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return null;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            if (headDataObtain.type == 9) {
                int i2 = read(0, 0, true);
                if (i2 < 0) {
                    throw new JceDecodeException("size invalid: " + i2);
                }
                int[] iArr2 = new int[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    iArr2[i3] = read(iArr2[0], 0, true);
                }
                return iArr2;
            }
            throw new JceDecodeException("type mismatch.");
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public long[] read(long[] jArr, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return null;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            if (headDataObtain.type == 9) {
                int i2 = read(0, 0, true);
                if (i2 < 0) {
                    throw new JceDecodeException("size invalid: " + i2);
                }
                long[] jArr2 = new long[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    jArr2[i3] = read(jArr2[0], 0, true);
                }
                return jArr2;
            }
            throw new JceDecodeException("type mismatch.");
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public float[] read(float[] fArr, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return null;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            if (headDataObtain.type == 9) {
                int i2 = read(0, 0, true);
                if (i2 < 0) {
                    throw new JceDecodeException("size invalid: " + i2);
                }
                float[] fArr2 = new float[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    fArr2[i3] = read(fArr2[0], 0, true);
                }
                return fArr2;
            }
            throw new JceDecodeException("type mismatch.");
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public double[] read(double[] dArr, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return null;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            if (headDataObtain.type == 9) {
                int i2 = read(0, 0, true);
                if (i2 < 0) {
                    throw new JceDecodeException("size invalid: " + i2);
                }
                double[] dArr2 = new double[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    dArr2[i3] = read(dArr2[0], 0, true);
                }
                return dArr2;
            }
            throw new JceDecodeException("type mismatch.");
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public <T> T[] readArray(T[] tArr, int i, boolean z) {
        if (tArr == null || tArr.length == 0) {
            throw new JceDecodeException("unable to get type of key and value.");
        }
        return (T[]) readArrayImpl(tArr[0], i, z);
    }

    public <T> List<T> readArray(List<T> list, int i, boolean z) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        Object[] arrayImpl = readArrayImpl(list.get(0), i, z);
        if (arrayImpl == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayImpl) {
            arrayList.add(obj);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> T[] readArrayImpl(T t, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return null;
        }
        HeadData headDataObtain = HeadData.obtain();
        try {
            readHead(headDataObtain);
            if (headDataObtain.type == 9) {
                int i2 = read(0, 0, true);
                if (i2 < 0) {
                    throw new JceDecodeException("size invalid: " + i2);
                }
                T[] tArr = (T[]) ((Object[]) Array.newInstance(t.getClass(), i2));
                for (int i3 = 0; i3 < i2; i3++) {
                    tArr[i3] = read((Object) t, 0, true);
                }
                return tArr;
            }
            throw new JceDecodeException("type mismatch.");
        } finally {
            HeadData.revert(headDataObtain);
        }
    }

    public JceStruct directRead(JceStruct jceStruct, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return null;
        }
        try {
            JceStruct jceStructNewInit = jceStruct.newInit();
            HeadData headDataObtain = HeadData.obtain();
            try {
                readHead(headDataObtain);
                if (headDataObtain.type != 10) {
                    throw new JceDecodeException("type mismatch.");
                }
                HeadData.revert(headDataObtain);
                jceStructNewInit.readFrom(this);
                skipToStructEnd();
                return jceStructNewInit;
            } catch (Throwable th) {
                HeadData.revert(headDataObtain);
                throw th;
            }
        } catch (Exception e) {
            throw new JceDecodeException(e.getMessage());
        }
    }

    public JceStruct read(JceStruct jceStruct, int i, boolean z) {
        if (!skipToTag(i)) {
            if (z) {
                throw new JceDecodeException("require field not exist.");
            }
            return null;
        }
        try {
            JceStruct jceStruct2 = (JceStruct) jceStruct.getClass().newInstance();
            HeadData headDataObtain = HeadData.obtain();
            try {
                readHead(headDataObtain);
                if (headDataObtain.type != 10) {
                    throw new JceDecodeException("type mismatch.");
                }
                HeadData.revert(headDataObtain);
                jceStruct2.readFrom(this);
                skipToStructEnd();
                return jceStruct2;
            } catch (Throwable th) {
                HeadData.revert(headDataObtain);
                throw th;
            }
        } catch (Exception e) {
            throw new JceDecodeException(e.getMessage());
        }
    }

    public JceStruct[] read(JceStruct[] jceStructArr, int i, boolean z) {
        return (JceStruct[]) readArray(jceStructArr, i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> Object read(T t, int i, boolean z) {
        if (t instanceof Byte) {
            return Byte.valueOf((byte) read(0, i, z));
        }
        if (t instanceof Boolean) {
            return Boolean.valueOf(read(false, i, z));
        }
        if (t instanceof Short) {
            return Short.valueOf(read((short) 0, i, z));
        }
        if (t instanceof Integer) {
            return Integer.valueOf(read(0, i, z));
        }
        if (t instanceof Long) {
            return Long.valueOf(read(0L, i, z));
        }
        if (t instanceof Float) {
            return Float.valueOf(read(0.0f, i, z));
        }
        if (t instanceof Double) {
            return Double.valueOf(read(0.0d, i, z));
        }
        if (t instanceof String) {
            return String.valueOf(readString(i, z));
        }
        if (t instanceof Map) {
            return readMap((Map) t, i, z);
        }
        if (t instanceof List) {
            return readArray((List) t, i, z);
        }
        if (t instanceof JceStruct) {
            return read((JceStruct) t, i, z);
        }
        if (t.getClass().isArray()) {
            if ((t instanceof byte[]) || (t instanceof Byte[])) {
                return read((byte[]) null, i, z);
            }
            if (t instanceof boolean[]) {
                return read((boolean[]) null, i, z);
            }
            if (t instanceof short[]) {
                return read((short[]) null, i, z);
            }
            if (t instanceof int[]) {
                return read((int[]) null, i, z);
            }
            if (t instanceof long[]) {
                return read((long[]) null, i, z);
            }
            if (t instanceof float[]) {
                return read((float[]) null, i, z);
            }
            if (t instanceof double[]) {
                return read((double[]) null, i, z);
            }
            return readArray((Object[]) t, i, z);
        }
        throw new JceDecodeException("read object error: unsupport type.");
    }

    public int setServerEncoding(String str) {
        this.sServerEncoding = str;
        return 0;
    }

    public ByteBuffer getBs() {
        return this.bs;
    }
}
