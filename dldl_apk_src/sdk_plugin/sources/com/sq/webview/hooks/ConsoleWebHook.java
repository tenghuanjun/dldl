package com.sq.webview.hooks;

import android.webkit.ConsoleMessage;
import com.sq.webview.SimpleWebHook;
import com.sq.webview.util.WebLogUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ConsoleWebHook extends SimpleWebHook {
    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onConsoleMessage(ConsoleMessage consoleMessage) {
        super.onConsoleMessage(consoleMessage);
        if (consoleMessage != null) {
            String str = String.format("onConsoleMessage: lineNumber = %s, sourceId = %s, message = %s", Integer.valueOf(consoleMessage.lineNumber()), consoleMessage.sourceId(), consoleMessage.message());
            int i = AnonymousClass1.$SwitchMap$android$webkit$ConsoleMessage$MessageLevel[consoleMessage.messageLevel().ordinal()];
            if (i == 1 || i == 2) {
                WebLogUtil.i(str);
                return;
            }
            if (i == 3) {
                WebLogUtil.w(str);
            } else if (i == 4) {
                WebLogUtil.e(str);
            } else {
                if (i != 5) {
                    return;
                }
                WebLogUtil.d(str);
            }
        }
    }

    /* JADX INFO: renamed from: com.sq.webview.hooks.ConsoleWebHook$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$webkit$ConsoleMessage$MessageLevel;

        static {
            int[] iArr = new int[ConsoleMessage.MessageLevel.values().length];
            $SwitchMap$android$webkit$ConsoleMessage$MessageLevel = iArr;
            try {
                iArr[ConsoleMessage.MessageLevel.TIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$webkit$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.LOG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$webkit$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$webkit$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$webkit$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.DEBUG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
