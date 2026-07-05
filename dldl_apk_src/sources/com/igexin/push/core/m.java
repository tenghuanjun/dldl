package com.igexin.push.core;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.igexin.assist.sdk.AssistPushConsts;
import com.igexin.push.core.p;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.message.BindAliasCmdMessage;
import com.igexin.sdk.message.FeedbackCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.igexin.sdk.message.SetTagCmdMessage;
import com.igexin.sdk.message.UnBindAliasCmdMessage;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class m extends Handler implements ServiceConnection {
    private static final String a = "MsgServerSender";
    private static volatile m c = null;
    private static final int f = 1;
    private static final int g = 2;
    private static Context i;
    private final ConcurrentLinkedQueue<Intent> b;
    private final a d;
    private boolean e;
    private volatile Messenger h;
    private final IBinder.DeathRecipient j;

    final class a {
        static final int a = 1;
        static final int b = 2;
        static final int c = 3;
        static final int d = 0;
        static final int e = 1;
        final Handler f;

        a() {
            HandlerThread handlerThread = new HandlerThread("GTIS-HANDLER");
            handlerThread.start();
            this.f = new Handler(handlerThread.getLooper()) { // from class: com.igexin.push.core.m.a.1
                @Override // android.os.Handler
                public final void handleMessage(Message message) {
                    if (message == null) {
                        return;
                    }
                    try {
                        boolean z = true;
                        switch (message.what) {
                            case 1:
                                if (m.this.a((IBinder) message.obj)) {
                                    removeMessages(2);
                                    removeMessages(1);
                                    m.b(m.this);
                                }
                                break;
                            case 2:
                            case 3:
                                m mVar = m.this;
                                if (message.arg1 != 0) {
                                    z = false;
                                }
                                m.a(mVar, z);
                                break;
                        }
                    } catch (Throwable unused) {
                    }
                }
            };
        }

        private Handler a() {
            return this.f;
        }
    }

    private m() {
        super(Looper.getMainLooper());
        this.j = new IBinder.DeathRecipient() { // from class: com.igexin.push.core.m.1
            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                com.igexin.b.a.c.a.a("MsgServerSender|remote iservice binderDied and reconnect !!!", new Object[0]);
                Message.obtain(m.this.d.f, 2, 0, 0).sendToTarget();
            }
        };
        this.d = new a();
        this.b = new ConcurrentLinkedQueue<>();
        Message.obtain(this.d.f, 3, 1, 0).sendToTarget();
    }

    public static m a() {
        if (c == null) {
            synchronized (m.class) {
                if (c == null) {
                    c = new m();
                }
            }
        }
        return c;
    }

    private void a(int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("action", 10008);
        bundle.putInt(PushConsts.KEY_SERVICE_PIT, i2);
        a(bundle);
    }

    public static void a(Context context) {
        i = context.getApplicationContext();
    }

    private void a(Intent intent) {
        if (intent == null) {
            return;
        }
        if (this.h == null) {
            com.igexin.b.a.c.a.a("MsgServerSender|realSend, remoteMessenger is null", new Object[0]);
        }
        Bundle extras = intent.getExtras();
        if (extras == null || extras.get("action") == null || !(extras.get("action") instanceof Integer)) {
            return;
        }
        com.igexin.b.a.c.a.a("MsgServerSender|realSend action = ".concat(String.valueOf(extras.getInt("action"))), new Object[0]);
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.obj = intent;
        try {
            this.h.send(messageObtain);
        } catch (Exception e) {
            com.igexin.b.a.c.a.a("MsgServerSender|realSend iservice error = " + e.toString(), new Object[0]);
            if (e instanceof DeadObjectException) {
                Message.obtain(this.d.f, 2, 0, 0).sendToTarget();
            }
        }
    }

    static /* synthetic */ void a(m mVar, boolean z) {
        if (z && mVar.h != null) {
            try {
                mVar.h.getBinder().unlinkToDeath(mVar.j, 0);
            } catch (Throwable unused) {
            }
            mVar.e = false;
            mVar.h = null;
        }
        if (mVar.e) {
            return;
        }
        com.igexin.b.a.c.a.a("MsgServerSender|try to bind iservice", new Object[0]);
        try {
            if (e.i == null) {
                e.i = i;
            }
            Intent intent = new Intent(e.i, (Class<?>) p.a.a.b(e.i));
            intent.setType(e.i.getPackageName());
            e.i.bindService(intent, mVar, 1);
        } catch (Exception e) {
            Log.e(a, "bind iservice error = " + e.toString());
            com.igexin.b.a.c.a.a("MsgServerSender|bind iservice error = " + e.toString(), new Object[0]);
        }
        mVar.e = true;
    }

    private void a(String str, String str2, String str3, String str4, long j) {
        if (e.a != null && e.a.equals(str)) {
            Bundle bundle = new Bundle();
            bundle.putInt("action", 10010);
            bundle.putSerializable(PushConsts.KEY_CMD_MSG, new FeedbackCmdMessage(str2, str3, str4, j, 10006));
            a(bundle);
        }
        Intent intentD = d();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("action", 10006);
        bundle2.putString("appid", str);
        bundle2.putString("taskid", str2);
        bundle2.putString("actionid", str3);
        bundle2.putString("result", str4);
        bundle2.putLong("timestamp", j);
        intentD.putExtras(bundle2);
        e.i.sendBroadcast(intentD);
    }

    private static void a(String str, String str2, String str3, byte[] bArr) {
        Intent intent = new Intent();
        intent.setAction(b.G.concat(String.valueOf(str3)));
        Bundle bundle = new Bundle();
        bundle.putInt("action", 10001);
        bundle.putString("taskid", str);
        bundle.putString("messageid", str2);
        bundle.putString("appid", str3);
        bundle.putString("payloadid", str2 + ":" + str);
        bundle.putString("packagename", e.d);
        bundle.putByteArray(AssistPushConsts.MSG_TYPE_PAYLOAD, bArr);
        intent.putExtras(bundle);
        intent.setPackage(e.i.getPackageName());
        e.i.sendBroadcast(intent);
    }

    private void a(boolean z) {
        if (z && this.h != null) {
            try {
                this.h.getBinder().unlinkToDeath(this.j, 0);
            } catch (Throwable unused) {
            }
            this.e = false;
            this.h = null;
        }
        if (this.e) {
            return;
        }
        com.igexin.b.a.c.a.a("MsgServerSender|try to bind iservice", new Object[0]);
        try {
            if (e.i == null) {
                e.i = i;
            }
            Intent intent = new Intent(e.i, (Class<?>) p.a.a.b(e.i));
            intent.setType(e.i.getPackageName());
            e.i.bindService(intent, this, 1);
        } catch (Exception e) {
            Log.e(a, "bind iservice error = " + e.toString());
            com.igexin.b.a.c.a.a("MsgServerSender|bind iservice error = " + e.toString(), new Object[0]);
        }
        this.e = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(IBinder iBinder) {
        if (iBinder == null) {
            return false;
        }
        this.h = new Messenger(iBinder);
        try {
            iBinder.linkToDeath(this.j, 0);
            return true;
        } catch (Exception e) {
            com.igexin.b.a.c.a.a("MsgServerSender|linkToDeath to iservice ex = " + e.toString(), new Object[0]);
            return true;
        }
    }

    private void b(Intent intent) {
        if (this.h != null) {
            a(intent);
        } else {
            try {
                Thread.getAllStackTraces().get(Thread.currentThread());
            } catch (Exception unused) {
            }
            this.b.add(intent);
        }
    }

    static /* synthetic */ void b(m mVar) {
        while (!mVar.b.isEmpty()) {
            Intent intentPoll = mVar.b.poll();
            if (intentPoll != null) {
                mVar.a(intentPoll);
            }
        }
    }

    @TargetApi(12)
    public static Intent d() {
        Intent intent = new Intent();
        intent.setAction(b.G + e.a);
        intent.setPackage(e.i.getPackageName());
        return intent;
    }

    private void e() {
        com.igexin.b.a.c.a.a("MsgServerSender|try to bind iservice", new Object[0]);
        try {
            if (e.i == null) {
                e.i = i;
            }
            Intent intent = new Intent(e.i, (Class<?>) p.a.a.b(e.i));
            intent.setType(e.i.getPackageName());
            e.i.bindService(intent, this, 1);
        } catch (Exception e) {
            Log.e(a, "bind iservice error = " + e.toString());
            com.igexin.b.a.c.a.a("MsgServerSender|bind iservice error = " + e.toString(), new Object[0]);
        }
    }

    private void f() {
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        try {
            this.h.send(messageObtain);
        } catch (Exception e) {
            com.igexin.b.a.c.a.a("MsgServerSender|send clent to iservice error = " + e.toString(), new Object[0]);
            if (e instanceof DeadObjectException) {
                Message.obtain(this.d.f, 2, 0, 0).sendToTarget();
            }
        }
    }

    private void g() {
        while (!this.b.isEmpty()) {
            Intent intentPoll = this.b.poll();
            if (intentPoll != null) {
                a(intentPoll);
            }
        }
    }

    private static Class h() {
        return p.a.a.b(e.i);
    }

    public final void a(Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        b(intent);
    }

    public final void a(String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("action", PushConsts.GET_DEVICETOKEN);
        bundle.putString(PushConsts.KEY_DEVICE_TOKEN, str);
        a(bundle);
    }

    public final void a(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("action", 10010);
        bundle.putSerializable(PushConsts.KEY_CMD_MSG, new SetTagCmdMessage(str, str2, 10009));
        a(bundle);
    }

    public final void a(String str, String str2, String str3, String str4) {
        byte[] msgExtra;
        com.igexin.b.a.c.a.a("startapp|broadcastPayload", new Object[0]);
        if (str4 != null) {
            msgExtra = str4.getBytes();
        } else {
            com.igexin.push.core.a.b.d();
            PushTaskBean pushTaskBean = e.aj.get(com.igexin.push.core.a.b.a(str, str2));
            msgExtra = pushTaskBean != null ? pushTaskBean.getMsgExtra() : null;
        }
        if (msgExtra == null) {
            com.igexin.b.a.c.a.a("startapp|broadcast|payload is empty!", new Object[0]);
            return;
        }
        new String(msgExtra);
        com.igexin.b.a.c.a.a("startapp|broadcast|payload = " + new String(msgExtra), new Object[0]);
        if (e.a != null && e.a.equals(str3)) {
            Bundle bundle = new Bundle();
            bundle.putInt("action", 10001);
            bundle.putSerializable(PushConsts.KEY_MESSAGE_DATA, new GTTransmitMessage(str, str2, str2 + ":" + str, msgExtra));
            a(bundle);
        }
        Intent intent = new Intent();
        intent.setAction(b.G.concat(String.valueOf(str3)));
        Bundle bundle2 = new Bundle();
        bundle2.putInt("action", 10001);
        bundle2.putString("taskid", str);
        bundle2.putString("messageid", str2);
        bundle2.putString("appid", str3);
        bundle2.putString("payloadid", str2 + ":" + str);
        bundle2.putString("packagename", e.d);
        bundle2.putByteArray(AssistPushConsts.MSG_TYPE_PAYLOAD, msgExtra);
        intent.putExtras(bundle2);
        intent.setPackage(e.i.getPackageName());
        e.i.sendBroadcast(intent);
    }

    public final void b() {
        Bundle bundle = new Bundle();
        bundle.putInt("action", 10007);
        bundle.putBoolean(PushConsts.KEY_ONLINE_STATE, e.r);
        a(bundle);
        Intent intentD = d();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("action", 10007);
        bundle2.putBoolean(PushConsts.KEY_ONLINE_STATE, e.r);
        intentD.putExtras(bundle2);
        e.i.sendBroadcast(intentD);
    }

    public final void b(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("action", 10010);
        bundle.putSerializable(PushConsts.KEY_CMD_MSG, new BindAliasCmdMessage(str, str2, 10010));
        a(bundle);
    }

    public final void b(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putInt("action", 10011);
        bundle.putSerializable(PushConsts.KEY_NOTIFICATION_ARRIVED, new GTNotificationMessage(str, str2, str3, str4));
        a(bundle);
    }

    public final void c() {
        Log.d("PushService", "clientid is " + e.x);
        com.igexin.b.a.c.a.a("broadcastClientId|" + e.x, new Object[0]);
        Bundle bundle = new Bundle();
        bundle.putInt("action", 10002);
        bundle.putString(PushConsts.KEY_CLIENT_ID, e.x);
        a(bundle);
        Intent intentD = d();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("action", 10002);
        bundle2.putString(PushConsts.KEY_CLIENT_ID, e.x);
        intentD.putExtras(bundle2);
        e.i.sendBroadcast(intentD);
    }

    public final void c(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("action", 10010);
        bundle.putSerializable(PushConsts.KEY_CMD_MSG, new UnBindAliasCmdMessage(str, str2, 10011));
        a(bundle);
    }

    public final void c(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putInt("action", 10012);
        bundle.putSerializable(PushConsts.KEY_NOTIFICATION_CLICKED, new GTNotificationMessage(str, str2, str3, str4));
        a(bundle);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i2 = message.what;
        super.handleMessage(message);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        com.igexin.b.a.c.a.a("MsgServerSender|remote iservice connected ", new Object[0]);
        Message.obtain(this.d.f, 1, iBinder).sendToTarget();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        com.igexin.b.a.c.a.a("MsgServerSender|remote iservice disConnected ~~~", new Object[0]);
        Message.obtain(this.d.f, 2, 0, 0).sendToTarget();
    }
}
