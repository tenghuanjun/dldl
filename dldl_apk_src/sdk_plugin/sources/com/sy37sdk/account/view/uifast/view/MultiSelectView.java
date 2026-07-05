package com.sy37sdk.account.view.uifast.view;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.util.DensityUtil;
import com.sy37sdk.account.AccountLogic;
import com.sy37sdk.account.LoginTractionManager;
import com.sy37sdk.account.view.base.view.BaseSwitchView;
import com.sy37sdk.account.view.uifast.ILoginDialog;
import com.sy37sdk.account.view.uifast.presenter.MultiAccountSelectPresenter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class MultiSelectView extends BaseSwitchView implements IMultiSelectView {
    private LinearLayout mAccountError;
    private LinearLayout mListLayout;
    private final MultiAccountSelectPresenter mPresenter;
    private TextView mTvReInput;
    private final Map<String, String> mUidMap;

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getLayoutResName() {
        return "sysq_dialog_login_view_multi_select";
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public String getTitle() {
        return "多账号选择";
    }

    public MultiSelectView(Context context, ILoginDialog iLoginDialog) {
        super(context);
        this.mUidMap = new HashMap();
        this.loginDialog = iLoginDialog;
        this.mPresenter = new MultiAccountSelectPresenter(context, this);
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initView() {
        this.mListLayout = (LinearLayout) getViewByName("ll_list");
        this.mAccountError = (LinearLayout) getViewByName("ll_account_error");
        this.mTvReInput = (TextView) getViewByName("tv_input");
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView
    public void initEvent() {
        this.mTvReInput.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.-$$Lambda$MultiSelectView$RtXzs8CnZVckq1OCVyYYaHVgfNM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$MultiSelectView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$0$MultiSelectView(View view) {
        trackReInput();
        this.loginDialog.goBack();
    }

    @Override // com.sy37sdk.account.view.base.view.BaseSwitchView, com.sy37sdk.account.view.base.view.BasePageSwitchView, com.sy37sdk.account.view.base.view.IPageSwitchView
    public void onSwitched(int i, int i2, Bundle bundle) {
        super.onSwitched(i, i2, bundle);
        resetUI();
        setAccountList(bundle);
        trackEnterPage();
    }

    public void setAccountList(Bundle bundle) {
        String string = bundle.getString("account_list");
        String string2 = bundle.getString("login_uname");
        String string3 = bundle.getString("login_pwd");
        this.mUidMap.clear();
        try {
            JSONArray jSONArray = new JSONArray(string);
            int i = 0;
            while (i < jSONArray.length()) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                bindData(jSONObjectOptJSONObject, string2, string3, i == 0);
                Map<String, String> map = this.mUidMap;
                StringBuilder sb = new StringBuilder();
                sb.append("uid");
                i++;
                sb.append(i);
                map.put(sb.toString(), jSONObjectOptJSONObject.optString("uid"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void resetUI() {
        this.mListLayout.removeAllViews();
        this.mAccountError.setVisibility(8);
    }

    private void bindData(final JSONObject jSONObject, final String str, final String str2, boolean z) {
        String strOptString = jSONObject.optString("role_name");
        String strOptString2 = jSONObject.optString("login_time");
        String strOptString3 = jSONObject.optString("server_name");
        String strOptString4 = jSONObject.optString("level");
        String strOptString5 = jSONObject.optString("public_game_name");
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(getIdByName("sy37_multi_account_item", "layout"), (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = DensityUtil.dip2px(getContext(), 8.0f);
        TextView textView = (TextView) relativeLayout.findViewById(getIdByName("tv_game_name", SqTrackCommonKey.id));
        TextView textView2 = (TextView) relativeLayout.findViewById(getIdByName("tv_role_level", SqTrackCommonKey.id));
        TextView textView3 = (TextView) relativeLayout.findViewById(getIdByName("tv_role_server", SqTrackCommonKey.id));
        TextView textView4 = (TextView) relativeLayout.findViewById(getIdByName("tv_login_time", SqTrackCommonKey.id));
        final TextView textView5 = (TextView) relativeLayout.findViewById(getIdByName("tv_confirm", SqTrackCommonKey.id));
        ((TextView) relativeLayout.findViewById(getIdByName("tv_new", SqTrackCommonKey.id))).setVisibility(z ? 0 : 8);
        textView.setText(strOptString5);
        textView4.setText(strOptString2);
        textView3.setText(strOptString + "-" + strOptString3);
        if (TextUtils.isEmpty(strOptString4)) {
            textView2.setVisibility(8);
        } else {
            textView2.setText(strOptString4 + "级");
        }
        this.mListLayout.addView(relativeLayout, layoutParams);
        textView5.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.uifast.view.-$$Lambda$MultiSelectView$_ANmTqIuEzsI6aRTFBtrM_i7hdk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindData$1$MultiSelectView(jSONObject, str, str2, textView5, view);
            }
        });
    }

    public /* synthetic */ void lambda$bindData$1$MultiSelectView(JSONObject jSONObject, String str, String str2, final TextView textView, View view) {
        final String strOptString = jSONObject.optString("login_type");
        this.mPresenter.login(strOptString, str, str2, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.view.MultiSelectView.1
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(Map<String, String> map) {
                MultiSelectView.this.hideLoading();
                MultiSelectView.this.trackLoginSucc(strOptString, map);
                MultiSelectView.this.loginDialog.loginSuccess(map);
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str3) {
                MultiSelectView.this.hideLoading();
                textView.setEnabled(false);
                MultiSelectView.this.mAccountError.setVisibility(0);
                MultiSelectView.this.trackLoginFail(strOptString, i, str3);
            }
        });
    }

    private void trackEnterPage() {
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.MULTI_ACCOUNT_SELECT_PAGE, this.mUidMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackLoginSucc(String str, Map<String, String> map) {
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.MULTI_ACCOUNT_LOGIN_SUCC, (Map<String, String>) null);
        if ("account".equals(str)) {
            LoginTractionManager.track("1", map);
        } else {
            LoginTractionManager.track("5", map);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackLoginFail(String str, int i, String str2) {
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.MULTI_ACCOUNT_LOGIN_FAIL, (Map<String, String>) null);
        if ("account".equals(str)) {
            LoginTractionManager.trackFail("1", "1", i + "", str2);
            return;
        }
        LoginTractionManager.trackFail("2", "5", i + "", str2);
    }

    private void trackReInput() {
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.MULTI_ACCOUNT_RE_INPUT, this.mUidMap);
    }
}
