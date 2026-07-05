package com.mobile.auth.ab;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.CountDownTimer;
import android.text.TextUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class a {
    private static volatile a a;
    private ConnectivityManager.NetworkCallback b = null;
    private ConnectivityManager c = null;
    private volatile CountDownTimerC0063a d = null;
    private ExecutorService e = null;

    /* JADX INFO: renamed from: com.mobile.auth.ab.a$a, reason: collision with other inner class name */
    private class CountDownTimerC0063a extends CountDownTimer {
        private b b;

        public CountDownTimerC0063a(b bVar) {
            super(3000L, 1000L);
            this.b = bVar;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            try {
                if (this.b != null && a.a(a.this) != null) {
                    this.b.a(false, null);
                }
                a.a(a.this, (CountDownTimerC0063a) null);
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    }

    public interface b {
        void a(boolean z, Network network);
    }

    static /* synthetic */ CountDownTimerC0063a a(a aVar) {
        try {
            return aVar.d;
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

    static /* synthetic */ CountDownTimerC0063a a(a aVar, CountDownTimerC0063a countDownTimerC0063a) {
        try {
            aVar.d = countDownTimerC0063a;
            return countDownTimerC0063a;
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

    public static a a() {
        try {
            if (a == null) {
                synchronized (a.class) {
                    if (a == null) {
                        a = new a();
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

    @TargetApi(21)
    private void a(Context context, final b bVar) {
        try {
            try {
                this.c = (ConnectivityManager) context.getSystemService("connectivity");
                NetworkRequest.Builder builder = new NetworkRequest.Builder();
                builder.addTransportType(0);
                builder.addCapability(12);
                NetworkRequest networkRequestBuild = builder.build();
                this.b = new ConnectivityManager.NetworkCallback() { // from class: com.mobile.auth.ab.a.2
                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onAvailable(Network network) {
                        try {
                            super.onAvailable(network);
                            try {
                                if (a.a(a.this) != null) {
                                    a.a(a.this).cancel();
                                    a.a(a.this, (CountDownTimerC0063a) null);
                                    if (bVar != null) {
                                        bVar.a(true, network);
                                    }
                                }
                            } catch (Exception unused) {
                                if (bVar != null) {
                                    bVar.a(false, null);
                                }
                            }
                        } catch (Throwable th) {
                            try {
                                com.mobile.auth.gatewayauth.a.a(th);
                            } catch (Throwable th2) {
                                com.mobile.auth.gatewayauth.a.a(th2);
                            }
                        }
                    }
                };
                if (this.d != null) {
                    this.d.cancel();
                    this.d = null;
                }
                this.d = new CountDownTimerC0063a(bVar);
                this.d.start();
                this.c.requestNetwork(networkRequestBuild, this.b);
            } catch (Exception e) {
                e.printStackTrace();
                if (bVar != null) {
                    bVar.a(false, null);
                }
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
            this.c = (ConnectivityManager) context.getSystemService("connectivity");
            if (this.c == null) {
                d.a("ConnectivityManager is null, cannot try to force a mobile connection");
                return false;
            }
            NetworkInfo.State state = this.c.getNetworkInfo(5).getState();
            d.a("TYPE_MOBILE_HIPRI network state: " + state);
            if (state.compareTo(NetworkInfo.State.CONNECTED) != 0 && state.compareTo(NetworkInfo.State.CONNECTING) != 0) {
                int iStartUsingNetworkFeature = this.c.startUsingNetworkFeature(0, "enableHIPRI");
                d.a("startUsingNetworkFeature for enableHIPRI result: " + iStartUsingNetworkFeature);
                if (-1 == iStartUsingNetworkFeature) {
                    d.a("Wrong result of startUsingNetworkFeature, maybe problems");
                    return false;
                }
                if (iStartUsingNetworkFeature == 0) {
                    d.a("No need to perform additional network settings");
                    return true;
                }
                String strB = f.b(str);
                d.a("Source address: " + str);
                d.a("Destination host address to route: " + strB);
                if (TextUtils.isEmpty(strB)) {
                    strB = str;
                }
                int iC = f.c(strB);
                if (-1 == iC) {
                    d.a("Wrong host address transformation, result was -1");
                    return false;
                }
                for (int i = 0; i < 3; i++) {
                    try {
                        if (this.c.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                            break;
                        }
                        Thread.sleep(1000L);
                    } catch (InterruptedException unused) {
                    }
                }
                boolean zRequestRouteToHost = this.c.requestRouteToHost(5, iC);
                d.a("requestRouteToHost result: " + zRequestRouteToHost);
                if (!zRequestRouteToHost) {
                    d.a("Wrong requestRouteToHost result: expected true, but was false");
                }
                d.a("TYPE_MOBILE_HIPRI network state after routing: " + this.c.getNetworkInfo(5).getState());
                return zRequestRouteToHost;
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

    static /* synthetic */ boolean a(a aVar, Context context, String str) {
        try {
            return aVar.a(context, str);
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

    public void a(final Context context, final String str, final b bVar) {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                a(context, bVar);
                return;
            }
            if (this.e != null) {
                this.e.shutdownNow();
            }
            this.e = Executors.newSingleThreadExecutor();
            this.e.submit(new Runnable() { // from class: com.mobile.auth.ab.a.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        boolean zA = a.a(a.this, context, str);
                        d.a("forceMobileConnectionForAddress = " + zA);
                        if (bVar != null) {
                            bVar.a(zA, null);
                        }
                    } catch (Throwable th) {
                        try {
                            com.mobile.auth.gatewayauth.a.a(th);
                        } catch (Throwable th2) {
                            com.mobile.auth.gatewayauth.a.a(th2);
                        }
                    }
                }
            });
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public void b() {
        try {
            try {
                try {
                    if (Build.VERSION.SDK_INT >= 21) {
                        if (this.c != null) {
                            if (this.b != null) {
                                this.c.unregisterNetworkCallback(this.b);
                                this.b = null;
                            }
                            this.c = null;
                        }
                    } else if (this.c != null) {
                        this.c = null;
                    }
                    if (this.d != null) {
                        this.d.cancel();
                        this.d = null;
                    }
                    if (this.e != null) {
                        this.e.shutdownNow();
                        this.e = null;
                        return;
                    }
                    return;
                } catch (Exception e) {
                    d.a(e);
                    return;
                }
            } catch (Throwable th) {
                com.mobile.auth.gatewayauth.a.a(th);
                return;
            }
            com.mobile.auth.gatewayauth.a.a(th);
            return;
        } catch (Throwable th2) {
            com.mobile.auth.gatewayauth.a.a(th2);
            return;
        }
    }
}
