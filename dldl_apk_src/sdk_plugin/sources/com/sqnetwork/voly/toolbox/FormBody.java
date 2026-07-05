package com.sqnetwork.voly.toolbox;

import com.sq.tools.network.ContentType;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class FormBody extends RequestBody {
    private static final MediaType CONTENT_TYPE = MediaType.get(ContentType.FORM);
    private final List<String> encodedNames;
    private final List<String> encodedValues;

    FormBody(List<String> encodedNames, List<String> encodedValues) {
        this.encodedNames = Collections.unmodifiableList(encodedNames);
        this.encodedValues = Collections.unmodifiableList(encodedValues);
    }

    public int size() {
        return this.encodedNames.size();
    }

    public String encodedName(int index) {
        return this.encodedNames.get(index);
    }

    public String name(int index) {
        return Util.percentDecode(encodedName(index), true);
    }

    public String encodedValue(int index) {
        return this.encodedValues.get(index);
    }

    public String value(int index) {
        return Util.percentDecode(encodedValue(index), true);
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return CONTENT_TYPE;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        return writeOrCountBytes(null, true);
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink sink) throws IOException {
        writeOrCountBytes(sink, false);
    }

    private long writeOrCountBytes(BufferedSink sink, boolean countBytes) {
        Buffer buffer;
        if (countBytes) {
            buffer = new Buffer();
        } else {
            buffer = sink.buffer();
        }
        int size = this.encodedNames.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                buffer.writeByte(38);
            }
            buffer.writeUtf8(this.encodedNames.get(i));
            buffer.writeByte(61);
            buffer.writeUtf8(this.encodedValues.get(i));
        }
        if (!countBytes) {
            return 0L;
        }
        long size2 = buffer.size();
        buffer.clear();
        return size2;
    }

    public static final class Builder {
        private final Charset charset;
        private final List<String> names;
        private final List<String> values;

        public Builder() {
            this(null);
        }

        public Builder(Charset charset) {
            this.names = new ArrayList();
            this.values = new ArrayList();
            this.charset = charset;
        }

        public Builder add(String name, String value) {
            if (name == null) {
                throw new NullPointerException("name == null");
            }
            if (value == null) {
                throw new NullPointerException("value == null");
            }
            this.names.add(Util.canonicalize(name, " !\"#$&'()+,/:;<=>?@[\\]^`{|}~", false, false, false, true, this.charset));
            this.values.add(Util.canonicalize(value, " !\"#$&'()+,/:;<=>?@[\\]^`{|}~", false, false, false, true, this.charset));
            return this;
        }

        public Builder addEncoded(String name, String value) {
            if (name == null) {
                throw new NullPointerException("name == null");
            }
            if (value == null) {
                throw new NullPointerException("value == null");
            }
            this.names.add(Util.canonicalize(name, " !\"#$&'()+,/:;<=>?@[\\]^`{|}~", true, false, true, true, this.charset));
            this.values.add(Util.canonicalize(value, " !\"#$&'()+,/:;<=>?@[\\]^`{|}~", true, false, true, true, this.charset));
            return this;
        }

        public FormBody build() {
            return new FormBody(this.names, this.values);
        }
    }
}
