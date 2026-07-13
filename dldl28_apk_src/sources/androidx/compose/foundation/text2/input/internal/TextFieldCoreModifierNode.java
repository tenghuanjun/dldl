package androidx.compose.foundation.text2.input.internal;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollExtensionsKt;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.internal.selection.AndroidTextFieldMagnifier_androidKt;
import androidx.compose.foundation.text2.input.internal.selection.TextFieldMagnifierNode;
import androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.node.NodeMeasuringIntrinsics;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextPainter;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: TextFieldCoreModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006BE\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\b\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0002\u0010\u0016J\u001a\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020 H\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0016JF\u0010/\u001a\u00020,2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\"\u00100\u001a\u00020,2\b\u00101\u001a\u0004\u0018\u00010\u001e2\u0006\u00102\u001a\u00020'2\u0006\u00103\u001a\u00020'H\u0002J\f\u00104\u001a\u00020,*\u000205H\u0016J\f\u00106\u001a\u00020,*\u000207H\u0016J\f\u00108\u001a\u00020,*\u000209H\u0002J&\u0010:\u001a\u00020,*\u0002092\u0006\u0010;\u001a\u00020 2\u0006\u0010<\u001a\u00020=H\u0002ø\u0001\u0000¢\u0006\u0004\b>\u0010?J\u0014\u0010@\u001a\u00020,*\u0002092\u0006\u0010<\u001a\u00020=H\u0002J&\u0010A\u001a\u00020B*\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GH\u0016ø\u0001\u0000¢\u0006\u0004\bH\u0010IJ&\u0010J\u001a\u00020B*\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GH\u0002ø\u0001\u0000¢\u0006\u0004\bK\u0010IJ&\u0010L\u001a\u00020B*\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GH\u0002ø\u0001\u0000¢\u0006\u0004\bM\u0010IR\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006N"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TextFieldCoreModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "isFocused", "", "textLayoutState", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "textFieldState", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "textFieldSelectionState", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "writeable", "scrollState", "Landroidx/compose/foundation/ScrollState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "(ZLandroidx/compose/foundation/text2/input/internal/TextLayoutState;Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/graphics/Brush;ZLandroidx/compose/foundation/ScrollState;Landroidx/compose/foundation/gestures/Orientation;)V", "changeObserverJob", "Lkotlinx/coroutines/Job;", "cursorAlpha", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "previousCursorRect", "Landroidx/compose/ui/geometry/Rect;", "previousSelection", "Landroidx/compose/ui/text/TextRange;", "showCursor", "getShowCursor", "()Z", "textFieldMagnifierNode", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldMagnifierNode;", "calculateOffsetToFollow", "", "currSelection", "calculateOffsetToFollow-5zc-tL8", "(J)I", "onGloballyPositioned", "", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "updateNode", "updateScrollState", "cursorRect", "containerSize", "textFieldSize", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "drawCursor", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "drawSelection", "selection", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "drawSelection-Sb-Bc2M", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JLandroidx/compose/ui/text/TextLayoutResult;)V", "drawText", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measureHorizontalScroll", "measureHorizontalScroll-3p2s80s", "measureVerticalScroll", "measureVerticalScroll-3p2s80s", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldCoreModifierNode extends DelegatingNode implements LayoutModifierNode, DrawModifierNode, CompositionLocalConsumerModifierNode, GlobalPositionAwareModifierNode, SemanticsModifierNode {
    public static final int $stable = 8;
    private Job changeObserverJob;
    private Brush cursorBrush;
    private boolean isFocused;
    private Orientation orientation;
    private TextRange previousSelection;
    private ScrollState scrollState;
    private final TextFieldMagnifierNode textFieldMagnifierNode;
    private TextFieldSelectionState textFieldSelectionState;
    private TransformedTextFieldState textFieldState;
    private TextLayoutState textLayoutState;
    private boolean writeable;
    private final Animatable<Float, AnimationVector1D> cursorAlpha = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
    private Rect previousCursorRect = new Rect(-1.0f, -1.0f, -1.0f, -1.0f);

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    /* JADX INFO: renamed from: getShouldClearDescendantSemantics */
    public /* synthetic */ boolean getIsClearingSemantics() {
        return SemanticsModifierNode.CC.$default$getShouldClearDescendantSemantics(this);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public /* synthetic */ boolean getShouldMergeDescendantSemantics() {
        return SemanticsModifierNode.CC.$default$getShouldMergeDescendantSemantics(this);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return NodeMeasuringIntrinsics.INSTANCE.maxHeight$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.maxIntrinsicHeight.1
            /* JADX INFO: Access modifiers changed from: package-private */
            public AnonymousClass1() {
            }

            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo4789measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                return LayoutModifierNode.this.mo121measure3p2s80s(measureScope, measurable, j);
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return NodeMeasuringIntrinsics.INSTANCE.maxWidth$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.maxIntrinsicWidth.1
            /* JADX INFO: Access modifiers changed from: package-private */
            public C08261() {
            }

            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo4789measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                return LayoutModifierNode.this.mo121measure3p2s80s(measureScope, measurable, j);
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return NodeMeasuringIntrinsics.INSTANCE.minHeight$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.minIntrinsicHeight.1
            /* JADX INFO: Access modifiers changed from: package-private */
            public C08271() {
            }

            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo4789measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                return LayoutModifierNode.this.mo121measure3p2s80s(measureScope, measurable, j);
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return NodeMeasuringIntrinsics.INSTANCE.minWidth$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.minIntrinsicWidth.1
            /* JADX INFO: Access modifiers changed from: package-private */
            public C08281() {
            }

            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo4789measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                return LayoutModifierNode.this.mo121measure3p2s80s(measureScope, measurable, j);
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public /* synthetic */ void onMeasureResultChanged() {
        DrawModifierNode.CC.$default$onMeasureResultChanged(this);
    }

    public TextFieldCoreModifierNode(boolean z, TextLayoutState textLayoutState, TransformedTextFieldState transformedTextFieldState, TextFieldSelectionState textFieldSelectionState, Brush brush, boolean z2, ScrollState scrollState, Orientation orientation) {
        this.isFocused = z;
        this.textLayoutState = textLayoutState;
        this.textFieldState = transformedTextFieldState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.cursorBrush = brush;
        this.writeable = z2;
        this.scrollState = scrollState;
        this.orientation = orientation;
        this.textFieldMagnifierNode = (TextFieldMagnifierNode) delegate(AndroidTextFieldMagnifier_androidKt.textFieldMagnifierNode(this.textFieldState, this.textFieldSelectionState, this.textLayoutState, this.isFocused));
    }

    private final boolean getShowCursor() {
        return this.writeable && this.isFocused && TextFieldCoreModifierKt.isSpecified(this.cursorBrush);
    }

    public final void updateNode(boolean isFocused, TextLayoutState textLayoutState, TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, Brush cursorBrush, boolean writeable, ScrollState scrollState, Orientation orientation) {
        boolean showCursor = getShowCursor();
        boolean z = this.isFocused;
        TransformedTextFieldState transformedTextFieldState = this.textFieldState;
        TextLayoutState textLayoutState2 = this.textLayoutState;
        TextFieldSelectionState textFieldSelectionState2 = this.textFieldSelectionState;
        ScrollState scrollState2 = this.scrollState;
        this.isFocused = isFocused;
        this.textLayoutState = textLayoutState;
        this.textFieldState = textFieldState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.cursorBrush = cursorBrush;
        this.writeable = writeable;
        this.scrollState = scrollState;
        this.orientation = orientation;
        this.textFieldMagnifierNode.update(textFieldState, textFieldSelectionState, textLayoutState, isFocused);
        if (!getShowCursor()) {
            Job job = this.changeObserverJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.changeObserverJob = null;
            BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AnonymousClass1(null), 3, null);
        } else if (!z || !Intrinsics.areEqual(transformedTextFieldState, textFieldState) || !showCursor) {
            this.changeObserverJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AnonymousClass2(textFieldState, this, null), 3, null);
        }
        if (Intrinsics.areEqual(transformedTextFieldState, textFieldState) && Intrinsics.areEqual(textLayoutState2, textLayoutState) && Intrinsics.areEqual(textFieldSelectionState2, textFieldSelectionState) && Intrinsics.areEqual(scrollState2, scrollState)) {
            return;
        }
        LayoutModifierNodeKt.invalidateMeasurement(this);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$1, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldCoreModifier.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$1", f = "TextFieldCoreModifier.kt", i = {}, l = {KeyBoardKey.KeyboardKeyGamepadRightThumbStickRight}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TextFieldCoreModifierNode.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (TextFieldCoreModifierNode.this.cursorAlpha.snapTo(Boxing.boxFloat(0.0f), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldCoreModifier.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2", f = "TextFieldCoreModifier.kt", i = {}, l = {KeyBoardKey.KeyboardKeyOem102}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ TransformedTextFieldState $textFieldState;
        int label;
        final /* synthetic */ TextFieldCoreModifierNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(TransformedTextFieldState transformedTextFieldState, TextFieldCoreModifierNode textFieldCoreModifierNode, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$textFieldState = transformedTextFieldState;
            this.this$0 = textFieldCoreModifierNode;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$textFieldState, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (BuildersKt.withContext(FixedMotionDurationScale.INSTANCE, new AnonymousClass1(this.$textFieldState, this.this$0, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldCoreModifier.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2$1", f = "TextFieldCoreModifier.kt", i = {}, l = {KeyBoardKey.KeyboardKeyIco00}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ TransformedTextFieldState $textFieldState;
            int label;
            final /* synthetic */ TextFieldCoreModifierNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TransformedTextFieldState transformedTextFieldState, TextFieldCoreModifierNode textFieldCoreModifierNode, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$textFieldState = transformedTextFieldState;
                this.this$0 = textFieldCoreModifierNode;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$textFieldState, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    final TransformedTextFieldState transformedTextFieldState = this.$textFieldState;
                    this.label = 1;
                    if (FlowKt.collectLatest(SnapshotStateKt.snapshotFlow(new Function0<TextFieldCharSequence>() { // from class: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode.updateNode.2.1.1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final TextFieldCharSequence invoke() {
                            return transformedTextFieldState.getText();
                        }
                    }), new C00782(this.this$0, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: TextFieldCoreModifier.kt */
            @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateNode$2$1$2", f = "TextFieldCoreModifier.kt", i = {}, l = {KeyBoardKey.KeyboardKeyIcoClear, 232}, m = "invokeSuspend", n = {}, s = {})
            static final class C00782 extends SuspendLambda implements Function2<TextFieldCharSequence, Continuation<? super Unit>, Object> {
                int label;
                final /* synthetic */ TextFieldCoreModifierNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00782(TextFieldCoreModifierNode textFieldCoreModifierNode, Continuation<? super C00782> continuation) {
                    super(2, continuation);
                    this.this$0 = textFieldCoreModifierNode;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00782(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(TextFieldCharSequence textFieldCharSequence, Continuation<? super Unit> continuation) {
                    return ((C00782) create(textFieldCharSequence, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (this.this$0.cursorAlpha.snapTo(Boxing.boxFloat(1.0f), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            if (i != 2) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    this.label = 2;
                    if (Animatable.animateTo$default(this.this$0.cursorAlpha, Boxing.boxFloat(0.0f), TextFieldCoreModifierKt.cursorAnimationSpec, null, null, this, 12, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
            }
        }
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo121measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        if (this.orientation == Orientation.Vertical) {
            return m1173measureVerticalScroll3p2s80s(measureScope, measurable, j);
        }
        return m1172measureHorizontalScroll3p2s80s(measureScope, measurable, j);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        TextFieldCharSequence text = this.textFieldState.getText();
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return;
        }
        if (TextRange.m5362getCollapsedimpl(text.getSelectionInChars())) {
            ContentDrawScope contentDrawScope2 = contentDrawScope;
            drawText(contentDrawScope2, layoutResult);
            drawCursor(contentDrawScope2);
        } else {
            ContentDrawScope contentDrawScope3 = contentDrawScope;
            m1171drawSelectionSbBc2M(contentDrawScope3, text.getSelectionInChars(), layoutResult);
            drawText(contentDrawScope3, layoutResult);
        }
        this.textFieldMagnifierNode.draw(contentDrawScope);
    }

    /* JADX INFO: renamed from: measureVerticalScroll-3p2s80s, reason: not valid java name */
    private final MeasureResult m1173measureVerticalScroll3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        final Placeable placeableMo4783measureBRTryo0 = measurable.mo4783measureBRTryo0(Constraints.m5819copyZbe2FdA$default(j, 0, 0, 0, Integer.MAX_VALUE, 7, null));
        final int iMin = Math.min(placeableMo4783measureBRTryo0.getHeight(), Constraints.m5827getMaxHeightimpl(j));
        return MeasureScope.CC.layout$default(measureScope, placeableMo4783measureBRTryo0.getWidth(), iMin, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$measureVerticalScroll$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope placementScope) {
                Rect cursorRectInScroller;
                long selectionInChars = this.this$0.textFieldState.getText().getSelectionInChars();
                int iM1170calculateOffsetToFollow5zctL8 = this.this$0.m1170calculateOffsetToFollow5zctL8(selectionInChars);
                if (iM1170calculateOffsetToFollow5zctL8 >= 0) {
                    cursorRectInScroller = TextFieldCoreModifierKt.getCursorRectInScroller(measureScope, iM1170calculateOffsetToFollow5zctL8, this.this$0.textLayoutState.getLayoutResult(), measureScope.getLayoutDirection() == LayoutDirection.Rtl, placeableMo4783measureBRTryo0.getWidth());
                } else {
                    cursorRectInScroller = null;
                }
                this.this$0.updateScrollState(cursorRectInScroller, iMin, placeableMo4783measureBRTryo0.getHeight());
                if (this.this$0.isFocused) {
                    this.this$0.previousSelection = TextRange.m5356boximpl(selectionInChars);
                }
                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo4783measureBRTryo0, 0, -this.this$0.scrollState.getValue(), 0.0f, 4, null);
            }
        }, 4, null);
    }

    /* JADX INFO: renamed from: measureHorizontalScroll-3p2s80s, reason: not valid java name */
    private final MeasureResult m1172measureHorizontalScroll3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        final Placeable placeableMo4783measureBRTryo0 = measurable.mo4783measureBRTryo0(measurable.maxIntrinsicWidth(Constraints.m5827getMaxHeightimpl(j)) < Constraints.m5828getMaxWidthimpl(j) ? j : Constraints.m5819copyZbe2FdA$default(j, 0, Integer.MAX_VALUE, 0, 0, 13, null));
        final int iMin = Math.min(placeableMo4783measureBRTryo0.getWidth(), Constraints.m5828getMaxWidthimpl(j));
        return MeasureScope.CC.layout$default(measureScope, iMin, placeableMo4783measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$measureHorizontalScroll$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope placementScope) {
                Rect cursorRectInScroller;
                long selectionInChars = this.this$0.textFieldState.getText().getSelectionInChars();
                int iM1170calculateOffsetToFollow5zctL8 = this.this$0.m1170calculateOffsetToFollow5zctL8(selectionInChars);
                if (iM1170calculateOffsetToFollow5zctL8 >= 0) {
                    cursorRectInScroller = TextFieldCoreModifierKt.getCursorRectInScroller(measureScope, iM1170calculateOffsetToFollow5zctL8, this.this$0.textLayoutState.getLayoutResult(), measureScope.getLayoutDirection() == LayoutDirection.Rtl, placeableMo4783measureBRTryo0.getWidth());
                } else {
                    cursorRectInScroller = null;
                }
                this.this$0.updateScrollState(cursorRectInScroller, iMin, placeableMo4783measureBRTryo0.getWidth());
                if (this.this$0.isFocused) {
                    this.this$0.previousSelection = TextRange.m5356boximpl(selectionInChars);
                }
                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo4783measureBRTryo0, -this.this$0.scrollState.getValue(), 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateOffsetToFollow-5zc-tL8, reason: not valid java name */
    public final int m1170calculateOffsetToFollow5zctL8(long currSelection) {
        TextRange textRange = this.previousSelection;
        if (textRange == null || TextRange.m5363getEndimpl(currSelection) != TextRange.m5363getEndimpl(textRange.getPackedValue())) {
            return TextRange.m5363getEndimpl(currSelection);
        }
        TextRange textRange2 = this.previousSelection;
        if (textRange2 == null || TextRange.m5368getStartimpl(currSelection) != TextRange.m5368getStartimpl(textRange2.getPackedValue())) {
            return TextRange.m5368getStartimpl(currSelection);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateScrollState(androidx.compose.ui.geometry.Rect r7, int r8, int r9) {
        /*
            r6 = this;
            int r9 = r9 - r8
            androidx.compose.foundation.ScrollState r0 = r6.scrollState
            r0.setMaxValue$foundation_release(r9)
            boolean r9 = r6.getShowCursor()
            if (r9 == 0) goto L8b
            if (r7 != 0) goto L10
            goto L8b
        L10:
            float r9 = r7.getLeft()
            androidx.compose.ui.geometry.Rect r0 = r6.previousCursorRect
            float r0 = r0.getLeft()
            int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r9 != 0) goto L2d
            float r9 = r7.getTop()
            androidx.compose.ui.geometry.Rect r0 = r6.previousCursorRect
            float r0 = r0.getTop()
            int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r9 != 0) goto L2d
            goto L8b
        L2d:
            androidx.compose.foundation.gestures.Orientation r9 = r6.orientation
            androidx.compose.foundation.gestures.Orientation r0 = androidx.compose.foundation.gestures.Orientation.Vertical
            if (r9 != r0) goto L35
            r9 = 1
            goto L36
        L35:
            r9 = 0
        L36:
            if (r9 == 0) goto L3d
            float r0 = r7.getTop()
            goto L41
        L3d:
            float r0 = r7.getLeft()
        L41:
            if (r9 == 0) goto L48
            float r9 = r7.getBottom()
            goto L4c
        L48:
            float r9 = r7.getRight()
        L4c:
            androidx.compose.foundation.ScrollState r1 = r6.scrollState
            int r1 = r1.getValue()
            int r2 = r1 + r8
            float r2 = (float) r2
            int r3 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r3 <= 0) goto L5b
        L59:
            float r9 = r9 - r2
            goto L74
        L5b:
            float r1 = (float) r1
            int r3 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r3 >= 0) goto L68
            float r4 = r9 - r0
            float r5 = (float) r8
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L68
            goto L59
        L68:
            if (r3 >= 0) goto L73
            float r9 = r9 - r0
            float r8 = (float) r8
            int r8 = (r9 > r8 ? 1 : (r9 == r8 ? 0 : -1))
            if (r8 > 0) goto L73
            float r9 = r0 - r1
            goto L74
        L73:
            r9 = 0
        L74:
            r6.previousCursorRect = r7
            kotlinx.coroutines.CoroutineScope r0 = r6.getCoroutineScope()
            kotlinx.coroutines.CoroutineStart r2 = kotlinx.coroutines.CoroutineStart.UNDISPATCHED
            androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateScrollState$1 r7 = new androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateScrollState$1
            r8 = 0
            r7.<init>(r9, r8)
            r3 = r7
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r4 = 1
            r5 = 0
            r1 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r0, r1, r2, r3, r4, r5)
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode.updateScrollState(androidx.compose.ui.geometry.Rect, int, int):void");
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateScrollState$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldCoreModifier.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierNode$updateScrollState$1", f = "TextFieldCoreModifier.kt", i = {}, l = {453}, m = "invokeSuspend", n = {}, s = {})
    static final class C06311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ float $offsetDifference;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06311(float f, Continuation<? super C06311> continuation) {
            super(2, continuation);
            this.$offsetDifference = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TextFieldCoreModifierNode.this.new C06311(this.$offsetDifference, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06311) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (ScrollExtensionsKt.scrollBy(TextFieldCoreModifierNode.this.scrollState, TextFieldCoreModifierKt.roundToNext(this.$offsetDifference), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: drawSelection-Sb-Bc2M, reason: not valid java name */
    private final void m1171drawSelectionSbBc2M(DrawScope drawScope, long j, TextLayoutResult textLayoutResult) {
        int iM5366getMinimpl = TextRange.m5366getMinimpl(j);
        int iM5365getMaximpl = TextRange.m5365getMaximpl(j);
        if (iM5366getMinimpl != iM5365getMaximpl) {
            DrawScope.CC.m4049drawPathLG529CI$default(drawScope, textLayoutResult.getPathForRange(iM5366getMinimpl, iM5365getMaximpl), ((TextSelectionColors) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, TextSelectionColorsKt.getLocalTextSelectionColors())).getBackgroundColor(), 0.0f, null, null, 0, 60, null);
        }
    }

    private final void drawCursor(DrawScope drawScope) {
        if (this.cursorAlpha.getValue().floatValue() <= 0.0f || !getShowCursor()) {
            return;
        }
        float fCoerceIn = RangesKt.coerceIn(this.cursorAlpha.getValue().floatValue(), 0.0f, 1.0f);
        if (fCoerceIn == 0.0f) {
            return;
        }
        Rect cursorRect = this.textFieldSelectionState.getCursorRect();
        DrawScope.CC.m4044drawLine1RTmtNc$default(drawScope, this.cursorBrush, cursorRect.m3253getTopCenterF1C5BW0(), cursorRect.m3246getBottomCenterF1C5BW0(), cursorRect.getWidth(), 0, null, fCoerceIn, null, 0, 432, null);
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        this.textLayoutState.setCoreNodeCoordinates(coordinates);
        this.textFieldMagnifierNode.onGloballyPositioned(coordinates);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        this.textFieldMagnifierNode.applySemantics(semanticsPropertyReceiver);
    }

    private final void drawText(DrawScope drawScope, TextLayoutResult textLayoutResult) {
        TextPainter.INSTANCE.paint(drawScope.getDrawContext().getCanvas(), textLayoutResult);
    }
}
