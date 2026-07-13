package androidx.compose.animation.core;

import androidx.compose.animation.core.KeyframesSpec;
import androidx.exifinterface.media.ExifInterface;
import com.bytedance.framwork.core.sdklib.DBHelper;
import kotlin.Metadata;

/* JADX INFO: compiled from: AnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u000eB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J,\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0001\u0010\n*\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\n0\rH\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/compose/animation/core/KeyframesWithSplineSpec;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/animation/core/DurationBasedAnimationSpec;", "config", "Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;", "(Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;)V", "getConfig", "()Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;", "vectorize", "Landroidx/compose/animation/core/VectorizedDurationBasedAnimationSpec;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "converter", "Landroidx/compose/animation/core/TwoWayConverter;", "KeyframesWithSplineSpecConfig", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KeyframesWithSplineSpec<T> implements DurationBasedAnimationSpec<T> {
    public static final int $stable = 0;
    private final KeyframesWithSplineSpecConfig<T> config;

    public KeyframesWithSplineSpec(KeyframesWithSplineSpecConfig<T> keyframesWithSplineSpecConfig) {
        this.config = keyframesWithSplineSpecConfig;
    }

    public final KeyframesWithSplineSpecConfig<T> getConfig() {
        return this.config;
    }

    /* JADX INFO: compiled from: AnimationSpec.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0006\u001a\u00028\u0001H\u0010¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/animation/core/KeyframesSpecBaseConfig;", "Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "()V", "createEntityFor", DBHelper.COL_VALUE, "createEntityFor$animation_core_release", "(Ljava/lang/Object;)Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class KeyframesWithSplineSpecConfig<T> extends KeyframesSpecBaseConfig<T, KeyframesSpec.KeyframeEntity<T>> {
        public static final int $stable = 0;

        public KeyframesWithSplineSpecConfig() {
            super(null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public KeyframesSpec.KeyframeEntity<T> createEntityFor$animation_core_release(T value) {
            return new KeyframesSpec.KeyframeEntity<>(value, null, 2, 0 == true ? 1 : 0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006a  */
    @Override // androidx.compose.animation.core.FiniteAnimationSpec, androidx.compose.animation.core.AnimationSpec
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <V extends androidx.compose.animation.core.AnimationVector> androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec<V> vectorize(androidx.compose.animation.core.TwoWayConverter<T, V> r20) {
        /*
            r19 = this;
            r0 = r19
            androidx.collection.MutableIntList r1 = new androidx.collection.MutableIntList
            r2 = 0
            r3 = 1
            r4 = 0
            r1.<init>(r2, r3, r4)
            androidx.collection.MutableIntObjectMap r5 = new androidx.collection.MutableIntObjectMap
            r5.<init>(r2, r3, r4)
            androidx.compose.animation.core.KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig<T> r3 = r0.config
            androidx.collection.MutableIntObjectMap r3 = r3.getKeyframes$animation_core_release()
            androidx.collection.IntObjectMap r3 = (androidx.collection.IntObjectMap) r3
            int[] r4 = r3.keys
            java.lang.Object[] r6 = r3.values
            long[] r3 = r3.metadata
            int r7 = r3.length
            int r7 = r7 + (-2)
            if (r7 < 0) goto L70
            r8 = 0
        L23:
            r9 = r3[r8]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r15 == 0) goto L6a
            int r11 = r8 - r7
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = 0
        L3d:
            if (r13 >= r11) goto L68
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.3E-322)
            int r18 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r18 >= 0) goto L63
            int r14 = r8 << 3
            int r14 = r14 + r13
            r15 = r4[r14]
            r14 = r6[r14]
            androidx.compose.animation.core.KeyframesSpec$KeyframeEntity r14 = (androidx.compose.animation.core.KeyframesSpec.KeyframeEntity) r14
            r1.add(r15)
            kotlin.jvm.functions.Function1 r2 = r20.getConvertToVector()
            java.lang.Object r14 = r14.getValue$animation_core_release()
            java.lang.Object r2 = r2.invoke(r14)
            r5.set(r15, r2)
        L63:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            r2 = 0
            goto L3d
        L68:
            if (r11 != r12) goto L70
        L6a:
            if (r8 == r7) goto L70
            int r8 = r8 + 1
            r2 = 0
            goto L23
        L70:
            r1.sort()
            androidx.compose.animation.core.VectorizedMonoSplineKeyframesSpec r2 = new androidx.compose.animation.core.VectorizedMonoSplineKeyframesSpec
            androidx.collection.IntList r1 = (androidx.collection.IntList) r1
            androidx.collection.IntObjectMap r5 = (androidx.collection.IntObjectMap) r5
            androidx.compose.animation.core.KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig<T> r3 = r0.config
            int r3 = r3.getDurationMillis()
            androidx.compose.animation.core.KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig<T> r4 = r0.config
            int r4 = r4.getDelayMillis()
            r2.<init>(r1, r5, r3, r4)
            androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec r2 = (androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.KeyframesWithSplineSpec.vectorize(androidx.compose.animation.core.TwoWayConverter):androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec");
    }
}
