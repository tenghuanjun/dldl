package com.cy.yyjia.zhe28.domain;

import com.bytedance.framwork.core.sdklib.DBHelper;
import com.lzy.okgo.cookie.SerializableCookie;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoginResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\nHÆ\u0003J7\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\nHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/LoginResult;", "", "token", "", "user", "Lcom/cy/yyjia/zhe28/domain/UserBean;", SerializableCookie.COOKIE, "", "Lcom/cy/yyjia/zhe28/domain/LoginResult$CookieBean;", "newReg", "", "(Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/UserBean;Ljava/util/List;I)V", "getCookie", "()Ljava/util/List;", "getNewReg", "()I", "getToken", "()Ljava/lang/String;", "getUser", "()Lcom/cy/yyjia/zhe28/domain/UserBean;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "CookieBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class LoginResult {
    public static final int $stable = 8;
    private final List<CookieBean> cookie;
    private final int newReg;
    private final String token;
    private final UserBean user;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LoginResult copy$default(LoginResult loginResult, String str, UserBean userBean, List list, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = loginResult.token;
        }
        if ((i2 & 2) != 0) {
            userBean = loginResult.user;
        }
        if ((i2 & 4) != 0) {
            list = loginResult.cookie;
        }
        if ((i2 & 8) != 0) {
            i = loginResult.newReg;
        }
        return loginResult.copy(str, userBean, list, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getToken() {
        return this.token;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final UserBean getUser() {
        return this.user;
    }

    public final List<CookieBean> component3() {
        return this.cookie;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getNewReg() {
        return this.newReg;
    }

    public final LoginResult copy(String token, UserBean user, List<CookieBean> cookie, int newReg) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        return new LoginResult(token, user, cookie, newReg);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoginResult)) {
            return false;
        }
        LoginResult loginResult = (LoginResult) other;
        return Intrinsics.areEqual(this.token, loginResult.token) && Intrinsics.areEqual(this.user, loginResult.user) && Intrinsics.areEqual(this.cookie, loginResult.cookie) && this.newReg == loginResult.newReg;
    }

    public int hashCode() {
        return (((((this.token.hashCode() * 31) + this.user.hashCode()) * 31) + this.cookie.hashCode()) * 31) + this.newReg;
    }

    public String toString() {
        return "LoginResult(token=" + this.token + ", user=" + this.user + ", cookie=" + this.cookie + ", newReg=" + this.newReg + ")";
    }

    public LoginResult(String token, UserBean user, List<CookieBean> cookie, int i) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        this.token = token;
        this.user = user;
        this.cookie = cookie;
        this.newReg = i;
    }

    public final String getToken() {
        return this.token;
    }

    public final UserBean getUser() {
        return this.user;
    }

    public final List<CookieBean> getCookie() {
        return this.cookie;
    }

    public final int getNewReg() {
        return this.newReg;
    }

    /* JADX INFO: compiled from: LoginResult.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/LoginResult$CookieBean;", "", "name", "", DBHelper.COL_VALUE, "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getValue", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CookieBean {
        public static final int $stable = 0;
        private final String name;
        private final String value;

        public CookieBean(String name, String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            this.name = name;
            this.value = value;
        }

        public final String getName() {
            return this.name;
        }

        public final String getValue() {
            return this.value;
        }

        public String toString() {
            return "{\"name\":\"" + this.name + "\",\"value\":\"" + this.value + "\"}";
        }
    }
}
