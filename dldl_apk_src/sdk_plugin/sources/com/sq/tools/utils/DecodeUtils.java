package com.sq.tools.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.sq.tools.Logger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class DecodeUtils {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String MD5(String str) {
        return MD5(str, StandardCharsets.UTF_8);
    }

    public static String MD5(String str, Charset charset) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes(charset), 0, str.length());
            return String.format("%032x", new BigInteger(1, messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            Logger.error("MD5 calculate exception", e);
            return "";
        }
    }

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public static byte[] hexToBytes(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String base64Encoding(String str, Charset charset) {
        return new String(Base64.encode(str.getBytes(charset), 2), charset);
    }

    public static byte[] base64Encoding(byte[] bArr) {
        return Base64.encode(bArr, 2);
    }

    public static String base64Decoding(String str, Charset charset) {
        return new String(Base64.decode(str.getBytes(charset), 2), charset);
    }

    public static byte[] base64Decoding(byte[] bArr) {
        return Base64.decode(bArr, 2);
    }

    public static String urlEncode(String str, Charset charset) {
        try {
            return URLEncoder.encode(str, charset.name());
        } catch (UnsupportedEncodingException e) {
            Logger.warning("URL encode exception", e);
            return "";
        }
    }

    public static String urlDecode(String str, Charset charset) {
        try {
            return URLDecoder.decode(str, charset.name());
        } catch (UnsupportedEncodingException e) {
            Logger.warning("URL decode exception", e);
            return "";
        }
    }

    public static byte[] gzip(String str, Charset charset) throws Throwable {
        GZIPOutputStream gZIPOutputStream;
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        GZIPOutputStream gZIPOutputStream2 = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                try {
                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e) {
                e = e;
            }
            try {
                gZIPOutputStream.write(str.getBytes(charset));
                gZIPOutputStream.flush();
                gZIPOutputStream.close();
            } catch (IOException e2) {
                e = e2;
                gZIPOutputStream2 = gZIPOutputStream;
                Logger.error("GZIP, compress data failed with exception, data: %s", str, e);
                if (gZIPOutputStream2 != null) {
                    gZIPOutputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream2 = gZIPOutputStream;
                if (gZIPOutputStream2 != null) {
                    try {
                        gZIPOutputStream2.close();
                    } catch (IOException unused) {
                    }
                }
                throw th;
            }
        } catch (IOException unused2) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] unzip(byte[] bArr) throws Throwable {
        Inflater inflater;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            try {
                inflater = new Inflater();
                inflater.setInput(bArr, 0, bArr.length);
                byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            } catch (Throwable th) {
                th = th;
            }
        } catch (DataFormatException e) {
            e = e;
        }
        try {
            byte[] bArr2 = new byte[bArr.length];
            while (!inflater.finished()) {
                byteArrayOutputStream.write(bArr2, 0, inflater.inflate(bArr2));
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                Logger.warning("Exception happen when trying to close %s, this may cause a memory leak", "ByteArrayOutputStream", e2);
            }
            return byteArray;
        } catch (DataFormatException e3) {
            e = e3;
            byteArrayOutputStream2 = byteArrayOutputStream;
            Logger.warning("Uncompress data Exception", e);
            byte[] bArr3 = new byte[0];
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException e4) {
                    Logger.warning("Exception happen when trying to close %s, this may cause a memory leak", "ByteArrayOutputStream", e4);
                }
            }
            return bArr3;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException e5) {
                    Logger.warning("Exception happen when trying to close %s, this may cause a memory leak", "ByteArrayOutputStream", e5);
                }
            }
            throw th;
        }
    }

    public static JSONObject mergeTwoJson(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null) {
            return jSONObject2;
        }
        if (jSONObject2 == null) {
            return jSONObject;
        }
        String string = jSONObject.toString();
        try {
            return new JSONObject(string.substring(0, string.length() - 1) + "," + jSONObject2.toString().substring(1));
        } catch (JSONException e) {
            Logger.warning("Merge two Json Object Exception", e);
            return null;
        }
    }
}
