package com.igexin.push.core.a.c;

import android.R;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.main.FeedbackImpl;
import com.igexin.sdk.message.GTNotificationMessage;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class g implements PushMessageInterface {
    private static final String a = com.igexin.push.core.b.d + g.class.getName();
    private static final int b = 131;
    private static final String c = "push_small";

    enum a {
        UNSET(0),
        BIG_IMAGE(1),
        LONG_TEXT(2),
        PURE_IMAGE(3);

        int e;

        a(int i) {
            this.e = i;
        }

        private int a() {
            return this.e;
        }
    }

    private static int a(com.igexin.push.core.b.h hVar, boolean z) {
        if (z) {
            int identifier = com.igexin.push.core.e.i.getResources().getIdentifier(c, "drawable", com.igexin.push.core.e.d);
            if (identifier == 0) {
                identifier = com.igexin.push.core.e.i.getResources().getIdentifier(c, "mipmap", com.igexin.push.core.e.d);
            }
            if (identifier != 0) {
                com.igexin.b.a.c.a.a(a + "|push_small.png is set, use default push_small", new Object[0]);
                return identifier;
            }
            com.igexin.b.a.c.a.a(a + "|push_small.png is missing", new Object[0]);
        }
        int identifier2 = com.igexin.push.core.e.i.getResources().getIdentifier(com.igexin.push.config.c.x, "drawable", com.igexin.push.core.e.d);
        if (identifier2 == 0) {
            identifier2 = com.igexin.push.core.e.i.getResources().getIdentifier(com.igexin.push.config.c.x, "mipmap", com.igexin.push.core.e.d);
        }
        if (TextUtils.isEmpty(hVar.f)) {
            return identifier2 != 0 ? identifier2 : R.drawable.sym_def_app_icon;
        }
        if ("null".equals(hVar.f)) {
            return R.drawable.sym_def_app_icon;
        }
        if (hVar.f.startsWith("@")) {
            String str = hVar.f;
            return str.substring(1, str.length()).endsWith("email") ? R.drawable.sym_action_email : R.drawable.sym_def_app_icon;
        }
        int identifier3 = com.igexin.push.core.e.i.getResources().getIdentifier(hVar.f, "drawable", com.igexin.push.core.e.d);
        if (identifier3 == 0) {
            identifier3 = com.igexin.push.core.e.i.getResources().getIdentifier(hVar.f, "mipmap", com.igexin.push.core.e.d);
        }
        return identifier3 != 0 ? identifier3 : identifier2 != 0 ? identifier2 : R.drawable.sym_def_app_icon;
    }

    private static int a(String str) {
        int iCharAt = 0;
        for (int i = 0; i != str.length(); i++) {
            iCharAt = (iCharAt * 131) + str.charAt(i);
        }
        if (iCharAt == Integer.MIN_VALUE) {
            iCharAt = 1;
        }
        return Math.abs(iCharAt);
    }

    private static PendingIntent a(String str, String str2, int i, com.igexin.push.core.b.h hVar) {
        Intent intent = new Intent();
        intent.putExtra("taskid", str);
        intent.putExtra("messageid", str2);
        intent.putExtra("appid", com.igexin.push.core.e.a);
        intent.putExtra("actionid", hVar.getDoActionId());
        intent.putExtra("accesstoken", com.igexin.push.core.e.az);
        intent.putExtra("notifID", i);
        StringBuilder sb = new StringBuilder();
        sb.append(hVar.h);
        intent.putExtra("notifyStyle", sb.toString());
        intent.putExtra("id", hVar.r);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(hVar.v);
        intent.putExtra("bigStyle", sb2.toString());
        intent.putExtra("isFloat", false);
        intent.putExtra("checkpackage", com.igexin.push.core.e.i.getPackageName());
        intent.putExtra("feedbackid", hVar.getActionId().substring(hVar.getActionId().length() - 1));
        String str3 = hVar.a;
        if (str3 == null) {
            str3 = "";
        }
        intent.putExtra("title", str3);
        String str4 = hVar.b;
        if (str4 == null) {
            str4 = "";
        }
        intent.putExtra("content", str4);
        try {
            Context context = com.igexin.push.core.e.i;
            com.igexin.push.core.a.b.d();
            Intent intent2 = new Intent(context, (Class<?>) com.igexin.push.core.a.b.a(com.igexin.push.core.e.i));
            intent2.putExtra("action", "com.igexin.action.notification.click");
            intent2.putExtra("broadcast_intent", intent);
            return PendingIntent.getService(com.igexin.push.core.e.i, new Random().nextInt(1000), intent2, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
        } catch (Throwable unused) {
            return PendingIntent.getBroadcast(com.igexin.push.core.e.i, new Random().nextInt(1000), intent, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
        }
    }

    private static PendingIntent a(String str, String str2, String str3, com.igexin.push.core.b.h hVar) {
        try {
            Context context = com.igexin.push.core.e.i;
            com.igexin.push.core.a.b.d();
            Intent intent = new Intent(context, (Class<?>) com.igexin.push.core.a.b.a(com.igexin.push.core.e.i));
            intent.putExtra("taskid", str2);
            intent.putExtra("messageid", str3);
            intent.putExtra("appid", com.igexin.push.core.e.a);
            intent.putExtra("appkey", str);
            intent.putExtra("actionid", hVar.getDoActionId());
            StringBuilder sb = new StringBuilder();
            sb.append(hVar.h);
            intent.putExtra("notifyStyle", sb.toString());
            intent.putExtra("id", hVar.r);
            intent.putExtra("feedbackid", hVar.getActionId().substring(hVar.getActionId().length() + (-1)));
            intent.putExtra("action", "com.igexin.action.notification.delete");
            return PendingIntent.getService(com.igexin.push.core.e.i, new Random().nextInt(1000), intent, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
        } catch (Exception e) {
            com.igexin.b.a.c.a.a(a + "|getDelPendingIntent err：" + e.toString(), new Object[0]);
            return null;
        }
    }

    private static Bitmap a(com.igexin.push.core.b.h hVar) {
        Bitmap bitmapA;
        String str = hVar.w;
        if (TextUtils.isEmpty(str)) {
            bitmapA = null;
        } else {
            bitmapA = com.igexin.push.f.l.a(str);
            StringBuilder sb = new StringBuilder();
            sb.append(a);
            sb.append("|use net logo bitmap is null = ");
            sb.append(bitmapA == null);
            com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
        }
        if (bitmapA == null) {
            return BitmapFactory.decodeResource(com.igexin.push.core.e.i.getResources(), a(hVar, false));
        }
        return bitmapA;
    }

    private static void a(Notification notification) {
        if (com.igexin.push.f.a.b() || Build.VERSION.SDK_INT < 21 || Build.VERSION.SDK_INT >= 24) {
            return;
        }
        try {
            Field field = Class.forName("com.android.internal.R$id").getField("right_icon");
            field.setAccessible(true);
            int i = field.getInt(null);
            if (notification.contentView != null) {
                notification.contentView.setViewVisibility(i, 8);
                notification.bigContentView.setViewVisibility(i, 8);
            }
        } catch (Exception unused) {
        }
    }

    private static void a(Notification notification, com.igexin.push.core.b.h hVar) {
        notification.defaults = 4;
        notification.ledARGB = -16711936;
        notification.ledOnMS = 1000;
        notification.ledOffMS = 3000;
        notification.flags = 1;
        notification.flags = hVar.e ? notification.flags | 16 : notification.flags | 32;
        if (hVar.c) {
            notification.defaults |= 2;
        }
        if (hVar.d) {
            if (TextUtils.isEmpty(hVar.p)) {
                notification.defaults |= 1;
            } else {
                notification.sound = b(hVar.p);
            }
        }
        if (hVar.o > 0) {
            com.igexin.push.f.d.a(hVar.o, false);
        }
        notification.icon = a(hVar, true);
    }

    private static void a(String str, String str2, String str3, com.igexin.push.core.b.h hVar, int i) {
        Notification.Style styleBigText;
        Bitmap bitmapA;
        com.igexin.push.core.e.ak.put(str2, Integer.valueOf(i));
        int iA = a(hVar, true);
        if (iA != 0 && com.igexin.push.core.e.i.getResources().getDrawable(iA) == null) {
            com.igexin.b.a.c.a.a(a + "|showNotification smallIconId: " + iA + " couldn't find resource", new Object[0]);
            return;
        }
        PendingIntent pendingIntentA = a(str2, str3, i, hVar);
        PendingIntent pendingIntentA2 = a(str, str2, str3, hVar);
        NotificationManager notificationManager = (NotificationManager) com.igexin.push.core.e.i.getSystemService(com.igexin.push.core.b.l);
        Notification.Builder builderB = Build.VERSION.SDK_INT >= 26 ? b(hVar) : new Notification.Builder(com.igexin.push.core.e.i);
        String str4 = hVar.a;
        String str5 = hVar.b;
        Bitmap bitmapDecodeResource = null;
        String str6 = hVar.w;
        if (!TextUtils.isEmpty(str6)) {
            bitmapDecodeResource = com.igexin.push.f.l.a(str6);
            StringBuilder sb = new StringBuilder();
            sb.append(a);
            sb.append("|use net logo bitmap is null = ");
            sb.append(bitmapDecodeResource == null);
            com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
        }
        if (bitmapDecodeResource == null) {
            bitmapDecodeResource = BitmapFactory.decodeResource(com.igexin.push.core.e.i.getResources(), a(hVar, false));
        }
        builderB.setSmallIcon(iA).setTicker(hVar.b).setWhen(System.currentTimeMillis()).setContentTitle(str4).setLargeIcon(bitmapDecodeResource).setContentIntent(pendingIntentA).setContentText(str5).setDeleteIntent(pendingIntentA2);
        if (Build.VERSION.SDK_INT >= 24 && !TextUtils.isEmpty(hVar.i)) {
            try {
                builderB.setColor(Color.parseColor(hVar.i));
            } catch (Throwable unused) {
            }
        }
        if (Build.VERSION.SDK_INT >= 16) {
            if (hVar.v == a.BIG_IMAGE.e) {
                String str7 = hVar.x;
                if (!TextUtils.isEmpty(str7) && (bitmapA = com.igexin.push.f.l.a(str7)) != null) {
                    builderB.setPriority(hVar.q);
                    styleBigText = new Notification.BigPictureStyle().bigPicture(bitmapA);
                    builderB.setStyle(styleBigText);
                }
            } else if (hVar.v == a.LONG_TEXT.e) {
                String str8 = hVar.u;
                if (!TextUtils.isEmpty(str8)) {
                    builderB.setPriority(hVar.q);
                    styleBigText = new Notification.BigTextStyle().bigText(str8);
                    builderB.setStyle(styleBigText);
                }
            }
        }
        if (hVar.s && Build.VERSION.SDK_INT >= 21 && (hVar.c || hVar.d)) {
            builderB.setPriority(2);
        }
        builderB.setWhen(System.currentTimeMillis());
        Notification notification = builderB.getNotification();
        notification.defaults = 4;
        notification.ledARGB = -16711936;
        notification.ledOnMS = 1000;
        notification.ledOffMS = 3000;
        notification.flags = 1;
        notification.flags = hVar.e ? 16 | notification.flags : notification.flags | 32;
        if (hVar.c) {
            notification.defaults |= 2;
        }
        if (hVar.d) {
            if (TextUtils.isEmpty(hVar.p)) {
                notification.defaults |= 1;
            } else {
                notification.sound = b(hVar.p);
            }
        }
        if (hVar.o > 0) {
            com.igexin.push.f.d.a(hVar.o, false);
        }
        notification.icon = a(hVar, true);
        a(notification);
        notificationManager.notify(i, notification);
        com.igexin.push.core.m.a().b(str2, str3, str4, str5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final String str2, final String str3, final BaseActionBean baseActionBean, final int i) {
        String string;
        StringBuilder sb;
        String str4;
        String str5 = "width=" + com.igexin.push.core.e.h + "&height=" + com.igexin.push.core.e.g;
        if (str.contains(str5)) {
            string = str;
        } else {
            if (str.indexOf("?") > 0) {
                sb = new StringBuilder();
                sb.append(str);
                str4 = com.alipay.sdk.sys.a.b;
            } else {
                sb = new StringBuilder();
                sb.append(str);
                str4 = "?";
            }
            sb.append(str4);
            sb.append(str5);
            string = sb.toString();
        }
        com.igexin.push.core.h.b bVar = new com.igexin.push.core.h.b(string, str, str2, baseActionBean, i, new com.igexin.push.core.h.d() { // from class: com.igexin.push.core.a.c.g.1
            @Override // com.igexin.push.core.h.d
            public final void a() {
                if (((com.igexin.push.core.b.h) baseActionBean).A >= 3) {
                    ((com.igexin.push.core.b.h) baseActionBean).y = true;
                }
                if (((com.igexin.push.core.b.h) baseActionBean).B >= 3) {
                    ((com.igexin.push.core.b.h) baseActionBean).z = true;
                }
                if (!((com.igexin.push.core.b.h) baseActionBean).y || !((com.igexin.push.core.b.h) baseActionBean).z) {
                    g.this.a(str, str2, str3, baseActionBean, i);
                } else if (com.igexin.push.core.e.a(str2) == 0) {
                    com.igexin.push.core.a.b.d();
                    com.igexin.push.core.a.b.a(str2, str3, "1");
                }
            }

            @Override // com.igexin.push.core.h.d
            public final void a(BaseActionBean baseActionBean2) {
                int i2 = i;
                if (i2 == 2) {
                    ((com.igexin.push.core.b.h) baseActionBean).y = true;
                } else if (i2 == 8) {
                    ((com.igexin.push.core.b.h) baseActionBean).z = true;
                }
                com.igexin.push.core.b.h hVar = (com.igexin.push.core.b.h) baseActionBean2;
                if (hVar.y && hVar.z && com.igexin.push.core.e.a(str2) == 0) {
                    com.igexin.push.core.a.b.d();
                    com.igexin.push.core.a.b.a(str2, str3, "1");
                }
            }
        });
        if (i == 2) {
            ((com.igexin.push.core.b.h) baseActionBean).A++;
        } else if (i == 8) {
            ((com.igexin.push.core.b.h) baseActionBean).B++;
        }
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new com.igexin.push.e.a.c(bVar), false, true);
    }

    private static void a(String str, String str2, String str3, String str4) {
        com.igexin.push.core.m.a().b(str, str2, str3, str4);
    }

    @TargetApi(26)
    private static Notification.Builder b(com.igexin.push.core.b.h hVar) {
        Notification.Builder builder = new Notification.Builder(com.igexin.push.core.e.i);
        NotificationManager notificationManager = (NotificationManager) com.igexin.push.core.e.i.getSystemService(com.igexin.push.core.b.l);
        try {
            Class<?> cls = Class.forName("android.app.NotificationChannel");
            Constructor<?> constructor = cls.getConstructor(String.class, CharSequence.class, Integer.TYPE);
            Class<?> cls2 = notificationManager.getClass();
            if (((Parcelable) cls2.getMethod("getNotificationChannel", String.class).invoke(notificationManager, hVar.j)) == null) {
                Parcelable parcelable = (Parcelable) constructor.newInstance(hVar.j, hVar.k, Integer.valueOf(hVar.l));
                Method method = cls2.getMethod("createNotificationChannel", Class.forName("android.app.NotificationChannel"));
                Method method2 = cls.getMethod("enableVibration", Boolean.TYPE);
                Method method3 = cls.getMethod("setSound", Uri.class, AudioAttributes.class);
                method2.invoke(parcelable, Boolean.valueOf(hVar.c));
                if (!hVar.d) {
                    method3.invoke(parcelable, null, null);
                } else if (!TextUtils.isEmpty(hVar.p)) {
                    method3.invoke(parcelable, b(hVar.p), null);
                }
                method.invoke(notificationManager, parcelable);
            }
            builder.getClass().getMethod("setChannelId", String.class).invoke(builder, hVar.j);
        } catch (Throwable unused) {
        }
        return builder;
    }

    private static Uri b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return Uri.parse("android.resource://" + com.igexin.push.core.e.i.getPackageName() + "/raw/" + str.toLowerCase());
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public boolean executeAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        int iAbs;
        int i;
        Bitmap bitmapDecodeResource;
        Notification.Style styleBigText;
        Bitmap bitmapA;
        if (pushTaskBean == null || !(baseActionBean instanceof com.igexin.push.core.b.h)) {
            return true;
        }
        com.igexin.push.core.b.h hVar = (com.igexin.push.core.b.h) baseActionBean;
        boolean z = false;
        if (hVar.n) {
            iAbs = hVar.m;
        } else {
            String taskId = pushTaskBean.getTaskId();
            int iCharAt = 0;
            for (int i2 = 0; i2 != taskId.length(); i2++) {
                iCharAt = (iCharAt * 131) + taskId.charAt(i2);
            }
            if (iCharAt == Integer.MIN_VALUE) {
                iCharAt = 1;
            }
            iAbs = Math.abs(iCharAt);
        }
        try {
            i = Integer.parseInt(hVar.getActionId().substring(hVar.getActionId().length() - 1)) + 30000;
        } catch (Exception unused) {
            i = 0;
        }
        String appKey = pushTaskBean.getAppKey();
        String taskId2 = pushTaskBean.getTaskId();
        String messageId = pushTaskBean.getMessageId();
        com.igexin.push.core.e.ak.put(taskId2, Integer.valueOf(iAbs));
        int iA = a(hVar, true);
        if (iA == 0 || com.igexin.push.core.e.i.getResources().getDrawable(iA) != null) {
            PendingIntent pendingIntentA = a(taskId2, messageId, iAbs, hVar);
            PendingIntent pendingIntentA2 = a(appKey, taskId2, messageId, hVar);
            NotificationManager notificationManager = (NotificationManager) com.igexin.push.core.e.i.getSystemService(com.igexin.push.core.b.l);
            Notification.Builder builderB = Build.VERSION.SDK_INT >= 26 ? b(hVar) : new Notification.Builder(com.igexin.push.core.e.i);
            String str = hVar.a;
            String str2 = hVar.b;
            String str3 = hVar.w;
            if (TextUtils.isEmpty(str3)) {
                bitmapDecodeResource = null;
            } else {
                Bitmap bitmapA2 = com.igexin.push.f.l.a(str3);
                StringBuilder sb = new StringBuilder();
                sb.append(a);
                sb.append("|use net logo bitmap is null = ");
                sb.append(bitmapA2 == null);
                z = false;
                com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
                bitmapDecodeResource = bitmapA2;
            }
            if (bitmapDecodeResource == null) {
                bitmapDecodeResource = BitmapFactory.decodeResource(com.igexin.push.core.e.i.getResources(), a(hVar, z));
            }
            builderB.setSmallIcon(iA).setTicker(hVar.b).setWhen(System.currentTimeMillis()).setContentTitle(str).setLargeIcon(bitmapDecodeResource).setContentIntent(pendingIntentA).setContentText(str2).setDeleteIntent(pendingIntentA2);
            if (Build.VERSION.SDK_INT >= 24 && !TextUtils.isEmpty(hVar.i)) {
                try {
                    builderB.setColor(Color.parseColor(hVar.i));
                } catch (Throwable unused2) {
                }
            }
            if (Build.VERSION.SDK_INT >= 16) {
                if (hVar.v == a.BIG_IMAGE.e) {
                    String str4 = hVar.x;
                    if (!TextUtils.isEmpty(str4) && (bitmapA = com.igexin.push.f.l.a(str4)) != null) {
                        builderB.setPriority(hVar.q);
                        styleBigText = new Notification.BigPictureStyle().bigPicture(bitmapA);
                        builderB.setStyle(styleBigText);
                    }
                } else if (hVar.v == a.LONG_TEXT.e) {
                    String str5 = hVar.u;
                    if (!TextUtils.isEmpty(str5)) {
                        builderB.setPriority(hVar.q);
                        styleBigText = new Notification.BigTextStyle().bigText(str5);
                        builderB.setStyle(styleBigText);
                    }
                }
            }
            if (hVar.s && Build.VERSION.SDK_INT >= 21 && (hVar.c || hVar.d)) {
                builderB.setPriority(2);
            }
            builderB.setWhen(System.currentTimeMillis());
            Notification notification = builderB.getNotification();
            notification.defaults = 4;
            notification.ledARGB = -16711936;
            notification.ledOnMS = 1000;
            notification.ledOffMS = 3000;
            notification.flags = 1;
            notification.flags = hVar.e ? 16 | notification.flags : notification.flags | 32;
            if (hVar.c) {
                notification.defaults |= 2;
            }
            if (hVar.d) {
                if (TextUtils.isEmpty(hVar.p)) {
                    notification.defaults |= 1;
                } else {
                    notification.sound = b(hVar.p);
                }
            }
            if (hVar.o > 0) {
                com.igexin.push.f.d.a(hVar.o, false);
            }
            notification.icon = a(hVar, true);
            a(notification);
            notificationManager.notify(iAbs, notification);
            com.igexin.push.core.m mVarA = com.igexin.push.core.m.a();
            Bundle bundle = new Bundle();
            bundle.putInt("action", 10011);
            bundle.putSerializable(PushConsts.KEY_NOTIFICATION_ARRIVED, new GTNotificationMessage(taskId2, messageId, str, str2));
            mVarA.a(bundle);
        } else {
            com.igexin.b.a.c.a.a(a + "|showNotification smallIconId: " + iA + " couldn't find resource", new Object[0]);
        }
        if (i != 0) {
            FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, String.valueOf(i), "notifyStyle:" + hVar.h);
        }
        pushTaskBean.setPerActionid(Integer.parseInt(hVar.getActionId()));
        pushTaskBean.setCurrentActionid(Integer.parseInt(hVar.getDoActionId()));
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00a0  */
    @Override // com.igexin.push.extension.mod.PushMessageInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.igexin.push.extension.mod.BaseActionBean parseAction(org.json.JSONObject r10) {
        /*
            Method dump skipped, instruction units count: 673
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.g.parseAction(org.json.JSONObject):com.igexin.push.extension.mod.BaseActionBean");
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        boolean z;
        if (!(baseActionBean instanceof com.igexin.push.core.b.h)) {
            return PushMessageInterface.ActionPrepareState.stop;
        }
        com.igexin.push.core.b.h hVar = (com.igexin.push.core.b.h) baseActionBean;
        String str = hVar.g;
        String str2 = hVar.t;
        String taskId = pushTaskBean.getTaskId();
        String messageId = pushTaskBean.getMessageId();
        boolean z2 = true;
        if (str2 != null) {
            String strA = com.igexin.push.core.h.a().a(str2);
            if (strA.equals("")) {
                hVar.z = false;
                z = true;
            } else {
                hVar.x = strA;
                z = false;
            }
        } else {
            z = false;
        }
        if (str != null) {
            String strA2 = com.igexin.push.core.h.a().a(str);
            if ("".equals(strA2)) {
                hVar.y = false;
            } else {
                hVar.w = strA2;
                z2 = false;
            }
        } else {
            z2 = false;
        }
        if (!z2 && !z) {
            return PushMessageInterface.ActionPrepareState.success;
        }
        if (z2) {
            a(str, taskId, messageId, baseActionBean, 2);
        }
        if (z) {
            a(str2, taskId, messageId, baseActionBean, 8);
        }
        return PushMessageInterface.ActionPrepareState.wait;
    }
}
