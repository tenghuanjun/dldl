package com.huya.component.user.api.data;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PresenterInfo {
    public boolean iCertified;
    public int iPresenterLevel;
    public int iRoomId;
    public long lPresenterExp;
    public long lSignedChannel;

    public String toString() {
        return "PresenterInfo{iCertified=" + this.iCertified + ", lSignedChannel=" + this.lSignedChannel + ", iPresenterLevel=" + this.iPresenterLevel + ", lPresenterExp=" + this.lPresenterExp + ", iRoomId=" + this.iRoomId + AbstractJsonLexerKt.END_OBJ;
    }
}
