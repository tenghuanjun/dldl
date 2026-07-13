package com.bytedance.downloader.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map f359a = new HashMap();
    private final File b;
    private final File c;
    private final boolean d;

    public j(File file, boolean z) throws Throwable {
        this.b = file;
        this.d = z;
        this.c = new File(file, "181a04e37c06fe4984b3a0df9e08e595");
        b();
    }

    private static String a(File file) throws Throwable {
        FileReader fileReader;
        IOException e;
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            if (file.exists()) {
                fileReader = new FileReader(file);
                try {
                    bufferedReader = new BufferedReader(fileReader);
                    while (true) {
                        try {
                            try {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                sb.append(line);
                                sb.append(StringUtils.LF);
                            } catch (IOException e2) {
                                e = e2;
                                h.a(e);
                                a(bufferedReader);
                            }
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader2 = bufferedReader;
                            a(bufferedReader2);
                            a(fileReader);
                            throw th;
                        }
                    }
                    bufferedReader2 = bufferedReader;
                } catch (IOException e3) {
                    e = e3;
                    bufferedReader = null;
                } catch (Throwable th2) {
                    th = th2;
                    a(bufferedReader2);
                    a(fileReader);
                    throw th;
                }
            } else {
                fileReader = null;
            }
            a(bufferedReader2);
        } catch (IOException e4) {
            fileReader = null;
            e = e4;
            bufferedReader = null;
        } catch (Throwable th3) {
            th = th3;
            fileReader = null;
        }
        a(fileReader);
        return sb.toString();
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                h.a(e);
            }
        }
    }

    private void a(File file, String str) {
        if (this.d) {
            c(file, str);
        } else {
            b(file, str);
        }
    }

    private void a(String str) {
        synchronized (j.class) {
            File file = new File(this.b, str);
            if (file.exists()) {
                h.a("Delete file result:" + file.delete());
            }
        }
    }

    private void a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        a(new File(this.b, str), str2);
    }

    private void b() throws Throwable {
        HashSet<String> hashSet = new HashSet();
        String strA = a(this.c);
        if (!"".equals(strA)) {
            hashSet.addAll(Arrays.asList(strA.split(StringUtils.LF)));
        }
        for (String str : hashSet) {
            File file = new File(this.b, str);
            if (file.isFile() && file.exists()) {
                try {
                    g gVarA = i.a(a(file));
                    if (gVarA.checkValidity()) {
                        if (gVarA.b() == DownloadState.Prepare || gVarA.b() == DownloadState.Downloading || gVarA.b() == DownloadState.Verifying) {
                            if (!gVarA.getFile().exists()) {
                                gVarA.a().clear();
                            }
                            gVarA.a(DownloadState.NotStart);
                        }
                        this.f359a.put(str, gVarA);
                    } else {
                        a(str);
                    }
                } catch (Exception e) {
                    h.a(e);
                }
            }
        }
    }

    private static void b(File file, String str) {
        FileChannel fileChannel;
        FileChannel fileChannel2;
        synchronized (j.class) {
            RandomAccessFile randomAccessFile = null;
            RandomAccessFile randomAccessFile2 = null;
            channel = null;
            FileChannel channel = null;
            randomAccessFile = null;
            try {
                if (file.exists() && q.a(str)) {
                    h.a("Delete file result:" + file.delete());
                    fileChannel2 = null;
                } else {
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists()) {
                        h.a("Make dirs result:" + parentFile.mkdirs());
                    }
                    byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
                    RandomAccessFile randomAccessFile3 = new RandomAccessFile(file, "rwd");
                    try {
                        randomAccessFile3.setLength(1L);
                        channel = randomAccessFile3.getChannel();
                        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0L, bytes.length);
                        map.put(bytes, 0, bytes.length);
                        map.force();
                        fileChannel2 = channel;
                        randomAccessFile2 = randomAccessFile3;
                    } catch (Exception e) {
                        e = e;
                        fileChannel = channel;
                        randomAccessFile = randomAccessFile3;
                        try {
                            h.a(e);
                            a(randomAccessFile);
                            a(fileChannel);
                        } catch (Throwable th) {
                            th = th;
                            a(randomAccessFile);
                            a(fileChannel);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileChannel = channel;
                        randomAccessFile = randomAccessFile3;
                        a(randomAccessFile);
                        a(fileChannel);
                        throw th;
                    }
                }
                a(randomAccessFile2);
                a(fileChannel2);
            } catch (Exception e2) {
                e = e2;
                fileChannel = null;
            } catch (Throwable th3) {
                th = th3;
                fileChannel = null;
            }
        }
    }

    private void c() {
        StringBuilder sb = new StringBuilder();
        Iterator it = this.f359a.keySet().iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append(StringUtils.LF);
        }
        a(this.c, sb.toString());
    }

    private static void c(File file, String str) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        synchronized (j.class) {
            BufferedWriter bufferedWriter2 = null;
            try {
                if (file.exists() && q.a(str)) {
                    h.a("Delete file result:" + file.delete());
                    fileWriter = null;
                } else {
                    fileWriter = new FileWriter(file.getAbsoluteFile());
                    try {
                        try {
                            bufferedWriter = new BufferedWriter(fileWriter);
                        } catch (Exception e) {
                            e = e;
                        }
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        bufferedWriter.write(str);
                        bufferedWriter.flush();
                        bufferedWriter2 = bufferedWriter;
                    } catch (Exception e2) {
                        bufferedWriter2 = bufferedWriter;
                        e = e2;
                        h.a(e);
                        a(bufferedWriter2);
                    } catch (Throwable th2) {
                        bufferedWriter2 = bufferedWriter;
                        th = th2;
                        a(bufferedWriter2);
                        a(fileWriter);
                        throw th;
                    }
                }
                a(bufferedWriter2);
            } catch (Exception e3) {
                e = e3;
                fileWriter = null;
            } catch (Throwable th3) {
                th = th3;
                fileWriter = null;
            }
            a(fileWriter);
        }
    }

    public final List a() {
        return new ArrayList(this.f359a.values());
    }

    public final void a(g gVar) {
        String strA = q.a(gVar.toString().getBytes());
        if (!this.f359a.containsKey(strA)) {
            this.f359a.put(strA, gVar);
        }
        a(strA, i.a(gVar));
        c();
    }

    public final void b(g gVar) {
        String strA = q.a(gVar.toString().getBytes());
        this.f359a.remove(strA);
        a(strA);
        c();
    }

    public final void c(g gVar) {
        String strA = q.a(gVar.toString().getBytes());
        String strA2 = i.a(gVar);
        if (this.f359a.containsKey(strA)) {
            this.f359a.put(strA, gVar);
        }
        a(strA, strA2);
    }
}
