package com.cy.yyjia.zhe28.domain;

import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.tencent.open.SocialConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BossServerBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001+Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\b¢\u0006\u0002\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0\bHÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\r0\bHÆ\u0003J{\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u00032\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\bHÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011¨\u0006,"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BossServerBean;", "", "bg", "", "gamebg", "titlePic", "gameIds", "gameList", "", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "name", "privilege", "privilegeList", "Lcom/cy/yyjia/zhe28/domain/BossServerBean$Privilege;", "news", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getBg", "()Ljava/lang/String;", "getGameIds", "getGameList", "()Ljava/util/List;", "getGamebg", "getName", "getNews", "getPrivilege", "getPrivilegeList", "getTitlePic", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "Privilege", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class BossServerBean {
    public static final int $stable = 8;
    private final String bg;
    private final String gameIds;
    private final List<GameBean> gameList;
    private final String gamebg;
    private final String name;
    private final List<Privilege> news;
    private final List<String> privilege;
    private final List<Privilege> privilegeList;
    private final String titlePic;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getBg() {
        return this.bg;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getGamebg() {
        return this.gamebg;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitlePic() {
        return this.titlePic;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getGameIds() {
        return this.gameIds;
    }

    public final List<GameBean> component5() {
        return this.gameList;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<String> component7() {
        return this.privilege;
    }

    public final List<Privilege> component8() {
        return this.privilegeList;
    }

    public final List<Privilege> component9() {
        return this.news;
    }

    public final BossServerBean copy(String bg, String gamebg, String titlePic, String gameIds, List<GameBean> gameList, String name, List<String> privilege, List<Privilege> privilegeList, List<Privilege> news) {
        Intrinsics.checkNotNullParameter(bg, "bg");
        Intrinsics.checkNotNullParameter(gamebg, "gamebg");
        Intrinsics.checkNotNullParameter(titlePic, "titlePic");
        Intrinsics.checkNotNullParameter(gameIds, "gameIds");
        Intrinsics.checkNotNullParameter(gameList, "gameList");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(privilege, "privilege");
        Intrinsics.checkNotNullParameter(privilegeList, "privilegeList");
        Intrinsics.checkNotNullParameter(news, "news");
        return new BossServerBean(bg, gamebg, titlePic, gameIds, gameList, name, privilege, privilegeList, news);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BossServerBean)) {
            return false;
        }
        BossServerBean bossServerBean = (BossServerBean) other;
        return Intrinsics.areEqual(this.bg, bossServerBean.bg) && Intrinsics.areEqual(this.gamebg, bossServerBean.gamebg) && Intrinsics.areEqual(this.titlePic, bossServerBean.titlePic) && Intrinsics.areEqual(this.gameIds, bossServerBean.gameIds) && Intrinsics.areEqual(this.gameList, bossServerBean.gameList) && Intrinsics.areEqual(this.name, bossServerBean.name) && Intrinsics.areEqual(this.privilege, bossServerBean.privilege) && Intrinsics.areEqual(this.privilegeList, bossServerBean.privilegeList) && Intrinsics.areEqual(this.news, bossServerBean.news);
    }

    public int hashCode() {
        return (((((((((((((((this.bg.hashCode() * 31) + this.gamebg.hashCode()) * 31) + this.titlePic.hashCode()) * 31) + this.gameIds.hashCode()) * 31) + this.gameList.hashCode()) * 31) + this.name.hashCode()) * 31) + this.privilege.hashCode()) * 31) + this.privilegeList.hashCode()) * 31) + this.news.hashCode();
    }

    public String toString() {
        return "BossServerBean(bg=" + this.bg + ", gamebg=" + this.gamebg + ", titlePic=" + this.titlePic + ", gameIds=" + this.gameIds + ", gameList=" + this.gameList + ", name=" + this.name + ", privilege=" + this.privilege + ", privilegeList=" + this.privilegeList + ", news=" + this.news + ")";
    }

    public BossServerBean(String bg, String gamebg, String titlePic, String gameIds, List<GameBean> gameList, String name, List<String> privilege, List<Privilege> privilegeList, List<Privilege> news) {
        Intrinsics.checkNotNullParameter(bg, "bg");
        Intrinsics.checkNotNullParameter(gamebg, "gamebg");
        Intrinsics.checkNotNullParameter(titlePic, "titlePic");
        Intrinsics.checkNotNullParameter(gameIds, "gameIds");
        Intrinsics.checkNotNullParameter(gameList, "gameList");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(privilege, "privilege");
        Intrinsics.checkNotNullParameter(privilegeList, "privilegeList");
        Intrinsics.checkNotNullParameter(news, "news");
        this.bg = bg;
        this.gamebg = gamebg;
        this.titlePic = titlePic;
        this.gameIds = gameIds;
        this.gameList = gameList;
        this.name = name;
        this.privilege = privilege;
        this.privilegeList = privilegeList;
        this.news = news;
    }

    public final String getBg() {
        return this.bg;
    }

    public final String getGamebg() {
        return this.gamebg;
    }

    public final String getTitlePic() {
        return this.titlePic;
    }

    public final String getGameIds() {
        return this.gameIds;
    }

    public final List<GameBean> getGameList() {
        return this.gameList;
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getPrivilege() {
        return this.privilege;
    }

    public final List<Privilege> getPrivilegeList() {
        return this.privilegeList;
    }

    public final List<Privilege> getNews() {
        return this.news;
    }

    /* JADX INFO: compiled from: BossServerBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BossServerBean$Privilege;", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "", SocialConstants.PARAM_APP_DESC, "icon", "id", "", "title", "url", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getDesc", "getIcon", "getId", "()I", "getTitle", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Privilege {
        public static final int $stable = 0;
        private final String content;
        private final String desc;
        private final String icon;
        private final int id;
        private final String title;
        private final String url;

        public static /* synthetic */ Privilege copy$default(Privilege privilege, String str, String str2, String str3, int i, String str4, String str5, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = privilege.content;
            }
            if ((i2 & 2) != 0) {
                str2 = privilege.desc;
            }
            String str6 = str2;
            if ((i2 & 4) != 0) {
                str3 = privilege.icon;
            }
            String str7 = str3;
            if ((i2 & 8) != 0) {
                i = privilege.id;
            }
            int i3 = i;
            if ((i2 & 16) != 0) {
                str4 = privilege.title;
            }
            String str8 = str4;
            if ((i2 & 32) != 0) {
                str5 = privilege.url;
            }
            return privilege.copy(str, str6, str7, i3, str8, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getContent() {
            return this.content;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getIcon() {
            return this.icon;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        public final Privilege copy(String content, String desc, String icon, int id, String title, String url) {
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(url, "url");
            return new Privilege(content, desc, icon, id, title, url);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Privilege)) {
                return false;
            }
            Privilege privilege = (Privilege) other;
            return Intrinsics.areEqual(this.content, privilege.content) && Intrinsics.areEqual(this.desc, privilege.desc) && Intrinsics.areEqual(this.icon, privilege.icon) && this.id == privilege.id && Intrinsics.areEqual(this.title, privilege.title) && Intrinsics.areEqual(this.url, privilege.url);
        }

        public int hashCode() {
            return (((((((((this.content.hashCode() * 31) + this.desc.hashCode()) * 31) + this.icon.hashCode()) * 31) + this.id) * 31) + this.title.hashCode()) * 31) + this.url.hashCode();
        }

        public String toString() {
            return "Privilege(content=" + this.content + ", desc=" + this.desc + ", icon=" + this.icon + ", id=" + this.id + ", title=" + this.title + ", url=" + this.url + ")";
        }

        public Privilege(String content, String desc, String icon, int i, String title, String url) {
            Intrinsics.checkNotNullParameter(content, "content");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(url, "url");
            this.content = content;
            this.desc = desc;
            this.icon = icon;
            this.id = i;
            this.title = title;
            this.url = url;
        }

        public final String getContent() {
            return this.content;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getIcon() {
            return this.icon;
        }

        public final int getId() {
            return this.id;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getUrl() {
            return this.url;
        }
    }
}
