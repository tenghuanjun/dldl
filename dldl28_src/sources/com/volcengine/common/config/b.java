package com.volcengine.common.config;

import com.volcengine.common.innerapi.ConfigService;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
interface b {

    public interface a {
        void a(int i, String str);
    }

    List<String> a();

    void a(a aVar);

    void a(ConfigService configService, int i, String str);
}
