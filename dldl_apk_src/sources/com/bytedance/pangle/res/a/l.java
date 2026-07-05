package com.bytedance.pangle.res.a;

import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class l {
    public static void a(g gVar) throws IOException {
        gVar.b(0);
        int i = gVar.readInt();
        int i2 = gVar.readInt();
        int i3 = gVar.readInt();
        gVar.skipBytes(4);
        int i4 = gVar.readInt();
        int i5 = gVar.readInt();
        gVar.skipBytes(i2 * 4);
        if (i3 != 0) {
            gVar.skipBytes(i3 * 4);
        }
        gVar.skipBytes((i5 == 0 ? i : i5) - i4);
        if (i5 == 0) {
            return;
        }
        int i6 = i - i5;
        gVar.skipBytes(i6);
        int i7 = i6 % 4;
        if (i7 <= 0) {
            return;
        }
        while (true) {
            int i8 = i7 - 1;
            if (i7 <= 0) {
                return;
            }
            gVar.readByte();
            i7 = i8;
        }
    }
}
