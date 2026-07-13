package com.mobile.auth.c;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.b.f;
import com.mobile.auth.d.i;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f507a = "d";
    private static final byte[] b = {15, 31, 94, 10, 90, 15, 91, 24, 10, 30, 88, 7, 89, 10, 95, 30};

    static /* synthetic */ String a(Context context, Queue queue) {
        try {
            return b(context, (Queue<String>) queue);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    static /* synthetic */ Queue a(Context context, List list, int i) {
        try {
            return c(context, list, i);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    static /* synthetic */ void a(Context context) {
        try {
            c(context);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    private static void a(Context context, int i) {
        try {
            try {
                com.mobile.auth.d.b.a(context, "key_c_l_l_v", i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r6, java.lang.String r7) {
        /*
            int r0 = r7.hashCode()     // Catch: java.lang.Throwable -> L45
            r1 = 64897(0xfd81, float:9.094E-41)
            r2 = 2
            r3 = 1
            r4 = -1
            r5 = 0
            if (r0 == r1) goto L2c
            r1 = 78159(0x1314f, float:1.09524E-40)
            if (r0 == r1) goto L22
            r1 = 66247144(0x3f2d9e8, float:1.42735105E-36)
            if (r0 == r1) goto L18
            goto L36
        L18:
            java.lang.String r0 = "ERROR"
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Throwable -> L45
            if (r7 == 0) goto L36
            r7 = 1
            goto L37
        L22:
            java.lang.String r0 = "OFF"
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Throwable -> L45
            if (r7 == 0) goto L36
            r7 = 2
            goto L37
        L2c:
            java.lang.String r0 = "ALL"
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Throwable -> L45
            if (r7 == 0) goto L36
            r7 = 0
            goto L37
        L36:
            r7 = -1
        L37:
            if (r7 == 0) goto L40
            if (r7 == r3) goto L41
            if (r7 == r2) goto L3e
            goto L40
        L3e:
            r4 = -2
            goto L41
        L40:
            r4 = 0
        L41:
            a(r6, r4)     // Catch: java.lang.Throwable -> L45
            goto L4e
        L45:
            r6 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r6)     // Catch: java.lang.Throwable -> L4a
            goto L4e
        L4a:
            r6 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r6)
        L4e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.c.d.a(android.content.Context, java.lang.String):void");
    }

    public static void a(Context context, List<String> list) {
        try {
            int iB = b(context);
            if (iB == -2) {
                return;
            }
            b(context, list, iB);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void a(Context context, Queue queue, int i) {
        try {
            b(context, (Queue<String>) queue, i);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    private static int b(Context context) {
        try {
            return com.mobile.auth.d.b.b(context, "key_c_l_l_v", 0);
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                return 0;
            } catch (Throwable th2) {
                try {
                    ExceptionProcessor.processException(th2);
                    return -1;
                } catch (Throwable th3) {
                    ExceptionProcessor.processException(th3);
                    return -1;
                }
            }
        }
    }

    private static String b(Context context, String str) {
        try {
            return a.a(context, "https://api-e189.21cn.com/gw/client/accountMsg.do", str);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private static String b(Context context, Queue<String> queue) {
        try {
            JSONArray jSONArray = new JSONArray();
            String string = jSONArray.toString();
            if (!queue.isEmpty()) {
                Iterator<String> it = queue.iterator();
                while (it.hasNext()) {
                    try {
                        jSONArray.put(new JSONObject(it.next()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (jSONArray.length() <= 0) {
                return "";
            }
            String string2 = jSONArray.toString();
            if (!TextUtils.isEmpty(string2)) {
                try {
                    string = URLEncoder.encode(com.mobile.auth.b.b.a(com.mobile.auth.b.a.b(string2, f.a(b))), "UTF-8");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return b(context, string);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private static void b(final Context context, final List<String> list, final int i) {
        try {
            i.a().a(new Runnable() { // from class: com.mobile.auth.c.d.1
                @Override // java.lang.Runnable
                public void run() throws JSONException {
                    try {
                        Queue queueA = d.a(context, list, i);
                        if (queueA.isEmpty()) {
                            return;
                        }
                        String strA = d.a(context, queueA);
                        JSONObject jSONObject = null;
                        int i2 = -1;
                        try {
                            if (!TextUtils.isEmpty(strA)) {
                                JSONObject jSONObject2 = new JSONObject(strA);
                                try {
                                    i2 = jSONObject2.getInt("code");
                                    jSONObject = jSONObject2;
                                } catch (Exception e) {
                                    e = e;
                                    jSONObject = jSONObject2;
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                        }
                        if (jSONObject == null || i2 != 0) {
                            d.a(context, queueA, i);
                        } else {
                            d.a(context);
                            queueA.clear();
                        }
                    } catch (Throwable th) {
                        try {
                            th.printStackTrace();
                        } catch (Throwable th2) {
                            try {
                                ExceptionProcessor.processException(th2);
                            } catch (Throwable th3) {
                                ExceptionProcessor.processException(th3);
                            }
                        }
                    }
                }
            });
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    private static void b(Context context, Queue<String> queue, int i) {
        JSONObject jSONObject;
        try {
            String strA = "";
            JSONArray jSONArray = new JSONArray();
            if (queue != null && !queue.isEmpty()) {
                Iterator<String> it = queue.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    try {
                        jSONObject = new JSONObject(it.next());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (i != -1 || jSONObject.getInt("rt") != 0) {
                        jSONArray.put(jSONObject);
                        i2++;
                        if (i2 > 10) {
                            break;
                        }
                    }
                }
            }
            if (jSONArray.length() > 0) {
                try {
                    strA = com.mobile.auth.b.a.a(jSONArray.toString(), f.a(b));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    strA = null;
                }
            }
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            c.a(context, strA);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    private static synchronized Queue<String> c(Context context, List<String> list, int i) {
        ConcurrentLinkedQueue concurrentLinkedQueue;
        try {
            concurrentLinkedQueue = new ConcurrentLinkedQueue();
            String strA = c.a(context);
            if (!TextUtils.isEmpty(strA)) {
                try {
                    JSONArray jSONArray = new JSONArray(com.mobile.auth.b.a.c(strA, f.a(b)));
                    int length = jSONArray.length();
                    for (int i2 = 0; i2 < length && i2 <= 10; i2++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        if (jSONObject != null) {
                            concurrentLinkedQueue.add(jSONObject.toString());
                        }
                    }
                    c.a(context, "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (i == -1) {
                for (String str : list) {
                    try {
                        if (new JSONObject(str).getInt("rt") != 0) {
                            concurrentLinkedQueue.add(str);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } else if (i == 0) {
                concurrentLinkedQueue.addAll(list);
            }
            while (concurrentLinkedQueue.size() > 10) {
                concurrentLinkedQueue.poll();
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
        return concurrentLinkedQueue;
    }

    private static void c(Context context) {
        try {
            c.a(context, "");
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }
}
