package com.bytedance.applog.alink;

import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00052\u000e\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tH'J0\u0010\n\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00052\u000e\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tH'¨\u0006\u000b"}, d2 = {"Lcom/bytedance/applog/alink/IALinkListener;", "", "onALinkData", "", "routingInfo", "", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onAttributionData", "agent_liteChinaRelease"}, k = 1, mv = {1, 1, 16})
public interface IALinkListener {
    void onALinkData(Map<String, String> routingInfo, Exception exception);

    void onAttributionData(Map<String, String> routingInfo, Exception exception);
}
