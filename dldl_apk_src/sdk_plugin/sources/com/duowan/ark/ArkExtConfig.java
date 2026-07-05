package com.duowan.ark;

import android.os.Environment;
import com.duowan.ark.util.IOUtils;
import com.duowan.ark.util.KLog;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ArkExtConfig {
    private static final String TAG = ArkExtConfig.class.getSimpleName();
    private JSONObject mData = null;

    public ArkExtConfig() {
        init();
    }

    public JSONObject data() {
        return this.mData;
    }

    private static String getArkConfigPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/kiwi/ark.config";
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
                KLog.error(TAG, "can not read from ark.config");
            }
        }
    }
}
