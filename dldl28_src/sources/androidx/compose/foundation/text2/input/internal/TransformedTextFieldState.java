package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.text2.input.CodepointTransformation;
import androidx.compose.foundation.text2.input.CodepointTransformationKt;
import androidx.compose.foundation.text2.input.InputTransformation;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.TextFieldCharSequenceKt;
import androidx.compose.foundation.text2.input.TextFieldState;
import androidx.compose.foundation.text2.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text2.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: TransformedTextFieldState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0001\u0018\u0000 H2\u00020\u0001:\u0002HIB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u0019J\u0006\u0010\u001a\u001a\u00020\u0013J,\u0010\u001b\u001a\u00020\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00130\u001f¢\u0006\u0002\b!H\u0086\bJ\u0013\u0010\"\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010$\u001a\u00020%H\u0016J\u0018\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\u000e\u0010&\u001a\u00020%2\u0006\u0010+\u001a\u00020%J\u0018\u0010,\u001a\u00020'2\u0006\u0010(\u001a\u00020'ø\u0001\u0000¢\u0006\u0004\b-\u0010*J\u001b\u0010,\u001a\u00020'2\u0006\u0010+\u001a\u00020%ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b.\u0010/J\u000e\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020%J\u0006\u00102\u001a\u00020\u0013J\u000e\u00103\u001a\u00020\u00132\u0006\u00104\u001a\u000205J\"\u00106\u001a\u00020\u00132\u0006\u00104\u001a\u0002052\b\b\u0002\u00107\u001a\u00020\u001d2\b\b\u0002\u00108\u001a\u000209J*\u0010:\u001a\u00020\u00132\u0006\u00104\u001a\u0002052\u0006\u0010(\u001a\u00020'2\b\b\u0002\u00108\u001a\u000209ø\u0001\u0000¢\u0006\u0004\b;\u0010<J\u0006\u0010=\u001a\u00020\u0013J\u0018\u0010>\u001a\u00020\u00132\u0006\u0010?\u001a\u00020'ø\u0001\u0000¢\u0006\u0004\b@\u0010AJ\u0018\u0010B\u001a\u00020\u00132\u0006\u0010C\u001a\u00020'ø\u0001\u0000¢\u0006\u0004\bD\u0010AJ\b\u0010E\u001a\u00020FH\u0016J\u0006\u0010G\u001a\u00020\u0013R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006J"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "", "textFieldState", "Landroidx/compose/foundation/text2/input/TextFieldState;", "inputTransformation", "Landroidx/compose/foundation/text2/input/InputTransformation;", "codepointTransformation", "Landroidx/compose/foundation/text2/input/CodepointTransformation;", "(Landroidx/compose/foundation/text2/input/TextFieldState;Landroidx/compose/foundation/text2/input/InputTransformation;Landroidx/compose/foundation/text2/input/CodepointTransformation;)V", "text", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "getText", "()Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "transformedText", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState$TransformedText;", "untransformedText", "getUntransformedText", "collapseSelectionToEnd", "", "collapseSelectionToMax", "collectImeNotifications", "", "notifyImeListener", "Landroidx/compose/foundation/text2/input/TextFieldState$NotifyImeListener;", "(Landroidx/compose/foundation/text2/input/TextFieldState$NotifyImeListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSelectedText", "editUntransformedTextAsUser", "notifyImeOfChanges", "", "block", "Lkotlin/Function1;", "Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "Lkotlin/ExtensionFunctionType;", "equals", "other", "hashCode", "", "mapFromTransformed", "Landroidx/compose/ui/text/TextRange;", "range", "mapFromTransformed-GEjPoXI", "(J)J", IjkMediaPlayer.OnNativeInvokeListener.ARG_OFFSET, "mapToTransformed", "mapToTransformed-GEjPoXI", "mapToTransformed--jx7JFs", "(I)J", "placeCursorBeforeCharAt", "transformedOffset", "redo", "replaceAll", "newText", "", "replaceSelectedText", "clearComposition", "undoBehavior", "Landroidx/compose/foundation/text2/input/internal/undo/TextFieldEditUndoBehavior;", "replaceText", "replaceText-Sb-Bc2M", "(Ljava/lang/CharSequence;JLandroidx/compose/foundation/text2/input/internal/undo/TextFieldEditUndoBehavior;)V", "selectAll", "selectCharsIn", "transformedRange", "selectCharsIn-5zc-tL8", "(J)V", "selectUntransformedCharsIn", "untransformedRange", "selectUntransformedCharsIn-5zc-tL8", "toString", "", "undo", "Companion", "TransformedText", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TransformedTextFieldState {
    public static final int $stable = 0;
    private static final Companion Companion = new Companion(null);
    private final CodepointTransformation codepointTransformation;
    private final InputTransformation inputTransformation;
    private final TextFieldState textFieldState;
    private final State<TransformedText> transformedText;

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$collectImeNotifications$1, reason: invalid class name */
    /* JADX INFO: compiled from: TransformedTextFieldState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text2.input.internal.TransformedTextFieldState", f = "TransformedTextFieldState.kt", i = {0, 0}, l = {394}, m = "collectImeNotifications", n = {"this", "notifyImeListener"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TransformedTextFieldState.this.collectImeNotifications(null, this);
        }
    }

    @JvmStatic
    private static final TransformedText calculateTransformedText(TextFieldCharSequence textFieldCharSequence, CodepointTransformation codepointTransformation) {
        return Companion.calculateTransformedText(textFieldCharSequence, codepointTransformation);
    }

    @JvmStatic
    /* JADX INFO: renamed from: mapFromTransformed-xdX6-G0, reason: not valid java name */
    private static final long m1194mapFromTransformedxdX6G0(long j, OffsetMappingCalculator offsetMappingCalculator) {
        return Companion.m1205mapFromTransformedxdX6G0(j, offsetMappingCalculator);
    }

    @JvmStatic
    /* JADX INFO: renamed from: mapToTransformed-xdX6-G0, reason: not valid java name */
    private static final long m1195mapToTransformedxdX6G0(long j, OffsetMappingCalculator offsetMappingCalculator) {
        return Companion.m1206mapToTransformedxdX6G0(j, offsetMappingCalculator);
    }

    public TransformedTextFieldState(TextFieldState textFieldState, InputTransformation inputTransformation, final CodepointTransformation codepointTransformation) {
        this.textFieldState = textFieldState;
        this.inputTransformation = inputTransformation;
        this.codepointTransformation = codepointTransformation;
        this.transformedText = codepointTransformation != null ? SnapshotStateKt.derivedStateOf(new Function0<TransformedText>() { // from class: androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$transformedText$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TransformedTextFieldState.TransformedText invoke() {
                return TransformedTextFieldState.Companion.calculateTransformedText(this.this$0.textFieldState.getText(), codepointTransformation);
            }
        }) : null;
    }

    public final TextFieldCharSequence getText() {
        TransformedText value;
        TextFieldCharSequence text;
        State<TransformedText> state = this.transformedText;
        return (state == null || (value = state.getValue()) == null || (text = value.getText()) == null) ? this.textFieldState.getText() : text;
    }

    public final TextFieldCharSequence getUntransformedText() {
        return this.textFieldState.getText();
    }

    public final void placeCursorBeforeCharAt(int transformedOffset) {
        m1201selectCharsIn5zctL8(TextRangeKt.TextRange(transformedOffset));
    }

    /* JADX INFO: renamed from: selectCharsIn-5zc-tL8, reason: not valid java name */
    public final void m1201selectCharsIn5zctL8(long transformedRange) {
        m1202selectUntransformedCharsIn5zctL8(m1197mapFromTransformedGEjPoXI(transformedRange));
    }

    /* JADX INFO: renamed from: selectUntransformedCharsIn-5zc-tL8, reason: not valid java name */
    public final void m1202selectUntransformedCharsIn5zctL8(long untransformedRange) {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        textFieldState.getMainBuffer().setSelection(TextRange.m5368getStartimpl(untransformedRange), TextRange.m5363getEndimpl(untransformedRange));
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5361equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1150getSelectiond9O1mEE()) && Intrinsics.areEqual(text.getCompositionInChars(), textFieldState.getMainBuffer().m1149getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, textFieldEditUndoBehavior);
    }

    public final void replaceAll(CharSequence newText) {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        EditCommandKt.deleteAll(mainBuffer);
        EditCommandKt.commitText(mainBuffer, newText.toString(), 1);
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5361equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1150getSelectiond9O1mEE()) && Intrinsics.areEqual(text.getCompositionInChars(), textFieldState.getMainBuffer().m1149getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, textFieldEditUndoBehavior);
    }

    public final void selectAll() {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.setSelection(0, mainBuffer.getLength());
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5361equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1150getSelectiond9O1mEE()) && Intrinsics.areEqual(text.getCompositionInChars(), textFieldState.getMainBuffer().m1149getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, textFieldEditUndoBehavior);
    }

    public final void deleteSelectedText() {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.NeverMerge;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.delete(TextRange.m5366getMinimpl(mainBuffer.m1150getSelectiond9O1mEE()), TextRange.m5365getMaximpl(mainBuffer.m1150getSelectiond9O1mEE()));
        mainBuffer.setSelection(TextRange.m5366getMinimpl(mainBuffer.m1150getSelectiond9O1mEE()), TextRange.m5366getMinimpl(mainBuffer.m1150getSelectiond9O1mEE()));
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5361equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1150getSelectiond9O1mEE()) && Intrinsics.areEqual(text.getCompositionInChars(), textFieldState.getMainBuffer().m1149getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, textFieldEditUndoBehavior);
    }

    /* JADX INFO: renamed from: replaceText-Sb-Bc2M$default, reason: not valid java name */
    public static /* synthetic */ void m1196replaceTextSbBc2M$default(TransformedTextFieldState transformedTextFieldState, CharSequence charSequence, long j, TextFieldEditUndoBehavior textFieldEditUndoBehavior, int i, Object obj) {
        if ((i & 4) != 0) {
            textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        transformedTextFieldState.m1200replaceTextSbBc2M(charSequence, j, textFieldEditUndoBehavior);
    }

    /* JADX INFO: renamed from: replaceText-Sb-Bc2M, reason: not valid java name */
    public final void m1200replaceTextSbBc2M(CharSequence newText, long range, TextFieldEditUndoBehavior undoBehavior) {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        long jM1197mapFromTransformedGEjPoXI = m1197mapFromTransformedGEjPoXI(range);
        mainBuffer.replace(TextRange.m5366getMinimpl(jM1197mapFromTransformedGEjPoXI), TextRange.m5365getMaximpl(jM1197mapFromTransformedGEjPoXI), newText);
        int iM5366getMinimpl = TextRange.m5366getMinimpl(jM1197mapFromTransformedGEjPoXI) + newText.length();
        mainBuffer.setSelection(iM5366getMinimpl, iM5366getMinimpl);
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5361equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1150getSelectiond9O1mEE()) && Intrinsics.areEqual(text.getCompositionInChars(), textFieldState.getMainBuffer().m1149getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, undoBehavior);
    }

    public static /* synthetic */ void replaceSelectedText$default(TransformedTextFieldState transformedTextFieldState, CharSequence charSequence, boolean z, TextFieldEditUndoBehavior textFieldEditUndoBehavior, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        transformedTextFieldState.replaceSelectedText(charSequence, z, textFieldEditUndoBehavior);
    }

    public final void replaceSelectedText(CharSequence newText, boolean clearComposition, TextFieldEditUndoBehavior undoBehavior) {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        if (clearComposition) {
            mainBuffer.commitComposition();
        }
        long jM1150getSelectiond9O1mEE = mainBuffer.m1150getSelectiond9O1mEE();
        mainBuffer.replace(TextRange.m5366getMinimpl(jM1150getSelectiond9O1mEE), TextRange.m5365getMaximpl(jM1150getSelectiond9O1mEE), newText);
        int iM5366getMinimpl = TextRange.m5366getMinimpl(jM1150getSelectiond9O1mEE) + newText.length();
        mainBuffer.setSelection(iM5366getMinimpl, iM5366getMinimpl);
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5361equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1150getSelectiond9O1mEE()) && Intrinsics.areEqual(text.getCompositionInChars(), textFieldState.getMainBuffer().m1149getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, undoBehavior);
    }

    public final void collapseSelectionToMax() {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.setSelection(TextRange.m5365getMaximpl(mainBuffer.m1150getSelectiond9O1mEE()), TextRange.m5365getMaximpl(mainBuffer.m1150getSelectiond9O1mEE()));
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5361equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1150getSelectiond9O1mEE()) && Intrinsics.areEqual(text.getCompositionInChars(), textFieldState.getMainBuffer().m1149getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, textFieldEditUndoBehavior);
    }

    public final void collapseSelectionToEnd() {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.setSelection(TextRange.m5363getEndimpl(mainBuffer.m1150getSelectiond9O1mEE()), TextRange.m5363getEndimpl(mainBuffer.m1150getSelectiond9O1mEE()));
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5361equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1150getSelectiond9O1mEE()) && Intrinsics.areEqual(text.getCompositionInChars(), textFieldState.getMainBuffer().m1149getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, textFieldEditUndoBehavior);
    }

    public final void undo() {
        this.textFieldState.getUndoState().undo();
    }

    public final void redo() {
        this.textFieldState.getUndoState().redo();
    }

    public static /* synthetic */ void editUntransformedTextAsUser$default(TransformedTextFieldState transformedTextFieldState, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        TextFieldState textFieldState = transformedTextFieldState.textFieldState;
        InputTransformation inputTransformation = transformedTextFieldState.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        function1.invoke(textFieldState.getMainBuffer());
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5361equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1150getSelectiond9O1mEE()) && Intrinsics.areEqual(text.getCompositionInChars(), textFieldState.getMainBuffer().m1149getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, z, textFieldEditUndoBehavior);
    }

    public final void editUntransformedTextAsUser(boolean notifyImeOfChanges, Function1<? super EditingBuffer, Unit> block) {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        block.invoke(textFieldState.getMainBuffer());
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5361equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1150getSelectiond9O1mEE()) && Intrinsics.areEqual(text.getCompositionInChars(), textFieldState.getMainBuffer().m1149getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, notifyImeOfChanges, textFieldEditUndoBehavior);
    }

    /* JADX INFO: renamed from: mapToTransformed--jx7JFs, reason: not valid java name */
    public final long m1198mapToTransformedjx7JFs(int offset) {
        TransformedText value;
        OffsetMappingCalculator offsetMapping;
        State<TransformedText> state = this.transformedText;
        if (state == null || (value = state.getValue()) == null || (offsetMapping = value.getOffsetMapping()) == null) {
            return TextRangeKt.TextRange(offset);
        }
        return offsetMapping.m1155mapFromSourcejx7JFs(offset);
    }

    /* JADX INFO: renamed from: mapToTransformed-GEjPoXI, reason: not valid java name */
    public final long m1199mapToTransformedGEjPoXI(long range) {
        TransformedText value;
        OffsetMappingCalculator offsetMapping;
        State<TransformedText> state = this.transformedText;
        return (state == null || (value = state.getValue()) == null || (offsetMapping = value.getOffsetMapping()) == null) ? range : Companion.m1206mapToTransformedxdX6G0(range, offsetMapping);
    }

    public final int mapFromTransformed(int offset) {
        TransformedText value;
        OffsetMappingCalculator offsetMapping;
        State<TransformedText> state = this.transformedText;
        return (state == null || (value = state.getValue()) == null || (offsetMapping = value.getOffsetMapping()) == null) ? offset : TextRange.m5366getMinimpl(offsetMapping.m1154mapFromDestjx7JFs(offset));
    }

    /* JADX INFO: renamed from: mapFromTransformed-GEjPoXI, reason: not valid java name */
    public final long m1197mapFromTransformedGEjPoXI(long range) {
        TransformedText value;
        OffsetMappingCalculator offsetMapping;
        State<TransformedText> state = this.transformedText;
        return (state == null || (value = state.getValue()) == null || (offsetMapping = value.getOffsetMapping()) == null) ? range : Companion.m1205mapFromTransformedxdX6G0(range, offsetMapping);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object collectImeNotifications(final androidx.compose.foundation.text2.input.TextFieldState.NotifyImeListener r5, kotlin.coroutines.Continuation<?> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.compose.foundation.text2.input.internal.TransformedTextFieldState.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r6
            androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$collectImeNotifications$1 r0 = (androidx.compose.foundation.text2.input.internal.TransformedTextFieldState.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$collectImeNotifications$1 r0 = new androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$collectImeNotifications$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 == r3) goto L2e
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2e:
            java.lang.Object r5 = r0.L$1
            androidx.compose.foundation.text2.input.TextFieldState$NotifyImeListener r5 = (androidx.compose.foundation.text2.input.TextFieldState.NotifyImeListener) r5
            java.lang.Object r5 = r0.L$0
            androidx.compose.foundation.text2.input.internal.TransformedTextFieldState r5 = (androidx.compose.foundation.text2.input.internal.TransformedTextFieldState) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L75
        L3a:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0
            kotlinx.coroutines.CancellableContinuationImpl r6 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r6.<init>(r2, r3)
            r6.initCancellability()
            r2 = r6
            kotlinx.coroutines.CancellableContinuation r2 = (kotlinx.coroutines.CancellableContinuation) r2
            androidx.compose.foundation.text2.input.TextFieldState r3 = access$getTextFieldState$p(r4)
            r3.addNotifyImeListener$foundation_release(r5)
            androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$collectImeNotifications$2$1 r3 = new androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$collectImeNotifications$2$1
            r3.<init>()
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
            r2.invokeOnCancellation(r3)
            java.lang.Object r5 = r6.getResult()
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r6) goto L72
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L72:
            if (r5 != r1) goto L75
            return r1
        L75:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.TransformedTextFieldState.collectImeNotifications(androidx.compose.foundation.text2.input.TextFieldState$NotifyImeListener, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TransformedTextFieldState)) {
            return false;
        }
        TransformedTextFieldState transformedTextFieldState = (TransformedTextFieldState) other;
        if (Intrinsics.areEqual(this.textFieldState, transformedTextFieldState.textFieldState)) {
            return Intrinsics.areEqual(this.codepointTransformation, transformedTextFieldState.codepointTransformation);
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = this.textFieldState.hashCode() * 31;
        CodepointTransformation codepointTransformation = this.codepointTransformation;
        return iHashCode + (codepointTransformation != null ? codepointTransformation.hashCode() : 0);
    }

    public String toString() {
        return "TransformedTextFieldState(textFieldState=" + this.textFieldState + ", codepointTransformation=" + this.codepointTransformation + ", transformedText=" + this.transformedText + ", text=\"" + ((Object) getText()) + "\")";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: TransformedTextFieldState.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState$TransformedText;", "", "text", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "offsetMapping", "Landroidx/compose/foundation/text2/input/internal/OffsetMappingCalculator;", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/internal/OffsetMappingCalculator;)V", "getOffsetMapping", "()Landroidx/compose/foundation/text2/input/internal/OffsetMappingCalculator;", "getText", "()Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final /* data */ class TransformedText {
        private final OffsetMappingCalculator offsetMapping;
        private final TextFieldCharSequence text;

        public static /* synthetic */ TransformedText copy$default(TransformedText transformedText, TextFieldCharSequence textFieldCharSequence, OffsetMappingCalculator offsetMappingCalculator, int i, Object obj) {
            if ((i & 1) != 0) {
                textFieldCharSequence = transformedText.text;
            }
            if ((i & 2) != 0) {
                offsetMappingCalculator = transformedText.offsetMapping;
            }
            return transformedText.copy(textFieldCharSequence, offsetMappingCalculator);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final TextFieldCharSequence getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final OffsetMappingCalculator getOffsetMapping() {
            return this.offsetMapping;
        }

        public final TransformedText copy(TextFieldCharSequence text, OffsetMappingCalculator offsetMapping) {
            return new TransformedText(text, offsetMapping);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TransformedText)) {
                return false;
            }
            TransformedText transformedText = (TransformedText) other;
            return Intrinsics.areEqual(this.text, transformedText.text) && Intrinsics.areEqual(this.offsetMapping, transformedText.offsetMapping);
        }

        public int hashCode() {
            return (this.text.hashCode() * 31) + this.offsetMapping.hashCode();
        }

        public String toString() {
            return "TransformedText(text=" + ((Object) this.text) + ", offsetMapping=" + this.offsetMapping + ')';
        }

        public TransformedText(TextFieldCharSequence textFieldCharSequence, OffsetMappingCalculator offsetMappingCalculator) {
            this.text = textFieldCharSequence;
            this.offsetMapping = offsetMappingCalculator;
        }

        public final TextFieldCharSequence getText() {
            return this.text;
        }

        public final OffsetMappingCalculator getOffsetMapping() {
            return this.offsetMapping;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: TransformedTextFieldState.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0003J\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0003ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\"\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0003ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState$Companion;", "", "()V", "calculateTransformedText", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState$TransformedText;", "untransformedText", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "codepointTransformation", "Landroidx/compose/foundation/text2/input/CodepointTransformation;", "mapFromTransformed", "Landroidx/compose/ui/text/TextRange;", "range", "mapping", "Landroidx/compose/foundation/text2/input/internal/OffsetMappingCalculator;", "mapFromTransformed-xdX6-G0", "(JLandroidx/compose/foundation/text2/input/internal/OffsetMappingCalculator;)J", "mapToTransformed", "mapToTransformed-xdX6-G0", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final TransformedText calculateTransformedText(TextFieldCharSequence untransformedText, CodepointTransformation codepointTransformation) {
            OffsetMappingCalculator offsetMappingCalculator = new OffsetMappingCalculator();
            CharSequence visualText = CodepointTransformationKt.toVisualText(untransformedText, codepointTransformation, offsetMappingCalculator);
            TextRange textRangeM5356boximpl = null;
            if (visualText == untransformedText) {
                return null;
            }
            long jM1206mapToTransformedxdX6G0 = m1206mapToTransformedxdX6G0(untransformedText.getSelectionInChars(), offsetMappingCalculator);
            TextRange compositionInChars = untransformedText.getCompositionInChars();
            if (compositionInChars != null) {
                textRangeM5356boximpl = TextRange.m5356boximpl(TransformedTextFieldState.Companion.m1206mapToTransformedxdX6G0(compositionInChars.getPackedValue(), offsetMappingCalculator));
            }
            return new TransformedText(TextFieldCharSequenceKt.m1131TextFieldCharSequence3r_uNRQ(visualText, jM1206mapToTransformedxdX6G0, textRangeM5356boximpl), offsetMappingCalculator);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        /* JADX INFO: renamed from: mapToTransformed-xdX6-G0, reason: not valid java name */
        public final long m1206mapToTransformedxdX6G0(long range, OffsetMappingCalculator mapping) {
            long jM1155mapFromSourcejx7JFs = mapping.m1155mapFromSourcejx7JFs(TextRange.m5368getStartimpl(range));
            long jM1155mapFromSourcejx7JFs2 = TextRange.m5362getCollapsedimpl(range) ? jM1155mapFromSourcejx7JFs : mapping.m1155mapFromSourcejx7JFs(TextRange.m5363getEndimpl(range));
            int iMin = Math.min(TextRange.m5366getMinimpl(jM1155mapFromSourcejx7JFs), TextRange.m5366getMinimpl(jM1155mapFromSourcejx7JFs2));
            int iMax = Math.max(TextRange.m5365getMaximpl(jM1155mapFromSourcejx7JFs), TextRange.m5365getMaximpl(jM1155mapFromSourcejx7JFs2));
            if (TextRange.m5367getReversedimpl(range)) {
                return TextRangeKt.TextRange(iMax, iMin);
            }
            return TextRangeKt.TextRange(iMin, iMax);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        /* JADX INFO: renamed from: mapFromTransformed-xdX6-G0, reason: not valid java name */
        public final long m1205mapFromTransformedxdX6G0(long range, OffsetMappingCalculator mapping) {
            long jM1154mapFromDestjx7JFs = mapping.m1154mapFromDestjx7JFs(TextRange.m5368getStartimpl(range));
            long jM1154mapFromDestjx7JFs2 = TextRange.m5362getCollapsedimpl(range) ? jM1154mapFromDestjx7JFs : mapping.m1154mapFromDestjx7JFs(TextRange.m5363getEndimpl(range));
            int iMin = Math.min(TextRange.m5366getMinimpl(jM1154mapFromDestjx7JFs), TextRange.m5366getMinimpl(jM1154mapFromDestjx7JFs2));
            int iMax = Math.max(TextRange.m5365getMaximpl(jM1154mapFromDestjx7JFs), TextRange.m5365getMaximpl(jM1154mapFromDestjx7JFs2));
            if (TextRange.m5367getReversedimpl(range)) {
                return TextRangeKt.TextRange(iMax, iMin);
            }
            return TextRangeKt.TextRange(iMin, iMax);
        }
    }
}
