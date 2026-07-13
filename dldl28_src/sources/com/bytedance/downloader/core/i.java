package com.bytedance.downloader.core;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static /* synthetic */ boolean f358a = true;

    public static g a(String str) {
        String line;
        g gVar = new g(new DownloadInfo());
        StringReader stringReader = new StringReader(str);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        while (bufferedReader.ready() && (line = bufferedReader.readLine()) != null) {
            if (line.startsWith("state:")) {
                gVar.a(DownloadState.valueOf(line.substring(6)));
            } else if (line.startsWith("fileName:")) {
                gVar.fileName = line.substring(9);
            } else if (line.startsWith("fileSize:")) {
                gVar.fileSize = Long.parseLong(line.substring(9));
            } else if (line.startsWith("md5:")) {
                gVar.md5 = line.substring(4);
            } else if (line.startsWith("savePath:")) {
                gVar.savePath = line.substring(9);
            } else if (line.startsWith("timestamp:")) {
                gVar.timestamp = Long.parseLong(line.substring(10));
            } else if (line.startsWith("url:")) {
                gVar.url = line.substring(4);
            } else {
                if (line.startsWith("chunks:")) {
                    for (String str2 : line.substring(7).split(";")) {
                        ArrayList arrayList = new ArrayList();
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < str2.length(); i++) {
                            char cCharAt = str2.charAt(i);
                            if (cCharAt != '-' || sb.length() == 0) {
                                sb.append(cCharAt);
                            } else {
                                arrayList.add(sb.toString());
                                sb.delete(0, sb.length());
                            }
                        }
                        if (sb.length() > 0) {
                            arrayList.add(sb.toString());
                        }
                        if (!f358a && arrayList.size() != 4) {
                            throw new AssertionError();
                        }
                        gVar.a().add(new b(Integer.parseInt((String) arrayList.get(0)), Long.parseLong((String) arrayList.get(1)), Long.parseLong((String) arrayList.get(3)), Long.parseLong((String) arrayList.get(2))));
                    }
                } else if (line.startsWith("hostIpList:")) {
                    for (String str3 : line.substring(11).split(";")) {
                        gVar.getHostIpList().add(str3);
                    }
                }
            }
        }
        q.a(bufferedReader);
        q.a(stringReader);
        return gVar;
    }

    public static String a(g gVar) {
        StringBuilder sb = new StringBuilder("state:");
        sb.append(gVar.b().toString());
        sb.append("\nfileName:");
        sb.append(gVar.getFileName());
        sb.append("\nfileSize:");
        sb.append(gVar.getFileSize());
        sb.append("\nmd5:");
        sb.append(gVar.getMd5());
        sb.append("\nsavePath:");
        sb.append(gVar.getSavePath());
        sb.append("\ntimestamp:");
        sb.append(gVar.getTimestamp());
        sb.append("\nurl:");
        sb.append(gVar.getUrl());
        sb.append(StringUtils.LF);
        List listA = gVar.a();
        boolean z = true;
        if (listA != null && !listA.isEmpty()) {
            sb.append("chunks:");
            boolean z2 = true;
            for (int i = 0; i < listA.size(); i++) {
                b bVar = (b) listA.get(i);
                if (z2) {
                    z2 = false;
                } else {
                    sb.append(";");
                }
                sb.append(bVar.a());
                sb.append("-");
                sb.append(bVar.b());
                sb.append("-");
                sb.append(bVar.d());
                sb.append("-");
                sb.append(bVar.c());
            }
            sb.append(StringUtils.LF);
        }
        List hostIpList = gVar.getHostIpList();
        if (hostIpList != null && !hostIpList.isEmpty()) {
            sb.append("hostIpList:");
            for (int i2 = 0; i2 < hostIpList.size(); i2++) {
                String str = (String) hostIpList.get(i2);
                if (z) {
                    z = false;
                } else {
                    sb.append(";");
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }
}
