package com.taptap.sdk.db.utils;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: DefaultPropertiesVerifyFunc.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\u001a&\u0010\u0000\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00040\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"defaultPropertiesVerifyFunc", "Lkotlin/Function2;", "", "", "", "maxLength", "", "tap-db_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class DefaultPropertiesVerifyFuncKt {
    public static /* synthetic */ Function2 defaultPropertiesVerifyFunc$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 256;
        }
        return defaultPropertiesVerifyFunc(i);
    }

    public static final Function2<String, Object, Boolean> defaultPropertiesVerifyFunc(final int i) {
        return new Function2<String, Object, Boolean>() { // from class: com.taptap.sdk.db.utils.DefaultPropertiesVerifyFuncKt.defaultPropertiesVerifyFunc.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x002c  */
            @Override // kotlin.jvm.functions.Function2
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Boolean invoke(java.lang.String r5, java.lang.Object r6) {
                /*
                    r4 = this;
                    r0 = r5
                    java.lang.CharSequence r0 = (java.lang.CharSequence) r0
                    r1 = 0
                    r2 = 1
                    if (r0 == 0) goto L10
                    int r0 = r0.length()
                    if (r0 != 0) goto Le
                    goto L10
                Le:
                    r0 = 0
                    goto L11
                L10:
                    r0 = 1
                L11:
                    if (r0 == 0) goto L14
                    goto L3f
                L14:
                    int r0 = r5.length()
                    int r3 = r1
                    if (r0 <= r3) goto L1d
                    goto L3f
                L1d:
                    boolean r0 = r6 instanceof java.lang.CharSequence
                    if (r0 == 0) goto L2e
                    r0 = r6
                    java.lang.CharSequence r0 = (java.lang.CharSequence) r0
                    int r0 = r0.length()
                    int r3 = r1
                    if (r0 > r3) goto L3f
                L2c:
                    r1 = 1
                    goto L3f
                L2e:
                    boolean r0 = r6 instanceof java.lang.Number
                    if (r0 == 0) goto L34
                    r0 = 1
                    goto L36
                L34:
                    boolean r0 = r6 instanceof java.lang.Boolean
                L36:
                    if (r0 == 0) goto L3a
                    r0 = 1
                    goto L3c
                L3a:
                    boolean r0 = r6 instanceof java.util.Date
                L3c:
                    if (r0 == 0) goto L3f
                    goto L2c
                L3f:
                    if (r1 != 0) goto L5f
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r2 = "The error property has been removed, key="
                    r0.append(r2)
                    r0.append(r5)
                    java.lang.String r5 = ", value="
                    r0.append(r5)
                    r0.append(r6)
                    java.lang.String r5 = r0.toString()
                    java.lang.String r6 = "TapDB"
                    android.util.Log.e(r6, r5)
                L5f:
                    java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.db.utils.DefaultPropertiesVerifyFuncKt.AnonymousClass1.invoke(java.lang.String, java.lang.Object):java.lang.Boolean");
            }
        };
    }
}
