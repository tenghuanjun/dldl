package androidx.compose.foundation.text2.input.internal.selection;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.TextFieldCursorKt;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.SelectionLayout;
import androidx.compose.foundation.text.selection.SelectionLayoutKt;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.foundation.text.selection.TextSelectionDelegateKt;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.TextFieldCharSequenceKt;
import androidx.compose.foundation.text2.input.internal.TextLayoutState;
import androidx.compose.foundation.text2.input.internal.TextLayoutStateKt;
import androidx.compose.foundation.text2.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text2.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import com.donkingliang.imageselector.utils.ImageSelector;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: TextFieldSelectionState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\b\u0010Z\u001a\u00020[H\u0002J\u0010\u0010\\\u001a\u00020[2\b\b\u0002\u0010]\u001a\u00020\tJ\u0006\u0010^\u001a\u00020[J\u0006\u0010_\u001a\u00020[J\u0006\u0010`\u001a\u00020[J\b\u0010a\u001a\u00020\u001eH\u0002J\u001d\u0010b\u001a\u00020\u00102\u0006\u0010c\u001a\u00020\tH\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bd\u0010eJ\u0010\u0010f\u001a\u00020\u00142\u0006\u0010c\u001a\u00020\tH\u0002J<\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020:2\u0006\u0010j\u001a\u00020:2\b\u0010k\u001a\u0004\u0018\u00010h2\u0006\u0010c\u001a\u00020\t2\u0006\u0010l\u001a\u00020mH\u0002ø\u0001\u0000¢\u0006\u0004\bn\u0010oJ\b\u0010p\u001a\u00020[H\u0002J\b\u0010q\u001a\u00020[H\u0002J\u000e\u0010r\u001a\u00020[H\u0086@¢\u0006\u0002\u0010sJ\u000e\u0010t\u001a\u00020[H\u0082@¢\u0006\u0002\u0010sJ\u000e\u0010u\u001a\u00020[H\u0082@¢\u0006\u0002\u0010sJ\u0006\u0010v\u001a\u00020[J\u0010\u0010w\u001a\u00020[2\u0006\u0010x\u001a\u00020\u001eH\u0002J6\u0010y\u001a\u00020[2\u0006\u00102\u001a\u0002032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\"\u0010z\u001a\u00020[2\u0006\u0010{\u001a\u00020#2\u0006\u0010|\u001a\u00020\u0010H\u0002ø\u0001\u0000¢\u0006\u0004\b}\u0010~JN\u0010\u007f\u001a\u00020h2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u0082\u0001\u001a\u00020:2\u0007\u0010\u0083\u0001\u001a\u00020:2\u0006\u0010c\u001a\u00020\t2\u0006\u0010l\u001a\u00020m2\t\b\u0002\u0010\u0084\u0001\u001a\u00020\tH\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J\u000f\u0010\u0087\u0001\u001a\u00020[2\u0006\u0010T\u001a\u00020SJ\u0015\u0010\u0088\u0001\u001a\u00020[*\u00030\u0089\u0001H\u0086@¢\u0006\u0003\u0010\u008a\u0001J\u0015\u0010\u008b\u0001\u001a\u00020[*\u00030\u0089\u0001H\u0082@¢\u0006\u0003\u0010\u008a\u0001J\u001d\u0010\u008c\u0001\u001a\u00020[*\u00030\u0089\u00012\u0006\u0010c\u001a\u00020\tH\u0082@¢\u0006\u0003\u0010\u008d\u0001J%\u0010\u008e\u0001\u001a\u00020[*\u00030\u0089\u00012\u000e\u0010\u008f\u0001\u001a\t\u0012\u0004\u0012\u00020[0\u0090\u0001H\u0082@¢\u0006\u0003\u0010\u0091\u0001J5\u0010\u0092\u0001\u001a\u00020[*\u00030\u0089\u00012\u000e\u0010\u008f\u0001\u001a\t\u0012\u0004\u0012\u00020[0\u0090\u00012\u000e\u0010\u0093\u0001\u001a\t\u0012\u0004\u0012\u00020[0\u0090\u0001H\u0082@¢\u0006\u0003\u0010\u0094\u0001J\u0015\u0010\u0095\u0001\u001a\u00020[*\u00030\u0089\u0001H\u0082@¢\u0006\u0003\u0010\u008a\u0001J\u001d\u0010\u0096\u0001\u001a\u00020[*\u00030\u0089\u00012\u0006\u0010c\u001a\u00020\tH\u0086@¢\u0006\u0003\u0010\u008d\u0001J5\u0010\u0097\u0001\u001a\u00020[*\u00030\u0089\u00012\u000e\u0010\u008f\u0001\u001a\t\u0012\u0004\u0012\u00020[0\u0090\u00012\u000e\u0010\u0093\u0001\u001a\t\u0012\u0004\u0012\u00020[0\u0090\u0001H\u0086@¢\u0006\u0003\u0010\u0094\u0001R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0019\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0018\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u00020\u001e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u0018\u001a\u0004\b\u001f\u0010 R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R/\u0010$\u001a\u0004\u0018\u00010#2\b\u0010\"\u001a\u0004\u0018\u00010#8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0014\u0010+\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\u001bR\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010-\u001a\u00020\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b/\u0010\u0018\u001a\u0004\b.\u0010\u0016R\u0017\u00100\u001a\u00020\u00108Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b1\u0010\u0012R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u001b\"\u0004\b4\u00105R+\u00106\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b8\u0010*\u001a\u0004\b6\u0010\u001b\"\u0004\b7\u00105R\u000e\u00109\u001a\u00020:X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R1\u0010=\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00108B@BX\u0082\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bA\u0010*\u001a\u0004\b>\u0010\u0012\"\u0004\b?\u0010@R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010B\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010*\u001a\u0004\bC\u0010\u001b\"\u0004\bD\u00105R1\u0010F\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00108B@BX\u0082\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bI\u0010*\u001a\u0004\bG\u0010\u0012\"\u0004\bH\u0010@R\u001b\u0010J\u001a\u00020\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bL\u0010\u0018\u001a\u0004\bK\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010M\u001a\u0004\u0018\u00010N8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bO\u0010PR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010Q\u001a\u0004\u0018\u00010RX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010T\u001a\u00020S2\u0006\u0010\"\u001a\u00020S8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bY\u0010*\u001a\u0004\bU\u0010V\"\u0004\bW\u0010X\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0098\u0001"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "", "textFieldState", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "textLayoutState", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "density", "Landroidx/compose/ui/unit/Density;", "enabled", "", "readOnly", "isFocused", "(Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text2/input/internal/TextLayoutState;Landroidx/compose/ui/unit/Density;ZZZ)V", "clipboardManager", "Landroidx/compose/ui/platform/ClipboardManager;", "currentContentVisibleOffset", "Landroidx/compose/ui/geometry/Offset;", "getCurrentContentVisibleOffset-F1C5BW0", "()J", "cursorHandle", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldHandleState;", "getCursorHandle", "()Landroidx/compose/foundation/text2/input/internal/selection/TextFieldHandleState;", "cursorHandle$delegate", "Landroidx/compose/runtime/State;", "cursorHandleInBounds", "getCursorHandleInBounds", "()Z", "cursorHandleInBounds$delegate", "cursorRect", "Landroidx/compose/ui/geometry/Rect;", "getCursorRect", "()Landroidx/compose/ui/geometry/Rect;", "cursorRect$delegate", "<set-?>", "Landroidx/compose/foundation/text/Handle;", "draggingHandle", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle$delegate", "Landroidx/compose/runtime/MutableState;", "editable", "getEditable", "endSelectionHandle", "getEndSelectionHandle", "endSelectionHandle$delegate", "handleDragPosition", "getHandleDragPosition-F1C5BW0", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setFocused", "(Z)V", "isInTouchMode", "setInTouchMode", "isInTouchMode$delegate", "previousRawDragOffset", "", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "rawHandleDragPosition", "getRawHandleDragPosition-F1C5BW0", "setRawHandleDragPosition-k-4lQ0M", "(J)V", "rawHandleDragPosition$delegate", "showCursorHandle", "getShowCursorHandle", "setShowCursorHandle", "showCursorHandle$delegate", "startContentVisibleOffset", "getStartContentVisibleOffset-F1C5BW0", "setStartContentVisibleOffset-k-4lQ0M", "startContentVisibleOffset$delegate", "startSelectionHandle", "getStartSelectionHandle", "startSelectionHandle$delegate", "textLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getTextLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "Landroidx/compose/foundation/text2/input/internal/selection/TextToolbarState;", "textToolbarState", "getTextToolbarState", "()Landroidx/compose/foundation/text2/input/internal/selection/TextToolbarState;", "setTextToolbarState", "(Landroidx/compose/foundation/text2/input/internal/selection/TextToolbarState;)V", "textToolbarState$delegate", "clearHandleDragging", "", "copy", "cancelSelection", "cut", "deselect", "dispose", "getContentRect", "getHandlePosition", "isStartHandle", "getHandlePosition-tuRUvjQ", "(Z)J", "getSelectionHandleState", "getTextFieldSelection", "Landroidx/compose/ui/text/TextRange;", "rawStartOffset", "rawEndOffset", "previousSelection", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "getTextFieldSelection-qeG_v_k", "(IILandroidx/compose/ui/text/TextRange;ZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)J", "hideTextToolbar", "markStartContentVisibleOffset", "observeChanges", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeTextChanges", "observeTextToolbarVisibility", "paste", "showTextToolbar", "contentRect", "update", "updateHandleDragging", "handle", ImageSelector.POSITION, "updateHandleDragging-Uv8p0NA", "(Landroidx/compose/foundation/text/Handle;J)V", "updateSelection", "textFieldCharSequence", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "startOffset", "endOffset", "allowPreviousSelectionCollapsed", "updateSelection-QNhciaU", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;IIZLandroidx/compose/foundation/text/selection/SelectionAdjustment;Z)J", "updateTextToolbarState", "cursorHandleGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectCursorHandleDragGestures", "detectSelectionHandleDragGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectTextFieldLongPressAndAfterDrag", "requestFocus", "Lkotlin/Function0;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectTextFieldTapGestures", "showKeyboard", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectTouchMode", "selectionHandleGestures", "textFieldGestures", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldSelectionState {
    public static final int $stable = 8;
    private ClipboardManager clipboardManager;
    private Density density;
    private boolean enabled;
    private HapticFeedback hapticFeedBack;
    private boolean isFocused;
    private SelectionLayout previousSelectionLayout;
    private boolean readOnly;
    private final TransformedTextFieldState textFieldState;
    private final TextLayoutState textLayoutState;
    private TextToolbar textToolbar;

    /* JADX INFO: renamed from: isInTouchMode$delegate, reason: from kotlin metadata */
    private final MutableState isInTouchMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);

    /* JADX INFO: renamed from: startContentVisibleOffset$delegate, reason: from kotlin metadata */
    private final MutableState startContentVisibleOffset = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m3208boximpl(Offset.INSTANCE.m3234getUnspecifiedF1C5BW0()), null, 2, null);

    /* JADX INFO: renamed from: rawHandleDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState rawHandleDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m3208boximpl(Offset.INSTANCE.m3234getUnspecifiedF1C5BW0()), null, 2, null);

    /* JADX INFO: renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: showCursorHandle$delegate, reason: from kotlin metadata */
    private final MutableState showCursorHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: textToolbarState$delegate, reason: from kotlin metadata */
    private final MutableState textToolbarState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TextToolbarState.None, null, 2, null);
    private int previousRawDragOffset = -1;

    /* JADX INFO: renamed from: cursorHandle$delegate, reason: from kotlin metadata */
    private final State cursorHandle = SnapshotStateKt.derivedStateOf(new Function0<TextFieldHandleState>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandle$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TextFieldHandleState invoke() {
            TextFieldCharSequence text = this.this$0.textFieldState.getText();
            if (!this.this$0.getShowCursorHandle() || !TextRange.m5362getCollapsedimpl(text.getSelectionInChars()) || text.length() <= 0 || (this.this$0.getDraggingHandle() != Handle.Cursor && !this.this$0.getCursorHandleInBounds())) {
                return TextFieldHandleState.INSTANCE.getHidden();
            }
            return new TextFieldHandleState(true, this.this$0.getCursorRect().m3246getBottomCenterF1C5BW0(), ResolvedTextDirection.Ltr, false, null);
        }
    });

    /* JADX INFO: renamed from: cursorHandleInBounds$delegate, reason: from kotlin metadata */
    private final State cursorHandleInBounds = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Boolean>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleInBounds$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            Rect rectVisibleBounds;
            Snapshot.Companion companion = Snapshot.INSTANCE;
            TextFieldSelectionState textFieldSelectionState = this.this$0;
            Snapshot snapshotCreateNonObservableSnapshot = companion.createNonObservableSnapshot();
            try {
                Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                try {
                    long jM3246getBottomCenterF1C5BW0 = textFieldSelectionState.getCursorRect().m3246getBottomCenterF1C5BW0();
                    snapshotCreateNonObservableSnapshot.dispose();
                    LayoutCoordinates textLayoutCoordinates = this.this$0.getTextLayoutCoordinates();
                    return Boolean.valueOf((textLayoutCoordinates == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates)) == null) ? false : SelectionManagerKt.m1085containsInclusiveUv8p0NA(rectVisibleBounds, jM3246getBottomCenterF1C5BW0));
                } finally {
                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                }
            } catch (Throwable th) {
                snapshotCreateNonObservableSnapshot.dispose();
                throw th;
            }
        }
    });

    /* JADX INFO: renamed from: cursorRect$delegate, reason: from kotlin metadata */
    private final State cursorRect = SnapshotStateKt.derivedStateOf(new Function0<Rect>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorRect$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Rect invoke() {
            float right;
            TextLayoutResult layoutResult = this.this$0.textLayoutState.getLayoutResult();
            if (layoutResult == null) {
                return Rect.INSTANCE.getZero();
            }
            TextFieldCharSequence text = this.this$0.textFieldState.getText();
            if (!TextRange.m5362getCollapsedimpl(text.getSelectionInChars())) {
                return Rect.INSTANCE.getZero();
            }
            Rect cursorRect = layoutResult.getCursorRect(TextRange.m5368getStartimpl(text.getSelectionInChars()));
            float fMo345toPx0680j_4 = this.this$0.density.mo345toPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness());
            if (layoutResult.getLayoutInput().getLayoutDirection() == LayoutDirection.Ltr) {
                right = cursorRect.getLeft() + (fMo345toPx0680j_4 / 2);
            } else {
                right = cursorRect.getRight() - (fMo345toPx0680j_4 / 2);
            }
            float f = fMo345toPx0680j_4 / 2;
            float fCoerceAtLeast = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(right, IntSize.m6056getWidthimpl(layoutResult.getSize()) - f), f);
            return new Rect(fCoerceAtLeast - f, cursorRect.getTop(), fCoerceAtLeast + f, cursorRect.getBottom());
        }
    });

    /* JADX INFO: renamed from: startSelectionHandle$delegate, reason: from kotlin metadata */
    private final State startSelectionHandle = SnapshotStateKt.derivedStateOf(new Function0<TextFieldHandleState>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$startSelectionHandle$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TextFieldHandleState invoke() {
            return this.this$0.getSelectionHandleState(true);
        }
    });

    /* JADX INFO: renamed from: endSelectionHandle$delegate, reason: from kotlin metadata */
    private final State endSelectionHandle = SnapshotStateKt.derivedStateOf(new Function0<TextFieldHandleState>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$endSelectionHandle$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TextFieldHandleState invoke() {
            return this.this$0.getSelectionHandleState(false);
        }
    });

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {0, 0, 0}, l = {498}, m = "detectCursorHandleDragGestures", n = {"this", "cursorDragStart", "cursorDragDelta"}, s = {"L$0", "L$1", "L$2"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.detectCursorHandleDragGestures(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectSelectionHandleDragGestures$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {0, 0, 0, 0}, l = {740}, m = "detectSelectionHandleDragGestures", n = {"this", "dragBeginPosition", "dragTotalDistance", "handle"}, s = {"L$0", "L$1", "L$2", "L$3"})
    static final class C06361 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C06361(Continuation<? super C06361> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.detectSelectionHandleDragGestures(null, false, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {0}, l = {393}, m = "observeChanges", n = {"this"}, s = {"L$0"})
    static final class C06481 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C06481(Continuation<? super C06481> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.observeChanges(this);
        }
    }

    public TextFieldSelectionState(TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, Density density, boolean z, boolean z2, boolean z3) {
        this.textFieldState = transformedTextFieldState;
        this.textLayoutState = textLayoutState;
        this.density = density;
        this.enabled = z;
        this.readOnly = z2;
        this.isFocused = z3;
    }

    /* JADX INFO: renamed from: isFocused, reason: from getter */
    public final boolean getIsFocused() {
        return this.isFocused;
    }

    public final void setFocused(boolean z) {
        this.isFocused = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setInTouchMode(boolean z) {
        this.isInTouchMode.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isInTouchMode() {
        return ((Boolean) this.isInTouchMode.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getStartContentVisibleOffset-F1C5BW0, reason: not valid java name */
    private final long m1227getStartContentVisibleOffsetF1C5BW0() {
        return ((Offset) this.startContentVisibleOffset.getValue()).getPackedValue();
    }

    /* JADX INFO: renamed from: setStartContentVisibleOffset-k-4lQ0M, reason: not valid java name */
    private final void m1230setStartContentVisibleOffsetk4lQ0M(long j) {
        this.startContentVisibleOffset.setValue(Offset.m3208boximpl(j));
    }

    /* JADX INFO: renamed from: getCurrentContentVisibleOffset-F1C5BW0, reason: not valid java name */
    private final long m1224getCurrentContentVisibleOffsetF1C5BW0() {
        Rect rectVisibleBounds;
        LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
        return (textLayoutCoordinates == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates)) == null) ? Offset.INSTANCE.m3234getUnspecifiedF1C5BW0() : rectVisibleBounds.m3254getTopLeftF1C5BW0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getRawHandleDragPosition-F1C5BW0, reason: not valid java name */
    private final long m1226getRawHandleDragPositionF1C5BW0() {
        return ((Offset) this.rawHandleDragPosition.getValue()).getPackedValue();
    }

    /* JADX INFO: renamed from: setRawHandleDragPosition-k-4lQ0M, reason: not valid java name */
    private final void m1229setRawHandleDragPositionk4lQ0M(long j) {
        this.rawHandleDragPosition.setValue(Offset.m3208boximpl(j));
    }

    /* JADX INFO: renamed from: getHandleDragPosition-F1C5BW0, reason: not valid java name */
    public final long m1234getHandleDragPositionF1C5BW0() {
        if (OffsetKt.m3240isUnspecifiedk4lQ0M(m1226getRawHandleDragPositionF1C5BW0())) {
            return Offset.INSTANCE.m3234getUnspecifiedF1C5BW0();
        }
        if (OffsetKt.m3240isUnspecifiedk4lQ0M(m1227getStartContentVisibleOffsetF1C5BW0())) {
            return TextLayoutStateKt.m1192fromDecorationToTextLayoutUv8p0NA(this.textLayoutState, m1226getRawHandleDragPositionF1C5BW0());
        }
        return Offset.m3223minusMKHz9U(Offset.m3224plusMKHz9U(m1226getRawHandleDragPositionF1C5BW0(), m1224getCurrentContentVisibleOffsetF1C5BW0()), m1227getStartContentVisibleOffsetF1C5BW0());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Handle getDraggingHandle() {
        return (Handle) this.draggingHandle.getValue();
    }

    public final void setDraggingHandle(Handle handle) {
        this.draggingHandle.setValue(handle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getShowCursorHandle() {
        return ((Boolean) this.showCursorHandle.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setShowCursorHandle(boolean z) {
        this.showCursorHandle.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final TextToolbarState getTextToolbarState() {
        return (TextToolbarState) this.textToolbarState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextToolbarState(TextToolbarState textToolbarState) {
        this.textToolbarState.setValue(textToolbarState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutCoordinates getTextLayoutCoordinates() {
        LayoutCoordinates textLayoutNodeCoordinates = this.textLayoutState.getTextLayoutNodeCoordinates();
        if (textLayoutNodeCoordinates == null || !textLayoutNodeCoordinates.isAttached()) {
            return null;
        }
        return textLayoutNodeCoordinates;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getEditable() {
        return this.enabled && !this.readOnly;
    }

    public final TextFieldHandleState getCursorHandle() {
        return (TextFieldHandleState) this.cursorHandle.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getCursorHandleInBounds() {
        return ((Boolean) this.cursorHandleInBounds.getValue()).booleanValue();
    }

    public final Rect getCursorRect() {
        return (Rect) this.cursorRect.getValue();
    }

    public final TextFieldHandleState getStartSelectionHandle() {
        return (TextFieldHandleState) this.startSelectionHandle.getValue();
    }

    public final TextFieldHandleState getEndSelectionHandle() {
        return (TextFieldHandleState) this.endSelectionHandle.getValue();
    }

    public final void update(HapticFeedback hapticFeedBack, ClipboardManager clipboardManager, TextToolbar textToolbar, Density density, boolean enabled, boolean readOnly) {
        if (!enabled) {
            hideTextToolbar();
        }
        this.hapticFeedBack = hapticFeedBack;
        this.clipboardManager = clipboardManager;
        this.textToolbar = textToolbar;
        this.density = density;
        this.enabled = enabled;
        this.readOnly = readOnly;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(PointerInputScope pointerInputScope, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_cursorHandleGestures = pointerInputScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = TextFieldSelectionState.this.new AnonymousClass2(this.$this_cursorHandleGestures, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {318}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_cursorHandleGestures = pointerInputScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$this_cursorHandleGestures, continuation);
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
                    if (this.this$0.detectTouchMode(this.$this_cursorHandleGestures, this) == coroutine_suspended) {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(TextFieldSelectionState.this, this.$this_cursorHandleGestures, null), 1, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new C00852(TextFieldSelectionState.this, this.$this_cursorHandleGestures, null), 1, null);
                return BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass3(this.$this_cursorHandleGestures, TextFieldSelectionState.this, null), 1, null);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
        static final class C00852 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00852(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super C00852> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_cursorHandleGestures = pointerInputScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00852(this.this$0, this.$this_cursorHandleGestures, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00852) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (this.this$0.detectCursorHandleDragGestures(this.$this_cursorHandleGestures, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$3", f = "TextFieldSelectionState.kt", i = {}, l = {324}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(PointerInputScope pointerInputScope, TextFieldSelectionState textFieldSelectionState, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.$this_cursorHandleGestures = pointerInputScope;
                this.this$0 = textFieldSelectionState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.$this_cursorHandleGestures, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PointerInputScope pointerInputScope = this.$this_cursorHandleGestures;
                    final TextFieldSelectionState textFieldSelectionState = this.this$0;
                    this.label = 1;
                    if (TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.cursorHandleGestures.2.3.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                            m1235invokek4lQ0M(offset.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                        public final void m1235invokek4lQ0M(long j) {
                            TextToolbarState textToolbarState;
                            TextFieldSelectionState textFieldSelectionState2 = textFieldSelectionState;
                            if (textFieldSelectionState2.getTextToolbarState() == TextToolbarState.Cursor) {
                                textToolbarState = TextToolbarState.None;
                            } else {
                                textToolbarState = TextToolbarState.Cursor;
                            }
                            textFieldSelectionState2.setTextToolbarState(textToolbarState);
                        }
                    }, this, 7, null) == coroutine_suspended) {
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
    }

    public final Object cursorHandleGestures(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(pointerInputScope, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06562 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ Function0<Unit> $requestFocus;
        final /* synthetic */ Function0<Unit> $showKeyboard;
        final /* synthetic */ PointerInputScope $this_textFieldGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06562(PointerInputScope pointerInputScope, Function0<Unit> function0, Function0<Unit> function02, Continuation<? super C06562> continuation) {
            super(2, continuation);
            this.$this_textFieldGestures = pointerInputScope;
            this.$requestFocus = function0;
            this.$showKeyboard = function02;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06562 c06562 = TextFieldSelectionState.this.new C06562(this.$this_textFieldGestures, this.$requestFocus, this.$showKeyboard, continuation);
            c06562.L$0 = obj;
            return c06562;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C06562) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {344}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_textFieldGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_textFieldGestures = pointerInputScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$this_textFieldGestures, continuation);
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
                    if (this.this$0.detectTouchMode(this.$this_textFieldGestures, this) == coroutine_suspended) {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(TextFieldSelectionState.this, this.$this_textFieldGestures, null), 1, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new C00892(TextFieldSelectionState.this, this.$this_textFieldGestures, this.$requestFocus, this.$showKeyboard, null), 1, null);
                return BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass3(TextFieldSelectionState.this, this.$this_textFieldGestures, this.$requestFocus, null), 1, null);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {347}, m = "invokeSuspend", n = {}, s = {})
        static final class C00892 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function0<Unit> $requestFocus;
            final /* synthetic */ Function0<Unit> $showKeyboard;
            final /* synthetic */ PointerInputScope $this_textFieldGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00892(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Function0<Unit> function0, Function0<Unit> function02, Continuation<? super C00892> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_textFieldGestures = pointerInputScope;
                this.$requestFocus = function0;
                this.$showKeyboard = function02;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00892(this.this$0, this.$this_textFieldGestures, this.$requestFocus, this.$showKeyboard, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00892) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (this.this$0.detectTextFieldTapGestures(this.$this_textFieldGestures, this.$requestFocus, this.$showKeyboard, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2$3", f = "TextFieldSelectionState.kt", i = {}, l = {350}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function0<Unit> $requestFocus;
            final /* synthetic */ PointerInputScope $this_textFieldGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Function0<Unit> function0, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_textFieldGestures = pointerInputScope;
                this.$requestFocus = function0;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.this$0, this.$this_textFieldGestures, this.$requestFocus, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (this.this$0.detectTextFieldLongPressAndAfterDrag(this.$this_textFieldGestures, this.$requestFocus, this) == coroutine_suspended) {
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
    }

    public final Object textFieldGestures(PointerInputScope pointerInputScope, Function0<Unit> function0, Function0<Unit> function02, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C06562(pointerInputScope, function0, function02, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06552 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ boolean $isStartHandle;
        final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06552(PointerInputScope pointerInputScope, boolean z, Continuation<? super C06552> continuation) {
            super(2, continuation);
            this.$this_selectionHandleGestures = pointerInputScope;
            this.$isStartHandle = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06552 c06552 = TextFieldSelectionState.this.new C06552(this.$this_selectionHandleGestures, this.$isStartHandle, continuation);
            c06552.L$0 = obj;
            return c06552;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C06552) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {361}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_selectionHandleGestures = pointerInputScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$this_selectionHandleGestures, continuation);
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
                    if (this.this$0.detectTouchMode(this.$this_selectionHandleGestures, this) == coroutine_suspended) {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(TextFieldSelectionState.this, this.$this_selectionHandleGestures, null), 1, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new C00872(this.$this_selectionHandleGestures, TextFieldSelectionState.this, this.$isStartHandle, null), 1, null);
                return BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass3(TextFieldSelectionState.this, this.$this_selectionHandleGestures, this.$isStartHandle, null), 1, null);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {364}, m = "invokeSuspend", n = {}, s = {})
        static final class C00872 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isStartHandle;
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00872(PointerInputScope pointerInputScope, TextFieldSelectionState textFieldSelectionState, boolean z, Continuation<? super C00872> continuation) {
                super(2, continuation);
                this.$this_selectionHandleGestures = pointerInputScope;
                this.this$0 = textFieldSelectionState;
                this.$isStartHandle = z;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00872(this.$this_selectionHandleGestures, this.this$0, this.$isStartHandle, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00872) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PointerInputScope pointerInputScope = this.$this_selectionHandleGestures;
                    final TextFieldSelectionState textFieldSelectionState = this.this$0;
                    final boolean z = this.$isStartHandle;
                    TapOnPosition tapOnPosition = new TapOnPosition() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.selectionHandleGestures.2.2.1
                        @Override // androidx.compose.foundation.text2.input.internal.selection.TapOnPosition
                        /* JADX INFO: renamed from: onEvent-k-4lQ0M */
                        public final void mo1207onEventk4lQ0M(long j) {
                            Handle handle;
                            textFieldSelectionState.markStartContentVisibleOffset();
                            TextFieldSelectionState textFieldSelectionState2 = textFieldSelectionState;
                            if (z) {
                                handle = Handle.SelectionStart;
                            } else {
                                handle = Handle.SelectionEnd;
                            }
                            textFieldSelectionState2.m1231updateHandleDraggingUv8p0NA(handle, SelectionHandlesKt.m1048getAdjustedCoordinatesk4lQ0M(textFieldSelectionState.m1225getHandlePositiontuRUvjQ(z)));
                        }
                    };
                    final TextFieldSelectionState textFieldSelectionState2 = this.this$0;
                    this.label = 1;
                    if (PressDownGestureKt.detectPressDownGesture(pointerInputScope, tapOnPosition, new Function0<Unit>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.selectionHandleGestures.2.2.2
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            textFieldSelectionState2.clearHandleDragging();
                        }
                    }, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$3", f = "TextFieldSelectionState.kt", i = {}, l = {382}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isStartHandle;
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, boolean z, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_selectionHandleGestures = pointerInputScope;
                this.$isStartHandle = z;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.this$0, this.$this_selectionHandleGestures, this.$isStartHandle, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (this.this$0.detectSelectionHandleDragGestures(this.$this_selectionHandleGestures, this.$isStartHandle, this) == coroutine_suspended) {
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
    }

    public final Object selectionHandleGestures(PointerInputScope pointerInputScope, boolean z, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C06552(pointerInputScope, z, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object observeChanges(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.C06481
            if (r0 == 0) goto L14
            r0 = r6
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$1 r0 = (androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.C06481) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$1 r0 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L39
            if (r2 != r4) goto L31
            java.lang.Object r0 = r0.L$0
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState r0 = (androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L2f
            goto L50
        L2f:
            r6 = move-exception
            goto L63
        L31:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2 r6 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2     // Catch: java.lang.Throwable -> L61
            r2 = 0
            r6.<init>(r2)     // Catch: java.lang.Throwable -> L61
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6     // Catch: java.lang.Throwable -> L61
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L61
            r0.label = r4     // Catch: java.lang.Throwable -> L61
            java.lang.Object r6 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r6, r0)     // Catch: java.lang.Throwable -> L61
            if (r6 != r1) goto L4f
            return r1
        L4f:
            r0 = r5
        L50:
            r0.setShowCursorHandle(r3)
            androidx.compose.foundation.text2.input.internal.selection.TextToolbarState r6 = r0.getTextToolbarState()
            androidx.compose.foundation.text2.input.internal.selection.TextToolbarState r1 = androidx.compose.foundation.text2.input.internal.selection.TextToolbarState.None
            if (r6 == r1) goto L5e
            r0.hideTextToolbar()
        L5e:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L61:
            r6 = move-exception
            r0 = r5
        L63:
            r0.setShowCursorHandle(r3)
            androidx.compose.foundation.text2.input.internal.selection.TextToolbarState r1 = r0.getTextToolbarState()
            androidx.compose.foundation.text2.input.internal.selection.TextToolbarState r2 = androidx.compose.foundation.text2.input.internal.selection.TextToolbarState.None
            if (r1 == r2) goto L71
            r0.hideTextToolbar()
        L71:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.observeChanges(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C06492 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C06492(Continuation<? super C06492> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06492 c06492 = TextFieldSelectionState.this.new C06492(continuation);
            c06492.L$0 = obj;
            return c06492;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C06492) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {394}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TextFieldSelectionState textFieldSelectionState, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
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
                    if (this.this$0.observeTextChanges(this) == coroutine_suspended) {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(TextFieldSelectionState.this, null), 3, null);
                return BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00862(TextFieldSelectionState.this, null), 3, null);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {395}, m = "invokeSuspend", n = {}, s = {})
        static final class C00862 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00862(TextFieldSelectionState textFieldSelectionState, Continuation<? super C00862> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00862(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00862) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (this.this$0.observeTextToolbarVisibility(this) == coroutine_suspended) {
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
    }

    public final void updateTextToolbarState(TextToolbarState textToolbarState) {
        setTextToolbarState(textToolbarState);
    }

    public final void dispose() {
        hideTextToolbar();
        this.textToolbar = null;
        this.clipboardManager = null;
        this.hapticFeedBack = null;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectTouchMode$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectTouchMode$2", f = "TextFieldSelectionState.kt", i = {0}, l = {425}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"})
    static final class C06472 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C06472(Continuation<? super C06472> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06472 c06472 = TextFieldSelectionState.this.new C06472(continuation);
            c06472.L$0 = obj;
            return c06472;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C06472) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0032 A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0030 -> B:12:0x0033). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r4.label
                r2 = 1
                if (r1 == 0) goto L1b
                if (r1 != r2) goto L13
                java.lang.Object r1 = r4.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                kotlin.ResultKt.throwOnFailure(r5)
                goto L33
            L13:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L1b:
                kotlin.ResultKt.throwOnFailure(r5)
                java.lang.Object r5 = r4.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
                r1 = r5
            L23:
                androidx.compose.ui.input.pointer.PointerEventPass r5 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                r3 = r4
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r4.L$0 = r1
                r4.label = r2
                java.lang.Object r5 = r1.awaitPointerEvent(r5, r3)
                if (r5 != r0) goto L33
                return r0
            L33:
                androidx.compose.ui.input.pointer.PointerEvent r5 = (androidx.compose.ui.input.pointer.PointerEvent) r5
                androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState r3 = androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.this
                boolean r5 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r5)
                r5 = r5 ^ r2
                androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.access$setInTouchMode(r3, r5)
                goto L23
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.C06472.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object detectTouchMode(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(new C06472(null), continuation);
        return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object detectTextFieldTapGestures(PointerInputScope pointerInputScope, final Function0<Unit> function0, final Function0<Unit> function02, Continuation<? super Unit> continuation) {
        Object objDetectTapAndDoubleTap = TapAndDoubleTapGestureKt.detectTapAndDoubleTap(pointerInputScope, new TapOnPosition() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectTextFieldTapGestures.2
            @Override // androidx.compose.foundation.text2.input.internal.selection.TapOnPosition
            /* JADX INFO: renamed from: onEvent-k-4lQ0M */
            public final void mo1207onEventk4lQ0M(long j) {
                TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectTextFieldTapGestures.2.1
                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "onTapTextField";
                    }
                });
                function0.invoke();
                if (this.getEditable() && this.getIsFocused()) {
                    function02.invoke();
                    if (this.textFieldState.getText().length() > 0) {
                        this.setShowCursorHandle(true);
                    }
                    this.updateTextToolbarState(TextToolbarState.None);
                    int iM1185getOffsetForPosition3MmeM6k$default = TextLayoutState.m1185getOffsetForPosition3MmeM6k$default(this.textLayoutState, j, false, 2, null);
                    if (iM1185getOffsetForPosition3MmeM6k$default >= 0) {
                        this.textFieldState.placeCursorBeforeCharAt(iM1185getOffsetForPosition3MmeM6k$default);
                    }
                }
            }
        }, new TapOnPosition() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectTextFieldTapGestures.3
            @Override // androidx.compose.foundation.text2.input.internal.selection.TapOnPosition
            /* JADX INFO: renamed from: onEvent-k-4lQ0M */
            public final void mo1207onEventk4lQ0M(long j) {
                TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectTextFieldTapGestures.3.1
                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "onDoubleTapTextField";
                    }
                });
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
                int iM1185getOffsetForPosition3MmeM6k$default = TextLayoutState.m1185getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, j, false, 2, null);
                TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
                TextFieldSelectionState.this.textFieldState.m1201selectCharsIn5zctL8(TextFieldSelectionState.m1233updateSelectionQNhciaU$default(textFieldSelectionState, TextFieldCharSequenceKt.m1132TextFieldCharSequence3r_uNRQ$default(textFieldSelectionState.textFieldState.getText(), TextRange.INSTANCE.m5373getZerod9O1mEE(), null, 4, null), iM1185getOffsetForPosition3MmeM6k$default, iM1185getOffsetForPosition3MmeM6k$default, false, SelectionAdjustment.INSTANCE.getWord(), false, 32, null));
            }
        }, continuation);
        return objDetectTapAndDoubleTap == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapAndDoubleTap : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object detectCursorHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r11 instanceof androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1 r0 = (androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1 r0 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1
            r0.<init>(r11)
        L19:
            r6 = r0
            java.lang.Object r11 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L42
            if (r1 != r2) goto L3a
            java.lang.Object r10 = r6.L$2
            kotlin.jvm.internal.Ref$LongRef r10 = (kotlin.jvm.internal.Ref.LongRef) r10
            java.lang.Object r0 = r6.L$1
            kotlin.jvm.internal.Ref$LongRef r0 = (kotlin.jvm.internal.Ref.LongRef) r0
            java.lang.Object r1 = r6.L$0
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState r1 = (androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState) r1
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L37
            goto L96
        L37:
            r11 = move-exception
            goto La1
        L3a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L42:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlin.jvm.internal.Ref$LongRef r11 = new kotlin.jvm.internal.Ref$LongRef
            r11.<init>()
            androidx.compose.ui.geometry.Offset$Companion r1 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r3 = r1.m3234getUnspecifiedF1C5BW0()
            r11.element = r3
            kotlin.jvm.internal.Ref$LongRef r7 = new kotlin.jvm.internal.Ref$LongRef
            r7.<init>()
            androidx.compose.ui.geometry.Offset$Companion r1 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r3 = r1.m3234getUnspecifiedF1C5BW0()
            r7.element = r3
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$2 r1 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$2     // Catch: java.lang.Throwable -> L9c
            r1.<init>()     // Catch: java.lang.Throwable -> L9c
            r3 = r1
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3     // Catch: java.lang.Throwable -> L9c
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$3 r1 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$3     // Catch: java.lang.Throwable -> L9c
            r1.<init>()     // Catch: java.lang.Throwable -> L9c
            r4 = r1
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4     // Catch: java.lang.Throwable -> L9c
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$4 r1 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$4     // Catch: java.lang.Throwable -> L9c
            r1.<init>()     // Catch: java.lang.Throwable -> L9c
            r5 = r1
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5     // Catch: java.lang.Throwable -> L9c
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$5 r1 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$5     // Catch: java.lang.Throwable -> L9c
            r1.<init>()     // Catch: java.lang.Throwable -> L9c
            r8 = r1
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8     // Catch: java.lang.Throwable -> L9c
            r6.L$0 = r9     // Catch: java.lang.Throwable -> L9c
            r6.L$1 = r11     // Catch: java.lang.Throwable -> L9c
            r6.L$2 = r7     // Catch: java.lang.Throwable -> L9c
            r6.label = r2     // Catch: java.lang.Throwable -> L9c
            r1 = r10
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r8
            java.lang.Object r10 = androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGestures(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L9c
            if (r10 != r0) goto L93
            return r0
        L93:
            r1 = r9
            r0 = r11
            r10 = r7
        L96:
            detectCursorHandleDragGestures$onDragStop(r0, r10, r1)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L9c:
            r10 = move-exception
            r1 = r9
            r0 = r11
            r11 = r10
            r10 = r7
        La1:
            detectCursorHandleDragGestures$onDragStop(r0, r10, r1)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectCursorHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectCursorHandleDragGestures$onDragStop(Ref.LongRef longRef, Ref.LongRef longRef2, TextFieldSelectionState textFieldSelectionState) {
        if (OffsetKt.m3238isSpecifiedk4lQ0M(longRef.element)) {
            longRef.element = Offset.INSTANCE.m3234getUnspecifiedF1C5BW0();
            longRef2.element = Offset.INSTANCE.m3234getUnspecifiedF1C5BW0();
            textFieldSelectionState.clearHandleDragging();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v5, types: [T, androidx.compose.foundation.text.Handle] */
    public final Object detectTextFieldLongPressAndAfterDrag(PointerInputScope pointerInputScope, final Function0<Unit> function0, Continuation<? super Unit> continuation) {
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        final Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = Offset.INSTANCE.m3234getUnspecifiedF1C5BW0();
        final Ref.LongRef longRef2 = new Ref.LongRef();
        longRef2.element = Offset.INSTANCE.m3235getZeroF1C5BW0();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = Handle.SelectionEnd;
        Object objDetectDragGesturesAfterLongPress = DragGestureDetectorKt.detectDragGesturesAfterLongPress(pointerInputScope, new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectTextFieldLongPressAndAfterDrag.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                m1240invokek4lQ0M(offset.getPackedValue());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
            public final void m1240invokek4lQ0M(final long j) {
                TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectTextFieldLongPressAndAfterDrag.2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "onDragStart after longPress " + ((Object) Offset.m3227toStringimpl(j));
                    }
                });
                function0.invoke();
                this.m1231updateHandleDraggingUv8p0NA(objectRef.element, j);
                longRef.element = j;
                longRef2.element = Offset.INSTANCE.m3235getZeroF1C5BW0();
                this.previousRawDragOffset = -1;
                if (!this.textLayoutState.m1188isPositionOnTextk4lQ0M(j)) {
                    int iM1185getOffsetForPosition3MmeM6k$default = TextLayoutState.m1185getOffsetForPosition3MmeM6k$default(this.textLayoutState, j, false, 2, null);
                    HapticFeedback hapticFeedback = this.hapticFeedBack;
                    if (hapticFeedback != null) {
                        hapticFeedback.mo4178performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4187getTextHandleMove5zf0vsI());
                    }
                    this.textFieldState.placeCursorBeforeCharAt(iM1185getOffsetForPosition3MmeM6k$default);
                    this.setShowCursorHandle(true);
                    this.updateTextToolbarState(TextToolbarState.Cursor);
                    return;
                }
                if (this.textFieldState.getText().length() == 0) {
                    return;
                }
                int iM1185getOffsetForPosition3MmeM6k$default2 = TextLayoutState.m1185getOffsetForPosition3MmeM6k$default(this.textLayoutState, j, false, 2, null);
                TextFieldSelectionState textFieldSelectionState = this;
                long jM1233updateSelectionQNhciaU$default = TextFieldSelectionState.m1233updateSelectionQNhciaU$default(textFieldSelectionState, TextFieldCharSequenceKt.m1132TextFieldCharSequence3r_uNRQ$default(textFieldSelectionState.textFieldState.getText(), TextRange.INSTANCE.m5373getZerod9O1mEE(), null, 4, null), iM1185getOffsetForPosition3MmeM6k$default2, iM1185getOffsetForPosition3MmeM6k$default2, false, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), false, 32, null);
                this.textFieldState.m1201selectCharsIn5zctL8(jM1233updateSelectionQNhciaU$default);
                this.updateTextToolbarState(TextToolbarState.Selection);
                intRef.element = TextRange.m5368getStartimpl(jM1233updateSelectionQNhciaU$default);
            }
        }, new Function0<Unit>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectTextFieldLongPressAndAfterDrag.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TextFieldSelectionState.detectTextFieldLongPressAndAfterDrag$onDragStop$1(longRef, this, intRef, longRef2);
            }
        }, new Function0<Unit>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectTextFieldLongPressAndAfterDrag.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TextFieldSelectionState.detectTextFieldLongPressAndAfterDrag$onDragStop$1(longRef, this, intRef, longRef2);
            }
        }, new Function2<PointerInputChange, Offset, Unit>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectTextFieldLongPressAndAfterDrag.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Offset offset) {
                m1241invokeUv8p0NA(pointerInputChange, offset.getPackedValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
            public final void m1241invokeUv8p0NA(PointerInputChange pointerInputChange, long j) {
                int iIntValue;
                int iM1187getOffsetForPosition3MmeM6k;
                SelectionAdjustment word;
                T t;
                if (TextFieldSelectionState.this.textFieldState.getText().length() == 0) {
                    return;
                }
                Ref.LongRef longRef3 = longRef2;
                longRef3.element = Offset.m3224plusMKHz9U(longRef3.element, j);
                final long jM3224plusMKHz9U = Offset.m3224plusMKHz9U(longRef.element, longRef2.element);
                TextFieldSelectionStateKt.logDebug(new Function0<String>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectTextFieldLongPressAndAfterDrag.5.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "onDrag after longPress " + ((Object) Offset.m3227toStringimpl(jM3224plusMKHz9U));
                    }
                });
                if (intRef.element < 0 && !TextFieldSelectionState.this.textLayoutState.m1188isPositionOnTextk4lQ0M(jM3224plusMKHz9U)) {
                    iIntValue = TextLayoutState.m1185getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, longRef.element, false, 2, null);
                    iM1187getOffsetForPosition3MmeM6k = TextLayoutState.m1185getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, jM3224plusMKHz9U, false, 2, null);
                    if (iIntValue == iM1187getOffsetForPosition3MmeM6k) {
                        word = SelectionAdjustment.INSTANCE.getNone();
                    } else {
                        word = SelectionAdjustment.INSTANCE.getWord();
                    }
                } else {
                    Integer numValueOf = Integer.valueOf(intRef.element);
                    if (numValueOf.intValue() < 0) {
                        numValueOf = null;
                    }
                    iIntValue = numValueOf != null ? numValueOf.intValue() : TextFieldSelectionState.this.textLayoutState.m1187getOffsetForPosition3MmeM6k(longRef.element, false);
                    iM1187getOffsetForPosition3MmeM6k = TextFieldSelectionState.this.textLayoutState.m1187getOffsetForPosition3MmeM6k(jM3224plusMKHz9U, false);
                    if (intRef.element < 0 && iIntValue == iM1187getOffsetForPosition3MmeM6k) {
                        return;
                    } else {
                        word = SelectionAdjustment.INSTANCE.getWord();
                    }
                }
                int i = iIntValue;
                int i2 = iM1187getOffsetForPosition3MmeM6k;
                SelectionAdjustment selectionAdjustment = word;
                long selectionInChars = TextFieldSelectionState.this.textFieldState.getText().getSelectionInChars();
                TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
                long jM1232updateSelectionQNhciaU = textFieldSelectionState.m1232updateSelectionQNhciaU(textFieldSelectionState.textFieldState.getText(), i, i2, false, selectionAdjustment, false);
                if (TextRange.m5367getReversedimpl(jM1232updateSelectionQNhciaU)) {
                    jM1232updateSelectionQNhciaU = TextFieldSelectionStateKt.m1243reverse5zctL8(jM1232updateSelectionQNhciaU);
                }
                if (intRef.element == -1 && !TextRange.m5362getCollapsedimpl(jM1232updateSelectionQNhciaU)) {
                    intRef.element = TextRange.m5368getStartimpl(jM1232updateSelectionQNhciaU);
                }
                if (!TextRange.m5361equalsimpl0(jM1232updateSelectionQNhciaU, selectionInChars)) {
                    Ref.ObjectRef<Handle> objectRef2 = objectRef;
                    if (TextRange.m5368getStartimpl(jM1232updateSelectionQNhciaU) != TextRange.m5368getStartimpl(selectionInChars) && TextRange.m5363getEndimpl(jM1232updateSelectionQNhciaU) == TextRange.m5363getEndimpl(selectionInChars)) {
                        t = Handle.SelectionStart;
                    } else if ((TextRange.m5368getStartimpl(jM1232updateSelectionQNhciaU) == TextRange.m5368getStartimpl(selectionInChars) && TextRange.m5363getEndimpl(jM1232updateSelectionQNhciaU) != TextRange.m5363getEndimpl(selectionInChars)) || (TextRange.m5368getStartimpl(jM1232updateSelectionQNhciaU) + TextRange.m5363getEndimpl(jM1232updateSelectionQNhciaU)) / 2.0f > (TextRange.m5368getStartimpl(selectionInChars) + TextRange.m5363getEndimpl(selectionInChars)) / 2.0f) {
                        t = Handle.SelectionEnd;
                    } else {
                        t = Handle.SelectionStart;
                    }
                    objectRef2.element = t;
                }
                if (TextRange.m5362getCollapsedimpl(selectionInChars) || !TextRange.m5362getCollapsedimpl(jM1232updateSelectionQNhciaU)) {
                    TextFieldSelectionState.this.textFieldState.m1201selectCharsIn5zctL8(jM1232updateSelectionQNhciaU);
                }
                TextFieldSelectionState.this.m1231updateHandleDraggingUv8p0NA(objectRef.element, jM3224plusMKHz9U);
            }
        }, continuation);
        return objDetectDragGesturesAfterLongPress == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectDragGesturesAfterLongPress : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectTextFieldLongPressAndAfterDrag$onDragStop$1(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Ref.IntRef intRef, Ref.LongRef longRef2) {
        if (OffsetKt.m3238isSpecifiedk4lQ0M(longRef.element)) {
            textFieldSelectionState.clearHandleDragging();
            intRef.element = -1;
            longRef.element = Offset.INSTANCE.m3234getUnspecifiedF1C5BW0();
            longRef2.element = Offset.INSTANCE.m3235getZeroF1C5BW0();
            textFieldSelectionState.previousRawDragOffset = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object detectSelectionHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope r18, final boolean r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectSelectionHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectSelectionHandleDragGestures$onDragStop$2(Ref.LongRef longRef, TextFieldSelectionState textFieldSelectionState, Ref.LongRef longRef2) {
        if (OffsetKt.m3238isSpecifiedk4lQ0M(longRef.element)) {
            textFieldSelectionState.clearHandleDragging();
            longRef.element = Offset.INSTANCE.m3234getUnspecifiedF1C5BW0();
            longRef2.element = Offset.INSTANCE.m3235getZeroF1C5BW0();
            textFieldSelectionState.previousRawDragOffset = -1;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeTextChanges$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* synthetic */ class C06513 extends FunctionReferenceImpl implements Function2<TextFieldCharSequence, CharSequence, Boolean> {
        public static final C06513 INSTANCE = new C06513();

        C06513() {
            super(2, TextFieldCharSequence.class, "contentEquals", "contentEquals(Ljava/lang/CharSequence;)Z", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Boolean invoke(TextFieldCharSequence textFieldCharSequence, CharSequence charSequence) {
            return Boolean.valueOf(textFieldCharSequence.contentEquals(charSequence));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextChanges(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.drop(FlowKt.distinctUntilChanged(SnapshotStateKt.snapshotFlow(new Function0<TextFieldCharSequence>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.observeTextChanges.2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextFieldCharSequence invoke() {
                return TextFieldSelectionState.this.textFieldState.getText();
            }
        }), C06513.INSTANCE), 1).collect(new FlowCollector() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.observeTextChanges.4
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((TextFieldCharSequence) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(TextFieldCharSequence textFieldCharSequence, Continuation<? super Unit> continuation2) {
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.None);
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextToolbarVisibility(Continuation<? super Unit> continuation) {
        Object objCollect = SnapshotStateKt.snapshotFlow(new Function0<Rect>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.observeTextToolbarVisibility.2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Rect invoke() {
                Rect rectIntersect;
                boolean zM5362getCollapsedimpl = TextRange.m5362getCollapsedimpl(TextFieldSelectionState.this.textFieldState.getText().getSelectionInChars());
                if (((zM5362getCollapsedimpl && TextFieldSelectionState.this.getTextToolbarState() == TextToolbarState.Cursor) || (!zM5362getCollapsedimpl && TextFieldSelectionState.this.getTextToolbarState() == TextToolbarState.Selection)) && TextFieldSelectionState.this.getDraggingHandle() == null && TextFieldSelectionState.this.isInTouchMode()) {
                    LayoutCoordinates textLayoutCoordinates = TextFieldSelectionState.this.getTextLayoutCoordinates();
                    Rect rectVisibleBounds = textLayoutCoordinates != null ? SelectionManagerKt.visibleBounds(textLayoutCoordinates) : null;
                    if (rectVisibleBounds != null) {
                        LayoutCoordinates textLayoutCoordinates2 = TextFieldSelectionState.this.getTextLayoutCoordinates();
                        Offset offsetM3208boximpl = textLayoutCoordinates2 != null ? Offset.m3208boximpl(textLayoutCoordinates2.mo4792localToRootMKHz9U(rectVisibleBounds.m3254getTopLeftF1C5BW0())) : null;
                        Intrinsics.checkNotNull(offsetM3208boximpl);
                        Rect rectM3259Recttz77jQw = RectKt.m3259Recttz77jQw(offsetM3208boximpl.getPackedValue(), rectVisibleBounds.m3252getSizeNHjbRc());
                        Rect contentRect = TextFieldSelectionState.this.getContentRect();
                        Rect rect = rectM3259Recttz77jQw.overlaps(contentRect) ? contentRect : null;
                        return (rect == null || (rectIntersect = rect.intersect(rectM3259Recttz77jQw)) == null) ? Rect.INSTANCE.getZero() : rectIntersect;
                    }
                    return Rect.INSTANCE.getZero();
                }
                return Rect.INSTANCE.getZero();
            }
        }).collect(new FlowCollector() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.observeTextToolbarVisibility.3
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((Rect) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(Rect rect, Continuation<? super Unit> continuation2) {
                if (Intrinsics.areEqual(rect, Rect.INSTANCE.getZero())) {
                    TextFieldSelectionState.this.hideTextToolbar();
                } else {
                    TextFieldSelectionState.this.showTextToolbar(rect);
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect getContentRect() {
        float fM3220getYimpl;
        Rect cursorRect;
        Rect cursorRect2;
        TextFieldCharSequence text = this.textFieldState.getText();
        if (TextRange.m5362getCollapsedimpl(text.getSelectionInChars())) {
            LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
            return RectKt.m3259Recttz77jQw(textLayoutCoordinates != null ? textLayoutCoordinates.mo4792localToRootMKHz9U(getCursorRect().m3254getTopLeftF1C5BW0()) : Offset.INSTANCE.m3235getZeroF1C5BW0(), getCursorRect().m3252getSizeNHjbRc());
        }
        LayoutCoordinates textLayoutCoordinates2 = getTextLayoutCoordinates();
        long jMo4792localToRootMKHz9U = textLayoutCoordinates2 != null ? textLayoutCoordinates2.mo4792localToRootMKHz9U(m1225getHandlePositiontuRUvjQ(true)) : Offset.INSTANCE.m3235getZeroF1C5BW0();
        LayoutCoordinates textLayoutCoordinates3 = getTextLayoutCoordinates();
        long jMo4792localToRootMKHz9U2 = textLayoutCoordinates3 != null ? textLayoutCoordinates3.mo4792localToRootMKHz9U(m1225getHandlePositiontuRUvjQ(false)) : Offset.INSTANCE.m3235getZeroF1C5BW0();
        LayoutCoordinates textLayoutCoordinates4 = getTextLayoutCoordinates();
        float fM3220getYimpl2 = 0.0f;
        if (textLayoutCoordinates4 != null) {
            TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
            fM3220getYimpl = Offset.m3220getYimpl(textLayoutCoordinates4.mo4792localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult == null || (cursorRect2 = layoutResult.getCursorRect(TextRange.m5368getStartimpl(text.getSelectionInChars()))) == null) ? 0.0f : cursorRect2.getTop())));
        } else {
            fM3220getYimpl = 0.0f;
        }
        LayoutCoordinates textLayoutCoordinates5 = getTextLayoutCoordinates();
        if (textLayoutCoordinates5 != null) {
            TextLayoutResult layoutResult2 = this.textLayoutState.getLayoutResult();
            fM3220getYimpl2 = Offset.m3220getYimpl(textLayoutCoordinates5.mo4792localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult2 == null || (cursorRect = layoutResult2.getCursorRect(TextRange.m5363getEndimpl(text.getSelectionInChars()))) == null) ? 0.0f : cursorRect.getTop())));
        }
        return new Rect(Math.min(Offset.m3219getXimpl(jMo4792localToRootMKHz9U), Offset.m3219getXimpl(jMo4792localToRootMKHz9U2)), Math.min(fM3220getYimpl, fM3220getYimpl2), Math.max(Offset.m3219getXimpl(jMo4792localToRootMKHz9U), Offset.m3219getXimpl(jMo4792localToRootMKHz9U2)), Math.max(Offset.m3220getYimpl(jMo4792localToRootMKHz9U), Offset.m3220getYimpl(jMo4792localToRootMKHz9U2)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextFieldHandleState getSelectionHandleState(boolean isStartHandle) {
        Rect rectVisibleBounds;
        Rect rectVisibleBounds2;
        Handle handle = isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return TextFieldHandleState.INSTANCE.getHidden();
        }
        long selectionInChars = this.textFieldState.getText().getSelectionInChars();
        if (TextRange.m5362getCollapsedimpl(selectionInChars)) {
            return TextFieldHandleState.INSTANCE.getHidden();
        }
        long jM1225getHandlePositiontuRUvjQ = m1225getHandlePositiontuRUvjQ(isStartHandle);
        if (getDraggingHandle() != handle) {
            LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
            if (!((textLayoutCoordinates == null || (rectVisibleBounds2 = SelectionManagerKt.visibleBounds(textLayoutCoordinates)) == null) ? false : SelectionManagerKt.m1085containsInclusiveUv8p0NA(rectVisibleBounds2, jM1225getHandlePositiontuRUvjQ))) {
                return TextFieldHandleState.INSTANCE.getHidden();
            }
        }
        ResolvedTextDirection bidiRunDirection = layoutResult.getBidiRunDirection(isStartHandle ? TextRange.m5368getStartimpl(selectionInChars) : Math.max(TextRange.m5363getEndimpl(selectionInChars) - 1, 0));
        boolean zM5367getReversedimpl = TextRange.m5367getReversedimpl(selectionInChars);
        LayoutCoordinates textLayoutCoordinates2 = getTextLayoutCoordinates();
        if (textLayoutCoordinates2 != null && (rectVisibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates2)) != null) {
            jM1225getHandlePositiontuRUvjQ = TextLayoutStateKt.m1191coerceIn3MmeM6k(jM1225getHandlePositiontuRUvjQ, rectVisibleBounds);
        }
        return new TextFieldHandleState(true, jM1225getHandlePositiontuRUvjQ, bidiRunDirection, zM5367getReversedimpl, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getHandlePosition-tuRUvjQ, reason: not valid java name */
    public final long m1225getHandlePositiontuRUvjQ(boolean isStartHandle) {
        int iM5363getEndimpl;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Offset.INSTANCE.m3235getZeroF1C5BW0();
        }
        long selectionInChars = this.textFieldState.getText().getSelectionInChars();
        if (isStartHandle) {
            iM5363getEndimpl = TextRange.m5368getStartimpl(selectionInChars);
        } else {
            iM5363getEndimpl = TextRange.m5363getEndimpl(selectionInChars);
        }
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(layoutResult, iM5363getEndimpl, isStartHandle, TextRange.m5367getReversedimpl(selectionInChars));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateHandleDragging-Uv8p0NA, reason: not valid java name */
    public final void m1231updateHandleDraggingUv8p0NA(Handle handle, long position) {
        setDraggingHandle(handle);
        m1229setRawHandleDragPositionk4lQ0M(position);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markStartContentVisibleOffset() {
        Rect rectVisibleBounds;
        LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
        m1230setStartContentVisibleOffsetk4lQ0M((textLayoutCoordinates == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates)) == null) ? Offset.INSTANCE.m3234getUnspecifiedF1C5BW0() : rectVisibleBounds.m3254getTopLeftF1C5BW0());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearHandleDragging() {
        setDraggingHandle(null);
        m1229setRawHandleDragPositionk4lQ0M(Offset.INSTANCE.m3234getUnspecifiedF1C5BW0());
        m1230setStartContentVisibleOffsetk4lQ0M(Offset.INSTANCE.m3234getUnspecifiedF1C5BW0());
    }

    public final void cut() {
        TextFieldCharSequence text = this.textFieldState.getText();
        if (TextRange.m5362getCollapsedimpl(text.getSelectionInChars())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(new AnnotatedString(TextFieldCharSequenceKt.getSelectedText(text).toString(), null, null, 6, null));
        }
        this.textFieldState.deleteSelectedText();
    }

    public static /* synthetic */ void copy$default(TextFieldSelectionState textFieldSelectionState, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        textFieldSelectionState.copy(z);
    }

    public final void copy(boolean cancelSelection) {
        TextFieldCharSequence text = this.textFieldState.getText();
        if (TextRange.m5362getCollapsedimpl(text.getSelectionInChars())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(new AnnotatedString(TextFieldCharSequenceKt.getSelectedText(text).toString(), null, null, 6, null));
        }
        if (cancelSelection) {
            this.textFieldState.collapseSelectionToMax();
        }
    }

    public final void paste() {
        AnnotatedString text;
        String text2;
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager == null || (text = clipboardManager.getText()) == null || (text2 = text.getText()) == null) {
            return;
        }
        TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, text2, false, TextFieldEditUndoBehavior.NeverMerge, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTextToolbar(Rect contentRect) {
        ClipboardManager clipboardManager;
        long selectionInChars = this.textFieldState.getText().getSelectionInChars();
        Function0<Unit> function0 = (getEditable() && (clipboardManager = this.clipboardManager) != null && clipboardManager.hasText()) ? new Function0<Unit>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$showTextToolbar$paste$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.paste();
                this.this$0.updateTextToolbarState(TextToolbarState.None);
            }
        } : null;
        Function0<Unit> function02 = !TextRange.m5362getCollapsedimpl(selectionInChars) ? new Function0<Unit>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$showTextToolbar$copy$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TextFieldSelectionState.copy$default(this.this$0, false, 1, null);
                this.this$0.updateTextToolbarState(TextToolbarState.None);
            }
        } : null;
        Function0<Unit> function03 = (TextRange.m5362getCollapsedimpl(selectionInChars) || !getEditable()) ? null : new Function0<Unit>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$showTextToolbar$cut$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.cut();
                this.this$0.updateTextToolbarState(TextToolbarState.None);
            }
        };
        Function0<Unit> function04 = TextRange.m5364getLengthimpl(selectionInChars) != this.textFieldState.getText().length() ? new Function0<Unit>() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$showTextToolbar$selectAll$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.textFieldState.selectAll();
                this.this$0.updateTextToolbarState(TextToolbarState.Selection);
            }
        } : null;
        TextToolbar textToolbar = this.textToolbar;
        if (textToolbar != null) {
            textToolbar.showMenu(contentRect, function02, function0, function03, function04);
        }
    }

    public final void deselect() {
        if (!TextRange.m5362getCollapsedimpl(this.textFieldState.getText().getSelectionInChars())) {
            this.textFieldState.collapseSelectionToEnd();
        }
        setShowCursorHandle(false);
        updateTextToolbarState(TextToolbarState.None);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideTextToolbar() {
        TextToolbar textToolbar;
        TextToolbar textToolbar2 = this.textToolbar;
        if ((textToolbar2 != null ? textToolbar2.getStatus() : null) != TextToolbarStatus.Shown || (textToolbar = this.textToolbar) == null) {
            return;
        }
        textToolbar.hide();
    }

    /* JADX INFO: renamed from: updateSelection-QNhciaU$default, reason: not valid java name */
    static /* synthetic */ long m1233updateSelectionQNhciaU$default(TextFieldSelectionState textFieldSelectionState, TextFieldCharSequence textFieldCharSequence, int i, int i2, boolean z, SelectionAdjustment selectionAdjustment, boolean z2, int i3, Object obj) {
        return textFieldSelectionState.m1232updateSelectionQNhciaU(textFieldCharSequence, i, i2, z, selectionAdjustment, (i3 & 32) != 0 ? false : z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSelection-QNhciaU, reason: not valid java name */
    public final long m1232updateSelectionQNhciaU(TextFieldCharSequence textFieldCharSequence, int startOffset, int endOffset, boolean isStartHandle, SelectionAdjustment adjustment, boolean allowPreviousSelectionCollapsed) {
        HapticFeedback hapticFeedback;
        TextRange textRangeM5356boximpl = TextRange.m5356boximpl(textFieldCharSequence.getSelectionInChars());
        long packedValue = textRangeM5356boximpl.getPackedValue();
        if (!allowPreviousSelectionCollapsed && TextRange.m5362getCollapsedimpl(packedValue)) {
            textRangeM5356boximpl = null;
        }
        long jM1228getTextFieldSelectionqeG_v_k = m1228getTextFieldSelectionqeG_v_k(startOffset, endOffset, textRangeM5356boximpl, isStartHandle, adjustment);
        if (TextRange.m5361equalsimpl0(jM1228getTextFieldSelectionqeG_v_k, textFieldCharSequence.getSelectionInChars())) {
            return jM1228getTextFieldSelectionqeG_v_k;
        }
        boolean z = TextRange.m5367getReversedimpl(jM1228getTextFieldSelectionqeG_v_k) != TextRange.m5367getReversedimpl(textFieldCharSequence.getSelectionInChars()) && TextRange.m5361equalsimpl0(TextRangeKt.TextRange(TextRange.m5363getEndimpl(jM1228getTextFieldSelectionqeG_v_k), TextRange.m5368getStartimpl(jM1228getTextFieldSelectionqeG_v_k)), textFieldCharSequence.getSelectionInChars());
        if (isInTouchMode() && !z && (hapticFeedback = this.hapticFeedBack) != null) {
            hapticFeedback.mo4178performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4187getTextHandleMove5zf0vsI());
        }
        return jM1228getTextFieldSelectionqeG_v_k;
    }

    /* JADX INFO: renamed from: getTextFieldSelection-qeG_v_k, reason: not valid java name */
    private final long m1228getTextFieldSelectionqeG_v_k(int rawStartOffset, int rawEndOffset, TextRange previousSelection, boolean isStartHandle, SelectionAdjustment adjustment) {
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return TextRange.INSTANCE.m5373getZerod9O1mEE();
        }
        if (previousSelection == null && Intrinsics.areEqual(adjustment, SelectionAdjustment.INSTANCE.getCharacter())) {
            return TextRangeKt.TextRange(rawStartOffset, rawEndOffset);
        }
        SelectionLayout selectionLayoutM1051getTextFieldSelectionLayoutRcvTLA = SelectionLayoutKt.m1051getTextFieldSelectionLayoutRcvTLA(layoutResult, rawStartOffset, rawEndOffset, this.previousRawDragOffset, previousSelection != null ? previousSelection.getPackedValue() : TextRange.INSTANCE.m5373getZerod9O1mEE(), previousSelection == null, isStartHandle);
        if (previousSelection != null && !selectionLayoutM1051getTextFieldSelectionLayoutRcvTLA.shouldRecomputeSelection(this.previousSelectionLayout)) {
            return previousSelection.getPackedValue();
        }
        long jM1039toTextRanged9O1mEE = adjustment.adjust(selectionLayoutM1051getTextFieldSelectionLayoutRcvTLA).m1039toTextRanged9O1mEE();
        this.previousSelectionLayout = selectionLayoutM1051getTextFieldSelectionLayoutRcvTLA;
        if (!isStartHandle) {
            rawStartOffset = rawEndOffset;
        }
        this.previousRawDragOffset = rawStartOffset;
        return jM1039toTextRanged9O1mEE;
    }
}
