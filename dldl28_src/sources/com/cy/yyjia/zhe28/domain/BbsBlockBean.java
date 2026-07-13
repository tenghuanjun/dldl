package com.cy.yyjia.zhe28.domain;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BbsBlockBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0010B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsBlockBean;", "", "show", "", "name", "", "list", "", "Lcom/cy/yyjia/zhe28/domain/BbsBlockBean$ListBean;", "(ILjava/lang/String;Ljava/util/List;)V", "getList", "()Ljava/util/List;", "getName", "()Ljava/lang/String;", "getShow", "()I", "ListBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BbsBlockBean {
    public static final int $stable = 8;
    private final List<ListBean> list;
    private final String name;
    private final int show;

    public BbsBlockBean(int i, String name, List<ListBean> list) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(list, "list");
        this.show = i;
        this.name = name;
        this.list = list;
    }

    public final int getShow() {
        return this.show;
    }

    public final String getName() {
        return this.name;
    }

    public final List<ListBean> getList() {
        return this.list;
    }

    /* JADX INFO: compiled from: BbsBlockBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsBlockBean$ListBean;", "", "id", "", "name", "", "icon", "bg", "views", "createTime", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBg", "()Ljava/lang/String;", "getCreateTime", "getIcon", "getId", "()I", "getName", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getViews", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ListBean {
        public static final int $stable = 8;
        private final String bg;
        private final String createTime;
        private final String icon;
        private final int id;
        private final String name;
        private boolean selected;
        private final String views;

        public ListBean(int i, String name, String icon, String bg, String views, String createTime) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(views, "views");
            Intrinsics.checkNotNullParameter(createTime, "createTime");
            this.id = i;
            this.name = name;
            this.icon = icon;
            this.bg = bg;
            this.views = views;
            this.createTime = createTime;
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

        public final String getBg() {
            return this.bg;
        }

        public final String getViews() {
            return this.views;
        }

        public final String getCreateTime() {
            return this.createTime;
        }

        public final boolean getSelected() {
            return this.selected;
        }

        public final void setSelected(boolean z) {
            this.selected = z;
        }
    }
}
