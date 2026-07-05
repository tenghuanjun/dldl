package com.igexin.sdk;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.igexin.b.a.c.a.c;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class GTIntentService extends Service {
    private static final int REMOTE_CLINET_RECEIVED = 2;
    private static final int REMOTE_MSG_RECEIVED = 1;
    public final String TAG = getClass().getName();
    private final Messenger client = new Messenger(new a());

    @SuppressLint({"HandlerLeak"})
    class a extends Handler {
        public a() {
            super(Looper.getMainLooper());
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            switch (message.what) {
                case 1:
                    if (!(message.obj instanceof Intent)) {
                        Log.e(GTIntentService.this.TAG, "receive bad msg");
                    } else {
                        GTIntentService gTIntentService = GTIntentService.this;
                        gTIntentService.processOnHandleIntent(gTIntentService, (Intent) message.obj);
                    }
                    break;
                case 2:
                    Log.d(GTIntentService.this.TAG, "handleMessage remoteMessenger = " + message.replyTo);
                    break;
            }
            super.handleMessage(message);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        c.a().a("onBind ".concat(String.valueOf(this)));
        return this.client.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        Log.d(this.TAG, "call -> onCreate -------");
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    public void onNotificationMessageArrived(Context context, GTNotificationMessage gTNotificationMessage) {
    }

    public void onNotificationMessageClicked(Context context, GTNotificationMessage gTNotificationMessage) {
    }

    public void onReceiveClientId(Context context, String str) {
    }

    public void onReceiveCommandResult(Context context, GTCmdMessage gTCmdMessage) {
    }

    public void onReceiveDeviceToken(Context context, String str) {
    }

    public void onReceiveMessageData(Context context, GTTransmitMessage gTTransmitMessage) {
    }

    public void onReceiveOnlineState(Context context, boolean z) {
    }

    public void onReceiveServicePid(Context context, int i) {
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.d(this.TAG, "call -> onStartCommand -------");
        if (intent == null) {
            return 2;
        }
        processOnHandleIntent(this, intent);
        return 2;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void processOnHandleIntent(Context context, Intent intent) {
        if (intent == null || context == null) {
            Log.d(this.TAG, "onHandleIntent() context or intent is null");
            return;
        }
        try {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.get("action") != null && (extras.get("action") instanceof Integer)) {
                int i = extras.getInt("action");
                Log.d(this.TAG, "onHandleIntent() action = ".concat(String.valueOf(i)));
                Context applicationContext = context.getApplicationContext();
                switch (i) {
                    case 10001:
                        onReceiveMessageData(applicationContext, (GTTransmitMessage) intent.getSerializableExtra(PushConsts.KEY_MESSAGE_DATA));
                        c.a().a("onHandleIntent() = received msg data ");
                        break;
                    case 10002:
                        onReceiveClientId(applicationContext, extras.getString(PushConsts.KEY_CLIENT_ID));
                        c.a().a("onHandleIntent() = received client id ");
                        break;
                    case 10007:
                        onReceiveOnlineState(applicationContext, extras.getBoolean(PushConsts.KEY_ONLINE_STATE));
                        break;
                    case 10008:
                        onReceiveServicePid(applicationContext, extras.getInt(PushConsts.KEY_SERVICE_PIT));
                        c.a().a("onHandleIntent() = get sdk service pid ");
                        break;
                    case 10010:
                        onReceiveCommandResult(applicationContext, (GTCmdMessage) intent.getSerializableExtra(PushConsts.KEY_CMD_MSG));
                        c.a().a("onHandleIntent() = " + intent.getSerializableExtra(PushConsts.KEY_CMD_MSG).getClass().getSimpleName());
                        break;
                    case 10011:
                        onNotificationMessageArrived(applicationContext, (GTNotificationMessage) intent.getSerializableExtra(PushConsts.KEY_NOTIFICATION_ARRIVED));
                        c.a().a("onHandleIntent() = notification arrived ");
                        break;
                    case 10012:
                        onNotificationMessageClicked(applicationContext, (GTNotificationMessage) intent.getSerializableExtra(PushConsts.KEY_NOTIFICATION_CLICKED));
                        c.a().a("onHandleIntent() notification clicked ");
                        break;
                    case PushConsts.GET_DEVICETOKEN /* 10013 */:
                        onReceiveDeviceToken(applicationContext, extras.getString(PushConsts.KEY_DEVICE_TOKEN));
                        c.a().a("onHandleIntent() = received device token ");
                        break;
                }
            }
        } catch (Exception unused) {
        }
    }
}
