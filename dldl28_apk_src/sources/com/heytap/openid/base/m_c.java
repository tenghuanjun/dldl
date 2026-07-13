package com.heytap.openid.base;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import com.heytap.openid.sdk.m_h;
import com.tencent.open.SocialOperation;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class m_c {
    public Handler m_f;
    public HandlerThread m_g;
    public Context m_h;
    public volatile IInterface m_a = null;
    public String m_b = null;
    public String m_c = null;
    public final Object m_d = new Object();
    public ServiceConnection m_e = null;
    public boolean m_i = false;
    public boolean m_j = false;
    public boolean m_k = false;
    public volatile ContentProviderClient m_l = null;
    public IBinder.DeathRecipient m_m = new m_a();

    public class m_a implements IBinder.DeathRecipient {
        public m_a() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public native void binderDied();
    }

    public Intent m_a() {
        throw null;
    }

    public void m_a(Context context, String str, String str2) {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x00e1 A[Catch: all -> 0x0102, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0005, B:6:0x001e, B:7:0x0027, B:9:0x002d, B:11:0x0035, B:14:0x0040, B:12:0x003a, B:15:0x0044, B:19:0x004c, B:21:0x0059, B:22:0x005f, B:24:0x0067, B:25:0x0071, B:27:0x0075, B:38:0x009f, B:40:0x00a3, B:42:0x00a7, B:43:0x00a9, B:45:0x00b0, B:44:0x00ad, B:53:0x00c8, B:55:0x00cc, B:68:0x00e7, B:67:0x00e3, B:69:0x00ea, B:66:0x00e1, B:72:0x00ee, B:74:0x00f2, B:76:0x00f6, B:77:0x00f8, B:79:0x00ff, B:78:0x00fc, B:80:0x0101, B:62:0x00d9, B:64:0x00dd, B:30:0x007d, B:32:0x0081, B:33:0x0091, B:35:0x0095, B:37:0x009a, B:48:0x00b4, B:49:0x00b8, B:51:0x00be, B:61:0x00d4), top: B:85:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e7 A[Catch: all -> 0x0102, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0005, B:6:0x001e, B:7:0x0027, B:9:0x002d, B:11:0x0035, B:14:0x0040, B:12:0x003a, B:15:0x0044, B:19:0x004c, B:21:0x0059, B:22:0x005f, B:24:0x0067, B:25:0x0071, B:27:0x0075, B:38:0x009f, B:40:0x00a3, B:42:0x00a7, B:43:0x00a9, B:45:0x00b0, B:44:0x00ad, B:53:0x00c8, B:55:0x00cc, B:68:0x00e7, B:67:0x00e3, B:69:0x00ea, B:66:0x00e1, B:72:0x00ee, B:74:0x00f2, B:76:0x00f6, B:77:0x00f8, B:79:0x00ff, B:78:0x00fc, B:80:0x0101, B:62:0x00d9, B:64:0x00dd, B:30:0x007d, B:32:0x0081, B:33:0x0091, B:35:0x0095, B:37:0x009a, B:48:0x00b4, B:49:0x00b8, B:51:0x00be, B:61:0x00d4), top: B:85:0x0001, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void m_a(android.content.Context r4, java.util.List<java.lang.String> r5, boolean r6) {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.heytap.openid.base.m_c.m_a(android.content.Context, java.util.List, boolean):void");
    }

    public final void m_a(List<String> list) {
        String str;
        m_h.m_a("2048");
        if (this.m_a == null) {
            m_h.m_a("2009");
            try {
                if (this.m_h.bindService(m_a(), this.m_e, 1)) {
                    m_h.m_a("2013");
                    if (this.m_a == null) {
                        synchronized (this.m_d) {
                            try {
                                if (this.m_a == null) {
                                    this.m_d.wait(10000L);
                                }
                            } catch (InterruptedException e) {
                                e = e;
                                str = "1006";
                                m_h.m_a(str, e);
                            } catch (Exception e2) {
                                e = e2;
                                str = "1057";
                                m_h.m_a(str, e);
                            }
                        }
                    }
                } else {
                    Log.e("IDHelper", "1007");
                }
            } catch (Exception e3) {
                m_h.m_a("1008", e3);
            }
        }
        if (this.m_a == null) {
            Log.e("IDHelper", "1004");
            return;
        }
        Handler handler = this.m_f;
        if (handler != null) {
            handler.removeMessages(2);
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            m_e(it.next());
        }
        Handler handler2 = this.m_f;
        if (handler2 == null) {
            return;
        }
        Message messageObtainMessage = handler2.obtainMessage();
        messageObtainMessage.what = 2;
        this.m_f.sendMessageDelayed(messageObtainMessage, MonitorCommonConstants.SECOND_STOP_INTERVAL);
    }

    public boolean m_a(String str) {
        throw null;
    }

    public boolean m_b(String str) {
        throw null;
    }

    public String m_c(String str) {
        throw null;
    }

    public String m_d(String str) {
        m_h.m_a("2049");
        Bundle bundle = new Bundle();
        bundle.putString("packageName", this.m_b);
        bundle.putString(SocialOperation.GAME_SIGNATURE, this.m_c);
        try {
            if (this.m_l != null) {
                return this.m_l.call(str, null, bundle).getString(str);
            }
        } catch (RemoteException unused) {
            Log.e("IDHelper", "3149");
        } catch (Exception e) {
            m_h.m_a("3150", e);
        }
        return "";
    }

    public final void m_e(String str) {
        String str2;
        synchronized (this.m_d) {
            m_h.m_a(str + " 2023");
            Handler handler = this.m_f;
            if (handler != null) {
                Message messageObtainMessage = handler.obtainMessage();
                if (str.equals("RESET_OUID")) {
                    messageObtainMessage.what = 3;
                } else {
                    messageObtainMessage.what = 1;
                }
                Bundle bundle = new Bundle();
                bundle.putString("IdType", str);
                messageObtainMessage.setData(bundle);
                this.m_f.sendMessage(messageObtainMessage);
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            int i = str.equals("DUID") ? 5000 : 2000;
            try {
                this.m_d.wait(i);
            } catch (InterruptedException e) {
                e = e;
                str2 = "1022";
                m_h.m_a(str2, e);
            } catch (Exception e2) {
                e = e2;
                str2 = "1058";
                m_h.m_a(str2, e);
            }
            if (SystemClock.uptimeMillis() - jUptimeMillis > i) {
                Log.e("IDHelper", "1023");
            }
            m_h.m_a(str + " 2024");
        }
    }
}
