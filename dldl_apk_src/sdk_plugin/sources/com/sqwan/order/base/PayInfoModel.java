package com.sqwan.order.base;

import com.sqwan.common.constants.SqConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayInfoModel {
    private String mDcn;
    private String mDext;
    private float mDmoney;
    private String mDoid;
    private String mDpt;
    private int mDrLevel;
    private String mDrName;
    private int mDradio;
    private String mDrid;
    private String mDsName;
    private String mDsid;

    public String getOrderId() {
        return this.mDoid;
    }

    public PayInfoModel setOrderId(String str) {
        this.mDoid = str;
        return this;
    }

    public String getProductName() {
        return this.mDpt;
    }

    public PayInfoModel setProductName(String str) {
        this.mDpt = str;
        return this;
    }

    public String getServerId() {
        return this.mDsid;
    }

    public PayInfoModel setServerId(String str) {
        this.mDsid = str;
        return this;
    }

    public String getServerName() {
        return this.mDsName;
    }

    public PayInfoModel setServerName(String str) {
        this.mDsName = str;
        return this;
    }

    public String getExtend() {
        return this.mDext;
    }

    public PayInfoModel setExtend(String str) {
        this.mDext = str;
        return this;
    }

    public String getRoleId() {
        return this.mDrid;
    }

    public PayInfoModel setRoleId(String str) {
        this.mDrid = str;
        return this;
    }

    public String getRoleName() {
        return this.mDrName;
    }

    public PayInfoModel setRoleName(String str) {
        this.mDrName = str;
        return this;
    }

    public int getRoleLevel() {
        return this.mDrLevel;
    }

    public PayInfoModel setRoleLevel(int i) {
        this.mDrLevel = i;
        return this;
    }

    public float getMoney() {
        return this.mDmoney;
    }

    public PayInfoModel setMoney(float f) {
        this.mDmoney = f;
        return this;
    }

    public int getRadio() {
        return this.mDradio;
    }

    public PayInfoModel setRadio(int i) {
        this.mDradio = i;
        return this;
    }

    public String getCurrencyName() {
        return this.mDcn;
    }

    public PayInfoModel setCurrencyName(String str) {
        this.mDcn = str;
        return this;
    }

    public String toString() {
        return "  orderId=" + getOrderId() + "\n  productName=" + getProductName() + "\n  serverId=" + getServerId() + "\n  serverName=" + getServerName() + "\n  extend=" + getExtend() + "\n  roleId=" + getRoleId() + "\n  roleName=" + getRoleName() + "\n  roleLevel=" + getRoleLevel() + "\n  money=" + getMoney() + "\n  currencyName=" + getCurrencyName() + "\n  radio=" + getRadio();
    }

    public Map<String, String> getDataMap() {
        HashMap map = new HashMap();
        map.put(SqConstants.DOID, getOrderId());
        map.put("dpt", getProductName());
        map.put("dcn", getCurrencyName());
        map.put(SqConstants.DSID, getServerId());
        map.put(SqConstants.DSNAME, getServerName());
        map.put(SqConstants.DEXT, getExtend());
        map.put(SqConstants.DRID, getRoleId());
        map.put(SqConstants.DRNAME, getRoleName());
        map.put(SqConstants.DRLEVEL, String.valueOf(getRoleLevel()));
        map.put(SqConstants.DMONEY, String.valueOf(getMoney()));
        map.put(SqConstants.DRADIO, String.valueOf(getRadio()));
        return map;
    }

    public String toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(SqConstants.DOID, getOrderId());
            jSONObject.put("dpt", getProductName());
            jSONObject.put("dcn", getCurrencyName());
            jSONObject.put(SqConstants.DSID, getServerId());
            jSONObject.put(SqConstants.DSNAME, getServerName());
            jSONObject.put(SqConstants.DEXT, getExtend());
            jSONObject.put(SqConstants.DRID, getRoleId());
            jSONObject.put(SqConstants.DRNAME, getRoleName());
            jSONObject.put(SqConstants.DRLEVEL, getRoleLevel());
            jSONObject.put("money", getMoney());
            jSONObject.put(SqConstants.DRADIO, getRadio());
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static PayInfoModel fromJson(String str) {
        PayInfoModel payInfoModel = new PayInfoModel();
        try {
            JSONObject jSONObject = new JSONObject(str);
            payInfoModel.setOrderId(jSONObject.optString(SqConstants.DOID));
            payInfoModel.setProductName(jSONObject.optString("dpt"));
            payInfoModel.setCurrencyName(jSONObject.optString("dcn"));
            payInfoModel.setServerId(jSONObject.optString(SqConstants.DSID));
            payInfoModel.setServerName(jSONObject.optString(SqConstants.DSNAME));
            payInfoModel.setExtend(jSONObject.optString(SqConstants.DEXT));
            payInfoModel.setRoleId(jSONObject.optString(SqConstants.DRID));
            payInfoModel.setRoleName(jSONObject.optString(SqConstants.DRNAME));
            payInfoModel.setRoleLevel(jSONObject.optInt(SqConstants.DRLEVEL));
            payInfoModel.setMoney((float) jSONObject.optDouble("money"));
            payInfoModel.setRadio(jSONObject.optInt(SqConstants.DRADIO));
        } catch (Exception unused) {
        }
        return payInfoModel;
    }
}
