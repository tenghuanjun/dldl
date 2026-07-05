package com.alipay.zoloz.toyger.face;

import android.graphics.Bitmap;
import android.os.Environment;
import com.alipay.zoloz.toyger.algorithm.TGDepthFrame;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class FrameProcessor {
    public static final SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS", Locale.US);
    private static final boolean SAVE_BITMAP = false;
    private static final String TAG = "FrameProcessor";
    private TGDepthFrame mTgDepthFrame;
    private TGFrame mTgFrame;

    public static class LogFileNameFilter implements FilenameFilter {
        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith(".bin");
        }
    }

    public static void bitmap2File(Bitmap bitmap, File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException | IOException unused) {
        }
    }

    public static void clearOldBinFiles() {
        File file = new File(Environment.getExternalStorageDirectory(), "Download");
        if (file.exists()) {
            try {
                File[] fileArrListFiles = file.listFiles(new LogFileNameFilter());
                if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(3L);
                for (File file2 : fileArrListFiles) {
                    if (file2.lastModified() < jCurrentTimeMillis && file2.delete()) {
                        file2.deleteOnExit();
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static String getDetailDateFormat(long j) {
        String str;
        SimpleDateFormat simpleDateFormat = DATE_FORMAT2;
        synchronized (simpleDateFormat) {
            str = simpleDateFormat.format(new Date(j));
        }
        return str;
    }

    public static synchronized boolean save(File file, byte[] bArr) {
        boolean z;
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        z = false;
        if (file != null && bArr != null) {
            if (file.exists()) {
                file.delete();
            } else {
                try {
                    file.createNewFile();
                } catch (IOException unused) {
                }
            }
            FileOutputStream fileOutputStream = null;
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream2);
                } catch (IOException unused2) {
                    bufferedOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedOutputStream = null;
                }
                try {
                    bufferedOutputStream.write(bArr);
                    bufferedOutputStream.flush();
                    z = true;
                    close(bufferedOutputStream);
                    close(fileOutputStream2);
                } catch (IOException unused3) {
                    fileOutputStream = fileOutputStream2;
                    close(bufferedOutputStream);
                    close(fileOutputStream);
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = fileOutputStream2;
                    close(bufferedOutputStream);
                    close(fileOutputStream);
                    throw th;
                }
            } catch (IOException unused4) {
                bufferedOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                bufferedOutputStream = null;
            }
        }
        return z;
    }

    public static void saveBitmap(Bitmap bitmap, String str) {
        String str2 = getDetailDateFormat(System.currentTimeMillis()) + "_" + str + ".png";
        File file = new File(Environment.getExternalStorageDirectory(), "Download");
        if (!file.exists()) {
            file.mkdirs();
        }
        bitmap2File(bitmap, new File(file, str2));
    }

    public static void saveBitmap(TGFrame tGFrame, TGDepthFrame tGDepthFrame, ToygerFaceAttr toygerFaceAttr, boolean z, String str) {
    }

    public void clearFrame() {
        this.mTgFrame = null;
        this.mTgDepthFrame = null;
    }

    public TGDepthFrame getTgDepthFrame() {
        return this.mTgDepthFrame;
    }

    public TGFrame getTgFrame() {
        return this.mTgFrame;
    }

    public void initFame(TGFrame tGFrame, TGDepthFrame tGDepthFrame) {
        this.mTgFrame = tGFrame;
        this.mTgDepthFrame = tGDepthFrame;
    }

    public void saveBitmap(ToygerFaceAttr toygerFaceAttr, boolean z, String str) {
        saveBitmap(this.mTgFrame, this.mTgDepthFrame, toygerFaceAttr, z, str);
    }

    public void saveTgDepthFrame(String str) {
    }

    public void saveTgFrame(String str) {
        File file = new File(Environment.getExternalStorageDirectory(), "Download");
        if (!file.exists()) {
            file.mkdirs();
        }
        String str2 = getDetailDateFormat(System.currentTimeMillis()) + "_" + str;
        save(new File(file, str2 + "_raw.bin"), this.mTgFrame.data);
    }
}
