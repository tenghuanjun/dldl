package com.huya.berry.endlive;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.duowan.live.common.framework.fragment.BaseDialogFragment;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.endlive.data.EndLiveData;
import com.huya.berry.endlive.event.EndLiveFragmentListener;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.UIUtil;
import com.huya.berry.gamesdk.widgets.CommonTopBar;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class EndLiveFragment extends BaseDialogFragment {
    private static final String TAG = "EndLiveFragment";
    private EndLiveData mEndLiveData;
    private EndLiveFragmentListener mListener;
    private View mLlLiveData;
    private CommonTopBar mTopBar;
    private TextView mTvBack;
    private TextView mTvExit;
    private TextView mTvGiftCount;
    private TextView mTvLiveDuration;
    private TextView mTvLiveShare;
    private TextView mTvLoading;
    private TextView mTvNewFans;
    private TextView mTvPeakViewer;
    private CommonTopBar.TopBarListener mTopBarListener = new CommonTopBar.TopBarListener() { // from class: com.huya.berry.endlive.EndLiveFragment.1
        @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
        public void onClickAvatar() {
        }

        @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
        public void onClickBack() {
        }

        @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
        public void onClickClose() {
            Report.event(SdkReportConst.CLICK_END_CLOSE);
            EndLiveFragment.this.backToHome();
        }
    };
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.huya.berry.endlive.EndLiveFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!view.equals(EndLiveFragment.this.mTvExit)) {
                if (view.equals(EndLiveFragment.this.mTvBack)) {
                    Report.event(SdkReportConst.CLICK_END_BACK);
                    EndLiveFragment.this.backToHome();
                    return;
                }
                return;
            }
            Report.event(SdkReportConst.CLICK_END_EXIT);
            if (EndLiveFragment.this.mListener != null) {
                EndLiveFragment.this.mListener.onExitClick();
            }
        }
    };

    public static EndLiveFragment getInstance(FragmentManager fragmentManager) {
        EndLiveFragment endLiveFragment = (EndLiveFragment) fragmentManager.findFragmentByTag(TAG);
        return endLiveFragment == null ? new EndLiveFragment() : endLiveFragment;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, ResourceUtil.getStyleResIDByName("hyberry.Widget.LiveList.Dialog"));
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate;
        if (CommonUtil.isScreenLandScape()) {
            viewInflate = layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_fragment_end_live_land), (ViewGroup) null);
        } else {
            viewInflate = layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_fragment_end_live), (ViewGroup) null);
        }
        Report.event(SdkReportConst.PV_END);
        initView(viewInflate);
        return viewInflate;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, TAG);
    }

    public void setEndLiveData(EndLiveData endLiveData) {
        this.mEndLiveData = endLiveData;
        showData();
    }

    public void setListener(EndLiveFragmentListener endLiveFragmentListener) {
        this.mListener = endLiveFragmentListener;
    }

    private void initView(View view) {
        this.mTopBar = (CommonTopBar) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.top_bar));
        this.mTvLoading = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_loading));
        this.mTvLiveDuration = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_live_duration));
        this.mLlLiveData = view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_live_data));
        this.mTvPeakViewer = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_peak_viewer));
        this.mTvGiftCount = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_gift_count));
        this.mTvNewFans = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_new_fans));
        this.mTvLiveShare = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_live_share));
        this.mTvExit = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_exit));
        this.mTvBack = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_back));
        if (!CommonUtil.isScreenLandScape()) {
            ImageView ivLogo = this.mTopBar.getIvLogo();
            ViewGroup.LayoutParams layoutParams = ivLogo.getLayoutParams();
            layoutParams.width = (int) UIUtil.getDp(78.0f);
            ivLogo.setLayoutParams(layoutParams);
            ivLogo.setImageResource(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_huya_logo));
        }
        this.mTopBar.setTopBarListener(this.mTopBarListener);
        this.mTvExit.setOnClickListener(this.mOnClickListener);
        this.mTvBack.setOnClickListener(this.mOnClickListener);
    }

    private void showData() {
        TextView textView = this.mTvLoading;
        if (textView == null) {
            return;
        }
        if (this.mEndLiveData == null) {
            textView.setText("网络错误，数据加载失败，请前往虎牙直播官网查看明细数据");
            return;
        }
        textView.setVisibility(8);
        this.mTvLiveDuration.setVisibility(0);
        this.mLlLiveData.setVisibility(0);
        this.mTvLiveDuration.setText(this.mEndLiveData.liveDurationStr);
        this.mTvPeakViewer.setText(this.mEndLiveData.peakViewerStr);
        this.mTvGiftCount.setText(this.mEndLiveData.giftCountStr);
        this.mTvNewFans.setText(this.mEndLiveData.newFansStr);
        this.mTvLiveShare.setText(this.mEndLiveData.liveShareStr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void backToHome() {
        EndLiveFragmentListener endLiveFragmentListener = this.mListener;
        if (endLiveFragmentListener != null) {
            endLiveFragmentListener.onBackToHome();
        }
    }
}
