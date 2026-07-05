package com.huya.berry.modifytitle;

import android.app.FragmentManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.live.common.framework.fragment.BaseDialogFragment;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.PreferenceUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.modifytitle.event.InputTitleCallback;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class InputTitleFragment extends BaseDialogFragment {
    public static final String DEFAULT_TITLE = "欢迎来到我的直播间";
    public static final String TAG = "InputTitleFragment";
    private EditText mEtLiveTitle;
    private ImageView mIvDelete;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.huya.berry.modifytitle.InputTitleFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.equals(InputTitleFragment.this.mIvDelete)) {
                InputTitleFragment.this.mEtLiveTitle.getText().clear();
            } else if (view.equals(InputTitleFragment.this.mTvStartLive)) {
                Report.event(SdkReportConst.CLICK_HOME_TITLEINPUT_BEGINLIVE);
                InputTitleFragment.this.startLive();
            }
        }
    };
    private TextView mTvStartLive;

    public static InputTitleFragment getInstance(FragmentManager fragmentManager) {
        InputTitleFragment inputTitleFragment = (InputTitleFragment) fragmentManager.findFragmentByTag(TAG);
        return inputTitleFragment == null ? new InputTitleFragment() : inputTitleFragment;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, ResourceUtil.getStyleResIDByName("hyberry.Widget.InputTitle.Dialog"));
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_input_live_title_dialog), (ViewGroup) null);
        initView(viewInflate);
        return viewInflate;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
        Report.event(SdkReportConst.PV_HOME_TITLEINPUT);
    }

    private void initView(View view) {
        this.mEtLiveTitle = (EditText) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.edit_live_title));
        this.mIvDelete = (ImageView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.iv_delete));
        this.mTvStartLive = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_start_live));
        this.mIvDelete.setOnClickListener(this.mOnClickListener);
        this.mTvStartLive.setOnClickListener(this.mOnClickListener);
        this.mEtLiveTitle.setText(DEFAULT_TITLE);
        this.mEtLiveTitle.setSelection(9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLive() {
        if (TextUtils.isEmpty(this.mEtLiveTitle.getText().toString().trim())) {
            ArkToast.show("直播标题不能为空");
            return;
        }
        String strTrim = this.mEtLiveTitle.getText().toString().trim();
        if (DEFAULT_TITLE.equals(strTrim)) {
            Report.event(SdkReportConst.CLICK_HOME_TITLEINPUT_BEGINLIVE_DEFAULTTITLE);
        }
        SdkProperties.liveTitle.set(strTrim);
        PreferenceUtil.setLiveTitle(strTrim);
        ArkUtils.send(new InputTitleCallback.GetLiveTitle(strTrim));
    }
}
