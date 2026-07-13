package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Dp;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: DragGestureDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a5\u0010\u0015\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00180\u0017H\u0082Hø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a!\u0010\u001b\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0014\u001aa\u0010\u001d\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001f26\u0010 \u001a2\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\r¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020&0!H\u0080@ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001aY\u0010)\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u001226\u0010*\u001a2\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\r¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020&0!H\u0086@ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a!\u0010-\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0004\b.\u0010\u0014\u001aK\u0010/\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00100\u001a\u00020\u00012\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020&0!H\u0080Hø\u0001\u0000¢\u0006\u0004\b2\u00103\u001aY\u00104\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u001226\u0010*\u001a2\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020&0!H\u0086@ø\u0001\u0000¢\u0006\u0004\b5\u0010,\u001a!\u00106\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0004\b7\u0010\u0014\u001aa\u00108\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001f26\u0010*\u001a2\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\r¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020&0!H\u0080@ø\u0001\u0000¢\u0006\u0004\b9\u0010(\u001aY\u0010:\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u001226\u0010*\u001a2\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\r¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020&0!H\u0086@ø\u0001\u0000¢\u0006\u0004\b;\u0010,\u001a\u0080\u0001\u0010<\u001a\u00020&*\u00020=2\u0014\b\u0002\u0010>\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020&0\u00172\u000e\b\u0002\u0010?\u001a\b\u0012\u0004\u0012\u00020&0@2\u000e\b\u0002\u0010A\u001a\b\u0012\u0004\u0012\u00020&0@26\u0010B\u001a2\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(C\u0012\u0004\u0012\u00020&0!H\u0086@¢\u0006\u0002\u0010D\u001a\u0080\u0001\u0010E\u001a\u00020&*\u00020=2\u0014\b\u0002\u0010>\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020&0\u00172\u000e\b\u0002\u0010?\u001a\b\u0012\u0004\u0012\u00020&0@2\u000e\b\u0002\u0010A\u001a\b\u0012\u0004\u0012\u00020&0@26\u0010B\u001a2\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(C\u0012\u0004\u0012\u00020&0!H\u0086@¢\u0006\u0002\u0010D\u001a\u0080\u0001\u0010F\u001a\u00020&*\u00020=2\u0014\b\u0002\u0010>\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020&0\u00172\u000e\b\u0002\u0010?\u001a\b\u0012\u0004\u0012\u00020&0@2\u000e\b\u0002\u0010A\u001a\b\u0012\u0004\u0012\u00020&0@26\u0010G\u001a2\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\r¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(C\u0012\u0004\u0012\u00020&0!H\u0086@¢\u0006\u0002\u0010D\u001a\u0080\u0001\u0010H\u001a\u00020&*\u00020=2\u0014\b\u0002\u0010>\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020&0\u00172\u000e\b\u0002\u0010?\u001a\b\u0012\u0004\u0012\u00020&0@2\u000e\b\u0002\u0010A\u001a\b\u0012\u0004\u0012\u00020&0@26\u0010I\u001a2\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\r¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(C\u0012\u0004\u0012\u00020&0!H\u0086@¢\u0006\u0002\u0010D\u001a3\u0010J\u001a\u00020\u0018*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020&0\u0017H\u0086@ø\u0001\u0000¢\u0006\u0004\bK\u0010\u001a\u001a]\u0010J\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020&0\u00172\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00180\u00172\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00180\u0017H\u0080Hø\u0001\u0000¢\u0006\u0004\bM\u0010N\u001a3\u0010O\u001a\u00020\u0018*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020&0\u0017H\u0086@ø\u0001\u0000¢\u0006\u0004\bP\u0010\u001a\u001a\u001e\u0010Q\u001a\u00020\u0018*\u00020R2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002ø\u0001\u0000¢\u0006\u0004\bS\u0010T\u001a\u001e\u0010U\u001a\u00020\r*\u00020V2\u0006\u0010\u001e\u001a\u00020\u001fH\u0000ø\u0001\u0000¢\u0006\u0004\bW\u0010X\u001a\f\u0010Y\u001a\u00020\u0001*\u00020ZH\u0000\u001a3\u0010[\u001a\u00020\u0018*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020&0\u0017H\u0086@ø\u0001\u0000¢\u0006\u0004\b\\\u0010\u001a\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0014\u0010\u0006\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n\"\u0010\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n\"\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006]"}, d2 = {"BidirectionalPointerDirectionConfig", "Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "getBidirectionalPointerDirectionConfig", "()Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "HorizontalPointerDirectionConfig", "getHorizontalPointerDirectionConfig", "VerticalPointerDirectionConfig", "getVerticalPointerDirectionConfig", "defaultTouchSlop", "Landroidx/compose/ui/unit/Dp;", "F", "mouseSlop", "mouseToTouchSlopRatio", "", "awaitDragOrCancellation", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "awaitDragOrCancellation-rnUCldI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitDragOrUp", "hasDragged", "Lkotlin/Function1;", "", "awaitDragOrUp-jO51t88", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitHorizontalDragOrCancellation", "awaitHorizontalDragOrCancellation-rnUCldI", "awaitHorizontalPointerSlopOrCancellation", "pointerType", "Landroidx/compose/ui/input/pointer/PointerType;", "onPointerSlopReached", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "change", "overSlop", "", "awaitHorizontalPointerSlopOrCancellation-gDDlDlE", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitHorizontalTouchSlopOrCancellation", "onTouchSlopReached", "awaitHorizontalTouchSlopOrCancellation-jO51t88", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitLongPressOrCancellation", "awaitLongPressOrCancellation-rnUCldI", "awaitPointerSlopOrCancellation", "pointerDirectionConfig", "Landroidx/compose/ui/geometry/Offset;", "awaitPointerSlopOrCancellation-pn7EDYM", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILandroidx/compose/foundation/gestures/PointerDirectionConfig;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitTouchSlopOrCancellation", "awaitTouchSlopOrCancellation-jO51t88", "awaitVerticalDragOrCancellation", "awaitVerticalDragOrCancellation-rnUCldI", "awaitVerticalPointerSlopOrCancellation", "awaitVerticalPointerSlopOrCancellation-gDDlDlE", "awaitVerticalTouchSlopOrCancellation", "awaitVerticalTouchSlopOrCancellation-jO51t88", "detectDragGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onDragStart", "onDragEnd", "Lkotlin/Function0;", "onDragCancel", "onDrag", "dragAmount", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectDragGesturesAfterLongPress", "detectHorizontalDragGestures", "onHorizontalDrag", "detectVerticalDragGestures", "onVerticalDrag", "drag", "drag-jO51t88", "motionConsumed", "drag-VnAYq1g", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "horizontalDrag", "horizontalDrag-jO51t88", "isPointerUp", "Landroidx/compose/ui/input/pointer/PointerEvent;", "isPointerUp-DmW0f2w", "(Landroidx/compose/ui/input/pointer/PointerEvent;J)Z", "pointerSlop", "Landroidx/compose/ui/platform/ViewConfiguration;", "pointerSlop-E8SPZFQ", "(Landroidx/compose/ui/platform/ViewConfiguration;I)F", "toPointerDirectionConfig", "Landroidx/compose/foundation/gestures/Orientation;", "verticalDrag", "verticalDrag-jO51t88", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DragGestureDetectorKt {
    private static final float defaultTouchSlop;
    private static final float mouseSlop;
    private static final float mouseToTouchSlopRatio;
    private static final PointerDirectionConfig HorizontalPointerDirectionConfig = new PointerDirectionConfig() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$HorizontalPointerDirectionConfig$1
        @Override // androidx.compose.foundation.gestures.PointerDirectionConfig
        /* JADX INFO: renamed from: calculateDeltaChange-k-4lQ0M */
        public float mo385calculateDeltaChangek4lQ0M(long offset) {
            return Math.abs(Offset.m3219getXimpl(offset));
        }

        @Override // androidx.compose.foundation.gestures.PointerDirectionConfig
        /* JADX INFO: renamed from: calculatePostSlopOffset-8S9VItk */
        public long mo386calculatePostSlopOffset8S9VItk(long totalPositionChange, float touchSlop) {
            return OffsetKt.Offset(Offset.m3219getXimpl(totalPositionChange) - (Math.signum(Offset.m3219getXimpl(totalPositionChange)) * touchSlop), Offset.m3220getYimpl(totalPositionChange));
        }
    };
    private static final PointerDirectionConfig VerticalPointerDirectionConfig = new PointerDirectionConfig() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$VerticalPointerDirectionConfig$1
        @Override // androidx.compose.foundation.gestures.PointerDirectionConfig
        /* JADX INFO: renamed from: calculateDeltaChange-k-4lQ0M */
        public float mo385calculateDeltaChangek4lQ0M(long offset) {
            return Math.abs(Offset.m3220getYimpl(offset));
        }

        @Override // androidx.compose.foundation.gestures.PointerDirectionConfig
        /* JADX INFO: renamed from: calculatePostSlopOffset-8S9VItk */
        public long mo386calculatePostSlopOffset8S9VItk(long totalPositionChange, float touchSlop) {
            return OffsetKt.Offset(Offset.m3219getXimpl(totalPositionChange), Offset.m3220getYimpl(totalPositionChange) - (Math.signum(Offset.m3220getYimpl(totalPositionChange)) * touchSlop));
        }
    };
    private static final PointerDirectionConfig BidirectionalPointerDirectionConfig = new PointerDirectionConfig() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$BidirectionalPointerDirectionConfig$1
        @Override // androidx.compose.foundation.gestures.PointerDirectionConfig
        /* JADX INFO: renamed from: calculateDeltaChange-k-4lQ0M, reason: not valid java name */
        public float mo385calculateDeltaChangek4lQ0M(long offset) {
            return Offset.m3217getDistanceimpl(offset);
        }

        @Override // androidx.compose.foundation.gestures.PointerDirectionConfig
        /* JADX INFO: renamed from: calculatePostSlopOffset-8S9VItk, reason: not valid java name */
        public long mo386calculatePostSlopOffset8S9VItk(long totalPositionChange, float touchSlop) {
            return Offset.m3223minusMKHz9U(totalPositionChange, Offset.m3226timestuRUvjQ(Offset.m3214divtuRUvjQ(totalPositionChange, mo385calculateDeltaChangek4lQ0M(totalPositionChange)), touchSlop));
        }
    };

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00fe A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x0139 -> B:20:0x00b1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x017d -> B:56:0x0184). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x01a6 -> B:60:0x018d). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m374awaitTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r21, long r22, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r24, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r25) {
        /*
            Method dump skipped, instruction units count: 433
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m374awaitTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0049 -> B:18:0x004c). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: drag-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m380dragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r4, long r5, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1
            if (r0 == 0) goto L14
            r0 = r8
            androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1 r0 = (androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1 r0 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            java.lang.Object r4 = r0.L$1
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r5 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
            kotlin.ResultKt.throwOnFailure(r8)
            r7 = r4
            r4 = r5
            goto L4c
        L34:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3c:
            kotlin.ResultKt.throwOnFailure(r8)
        L3f:
            r0.L$0 = r4
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = m366awaitDragOrCancellationrnUCldI(r4, r5, r0)
            if (r8 != r1) goto L4c
            return r1
        L4c:
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
            if (r8 != 0) goto L56
            r4 = 0
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r4
        L56:
            boolean r5 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUpIgnoreConsumed(r8)
            if (r5 == 0) goto L61
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r4
        L61:
            r7.invoke(r8)
            long r5 = r8.getId()
            goto L3f
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m380dragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00cb, code lost:
    
        if (androidx.compose.ui.input.pointer.PointerEventKt.positionChangedIgnoreConsumed(r11) != false) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0092 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0068 -> B:22:0x006d). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitDragOrCancellation-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m366awaitDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r20) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m366awaitDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object detectDragGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function02, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGestures.2
                /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m387invokek4lQ0M(long j) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                    m387invokek4lQ0M(offset.getPackedValue());
                    return Unit.INSTANCE;
                }
            };
        }
        Function1 function12 = function1;
        if ((i & 2) != 0) {
            function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGestures.3
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function03 = function0;
        if ((i & 4) != 0) {
            function02 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGestures.4
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        return detectDragGestures(pointerInputScope, function12, function03, function02, function2, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$5, reason: invalid class name */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$5", f = "DragGestureDetector.kt", i = {0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {KeyBoardKey.KeyboardKeyVolumeUp, 884, 926, KeyBoardKey.KeyboardKeyOem3}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "down", "overSlop", "$this$awaitPointerSlopOrCancellation_u2dpn7EDYM$iv", "pointerDirectionConfig$iv", "pointer$iv", "touchSlop$iv", "totalPositionChange$iv", "$this$awaitEachGesture", "down", "overSlop", "$this$awaitPointerSlopOrCancellation_u2dpn7EDYM$iv", "pointerDirectionConfig$iv", "pointer$iv", "dragEvent$iv", "touchSlop$iv", "totalPositionChange$iv"}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "F$0", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "F$0", "J$0"})
    static final class AnonymousClass5 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        float F$0;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass5(Function1<? super Offset, Unit> function1, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Function0<Unit> function0, Function0<Unit> function02, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onDrag = function2;
            this.$onDragCancel = function0;
            this.$onDragEnd = function02;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass5 anonymousClass5 = new AnonymousClass5(this.$onDragStart, this.$onDrag, this.$onDragCancel, this.$onDragEnd, continuation);
            anonymousClass5.L$0 = obj;
            return anonymousClass5;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Path cross not found for [B:35:0x0152, B:36:0x015a], limit reached: 83 */
        /* JADX WARN: Path cross not found for [B:39:0x0161, B:35:0x0152], limit reached: 83 */
        /* JADX WARN: Removed duplicated region for block: B:20:0x00d4  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00d9  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0111 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x015a  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0167  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0194  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x01d9  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x01de  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x01ff  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x020f  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x0258  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x025e  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x0146 A[SYNTHETIC] */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x01d1 -> B:58:0x01d3). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x01fa -> B:66:0x01fd). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:81:0x0266 -> B:23:0x00f4). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r23) {
            /*
                Method dump skipped, instruction units count: 634
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.AnonymousClass5.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final Object detectDragGestures(PointerInputScope pointerInputScope, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function02, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new AnonymousClass5(function1, function2, function02, function0, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    public static /* synthetic */ Object detectDragGesturesAfterLongPress$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function02, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGesturesAfterLongPress.2
                /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m388invokek4lQ0M(long j) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                    m388invokek4lQ0M(offset.getPackedValue());
                    return Unit.INSTANCE;
                }
            };
        }
        Function1 function12 = function1;
        if ((i & 2) != 0) {
            function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGesturesAfterLongPress.3
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function03 = function0;
        if ((i & 4) != 0) {
            function02 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGesturesAfterLongPress.4
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        return detectDragGesturesAfterLongPress(pointerInputScope, function12, function03, function02, function2, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5", f = "DragGestureDetector.kt", i = {0, 1, 2}, l = {KeyBoardKey.KeyboardKeyOemJump, KeyBoardKey.KeyboardKeyOemPa1, KeyBoardKey.KeyboardKeyOemAttn}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "$this$awaitEachGesture"}, s = {"L$0", "L$0", "L$0"})
    static final class C05205 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C05205(Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function02, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super C05205> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onDragEnd = function0;
            this.$onDragCancel = function02;
            this.$onDrag = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05205 c05205 = new C05205(this.$onDragStart, this.$onDragEnd, this.$onDragCancel, this.$onDrag, continuation);
            c05205.L$0 = obj;
            return c05205;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C05205) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x0065 A[Catch: CancellationException -> 0x00c5, TryCatch #0 {CancellationException -> 0x00c5, blocks: (B:8:0x0015, B:31:0x008e, B:33:0x0096, B:35:0x00a5, B:37:0x00b1, B:38:0x00b4, B:39:0x00b7, B:40:0x00bd, B:13:0x0026, B:25:0x0061, B:27:0x0065, B:16:0x002e, B:22:0x004d, B:19:0x003a), top: B:46:0x0009 }] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0096 A[Catch: CancellationException -> 0x00c5, TryCatch #0 {CancellationException -> 0x00c5, blocks: (B:8:0x0015, B:31:0x008e, B:33:0x0096, B:35:0x00a5, B:37:0x00b1, B:38:0x00b4, B:39:0x00b7, B:40:0x00bd, B:13:0x0026, B:25:0x0061, B:27:0x0065, B:16:0x002e, B:22:0x004d, B:19:0x003a), top: B:46:0x0009 }] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00bd A[Catch: CancellationException -> 0x00c5, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x00c5, blocks: (B:8:0x0015, B:31:0x008e, B:33:0x0096, B:35:0x00a5, B:37:0x00b1, B:38:0x00b4, B:39:0x00b7, B:40:0x00bd, B:13:0x0026, B:25:0x0061, B:27:0x0065, B:16:0x002e, B:22:0x004d, B:19:0x003a), top: B:46:0x0009 }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                Method dump skipped, instruction units count: 204
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.C05205.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final Object detectDragGesturesAfterLongPress(PointerInputScope pointerInputScope, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function02, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C05205(function1, function0, function02, function2, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00fe A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x0139 -> B:20:0x00b1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x017d -> B:56:0x0184). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x01aa -> B:60:0x018d). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitVerticalTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m377awaitVerticalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r21, long r22, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r24, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r25) {
        /*
            Method dump skipped, instruction units count: 437
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m377awaitVerticalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0100 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x013b -> B:20:0x00b3). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x017f -> B:56:0x0186). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x01ac -> B:60:0x018f). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitVerticalPointerSlopOrCancellation-gDDlDlE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m376awaitVerticalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, int r25, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r26, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r27) {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m376awaitVerticalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ea, code lost:
    
        if ((androidx.compose.ui.geometry.Offset.m3220getYimpl(androidx.compose.ui.input.pointer.PointerEventKt.positionChangeIgnoreConsumed(r13)) == 0.0f) == false) goto L51;
     */
    /* JADX WARN: Path cross not found for [B:46:0x00da, B:35:0x00b1], limit reached: 64 */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0079 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00a4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x007a -> B:23:0x007f). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: verticalDrag-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m384verticalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r20, kotlin.coroutines.Continuation<? super java.lang.Boolean> r21) {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m384verticalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00d5, code lost:
    
        if ((androidx.compose.ui.geometry.Offset.m3220getYimpl(androidx.compose.ui.input.pointer.PointerEventKt.positionChangeIgnoreConsumed(r11)) == 0.0f ? 1 : 0) == 0) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0092 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0068 -> B:22:0x006d). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitVerticalDragOrCancellation-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m375awaitVerticalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r20) {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m375awaitVerticalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object detectVerticalDragGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function02, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectVerticalDragGestures.2
                /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m390invokek4lQ0M(long j) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                    m390invokek4lQ0M(offset.getPackedValue());
                    return Unit.INSTANCE;
                }
            };
        }
        Function1 function12 = function1;
        if ((i & 2) != 0) {
            function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectVerticalDragGestures.3
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function03 = function0;
        if ((i & 4) != 0) {
            function02 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectVerticalDragGestures.4
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        return detectVerticalDragGestures(pointerInputScope, function12, function03, function02, function2, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5", f = "DragGestureDetector.kt", i = {0, 1, 1}, l = {390, 392, 400}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "overSlop"}, s = {"L$0", "L$0", "L$1"})
    static final class C05285 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        final /* synthetic */ Function2<PointerInputChange, Float, Unit> $onVerticalDrag;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C05285(Function1<? super Offset, Unit> function1, Function2<? super PointerInputChange, ? super Float, Unit> function2, Function0<Unit> function0, Function0<Unit> function02, Continuation<? super C05285> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onVerticalDrag = function2;
            this.$onDragEnd = function0;
            this.$onDragCancel = function02;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05285 c05285 = new C05285(this.$onDragStart, this.$onVerticalDrag, this.$onDragEnd, this.$onDragCancel, continuation);
            c05285.L$0 = obj;
            return c05285;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C05285) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x007d  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00bb  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00c1  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instruction units count: 201
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.C05285.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final Object detectVerticalDragGestures(PointerInputScope pointerInputScope, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function02, Function2<? super PointerInputChange, ? super Float, Unit> function2, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C05285(function1, function2, function0, function02, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00fe A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x0139 -> B:20:0x00b1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x017d -> B:56:0x0184). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x01aa -> B:60:0x018d). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitHorizontalTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m370awaitHorizontalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r21, long r22, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r24, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r25) {
        /*
            Method dump skipped, instruction units count: 437
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m370awaitHorizontalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0100 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x013b -> B:20:0x00b3). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x017f -> B:56:0x0186). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x01ac -> B:60:0x018f). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitHorizontalPointerSlopOrCancellation-gDDlDlE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m369awaitHorizontalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, int r25, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r26, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r27) {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m369awaitHorizontalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ea, code lost:
    
        if ((androidx.compose.ui.geometry.Offset.m3219getXimpl(androidx.compose.ui.input.pointer.PointerEventKt.positionChangeIgnoreConsumed(r13)) == 0.0f) == false) goto L51;
     */
    /* JADX WARN: Path cross not found for [B:46:0x00da, B:35:0x00b1], limit reached: 64 */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0079 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00a4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x007a -> B:23:0x007f). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: horizontalDrag-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m381horizontalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r20, kotlin.coroutines.Continuation<? super java.lang.Boolean> r21) {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m381horizontalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00d5, code lost:
    
        if ((androidx.compose.ui.geometry.Offset.m3219getXimpl(androidx.compose.ui.input.pointer.PointerEventKt.positionChangeIgnoreConsumed(r11)) == 0.0f ? 1 : 0) == 0) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0092 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0068 -> B:22:0x006d). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitHorizontalDragOrCancellation-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m368awaitHorizontalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r20) {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m368awaitHorizontalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object detectHorizontalDragGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function02, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectHorizontalDragGestures.2
                /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m389invokek4lQ0M(long j) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                    m389invokek4lQ0M(offset.getPackedValue());
                    return Unit.INSTANCE;
                }
            };
        }
        Function1 function12 = function1;
        if ((i & 2) != 0) {
            function0 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectHorizontalDragGestures.3
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function03 = function0;
        if ((i & 4) != 0) {
            function02 = new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectHorizontalDragGestures.4
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        return detectHorizontalDragGestures(pointerInputScope, function12, function03, function02, function2, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5", f = "DragGestureDetector.kt", i = {0, 1, 1}, l = {538, 540, 551}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "overSlop"}, s = {"L$0", "L$0", "L$1"})
    static final class C05245 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        final /* synthetic */ Function2<PointerInputChange, Float, Unit> $onHorizontalDrag;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C05245(Function1<? super Offset, Unit> function1, Function2<? super PointerInputChange, ? super Float, Unit> function2, Function0<Unit> function0, Function0<Unit> function02, Continuation<? super C05245> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onHorizontalDrag = function2;
            this.$onDragEnd = function0;
            this.$onDragCancel = function02;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05245 c05245 = new C05245(this.$onDragStart, this.$onHorizontalDrag, this.$onDragEnd, this.$onDragCancel, continuation);
            c05245.L$0 = obj;
            return c05245;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C05245) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x007d  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00bb  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00c1  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instruction units count: 201
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.C05245.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final Object detectHorizontalDragGestures(PointerInputScope pointerInputScope, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function02, Function2<? super PointerInputChange, ? super Float, Unit> function2, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C05245(function1, function2, function0, function02, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00fd, code lost:
    
        if (r3.invoke(r15).booleanValue() != false) goto L48;
     */
    /* JADX WARN: Path cross not found for [B:46:0x00f3, B:35:0x00ca], limit reached: 59 */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00bd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x008e -> B:23:0x0094). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: drag-VnAYq1g, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m378dragVnAYq1g(androidx.compose.ui.input.pointer.AwaitPointerEventScope r19, long r20, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r22, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Boolean> r23, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Boolean> r24, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r25) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m378dragVnAYq1g(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: drag-VnAYq1g$$forInline, reason: not valid java name */
    private static final Object m379dragVnAYq1g$$forInline(AwaitPointerEventScope awaitPointerEventScope, long j, Function1<? super PointerInputChange, Unit> function1, Function1<? super PointerInputChange, Boolean> function12, Function1<? super PointerInputChange, Boolean> function13, Continuation<? super PointerInputChange> continuation) {
        PointerInputChange pointerInputChange;
        PointerInputChange pointerInputChange2;
        PointerInputChange pointerInputChange3;
        long id = j;
        if (m382isPointerUpDmW0f2w(awaitPointerEventScope.getCurrentEvent(), id)) {
            return null;
        }
        while (true) {
            Ref.LongRef longRef = new Ref.LongRef();
            longRef.element = id;
            while (true) {
                int i = 0;
                InlineMarker.mark(0);
                Object objAwaitPointerEvent$default = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope, null, continuation, 1, null);
                InlineMarker.mark(1);
                PointerEvent pointerEvent = (PointerEvent) objAwaitPointerEvent$default;
                List<PointerInputChange> changes = pointerEvent.getChanges();
                int size = changes.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        pointerInputChange = null;
                        break;
                    }
                    pointerInputChange = changes.get(i2);
                    if (Boolean.valueOf(PointerId.m4638equalsimpl0(pointerInputChange.getId(), longRef.element)).booleanValue()) {
                        break;
                    }
                    i2++;
                }
                pointerInputChange2 = pointerInputChange;
                if (pointerInputChange2 == null) {
                    pointerInputChange2 = null;
                    break;
                }
                if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange2)) {
                    List<PointerInputChange> changes2 = pointerEvent.getChanges();
                    int size2 = changes2.size();
                    while (true) {
                        if (i >= size2) {
                            pointerInputChange3 = null;
                            break;
                        }
                        pointerInputChange3 = changes2.get(i);
                        if (Boolean.valueOf(pointerInputChange3.getPressed()).booleanValue()) {
                            break;
                        }
                        i++;
                    }
                    PointerInputChange pointerInputChange4 = pointerInputChange3;
                    if (pointerInputChange4 == null) {
                        break;
                    }
                    longRef.element = pointerInputChange4.getId();
                } else if (function12.invoke(pointerInputChange2).booleanValue()) {
                    break;
                }
            }
            if (pointerInputChange2 == null || function13.invoke(pointerInputChange2).booleanValue()) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange2)) {
                return pointerInputChange2;
            }
            function1.invoke(pointerInputChange2);
            id = pointerInputChange2.getId();
        }
    }

    /* JADX INFO: renamed from: awaitDragOrUp-jO51t88, reason: not valid java name */
    private static final Object m367awaitDragOrUpjO51t88(AwaitPointerEventScope awaitPointerEventScope, long j, Function1<? super PointerInputChange, Boolean> function1, Continuation<? super PointerInputChange> continuation) {
        PointerInputChange pointerInputChange;
        Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = j;
        while (true) {
            int i = 0;
            InlineMarker.mark(0);
            PointerInputChange pointerInputChange2 = null;
            Object objAwaitPointerEvent$default = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope, null, continuation, 1, null);
            InlineMarker.mark(1);
            PointerEvent pointerEvent = (PointerEvent) objAwaitPointerEvent$default;
            List<PointerInputChange> changes = pointerEvent.getChanges();
            int size = changes.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    pointerInputChange = null;
                    break;
                }
                pointerInputChange = changes.get(i2);
                if (Boolean.valueOf(PointerId.m4638equalsimpl0(pointerInputChange.getId(), longRef.element)).booleanValue()) {
                    break;
                }
                i2++;
            }
            PointerInputChange pointerInputChange3 = pointerInputChange;
            if (pointerInputChange3 == null) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange3)) {
                List<PointerInputChange> changes2 = pointerEvent.getChanges();
                int size2 = changes2.size();
                while (true) {
                    if (i >= size2) {
                        break;
                    }
                    PointerInputChange pointerInputChange4 = changes2.get(i);
                    if (Boolean.valueOf(pointerInputChange4.getPressed()).booleanValue()) {
                        pointerInputChange2 = pointerInputChange4;
                        break;
                    }
                    i++;
                }
                PointerInputChange pointerInputChange5 = pointerInputChange2;
                if (pointerInputChange5 == null) {
                    return pointerInputChange3;
                }
                longRef.element = pointerInputChange5.getId();
            } else if (function1.invoke(pointerInputChange3).booleanValue()) {
                return pointerInputChange3;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0187 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0102 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x017a -> B:56:0x0180). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitPointerSlopOrCancellation-pn7EDYM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m372awaitPointerSlopOrCancellationpn7EDYM(androidx.compose.ui.input.pointer.AwaitPointerEventScope r21, long r22, int r24, androidx.compose.foundation.gestures.PointerDirectionConfig r25, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r26, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r27) {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m372awaitPointerSlopOrCancellationpn7EDYM(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, androidx.compose.foundation.gestures.PointerDirectionConfig, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: awaitPointerSlopOrCancellation-pn7EDYM$$forInline, reason: not valid java name */
    private static final Object m373awaitPointerSlopOrCancellationpn7EDYM$$forInline(AwaitPointerEventScope awaitPointerEventScope, long j, int i, PointerDirectionConfig pointerDirectionConfig, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super PointerInputChange> continuation) {
        PointerInputChange pointerInputChange;
        long jM3235getZeroF1C5BW0;
        PointerInputChange pointerInputChange2;
        if (m382isPointerUpDmW0f2w(awaitPointerEventScope.getCurrentEvent(), j)) {
            return null;
        }
        float fM383pointerSlopE8SPZFQ = m383pointerSlopE8SPZFQ(awaitPointerEventScope.getViewConfiguration(), i);
        Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = j;
        long jM3235getZeroF1C5BW02 = Offset.INSTANCE.m3235getZeroF1C5BW0();
        while (true) {
            InlineMarker.mark(0);
            Object objAwaitPointerEvent$default = AwaitPointerEventScope.CC.awaitPointerEvent$default(awaitPointerEventScope, null, continuation, 1, null);
            InlineMarker.mark(1);
            PointerEvent pointerEvent = (PointerEvent) objAwaitPointerEvent$default;
            List<PointerInputChange> changes = pointerEvent.getChanges();
            int size = changes.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    pointerInputChange = null;
                    break;
                }
                pointerInputChange = changes.get(i2);
                List<PointerInputChange> list = changes;
                int i3 = size;
                if (Boolean.valueOf(PointerId.m4638equalsimpl0(pointerInputChange.getId(), longRef.element)).booleanValue()) {
                    break;
                }
                i2++;
                changes = list;
                size = i3;
            }
            PointerInputChange pointerInputChange3 = pointerInputChange;
            if (pointerInputChange3 == null || pointerInputChange3.isConsumed()) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange3)) {
                List<PointerInputChange> changes2 = pointerEvent.getChanges();
                int size2 = changes2.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size2) {
                        pointerInputChange2 = null;
                        break;
                    }
                    pointerInputChange2 = changes2.get(i4);
                    if (Boolean.valueOf(pointerInputChange2.getPressed()).booleanValue()) {
                        break;
                    }
                    i4++;
                }
                PointerInputChange pointerInputChange4 = pointerInputChange2;
                if (pointerInputChange4 == null) {
                    return null;
                }
                longRef.element = pointerInputChange4.getId();
            } else {
                jM3235getZeroF1C5BW02 = Offset.m3224plusMKHz9U(jM3235getZeroF1C5BW02, Offset.m3223minusMKHz9U(pointerInputChange3.getPosition(), pointerInputChange3.getPreviousPosition()));
                if (pointerDirectionConfig.mo385calculateDeltaChangek4lQ0M(jM3235getZeroF1C5BW02) < fM383pointerSlopE8SPZFQ) {
                    PointerEventPass pointerEventPass = PointerEventPass.Final;
                    InlineMarker.mark(0);
                    awaitPointerEventScope.awaitPointerEvent(pointerEventPass, continuation);
                    InlineMarker.mark(1);
                    if (pointerInputChange3.isConsumed()) {
                        return null;
                    }
                } else {
                    function2.invoke(pointerInputChange3, Offset.m3208boximpl(pointerDirectionConfig.mo386calculatePostSlopOffset8S9VItk(jM3235getZeroF1C5BW02, fM383pointerSlopE8SPZFQ)));
                    if (pointerInputChange3.isConsumed()) {
                        return pointerInputChange3;
                    }
                    jM3235getZeroF1C5BW0 = Offset.INSTANCE.m3235getZeroF1C5BW0();
                    jM3235getZeroF1C5BW02 = jM3235getZeroF1C5BW0;
                }
            }
            jM3235getZeroF1C5BW0 = jM3235getZeroF1C5BW02;
            jM3235getZeroF1C5BW02 = jM3235getZeroF1C5BW0;
        }
    }

    static {
        float fM5882constructorimpl = Dp.m5882constructorimpl((float) 0.125d);
        mouseSlop = fM5882constructorimpl;
        float fM5882constructorimpl2 = Dp.m5882constructorimpl(18);
        defaultTouchSlop = fM5882constructorimpl2;
        mouseToTouchSlopRatio = fM5882constructorimpl / fM5882constructorimpl2;
    }

    public static final PointerDirectionConfig getHorizontalPointerDirectionConfig() {
        return HorizontalPointerDirectionConfig;
    }

    public static final PointerDirectionConfig getVerticalPointerDirectionConfig() {
        return VerticalPointerDirectionConfig;
    }

    public static final PointerDirectionConfig getBidirectionalPointerDirectionConfig() {
        return BidirectionalPointerDirectionConfig;
    }

    public static final PointerDirectionConfig toPointerDirectionConfig(Orientation orientation) {
        return orientation == Orientation.Vertical ? VerticalPointerDirectionConfig : HorizontalPointerDirectionConfig;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r10v2, types: [T, androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object] */
    /* JADX INFO: renamed from: awaitLongPressOrCancellation-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m371awaitLongPressOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r9, long r10, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r12) {
        /*
            boolean r0 = r12 instanceof androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1
            if (r0 == 0) goto L14
            r0 = r12
            androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1 r0 = (androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1 r0 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1
            r0.<init>(r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r9 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r9 = (kotlin.jvm.internal.Ref.ObjectRef) r9
            java.lang.Object r10 = r0.L$0
            androidx.compose.ui.input.pointer.PointerInputChange r10 = (androidx.compose.ui.input.pointer.PointerInputChange) r10
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L34
            goto Laa
        L34:
            goto La1
        L36:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3e:
            kotlin.ResultKt.throwOnFailure(r12)
            androidx.compose.ui.input.pointer.PointerEvent r12 = r9.getCurrentEvent()
            boolean r12 = m382isPointerUpDmW0f2w(r12, r10)
            if (r12 == 0) goto L4c
            return r4
        L4c:
            androidx.compose.ui.input.pointer.PointerEvent r12 = r9.getCurrentEvent()
            java.util.List r12 = r12.getChanges()
            int r2 = r12.size()
            r5 = 0
        L59:
            if (r5 >= r2) goto L70
            java.lang.Object r6 = r12.get(r5)
            r7 = r6
            androidx.compose.ui.input.pointer.PointerInputChange r7 = (androidx.compose.ui.input.pointer.PointerInputChange) r7
            long r7 = r7.getId()
            boolean r7 = androidx.compose.ui.input.pointer.PointerId.m4638equalsimpl0(r7, r10)
            if (r7 == 0) goto L6d
            goto L71
        L6d:
            int r5 = r5 + 1
            goto L59
        L70:
            r6 = r4
        L71:
            r10 = r6
            androidx.compose.ui.input.pointer.PointerInputChange r10 = (androidx.compose.ui.input.pointer.PointerInputChange) r10
            if (r10 != 0) goto L77
            return r4
        L77:
            kotlin.jvm.internal.Ref$ObjectRef r11 = new kotlin.jvm.internal.Ref$ObjectRef
            r11.<init>()
            kotlin.jvm.internal.Ref$ObjectRef r12 = new kotlin.jvm.internal.Ref$ObjectRef
            r12.<init>()
            r12.element = r10
            androidx.compose.ui.platform.ViewConfiguration r2 = r9.getViewConfiguration()
            long r5 = r2.getLongPressTimeoutMillis()
            androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$2 r2 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$2     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L9f
            r2.<init>(r12, r11, r4)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L9f
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L9f
            r0.L$0 = r10     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L9f
            r0.L$1 = r11     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L9f
            r0.label = r3     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L9f
            java.lang.Object r9 = r9.withTimeout(r5, r2, r0)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L9f
            if (r9 != r1) goto Laa
            return r1
        L9f:
            r9 = r11
        La1:
            T r9 = r9.element
            androidx.compose.ui.input.pointer.PointerInputChange r9 = (androidx.compose.ui.input.pointer.PointerInputChange) r9
            if (r9 != 0) goto La9
            r4 = r10
            goto Laa
        La9:
            r4 = r9
        Laa:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m371awaitLongPressOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isPointerUp-DmW0f2w, reason: not valid java name */
    public static final boolean m382isPointerUpDmW0f2w(PointerEvent pointerEvent, long j) {
        PointerInputChange pointerInputChange;
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                pointerInputChange = null;
                break;
            }
            pointerInputChange = changes.get(i);
            if (PointerId.m4638equalsimpl0(pointerInputChange.getId(), j)) {
                break;
            }
            i++;
        }
        PointerInputChange pointerInputChange2 = pointerInputChange;
        if (pointerInputChange2 != null && pointerInputChange2.getPressed()) {
            z = true;
        }
        return true ^ z;
    }

    /* JADX INFO: renamed from: pointerSlop-E8SPZFQ, reason: not valid java name */
    public static final float m383pointerSlopE8SPZFQ(ViewConfiguration viewConfiguration, int i) {
        return PointerType.m4729equalsimpl0(i, PointerType.INSTANCE.m4734getMouseT8wyACA()) ? viewConfiguration.getTouchSlop() * mouseToTouchSlopRatio : viewConfiguration.getTouchSlop();
    }
}
