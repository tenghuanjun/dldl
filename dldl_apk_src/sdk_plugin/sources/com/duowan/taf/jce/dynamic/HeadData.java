package com.duowan.taf.jce.dynamic;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HeadData {
    public int tag;
    public byte type;

    public void clear() {
        this.type = (byte) 0;
        this.tag = 0;
    }

    public static HeadData obtain() {
        return HeadDataPoolsFactory.getInstance().obtain();
    }

    public static void revert(HeadData headData) {
        HeadDataPoolsFactory.getInstance().recycle(headData);
    }

    private static class HeadDataPoolsFactory extends AbsPoolFactory<HeadData> {
        private static final HeadDataPoolsFactory INSTANCE = new HeadDataPoolsFactory(100);
        private AtomicInteger mCount;
        private AtomicInteger mResetTimes;

        public static HeadDataPoolsFactory getInstance() {
            return INSTANCE;
        }

        private HeadDataPoolsFactory(int i) {
            super(i);
            this.mCount = new AtomicInteger();
            this.mResetTimes = new AtomicInteger();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.duowan.taf.jce.dynamic.AbsPoolFactory
        public HeadData createObject() {
            return new HeadData();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.duowan.taf.jce.dynamic.AbsPoolFactory
        public void resetObject(HeadData headData) {
            this.mResetTimes.incrementAndGet();
            if (headData != null) {
                headData.clear();
            }
        }
    }
}
