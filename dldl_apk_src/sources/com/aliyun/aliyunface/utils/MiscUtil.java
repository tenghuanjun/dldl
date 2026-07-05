package com.aliyun.aliyunface.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Window;
import android.view.WindowManager;
import com.alibaba.fastjson.JSON;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.UUID;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class MiscUtil {
    public static String readAssetsFile(Context context, String str) {
        try {
            StringBuilder sb = new StringBuilder();
            InputStream inputStreamOpen = context.getResources().getAssets().open(str);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (-1 != i) {
                    sb.append(new String(bArr, 0, i));
                } else {
                    return sb.toString();
                }
            }
        } catch (Exception unused) {
            return "";
        }
    }

    public static String base64Encode(String str) {
        try {
            return Base64.encodeToString(str.getBytes(), 2);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String base64Encode(byte[] bArr) {
        try {
            return Base64.encodeToString(bArr, 2);
        } catch (Exception unused) {
            return "";
        }
    }

    public static byte[] base64Decode(String str) {
        try {
            return Base64.decode(str, 2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String bitmapToBase64(Bitmap bitmap) throws Throwable {
        String strEncodeToString;
        ByteArrayOutputStream byteArrayOutputStream;
        strEncodeToString = "";
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (bitmap != null) {
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (IOException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byteArrayOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                strEncodeToString = byteArray != null ? Base64.encodeToString(byteArray, 2) : "";
                byteArrayOutputStream2 = byteArrayOutputStream;
            } catch (IOException e2) {
                e = e2;
                byteArrayOutputStream2 = byteArrayOutputStream;
                e.printStackTrace();
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream2 = byteArrayOutputStream;
                safeClose(byteArrayOutputStream2);
                throw th;
            }
        }
        safeClose(byteArrayOutputStream2);
        return strEncodeToString;
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (bitmap != null) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
                        byteArrayOutputStream.flush();
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        safeClose(byteArrayOutputStream);
                        return byteArray;
                    } catch (IOException e) {
                        e = e;
                        e.printStackTrace();
                        safeClose(byteArrayOutputStream);
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    safeClose(byteArrayOutputStream2);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                byteArrayOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                safeClose(byteArrayOutputStream2);
                throw th;
            }
        } else {
            safeClose((OutputStream) null);
        }
        return null;
    }

    public static Bitmap bytes2Bitmap(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void safeClose(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static <T> T json2Object(String str, Class<T> cls) {
        try {
            return (T) JSON.parseObject(str, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String object2Json(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String byteToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString().trim();
    }

    public static String getMd5(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            return byteToHex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getFileMd5(String str) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    safeClose((InputStream) null);
                    return "";
                }
                int length = (int) file.length();
                byte[] bArr = new byte[length];
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    if (fileInputStream2.read(bArr, 0, length) != length) {
                        safeClose(fileInputStream2);
                        return "";
                    }
                    String md5 = getMd5(bArr);
                    safeClose(fileInputStream2);
                    return md5;
                } catch (Exception e) {
                    e = e;
                    fileInputStream = fileInputStream2;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    safeClose(fileInputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            e.printStackTrace();
            safeClose(fileInputStream);
            return "";
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void setActivityScreenBrightness(Activity activity, float f) {
        Window window = activity.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.screenBrightness = f;
            window.setAttributes(attributes);
        }
    }

    public static byte[] readFileContent(String str) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr = new byte[1024];
                fileInputStream = new FileInputStream(str);
                while (true) {
                    try {
                        int i = fileInputStream.read(bArr, 0, 1024);
                        if (-1 != i) {
                            byteArrayOutputStream.write(bArr, 0, i);
                        } else {
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            safeClose(fileInputStream);
                            safeClose(byteArrayOutputStream);
                            return byteArray;
                        }
                    } catch (Exception unused) {
                        safeClose(fileInputStream);
                        safeClose(byteArrayOutputStream);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        safeClose(fileInputStream2);
                        safeClose(byteArrayOutputStream);
                        throw th;
                    }
                }
            } catch (Exception unused2) {
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused3) {
            byteArrayOutputStream = null;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
        }
    }

    public static String genOssFileName(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_");
        sb.append(str2);
        sb.append("_");
        sb.append(UUID.randomUUID().toString().substring(r1.length() - 4));
        sb.append(".");
        sb.append(str3);
        return sb.toString();
    }

    public static Bitmap cropBitmap(Bitmap bitmap, RectF rectF) {
        try {
            float width = bitmap.getWidth();
            float height = bitmap.getHeight();
            return Bitmap.createBitmap(bitmap, (int) (rectF.left * width), (int) (rectF.top * height), (int) (width * (rectF.right - rectF.left)), (int) (height * (rectF.bottom - rectF.top)), (Matrix) null, false);
        } catch (Exception e) {
            e.printStackTrace();
            return bitmap;
        }
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return bitmap;
        }
        try {
            return Bitmap.createScaledBitmap(bitmap, i, (int) ((bitmap.getHeight() / bitmap.getWidth()) * i), true);
        } catch (Exception e) {
            e.printStackTrace();
            return bitmap;
        }
    }

    public static boolean isIdCardNoValid(String str) {
        return !TextUtils.isEmpty(str) && str.length() == 18;
    }

    public static boolean isIdCardNameValid(String str) {
        return !TextUtils.isEmpty(str) && str.length() >= 1 && str.length() <= 100;
    }
}
