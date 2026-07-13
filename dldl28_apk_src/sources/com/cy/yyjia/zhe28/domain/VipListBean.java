package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VipListBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/VipListBean;", "", "list", "", "Lcom/cy/yyjia/zhe28/domain/VipListBean$ListBean;", "user", "Lcom/cy/yyjia/zhe28/domain/UserBean;", "configUrl", "", "label", "(Ljava/util/List;Lcom/cy/yyjia/zhe28/domain/UserBean;Ljava/lang/String;Ljava/lang/String;)V", "getConfigUrl", "()Ljava/lang/String;", "getLabel", "getList", "()Ljava/util/List;", "getUser", "()Lcom/cy/yyjia/zhe28/domain/UserBean;", "ListBean", "RightBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VipListBean {
    public static final int $stable = 8;
    private final String configUrl;
    private final String label;
    private final List<ListBean> list;
    private final UserBean user;

    public VipListBean(List<ListBean> list, UserBean user, String configUrl, String label) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(configUrl, "configUrl");
        Intrinsics.checkNotNullParameter(label, "label");
        this.list = list;
        this.user = user;
        this.configUrl = configUrl;
        this.label = label;
    }

    public final List<ListBean> getList() {
        return this.list;
    }

    public final UserBean getUser() {
        return this.user;
    }

    public final String getConfigUrl() {
        return this.configUrl;
    }

    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: compiled from: VipListBean.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003JU\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\tHÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/VipListBean$ListBean;", "", "id", "", "name", "pic", "bg", SocialConstants.PARAM_APP_DESC, "growth_alue", "", "privilege_list", "", "Lcom/cy/yyjia/zhe28/domain/VipListBean$RightBean;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;)V", "getBg", "()Ljava/lang/String;", "getDesc", "getGrowth_alue", "()I", "getId", "getName", "getPic", "getPrivilege_list", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ListBean {
        public static final int $stable = 8;
        private final String bg;
        private final String desc;
        private final int growth_alue;
        private final String id;
        private final String name;
        private final String pic;
        private final List<RightBean> privilege_list;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ListBean copy$default(ListBean listBean, String str, String str2, String str3, String str4, String str5, int i, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = listBean.id;
            }
            if ((i2 & 2) != 0) {
                str2 = listBean.name;
            }
            String str6 = str2;
            if ((i2 & 4) != 0) {
                str3 = listBean.pic;
            }
            String str7 = str3;
            if ((i2 & 8) != 0) {
                str4 = listBean.bg;
            }
            String str8 = str4;
            if ((i2 & 16) != 0) {
                str5 = listBean.desc;
            }
            String str9 = str5;
            if ((i2 & 32) != 0) {
                i = listBean.growth_alue;
            }
            int i3 = i;
            if ((i2 & 64) != 0) {
                list = listBean.privilege_list;
            }
            return listBean.copy(str, str6, str7, str8, str9, i3, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPic() {
            return this.pic;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getBg() {
            return this.bg;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getGrowth_alue() {
            return this.growth_alue;
        }

        public final List<RightBean> component7() {
            return this.privilege_list;
        }

        public final ListBean copy(String id, String name, String pic, String bg, String desc, int growth_alue, List<RightBean> privilege_list) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(pic, "pic");
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(privilege_list, "privilege_list");
            return new ListBean(id, name, pic, bg, desc, growth_alue, privilege_list);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ListBean)) {
                return false;
            }
            ListBean listBean = (ListBean) other;
            return Intrinsics.areEqual(this.id, listBean.id) && Intrinsics.areEqual(this.name, listBean.name) && Intrinsics.areEqual(this.pic, listBean.pic) && Intrinsics.areEqual(this.bg, listBean.bg) && Intrinsics.areEqual(this.desc, listBean.desc) && this.growth_alue == listBean.growth_alue && Intrinsics.areEqual(this.privilege_list, listBean.privilege_list);
        }

        public int hashCode() {
            return (((((((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.pic.hashCode()) * 31) + this.bg.hashCode()) * 31) + this.desc.hashCode()) * 31) + this.growth_alue) * 31) + this.privilege_list.hashCode();
        }

        public String toString() {
            return "ListBean(id=" + this.id + ", name=" + this.name + ", pic=" + this.pic + ", bg=" + this.bg + ", desc=" + this.desc + ", growth_alue=" + this.growth_alue + ", privilege_list=" + this.privilege_list + ")";
        }

        public ListBean(String id, String name, String pic, String bg, String desc, int i, List<RightBean> privilege_list) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(pic, "pic");
            Intrinsics.checkNotNullParameter(bg, "bg");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(privilege_list, "privilege_list");
            this.id = id;
            this.name = name;
            this.pic = pic;
            this.bg = bg;
            this.desc = desc;
            this.growth_alue = i;
            this.privilege_list = privilege_list;
        }

        public final String getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPic() {
            return this.pic;
        }

        public final String getBg() {
            return this.bg;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final int getGrowth_alue() {
            return this.growth_alue;
        }

        public final List<RightBean> getPrivilege_list() {
            return this.privilege_list;
        }
    }

    /* JADX INFO: compiled from: VipListBean.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\fHÆ\u0003J[\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0003HÖ\u0001J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011¨\u0006'"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/VipListBean$RightBean;", "", "id", "", "name", "", "pic", SocialConstants.PARAM_APP_DESC, "kf_pic", "unlock", "user_unlock", "link", "Lcom/cy/yyjia/zhe28/domain/BtnBean;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILcom/cy/yyjia/zhe28/domain/BtnBean;)V", "getDesc", "()Ljava/lang/String;", "getId", "()I", "getKf_pic", "getLink", "()Lcom/cy/yyjia/zhe28/domain/BtnBean;", "getName", "getPic", "getUnlock", "getUser_unlock", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class RightBean {
        public static final int $stable = 0;
        private final String desc;
        private final int id;
        private final String kf_pic;
        private final BtnBean link;
        private final String name;
        private final String pic;
        private final int unlock;
        private final int user_unlock;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPic() {
            return this.pic;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getKf_pic() {
            return this.kf_pic;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getUnlock() {
            return this.unlock;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getUser_unlock() {
            return this.user_unlock;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final BtnBean getLink() {
            return this.link;
        }

        public final RightBean copy(int id, String name, String pic, String desc, String kf_pic, int unlock, int user_unlock, BtnBean link) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(pic, "pic");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(link, "link");
            return new RightBean(id, name, pic, desc, kf_pic, unlock, user_unlock, link);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RightBean)) {
                return false;
            }
            RightBean rightBean = (RightBean) other;
            return this.id == rightBean.id && Intrinsics.areEqual(this.name, rightBean.name) && Intrinsics.areEqual(this.pic, rightBean.pic) && Intrinsics.areEqual(this.desc, rightBean.desc) && Intrinsics.areEqual(this.kf_pic, rightBean.kf_pic) && this.unlock == rightBean.unlock && this.user_unlock == rightBean.user_unlock && Intrinsics.areEqual(this.link, rightBean.link);
        }

        public int hashCode() {
            int iHashCode = ((((((this.id * 31) + this.name.hashCode()) * 31) + this.pic.hashCode()) * 31) + this.desc.hashCode()) * 31;
            String str = this.kf_pic;
            return ((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.unlock) * 31) + this.user_unlock) * 31) + this.link.hashCode();
        }

        public String toString() {
            return "RightBean(id=" + this.id + ", name=" + this.name + ", pic=" + this.pic + ", desc=" + this.desc + ", kf_pic=" + this.kf_pic + ", unlock=" + this.unlock + ", user_unlock=" + this.user_unlock + ", link=" + this.link + ")";
        }

        public RightBean(int i, String name, String pic, String desc, String str, int i2, int i3, BtnBean link) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(pic, "pic");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(link, "link");
            this.id = i;
            this.name = name;
            this.pic = pic;
            this.desc = desc;
            this.kf_pic = str;
            this.unlock = i2;
            this.user_unlock = i3;
            this.link = link;
        }

        public final int getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPic() {
            return this.pic;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getKf_pic() {
            return this.kf_pic;
        }

        public final int getUnlock() {
            return this.unlock;
        }

        public final int getUser_unlock() {
            return this.user_unlock;
        }

        public final BtnBean getLink() {
            return this.link;
        }
    }
}
