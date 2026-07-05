package com.sq.diagnostic.assistant.http;

import android.text.TextUtils;
import com.sq.diagnostic.assistant.DiagnosticAssistant;
import com.sq.diagnostic.assistant.http.HttpUtils;
import com.sq.diagnostic.assistant.http.entity.UploadLogRequest;
import com.sq.diagnostic.assistant.log.impl.ErrorStats;
import com.sq.diagnostic.assistant.other.IBusinessParameters;
import com.sqwan.bugless.core.Constant;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.msdk.BaseSQwanCore;
import com.sqwan.msdk.api.IMUrl;
import com.taptap.sdk.db.constant.Common;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpManager {
    public static void uploadLog(UploadLogRequest uploadLogRequest, String str, final OnHttpListener<JSONObject> onHttpListener) {
        if (TextUtils.isEmpty(str) || !new File(str).isFile()) {
            if (onHttpListener != null) {
                onHttpListener.onFailed(10005, ErrorStats.ERROR_MSG_LOG_ZIP_FILE_NOT_FOUND);
                return;
            }
            return;
        }
        IBusinessParameters businessParameters = DiagnosticAssistant.getInstance().getBusinessParameters();
        HashMap map = new HashMap();
        map.put("sdk_log", new File(str));
        map.put("log_start_date", uploadLogRequest.logStartDate);
        map.put("log_end_date", uploadLogRequest.logEndDate);
        if (businessParameters != null) {
            map.put("gid", businessParameters.getGid());
            map.put("pid", businessParameters.getPid());
            map.put("sversion", businessParameters.getSdkVersion());
            map.put(Constant.PKG_NAME, businessParameters.getPackageName());
            map.put(Constant.APP_NAME, businessParameters.getGameName());
            map.put("os", IMUrl.OS);
            map.put("dev", businessParameters.getDeviceId());
            map.put(Common.Predefined.SUB_TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000));
            map.put("uid", businessParameters.getUserId());
            map.put("uname", businessParameters.getUserName());
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("gwversion", businessParameters.getGwVersion());
                jSONObject.put("sversion", businessParameters.getSdkVersion());
                jSONObject.put(Constant.DEV_SYS_VERSION, businessParameters.getSystemVersion());
                jSONObject.put("brand", businessParameters.getPhoneBrand());
                jSONObject.put(Constant.DEV_MODEL, businessParameters.getPhoneModel());
                jSONObject.put(Constant.PKG_VERSION_NAME, businessParameters.getVersionName());
                jSONObject.put(Constant.PKG_VERSION_CODE, businessParameters.getVersionCode());
                jSONObject.put("country", businessParameters.getCountry());
                jSONObject.put("province", businessParameters.getProvince());
                jSONObject.put("city", businessParameters.getCity());
                jSONObject.put(Constant.DEV_NETWORK, businessParameters.getNetworkType());
                jSONObject.put("ip", businessParameters.getIpAddress());
                jSONObject.put("carrier", businessParameters.getCarrier());
                jSONObject.put(BaseSQwanCore.INFO_SERVERID, businessParameters.getServerId());
                jSONObject.put(BaseSQwanCore.INFO_SERVERNAME, businessParameters.getServerName());
                jSONObject.put(BaseSQwanCore.INFO_ROLEID, businessParameters.getRoleId());
                jSONObject.put(BaseSQwanCore.INFO_ROLENAME, businessParameters.getRoleName());
                map.put("ext", jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("content", uploadLogRequest.uploadReason);
            map.put("contact", uploadLogRequest.contact);
            map.put("log_update_type", Integer.valueOf(uploadLogRequest.updateLogType));
            map.put(SqConstants.SIGN, getSignature(map.get("dev") + "-" + map.get(Common.Predefined.SUB_TIMESTAMP) + "-" + map.get(Constant.PKG_NAME)));
        }
        HttpUtils.request(HttpMethod.POST, DiagnosticAssistant.getInstance().getServerHost() + UrlContract.UPLOAD_LOG_FILE, map, new HttpUtils.HttpCallback() { // from class: com.sq.diagnostic.assistant.http.HttpManager.1
            /* JADX WARN: Type inference failed for: r4v1, types: [T, org.json.JSONObject] */
            @Override // com.sq.diagnostic.assistant.http.HttpUtils.HttpCallback
            public void onHttpSuccess(JSONObject jSONObject2) {
                if (onHttpListener == null) {
                    return;
                }
                int iOptInt = jSONObject2.optInt("code");
                String strOptString = jSONObject2.optString("message");
                ?? OptJSONObject = jSONObject2.optJSONObject(FunctionRouter.KEY_DATA);
                HttpData httpData = new HttpData();
                httpData.code = iOptInt;
                httpData.msg = strOptString;
                httpData.data = OptJSONObject;
                if (iOptInt == 0) {
                    onHttpListener.onSuccess(httpData);
                } else {
                    onHttpListener.onFailed(iOptInt, strOptString);
                }
            }

            @Override // com.sq.diagnostic.assistant.http.HttpUtils.HttpCallback
            public void onHttpFail(Exception exc) {
                OnHttpListener onHttpListener2 = onHttpListener;
                if (onHttpListener2 == null) {
                    return;
                }
                onHttpListener2.onFailed(10006, exc.getMessage());
            }
        });
    }

    public static void getNetworkConfig(final OnHttpListener<JSONObject> onHttpListener) {
        IBusinessParameters businessParameters = DiagnosticAssistant.getInstance().getBusinessParameters();
        if (businessParameters == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("gid", businessParameters.getGid());
        map.put("pid", businessParameters.getPid());
        map.put("referer", businessParameters.getReferer());
        map.put(Common.Predefined.SUB_TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000));
        map.put(SqConstants.SIGN, getSignature(map.get("pid") + "-" + map.get("gid") + "-" + map.get(Common.Predefined.SUB_TIMESTAMP)));
        HttpMethod httpMethod = HttpMethod.GET;
        StringBuilder sb = new StringBuilder();
        sb.append(DiagnosticAssistant.getInstance().getServerHost());
        sb.append(UrlContract.GET_NETWORK_CONFIG);
        HttpUtils.request(httpMethod, sb.toString(), map, new HttpUtils.HttpCallback() { // from class: com.sq.diagnostic.assistant.http.HttpManager.2
            /* JADX WARN: Type inference failed for: r4v1, types: [T, org.json.JSONObject] */
            @Override // com.sq.diagnostic.assistant.http.HttpUtils.HttpCallback
            public void onHttpSuccess(JSONObject jSONObject) {
                if (onHttpListener == null) {
                    return;
                }
                int iOptInt = jSONObject.optInt("code");
                String strOptString = jSONObject.optString("message");
                ?? OptJSONObject = jSONObject.optJSONObject(FunctionRouter.KEY_DATA);
                HttpData httpData = new HttpData();
                httpData.code = iOptInt;
                httpData.msg = strOptString;
                httpData.data = OptJSONObject;
                if (iOptInt == 0) {
                    onHttpListener.onSuccess(httpData);
                } else {
                    onHttpListener.onFailed(iOptInt, strOptString);
                }
            }

            @Override // com.sq.diagnostic.assistant.http.HttpUtils.HttpCallback
            public void onHttpFail(Exception exc) {
                OnHttpListener onHttpListener2 = onHttpListener;
                if (onHttpListener2 == null) {
                    return;
                }
                onHttpListener2.onFailed(ErrorStats.ERROR_CODE_GET_PING, exc.getMessage());
            }
        });
    }

    public static void checkLogRetrievalTask(final OnHttpListener<JSONObject> onHttpListener) {
        IBusinessParameters businessParameters = DiagnosticAssistant.getInstance().getBusinessParameters();
        if (businessParameters == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("gid", businessParameters.getGid());
        map.put("pid", businessParameters.getPid());
        map.put("dev", businessParameters.getDeviceId());
        map.put("referer", businessParameters.getReferer());
        map.put(Common.Predefined.SUB_TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000));
        map.put(SqConstants.SIGN, getSignature(map.get("pid") + "-" + map.get("gid") + "-" + map.get(Common.Predefined.SUB_TIMESTAMP)));
        HttpMethod httpMethod = HttpMethod.GET;
        StringBuilder sb = new StringBuilder();
        sb.append(DiagnosticAssistant.getInstance().getServerHost());
        sb.append(UrlContract.SDK_LOG_TASK);
        HttpUtils.request(httpMethod, sb.toString(), map, new HttpUtils.HttpCallback() { // from class: com.sq.diagnostic.assistant.http.HttpManager.3
            /* JADX WARN: Type inference failed for: r4v1, types: [T, org.json.JSONObject] */
            @Override // com.sq.diagnostic.assistant.http.HttpUtils.HttpCallback
            public void onHttpSuccess(JSONObject jSONObject) {
                if (onHttpListener == null) {
                    return;
                }
                int iOptInt = jSONObject.optInt("code");
                String strOptString = jSONObject.optString("message");
                ?? OptJSONObject = jSONObject.optJSONObject(FunctionRouter.KEY_DATA);
                HttpData httpData = new HttpData();
                httpData.code = iOptInt;
                httpData.msg = strOptString;
                httpData.data = OptJSONObject;
                if (iOptInt == 0) {
                    onHttpListener.onSuccess(httpData);
                } else {
                    onHttpListener.onFailed(iOptInt, strOptString);
                }
            }

            @Override // com.sq.diagnostic.assistant.http.HttpUtils.HttpCallback
            public void onHttpFail(Exception exc) {
                OnHttpListener onHttpListener2 = onHttpListener;
                if (onHttpListener2 == null) {
                    return;
                }
                onHttpListener2.onFailed(ErrorStats.ERROR_CODE_GET_PING, exc.getMessage());
            }
        });
    }

    public static String getSignature(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb.append("0");
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static void requestScheduleTaskConfig(final OnHttpListener<JSONArray> onHttpListener) {
        IBusinessParameters businessParameters = DiagnosticAssistant.getInstance().getBusinessParameters();
        if (businessParameters == null) {
            return;
        }
        String str = DiagnosticAssistant.getInstance().getServerHost() + UrlContract.COMMON_CONFIG;
        HashMap map = new HashMap();
        String gid = businessParameters.getGid();
        String pid = businessParameters.getPid();
        map.put("gid", gid);
        map.put("pid", pid);
        map.put(Common.Predefined.SUB_TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000));
        map.put(SqConstants.SIGN, getSignature(map.get("pid") + "-" + map.get("gid") + "-" + map.get(Common.Predefined.SUB_TIMESTAMP)));
        map.put("type", "schedule_report");
        map.put("keys", pid + "_" + gid + "," + pid + "_default,default");
        map.put("mod", "full");
        HttpUtils.request(HttpMethod.GET, str, map, new HttpUtils.HttpCallback() { // from class: com.sq.diagnostic.assistant.http.HttpManager.4
            /* JADX WARN: Type inference failed for: r4v1, types: [T, org.json.JSONArray] */
            @Override // com.sq.diagnostic.assistant.http.HttpUtils.HttpCallback
            public void onHttpSuccess(JSONObject jSONObject) {
                if (onHttpListener == null) {
                    return;
                }
                int iOptInt = jSONObject.optInt("code");
                String strOptString = jSONObject.optString("message");
                ?? OptJSONArray = jSONObject.optJSONArray(FunctionRouter.KEY_DATA);
                HttpData httpData = new HttpData();
                httpData.code = iOptInt;
                httpData.msg = strOptString;
                httpData.data = OptJSONArray;
                if (iOptInt == 0) {
                    onHttpListener.onSuccess(httpData);
                } else {
                    onHttpListener.onFailed(iOptInt, strOptString);
                }
            }

            @Override // com.sq.diagnostic.assistant.http.HttpUtils.HttpCallback
            public void onHttpFail(Exception exc) {
                OnHttpListener onHttpListener2 = onHttpListener;
                if (onHttpListener2 == null) {
                    return;
                }
                onHttpListener2.onFailed(ErrorStats.ERROR_CODE_FETCH_SCHEDULE_TASK, exc.getMessage());
            }
        });
    }

    public static void requestHelpDocConfig(final OnHttpListener<JSONArray> onHttpListener) {
        IBusinessParameters businessParameters = DiagnosticAssistant.getInstance().getBusinessParameters();
        if (businessParameters == null) {
            return;
        }
        String str = DiagnosticAssistant.getInstance().getServerHost() + UrlContract.COMMON_CONFIG;
        HashMap map = new HashMap();
        String gid = businessParameters.getGid();
        String pid = businessParameters.getPid();
        map.put("gid", gid);
        map.put("pid", pid);
        map.put(Common.Predefined.SUB_TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000));
        map.put(SqConstants.SIGN, getSignature(map.get("pid") + "-" + map.get("gid") + "-" + map.get(Common.Predefined.SUB_TIMESTAMP)));
        map.put("type", "help_page");
        map.put("mod", "tiny");
        Locale locale = Locale.getDefault();
        if (locale != null) {
            String language = locale.getLanguage();
            String country = locale.getCountry();
            if (!TextUtils.isEmpty(language)) {
                if (!TextUtils.isEmpty(language)) {
                    map.put("language", language + "_" + country);
                } else {
                    map.put("language", language);
                }
            }
        }
        HttpUtils.request(HttpMethod.GET, str, map, new HttpUtils.HttpCallback() { // from class: com.sq.diagnostic.assistant.http.HttpManager.5
            /* JADX WARN: Type inference failed for: r4v1, types: [T, org.json.JSONArray] */
            @Override // com.sq.diagnostic.assistant.http.HttpUtils.HttpCallback
            public void onHttpSuccess(JSONObject jSONObject) {
                if (onHttpListener == null) {
                    return;
                }
                int iOptInt = jSONObject.optInt("code");
                String strOptString = jSONObject.optString("message");
                ?? OptJSONArray = jSONObject.optJSONArray(FunctionRouter.KEY_DATA);
                HttpData httpData = new HttpData();
                httpData.code = iOptInt;
                httpData.msg = strOptString;
                httpData.data = OptJSONArray;
                if (iOptInt == 0) {
                    onHttpListener.onSuccess(httpData);
                } else {
                    onHttpListener.onFailed(iOptInt, strOptString);
                }
            }

            @Override // com.sq.diagnostic.assistant.http.HttpUtils.HttpCallback
            public void onHttpFail(Exception exc) {
                OnHttpListener onHttpListener2 = onHttpListener;
                if (onHttpListener2 == null) {
                    return;
                }
                onHttpListener2.onFailed(ErrorStats.ERROR_CODE_GET_HELP_DOC, exc.getMessage());
            }
        });
    }
}
