package com.duowan.ark.preference.file;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class InnerFile implements InnerFileAction {
    private final Context mContext;
    private final String mKey;

    public InnerFile(Context context, String str) {
        if (context == null) {
            throw new NullPointerException("context can't be null");
        }
        if (str == null) {
            throw new NullPointerException("key can't be null");
        }
        this.mKey = str;
        this.mContext = context;
    }

    @Override // com.duowan.ark.preference.file.InnerFileAction
    public String get() throws Exception {
        return readFile();
    }

    private String readFile() throws IOException {
        FileInputStream fileInputStreamOpenFileInput = this.mContext.openFileInput(this.mKey);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(fileInputStreamOpenFileInput)));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                sb.append(line);
            } else {
                bufferedReader.close();
                fileInputStreamOpenFileInput.close();
                return sb.toString();
            }
        }
    }

    @Override // com.duowan.ark.preference.file.InnerFileAction
    public boolean save(String str) throws Exception {
        return writeFile(str);
    }

    private boolean writeFile(String str) throws IOException {
        FileOutputStream fileOutputStreamOpenFileOutput = this.mContext.openFileOutput(this.mKey, 0);
        PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(fileOutputStreamOpenFileOutput));
        printWriter.write(str);
        printWriter.flush();
        printWriter.close();
        fileOutputStreamOpenFileOutput.close();
        return true;
    }
}
