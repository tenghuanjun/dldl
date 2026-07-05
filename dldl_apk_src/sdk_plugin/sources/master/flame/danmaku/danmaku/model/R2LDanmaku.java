package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class R2LDanmaku extends BaseDanmaku {
    protected static final long CORDON_RENDERING_TIME = 40;
    protected static final long MAX_RENDERING_TIME = 100;
    protected int mDistance;
    protected long mLastTime;
    protected float mStepX;
    protected float x = 0.0f;
    protected float y = -1.0f;
    protected float[] RECT = null;

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public int getType() {
        return 1;
    }

    public R2LDanmaku(Duration duration) {
        this.duration = duration;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
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

    protected float getAccurateLeft(IDisplayer iDisplayer, long j) {
        long actualTime = j - getActualTime();
        if (actualTime >= this.duration.value) {
            return -this.paintWidth;
        }
        return iDisplayer.getWidth() - (actualTime * this.mStepX);
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float[] getRectAtTime(IDisplayer iDisplayer, long j) {
        if (!isMeasured()) {
            return null;
        }
        float accurateLeft = getAccurateLeft(iDisplayer, j);
        if (this.RECT == null) {
            this.RECT = new float[4];
        }
        float[] fArr = this.RECT;
        fArr[0] = accurateLeft;
        fArr[1] = this.y;
        fArr[2] = accurateLeft + this.paintWidth;
        this.RECT[3] = this.y + this.paintHeight;
        return this.RECT;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getLeft() {
        return this.x;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getTop() {
        return this.y;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getRight() {
        return this.x + this.paintWidth;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getBottom() {
        return this.y + this.paintHeight;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public void measure(IDisplayer iDisplayer, boolean z) {
        super.measure(iDisplayer, z);
        int width = (int) (iDisplayer.getWidth() + this.paintWidth);
        this.mDistance = width;
        this.mStepX = width / this.duration.value;
    }
}
