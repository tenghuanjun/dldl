package com.alicom.tools.networking;

import android.util.Log;
import com.alipay.sdk.packet.e;
import com.alipay.sdk.sys.a;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class PopRequest extends RequestMode {
    public static final SimpleDateFormat POP_REQUEST_DATE_FORMAT;

    @SerializationName("Action")
    private String Action;
    private String accessKeySecret;

    @SerializationName("Timestamp")
    private String timestamp = POP_REQUEST_DATE_FORMAT.format(new Date());

    @SerializationName("SignatureMethod")
    private String signatureMethod = "HMAC-SHA1";

    @SerializationName("SignatureNonce")
    private String SignatureNonce = UUID.randomUUID().toString();

    @SerializationName("SignatureVersion")
    private String SignatureVersion = "1.0";

    @SerializationName(e.e)
    private String Version = "2017-05-25";

    @SerializationName("Format")
    private String Format = "JSON";

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        POP_REQUEST_DATE_FORMAT = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("O"));
    }

    @Override // com.alicom.tools.networking.RequestMode
    public String buildSignByAnnotation() throws IOException {
        List<Field> allDeclaredFields = ParamsUtils.getAllDeclaredFields(getClass());
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        TreeMap treeMap = new TreeMap();
        for (Field field : allDeclaredFields) {
            SerializationName serializationName = (SerializationName) field.getAnnotation(SerializationName.class);
            if (serializationName != null) {
                String strValue = serializationName.value();
                field.setAccessible(true);
                try {
                    Object obj = field.get(this);
                    if (obj != null) {
                        treeMap.put(strValue, obj);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            sb2.append(a.b);
            sb2.append(ParamsUtils.specialUrlEncode((String) entry.getKey()));
            sb2.append("=");
            sb2.append(ParamsUtils.specialUrlEncode(entry.getValue() == null ? "" : entry.getValue().toString()));
        }
        sb.append("POST");
        sb.append(a.b);
        sb.append(ParamsUtils.specialUrlEncode("/"));
        sb.append(a.b);
        sb.append(ParamsUtils.specialUrlEncode(sb2.toString().substring(1)));
        if (!isSign()) {
            Log.d("param:", sb2.toString());
            return sb2.toString();
        }
        return ((Object) sb2) + "&Signature=" + ParamsUtils.specialUrlEncode(ParamsUtils.sign(sb, this.accessKeySecret + a.b));
    }

    @Override // com.alicom.tools.networking.RequestMode
    public String buildSignByListFields() throws IOException {
        List<Field> allDeclaredFields = ParamsUtils.getAllDeclaredFields(getClass());
        this.fileds.add("Action");
        this.fileds.add("Timestamp");
        this.fileds.add("SignatureMethod");
        this.fileds.add("SignatureNonce");
        this.fileds.add(e.e);
        this.fileds.add("Format");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        TreeMap treeMap = new TreeMap();
        for (Field field : allDeclaredFields) {
            SerializationName serializationName = (SerializationName) field.getAnnotation(SerializationName.class);
            if (serializationName != null) {
                String strValue = serializationName.value();
                if (this.fileds.contains(strValue)) {
                    field.setAccessible(true);
                    try {
                        Object obj = field.get(this);
                        if (obj != null) {
                            treeMap.put(strValue, obj);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            sb2.append(a.b);
            sb2.append(ParamsUtils.specialUrlEncode((String) entry.getKey()));
            sb2.append("=");
            sb2.append(ParamsUtils.specialUrlEncode(entry.getValue() == null ? "" : entry.getValue().toString()));
        }
        sb.append("POST");
        sb.append(a.b);
        sb.append(ParamsUtils.specialUrlEncode("/"));
        sb.append(a.b);
        sb.append(ParamsUtils.specialUrlEncode(sb2.toString().substring(1)));
        if (!isSign()) {
            Log.d("param:", sb2.toString());
            return sb2.toString();
        }
        return ((Object) sb2) + "&Signature=" + ParamsUtils.specialUrlEncode(ParamsUtils.sign(sb, this.accessKeySecret + a.b));
    }

    public String getAccessKeySecret() {
        return this.accessKeySecret;
    }

    public String getAction() {
        return this.Action;
    }

    public void setAccessKeySecret(String str) {
        this.accessKeySecret = str;
    }

    public void setAction(String str) {
        this.Action = str;
    }
}
