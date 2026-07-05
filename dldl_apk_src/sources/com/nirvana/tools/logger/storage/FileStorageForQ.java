package com.nirvana.tools.logger.storage;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
@TargetApi(29)
class FileStorageForQ extends FileStorage {
    private boolean isExternalStorageLegacy;

    public FileStorageForQ(Context context) {
        super(context);
        this.isExternalStorageLegacy = false;
        initExternalStorageLegacy();
    }

    private void initExternalStorageLegacy() {
        try {
            this.isExternalStorageLegacy = ((Boolean) Environment.class.getDeclaredMethod("isExternalStorageLegacy", new Class[0]).invoke(Environment.class, new Object[0])).booleanValue();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.nirvana.tools.logger.storage.FileStorage
    public boolean createNewFile(String str, String str2, boolean z) {
        if (this.isExternalStorageLegacy) {
            return super.createNewFile(str, str2, z);
        }
        return false;
    }

    @Override // com.nirvana.tools.logger.storage.FileStorage
    public String firstFileName(String str) {
        if (this.isExternalStorageLegacy) {
            return super.firstFileName(str);
        }
        return null;
    }

    @Override // com.nirvana.tools.logger.storage.FileStorage
    public boolean isFileExist(String str) {
        if (this.isExternalStorageLegacy) {
            return super.isFileExist(str);
        }
        return false;
    }
}
