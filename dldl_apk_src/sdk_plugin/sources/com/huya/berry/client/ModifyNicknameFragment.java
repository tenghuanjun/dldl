package com.huya.berry.client;

import android.app.FragmentManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.duowan.HUYA.ModifyUserNickRsp;
import com.duowan.HUYA.UserNickStatusRsp;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.live.common.framework.fragment.BaseDialogFragment;
import com.duowan.live.one.module.report.Report;
import com.duowan.live.one.util.NetworkUtil;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.widgets.CommonTopBar;
import com.huya.berry.gamesdk.widgets.LiveAlert;
import com.huya.berry.modifynickname.IModifyNickNameView;
import com.huya.berry.modifynickname.IModifyNicknamePresenter;
import com.huya.berry.modifynickname.ModifyNicknamePresenterImpl;
import com.huya.berry.modifynickname.NickNameInputFilter;
import com.huya.berry.webview.WebviewApi;
import com.huya.component.user.UserProperties;
import com.sqwan.liveshow.huya.SqR;
import java.text.DecimalFormat;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ModifyNicknameFragment extends BaseDialogFragment implements IModifyNickNameView {
    private static final int MAX_NICK_NAME_COUNT = 20;
    private static final String TAG = "ModifyNicknameFragment";
    private String mCurInput;
    private EditText mEtNickname;
    private FrameLayout mFlPayGoldBtn;
    private FrameLayout mFlPaySliverBtn;
    private boolean mIsFree;
    private boolean mIsGoldBeanEnough;
    private boolean mIsSilverBeanEnough;
    private ImageView mIvClear;
    private IModifyNicknamePresenter mModifyNicknamePresenter;
    private int mPayType;
    private CommonTopBar mTopBar;
    private TextView mTvEditNicknameCostTips;
    private TextView mTvModifyRule;
    private TextView mTvSaveNick;
    private View mViewModifyNickname;
    private View mViewNetworkException;
    private ViewStub mVsNetworkException;
    private String mCurNickname = UserProperties.nickName.get();
    private CommonTopBar.TopBarListener mTopBarListener = new CommonTopBar.TopBarListener() { // from class: com.huya.berry.client.ModifyNicknameFragment.1
        @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
        public void onClickAvatar() {
        }

        @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
        public void onClickBack() {
            ModifyNicknameFragment.this.dismissAllowingStateLoss();
        }

        @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
        public void onClickClose() {
            ModifyNicknameFragment.this.dismissAllowingStateLoss();
        }
    };
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.huya.berry.client.ModifyNicknameFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.equals(ModifyNicknameFragment.this.mIvClear)) {
                ModifyNicknameFragment.this.clearInput();
                return;
            }
            if (view.equals(ModifyNicknameFragment.this.mFlPaySliverBtn)) {
                ModifyNicknameFragment.this.selectSliverBean();
            } else if (view.equals(ModifyNicknameFragment.this.mFlPayGoldBtn)) {
                ModifyNicknameFragment.this.selectGoldBean();
            } else if (view.equals(ModifyNicknameFragment.this.mTvSaveNick)) {
                ModifyNicknameFragment.this.saveNickname("");
            }
        }
    };
    private TextWatcher mTextWatcher = new TextWatcher() { // from class: com.huya.berry.client.ModifyNicknameFragment.3
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable) || editable.toString().equals(ModifyNicknameFragment.this.mCurNickname)) {
                ModifyNicknameFragment.this.mTvSaveNick.setEnabled(false);
            } else {
                ModifyNicknameFragment.this.mTvSaveNick.setEnabled(true);
            }
        }
    };

    public static ModifyNicknameFragment getIntance(FragmentManager fragmentManager) {
        ModifyNicknameFragment modifyNicknameFragment = (ModifyNicknameFragment) fragmentManager.findFragmentByTag(TAG);
        return modifyNicknameFragment == null ? new ModifyNicknameFragment() : modifyNicknameFragment;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, ResourceUtil.getStyleResIDByName("hyberry.Widget.InputTitle.Dialog"));
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(CommonUtil.isScreenLandScape() ? SqR.layout.hyberry_fragment_modify_nick_name_land : SqR.layout.hyberry_fragment_modify_nick_name), (ViewGroup) null);
        initPresenter();
        initView(viewInflate);
        Report.event(SdkReportConst.PV_NAMEEDIT);
        return viewInflate;
    }

    @Override // com.huya.berry.modifynickname.IModifyNickNameView
    public void handleNickNameStatus(UserNickStatusRsp userNickStatusRsp) {
        if (userNickStatusRsp == null) {
            handleNickNameStatusFail();
            return;
        }
        this.mIsFree = userNickStatusRsp.iModifyTimes == 0;
        this.mViewModifyNickname.setVisibility(0);
        this.mIsSilverBeanEnough = userNickStatusRsp.lSilverBeanBalance >= ((long) userNickStatusRsp.iSilverBeanPrice);
        this.mIsGoldBeanEnough = userNickStatusRsp.lGoldBeanBalance >= ((long) userNickStatusRsp.iGoldBeanPrice);
        this.mTvModifyRule.setText(userNickStatusRsp.sRulerDesc);
        if (this.mIsFree) {
            this.mPayType = 3;
            this.mTvEditNicknameCostTips.setText("本次修改昵称免费");
            this.mFlPaySliverBtn.setVisibility(8);
            this.mFlPayGoldBtn.setVisibility(8);
            return;
        }
        this.mTvEditNicknameCostTips.setText("本次修改昵称需花费");
        this.mFlPaySliverBtn.setVisibility(0);
        this.mFlPayGoldBtn.setVisibility(0);
        ((TextView) this.mFlPaySliverBtn.getChildAt(0)).setText(String.format("%s银豆", new DecimalFormat(",###").format(userNickStatusRsp.iSilverBeanPrice)));
        ((TextView) this.mFlPayGoldBtn.getChildAt(0)).setText(String.format("%s金豆", new DecimalFormat(",###").format(userNickStatusRsp.iGoldBeanPrice)));
    }

    @Override // com.huya.berry.modifynickname.IModifyNickNameView
    public void popupMoneyNotEnough() {
        int i = this.mPayType;
        new LiveAlert.Builder(getActivity()).title("余额不足").message(i == 1 ? "请在虎牙直播APP或者虎牙官网充值\n金豆后再进行改名" : i == 2 ? "请在虎牙直播APP或者虎牙官网充值\n银豆后再进行改名" : "").positive("好的").create().show();
    }

    @Override // com.huya.berry.modifynickname.IModifyNickNameView
    public void navToVerify(String str) {
        if (TextUtils.isEmpty(str)) {
            L.error(TAG, "验证url为空");
        } else {
            WebviewApi.openWebview(getActivity(), "修改昵称", str, false);
        }
    }

    @Override // com.huya.berry.modifynickname.IModifyNickNameView
    public void saveWithVerifyCode(String str) {
        saveNickname(str);
    }

    @Override // com.huya.berry.modifynickname.IModifyNickNameView
    public void handleNickNameStatusFail() {
        if (this.mViewNetworkException == null) {
            this.mViewNetworkException = this.mVsNetworkException.inflate();
        }
        this.mViewModifyNickname.setVisibility(8);
        this.mViewNetworkException.setVisibility(0);
        TextView textView = (TextView) this.mViewNetworkException.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_exception_msg));
        if (textView == null) {
            return;
        }
        if (NetworkUtil.isNetworkAvailable(getActivity())) {
            textView.setText("服务器连接似乎有点问题");
        } else {
            textView.setText("似乎已断开与互联网的连接");
        }
    }

    @Override // com.huya.berry.modifynickname.IModifyNickNameView
    public void handleModifyNickNameRsp(ModifyUserNickRsp modifyUserNickRsp) {
        UserProperties.nickName.set(this.mCurInput);
        dismiss();
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    private void initPresenter() {
        if (this.mModifyNicknamePresenter == null) {
            this.mModifyNicknamePresenter = new ModifyNicknamePresenterImpl(this);
        }
        this.mModifyNicknamePresenter.onCreate();
        this.mModifyNicknamePresenter.getUserNickNameStatus();
    }

    private void initView(View view) {
        this.mTopBar = (CommonTopBar) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.top_bar));
        this.mViewModifyNickname = view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.sv_modify_nickname));
        this.mEtNickname = (EditText) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.et_nickname));
        this.mIvClear = (ImageView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.iv_clear));
        this.mTvEditNicknameCostTips = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_edit_nickname_cost_tips));
        this.mFlPaySliverBtn = (FrameLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.fl_pay_sliver_btn));
        this.mFlPayGoldBtn = (FrameLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.fl_pay_gold_btn));
        this.mTvSaveNick = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_save_nick));
        this.mTvModifyRule = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_modify_rule));
        this.mVsNetworkException = (ViewStub) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.vs_network_exception));
        this.mTopBar.setTitle("修改昵称");
        this.mTopBar.showLogo(false);
        this.mTopBar.setTopBarListener(this.mTopBarListener);
        this.mEtNickname.setText(this.mCurNickname);
        this.mEtNickname.setFilters(new InputFilter[]{new NickNameInputFilter(20)});
        this.mEtNickname.setSelection(TextUtils.isEmpty(this.mCurNickname) ? 0 : this.mCurNickname.length());
        this.mEtNickname.addTextChangedListener(this.mTextWatcher);
        selectSliverBean();
        this.mTvSaveNick.setEnabled(false);
        this.mIvClear.setOnClickListener(this.mOnClickListener);
        this.mFlPaySliverBtn.setOnClickListener(this.mOnClickListener);
        this.mFlPayGoldBtn.setOnClickListener(this.mOnClickListener);
        this.mTvSaveNick.setOnClickListener(this.mOnClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearInput() {
        this.mEtNickname.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectSliverBean() {
        this.mFlPaySliverBtn.setSelected(true);
        showPayImgSelected(this.mFlPaySliverBtn, true);
        this.mFlPayGoldBtn.setSelected(false);
        showPayImgSelected(this.mFlPayGoldBtn, false);
        this.mPayType = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectGoldBean() {
        this.mFlPaySliverBtn.setSelected(false);
        showPayImgSelected(this.mFlPaySliverBtn, false);
        this.mFlPayGoldBtn.setSelected(true);
        showPayImgSelected(this.mFlPayGoldBtn, true);
        this.mPayType = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveNickname(String str) {
        String strTrim = this.mEtNickname.getText().toString().trim();
        this.mCurInput = strTrim;
        if (calLength(strTrim) > 20) {
            ArkToast.show("昵称超出20个字符长度限制");
            return;
        }
        if (!NetworkUtil.isNetworkAvailable(getActivity())) {
            ArkToast.show("网络异常，请稍后重试");
            return;
        }
        if ((this.mPayType == 2 && !this.mIsSilverBeanEnough) || (this.mPayType == 1 && !this.mIsGoldBeanEnough)) {
            popupMoneyNotEnough();
        } else {
            this.mModifyNicknamePresenter.saveUserNickName(this.mCurInput, str, this.mPayType);
        }
    }

    private void showPayImgSelected(ViewGroup viewGroup, boolean z) {
        if (viewGroup.getChildAt(1) != null) {
            viewGroup.getChildAt(1).setVisibility(z ? 0 : 8);
        }
    }

    private int calLength(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int i2 = 0;
        while (i < str.length()) {
            int i3 = i + 1;
            i2 = str.substring(i, i3).matches("[Α-￥]") ? i2 + 2 : i2 + 1;
            i = i3;
        }
        return i2;
    }
}
