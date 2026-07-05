package com.alicom.tools.networking;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.alipay.sdk.packet.e;
import com.alipay.sdk.sys.a;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class TopRequest extends RequestMode {
    public static final SimpleDateFormat REQUEST_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @SerializationName("accessKeySecret")
    private String accessKeySecret;

    @SerializationName(RequestParameters.SIGNATURE)
    private String sign;

    @SerializationName(e.q)
    protected String method = null;

    @SerializationName("timestamp")
    private String timestamp = REQUEST_DATE_FORMAT.format(new Date());

    @SerializationName("sign_method")
    protected String sign_method = "hmac";

    @Override // com.alicom.tools.networking.RequestMode
    public String buildSignByAnnotation() throws IOException {
        String strValue;
        List<Field> allDeclaredFields = ParamsUtils.getAllDeclaredFields(getClass());
        StringBuilder sb = new StringBuilder();
        TreeMap treeMap = new TreeMap();
        for (Field field : allDeclaredFields) {
            SerializationName serializationName = (SerializationName) field.getAnnotation(SerializationName.class);
            if (serializationName != null && RequestParameters.SIGNATURE != (strValue = serializationName.value())) {
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
        if (!isSign()) {
            return sb.toString();
        }
        return ((Object) sb) + "&sign=" + ParamsUtils.specialUrlEncode(ParamsUtils.signTopRequest(treeMap, this.accessKeySecret, this.sign_method));
    }

    @Override // com.alicom.tools.networking.RequestMode
    public String buildSignByListFields() throws IOException {
        String strValue;
        List<Field> allDeclaredFields = ParamsUtils.getAllDeclaredFields(getClass());
        this.fileds.add("sign");
        this.fileds.add(e.q);
        this.fileds.add("timestamp");
        this.fileds.add("sign_method");
        StringBuilder sb = new StringBuilder();
        TreeMap treeMap = new TreeMap();
        for (Field field : allDeclaredFields) {
            SerializationName serializationName = (SerializationName) field.getAnnotation(SerializationName.class);
            if (serializationName != null && RequestParameters.SIGNATURE != (strValue = serializationName.value()) && this.fileds.contains(strValue)) {
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
        if (!isSign()) {
            return sb.toString();
        }
        return ((Object) sb) + "&sign=" + ParamsUtils.specialUrlEncode(ParamsUtils.signTopRequest(treeMap, this.accessKeySecret, this.sign_method));
    }

    public String getAccessKeySecret() {
        return this.accessKeySecret;
    }

    public String getMethod() {
        return this.method;
    }

    public String getSign() {
        return this.sign;
    }

    public String getSign_method() {
        return this.sign_method;
    }

    public void setAccessKeySecret(String str) {
        this.accessKeySecret = str;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setSign_method(String str) {
        this.sign_method = str;
    }
}
