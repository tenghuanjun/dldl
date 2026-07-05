package com.ss.android.downloadlib.addownload.compliance;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.g.c;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.ss.android.socialbase.downloader.utils.LruCache;
import java.io.BufferedInputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class d extends LruCache<Long, Bitmap> {
    private final Map<Long, SoftReference<a>> a;

    public interface a {
        void a(Bitmap bitmap);
    }

    private static class b {
        private static d a = new d();
    }

    public static d a() {
        return b.a;
    }

    private d() {
        super(8, 8);
        this.a = new HashMap();
    }

    public void a(long j, a aVar) {
        if (get(Long.valueOf(j)) != null) {
            aVar.a((Bitmap) get(Long.valueOf(j)));
        } else {
            this.a.put(Long.valueOf(j), new SoftReference<>(aVar));
        }
    }

    public void a(final long j, final long j2, final String str) {
        if (get(Long.valueOf(j)) != null) {
            SoftReference<a> softReferenceRemove = this.a.remove(Long.valueOf(j));
            if (softReferenceRemove == null || softReferenceRemove.get() == null) {
                return;
            }
            softReferenceRemove.get().a((Bitmap) get(Long.valueOf(j)));
            return;
        }
        if (TextUtils.isEmpty(str)) {
            g.a(12, j2);
        } else {
            com.ss.android.downloadlib.g.c.a((c.a<Object, R>) new c.a<Object, Object>() { // from class: com.ss.android.downloadlib.addownload.compliance.d.2
                @Override // com.ss.android.downloadlib.g.c.a
                public Object a(Object obj) throws Throwable {
                    BufferedInputStream bufferedInputStream;
                    Throwable th;
                    IDownloadHttpConnection iDownloadHttpConnectionDownloadWithConnection;
                    try {
                        iDownloadHttpConnectionDownloadWithConnection = DownloadComponentManager.downloadWithConnection(true, 0, str, null);
                    } catch (Exception e) {
                        e = e;
                        bufferedInputStream = null;
                    } catch (Throwable th2) {
                        bufferedInputStream = null;
                        th = th2;
                        DownloadUtils.safeClose(bufferedInputStream);
                        throw th;
                    }
                    if (iDownloadHttpConnectionDownloadWithConnection != null) {
                        bufferedInputStream = new BufferedInputStream(iDownloadHttpConnectionDownloadWithConnection.getInputStream());
                        try {
                            try {
                                bufferedInputStream.mark(bufferedInputStream.available());
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inJustDecodeBounds = true;
                                BitmapFactory.decodeStream(bufferedInputStream, null, options);
                                int i = options.outWidth;
                                int i2 = options.outHeight;
                                int iA = m.a(k.a(), 60.0f);
                                options.inSampleSize = d.b(iA, iA, options);
                                options.inJustDecodeBounds = false;
                                bufferedInputStream.reset();
                                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(bufferedInputStream, null, options);
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.putOpt("ttdownloader_type", "load_bitmap");
                                    jSONObject.putOpt("bm_original_w", Integer.valueOf(i));
                                    jSONObject.putOpt("bm_original_h", Integer.valueOf(i2));
                                    jSONObject.putOpt("bm_bytes", Integer.valueOf(bitmapDecodeStream == null ? -1 : bitmapDecodeStream.getByteCount()));
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                                com.ss.android.downloadlib.d.a.a().a("ttd_pref_monitor", jSONObject, j2);
                                d.this.put(Long.valueOf(j), bitmapDecodeStream);
                                DownloadUtils.safeClose(bufferedInputStream);
                            } catch (Exception e3) {
                                e = e3;
                                com.ss.android.downloadlib.e.c.a().a(e, "BitmapCache loadBitmap");
                                DownloadUtils.safeClose(bufferedInputStream);
                            }
                            return null;
                        } catch (Throwable th3) {
                            th = th3;
                            DownloadUtils.safeClose(bufferedInputStream);
                            throw th;
                        }
                    }
                    DownloadUtils.safeClose(null);
                    return null;
                    com.ss.android.downloadlib.e.c.a().a(e, "BitmapCache loadBitmap");
                    DownloadUtils.safeClose(bufferedInputStream);
                    return null;
                }
            }, (Object) null).a(new c.a<Object, Object>() { // from class: com.ss.android.downloadlib.addownload.compliance.d.1
                @Override // com.ss.android.downloadlib.g.c.a
                public Object a(Object obj) {
                    SoftReference softReference = (SoftReference) d.this.a.remove(Long.valueOf(j));
                    if (softReference == null || softReference.get() == null) {
                        return null;
                    }
                    ((a) softReference.get()).a((Bitmap) d.this.get(Long.valueOf(j)));
                    return null;
                }
            }).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(int i, int i2, BitmapFactory.Options options) {
        if (options.outWidth > i || options.outHeight > i2) {
            return Math.min(Math.round(options.outWidth / i), Math.round(options.outHeight / i2));
        }
        return 1;
    }
}
