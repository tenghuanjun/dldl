package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class L2RDanmaku extends R2LDanmaku {
    @Override // master.flame.danmaku.danmaku.model.R2LDanmaku, master.flame.danmaku.danmaku.model.BaseDanmaku
    public int getType() {
        return 6;
    }

    public L2RDanmaku(Duration duration) {
        super(duration);
    }

    @Override // master.flame.danmaku.danmaku.model.R2LDanmaku, master.flame.danmaku.danmaku.model.BaseDanmaku
    public void layout(IDisplayer iDisplayer, float f, float f2) {
        if (this.mTimer != null) {
            long j = this.mTimer.currMillisecond;
            long actualTime = j - getActualTime();
            if (actualTime > 0 && actualTime < this.duration.value) {
                this.x = getAccurateLeft(iDisplayer, j);
                if (!isShown()) {
                    this.y = f2;
                    setVisibility(true);
                }
                this.mLastTime = j;
                return;
            }
            this.mLastTime = j;
        }
        setVisibility(false);
    }

    @Override // master.flame.danmaku.danmaku.model.R2LDanmaku, master.flame.danmaku.danmaku.model.BaseDanmaku
    public float[] getRectAtTime(IDisplayer iDisplayer, long j) {
        if (!isMeasured()) {
            return null;
        }
        float accurateLeft = getAccurateLeft(iDisplayer, j);
        if (this.RECT == null) {
            this.RECT = new float[4];
        }
        this.RECT[0] = accurateLeft;
        this.RECT[1] = this.y;
        this.RECT[2] = accurateLeft + this.paintWidth;
        this.RECT[3] = this.y + this.paintHeight;
        return this.RECT;
    }

    @Override // master.flame.danmaku.danmaku.model.R2LDanmaku
    protected float getAccurateLeft(IDisplayer iDisplayer, long j) {
        long actualTime = j - getActualTime();
        if (actualTime >= this.duration.value) {
            return iDisplayer.getWidth();
        }
        return (this.mStepX * actualTime) - this.paintWidth;
    }

    @Override // master.flame.danmaku.danmaku.model.R2LDanmaku, master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getLeft() {
        return this.x;
    }

    @Override // master.flame.danmaku.danmaku.model.R2LDanmaku, master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getTop() {
        return this.y;
    }

    @Override // master.flame.danmaku.danmaku.model.R2LDanmaku, master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getRight() {
        return this.x + this.paintWidth;
    }

    @Override // master.flame.danmaku.danmaku.model.R2LDanmaku, master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getBottom() {
        return this.y + this.paintHeight;
    }
}
