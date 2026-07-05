package com.huya.berry.sdklivelist;

import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.duowan.HUYA.ActiveEventInfo;
import com.duowan.HUYA.ComponentDistributeRsp;
import com.duowan.HUYA.ComponentItem;
import com.duowan.HUYA.GetActiveEventInfoRsp;
import com.duowan.HUYA.GetLivingInfoRsp;
import com.duowan.HUYA.GetUserProfileRsp;
import com.duowan.HUYA.UserRecItem;
import com.duowan.HUYA.UserRecListRsp;
import com.duowan.auk.ArkValue;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.live.common.framework.fragment.BaseDialogFragment;
import com.duowan.live.login.api.ILoginService;
import com.duowan.live.one.module.report.Report;
import com.duowan.live.one.util.NetworkUtil;
import com.huya.android.support.v7.widget.GridLayoutManager;
import com.huya.android.support.v7.widget.RecyclerView;
import com.huya.berry.endlive.api.ISdkPlayerService;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.ICommonService;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.resolutions.ResolutionOptions;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.PermissionTool;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.TaskExecutor;
import com.huya.berry.gamesdk.utils.UIUtil;
import com.huya.berry.gamesdk.widgets.CommonTopBar;
import com.huya.berry.gamesdk.widgets.ComnTextView;
import com.huya.berry.gamesdk.wup.WupHelper;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.module.help.LiveHelper;
import com.huya.berry.module.live.ISdkLiveService;
import com.huya.berry.module.live.LiveInterface;
import com.huya.berry.sdklivelist.LiveListAdapter;
import com.huya.berry.sdklivelist.SwipeToLoadHelper;
import com.huya.berry.sdklivelist.api.LiveListListener;
import com.huya.berry.sdklivelist.api.LiveListPresenter;
import com.huya.berry.sdklivelist.help.LiveListHelper;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.LoginInterface;
import com.huya.component.user.UserProperties;
import com.huya.component.user.api.IUserService;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.live.rxutils.SchedulerUtils;
import com.huya.live.service.ServiceCenter;
import com.huya.live.utils.image.ImageBind;
import com.sqwan.liveshow.huya.SqR;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveListFragment extends BaseDialogFragment implements LiveListPresenter {
    public static final String TAG = "LiveListFragment";
    private FrameLayout mFlAnthorComponent;
    private FrameLayout mFlAnthorRecruit;
    private boolean mIsLandscape;
    private LiveListListener mListener;
    private LiveListAdapter mLiveListAdapter;
    private LinearLayout mLlEmpty;
    private LinearLayout mLlLiveList;
    private LinearLayout mLlSmallWindow;
    private LinearLayout mLlStartLive;
    private SwipeToLoadHelper mLoadmoreHelper;
    private RelativeLayout mRlAnthorRecruitSmall;
    private RecyclerView mRvLiveList;
    private SwipeRefreshLayout mSrlLiveList;
    private CommonTopBar mTopBar;
    private ComnTextView mTvAnthorComponentSmall;
    private ComnTextView mTvAnthorRecruitSmall;
    private TextView mTvComponentStartLiveSmall;
    private TextView mTvStartLiveSmall;
    private RelativeLayout mfLAnthorComponentSmall;
    private boolean mIsReportRecruit = false;
    private boolean mIsRefresh = true;
    private boolean mIsHasLiveList = true;
    private ArrayList<ComponentInfo> mComponentList = new ArrayList<>();
    private CommonTopBar.TopBarListener mTopBarListener = new CommonTopBar.TopBarListener() { // from class: com.huya.berry.sdklivelist.LiveListFragment.1
        @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
        public void onClickBack() {
        }

        @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
        public void onClickAvatar() {
            if (LoginProperties.uid.get().longValue() > 0) {
                Report.event(SdkReportConst.CLICK_HOME_USERINFO);
                LiveListHelper.usercenter(LiveListFragment.this.getActivity());
                return;
            }
            Report.event(SdkReportConst.CLICK_HOME_LOGIN);
            LiveListFragment.this.isLogining();
            if (LiveListFragment.this.mListener != null) {
                LiveListFragment.this.mListener.onLogin();
            }
        }

        @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
        public void onClickClose() {
            if (LiveListFragment.this.mListener != null) {
                LiveListFragment.this.mListener.onClose();
                Report.event(SdkReportConst.CLICK_HOME_CLOSE);
            }
        }
    };
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.huya.berry.sdklivelist.LiveListFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == ResourceUtil.getIdResIDByName(SqR.id.ll_start_live)) {
                LiveListFragment.this.startLive(SdkReportConst.CLICK_HOME_BEGINLIVE);
                return;
            }
            if (view.equals(LiveListFragment.this.mFlAnthorRecruit)) {
                LiveListFragment.this.openAnthorRecruit((String) LiveListFragment.this.mFlAnthorRecruit.getTag());
                Report.event(SdkReportConst.CLICK_HOME_ZHAOMU);
                return;
            }
            if (view.equals(LiveListFragment.this.mFlAnthorComponent)) {
                LiveListFragment.this.openComponent((String) LiveListFragment.this.mFlAnthorComponent.getTag(), false);
                return;
            }
            if (view.equals(LiveListFragment.this.mTvStartLiveSmall)) {
                LiveListFragment.this.startLive(SdkReportConst.CLICK_HOME_TOPBAR_BEGINLIVE);
                return;
            }
            if (view.equals(LiveListFragment.this.mTvComponentStartLiveSmall)) {
                LiveListFragment.this.startLive(SdkReportConst.CLICK_HOME_TOPBAR_BEGINLIVE);
                return;
            }
            if (view.equals(LiveListFragment.this.mTvAnthorRecruitSmall)) {
                LiveListFragment.this.openAnthorRecruit((String) LiveListFragment.this.mTvAnthorRecruitSmall.getTag());
                Report.event(SdkReportConst.CLICK_HOME_TOPBAR_ZHAOMU);
            } else if (view.equals(LiveListFragment.this.mTvAnthorComponentSmall)) {
                LiveListFragment.this.openComponent((String) LiveListFragment.this.mTvAnthorComponentSmall.getTag(), true);
            }
        }
    };
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() { // from class: com.huya.berry.sdklivelist.LiveListFragment.3
        @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
        public void onRefresh() {
            L.info(LiveListFragment.TAG, "onRefresh");
            LiveListFragment.this.mIsRefresh = true;
            LiveListFragment.this.requestLiveList();
        }
    };
    private SwipeToLoadHelper.LoadMoreListener mLoadMoreListener = new SwipeToLoadHelper.LoadMoreListener() { // from class: com.huya.berry.sdklivelist.LiveListFragment.4
        @Override // com.huya.berry.sdklivelist.SwipeToLoadHelper.LoadMoreListener
        public void onLoad() {
            if (LiveListFragment.this.mIsRefresh) {
                return;
            }
            L.info(LiveListFragment.TAG, "onLoad");
            LiveListFragment.this.requestLiveList();
        }
    };
    private LiveListAdapter.LiveListClickListener mLiveListClickListener = new LiveListAdapter.LiveListClickListener() { // from class: com.huya.berry.sdklivelist.LiveListFragment.5
        @Override // com.huya.berry.sdklivelist.LiveListAdapter.LiveListClickListener
        public void onHeaderViewClick() {
            L.info(LiveListFragment.TAG, "onHeaderViewClick");
            LiveListFragment.this.startLive(SdkReportConst.CLICK_HOME_BEGINLIVE);
        }

        @Override // com.huya.berry.sdklivelist.LiveListAdapter.LiveListClickListener
        public void onItemClick(UserRecItem userRecItem) {
            L.info(LiveListFragment.TAG, "onItemClick:" + userRecItem.sTitle);
            Report.event(SdkReportConst.CLICK_HOME_LIVEPREVIEW);
            if (SdkProperties.isNeedPlay.get().booleanValue()) {
                PlayerHelper.openFloat(LiveListFragment.this.getActivity(), userRecItem);
                SdkProperties.needPlayerFloat.set(true);
                LiveListFragment.this.checkAlertWindowPermission();
                return;
            }
            LiveHelper.startApp(LiveListFragment.this.getActivity(), userRecItem, WupHelper.getSHuYaUA(), SdkProperties.gameId.get().intValue());
        }

        @Override // com.huya.berry.sdklivelist.LiveListAdapter.LiveListClickListener
        public void onAnthorRecruitClick(String str) {
            LiveListFragment.this.openAnthorRecruit(str);
            Report.event(SdkReportConst.CLICK_HOME_ZHAOMU);
        }

        @Override // com.huya.berry.sdklivelist.LiveListAdapter.LiveListClickListener
        public void onComponentClick(String str) {
            LiveListFragment.this.openComponent(str, false);
        }
    };
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() { // from class: com.huya.berry.sdklivelist.LiveListFragment.6
        @Override // com.huya.android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (recyclerView.getLayoutManager() != null) {
                LiveListFragment.this.showRecruitSmall(recyclerView.getLayoutManager());
            }
        }
    };
    private Runnable mQueryUserInfoTask = new Runnable() { // from class: com.huya.berry.sdklivelist.LiveListFragment.10
        @Override // java.lang.Runnable
        public void run() {
            if (UserProperties.roomId.get().intValue() <= 0) {
                TaskExecutor.uiHandler().removeCallbacks(LiveListFragment.this.mQueryUserInfoTask);
                LiveListFragment.this.getUserProfile();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void startLive(String str) {
        if (!NetworkUtil.isNetworkAvailable(ArkValue.gContext)) {
            ArkToast.show("网络已断开，请检查网络连接");
            return;
        }
        isLogining();
        if (PlayerHelper.isShown()) {
            ArkToast.show("观看直播时无法开播，请关掉直播后重试");
            return;
        }
        LiveListListener liveListListener = this.mListener;
        if (liveListListener != null) {
            liveListListener.onStartLive();
            Report.event(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAlertWindowPermission() {
        L.info(TAG, "checkAlertWindowPermission");
        if (!PermissionTool.isIgnoreDrawOverlays() && !PermissionTool.checkDrawOverlays(getActivity())) {
            showAlertWindowPermissionFragment();
            return;
        }
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.getLivingInfo(PlayerHelper.sid, PlayerHelper.subSid, PlayerHelper.presenterUid, 0L).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<GetLivingInfoRsp>() { // from class: com.huya.berry.sdklivelist.LiveListFragment.7
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(GetLivingInfoRsp getLivingInfoRsp) {
                    ISdkPlayerService iSdkPlayerService = (ISdkPlayerService) ServiceCenter.instance().getService(ISdkPlayerService.class);
                    if (iSdkPlayerService != null) {
                        iSdkPlayerService.openRealFloat();
                    }
                    L.info(LiveListFragment.TAG, "OpenRealFloat mView");
                }
            });
        }
    }

    private void showAlertWindowPermissionFragment() {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        AlertWindowDialogFragment.getInstance(getActivity().getFragmentManager()).show(getActivity().getFragmentManager(), AlertWindowDialogFragment.TAG);
    }

    public static LiveListFragment getInstance(FragmentManager fragmentManager, boolean z) {
        LiveListFragment liveListFragment = (LiveListFragment) fragmentManager.findFragmentByTag(TAG);
        if (liveListFragment == null) {
            liveListFragment = new LiveListFragment();
        }
        liveListFragment.setIsHasLiveList(z);
        return liveListFragment;
    }

    public void setListener(LiveListListener liveListListener) {
        this.mListener = liveListListener;
    }

    public void setIsHasLiveList(boolean z) {
        this.mIsHasLiveList = z;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, ResourceUtil.getStyleResIDByName("hyberry.Widget.LiveList.Dialog"));
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate;
        boolean zIsScreenLandScape = CommonUtil.isScreenLandScape();
        this.mIsLandscape = zIsScreenLandScape;
        if (zIsScreenLandScape) {
            viewInflate = layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_fragment_live_list_land), (ViewGroup) null);
        } else {
            viewInflate = layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_fragment_live_list), (ViewGroup) null);
        }
        initView(viewInflate);
        if (LoginProperties.loginState.get() == LoginProperties.LoginState.LoggedIn && LoginProperties.uid.get().longValue() > 0) {
            this.mTopBar.updateResolutionList();
            this.mTopBar.setAvatarInfo(true, UserProperties.nickName.get(), UserProperties.avatarUrl.get());
        }
        getComponentDistribute();
        return viewInflate;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.mIsHasLiveList) {
            requestLiveList();
        }
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ILoginService iLoginService = (ILoginService) ServiceCenter.instance().getService(ILoginService.class);
        if (iLoginService != null && LoginProperties.uid.get().longValue() == 0 && iLoginService.needAutoLogin()) {
            iLoginService.autoLogin();
        }
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
        if (this.mIsHasLiveList) {
            Report.event(SdkReportConst.PV_HOME_WITHLIVE);
        } else {
            Report.event(SdkReportConst.PV_HOME_NOLIVE);
        }
    }

    private void initView(View view) {
        this.mLlLiveList = (LinearLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_live_list));
        this.mLlSmallWindow = (LinearLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_small_window));
        if (this.mIsHasLiveList) {
            this.mLlLiveList.setVisibility(0);
            this.mLlSmallWindow.setVisibility(8);
            this.mTopBar = (CommonTopBar) this.mLlLiveList.findViewById(ResourceUtil.getIdResIDByName(SqR.id.top_bar));
            this.mSrlLiveList = (SwipeRefreshLayout) this.mLlLiveList.findViewById(ResourceUtil.getIdResIDByName(SqR.id.srl_live_list));
            this.mFlAnthorRecruit = (FrameLayout) this.mLlLiveList.findViewById(ResourceUtil.getIdResIDByName(SqR.id.fl_anthor_recruit));
            this.mFlAnthorComponent = (FrameLayout) this.mLlLiveList.findViewById(ResourceUtil.getIdResIDByName(SqR.id.fl_anthor_component));
            this.mRvLiveList = (RecyclerView) this.mLlLiveList.findViewById(ResourceUtil.getIdResIDByName(SqR.id.rv_live_list));
            this.mLlEmpty = (LinearLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_empty));
            this.mTopBar.showLogo(true);
            this.mTopBar.setTopBarListener(this.mTopBarListener);
            this.mSrlLiveList.setOnRefreshListener(this.mOnRefreshListener);
            LiveListAdapter liveListAdapter = new LiveListAdapter(getActivity());
            this.mLiveListAdapter = liveListAdapter;
            liveListAdapter.setOnLiveListClickListener(this.mLiveListClickListener);
            this.mRvLiveList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            this.mRvLiveList.setAdapter(this.mLiveListAdapter);
            this.mRvLiveList.addOnScrollListener(this.mOnScrollListener);
            SwipeToLoadHelper swipeToLoadHelper = new SwipeToLoadHelper(this.mRvLiveList);
            this.mLoadmoreHelper = swipeToLoadHelper;
            swipeToLoadHelper.setLoadMoreListener(this.mLoadMoreListener);
            if (this.mIsLandscape) {
                if (this.mLlLiveList.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_start_live)) == null) {
                    return;
                }
                LinearLayout linearLayout = (LinearLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_start_live));
                this.mLlStartLive = linearLayout;
                linearLayout.setOnClickListener(this.mOnClickListener);
                this.mLiveListAdapter.setIsHasHeader(false);
                this.mRvLiveList.addItemDecoration(new LiveListDecoration(11, 14, 8, 0, 2, false));
            } else {
                this.mRvLiveList.addItemDecoration(new LiveListDecoration(11, 14, 0, 13, 2, true));
                this.mRlAnthorRecruitSmall = (RelativeLayout) this.mLlLiveList.findViewById(ResourceUtil.getIdResIDByName(SqR.id.rl_anthor_recruit_small));
                this.mTvAnthorRecruitSmall = (ComnTextView) this.mLlLiveList.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_anthor_recruit_small));
                this.mTvStartLiveSmall = (TextView) this.mLlLiveList.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_start_live_small));
                this.mTvComponentStartLiveSmall = (TextView) this.mLlLiveList.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_component_start_live_small));
                this.mfLAnthorComponentSmall = (RelativeLayout) this.mLlLiveList.findViewById(ResourceUtil.getIdResIDByName(SqR.id.fl_component_small));
                this.mTvAnthorComponentSmall = (ComnTextView) this.mLlLiveList.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_component_small));
                this.mTvStartLiveSmall.setOnClickListener(this.mOnClickListener);
                this.mTvComponentStartLiveSmall.setOnClickListener(this.mOnClickListener);
            }
        } else {
            this.mLlLiveList.setVisibility(8);
            this.mLlSmallWindow.setVisibility(0);
            this.mTopBar = (CommonTopBar) this.mLlSmallWindow.findViewById(ResourceUtil.getIdResIDByName(SqR.id.top_bar));
            this.mLlStartLive = (LinearLayout) this.mLlSmallWindow.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_start_live));
            this.mFlAnthorRecruit = (FrameLayout) this.mLlSmallWindow.findViewById(ResourceUtil.getIdResIDByName(SqR.id.fl_anthor_recruit));
            this.mFlAnthorComponent = (FrameLayout) this.mLlSmallWindow.findViewById(ResourceUtil.getIdResIDByName(SqR.id.fl_anthor_component));
            this.mTopBar.showLogo(true);
            this.mTopBar.setTopBarListener(this.mTopBarListener);
            this.mLlStartLive.setOnClickListener(this.mOnClickListener);
        }
        this.mTopBar.showKaihei(SdkProperties.isOneKeyGangUp.get().booleanValue());
        if (SdkProperties.isOneKeyGangUp.get().booleanValue()) {
            this.mTopBar.setKaiheiStatus(LoginProperties.uid.get().longValue() != 0, (LoginProperties.uid.get().longValue() != 0 ? SdkProperties.loginGangUpTip : SdkProperties.notLoginGangUpTip).get());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestLiveList() {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.getRecListByGame(this.mIsRefresh, SdkProperties.gameId.get().intValue(), "").compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<UserRecListRsp>() { // from class: com.huya.berry.sdklivelist.LiveListFragment.8
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(UserRecListRsp userRecListRsp) {
                    if (userRecListRsp == null || userRecListRsp.vItems == null || userRecListRsp.vItems.size() == 0) {
                        if (LiveListFragment.this.mSrlLiveList != null) {
                            LiveListFragment.this.mSrlLiveList.setRefreshing(false);
                        }
                        if (LiveListFragment.this.mLoadmoreHelper != null) {
                            LiveListFragment.this.mLoadmoreHelper.setLoadMoreFinish();
                        }
                        if (LiveListFragment.this.mLiveListAdapter != null) {
                            LiveListFragment.this.mLiveListAdapter.setLoadMoreFinish(true);
                        }
                        LiveListFragment.this.ifShowEmptyView();
                        return;
                    }
                    if (LiveListFragment.this.mSrlLiveList == null) {
                        return;
                    }
                    L.info(LiveListFragment.TAG, "getRecList:" + userRecListRsp.vItems.size() + ",mIsRefresh:" + LiveListFragment.this.mIsRefresh);
                    if (LiveListFragment.this.mIsRefresh) {
                        LiveListFragment.this.mSrlLiveList.setRefreshing(false);
                        LiveListFragment.this.mLiveListAdapter.setItems(userRecListRsp.vItems);
                        LiveListFragment.this.mIsRefresh = false;
                        if (userRecListRsp.vItems.size() < 6) {
                            LiveListFragment.this.mLiveListAdapter.setLoadMoreFinish(true);
                        }
                    } else {
                        LiveListFragment.this.mLoadmoreHelper.setLoadMoreFinish();
                        LiveListFragment.this.mLiveListAdapter.addItems(userRecListRsp.vItems);
                    }
                    LiveListFragment.this.ifShowEmptyView();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserProfile() {
        IUserService iUserService = (IUserService) ServiceCenter.instance().getService(IUserService.class);
        if (iUserService != null) {
            iUserService.getUserProfile(LoginApi.getUid()).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<GetUserProfileRsp>() { // from class: com.huya.berry.sdklivelist.LiveListFragment.9
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(GetUserProfileRsp getUserProfileRsp) {
                    if (LiveListFragment.this.reQueryUserInfo()) {
                        return;
                    }
                    LiveListFragment.this.mTopBar.setAvatarInfo(true, UserProperties.nickName.get(), UserProperties.avatarUrl.get());
                    LiveListFragment.this.mTopBar.setKaiheiStatus(true, SdkProperties.loginGangUpTip.get());
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    LiveListFragment.this.reQueryUserInfo();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean reQueryUserInfo() {
        if (LoginProperties.uid.get().longValue() <= 0 || !TextUtils.isEmpty(UserProperties.avatarUrl.get()) || !TextUtils.isEmpty(UserProperties.nickName.get())) {
            return false;
        }
        TaskExecutor.uiHandler().postDelayed(this.mQueryUserInfoTask, 500L);
        return true;
    }

    private void getComponentDistribute() {
        ICommonService iCommonService = (ICommonService) ServiceCenter.instance().getService(ICommonService.class);
        if (iCommonService != null) {
            iCommonService.getComponentDistribute(SdkProperties.gameId.get().intValue(), LiveHelper.getUserId(), WupHelper.getVersion()).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<ComponentDistributeRsp>() { // from class: com.huya.berry.sdklivelist.LiveListFragment.11
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(ComponentDistributeRsp componentDistributeRsp) {
                    if (componentDistributeRsp == null) {
                        LiveListFragment.this.getAnthorRecruitInfo();
                        return;
                    }
                    L.info(LiveListFragment.TAG, "resp.rsp.vComList:" + componentDistributeRsp.getVComList());
                    ArrayList arrayList = new ArrayList();
                    if (componentDistributeRsp.vComList == null) {
                        LiveListFragment.this.getAnthorRecruitInfo();
                        LiveListFragment.this.mComponentList = arrayList;
                        return;
                    }
                    Iterator<ComponentItem> it = componentDistributeRsp.getVComList().iterator();
                    while (it.hasNext()) {
                        arrayList.add(new ComponentInfo(it.next()));
                    }
                    Collections.sort(arrayList);
                    LiveListFragment.this.mComponentList = arrayList;
                    if (LiveListFragment.this.mComponentList.size() == 0) {
                        LiveListFragment.this.getAnthorRecruitInfo();
                        return;
                    }
                    ComponentInfo componentInfo = (ComponentInfo) LiveListFragment.this.mComponentList.get(0);
                    if (!LiveListFragment.this.mIsHasLiveList || LiveListFragment.this.mIsLandscape) {
                        if (LiveListFragment.this.mFlAnthorComponent == null) {
                            return;
                        }
                        LiveListFragment.this.mFlAnthorComponent.setVisibility(0);
                        if (LiveListFragment.this.mFlAnthorComponent.getChildAt(1) != null && (LiveListFragment.this.mFlAnthorComponent.getChildAt(1) instanceof ImageView)) {
                            ImageBind.displayRound((ImageView) LiveListFragment.this.mFlAnthorComponent.getChildAt(1), componentInfo.sIconUrl, ResourceUtil.getDrawableResIDByName("hyberry_component_horn"), 4);
                        }
                        if (LiveListFragment.this.mFlAnthorComponent.getChildAt(2) != null && (LiveListFragment.this.mFlAnthorComponent.getChildAt(2) instanceof ComnTextView)) {
                            ((ComnTextView) LiveListFragment.this.mFlAnthorComponent.getChildAt(2)).setComnText(componentInfo.sTitle);
                        }
                        LiveListFragment.this.mFlAnthorComponent.setTag(componentInfo.sUrl);
                        LiveListFragment.this.mFlAnthorComponent.setOnClickListener(LiveListFragment.this.mOnClickListener);
                        LiveListFragment.this.reportComponent(componentInfo.iComID + "");
                        return;
                    }
                    LiveListFragment.this.mLiveListAdapter.setComponetnInfo(componentInfo.sTitle, componentInfo.sUrl, componentInfo.sIconUrl);
                    LiveListFragment.this.setComponentSmall(componentInfo.sTitle, componentInfo.sUrl, componentInfo.sIconUrl);
                    LiveListFragment.this.reportComponent(componentInfo.iComID + "");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAnthorRecruitInfo() {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.getAnthorRecruitInfo(LiveHelper.getUserId(), SdkProperties.gameId.get().intValue()).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<GetActiveEventInfoRsp>() { // from class: com.huya.berry.sdklivelist.LiveListFragment.12
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(GetActiveEventInfoRsp getActiveEventInfoRsp) {
                    if (getActiveEventInfoRsp == null || getActiveEventInfoRsp.vActiveEventInfo == null || getActiveEventInfoRsp.vActiveEventInfo.size() == 0) {
                        L.error(LiveListFragment.TAG, "getAnthorRecruitInfo response is null");
                        return;
                    }
                    ActiveEventInfo activeEventInfo = null;
                    Iterator<ActiveEventInfo> it = getActiveEventInfoRsp.vActiveEventInfo.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ActiveEventInfo next = it.next();
                        if (next.iGameID == SdkProperties.gameId.get().intValue()) {
                            activeEventInfo = next;
                            break;
                        }
                    }
                    if (activeEventInfo == null) {
                        return;
                    }
                    if (!TextUtils.isEmpty(activeEventInfo.sTitle) && !TextUtils.isEmpty(activeEventInfo.sDetailUrl)) {
                        if (!LiveListFragment.this.mIsHasLiveList || LiveListFragment.this.mIsLandscape) {
                            if (LiveListFragment.this.mFlAnthorRecruit == null) {
                                return;
                            }
                            LiveListFragment.this.mFlAnthorRecruit.setVisibility(0);
                            if (LiveListFragment.this.mFlAnthorRecruit.getChildAt(2) != null && (LiveListFragment.this.mFlAnthorRecruit.getChildAt(2) instanceof ComnTextView)) {
                                ((ComnTextView) LiveListFragment.this.mFlAnthorRecruit.getChildAt(2)).setComnText(activeEventInfo.sTitle);
                            }
                            LiveListFragment.this.mFlAnthorRecruit.setTag(activeEventInfo.sDetailUrl);
                            LiveListFragment.this.mFlAnthorRecruit.setOnClickListener(LiveListFragment.this.mOnClickListener);
                            Report.event(SdkReportConst.PV_HOME_ZHAOMU);
                            return;
                        }
                        LiveListFragment.this.mLiveListAdapter.setAnthorRecruitInfo(activeEventInfo.sTitle, activeEventInfo.sDetailUrl);
                        LiveListFragment.this.setAnthorRecruitSmall(activeEventInfo.sTitle, activeEventInfo.sDetailUrl);
                        return;
                    }
                    L.error(LiveListFragment.TAG, "getAnthorRecruitInfo title or url is null str");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ifShowEmptyView() {
        if (this.mIsHasLiveList) {
            boolean zIsEmpty = this.mLiveListAdapter.isEmpty();
            if (zIsEmpty) {
                handleLiveList(zIsEmpty);
                if (this.mLlEmpty.getVisibility() == 8) {
                    this.mLlEmpty.setVisibility(0);
                    return;
                }
                return;
            }
            handleLiveList(zIsEmpty);
            if (this.mLlEmpty.getVisibility() == 0) {
                this.mLlEmpty.setVisibility(8);
            }
        }
    }

    private void handleLiveList(boolean z) {
        if (z) {
            if (this.mIsLandscape) {
                if (this.mRvLiveList.getVisibility() == 0) {
                    this.mRvLiveList.setVisibility(8);
                    ViewGroup.LayoutParams layoutParams = this.mSrlLiveList.getLayoutParams();
                    layoutParams.height = (int) UIUtil.getDp((this.mFlAnthorRecruit.getVisibility() == 0 || this.mFlAnthorComponent.getVisibility() == 0) ? 60.0f : 90.0f);
                    this.mSrlLiveList.setLayoutParams(layoutParams);
                    return;
                }
                return;
            }
            ViewGroup.LayoutParams layoutParams2 = this.mSrlLiveList.getLayoutParams();
            layoutParams2.width = (int) UIUtil.getDp(309.0f);
            layoutParams2.height = (int) UIUtil.getDp(160.0f);
            this.mSrlLiveList.setLayoutParams(layoutParams2);
            return;
        }
        if (this.mIsLandscape) {
            if (this.mRvLiveList.getVisibility() == 8) {
                this.mRvLiveList.setVisibility(0);
                ViewGroup.LayoutParams layoutParams3 = this.mSrlLiveList.getLayoutParams();
                layoutParams3.height = -1;
                this.mSrlLiveList.setLayoutParams(layoutParams3);
                return;
            }
            return;
        }
        ViewGroup.LayoutParams layoutParams4 = this.mSrlLiveList.getLayoutParams();
        layoutParams4.width = -1;
        layoutParams4.height = -2;
        this.mSrlLiveList.setLayoutParams(layoutParams4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openAnthorRecruit(String str) {
        if (TextUtils.isEmpty(str)) {
            L.error(TAG, "url为空");
        } else {
            LiveListHelper.anchorRecruit(getActivity(), str, getView().getWidth(), getView().getHeight());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openComponent(String str, boolean z) {
        String str2;
        String str3;
        if (Build.VERSION.SDK_INT <= 23) {
            ArkToast.show("安卓系统版本过低，暂不支持打开此页面");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            L.error(TAG, "url为空");
            return;
        }
        if (this.mComponentList == null) {
            L.error(TAG, "mComponentList为空");
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.mComponentList.size()) {
                str2 = "";
                str3 = str2;
                break;
            }
            ComponentInfo componentInfo = this.mComponentList.get(i);
            if (componentInfo.sUrl.equals(str)) {
                str2 = componentInfo.sTitle;
                str3 = componentInfo.iComID + "";
                break;
            }
            i++;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("Gameid", SdkProperties.gameId.get());
            jSONObject.put("Activity_id", str3);
            if (z) {
                Report.event(SdkReportConst.CLICK_HOME_TOPBAR_ACTIVITY, SdkReportConst.CLICK_HOME_TOPBAR_ACTIVITY_DES, "", jSONObject.toString());
            } else {
                Report.event(SdkReportConst.CLICK_HOME_ACTIVITY, SdkReportConst.CLICK_HOME_ACTIVITY_DES, "", jSONObject.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LiveListHelper.component(getActivity(), CommonUtil.getShortText(str2, 10), str, getView().getWidth(), getView().getHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnthorRecruitSmall(String str, String str2) {
        this.mRlAnthorRecruitSmall.getChildAt(0).setVisibility(0);
        this.mRlAnthorRecruitSmall.getChildAt(1).setVisibility(0);
        this.mRlAnthorRecruitSmall.getChildAt(2).setVisibility(0);
        this.mRlAnthorRecruitSmall.getChildAt(4).setVisibility(8);
        float fMeasureText = this.mTvAnthorRecruitSmall.getPaint().measureText(str);
        if (fMeasureText < UIUtil.getDp(180.0f)) {
            ViewGroup.LayoutParams layoutParams = this.mTvAnthorRecruitSmall.getLayoutParams();
            layoutParams.width = Math.round(fMeasureText);
            this.mTvAnthorRecruitSmall.setLayoutParams(layoutParams);
        }
        this.mTvAnthorRecruitSmall.setComnText(str);
        this.mTvAnthorRecruitSmall.setTag(str2);
        this.mTvAnthorRecruitSmall.setOnClickListener(this.mOnClickListener);
        this.mIsReportRecruit = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setComponentSmall(String str, String str2, String str3) {
        this.mfLAnthorComponentSmall.getChildAt(0).setVisibility(0);
        this.mfLAnthorComponentSmall.getChildAt(1).setVisibility(0);
        this.mfLAnthorComponentSmall.getChildAt(2).setVisibility(0);
        if (this.mfLAnthorComponentSmall.getChildAt(0) != null && (this.mfLAnthorComponentSmall.getChildAt(0) instanceof ImageView)) {
            ImageBind.displayRound((ImageView) this.mfLAnthorComponentSmall.getChildAt(0), str3, ResourceUtil.getDrawableResIDByName("hyberry_component_horn"), 4);
        }
        float fMeasureText = this.mTvAnthorComponentSmall.getPaint().measureText(str);
        if (fMeasureText < UIUtil.getDp(180.0f)) {
            ViewGroup.LayoutParams layoutParams = this.mTvAnthorComponentSmall.getLayoutParams();
            layoutParams.width = Math.round(fMeasureText);
            this.mTvAnthorComponentSmall.setLayoutParams(layoutParams);
        }
        this.mTvAnthorComponentSmall.setComnText(str);
        this.mTvAnthorComponentSmall.setTag(str2);
        this.mTvAnthorComponentSmall.setOnClickListener(this.mOnClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isLogining() {
        if (!NetworkUtil.isNetworkAvailable(ArkValue.gContext)) {
            ArkToast.show("网络已断开，请检查网络连接");
        } else if (LoginProperties.loginState.get() == LoginProperties.LoginState.Logining) {
            ArkToast.show("自动登录中，请稍候...");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRecruitSmall(RecyclerView.LayoutManager layoutManager) {
        View childAt;
        View childAt2;
        ArrayList<ComponentInfo> arrayList = this.mComponentList;
        if (arrayList != null && arrayList.size() > 0) {
            if (this.mfLAnthorComponentSmall == null || (childAt2 = layoutManager.getChildAt(0)) == null) {
                return;
            }
            if (layoutManager.getPosition(childAt2) != 0) {
                if (this.mfLAnthorComponentSmall.getVisibility() == 0) {
                    return;
                }
                this.mfLAnthorComponentSmall.setVisibility(0);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("Gameid", SdkProperties.gameId.get());
                    jSONObject.put("Activity_id", this.mComponentList.get(0).iComID);
                    Report.event(SdkReportConst.PV_HOME_TOPBAR_ACTIVITY, SdkReportConst.PV_HOME_TOPBAR_ACTIVITY_DES, "", jSONObject.toString());
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            this.mfLAnthorComponentSmall.setVisibility(8);
            return;
        }
        if (this.mRlAnthorRecruitSmall == null || (childAt = layoutManager.getChildAt(0)) == null) {
            return;
        }
        if (layoutManager.getPosition(childAt) != 0) {
            if (this.mRlAnthorRecruitSmall.getVisibility() == 0) {
                return;
            }
            this.mRlAnthorRecruitSmall.setVisibility(0);
            if (this.mIsReportRecruit) {
                Report.event(SdkReportConst.PV_HOME_TOPBAR_ZHAOMU);
            }
            Report.event(SdkReportConst.PV_HOME_TOPBAR);
            return;
        }
        this.mRlAnthorRecruitSmall.setVisibility(8);
    }

    @IASlot(executorID = 1)
    public void onLogoutFinish(LoginInterface.LogOut logOut) {
        this.mTopBar.setAvatarInfo(false, null, null);
        ResolutionOptions.getInstance().resetResolution();
        this.mTopBar.showResolution(false);
        this.mTopBar.setKaiheiStatus(false, SdkProperties.notLoginGangUpTip.get());
    }

    @IASlot(executorID = 1)
    public void onUpdateResolutionList(LiveInterface.UpdateResolutionList updateResolutionList) {
        this.mTopBar.updateResolutionList();
        getUserProfile();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportComponent(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("Gameid", SdkProperties.gameId.get());
            jSONObject.put("Activity_id", str);
            Report.event(SdkReportConst.PV_HOME_ACTIVITY, SdkReportConst.PV_HOME_ACTIVITY_DES, "", jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
