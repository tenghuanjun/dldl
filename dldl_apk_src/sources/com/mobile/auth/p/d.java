package com.mobile.auth.p;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.text.TextUtils;
import com.nirvana.tools.requestqueue.TimeoutCallable;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class d implements TimeoutCallable<com.mobile.auth.u.d> {
    private Context a;
    private com.mobile.auth.o.a b;

    @TargetApi(21)
    public static class a {
        private static volatile a a;
        private volatile String b = null;

        public a(Context context) {
            b(context);
        }

        public static a a(Context context) {
            try {
                if (a == null) {
                    synchronized (a.class) {
                        if (a == null) {
                            a = new a(context);
                        }
                    }
                }
                return a;
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                    return null;
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                    return null;
                }
            }
        }

        static /* synthetic */ String a(a aVar, String str) {
            try {
                aVar.b = str;
                return str;
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                    return null;
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                    return null;
                }
            }
        }

        public String a() {
            try {
                return this.b;
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                    return null;
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                    return null;
                }
            }
        }

        public void b(Context context) {
            try {
                try {
                    this.b = com.mobile.auth.gatewayauth.utils.c.b();
                    if (TextUtils.isEmpty(this.b)) {
                        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                        NetworkRequest.Builder builder = new NetworkRequest.Builder();
                        builder.addTransportType(0);
                        builder.addCapability(12);
                        connectivityManager.requestNetwork(builder.build(), new ConnectivityManager.NetworkCallback() { // from class: com.mobile.auth.p.d.a.1
                            @Override // android.net.ConnectivityManager.NetworkCallback
                            public void onAvailable(Network network) {
                                try {
                                    super.onAvailable(network);
                                    a.a(a.this, com.mobile.auth.gatewayauth.utils.c.b());
                                } catch (Throwable th) {
                                    try {
                                        com.mobile.auth.gatewayauth.a.a(th);
                                    } catch (Throwable th2) {
                                        com.mobile.auth.gatewayauth.a.a(th2);
                                    }
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    public d(Context context, com.mobile.auth.o.a aVar) {
        this.a = context;
        this.b = aVar;
    }

    @TargetApi(21)
    private void a(Context context, com.mobile.auth.u.d dVar) {
        try {
            String strA = a.a(context).a();
            if (TextUtils.isEmpty(strA)) {
                dVar.a(false);
            } else {
                dVar.a(true);
                dVar.a(strA);
            }
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    private boolean a(Context context, String str) {
        try {
            Class<?> cls = Class.forName("android.net.ConnectivityManager");
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                this.b.a(new String[]{"ConnectivityManager is null, cannot try to force a mobile connection"});
                return false;
            }
            NetworkInfo.State state = connectivityManager.getNetworkInfo(5).getState();
            this.b.a(new String[]{"TYPE_MOBILE_HIPRI network state: ", String.valueOf(state)});
            if (state.compareTo(NetworkInfo.State.CONNECTED) != 0 && state.compareTo(NetworkInfo.State.CONNECTING) != 0) {
                int iIntValue = ((Integer) cls.getMethod("startUsingNetworkFeature", Integer.TYPE, String.class).invoke(connectivityManager, 0, "enableHIPRI")).intValue();
                this.b.a(new String[]{"startUsingNetworkFeature for enableHIPRI result: ", String.valueOf(iIntValue)});
                if (-1 == iIntValue) {
                    this.b.a(new String[]{"Wrong result of startUsingNetworkFeature, maybe problems"});
                    return false;
                }
                if (iIntValue == 0) {
                    this.b.a(new String[]{"No need to perform additional network settings"});
                    return true;
                }
                String strB = com.mobile.auth.ab.f.b(str);
                this.b.a(new String[]{"Source address: " + str});
                this.b.a(new String[]{"Destination host address to route: " + strB});
                if (!TextUtils.isEmpty(strB)) {
                    str = strB;
                }
                int iC = com.mobile.auth.ab.f.c(str);
                if (-1 == iC) {
                    this.b.a(new String[]{"Wrong host address transformation, result was -1"});
                    return false;
                }
                for (int i = 0; i < 3; i++) {
                    try {
                        if (connectivityManager.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                            break;
                        }
                        Thread.sleep(1000L);
                    } catch (InterruptedException unused) {
                    }
                }
                boolean zBooleanValue = ((Boolean) cls.getMethod("requestRouteToHost", Integer.TYPE, Integer.TYPE).invoke(connectivityManager, 5, Integer.valueOf(iC))).booleanValue();
                this.b.b(new String[]{"requestRouteToHost result: " + zBooleanValue});
                if (!zBooleanValue) {
                    this.b.b(new String[]{"Wrong requestRouteToHost result: expected true, but was false"});
                }
                this.b.a(new String[]{"TYPE_MOBILE_HIPRI network state after routing: ", String.valueOf(connectivityManager.getNetworkInfo(5).getState())});
                return zBooleanValue;
            }
            return true;
        } catch (Exception unused2) {
            return false;
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return false;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return false;
            }
        }
    }

    public com.mobile.auth.u.d a() {
        try {
            return new com.mobile.auth.u.d(true, false);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }

    public com.mobile.auth.u.d b() throws Exception {
        try {
            com.mobile.auth.u.d dVar = new com.mobile.auth.u.d(false, false);
            if (Build.VERSION.SDK_INT >= 21) {
                a(this.a, dVar);
            } else if (a(this.a, "https://id6.me/auth/preauth.do")) {
                com.mobile.auth.d.d.a(this.a, "https://id6.me/auth/preauth.do", null, null, "");
                dVar.a(true);
                dVar.a(com.mobile.auth.gatewayauth.utils.c.b());
            } else {
                dVar.a(false);
            }
            return dVar;
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }

    @Override // java.util.concurrent.Callable
    public /* synthetic */ Object call() throws Exception {
        try {
            return b();
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }

    @Override // com.nirvana.tools.requestqueue.TimeoutCallable
    public /* synthetic */ com.mobile.auth.u.d onTimeout() {
        try {
            return a();
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }
}
