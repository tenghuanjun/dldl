package com.cy.yyjia.zhe28.domain;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameViewModel.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\tR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0007\"\u0004\b\u0011\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "currentPosition", "Landroidx/lifecycle/MutableLiveData;", "", "getCurrentPosition", "()Landroidx/lifecycle/MutableLiveData;", "setCurrentPosition", "(Landroidx/lifecycle/MutableLiveData;)V", "data", "Lcom/cy/yyjia/zhe28/domain/GameDetailBean;", "getData", "setData", "score", "Lcom/cy/yyjia/zhe28/domain/GameScoreBean;", "getScore", "setScore", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameViewModel extends ViewModel {
    public static final int $stable = 8;
    private MutableLiveData<GameDetailBean> data = new MutableLiveData<>();
    private MutableLiveData<GameScoreBean> score = new MutableLiveData<>();
    private MutableLiveData<Integer> currentPosition = new MutableLiveData<>();

    public final MutableLiveData<GameDetailBean> getData() {
        return this.data;
    }

    public final void setData(MutableLiveData<GameDetailBean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.data = mutableLiveData;
    }

    public final MutableLiveData<GameScoreBean> getScore() {
        return this.score;
    }

    public final void setScore(MutableLiveData<GameScoreBean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.score = mutableLiveData;
    }

    public final MutableLiveData<Integer> getCurrentPosition() {
        return this.currentPosition;
    }

    public final void setCurrentPosition(MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.currentPosition = mutableLiveData;
    }
}
