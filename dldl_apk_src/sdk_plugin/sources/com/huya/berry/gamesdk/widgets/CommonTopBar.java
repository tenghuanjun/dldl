package com.huya.berry.gamesdk.widgets;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.duowan.auk.ArkValue;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.resolutions.LivingParams;
import com.huya.berry.gamesdk.resolutions.Resolution;
import com.huya.berry.gamesdk.resolutions.ResolutionOptions;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.UIUtil;
import com.huya.live.utils.image.ImageBind;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.liveshow.huya.SqR;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CommonTopBar extends FrameLayout {
    private Runnable delayTask;
    private Context mContext;
    private PopupWindow.OnDismissListener mDismissListener;
    private ImageView mIvAvatar;
    private ImageView mIvBack;
    private ImageView mIvClose;
    private ImageView mIvDown;
    private ImageView mIvKaihei;
    private ImageView mIvLogo;
    private ImageView mIvUp;
    private String mKaiHeiTip;
    private LinearLayout mLlAvatar;
    private LinearLayout mLlKaihei;
    private LinearLayout mLlResolution;
    private LinearLayout mLlSwitchResolution;
    private LinearLayout mLlTitle;
    private View.OnClickListener mOnClickListener;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private ResolutionListAdapter mResolutionListAdapter;
    private PopupWindow mSwitchResolutionWindow;
    private boolean mToastIsShow;
    private PopupWindow mToastWindow;
    private TopBarListener mTopBarListener;
    private TextView mTvNickname;
    private TextView mTvTitle;

    public interface TopBarListener {
        void onClickAvatar();

        void onClickBack();

        void onClickClose();
    }

    public CommonTopBar(Context context) {
        super(context);
        this.mKaiHeiTip = "";
        this.mToastIsShow = false;
        this.mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Resolution item = CommonTopBar.this.mResolutionListAdapter.getItem(i);
                CommonTopBar.this.mResolutionListAdapter.setSelectedIndex(i);
                if (CommonTopBar.this.mLlSwitchResolution.getChildAt(0) != null && (CommonTopBar.this.mLlSwitchResolution.getChildAt(0) instanceof TextView)) {
                    ((TextView) CommonTopBar.this.mLlSwitchResolution.getChildAt(0)).setText(item.name);
                }
                CommonTopBar.this.mIvDown.setVisibility(0);
                CommonTopBar.this.mIvUp.setVisibility(8);
                SdkProperties.resolution.set(Integer.valueOf(item.resolution));
                if (CommonTopBar.this.mSwitchResolutionWindow != null) {
                    CommonTopBar.this.mSwitchResolutionWindow.dismiss();
                }
                if (item.videoBitrate <= 800) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_800);
                    return;
                }
                if (item.videoBitrate <= 1200) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_1200);
                    return;
                }
                if (item.videoBitrate <= 2000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_2000);
                    return;
                }
                if (item.videoBitrate <= 3000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_3000);
                } else if (item.videoBitrate <= 4000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_4000);
                } else if (item.videoBitrate > 4000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_ABOVE4K);
                }
            }
        };
        this.mOnClickListener = new View.OnClickListener() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.equals(CommonTopBar.this.mIvBack)) {
                    if (CommonTopBar.this.mTopBarListener == null) {
                        return;
                    }
                    CommonTopBar.this.mTopBarListener.onClickBack();
                    return;
                }
                if (view.equals(CommonTopBar.this.mLlAvatar)) {
                    if (CommonTopBar.this.mTopBarListener == null) {
                        return;
                    }
                    CommonTopBar.this.mTopBarListener.onClickAvatar();
                    return;
                }
                if (view.equals(CommonTopBar.this.mIvClose)) {
                    if (CommonTopBar.this.mTopBarListener == null) {
                        return;
                    }
                    CommonTopBar.this.mTopBarListener.onClickClose();
                    return;
                }
                if (!view.equals(CommonTopBar.this.mLlSwitchResolution)) {
                    if (view.equals(CommonTopBar.this.mIvKaihei)) {
                        CommonTopBar.this.mToastIsShow = !r2.mToastIsShow;
                        CommonTopBar commonTopBar = CommonTopBar.this;
                        commonTopBar.showToastWindow(commonTopBar.mToastIsShow);
                        if (CommonTopBar.this.mLlKaihei.isSelected()) {
                            Report.event(SdkReportConst.GANGUP_CLICK_ZUDUI_ON);
                            return;
                        } else {
                            Report.event(SdkReportConst.GANGUP_CLICK_ZUDUI_OFF);
                            return;
                        }
                    }
                    return;
                }
                Report.event(SdkReportConst.CLICK_HOME_QUALITY);
                CommonTopBar.this.showSwitchWindow();
                CommonTopBar.this.mIvDown.setVisibility(8);
                CommonTopBar.this.mIvUp.setVisibility(0);
            }
        };
        this.delayTask = new Runnable() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.3
            @Override // java.lang.Runnable
            public void run() {
                CommonTopBar.this.showToastWindow(false);
                CommonTopBar.this.mToastIsShow = false;
            }
        };
        this.mDismissListener = new PopupWindow.OnDismissListener() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.4
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                CommonTopBar.this.mIvDown.setVisibility(0);
                CommonTopBar.this.mIvUp.setVisibility(8);
            }
        };
        init(context, null);
    }

    public CommonTopBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mKaiHeiTip = "";
        this.mToastIsShow = false;
        this.mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Resolution item = CommonTopBar.this.mResolutionListAdapter.getItem(i);
                CommonTopBar.this.mResolutionListAdapter.setSelectedIndex(i);
                if (CommonTopBar.this.mLlSwitchResolution.getChildAt(0) != null && (CommonTopBar.this.mLlSwitchResolution.getChildAt(0) instanceof TextView)) {
                    ((TextView) CommonTopBar.this.mLlSwitchResolution.getChildAt(0)).setText(item.name);
                }
                CommonTopBar.this.mIvDown.setVisibility(0);
                CommonTopBar.this.mIvUp.setVisibility(8);
                SdkProperties.resolution.set(Integer.valueOf(item.resolution));
                if (CommonTopBar.this.mSwitchResolutionWindow != null) {
                    CommonTopBar.this.mSwitchResolutionWindow.dismiss();
                }
                if (item.videoBitrate <= 800) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_800);
                    return;
                }
                if (item.videoBitrate <= 1200) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_1200);
                    return;
                }
                if (item.videoBitrate <= 2000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_2000);
                    return;
                }
                if (item.videoBitrate <= 3000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_3000);
                } else if (item.videoBitrate <= 4000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_4000);
                } else if (item.videoBitrate > 4000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_ABOVE4K);
                }
            }
        };
        this.mOnClickListener = new View.OnClickListener() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.equals(CommonTopBar.this.mIvBack)) {
                    if (CommonTopBar.this.mTopBarListener == null) {
                        return;
                    }
                    CommonTopBar.this.mTopBarListener.onClickBack();
                    return;
                }
                if (view.equals(CommonTopBar.this.mLlAvatar)) {
                    if (CommonTopBar.this.mTopBarListener == null) {
                        return;
                    }
                    CommonTopBar.this.mTopBarListener.onClickAvatar();
                    return;
                }
                if (view.equals(CommonTopBar.this.mIvClose)) {
                    if (CommonTopBar.this.mTopBarListener == null) {
                        return;
                    }
                    CommonTopBar.this.mTopBarListener.onClickClose();
                    return;
                }
                if (!view.equals(CommonTopBar.this.mLlSwitchResolution)) {
                    if (view.equals(CommonTopBar.this.mIvKaihei)) {
                        CommonTopBar.this.mToastIsShow = !r2.mToastIsShow;
                        CommonTopBar commonTopBar = CommonTopBar.this;
                        commonTopBar.showToastWindow(commonTopBar.mToastIsShow);
                        if (CommonTopBar.this.mLlKaihei.isSelected()) {
                            Report.event(SdkReportConst.GANGUP_CLICK_ZUDUI_ON);
                            return;
                        } else {
                            Report.event(SdkReportConst.GANGUP_CLICK_ZUDUI_OFF);
                            return;
                        }
                    }
                    return;
                }
                Report.event(SdkReportConst.CLICK_HOME_QUALITY);
                CommonTopBar.this.showSwitchWindow();
                CommonTopBar.this.mIvDown.setVisibility(8);
                CommonTopBar.this.mIvUp.setVisibility(0);
            }
        };
        this.delayTask = new Runnable() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.3
            @Override // java.lang.Runnable
            public void run() {
                CommonTopBar.this.showToastWindow(false);
                CommonTopBar.this.mToastIsShow = false;
            }
        };
        this.mDismissListener = new PopupWindow.OnDismissListener() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.4
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                CommonTopBar.this.mIvDown.setVisibility(0);
                CommonTopBar.this.mIvUp.setVisibility(8);
            }
        };
        init(context, attributeSet);
    }

    public CommonTopBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mKaiHeiTip = "";
        this.mToastIsShow = false;
        this.mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                Resolution item = CommonTopBar.this.mResolutionListAdapter.getItem(i2);
                CommonTopBar.this.mResolutionListAdapter.setSelectedIndex(i2);
                if (CommonTopBar.this.mLlSwitchResolution.getChildAt(0) != null && (CommonTopBar.this.mLlSwitchResolution.getChildAt(0) instanceof TextView)) {
                    ((TextView) CommonTopBar.this.mLlSwitchResolution.getChildAt(0)).setText(item.name);
                }
                CommonTopBar.this.mIvDown.setVisibility(0);
                CommonTopBar.this.mIvUp.setVisibility(8);
                SdkProperties.resolution.set(Integer.valueOf(item.resolution));
                if (CommonTopBar.this.mSwitchResolutionWindow != null) {
                    CommonTopBar.this.mSwitchResolutionWindow.dismiss();
                }
                if (item.videoBitrate <= 800) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_800);
                    return;
                }
                if (item.videoBitrate <= 1200) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_1200);
                    return;
                }
                if (item.videoBitrate <= 2000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_2000);
                    return;
                }
                if (item.videoBitrate <= 3000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_3000);
                } else if (item.videoBitrate <= 4000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_4000);
                } else if (item.videoBitrate > 4000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_ABOVE4K);
                }
            }
        };
        this.mOnClickListener = new View.OnClickListener() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.equals(CommonTopBar.this.mIvBack)) {
                    if (CommonTopBar.this.mTopBarListener == null) {
                        return;
                    }
                    CommonTopBar.this.mTopBarListener.onClickBack();
                    return;
                }
                if (view.equals(CommonTopBar.this.mLlAvatar)) {
                    if (CommonTopBar.this.mTopBarListener == null) {
                        return;
                    }
                    CommonTopBar.this.mTopBarListener.onClickAvatar();
                    return;
                }
                if (view.equals(CommonTopBar.this.mIvClose)) {
                    if (CommonTopBar.this.mTopBarListener == null) {
                        return;
                    }
                    CommonTopBar.this.mTopBarListener.onClickClose();
                    return;
                }
                if (!view.equals(CommonTopBar.this.mLlSwitchResolution)) {
                    if (view.equals(CommonTopBar.this.mIvKaihei)) {
                        CommonTopBar.this.mToastIsShow = !r2.mToastIsShow;
                        CommonTopBar commonTopBar = CommonTopBar.this;
                        commonTopBar.showToastWindow(commonTopBar.mToastIsShow);
                        if (CommonTopBar.this.mLlKaihei.isSelected()) {
                            Report.event(SdkReportConst.GANGUP_CLICK_ZUDUI_ON);
                            return;
                        } else {
                            Report.event(SdkReportConst.GANGUP_CLICK_ZUDUI_OFF);
                            return;
                        }
                    }
                    return;
                }
                Report.event(SdkReportConst.CLICK_HOME_QUALITY);
                CommonTopBar.this.showSwitchWindow();
                CommonTopBar.this.mIvDown.setVisibility(8);
                CommonTopBar.this.mIvUp.setVisibility(0);
            }
        };
        this.delayTask = new Runnable() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.3
            @Override // java.lang.Runnable
            public void run() {
                CommonTopBar.this.showToastWindow(false);
                CommonTopBar.this.mToastIsShow = false;
            }
        };
        this.mDismissListener = new PopupWindow.OnDismissListener() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.4
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                CommonTopBar.this.mIvDown.setVisibility(0);
                CommonTopBar.this.mIvUp.setVisibility(8);
            }
        };
        init(context, attributeSet);
    }

    public CommonTopBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mKaiHeiTip = "";
        this.mToastIsShow = false;
        this.mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i22, long j) {
                Resolution item = CommonTopBar.this.mResolutionListAdapter.getItem(i22);
                CommonTopBar.this.mResolutionListAdapter.setSelectedIndex(i22);
                if (CommonTopBar.this.mLlSwitchResolution.getChildAt(0) != null && (CommonTopBar.this.mLlSwitchResolution.getChildAt(0) instanceof TextView)) {
                    ((TextView) CommonTopBar.this.mLlSwitchResolution.getChildAt(0)).setText(item.name);
                }
                CommonTopBar.this.mIvDown.setVisibility(0);
                CommonTopBar.this.mIvUp.setVisibility(8);
                SdkProperties.resolution.set(Integer.valueOf(item.resolution));
                if (CommonTopBar.this.mSwitchResolutionWindow != null) {
                    CommonTopBar.this.mSwitchResolutionWindow.dismiss();
                }
                if (item.videoBitrate <= 800) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_800);
                    return;
                }
                if (item.videoBitrate <= 1200) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_1200);
                    return;
                }
                if (item.videoBitrate <= 2000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_2000);
                    return;
                }
                if (item.videoBitrate <= 3000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_3000);
                } else if (item.videoBitrate <= 4000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_4000);
                } else if (item.videoBitrate > 4000) {
                    Report.event(SdkReportConst.CLICK_HOME_QUALITY_ABOVE4K);
                }
            }
        };
        this.mOnClickListener = new View.OnClickListener() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.equals(CommonTopBar.this.mIvBack)) {
                    if (CommonTopBar.this.mTopBarListener == null) {
                        return;
                    }
                    CommonTopBar.this.mTopBarListener.onClickBack();
                    return;
                }
                if (view.equals(CommonTopBar.this.mLlAvatar)) {
                    if (CommonTopBar.this.mTopBarListener == null) {
                        return;
                    }
                    CommonTopBar.this.mTopBarListener.onClickAvatar();
                    return;
                }
                if (view.equals(CommonTopBar.this.mIvClose)) {
                    if (CommonTopBar.this.mTopBarListener == null) {
                        return;
                    }
                    CommonTopBar.this.mTopBarListener.onClickClose();
                    return;
                }
                if (!view.equals(CommonTopBar.this.mLlSwitchResolution)) {
                    if (view.equals(CommonTopBar.this.mIvKaihei)) {
                        CommonTopBar.this.mToastIsShow = !r2.mToastIsShow;
                        CommonTopBar commonTopBar = CommonTopBar.this;
                        commonTopBar.showToastWindow(commonTopBar.mToastIsShow);
                        if (CommonTopBar.this.mLlKaihei.isSelected()) {
                            Report.event(SdkReportConst.GANGUP_CLICK_ZUDUI_ON);
                            return;
                        } else {
                            Report.event(SdkReportConst.GANGUP_CLICK_ZUDUI_OFF);
                            return;
                        }
                    }
                    return;
                }
                Report.event(SdkReportConst.CLICK_HOME_QUALITY);
                CommonTopBar.this.showSwitchWindow();
                CommonTopBar.this.mIvDown.setVisibility(8);
                CommonTopBar.this.mIvUp.setVisibility(0);
            }
        };
        this.delayTask = new Runnable() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.3
            @Override // java.lang.Runnable
            public void run() {
                CommonTopBar.this.showToastWindow(false);
                CommonTopBar.this.mToastIsShow = false;
            }
        };
        this.mDismissListener = new PopupWindow.OnDismissListener() { // from class: com.huya.berry.gamesdk.widgets.CommonTopBar.4
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                CommonTopBar.this.mIvDown.setVisibility(0);
                CommonTopBar.this.mIvUp.setVisibility(8);
            }
        };
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        View viewInflate;
        this.mContext = context;
        if (CommonUtil.isScreenLandScape()) {
            viewInflate = LayoutInflater.from(context).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_layout_common_top_bar_land), this);
        } else {
            viewInflate = LayoutInflater.from(context).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_layout_common_top_bar), this);
        }
        initView(viewInflate);
        this.mResolutionListAdapter = new ResolutionListAdapter(context);
    }

    private void initView(View view) {
        this.mIvLogo = (ImageView) view.findViewById(ResourceUtil.getIdResIDByName("iv_logo"));
        this.mLlTitle = (LinearLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_title));
        this.mIvBack = (ImageView) view.findViewById(ResourceUtil.getIdResIDByName("iv_back"));
        this.mTvTitle = (TextView) view.findViewById(ResourceUtil.getIdResIDByName("tv_title"));
        this.mLlKaihei = (LinearLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_kaihei));
        this.mIvKaihei = (ImageView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.iv_kaihei));
        this.mLlResolution = (LinearLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_resolution));
        this.mLlSwitchResolution = (LinearLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_switch_resolution));
        this.mLlAvatar = (LinearLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_avatar));
        this.mIvAvatar = (ImageView) view.findViewById(ResourceUtil.getIdResIDByName("iv_avatar"));
        this.mTvNickname = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_nickname));
        this.mIvClose = (ImageView) view.findViewById(ResourceUtil.getIdResIDByName("iv_close"));
        this.mIvDown = (ImageView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.down_icon));
        this.mIvUp = (ImageView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.up_icon));
        this.mIvBack.setOnClickListener(this.mOnClickListener);
        this.mLlAvatar.setOnClickListener(this.mOnClickListener);
        this.mIvClose.setOnClickListener(this.mOnClickListener);
        this.mLlSwitchResolution.setOnClickListener(this.mOnClickListener);
        this.mIvKaihei.setOnClickListener(this.mOnClickListener);
    }

    public void showLogo(boolean z) {
        if (z) {
            this.mIvLogo.setVisibility(0);
            this.mLlAvatar.setVisibility(0);
            this.mLlTitle.setVisibility(8);
        } else {
            this.mIvLogo.setVisibility(8);
            this.mLlAvatar.setVisibility(8);
            this.mLlTitle.setVisibility(0);
        }
    }

    public void showKaihei(boolean z) {
        if (z) {
            this.mLlKaihei.setVisibility(0);
        } else {
            this.mLlKaihei.setVisibility(8);
        }
    }

    public void setKaiheiStatus(boolean z, String str) {
        this.mKaiHeiTip = str;
        if (z) {
            this.mLlKaihei.setSelected(true);
        } else {
            this.mLlKaihei.setSelected(false);
        }
    }

    public void setTitle(String str) {
        this.mTvTitle.setText(str);
    }

    public ImageView getIvLogo() {
        return this.mIvLogo;
    }

    public void showResolution(boolean z) {
        if (z) {
            this.mLlResolution.setVisibility(0);
        } else {
            this.mLlResolution.setVisibility(8);
        }
    }

    public void setAvatarInfo(boolean z, String str, String str2) {
        if (z) {
            this.mTvNickname.setText(str);
            ImageBind.displayCircle(ArkValue.gContext, this.mIvAvatar, str2, ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_unlogin_icon));
        } else {
            this.mTvNickname.setText(SqTrackBtn.SqTrackBtnExt.login);
            this.mIvAvatar.setImageResource(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_unlogin_icon));
        }
    }

    public void setTopBarListener(TopBarListener topBarListener) {
        this.mTopBarListener = topBarListener;
    }

    public void updateResolutionList() {
        this.mSwitchResolutionWindow = null;
        updateResolution();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToastWindow(boolean z) {
        View viewInflate;
        if (z) {
            if (this.mToastWindow == null) {
                if (this.mLlKaihei.isSelected()) {
                    viewInflate = LayoutInflater.from(this.mContext).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_layout_kaihei_window), (ViewGroup) null, false);
                } else {
                    viewInflate = LayoutInflater.from(this.mContext).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_layout_kaihei_window_not_login), (ViewGroup) null, false);
                }
                TextView textView = (TextView) viewInflate.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_kaihei_toast));
                textView.setText(CommonUtil.getShortText(this.mKaiHeiTip, 50));
                textView.requestLayout();
                int dp = (int) UIUtil.getDp(204.0f);
                int textWidth = ((int) CommonUtil.getTextWidth(this.mKaiHeiTip, UIUtil.getDp(9.0f))) + ((int) UIUtil.getDp(22.0f));
                ImageView imageView = (ImageView) viewInflate.findViewById(ResourceUtil.getIdResIDByName(SqR.id.iv_kaihei_arrow));
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                imageView.setLayoutParams(layoutParams);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) textView.getLayoutParams();
                if (textWidth < dp) {
                    dp = textWidth;
                }
                if (this.mLlKaihei.isSelected()) {
                    if (CommonUtil.getStringLen(this.mTvNickname.getText().toString()) > 2 || textWidth < ((int) UIUtil.getDp(160.0f))) {
                        layoutParams.leftMargin = (int) UIUtil.getDp(7.0f);
                    } else {
                        layoutParams.leftMargin = (int) UIUtil.getDp(15.0f);
                    }
                } else if (textWidth <= dp) {
                    layoutParams.leftMargin = (int) UIUtil.getDp(10.0f);
                } else {
                    layoutParams.leftMargin = (dp - ((int) UIUtil.getDp(40.0f))) / 2;
                }
                PopupWindow popupWindow = new PopupWindow(viewInflate, dp, layoutParams2.height, true);
                this.mToastWindow = popupWindow;
                popupWindow.setOutsideTouchable(false);
                this.mToastWindow.setFocusable(false);
                this.mToastWindow.showAsDropDown(this.mIvKaihei, 0, (int) UIUtil.getDp(3.0f));
                this.mIvKaihei.removeCallbacks(this.delayTask);
                this.mIvKaihei.postDelayed(this.delayTask, OAIDHelper.TIMEOUT);
                return;
            }
            return;
        }
        if (this.mToastWindow != null) {
            this.mIvKaihei.removeCallbacks(this.delayTask);
            this.mToastWindow.dismiss();
            this.mToastWindow = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSwitchWindow() {
        if (this.mSwitchResolutionWindow != null || initPopWindow()) {
            this.mSwitchResolutionWindow.showAsDropDown(this.mLlSwitchResolution, 0, (int) UIUtil.getDp(3.0f));
        }
    }

    private boolean initPopWindow() {
        List<LivingParams> resolutionSettingList = ResolutionOptions.getInstance().getResolutionSettingList();
        if (resolutionSettingList.size() == 0) {
            return false;
        }
        View viewInflate = LayoutInflater.from(this.mContext).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_layout_resolution_window), (ViewGroup) null, false);
        ListView listView = (ListView) viewInflate.findViewById(ResourceUtil.getIdResIDByName(SqR.id.lv_resolution));
        ArrayList arrayList = new ArrayList();
        for (LivingParams livingParams : resolutionSettingList) {
            arrayList.add(new Resolution(livingParams.getResolution(), livingParams.getVideoBitrate(), livingParams.getName(), livingParams.getTips()));
        }
        this.mResolutionListAdapter.setResolutionList(arrayList);
        this.mResolutionListAdapter.setResolution(SdkProperties.resolution.get().intValue());
        listView.setAdapter((ListAdapter) this.mResolutionListAdapter);
        listView.setOnItemClickListener(this.mOnItemClickListener);
        if (resolutionSettingList.size() < 4) {
            this.mSwitchResolutionWindow = new PopupWindow(viewInflate, (int) UIUtil.getDp(194.0f), (int) UIUtil.getDp((resolutionSettingList.size() * 50) + 2), true);
        } else {
            this.mSwitchResolutionWindow = new PopupWindow(viewInflate, (int) UIUtil.getDp(194.0f), (int) UIUtil.getDp(152.0f), true);
        }
        this.mSwitchResolutionWindow.setFocusable(true);
        this.mSwitchResolutionWindow.setTouchable(true);
        this.mSwitchResolutionWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.mSwitchResolutionWindow.setOutsideTouchable(true);
        this.mSwitchResolutionWindow.setOnDismissListener(this.mDismissListener);
        return true;
    }

    private void updateResolution() {
        if (this.mLlResolution.getVisibility() == 8) {
            this.mLlResolution.setVisibility(0);
        }
        LivingParams curLivingParams = ResolutionOptions.getInstance().getCurLivingParams();
        if (this.mLlSwitchResolution.getChildAt(0) == null || !(this.mLlSwitchResolution.getChildAt(0) instanceof TextView)) {
            return;
        }
        ((TextView) this.mLlSwitchResolution.getChildAt(0)).setText(curLivingParams.getName());
    }
}
