package com.bytedance.bdtracker;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import java.nio.charset.Charset;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q f308a = new q();

    public final JSONObject a(Context context) {
        Object systemService;
        if (context != null) {
            try {
                systemService = context.getSystemService("clipboard");
            } catch (Throwable unused) {
            }
        } else {
            systemService = null;
        }
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.content.ClipboardManager");
        }
        ClipboardManager clipboardManager = (ClipboardManager) systemService;
        ClipData primaryClip = clipboardManager.getPrimaryClip();
        if (primaryClip != null) {
            ClipData.Item itemAt = primaryClip.getItemAt(0);
            Intrinsics.checkExpressionValueIsNotNull(itemAt, "clipData.getItemAt(0)");
            String string = itemAt.getText().toString();
            if (StringsKt.startsWith$default(string, "datatracer:", false, 2, (Object) null)) {
                clipboardManager.setPrimaryClip(ClipData.newPlainText("", ""));
                if (string == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring = string.substring(11);
                Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
                Charset charset = Charsets.UTF_8;
                if (strSubstring == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                byte[] bytes = strSubstring.getBytes(charset);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                byte[] data = Base64.decode(bytes, 2);
                Intrinsics.checkExpressionValueIsNotNull(data, "data");
                return a(Uri.parse("?".concat(new String(data, Charsets.UTF_8))));
            }
        }
        return null;
    }

    public final JSONObject a(Uri uri) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (uri == null) {
                return jSONObject;
            }
            String scheme = uri.getScheme();
            if (Intrinsics.areEqual(scheme, "http") || Intrinsics.areEqual(scheme, "https")) {
                jSONObject.put("tr_token", uri.getLastPathSegment());
            }
            for (String str : uri.getQueryParameterNames()) {
                jSONObject.put(str, uri.getQueryParameter(str));
            }
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }
}
