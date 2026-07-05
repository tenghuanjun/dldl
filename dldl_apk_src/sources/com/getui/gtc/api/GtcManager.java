package com.getui.gtc.api;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.getui.gtc.api.GtcIdCallback;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.ProcessSwitchContract;
import com.getui.gtc.base.publish.Broker;
import com.getui.gtc.base.publish.Subscriber;
import com.getui.gtc.base.util.BundleCompat;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.d.a;
import com.getui.gtc.g.b;
import com.getui.gtc.i.c.a;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class GtcManager implements Subscriber {
    private static String methodName;

    static class InstanceHolder {
        private static final GtcManager instance = new GtcManager();

        private InstanceHolder() {
        }
    }

    private GtcManager() {
    }

    private void checkSdkInfo(SdkInfo sdkInfo) {
        if (TextUtils.isEmpty(sdkInfo.getModuleName())) {
            a.c("moduleName not set for sdkinfo");
            throw new RuntimeException("moduleName not set for sdkinfo");
        }
        if (TextUtils.isEmpty(sdkInfo.getAppid())) {
            a.c("appid not set for sdkinfo");
            throw new RuntimeException("appid not set for sdkinfo");
        }
        if (TextUtils.isEmpty(sdkInfo.getVersion())) {
            a.c("version not set for sdkinfo");
            throw new RuntimeException("version not set for sdkinfo");
        }
    }

    private Bundle createBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(ProcessSwitchContract.CLASS_NAME, getClass().getName());
        bundle.putString(ProcessSwitchContract.GET_INSTANCE, methodName);
        return bundle;
    }

    public static GtcManager getInstance() {
        methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return InstanceHolder.instance;
    }

    public ClassLoader getClassLoader(Bundle bundle) {
        return b.a(bundle);
    }

    @Deprecated
    public void init(Context context, GtcIdCallback.Stub stub) {
        if (CommonUtil.isMainProcess(context)) {
            a.C0036a.a.a(stub);
            return;
        }
        GtcProvider.setContext(context);
        Bundle bundleCreateBundle = createBundle();
        bundleCreateBundle.putString(ProcessSwitchContract.METHOD_NAME, "gtc-1-1");
        BundleCompat.putBinder(bundleCreateBundle, "gtc-1-2", stub);
        Broker.getInstance().subscribe(bundleCreateBundle);
    }

    public String initialize(Context context, GtcIdCallback.Stub stub) {
        if (CommonUtil.isMainProcess(context)) {
            return a.C0036a.a.a(stub);
        }
        GtcProvider.setContext(context);
        Bundle bundleCreateBundle = createBundle();
        bundleCreateBundle.putString(ProcessSwitchContract.METHOD_NAME, "gtc-1-1");
        BundleCompat.putBinder(bundleCreateBundle, "gtc-1-2", stub);
        return Broker.getInstance().subscribe(bundleCreateBundle).getString(ProcessSwitchContract.METHOD_RETURN);
    }

    public boolean loadBundle(Context context, Bundle bundle) {
        if (context != null) {
            GtcProvider.setContext(context.getApplicationContext());
        }
        return b.a(context, bundle);
    }

    public void loadSdk(SdkInfo sdkInfo) {
        checkSdkInfo(sdkInfo);
        if (CommonUtil.isMainProcess()) {
            a.C0036a.a.a(sdkInfo);
            return;
        }
        Bundle bundleCreateBundle = createBundle();
        bundleCreateBundle.putString(ProcessSwitchContract.METHOD_NAME, "gtc-2-1");
        bundleCreateBundle.putParcelable("gtc-2-2", sdkInfo);
        Bundle bundleSubscribe = Broker.getInstance().subscribe(bundleCreateBundle);
        if (bundleSubscribe.get(ProcessSwitchContract.METHOD_EXCEPTION) != null) {
            com.getui.gtc.i.c.a.b((Throwable) bundleSubscribe.get(ProcessSwitchContract.METHOD_EXCEPTION));
        }
    }

    @Override // com.getui.gtc.base.publish.Subscriber
    public void receive(Bundle bundle, Bundle bundle2) {
        ArrayList arrayList = new ArrayList();
        try {
            try {
                Throwable th = (Throwable) bundle2.getSerializable(ProcessSwitchContract.METHOD_EXCEPTION);
                if (th != null) {
                    arrayList.add(th);
                }
                String string = bundle.getString(ProcessSwitchContract.METHOD_NAME);
                if (TextUtils.isEmpty(string)) {
                    throw new RuntimeException("methodName missed");
                }
                byte b = -1;
                int iHashCode = string.hashCode();
                if (iHashCode != 337397854) {
                    if (iHashCode != 337398815) {
                        if (iHashCode == 337399776 && string.equals("gtc-3-1")) {
                            b = 2;
                        }
                    } else if (string.equals("gtc-2-1")) {
                        b = 1;
                    }
                } else if (string.equals("gtc-1-1")) {
                    b = 0;
                }
                switch (b) {
                    case 0:
                        bundle2.putString(ProcessSwitchContract.METHOD_RETURN, a.C0036a.a.a(GtcIdCallback.Stub.asInterface(BundleCompat.getBinder(bundle, "gtc-1-2"))));
                        break;
                    case 1:
                        a.C0036a.a.a((SdkInfo) bundle.getParcelable("gtc-2-2"));
                        break;
                    case 2:
                        bundle.getString("gtc-3-2");
                        a.C0036a.a.a(bundle.getIntArray("gtc-3-3"));
                        break;
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    com.getui.gtc.i.c.a.a((Throwable) it.next());
                }
            } catch (Throwable th2) {
                arrayList.add(th2);
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    com.getui.gtc.i.c.a.a((Throwable) it2.next());
                }
            }
        } catch (Throwable th3) {
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                com.getui.gtc.i.c.a.a((Throwable) it3.next());
            }
            throw th3;
        }
    }

    public void removeExt(String str, int[] iArr) {
        if (CommonUtil.isMainProcess()) {
            a.C0036a.a.a(iArr);
            return;
        }
        Bundle bundleCreateBundle = createBundle();
        bundleCreateBundle.putString(ProcessSwitchContract.METHOD_NAME, "gtc-3-1");
        bundleCreateBundle.putString("gtc-3-2", str);
        bundleCreateBundle.putIntArray("gtc-3-3", iArr);
        Bundle bundleSubscribe = Broker.getInstance().subscribe(bundleCreateBundle);
        if (bundleSubscribe.get(ProcessSwitchContract.METHOD_EXCEPTION) != null) {
            com.getui.gtc.i.c.a.b((Throwable) bundleSubscribe.get(ProcessSwitchContract.METHOD_EXCEPTION));
        }
    }
}
