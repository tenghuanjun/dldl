package com.taptap.sdk.gid.service.disk.cn;

import android.content.SharedPreferences;
import com.taptap.sdk.base.utils.prefs.SharedPreferenceDelegate;
import com.taptap.sdk.gid.service.GidService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: TapCNGidDiskService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/gid/service/disk/cn/TapCNGidDiskService;", "Lcom/taptap/sdk/gid/service/GidService;", "sharedPreferences", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "<set-?>", "", "gid", "getGid", "()Ljava/lang/String;", "setGid", "(Ljava/lang/String;)V", "gid$delegate", "Lcom/taptap/sdk/base/utils/prefs/SharedPreferenceDelegate;", "tap-gid_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapCNGidDiskService implements GidService {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(TapCNGidDiskService.class, "gid", "getGid()Ljava/lang/String;", 0))};

    /* JADX INFO: renamed from: gid$delegate, reason: from kotlin metadata */
    private final SharedPreferenceDelegate gid;

    public TapCNGidDiskService(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        this.gid = new SharedPreferenceDelegate(sharedPreferences, "taptap_gid_cn_cache", "");
    }

    @Override // com.taptap.sdk.gid.service.GidService
    public String getGid() {
        return (String) this.gid.getValue(this, $$delegatedProperties[0]);
    }

    @Override // com.taptap.sdk.gid.service.GidService
    public void setGid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.gid.setValue(this, $$delegatedProperties[0], str);
    }
}
