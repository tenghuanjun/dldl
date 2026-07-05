package com.bytedance.pangle.e;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.e.f;
import com.bytedance.pangle.util.FieldUtils;
import com.bytedance.pangle.util.MethodUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class e implements f.a {
    private static volatile IBinder a;
    private static volatile Object b;

    public interface a {
    }

    private static void a(String[] strArr) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeFileDescriptor(FileDescriptor.in);
        parcelObtain.writeFileDescriptor(FileDescriptor.out);
        parcelObtain.writeFileDescriptor(FileDescriptor.err);
        parcelObtain.writeStringArray(strArr);
        parcelObtain.writeStrongBinder(null);
        new b().writeToParcel(parcelObtain, 0);
        try {
            a.transact(1598246212, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } catch (Exception unused) {
        } catch (Throwable th) {
            parcelObtain.recycle();
            parcelObtain2.recycle();
            throw th;
        }
        parcelObtain.recycle();
        parcelObtain2.recycle();
    }

    static class b extends ResultReceiver {
        private a a;

        public b() {
            super(null);
            this.a = null;
        }

        @Override // android.os.ResultReceiver
        protected final void onReceiveResult(int i, Bundle bundle) {
            super.onReceiveResult(i, bundle);
        }
    }

    private static Object a(Field field, Object obj) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            Object obj2 = field.get(obj);
            field.setAccessible(false);
            return obj2;
        } catch (Exception unused) {
            return null;
        }
    }

    private static Object a(Object obj, String str, Object[] objArr, Class<?>[] clsArr) {
        try {
            return MethodUtils.invokeMethod(obj, str, objArr, clsArr);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.bytedance.pangle.e.f.a
    public final boolean a(String str, int i) {
        boolean z;
        IBinder iBinderAsBinder;
        if (a == null) {
            PackageManager packageManager = Zeus.getAppApplication().getPackageManager();
            Field field = packageManager == null ? null : FieldUtils.getField(packageManager.getClass(), "mPM");
            if (field != null) {
                Object objA = a(field, packageManager);
                b = objA;
                if ((objA instanceof IInterface) && (iBinderAsBinder = ((IInterface) b).asBinder()) != null) {
                    a = iBinderAsBinder;
                }
            }
        }
        if (str != null) {
            String strB = com.bytedance.pangle.d.c.b(str, i);
            String strE = com.bytedance.pangle.d.c.e(str, i);
            try {
                com.bytedance.pangle.util.h.a(strB, strE);
            } catch (Exception unused) {
            }
            String packageName = Zeus.getAppApplication().getPackageName();
            String strA = com.bytedance.pangle.e.b.a();
            if (Build.VERSION.SDK_INT != 30) {
                if (Build.VERSION.SDK_INT == 29 && b != null && packageName != null && strE != null && strA != null) {
                    a(b, "notifyDexLoad", new Object[]{packageName, Collections.singletonList("dalvik.system.DexClassLoader"), Collections.singletonList(strE), strA}, new Class[]{String.class, List.class, List.class, String.class});
                }
            } else if (b != null && packageName != null && strE != null && strA != null) {
                a(b, "notifyDexLoad", new Object[]{packageName, Collections.singletonMap(strE, "PCL[]"), strA}, new Class[]{String.class, Map.class, String.class});
            }
        }
        String strE2 = com.bytedance.pangle.d.c.e(str, i);
        String str2 = com.bytedance.pangle.d.c.h(str, i) + File.separator + com.bytedance.pangle.e.b.a(strE2);
        int i2 = 1;
        while (true) {
            if (i2 > 3) {
                z = false;
                break;
            }
            a(new String[]{"compile", "-m", "speed", "-f", "--secondary-dex", Zeus.getAppApplication().getPackageName()});
            if (com.bytedance.pangle.e.b.a(str2)) {
                z = true;
                break;
            }
            i2++;
        }
        if (str != null) {
            try {
                com.bytedance.pangle.util.g.a(com.bytedance.pangle.d.c.g(str, i), com.bytedance.pangle.d.c.f(str, i));
            } catch (Exception unused2) {
            }
            try {
                File file = new File(com.bytedance.pangle.d.c.e(str, i));
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception unused3) {
            }
            a(new String[]{"reconcile-secondary-dex-files", Zeus.getAppApplication().getPackageName()});
        }
        return z;
    }
}
