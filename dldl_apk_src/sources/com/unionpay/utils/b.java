package com.unionpay.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public final class b {
    private static HashMap a = new c();

    public static int a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.parseInt(str);
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r9) {
        /*
            java.lang.String r0 = "sePayConf"
            java.lang.String r1 = "configs"
            java.lang.String r2 = com.unionpay.utils.UPUtils.a(r9, r1)
            java.lang.String r3 = "mode"
            java.lang.String r3 = com.unionpay.utils.UPUtils.a(r9, r3)
            java.lang.String r4 = "or"
            java.lang.String r9 = com.unionpay.utils.UPUtils.a(r9, r4)
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            r5 = 0
            r6 = 2
            java.lang.String r7 = ""
            if (r4 != 0) goto L8f
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L8f
            boolean r4 = android.text.TextUtils.isEmpty(r9)
            if (r4 != 0) goto L8f
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Exception -> L8f
            r4.<init>(r2)     // Catch: java.lang.Exception -> L8f
            java.lang.String r2 = "sign"
            java.lang.String r2 = com.unionpay.utils.i.a(r4, r2)     // Catch: java.lang.Exception -> L8f
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.Exception -> L3a
            goto L3b
        L3a:
            r3 = 0
        L3b:
            java.lang.String r8 = new java.lang.String     // Catch: java.lang.Exception -> L8f
            java.lang.String r1 = r4.getString(r1)     // Catch: java.lang.Exception -> L8f
            byte[] r1 = android.util.Base64.decode(r1, r6)     // Catch: java.lang.Exception -> L8f
            r8.<init>(r1)     // Catch: java.lang.Exception -> L8f
            boolean r1 = r4.has(r0)     // Catch: java.lang.Exception -> L8f
            if (r1 == 0) goto L5c
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Exception -> L8f
            java.lang.String r0 = r4.getString(r0)     // Catch: java.lang.Exception -> L8f
            byte[] r0 = android.util.Base64.decode(r0, r6)     // Catch: java.lang.Exception -> L8f
            r1.<init>(r0)     // Catch: java.lang.Exception -> L8f
            goto L5d
        L5c:
            r1 = r7
        L5d:
            boolean r0 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> L8f
            if (r0 == 0) goto L64
            r1 = r7
        L64:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L8f
            r0.<init>()     // Catch: java.lang.Exception -> L8f
            r0.append(r8)     // Catch: java.lang.Exception -> L8f
            r0.append(r1)     // Catch: java.lang.Exception -> L8f
            r0.append(r9)     // Catch: java.lang.Exception -> L8f
            java.lang.String r9 = r0.toString()     // Catch: java.lang.Exception -> L8f
            java.lang.String r9 = com.unionpay.utils.UPUtils.a(r9)     // Catch: java.lang.Exception -> L8f
            java.lang.String r9 = b(r9)     // Catch: java.lang.Exception -> L8f
            java.lang.String r0 = com.unionpay.utils.UPUtils.a(r3, r2)     // Catch: java.lang.Exception -> L8f
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L8f
            if (r1 != 0) goto L8f
            boolean r9 = r0.equals(r9)     // Catch: java.lang.Exception -> L8f
            if (r9 == 0) goto L8f
            goto L90
        L8f:
            r8 = r7
        L90:
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch: java.lang.Exception -> Lc4
            r9.<init>(r8)     // Catch: java.lang.Exception -> Lc4
            int r0 = r9.length()
        L99:
            if (r5 >= r0) goto Lc4
            java.lang.Object r1 = com.unionpay.utils.i.a(r9, r5)
            if (r1 == 0) goto Lc1
            org.json.JSONObject r1 = (org.json.JSONObject) r1
            java.lang.String r2 = "type"
            java.lang.String r2 = com.unionpay.utils.i.a(r1, r2)
            java.lang.String r3 = "app"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto Lc1
            java.lang.String r9 = "ca"
            java.lang.String r9 = com.unionpay.utils.i.a(r1, r9)
            java.lang.String r0 = new java.lang.String
            byte[] r9 = android.util.Base64.decode(r9, r6)
            r0.<init>(r9)
            return r0
        Lc1:
            int r5 = r5 + 1
            goto L99
        Lc4:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.utils.b.a(android.content.Context):java.lang.String");
    }

    public static String a(InputStream inputStream, String str) {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (i <= 0) {
                    return byteArrayOutputStream.toString(str);
                }
                byteArrayOutputStream.write(bArr, 0, i);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strA = i.a(jSONObject, "sign");
            String strA2 = i.a(jSONObject, "configs");
            if (TextUtils.isEmpty(strA) || TextUtils.isEmpty(strA2)) {
                return null;
            }
            String str3 = new String(Base64.decode(strA2, 2));
            String strB = b(UPUtils.a(str3));
            String strA3 = UPUtils.a(a(str2), strA);
            if (TextUtils.isEmpty(strA3)) {
                return null;
            }
            if (strA3.equals(strB)) {
                return str3;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public static boolean a() {
        try {
            return "HUAWEI".equalsIgnoreCase(Build.MANUFACTURER);
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean a(Context context, String str) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = null;
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null && !TextUtils.isEmpty(str)) {
                    packageInfo = packageManager.getPackageInfo(str, 0);
                }
            } catch (Exception unused) {
            }
        }
        return packageInfo != null;
    }

    private static boolean a(Context context, String str, String str2) {
        int iG;
        int iF = f(context, str);
        try {
            iG = g(str2);
        } catch (Exception unused) {
            iG = Integer.MAX_VALUE;
        }
        return iF >= iG;
    }

    public static boolean a(Context context, String str, String str2, String str3) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                    int iF = f(context, str);
                    int iG = g(str3);
                    if (a(context, str) && iF >= iG) {
                        if (str2.equalsIgnoreCase(b(context, str, "SHA256"))) {
                            return true;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private static boolean a(Context context, String str, String str2, String str3, String str4) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                    if (str4 == null || TextUtils.isEmpty(str4)) {
                        str4 = Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE;
                    }
                    String strD = d(context, str);
                    if ((!f(str4) || !TextUtils.isEmpty(strD)) && a(context, str)) {
                        if (((Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE.equals(str4) || Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(str4)) && str2.equalsIgnoreCase(b(context, str, "SHA1"))) || str2.equalsIgnoreCase(b(context, str, "SHA256"))) {
                            if (!f(str4) || !strD.matches(str3)) {
                                if (a(context, str, str3)) {
                                }
                            }
                            return true;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static boolean a(Context context, JSONObject jSONObject) {
        if (context == null || jSONObject == null) {
            return false;
        }
        return a(context, i.a(jSONObject, "schema"), i.a(jSONObject, "sign"), i.a(jSONObject, "version"), i.a(jSONObject, "checkMode"));
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0052 A[Catch: CertificateEncodingException -> 0x0060, NoSuchAlgorithmException -> 0x0065, Exception -> 0x0072, TRY_LEAVE, TryCatch #6 {NoSuchAlgorithmException -> 0x0065, CertificateEncodingException -> 0x0060, blocks: (B:33:0x004c, B:35:0x0052), top: B:54:0x004c, outer: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006b A[Catch: Exception -> 0x0072, TRY_LEAVE, TryCatch #5 {Exception -> 0x0072, blocks: (B:4:0x0005, B:9:0x000f, B:15:0x001b, B:17:0x001f, B:19:0x0022, B:21:0x0027, B:22:0x0032, B:28:0x0040, B:33:0x004c, B:35:0x0052, B:42:0x006b, B:38:0x0061, B:40:0x0066, B:31:0x0048, B:25:0x003a, B:12:0x0015), top: B:52:0x0005, inners: #0, #1, #3, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0040 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String b(android.content.Context r3, java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            if (r3 == 0) goto La
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch: java.lang.Exception -> L72
            goto Lb
        La:
            r3 = r1
        Lb:
            if (r3 == 0) goto L18
            r2 = 64
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r4, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L14 java.lang.Exception -> L72
            goto L19
        L14:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> L72
        L18:
            r3 = r1
        L19:
            if (r3 == 0) goto L72
            android.content.pm.Signature[] r3 = r3.signatures     // Catch: java.lang.Exception -> L72
            if (r3 == 0) goto L72
            int r4 = r3.length     // Catch: java.lang.Exception -> L72
            if (r4 <= 0) goto L72
            r4 = 0
            r2 = r3[r4]     // Catch: java.lang.Exception -> L72
            if (r2 == 0) goto L72
            r3 = r3[r4]     // Catch: java.lang.Exception -> L72
            byte[] r3 = r3.toByteArray()     // Catch: java.lang.Exception -> L72
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch: java.lang.Exception -> L72
            r4.<init>(r3)     // Catch: java.lang.Exception -> L72
            java.lang.String r3 = "X509"
            java.security.cert.CertificateFactory r3 = java.security.cert.CertificateFactory.getInstance(r3)     // Catch: java.security.cert.CertificateException -> L39 java.lang.Exception -> L72
            goto L3e
        L39:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> L72
            r3 = r1
        L3e:
            if (r3 == 0) goto L4b
            java.security.cert.Certificate r3 = r3.generateCertificate(r4)     // Catch: java.security.cert.CertificateException -> L47 java.lang.Exception -> L72
            java.security.cert.X509Certificate r3 = (java.security.cert.X509Certificate) r3     // Catch: java.security.cert.CertificateException -> L47 java.lang.Exception -> L72
            goto L4c
        L47:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> L72
        L4b:
            r3 = r1
        L4c:
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r5)     // Catch: java.security.cert.CertificateEncodingException -> L60 java.security.NoSuchAlgorithmException -> L65 java.lang.Exception -> L72
            if (r3 == 0) goto L69
            byte[] r3 = r3.getEncoded()     // Catch: java.security.cert.CertificateEncodingException -> L60 java.security.NoSuchAlgorithmException -> L65 java.lang.Exception -> L72
            byte[] r3 = r4.digest(r3)     // Catch: java.security.cert.CertificateEncodingException -> L60 java.security.NoSuchAlgorithmException -> L65 java.lang.Exception -> L72
            java.lang.String r3 = a(r3)     // Catch: java.security.cert.CertificateEncodingException -> L60 java.security.NoSuchAlgorithmException -> L65 java.lang.Exception -> L72
            r1 = r3
            goto L69
        L60:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> L72
            goto L69
        L65:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Exception -> L72
        L69:
            if (r1 == 0) goto L72
            java.lang.String r3 = ":"
            java.lang.String r3 = r1.replaceAll(r3, r0)     // Catch: java.lang.Exception -> L72
            return r3
        L72:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.utils.b.b(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    public static String b(String str) {
        if (str == null) {
            return "";
        }
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        for (byte b : str.getBytes()) {
            sb.append(charArray[(b & 240) >> 4]);
            sb.append(charArray[b & 15]);
        }
        return sb.toString().trim();
    }

    public static ArrayList b(Context context, String str) {
        ArrayList arrayList = null;
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                Object objA = i.a(jSONArray, i);
                if (objA instanceof JSONObject) {
                    JSONObject jSONObject = (JSONObject) objA;
                    if (Constants.JumpUrlConstants.SRC_TYPE_APP.equals(i.a(jSONObject, "type"))) {
                        JSONArray jSONArrayB = i.b(jSONObject, "package_info");
                        ArrayList arrayList2 = new ArrayList();
                        int i2 = 0;
                        while (jSONArrayB != null) {
                            try {
                                if (i2 >= jSONArrayB.length()) {
                                    break;
                                }
                                Object objA2 = i.a(jSONArrayB, i2);
                                if ((objA2 instanceof JSONObject) && a(context, (JSONObject) objA2)) {
                                    arrayList2.add(i.a((JSONObject) objA2, "appEnAbbr"));
                                }
                                i2++;
                            } catch (Exception unused) {
                                return arrayList2;
                            }
                        }
                        arrayList = arrayList2;
                    } else {
                        continue;
                    }
                }
            }
            return arrayList;
        } catch (Exception unused2) {
            return arrayList;
        }
    }

    public static String c(Context context, String str) {
        return b(context, str, "SHA1");
    }

    public static String c(String str) {
        return !TextUtils.isEmpty((CharSequence) a.get(str)) ? (String) a.get(str) : str;
    }

    public static String d(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            return packageInfo != null ? packageInfo.versionName : "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static final boolean d(String str) {
        return !Pattern.compile("[^0-9]+").matcher(str).find();
    }

    public static String e(String str) {
        if (str == null) {
            return "";
        }
        try {
            return Pattern.compile("[\":,\\[\\]{}]").matcher(str).replaceAll("").trim();
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean e(Context context, String str) {
        return a(context, str);
    }

    private static int f(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    private static boolean f(String str) {
        return Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE.equals(str) || "01".equals(str);
    }

    private static int g(String str) {
        try {
            return Integer.valueOf(str, 10).intValue();
        } catch (Exception unused) {
            return Integer.MAX_VALUE;
        }
    }
}
