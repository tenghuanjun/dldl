package com.huya.mtp.utils.env;

import android.os.Environment;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.utils.IOUtils;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
final class EnvExtConfig {
    private static final String TAG = EnvExtConfig.class.getSimpleName();
    private String mCfgFilePath;
    private JSONObject mData = null;

    public EnvExtConfig(String str) {
        this.mCfgFilePath = null;
        this.mCfgFilePath = str;
        if (str == null || "".equals(str)) {
            this.mCfgFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        init();
    }

    public JSONObject data() {
        return this.mData;
    }

    private String getArkConfigPath() {
        return new File(this.mCfgFilePath, "ark.config").getAbsolutePath();
    }

    public boolean exists() {
        return new File(getArkConfigPath()).exists();
    }

    private void init() {
        File file = new File(getArkConfigPath());
        if (file.exists() && file.canRead()) {
            try {
                this.mData = new JSONObject(IOUtils.readString(file));
            } catch (JSONException unused) {
                MTPApi.LOGGER.error(TAG, "can not read from ark.config");
            }
        }
    }
}
