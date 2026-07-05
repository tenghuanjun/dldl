package com.sq.diagnostic.assistant.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.sq.diagnostic.assistant.DiagnosticAssistant;
import com.sq.diagnostic.assistant.http.HttpData;
import com.sq.diagnostic.assistant.http.HttpManager;
import com.sq.diagnostic.assistant.http.OnHttpListener;
import com.sq.diagnostic.assistant.log.utils.DiagnosticAssistantSpUtils;
import com.sq.diagnostic.assistant.other.HttpNetworkConfigManager;
import com.sq.diagnostic.assistant.ui.AssistantEntranceDialog;
import com.sq.diagnostic.assistant.ui.BaseDialog;
import com.sq.diagnostic.assistant.ui.NetworkTestDialog;
import com.sq.diagnostic.assistant.ui.UpdateLogDialog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class AssistantEntranceDialog {

    public static final class Builder extends BaseDialog.Builder<Builder> {
        private final View goBackView;
        private final View helpDocView;
        private final View networkTestView;
        private final View updateLogView;

        public Builder(Context context) {
            super(context);
            setWidth(-1);
            setHeight(-1);
            setContentView("sdk_assistant_entrance_dialog");
            this.goBackView = findViewById("iv_assistant_entrance_go_back");
            this.helpDocView = findViewById("tv_assistant_entrance_help_doc");
            this.updateLogView = findViewById("fl_assistant_entrance_update_log");
            this.networkTestView = findViewById("fl_assistant_entrance_network_test");
            this.goBackView.setOnClickListener(this);
            this.helpDocView.setOnClickListener(this);
            this.updateLogView.setOnClickListener(this);
            this.networkTestView.setOnClickListener(this);
            addOnShowListener(new BaseDialog.OnShowListener() { // from class: com.sq.diagnostic.assistant.ui.-$$Lambda$AssistantEntranceDialog$Builder$AJ9e2bjD9YfwE2ctLUQIHGjv-pk
                @Override // com.sq.diagnostic.assistant.ui.BaseDialog.OnShowListener
                public final void onShow(BaseDialog baseDialog) {
                    AssistantEntranceDialog.Builder.lambda$new$0(baseDialog);
                }
            });
            addOnDismissListener(new BaseDialog.OnDismissListener() { // from class: com.sq.diagnostic.assistant.ui.-$$Lambda$AssistantEntranceDialog$Builder$g2g4iFz9h_c-wNi44TCvM7V_c10
                @Override // com.sq.diagnostic.assistant.ui.BaseDialog.OnDismissListener
                public final void onDismiss(BaseDialog baseDialog) {
                    AssistantEntranceDialog.Builder.lambda$new$1(baseDialog);
                }
            });
            HttpNetworkConfigManager.init(context);
            HttpManager.requestHelpDocConfig(new AnonymousClass1(context));
            this.helpDocView.setVisibility(TextUtils.isEmpty(DiagnosticAssistantSpUtils.getString(context, DiagnosticAssistantSpUtils.SP_CACHE_HELP_DOC_URL, "")) ? 8 : 0);
        }

        static /* synthetic */ void lambda$new$0(BaseDialog baseDialog) {
            DiagnosticAssistant.CallBack callBack = DiagnosticAssistant.getInstance().getCallBack();
            if (callBack == null) {
                return;
            }
            callBack.onHomePageShow();
        }

        static /* synthetic */ void lambda$new$1(BaseDialog baseDialog) {
            DiagnosticAssistant.CallBack callBack = DiagnosticAssistant.getInstance().getCallBack();
            if (callBack == null) {
                return;
            }
            callBack.onHomePageClose();
        }

        /* JADX INFO: renamed from: com.sq.diagnostic.assistant.ui.AssistantEntranceDialog$Builder$1, reason: invalid class name */
        class AnonymousClass1 implements OnHttpListener<JSONArray> {
            final /* synthetic */ Context val$context;

            @Override // com.sq.diagnostic.assistant.http.OnHttpListener
            public void onFailed(int i, String str) {
            }

            AnonymousClass1(Context context) {
                this.val$context = context;
            }

            @Override // com.sq.diagnostic.assistant.http.OnHttpListener
            public void onSuccess(HttpData<JSONArray> httpData) {
                JSONObject jSONObjectOptJSONObject;
                JSONArray data = httpData.getData();
                if (data == null || data.length() == 0 || (jSONObjectOptJSONObject = data.optJSONObject(0)) == null) {
                    return;
                }
                String strOptString = jSONObjectOptJSONObject.optString("content");
                if (TextUtils.isEmpty(strOptString)) {
                    return;
                }
                try {
                    final String strOptString2 = new JSONObject(strOptString).optString("page_url");
                    DiagnosticAssistantSpUtils.putString(this.val$context, DiagnosticAssistantSpUtils.SP_CACHE_HELP_DOC_URL, strOptString2);
                    Builder.this.post(new Runnable() { // from class: com.sq.diagnostic.assistant.ui.-$$Lambda$AssistantEntranceDialog$Builder$1$Z-UAuxxIx7ECvjUf9WwWb861ZmY
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onSuccess$0$AssistantEntranceDialog$Builder$1(strOptString2);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public /* synthetic */ void lambda$onSuccess$0$AssistantEntranceDialog$Builder$1(String str) {
                Builder.this.helpDocView.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
            }
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.Builder, com.sq.diagnostic.assistant.ui.action.ClickAction, android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == this.goBackView) {
                dismiss();
                return;
            }
            if (view == this.helpDocView) {
                String string = DiagnosticAssistantSpUtils.getString(getContext(), DiagnosticAssistantSpUtils.SP_CACHE_HELP_DOC_URL, "");
                if (TextUtils.isEmpty(string)) {
                    Toast.makeText(getContext(), getString("sdk_assistant_entrance_dialog_help_doc_open_fail"), 0).show();
                    return;
                }
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setFlags(268435456);
                intent.setData(Uri.parse(string));
                getContext().startActivity(intent);
                return;
            }
            if (view == this.updateLogView) {
                new UpdateLogDialog.Builder(getContext()).show();
            } else if (view == this.networkTestView) {
                new NetworkTestDialog.Builder(getContext()).show();
            }
        }
    }
}
