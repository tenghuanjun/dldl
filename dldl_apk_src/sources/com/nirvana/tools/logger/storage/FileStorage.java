package com.nirvana.tools.logger.storage;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.jiguang.h5.PermissionUtils;
import com.nirvana.tools.core.SupportJarUtils;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
class FileStorage {
    private static final String BASE_DIR = ".pns";
    public static final String DIR_UNIQUE_ID = ".uniqueId";
    private Context mContext;

    public FileStorage(Context context) {
        this.mContext = context;
    }

    private boolean isBaseDirExist() {
        if (SupportJarUtils.checkSelfPermission(this.mContext, PermissionUtils.PERMISSION_READ_EXTERNAL_STORAGE) == -1 || !Environment.getExternalStorageState().equals("mounted")) {
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory(), BASE_DIR);
        return file.isDirectory() && file.exists();
    }

    private boolean newBaseDir() {
        if (SupportJarUtils.checkSelfPermission(this.mContext, PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE) != -1 && Environment.getExternalStorageState().equals("mounted")) {
            return new File(Environment.getExternalStorageDirectory(), BASE_DIR).mkdirs();
        }
        return false;
    }

    public boolean createNewFile(String str, String str2, boolean z) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = BASE_DIR;
        } else {
            str3 = BASE_DIR + File.separator + str;
        }
        if (SupportJarUtils.checkSelfPermission(this.mContext, PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE) == -1) {
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory(), str3);
        if (!file.exists() && !file.mkdirs()) {
            return false;
        }
        File file2 = new File(file, str2);
        if (file2.exists()) {
            return true;
        }
        try {
            return z ? file2.mkdirs() : file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String firstFileName(String str) {
        String[] list;
        if (TextUtils.isEmpty(str) || !isBaseDirExist()) {
            return null;
        }
        File file = new File(new File(Environment.getExternalStorageDirectory(), BASE_DIR), str);
        if (!file.exists() || !file.isDirectory() || (list = file.list()) == null || list.length <= 0) {
            return null;
        }
        return list[0];
    }

    public boolean isFileExist(String str) {
        if (!TextUtils.isEmpty(str) && isBaseDirExist()) {
            return new File(new File(Environment.getExternalStorageDirectory(), BASE_DIR), str).exists();
        }
        return false;
    }
}
