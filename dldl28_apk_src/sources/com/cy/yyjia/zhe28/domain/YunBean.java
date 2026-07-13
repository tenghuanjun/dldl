package com.cy.yyjia.zhe28.domain;

import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: YunBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001 B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/YunBean;", "", CommonConstants.key_accountId, "", "cpsId", CommonConstants.key_gameId, "reserverd_id", "siteToken", "token", "Lcom/cy/yyjia/zhe28/domain/YunBean$Token;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/YunBean$Token;)V", "getAccountId", "()Ljava/lang/String;", "getCpsId", "getGameId", "getReserverd_id", "getSiteToken", "getToken", "()Lcom/cy/yyjia/zhe28/domain/YunBean$Token;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "Token", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class YunBean {
    public static final int $stable = 0;
    private final String accountId;
    private final String cpsId;
    private final String gameId;
    private final String reserverd_id;
    private final String siteToken;
    private final Token token;

    public static /* synthetic */ YunBean copy$default(YunBean yunBean, String str, String str2, String str3, String str4, String str5, Token token, int i, Object obj) {
        if ((i & 1) != 0) {
            str = yunBean.accountId;
        }
        if ((i & 2) != 0) {
            str2 = yunBean.cpsId;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = yunBean.gameId;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = yunBean.reserverd_id;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = yunBean.siteToken;
        }
        String str9 = str5;
        if ((i & 32) != 0) {
            token = yunBean.token;
        }
        return yunBean.copy(str, str6, str7, str8, str9, token);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAccountId() {
        return this.accountId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCpsId() {
        return this.cpsId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getReserverd_id() {
        return this.reserverd_id;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getSiteToken() {
        return this.siteToken;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Token getToken() {
        return this.token;
    }

    public final YunBean copy(String accountId, String cpsId, String gameId, String reserverd_id, String siteToken, Token token) {
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(cpsId, "cpsId");
        Intrinsics.checkNotNullParameter(gameId, "gameId");
        Intrinsics.checkNotNullParameter(reserverd_id, "reserverd_id");
        Intrinsics.checkNotNullParameter(siteToken, "siteToken");
        Intrinsics.checkNotNullParameter(token, "token");
        return new YunBean(accountId, cpsId, gameId, reserverd_id, siteToken, token);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof YunBean)) {
            return false;
        }
        YunBean yunBean = (YunBean) other;
        return Intrinsics.areEqual(this.accountId, yunBean.accountId) && Intrinsics.areEqual(this.cpsId, yunBean.cpsId) && Intrinsics.areEqual(this.gameId, yunBean.gameId) && Intrinsics.areEqual(this.reserverd_id, yunBean.reserverd_id) && Intrinsics.areEqual(this.siteToken, yunBean.siteToken) && Intrinsics.areEqual(this.token, yunBean.token);
    }

    public int hashCode() {
        return (((((((((this.accountId.hashCode() * 31) + this.cpsId.hashCode()) * 31) + this.gameId.hashCode()) * 31) + this.reserverd_id.hashCode()) * 31) + this.siteToken.hashCode()) * 31) + this.token.hashCode();
    }

    public String toString() {
        return "YunBean(accountId=" + this.accountId + ", cpsId=" + this.cpsId + ", gameId=" + this.gameId + ", reserverd_id=" + this.reserverd_id + ", siteToken=" + this.siteToken + ", token=" + this.token + ")";
    }

    public YunBean(String accountId, String cpsId, String gameId, String reserverd_id, String siteToken, Token token) {
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(cpsId, "cpsId");
        Intrinsics.checkNotNullParameter(gameId, "gameId");
        Intrinsics.checkNotNullParameter(reserverd_id, "reserverd_id");
        Intrinsics.checkNotNullParameter(siteToken, "siteToken");
        Intrinsics.checkNotNullParameter(token, "token");
        this.accountId = accountId;
        this.cpsId = cpsId;
        this.gameId = gameId;
        this.reserverd_id = reserverd_id;
        this.siteToken = siteToken;
        this.token = token;
    }

    public final String getAccountId() {
        return this.accountId;
    }

    public final String getCpsId() {
        return this.cpsId;
    }

    public final String getGameId() {
        return this.gameId;
    }

    public final String getReserverd_id() {
        return this.reserverd_id;
    }

    public final String getSiteToken() {
        return this.siteToken;
    }

    public final Token getToken() {
        return this.token;
    }

    /* JADX INFO: compiled from: YunBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/YunBean$Token;", "", "ak", "", "create_at", "expire_at", "sk", "token", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAk", "()Ljava/lang/String;", "getCreate_at", "getExpire_at", "getSk", "getToken", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Token {
        public static final int $stable = 0;
        private final String ak;
        private final String create_at;
        private final String expire_at;
        private final String sk;
        private final String token;

        public static /* synthetic */ Token copy$default(Token token, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = token.ak;
            }
            if ((i & 2) != 0) {
                str2 = token.create_at;
            }
            String str6 = str2;
            if ((i & 4) != 0) {
                str3 = token.expire_at;
            }
            String str7 = str3;
            if ((i & 8) != 0) {
                str4 = token.sk;
            }
            String str8 = str4;
            if ((i & 16) != 0) {
                str5 = token.token;
            }
            return token.copy(str, str6, str7, str8, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAk() {
            return this.ak;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCreate_at() {
            return this.create_at;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getExpire_at() {
            return this.expire_at;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSk() {
            return this.sk;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getToken() {
            return this.token;
        }

        public final Token copy(String ak, String create_at, String expire_at, String sk, String token) {
            Intrinsics.checkNotNullParameter(ak, "ak");
            Intrinsics.checkNotNullParameter(create_at, "create_at");
            Intrinsics.checkNotNullParameter(expire_at, "expire_at");
            Intrinsics.checkNotNullParameter(sk, "sk");
            Intrinsics.checkNotNullParameter(token, "token");
            return new Token(ak, create_at, expire_at, sk, token);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Token)) {
                return false;
            }
            Token token = (Token) other;
            return Intrinsics.areEqual(this.ak, token.ak) && Intrinsics.areEqual(this.create_at, token.create_at) && Intrinsics.areEqual(this.expire_at, token.expire_at) && Intrinsics.areEqual(this.sk, token.sk) && Intrinsics.areEqual(this.token, token.token);
        }

        public int hashCode() {
            return (((((((this.ak.hashCode() * 31) + this.create_at.hashCode()) * 31) + this.expire_at.hashCode()) * 31) + this.sk.hashCode()) * 31) + this.token.hashCode();
        }

        public String toString() {
            return "Token(ak=" + this.ak + ", create_at=" + this.create_at + ", expire_at=" + this.expire_at + ", sk=" + this.sk + ", token=" + this.token + ")";
        }

        public Token(String ak, String create_at, String expire_at, String sk, String token) {
            Intrinsics.checkNotNullParameter(ak, "ak");
            Intrinsics.checkNotNullParameter(create_at, "create_at");
            Intrinsics.checkNotNullParameter(expire_at, "expire_at");
            Intrinsics.checkNotNullParameter(sk, "sk");
            Intrinsics.checkNotNullParameter(token, "token");
            this.ak = ak;
            this.create_at = create_at;
            this.expire_at = expire_at;
            this.sk = sk;
            this.token = token;
        }

        public final String getAk() {
            return this.ak;
        }

        public final String getCreate_at() {
            return this.create_at;
        }

        public final String getExpire_at() {
            return this.expire_at;
        }

        public final String getSk() {
            return this.sk;
        }

        public final String getToken() {
            return this.token;
        }
    }
}
