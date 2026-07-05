package com.bun.miitmdid.provider.huawei;

import android.content.Context;
import com.bun.miitmdid.m;
import com.bun.miitmdid.m0;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.aaid.entity.AAIDResult;
import com.huawei.hms.ads.identifier.AdvertisingIdClient;
import com.huawei.hms.opendevice.OpenDevice;
import com.huawei.hms.support.api.opendevice.OdidResult;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HWProvider extends m {
    public static final String TAG = "HWProvider";
    private AdvertisingIdClient.Info mAdvertisingIdInfo;
    private int mCallbackCount;
    public Context mContext;

    public HWProvider(Context context) {
        this.mContext = checkContext(context);
        m0.c(TAG, "enter into HWProvider");
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
            HmsInstanceId.getInstance(this.mContext).getAAID().addOnSuccessListener(new OnSuccessListener() { // from class: com.bun.miitmdid.provider.huawei.-$$Lambda$HWProvider$skkmm3TMb_ohd4lLQ5JkyMqKVUU
                public final void onSuccess(Object obj) {
                    this.f$0.lambda$getIdAAID$0$HWProvider((AAIDResult) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.bun.miitmdid.provider.huawei.-$$Lambda$HWProvider$8xQnD77CLXq-D4UyW-tJG_hBTFs
                public final void onFailure(Exception exc) {
                    this.f$0.lambda$getIdAAID$1$HWProvider(exc);
                }
            });
            return;
        }
        m0.a(TAG, "no combined class to unsupport get AAID ");
        this.AAIDCache = "";
        finishCallback();
    }

    public native void getIdOAID();

    public void getIdVAID() {
        if (!isGetOAID()) {
            this.isSupportedCache = true;
        }
        if (isClassExists("com.huawei.hms.opendevice.OpenDevice")) {
            OpenDevice.getOpenDeviceClient(this.mContext).getOdid().addOnSuccessListener(new OnSuccessListener() { // from class: com.bun.miitmdid.provider.huawei.-$$Lambda$HWProvider$tFwUC31w5_m6uxF3E8-j4T7CaYc
                public final void onSuccess(Object obj) {
                    this.f$0.lambda$getIdVAID$2$HWProvider((OdidResult) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.bun.miitmdid.provider.huawei.-$$Lambda$HWProvider$5n3veZ_pYLtDToZabSznN5ZyM1E
                public final void onFailure(Exception exc) {
                    this.f$0.lambda$getIdVAID$3$HWProvider(exc);
                }
            });
            return;
        }
        m0.a(TAG, "no combined class to unsupport get VAID ");
        this.VAIDCache = "";
        finishCallback();
    }

    @Override // com.bun.miitmdid.m, com.bun.miitmdid.interfaces.IdSupplier
    public native boolean isLimited();

    @Override // com.bun.miitmdid.m, com.bun.miitmdid.interfaces.IIdProvider
    public native boolean isSync();

    public /* synthetic */ void lambda$getIdAAID$0$HWProvider(AAIDResult aAIDResult) {
        this.AAIDCache = aAIDResult.getId();
        finishCallback();
        m0.a(TAG, "getAAID successfully, aaid is " + this.AAIDCache);
    }

    public /* synthetic */ void lambda$getIdAAID$1$HWProvider(Exception exc) {
        finishCallback();
        m0.a(TAG, "getAAID failed, catch exception: " + exc);
    }

    public /* synthetic */ void lambda$getIdVAID$2$HWProvider(OdidResult odidResult) {
        this.VAIDCache = odidResult.getId();
        finishCallback();
        m0.a(TAG, "getVAID successfully, the VAID is " + this.VAIDCache);
    }

    public /* synthetic */ void lambda$getIdVAID$3$HWProvider(Exception exc) {
        finishCallback();
        m0.a(TAG, "getVAID failed, catch exception : " + exc);
    }

    @Override // com.bun.miitmdid.interfaces.IIdProvider
    public native void shutDown();
}
