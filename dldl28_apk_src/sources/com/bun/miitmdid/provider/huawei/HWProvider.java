package com.bun.miitmdid.provider.huawei;

import android.content.Context;
import com.bun.miitmdid.l0;
import com.bun.miitmdid.m;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.aaid.entity.AAIDResult;
import com.huawei.hms.ads.identifier.AdvertisingIdClient;
import com.huawei.hms.opendevice.OpenDevice;
import com.huawei.hms.support.api.opendevice.OdidResult;

/* JADX INFO: loaded from: classes2.dex */
public class HWProvider extends m {
    public static final String TAG = "HWProvider";
    private AdvertisingIdClient.Info mAdvertisingIdInfo;
    private int mCallbackCount;
    public Context mContext;

    public HWProvider(Context context) {
        this.mContext = checkContext(context);
        l0.c(TAG, "enter into HWProvider");
    }

    private native void finishCallback();

    private native void initCallbackCount();

    private native boolean isClassExists(String str);

    @Override // com.bun.miitmdid.interfaces.IIdProvider
    public native void doStart();

    public void getIdAAID() {
        if (!isGetOAID()) {
            this.isSupportedCache = true;
        }
        if (isClassExists("com.huawei.hms.opendevice.OpenDevice")) {
            HmsInstanceId.getInstance(this.mContext).getAAID().addOnSuccessListener(new OnSuccessListener() { // from class: com.bun.miitmdid.provider.huawei.HWProvider$$ExternalSyntheticLambda0
                public final void onSuccess(Object obj) {
                    this.f$0.m6336lambda$getIdAAID$0$combunmiitmdidproviderhuaweiHWProvider((AAIDResult) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.bun.miitmdid.provider.huawei.HWProvider$$ExternalSyntheticLambda1
                public final void onFailure(Exception exc) {
                    this.f$0.m6337lambda$getIdAAID$1$combunmiitmdidproviderhuaweiHWProvider(exc);
                }
            });
            return;
        }
        l0.a(TAG, "no combined class to unsupport get AAID ");
        this.AAIDCache = "";
        finishCallback();
    }

    public native void getIdOAID();

    public void getIdVAID() {
        if (!isGetOAID()) {
            this.isSupportedCache = true;
        }
        if (isClassExists("com.huawei.hms.opendevice.OpenDevice")) {
            OpenDevice.getOpenDeviceClient(this.mContext).getOdid().addOnSuccessListener(new OnSuccessListener() { // from class: com.bun.miitmdid.provider.huawei.HWProvider$$ExternalSyntheticLambda2
                public final void onSuccess(Object obj) {
                    this.f$0.m6338lambda$getIdVAID$2$combunmiitmdidproviderhuaweiHWProvider((OdidResult) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.bun.miitmdid.provider.huawei.HWProvider$$ExternalSyntheticLambda3
                public final void onFailure(Exception exc) {
                    this.f$0.m6339lambda$getIdVAID$3$combunmiitmdidproviderhuaweiHWProvider(exc);
                }
            });
            return;
        }
        l0.a(TAG, "no combined class to unsupport get VAID ");
        this.VAIDCache = "";
        finishCallback();
    }

    @Override // com.bun.miitmdid.m, com.bun.miitmdid.interfaces.IdSupplier
    public native boolean isLimited();

    @Override // com.bun.miitmdid.m, com.bun.miitmdid.interfaces.IIdProvider
    public native boolean isSync();

    /* JADX INFO: renamed from: lambda$getIdAAID$0$com-bun-miitmdid-provider-huawei-HWProvider, reason: not valid java name */
    /* synthetic */ void m6336lambda$getIdAAID$0$combunmiitmdidproviderhuaweiHWProvider(AAIDResult aAIDResult) {
        this.AAIDCache = aAIDResult.getId();
        finishCallback();
        l0.a(TAG, "getAAID successfully, aaid is " + this.AAIDCache);
    }

    /* JADX INFO: renamed from: lambda$getIdAAID$1$com-bun-miitmdid-provider-huawei-HWProvider, reason: not valid java name */
    /* synthetic */ void m6337lambda$getIdAAID$1$combunmiitmdidproviderhuaweiHWProvider(Exception exc) {
        finishCallback();
        l0.a(TAG, "getAAID failed, catch exception: " + exc);
    }

    /* JADX INFO: renamed from: lambda$getIdVAID$2$com-bun-miitmdid-provider-huawei-HWProvider, reason: not valid java name */
    /* synthetic */ void m6338lambda$getIdVAID$2$combunmiitmdidproviderhuaweiHWProvider(OdidResult odidResult) {
        this.VAIDCache = odidResult.getId();
        finishCallback();
        l0.a(TAG, "getVAID successfully, the VAID is " + this.VAIDCache);
    }

    /* JADX INFO: renamed from: lambda$getIdVAID$3$com-bun-miitmdid-provider-huawei-HWProvider, reason: not valid java name */
    /* synthetic */ void m6339lambda$getIdVAID$3$combunmiitmdidproviderhuaweiHWProvider(Exception exc) {
        finishCallback();
        l0.a(TAG, "getVAID failed, catch exception : " + exc);
    }

    @Override // com.bun.miitmdid.interfaces.IIdProvider
    public native void shutDown();
}
