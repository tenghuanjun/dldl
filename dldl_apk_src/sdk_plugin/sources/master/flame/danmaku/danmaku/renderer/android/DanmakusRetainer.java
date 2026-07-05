package master.flame.danmaku.danmaku.renderer.android;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.util.DanmakuUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class DanmakusRetainer {
    private IDanmakusRetainer rldrInstance = null;
    private IDanmakusRetainer lrdrInstance = null;
    private IDanmakusRetainer ftdrInstance = null;
    private IDanmakusRetainer fbdrInstance = null;

    public interface IDanmakusRetainer {
        void clear();

        void fix(BaseDanmaku baseDanmaku, IDisplayer iDisplayer, Verifier verifier);
    }

    public interface Verifier {
        boolean skipLayout(BaseDanmaku baseDanmaku, float f, int i, boolean z);
    }

    public DanmakusRetainer(boolean z) {
        alignBottom(z);
    }

    public void alignBottom(boolean z) {
        this.rldrInstance = z ? new AlignBottomRetainer() : new AlignTopRetainer();
        this.lrdrInstance = z ? new AlignBottomRetainer() : new AlignTopRetainer();
        if (this.ftdrInstance == null) {
            this.ftdrInstance = new FTDanmakusRetainer();
        }
        if (this.fbdrInstance == null) {
            this.fbdrInstance = new AlignBottomRetainer();
        }
    }

    public void fix(BaseDanmaku baseDanmaku, IDisplayer iDisplayer, Verifier verifier) {
        int type = baseDanmaku.getType();
        if (type == 1) {
            this.rldrInstance.fix(baseDanmaku, iDisplayer, verifier);
            return;
        }
        if (type == 4) {
            this.fbdrInstance.fix(baseDanmaku, iDisplayer, verifier);
            return;
        }
        if (type == 5) {
            this.ftdrInstance.fix(baseDanmaku, iDisplayer, verifier);
        } else if (type == 6) {
            this.lrdrInstance.fix(baseDanmaku, iDisplayer, verifier);
        } else {
            if (type != 7) {
                return;
            }
            baseDanmaku.layout(iDisplayer, 0.0f, 0.0f);
        }
    }

    public void clear() {
        IDanmakusRetainer iDanmakusRetainer = this.rldrInstance;
        if (iDanmakusRetainer != null) {
            iDanmakusRetainer.clear();
        }
        IDanmakusRetainer iDanmakusRetainer2 = this.lrdrInstance;
        if (iDanmakusRetainer2 != null) {
            iDanmakusRetainer2.clear();
        }
        IDanmakusRetainer iDanmakusRetainer3 = this.ftdrInstance;
        if (iDanmakusRetainer3 != null) {
            iDanmakusRetainer3.clear();
        }
        IDanmakusRetainer iDanmakusRetainer4 = this.fbdrInstance;
        if (iDanmakusRetainer4 != null) {
            iDanmakusRetainer4.clear();
        }
    }

    public void release() {
        clear();
    }

    private static class RetainerState {
        public BaseDanmaku firstItem;
        public BaseDanmaku insertItem;
        public BaseDanmaku lastItem;
        public int lines;
        public BaseDanmaku minRightRow;
        public boolean overwriteInsert;
        public BaseDanmaku removeItem;
        public boolean shown;
        public boolean willHit;

        private RetainerState() {
            this.lines = 0;
            this.insertItem = null;
            this.firstItem = null;
            this.lastItem = null;
            this.minRightRow = null;
            this.removeItem = null;
            this.overwriteInsert = false;
            this.shown = false;
            this.willHit = false;
        }
    }

    private static class AlignTopRetainer implements IDanmakusRetainer {
        protected boolean mCancelFixingFlag;
        protected RetainerConsumer mConsumer;
        protected Danmakus mVisibleDanmakus;

        protected class RetainerConsumer extends IDanmakus.Consumer<BaseDanmaku, RetainerState> {
            public IDisplayer disp;
            int lines = 0;
            public BaseDanmaku insertItem = null;
            public BaseDanmaku firstItem = null;
            public BaseDanmaku lastItem = null;
            public BaseDanmaku minRightRow = null;
            public BaseDanmaku drawItem = null;
            boolean overwriteInsert = false;
            boolean shown = false;
            boolean willHit = false;

            protected RetainerConsumer() {
            }

            @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
            public void before() {
                this.lines = 0;
                this.minRightRow = null;
                this.lastItem = null;
                this.firstItem = null;
                this.insertItem = null;
                this.willHit = false;
                this.shown = false;
                this.overwriteInsert = false;
            }

            @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
            public int accept(BaseDanmaku baseDanmaku) {
                if (AlignTopRetainer.this.mCancelFixingFlag) {
                    return 1;
                }
                this.lines++;
                if (baseDanmaku == this.drawItem) {
                    this.insertItem = baseDanmaku;
                    this.lastItem = null;
                    this.shown = true;
                    this.willHit = false;
                    return 1;
                }
                if (this.firstItem == null) {
                    this.firstItem = baseDanmaku;
                }
                if (this.drawItem.paintHeight + baseDanmaku.getTop() > this.disp.getHeight()) {
                    this.overwriteInsert = true;
                    return 1;
                }
                BaseDanmaku baseDanmaku2 = this.minRightRow;
                if (baseDanmaku2 == null || baseDanmaku2.getRight() >= baseDanmaku.getRight()) {
                    this.minRightRow = baseDanmaku;
                }
                IDisplayer iDisplayer = this.disp;
                BaseDanmaku baseDanmaku3 = this.drawItem;
                boolean zWillHitInDuration = DanmakuUtils.willHitInDuration(iDisplayer, baseDanmaku, baseDanmaku3, baseDanmaku3.getDuration(), this.drawItem.getTimer().currMillisecond);
                this.willHit = zWillHitInDuration;
                if (!zWillHitInDuration) {
                    this.insertItem = baseDanmaku;
                    return 1;
                }
                this.lastItem = baseDanmaku;
                return 0;
            }

            @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
            public RetainerState result() {
                RetainerState retainerState = new RetainerState();
                retainerState.lines = this.lines;
                retainerState.firstItem = this.firstItem;
                retainerState.insertItem = this.insertItem;
                retainerState.lastItem = this.lastItem;
                retainerState.minRightRow = this.minRightRow;
                retainerState.overwriteInsert = this.overwriteInsert;
                retainerState.shown = this.shown;
                retainerState.willHit = this.willHit;
                return retainerState;
            }
        }

        private AlignTopRetainer() {
            this.mVisibleDanmakus = new Danmakus(1);
            this.mCancelFixingFlag = false;
            this.mConsumer = new RetainerConsumer();
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x00b5  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00c2  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00c5  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00ce  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00e0  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00e6  */
        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.IDanmakusRetainer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void fix(master.flame.danmaku.danmaku.model.BaseDanmaku r20, master.flame.danmaku.danmaku.model.IDisplayer r21, master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.Verifier r22) {
            /*
                Method dump skipped, instruction units count: 271
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer.fix(master.flame.danmaku.danmaku.model.BaseDanmaku, master.flame.danmaku.danmaku.model.IDisplayer, master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer$Verifier):void");
        }

        protected boolean isOutVerticalEdge(boolean z, BaseDanmaku baseDanmaku, IDisplayer iDisplayer, float f, BaseDanmaku baseDanmaku2, BaseDanmaku baseDanmaku3) {
            if (f >= iDisplayer.getAllMarginTop()) {
                return (baseDanmaku2 != null && baseDanmaku2.getTop() > 0.0f) || f + baseDanmaku.paintHeight > ((float) iDisplayer.getHeight());
            }
            return true;
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.IDanmakusRetainer
        public void clear() {
            this.mCancelFixingFlag = true;
            this.mVisibleDanmakus.clear();
        }
    }

    private static class FTDanmakusRetainer extends AlignTopRetainer {
        private FTDanmakusRetainer() {
            super();
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer
        protected boolean isOutVerticalEdge(boolean z, BaseDanmaku baseDanmaku, IDisplayer iDisplayer, float f, BaseDanmaku baseDanmaku2, BaseDanmaku baseDanmaku3) {
            return f + baseDanmaku.paintHeight > ((float) iDisplayer.getHeight());
        }
    }

    private static class AlignBottomRetainer extends FTDanmakusRetainer {
        protected RetainerConsumer mConsumer;
        protected Danmakus mVisibleDanmakus;

        protected class RetainerConsumer extends IDanmakus.Consumer<BaseDanmaku, RetainerState> {
            public IDisplayer disp;
            float topPos;
            int lines = 0;
            public BaseDanmaku removeItem = null;
            public BaseDanmaku firstItem = null;
            public BaseDanmaku drawItem = null;
            boolean willHit = false;

            protected RetainerConsumer() {
            }

            @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
            public void before() {
                this.lines = 0;
                this.firstItem = null;
                this.removeItem = null;
                this.willHit = false;
            }

            @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
            public int accept(BaseDanmaku baseDanmaku) {
                if (AlignBottomRetainer.this.mCancelFixingFlag) {
                    return 1;
                }
                this.lines++;
                if (baseDanmaku == this.drawItem) {
                    this.removeItem = null;
                    this.willHit = false;
                    return 1;
                }
                if (this.firstItem == null) {
                    this.firstItem = baseDanmaku;
                    if (baseDanmaku.getBottom() != this.disp.getHeight()) {
                        return 1;
                    }
                }
                if (this.topPos < this.disp.getAllMarginTop()) {
                    this.removeItem = null;
                    return 1;
                }
                IDisplayer iDisplayer = this.disp;
                BaseDanmaku baseDanmaku2 = this.drawItem;
                boolean zWillHitInDuration = DanmakuUtils.willHitInDuration(iDisplayer, baseDanmaku, baseDanmaku2, baseDanmaku2.getDuration(), this.drawItem.getTimer().currMillisecond);
                this.willHit = zWillHitInDuration;
                if (!zWillHitInDuration) {
                    this.removeItem = baseDanmaku;
                    return 1;
                }
                this.topPos = (baseDanmaku.getTop() - this.disp.getMargin()) - this.drawItem.paintHeight;
                return 0;
            }

            @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
            public RetainerState result() {
                RetainerState retainerState = new RetainerState();
                retainerState.lines = this.lines;
                retainerState.firstItem = this.firstItem;
                retainerState.removeItem = this.removeItem;
                retainerState.willHit = this.willHit;
                return retainerState;
            }
        }

        private AlignBottomRetainer() {
            super();
            this.mConsumer = new RetainerConsumer();
            this.mVisibleDanmakus = new Danmakus(2);
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer, master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.IDanmakusRetainer
        public void fix(BaseDanmaku baseDanmaku, IDisplayer iDisplayer, Verifier verifier) {
            boolean z;
            boolean z2;
            BaseDanmaku baseDanmaku2;
            BaseDanmaku baseDanmaku3;
            int i;
            if (baseDanmaku.isOutside()) {
                return;
            }
            boolean zIsShown = baseDanmaku.isShown();
            float top = zIsShown ? baseDanmaku.getTop() : -1.0f;
            int i2 = 1;
            boolean z3 = false;
            boolean z4 = (zIsShown || this.mVisibleDanmakus.isEmpty()) ? false : true;
            if (top < iDisplayer.getAllMarginTop()) {
                top = iDisplayer.getHeight() - baseDanmaku.paintHeight;
            }
            BaseDanmaku baseDanmaku4 = null;
            if (zIsShown) {
                i2 = 0;
            } else {
                this.mCancelFixingFlag = false;
                this.mConsumer.topPos = top;
                this.mConsumer.disp = iDisplayer;
                this.mConsumer.drawItem = baseDanmaku;
                this.mVisibleDanmakus.forEachSync(this.mConsumer);
                RetainerState retainerStateResult = this.mConsumer.result();
                float f = this.mConsumer.topPos;
                if (retainerStateResult != null) {
                    int i3 = retainerStateResult.lines;
                    BaseDanmaku baseDanmaku5 = retainerStateResult.firstItem;
                    BaseDanmaku baseDanmaku6 = retainerStateResult.removeItem;
                    boolean z5 = retainerStateResult.shown;
                    i = i3;
                    z2 = retainerStateResult.willHit;
                    baseDanmaku2 = baseDanmaku5;
                    baseDanmaku3 = baseDanmaku6;
                    z = z5;
                } else {
                    z = zIsShown;
                    z2 = z4;
                    baseDanmaku2 = null;
                    baseDanmaku3 = null;
                    i = 0;
                }
                boolean zIsOutVerticalEdge = isOutVerticalEdge(false, baseDanmaku, iDisplayer, f, baseDanmaku2, null);
                if (zIsOutVerticalEdge) {
                    top = iDisplayer.getHeight() - baseDanmaku.paintHeight;
                    z3 = zIsOutVerticalEdge;
                    zIsShown = z;
                    baseDanmaku4 = baseDanmaku3;
                    z4 = true;
                } else {
                    boolean z6 = f >= ((float) iDisplayer.getAllMarginTop()) ? false : z2;
                    if (baseDanmaku3 != null) {
                        z3 = zIsOutVerticalEdge;
                        z4 = z6;
                        zIsShown = z;
                        baseDanmaku4 = baseDanmaku3;
                        i2 = i - 1;
                        top = f;
                    } else {
                        z3 = zIsOutVerticalEdge;
                        z4 = z6;
                        top = f;
                        i2 = i;
                        zIsShown = z;
                        baseDanmaku4 = baseDanmaku3;
                    }
                }
            }
            if (verifier == null || !verifier.skipLayout(baseDanmaku, top, i2, z4)) {
                if (z3) {
                    clear();
                }
                baseDanmaku.layout(iDisplayer, baseDanmaku.getLeft(), top);
                if (zIsShown) {
                    return;
                }
                this.mVisibleDanmakus.removeItem(baseDanmaku4);
                this.mVisibleDanmakus.addItem(baseDanmaku);
            }
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.FTDanmakusRetainer, master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer
        protected boolean isOutVerticalEdge(boolean z, BaseDanmaku baseDanmaku, IDisplayer iDisplayer, float f, BaseDanmaku baseDanmaku2, BaseDanmaku baseDanmaku3) {
            if (f >= iDisplayer.getAllMarginTop()) {
                return (baseDanmaku2 == null || baseDanmaku2.getBottom() == ((float) iDisplayer.getHeight())) ? false : true;
            }
            return true;
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer, master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.IDanmakusRetainer
        public void clear() {
            this.mCancelFixingFlag = true;
            this.mVisibleDanmakus.clear();
        }
    }
}
