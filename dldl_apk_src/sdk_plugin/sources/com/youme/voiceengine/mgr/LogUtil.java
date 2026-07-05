package com.youme.voiceengine.mgr;

import com.snail.antifake.deviceid.ShellAdbUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class LogUtil {
    public static void SaveLogcat(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -v time -d ").getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str)));
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    bufferedWriter.write(line);
                    bufferedWriter.write(ShellAdbUtils.COMMAND_LINE_END);
                } else {
                    bufferedWriter.close();
                    return;
                }
            }
        } catch (Exception unused) {
        }
    }
}
