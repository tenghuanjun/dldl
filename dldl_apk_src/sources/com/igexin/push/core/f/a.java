package com.igexin.push.core.f;

import android.content.Intent;
import com.igexin.push.core.e;
import com.igexin.push.core.o;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.main.FeedbackImpl;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a {
    private static final String a = "NotificationExecutor";
    private static a b;

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public static void a(Intent intent) {
        StringBuilder sb;
        String stringExtra;
        String stringExtra2 = intent.getStringExtra("checkpackage");
        String stringExtra3 = intent.getStringExtra("accesstoken");
        if (stringExtra2 == null || stringExtra3 == null || !stringExtra2.equals(e.i.getPackageName()) || !stringExtra3.equals(e.az)) {
            return;
        }
        intent.putExtra("accesstoken", e.an);
        o.a().a(intent);
        PushTaskBean pushTaskBean = new PushTaskBean();
        pushTaskBean.setAppid(intent.getStringExtra("appid"));
        pushTaskBean.setMessageId(intent.getStringExtra("messageid"));
        pushTaskBean.setTaskId(intent.getStringExtra("taskid"));
        pushTaskBean.setId(intent.getStringExtra("id"));
        intent.getStringExtra("bigStyle");
        intent.getStringExtra("notifyStyle");
        try {
            int i = Integer.parseInt(intent.getStringExtra("feedbackid")) + 30010;
            pushTaskBean.setCurrentActionid(i);
            if (intent.getBooleanExtra("isFloat", false)) {
                sb = new StringBuilder("notifyFloat:");
                stringExtra = intent.getStringExtra("bigStyle");
            } else {
                sb = new StringBuilder("notifyStyle:");
                stringExtra = intent.getStringExtra("notifyStyle");
            }
            sb.append(stringExtra);
            FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, String.valueOf(i), sb.toString());
        } catch (Exception unused) {
        }
    }
}
