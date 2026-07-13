package com.lzy.okgo.utils;

import android.os.StatFs;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class IOUtils {
    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
        }
    }

    public static void flushQuietly(Flushable flushable) {
        if (flushable == null) {
            return;
        }
        try {
            flushable.flush();
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
        }
    }

    public static InputStream toInputStream(CharSequence charSequence) {
        return new ByteArrayInputStream(charSequence.toString().getBytes());
    }

    public static InputStream toInputStream(CharSequence charSequence, String str) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(charSequence.toString().getBytes(str));
    }

    public static BufferedInputStream toBufferedInputStream(InputStream inputStream) {
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
    }

    public static BufferedOutputStream toBufferedOutputStream(OutputStream outputStream) {
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream);
    }

    public static BufferedReader toBufferedReader(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    public static BufferedWriter toBufferedWriter(Writer writer) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer);
    }

    public static String toString(InputStream inputStream) throws IOException {
        return new String(toByteArray(inputStream));
    }

    public static String toString(InputStream inputStream, String str) throws IOException {
        return new String(toByteArray(inputStream), str);
    }

    public static String toString(Reader reader) throws IOException {
        return new String(toByteArray(reader));
    }

    public static String toString(Reader reader, String str) throws IOException {
        return new String(toByteArray(reader), str);
    }

    public static String toString(byte[] bArr) {
        return new String(bArr);
    }

    public static String toString(byte[] bArr, String str) {
        try {
            return new String(bArr, str);
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr);
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0034: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:22:0x0034 */
    public static byte[] toByteArray(Object obj) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
                closeable2 = closeable;
            }
        } catch (IOException e) {
            e = e;
            byteArrayOutputStream = null;
            objectOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                closeQuietly(objectOutputStream);
                closeQuietly(byteArrayOutputStream);
                return byteArray;
            } catch (IOException e2) {
                e = e2;
                OkLogger.printStackTrace(e);
                closeQuietly(objectOutputStream);
                closeQuietly(byteArrayOutputStream);
                return null;
            }
        } catch (IOException e3) {
            e = e3;
            objectOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            closeQuietly(closeable2);
            closeQuietly(byteArrayOutputStream);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v7 */
    public static Object toObject(byte[] bArr) throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        try {
            if (bArr == 0) {
                return null;
            }
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArr);
                try {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    try {
                        Object object = objectInputStream.readObject();
                        closeQuietly(objectInputStream);
                        closeQuietly(byteArrayInputStream);
                        return object;
                    } catch (Exception e) {
                        e = e;
                        OkLogger.printStackTrace(e);
                        closeQuietly(objectInputStream);
                        closeQuietly(byteArrayInputStream);
                        return null;
                    }
                } catch (Exception e2) {
                    e = e2;
                    objectInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    bArr = 0;
                    closeQuietly(bArr);
                    closeQuietly(byteArrayInputStream);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                objectInputStream = null;
                byteArrayInputStream = null;
            } catch (Throwable th2) {
                byteArrayInputStream = null;
                th = th2;
                bArr = 0;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static byte[] toByteArray(CharSequence charSequence) {
        if (charSequence == null) {
            return new byte[0];
        }
        return charSequence.toString().getBytes();
    }

    public static byte[] toByteArray(CharSequence charSequence, String str) throws UnsupportedEncodingException {
        if (charSequence == null) {
            return new byte[0];
        }
        return charSequence.toString().getBytes(str);
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        write(inputStream, byteArrayOutputStream);
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] toByteArray(Reader reader) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        write(reader, byteArrayOutputStream);
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] toByteArray(Reader reader, String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        write(reader, byteArrayOutputStream, str);
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static char[] toCharArray(CharSequence charSequence) throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        write(charSequence, charArrayWriter);
        return charArrayWriter.toCharArray();
    }

    public static char[] toCharArray(InputStream inputStream) throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        write(inputStream, charArrayWriter);
        return charArrayWriter.toCharArray();
    }

    public static char[] toCharArray(InputStream inputStream, String str) throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        write(inputStream, charArrayWriter, str);
        return charArrayWriter.toCharArray();
    }

    public static char[] toCharArray(Reader reader) throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        write(reader, charArrayWriter);
        return charArrayWriter.toCharArray();
    }

    public static List<String> readLines(InputStream inputStream, String str) throws IOException {
        return readLines(new InputStreamReader(inputStream, str));
    }

    public static List<String> readLines(InputStream inputStream) throws IOException {
        return readLines(new InputStreamReader(inputStream));
    }

    public static List<String> readLines(Reader reader) throws IOException {
        BufferedReader bufferedReader = toBufferedReader(reader);
        ArrayList arrayList = new ArrayList();
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            arrayList.add(line);
        }
        return arrayList;
    }

    public static void write(byte[] bArr, OutputStream outputStream) throws IOException {
        if (bArr != null) {
            outputStream.write(bArr);
        }
    }

    public static void write(byte[] bArr, Writer writer) throws IOException {
        if (bArr != null) {
            writer.write(new String(bArr));
        }
    }

    public static void write(byte[] bArr, Writer writer, String str) throws IOException {
        if (bArr != null) {
            writer.write(new String(bArr, str));
        }
    }

    public static void write(char[] cArr, Writer writer) throws IOException {
        if (cArr != null) {
            writer.write(cArr);
        }
    }

    public static void write(char[] cArr, OutputStream outputStream) throws IOException {
        if (cArr != null) {
            outputStream.write(new String(cArr).getBytes());
        }
    }

    public static void write(char[] cArr, OutputStream outputStream, String str) throws IOException {
        if (cArr != null) {
            outputStream.write(new String(cArr).getBytes(str));
        }
    }

    public static void write(CharSequence charSequence, Writer writer) throws IOException {
        if (charSequence != null) {
            writer.write(charSequence.toString());
        }
    }

    public static void write(CharSequence charSequence, OutputStream outputStream) throws IOException {
        if (charSequence != null) {
            outputStream.write(charSequence.toString().getBytes());
        }
    }

    public static void write(CharSequence charSequence, OutputStream outputStream, String str) throws IOException {
        if (charSequence != null) {
            outputStream.write(charSequence.toString().getBytes(str));
        }
    }

    public static void write(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    public static void write(Reader reader, OutputStream outputStream) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        write(reader, outputStreamWriter);
        outputStreamWriter.flush();
    }

    public static void write(InputStream inputStream, Writer writer) throws IOException {
        write(new InputStreamReader(inputStream), writer);
    }

    public static void write(Reader reader, OutputStream outputStream, String str) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, str);
        write(reader, outputStreamWriter);
        outputStreamWriter.flush();
    }

    public static void write(InputStream inputStream, OutputStream outputStream, String str) throws IOException {
        write(new InputStreamReader(inputStream, str), outputStream);
    }

    public static void write(InputStream inputStream, Writer writer, String str) throws IOException {
        write(new InputStreamReader(inputStream, str), writer);
    }

    public static void write(Reader reader, Writer writer) throws IOException {
        char[] cArr = new char[4096];
        while (true) {
            int i = reader.read(cArr);
            if (-1 == i) {
                return;
            } else {
                writer.write(cArr, 0, i);
            }
        }
    }

    public static boolean contentEquals(InputStream inputStream, InputStream inputStream2) throws IOException {
        BufferedInputStream bufferedInputStream = toBufferedInputStream(inputStream);
        BufferedInputStream bufferedInputStream2 = toBufferedInputStream(inputStream2);
        for (int i = bufferedInputStream.read(); -1 != i; i = bufferedInputStream.read()) {
            if (i != bufferedInputStream2.read()) {
                return false;
            }
        }
        return bufferedInputStream2.read() == -1;
    }

    public static boolean contentEquals(Reader reader, Reader reader2) throws IOException {
        BufferedReader bufferedReader = toBufferedReader(reader);
        BufferedReader bufferedReader2 = toBufferedReader(reader2);
        for (int i = bufferedReader.read(); -1 != i; i = bufferedReader.read()) {
            if (i != bufferedReader2.read()) {
                return false;
            }
        }
        return bufferedReader2.read() == -1;
    }

    public static boolean contentEqualsIgnoreEOL(Reader reader, Reader reader2) throws IOException {
        BufferedReader bufferedReader = toBufferedReader(reader);
        BufferedReader bufferedReader2 = toBufferedReader(reader2);
        String line = bufferedReader.readLine();
        String line2 = bufferedReader2.readLine();
        while (line != null && line2 != null && line.equals(line2)) {
            line = bufferedReader.readLine();
            line2 = bufferedReader2.readLine();
        }
        return line != null && (line2 == null || line.equals(line2));
    }

    public static long getDirSize(String str) {
        try {
            return getStatFsSize(new StatFs(str), "getBlockSizeLong", "getAvailableBlocksLong");
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
            return 0L;
        }
    }

    private static long getStatFsSize(StatFs statFs, String str, String str2) {
        try {
            Method method = statFs.getClass().getMethod(str, null);
            method.setAccessible(true);
            Method method2 = statFs.getClass().getMethod(str2, null);
            method2.setAccessible(true);
            return ((Long) method.invoke(statFs, null)).longValue() * ((Long) method2.invoke(statFs, null)).longValue();
        } catch (Throwable th) {
            OkLogger.printStackTrace(th);
            return 0L;
        }
    }

    public static boolean canWrite(String str) {
        return new File(str).canWrite();
    }

    public static boolean canRead(String str) {
        return new File(str).canRead();
    }

    public static boolean createFolder(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return createFolder(new File(str));
    }

    public static boolean createFolder(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            }
            file.delete();
        }
        return file.mkdirs();
    }

    public static boolean createNewFolder(String str) {
        return delFileOrFolder(str) && createFolder(str);
    }

    public static boolean createNewFolder(File file) {
        return delFileOrFolder(file) && createFolder(file);
    }

    public static boolean createFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return createFile(new File(str));
    }

    public static boolean createFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                return true;
            }
            delFileOrFolder(file);
        }
        try {
            return file.createNewFile();
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean createNewFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return createNewFile(new File(str));
    }

    public static boolean createNewFile(File file) {
        if (file.exists()) {
            delFileOrFolder(file);
        }
        try {
            return file.createNewFile();
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean delFileOrFolder(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return delFileOrFolder(new File(str));
    }

    public static boolean delFileOrFolder(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isFile()) {
            file.delete();
            return true;
        }
        if (!file.isDirectory()) {
            return true;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                delFileOrFolder(file2);
            }
        }
        file.delete();
        return true;
    }
}
