package com.sqwan.common.util;

import android.content.Context;
import com.sq.sdk.tool.util.EncodeUtil;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ZipUtil {
    public static String enZip(String str, String str2) {
        try {
            return EncodeUtil.encode(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String unZip(String str, String str2) {
        try {
            return EncodeUtil.decode(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String sqEnZip(Context context, String str) {
        SQAppConfig sQAppConfig = ConfigManager.getInstance(context).getSQAppConfig();
        return enZip(sQAppConfig.getPartner() + sQAppConfig.getGameid() + ConfigManager.getInstance(context).getAppKey(), str);
    }

    public static String sqUnZip(Context context, String str) {
        SQAppConfig sQAppConfig = ConfigManager.getInstance(context).getSQAppConfig();
        return unZip(sQAppConfig.getPartner() + sQAppConfig.getGameid() + ConfigManager.getInstance(context).getAppKey(), str);
    }
}
