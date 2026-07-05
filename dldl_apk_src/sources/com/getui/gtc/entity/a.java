package com.getui.gtc.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import com.alipay.zoloz.toyger.ToygerBaseService;
import com.igexin.push.core.b;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public SparseArray<C0046a> a = new SparseArray<>();
    private String b;

    /* JADX INFO: renamed from: com.getui.gtc.entity.a$a, reason: collision with other inner class name */
    public static class C0046a implements Parcelable {
        public static final Parcelable.Creator<C0046a> CREATOR = new Parcelable.Creator<C0046a>() { // from class: com.getui.gtc.entity.a.a.1
            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ C0046a createFromParcel(Parcel parcel) {
                return new C0046a(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final /* bridge */ /* synthetic */ C0046a[] newArray(int i) {
                return new C0046a[i];
            }
        };
        public int a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public long g;
        public String h;
        public boolean i;
        public boolean j;

        public C0046a() {
        }

        protected C0046a(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readString();
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = parcel.readString();
            this.f = parcel.readString();
            this.g = parcel.readLong();
            this.h = parcel.readString();
            this.i = parcel.readByte() != 0;
            this.j = parcel.readByte() != 0;
        }

        public final String a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", this.a);
                jSONObject.put("version", this.b);
                jSONObject.put("name", this.c);
                jSONObject.put("cls_name", this.d);
                jSONObject.put("url", this.h);
                jSONObject.put("isdestroy", this.i);
                jSONObject.put("effective", String.valueOf(this.g));
                jSONObject.put(ToygerBaseService.KEY_RES_9_KEY, this.f);
                jSONObject.put("checksum", this.e);
            } catch (Exception e) {
                com.getui.gtc.i.c.a.a(e);
            }
            return jSONObject.toString();
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeString(this.b);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
            parcel.writeString(this.f);
            parcel.writeLong(this.g);
            parcel.writeString(this.h);
            parcel.writeByte(this.i ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.j ? (byte) 1 : (byte) 0);
        }
    }

    public static a a(Map<String, String> map) {
        a aVar;
        String str = map.get("ext_infos");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            aVar = new a();
            aVar.b = jSONObject.getString("version");
            JSONArray jSONArray = jSONObject.getJSONArray("extensions");
            if (jSONArray != null && jSONArray.length() > 0) {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    C0046a c0046a = new C0046a();
                    c0046a.a = jSONObject2.getInt("id");
                    c0046a.b = jSONObject2.getString("version");
                    c0046a.c = jSONObject2.getString("name");
                    c0046a.d = jSONObject2.getString("cls_name");
                    c0046a.h = jSONObject2.getString("url");
                    c0046a.e = jSONObject2.getString("checksum");
                    c0046a.f = jSONObject2.getString(ToygerBaseService.KEY_RES_9_KEY);
                    if (jSONObject2.has("isdestroy")) {
                        c0046a.i = jSONObject2.getBoolean("isdestroy");
                    }
                    if (jSONObject2.has("effective")) {
                        long j = 0;
                        try {
                            j = Long.parseLong(jSONObject2.getString("effective")) * 1000;
                        } catch (Exception e) {
                            com.getui.gtc.i.c.a.b(e);
                        }
                        c0046a.g = j;
                    }
                    aVar.a.put(c0046a.a, c0046a);
                }
            }
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            aVar = null;
        }
        String str2 = map.get("sdk.push.plugins");
        if (aVar != null && !TextUtils.isEmpty(str2)) {
            for (String str3 : str2.split(b.aj)) {
                try {
                    C0046a c0046aB = aVar.b(Integer.parseInt(str3));
                    if (c0046aB != null) {
                        c0046aB.j = true;
                    }
                } catch (Exception unused) {
                }
            }
        }
        return aVar;
    }

    public final C0046a a(int i) {
        SparseArray<C0046a> sparseArray = this.a;
        return sparseArray.get(sparseArray.keyAt(i));
    }

    public final String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.b);
            JSONArray jSONArray = new JSONArray();
            jSONObject.put("extensions", jSONArray);
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                jSONArray.put(i, new JSONObject(this.a.get(this.a.keyAt(i)).a()));
            }
        } catch (Exception e) {
            com.getui.gtc.i.c.a.a(e);
        }
        return jSONObject.toString();
    }

    public final C0046a b(int i) {
        return this.a.get(i);
    }

    public final void c(int i) {
        this.a.removeAt(i);
    }
}
