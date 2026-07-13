package com.cy.yyjia.zhe28.domain;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoginAccount.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/LoginAccount;", "", HintConstants.AUTOFILL_HINT_USERNAME, "", HintConstants.AUTOFILL_HINT_PASSWORD, "time", "", "(Ljava/lang/String;Ljava/lang/String;J)V", "getPassword", "()Ljava/lang/String;", "getTime", "()J", "getUsername", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class LoginAccount {
    public static final int $stable = 0;
    private final String password;
    private final long time;
    private final String username;

    public static /* synthetic */ LoginAccount copy$default(LoginAccount loginAccount, String str, String str2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = loginAccount.username;
        }
        if ((i & 2) != 0) {
            str2 = loginAccount.password;
        }
        if ((i & 4) != 0) {
            j = loginAccount.time;
        }
        return loginAccount.copy(str, str2, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getTime() {
        return this.time;
    }

    public final LoginAccount copy(String username, String password, long time) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        return new LoginAccount(username, password, time);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoginAccount)) {
            return false;
        }
        LoginAccount loginAccount = (LoginAccount) other;
        return Intrinsics.areEqual(this.username, loginAccount.username) && Intrinsics.areEqual(this.password, loginAccount.password) && this.time == loginAccount.time;
    }

    public int hashCode() {
        return (((this.username.hashCode() * 31) + this.password.hashCode()) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.time);
    }

    public String toString() {
        return "LoginAccount(username=" + this.username + ", password=" + this.password + ", time=" + this.time + ")";
    }

    public LoginAccount(String username, String password, long j) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        this.username = username;
        this.password = password;
        this.time = j;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getPassword() {
        return this.password;
    }

    public final long getTime() {
        return this.time;
    }
}
