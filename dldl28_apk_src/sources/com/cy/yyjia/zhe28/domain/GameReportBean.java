package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.bytedance.framwork.core.sdklib.DBHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameReportBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\b\u0014\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\bR\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R&\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00048G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\fR&\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00048G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\n\"\u0004\b\u0017\u0010\fR \u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\n\"\u0004\b\u0019\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameReportBean;", "Landroidx/databinding/BaseObservable;", "()V", "game", "", "pic", "", "video", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getGame", "()Ljava/lang/String;", "setGame", "(Ljava/lang/String;)V", "getPic", "()Ljava/util/List;", "setPic", "(Ljava/util/List;)V", DBHelper.COL_VALUE, "reason", "getReason", "setReason", "str", "getStr", "setStr", "getVideo", "setVideo", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameReportBean extends BaseObservable {
    public static final int $stable = 8;

    @Bindable
    private String game;
    private List<String> pic;
    private String reason;
    private String str;

    @Bindable
    private String video;

    public final String getGame() {
        return this.game;
    }

    public final void setGame(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.game = str;
    }

    public final List<String> getPic() {
        return this.pic;
    }

    public final void setPic(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.pic = list;
    }

    public final String getVideo() {
        return this.video;
    }

    public final void setVideo(String str) {
        this.video = str;
    }

    public GameReportBean(String game, List<String> pic, String str) {
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(pic, "pic");
        this.game = game;
        this.pic = pic;
        this.video = str;
        this.str = "";
        this.reason = "";
    }

    public GameReportBean() {
        this("", CollectionsKt.arrayListOf(""), null);
    }

    @Bindable
    public final String getStr() {
        return this.str;
    }

    public final void setStr(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.str = value;
        notifyPropertyChanged(104);
    }

    @Bindable
    public final String getReason() {
        return this.reason;
    }

    public final void setReason(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.reason = value;
        notifyPropertyChanged(84);
    }
}
