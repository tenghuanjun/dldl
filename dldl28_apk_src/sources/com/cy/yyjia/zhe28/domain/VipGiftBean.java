package com.cy.yyjia.zhe28.domain;

import android.content.Context;
import android.view.View;
import com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VipGiftBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/VipGiftBean;", "", "id", "", "name", "", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(ILjava/lang/String;Lcom/cy/yyjia/zhe28/domain/GameBean;)V", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "getId", "()I", "getName", "()Ljava/lang/String;", "onClick", "", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VipGiftBean {
    public static final int $stable = 8;
    private final GameBean game;
    private final int id;
    private final String name;

    public VipGiftBean(int i, String name, GameBean game) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(game, "game");
        this.id = i;
        this.name = name;
        this.game = game;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final GameBean getGame() {
        return this.game;
    }

    public final void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        Context context = v.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        new ReceiveStatusDialog(context).set(3, this.id, this.game.getId()).setConfirm(new Function1<ReceiveStatusBean, Unit>() { // from class: com.cy.yyjia.zhe28.domain.VipGiftBean.onClick.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ReceiveStatusBean receiveStatusBean) {
                invoke2(receiveStatusBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ReceiveStatusBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Repository.INSTANCE.receive(3, VipGiftBean.this.getId(), String.valueOf(it.getId()), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.domain.VipGiftBean.onClick.1.1
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Result it2) {
                        Intrinsics.checkNotNullParameter(it2, "it");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                        invoke2(result);
                        return Unit.INSTANCE;
                    }
                }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.domain.VipGiftBean.onClick.1.2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Exception it2) {
                        Intrinsics.checkNotNullParameter(it2, "it");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                        invoke2(exc);
                        return Unit.INSTANCE;
                    }
                });
            }
        }).show();
    }
}
