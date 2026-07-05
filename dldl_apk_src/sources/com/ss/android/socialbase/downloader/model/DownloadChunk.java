package com.ss.android.socialbase.downloader.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.os.Parcel;
import android.os.Parcelable;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.thread.DownloadChunkRunnable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DownloadChunk implements Parcelable {
    private int bindValueCount;
    private int chunkIndex;
    private DownloadChunkRunnable chunkRunnable;
    private long contentLength;
    private AtomicLong currentOffset;
    private long endOffset;
    private DownloadChunk hostChunk;
    private AtomicInteger hostChunkIndex;
    private int id;
    private AtomicBoolean isDownloading;
    private long oldOffset;
    private boolean reuseingFirstConnection;
    private long startOffset;
    private List<DownloadChunk> subChunkList;
    private static final String TAG = DownloadChunk.class.getSimpleName();
    public static final Parcelable.Creator<DownloadChunk> CREATOR = new Parcelable.Creator<DownloadChunk>() { // from class: com.ss.android.socialbase.downloader.model.DownloadChunk.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadChunk createFromParcel(Parcel parcel) {
            return new DownloadChunk(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DownloadChunk[] newArray(int i) {
            return new DownloadChunk[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private DownloadChunk(Builder builder) {
        if (builder == null) {
            return;
        }
        this.id = builder.id;
        this.startOffset = builder.startOffset;
        this.currentOffset = new AtomicLong(builder.currentOffset);
        this.endOffset = builder.endOffset;
        this.contentLength = builder.contentLength;
        this.chunkIndex = builder.chunkIndex;
        this.oldOffset = builder.oldOffset;
        this.hostChunkIndex = new AtomicInteger(-1);
        setHostChunk(builder.hostChunk);
        this.isDownloading = new AtomicBoolean(false);
    }

    public DownloadChunk(Cursor cursor) {
        if (cursor == null) {
            return;
        }
        this.id = cursor.getInt(cursor.getColumnIndex(DBDefinition.ID));
        this.chunkIndex = cursor.getInt(cursor.getColumnIndex(DBDefinition.CHUNK_INDEX));
        this.startOffset = cursor.getLong(cursor.getColumnIndex(DBDefinition.START_OFFSET));
        int columnIndex = cursor.getColumnIndex(DBDefinition.CUR_OFFSET);
        if (columnIndex != -1) {
            this.currentOffset = new AtomicLong(cursor.getLong(columnIndex));
        } else {
            this.currentOffset = new AtomicLong(0L);
        }
        this.endOffset = cursor.getLong(cursor.getColumnIndex(DBDefinition.END_OFFSET));
        int columnIndex2 = cursor.getColumnIndex(DBDefinition.HOST_CHUNK_INDEX);
        if (columnIndex2 != -1) {
            this.hostChunkIndex = new AtomicInteger(cursor.getInt(columnIndex2));
        } else {
            this.hostChunkIndex = new AtomicInteger(-1);
        }
        int columnIndex3 = cursor.getColumnIndex(DBDefinition.CHUNK_CONTENT_LEN);
        if (columnIndex3 != -1) {
            this.contentLength = cursor.getLong(columnIndex3);
        }
        this.isDownloading = new AtomicBoolean(false);
    }

    protected DownloadChunk(Parcel parcel) {
        this.id = parcel.readInt();
        this.startOffset = parcel.readLong();
        this.currentOffset = new AtomicLong(parcel.readLong());
        this.endOffset = parcel.readLong();
        this.contentLength = parcel.readLong();
        this.chunkIndex = parcel.readInt();
        this.hostChunkIndex = new AtomicInteger(parcel.readInt());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeLong(this.startOffset);
        AtomicLong atomicLong = this.currentOffset;
        parcel.writeLong(atomicLong != null ? atomicLong.get() : 0L);
        parcel.writeLong(this.endOffset);
        parcel.writeLong(this.contentLength);
        parcel.writeInt(this.chunkIndex);
        AtomicInteger atomicInteger = this.hostChunkIndex;
        parcel.writeInt(atomicInteger != null ? atomicInteger.get() : -1);
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBDefinition.ID, Integer.valueOf(this.id));
        contentValues.put(DBDefinition.CHUNK_INDEX, Integer.valueOf(this.chunkIndex));
        contentValues.put(DBDefinition.START_OFFSET, Long.valueOf(this.startOffset));
        contentValues.put(DBDefinition.CUR_OFFSET, Long.valueOf(getCurrentOffset()));
        contentValues.put(DBDefinition.END_OFFSET, Long.valueOf(this.endOffset));
        contentValues.put(DBDefinition.CHUNK_CONTENT_LEN, Long.valueOf(this.contentLength));
        contentValues.put(DBDefinition.HOST_CHUNK_INDEX, Integer.valueOf(getHostChunkIndex()));
        return contentValues;
    }

    public void bindValue(SQLiteStatement sQLiteStatement) {
        if (sQLiteStatement == null) {
            return;
        }
        this.bindValueCount = 0;
        sQLiteStatement.clearBindings();
        int i = this.bindValueCount + 1;
        this.bindValueCount = i;
        sQLiteStatement.bindLong(i, this.id);
        int i2 = this.bindValueCount + 1;
        this.bindValueCount = i2;
        sQLiteStatement.bindLong(i2, this.chunkIndex);
        int i3 = this.bindValueCount + 1;
        this.bindValueCount = i3;
        sQLiteStatement.bindLong(i3, this.startOffset);
        int i4 = this.bindValueCount + 1;
        this.bindValueCount = i4;
        sQLiteStatement.bindLong(i4, getCurrentOffset());
        int i5 = this.bindValueCount + 1;
        this.bindValueCount = i5;
        sQLiteStatement.bindLong(i5, this.endOffset);
        int i6 = this.bindValueCount + 1;
        this.bindValueCount = i6;
        sQLiteStatement.bindLong(i6, this.contentLength);
        int i7 = this.bindValueCount + 1;
        this.bindValueCount = i7;
        sQLiteStatement.bindLong(i7, getHostChunkIndex());
    }

    public int getHostChunkIndex() {
        AtomicInteger atomicInteger = this.hostChunkIndex;
        if (atomicInteger == null) {
            return -1;
        }
        return atomicInteger.get();
    }

    public void setHostChunkIndex(int i) {
        AtomicInteger atomicInteger = this.hostChunkIndex;
        if (atomicInteger == null) {
            this.hostChunkIndex = new AtomicInteger(i);
        } else {
            atomicInteger.set(i);
        }
    }

    public boolean isDownloading() {
        AtomicBoolean atomicBoolean = this.isDownloading;
        if (atomicBoolean == null) {
            return false;
        }
        return atomicBoolean.get();
    }

    public void setChunkRunnable(DownloadChunkRunnable downloadChunkRunnable) {
        this.chunkRunnable = downloadChunkRunnable;
        setOldOffset();
    }

    public void setDownloading(boolean z) {
        AtomicBoolean atomicBoolean = this.isDownloading;
        if (atomicBoolean == null) {
            this.isDownloading = new AtomicBoolean(z);
        } else {
            atomicBoolean.set(z);
        }
        this.chunkRunnable = null;
    }

    public void setHostChunk(DownloadChunk downloadChunk) {
        this.hostChunk = downloadChunk;
        if (downloadChunk != null) {
            setHostChunkIndex(downloadChunk.getChunkIndex());
        }
    }

    public DownloadChunk getHostChunk() {
        return this.hostChunk;
    }

    public boolean isHostChunk() {
        return getHostChunkIndex() == -1;
    }

    public DownloadChunk getFirstReuseChunk() {
        DownloadChunk downloadChunk = !isHostChunk() ? this.hostChunk : this;
        if (downloadChunk == null || !downloadChunk.hasChunkDivided()) {
            return null;
        }
        return downloadChunk.getSubChunkList().get(0);
    }

    public boolean hasChunkDivided() {
        List<DownloadChunk> list = this.subChunkList;
        return list != null && list.size() > 0;
    }

    public void setSubChunkList(List<DownloadChunk> list) {
        this.subChunkList = list;
    }

    public long getOldOffset() {
        return this.oldOffset;
    }

    public void setOldOffset(long j) {
        this.oldOffset = j;
    }

    public List<DownloadChunk> getSubChunkList() {
        return this.subChunkList;
    }

    public boolean canRefreshCurOffsetForReuseChunk() {
        DownloadChunk downloadChunk = this.hostChunk;
        if (downloadChunk == null) {
            return true;
        }
        if (!downloadChunk.hasChunkDivided()) {
            return false;
        }
        for (int i = 0; i < this.hostChunk.getSubChunkList().size(); i++) {
            DownloadChunk downloadChunk2 = this.hostChunk.getSubChunkList().get(i);
            if (downloadChunk2 != null) {
                int iIndexOf = this.hostChunk.getSubChunkList().indexOf(this);
                if (iIndexOf > i && !downloadChunk2.hasNoBytesDownload()) {
                    return false;
                }
                if (iIndexOf == i) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setReuseingFirstConnection(boolean z) {
        this.reuseingFirstConnection = z;
    }

    public boolean hasNoBytesDownload() {
        long j = this.startOffset;
        if (isHostChunk()) {
            long j2 = this.oldOffset;
            if (j2 > this.startOffset) {
                j = j2;
            }
        }
        return getCurrentOffset() - j >= this.contentLength;
    }

    public long getNextChunkCurOffset() {
        DownloadChunk downloadChunk = this.hostChunk;
        if (downloadChunk != null && downloadChunk.getSubChunkList() != null) {
            int iIndexOf = this.hostChunk.getSubChunkList().indexOf(this);
            boolean z = false;
            for (int i = 0; i < this.hostChunk.getSubChunkList().size(); i++) {
                DownloadChunk downloadChunk2 = this.hostChunk.getSubChunkList().get(i);
                if (downloadChunk2 != null) {
                    if (z) {
                        return downloadChunk2.getCurrentOffset();
                    }
                    if (iIndexOf == i) {
                        z = true;
                    }
                }
            }
        }
        return -1L;
    }

    public boolean isReuseingFirstConnection() {
        return this.chunkIndex == 0 && this.reuseingFirstConnection;
    }

    public int getBindValueCount() {
        return this.bindValueCount;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setChunkIndex(int i) {
        this.chunkIndex = i;
    }

    public void setContentLength(long j) {
        this.contentLength = j;
    }

    public int getId() {
        return this.id;
    }

    public long getStartOffset() {
        return this.startOffset;
    }

    public long getCurOffset() {
        AtomicLong atomicLong = this.currentOffset;
        if (atomicLong != null) {
            return atomicLong.get();
        }
        return 0L;
    }

    public long getCurrentOffset() {
        if (isHostChunk() && hasChunkDivided()) {
            long curOffset = 0;
            for (int i = 0; i < this.subChunkList.size(); i++) {
                DownloadChunk downloadChunk = this.subChunkList.get(i);
                if (downloadChunk != null) {
                    if (!downloadChunk.hasNoBytesDownload()) {
                        return downloadChunk.getCurOffset();
                    }
                    if (curOffset < downloadChunk.getCurOffset()) {
                        curOffset = downloadChunk.getCurOffset();
                    }
                }
            }
            return curOffset;
        }
        return getCurOffset();
    }

    public void setCurrentOffset(long j) {
        AtomicLong atomicLong = this.currentOffset;
        if (atomicLong != null) {
            atomicLong.set(j);
        } else {
            this.currentOffset = new AtomicLong(j);
        }
    }

    public long getDownloadChunkBytes() {
        long currentOffset = getCurrentOffset() - this.startOffset;
        if (hasChunkDivided()) {
            currentOffset = 0;
            for (int i = 0; i < this.subChunkList.size(); i++) {
                DownloadChunk downloadChunk = this.subChunkList.get(i);
                if (downloadChunk != null) {
                    currentOffset += downloadChunk.getCurrentOffset() - downloadChunk.getStartOffset();
                }
            }
        }
        return currentOffset;
    }

    public long getEndOffset() {
        return this.endOffset;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public void setOldOffset() {
        this.oldOffset = getCurrentOffset();
    }

    public long getRetainLength(boolean z) {
        long currentOffset = getCurrentOffset();
        long j = this.contentLength;
        long j2 = this.oldOffset;
        long j3 = j - (currentOffset - j2);
        if (!z && currentOffset == j2) {
            j3 = j - (currentOffset - this.startOffset);
        }
        Logger.d("DownloadChunk", "contentLength:" + this.contentLength + " curOffset:" + getCurrentOffset() + " oldOffset:" + this.oldOffset + " retainLen:" + j3);
        if (j3 < 0) {
            return 0L;
        }
        return j3;
    }

    public List<DownloadChunk> divideChunkForReuse(int i, long j) {
        DownloadChunk downloadChunk;
        long endOffset;
        long j2;
        long startOffset;
        long j3;
        long j4;
        DownloadChunk downloadChunk2 = this;
        int i2 = i;
        if (!isHostChunk() || hasChunkDivided()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        long curOffset = getCurOffset();
        long retainLength = downloadChunk2.getRetainLength(true);
        long j5 = retainLength / ((long) i2);
        Logger.d(TAG, "retainLen:" + retainLength + " divideChunkForReuse chunkSize:" + j5 + " current host downloadChunk index:" + downloadChunk2.chunkIndex);
        int i3 = 0;
        while (i3 < i2) {
            if (i3 == 0) {
                startOffset = getStartOffset();
                j2 = (curOffset + j5) - 1;
            } else {
                int i4 = i2 - 1;
                if (i3 == i4) {
                    long endOffset2 = getEndOffset();
                    j3 = endOffset2 > curOffset ? (endOffset2 - curOffset) + 1 : retainLength - (((long) i4) * j5);
                    j4 = endOffset2;
                    startOffset = curOffset;
                    long j6 = retainLength;
                    long j7 = j4;
                    DownloadChunk downloadChunkBuild = new Builder(downloadChunk2.id).chunkIndex((-i3) - 1).startOffset(startOffset).currentOffset(curOffset).oldOffset(curOffset).endOffset(j7).contentLength(j3).hostChunk(downloadChunk2).build();
                    Logger.d(TAG, "divide sub chunk : " + i3 + " startOffset:" + startOffset + " curOffset:" + curOffset + " endOffset:" + j7 + " contentLen:" + j3);
                    arrayList.add(downloadChunkBuild);
                    curOffset += j5;
                    i3++;
                    downloadChunk2 = this;
                    i2 = i;
                    retainLength = j6;
                } else {
                    j2 = (curOffset + j5) - 1;
                    startOffset = curOffset;
                }
            }
            j3 = j5;
            j4 = j2;
            long j62 = retainLength;
            long j72 = j4;
            DownloadChunk downloadChunkBuild2 = new Builder(downloadChunk2.id).chunkIndex((-i3) - 1).startOffset(startOffset).currentOffset(curOffset).oldOffset(curOffset).endOffset(j72).contentLength(j3).hostChunk(downloadChunk2).build();
            Logger.d(TAG, "divide sub chunk : " + i3 + " startOffset:" + startOffset + " curOffset:" + curOffset + " endOffset:" + j72 + " contentLen:" + j3);
            arrayList.add(downloadChunkBuild2);
            curOffset += j5;
            i3++;
            downloadChunk2 = this;
            i2 = i;
            retainLength = j62;
        }
        long contentLength = 0;
        for (int size = arrayList.size() - 1; size > 0; size--) {
            DownloadChunk downloadChunk3 = arrayList.get(size);
            if (downloadChunk3 != null) {
                contentLength += downloadChunk3.getContentLength();
            }
        }
        Logger.d(TAG, "reuseChunkContentLen:" + contentLength);
        DownloadChunk downloadChunk4 = arrayList.get(0);
        if (downloadChunk4 != null) {
            if (getEndOffset() == 0) {
                endOffset = j - getStartOffset();
            } else {
                endOffset = (getEndOffset() - getStartOffset()) + 1;
            }
            downloadChunk4.setContentLength(endOffset - contentLength);
            downloadChunk = this;
            downloadChunk4.setChunkIndex(downloadChunk.chunkIndex);
            DownloadChunkRunnable downloadChunkRunnable = downloadChunk.chunkRunnable;
            if (downloadChunkRunnable != null) {
                downloadChunkRunnable.refreshResponseHandleOffset(downloadChunk4.getEndOffset(), getContentLength() - contentLength);
            }
        } else {
            downloadChunk = this;
        }
        downloadChunk.setSubChunkList(arrayList);
        return arrayList;
    }

    public int getChunkIndex() {
        return this.chunkIndex;
    }

    public static class Builder {
        private int chunkIndex;
        private long contentLength;
        private long currentOffset;
        private long endOffset;
        private DownloadChunk hostChunk;
        private int id;
        private long oldOffset;
        private long startOffset;

        public Builder(int i) {
            this.id = i;
        }

        public Builder id(int i) {
            this.id = i;
            return this;
        }

        public Builder startOffset(long j) {
            this.startOffset = j;
            return this;
        }

        public Builder currentOffset(long j) {
            this.currentOffset = j;
            return this;
        }

        public Builder endOffset(long j) {
            this.endOffset = j;
            return this;
        }

        public Builder contentLength(long j) {
            this.contentLength = j;
            return this;
        }

        public Builder chunkIndex(int i) {
            this.chunkIndex = i;
            return this;
        }

        public Builder hostChunk(DownloadChunk downloadChunk) {
            this.hostChunk = downloadChunk;
            return this;
        }

        public Builder oldOffset(long j) {
            this.oldOffset = j;
            return this;
        }

        public DownloadChunk build() {
            return new DownloadChunk(this);
        }
    }
}
