package com.huya.android.support.v7.widget;

import com.huya.android.support.v7.widget.AdapterHelper;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class OpReorderer {
    final Callback mCallback;

    interface Callback {
        AdapterHelper.UpdateOp obtainUpdateOp(int i, int i2, int i3);

        void recycleUpdateOp(AdapterHelper.UpdateOp updateOp);
    }

    public OpReorderer(Callback callback) {
        this.mCallback = callback;
    }

    void reorderOps(List<AdapterHelper.UpdateOp> list) {
        while (true) {
            int lastMoveOutOfOrder = getLastMoveOutOfOrder(list);
            if (lastMoveOutOfOrder == -1) {
                return;
            } else {
                swapMoveOp(list, lastMoveOutOfOrder, lastMoveOutOfOrder + 1);
            }
        }
    }

    private void swapMoveOp(List<AdapterHelper.UpdateOp> list, int i, int i2) {
        AdapterHelper.UpdateOp updateOp = list.get(i);
        AdapterHelper.UpdateOp updateOp2 = list.get(i2);
        int i3 = updateOp2.cmd;
        if (i3 == 0) {
            swapMoveAdd(list, i, updateOp, i2, updateOp2);
        } else if (i3 == 1) {
            swapMoveRemove(list, i, updateOp, i2, updateOp2);
        } else {
            if (i3 != 2) {
                return;
            }
            swapMoveUpdate(list, i, updateOp, i2, updateOp2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0097  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void swapMoveRemove(java.util.List<com.huya.android.support.v7.widget.AdapterHelper.UpdateOp> r7, int r8, com.huya.android.support.v7.widget.AdapterHelper.UpdateOp r9, int r10, com.huya.android.support.v7.widget.AdapterHelper.UpdateOp r11) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.android.support.v7.widget.OpReorderer.swapMoveRemove(java.util.List, int, com.huya.android.support.v7.widget.AdapterHelper$UpdateOp, int, com.huya.android.support.v7.widget.AdapterHelper$UpdateOp):void");
    }

    private void swapMoveAdd(List<AdapterHelper.UpdateOp> list, int i, AdapterHelper.UpdateOp updateOp, int i2, AdapterHelper.UpdateOp updateOp2) {
        int i3 = updateOp.itemCount < updateOp2.positionStart ? -1 : 0;
        if (updateOp.positionStart < updateOp2.positionStart) {
            i3++;
        }
        if (updateOp2.positionStart <= updateOp.positionStart) {
            updateOp.positionStart += updateOp2.itemCount;
        }
        if (updateOp2.positionStart <= updateOp.itemCount) {
            updateOp.itemCount += updateOp2.itemCount;
        }
        updateOp2.positionStart += i3;
        list.set(i, updateOp2);
        list.set(i2, updateOp);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void swapMoveUpdate(java.util.List<com.huya.android.support.v7.widget.AdapterHelper.UpdateOp> r8, int r9, com.huya.android.support.v7.widget.AdapterHelper.UpdateOp r10, int r11, com.huya.android.support.v7.widget.AdapterHelper.UpdateOp r12) {
        /*
            r7 = this;
            int r0 = r10.itemCount
            int r1 = r12.positionStart
            r2 = 2
            r3 = 0
            r4 = 1
            if (r0 >= r1) goto Lf
            int r0 = r12.positionStart
            int r0 = r0 - r4
            r12.positionStart = r0
            goto L26
        Lf:
            int r0 = r10.itemCount
            int r1 = r12.positionStart
            int r5 = r12.itemCount
            int r1 = r1 + r5
            if (r0 >= r1) goto L26
            int r0 = r12.itemCount
            int r0 = r0 - r4
            r12.itemCount = r0
            com.huya.android.support.v7.widget.OpReorderer$Callback r0 = r7.mCallback
            int r1 = r10.positionStart
            com.huya.android.support.v7.widget.AdapterHelper$UpdateOp r0 = r0.obtainUpdateOp(r2, r1, r4)
            goto L27
        L26:
            r0 = r3
        L27:
            int r1 = r10.positionStart
            int r5 = r12.positionStart
            if (r1 > r5) goto L33
            int r1 = r12.positionStart
            int r1 = r1 + r4
            r12.positionStart = r1
            goto L52
        L33:
            int r1 = r10.positionStart
            int r5 = r12.positionStart
            int r6 = r12.itemCount
            int r5 = r5 + r6
            if (r1 >= r5) goto L52
            int r1 = r12.positionStart
            int r3 = r12.itemCount
            int r1 = r1 + r3
            int r3 = r10.positionStart
            int r1 = r1 - r3
            com.huya.android.support.v7.widget.OpReorderer$Callback r3 = r7.mCallback
            int r5 = r10.positionStart
            int r5 = r5 + r4
            com.huya.android.support.v7.widget.AdapterHelper$UpdateOp r3 = r3.obtainUpdateOp(r2, r5, r1)
            int r2 = r12.itemCount
            int r2 = r2 - r1
            r12.itemCount = r2
        L52:
            r8.set(r11, r10)
            int r10 = r12.itemCount
            if (r10 <= 0) goto L5d
            r8.set(r9, r12)
            goto L65
        L5d:
            r8.remove(r9)
            com.huya.android.support.v7.widget.OpReorderer$Callback r10 = r7.mCallback
            r10.recycleUpdateOp(r12)
        L65:
            if (r0 == 0) goto L6a
            r8.add(r9, r0)
        L6a:
            if (r3 == 0) goto L6f
            r8.add(r9, r3)
        L6f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.android.support.v7.widget.OpReorderer.swapMoveUpdate(java.util.List, int, com.huya.android.support.v7.widget.AdapterHelper$UpdateOp, int, com.huya.android.support.v7.widget.AdapterHelper$UpdateOp):void");
    }

    private int getLastMoveOutOfOrder(List<AdapterHelper.UpdateOp> list) {
        boolean z = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).cmd != 3) {
                z = true;
            } else if (z) {
                return size;
            }
        }
        return -1;
    }
}
