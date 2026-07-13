package com.tencent.open;

import android.app.Dialog;
import android.os.Build;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.activity.ComponentDialog$;
import com.lzy.okgo.model.Priority;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.open.log.SLog;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a {
    public static void a(Window window) {
        if (window == null) {
            return;
        }
        window.setFlags(WXMediaMessage.DESCRIPTION_LENGTH_LIMIT, WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
        window.getDecorView().setSystemUiVisibility(1280);
        window.addFlags(Priority.BG_LOW);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            ComponentDialog$.ExternalSyntheticApiModelOutline0.m(attributes, 1);
            window.setAttributes(attributes);
        }
    }

    public static void a(final Dialog dialog, Handler handler) {
        if (dialog == null || dialog.getContext() == null || handler == null) {
            return;
        }
        Toast.makeText(dialog.getContext(), "网页加载异常，请自行下载并安装QQ后，再重新登录。", 0).show();
        handler.postDelayed(new Runnable() { // from class: com.tencent.open.a.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                    SLog.e("openSDK_LOG.DialogUtils", "dismiss dialog exception", e);
                }
            }
        }, 100L);
    }
}
