package com.bytedance.pangle.receiver;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.pangle.util.FieldUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b {
    private static final c a;

    interface c {
        boolean a(Context context);
    }

    static {
        int i = Build.VERSION.SDK_INT;
        byte b = 0;
        if (i < 24) {
            a = new a(b);
            return;
        }
        if (i < 26) {
            a = new d(b);
        } else if (i < 28) {
            a = new e(b);
        } else {
            a = new C0021b(b);
        }
    }

    /* JADX INFO: renamed from: com.bytedance.pangle.receiver.b$b, reason: collision with other inner class name */
    static class C0021b extends e {
        @Override // com.bytedance.pangle.receiver.b.e, com.bytedance.pangle.receiver.b.a, com.bytedance.pangle.receiver.b.c
        public final boolean a(Context context) {
            return false;
        }

        private C0021b() {
            super((byte) 0);
        }

        /* synthetic */ C0021b(byte b) {
            this();
        }
    }

    static class e extends a {
        private e() {
            super((byte) 0);
        }

        /* synthetic */ e(byte b) {
            this();
        }

        @Override // com.bytedance.pangle.receiver.b.a, com.bytedance.pangle.receiver.b.c
        public boolean a(Context context) {
            Object objA = a(context, "mWhiteListMap");
            if (!(objA instanceof Map)) {
                return false;
            }
            Map map = (Map) objA;
            List arrayList = (List) map.get(0);
            if (arrayList == null) {
                arrayList = new ArrayList();
                map.put(0, arrayList);
            }
            arrayList.add(context.getPackageName());
            return true;
        }
    }

    static class d extends a {
        private d() {
            super((byte) 0);
        }

        /* synthetic */ d(byte b) {
            this();
        }

        @Override // com.bytedance.pangle.receiver.b.a, com.bytedance.pangle.receiver.b.c
        public final boolean a(Context context) {
            Object objA = a(context, "mWhiteList");
            if (!(objA instanceof List)) {
                return false;
            }
            ((List) objA).add(context.getPackageName());
            return true;
        }
    }

    static class a implements c {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        @Override // com.bytedance.pangle.receiver.b.c
        public boolean a(Context context) throws IllegalAccessException {
            Object objB = b(context);
            Object objA = a(objB, "mWhiteList");
            if (!(objA instanceof String[])) {
                if (objB == null) {
                    return false;
                }
                FieldUtils.writeField(objB, "mResourceConfig", (Object) null);
                return false;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(context.getPackageName());
            Collections.addAll(arrayList, (String[]) objA);
            FieldUtils.writeField(objB, "mWhiteList", arrayList.toArray(new String[arrayList.size()]));
            return true;
        }

        static Object a(Context context, String str) {
            return a(b(context), str);
        }

        private static Object a(Object obj, String str) {
            if (obj == null) {
                return null;
            }
            try {
                return FieldUtils.readField(obj, str);
            } catch (Throwable unused) {
                return null;
            }
        }

        private static Object b(Context context) {
            Field field;
            Object field2;
            try {
                Field field3 = FieldUtils.getField(Class.forName("android.app.LoadedApk"), "mReceiverResource");
                if (field3 == null || (field = FieldUtils.getField(Class.forName("android.app.ContextImpl"), "mPackageInfo")) == null || (field2 = FieldUtils.readField(field, context)) == null) {
                    return null;
                }
                return FieldUtils.readField(field3, field2);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public static void a(Application application) {
        if (application != null) {
            try {
                if (TextUtils.equals(Build.BRAND.toLowerCase(), "huawei")) {
                    a.a(application.getBaseContext());
                }
            } catch (Throwable unused) {
            }
        }
    }
}
