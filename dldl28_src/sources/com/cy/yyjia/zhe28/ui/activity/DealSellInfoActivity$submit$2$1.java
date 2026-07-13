package com.cy.yyjia.zhe28.ui.activity;

import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.dialog.TipDialog;
import com.cy.yyjia.zhe28.ui.dialog.WaitDialog;
import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;
import com.mobile.auth.gatewayauth.ResultCode;
import com.volcengine.common.contant.CommonConstants;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: DealSellInfoActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tH\u0016J\u0018\u0010\n\u001a\u00020\u00072\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tH\u0016¨\u0006\u000b"}, d2 = {"com/cy/yyjia/zhe28/ui/activity/DealSellInfoActivity$submit$2$1", "Lcom/lzy/okgo/callback/AbsCallback;", "Lcom/cy/yyjia/zhe28/domain/Result;", "convertResponse", "p0", "Lokhttp3/Response;", "onError", "", CommonConstants.KEY_RESPONSE, "Lcom/lzy/okgo/model/Response;", "onSuccess", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DealSellInfoActivity$submit$2$1 extends AbsCallback<Result> {
    final /* synthetic */ WaitDialog $dialog;
    final /* synthetic */ DealSellInfoActivity this$0;

    DealSellInfoActivity$submit$2$1(DealSellInfoActivity dealSellInfoActivity, WaitDialog waitDialog) {
        this.this$0 = dealSellInfoActivity;
        this.$dialog = waitDialog;
    }

    @Override // com.lzy.okgo.convert.Converter
    public Result convertResponse(Response p0) throws IOException {
        String strString;
        if ((p0 != null ? p0.body() : null) == null) {
            strString = "";
        } else {
            ResponseBody responseBodyBody = p0.body();
            Intrinsics.checkNotNull(responseBodyBody);
            strString = responseBodyBody.string();
        }
        if (strString.length() > 0) {
            return (Result) new Gson().fromJson(strString, Result.class);
        }
        return null;
    }

    @Override // com.lzy.okgo.callback.Callback
    public void onSuccess(com.lzy.okgo.model.Response<Result> p0) {
        this.this$0.getMainLooper();
        WaitDialog waitDialog = this.$dialog;
        final DealSellInfoActivity dealSellInfoActivity = this.this$0;
        waitDialog.hide();
        if ((p0 != null ? p0.body() : null) != null) {
            Result resultBody = p0.body();
            Intrinsics.checkNotNull(resultBody);
            String msg = resultBody.getMsg();
            if (Intrinsics.areEqual(msg, ResultCode.MSG_SUCCESS)) {
                new TipDialog(dealSellInfoActivity.getMContext()).setTitle("出售成功").setTip("可在我出售的查看出售进度").setBtnText("完成").addOnDismissListener(new BaseDialog.OnDismissListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity$submit$2$1$$ExternalSyntheticLambda0
                    @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnDismissListener
                    public final void onDismiss(BaseDialog baseDialog) {
                        DealSellInfoActivity$submit$2$1.onSuccess$lambda$1$lambda$0(dealSellInfoActivity, baseDialog);
                    }
                }).show();
                return;
            } else {
                dealSellInfoActivity.toast(msg);
                return;
            }
        }
        dealSellInfoActivity.toast("返回为空");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSuccess$lambda$1$lambda$0(DealSellInfoActivity this$0, BaseDialog baseDialog) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.lzy.okgo.callback.AbsCallback, com.lzy.okgo.callback.Callback
    public void onError(com.lzy.okgo.model.Response<Result> response) {
        Throwable exception;
        super.onError(response);
        this.$dialog.hide();
        String localizedMessage = (response == null || (exception = response.getException()) == null) ? null : exception.getLocalizedMessage();
        if (localizedMessage != null) {
            this.this$0.toast(localizedMessage);
        }
    }
}
