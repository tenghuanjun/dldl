package com.huya.live.utils.system;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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

    public static void copy(String str, Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
        if (clipboardManager != null) {
            clipboardManager.setText(str.trim());
        }
    }
}
