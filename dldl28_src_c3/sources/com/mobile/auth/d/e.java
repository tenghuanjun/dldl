package com.mobile.auth.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.net.InetAddress;
import kotlin.UByte;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class e {
    private static final String a = "e";
    private a e;
    private boolean b = false;
    private ConnectivityManager c = null;
    private ConnectivityManager.NetworkCallback d = null;
    private long f = 0;
    private long g = 0;

    public interface a {
        void a();

        void a(int i, String str, long j);

        void a(Network network, long j);
    }

    public static int a(String str) {
        try {
            try {
                byte[] address = InetAddress.getByName(str).getAddress();
                return (address[0] & UByte.MAX_VALUE) | ((address[3] & UByte.MAX_VALUE) << 24) | ((address[2] & UByte.MAX_VALUE) << 16) | ((address[1] & UByte.MAX_VALUE) << 8);
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return -1;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return -1;
                }
            }
        } catch (Throwable th3) {
            com.mobile.auth.a.a.a(a, "When InetAddress.getByName(),throws exception", th3);
            return -1;
        }
    }

    static /* synthetic */ long a(e eVar, long j) {
        try {
            eVar.f = j;
            return j;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return -1L;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return -1L;
            }
        }
    }

    static /* synthetic */ ConnectivityManager a(e eVar, ConnectivityManager connectivityManager) {
        try {
            eVar.c = connectivityManager;
            return connectivityManager;
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

    static /* synthetic */ String a() {
        try {
            return a;
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

    private void a(Context context) {
        try {
            this.f = 0L;
            this.c = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            this.g = System.currentTimeMillis();
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            builder.addCapability(12);
            builder.addTransportType(0);
            NetworkRequest networkRequestBuild = builder.build();
            ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.mobile.auth.d.e.2
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    try {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        e eVar = e.this;
                        e.a(eVar, jCurrentTimeMillis - e.d(eVar));
                        e.a(e.this, true);
                        if (e.b(e.this) != null) {
                            e.b(e.this).a(network, e.e(e.this));
                        }
                        if (e.f(e.this) != null) {
                            try {
                                e.f(e.this).unregisterNetworkCallback(this);
                                e.a(e.this, (ConnectivityManager) null);
                            } catch (Throwable th) {
                                com.mobile.auth.a.a.a(e.a(), "switchToMobileForAboveL", th);
                            }
                        }
                    } catch (Throwable th2) {
                        try {
                            ExceptionProcessor.processException(th2);
                        } catch (Throwable th3) {
                            ExceptionProcessor.processException(th3);
                        }
                    }
                }
            };
            this.d = networkCallback;
            this.c.requestNetwork(networkRequestBuild, networkCallback);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ boolean a(e eVar) {
        try {
            return eVar.b;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
    }

    static /* synthetic */ boolean a(e eVar, boolean z) {
        try {
            eVar.b = z;
            return z;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
    }

    static /* synthetic */ a b(e eVar) {
        try {
            return eVar.e;
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

    public static String b(String str) {
        try {
            int iIndexOf = str.indexOf("://");
            if (iIndexOf > 0) {
                str = str.substring(iIndexOf + 3);
            }
            int iIndexOf2 = str.indexOf(58);
            if (iIndexOf2 >= 0) {
                str = str.substring(0, iIndexOf2);
            }
            int iIndexOf3 = str.indexOf(47);
            if (iIndexOf3 >= 0) {
                str = str.substring(0, iIndexOf3);
            }
            int iIndexOf4 = str.indexOf(63);
            return iIndexOf4 >= 0 ? str.substring(0, iIndexOf4) : str;
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

    private void b() {
        ConnectivityManager.NetworkCallback networkCallback;
        try {
            ConnectivityManager connectivityManager = this.c;
            if (connectivityManager == null || (networkCallback = this.d) == null) {
                return;
            }
            try {
                connectivityManager.unregisterNetworkCallback(networkCallback);
            } catch (Throwable th) {
                com.mobile.auth.a.a.a(a, "unregisterNetworkCallback", th);
            }
            this.c = null;
        } catch (Throwable th2) {
            try {
                ExceptionProcessor.processException(th2);
            } catch (Throwable th3) {
                ExceptionProcessor.processException(th3);
            }
        }
    }

    private boolean b(Context context, String str) {
        boolean z;
        boolean zBooleanValue;
        try {
            Class<?> cls = Class.forName("android.net.ConnectivityManager");
            this.f = 0L;
            this.g = System.currentTimeMillis();
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            this.c = connectivityManager;
            if (connectivityManager.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) != 0) {
                cls.getMethod("startUsingNetworkFeature", Integer.TYPE, String.class).invoke(this.c, 0, "enableHIPRI");
                for (int i = 0; i < 5; i++) {
                    try {
                        if (this.c.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                            break;
                        }
                        Thread.sleep(500L);
                    } catch (Throwable th) {
                        com.mobile.auth.a.a.a(a, "switchToMobileForUnderL", th);
                    }
                }
            }
            int iA = a(b(str));
            Class<?> cls2 = Integer.TYPE;
            zBooleanValue = ((Boolean) cls.getMethod("requestRouteToHost", cls2, cls2).invoke(this.c, 5, Integer.valueOf(iA))).booleanValue();
        } catch (Throwable th2) {
            th = th2;
            z = false;
        }
        try {
            this.f = System.currentTimeMillis() - this.g;
            com.mobile.auth.a.a.a(a, "Switch network result ： " + zBooleanValue + " (4.x) , expendTime ：" + this.f);
            return zBooleanValue;
        } catch (Throwable th3) {
            z = zBooleanValue;
            th = th3;
            try {
                com.mobile.auth.a.a.a(a, "4.x网络切换异常", th);
                return z;
            } catch (Throwable th4) {
                try {
                    ExceptionProcessor.processException(th4);
                    return false;
                } catch (Throwable th5) {
                    ExceptionProcessor.processException(th5);
                    return false;
                }
            }
        }
    }

    static /* synthetic */ void c(e eVar) {
        try {
            eVar.b();
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ long d(e eVar) {
        try {
            return eVar.g;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return -1L;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return -1L;
            }
        }
    }

    static /* synthetic */ long e(e eVar) {
        try {
            return eVar.f;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return -1L;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return -1L;
            }
        }
    }

    static /* synthetic */ ConnectivityManager f(e eVar) {
        try {
            return eVar.c;
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

    public void a(final int i) {
        try {
            i.a().a(new Runnable() { // from class: com.mobile.auth.d.e.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (i > 2500) {
                            try {
                                Thread.sleep(2500L);
                            } catch (Throwable th) {
                                com.mobile.auth.a.a.a(e.a(), "timeoutCheckRunnable exception!", th);
                            }
                            if (!e.a(e.this)) {
                                if (e.b(e.this) != null) {
                                    e.b(e.this).a(80800, "WIFI切换超时", 2500L);
                                }
                                com.mobile.auth.a.a.a(e.a(), "切换网络超时(L)");
                                e.c(e.this);
                                return;
                            }
                        }
                        try {
                            int i2 = i;
                            if (i2 > 2500) {
                                i2 -= 2500;
                            }
                            Thread.sleep(i2);
                        } catch (Throwable th2) {
                            com.mobile.auth.a.a.a(e.a(), "timeoutCheckRunnable exception!", th2);
                        }
                        if (e.b(e.this) != null) {
                            if (e.a(e.this)) {
                                e.b(e.this).a();
                            } else {
                                e.b(e.this).a(80800, "WIFI切换超时", 2500L);
                                e.c(e.this);
                            }
                        }
                    } catch (Throwable th3) {
                        try {
                            ExceptionProcessor.processException(th3);
                        } catch (Throwable th4) {
                            ExceptionProcessor.processException(th4);
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

    public void a(Context context, a aVar) {
        try {
            this.e = aVar;
            try {
                a(context);
            } catch (Throwable th) {
                com.mobile.auth.a.a.a(a, "switchToMobileForAboveL", th);
                if (this.e != null) {
                    this.e.a(80801, "WIFI切换异常", -1L);
                }
            }
        } catch (Throwable th2) {
            try {
                ExceptionProcessor.processException(th2);
            } catch (Throwable th3) {
                ExceptionProcessor.processException(th3);
            }
        }
    }

    public boolean a(Context context, String str) {
        try {
            return b(context, str);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
    }
}
