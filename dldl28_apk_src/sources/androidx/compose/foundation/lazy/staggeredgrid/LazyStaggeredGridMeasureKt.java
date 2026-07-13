package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.exifinterface.media.ExifInterface;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.tencent.connect.common.Constants;
import com.volcengine.common.contant.CommonConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyStaggeredGridMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0082\b\u001a5\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\n0\u000e¢\u0006\u0002\b\u000fH\u0083\b¢\u0006\u0002\u0010\u0010\u001aR\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u000e2!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u000e2\u0006\u0010\u001a\u001a\u00020\u0001H\u0083\b\u001a;\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u00142\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0003H\u0002¢\u0006\u0002\u0010\"\u001a\u001d\u0010#\u001a\u00020\b*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u001e0\u001dH\u0002¢\u0006\u0002\u0010$\u001a\u001c\u0010%\u001a\u00020\u0005*\u00020\u00142\u0006\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020\u0003H\u0002\u001a7\u0010(\u001a\u00020\u0005\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00122\b\b\u0002\u0010)\u001a\u00020\u00012\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00050\u000eH\u0082\b\u001a\u001c\u0010+\u001a\u00020\u0003*\u00020\u00142\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0003H\u0002\u001a+\u0010.\u001a\u00020\u0005*\u00020/2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u000eH\u0082\bø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\f\u00102\u001a\u00020\u0003*\u00020 H\u0002\u001a2\u00103\u001a\u00020\u0003\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u001d2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00030\u000eH\u0082\b¢\u0006\u0002\u00104\u001a\u0016\u00105\u001a\u00020\u0003*\u00020 2\b\b\u0002\u00106\u001a\u00020\u0003H\u0000\u001a\u001e\u00107\u001a\u00020\u0003*\u00020 2\u0006\u00108\u001a\u00020/H\u0002ø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001a,\u0010;\u001a\u00020<*\u00020\u00142\u0006\u0010=\u001a\u00020\u00032\u0006\u0010>\u001a\u00020 2\u0006\u0010?\u001a\u00020 2\u0006\u0010@\u001a\u00020\u0001H\u0003\u001a\u0084\u0001\u0010A\u001a\u00020<*\u00020\f2\u0006\u0010B\u001a\u00020C2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u00012\u0006\u0010L\u001a\u00020\u00012\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\u00032\u0006\u0010P\u001a\u00020\u00032\u0006\u0010Q\u001a\u00020\u00032\u0006\u0010R\u001a\u00020\u00032\u0006\u0010S\u001a\u00020TH\u0001ø\u0001\u0000¢\u0006\u0004\bU\u0010V\u001a\u0014\u0010W\u001a\u00020\u0005*\u00020 2\u0006\u0010X\u001a\u00020\u0003H\u0002\u001a!\u0010Y\u001a\u00020 *\u00020 2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eH\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006Z"}, d2 = {"DebugLoggingEnabled", "", "Unset", "", "debugLog", "", CommonConstants.KEY_MESSAGE, "Lkotlin/Function0;", "", "withDebugLogging", ExifInterface.GPS_DIRECTION_TRUE, Constants.PARAM_SCOPE, "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "calculateExtraItems", "", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;", ImageSelector.POSITION, "filter", "Lkotlin/ParameterName;", "name", "itemIndex", "beforeVisibleBounds", "calculateVisibleItems", "measuredItems", "", "Lkotlin/collections/ArrayDeque;", "itemScrollOffsets", "", "mainAxisLayoutSize", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;[Lkotlin/collections/ArrayDeque;[II)Ljava/util/List;", "debugRender", "([Lkotlin/collections/ArrayDeque;)Ljava/lang/String;", "ensureIndicesInRange", "indices", "itemCount", "fastForEach", "reverse", "action", "findPreviousItemIndex", "item", "lane", "forEach", "Landroidx/compose/foundation/lazy/staggeredgrid/SpanRange;", "forEach-nIS5qE8", "(JLkotlin/jvm/functions/Function1;)V", "indexOfMaxValue", "indexOfMinBy", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "indexOfMinValue", "minBound", "maxInRange", "indexRange", "maxInRange-jy6DScQ", "([IJ)I", "measure", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "initialScrollDelta", "initialItemIndices", "initialItemOffsets", "canRestartMeasure", "measureStaggeredGrid", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "pinnedItems", "itemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "resolvedSlots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "reverseLayout", "contentOffset", "Landroidx/compose/ui/unit/IntOffset;", "mainAxisAvailableSize", "mainAxisSpacing", "beforeContentPadding", "afterContentPadding", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "measureStaggeredGrid-sdzDtKU", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Ljava/util/List;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;JZZJIIIILkotlinx/coroutines/CoroutineScope;)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "offsetBy", "delta", "transform", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridMeasureKt {
    private static final boolean DebugLoggingEnabled = false;
    private static final int Unset = Integer.MIN_VALUE;

    private static final void debugLog(Function0<String> function0) {
    }

    private static final <T> T withDebugLogging(LazyLayoutMeasureScope lazyLayoutMeasureScope, Function1<? super LazyLayoutMeasureScope, ? extends T> function1) {
        return function1.invoke(lazyLayoutMeasureScope);
    }

    private static final String debugRender(ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr) {
        return "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: measureStaggeredGrid-sdzDtKU, reason: not valid java name */
    public static final LazyStaggeredGridMeasureResult m807measureStaggeredGridsdzDtKU(LazyLayoutMeasureScope lazyLayoutMeasureScope, LazyStaggeredGridState lazyStaggeredGridState, List<Integer> list, LazyStaggeredGridItemProvider lazyStaggeredGridItemProvider, LazyStaggeredGridSlots lazyStaggeredGridSlots, long j, boolean z, boolean z2, long j2, int i, int i2, int i3, int i4, CoroutineScope coroutineScope) {
        T t;
        int iM806maxInRangejy6DScQ;
        T t2;
        int i5;
        LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext = new LazyStaggeredGridMeasureContext(lazyStaggeredGridState, list, lazyStaggeredGridItemProvider, lazyStaggeredGridSlots, j, z, lazyLayoutMeasureScope, i, j2, i3, i4, z2, i2, coroutineScope, null);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                int[] iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release = lazyStaggeredGridState.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(lazyStaggeredGridItemProvider, lazyStaggeredGridState.getScrollPosition().getIndices());
                int[] scrollOffsets = lazyStaggeredGridState.getScrollPosition().getScrollOffsets();
                if (iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release.length == lazyStaggeredGridMeasureContext.getLaneCount()) {
                    t = iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release;
                } else {
                    lazyStaggeredGridMeasureContext.getLaneInfo().reset();
                    int laneCount = lazyStaggeredGridMeasureContext.getLaneCount();
                    int[] iArr = new int[laneCount];
                    int i6 = 0;
                    while (i6 < laneCount) {
                        if (i6 >= iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release.length || (iM806maxInRangejy6DScQ = iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release[i6]) == -1) {
                            iM806maxInRangejy6DScQ = i6 == 0 ? 0 : m806maxInRangejy6DScQ(iArr, SpanRange.m816constructorimpl(0, i6)) + 1;
                        }
                        iArr[i6] = iM806maxInRangejy6DScQ;
                        lazyStaggeredGridMeasureContext.getLaneInfo().setLane(iArr[i6], i6);
                        i6++;
                    }
                    t = iArr;
                }
                objectRef.element = t;
                if (scrollOffsets.length == lazyStaggeredGridMeasureContext.getLaneCount()) {
                    t2 = scrollOffsets;
                } else {
                    int laneCount2 = lazyStaggeredGridMeasureContext.getLaneCount();
                    int[] iArr2 = new int[laneCount2];
                    int i7 = 0;
                    while (i7 < laneCount2) {
                        if (i7 < scrollOffsets.length) {
                            i5 = scrollOffsets[i7];
                        } else {
                            i5 = i7 == 0 ? 0 : iArr2[i7 - 1];
                        }
                        iArr2[i7] = i5;
                        i7++;
                    }
                    t2 = iArr2;
                }
                objectRef2.element = t2;
                Unit unit = Unit.INSTANCE;
                snapshotCreateNonObservableSnapshot.dispose();
                return measure(lazyStaggeredGridMeasureContext, MathKt.roundToInt(lazyStaggeredGridState.getScrollToBeConsumed()), (int[]) objectRef.element, (int[]) objectRef2.element, true);
            } finally {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } catch (Throwable th) {
            snapshotCreateNonObservableSnapshot.dispose();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:148:0x02fa, code lost:
    
        r6 = indexOfMinValue$default(r8, 0, 1, null);
        r7 = indexOfMaxValue(r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0305, code lost:
    
        if (r6 == r7) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x030b, code lost:
    
        if (r8[r6] != r8[r7]) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x030d, code lost:
    
        r6 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x030f, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0310, code lost:
    
        r7 = r29[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0313, code lost:
    
        if (r7 != (-1)) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0315, code lost:
    
        r7 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0316, code lost:
    
        r7 = findPreviousItemIndex(r42, r7, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x031a, code lost:
    
        if (r7 >= 0) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x031c, code lost:
    
        r14 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x031e, code lost:
    
        if (r1 != false) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0324, code lost:
    
        if (measure$lambda$38$misalignedStart(r14, r42, r8, r6) == false) goto L172;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x0326, code lost:
    
        if (r46 == false) goto L172;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0328, code lost:
    
        r42.getLaneInfo().reset();
        r1 = r14.length;
        r2 = new int[r1];
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0333, code lost:
    
        if (r3 >= r1) goto L431;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0335, code lost:
    
        r2[r3] = -1;
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x033b, code lost:
    
        r1 = r8.length;
        r3 = new int[r1];
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x033f, code lost:
    
        if (r4 >= r1) goto L432;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x0341, code lost:
    
        r3[r4] = r8[r6];
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x034f, code lost:
    
        return measure(r42, r27, r2, r3, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0350, code lost:
    
        r10 = r27;
        r44 = r3;
        r31 = r4;
        r32 = r5;
        r26 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x035c, code lost:
    
        r10 = r27;
        r14 = r29;
        r32 = r5;
        r5 = r42.m803getSpanRangelOCCd4c(r42.getItemProvider(), r7, r6);
        r13 = r42.getLaneInfo();
        r44 = r3;
        r31 = r4;
        r4 = (int) (r5 & 4294967295L);
        r26 = r11;
        r27 = r12;
        r3 = (int) (r5 >> 32);
        r11 = r4 - r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0386, code lost:
    
        if (r11 == 1) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x0388, code lost:
    
        r20 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x038b, code lost:
    
        r20 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x038d, code lost:
    
        if (r20 == false) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x038f, code lost:
    
        r12 = -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x0391, code lost:
    
        r12 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x0392, code lost:
    
        r13.setLane(r7, r12);
        r12 = r42.getMeasuredItemProvider().m811getAndMeasurejy6DScQ(r7, r5);
        r5 = m806maxInRangejy6DScQ(r8, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x03a2, code lost:
    
        if (r11 == 1) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x03a4, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x03a6, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x03a7, code lost:
    
        if (r6 == false) goto L186;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x03a9, code lost:
    
        r6 = r42.getLaneInfo().getGaps(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x03b2, code lost:
    
        r6 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x03b3, code lost:
    
        if (r3 >= r4) goto L427;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x03b7, code lost:
    
        if (r8[r3] == r5) goto L191;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x03b9, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x03ba, code lost:
    
        r9[r3].addFirst(r12);
        r14[r3] = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x03c1, code lost:
    
        if (r6 != null) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x03c3, code lost:
    
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x03c5, code lost:
    
        r11 = r6[r3];
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x03c7, code lost:
    
        r8[r3] = (r12.getSizeWithSpacings() + r5) + r11;
        r3 = r3 + 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:336:0x06df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResult measure(final androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureContext r42, int r43, int[] r44, int[] r45, boolean r46) {
        /*
            Method dump skipped, instruction units count: 2090
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt.measure(androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureContext, int, int[], int[], boolean):androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResult");
    }

    private static final boolean measure$lambda$38$hasSpaceBeforeFirst(int[] iArr, int[] iArr2, LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = iArr[i];
            if (iArr2[i] < Math.max(-lazyStaggeredGridMeasureContext.getMainAxisSpacing(), 0) && i2 > 0) {
                return true;
            }
        }
        return false;
    }

    private static final boolean measure$lambda$38$misalignedStart(int[] iArr, LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int[] iArr2, int i) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[i2], i2) == -1 && iArr2[i2] != iArr2[i]) {
                return true;
            }
        }
        int length2 = iArr.length;
        for (int i3 = 0; i3 < length2; i3++) {
            if (findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[i3], i3) != -1 && iArr2[i3] >= iArr2[i]) {
                return true;
            }
        }
        int lane = lazyStaggeredGridMeasureContext.getLaneInfo().getLane(0);
        return (lane == 0 || lane == -1 || lane == -2) ? false : true;
    }

    private static final List<LazyStaggeredGridMeasuredItem> calculateVisibleItems(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr, int[] iArr, int i) {
        int size = 0;
        for (ArrayDeque<LazyStaggeredGridMeasuredItem> arrayDeque : arrayDequeArr) {
            size += arrayDeque.size();
        }
        ArrayList arrayList = new ArrayList(size);
        while (true) {
            for (ArrayDeque<LazyStaggeredGridMeasuredItem> arrayDeque2 : arrayDequeArr) {
                if (!arrayDeque2.isEmpty()) {
                    int length = arrayDequeArr.length;
                    int i2 = -1;
                    int i3 = Integer.MAX_VALUE;
                    for (int i4 = 0; i4 < length; i4++) {
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemFirstOrNull = arrayDequeArr[i4].firstOrNull();
                        int index = lazyStaggeredGridMeasuredItemFirstOrNull != null ? lazyStaggeredGridMeasuredItemFirstOrNull.getIndex() : Integer.MAX_VALUE;
                        if (i3 > index) {
                            i2 = i4;
                            i3 = index;
                        }
                    }
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemRemoveFirst = arrayDequeArr[i2].removeFirst();
                    if (lazyStaggeredGridMeasuredItemRemoveFirst.getLane() == i2) {
                        long jM816constructorimpl = SpanRange.m816constructorimpl(lazyStaggeredGridMeasuredItemRemoveFirst.getLane(), lazyStaggeredGridMeasuredItemRemoveFirst.getSpan());
                        int iM806maxInRangejy6DScQ = m806maxInRangejy6DScQ(iArr, jM816constructorimpl);
                        int i5 = lazyStaggeredGridMeasureContext.getResolvedSlots().getPositions()[i2];
                        if (lazyStaggeredGridMeasuredItemRemoveFirst.getPlaceablesCount() != 0) {
                            lazyStaggeredGridMeasuredItemRemoveFirst.position(iM806maxInRangejy6DScQ, i5, i);
                            arrayList.add(lazyStaggeredGridMeasuredItemRemoveFirst);
                            int i6 = (int) (jM816constructorimpl & 4294967295L);
                            for (int i7 = (int) (jM816constructorimpl >> 32); i7 < i6; i7++) {
                                iArr[i7] = lazyStaggeredGridMeasuredItemRemoveFirst.getSizeWithSpacings() + iM806maxInRangejy6DScQ;
                            }
                        }
                    }
                }
            }
            return arrayList;
        }
    }

    private static final List<LazyStaggeredGridMeasuredItem> calculateExtraItems(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, Function1<? super LazyStaggeredGridMeasuredItem, Unit> function1, Function1<? super Integer, Boolean> function12, boolean z) {
        List<Integer> pinnedItems = lazyStaggeredGridMeasureContext.getPinnedItems();
        ArrayList arrayList = null;
        if (z) {
            int size = pinnedItems.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i = size - 1;
                    int iIntValue = pinnedItems.get(size).intValue();
                    if (function12.invoke(Integer.valueOf(iIntValue)).booleanValue()) {
                        long jM803getSpanRangelOCCd4c = lazyStaggeredGridMeasureContext.m803getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext.getItemProvider(), iIntValue, 0);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM811getAndMeasurejy6DScQ = lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m811getAndMeasurejy6DScQ(iIntValue, jM803getSpanRangelOCCd4c);
                        function1.invoke(lazyStaggeredGridMeasuredItemM811getAndMeasurejy6DScQ);
                        arrayList.add(lazyStaggeredGridMeasuredItemM811getAndMeasurejy6DScQ);
                    }
                    if (i < 0) {
                        break;
                    }
                    size = i;
                }
            }
        } else {
            int size2 = pinnedItems.size();
            for (int i2 = 0; i2 < size2; i2++) {
                int iIntValue2 = pinnedItems.get(i2).intValue();
                if (function12.invoke(Integer.valueOf(iIntValue2)).booleanValue()) {
                    long jM803getSpanRangelOCCd4c2 = lazyStaggeredGridMeasureContext.m803getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext.getItemProvider(), iIntValue2, 0);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM811getAndMeasurejy6DScQ2 = lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m811getAndMeasurejy6DScQ(iIntValue2, jM803getSpanRangelOCCd4c2);
                    function1.invoke(lazyStaggeredGridMeasuredItemM811getAndMeasurejy6DScQ2);
                    arrayList.add(lazyStaggeredGridMeasuredItemM811getAndMeasurejy6DScQ2);
                }
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    /* JADX INFO: renamed from: forEach-nIS5qE8, reason: not valid java name */
    private static final void m805forEachnIS5qE8(long j, Function1<? super Integer, Unit> function1) {
        int i = (int) (j & 4294967295L);
        for (int i2 = (int) (j >> 32); i2 < i; i2++) {
            function1.invoke(Integer.valueOf(i2));
        }
    }

    private static final void offsetBy(int[] iArr, int i) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = iArr[i2] + i;
        }
    }

    /* JADX INFO: renamed from: maxInRange-jy6DScQ, reason: not valid java name */
    private static final int m806maxInRangejy6DScQ(int[] iArr, long j) {
        int i = (int) (j & 4294967295L);
        int iMax = Integer.MIN_VALUE;
        for (int i2 = (int) (j >> 32); i2 < i; i2++) {
            iMax = Math.max(iMax, iArr[i2]);
        }
        return iMax;
    }

    public static /* synthetic */ int indexOfMinValue$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Integer.MIN_VALUE;
        }
        return indexOfMinValue(iArr, i);
    }

    public static final int indexOfMinValue(int[] iArr, int i) {
        int length = iArr.length;
        int i2 = -1;
        int i3 = Integer.MAX_VALUE;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = i + 1;
            int i6 = iArr[i4];
            if (i5 <= i6 && i6 < i3) {
                i2 = i4;
                i3 = i6;
            }
        }
        return i2;
    }

    private static final <T> int indexOfMinBy(T[] tArr, Function1<? super T, Integer> function1) {
        int length = tArr.length;
        int i = -1;
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < length; i3++) {
            int iIntValue = function1.invoke(tArr[i3]).intValue();
            if (i2 > iIntValue) {
                i = i3;
                i2 = iIntValue;
            }
        }
        return i;
    }

    private static final int indexOfMaxValue(int[] iArr) {
        int length = iArr.length;
        int i = -1;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = iArr[i3];
            if (i2 < i4) {
                i = i3;
                i2 = i4;
            }
        }
        return i;
    }

    private static final int[] transform(int[] iArr, Function1<? super Integer, Integer> function1) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            iArr[i] = function1.invoke(Integer.valueOf(iArr[i])).intValue();
        }
        return iArr;
    }

    private static final void ensureIndicesInRange(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int[] iArr, int i) {
        int length = iArr.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i2 = length - 1;
            while (true) {
                if (iArr[length] < i && lazyStaggeredGridMeasureContext.getLaneInfo().assignedToLane(iArr[length], length)) {
                    break;
                } else {
                    iArr[length] = findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[length], length);
                }
            }
            if (iArr[length] >= 0 && !lazyStaggeredGridMeasureContext.isFullSpan(lazyStaggeredGridMeasureContext.getItemProvider(), iArr[length])) {
                lazyStaggeredGridMeasureContext.getLaneInfo().setLane(iArr[length], length);
            }
            if (i2 < 0) {
                return;
            } else {
                length = i2;
            }
        }
    }

    private static final int findPreviousItemIndex(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int i, int i2) {
        return lazyStaggeredGridMeasureContext.getLaneInfo().findPreviousItemIndex(i, i2);
    }

    private static final <T> void fastForEach(List<? extends T> list, boolean z, Function1<? super T, Unit> function1) {
        if (z) {
            int size = list.size() - 1;
            if (size < 0) {
                return;
            }
            while (true) {
                int i = size - 1;
                function1.invoke(list.get(size));
                if (i < 0) {
                    return;
                } else {
                    size = i;
                }
            }
        } else {
            int size2 = list.size();
            for (int i2 = 0; i2 < size2; i2++) {
                function1.invoke(list.get(i2));
            }
        }
    }

    static /* synthetic */ void fastForEach$default(List list, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if (z) {
            int size = list.size() - 1;
            if (size < 0) {
                return;
            }
            while (true) {
                int i2 = size - 1;
                function1.invoke(list.get(size));
                if (i2 < 0) {
                    return;
                } else {
                    size = i2;
                }
            }
        } else {
            int size2 = list.size();
            for (int i3 = 0; i3 < size2; i3++) {
                function1.invoke(list.get(i3));
            }
        }
    }
}
