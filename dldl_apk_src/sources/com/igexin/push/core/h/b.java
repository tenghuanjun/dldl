package com.igexin.push.core.h;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.igexin.push.core.b.h;
import com.igexin.push.extension.mod.BaseActionBean;
import java.io.File;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b extends com.igexin.push.e.a.b {
    public static final int a = 2;
    public static final int b = 8;
    public static final int c = 65557;
    private static final String m = "EXT-DownloadImgPlugin";
    private String n;
    private BaseActionBean o;
    private int p;
    private d q;
    private String r;

    public b(String str, String str2, String str3, BaseActionBean baseActionBean, int i, d dVar) {
        super(str);
        this.o = baseActionBean;
        this.n = str3;
        this.p = i;
        this.q = dVar;
        this.r = str2;
        this.k = false;
    }

    private void a(String str) {
        int i = this.p;
        if (i == 2) {
            ((h) this.o).w = str;
        } else {
            if (i != 8) {
                return;
            }
            ((h) this.o).x = str;
        }
    }

    private static void b() {
        File file = new File(com.igexin.push.core.e.aJ);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    @Override // com.igexin.push.e.a.b
    public final void a(Exception exc) {
        d dVar = this.q;
        if (dVar != null) {
            dVar.a();
        }
    }

    @Override // com.igexin.push.e.a.b
    public final void a(byte[] bArr) {
        this.l = false;
        try {
            File file = new File(com.igexin.push.core.e.aJ);
            if (!file.exists()) {
                file.mkdirs();
            }
            String str = com.igexin.push.core.e.aJ + com.igexin.assist.util.a.a(this.r) + ".bin";
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.PNG;
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            if (bitmapDecodeByteArray != null) {
                bitmapDecodeByteArray.compress(compressFormat, 100, fileOutputStream);
                fileOutputStream.close();
                bitmapDecodeByteArray.recycle();
                int i = this.p;
                if (i == 2) {
                    ((h) this.o).w = str;
                } else if (i == 8) {
                    ((h) this.o).x = str;
                }
                this.l = true;
            } else {
                fileOutputStream.close();
                this.l = false;
            }
            if (this.q != null) {
                if (this.l) {
                    this.q.a(this.o);
                    return;
                }
                d dVar = this.q;
                new Exception("no target existed or downloading bitmap failed!");
                dVar.a();
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return c;
    }
}
