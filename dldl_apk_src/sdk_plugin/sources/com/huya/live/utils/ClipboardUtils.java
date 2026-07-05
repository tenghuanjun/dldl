package com.huya.live.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import com.duowan.auk.ArkValue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ClipboardUtils {
    private static final String CLIPBOARD_LABEL = "ClipboardUtils";

    public static boolean clipText(String str) {
        ClipboardManager clipboardManager = (ClipboardManager) ArkValue.gContext.getSystemService("clipboard");
        if (clipboardManager == null) {
            return false;
        }
        clipboardManager.setPrimaryClip(ClipData.newPlainText(CLIPBOARD_LABEL, str));
        return true;
    }
}
