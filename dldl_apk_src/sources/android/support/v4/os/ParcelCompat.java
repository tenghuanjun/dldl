package android.support.v4.os;

import android.os.Parcel;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes3.dex */
public final class ParcelCompat {
    public static boolean readBoolean(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    public static void writeBoolean(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }

    private ParcelCompat() {
    }
}
