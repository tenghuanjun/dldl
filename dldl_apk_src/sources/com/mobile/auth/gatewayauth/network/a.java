package com.mobile.auth.gatewayauth.network;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.model.ConfigRule;
import com.nirvana.tools.core.EncodeUtil;
import com.nirvana.tools.jsoner.JSONUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class a {
    public static <T> T a(Context context, String str, String str2, T t) {
        try {
            if (!a(context, str, str2)) {
                return t;
            }
            String strDecode = EncodeUtil.decode(context.getSharedPreferences(str, 0).getString(str2, ""));
            if (t instanceof Integer) {
                return (T) Integer.valueOf(strDecode);
            }
            if (t instanceof Boolean) {
                return (T) Boolean.valueOf(strDecode);
            }
            if (t instanceof Long) {
                return (T) Long.valueOf(strDecode);
            }
            if (t instanceof String) {
                return (T) String.valueOf(strDecode);
            }
            throw new Exception("unsupported type");
        } catch (Exception unused) {
            return t;
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

    public static synchronized String a(Context context) {
        try {
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
        return (String) a(context, "AUTH_UT_DATA", UTSharedPreferencesHelper.AUTH_FLAG_LIMIT_GET_CONFIG_KEY, "");
    }

    public static synchronized void a(Context context, String str) {
        try {
            b(context, "AUTH_UT_DATA", UTSharedPreferencesHelper.AUTH_FLAG_LIMIT_GET_CONFIG_KEY, str);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static synchronized <T> void a(Context context, String str, T t) {
        try {
            b(context, "AUTH_APP_INFO", str, t);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static synchronized void a(Context context, boolean z) {
        try {
            b(context, "AUTH_UT_DATA", UTSharedPreferencesHelper.AUTH_FLAG_CLOSE_GET_CONFIG_KEY, Boolean.valueOf(z));
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static boolean a(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(str, 0).contains(str2);
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

    public static synchronized <T> T b(Context context, String str, T t) {
        try {
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
        return (T) a(context, "AUTH_APP_INFO", str, t);
    }

    public static synchronized void b(Context context, String str) {
        try {
            b(context, "AUTH_UT_DATA", "AUTH_SDK_CONFIG_KEY", str);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static synchronized void b(Context context, String str, String str2) {
        int iValueOf;
        try {
            String str3 = (String) a(context, "AUTH_UT_DATA", str, "");
            Map<String, Integer> mapJson2MapForStringInteger = TextUtils.isEmpty(str3) ? null : JSONUtils.json2MapForStringInteger(str3);
            if (mapJson2MapForStringInteger == null || mapJson2MapForStringInteger.isEmpty() || !mapJson2MapForStringInteger.containsKey(str2)) {
                mapJson2MapForStringInteger = new HashMap<>();
                iValueOf = 1;
            } else {
                iValueOf = Integer.valueOf(mapJson2MapForStringInteger.get(str2).intValue() + 1);
            }
            mapJson2MapForStringInteger.put(str2, iValueOf);
            b(context, "AUTH_UT_DATA", str, new JSONObject(mapJson2MapForStringInteger).toString());
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static <T> void b(Context context, String str, String str2, T t) {
        try {
            try {
                context.getSharedPreferences(str, 0).edit().putString(str2, EncodeUtil.encode(t.toString())).commit();
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

    public static synchronized boolean b(Context context) {
        try {
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return false;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return false;
            }
        }
        return ((Boolean) a(context, "AUTH_UT_DATA", UTSharedPreferencesHelper.AUTH_FLAG_CLOSE_GET_CONFIG_KEY, false)).booleanValue();
    }

    public static synchronized int c(Context context, String str, String str2) {
        try {
            String str3 = (String) a(context, "AUTH_UT_DATA", str, "");
            Map<String, Integer> mapJson2MapForStringInteger = TextUtils.isEmpty(str3) ? null : JSONUtils.json2MapForStringInteger(str3);
            if (mapJson2MapForStringInteger == null || mapJson2MapForStringInteger.isEmpty() || !mapJson2MapForStringInteger.containsKey(str2)) {
                return 0;
            }
            return mapJson2MapForStringInteger.get(str2).intValue();
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return -1;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return -1;
            }
        }
    }

    public static synchronized ConfigRule c(Context context) {
        ConfigRule configRuleFromJson;
        configRuleFromJson = null;
        try {
            String str = (String) a(context, "AUTH_UT_DATA", "AUTH_SDK_CONFIG_KEY", "");
            if (!TextUtils.isEmpty(str)) {
                try {
                    configRuleFromJson = ConfigRule.fromJson(str);
                } catch (Exception unused) {
                    return null;
                }
            }
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
        return configRuleFromJson;
    }

    public static synchronized void c(Context context, String str) {
        try {
            b(context, "AUTH_LIMIT_SLS_KEY", str);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static synchronized int d(Context context, String str) {
        try {
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return -1;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return -1;
            }
        }
        return c(context, "AUTH_LIMIT_SLS_KEY", str);
    }

    public static synchronized void d(Context context) {
        try {
            b(context, "AUTH_UT_DATA", "AUTH_LIMIT_SLS_KEY", "");
            b(context, "AUTH_UT_DATA", UTSharedPreferencesHelper.AUTH_LIMIT_VENDOR_LIST_KEY, "");
            b(context, "AUTH_UT_DATA", UTSharedPreferencesHelper.AUTH_LIMIT_GET_CONFIG_KEY, "");
            b(context, "AUTH_UT_DATA", UTSharedPreferencesHelper.AUTH_LIMIT_AUTH_TOKEN_KEY, "");
            b(context, "AUTH_UT_DATA", UTSharedPreferencesHelper.AUTH_LIMIT_LOGIN_TOKEN_KEY, "");
            b(context, "AUTH_UT_DATA", UTSharedPreferencesHelper.AUTH_LIMIT_LOGIN_PHONE_KEY, "");
            b(context, "AUTH_UT_DATA", UTSharedPreferencesHelper.AUTH_LIMIT_LOGIN_PAGE_KEY, "");
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static synchronized void d(Context context, String str, String str2) {
        try {
            context.getSharedPreferences(str, 0).edit().remove(str2).commit();
        } catch (Exception unused) {
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static synchronized void e(Context context, String str) {
        try {
            b(context, UTSharedPreferencesHelper.AUTH_LIMIT_VENDOR_LIST_KEY, str);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static synchronized int f(Context context, String str) {
        try {
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return -1;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return -1;
            }
        }
        return c(context, UTSharedPreferencesHelper.AUTH_LIMIT_VENDOR_LIST_KEY, str);
    }

    public static synchronized void g(Context context, String str) {
        try {
            b(context, UTSharedPreferencesHelper.AUTH_LIMIT_GET_CONFIG_KEY, str);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static synchronized int h(Context context, String str) {
        try {
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return -1;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return -1;
            }
        }
        return c(context, UTSharedPreferencesHelper.AUTH_LIMIT_GET_CONFIG_KEY, str);
    }

    public static synchronized void i(Context context, String str) {
        try {
            b(context, UTSharedPreferencesHelper.AUTH_LIMIT_AUTH_TOKEN_KEY, str);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static synchronized int j(Context context, String str) {
        try {
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return -1;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return -1;
            }
        }
        return c(context, UTSharedPreferencesHelper.AUTH_LIMIT_AUTH_TOKEN_KEY, str);
    }

    public static synchronized void k(Context context, String str) {
        try {
            b(context, UTSharedPreferencesHelper.AUTH_LIMIT_LOGIN_TOKEN_KEY, str);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static synchronized int l(Context context, String str) {
        try {
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return -1;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return -1;
            }
        }
        return c(context, UTSharedPreferencesHelper.AUTH_LIMIT_LOGIN_TOKEN_KEY, str);
    }

    public static synchronized void m(Context context, String str) {
        try {
            b(context, UTSharedPreferencesHelper.AUTH_LIMIT_LOGIN_PHONE_KEY, str);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static synchronized int n(Context context, String str) {
        try {
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return -1;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return -1;
            }
        }
        return c(context, UTSharedPreferencesHelper.AUTH_LIMIT_LOGIN_PHONE_KEY, str);
    }

    public static synchronized void o(Context context, String str) {
        try {
            b(context, UTSharedPreferencesHelper.AUTH_LIMIT_LOGIN_PAGE_KEY, str);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static synchronized int p(Context context, String str) {
        try {
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return -1;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return -1;
            }
        }
        return c(context, UTSharedPreferencesHelper.AUTH_LIMIT_LOGIN_PAGE_KEY, str);
    }

    public static synchronized void q(Context context, String str) {
        try {
            d(context, "AUTH_APP_INFO", str);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }
}
