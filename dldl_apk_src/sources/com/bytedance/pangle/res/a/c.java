package com.bytedance.pangle.res.a;

import android.text.TextUtils;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.plugin.core.manifest.AndroidManifestConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c {
    public final int a(File file, boolean z, StringBuilder sb) {
        String string;
        ZipFile zipFile = null;
        try {
            ZipFile zipFile2 = new ZipFile(file);
            try {
                ZipEntry entry = zipFile2.getEntry("assets/ZeusResMapping");
                if (entry == null) {
                    com.bytedance.pangle.util.g.a(zipFile2);
                    return 200;
                }
                if (z) {
                    File file2 = new File(file.getParentFile(), "resMappingBak");
                    if (!file2.exists()) {
                        ZeusLogger.errReport(ZeusLogger.TAG_INSTALL, "resMappingBakFile is not exists. " + file2.getAbsolutePath());
                        sb.append("resMappingBakFile is not exists. ");
                        sb.append(file2.getAbsolutePath());
                        com.bytedance.pangle.util.g.a(zipFile2);
                        return 300;
                    }
                    FileInputStream fileInputStream = new FileInputStream(file2);
                    FileChannel channel = fileInputStream.getChannel();
                    string = Charset.defaultCharset().newDecoder().decode(channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size()).asReadOnlyBuffer()).toString();
                    channel.close();
                    fileInputStream.close();
                } else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    InputStream inputStream = zipFile2.getInputStream(entry);
                    if (inputStream != null) {
                        ReadableByteChannel readableByteChannelNewChannel = Channels.newChannel(inputStream);
                        WritableByteChannel writableByteChannelNewChannel = Channels.newChannel(byteArrayOutputStream);
                        com.bytedance.pangle.util.h.a(readableByteChannelNewChannel, writableByteChannelNewChannel);
                        readableByteChannelNewChannel.close();
                        writableByteChannelNewChannel.close();
                    }
                    string = byteArrayOutputStream.toString();
                }
                if (TextUtils.isEmpty(string)) {
                    ZeusLogger.errReport(ZeusLogger.TAG_INSTALL, "zeusResMappingContent empty, useBakFile:".concat(String.valueOf(z)));
                    sb.append("zeusResMappingContent isEmpty. useBakFile:");
                    sb.append(z);
                    com.bytedance.pangle.util.g.a(zipFile2);
                    return 300;
                }
                JSONObject jSONObject = new JSONObject(string);
                JSONObject jSONObject2 = new JSONObject(string);
                JSONArray jSONArray = (JSONArray) jSONObject.get("fileNames");
                final HashSet hashSet = new HashSet();
                for (int i = 0; i < jSONArray.length(); i++) {
                    hashSet.add((String) jSONArray.get(i));
                }
                final JSONObject jSONObject3 = (JSONObject) jSONObject.get("resMapping");
                final JSONObject jSONObject4 = (JSONObject) jSONObject2.get("resMapping");
                final int[] iArr = {0};
                a(file, (HashSet<String>) hashSet, new h() { // from class: com.bytedance.pangle.res.a.c.1
                    @Override // com.bytedance.pangle.res.a.h
                    public final int a(int i2) {
                        String str = "0x" + Integer.toHexString(i2);
                        String str2 = (String) jSONObject3.opt(str);
                        if (str2 == null) {
                            return i2;
                        }
                        int identifier = Zeus.getAppApplication().getResources().getIdentifier(str2.split(" ")[1], str2.split(" ")[0], Zeus.getAppApplication().getPackageName());
                        if (identifier == 0) {
                            identifier = Zeus.getAppApplication().getResources().getIdentifier(str2.split(" ")[1].replaceAll("_", "."), str2.split(" ")[0], Zeus.getAppApplication().getPackageName());
                        }
                        if (identifier == 0) {
                            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "getIdentifier failed. resName is ".concat(String.valueOf(str2)));
                            return i2;
                        }
                        String str3 = "0x" + Integer.toHexString(identifier);
                        if (TextUtils.equals(jSONObject4.optString(str), str2)) {
                            jSONObject4.remove(str);
                        }
                        try {
                            jSONObject4.put(str3, str2);
                        } catch (Throwable th) {
                            ZeusLogger.errReport(ZeusLogger.TAG_INSTALL, "update resMappingBak failed.", th);
                        }
                        if (identifier != i2) {
                            int[] iArr2 = iArr;
                            iArr2[0] = iArr2[0] + 1;
                        }
                        return identifier;
                    }

                    @Override // com.bytedance.pangle.res.a.h
                    public final boolean a(String str) {
                        return hashSet.contains(str);
                    }
                });
                ZeusLogger.d(ZeusLogger.TAG_INSTALL, "modifyRes count = " + iArr[0]);
                if (com.bytedance.pangle.util.h.a(jSONObject2.toString(), new File(file.getParentFile(), "resMappingBak"), sb)) {
                    com.bytedance.pangle.util.g.a(zipFile2);
                    return 100;
                }
                ZeusLogger.errReport(ZeusLogger.TAG_INSTALL, "writeText failed." + sb.toString());
                sb.append("writeText failed.");
                com.bytedance.pangle.util.g.a(zipFile2);
                return 300;
            } catch (Throwable th) {
                th = th;
                zipFile = zipFile2;
                try {
                    ZeusLogger.errReport(ZeusLogger.TAG_INSTALL, "modifyRes failed. catch: " + th.getMessage(), th);
                    sb.append("modifyRes failed. catch: ");
                    sb.append(th.getMessage());
                    return 300;
                } finally {
                    if (zipFile != null) {
                        com.bytedance.pangle.util.g.a(zipFile);
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void a(File file, HashSet<String> hashSet, h hVar) throws Throwable {
        int i;
        MappedByteBuffer mappedByteBufferA = a(file);
        int iCapacity = mappedByteBufferA.capacity();
        if (iCapacity >= 22) {
            int i2 = iCapacity - 22;
            int iMin = Math.min(i2, 65535);
            for (int i3 = 0; i3 < iMin; i3++) {
                i = i2 - i3;
                if (mappedByteBufferA.getInt(i) == 101010256 && mappedByteBufferA.getShort(i + 20) == i3) {
                    break;
                }
            }
            i = -1;
        } else {
            i = -1;
        }
        if (i == -1) {
            throw new Throwable("endOfCentralPosition == -1");
        }
        int i4 = mappedByteBufferA.getInt(i + 12);
        int i5 = mappedByteBufferA.getInt(i + 16);
        int i6 = i4 + i5;
        while (i5 < i6) {
            if (mappedByteBufferA.getInt(i5) != 33639248) {
                throw new RuntimeException("Expected: 0x02014b50, got: " + mappedByteBufferA.getInt(i5));
            }
            int i7 = mappedByteBufferA.getShort(i5 + 28);
            short s = mappedByteBufferA.getShort(i5 + 30);
            byte[] bArr = new byte[i7];
            for (int i8 = 0; i8 < i7; i8++) {
                bArr[i8] = mappedByteBufferA.get(i5 + 46 + i8);
            }
            String str = new String(bArr);
            int i9 = mappedByteBufferA.getInt(i5 + 20);
            int i10 = mappedByteBufferA.getInt(i5 + 24);
            if (hashSet.contains(str)) {
                if (i9 != i10) {
                    throw new Throwable(str + " is compressed.");
                }
                com.bytedance.pangle.util.e<Integer, byte[]> eVarA = a(mappedByteBufferA, mappedByteBufferA.getInt(i5 + 42), str);
                try {
                    byte[] bArr2 = eVarA.b;
                    if (!TextUtils.isEmpty(str) && hVar.a(str)) {
                        if (str.equals(AndroidManifestConstants.ANDROID_MANIFEST_FILE_NAME)) {
                            k.a(bArr2, hVar);
                        } else if ((str.endsWith(".xml") && str.startsWith("res/")) || TextUtils.equals(str, AndroidManifestConstants.ANDROID_MANIFEST_FILE_NAME)) {
                            k.a(bArr2, hVar);
                        } else if (str.equals("resources.arsc")) {
                            new a(bArr2, hVar).a();
                        }
                    }
                    for (int i11 = 0; i11 < eVarA.b.length; i11++) {
                        mappedByteBufferA.put(eVarA.a.intValue() + i11, eVarA.b[i11]);
                    }
                } catch (Throwable th) {
                    throw new RuntimeException(th);
                }
            }
            i5 += i7 + 46 + s;
        }
    }

    private static com.bytedance.pangle.util.e<Integer, byte[]> a(MappedByteBuffer mappedByteBuffer, int i, String str) {
        if (mappedByteBuffer.getInt(i) != 67324752) {
            throw new RuntimeException("Expected: 0x04034b50, got: " + mappedByteBuffer.getInt(i) + " FileName:" + str);
        }
        int i2 = mappedByteBuffer.getInt(i + 18);
        int i3 = mappedByteBuffer.getInt(i + 22);
        if (i2 != i3) {
            throw new RuntimeException(str + " is compressed. compressSize:" + i2 + " size:" + i3);
        }
        byte[] bArr = new byte[i3];
        int i4 = i + 30 + mappedByteBuffer.getShort(i + 26) + mappedByteBuffer.getShort(i + 28);
        for (int i5 = 0; i5 < i3; i5++) {
            bArr[i5] = mappedByteBuffer.get(i4 + i5);
        }
        return new com.bytedance.pangle.util.e<>(Integer.valueOf(i4), bArr);
    }

    private static MappedByteBuffer a(File file) throws Throwable {
        RandomAccessFile randomAccessFile = null;
        try {
            ZeusLogger.d("temp-pxr", "mappedByteBuffer " + file.canWrite());
            if (!file.canWrite()) {
                file.setWritable(true);
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            try {
                FileChannel channel = randomAccessFile2.getChannel();
                long size = channel.size();
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0L, size);
                byte[] bArr = new byte[4194304];
                long j = size / 4194304;
                int i = (int) (size % 4194304);
                for (int i2 = 0; i2 < j; i2++) {
                    map.get(bArr);
                }
                if (i > 0) {
                    map.get(new byte[i]);
                }
                map.order(ByteOrder.LITTLE_ENDIAN);
                randomAccessFile2.close();
                return map;
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
}
