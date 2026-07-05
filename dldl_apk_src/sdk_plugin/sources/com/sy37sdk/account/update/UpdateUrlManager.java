package com.sy37sdk.account.update;

import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqwan.common.request.CommonParamsV1;
import com.sy37sdk.account.UrlConstant;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UpdateUrlManager {
    public static HashMap<String, String> apiMap = new HashMap<>();
    public ArrayList<String> apiUrls = new ArrayList<>();

    public void reqUrlUpdateManager(SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.UPDATE_URL).signV3().addParamsTransformer(new CommonParamsV1()).get(sqHttpCallback);
    }

    public HashMap<String, String> parse(JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("api_infos");
        if (jSONArrayOptJSONArray == null) {
            return null;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                try {
                    String string = jSONObjectOptJSONObject.getString("api_key");
                    String string2 = jSONObjectOptJSONObject.getString("api_info");
                    if (!"x_secure_key".equals(string)) {
                        apiMap.put(string, string2);
                        this.apiUrls.add(string2);
                    }
                } catch (JSONException unused) {
                }
            }
        }
        return apiMap;
    }

    public HashMap<String, String> getApiUrlMap() {
        return apiMap;
    }

    public ArrayList<String> getApiUrls() {
        return this.apiUrls;
    }
}
