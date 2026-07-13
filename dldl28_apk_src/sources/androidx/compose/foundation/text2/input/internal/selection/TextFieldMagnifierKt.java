package androidx.compose.foundation.text2.input.internal.selection;

import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.foundation.text2.input.internal.TextLayoutState;
import androidx.compose.foundation.text2.input.internal.TextLayoutStateKt;
import androidx.compose.foundation.text2.input.internal.TransformedTextFieldState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextFieldMagnifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\f"}, d2 = {"calculateSelectionMagnifierCenterAndroid", "Landroidx/compose/ui/geometry/Offset;", "textFieldState", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "selectionState", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "textLayoutState", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;", "calculateSelectionMagnifierCenterAndroid-hUlJWOE", "(Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;Landroidx/compose/foundation/text2/input/internal/TextLayoutState;J)J", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldMagnifierKt {

    /* JADX INFO: compiled from: TextFieldMagnifier.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Handle.values().length];
            try {
                iArr[Handle.Cursor.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Handle.SelectionStart.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Handle.SelectionEnd.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: calculateSelectionMagnifierCenterAndroid-hUlJWOE, reason: not valid java name */
    public static final long m1212calculateSelectionMagnifierCenterAndroidhUlJWOE(TransformedTextFieldState transformedTextFieldState, TextFieldSelectionState textFieldSelectionState, TextLayoutState textLayoutState, long j) {
        int iM5368getStartimpl;
        long jM1234getHandleDragPositionF1C5BW0 = textFieldSelectionState.m1234getHandleDragPositionF1C5BW0();
        if (OffsetKt.m3240isUnspecifiedk4lQ0M(jM1234getHandleDragPositionF1C5BW0) || transformedTextFieldState.getText().length() == 0) {
            return Offset.INSTANCE.m3234getUnspecifiedF1C5BW0();
        }
        long selectionInChars = transformedTextFieldState.getText().getSelectionInChars();
        Handle draggingHandle = textFieldSelectionState.getDraggingHandle();
        int i = draggingHandle == null ? -1 : WhenMappings.$EnumSwitchMapping$0[draggingHandle.ordinal()];
        if (i == -1) {
            return Offset.INSTANCE.m3234getUnspecifiedF1C5BW0();
        }
        if (i == 1 || i == 2) {
            iM5368getStartimpl = TextRange.m5368getStartimpl(selectionInChars);
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            iM5368getStartimpl = TextRange.m5363getEndimpl(selectionInChars);
        }
        TextLayoutResult layoutResult = textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Offset.INSTANCE.m3234getUnspecifiedF1C5BW0();
        }
        float fM3219getXimpl = Offset.m3219getXimpl(jM1234getHandleDragPositionF1C5BW0);
        int lineForOffset = layoutResult.getLineForOffset(iM5368getStartimpl);
        float lineLeft = layoutResult.getLineLeft(lineForOffset);
        float lineRight = layoutResult.getLineRight(lineForOffset);
        float fCoerceIn = RangesKt.coerceIn(fM3219getXimpl, Math.min(lineLeft, lineRight), Math.max(lineLeft, lineRight));
        if (Math.abs(fM3219getXimpl - fCoerceIn) > IntSize.m6056getWidthimpl(j) / 2) {
            return Offset.INSTANCE.m3234getUnspecifiedF1C5BW0();
        }
        float lineTop = layoutResult.getLineTop(lineForOffset);
        long jOffset = OffsetKt.Offset(fCoerceIn, ((layoutResult.getLineBottom(lineForOffset) - lineTop) / 2) + lineTop);
        LayoutCoordinates textLayoutNodeCoordinates = textLayoutState.getTextLayoutNodeCoordinates();
        if (textLayoutNodeCoordinates != null) {
            if (!textLayoutNodeCoordinates.isAttached()) {
                textLayoutNodeCoordinates = null;
            }
            if (textLayoutNodeCoordinates != null) {
                jOffset = TextLayoutStateKt.m1191coerceIn3MmeM6k(jOffset, SelectionManagerKt.visibleBounds(textLayoutNodeCoordinates));
            }
        }
        return TextLayoutStateKt.m1193fromTextLayoutToCoreUv8p0NA(textLayoutState, jOffset);
    }
}
