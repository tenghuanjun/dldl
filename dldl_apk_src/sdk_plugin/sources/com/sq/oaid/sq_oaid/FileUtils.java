package com.sq.oaid.sq_oaid;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FileUtils {
    public static void save(Context context, String fileName, String content) {
        File file = new File(context.getFilesDir() + File.separator + fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput(fileName, 0);
            fileOutputStreamOpenFileOutput.write(content.getBytes());
            fileOutputStreamOpenFileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String read(Context context, String fileName) {
        try {
            return readInStream(context.openFileInput(fileName));
        } catch (Exception unused) {
            return "";
        }
    }

    private static String readInStream(FileInputStream inStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[512];
            while (true) {
                int i = inStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    byteArrayOutputStream.close();
                    inStream.close();
                    return byteArrayOutputStream.toString();
                }
            }
        } catch (IOException unused) {
            return "";
        }
    }
}
