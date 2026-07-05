package com.duowan.live.common.packer.helper;

import android.content.Context;
import com.duowan.live.common.packer.common.PackerCommon;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class PackerNg {
    private static final String EMPTY_STRING = "";
    private static final String TAG = "PackerNg";
    private static String sCachedChannel;

    public static String getChannel(File file) {
        try {
            return PackerCommon.readChannel(file);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getChannel(Context context) {
        try {
            return getChannelOrThrow(context);
        } catch (Exception unused) {
            return "";
        }
    }

    public static synchronized String getChannelOrThrow(Context context) throws IOException {
        return PackerCommon.readChannel(new File(context.getApplicationInfo().sourceDir));
    }
}
