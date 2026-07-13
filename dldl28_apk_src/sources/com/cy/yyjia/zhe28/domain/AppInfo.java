package com.cy.yyjia.zhe28.domain;

import androidx.core.app.NotificationCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.lzy.okgo.model.Progress;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppInfo.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b5\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002Bu\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0006\u0012\u0006\u0010\u0011\u001a\u00020\u0006\u0012\u0006\u0010\u0012\u001a\u00020\u0006\u0012\u0006\u0010\u0013\u001a\u00020\u0006¢\u0006\u0002\u0010\u0014J\t\u00104\u001a\u00020\u0004HÆ\u0003J\t\u00105\u001a\u00020\u000fHÆ\u0003J\t\u00106\u001a\u00020\u0006HÆ\u0003J\t\u00107\u001a\u00020\u0006HÆ\u0003J\t\u00108\u001a\u00020\u0006HÆ\u0003J\t\u00109\u001a\u00020\u0006HÆ\u0003J\t\u0010:\u001a\u00020\u0006HÆ\u0003J\t\u0010;\u001a\u00020\u0006HÆ\u0003J\t\u0010<\u001a\u00020\u0006HÆ\u0003J\t\u0010=\u001a\u00020\u0006HÆ\u0003J\t\u0010>\u001a\u00020\u0006HÆ\u0003J\t\u0010?\u001a\u00020\u0006HÆ\u0003J\t\u0010@\u001a\u00020\u0006HÆ\u0003J\t\u0010A\u001a\u00020\u0004HÆ\u0003J\u0095\u0001\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u0006HÆ\u0001J\u0013\u0010C\u001a\u00020\u000f2\b\u0010D\u001a\u0004\u0018\u00010EHÖ\u0003J\t\u0010F\u001a\u00020\u0004HÖ\u0001J\t\u0010G\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0016\u0010\t\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0016\u0010\n\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0016\u0010\u000b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R&\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0018\"\u0004\b!\u0010\"R\u001a\u0010\u0012\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0018\"\u0004\b$\u0010\"R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010&R\u001a\u0010\u0011\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0018\"\u0004\b(\u0010\"R\u0011\u0010\u0010\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018R&\u0010*\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00048G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0016\"\u0004\b,\u0010-R&\u0010.\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u000f8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010&\"\u0004\b0\u00101R\u001a\u0010\u0013\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0018\"\u0004\b3\u0010\"¨\u0006H"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/AppInfo;", "Landroidx/databinding/BaseObservable;", "Ljava/io/Serializable;", "Download", "", "DownloadOther", "", "DownloadType", "Iap", "Package", "Size", "Version", "VersionCode", "id", "isPack", "", "os", "name", "icon", Progress.TOTAL_SIZE, "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDownload", "()I", "getDownloadOther", "()Ljava/lang/String;", "getDownloadType", "getIap", "getPackage", "getSize", "getVersion", "getVersionCode", Progress.CURRENT_SIZE, "getCurrentSize", "setCurrentSize", "(Ljava/lang/String;)V", "getIcon", "setIcon", "getId", "()Z", "getName", "setName", "getOs", NotificationCompat.CATEGORY_PROGRESS, "getProgress", "setProgress", "(I)V", "selected", "getSelected", "setSelected", "(Z)V", "getTotalSize", "setTotalSize", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class AppInfo extends BaseObservable implements Serializable {
    public static final int $stable = 8;
    private final int Download;
    private final String DownloadOther;
    private final String DownloadType;
    private final String Iap;

    @SerializedName(alternate = {"androidPackage"}, value = "Package")
    private final String Package;

    @SerializedName(alternate = {"androidSize"}, value = "Size")
    private final String Size;

    @SerializedName(alternate = {"androidVersion"}, value = "Version")
    private final String Version;
    private final String VersionCode;
    private String currentSize;
    private String icon;
    private final int id;
    private final boolean isPack;
    private String name;
    private final String os;
    private int progress;
    private boolean selected;
    private String totalSize;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getDownload() {
        return this.Download;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getIsPack() {
        return this.isPack;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getOs() {
        return this.os;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getTotalSize() {
        return this.totalSize;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDownloadOther() {
        return this.DownloadOther;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDownloadType() {
        return this.DownloadType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getIap() {
        return this.Iap;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPackage() {
        return this.Package;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getSize() {
        return this.Size;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getVersion() {
        return this.Version;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getVersionCode() {
        return this.VersionCode;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public final AppInfo copy(int Download, String DownloadOther, String DownloadType, String Iap, String Package, String Size, String Version, String VersionCode, int id, boolean isPack, String os, String name, String icon, String totalSize) {
        Intrinsics.checkNotNullParameter(DownloadOther, "DownloadOther");
        Intrinsics.checkNotNullParameter(DownloadType, "DownloadType");
        Intrinsics.checkNotNullParameter(Iap, "Iap");
        Intrinsics.checkNotNullParameter(Package, "Package");
        Intrinsics.checkNotNullParameter(Size, "Size");
        Intrinsics.checkNotNullParameter(Version, "Version");
        Intrinsics.checkNotNullParameter(VersionCode, "VersionCode");
        Intrinsics.checkNotNullParameter(os, "os");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(totalSize, "totalSize");
        return new AppInfo(Download, DownloadOther, DownloadType, Iap, Package, Size, Version, VersionCode, id, isPack, os, name, icon, totalSize);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppInfo)) {
            return false;
        }
        AppInfo appInfo = (AppInfo) other;
        return this.Download == appInfo.Download && Intrinsics.areEqual(this.DownloadOther, appInfo.DownloadOther) && Intrinsics.areEqual(this.DownloadType, appInfo.DownloadType) && Intrinsics.areEqual(this.Iap, appInfo.Iap) && Intrinsics.areEqual(this.Package, appInfo.Package) && Intrinsics.areEqual(this.Size, appInfo.Size) && Intrinsics.areEqual(this.Version, appInfo.Version) && Intrinsics.areEqual(this.VersionCode, appInfo.VersionCode) && this.id == appInfo.id && this.isPack == appInfo.isPack && Intrinsics.areEqual(this.os, appInfo.os) && Intrinsics.areEqual(this.name, appInfo.name) && Intrinsics.areEqual(this.icon, appInfo.icon) && Intrinsics.areEqual(this.totalSize, appInfo.totalSize);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v16, types: [int] */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    public int hashCode() {
        int iHashCode = ((((((((((((((((this.Download * 31) + this.DownloadOther.hashCode()) * 31) + this.DownloadType.hashCode()) * 31) + this.Iap.hashCode()) * 31) + this.Package.hashCode()) * 31) + this.Size.hashCode()) * 31) + this.Version.hashCode()) * 31) + this.VersionCode.hashCode()) * 31) + this.id) * 31;
        boolean z = this.isPack;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return ((((((((iHashCode + r1) * 31) + this.os.hashCode()) * 31) + this.name.hashCode()) * 31) + this.icon.hashCode()) * 31) + this.totalSize.hashCode();
    }

    public String toString() {
        return "AppInfo(Download=" + this.Download + ", DownloadOther=" + this.DownloadOther + ", DownloadType=" + this.DownloadType + ", Iap=" + this.Iap + ", Package=" + this.Package + ", Size=" + this.Size + ", Version=" + this.Version + ", VersionCode=" + this.VersionCode + ", id=" + this.id + ", isPack=" + this.isPack + ", os=" + this.os + ", name=" + this.name + ", icon=" + this.icon + ", totalSize=" + this.totalSize + ")";
    }

    public final int getDownload() {
        return this.Download;
    }

    public final String getDownloadOther() {
        return this.DownloadOther;
    }

    public final String getDownloadType() {
        return this.DownloadType;
    }

    public final String getIap() {
        return this.Iap;
    }

    public final String getPackage() {
        return this.Package;
    }

    public final String getSize() {
        return this.Size;
    }

    public final String getVersion() {
        return this.Version;
    }

    public final String getVersionCode() {
        return this.VersionCode;
    }

    public final int getId() {
        return this.id;
    }

    public final boolean isPack() {
        return this.isPack;
    }

    public final String getOs() {
        return this.os;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final void setIcon(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.icon = str;
    }

    public final String getTotalSize() {
        return this.totalSize;
    }

    public final void setTotalSize(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.totalSize = str;
    }

    public AppInfo(int i, String DownloadOther, String DownloadType, String Iap, String Package, String Size, String Version, String VersionCode, int i2, boolean z, String os, String name, String icon, String totalSize) {
        Intrinsics.checkNotNullParameter(DownloadOther, "DownloadOther");
        Intrinsics.checkNotNullParameter(DownloadType, "DownloadType");
        Intrinsics.checkNotNullParameter(Iap, "Iap");
        Intrinsics.checkNotNullParameter(Package, "Package");
        Intrinsics.checkNotNullParameter(Size, "Size");
        Intrinsics.checkNotNullParameter(Version, "Version");
        Intrinsics.checkNotNullParameter(VersionCode, "VersionCode");
        Intrinsics.checkNotNullParameter(os, "os");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(totalSize, "totalSize");
        this.Download = i;
        this.DownloadOther = DownloadOther;
        this.DownloadType = DownloadType;
        this.Iap = Iap;
        this.Package = Package;
        this.Size = Size;
        this.Version = Version;
        this.VersionCode = VersionCode;
        this.id = i2;
        this.isPack = z;
        this.os = os;
        this.name = name;
        this.icon = icon;
        this.totalSize = totalSize;
        this.progress = 100;
        this.currentSize = "";
    }

    @Bindable
    public final int getProgress() {
        return this.progress;
    }

    public final void setProgress(int i) {
        this.progress = i;
        notifyPropertyChanged(79);
    }

    @Bindable
    public final String getCurrentSize() {
        return this.currentSize;
    }

    public final void setCurrentSize(String currentSize) {
        Intrinsics.checkNotNullParameter(currentSize, "currentSize");
        this.currentSize = currentSize;
        notifyPropertyChanged(22);
    }

    @Bindable
    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
        notifyPropertyChanged(94);
    }
}
