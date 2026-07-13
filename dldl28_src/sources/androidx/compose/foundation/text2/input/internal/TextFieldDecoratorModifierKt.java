package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.KeyboardType;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextFieldDecoratorModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0000¨\u0006\u0003"}, d2 = {"withDefaultsFrom", "Landroidx/compose/foundation/text/KeyboardOptions;", "defaults", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldDecoratorModifierKt {
    public static final KeyboardOptions withDefaultsFrom(KeyboardOptions keyboardOptions, KeyboardOptions keyboardOptions2) {
        int capitalization;
        int keyboardType;
        int imeAction;
        if (keyboardOptions2 == null) {
            return keyboardOptions;
        }
        if (!KeyboardCapitalization.m5567equalsimpl0(keyboardOptions.getCapitalization(), KeyboardCapitalization.INSTANCE.m5576getNoneIUNYP9k())) {
            capitalization = keyboardOptions.getCapitalization();
        } else {
            capitalization = keyboardOptions2.getCapitalization();
        }
        int i = capitalization;
        boolean z = keyboardOptions.getAutoCorrect() && keyboardOptions2.getAutoCorrect();
        if (!KeyboardType.m5582equalsimpl0(keyboardOptions.getKeyboardType(), KeyboardType.INSTANCE.m5602getTextPjHm6EE())) {
            keyboardType = keyboardOptions.getKeyboardType();
        } else {
            keyboardType = keyboardOptions2.getKeyboardType();
        }
        int i2 = keyboardType;
        if (!ImeAction.m5535equalsimpl0(keyboardOptions.getImeAction(), ImeAction.INSTANCE.m5547getDefaulteUduSuo())) {
            imeAction = keyboardOptions.getImeAction();
        } else {
            imeAction = keyboardOptions2.getImeAction();
        }
        return new KeyboardOptions(i, z, i2, imeAction, null, 16, null);
    }
}
