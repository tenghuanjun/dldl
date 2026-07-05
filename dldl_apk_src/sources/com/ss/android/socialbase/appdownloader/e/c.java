package com.ss.android.socialbase.appdownloader.e;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class c {
    private static int a = 8;
    private static volatile c b;
    private a<Integer, Bitmap> c;

    private static class a<K, T> extends LinkedHashMap<K, T> {
        final int a;

        public a(int i, int i2) {
            super(i2, 0.75f, true);
            this.a = i;
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<K, T> entry) {
            return size() > this.a;
        }
    }

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    private c() {
        this.c = null;
        int i = a;
        this.c = new a<>(i, i / 2);
    }

    public Bitmap a(int i) {
        return this.c.get(Integer.valueOf(i));
    }

    public void a(final int i, final String str) {
        if (TextUtils.isEmpty(str) || a(i) != null) {
            return;
        }
        DownloadComponentManager.getIOThreadExecutor().submit(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.e.c.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                ByteArrayOutputStream byteArrayOutputStreamB;
                ByteArrayInputStream byteArrayInputStream;
                ByteArrayInputStream byteArrayInputStream2;
                Throwable th;
                InputStream inputStream;
                Exception e;
                IDownloadHttpConnection iDownloadHttpConnectionDownloadWithConnection;
                int i2 = 4;
                i2 = 4;
                i2 = 4;
                i2 = 4;
                i2 = 4;
                try {
                    try {
                        iDownloadHttpConnectionDownloadWithConnection = DownloadComponentManager.downloadWithConnection(true, 0, str, null);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e2) {
                    byteArrayOutputStreamB = null;
                    byteArrayInputStream = null;
                    byteArrayInputStream2 = null;
                    e = e2;
                    inputStream = null;
                } catch (Throwable th3) {
                    byteArrayOutputStreamB = null;
                    byteArrayInputStream = null;
                    byteArrayInputStream2 = null;
                    th = th3;
                    inputStream = null;
                }
                if (iDownloadHttpConnectionDownloadWithConnection != null) {
                    inputStream = iDownloadHttpConnectionDownloadWithConnection.getInputStream();
                    try {
                        byteArrayOutputStreamB = c.b(inputStream);
                        try {
                            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStreamB.toByteArray());
                            try {
                                byteArrayInputStream2 = new ByteArrayInputStream(byteArrayOutputStreamB.toByteArray());
                                try {
                                    BitmapFactory.Options options = new BitmapFactory.Options();
                                    options.inJustDecodeBounds = true;
                                    BitmapFactory.decodeStream(byteArrayInputStream, null, options);
                                    int i3 = options.outWidth;
                                    int i4 = options.outHeight;
                                    int iA = com.ss.android.socialbase.appdownloader.c.a(DownloadComponentManager.getAppContext(), 44.0f);
                                    options.inSampleSize = c.a(iA, iA, options);
                                    options.inJustDecodeBounds = false;
                                    c.this.c.put(Integer.valueOf(i), BitmapFactory.decodeStream(byteArrayInputStream2, null, options));
                                    Closeable[] closeableArr = {inputStream, byteArrayOutputStreamB, byteArrayInputStream, byteArrayInputStream2};
                                    DownloadUtils.safeClose(closeableArr);
                                    i2 = closeableArr;
                                } catch (Exception e3) {
                                    e = e3;
                                    e.printStackTrace();
                                    Closeable[] closeableArr2 = {inputStream, byteArrayOutputStreamB, byteArrayInputStream, byteArrayInputStream2};
                                    DownloadUtils.safeClose(closeableArr2);
                                    i2 = closeableArr2;
                                }
                            } catch (Exception e4) {
                                byteArrayInputStream2 = null;
                                e = e4;
                            } catch (Throwable th4) {
                                byteArrayInputStream2 = null;
                                th = th4;
                                Closeable[] closeableArr3 = new Closeable[i2];
                                closeableArr3[0] = inputStream;
                                closeableArr3[1] = byteArrayOutputStreamB;
                                closeableArr3[2] = byteArrayInputStream;
                                closeableArr3[3] = byteArrayInputStream2;
                                DownloadUtils.safeClose(closeableArr3);
                                throw th;
                            }
                        } catch (Exception e5) {
                            byteArrayInputStream2 = null;
                            e = e5;
                            byteArrayInputStream = null;
                        } catch (Throwable th5) {
                            byteArrayInputStream2 = null;
                            th = th5;
                            byteArrayInputStream = null;
                        }
                    } catch (Exception e6) {
                        byteArrayInputStream = null;
                        byteArrayInputStream2 = null;
                        e = e6;
                        byteArrayOutputStreamB = null;
                    } catch (Throwable th6) {
                        byteArrayInputStream = null;
                        byteArrayInputStream2 = null;
                        th = th6;
                        byteArrayOutputStreamB = null;
                    }
                }
                DownloadUtils.safeClose(null, null, null, null);
                return;
                e.printStackTrace();
                Closeable[] closeableArr22 = {inputStream, byteArrayOutputStreamB, byteArrayInputStream, byteArrayInputStream2};
                DownloadUtils.safeClose(closeableArr22);
                i2 = closeableArr22;
            }
        });
    }

    public static int a(int i, int i2, BitmapFactory.Options options) {
        if (options.outWidth > i || options.outHeight > i2) {
            return Math.min(Math.round(options.outWidth / i), Math.round(options.outHeight / i2));
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteArrayOutputStream b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i > -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream;
            }
        }
    }
}
