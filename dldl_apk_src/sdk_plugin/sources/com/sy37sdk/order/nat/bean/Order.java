package com.sy37sdk.order.nat.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.sqwan.common.constants.SqConstants;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class Order implements Parcelable {
    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() { // from class: com.sy37sdk.order.nat.bean.Order.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Order createFromParcel(Parcel parcel) {
            return new Order(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Order[] newArray(int i) {
            return new Order[i];
        }
    };
    private String dcn;
    private String dext;
    private String doid;
    private String dpt;
    private int dradio;
    private String drid;
    private int drlevel;
    private String drname;
    private String dsid;
    private String isVouchers;
    private String moid;
    private float money;
    private String payAmount;
    private String payChannel;
    private String payMethod;
    private int type;
    private String vouchersId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Order() {
        this.payAmount = "";
        this.isVouchers = "0";
        this.vouchersId = "";
        this.payChannel = "1";
        this.payMethod = "1";
    }

    protected Order(Parcel parcel) {
        this.payAmount = "";
        this.isVouchers = "0";
        this.vouchersId = "";
        this.payChannel = "1";
        this.payMethod = "1";
        this.moid = parcel.readString();
        this.money = parcel.readFloat();
        this.dsid = parcel.readString();
        this.dpt = parcel.readString();
        this.doid = parcel.readString();
        this.dext = parcel.readString();
        this.drid = parcel.readString();
        this.drname = parcel.readString();
        this.dcn = parcel.readString();
        this.drlevel = parcel.readInt();
        this.dradio = parcel.readInt();
        this.type = parcel.readInt();
        this.payAmount = parcel.readString();
        this.isVouchers = parcel.readString();
        this.vouchersId = parcel.readString();
        this.payChannel = parcel.readString();
        this.payMethod = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.moid);
        parcel.writeFloat(this.money);
        parcel.writeString(this.dsid);
        parcel.writeString(this.dpt);
        parcel.writeString(this.doid);
        parcel.writeString(this.dext);
        parcel.writeString(this.drid);
        parcel.writeString(this.drname);
        parcel.writeString(this.dcn);
        parcel.writeInt(this.drlevel);
        parcel.writeInt(this.dradio);
        parcel.writeInt(this.type);
        parcel.writeString(this.payAmount);
        parcel.writeString(this.isVouchers);
        parcel.writeString(this.vouchersId);
        parcel.writeString(this.payChannel);
        parcel.writeString(this.payMethod);
    }

    public int getDrlevel() {
        return this.drlevel;
    }

    public void setDrlevel(int i) {
        this.drlevel = i;
    }

    public int getDradio() {
        return this.dradio;
    }

    public void setDradio(int i) {
        this.dradio = i;
    }

    public String getDcn() {
        return this.dcn;
    }

    public void setDcn(String str) {
        this.dcn = str;
    }

    public String getMoid() {
        return this.moid;
    }

    public void setMoid(String str) {
        this.moid = str;
    }

    public float getMoney() {
        return this.money;
    }

    public void setMoney(float f) {
        this.money = f;
    }

    public String getDsid() {
        return this.dsid;
    }

    public void setDsid(String str) {
        this.dsid = str;
    }

    public String getDpt() {
        return this.dpt;
    }

    public void setDpt(String str) {
        this.dpt = str;
    }

    public String getDoid() {
        return this.doid;
    }

    public void setDoid(String str) {
        this.doid = str;
    }

    public String getDext() {
        return this.dext;
    }

    public void setDext(String str) {
        this.dext = str;
    }

    public String getDrid() {
        return this.drid;
    }

    public void setDrid(String str) {
        this.drid = str;
    }

    public String getDrname() {
        return this.drname;
    }

    public void setDrname(String str) {
        this.drname = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getPayAmount() {
        return this.payAmount;
    }

    public void setPayAmount(String str) {
        this.payAmount = str;
    }

    public String toString() {
        return "Order{moid='" + this.moid + "', money=" + this.money + ", dsid='" + this.dsid + "', dpt='" + this.dpt + "', doid='" + this.doid + "', dext='" + this.dext + "', drid='" + this.drid + "', drname='" + this.drname + "', dcn='" + this.dcn + "', drlevel=" + this.drlevel + ", dradio=" + this.dradio + ", type=" + this.type + ", payAmount='" + this.payAmount + "', isVouchers='" + this.isVouchers + "', vouchersId='" + this.vouchersId + "', payChannel='" + this.payChannel + "', payMethod='" + this.payMethod + '\'' + AbstractJsonLexerKt.END_OBJ;
    }

    public String getIsVouchers() {
        return this.isVouchers;
    }

    public void setIsVouchers(String str) {
        this.isVouchers = str;
    }

    public String getVouchersId() {
        return this.vouchersId;
    }

    public void setVouchersId(String str) {
        this.vouchersId = str;
    }

    public String getPayChannel() {
        return this.payChannel;
    }

    public void setPayChannel(String str) {
        this.payChannel = str;
    }

    public String getPayMethod() {
        return this.payMethod;
    }

    public void setPayMethod(String str) {
        this.payMethod = str;
    }

    public static String objectToJsonStr(Order order) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(SqConstants.MOID, order.getMoid());
            jSONObject.put("dcn", order.getDcn());
            jSONObject.put(SqConstants.DEXT, order.getDext());
            jSONObject.put(SqConstants.DOID, order.getDoid());
            jSONObject.put("dpt", order.getDpt());
            jSONObject.put(SqConstants.DRADIO, order.getDradio());
            jSONObject.put(SqConstants.DRID, order.getDrid());
            jSONObject.put(SqConstants.DRLEVEL, order.getDrlevel());
            jSONObject.put(SqConstants.DRNAME, order.getDrname());
            jSONObject.put(SqConstants.DSID, order.getDsid());
            jSONObject.put("money", order.getMoney());
            jSONObject.put("type", order.getType());
            jSONObject.put("payAmount", order.getPayAmount());
            jSONObject.put("isVouchers", order.getIsVouchers());
            jSONObject.put("vouchersId", order.getVouchersId());
            jSONObject.put("payMethod", order.getPayMethod());
            jSONObject.put("payChannel", order.getPayChannel());
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Order jsonToObject(String str) {
        Order order = new Order();
        try {
            JSONObject jSONObject = new JSONObject(str);
            order.setMoid(jSONObject.optString(SqConstants.MOID));
            order.setDcn(jSONObject.optString("dcn"));
            order.setDext(jSONObject.optString(SqConstants.DEXT));
            order.setDoid(jSONObject.optString(SqConstants.DOID));
            order.setDpt(jSONObject.optString("dpt"));
            order.setDradio(jSONObject.optInt(SqConstants.DRADIO));
            order.setDrid(jSONObject.optString(SqConstants.DRID));
            order.setDrlevel(jSONObject.optInt(SqConstants.DRLEVEL));
            order.setDsid(jSONObject.optString(SqConstants.DSID));
            order.setDrname(jSONObject.optString(SqConstants.DRNAME));
            order.setMoney((float) jSONObject.optDouble("money"));
            order.setType(jSONObject.optInt("type"));
            order.setPayAmount(jSONObject.optString("payAmount"));
            order.setIsVouchers(jSONObject.optString("isVouchers"));
            order.setVouchersId(jSONObject.optString("vouchersId"));
            order.setPayMethod(jSONObject.optString("payMethod"));
            order.setPayChannel(jSONObject.optString("payChannel"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }
}
