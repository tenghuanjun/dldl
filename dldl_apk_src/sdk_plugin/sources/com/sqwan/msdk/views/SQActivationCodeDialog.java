package com.sqwan.msdk.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.api.MRequestCallBack;
import com.sqwan.msdk.api.MRequestManager;
import com.sqwan.msdk.utils.ViewUtils;
import com.sy37sdk.utils.AppUtils;
import com.sy37sdk.utils.Util;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQActivationCodeDialog extends Dialog {
    private EditText activationCodeEdit;
    private Button checkActivationCodeBtn;
    private ImageView cleanBtn;
    private Context context;
    private Button getActivationCodeBtn;
    private String mBeta;
    private CheckActivationCodeCallback mCallback;
    private String mDesc;
    private CharSequence mTitle;
    private String mUrl;

    public interface CheckActivationCodeCallback {
        void onActiveSucecess();
    }

    public SQActivationCodeDialog(Context context) {
        this(context, Util.getIdByName("Dialog", "style", context.getPackageName(), context));
        this.context = context;
    }

    SQActivationCodeDialog(Context context, int i) {
        super(context, i);
        this.mBeta = "";
        this.mTitle = "";
        this.mDesc = "";
        this.mUrl = "";
        this.context = context;
    }

    SQActivationCodeDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.mBeta = "";
        this.mTitle = "";
        this.mDesc = "";
        this.mUrl = "";
        this.context = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        getWindow().setAttributes(getWindow().getAttributes());
        super.onCreate(bundle);
        View viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(Util.getIdByName("sy37_m_activation_code_dialog", "layout", this.context.getPackageName(), this.context), (ViewGroup) null);
        this.activationCodeEdit = (EditText) viewInflate.findViewById(Util.getIdByName("sy37_m_active_edit_accode", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.cleanBtn = (ImageView) viewInflate.findViewById(Util.getIdByName("sy37_m_active_img_clean", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.getActivationCodeBtn = (Button) viewInflate.findViewById(Util.getIdByName("sy37_m_active_btn_get_code", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.checkActivationCodeBtn = (Button) viewInflate.findViewById(Util.getIdByName("sy37_m_active_btn_confirm", SqTrackCommonKey.id, this.context.getPackageName(), this.context));
        this.cleanBtn.setFocusable(true);
        if (!TextUtils.isEmpty(this.mTitle)) {
            ((TextView) viewInflate.findViewById(Util.getIdByName("sy37_m_active_text_accode_title", SqTrackCommonKey.id, this.context.getPackageName(), this.context))).setText(this.mTitle);
        }
        if (!TextUtils.isEmpty(this.mDesc)) {
            ((TextView) viewInflate.findViewById(Util.getIdByName("sy37_m_active_text_accode_desc", SqTrackCommonKey.id, this.context.getPackageName(), this.context))).setText(this.mDesc);
        }
        this.getActivationCodeBtn.setVisibility(TextUtils.isEmpty(this.mUrl) ? 8 : 0);
        this.activationCodeEdit.addTextChangedListener(new TextWatcher() { // from class: com.sqwan.msdk.views.SQActivationCodeDialog.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                SQActivationCodeDialog.this.cleanBtn.setVisibility(TextUtils.isEmpty(editable.toString()) ? 8 : 0);
            }
        });
        this.cleanBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.views.SQActivationCodeDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SQActivationCodeDialog.this.activationCodeEdit.setText("");
            }
        });
        this.checkActivationCodeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.views.SQActivationCodeDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String string = SQActivationCodeDialog.this.activationCodeEdit.getText().toString();
                if (TextUtils.isEmpty(string)) {
                    ViewUtils.showToast(SQActivationCodeDialog.this.context, "请输入正确的激活码");
                } else {
                    new MRequestManager(SQActivationCodeDialog.this.context).checkActivationCodeRequest(string, new MRequestCallBack() { // from class: com.sqwan.msdk.views.SQActivationCodeDialog.3.1
                        @Override // com.sqwan.msdk.api.MRequestCallBack
                        public void onRequestSuccess(String str) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                int i = jSONObject.getInt("state");
                                String string2 = jSONObject.getString("msg");
                                if (i == 1) {
                                    SQActivationCodeDialog.this.activationCodeEdit.setText("");
                                    SQActivationCodeDialog.this.dismiss();
                                    SQActivationCodeDialog.this.mCallback.onActiveSucecess();
                                } else {
                                    ViewUtils.showToast(SQActivationCodeDialog.this.context, string2);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                ViewUtils.showToast(SQActivationCodeDialog.this.context, "激活码验证失败，请检查激活码是否正确, 0xe");
                            }
                        }

                        @Override // com.sqwan.msdk.api.MRequestCallBack
                        public void onRequestError(String str) {
                            ViewUtils.showToast(SQActivationCodeDialog.this.context, str);
                        }
                    });
                }
            }
        });
        this.getActivationCodeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.msdk.views.SQActivationCodeDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppUtils.toSdkUrl(SQActivationCodeDialog.this.context, SQActivationCodeDialog.this.mUrl);
            }
        });
        setContentView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
    }

    public static SQActivationCodeDialog show(Context context, CharSequence charSequence) {
        return show(context, charSequence, false);
    }

    public static SQActivationCodeDialog show(Context context, int i) {
        return show(context, context.getResources().getString(i), false);
    }

    public static SQActivationCodeDialog show(Context context, CharSequence charSequence, boolean z) {
        SQActivationCodeDialog sQActivationCodeDialog = new SQActivationCodeDialog(context);
        sQActivationCodeDialog.setTitle(charSequence);
        sQActivationCodeDialog.setCancelable(z);
        sQActivationCodeDialog.show();
        return sQActivationCodeDialog;
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
    }

    public void setDesc(String str) {
        this.mDesc = str;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public void setCallback(CheckActivationCodeCallback checkActivationCodeCallback) {
        this.mCallback = checkActivationCodeCallback;
    }

    public void setBetaData(String str) {
        this.mBeta = str;
        LogUtil.w("SQActivationCodeDialog mBeta:" + this.mBeta);
        BetaInfo betaInfo = new BetaInfo();
        JSONObject jSONObject = new JSONObject();
        betaInfo.setDesc(jSONObject.optString("desc"));
        betaInfo.setTitle(jSONObject.optString("title"));
        betaInfo.setUrl(jSONObject.optString("url"));
        this.mTitle = betaInfo.getTitle();
        this.mDesc = betaInfo.getDesc();
        this.mUrl = betaInfo.getUrl();
        LogUtil.w("SQActivationCodeDialog mTitle:" + ((Object) this.mTitle) + " mDesc:" + this.mDesc + " mUrl:" + this.mUrl);
    }

    class BetaInfo {
        private String desc;
        private String title;
        private String url;

        BetaInfo() {
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String str) {
            this.desc = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }
    }
}
