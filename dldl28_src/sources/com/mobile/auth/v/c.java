package com.mobile.auth.v;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.model.ConfigRule;
import com.nirvana.tools.requestqueue.TimeoutResponse;

/* JADX INFO: loaded from: classes3.dex */
public class c extends TimeoutResponse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ConfigRule f801a;

    public c(boolean z) {
        super(z);
    }

    public c(boolean z, ConfigRule configRule) {
        super(z);
        this.f801a = configRule;
    }

    public ConfigRule a() {
        try {
            return this.f801a;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    @Override // com.nirvana.tools.requestqueue.TimeoutResponse
    public boolean isResultTimeout() {
        return false;
    }
}
