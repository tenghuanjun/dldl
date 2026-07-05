package com.igexin.assist.action;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.igexin.assist.MessageBean;
import com.igexin.assist.sdk.AssistPushConsts;
import com.igexin.assist.util.AssistUtils;
import com.igexin.base.api.GTSchedulerManager;
import com.igexin.base.scheduler.task.SimpleTask;
import com.igexin.push.core.d;
import com.igexin.push.core.e;
import com.igexin.push.core.e.c;
import com.igexin.push.core.m;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.main.FeedbackImpl;
import com.igexin.sdk.message.GTTransmitMessage;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class MessageManger {
    private static final String a = "Assist_MessageManger";
    private String b;

    /* JADX INFO: renamed from: com.igexin.assist.action.MessageManger$1, reason: invalid class name */
    final class AnonymousClass1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;
        final /* synthetic */ Context c;

        AnonymousClass1(String str, boolean z, Context context) {
            this.a = str;
            this.b = z;
            this.c = context;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (e.j.get()) {
                Log.d(MessageManger.a, "delay 1s save token = " + this.a);
                MessageManger.b(this.a, this.b);
                return;
            }
            Context context = this.c;
            if (context == null) {
                Log.d(MessageManger.a, " save token in SP ,but context is null " + this.a);
            } else {
                c cVar = new c(context);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(AssistPushConsts.MSG_TYPE_TOKEN, this.a);
                    jSONObject.put("isForce", this.b);
                } catch (JSONException unused) {
                }
                cVar.a(jSONObject);
            }
        }
    }

    class a extends SimpleTask {
        MessageBean a;

        a(MessageBean messageBean) {
            this.a = messageBean;
        }

        @Override // com.igexin.base.scheduler.BaseTask
        protected final void onRunTask() {
            try {
                if (this.a != null) {
                    String messageType = this.a.getMessageType();
                    byte b = -1;
                    int iHashCode = messageType.hashCode();
                    if (iHashCode != -1161803523) {
                        if (iHashCode != -786701938) {
                            if (iHashCode == 110541305 && messageType.equals(AssistPushConsts.MSG_TYPE_TOKEN)) {
                                b = 0;
                            }
                        } else if (messageType.equals(AssistPushConsts.MSG_TYPE_PAYLOAD)) {
                            b = 1;
                        }
                    } else if (messageType.equals(AssistPushConsts.MSG_TYPE_ACTIONS)) {
                        b = 2;
                    }
                    switch (b) {
                        case 0:
                            MessageManger.a(MessageManger.this, this.a.getContext(), this.a.getStringMessage(), this.a.extra.getBoolean("isForce"));
                            break;
                        case 1:
                            if (!TextUtils.isEmpty(this.a.getStringMessage())) {
                                com.igexin.assist.action.a aVar = new com.igexin.assist.action.a();
                                aVar.a(this.a);
                                if (aVar.a() && aVar.e.equals(AssistPushConsts.MSG_VALUE_PAYLOAD)) {
                                    MessageManger.a(MessageManger.this, aVar, this.a.getContext());
                                    break;
                                }
                            }
                            break;
                        case 2:
                            if (!TextUtils.isEmpty(this.a.getStringMessage())) {
                                com.igexin.assist.action.a aVar2 = new com.igexin.assist.action.a();
                                aVar2.a(this.a);
                                if (aVar2.a() && aVar2.e.equals(AssistPushConsts.MSG_VALUE_PAYLOAD)) {
                                    MessageManger.a(this.a.getContext(), aVar2);
                                    break;
                                }
                            }
                            break;
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    static class b {
        private static final MessageManger a = new MessageManger(0);

        private b() {
        }
    }

    private MessageManger() {
    }

    /* synthetic */ MessageManger(byte b2) {
        this();
    }

    private static PushTaskBean a(com.igexin.assist.action.a aVar) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        PushTaskBean pushTaskBean = new PushTaskBean();
        pushTaskBean.setAppid(aVar.d);
        pushTaskBean.setMessageId(aVar.c);
        pushTaskBean.setTaskId(aVar.b);
        pushTaskBean.setId(String.valueOf(jCurrentTimeMillis));
        pushTaskBean.setCurrentActionid(1);
        return pushTaskBean;
    }

    static /* synthetic */ void a(Context context, com.igexin.assist.action.a aVar) {
        if (!e.j.get()) {
            AssistUtils.startGetuiService(context);
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = com.igexin.push.core.b.T;
        messageObtain.obj = aVar.f;
        Bundle bundle = new Bundle();
        bundle.putString("content", aVar.f);
        if (aVar.a != null) {
            bundle.putByteArray(AssistPushConsts.MSG_TYPE_PAYLOAD, aVar.a);
        }
        messageObtain.setData(bundle);
        d.a.a.a(messageObtain);
    }

    private void a(Context context, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Log.e(a, "other token = ".concat(String.valueOf(str)));
        if (e.j.get()) {
            b(str, z);
        } else {
            new Handler(Looper.getMainLooper()).postDelayed(new AnonymousClass1(str, z, context), 1000L);
        }
    }

    static /* synthetic */ void a(MessageManger messageManger, Context context, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Log.e(a, "other token = ".concat(String.valueOf(str)));
        if (e.j.get()) {
            b(str, z);
        } else {
            new Handler(Looper.getMainLooper()).postDelayed(messageManger.new AnonymousClass1(str, z, context), 1000L);
        }
    }

    static /* synthetic */ void a(MessageManger messageManger, com.igexin.assist.action.a aVar, Context context) {
        if (context == null) {
            return;
        }
        try {
            c cVar = new c(context);
            if (cVar.a(aVar.b)) {
                messageManger.feedbackPushMessage(context, aVar, messageManger.getBrandCode() + "1");
                return;
            }
            cVar.b(aVar.b);
            Bundle bundle = new Bundle();
            bundle.putInt("action", 10001);
            bundle.putSerializable(PushConsts.KEY_MESSAGE_DATA, new GTTransmitMessage(aVar.b, aVar.c, aVar.c + ":" + aVar.b, aVar.a));
            m.a(context);
            m.a().a(bundle);
            messageManger.feedbackPushMessage(context, aVar, messageManger.getBrandCode() + "0");
        } catch (Throwable unused) {
        }
    }

    private void a(com.igexin.assist.action.a aVar, Context context) {
        if (aVar == null || context == null) {
            return;
        }
        try {
            c cVar = new c(context);
            if (cVar.a(aVar.b)) {
                feedbackPushMessage(context, aVar, getBrandCode() + "1");
                return;
            }
            cVar.b(aVar.b);
            Bundle bundle = new Bundle();
            bundle.putInt("action", 10001);
            bundle.putSerializable(PushConsts.KEY_MESSAGE_DATA, new GTTransmitMessage(aVar.b, aVar.c, aVar.c + ":" + aVar.b, aVar.a));
            m.a(context);
            m.a().a(bundle);
            feedbackPushMessage(context, aVar, getBrandCode() + "0");
        } catch (Throwable unused) {
        }
    }

    private static void a(String str) {
        try {
            m.a().a(str);
        } catch (Exception unused) {
        }
    }

    private static void b(Context context, com.igexin.assist.action.a aVar) {
        if (!e.j.get()) {
            AssistUtils.startGetuiService(context);
        }
        if (aVar == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = com.igexin.push.core.b.T;
        messageObtain.obj = aVar.f;
        Bundle bundle = new Bundle();
        bundle.putString("content", aVar.f);
        if (aVar.a != null) {
            bundle.putByteArray(AssistPushConsts.MSG_TYPE_PAYLOAD, aVar.a);
        }
        messageObtain.setData(bundle);
        d.a.a.a(messageObtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, boolean z) {
        a(str);
        if (!z) {
            try {
                if (str.equals(e.G)) {
                    return;
                }
            } catch (Exception unused) {
                return;
            }
        }
        com.igexin.push.core.e.e.a().b(str);
        Log.d(a, "save token, isOnline = " + e.r);
        if (e.r) {
            Log.d(a, "online, send addphoneinfo");
            com.igexin.push.core.a.b.d().i();
        } else if (z) {
            com.igexin.push.core.e.e.a().c("");
        }
    }

    public static MessageManger getInstance() {
        return b.a;
    }

    public void addMessage(MessageBean messageBean) {
        GTSchedulerManager.getInstance().submit(new a(messageBean));
    }

    public void feedbackPushMessage(Context context, com.igexin.assist.action.a aVar, String str) {
        try {
            if (e.j.get()) {
                FeedbackImpl feedbackImpl = FeedbackImpl.getInstance();
                long jCurrentTimeMillis = System.currentTimeMillis();
                PushTaskBean pushTaskBean = new PushTaskBean();
                pushTaskBean.setAppid(aVar.d);
                pushTaskBean.setMessageId(aVar.c);
                pushTaskBean.setTaskId(aVar.b);
                pushTaskBean.setId(String.valueOf(jCurrentTimeMillis));
                pushTaskBean.setCurrentActionid(1);
                feedbackImpl.feedbackMultiBrandMessageAction(pushTaskBean, str);
                return;
            }
            c cVar = new c(context);
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", String.valueOf(jCurrentTimeMillis2));
            jSONObject.put("messageid", aVar.c);
            jSONObject.put("taskid", aVar.b);
            jSONObject.put("multaid", str);
            jSONObject.put("timestamp", String.valueOf(System.currentTimeMillis()));
            cVar.a(aVar.b, jSONObject);
        } catch (Throwable unused) {
        }
    }

    public String getBrandCode() {
        if (!TextUtils.isEmpty(this.b)) {
            return this.b;
        }
        com.igexin.assist.sdk.a aVarA = com.igexin.assist.sdk.a.a();
        this.b = aVarA.b == null ? "" : aVarA.b.getBrandCode();
        return this.b;
    }
}
