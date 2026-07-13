package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.bytedance.framwork.core.sdklib.DBHelper;
import kotlin.Metadata;

/* JADX INFO: compiled from: UnreadBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R&\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR&\u0010\n\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00048G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/UnreadBean;", "Landroidx/databinding/BaseObservable;", "()V", DBHelper.COL_VALUE, "", "commentNum", "getCommentNum", "()I", "setCommentNum", "(I)V", "msgNum", "getMsgNum", "setMsgNum", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class UnreadBean extends BaseObservable {
    public static final int $stable = 8;
    private int commentNum;
    private int msgNum;

    @Bindable
    public final int getCommentNum() {
        return this.commentNum;
    }

    public final void setCommentNum(int i) {
        this.commentNum = i;
        notifyPropertyChanged(15);
    }

    @Bindable
    public final int getMsgNum() {
        return this.msgNum;
    }

    public final void setMsgNum(int i) {
        this.msgNum = i;
        notifyPropertyChanged(60);
    }
}
