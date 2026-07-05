package com.igexin.b.a.c.a;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.igexin.push.core.p;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class d extends Handler {
    public static final int b = 1;
    public static final int c = 2;
    public static final String d = "log_data";
    private static final String e = "d";
    public final Messenger a;
    private final StringBuffer f;
    private Messenger g;

    static class a {
        private static final d a = new d(0);

        private a() {
        }
    }

    private d() {
        super(Looper.getMainLooper());
        this.a = new Messenger(this);
        this.f = new StringBuffer();
    }

    /* synthetic */ d(byte b2) {
        this();
    }

    public static d a() {
        return a.a;
    }

    private void a(Message message) {
        this.g = message.replyTo;
        if (this.f.length() > 0) {
            b(this.f.toString());
            this.f.setLength(0);
        }
    }

    private void b(String str) {
        try {
            Message messageObtain = Message.obtain();
            messageObtain.what = 2;
            Bundle bundle = new Bundle();
            bundle.putString(d, str);
            messageObtain.setData(bundle);
            this.g.send(messageObtain);
        } catch (Exception e2) {
            Log.e(e, "send message error = ".concat(String.valueOf(e2)));
        }
    }

    private boolean b() {
        return this.f.length() > 0;
    }

    private IBinder c() {
        return this.a.getBinder();
    }

    public final void a(String str) {
        if (com.igexin.push.f.c.a(p.b)) {
            if (this.g != null) {
                b(str);
                return;
            }
            if (this.f.length() + str.length() < 5120) {
                StringBuffer stringBuffer = this.f;
                stringBuffer.append(str);
                stringBuffer.append("\n");
            } else {
                if (this.f.length() > 5120 || this.f.length() + TTDownloadField.CALL_DOWNLOAD_MODEL_SET_LOG_EXTRA <= 5120) {
                    return;
                }
                StringBuffer stringBuffer2 = this.f;
                stringBuffer2.append("Warning! the log cache is too long to show the full content,we suggest you call initialize and setDebugLogger in a short time interval.");
                stringBuffer2.append("\n");
            }
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what != 1) {
            super.handleMessage(message);
            return;
        }
        this.g = message.replyTo;
        if (this.f.length() > 0) {
            b(this.f.toString());
            this.f.setLength(0);
        }
    }
}
