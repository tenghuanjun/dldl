package com.aliyun.aliyunface.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ss.android.socialbase.downloader.BuildConfig;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class AndroidClientConfig {
    private JSONObject algorithm;
    private JSONObject upload;
    private SceneEnv sceneEnv = new SceneEnv();
    private NavigatePage navi = new NavigatePage();
    private Coll coll = new Coll();
    private FaceTips faceTips = new FaceTips();
    private ArrayList<SDKAction> sdkActionList = new ArrayList<>();
    private DeviceSetting[] deviceSettings = new DeviceSetting[0];
    private int env = 0;
    private int ui = 991;
    private String verifyMode = BuildConfig.FLAVOR;

    public void setSceneEnv(SceneEnv sceneEnv) {
        this.sceneEnv = sceneEnv;
    }

    public SceneEnv getSceneEnv() {
        return this.sceneEnv;
    }

    public void setNavi(NavigatePage navigatePage) {
        this.navi = navigatePage;
    }

    public NavigatePage getNavi() {
        return this.navi;
    }

    public void setColl(Coll coll) {
        this.coll = coll;
    }

    public Coll getColl() {
        return this.coll;
    }

    public void setUpload(JSONObject jSONObject) {
        this.upload = jSONObject;
    }

    public JSONObject getUpload() {
        return this.upload;
    }

    public void setAlgorithm(JSONObject jSONObject) {
        this.algorithm = jSONObject;
    }

    public JSONObject getAlgorithm() {
        return this.algorithm;
    }

    public void setEnv(int i) {
        this.env = i;
    }

    public int getEnv() {
        return this.env;
    }

    public void setUi(int i) {
        this.ui = i;
    }

    public int getUi() {
        return this.ui;
    }

    public FaceTips getFaceTips() {
        return this.faceTips;
    }

    public void setFaceTips(FaceTips faceTips) {
        this.faceTips = faceTips;
    }

    public DeviceSetting[] getDeviceSettings() {
        return this.deviceSettings;
    }

    public void setDeviceSettings(DeviceSetting[] deviceSettingArr) {
        this.deviceSettings = deviceSettingArr;
    }

    public String getVerifyMode() {
        return this.verifyMode;
    }

    public void setVerifyMode(String str) {
        this.verifyMode = str;
    }

    public Upload getPhotinusCfg() {
        JSONObject jSONObject = this.upload;
        if (jSONObject == null) {
            return null;
        }
        return (Upload) JSON.toJavaObject(jSONObject, Upload.class);
    }

    public ArrayList<SDKAction> getSdkActionList() {
        return this.sdkActionList;
    }

    public void setSdkActionList(ArrayList<SDKAction> arrayList) {
        this.sdkActionList = arrayList;
    }
}
