package com.sq.tools.network.request;

import android.content.Context;
import android.text.TextUtils;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sq.tools.Logger;
import com.sq.tools.encrypt.EncryptApi;
import com.sq.tools.network.ContentType;
import com.sqwan.common.constants.SqConstants;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class RequestTools {
    public String contentType = ContentType.JSON;
    protected boolean isSign = true;
    protected HashMap<String, String> transparent;

    public interface RequestAsync {
        void onReady();
    }

    protected boolean isReady() {
        return true;
    }

    public String toPostParam(Context context) {
        return toPostParam(context, true);
    }

    public String toPostParam(Context context, boolean z) {
        if (ContentType.FORM.equals(this.contentType)) {
            return toFormParam(context, z);
        }
        return toJsonParam(context);
    }

    public String toGetParam(Context context) {
        return toPostParam(context);
    }

    protected String toFormParam(Context context, boolean z) {
        JSONObject jSONObjectGenerateJsonByFields = generateJsonByFields(this.transparent);
        Iterator<String> itKeys = jSONObjectGenerateJsonByFields.keys();
        StringBuilder sb = new StringBuilder();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (z) {
                try {
                    sb.append(URLEncoder.encode(next));
                    sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                    sb.append(URLEncoder.encode(jSONObjectGenerateJsonByFields.getString(next)));
                    sb.append("&");
                } catch (JSONException e) {
                    Logger.warning("RequestTools to Form Param Exception", e);
                }
            } else {
                sb.append(next);
                sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                sb.append(jSONObjectGenerateJsonByFields.getString(next));
                sb.append("&");
            }
        }
        sb.setLength(sb.length() - 1);
        if (this.isSign) {
            String strSign = EncryptApi.sign(context, jSONObjectGenerateJsonByFields);
            if (z) {
                strSign = URLEncoder.encode(strSign);
            }
            sb.append("&sign=");
            sb.append(strSign);
        }
        return sb.toString();
    }

    protected final String toJsonParam(Context context) {
        JSONObject jSONObjectGenerateJsonByFields = generateJsonByFields(this.transparent);
        if (!this.isSign) {
            return jSONObjectGenerateJsonByFields.toString();
        }
        try {
            jSONObjectGenerateJsonByFields.put(SqConstants.SIGN, EncryptApi.sign(context, jSONObjectGenerateJsonByFields));
        } catch (JSONException unused) {
            Logger.error("Put Sign in Json failed, this could cause network request fail", new Object[0]);
        }
        return jSONObjectGenerateJsonByFields.toString();
    }

    private JSONObject generateJsonByFields(HashMap<String, String> map) {
        ArrayList<Field> arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(getClass().getDeclaredFields()));
        arrayList.addAll(Arrays.asList(getClass().getFields()));
        arrayList.addAll(Arrays.asList(getClass().getInterfaces().getClass().getDeclaredFields()));
        JSONObject jSONObject = new JSONObject();
        if (map != null && map.size() != 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey())) {
                    try {
                        jSONObject.put(entry.getKey(), entry.getValue() == null ? "" : entry.getValue());
                    } catch (JSONException e) {
                        Logger.error("Put values from transparent map in JSONObject", e);
                    }
                }
            }
        }
        for (Field field : arrayList) {
            RequestParam requestParam = (RequestParam) field.getAnnotation(RequestParam.class);
            if (requestParam != null) {
                field.setAccessible(true);
                Object obj = null;
                try {
                    obj = field.get(this);
                } catch (IllegalAccessException unused) {
                }
                if (obj != null) {
                    String strValue = requestParam.value();
                    if (TextUtils.isEmpty(strValue)) {
                        strValue = field.getName();
                    }
                    try {
                        jSONObject.put(strValue, obj);
                    } catch (JSONException e2) {
                        Logger.error("object with %s marked can not put in JSONObject", e2);
                    }
                }
            }
        }
        return jSONObject;
    }

    protected void async(RequestAsync requestAsync) {
        requestAsync.onReady();
    }
}
