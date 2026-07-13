package androidx.compose.animation.core;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: MonoSpline.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J&\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u000bj\b\u0012\u0004\u0012\u00020\u0003`\f2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0002J8\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0011H\u0002J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0003J\u0016\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0003J\u0018\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J8\u0010 \u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0011H\u0002J(\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u000bj\b\u0012\u0004\u0012\u00020\u0003`\f2\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u001eH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u000bj\b\u0012\u0004\u0012\u00020\u0003`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u000bj\b\u0012\u0004\u0012\u00020\u0003`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Landroidx/compose/animation/core/MonoSpline;", "", "time", "", "y", "", "([FLjava/util/List;)V", "isExtrapolate", "", "slopeTemp", "tangents", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "timePoints", "values", "copyData", "diff", "", "h", "x", "y1", "y2", "t1", "t2", "getPos", "", "t", "v", "Landroidx/compose/animation/core/AnimationVector;", "j", "", "getSlope", "interpolate", "makeFloatArray", "a", "b", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MonoSpline {
    public static final int $stable = 8;
    private final boolean isExtrapolate = true;
    private final float[] slopeTemp;
    private final ArrayList<float[]> tangents;
    private final float[] timePoints;
    private final ArrayList<float[]> values;

    private final float diff(float h, float x, float y1, float y2, float t1, float t2) {
        float f = x * x;
        float f2 = 6;
        float f3 = f2 * x;
        float f4 = (((((-6) * f) * y2) + (y2 * f3)) + ((f2 * f) * y1)) - (f3 * y1);
        float f5 = 3 * h;
        return ((((f4 + ((f5 * t2) * f)) + ((f5 * t1) * f)) - (((2 * h) * t2) * x)) - (((4 * h) * t1) * x)) + (h * t1);
    }

    private final float interpolate(float h, float x, float y1, float y2, float t1, float t2) {
        float f = x * x;
        float f2 = f * x;
        float f3 = 3 * f;
        float f4 = ((-2) * f2 * y2) + (y2 * f3);
        float f5 = 2;
        float f6 = t2 * h;
        float f7 = ((f4 + ((f5 * f2) * y1)) - (f3 * y1)) + y1 + (f6 * f2);
        float f8 = h * t1;
        return (((f7 + (f2 * f8)) - (f6 * f)) - (((f5 * h) * t1) * f)) + (f8 * x);
    }

    public MonoSpline(float[] fArr, List<float[]> list) {
        int i;
        int length = fArr.length;
        int length2 = list.get(0).length;
        this.slopeTemp = new float[length2];
        int i2 = length - 1;
        ArrayList<float[]> arrayListMakeFloatArray = makeFloatArray(i2, length2);
        ArrayList<float[]> arrayListMakeFloatArray2 = makeFloatArray(length, length2);
        for (int i3 = 0; i3 < length2; i3++) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i4 + 1;
                arrayListMakeFloatArray.get(i4)[i3] = (list.get(i5)[i3] - list.get(i4)[i3]) / (fArr[i5] - fArr[i4]);
                if (i4 == 0) {
                    arrayListMakeFloatArray2.get(i4)[i3] = arrayListMakeFloatArray.get(i4)[i3];
                } else {
                    arrayListMakeFloatArray2.get(i4)[i3] = (arrayListMakeFloatArray.get(i4 - 1)[i3] + arrayListMakeFloatArray.get(i4)[i3]) * 0.5f;
                }
                i4 = i5;
            }
            arrayListMakeFloatArray2.get(i2)[i3] = arrayListMakeFloatArray.get(length - 2)[i3];
        }
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = 0;
            while (i7 < length2) {
                if (arrayListMakeFloatArray.get(i6)[i7] == 0.0f) {
                    arrayListMakeFloatArray2.get(i6)[i7] = 0.0f;
                    arrayListMakeFloatArray2.get(i6 + 1)[i7] = 0.0f;
                    i = length2;
                } else {
                    float f = arrayListMakeFloatArray2.get(i6)[i7] / arrayListMakeFloatArray.get(i6)[i7];
                    int i8 = i6 + 1;
                    float f2 = arrayListMakeFloatArray2.get(i8)[i7] / arrayListMakeFloatArray.get(i6)[i7];
                    i = length2;
                    float fHypot = (float) Math.hypot(f, f2);
                    if (fHypot > 9.0d) {
                        float f3 = 3.0f / fHypot;
                        arrayListMakeFloatArray2.get(i6)[i7] = f * f3 * arrayListMakeFloatArray.get(i6)[i7];
                        arrayListMakeFloatArray2.get(i8)[i7] = f3 * f2 * arrayListMakeFloatArray.get(i6)[i7];
                    }
                }
                i7++;
                length2 = i;
            }
        }
        this.timePoints = fArr;
        this.values = copyData(list);
        this.tangents = arrayListMakeFloatArray2;
    }

    private final ArrayList<float[]> makeFloatArray(int a2, int b) {
        ArrayList<float[]> arrayList = new ArrayList<>();
        for (int i = 0; i < a2; i++) {
            arrayList.add(new float[b]);
        }
        return arrayList;
    }

    private final ArrayList<float[]> copyData(List<float[]> y) {
        ArrayList<float[]> arrayList = new ArrayList<>();
        arrayList.addAll(y);
        return arrayList;
    }

    public final void getPos(float t, float[] v) {
        int length = this.timePoints.length;
        int i = 0;
        int length2 = this.values.get(0).length;
        if (this.isExtrapolate) {
            float[] fArr = this.timePoints;
            float f = fArr[0];
            if (t <= f) {
                getSlope(f, this.slopeTemp);
                for (int i2 = 0; i2 < length2; i2++) {
                    v[i2] = this.values.get(0)[i2] + ((t - this.timePoints[0]) * this.slopeTemp[i2]);
                }
                return;
            }
            int i3 = length - 1;
            float f2 = fArr[i3];
            if (t >= f2) {
                getSlope(f2, this.slopeTemp);
                while (i < length2) {
                    v[i] = this.values.get(i3)[i] + ((t - this.timePoints[i3]) * this.slopeTemp[i]);
                    i++;
                }
                return;
            }
        } else {
            float[] fArr2 = this.timePoints;
            if (t <= fArr2[0]) {
                for (int i4 = 0; i4 < length2; i4++) {
                    v[i4] = this.values.get(0)[i4];
                }
                return;
            }
            int i5 = length - 1;
            if (t >= fArr2[i5]) {
                while (i < length2) {
                    v[i] = this.values.get(i5)[i];
                    i++;
                }
                return;
            }
        }
        int i6 = length - 1;
        int i7 = 0;
        while (i7 < i6) {
            if (t == this.timePoints[i7]) {
                for (int i8 = 0; i8 < length2; i8++) {
                    v[i8] = this.values.get(i7)[i8];
                }
            }
            float[] fArr3 = this.timePoints;
            int i9 = i7 + 1;
            float f3 = fArr3[i9];
            if (t < f3) {
                float f4 = fArr3[i7];
                float f5 = f3 - f4;
                float f6 = (t - f4) / f5;
                for (int i10 = 0; i10 < length2; i10++) {
                    v[i10] = interpolate(f5, f6, this.values.get(i7)[i10], this.values.get(i9)[i10], this.tangents.get(i7)[i10], this.tangents.get(i9)[i10]);
                }
                return;
            }
            i7 = i9;
        }
    }

    public final float getPos(float t, int j) {
        float f;
        float f2;
        float slope;
        float[] fArr = this.timePoints;
        int length = fArr.length;
        int i = 0;
        if (this.isExtrapolate) {
            if (t <= fArr[0]) {
                f = this.values.get(0)[j];
                float f3 = this.timePoints[0];
                f2 = t - f3;
                slope = getSlope(f3, j);
            } else {
                int i2 = length - 1;
                if (t >= fArr[i2]) {
                    f = this.values.get(i2)[j];
                    float f4 = this.timePoints[i2];
                    f2 = t - f4;
                    slope = getSlope(f4, j);
                }
            }
            return f + (f2 * slope);
        }
        if (t <= fArr[0]) {
            return this.values.get(0)[j];
        }
        int i3 = length - 1;
        if (t >= fArr[i3]) {
            return this.values.get(i3)[j];
        }
        int i4 = length - 1;
        while (i < i4) {
            float[] fArr2 = this.timePoints;
            float f5 = fArr2[i];
            if (t == f5) {
                return this.values.get(i)[j];
            }
            int i5 = i + 1;
            float f6 = fArr2[i5];
            if (t < f6) {
                float f7 = f6 - f5;
                return interpolate(f7, (t - f5) / f7, this.values.get(i)[j], this.values.get(i5)[j], this.tangents.get(i)[j], this.tangents.get(i5)[j]);
            }
            i = i5;
        }
        return 0.0f;
    }

    public final void getPos(float t, AnimationVector v) {
        int length = this.timePoints.length;
        int i = 0;
        int length2 = this.values.get(0).length;
        if (this.isExtrapolate) {
            float[] fArr = this.timePoints;
            float f = fArr[0];
            if (t <= f) {
                getSlope(f, this.slopeTemp);
                for (int i2 = 0; i2 < length2; i2++) {
                    v.set$animation_core_release(i2, this.values.get(0)[i2] + ((t - this.timePoints[0]) * this.slopeTemp[i2]));
                }
                return;
            }
            int i3 = length - 1;
            float f2 = fArr[i3];
            if (t >= f2) {
                getSlope(f2, this.slopeTemp);
                while (i < length2) {
                    v.set$animation_core_release(i, this.values.get(i3)[i] + ((t - this.timePoints[i3]) * this.slopeTemp[i]));
                    i++;
                }
                return;
            }
        } else {
            float[] fArr2 = this.timePoints;
            if (t <= fArr2[0]) {
                for (int i4 = 0; i4 < length2; i4++) {
                    v.set$animation_core_release(i4, this.values.get(0)[i4]);
                }
                return;
            }
            int i5 = length - 1;
            if (t >= fArr2[i5]) {
                while (i < length2) {
                    v.set$animation_core_release(i, this.values.get(i5)[i]);
                    i++;
                }
                return;
            }
        }
        int i6 = length - 1;
        int i7 = 0;
        while (i7 < i6) {
            if (t == this.timePoints[i7]) {
                for (int i8 = 0; i8 < length2; i8++) {
                    v.set$animation_core_release(i8, this.values.get(i7)[i8]);
                }
            }
            float[] fArr3 = this.timePoints;
            int i9 = i7 + 1;
            float f3 = fArr3[i9];
            if (t < f3) {
                float f4 = fArr3[i7];
                float f5 = f3 - f4;
                float f6 = (t - f4) / f5;
                for (int i10 = 0; i10 < length2; i10++) {
                    v.set$animation_core_release(i10, interpolate(f5, f6, this.values.get(i7)[i10], this.values.get(i9)[i10], this.tangents.get(i7)[i10], this.tangents.get(i9)[i10]));
                }
                return;
            }
            i7 = i9;
        }
    }

    public final void getSlope(float time, float[] v) {
        float f;
        int length = this.timePoints.length;
        int length2 = this.values.get(0).length;
        float[] fArr = this.timePoints;
        float f2 = fArr[0];
        if (time <= f2) {
            f = f2;
        } else {
            f = fArr[length - 1];
            if (time < f) {
                f = time;
            }
        }
        int i = length - 1;
        int i2 = 0;
        while (i2 < i) {
            float[] fArr2 = this.timePoints;
            int i3 = i2 + 1;
            float f3 = fArr2[i3];
            if (f <= f3) {
                float f4 = fArr2[i2];
                float f5 = f3 - f4;
                float f6 = (f - f4) / f5;
                for (int i4 = 0; i4 < length2; i4++) {
                    v[i4] = diff(f5, f6, this.values.get(i2)[i4], this.values.get(i3)[i4], this.tangents.get(i2)[i4], this.tangents.get(i3)[i4]) / f5;
                }
                return;
            }
            i2 = i3;
        }
    }

    public final void getSlope(float time, AnimationVector v) {
        float f;
        int length = this.timePoints.length;
        int length2 = this.values.get(0).length;
        float[] fArr = this.timePoints;
        float f2 = fArr[0];
        if (time <= f2) {
            f = f2;
        } else {
            f = fArr[length - 1];
            if (time < f) {
                f = time;
            }
        }
        int i = length - 1;
        int i2 = 0;
        while (i2 < i) {
            float[] fArr2 = this.timePoints;
            int i3 = i2 + 1;
            float f3 = fArr2[i3];
            if (f <= f3) {
                float f4 = fArr2[i2];
                float f5 = f3 - f4;
                float f6 = (f - f4) / f5;
                for (int i4 = 0; i4 < length2; i4++) {
                    v.set$animation_core_release(i4, diff(f5, f6, this.values.get(i2)[i4], this.values.get(i3)[i4], this.tangents.get(i2)[i4], this.tangents.get(i3)[i4]) / f5);
                }
                return;
            }
            i2 = i3;
        }
    }

    private final float getSlope(float time, int j) {
        float[] fArr = this.timePoints;
        int length = fArr.length;
        int i = 0;
        float f = fArr[0];
        if (time < f) {
            time = f;
        } else {
            float f2 = fArr[length - 1];
            if (time >= f2) {
                time = f2;
            }
        }
        int i2 = length - 1;
        while (i < i2) {
            float[] fArr2 = this.timePoints;
            int i3 = i + 1;
            float f3 = fArr2[i3];
            if (time <= f3) {
                float f4 = fArr2[i];
                float f5 = f3 - f4;
                return diff(f5, (time - f4) / f5, this.values.get(i)[j], this.values.get(i3)[j], this.tangents.get(i)[j], this.tangents.get(i3)[j]) / f5;
            }
            i = i3;
        }
        return 0.0f;
    }
}
