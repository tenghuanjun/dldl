package master.flame.danmaku.danmaku.model;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class SpecialDanmaku extends BaseDanmaku {
    public long alphaDuration;
    public int beginAlpha;
    public float beginX;
    public float beginY;
    public int deltaAlpha;
    public float deltaX;
    public float deltaY;
    public int endAlpha;
    public float endX;
    public float endY;
    public LinePath[] linePaths;
    private ScaleFactor mScaleFactor;
    private int mScaleFactorChangedFlag;
    public float pivotX;
    public float pivotY;
    public float rotateX;
    public float rotateZ;
    public long translationDuration;
    public long translationStartDelay;
    private int mCurrentWidth = 0;
    private int mCurrentHeight = 0;
    public boolean isQuadraticEaseOut = false;
    private float[] currStateValues = new float[4];

    private static final float getQuadEaseOutProgress(long j, long j2) {
        float f = j / j2;
        return (-1.0f) * f * (f - 2.0f);
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public int getType() {
        return 7;
    }

    public static class ScaleFactor {
        int flag = 0;
        int height;
        float scaleX;
        float scaleY;
        int width;

        public ScaleFactor(int i, int i2, float f, float f2) {
            update(i, i2, f, f2);
        }

        public void update(int i, int i2, float f, float f2) {
            if (Float.compare(this.scaleX, f) != 0 || Float.compare(this.scaleY, f2) != 0) {
                this.flag++;
            }
            this.width = i;
            this.height = i2;
            this.scaleX = f;
            this.scaleY = f2;
        }

        public boolean isUpdated(int i, int i2, int i3) {
            return (this.flag == i || (this.width == i2 && this.height == i3)) ? false : true;
        }
    }

    private class Point {
        float x;
        float y;

        public Point(float f, float f2) {
            this.x = f;
            this.y = f2;
        }

        public float getDistance(Point point) {
            float fAbs = Math.abs(this.x - point.x);
            float fAbs2 = Math.abs(this.y - point.y);
            return (float) Math.sqrt((fAbs * fAbs) + (fAbs2 * fAbs2));
        }
    }

    public class LinePath {
        public long beginTime;
        float delatX;
        float deltaY;
        public long duration;
        public long endTime;
        Point pBegin;
        Point pEnd;

        public LinePath() {
        }

        public void setPoints(Point point, Point point2) {
            this.pBegin = point;
            this.pEnd = point2;
            this.delatX = point2.x - point.x;
            this.deltaY = point2.y - point.y;
        }

        public float getDistance() {
            return this.pEnd.getDistance(this.pBegin);
        }

        public float[] getBeginPoint() {
            return new float[]{this.pBegin.x, this.pBegin.y};
        }

        public float[] getEndPoint() {
            return new float[]{this.pEnd.x, this.pEnd.y};
        }
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public void measure(IDisplayer iDisplayer, boolean z) {
        super.measure(iDisplayer, z);
        if (this.mCurrentWidth == 0 || this.mCurrentHeight == 0) {
            this.mCurrentWidth = iDisplayer.getWidth();
            this.mCurrentHeight = iDisplayer.getHeight();
        }
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public void layout(IDisplayer iDisplayer, float f, float f2) {
        getRectAtTime(iDisplayer, this.mTimer.currMillisecond);
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float[] getRectAtTime(IDisplayer iDisplayer, long j) {
        LinePath linePath;
        int i;
        if (!isMeasured()) {
            return null;
        }
        if (this.mScaleFactor.isUpdated(this.mScaleFactorChangedFlag, this.mCurrentWidth, this.mCurrentHeight)) {
            float f = this.mScaleFactor.scaleX;
            float f2 = this.mScaleFactor.scaleY;
            setTranslationData(this.beginX * f, this.beginY * f2, this.endX * f, this.endY * f2, this.translationDuration, this.translationStartDelay);
            LinePath[] linePathArr = this.linePaths;
            if (linePathArr != null && linePathArr.length > 0) {
                int length = linePathArr.length;
                float[][] fArr = (float[][]) Array.newInstance((Class<?>) float.class, length + 1, 2);
                int i2 = 0;
                while (i2 < length) {
                    fArr[i2] = this.linePaths[i2].getBeginPoint();
                    int i3 = i2 + 1;
                    fArr[i3] = this.linePaths[i2].getEndPoint();
                    i2 = i3;
                }
                for (int i4 = 0; i4 < fArr.length; i4++) {
                    float[] fArr2 = fArr[i4];
                    fArr2[0] = fArr2[0] * f;
                    float[] fArr3 = fArr[i4];
                    fArr3[1] = fArr3[1] * f2;
                }
                setLinePathData(fArr);
            }
            this.mScaleFactorChangedFlag = this.mScaleFactor.flag;
            this.mCurrentWidth = this.mScaleFactor.width;
            this.mCurrentHeight = this.mScaleFactor.height;
        }
        long actualTime = j - getActualTime();
        long j2 = this.alphaDuration;
        if (j2 > 0 && (i = this.deltaAlpha) != 0) {
            if (actualTime >= j2) {
                this.alpha = this.endAlpha;
            } else {
                this.alpha = this.beginAlpha + ((int) (i * (actualTime / j2)));
            }
        }
        float f3 = this.beginX;
        float f4 = this.beginY;
        long j3 = actualTime - this.translationStartDelay;
        long j4 = this.translationDuration;
        if (j4 > 0 && j3 >= 0 && j3 <= j4) {
            LinePath[] linePathArr2 = this.linePaths;
            if (linePathArr2 != null) {
                int length2 = linePathArr2.length;
                int i5 = 0;
                while (true) {
                    if (i5 >= length2) {
                        linePath = null;
                        break;
                    }
                    LinePath linePath2 = linePathArr2[i5];
                    if (j3 >= linePath2.beginTime && j3 < linePath2.endTime) {
                        linePath = linePath2;
                        break;
                    }
                    f3 = linePath2.pEnd.x;
                    f4 = linePath2.pEnd.y;
                    i5++;
                }
                if (linePath != null) {
                    float f5 = linePath.delatX;
                    float f6 = linePath.deltaY;
                    float f7 = (actualTime - linePath.beginTime) / linePath.duration;
                    float f8 = linePath.pBegin.x;
                    float f9 = linePath.pBegin.y;
                    if (f5 != 0.0f) {
                        f3 = f8 + (f5 * f7);
                    }
                    if (f6 != 0.0f) {
                        f4 = f9 + (f6 * f7);
                    }
                }
            } else {
                float quadEaseOutProgress = this.isQuadraticEaseOut ? getQuadEaseOutProgress(j3, j4) : j3 / j4;
                float f10 = this.deltaX;
                if (f10 != 0.0f) {
                    f3 = this.beginX + (f10 * quadEaseOutProgress);
                }
                float f11 = this.deltaY;
                if (f11 != 0.0f) {
                    f4 = this.beginY + (f11 * quadEaseOutProgress);
                }
            }
        } else if (j3 > this.translationDuration) {
            f3 = this.endX;
            f4 = this.endY;
        }
        float[] fArr4 = this.currStateValues;
        fArr4[0] = f3;
        fArr4[1] = f4;
        fArr4[2] = f3 + this.paintWidth;
        this.currStateValues[3] = f4 + this.paintHeight;
        setVisibility(!isOutside());
        return this.currStateValues;
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getLeft() {
        return this.currStateValues[0];
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getTop() {
        return this.currStateValues[1];
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getRight() {
        return this.currStateValues[2];
    }

    @Override // master.flame.danmaku.danmaku.model.BaseDanmaku
    public float getBottom() {
        return this.currStateValues[3];
    }

    public void setTranslationData(float f, float f2, float f3, float f4, long j, long j2) {
        this.beginX = f;
        this.beginY = f2;
        this.endX = f3;
        this.endY = f4;
        this.deltaX = f3 - f;
        this.deltaY = f4 - f2;
        this.translationDuration = j;
        this.translationStartDelay = j2;
    }

    public void setAlphaData(int i, int i2, long j) {
        this.beginAlpha = i;
        this.endAlpha = i2;
        this.deltaAlpha = i2 - i;
        this.alphaDuration = j;
        if (i != AlphaValue.MAX) {
            this.alpha = i;
        }
    }

    public void setLinePathData(float[][] fArr) {
        LinePath[] linePathArr;
        if (fArr != null) {
            int length = fArr.length;
            int i = 0;
            this.beginX = fArr[0][0];
            this.beginY = fArr[0][1];
            int i2 = length - 1;
            this.endX = fArr[i2][0];
            this.endY = fArr[i2][1];
            if (fArr.length > 1) {
                this.linePaths = new LinePath[fArr.length - 1];
                int i3 = 0;
                while (true) {
                    linePathArr = this.linePaths;
                    if (i3 >= linePathArr.length) {
                        break;
                    }
                    linePathArr[i3] = new LinePath();
                    LinePath linePath = this.linePaths[i3];
                    Point point = new Point(fArr[i3][0], fArr[i3][1]);
                    i3++;
                    linePath.setPoints(point, new Point(fArr[i3][0], fArr[i3][1]));
                }
                float distance = 0.0f;
                for (LinePath linePath2 : linePathArr) {
                    distance += linePath2.getDistance();
                }
                LinePath linePath3 = null;
                LinePath[] linePathArr2 = this.linePaths;
                int length2 = linePathArr2.length;
                while (i < length2) {
                    LinePath linePath4 = linePathArr2[i];
                    linePath4.duration = (long) ((linePath4.getDistance() / distance) * this.translationDuration);
                    linePath4.beginTime = linePath3 == null ? 0L : linePath3.endTime;
                    linePath4.endTime = linePath4.beginTime + linePath4.duration;
                    i++;
                    linePath3 = linePath4;
                }
            }
        }
    }

    public void setScaleFactor(ScaleFactor scaleFactor) {
        this.mScaleFactor = scaleFactor;
        this.mScaleFactorChangedFlag = scaleFactor.flag;
    }
}
