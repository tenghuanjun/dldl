package com.cy.yyjia.zhe28.domain;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GMGameBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GMGameBean;", "Ljava/io/Serializable;", "gameBean", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(Lcom/cy/yyjia/zhe28/domain/GameBean;)V", "id", "", "name", "", "icon", "(ILjava/lang/String;Ljava/lang/String;)V", "getIcon", "()Ljava/lang/String;", "getId", "()I", "getName", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GMGameBean implements Serializable {
    public static final int $stable = 0;
    private final String icon;
    private final int id;
    private final String name;

    public GMGameBean(int i, String name, String icon) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        this.id = i;
        this.name = name;
        this.icon = icon;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getIcon() {
        return this.icon;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GMGameBean(GameBean gameBean) {
        this(gameBean.getId(), gameBean.getShowName(), gameBean.getIcon());
        Intrinsics.checkNotNullParameter(gameBean, "gameBean");
    }
}
