package com.cy.yyjia.zhe28.db;

import androidx.autofill.HintConstants;
import com.cy.yyjia.zhe28.domain.LoginAccount;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: LoginAccountDao.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H'¢\u0006\u0002\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\nH'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0006H'J\b\u0010\r\u001a\u00020\u0003H'J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH'J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0006H'J!\u0010\u0011\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H'¢\u0006\u0002\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/db/LoginAccountDao;", "", "add", "", "data", "", "Lcom/cy/yyjia/zhe28/domain/LoginAccount;", "([Lcom/cy/yyjia/zhe28/domain/LoginAccount;)V", "check", HintConstants.AUTOFILL_HINT_USERNAME, "", "delete", "account", "deleteAll", "getAll", "", "getRecent", "update", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface LoginAccountDao {
    void add(LoginAccount... data);

    LoginAccount check(String username);

    void delete(LoginAccount account);

    void deleteAll();

    List<LoginAccount> getAll();

    LoginAccount getRecent();

    void update(LoginAccount... data);
}
