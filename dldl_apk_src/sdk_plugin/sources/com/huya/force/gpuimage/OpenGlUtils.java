package com.huya.force.gpuimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.huya.force.log.ForceLog;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class OpenGlUtils {
    public static final int NO_FRAMEBUFFER = -1;
    public static final int NO_TEXTURE = -1;
    private static final String TAG = "OpenGlUtils";

    public static int loadTexture(Bitmap bitmap, int i) {
        return loadTexture(bitmap, i, true);
    }

    public static int loadTexture(Bitmap bitmap, int i, boolean z) {
        int[] iArr = new int[1];
        if (i == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLUtils.texImage2D(3553, 0, bitmap, 0);
        } else {
            GLES20.glBindTexture(3553, i);
            GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
            iArr[0] = i;
        }
        if (z) {
            bitmap.recycle();
        }
        return iArr[0];
    }

    public static int loadTexture(IntBuffer intBuffer, int i, int i2, int i3) {
        int[] iArr = new int[1];
        if (i3 == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, intBuffer);
        } else {
            GLES20.glBindTexture(3553, i3);
            GLES20.glTexSubImage2D(3553, 0, 0, 0, i, i2, 6408, 5121, intBuffer);
            iArr[0] = i3;
        }
        return iArr[0];
    }

    public static int loadTexture(ByteBuffer byteBuffer, int i, int i2, int i3) {
        int[] iArr = new int[1];
        if (i3 == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, byteBuffer);
        } else {
            GLES20.glBindTexture(3553, i3);
            GLES20.glTexSubImage2D(3553, 0, 0, 0, i, i2, 6408, 5121, byteBuffer);
            iArr[0] = i3;
        }
        return iArr[0];
    }

    public static int loadTextureAsBitmap(IntBuffer intBuffer, Camera.Size size, int i) {
        return loadTexture(Bitmap.createBitmap(intBuffer.array(), size.width, size.height, Bitmap.Config.ARGB_8888), i);
    }

    public static int loadTexture(Context context, String str) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        if (iArr[0] != 0) {
            Bitmap imageFromAssetsFile = getImageFromAssetsFile(context, str);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            GLUtils.texImage2D(3553, 0, imageFromAssetsFile, 0);
            imageFromAssetsFile.recycle();
        }
        if (iArr[0] == 0) {
            throw new RuntimeException("Error loading texture.");
        }
        return iArr[0];
    }

    public static Bitmap getImageFromAssetsFile(Context context, String str) {
        Bitmap bitmapDecodeStream = null;
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open(str);
            bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
            inputStreamOpen.close();
            return bitmapDecodeStream;
        } catch (IOException e) {
            e.printStackTrace();
            return bitmapDecodeStream;
        }
    }

    public static int loadShader(String str, int i) {
        int[] iArr = new int[1];
        int iGlCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return iGlCreateShader;
        }
        Log.e("Load Shader Failed", "Compilation\n" + GLES20.glGetShaderInfoLog(iGlCreateShader));
        return 0;
    }

    public static int loadProgram(String str, String str2) {
        int[] iArr = new int[1];
        int iLoadShader = loadShader(str, 35633);
        if (iLoadShader == 0) {
            Log.e("Load Program", "Vertex Shader Failed");
            return 0;
        }
        int iLoadShader2 = loadShader(str2, 35632);
        if (iLoadShader2 == 0) {
            Log.e("Load Program", "Fragment Shader Failed");
            return 0;
        }
        int iGlCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(iGlCreateProgram, iLoadShader);
        GLES20.glAttachShader(iGlCreateProgram, iLoadShader2);
        GLES20.glLinkProgram(iGlCreateProgram);
        GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
        if (iArr[0] <= 0) {
            Log.e("Load Program", "Linking Failed");
            return 0;
        }
        GLES20.glDeleteShader(iLoadShader);
        GLES20.glDeleteShader(iLoadShader2);
        return iGlCreateProgram;
    }

    public static float rnd(float f, float f2) {
        return f + ((f2 - f) * ((float) Math.random()));
    }

    public static void checkGlError(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            Log.e("opengl util", str + ": glError 0x" + Integer.toHexString(iGlGetError));
        }
    }

    public static String readShaderFromRawResource(Context context, int i) {
        if (context == null) {
            ForceLog.error(TAG, "readShaderFromRawResource, context == null");
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(i)));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                    sb.append('\n');
                } else {
                    return sb.toString();
                }
            } catch (IOException unused) {
                return null;
            }
        }
    }

    public static String loadStringFromAsset(Context context, String str) {
        String string = "";
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            string = readString(inputStreamOpen);
            inputStreamOpen.close();
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return string;
        }
    }

    public static String readString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                byteArrayOutputStream.close();
                inputStream.close();
                return byteArrayOutputStream.toString();
            }
        }
    }

    public static int loadTextureFromAsset(Context context, String str) {
        int[] iArr = new int[1];
        Bitmap bitmapLoadBitmapFromAsset = loadBitmapFromAsset(context, str);
        if (bitmapLoadBitmapFromAsset == null) {
            ForceLog.error(TAG, "loadTextureFromAsset bitmap == null");
            return -1;
        }
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(3553, iArr[0]);
        GLUtils.texImage2D(3553, 0, bitmapLoadBitmapFromAsset, 0);
        bitmapLoadBitmapFromAsset.recycle();
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        return iArr[0];
    }

    public static Bitmap loadBitmapFromAsset(Context context, String str) {
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = context.getAssets().open(str);
        } catch (IOException unused) {
            inputStreamOpen = null;
        }
        if (inputStreamOpen == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeStream(inputStreamOpen, null, options);
    }
}
