package com.google.sqgson;

import com.google.sqgson.internal.bind.util.ISO8601Utils;
import com.google.sqgson.stream.JsonReader;
import com.google.sqgson.stream.JsonToken;
import com.google.sqgson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
final class DefaultDateTypeAdapter extends TypeAdapter<Date> {
    private static final String SIMPLE_NAME = "DefaultDateTypeAdapter";
    private final Class<? extends Date> dateType;
    private final DateFormat enUsFormat;
    private final DateFormat localFormat;

    DefaultDateTypeAdapter(Class<? extends Date> cls) {
        this(cls, DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    DefaultDateTypeAdapter(Class<? extends Date> cls, String str) {
        this(cls, new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    DefaultDateTypeAdapter(Class<? extends Date> cls, int i) {
        this(cls, DateFormat.getDateInstance(i, Locale.US), DateFormat.getDateInstance(i));
    }

    public DefaultDateTypeAdapter(int i, int i2) {
        this((Class<? extends Date>) Date.class, DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    public DefaultDateTypeAdapter(Class<? extends Date> cls, int i, int i2) {
        this(cls, DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    DefaultDateTypeAdapter(Class<? extends Date> cls, DateFormat dateFormat, DateFormat dateFormat2) {
        if (cls != Date.class && cls != java.sql.Date.class && cls != Timestamp.class) {
            throw new IllegalArgumentException("Date type must be one of " + Date.class + ", " + Timestamp.class + ", or " + java.sql.Date.class + " but was " + cls);
        }
        this.dateType = cls;
        this.enUsFormat = dateFormat;
        this.localFormat = dateFormat2;
    }

    @Override // com.google.sqgson.TypeAdapter
    public void write(JsonWriter jsonWriter, Date date) throws IOException {
        synchronized (this.localFormat) {
            jsonWriter.value(this.enUsFormat.format(date));
        }
    }

    @Override // com.google.sqgson.TypeAdapter
    /* JADX INFO: renamed from: read, reason: avoid collision after fix types in other method */
    public Date read2(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() != JsonToken.STRING) {
            throw new JsonParseException("The date should be a string value");
        }
        Date dateDeserializeToDate = deserializeToDate(jsonReader.nextString());
        Class<? extends Date> cls = this.dateType;
        if (cls == Date.class) {
            return dateDeserializeToDate;
        }
        if (cls == Timestamp.class) {
            return new Timestamp(dateDeserializeToDate.getTime());
        }
        if (cls == java.sql.Date.class) {
            return new java.sql.Date(dateDeserializeToDate.getTime());
        }
        throw new AssertionError();
    }

    private Date deserializeToDate(String str) {
        Date date;
        synchronized (this.localFormat) {
            try {
                try {
                    try {
                        date = this.localFormat.parse(str);
                    } catch (ParseException e) {
                        throw new JsonSyntaxException(str, e);
                    }
                } catch (ParseException unused) {
                    return ISO8601Utils.parse(str, new ParsePosition(0));
                }
            } catch (ParseException unused2) {
                return this.enUsFormat.parse(str);
            }
        }
        return date;
    }

    public String toString() {
        return SIMPLE_NAME + '(' + this.localFormat.getClass().getSimpleName() + ')';
    }
}
