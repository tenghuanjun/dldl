package com.huyaudbunify.util;

import com.huyaudbunify.core.AuthEvent;
import com.huyaudbunify.handler.ProxyEventHandlerEx;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaEventUtils {
    public static void dispatchAuthEvent(AuthEvent.AuthBaseEvent authBaseEvent) {
        ProxyEventHandlerEx.getInstance().dispatchAuthEvent(authBaseEvent);
    }

    public static boolean checkTimeOut(int i, String str) {
        return checkTimeOut(i, str, true);
    }

    public static boolean checkTimeOut(int i, String str, boolean z) {
        if (i != -2) {
            return false;
        }
        if (!z) {
            return true;
        }
        AuthEvent.TimeoutEvent timeoutEvent = new AuthEvent.TimeoutEvent();
        timeoutEvent.context = str;
        dispatchAuthEvent(timeoutEvent);
        return true;
    }
}
