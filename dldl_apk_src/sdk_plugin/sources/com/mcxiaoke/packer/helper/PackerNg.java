package com.mcxiaoke.packer.helper;

import com.sqwan.bugless.util.FileUtil;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class PackerNg {
    private static final String EMPTY_STRING = "";
    private static final String INTRO_TEXT = "\nAttention: if your app using Android gradle plugin 2.2.0 or later, be sure to install one of the generated Apks to device or emulator, to ensure the apk can be installed without errors. More details please go to github https://github.com/mcxiaoke/packer-ng-plugin .\n";
    private static final String TAG = PackerNg.class.getSimpleName();
    private static final String USAGE_TEXT = "Usage: java -jar PackerNg-x.x.x.jar apkFile marketFile [outputDir] ";
    private static String sCachedMarket;

    public static String getMarket(Object obj) {
        return getMarket(obj, "");
    }

    public static synchronized String getMarket(Object obj, String str) {
        if (sCachedMarket == null) {
            sCachedMarket = getMarketInternal(obj, str).market;
        }
        return sCachedMarket;
    }

    public static MarketInfo getMarketInfo(Object obj) {
        return getMarketInfo(obj, "");
    }

    public static synchronized MarketInfo getMarketInfo(Object obj, String str) {
        return getMarketInternal(obj, str);
    }

    private static MarketInfo getMarketInternal(Object obj, String str) throws IOException {
        String market = null;
        try {
            market = Helper.readMarket(new File(Helper.getSourceDir(obj)));
            e = null;
        } catch (Exception e) {
            e = e;
        }
        if (market != null) {
            str = market;
        }
        return new MarketInfo(str, e);
    }

    public static final class MarketInfo {
        public final Exception error;
        public final String market;

        public MarketInfo(String str, Exception exc) {
            this.market = str;
            this.error = exc;
        }

        public String toString() {
            return "MarketInfo{market='" + this.market + "', error=" + this.error + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public static class MarketExistsException extends IOException {
        public MarketExistsException() {
        }

        public MarketExistsException(String str) {
            super(str);
        }
    }

    public static class MarketNotFoundException extends IOException {
        public MarketNotFoundException() {
        }

        public MarketNotFoundException(String str) {
            super(str);
        }
    }

    public static class Helper {
        static final byte[] MAGIC = {33, 90, 88, 75, 33};
        static final int SHORT_LENGTH = 2;
        static final String UTF_8 = "UTF-8";
        static final int ZIP_COMMENT_MAX_LENGTH = 65535;

        /* JADX INFO: Access modifiers changed from: private */
        public static String getSourceDir(Object obj) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
            Class<?> cls = Class.forName("android.content.Context");
            Class<?> cls2 = Class.forName("android.content.pm.ApplicationInfo");
            Object objInvoke = cls.getMethod("getApplicationInfo", new Class[0]).invoke(obj, new Object[0]);
            String str = (String) cls2.getField("sourceDir").get(objInvoke);
            if (str == null) {
                str = (String) cls2.getField("publicSourceDir").get(objInvoke);
            }
            return str == null ? (String) cls.getMethod("getPackageCodePath", new Class[0]).invoke(obj, new Object[0]) : str;
        }

        private static boolean isMagicMatched(byte[] bArr) {
            if (bArr.length != MAGIC.length) {
                return false;
            }
            int i = 0;
            while (true) {
                byte[] bArr2 = MAGIC;
                if (i >= bArr2.length) {
                    return true;
                }
                if (bArr[i] != bArr2[i]) {
                    return false;
                }
                i++;
            }
        }

        private static void writeBytes(byte[] bArr, DataOutput dataOutput) throws IOException {
            dataOutput.write(bArr);
        }

        private static void writeShort(int i, DataOutput dataOutput) throws IOException {
            ByteBuffer byteBufferOrder = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
            byteBufferOrder.putShort((short) i);
            dataOutput.write(byteBufferOrder.array());
        }

        private static short readShort(DataInput dataInput) throws IOException {
            byte[] bArr = new byte[2];
            dataInput.readFully(bArr);
            return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getShort(0);
        }

        public static void writeZipComment(File file, String str) throws IOException {
            if (hasZipCommentMagic(file)) {
                throw new MarketExistsException("Zip comment already exists, ignore.");
            }
            byte[] bytes = str.getBytes("UTF-8");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(file.length() - 2);
            writeShort(bytes.length + 2 + MAGIC.length, randomAccessFile);
            writeBytes(bytes, randomAccessFile);
            writeShort(bytes.length, randomAccessFile);
            writeBytes(MAGIC, randomAccessFile);
            randomAccessFile.close();
        }

        public static boolean hasZipCommentMagic(File file) throws Throwable {
            RandomAccessFile randomAccessFile = null;
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
                try {
                    long length = randomAccessFile2.length();
                    byte[] bArr = new byte[MAGIC.length];
                    randomAccessFile2.seek(length - ((long) MAGIC.length));
                    randomAccessFile2.readFully(bArr);
                    boolean zIsMagicMatched = isMagicMatched(bArr);
                    randomAccessFile2.close();
                    return zIsMagicMatched;
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        public static String readZipComment(File file) throws Throwable {
            RandomAccessFile randomAccessFile = null;
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
                try {
                    long length = randomAccessFile2.length();
                    byte[] bArr = new byte[MAGIC.length];
                    long length2 = length - ((long) MAGIC.length);
                    randomAccessFile2.seek(length2);
                    randomAccessFile2.readFully(bArr);
                    if (isMagicMatched(bArr)) {
                        long j = length2 - 2;
                        randomAccessFile2.seek(j);
                        int i = readShort(randomAccessFile2);
                        if (i > 0) {
                            randomAccessFile2.seek(j - ((long) i));
                            byte[] bArr2 = new byte[i];
                            randomAccessFile2.readFully(bArr2);
                            String str = new String(bArr2, "UTF-8");
                            randomAccessFile2.close();
                            return str;
                        }
                        throw new MarketNotFoundException("Zip comment content not found");
                    }
                    throw new MarketNotFoundException("Zip comment magic bytes not found");
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        private static String readZipCommentMmp(File file) throws Throwable {
            RandomAccessFile randomAccessFile;
            long length = file.length();
            MappedByteBuffer mappedByteBuffer = null;
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
                try {
                    MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, length - 10240, 10240L);
                    try {
                        map.order(ByteOrder.LITTLE_ENDIAN);
                        byte[] bArr = new byte[MAGIC.length];
                        int length2 = 10240 - MAGIC.length;
                        map.position(length2);
                        map.get(bArr);
                        if (isMagicMatched(bArr)) {
                            int i = length2 - 2;
                            map.position(i);
                            int i2 = map.getShort();
                            if (i2 > 0) {
                                map.position(i - i2);
                                byte[] bArr2 = new byte[i2];
                                map.get(bArr2);
                                String str = new String(bArr2, "UTF-8");
                                if (map != null) {
                                    map.clear();
                                }
                                randomAccessFile.close();
                                return str;
                            }
                        }
                        if (map != null) {
                            map.clear();
                        }
                        randomAccessFile.close();
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        mappedByteBuffer = map;
                        if (mappedByteBuffer != null) {
                            mappedByteBuffer.clear();
                        }
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile = null;
            }
        }

        public static void writeMarket(File file, String str) throws IOException {
            writeZipComment(file, str);
        }

        public static String readMarket(File file) throws IOException {
            return readZipComment(file);
        }

        public static boolean verifyMarket(File file, String str) throws IOException {
            return str.equals(readMarket(file));
        }

        public static void println(String str) {
            System.out.println(str);
        }

        public static void printErr(String str) {
            System.err.println(str);
        }

        public static List<String> parseMarkets(File file) throws IOException {
            ArrayList arrayList = new ArrayList();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    String[] strArrSplit = line.split("#");
                    if (strArrSplit.length > 0) {
                        String strTrim = strArrSplit[0].trim();
                        if (strTrim.length() > 0) {
                            arrayList.add(strTrim);
                        }
                    }
                } else {
                    bufferedReader.close();
                    fileReader.close();
                    return arrayList;
                }
            }
        }

        public static void copyFile(File file, File file2) throws Throwable {
            FileChannel fileChannel;
            if (!file2.exists()) {
                file2.createNewFile();
            }
            FileChannel channel = null;
            try {
                FileChannel channel2 = new FileInputStream(file).getChannel();
                try {
                    channel = new FileOutputStream(file2).getChannel();
                    channel.transferFrom(channel2, 0L, channel2.size());
                    if (channel2 != null) {
                        channel2.close();
                    }
                    if (channel != null) {
                        channel.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    FileChannel fileChannel2 = channel;
                    channel = channel2;
                    fileChannel = fileChannel2;
                    if (channel != null) {
                        channel.close();
                    }
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileChannel = null;
            }
        }

        public static boolean deleteDir(File file) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                return false;
            }
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    deleteDir(file2);
                } else {
                    file2.delete();
                }
            }
            return true;
        }

        public static String getExtension(String str) {
            int iLastIndexOf = str.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR);
            if (iLastIndexOf > 0) {
                return str.substring(iLastIndexOf + 1);
            }
            return null;
        }

        public static String getBaseName(String str) {
            int iLastIndexOf = str.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR);
            return iLastIndexOf > 0 ? str.substring(0, iLastIndexOf) : str;
        }
    }

    public static void main(String[] strArr) throws Throwable {
        if (strArr.length < 2) {
            Helper.println(USAGE_TEXT);
            Helper.println(INTRO_TEXT);
            System.exit(1);
        }
        int i = 0;
        File file = new File(strArr[0]);
        File file2 = new File(strArr[1]);
        File file3 = new File(strArr.length >= 3 ? strArr[2] : "apks");
        if (!file.exists()) {
            Helper.printErr("Apk file '" + file.getAbsolutePath() + "' is not exists or not readable.");
            Helper.println(USAGE_TEXT);
            System.exit(1);
            return;
        }
        if (!file2.exists()) {
            Helper.printErr("Market file '" + file2.getAbsolutePath() + "' is not exists or not readable.");
            Helper.println(USAGE_TEXT);
            System.exit(1);
            return;
        }
        if (!file3.exists()) {
            file3.mkdirs();
        }
        Helper.println("Apk File: " + file.getAbsolutePath());
        Helper.println("Market File: " + file2.getAbsolutePath());
        Helper.println("Output Dir: " + file3.getAbsolutePath());
        List<String> markets = null;
        try {
            markets = Helper.parseMarkets(file2);
        } catch (IOException unused) {
            Helper.printErr("Market file parse failed.");
            System.exit(1);
        }
        if (markets == null || markets.isEmpty()) {
            Helper.printErr("No markets found.");
            System.exit(1);
            return;
        }
        String baseName = Helper.getBaseName(file.getName());
        String extension = Helper.getExtension(file.getName());
        try {
            for (String str : markets) {
                String str2 = baseName + "-" + str + FileUtil.FILE_EXTENSION_SEPARATOR + extension;
                File file4 = new File(file3, str2);
                Helper.copyFile(file, file4);
                Helper.writeMarket(file4, str);
                if (Helper.verifyMarket(file4, str)) {
                    i++;
                    Helper.println("Generating apk " + str2);
                } else {
                    file4.delete();
                    Helper.printErr("Failed to generate " + str2);
                }
            }
            Helper.println("[Success] All " + i + " apks saved to " + file3.getAbsolutePath());
            Helper.println(INTRO_TEXT);
        } catch (MarketExistsException unused2) {
            Helper.printErr("Market info exists in '" + file + "', please using a clean apk.");
            System.exit(1);
        } catch (IOException e) {
            Helper.printErr("" + e);
            System.exit(1);
        }
    }
}
