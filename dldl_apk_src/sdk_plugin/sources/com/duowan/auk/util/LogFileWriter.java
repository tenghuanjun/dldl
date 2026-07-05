package com.duowan.auk.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class LogFileWriter {
    private static Map<String, Buffer> mBuffer = new HashMap();
    protected static int mBufferCount = 15;
    private String mFileName;

    private static class Buffer {
        public final StringBuilder buffer = new StringBuilder();
        public int current;

        Buffer() {
            this.current = 0;
            this.current = 0;
        }
    }

    public LogFileWriter(String str) {
        if (str == null) {
            throw new NullPointerException("file may not be null");
        }
        this.mFileName = str;
        if (mBuffer.containsKey(str)) {
            return;
        }
        mBuffer.put(str, new Buffer());
    }

    public void write(StringBuffer stringBuffer) throws IOException {
        if (stringBuffer == null || stringBuffer.length() == 0) {
            return;
        }
        Buffer buffer = mBuffer.get(this.mFileName);
        if (buffer == null) {
            throw new NullPointerException("Buffer may not be null !");
        }
        buffer.buffer.append(stringBuffer);
        buffer.current++;
        if (buffer.current >= mBufferCount) {
            flush();
        }
    }

    public void write(String str) throws IOException {
        if (str == null || str.isEmpty()) {
            return;
        }
        Buffer buffer = mBuffer.get(this.mFileName);
        if (buffer == null) {
            throw new NullPointerException("Buffer may not be null !");
        }
        buffer.buffer.append(str);
        buffer.current++;
        if (buffer.current >= mBufferCount) {
            flush();
        }
    }

    public void flush() throws IOException {
        flush(this.mFileName);
    }

    private static void flush(String str) throws IOException {
        Buffer buffer = mBuffer.get(str);
        if (buffer == null) {
            throw new NullPointerException("Buffer may not be null !");
        }
        if (buffer.current == 0 || buffer.buffer.length() == 0) {
            return;
        }
        synchronized (buffer) {
            FileWriter fileWriter = new FileWriter(new File(str), true);
            fileWriter.write(buffer.buffer.toString());
            fileWriter.flush();
            fileWriter.close();
            buffer.current = 0;
            buffer.buffer.delete(0, buffer.buffer.length());
        }
    }

    public static void flushAll() throws IOException {
        Iterator<String> it = mBuffer.keySet().iterator();
        while (it.hasNext()) {
            flush(it.next());
        }
    }
}
