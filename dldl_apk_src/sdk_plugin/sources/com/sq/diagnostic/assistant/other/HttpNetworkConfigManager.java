package com.sq.diagnostic.assistant.other;

import android.content.Context;
import com.sq.diagnostic.assistant.http.HttpData;
import com.sq.diagnostic.assistant.http.HttpManager;
import com.sq.diagnostic.assistant.http.OnHttpListener;
import com.sq.diagnostic.assistant.log.SQLogUtils;
import com.sq.diagnostic.assistant.log.utils.DiagnosticAssistantSpUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpNetworkConfigManager {
    private static final String TAG = "HttpNetworkConfigManager";
    private static final List<TcpingBean> TCPING_LIST = new ArrayList();
    private static final List<MtrBean> MTR_LIST = new ArrayList();

    public static void init(final Context context) {
        TCPING_LIST.clear();
        MTR_LIST.clear();
        HttpManager.getNetworkConfig(new OnHttpListener<JSONObject>() { // from class: com.sq.diagnostic.assistant.other.HttpNetworkConfigManager.1
            @Override // com.sq.diagnostic.assistant.http.OnHttpListener
            public void onSuccess(HttpData<JSONObject> httpData) {
                SQLogUtils.i(HttpNetworkConfigManager.TAG, "获取诊断助手网络配置成功");
                if (httpData == null) {
                    return;
                }
                JSONObject data = httpData.getData();
                if (data != null) {
                    HttpNetworkConfigManager.parseHttpConfigJson(data);
                    DiagnosticAssistantSpUtils.putString(context, DiagnosticAssistantSpUtils.SP_CACHE_JSON_KEY, String.valueOf(data));
                } else {
                    SQLogUtils.i(HttpNetworkConfigManager.TAG, "获取诊断助手网络配置成功，但是数据为空");
                }
            }

            @Override // com.sq.diagnostic.assistant.http.OnHttpListener
            public void onFailed(int i, String str) {
                SQLogUtils.i(HttpNetworkConfigManager.TAG, "诊断助手网络配置请求失败，错误码：" + i + "，错误信息：" + str);
                String string = DiagnosticAssistantSpUtils.getString(context, DiagnosticAssistantSpUtils.SP_CACHE_JSON_KEY, "");
                if (string != null) {
                    try {
                        HttpNetworkConfigManager.parseHttpConfigJson(new JSONObject(string));
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        SQLogUtils.i(HttpNetworkConfigManager.TAG, "解析缓存的网络配置缓存列表数据失败：" + string);
                        return;
                    }
                }
                SQLogUtils.i(HttpNetworkConfigManager.TAG, "诊断助手网络配置缓存数据为空");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void parseHttpConfigJson(JSONObject jSONObject) {
        if (TCPING_LIST.isEmpty()) {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("tcping_list");
            SQLogUtils.i(TAG, "获取 tcping 列表成功，tcping_list：" + jSONArrayOptJSONArray);
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                TCPING_LIST.add(new TcpingBean(jSONObjectOptJSONObject.optString("host"), jSONObjectOptJSONObject.optInt("port")));
            }
        }
        if (MTR_LIST.isEmpty()) {
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("mtr_list");
            SQLogUtils.i(TAG, "获取 mtr 列表成功，mtr_list：" + jSONArrayOptJSONArray2);
            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
                String strOptString = jSONObjectOptJSONObject2.optString("host");
                int iOptInt = jSONObjectOptJSONObject2.optInt("ttl");
                if (iOptInt == 0) {
                    iOptInt = 20;
                }
                MTR_LIST.add(new MtrBean(strOptString, iOptInt));
            }
        }
    }

    public static List<TcpingBean> getTcpingList() {
        return TCPING_LIST;
    }

    public static List<MtrBean> getMtrList() {
        return MTR_LIST;
    }

    public static class TcpingBean {
        private String host;
        private int port;

        public TcpingBean(String str, int i) {
            this.host = str;
            this.port = i;
        }

        public String getHost() {
            return this.host;
        }

        public int getPort() {
            return this.port;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            TcpingBean tcpingBean = (TcpingBean) obj;
            if (this.port != tcpingBean.port) {
                return false;
            }
            String str = this.host;
            String str2 = tcpingBean.host;
            return str != null ? str.equals(str2) : str2 == null;
        }

        public int hashCode() {
            String str = this.host;
            return ((str != null ? str.hashCode() : 0) * 31) + this.port;
        }
    }

    public static class MtrBean {
        private String host;
        private int ttl;

        public MtrBean(String str, int i) {
            this.host = str;
            this.ttl = i;
        }

        public String getHost() {
            return this.host;
        }

        public int getTtl() {
            return this.ttl;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            MtrBean mtrBean = (MtrBean) obj;
            if (this.ttl != mtrBean.ttl) {
                return false;
            }
            String str = this.host;
            String str2 = mtrBean.host;
            return str != null ? str.equals(str2) : str2 == null;
        }

        public int hashCode() {
            String str = this.host;
            return ((str != null ? str.hashCode() : 0) * 31) + this.ttl;
        }
    }
}
