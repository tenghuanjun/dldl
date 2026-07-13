package com.mobile.auth.z;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import android.text.TextUtils;
import com.bun.miitmdid.x$;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class q {
    private static q f;
    private Network a = null;
    private ConnectivityManager.NetworkCallback b = null;
    private ConnectivityManager c = null;
    private List<a> d = new ArrayList();
    private Timer e = null;

    public interface a {
        void a(boolean z, Object obj);
    }

    private q() {
    }

    static /* synthetic */ Network a(q qVar) {
        try {
            return qVar.a;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    static /* synthetic */ Network a(q qVar, Network network) {
        try {
            qVar.a = network;
            return network;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static q a() {
        try {
            if (f == null) {
                synchronized (q.class) {
                    if (f == null) {
                        f = new q();
                    }
                }
            }
            return f;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    private synchronized void a(a aVar) {
        try {
            try {
                this.d.add(aVar);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
        }
    }

    static /* synthetic */ void a(q qVar, boolean z, Network network) {
        try {
            qVar.a(z, network);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    private synchronized void a(boolean z, Network network) {
        try {
            try {
                Timer timer = this.e;
                if (timer != null) {
                    timer.cancel();
                    this.e = null;
                }
                Iterator<a> it = this.d.iterator();
                while (it.hasNext()) {
                    it.next().a(z, network);
                }
                this.d.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
        }
    }

    static /* synthetic */ ConnectivityManager b(q qVar) {
        try {
            return qVar.c;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public final synchronized void a(Context context, a aVar) {
        try {
            Network network = this.a;
            if (network != null) {
                aVar.a(true, network);
                return;
            }
            a(aVar);
            if (this.b != null && this.d.size() >= 2) {
                return;
            }
            try {
                this.c = (ConnectivityManager) context.getSystemService("connectivity");
                NetworkRequest.Builder builder = new NetworkRequest.Builder();
                builder.addTransportType(0);
                builder.addCapability(12);
                NetworkRequest networkRequestBuild = builder.build();
                this.b = new ConnectivityManager.NetworkCallback() { // from class: com.mobile.auth.z.q.1
                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public final void onAvailable(Network network2) {
                        try {
                            super.onAvailable(network2);
                            t.c("Network onAvailable");
                            q.a(q.this, network2);
                            q.a(q.this, true, network2);
                            try {
                                String extraInfo = q.b(q.this).getNetworkInfo(q.a(q.this)).getExtraInfo();
                                if (TextUtils.isEmpty(extraInfo)) {
                                    return;
                                }
                                u.d(extraInfo);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (Throwable th) {
                            ExceptionProcessor.processException(th);
                        }
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public final void onLost(Network network2) {
                        try {
                            super.onLost(network2);
                            t.c("Network onLost");
                            q.this.b();
                        } catch (Throwable th) {
                            ExceptionProcessor.processException(th);
                        }
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public final void onUnavailable() {
                        try {
                            super.onUnavailable();
                            t.c("Network onUnavailable");
                            q.a(q.this, false, null);
                            q.this.b();
                        } catch (Throwable th) {
                            ExceptionProcessor.processException(th);
                        }
                    }
                };
                int i = 3000;
                if (u.g() < 3000) {
                    i = GSYVideoView.CHANGE_DELAY_TIME;
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    x$.ExternalSyntheticApiModelOutline0.m(this.c, networkRequestBuild, this.b, i);
                    return;
                }
                Timer timer = new Timer();
                this.e = timer;
                timer.schedule(new TimerTask() { // from class: com.mobile.auth.z.q.2
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public final void run() {
                        try {
                            q.a(q.this, false, null);
                        } catch (Throwable th) {
                            ExceptionProcessor.processException(th);
                        }
                    }
                }, i);
                this.c.requestNetwork(networkRequestBuild, this.b);
            } catch (Exception e) {
                e.printStackTrace();
                a(false, (Network) null);
            }
        } finally {
        }
    }

    public final synchronized void b() {
        ConnectivityManager.NetworkCallback networkCallback;
        try {
            try {
                Timer timer = this.e;
                if (timer != null) {
                    timer.cancel();
                    this.e = null;
                }
                ConnectivityManager connectivityManager = this.c;
                if (connectivityManager != null && (networkCallback = this.b) != null) {
                    connectivityManager.unregisterNetworkCallback(networkCallback);
                }
                this.c = null;
                this.b = null;
                this.a = null;
                this.d.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
        }
    }
}
