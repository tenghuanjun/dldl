package com.tencent.open;

import android.app.Activity;
import android.os.Bundle;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
public class SocialApi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SocialApiIml f1026a;

    public SocialApi(QQToken qQToken) {
        this.f1026a = new SocialApiIml(qQToken);
    }

    public void invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (com.tencent.connect.a.a("SocialApi", iUiListener)) {
            return;
        }
        this.f1026a.invite(activity, bundle, iUiListener);
    }

    public void story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (com.tencent.connect.a.a("SocialApi", iUiListener)) {
            return;
        }
        this.f1026a.story(activity, bundle, iUiListener);
    }

    public void gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (com.tencent.connect.a.a("SocialApi", iUiListener)) {
            return;
        }
        this.f1026a.gift(activity, bundle, iUiListener);
    }

    public void ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (com.tencent.connect.a.a("SocialApi", iUiListener)) {
            return;
        }
        this.f1026a.ask(activity, bundle, iUiListener);
    }
}
