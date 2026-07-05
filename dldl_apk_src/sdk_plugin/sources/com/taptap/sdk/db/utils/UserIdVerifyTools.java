package com.taptap.sdk.db.utils;

import com.sqwan.common.constants.SqConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: UserIdVerifyTools.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/taptap/sdk/db/utils/UserIdVerifyTools;", "", "()V", "PATTERN", "Lkotlin/text/Regex;", SqConstants.VERIFY, "", "userId", "", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class UserIdVerifyTools {
    public static final UserIdVerifyTools INSTANCE = new UserIdVerifyTools();
    private static final Regex PATTERN = new Regex("^[a-zA-Z0-9_\\-+/=.,:]{1,160}$");

    private UserIdVerifyTools() {
    }

    public final void verify(String userId) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        String str = userId;
        if (str.length() == 0) {
            throw new IllegalStateException("userId is empty, userId: " + userId);
        }
        if (!PATTERN.matches(str)) {
            throw new IllegalStateException("userId is invalid, userId: " + userId);
        }
        if (userId.length() <= 160) {
            return;
        }
        throw new IllegalStateException("userId is too long, userId: " + userId);
    }
}
