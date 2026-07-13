package androidx.compose.foundation.text2.input.internal.undo;

import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.TextFieldCharSequenceKt;
import androidx.compose.foundation.text2.input.TextFieldState;
import androidx.compose.foundation.text2.input.internal.EditingBuffer;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextUndoOperation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0006"}, d2 = {"redo", "", "Landroidx/compose/foundation/text2/input/TextFieldState;", "op", "Landroidx/compose/foundation/text2/input/internal/undo/TextUndoOperation;", "undo", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextUndoOperationKt {
    public static final void undo(TextFieldState textFieldState, TextUndoOperation textUndoOperation) {
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.replace(textUndoOperation.getIndex(), textUndoOperation.getIndex() + textUndoOperation.getPostText().length(), textUndoOperation.getPreText());
        mainBuffer.setSelection(TextRange.m5368getStartimpl(textUndoOperation.getPreSelection()), TextRange.m5363getEndimpl(textUndoOperation.getPreSelection()));
        TextFieldCharSequence textFieldCharSequenceM1131TextFieldCharSequence3r_uNRQ = TextFieldCharSequenceKt.m1131TextFieldCharSequence3r_uNRQ(textFieldState.getMainBuffer().toString(), textFieldState.getMainBuffer().m1150getSelectiond9O1mEE(), textFieldState.getMainBuffer().m1149getCompositionMzsxiRA());
        textFieldState.setText(textFieldCharSequenceM1131TextFieldCharSequence3r_uNRQ);
        textFieldState.notifyIme(text, textFieldCharSequenceM1131TextFieldCharSequence3r_uNRQ);
    }

    public static final void redo(TextFieldState textFieldState, TextUndoOperation textUndoOperation) {
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.replace(textUndoOperation.getIndex(), textUndoOperation.getIndex() + textUndoOperation.getPreText().length(), textUndoOperation.getPostText());
        mainBuffer.setSelection(TextRange.m5368getStartimpl(textUndoOperation.getPostSelection()), TextRange.m5363getEndimpl(textUndoOperation.getPostSelection()));
        TextFieldCharSequence textFieldCharSequenceM1131TextFieldCharSequence3r_uNRQ = TextFieldCharSequenceKt.m1131TextFieldCharSequence3r_uNRQ(textFieldState.getMainBuffer().toString(), textFieldState.getMainBuffer().m1150getSelectiond9O1mEE(), textFieldState.getMainBuffer().m1149getCompositionMzsxiRA());
        textFieldState.setText(textFieldCharSequenceM1131TextFieldCharSequence3r_uNRQ);
        textFieldState.notifyIme(text, textFieldCharSequenceM1131TextFieldCharSequence3r_uNRQ);
    }
}
