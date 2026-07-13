package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UpdateBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0011\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001cBU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0002\u0010\u000fR\u0011\u0010\u000e\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/UpdateBean;", "", "baseInfo", "Lcom/cy/yyjia/zhe28/domain/UpdateBean$BaseInfoBean;", "isUpdate", "", "updateUrl", "updateVersion", "updateDesc", "updateForced", "oaidsdkcert", "versionCode", "", "hideTrade", "appTheme", "(Lcom/cy/yyjia/zhe28/domain/UpdateBean$BaseInfoBean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;III)V", "getAppTheme", "()I", "getBaseInfo", "()Lcom/cy/yyjia/zhe28/domain/UpdateBean$BaseInfoBean;", "getHideTrade", "()Ljava/lang/String;", "getOaidsdkcert", "getUpdateDesc", "getUpdateForced", "getUpdateUrl", "getUpdateVersion", "getVersionCode", "BaseInfoBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class UpdateBean {
    public static final int $stable = 0;
    private final int appTheme;
    private final BaseInfoBean baseInfo;
    private final int hideTrade;
    private final String isUpdate;
    private final String oaidsdkcert;
    private final String updateDesc;
    private final String updateForced;
    private final String updateUrl;
    private final String updateVersion;
    private final int versionCode;

    public UpdateBean(BaseInfoBean baseInfo, String isUpdate, String updateUrl, String updateVersion, String updateDesc, String updateForced, String oaidsdkcert, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(baseInfo, "baseInfo");
        Intrinsics.checkNotNullParameter(isUpdate, "isUpdate");
        Intrinsics.checkNotNullParameter(updateUrl, "updateUrl");
        Intrinsics.checkNotNullParameter(updateVersion, "updateVersion");
        Intrinsics.checkNotNullParameter(updateDesc, "updateDesc");
        Intrinsics.checkNotNullParameter(updateForced, "updateForced");
        Intrinsics.checkNotNullParameter(oaidsdkcert, "oaidsdkcert");
        this.baseInfo = baseInfo;
        this.isUpdate = isUpdate;
        this.updateUrl = updateUrl;
        this.updateVersion = updateVersion;
        this.updateDesc = updateDesc;
        this.updateForced = updateForced;
        this.oaidsdkcert = oaidsdkcert;
        this.versionCode = i;
        this.hideTrade = i2;
        this.appTheme = i3;
    }

    public final BaseInfoBean getBaseInfo() {
        return this.baseInfo;
    }

    /* JADX INFO: renamed from: isUpdate, reason: from getter */
    public final String getIsUpdate() {
        return this.isUpdate;
    }

    public final String getUpdateUrl() {
        return this.updateUrl;
    }

    public final String getUpdateVersion() {
        return this.updateVersion;
    }

    public final String getUpdateDesc() {
        return this.updateDesc;
    }

    public final String getUpdateForced() {
        return this.updateForced;
    }

    public final String getOaidsdkcert() {
        return this.oaidsdkcert;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    public final int getHideTrade() {
        return this.hideTrade;
    }

    public final int getAppTheme() {
        return this.appTheme;
    }

    /* JADX INFO: compiled from: UpdateBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/UpdateBean$BaseInfoBean;", "", "pic", "", "dumpType", "dumpGameId", "", "dumpUrl", "Lcom/cy/yyjia/zhe28/domain/BtnBean;", "(Ljava/lang/String;Ljava/lang/String;ILcom/cy/yyjia/zhe28/domain/BtnBean;)V", "getDumpGameId", "()I", "getDumpType", "()Ljava/lang/String;", "getDumpUrl", "()Lcom/cy/yyjia/zhe28/domain/BtnBean;", "getPic", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class BaseInfoBean {
        public static final int $stable = 0;
        private final int dumpGameId;
        private final String dumpType;
        private final BtnBean dumpUrl;
        private final String pic;

        public BaseInfoBean(String pic, String dumpType, int i, BtnBean dumpUrl) {
            Intrinsics.checkNotNullParameter(pic, "pic");
            Intrinsics.checkNotNullParameter(dumpType, "dumpType");
            Intrinsics.checkNotNullParameter(dumpUrl, "dumpUrl");
            this.pic = pic;
            this.dumpType = dumpType;
            this.dumpGameId = i;
            this.dumpUrl = dumpUrl;
        }

        public final String getPic() {
            return this.pic;
        }

        public final String getDumpType() {
            return this.dumpType;
        }

        public final int getDumpGameId() {
            return this.dumpGameId;
        }

        public final BtnBean getDumpUrl() {
            return this.dumpUrl;
        }
    }
}
