package com.sy37sdk.account.pop;

import android.text.TextUtils;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.SpUtils;
import com.sy37sdk.account.UrlConstant;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountPopupDialogHttpUtil {
    private static final String SQ_PREFS = "sq_prefs";
    public static final String URL_M_POP_LOGIN = "pop_ups_login_api";

    public static void requestLoginPopup(String str, String str2, SqHttpCallback<String> sqHttpCallback) {
        String string = SpUtils.get(SQContextWrapper.getActivity(), SQ_PREFS).getString("pop_ups_login_api");
        if (TextUtils.isEmpty(string)) {
            string = UrlConstant.URL_M_LOGIN_POPUP;
        }
        SqRequest.of(string).signV3().addParamsTransformer(new CommonParamsV3()).addParam("token", str).addParam("action_type", str2).post(sqHttpCallback, String.class);
    }
}
