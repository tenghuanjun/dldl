package com.volcengine.g;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.volcengine.common.innerapi.IJsonConverter;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class b implements IJsonConverter {
    private final Gson a = new Gson().newBuilder().registerTypeAdapter(Double.class, a()).registerTypeAdapter(Float.class, b()).create();

    class a extends TypeAdapter<Number> {
        a(b bVar) {
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Float.valueOf((float) jsonReader.nextDouble());
            }
            jsonReader.nextNull();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            if (number == null) {
                jsonWriter.nullValue();
                return;
            }
            double dFloatValue = number.floatValue();
            if (Double.isNaN(dFloatValue) || Double.isInfinite(dFloatValue)) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(number);
            }
        }
    }

    /* JADX INFO: renamed from: com.volcengine.g.b$b, reason: collision with other inner class name */
    class C0207b extends TypeAdapter<Number> {
        C0207b(b bVar) {
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Double read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Double.valueOf(jsonReader.nextDouble());
            }
            jsonReader.nextNull();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            if (number == null) {
                jsonWriter.nullValue();
                return;
            }
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(number);
            }
        }
    }

    private TypeAdapter<Number> a() {
        return new C0207b(this);
    }

    private TypeAdapter<Number> b() {
        return new a(this);
    }

    @Override // com.volcengine.common.innerapi.IJsonConverter
    public <T> T fromJson(String str, Class<T> cls) {
        if (str == null) {
            return null;
        }
        try {
            return (T) this.a.fromJson(str, (Class) cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.volcengine.common.innerapi.IJsonConverter
    public <T> T fromJson(String str, Type type) {
        try {
            return (T) this.a.fromJson(str, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.volcengine.common.innerapi.IJsonConverter
    public <T> String toJson(T t) {
        if (t == null) {
            return null;
        }
        return this.a.toJson(t);
    }
}
