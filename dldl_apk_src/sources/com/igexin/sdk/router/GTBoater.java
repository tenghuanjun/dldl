package com.igexin.sdk.router;

import com.igexin.base.api.ShipsManager;
import com.igexin.base.boatman.Boater;
import com.igexin.sdk.router.site.BridgeMessageSite;
import com.igexin.sdk.router.site.InitSite;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class GTBoater extends Boater {
    private static GTBoater instance;

    private GTBoater() {
        ShipsManager.get().register(new BridgeMessageSite());
        ShipsManager.get().register(new InitSite());
    }

    public static GTBoater getInstance() {
        if (instance == null) {
            synchronized (GTBoater.class) {
                if (instance == null) {
                    instance = new GTBoater();
                }
            }
        }
        return instance;
    }

    @Override // com.igexin.base.boatman.Boater
    public String getTag() {
        return ShipsManager.TAG_GT;
    }

    @Override // com.igexin.base.boatman.Boater
    public Object postSync(Object obj) {
        return super.postSync(obj);
    }

    @Override // com.igexin.base.boatman.Boater
    public boolean removeSticky(Object obj) {
        return super.removeSticky(obj);
    }
}
