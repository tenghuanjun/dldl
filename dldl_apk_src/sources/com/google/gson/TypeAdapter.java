package com.google.gson;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class TypeAdapter<T> {
    /* JADX INFO: renamed from: read */
    public abstract T read2(JsonReader jsonReader) throws IOException;

    public abstract void write(JsonWriter jsonWriter, T t) throws IOException;

    final void toJson(Writer writer, T t) throws IOException {
        write(new JsonWriter(writer), t);
    }

    public final TypeAdapter<T> nullSafe() {
        return new TypeAdapter<T>() { // from class: com.google.gson.TypeAdapter.1
            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, T t) throws IOException {
                if (t == null) {
                    jsonWriter.nullValue();
                } else {
                    TypeAdapter.this.write(jsonWriter, t);
                }
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: read */
            public T read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return (T) TypeAdapter.this.read2(jsonReader);
            }
        };
    }

    final String toJson(T t) throws IOException {
        StringWriter stringWriter = new StringWriter();
        toJson(stringWriter, t);
        return stringWriter.toString();
    }

    final JsonElement toJsonTree(T t) {
        try {
            JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
            jsonTreeWriter.setLenient(true);
            write(jsonTreeWriter, t);
            return jsonTreeWriter.get();
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    final T fromJson(Reader reader) throws IOException {
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(true);
        return read2(jsonReader);
    }

    final T fromJson(String str) throws IOException {
        return fromJson(new StringReader(str));
    }

    final T fromJsonTree(JsonElement jsonElement) {
        try {
            JsonTreeReader jsonTreeReader = new JsonTreeReader(jsonElement);
            jsonTreeReader.setLenient(true);
            return read2(jsonTreeReader);
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }
}
