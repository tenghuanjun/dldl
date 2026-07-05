package com.alicom.tools.networking;

import android.util.Log;
import com.alibaba.sdk.android.oss.common.RequestParameters;
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
public abstract class Request {
    public static final SimpleDateFormat POP_REQUEST_DATE_FORMAT;
    public static final SimpleDateFormat REQUEST_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @SerializationName("Action")
    private String Action;
    private String accessKeySecret;
    private String baseUrl;
    protected String requestMethod;
    private String sign;
    protected String method = null;

    @SerializationName("Timestamp")
    private String timestamp = POP_REQUEST_DATE_FORMAT.format(new Date());
    protected boolean isSign = true;

    @SerializationName("SignatureMethod")
    private String signatureMethod = "HMAC-SHA1";

    @SerializationName("SignatureNonce")
    private String SignatureNonce = UUID.randomUUID().toString();

    @SerializationName("SignatureVersion")
    private String SignatureVersion = "1.0";

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        POP_REQUEST_DATE_FORMAT = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("O"));
    }

    public String buildPopRequestParamas() {
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

    public String buildTopRequestParamas() throws IOException {
        String strValue;
        List<Field> allDeclaredFields = ParamsUtils.getAllDeclaredFields(getClass());
        StringBuilder sb = new StringBuilder();
        TreeMap treeMap = new TreeMap();
        for (Field field : allDeclaredFields) {
            SerializationName serializationName = (SerializationName) field.getAnnotation(SerializationName.class);
            if (serializationName != null && RequestParameters.SIGNATURE != (strValue = serializationName.value()) && (isSign() || (strValue.toLowerCase().indexOf("sign") <= -1 && "accessKeySecret" != strValue))) {
                field.setAccessible(true);
                try {
                    Object obj = field.get(this);
                    if (obj != null) {
                        treeMap.put(strValue, obj.toString());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            sb.append(a.b);
            sb.append(ParamsUtils.specialUrlEncode((String) entry.getKey()));
            sb.append("=");
            sb.append(ParamsUtils.specialUrlEncode(entry.getValue() == null ? "" : (String) entry.getValue()));
        }
        if (!this.isSign) {
            return sb.toString();
        }
        return ((Object) sb) + "&sign=" + ParamsUtils.specialUrlEncode(ParamsUtils.signTopRequest(treeMap, this.accessKeySecret, this.signatureMethod));
    }

    public String getAccessKeySecret() {
        return this.accessKeySecret;
    }

    public String getAction() {
        return this.Action;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getMethod() {
        return this.method;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public String getSign() {
        return this.sign;
    }

    public boolean isSign() {
        return this.isSign;
    }

    public void setAccessKeySecret(String str) {
        this.accessKeySecret = str;
    }

    public void setAction(String str) {
        this.Action = str;
    }

    public void setBaseUrl(String str) {
        this.baseUrl = str;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public void setRequestMethod(String str) {
        this.requestMethod = str;
    }

    public void setSign(boolean z) {
        this.isSign = z;
    }
}
