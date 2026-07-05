package com.duowan.taf.jce;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class JceDisplayer {
    private int _level;
    private StringBuilder sb;

    private void ps(String str) {
        for (int i = 0; i < this._level; i++) {
            this.sb.append('\t');
        }
        if (str != null) {
            StringBuilder sb = this.sb;
            sb.append(str);
            sb.append(": ");
        }
    }

    public JceDisplayer(StringBuilder sb, int i) {
        this._level = 0;
        this.sb = sb;
        this._level = i;
    }

    public JceDisplayer(StringBuilder sb) {
        this._level = 0;
        this.sb = sb;
    }

    public JceDisplayer display(boolean z, String str) {
        ps(str);
        StringBuilder sb = this.sb;
        sb.append(z ? 'T' : 'F');
        sb.append('\n');
        return this;
    }

    public JceDisplayer display(byte b, String str) {
        ps(str);
        StringBuilder sb = this.sb;
        sb.append((int) b);
        sb.append('\n');
        return this;
    }

    public JceDisplayer display(char c, String str) {
        ps(str);
        StringBuilder sb = this.sb;
        sb.append(c);
        sb.append('\n');
        return this;
    }

    public JceDisplayer display(short s, String str) {
        ps(str);
        StringBuilder sb = this.sb;
        sb.append((int) s);
        sb.append('\n');
        return this;
    }

    public JceDisplayer display(int i, String str) {
        ps(str);
        StringBuilder sb = this.sb;
        sb.append(i);
        sb.append('\n');
        return this;
    }

    public JceDisplayer display(long j, String str) {
        ps(str);
        StringBuilder sb = this.sb;
        sb.append(j);
        sb.append('\n');
        return this;
    }

    public JceDisplayer display(float f, String str) {
        ps(str);
        StringBuilder sb = this.sb;
        sb.append(f);
        sb.append('\n');
        return this;
    }

    public JceDisplayer display(double d, String str) {
        ps(str);
        StringBuilder sb = this.sb;
        sb.append(d);
        sb.append('\n');
        return this;
    }

    public JceDisplayer display(String str, String str2) {
        ps(str2);
        if (str == null) {
            StringBuilder sb = this.sb;
            sb.append(AbstractJsonLexerKt.NULL);
            sb.append('\n');
        } else {
            StringBuilder sb2 = this.sb;
            sb2.append(str);
            sb2.append('\n');
        }
        return this;
    }

    public JceDisplayer display(byte[] bArr, String str) {
        ps(str);
        if (bArr == null) {
            StringBuilder sb = this.sb;
            sb.append(AbstractJsonLexerKt.NULL);
            sb.append('\n');
            return this;
        }
        if (bArr.length == 0) {
            StringBuilder sb2 = this.sb;
            sb2.append(bArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        }
        StringBuilder sb3 = this.sb;
        sb3.append(bArr.length);
        sb3.append(", [");
        sb3.append('\n');
        JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
        for (byte b : bArr) {
            jceDisplayer.display(b, (String) null);
        }
        display(AbstractJsonLexerKt.END_LIST, (String) null);
        return this;
    }

    public JceDisplayer display(char[] cArr, String str) {
        ps(str);
        if (cArr == null) {
            StringBuilder sb = this.sb;
            sb.append(AbstractJsonLexerKt.NULL);
            sb.append('\n');
            return this;
        }
        if (cArr.length == 0) {
            StringBuilder sb2 = this.sb;
            sb2.append(cArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        }
        StringBuilder sb3 = this.sb;
        sb3.append(cArr.length);
        sb3.append(", [");
        sb3.append('\n');
        JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
        for (char c : cArr) {
            jceDisplayer.display(c, (String) null);
        }
        display(AbstractJsonLexerKt.END_LIST, (String) null);
        return this;
    }

    public JceDisplayer display(short[] sArr, String str) {
        ps(str);
        if (sArr == null) {
            StringBuilder sb = this.sb;
            sb.append(AbstractJsonLexerKt.NULL);
            sb.append('\n');
            return this;
        }
        if (sArr.length == 0) {
            StringBuilder sb2 = this.sb;
            sb2.append(sArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        }
        StringBuilder sb3 = this.sb;
        sb3.append(sArr.length);
        sb3.append(", [");
        sb3.append('\n');
        JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
        for (short s : sArr) {
            jceDisplayer.display(s, (String) null);
        }
        display(AbstractJsonLexerKt.END_LIST, (String) null);
        return this;
    }

    public JceDisplayer display(int[] iArr, String str) {
        ps(str);
        if (iArr == null) {
            StringBuilder sb = this.sb;
            sb.append(AbstractJsonLexerKt.NULL);
            sb.append('\n');
            return this;
        }
        if (iArr.length == 0) {
            StringBuilder sb2 = this.sb;
            sb2.append(iArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        }
        StringBuilder sb3 = this.sb;
        sb3.append(iArr.length);
        sb3.append(", [");
        sb3.append('\n');
        JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
        for (int i : iArr) {
            jceDisplayer.display(i, (String) null);
        }
        display(AbstractJsonLexerKt.END_LIST, (String) null);
        return this;
    }

    public JceDisplayer display(long[] jArr, String str) {
        ps(str);
        if (jArr == null) {
            StringBuilder sb = this.sb;
            sb.append(AbstractJsonLexerKt.NULL);
            sb.append('\n');
            return this;
        }
        if (jArr.length == 0) {
            StringBuilder sb2 = this.sb;
            sb2.append(jArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        }
        StringBuilder sb3 = this.sb;
        sb3.append(jArr.length);
        sb3.append(", [");
        sb3.append('\n');
        JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
        for (long j : jArr) {
            jceDisplayer.display(j, (String) null);
        }
        display(AbstractJsonLexerKt.END_LIST, (String) null);
        return this;
    }

    public JceDisplayer display(float[] fArr, String str) {
        ps(str);
        if (fArr == null) {
            StringBuilder sb = this.sb;
            sb.append(AbstractJsonLexerKt.NULL);
            sb.append('\n');
            return this;
        }
        if (fArr.length == 0) {
            StringBuilder sb2 = this.sb;
            sb2.append(fArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        }
        StringBuilder sb3 = this.sb;
        sb3.append(fArr.length);
        sb3.append(", [");
        sb3.append('\n');
        JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
        for (float f : fArr) {
            jceDisplayer.display(f, (String) null);
        }
        display(AbstractJsonLexerKt.END_LIST, (String) null);
        return this;
    }

    public JceDisplayer display(double[] dArr, String str) {
        ps(str);
        if (dArr == null) {
            StringBuilder sb = this.sb;
            sb.append(AbstractJsonLexerKt.NULL);
            sb.append('\n');
            return this;
        }
        if (dArr.length == 0) {
            StringBuilder sb2 = this.sb;
            sb2.append(dArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        }
        StringBuilder sb3 = this.sb;
        sb3.append(dArr.length);
        sb3.append(", [");
        sb3.append('\n');
        JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
        for (double d : dArr) {
            jceDisplayer.display(d, (String) null);
        }
        display(AbstractJsonLexerKt.END_LIST, (String) null);
        return this;
    }

    public <K, V> JceDisplayer display(Map<K, V> map, String str) {
        ps(str);
        if (map == null) {
            StringBuilder sb = this.sb;
            sb.append(AbstractJsonLexerKt.NULL);
            sb.append('\n');
            return this;
        }
        if (map.isEmpty()) {
            StringBuilder sb2 = this.sb;
            sb2.append(map.size());
            sb2.append(", {}");
            sb2.append('\n');
            return this;
        }
        StringBuilder sb3 = this.sb;
        sb3.append(map.size());
        sb3.append(", {");
        sb3.append('\n');
        JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
        JceDisplayer jceDisplayer2 = new JceDisplayer(this.sb, this._level + 2);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            String str2 = (String) null;
            jceDisplayer.display('(', str2);
            jceDisplayer2.display(entry.getKey(), (String) null);
            jceDisplayer2.display(entry.getValue(), (String) null);
            jceDisplayer.display(')', str2);
        }
        display(AbstractJsonLexerKt.END_OBJ, (String) null);
        return this;
    }

    public <T> JceDisplayer display(T[] tArr, String str) {
        ps(str);
        if (tArr == null) {
            StringBuilder sb = this.sb;
            sb.append(AbstractJsonLexerKt.NULL);
            sb.append('\n');
            return this;
        }
        if (tArr.length == 0) {
            StringBuilder sb2 = this.sb;
            sb2.append(tArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        }
        StringBuilder sb3 = this.sb;
        sb3.append(tArr.length);
        sb3.append(", [");
        sb3.append('\n');
        JceDisplayer jceDisplayer = new JceDisplayer(this.sb, this._level + 1);
        for (T t : tArr) {
            jceDisplayer.display(t, (String) null);
        }
        display(AbstractJsonLexerKt.END_LIST, (String) null);
        return this;
    }

    public <T> JceDisplayer display(Collection<T> collection, String str) {
        if (collection == null) {
            ps(str);
            StringBuilder sb = this.sb;
            sb.append(AbstractJsonLexerKt.NULL);
            sb.append('\t');
            return this;
        }
        return display(collection.toArray(), str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> JceDisplayer display(T t, String str) {
        if (t == 0) {
            StringBuilder sb = this.sb;
            sb.append(AbstractJsonLexerKt.NULL);
            sb.append('\n');
        } else if (t instanceof Byte) {
            display(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            display(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            display(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            display(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            display(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            display(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            display(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            display((String) t, str);
        } else if (t instanceof Map) {
            display((Map) t, str);
        } else if (t instanceof List) {
            display((Collection) t, str);
        } else if (t instanceof JceStruct) {
            display((JceStruct) t, str);
        } else if (t instanceof byte[]) {
            display((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            display((boolean[]) t, str);
        } else if (t instanceof short[]) {
            display((short[]) t, str);
        } else if (t instanceof int[]) {
            display((int[]) t, str);
        } else if (t instanceof long[]) {
            display((long[]) t, str);
        } else if (t instanceof float[]) {
            display((float[]) t, str);
        } else if (t instanceof double[]) {
            display((double[]) t, str);
        } else if (t.getClass().isArray()) {
            display((Object[]) t, str);
        } else {
            throw new JceEncodeException("write object error: unsupport type.");
        }
        return this;
    }

    public JceDisplayer display(JceStruct jceStruct, String str) {
        display(AbstractJsonLexerKt.BEGIN_OBJ, str);
        if (jceStruct == null) {
            StringBuilder sb = this.sb;
            sb.append('\t');
            sb.append(AbstractJsonLexerKt.NULL);
        } else {
            jceStruct.display(this.sb, this._level + 1);
        }
        display(AbstractJsonLexerKt.END_OBJ, (String) null);
        return this;
    }

    public static void main(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(1.2d);
        System.out.println(sb.toString());
    }
}
