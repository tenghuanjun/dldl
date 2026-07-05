package net.security.device.api.id.oaid;

import net.security.device.api.id.IOAID;
import net.security.device.api.id.IOAIDGetter;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class UnsupportedImpl implements IOAID {
    @Override // net.security.device.api.id.IOAID
    public boolean supportOAID() {
        return false;
    }

    @Override // net.security.device.api.id.IOAID
    public void doGet(IOAIDGetter iOAIDGetter) {
        iOAIDGetter.onOAIDGetError(new RuntimeException("OAID unsupported"));
    }
}
