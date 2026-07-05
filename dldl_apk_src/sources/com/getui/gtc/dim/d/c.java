package com.getui.gtc.dim.d;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class c implements Parcelable {
    public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator<c>() { // from class: com.getui.gtc.dim.d.c.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ c[] newArray(int i) {
            return new c[i];
        }
    };
    List<Parcelable> a;
    private String b;

    protected c(Parcel parcel) {
        try {
            this.b = parcel.readString();
            Field declaredField = Class.forName(this.b).getDeclaredField("CREATOR");
            declaredField.setAccessible(true);
            this.a = new ArrayList();
            parcel.readTypedList(this.a, (Parcelable.Creator) declaredField.get(null));
        } catch (Throwable th) {
            a.a(th);
        }
    }

    public c(List<Parcelable> list) {
        try {
            this.a = list;
            this.b = list.get(0).getClass().getName();
        } catch (Throwable th) {
            a.a(th);
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeTypedList(this.a);
    }
}
