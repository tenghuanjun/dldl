package com.sq.oaid.sq_oaid;

import android.content.Context;
import android.util.Log;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;
import com.bun.miitmdid.pojo.IdSupplierImpl;
import com.sq.oaid.sq_oaid.CertManager;
import com.sq.tools.report.event.IEventReporter;
import com.sq.tools.report.exception.IExceptionReporter;
import com.sqwan.common.track.SqTrackKey;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqOAIDHelper implements IIdentifierListener {
    private static final String TAG = "SqOAIDHelper";
    private static boolean isArchSupport;
    public static boolean isTanZhenDev = Log.isLoggable("tanzhen.check", 3);
    private final Callback callback;
    private boolean isCertInit = false;
    private final IEventReporter mEventReporter;
    private final IExceptionReporter mIExceptionReporter;

    public interface Callback {
        void onIdsValid(IdsBean idsBean);
    }

    public SqOAIDHelper(Callback appIdsUpdater, IEventReporter eventReporter, IExceptionReporter exceptionReporter) {
        this.callback = appIdsUpdater;
        this.mEventReporter = eventReporter;
        this.mIExceptionReporter = exceptionReporter;
    }

    private void trackFail(String reason, int code) {
        if (this.mEventReporter != null) {
            HashMap map = new HashMap();
            map.put("fail_reason", reason);
            map.put(SqTrackKey.fail_code, String.valueOf(code));
            this.mEventReporter.report("get_oaid_fail", map);
        }
    }

    public void getDeviceIds(final Context cxt) {
        if (this.isCertInit) {
            return;
        }
        try {
            CertManager.getCert(cxt, new CertManager.CertCallBack() { // from class: com.sq.oaid.sq_oaid.SqOAIDHelper.1
                @Override // com.sq.oaid.sq_oaid.CertManager.CertCallBack
                public void onCertGet(String certContent) {
                    SqOAIDHelper.this.onCertReady(certContent, cxt);
                }

                @Override // com.sq.oaid.sq_oaid.CertManager.CertCallBack
                public void onCertFail(String failReason, int code, Exception e) {
                    if (SqOAIDHelper.this.mIExceptionReporter != null) {
                        SqOAIDHelper.this.mIExceptionReporter.reportException(e, code, failReason, "");
                    }
                }
            });
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCertReady(String certContent, final Context cxt) {
        try {
            boolean zInitCert = MdidSdkHelper.InitCert(cxt, certContent);
            this.isCertInit = zInitCert;
            if (!zInitCert) {
                trackFail("init cert fail", -1);
            }
            int iInitSdk = MdidSdkHelper.InitSdk(cxt, true, true, true, true, this);
            IdSupplierImpl idSupplierImpl = new IdSupplierImpl();
            if (iInitSdk == 1008616) {
                Log.w(TAG, "cert not init or check not pass");
                trackFail("cert not init or check not pass", iInitSdk);
                onSupport(idSupplierImpl);
                return;
            }
            if (iInitSdk == 1008612) {
                Log.w(TAG, "device not supported");
                trackFail("device not supported", iInitSdk);
                onSupport(idSupplierImpl);
                return;
            }
            if (iInitSdk == 1008613) {
                Log.w(TAG, "failed to load config file");
                trackFail("failed to load config file", iInitSdk);
                onSupport(idSupplierImpl);
                return;
            }
            if (iInitSdk == 1008611) {
                Log.w(TAG, "manufacturer not supported");
                trackFail("manufacturer not supported", iInitSdk);
                onSupport(idSupplierImpl);
                return;
            }
            if (iInitSdk == 1008615) {
                Log.w(TAG, "sdk call error");
                trackFail("sdk call error", iInitSdk);
                onSupport(idSupplierImpl);
            } else {
                if (iInitSdk == 1008614) {
                    Log.i(TAG, "result delay (async)");
                    return;
                }
                if (iInitSdk == 1008610) {
                    Log.i(TAG, "result ok (sync)");
                    return;
                }
                Log.w(TAG, "getDeviceIds: unknown code: " + iInitSdk);
            }
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    @Override // com.bun.miitmdid.interfaces.IIdentifierListener
    public void onSupport(IdSupplier supplier) {
        String aaid;
        String vaid;
        boolean zIsLimited;
        if (supplier == null) {
            Log.w(TAG, "onSupport: supplier is null");
            return;
        }
        if (this.callback == null) {
            Log.e(TAG, "onSupport: callbackListener is null");
            return;
        }
        boolean zIsSupported = false;
        String oaid = null;
        if (isArchSupport) {
            zIsSupported = supplier.isSupported();
            zIsLimited = supplier.isLimited();
            oaid = supplier.getOAID();
            vaid = supplier.getVAID();
            aaid = supplier.getAAID();
        } else {
            aaid = null;
            vaid = null;
            zIsLimited = false;
        }
        Log.d(TAG, "onSupport: oaid: \n" + oaid);
        if (zIsSupported && !zIsLimited && isArchSupport) {
            this.callback.onIdsValid(new IdsBean(aaid, oaid, vaid));
            return;
        }
        if (!zIsSupported) {
            trackFail("isSupport is false", -1);
        }
        if (zIsLimited) {
            trackFail("limited is true", -1);
        }
        if (isArchSupport) {
            return;
        }
        trackFail("ArchSupport is false", -1);
    }

    public static void init() {
        if (isTanZhenDev) {
            Log.w(TAG, "探针环境 不初始化");
        } else {
            loadLibrary("msaoaidsec");
        }
    }

    private static void loadLibrary(String lib) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            if (((String) cls.getMethod("get", String.class, String.class).invoke(cls, "ro.product.cpu.abi", "")).contains("x86")) {
                isArchSupport = false;
            } else {
                isArchSupport = true;
                System.loadLibrary(lib);
            }
        } catch (Throwable unused) {
        }
    }
}
