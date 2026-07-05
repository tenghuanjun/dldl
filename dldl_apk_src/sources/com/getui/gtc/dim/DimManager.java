package com.getui.gtc.dim;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.ProcessSwitchContract;
import com.getui.gtc.base.crypt.CryptTools;
import com.getui.gtc.base.publish.Broker;
import com.getui.gtc.base.publish.Subscriber;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.dim.DimRequest;
import com.getui.gtc.dim.a;
import com.getui.gtc.dim.b.d;
import com.getui.gtc.dim.d.a;
import com.getui.gtc.dim.d.b;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class DimManager implements Subscriber {
    private static String methodName;

    static class a {
        private static final DimManager a = new DimManager();
    }

    private DimManager() {
    }

    private Bundle createBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(ProcessSwitchContract.CLASS_NAME, getClass().getName());
        bundle.putString(ProcessSwitchContract.GET_INSTANCE, methodName);
        return bundle;
    }

    public static DimManager getInstance() {
        methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return a.a;
    }

    public Object get(DimRequest dimRequest) {
        if (CommonUtil.isMainProcess()) {
            return a.C0037a.a.a(dimRequest, true);
        }
        Bundle bundleCreateBundle = createBundle();
        bundleCreateBundle.putString(ProcessSwitchContract.METHOD_NAME, "dim-1-1-1");
        bundleCreateBundle.putParcelable("dim-1-1-2", dimRequest);
        Object obj = Broker.getInstance().subscribe(bundleCreateBundle).get(ProcessSwitchContract.METHOD_RETURN);
        if (!(obj instanceof File)) {
            return obj;
        }
        try {
            byte[] bArrA = b.a((File) obj);
            ((File) obj).delete();
            return b.a(bArrA);
        } catch (Throwable unused) {
            return null;
        }
    }

    public Object get(String str) {
        return get(new DimRequest.Builder().key(str).build());
    }

    @Override // com.getui.gtc.base.publish.Subscriber
    public void receive(Bundle bundle, Bundle bundle2) {
        String str;
        Serializable serializable;
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
                if (iHashCode != 1538245748) {
                    if (iHashCode == 1538246709 && string.equals("dim-1-2-1")) {
                        b = 1;
                    }
                } else if (string.equals("dim-1-1-1")) {
                    b = 0;
                }
                switch (b) {
                    case 0:
                        Object obj = get((DimRequest) bundle.getParcelable("dim-1-1-2"));
                        if (obj != null) {
                            byte[] bArrA = b.a(obj);
                            if (bArrA.length > 204800) {
                                File file = new File(GtcProvider.context().getCacheDir(), CryptTools.digestToHexString("MD5", bArrA));
                                if (!b.a(bArrA, file)) {
                                    throw new RuntimeException("failed to save dim result bytes to file");
                                }
                                str = ProcessSwitchContract.METHOD_RETURN;
                                serializable = file;
                            } else {
                                if (obj instanceof Parcelable) {
                                    bundle2.putParcelable(ProcessSwitchContract.METHOD_RETURN, (Parcelable) obj);
                                }
                                if (obj instanceof Serializable) {
                                    str = ProcessSwitchContract.METHOD_RETURN;
                                    serializable = (Serializable) obj;
                                }
                            }
                            bundle2.putSerializable(str, serializable);
                        }
                        break;
                    case 1:
                        set(bundle.getString("dim-1-2-2"), bundle.getString("dim-1-2-3"), bundle.getString("dim-1-2-4"));
                        break;
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    com.getui.gtc.dim.d.a.a((Throwable) it.next());
                }
            } catch (Throwable th2) {
                arrayList.add(th2);
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    com.getui.gtc.dim.d.a.a((Throwable) it2.next());
                }
            }
        } catch (Throwable th3) {
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                com.getui.gtc.dim.d.a.a((Throwable) it3.next());
            }
            throw th3;
        }
    }

    public void set(String str, String str2, String str3) {
        if (!CommonUtil.isMainProcess()) {
            Bundle bundleCreateBundle = createBundle();
            bundleCreateBundle.putString(ProcessSwitchContract.METHOD_NAME, "dim-1-2-1");
            bundleCreateBundle.putString("dim-1-2-2", str);
            bundleCreateBundle.putString("dim-1-2-3", str2);
            bundleCreateBundle.putString("dim-1-2-4", str3);
            Broker.getInstance().subscribe(bundleCreateBundle);
            return;
        }
        com.getui.gtc.dim.a aVar = a.C0037a.a;
        try {
            switch (str) {
                case "dim-2-2-1-1":
                    com.getui.gtc.dim.b.a aVar2 = aVar.a;
                    long j = Long.parseLong(str3);
                    if (!TextUtils.isEmpty(str2)) {
                        aVar2.a.put(str2, Long.valueOf(j));
                        com.getui.gtc.dim.d.a.a("dim ram globalValidTime set: " + str2 + " : " + j);
                        break;
                    }
                    break;
                case "dim-2-2-2-1":
                    com.getui.gtc.dim.b.b bVar = aVar.b;
                    long j2 = Long.parseLong(str3);
                    if (!TextUtils.isEmpty(str2)) {
                        bVar.a.put(str2, Long.valueOf(j2));
                        com.getui.gtc.dim.d.a.a("dim storage globalValidTime set: " + str2 + " : " + j2);
                        break;
                    }
                    break;
                case "dim-2-2-3-1":
                    d dVar = aVar.c;
                    int i = Integer.parseInt(str3);
                    if (!TextUtils.isEmpty(str2)) {
                        dVar.a.put(str2, Integer.valueOf(i));
                        com.getui.gtc.dim.d.a.a("dim sys globalAllow set: " + str2 + " : " + i);
                        break;
                    }
                    break;
                case "dim-2-2-4-1":
                    d.b(str3);
                    break;
                case "dim-2-2-5-1":
                    d.a(str3);
                    break;
                case "dim-2-2-6-1":
                    aVar.a(str2, str3);
                    break;
            }
        } catch (Throwable th) {
            a.C0041a.a.a.e(th);
        }
    }
}
