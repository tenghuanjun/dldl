package com.sq.tools.event;

import android.content.Context;
import android.text.TextUtils;
import com.sq.tools.Assert;
import com.sq.tools.Logger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
final class EventStorage {
    private String EVENT_STORAGE_FILE_NAME = "SQ_EVENT_LOG.log";

    EventStorage() {
    }

    void setEventStorageFileName(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.EVENT_STORAGE_FILE_NAME = str;
    }

    synchronized EventCollection readAndClearStore(Context context) {
        Assert.shouldNotInUIThread();
        Logger.info(Logger.tag(EventsTracker.TAG), "Trying to read events from log file, waiting....", new Object[0]);
        if (!isEventFileExist(context)) {
            Logger.info(Logger.tag(EventsTracker.TAG), "Event file does not exist, return directly", new Object[0]);
            return new EventCollection();
        }
        EventCollection storage = readStorage(context);
        Logger.info(Logger.tag(EventsTracker.TAG), "Read Events should success, read Events count: %d", Integer.valueOf(storage.size()));
        try {
            if (!context.getApplicationContext().getFileStreamPath(this.EVENT_STORAGE_FILE_NAME).delete()) {
                Logger.warning(Logger.tag(EventsTracker.TAG), "Events file delete fail somehow", new Object[0]);
            }
        } catch (Exception e) {
            Logger.warning(Logger.tag(EventsTracker.TAG), "Delete File has Exception, this may cause repeat event post", e);
        }
        return storage;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ec A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00bd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:? A[Catch: all -> 0x0101, SYNTHETIC, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x000a, B:10:0x0029, B:16:0x005b, B:20:0x006f, B:23:0x0074, B:19:0x0060, B:55:0x00d6, B:60:0x00ec, B:64:0x0100, B:63:0x00f1, B:58:0x00db, B:42:0x00a7, B:47:0x00bd, B:50:0x00c2, B:45:0x00ac), top: B:70:0x0001, inners: #0, #4, #6, #8, #11, #13 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    synchronized boolean saveEventToDisk(android.content.Context r7, com.sq.tools.event.EventCollection r8) {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.tools.event.EventStorage.saveEventToDisk(android.content.Context, com.sq.tools.event.EventCollection):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:? A[Catch: all -> 0x0111, SYNTHETIC, TRY_LEAVE, TryCatch #12 {, blocks: (B:3:0x0001, B:15:0x002a, B:19:0x003e, B:22:0x0044, B:18:0x002f, B:36:0x0079, B:41:0x008f, B:44:0x0094, B:39:0x007e, B:51:0x00b7, B:56:0x00cd, B:59:0x00d2, B:54:0x00bc, B:64:0x00e6, B:69:0x00fc, B:73:0x0110, B:72:0x0101, B:67:0x00eb), top: B:91:0x0001, inners: #5, #6, #8, #11, #14, #15, #17, #19 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00e6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00fc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r10v15, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized com.sq.tools.event.EventCollection readStorage(android.content.Context r10) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.tools.event.EventStorage.readStorage(android.content.Context):com.sq.tools.event.EventCollection");
    }

    private synchronized boolean isEventFileExist(Context context) {
        boolean z;
        String[] strArrFileList = context.getApplicationContext().fileList();
        int length = strArrFileList.length;
        z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (strArrFileList[i].contains(this.EVENT_STORAGE_FILE_NAME)) {
                z = true;
                break;
            }
            i++;
        }
        return z;
    }
}
