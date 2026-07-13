package com.volcengine.j;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.volcengine.common.SDKContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/* JADX INFO: loaded from: classes3.dex */
public final class i {
    static {
        System.getProperty("line.separator");
    }

    public static boolean a(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static boolean a(String str) {
        return a(c(str));
    }

    public static boolean b(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!a(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean b(String str) {
        return c(c(str));
    }

    public static File c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new File(str);
    }

    public static boolean c(File file) {
        if (file == null) {
            return false;
        }
        return file.isDirectory() ? d(file) : e(file);
    }

    private static boolean d(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            for (File file2 : fileArrListFiles) {
                if (file2.isFile()) {
                    if (!file2.delete()) {
                        return false;
                    }
                } else if (file2.isDirectory() && !d(file2)) {
                    return false;
                }
            }
        }
        if (!file.canWrite()) {
            file.setWritable(true);
        }
        return file.delete();
    }

    public static boolean d(String str) {
        File fileC = c(str);
        if (fileC == null) {
            return false;
        }
        if (fileC.exists()) {
            return true;
        }
        return e(str);
    }

    private static boolean e(File file) {
        if (file != null && !file.canWrite()) {
            file.setWritable(true);
        }
        if (file != null) {
            if (!file.exists()) {
                return true;
            }
            if (file.isFile() && file.delete()) {
                return true;
            }
        }
        return false;
    }

    private static boolean e(String str) {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = SDKContext.getContext().getContentResolver().openAssetFileDescriptor(Uri.parse(str), "r");
                if (assetFileDescriptorOpenAssetFileDescriptor == null) {
                    return false;
                }
                try {
                    assetFileDescriptorOpenAssetFileDescriptor.close();
                    return true;
                } catch (IOException unused) {
                    return true;
                }
            } catch (FileNotFoundException unused2) {
            }
        }
        return false;
    }

    public static String f(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            File fileC = c(str);
            if (fileC != null && fileC.isFile() && f(fileC)) {
                Scanner scanner = new Scanner(fileC);
                while (scanner.hasNextLine()) {
                    sb.append(scanner.nextLine());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static boolean f(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        return d(file.getAbsolutePath());
    }
}
