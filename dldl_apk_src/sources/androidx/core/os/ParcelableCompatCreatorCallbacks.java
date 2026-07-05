package androidx.core.os;

import android.os.Parcel;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
@Deprecated
public interface ParcelableCompatCreatorCallbacks<T> {
    T createFromParcel(Parcel parcel, ClassLoader classLoader);

    T[] newArray(int i);
}
