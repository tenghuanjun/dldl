package com.jiguang.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;
import layaair.game.IMarket.IPluginRuntimeProxy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class RuntimeProxy implements IPluginRuntimeProxy {
    private String TAG = "RuntimeProxy";
    private Activity mActivity;

    public String getExpansionMainPath() {
        return "";
    }

    public String getExpansionPatchPath() {
        return "";
    }

    @Override // layaair.game.IMarket.IPluginRuntimeProxy
    public Object laya_invoke_Method(String str, Bundle bundle) {
        return null;
    }

    @Override // layaair.game.IMarket.IPluginRuntimeProxy
    public boolean laya_set_value(String str, Object obj) {
        return false;
    }

    public RuntimeProxy(Activity activity) {
        this.mActivity = null;
        this.mActivity = activity;
    }

    public String getCacheDir() {
        String[] strArrSplit = this.mActivity.getCacheDir().toString().split("/");
        String str = "";
        for (int i = 0; i < strArrSplit.length - 1; i++) {
            str = (str + strArrSplit[i]) + "/";
        }
        return str;
    }

    @Override // layaair.game.IMarket.IPluginRuntimeProxy
    public Object laya_get_value(String str) {
        Log.d(this.TAG, "laya_get_value key=" + str);
        if (str.equalsIgnoreCase("CacheDir")) {
            return getCacheDir();
        }
        if (str.equalsIgnoreCase("ExpansionMainPath")) {
            return getExpansionMainPath();
        }
        if (str.equalsIgnoreCase("ExpansionPatchPath")) {
            return getExpansionPatchPath();
        }
        return null;
    }

    @Override // layaair.game.IMarket.IPluginRuntimeProxy
    public void laya_stop_game_engine() {
        Log.d(this.TAG, "Login laya_stop_game_engine.");
    }

    @Override // layaair.game.IMarket.IPluginRuntimeProxy
    public void Login(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback) {
        Log.d(this.TAG, "Login info = " + jSONObject.toString());
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("status", 0);
            jSONObject2.put("unionUserID", "1123fff4");
            jSONObject2.put("nickName", "中南海");
            jSONObject2.put("photo", "http://www.xxx.com/xxx.jpg");
            jSONObject2.put("sptoken", "33ffffh54444dddd");
            jSONObject2.put("msg", "success");
            valueCallback.onReceiveValue(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // layaair.game.IMarket.IPluginRuntimeProxy
    public void Logout(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback) {
        Log.d(this.TAG, "Logout info = " + jSONObject.toString());
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("status", 0);
            jSONObject2.put("msg", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        valueCallback.onReceiveValue(jSONObject2);
    }

    @Override // layaair.game.IMarket.IPluginRuntimeProxy
    public void Pay(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback) {
        Log.d(this.TAG, "Logout Pay = " + jSONObject.toString());
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("status", 0);
            jSONObject2.put("msg", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        valueCallback.onReceiveValue(jSONObject2);
    }

    @Override // layaair.game.IMarket.IPluginRuntimeProxy
    public void PushIcon(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback) {
        Log.d(this.TAG, "Logout PushIcon = " + jSONObject.toString());
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("status", 0);
            jSONObject2.put("msg", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        valueCallback.onReceiveValue(jSONObject2);
    }

    @Override // layaair.game.IMarket.IPluginRuntimeProxy
    public void Share(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback) {
        Log.d(this.TAG, "Logout Share = " + jSONObject.toString());
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("status", 0);
            jSONObject2.put("msg", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        valueCallback.onReceiveValue(jSONObject2);
    }

    @Override // layaair.game.IMarket.IPluginRuntimeProxy
    public void OpenBBS(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback) {
        Log.d(this.TAG, "Logout OpenBBS = " + jSONObject.toString());
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("status", 0);
            jSONObject2.put("msg", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        valueCallback.onReceiveValue(jSONObject2);
    }

    @Override // layaair.game.IMarket.IPluginRuntimeProxy
    public void GetFriendsList(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback) {
        Log.d(this.TAG, "Logout GetFriendsList = " + jSONObject.toString());
        JSONObject jSONObject2 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("userId", "1111111");
            jSONObject3.put("nickName", "xiaoming");
            jSONObject3.put("photo", "http://xxx.com/xxx.jpg");
            jSONObject3.put("sex", "0");
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("userId", "1111111");
            jSONObject4.put("nickName", "xiaoming");
            jSONObject4.put("photo", "http://xxx.com/xxx.jpg");
            jSONObject4.put("sex", "0");
            jSONArray.put(jSONObject3);
            jSONArray.put(jSONObject4);
            jSONObject2.put("status", 0);
            jSONObject2.put("msg", "success");
            jSONObject2.put("friends", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        valueCallback.onReceiveValue(jSONObject2);
    }

    @Override // layaair.game.IMarket.IPluginRuntimeProxy
    public void SendMessageToPlatform(JSONObject jSONObject, ValueCallback<JSONObject> valueCallback) {
        Log.d(this.TAG, "Logout SendMessageToPlatform = " + jSONObject.toString());
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("status", 0);
            jSONObject2.put("msg", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        valueCallback.onReceiveValue(jSONObject2);
    }
}
