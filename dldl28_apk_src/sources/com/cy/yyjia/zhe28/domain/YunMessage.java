package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;

/* JADX INFO: compiled from: YunMessage.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/YunMessage;", "", "()V", "messageID", "", "getMessageID", "()Ljava/lang/String;", "setMessageID", "(Ljava/lang/String;)V", "paydata", "getPaydata", "setPaydata", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class YunMessage {
    public static final int $stable = 8;
    private String messageID;
    private String paydata;

    public final String getMessageID() {
        return this.messageID;
    }

    public final void setMessageID(String str) {
        this.messageID = str;
    }

    public final String getPaydata() {
        return this.paydata;
    }

    public final void setPaydata(String str) {
        this.paydata = str;
    }
}
