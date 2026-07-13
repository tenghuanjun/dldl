package com.tencent.connect;

import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialOperation;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.g;
import com.tencent.tauth.IUiListener;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
public class UnionInfo extends BaseApi {
    public static final String URL_GET_UNION_ID = "https://openmobile.qq.com/oauth2.0/me";

    public UnionInfo(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public void getUnionId(IUiListener iUiListener) {
        Bundle bundleA = a();
        bundleA.putString(SocialOperation.GAME_UNION_ID, "1");
        HttpUtils.requestAsync(this.c, g.a(), URL_GET_UNION_ID, bundleA, Constants.HTTP_GET, new BaseApi.TempRequestListener(iUiListener));
    }
}
