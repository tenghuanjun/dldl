package com.mobile.auth.gatewayauth.manager;

import android.content.Context;
import android.os.Debug;
import android.os.Looper;
import android.text.TextUtils;
import com.ali.security.MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.manager.base.Cache;
import com.mobile.auth.gatewayauth.manager.base.CacheKey;
import com.mobile.auth.gatewayauth.manager.compat.ResultCodeProcessor;
import com.mobile.auth.gatewayauth.model.TokenRet;
import com.mobile.auth.gatewayauth.utils.Checker;
import com.mobile.auth.gatewayauth.utils.i;
import com.mobile.auth.gatewayauth.utils.security.CheckProxy;
import com.mobile.auth.gatewayauth.utils.security.CheckRoot;
import com.mobile.auth.gatewayauth.utils.security.EmulatorDetector;
import com.mobile.auth.gatewayauth.utils.security.PackageUtils;
import com.nirvana.tools.core.CryptUtil;
import com.nirvana.tools.core.ExecutorManager;
import com.nirvana.tools.requestqueue.Callback;
import com.nirvana.tools.requestqueue.Response;
import com.nirvana.tools.requestqueue.strategy.ThreadStrategy;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class SystemManager {
    private final Context a;
    private String b;
    private String c;
    private final com.mobile.auth.q.a d;
    private volatile boolean e = true;
    private Future<?> f;

    /* JADX INFO: renamed from: com.mobile.auth.gatewayauth.manager.SystemManager$2, reason: invalid class name */
    class AnonymousClass2 extends Callback<com.mobile.auth.w.d> {
        final /* synthetic */ StringBuffer a;
        final /* synthetic */ CountDownLatch b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ThreadStrategy threadStrategy, long j, StringBuffer stringBuffer, CountDownLatch countDownLatch) {
            super(threadStrategy, j);
            this.a = stringBuffer;
            this.b = countDownLatch;
        }

        public void a(com.mobile.auth.w.d dVar) {
            try {
                if (dVar.a() != null) {
                    this.a.append(dVar.a());
                }
                this.b.countDown();
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                }
            }
        }

        @Override // com.nirvana.tools.requestqueue.Callback
        public /* synthetic */ void onResult(Response response) {
            try {
                a((com.mobile.auth.w.d) response);
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                }
            }
        }
    }

    static {
        MinosSecurityLoad_58c63a9fd947d2b1e3a90e7b14f910b5.SLoad("pns-2.13.2.1-LogOnlineStandardCuumRelease_alijtca_plus");
    }

    public SystemManager(final Context context, com.mobile.auth.q.a aVar) {
        this.a = context.getApplicationContext();
        this.f = ExecutorManager.getInstance().scheduleFuture(new Runnable() { // from class: com.mobile.auth.gatewayauth.manager.SystemManager.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SystemManager.a(SystemManager.this, PackageUtils.getPackageName(context));
                    SystemManager.b(SystemManager.this, PackageUtils.getSign(context));
                } catch (Throwable th) {
                    try {
                        ExceptionProcessor.processException(th);
                    } catch (Throwable th2) {
                        ExceptionProcessor.processException(th2);
                    }
                }
            }
        });
        this.d = aVar;
    }

    private TokenRet a(ResultCodeProcessor resultCodeProcessor, String str) {
        try {
            if (!com.mobile.auth.gatewayauth.utils.c.f(this.a)) {
                return resultCodeProcessor.convertErrorInfo(Constant.CODE_ERROR_NO_SIM_FAIL, "SIM卡无法检测", str);
            }
            if (com.mobile.auth.gatewayauth.utils.c.e(this.a)) {
                return null;
            }
            return resultCodeProcessor.convertErrorInfo(Constant.CODE_ERROR_NO_MOBILE_NETWORK_FAIL, "移动网络未开启", str);
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

    static /* synthetic */ String a(SystemManager systemManager, String str) {
        try {
            systemManager.b = str;
            return str;
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

    static /* synthetic */ String b(SystemManager systemManager, String str) {
        try {
            systemManager.c = str;
            return str;
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

    private native String requestCellularIp();

    public TokenRet a(ResultCodeProcessor resultCodeProcessor, boolean z, String str) {
        try {
            TokenRet tokenRetCheckEnvSafe = checkEnvSafe(resultCodeProcessor, str);
            return (tokenRetCheckEnvSafe == null && z) ? a(resultCodeProcessor, str) : tokenRetCheckEnvSafe;
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

    public synchronized String a() {
        try {
            if (this.f != null) {
                try {
                    this.f.get();
                    this.f = null;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e2) {
                    e2.printStackTrace();
                }
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
        return this.b;
    }

    public void a(String str) {
        try {
            if ((FeatureManager.getInstance().get("whiteFileCheck") == null || !ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_FALSE.equals(FeatureManager.getInstance().get("whiteFileCheck").toString())) && !TextUtils.isEmpty(str)) {
                InputStream inputStream = null;
                try {
                    try {
                        String strMd5Hex = CryptUtil.md5Hex(str);
                        if (TextUtils.isEmpty(strMd5Hex)) {
                            this.e = true;
                            return;
                        }
                        InputStream inputStreamOpen = this.a.getAssets().open(strMd5Hex);
                        this.e = false;
                        if (inputStreamOpen != null) {
                            try {
                                inputStreamOpen.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception unused) {
                        this.e = true;
                        if (0 != 0) {
                            try {
                                inputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                } finally {
                }
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    protected synchronized <T> boolean a(String str, Cache<T> cache, long j) {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (cache != null && cache.getKey() != null && cache.getKey().equals(str) && cache.getExpiredTime() - j > jCurrentTimeMillis) {
                return true;
            }
            if (cache != null) {
                this.d.a("ExpiredTime:", String.valueOf(cache.getExpiredTime()), "|threshold:", String.valueOf(j), "|currTime:", String.valueOf(jCurrentTimeMillis));
            }
            return false;
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

    public synchronized String b() {
        try {
            if (this.f != null) {
                try {
                    this.f.get();
                    this.f = null;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e2) {
                    e2.printStackTrace();
                }
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
        return this.c;
    }

    public String b(String str) {
        if (str == null) {
            return "unknown";
        }
        byte b = -1;
        try {
            int iHashCode = str.hashCode();
            if (iHashCode != -1350608857) {
                if (iHashCode != 95009260) {
                    if (iHashCode == 880617272 && str.equals(Constant.VENDOR_CMCC)) {
                        b = 0;
                    }
                } else if (str.equals(Constant.VENDOR_CUCC)) {
                    b = 1;
                }
            } else if (str.equals(Constant.VENDOR_CTCC)) {
                b = 2;
            }
            return b != 0 ? b != 1 ? b != 2 ? "unknown" : Constant.CTCC : Constant.CUCC : Constant.CMCC;
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

    public String c() {
        try {
            return com.mobile.auth.gatewayauth.utils.c.b(this.a);
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

    public String c(String str) {
        try {
            return Constant.ACTION_SDK + b(str).toLowerCase() + Constant.ACTION_SDK_PRE_LOGIN_CODE;
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

    public TokenRet checkEnvSafe(ResultCodeProcessor resultCodeProcessor, String str) {
        try {
            try {
                if (this.e) {
                    String strC = Checker.c();
                    if (!TextUtils.isEmpty(strC) && !"0".equals(strC)) {
                        return resultCodeProcessor.convertErrorInfo(Constant.CODE_ERROR_PHONE_UNSAFE_FAIL, "手机终端不安全:the app is attached, please use safe phone!", str);
                    }
                }
                String strIsDeviceRooted = CheckRoot.isDeviceRooted();
                if (!TextUtils.isEmpty(strIsDeviceRooted)) {
                    return resultCodeProcessor.convertErrorInfo(Constant.CODE_ERROR_PHONE_UNSAFE_FAIL, "手机终端不安全:the phone is root, " + strIsDeviceRooted, str);
                }
                if (Thread.currentThread() == Looper.getMainLooper().getThread() && EmulatorDetector.isEmulator(this.a)) {
                    return resultCodeProcessor.convertErrorInfo(Constant.CODE_ERROR_PHONE_UNSAFE_FAIL, "手机终端不安全:Emulator is detected, please use real phone!", str);
                }
                if (CheckProxy.isDevicedProxy(this.a)) {
                    return resultCodeProcessor.convertErrorInfo(Constant.CODE_ERROR_PHONE_UNSAFE_FAIL, "手机终端不安全:the phone is proxy, please do not proxy!", str);
                }
                if (!Debug.isDebuggerConnected() || i.a()) {
                    return null;
                }
                return resultCodeProcessor.convertErrorInfo(Constant.CODE_ERROR_PHONE_UNSAFE_FAIL, "手机终端不安全:the app is debuggerConnected, please do not debug!", str);
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return null;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return null;
                }
            }
        } catch (Exception e) {
            return resultCodeProcessor.convertErrorInfo(Constant.CODE_ERROR_PHONE_UNSAFE_FAIL, "无法判运营商: " + e.getMessage(), str);
        }
    }

    public String d() {
        try {
            return com.mobile.auth.gatewayauth.utils.c.c(this.a);
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

    public String d(String str) {
        try {
            return Constant.ACTION_SDK + b(str).toLowerCase() + Constant.ACTION_SDK_PRE_AUTH_CODE;
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

    protected native synchronized String decryptContent(String str);

    public Context e() {
        try {
            return this.a;
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

    public String e(String str) {
        try {
            return Constant.ACTION_SDK + b(str).toLowerCase() + Constant.ACTION_SDK_LOGIN_CODE;
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

    protected native synchronized String encryptContent(String str);

    public String f(String str) {
        try {
            return Constant.ACTION_SDK + b(str).toLowerCase() + Constant.ACTION_SDK_LOGIN_TOKEN;
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

    public String g(String str) {
        try {
            return Constant.ACTION_SDK + b(str).toLowerCase() + Constant.ACTION_SDK_AUTH_TOKEN;
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

    public native CacheKey getSimCacheKey(boolean z, String str);

    public native CacheKey getVendorCacheKey(String str);

    public String h(String str) {
        try {
            return b(str).toLowerCase() + Constant.ACTION_AUTH_PAGE_LOGIN;
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

    public String i(String str) {
        try {
            return Constant.ACTION_SDK + b(str).toLowerCase() + Constant.ACTION_AUTH_PAGE_PRIVACYALERT;
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

    public String j(String str) {
        try {
            return b(str).toLowerCase() + Constant.ACTION_AUTH_PAGE_RETURN;
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

    public String k(String str) {
        try {
            return b(str).toLowerCase() + Constant.ACTION_CLICK_PRIVACYALERT_PRIVACY;
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

    public String l(String str) {
        try {
            return b(str).toLowerCase() + Constant.ACTION_AUTH_PAGE_PROTOCOL;
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

    public String m(String str) {
        try {
            return b(str).toLowerCase() + Constant.ACTION_AUTH_PAGE_START;
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
}
