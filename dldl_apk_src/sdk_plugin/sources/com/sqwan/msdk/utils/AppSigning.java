package com.sqwan.msdk.utils;

import android.content.Context;
import android.content.pm.Signature;
import com.sqwan.common.util.LogUtil;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AppSigning {
    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA1";
    public static final String SHA256 = "SHA256";
    private static HashMap<String, ArrayList<String>> mSignMap = new HashMap<>();

    public static ArrayList<String> getSignInfo(Context context, String str) {
        ArrayList<String> arrayList = null;
        if (context != null && str != null) {
            String packageName = context.getPackageName();
            if (packageName == null) {
                return null;
            }
            if (mSignMap.get(str) != null) {
                return mSignMap.get(str);
            }
            arrayList = new ArrayList<>();
            try {
                for (Signature signature : getSignatures(context, packageName)) {
                    String signatureByteString = "error!";
                    byte b = -1;
                    int iHashCode = str.hashCode();
                    if (iHashCode != -1850268089) {
                        if (iHashCode != 76158) {
                            if (iHashCode == 2543909 && str.equals(SHA1)) {
                                b = 1;
                            }
                        } else if (str.equals("MD5")) {
                            b = 0;
                        }
                    } else if (str.equals(SHA256)) {
                        b = 2;
                    }
                    if (b == 0) {
                        signatureByteString = getSignatureByteString(signature, "MD5");
                    } else if (b == 1) {
                        signatureByteString = getSignatureByteString(signature, SHA1);
                    } else if (b == 2) {
                        signatureByteString = getSignatureByteString(signature, SHA256);
                    }
                    arrayList.add(signatureByteString);
                }
            } catch (Exception e) {
                LogUtil.e(e.toString());
            }
            mSignMap.put(str, arrayList);
        }
        return arrayList;
    }

    public static String getSha1(Context context) {
        ArrayList<String> signInfo = getSignInfo(context, SHA1);
        return (signInfo == null || signInfo.size() == 0) ? "" : signInfo.get(0);
    }

    public static String getMD5(Context context) {
        ArrayList<String> signInfo = getSignInfo(context, "MD5");
        return (signInfo == null || signInfo.size() == 0) ? "" : signInfo.get(0);
    }

    public static String getSHA256(Context context) {
        ArrayList<String> signInfo = getSignInfo(context, SHA256);
        return (signInfo == null || signInfo.size() == 0) ? "" : signInfo.get(0);
    }

    private static Signature[] getSignatures(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 64).signatures;
        } catch (Exception e) {
            LogUtil.e(e.toString());
            return null;
        }
    }

    private static String getSignatureString(Signature signature, String str) {
        byte[] byteArray = signature.toByteArray();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            if (messageDigest == null) {
                return "error!";
            }
            byte[] bArrDigest = messageDigest.digest(byteArray);
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            LogUtil.e(e.toString());
            return "error!";
        }
    }

    private static String getSignatureByteString(Signature signature, String str) {
        byte[] byteArray = signature.toByteArray();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            if (messageDigest == null) {
                return "error!";
            }
            byte[] bArrDigest = messageDigest.digest(byteArray);
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3).toUpperCase());
                sb.append(":");
            }
            return sb.substring(0, sb.length() - 1).toString();
        } catch (Exception e) {
            LogUtil.e(e.toString());
            return "error!";
        }
    }
}
