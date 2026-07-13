package com.cy.yyjia.zhe28.domain;

import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.tencent.open.SocialConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommentConfig.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CommentConfig;", "", SocialConstants.PARAM_APP_DESC, "", "tips", "rule", "tags", "", "Lcom/cy/yyjia/zhe28/domain/CommentConfig$Tag;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getDesc", "()Ljava/lang/String;", "getRule", "getTags", "()Ljava/util/List;", "getTips", "Content", "Tag", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CommentConfig {
    public static final int $stable = 8;
    private final String desc;
    private final String rule;
    private final List<Tag> tags;
    private final String tips;

    public CommentConfig(String desc, String tips, String rule, List<Tag> tags) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(tips, "tips");
        Intrinsics.checkNotNullParameter(rule, "rule");
        Intrinsics.checkNotNullParameter(tags, "tags");
        this.desc = desc;
        this.tips = tips;
        this.rule = rule;
        this.tags = tags;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getTips() {
        return this.tips;
    }

    public final String getRule() {
        return this.rule;
    }

    public final List<Tag> getTags() {
        return this.tags;
    }

    /* JADX INFO: compiled from: CommentConfig.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CommentConfig$Tag;", "", "id", "", "icon", "", "name", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "", "Lcom/cy/yyjia/zhe28/domain/CommentConfig$Content;", "selected", "", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/List;Z)V", "getContent", "()Ljava/util/List;", "getIcon", "()Ljava/lang/String;", "getId", "()I", "getName", "getSelected", "()Z", "setSelected", "(Z)V", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Tag {
        public static final int $stable = 8;
        private final List<Content> content;
        private final String icon;
        private final int id;
        private final String name;
        private boolean selected;

        public Tag(int i, String icon, String name, List<Content> content, boolean z) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(content, "content");
            this.id = i;
            this.icon = icon;
            this.name = name;
            this.content = content;
            this.selected = z;
        }

        public final int getId() {
            return this.id;
        }

        public final String getIcon() {
            return this.icon;
        }

        public final String getName() {
            return this.name;
        }

        public final List<Content> getContent() {
            return this.content;
        }

        public final boolean getSelected() {
            return this.selected;
        }

        public final void setSelected(boolean z) {
            this.selected = z;
        }
    }

    /* JADX INFO: compiled from: CommentConfig.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CommentConfig$Content;", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "", "(Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Content {
        public static final int $stable = 0;
        private final String content;

        public Content(String content) {
            Intrinsics.checkNotNullParameter(content, "content");
            this.content = content;
        }

        public final String getContent() {
            return this.content;
        }
    }
}
