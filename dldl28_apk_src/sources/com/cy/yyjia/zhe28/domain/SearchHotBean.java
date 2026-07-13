package com.cy.yyjia.zhe28.domain;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchHotBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0001\u000bB!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SearchHotBean;", "", "keywords", "", "Lcom/cy/yyjia/zhe28/domain/SearchHotBean$Word;", "category", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "(Ljava/util/List;Ljava/util/List;)V", "getCategory", "()Ljava/util/List;", "getKeywords", "Word", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SearchHotBean {
    public static final int $stable = 8;
    private final List<TypeBean> category;
    private final List<Word> keywords;

    public SearchHotBean(List<Word> keywords, List<TypeBean> category) {
        Intrinsics.checkNotNullParameter(keywords, "keywords");
        Intrinsics.checkNotNullParameter(category, "category");
        this.keywords = keywords;
        this.category = category;
    }

    public final List<Word> getKeywords() {
        return this.keywords;
    }

    public final List<TypeBean> getCategory() {
        return this.category;
    }

    /* JADX INFO: compiled from: SearchHotBean.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SearchHotBean$Word;", "", "name", "", "hot", "", "(Ljava/lang/String;I)V", "getHot", "()I", "getName", "()Ljava/lang/String;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Word {
        public static final int $stable = 0;
        private final int hot;
        private final String name;

        public Word(String name, int i) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
            this.hot = i;
        }

        public final String getName() {
            return this.name;
        }

        public final int getHot() {
            return this.hot;
        }
    }
}
