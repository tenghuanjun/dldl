package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SanbaoBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SanbaoBean;", "", "id", "", "is_received", "title", "", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(IILjava/lang/String;Lcom/cy/yyjia/zhe28/domain/GameBean;)V", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "getId", "()I", "set_received", "(I)V", "getTitle", "()Ljava/lang/String;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SanbaoBean {
    public static final int $stable = 8;
    private final GameBean game;
    private final int id;
    private int is_received;
    private final String title;

    public SanbaoBean(int i, int i2, String title, GameBean game) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(game, "game");
        this.id = i;
        this.is_received = i2;
        this.title = title;
        this.game = game;
    }

    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: is_received, reason: from getter */
    public final int getIs_received() {
        return this.is_received;
    }

    public final void set_received(int i) {
        this.is_received = i;
    }

    public final String getTitle() {
        return this.title;
    }

    public final GameBean getGame() {
        return this.game;
    }
}
