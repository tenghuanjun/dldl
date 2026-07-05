package com.ss.android.socialbase.downloader.segment;

import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.exception.RetryThrowable;
import com.ss.android.socialbase.downloader.model.HttpResponse;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
interface ISegmentCallback {
    void applySegment(SegmentReader segmentReader, Segment segment) throws BaseException;

    IOutput createOutput(SegmentReader segmentReader, Segment segment) throws BaseException;

    Segment obtainSegment(SegmentReader segmentReader, UrlRecord urlRecord);

    void onReaderExit(SegmentReader segmentReader);

    void onReaderRun(SegmentReader segmentReader);

    void onSegmentConnected(SegmentReader segmentReader, Segment segment, UrlRecord urlRecord, HttpResponse httpResponse) throws RetryThrowable, BaseException;

    void onSegmentFailed(SegmentReader segmentReader, UrlRecord urlRecord, Segment segment, BaseException baseException);

    void onSegmentRetry(SegmentReader segmentReader, UrlRecord urlRecord, Segment segment, BaseException baseException, int i, int i2);

    void unApplySegment(SegmentReader segmentReader, Segment segment);

    void unObtainSegment(SegmentReader segmentReader, Segment segment);
}
