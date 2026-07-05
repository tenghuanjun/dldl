package com.sy37sdk.order.nat.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.sy37sdk.order.nat.PayBundleKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class Coupon implements Parcelable {
    public static final Parcelable.Creator<Coupon> CREATOR = new Parcelable.Creator<Coupon>() { // from class: com.sy37sdk.order.nat.bean.Coupon.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Coupon createFromParcel(Parcel parcel) {
            return new Coupon(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Coupon[] newArray(int i) {
            return new Coupon[i];
        }
    };
    private float amount;
    private String code;
    private String etime;
    private boolean isSelect;
    private int minAmount;
    private int moneyStatus;
    private String name;
    private int pidgidstatus;
    private int status;
    private int type;
    private String uname;
    private int useStatus;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isAvailable() {
        return this.pidgidstatus == 1 && this.moneyStatus == 1;
    }

    public String getName() {
        return this.name;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public String getCode() {
        return this.code;
    }

    public float getAmount() {
        return this.amount;
    }

    public int getMinAmount() {
        return this.minAmount;
    }

    public long getEtime() {
        return Date.parse(this.etime);
    }

    public String toString() {
        return "{code:" + this.code + ", uname:" + this.uname + ", status:" + this.status + ", etime:" + this.etime + ", useStatus:" + this.useStatus + ", pidgidstatus:" + this.pidgidstatus + ", name:" + this.name + ", amount:" + this.amount + ", minAmount:" + this.minAmount + ", type:" + this.type + ", moneyStatus:" + this.moneyStatus + ", isAvailable:" + isAvailable() + "}";
    }

    public static List<Coupon> fromJson(String str) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has(PayBundleKey.KEY_COUPON)) {
            JSONArray jSONArray = jSONObject.getJSONArray(PayBundleKey.KEY_COUPON);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                Coupon coupon = new Coupon();
                coupon.code = jSONObject2.optString("CODE");
                coupon.uname = jSONObject2.optString("UNAME");
                coupon.status = jSONObject2.optInt("STATUS");
                coupon.etime = jSONObject2.optString("ETIME");
                coupon.useStatus = jSONObject2.optInt("USESTATUS");
                coupon.pidgidstatus = jSONObject2.optInt("PIDGIDSTATUS");
                coupon.name = jSONObject2.optString("NAME");
                coupon.amount = (float) jSONObject2.optDouble("AMOUNT");
                coupon.minAmount = jSONObject2.optInt("MIN_AMOUNT");
                coupon.type = jSONObject2.optInt("TYPE");
                coupon.moneyStatus = jSONObject2.optInt("MONEYSTATUS");
                arrayList.add(coupon);
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.code);
        parcel.writeString(this.uname);
        parcel.writeInt(this.status);
        parcel.writeString(this.etime);
        parcel.writeInt(this.useStatus);
        parcel.writeInt(this.pidgidstatus);
        parcel.writeString(this.name);
        parcel.writeFloat(this.amount);
        parcel.writeInt(this.minAmount);
        parcel.writeInt(this.type);
        parcel.writeInt(this.moneyStatus);
        parcel.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel parcel) {
        this.code = parcel.readString();
        this.uname = parcel.readString();
        this.status = parcel.readInt();
        this.etime = parcel.readString();
        this.useStatus = parcel.readInt();
        this.pidgidstatus = parcel.readInt();
        this.name = parcel.readString();
        this.amount = parcel.readFloat();
        this.minAmount = parcel.readInt();
        this.type = parcel.readInt();
        this.moneyStatus = parcel.readInt();
        this.isSelect = parcel.readByte() != 0;
    }

    public Coupon() {
    }

    protected Coupon(Parcel parcel) {
        this.code = parcel.readString();
        this.uname = parcel.readString();
        this.status = parcel.readInt();
        this.etime = parcel.readString();
        this.useStatus = parcel.readInt();
        this.pidgidstatus = parcel.readInt();
        this.name = parcel.readString();
        this.amount = parcel.readFloat();
        this.minAmount = parcel.readInt();
        this.type = parcel.readInt();
        this.moneyStatus = parcel.readInt();
        this.isSelect = parcel.readByte() != 0;
    }
}
