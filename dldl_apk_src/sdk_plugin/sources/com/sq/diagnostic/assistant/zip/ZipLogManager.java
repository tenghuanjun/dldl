package com.sq.diagnostic.assistant.zip;

import android.text.TextUtils;
import com.sq.diagnostic.assistant.log.impl.ErrorStats;
import com.sq.diagnostic.assistant.other.ThreadPoolManager;
import java.io.File;
import java.util.Queue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ZipLogManager {
    private static volatile ZipLogManager instance;

    public static ZipLogManager getInstance() {
        if (instance == null) {
            synchronized (ZipLogManager.class) {
                if (instance == null) {
                    instance = new ZipLogManager();
                }
            }
        }
        return instance;
    }

    public void zip(final Queue<String> queue, final String str, final String str2, final ICompressionCallBack iCompressionCallBack) {
        ThreadPoolManager.getInstance().execute(new Runnable() { // from class: com.sq.diagnostic.assistant.zip.-$$Lambda$ZipLogManager$bxvwDcwlIgFv11-rHUPSICHrOW8
            @Override // java.lang.Runnable
            public final void run() {
                ZipLogManager.lambda$zip$0(str, str2, queue, iCompressionCallBack);
            }
        });
    }

    static /* synthetic */ void lambda$zip$0(String str, String str2, Queue queue, ICompressionCallBack iCompressionCallBack) {
        String str3 = str + File.separator + str2;
        if (ZipUtil.zipFiles(queue, str3)) {
            if (iCompressionCallBack != null) {
                iCompressionCallBack.onSuccess(str3);
            }
        } else if (iCompressionCallBack != null) {
            iCompressionCallBack.onFailed(ErrorStats.ERROR_CODE_COMPRESSION_LOG_FAIL, ErrorStats.ERROR_MSG_COMPRESSION_LOG_FAIL);
        }
    }

    public File[] getAllZipFiles(String str) {
        if (valid(str)) {
            return new File(str).listFiles();
        }
        return null;
    }

    public String findZipFilePath(String str, String str2) {
        if (valid(str) && !TextUtils.isEmpty(str2)) {
            for (File file : new File(str).listFiles()) {
                String name = file.getName();
                if (!TextUtils.isEmpty(name) && name.contains(str2)) {
                    return file.getPath();
                }
            }
        }
        return null;
    }

    public boolean delete(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    private boolean valid(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.listFiles() != null && file.listFiles().length > 0;
    }
}
