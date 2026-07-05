package com.huya.mtp.utils;

import com.huya.mtp.api.MTPApi;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ShellCmd {
    public static final String TAG = "ShellCmd";

    public static String getSystemProperty(String str) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            try {
                try {
                    String line = bufferedReader.readLine();
                    bufferedReader.close();
                    IOUtils.close(bufferedReader);
                    return line;
                } catch (IOException e) {
                    e = e;
                    MTPApi.LOGGER.error("ShellCmd Unable to read sysprop " + str, e);
                    IOUtils.close(bufferedReader);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                bufferedReader2 = bufferedReader;
                IOUtils.close(bufferedReader2);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
            IOUtils.close(bufferedReader2);
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0057: MOVE (r1 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:20:0x0057 */
    public static ArrayList<String> docmd(String str) throws Throwable {
        BufferedReader bufferedReader;
        Closeable closeable;
        ArrayList<String> arrayList = new ArrayList<>();
        Closeable closeable2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(str).getInputStream()), 1024);
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line != null) {
                            arrayList.add(line);
                        } else {
                            IOUtils.close(bufferedReader);
                            return arrayList;
                        }
                    } catch (IOException e) {
                        e = e;
                        MTPApi.LOGGER.error("ShellCmd Unable to do cmd: " + str, e);
                        IOUtils.close(bufferedReader);
                        return null;
                    }
                }
            } catch (Throwable th) {
                th = th;
                closeable2 = closeable;
                IOUtils.close(closeable2);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
            IOUtils.close(closeable2);
            throw th;
        }
    }
}
