package com.ss.android.socialbase.downloader.segment;

import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class SegmentUtils {
    public static long getFirstOffset(List<Segment> list) {
        int size = list.size();
        long currentOffsetRead = 0;
        for (int i = 0; i < size; i++) {
            Segment segment = list.get(i);
            if (segment.getStartOffset() > currentOffsetRead) {
                break;
            }
            if (segment.getCurrentOffsetRead() > currentOffsetRead) {
                currentOffsetRead = segment.getCurrentOffsetRead();
            }
        }
        return currentOffsetRead;
    }

    public static long getDownloadedBytes(List<Segment> list) {
        long startOffset;
        long currentOffset;
        long j = 0;
        loop0: while (true) {
            startOffset = -1;
            currentOffset = -1;
            for (Segment segment : list) {
                if (startOffset == -1) {
                    if (segment.getDownloadBytes() > 0) {
                        startOffset = segment.getStartOffset();
                        currentOffset = segment.getCurrentOffset();
                    }
                } else if (segment.getStartOffset() > currentOffset) {
                    j += currentOffset - startOffset;
                    if (segment.getDownloadBytes() > 0) {
                        startOffset = segment.getStartOffset();
                        currentOffset = segment.getCurrentOffset();
                    }
                } else if (segment.getCurrentOffset() > currentOffset) {
                    currentOffset = segment.getCurrentOffset();
                }
            }
        }
        return (startOffset < 0 || currentOffset <= startOffset) ? j : j + (currentOffset - startOffset);
    }
}
