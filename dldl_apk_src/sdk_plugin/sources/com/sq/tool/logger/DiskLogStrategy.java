package com.sq.tool.logger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DiskLogStrategy implements LogStrategy {
    private final Handler handler;

    public DiskLogStrategy(Handler handler) {
        this.handler = (Handler) Utils.checkNotNull(handler);
    }

    @Override // com.sq.tool.logger.LogStrategy
    public void log(int level, String tag, String message) {
        Utils.checkNotNull(message);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(level, message));
    }

    static class WriteHandler extends Handler {
        private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
        private final String folder;
        private final int maxFileSize;
        private final String processName;

        WriteHandler(Looper looper, String folder, int maxFileSize) {
            super((Looper) Utils.checkNotNull(looper));
            this.folder = (String) Utils.checkNotNull(folder);
            this.maxFileSize = maxFileSize;
            this.processName = Utils.wrapProcessName();
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            String str;
            FileWriter fileWriter;
            String str2 = (String) msg.obj;
            if (TextUtils.isEmpty(this.processName)) {
                str = "";
            } else {
                str = "_" + this.processName;
            }
            FileWriter fileWriter2 = null;
            try {
                fileWriter = new FileWriter(getLogFile(this.folder, "log_" + SIMPLE_DATE_FORMAT.format(new Date()) + str), true);
            } catch (IOException unused) {
            }
            try {
                writeLog(fileWriter, str2);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException unused2) {
                fileWriter2 = fileWriter;
                if (fileWriter2 != null) {
                    try {
                        fileWriter2.flush();
                        fileWriter2.close();
                    } catch (IOException unused3) {
                    }
                }
            }
        }

        private void writeLog(FileWriter fileWriter, String content) throws IOException {
            Utils.checkNotNull(fileWriter);
            Utils.checkNotNull(content);
            fileWriter.append((CharSequence) content);
        }

        private File getLogFile(String folderName, String fileName) {
            File file;
            Utils.checkNotNull(folderName);
            Utils.checkNotNull(fileName);
            File file2 = new File(folderName);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = null;
            File file4 = new File(file2, String.format("%s_%s.txt", fileName, 0));
            int i = 0;
            while (true) {
                File file5 = file4;
                file = file3;
                file3 = file5;
                if (!file3.exists()) {
                    break;
                }
                i++;
                file4 = new File(file2, String.format("%s_%s.txt", fileName, Integer.valueOf(i)));
            }
            return (file == null || file.length() >= ((long) this.maxFileSize)) ? file3 : file;
        }
    }
}
