package com.igexin.push.core;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.igexin.assist.sdk.AssistPushManager;
import com.igexin.push.core.d;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.main.FeedbackImpl;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class c extends Handler {
    private static String a = "com.igexin.push.core.c";
    private boolean b;

    public c(Looper looper) {
        super(looper);
        this.b = false;
    }

    private static void a() {
        if (e.r || e.L <= com.igexin.push.config.c.i) {
            return;
        }
        int iRandom = (int) ((Math.random() * 100.0d) + 150.0d);
        long j = e.L;
        com.igexin.b.a.c.a.a(a + "|userPresent, rdelay = " + e.L + ", reset = " + iRandom, new Object[0]);
        e.L = (long) iRandom;
        com.igexin.push.e.b.e.g().a(e.L);
    }

    private static void a(Intent intent) {
        String stringExtra = intent.getStringExtra("action");
        if (stringExtra.equals(PushConsts.ACTION_SERVICE_INITIALIZE)) {
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.a(intent);
            return;
        }
        if (stringExtra.equals(PushConsts.ACTION_SERVICE_ONRESUME)) {
            com.igexin.b.a.c.a.a(a + "|handle onresume ~~~", new Object[0]);
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.b("on fg");
            return;
        }
        if (stringExtra.equals(PushConsts.ACTION_SERVICE_INITIALIZE_SLAVE)) {
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.b(intent);
            AssistPushManager.getInstance().turnOnPush(e.i);
            return;
        }
        if (stringExtra.equals(PushConsts.ACTION_BROADCAST_PUSHMANAGER)) {
            String stringExtra2 = intent.getStringExtra(com.igexin.push.f.o.d);
            if (TextUtils.isEmpty(e.f) || e.f.equals(stringExtra2)) {
                Bundle bundleExtra = intent.getBundleExtra("bundle");
                com.igexin.push.core.a.b.d();
                com.igexin.push.core.a.b.a(bundleExtra);
                return;
            }
            com.igexin.b.a.c.a.a("safeCode not match!!" + e.f + b.aj + stringExtra2, new Object[0]);
            com.igexin.b.a.c.a.d.a().a("safeCode not match!!" + e.f + b.aj + stringExtra2);
            return;
        }
        if (stringExtra.equals(PushConsts.ACTION_BROADCAST_USER_PRESENT)) {
            if (e.r || e.L <= com.igexin.push.config.c.i) {
                return;
            }
            int iRandom = (int) ((Math.random() * 100.0d) + 150.0d);
            long j = e.L;
            com.igexin.b.a.c.a.a(a + "|userPresent, rdelay = " + e.L + ", reset = " + iRandom, new Object[0]);
            e.L = (long) iRandom;
            com.igexin.push.e.b.e.g().a(e.L);
            return;
        }
        if (stringExtra.equals("com.igexin.action.notification.click")) {
            Intent intent2 = (Intent) intent.getParcelableExtra("broadcast_intent");
            if (intent2 != null) {
                com.igexin.push.core.f.a.a();
                com.igexin.push.core.f.a.a(intent2);
                return;
            }
            return;
        }
        if (stringExtra.equals(b.I)) {
            HashMap map = (HashMap) intent.getSerializableExtra("push_action");
            com.igexin.b.a.c.a.a(a + "| handle other push action broadcast", new Object[0]);
            o.a().a.putAll(map);
            return;
        }
        if (stringExtra.equals("com.igexin.action.notification.delete")) {
            PushTaskBean pushTaskBean = new PushTaskBean();
            pushTaskBean.setAppid(intent.getStringExtra("appid"));
            pushTaskBean.setMessageId(intent.getStringExtra("messageid"));
            pushTaskBean.setTaskId(intent.getStringExtra("taskid"));
            pushTaskBean.setId(intent.getStringExtra("id"));
            pushTaskBean.setAppKey(intent.getStringExtra("appkey"));
            int i = Integer.parseInt(intent.getStringExtra("feedbackid")) + 30040;
            pushTaskBean.setCurrentActionid(i);
            FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, String.valueOf(i), "notifyStyle:" + intent.getStringExtra("notifyStyle"));
            com.igexin.push.core.a.b.d();
            String strA = com.igexin.push.core.a.b.a(intent.getStringExtra("taskid"), intent.getStringExtra("messageid"));
            com.igexin.b.a.c.a.a(a + "|notification delete = " + strA, new Object[0]);
            try {
                e.aj.remove(strA);
                com.igexin.b.a.c.a.a(a + "|del notification, pushMessageMap remove = " + strA, new Object[0]);
            } catch (Exception e) {
                com.igexin.b.a.c.a.a("EndAction|" + e.toString(), new Object[0]);
            }
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message == null) {
            return;
        }
        int i = message.what;
        if (message.what == b.S) {
            o.a().e();
            return;
        }
        if (message.obj == null) {
            return;
        }
        try {
            if (message.what != b.P) {
                if (message.what == b.Q) {
                    com.igexin.push.core.a.b.d();
                    Intent intent = (Intent) message.obj;
                    if (intent == null || intent.getAction() == null) {
                        return;
                    }
                    try {
                        String action = intent.getAction();
                        if (PushConsts.ACTION_BROADCAST_NETWORK_CHANGE.equals(action)) {
                            com.igexin.push.core.a.b.e();
                            return;
                        }
                        if (b.H.equals(action)) {
                            o.a().a(intent);
                            return;
                        }
                        if (b.J.equals(action)) {
                            if (com.igexin.push.config.d.b != 0) {
                                com.igexin.push.e.e.c().d();
                                return;
                            }
                            return;
                        } else {
                            if (!"android.intent.action.SCREEN_ON".equals(action)) {
                                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                                    e.v = 0;
                                    return;
                                }
                                return;
                            }
                            e.v = 1;
                            o.a();
                            if (o.b()) {
                                o.a().e();
                            }
                            if (Build.VERSION.SDK_INT >= 26) {
                                com.igexin.push.core.a.b.b("screen on");
                                return;
                            }
                            return;
                        }
                    } catch (Throwable th) {
                        com.igexin.b.a.c.a.a("CoreAction" + th.toString(), new Object[0]);
                        return;
                    }
                }
                if (message.what == b.U) {
                    Bundle bundle = (Bundle) message.obj;
                    String string = bundle.getString("taskid");
                    String string2 = bundle.getString("messageid");
                    o oVarA = o.a();
                    com.igexin.b.a.c.a.a("PushMessageExecutor do processActionExecute", new Object[0]);
                    if (string2 == null || string == null) {
                        return;
                    }
                    try {
                        if (oVarA.a(string, string2) == PushMessageInterface.ActionPrepareState.success) {
                            oVarA.a(string, string2, "1");
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        com.igexin.b.a.c.a.a("PushMessageExecutor|" + th2.toString(), new Object[0]);
                        return;
                    }
                }
                if (message.what != b.R) {
                    if (message.what != b.V || this.b) {
                        return;
                    }
                    d.a.a.a();
                    this.b = true;
                    return;
                }
                Bundle bundle2 = (Bundle) message.obj;
                String string3 = bundle2.getString("taskid");
                String string4 = bundle2.getString("messageid");
                String string5 = bundle2.getString("actionid");
                com.igexin.b.a.c.a.a(a + "|hand execute_action taskid = " + string3 + ", actionid = " + string5, new Object[0]);
                o.a().b(string3, string4, string5);
                return;
            }
            Intent intent2 = (Intent) message.obj;
            if (intent2.hasExtra("action")) {
                String stringExtra = intent2.getStringExtra("action");
                if (stringExtra.equals(PushConsts.ACTION_SERVICE_INITIALIZE)) {
                    com.igexin.push.core.a.b.d();
                    com.igexin.push.core.a.b.a(intent2);
                    return;
                }
                if (stringExtra.equals(PushConsts.ACTION_SERVICE_ONRESUME)) {
                    com.igexin.b.a.c.a.a(a + "|handle onresume ~~~", new Object[0]);
                    com.igexin.push.core.a.b.d();
                    com.igexin.push.core.a.b.b("on fg");
                    return;
                }
                if (stringExtra.equals(PushConsts.ACTION_SERVICE_INITIALIZE_SLAVE)) {
                    com.igexin.push.core.a.b.d();
                    com.igexin.push.core.a.b.b(intent2);
                    AssistPushManager.getInstance().turnOnPush(e.i);
                    return;
                }
                if (stringExtra.equals(PushConsts.ACTION_BROADCAST_PUSHMANAGER)) {
                    String stringExtra2 = intent2.getStringExtra(com.igexin.push.f.o.d);
                    if (TextUtils.isEmpty(e.f) || e.f.equals(stringExtra2)) {
                        Bundle bundleExtra = intent2.getBundleExtra("bundle");
                        com.igexin.push.core.a.b.d();
                        com.igexin.push.core.a.b.a(bundleExtra);
                        return;
                    }
                    com.igexin.b.a.c.a.a("safeCode not match!!" + e.f + b.aj + stringExtra2, new Object[0]);
                    com.igexin.b.a.c.a.d.a().a("safeCode not match!!" + e.f + b.aj + stringExtra2);
                    return;
                }
                if (stringExtra.equals(PushConsts.ACTION_BROADCAST_USER_PRESENT)) {
                    if (e.r || e.L <= com.igexin.push.config.c.i) {
                        return;
                    }
                    int iRandom = (int) ((Math.random() * 100.0d) + 150.0d);
                    long j = e.L;
                    com.igexin.b.a.c.a.a(a + "|userPresent, rdelay = " + e.L + ", reset = " + iRandom, new Object[0]);
                    e.L = (long) iRandom;
                    com.igexin.push.e.b.e.g().a(e.L);
                    return;
                }
                if (stringExtra.equals("com.igexin.action.notification.click")) {
                    Intent intent3 = (Intent) intent2.getParcelableExtra("broadcast_intent");
                    if (intent3 != null) {
                        com.igexin.push.core.f.a.a();
                        com.igexin.push.core.f.a.a(intent3);
                        return;
                    }
                    return;
                }
                if (stringExtra.equals(b.I)) {
                    HashMap map = (HashMap) intent2.getSerializableExtra("push_action");
                    com.igexin.b.a.c.a.a(a + "| handle other push action broadcast", new Object[0]);
                    o.a().a.putAll(map);
                    return;
                }
                if (stringExtra.equals("com.igexin.action.notification.delete")) {
                    PushTaskBean pushTaskBean = new PushTaskBean();
                    pushTaskBean.setAppid(intent2.getStringExtra("appid"));
                    pushTaskBean.setMessageId(intent2.getStringExtra("messageid"));
                    pushTaskBean.setTaskId(intent2.getStringExtra("taskid"));
                    pushTaskBean.setId(intent2.getStringExtra("id"));
                    pushTaskBean.setAppKey(intent2.getStringExtra("appkey"));
                    int i2 = Integer.parseInt(intent2.getStringExtra("feedbackid")) + 30040;
                    pushTaskBean.setCurrentActionid(i2);
                    FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, String.valueOf(i2), "notifyStyle:" + intent2.getStringExtra("notifyStyle"));
                    com.igexin.push.core.a.b.d();
                    String strA = com.igexin.push.core.a.b.a(intent2.getStringExtra("taskid"), intent2.getStringExtra("messageid"));
                    com.igexin.b.a.c.a.a(a + "|notification delete = " + strA, new Object[0]);
                    try {
                        e.aj.remove(strA);
                        com.igexin.b.a.c.a.a(a + "|del notification, pushMessageMap remove = " + strA, new Object[0]);
                        return;
                    } catch (Exception e) {
                        com.igexin.b.a.c.a.a("EndAction|" + e.toString(), new Object[0]);
                        return;
                    }
                }
                return;
            }
            return;
        } catch (Throwable th3) {
            com.igexin.b.a.c.a.a(a + "|" + th3.toString(), new Object[0]);
        }
        com.igexin.b.a.c.a.a(a + "|" + th3.toString(), new Object[0]);
    }
}
