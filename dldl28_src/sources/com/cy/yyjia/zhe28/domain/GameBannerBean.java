package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameBannerBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameBannerBean;", "", "video", "", "pic", "(Ljava/lang/String;Ljava/lang/String;)V", "horizontal", "", "getHorizontal", "()Z", "setHorizontal", "(Z)V", "getPic", "()Ljava/lang/String;", "getVideo", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameBannerBean {
    public static final int $stable = 8;
    private boolean horizontal;
    private final String pic;
    private final String video;

    public GameBannerBean(String str, String pic) {
        Intrinsics.checkNotNullParameter(pic, "pic");
        this.video = str;
        this.pic = pic;
        this.horizontal = true;
    }

    public final String getVideo() {
        return this.video;
    }

    public final String getPic() {
        return this.pic;
    }

    public final boolean getHorizontal() {
        return this.horizontal;
    }

    public final void setHorizontal(boolean z) {
        this.horizontal = z;
    }
}
