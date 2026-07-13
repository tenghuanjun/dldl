package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BtnBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BtnBean;", "", "actionId", "", "typeId", "auth", "url", "", "title", "(IIILjava/lang/String;Ljava/lang/String;)V", "getActionId", "()I", "getAuth", "getTitle", "()Ljava/lang/String;", "getTypeId", "getUrl", "onClick", "", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public class BtnBean {
    public static final int $stable = 0;
    private final int actionId;
    private final int auth;
    private final String title;
    private final int typeId;
    private final String url;

    public BtnBean(int i, int i2, int i3, String url, String str) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.actionId = i;
        this.typeId = i2;
        this.auth = i3;
        this.url = url;
        this.title = str;
    }

    public final int getActionId() {
        return this.actionId;
    }

    public final int getTypeId() {
        return this.typeId;
    }

    public final int getAuth() {
        return this.auth;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getTitle() {
        return this.title;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onClick(android.view.View r9) {
        /*
            Method dump skipped, instruction units count: 580
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.domain.BtnBean.onClick(android.view.View):void");
    }
}
