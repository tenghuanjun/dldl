package org.json.simple;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public class JSONValue {
    public static String escape(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        escape(str, stringBuffer);
        return stringBuffer.toString();
    }

    static void escape(String str, StringBuffer stringBuffer) {
        String upperCase;
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"') {
                upperCase = "\\\"";
            } else if (cCharAt == '/') {
                upperCase = "\\/";
            } else if (cCharAt != '\\') {
                switch (cCharAt) {
                    case '\b':
                        upperCase = "\\b";
                        break;
                    case '\t':
                        upperCase = "\\t";
                        break;
                    case '\n':
                        upperCase = "\\n";
                        break;
                    default:
                        switch (cCharAt) {
                            case '\f':
                                upperCase = "\\f";
                                break;
                            case '\r':
                                upperCase = "\\r";
                                break;
                            default:
                                if ((cCharAt < 0 || cCharAt > 31) && ((cCharAt < 127 || cCharAt > 159) && (cCharAt < 8192 || cCharAt > 8447))) {
                                    stringBuffer.append(cCharAt);
                                } else {
                                    String hexString = Integer.toHexString(cCharAt);
                                    stringBuffer.append("\\u");
                                    for (int i2 = 0; i2 < 4 - hexString.length(); i2++) {
                                        stringBuffer.append('0');
                                    }
                                    upperCase = hexString.toUpperCase();
                                }
                                break;
                        }
                        break;
                }
            } else {
                upperCase = "\\\\";
            }
            stringBuffer.append(upperCase);
        }
    }

    public static Object parse(Reader reader) {
        try {
            return new JSONParser().parse(reader);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object parse(String str) {
        return parse(new StringReader(str));
    }

    public static Object parseWithException(Reader reader) throws ParseException, IOException {
        return new JSONParser().parse(reader);
    }

    public static Object parseWithException(String str) throws ParseException {
        return new JSONParser().parse(str);
    }

    public static String toJSONString(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof String) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("\"");
            stringBuffer.append(escape((String) obj));
            stringBuffer.append("\"");
            return stringBuffer.toString();
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            return (d.isInfinite() || d.isNaN()) ? "null" : obj.toString();
        }
        if (obj instanceof Float) {
            Float f = (Float) obj;
            return (f.isInfinite() || f.isNaN()) ? "null" : obj.toString();
        }
        if (!(obj instanceof Number) && !(obj instanceof Boolean)) {
            return obj instanceof JSONAware ? ((JSONAware) obj).toJSONString() : obj instanceof Map ? JSONObject.toJSONString((Map) obj) : obj instanceof List ? JSONArray.toJSONString((List) obj) : obj.toString();
        }
        return obj.toString();
    }

    public static void writeJSONString(Object obj, Writer writer) throws IOException {
        if (obj == null) {
            writer.write("null");
            return;
        }
        if (obj instanceof String) {
            writer.write(34);
            writer.write(escape((String) obj));
            writer.write(34);
            return;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            writer.write((d.isInfinite() || d.isNaN()) ? "null" : obj.toString());
            return;
        }
        if (obj instanceof Float) {
            Float f = (Float) obj;
            writer.write((f.isInfinite() || f.isNaN()) ? "null" : obj.toString());
            return;
        }
        if (obj instanceof Number) {
            writer.write(obj.toString());
            return;
        }
        if (obj instanceof Boolean) {
            writer.write(obj.toString());
            return;
        }
        if (obj instanceof JSONStreamAware) {
            ((JSONStreamAware) obj).writeJSONString(writer);
            return;
        }
        if (obj instanceof JSONAware) {
            writer.write(((JSONAware) obj).toJSONString());
            return;
        }
        if (obj instanceof Map) {
            JSONObject.writeJSONString((Map) obj, writer);
        } else if (obj instanceof List) {
            JSONArray.writeJSONString((List) obj, writer);
        } else {
            writer.write(obj.toString());
        }
    }
}
