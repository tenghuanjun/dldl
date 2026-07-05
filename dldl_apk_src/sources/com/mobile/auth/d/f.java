package com.mobile.auth.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.aliyun.aliyunface.utils.MobileUtil;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.tencent.connect.common.Constants;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class f {
    private static int a(int i) {
        int i2 = -101;
        if (i != -101) {
            i2 = -1;
            if (i != -1) {
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                    case 16:
                        return 1;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                    case 17:
                        return 2;
                    case 13:
                    case 18:
                    case 19:
                        return 3;
                    default:
                        return i;
                }
            }
        }
        return i2;
    }

    public static NetworkInfo a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
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

    public static boolean b(Context context) {
        try {
            NetworkInfo networkInfoA = a(context);
            if (networkInfoA != null) {
                return networkInfoA.isAvailable();
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

    public static boolean c(Context context) {
        try {
            NetworkInfo networkInfoA = a(context);
            if (networkInfoA != null) {
                if (networkInfoA.getType() == 0) {
                    return true;
                }
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

    public static boolean d(Context context) {
        if (context == null) {
            return true;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
        } catch (Throwable th) {
            try {
                com.mobile.auth.a.a.a("NetUtil", "isMobileEnable error ", th);
                return true;
            } catch (Throwable th2) {
                try {
                    ExceptionProcessor.processException(th2);
                    return false;
                } catch (Throwable th3) {
                    ExceptionProcessor.processException(th3);
                    return false;
                }
            }
        }
    }

    public static String e(Context context) {
        try {
            int iH = h(context);
            return iH != -101 ? (iH == -1 || iH == 0) ? "null" : iH != 1 ? iH != 2 ? iH != 3 ? Integer.toString(iH) : MobileUtil.NETWORK_4G : MobileUtil.NETWORK_3G : MobileUtil.NETWORK_2G : MobileUtil.NETWORK_WIFI;
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

    public static String f(Context context) {
        try {
            String strE = e(context);
            if (strE != null && strE.equals(MobileUtil.NETWORK_WIFI)) {
                if (d(context)) {
                    return "BOTH";
                }
            }
            return strE;
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

    public static String g(Context context) {
        try {
            String strF = f(context);
            if (!TextUtils.isEmpty(strF) && !strF.equals("null")) {
                if (strF.equals(MobileUtil.NETWORK_2G)) {
                    return Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
                }
                if (strF.equals(MobileUtil.NETWORK_3G)) {
                    return Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE;
                }
                if (strF.equals(MobileUtil.NETWORK_4G)) {
                    return Constants.VIA_REPORT_TYPE_SET_AVATAR;
                }
                if (strF.equals(MobileUtil.NETWORK_WIFI)) {
                    return Constants.VIA_REPORT_TYPE_JOININ_GROUP;
                }
                if (strF.equals("BOTH")) {
                    return Constants.VIA_REPORT_TYPE_MAKE_FRIEND;
                }
            }
            return Constants.VIA_REPORT_TYPE_WPA_STATE;
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

    private static int h(Context context) {
        int subtype = 0;
        try {
            try {
                try {
                    NetworkInfo networkInfoA = a(context);
                    if (networkInfoA != null && networkInfoA.isAvailable() && networkInfoA.isConnected()) {
                        int type = networkInfoA.getType();
                        if (type == 1) {
                            subtype = -101;
                        } else if (type == 0) {
                            try {
                                subtype = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (subtype == 0) {
                                subtype = networkInfoA.getSubtype();
                            }
                        }
                    } else {
                        subtype = -1;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } catch (NullPointerException e3) {
                e3.printStackTrace();
            }
            return a(subtype);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return -1;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return -1;
            }
        }
    }
}
