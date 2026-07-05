package com.huyaudbunify.handler;

import android.os.Message;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.core.AuthEvent;
import com.huyaudbunify.core.LoginEvent;
import com.huyaudbunify.util.HuyaAccountSaveUtils;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ProxyEventHandlerEx {
    private static volatile ProxyEventHandlerEx sAuthEventHandler;
    private CopyOnWriteArraySet<HYHandler> mHandlers = new CopyOnWriteArraySet<>();

    public static class ProxyLoginResNGEvent extends LoginEvent.LoginResNGEvent {
        public AuthEvent.AuthBaseEvent event = null;
    }

    public static ProxyEventHandlerEx getInstance() {
        if (sAuthEventHandler == null) {
            synchronized (ProxyEventHandlerEx.class) {
                sAuthEventHandler = new ProxyEventHandlerEx();
            }
        }
        return sAuthEventHandler;
    }

    public synchronized void addHandler(HYHandler hYHandler) {
        this.mHandlers.add(hYHandler);
    }

    public synchronized void removeHandler(HYHandler hYHandler) {
        this.mHandlers.remove(hYHandler);
    }

    public void dispatchAuthEvent(AuthEvent.AuthBaseEvent authBaseEvent) {
        ProxyLoginResNGEvent proxyLoginResNGEvent = new ProxyLoginResNGEvent();
        proxyLoginResNGEvent.event = authBaseEvent;
        proxyLoginResNGEvent.uSrvResCode = 4;
        handleLoginRegTrustInfo(authBaseEvent);
        despatchMsgEx(LoginEvent.LoginMessage.onLoginNGRes, proxyLoginResNGEvent);
    }

    public void dispatchKickOff(LoginEvent.ETLoginKickoff eTLoginKickoff) {
        despatchMsgEx(LoginEvent.LoginMessage.onKickoff, eTLoginKickoff);
    }

    public void dspatchNewLogin(LoginEvent.ETNewLogin eTNewLogin) {
        despatchMsgEx(LoginEvent.LoginMessage.onNewLogin, eTNewLogin);
    }

    public void dispatchAnonymInfo(LoginEvent.ETMyInfoAnonym eTMyInfoAnonym) {
        despatchMsgEx(LoginEvent.LoginMessage.onMyInfoAnonym, eTMyInfoAnonym);
    }

    public void handleLoginRegTrustInfo(AuthEvent.AuthBaseEvent authBaseEvent) {
        if (authBaseEvent instanceof AuthEvent.LoginEvent) {
            AuthEvent.LoginEvent loginEvent = (AuthEvent.LoginEvent) authBaseEvent;
            if (loginEvent.uiAction == 0) {
                if (!loginEvent.getUid().isEmpty()) {
                    HuyaAccountSaveUtils.getInstance().setUid(Long.parseLong(loginEvent.getUid()));
                }
                HuyaAccountSaveUtils.getInstance().setPassport(loginEvent.passport);
                HuyaAccountSaveUtils.getInstance().setMobileMask(loginEvent.mobileMask);
                HuyaAccountSaveUtils.getInstance().setLogin(true);
                HuyaAuth.getInstance().regTrustInfo(10);
                return;
            }
            HuyaAccountSaveUtils.getInstance().setLogin(false);
            HuyaAccountSaveUtils.getInstance().setUid(0L);
            HuyaAccountSaveUtils.getInstance().setPassport("");
            HuyaAccountSaveUtils.getInstance().setMobileMask("");
        }
    }

    public void despatchMsg(Message message) {
        for (HYHandler hYHandler : this.mHandlers) {
            Message messageObtain = Message.obtain();
            messageObtain.what = message.what;
            messageObtain.obj = message.obj;
            if (hYHandler.canHandleMessage(messageObtain.what)) {
                hYHandler.sendMessage(messageObtain);
            }
        }
    }

    public synchronized void despatchMsgEx(int i, Object... objArr) {
        try {
            for (HYHandler hYHandler : this.mHandlers) {
                Message messageObtain = Message.obtain();
                messageObtain.what = i;
                messageObtain.obj = objArr;
                if (hYHandler.canHandleMessage(messageObtain.what)) {
                    hYHandler.sendMessage(messageObtain);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
