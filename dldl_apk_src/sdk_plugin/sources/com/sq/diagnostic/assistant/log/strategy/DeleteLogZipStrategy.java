package com.sq.diagnostic.assistant.log.strategy;

import android.text.TextUtils;
import com.sq.diagnostic.assistant.http.entity.UploadLogRequest;
import com.sq.diagnostic.assistant.log.ILogCallBack;
import com.sq.diagnostic.assistant.log.impl.ErrorStats;
import com.sq.diagnostic.assistant.log.utils.ExtraUtil;
import com.sq.diagnostic.assistant.zip.ZipLogManager;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DeleteLogZipStrategy extends HandlerStrategy {
    public DeleteLogZipStrategy(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    @Override // com.sq.diagnostic.assistant.log.strategy.HandlerStrategy
    public boolean process(UploadLogRequest uploadLogRequest, ILogCallBack<?> iLogCallBack) {
        String strFindZipFilePath = ZipLogManager.getInstance().findZipFilePath(this.zipLogDirPath, ExtraUtil.genZipFileName(this.logDateStart, this.logDateEnd));
        if (TextUtils.isEmpty(strFindZipFilePath) || !new File(strFindZipFilePath).exists() || ZipLogManager.getInstance().delete(strFindZipFilePath)) {
            return false;
        }
        if (iLogCallBack == null) {
            return true;
        }
        iLogCallBack.onFailed(10002, ErrorStats.ERROR_MSG_DELETE_LOG_ZIP_FAIL);
        return true;
    }
}
