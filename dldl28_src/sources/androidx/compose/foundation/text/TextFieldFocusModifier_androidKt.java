package androidx.compose.foundation.text;

import android.view.InputDevice;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.core.view.InputDeviceCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: TextFieldFocusModifier.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u001e\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"interceptDPadAndMoveFocus", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/TextFieldState;", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "isKeyCode", "", "Landroidx/compose/ui/input/key/KeyEvent;", "keyCode", "", "isKeyCode-YhN2O0w", "(Landroid/view/KeyEvent;I)Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldFocusModifier_androidKt {
    public static final Modifier interceptDPadAndMoveFocus(Modifier modifier, final TextFieldState textFieldState, final FocusManager focusManager) {
        return KeyInputModifierKt.onPreviewKeyEvent(modifier, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m960invokeZmokQxo(keyEvent.m4504unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m960invokeZmokQxo(android.view.KeyEvent keyEvent) {
                InputDevice device = keyEvent.getDevice();
                boolean zMo3153moveFocus3ESFkO8 = false;
                if (device != null && device.supportsSource(InputDeviceCompat.SOURCE_DPAD) && !device.isVirtual() && KeyEventType.m4508equalsimpl0(KeyEvent_androidKt.m4516getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4512getKeyDownCS__XNY())) {
                    if (TextFieldFocusModifier_androidKt.m959isKeyCodeYhN2O0w(keyEvent, 19)) {
                        zMo3153moveFocus3ESFkO8 = focusManager.mo3153moveFocus3ESFkO8(FocusDirection.INSTANCE.m3152getUpdhqQ8s());
                    } else if (TextFieldFocusModifier_androidKt.m959isKeyCodeYhN2O0w(keyEvent, 20)) {
                        zMo3153moveFocus3ESFkO8 = focusManager.mo3153moveFocus3ESFkO8(FocusDirection.INSTANCE.m3145getDowndhqQ8s());
                    } else if (TextFieldFocusModifier_androidKt.m959isKeyCodeYhN2O0w(keyEvent, 21)) {
                        zMo3153moveFocus3ESFkO8 = focusManager.mo3153moveFocus3ESFkO8(FocusDirection.INSTANCE.m3148getLeftdhqQ8s());
                    } else if (TextFieldFocusModifier_androidKt.m959isKeyCodeYhN2O0w(keyEvent, 22)) {
                        zMo3153moveFocus3ESFkO8 = focusManager.mo3153moveFocus3ESFkO8(FocusDirection.INSTANCE.m3151getRightdhqQ8s());
                    } else if (TextFieldFocusModifier_androidKt.m959isKeyCodeYhN2O0w(keyEvent, 23)) {
                        SoftwareKeyboardController keyboardController = textFieldState.getKeyboardController();
                        if (keyboardController != null) {
                            keyboardController.show();
                        }
                        zMo3153moveFocus3ESFkO8 = true;
                    }
                }
                return Boolean.valueOf(zMo3153moveFocus3ESFkO8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isKeyCode-YhN2O0w, reason: not valid java name */
    public static final boolean m959isKeyCodeYhN2O0w(android.view.KeyEvent keyEvent, int i) {
        return Key_androidKt.m4522getNativeKeyCodeYVgTNJs(KeyEvent_androidKt.m4515getKeyZmokQxo(keyEvent)) == i;
    }
}
