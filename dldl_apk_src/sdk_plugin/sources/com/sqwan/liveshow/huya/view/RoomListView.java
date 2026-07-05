package com.sqwan.liveshow.huya.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.huya.berry.client.customui.model.LiveListInfo;
import com.sq.websocket_engine.NetworkConst;
import com.sqwan.common.dialog.LoadingExDialog;
import com.sqwan.common.util.CheckNetwork;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.common.util.task.Task;
import com.sqwan.liveshow.huya.LiveRoomDataManager;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.adapter.RoomListAdapter;
import com.sqwan.liveshow.huya.adapter.RoomTabAdapter;
import com.sqwan.liveshow.huya.adapter.RoomViewHolder;
import com.sqwan.liveshow.huya.bean.LiveMenuBean;
import com.sqwan.liveshow.huya.engine.LiveshowManager;
import com.sqwan.liveshow.huya.skin.SkinHelper;
import com.sy37sdk.account.floatview.CheckSystemUiViewBase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RoomListView extends CheckSystemUiViewBase implements LiveRoomDataManager.RequestListener {
    private static final String TAG = "RoomListView";
    private Dialog customDialog;
    private final HashMap<Integer, List<LiveListInfo>> dataMap;
    LoadingExDialog initLoading;
    private Typeface mFromAsset;
    private View mLlEmptyLayout;
    private int mPlatformId;
    private RecyclerView mRvLeftTab;
    private RoomListAdapter roomListAdapter;
    private Shader selectShader;
    private Shader unSelectShader;

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase
    public boolean isScreenOnType() {
        return true;
    }

    public RoomListView(Context context) {
        super(context);
        this.dataMap = new HashMap<>();
        this.initLoading = null;
        initView();
    }

    public void show() {
        if (isAttachedToWindow()) {
            ViewUtils.show(this);
            update();
        } else {
            addView();
            StatusBarUtil.hideSystemUI(this.mContext);
        }
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase
    public void initLayoutParams() {
        super.initLayoutParams();
        this.floatLayoutParams.width = -1;
        this.floatLayoutParams.height = -1;
    }

    private void initView() {
        inflate(this.mContext, SqResUtils.getLayoutId(getContext(), SqR.layout.sy37_layout_liveshow_room_list), this);
        initRecyclerView();
        initEmptyView();
        showInitLoading();
        initLeftTab();
        handleClick();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(SqResUtils.getId(getContext(), SqR.id.rv_room_list));
        recyclerView.setItemAnimator(null);
        recyclerView.setOverScrollMode(2);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), this.mContext.getResources().getConfiguration().orientation == 1 ? 2 : 3));
        RoomListAdapter roomListAdapter = new RoomListAdapter(this.mContext, new RoomListAdapter.OnRoomClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$RoomListView$Pf9a2ZkDWskQc6isHs7X0YN38As
            @Override // com.sqwan.liveshow.huya.adapter.RoomListAdapter.OnRoomClickListener
            public final void onRoomClick(LiveListInfo liveListInfo) {
                this.f$0.lambda$initRecyclerView$3$RoomListView(liveListInfo);
            }
        });
        this.roomListAdapter = roomListAdapter;
        recyclerView.setAdapter(roomListAdapter);
        int colorValue = SkinHelper.getColorValue(this.mContext, SqR.color.sy37_liveshow_room_list_bottom_obscuration_color_end, 0);
        View viewFindViewById = findViewById(SqResUtils.getId(getContext(), SqR.id.v_room_list_bottom_obscuration));
        if (this.mContext.getResources().getConfiguration().orientation != 1 || colorValue == 0 || viewFindViewById == null) {
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColors(new int[]{0, colorValue});
        gradientDrawable.setGradientRadius(-45.0f);
        gradientDrawable.setGradientType(0);
        viewFindViewById.setBackground(gradientDrawable);
    }

    public /* synthetic */ void lambda$initRecyclerView$3$RoomListView(final LiveListInfo liveListInfo) {
        if (!CheckNetwork.getInstance().hasNetwork()) {
            ToastUtil.showToast(NetworkConst.tips_no_network);
            return;
        }
        if (CheckNetwork.getInstance().isWifi()) {
            watchLive(this.mPlatformId, liveListInfo);
            return;
        }
        if (this.customDialog == null) {
            Dialog dialog = new Dialog(this.mContext, SqResUtils.getStyleId(this.mContext, "CustomDialog"));
            this.customDialog = dialog;
            dialog.setContentView(SqResUtils.getLayoutId(getContext(), SqR.layout.sy37_dialog_network_desc));
            this.customDialog.setCancelable(true);
            this.customDialog.setCanceledOnTouchOutside(true);
            StatusBarUtil.hideSystemUI(this.customDialog.getWindow());
            if ((this.mContext instanceof Activity) && ((Activity) this.mContext).isFinishing()) {
                return;
            }
            View viewFindViewById = this.customDialog.findViewById(SqResUtils.getId(this.mContext, SqR.id.iv_sy37_liveshow_icon_close));
            if (viewFindViewById != null) {
                viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$RoomListView$hZnLb89jMgHu1HHGztqTbohaHbk
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$null$0$RoomListView(view);
                    }
                });
            }
            this.customDialog.findViewById(SqResUtils.getId(this.mContext, SqR.id.iv_sy37_liveshow_dialog_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$RoomListView$3R4R9TjD3V4Ov-6-56mcuXSWcLU
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$null$1$RoomListView(view);
                }
            });
            this.customDialog.findViewById(SqResUtils.getId(this.mContext, SqR.id.iv_sy37_liveshow_dialog_join)).setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$RoomListView$OH0nfIbH7ZWkU7ldtxVMt0KSs4g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$null$2$RoomListView(liveListInfo, view);
                }
            });
        }
        this.customDialog.show();
    }

    public /* synthetic */ void lambda$null$0$RoomListView(View view) {
        Dialog dialog = this.customDialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.customDialog.dismiss();
    }

    public /* synthetic */ void lambda$null$1$RoomListView(View view) {
        Dialog dialog = this.customDialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.customDialog.dismiss();
    }

    public /* synthetic */ void lambda$null$2$RoomListView(LiveListInfo liveListInfo, View view) {
        Dialog dialog = this.customDialog;
        if (dialog != null && dialog.isShowing()) {
            this.customDialog.dismiss();
        }
        watchLive(this.mPlatformId, liveListInfo);
    }

    private void initEmptyView() {
        this.mLlEmptyLayout = findViewById(SqResUtils.getId(getContext(), SqR.id.ll_empty_layout));
    }

    private void showEmptyView() {
        this.mLlEmptyLayout.setVisibility(0);
    }

    private void hideEmptyView() {
        this.mLlEmptyLayout.setVisibility(8);
    }

    private void showInitLoading() {
        Task.post(new Runnable() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$RoomListView$uED43Se7X5zFlzkZ9DGDSWrIPpc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$showInitLoading$4$RoomListView();
            }
        });
    }

    public /* synthetic */ void lambda$showInitLoading$4$RoomListView() {
        if (this.initLoading != null || getContext() == null) {
            return;
        }
        LoadingExDialog loadingExDialog = new LoadingExDialog(getContext());
        this.initLoading = loadingExDialog;
        loadingExDialog.show();
        this.initLoading.setMessage("");
    }

    private void hideInitLoading() {
        Task.post(new Runnable() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$RoomListView$FmyKA-64Ih_j7_5pQ7lBbCY00Ic
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$hideInitLoading$5$RoomListView();
            }
        });
    }

    public /* synthetic */ void lambda$hideInitLoading$5$RoomListView() {
        LoadingExDialog loadingExDialog = this.initLoading;
        if (loadingExDialog == null || !loadingExDialog.isShowing() || getContext() == null) {
            return;
        }
        this.initLoading.dismiss();
    }

    private void handleClick() {
        findViewById(SqResUtils.getId(getContext(), SqR.id.iv_sy37_liveshow_icon_close)).setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$RoomListView$VM0hA0LugbSmDRssBec-PdaGl-w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$handleClick$6$RoomListView(view);
            }
        });
    }

    public /* synthetic */ void lambda$handleClick$6$RoomListView(View view) {
        handleFinish();
    }

    private void watchLive(int i, LiveListInfo liveListInfo) {
        LiveshowManager.getInstance().watchLive(i, liveListInfo, null);
    }

    private void initListInfo(Map<Integer, List<LiveListInfo>> map, int i) {
        hideInitLoading();
        List<LiveListInfo> list = map.get(Integer.valueOf(i));
        this.roomListAdapter.setLiveListInfoData(list);
        this.dataMap.put(Integer.valueOf(i), list);
        if (list == null || list.size() == 0) {
            showEmptyView();
        } else {
            hideEmptyView();
        }
    }

    private void initLeftTab() {
        LinearLayoutManager linearLayoutManager;
        RecyclerView recyclerView = (RecyclerView) findViewById(SqResUtils.getId(getContext(), SqR.id.rv_room_left_tab));
        this.mRvLeftTab = recyclerView;
        recyclerView.setItemAnimator(null);
        this.mRvLeftTab.setOverScrollMode(2);
        if (this.mContext.getResources().getConfiguration().orientation == 1) {
            linearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
        } else {
            linearLayoutManager = new LinearLayoutManager(getContext(), 1, false);
        }
        this.mRvLeftTab.setLayoutManager(linearLayoutManager);
        LiveRoomDataManager.getInstance().getLiveMenuData(this);
    }

    private void initLeftTabView(final LiveMenuBean liveMenuBean) {
        hideInitLoading();
        this.mRvLeftTab.setAdapter(new RoomTabAdapter(getContext(), SqResUtils.getLayoutId(getContext(), SqR.layout.sy37_tab_item_room_left), liveMenuBean.getItems(), new RoomTabAdapter.OnBindViewListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$RoomListView$RcmYb4zb-UNO81SVsTprelPosBI
            @Override // com.sqwan.liveshow.huya.adapter.RoomTabAdapter.OnBindViewListener
            public final void onBindView(RoomViewHolder roomViewHolder, int i, boolean z) {
                this.f$0.lambda$initLeftTabView$7$RoomListView(liveMenuBean, roomViewHolder, i, z);
            }
        }, new RoomTabAdapter.OnItemClickListener() { // from class: com.sqwan.liveshow.huya.view.-$$Lambda$RoomListView$xpAQmYptZq-yp88LMzjCbuoMF3s
            @Override // com.sqwan.liveshow.huya.adapter.RoomTabAdapter.OnItemClickListener
            public final void onItemClick(RoomViewHolder roomViewHolder, int i) {
                this.f$0.lambda$initLeftTabView$8$RoomListView(liveMenuBean, roomViewHolder, i);
            }
        }));
    }

    public /* synthetic */ void lambda$initLeftTabView$7$RoomListView(LiveMenuBean liveMenuBean, RoomViewHolder roomViewHolder, int i, boolean z) {
        Shader shader;
        TextView textView = (TextView) roomViewHolder.findViewById(SqResUtils.getId(getContext(), SqR.id.live_show_menu_platform_name_tv));
        ImageView imageView = (ImageView) roomViewHolder.findViewById(SqResUtils.getId(getContext(), SqR.id.live_show_menu_icon_iv));
        imageView.setAdjustViewBounds(true);
        Typeface typeface = getTypeface();
        if (typeface != null) {
            textView.setTypeface(typeface);
        }
        Glide.with(getContext()).load(liveMenuBean.getItems().get(i).getIcon_url()).into(imageView);
        Shader shader2 = null;
        String platform_name = liveMenuBean.getItems().get(i).getPlatform_name();
        try {
            if (z) {
                imageView.setImageTintList(ColorStateList.valueOf(SkinHelper.getColorValue(this.mContext, SqR.color.sy37_tab_item_room_left_live_show_menu_icon_iv_select_color, this.mContext.getResources().getConfiguration().orientation == 1 ? Color.parseColor("#334060") : Color.parseColor("#857659"))));
                if (this.selectShader == null) {
                    this.selectShader = new LinearGradient(0.0f, 0.0f, 0.0f, textView.getLineHeight(), SkinHelper.getColorValue(this.mContext, SqR.color.sy37_tab_item_room_left_live_show_menu_platform_name_tv_color_select_start, this.mContext.getResources().getConfiguration().orientation == 1 ? Color.parseColor("#354A6D") : Color.parseColor("#746750")), SkinHelper.getColorValue(this.mContext, SqR.color.sy37_tab_item_room_left_live_show_menu_platform_name_tv_color_select_end, this.mContext.getResources().getConfiguration().orientation == 1 ? Color.parseColor("#354A6D") : Color.parseColor("#857659")), Shader.TileMode.REPEAT);
                }
                shader = this.selectShader;
            } else {
                imageView.setImageTintList(ColorStateList.valueOf(SkinHelper.getColorValue(this.mContext, SqR.color.sy37_tab_item_room_left_live_show_menu_icon_iv_not_select_color, this.mContext.getResources().getConfiguration().orientation == 1 ? Color.parseColor("#64728b") : Color.parseColor("#474b76"))));
                if (this.unSelectShader == null) {
                    this.unSelectShader = new LinearGradient(0.0f, 0.0f, 0.0f, textView.getLineHeight(), SkinHelper.getColorValue(this.mContext, SqR.color.sy37_tab_item_room_left_live_show_menu_platform_name_tv_color_not_select_start, this.mContext.getResources().getConfiguration().orientation == 1 ? Color.parseColor("#6D7E97") : Color.parseColor("#474b76")), SkinHelper.getColorValue(this.mContext, SqR.color.sy37_tab_item_room_left_live_show_menu_platform_name_tv_color_not_select_end, this.mContext.getResources().getConfiguration().orientation == 1 ? Color.parseColor("#6D7E97") : Color.parseColor("#4b507e")), Shader.TileMode.REPEAT);
                }
                shader = this.unSelectShader;
            }
            shader2 = shader;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (shader2 != null) {
            textView.getPaint().setShader(shader2);
        }
        textView.setText(platform_name);
        LogUtil.i("onBindView: 当前的position:" + i + "\t 当前选中的:" + z);
    }

    public /* synthetic */ void lambda$initLeftTabView$8$RoomListView(LiveMenuBean liveMenuBean, RoomViewHolder roomViewHolder, int i) {
        LogUtil.i("onItemClick: 点击了:" + i);
        int platform_id = liveMenuBean.getItems().get(i).getPlatform_id();
        this.mPlatformId = platform_id;
        this.roomListAdapter.setLiveListInfoData(this.dataMap.get(Integer.valueOf(platform_id)));
        if (!LiveRoomDataManager.getInstance().isHasplatformData(platform_id)) {
            loadAnchorMessage(platform_id, liveMenuBean.getItems().get(i).getWhitelist());
            return;
        }
        List<LiveListInfo> platformData = LiveRoomDataManager.getInstance().getPlatformData(platform_id);
        if (platformData != null && platformData.size() > 0) {
            hideEmptyView();
        } else {
            showEmptyView();
        }
    }

    private Typeface getTypeface() {
        Typeface typeface = this.mFromAsset;
        if (typeface != null) {
            return typeface;
        }
        try {
            Typeface typefaceCreateFromAsset = Typeface.createFromAsset(getContext().getResources().getAssets(), "fonts/fzcsf_37.TTF");
            this.mFromAsset = typefaceCreateFromAsset;
            return typefaceCreateFromAsset;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadAnchorMessage(int i, List<String> list) {
        if (i == 0) {
            LiveRoomDataManager.getInstance().getRecommendedAnchorData(this);
        } else {
            if (i != 1) {
                return;
            }
            LiveRoomDataManager.getInstance().getHuyaAnchorLiveInfo(list, this, 1);
        }
    }

    private void handleFinish() {
        LiveshowManager.getInstance().leaveLiveshowRoomWrapper(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Log.d(TAG, "dispatchKeyEvent: ");
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
            handleFinish();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // com.sqwan.liveshow.huya.LiveRoomDataManager.RequestListener
    public void onLiveMenuDataCallback(LiveMenuBean liveMenuBean) {
        initLeftTabView(liveMenuBean);
    }

    @Override // com.sqwan.liveshow.huya.LiveRoomDataManager.RequestListener
    public void onHuyaAnchorInfoCallback(Map<Integer, List<LiveListInfo>> map) {
        initListInfo(map, 1);
    }

    @Override // com.sqwan.liveshow.huya.LiveRoomDataManager.RequestListener
    public void onRecommendedAnchorDataCallback(Map<Integer, List<LiveListInfo>> map) {
        initListInfo(map, 0);
    }

    @Override // com.sqwan.liveshow.huya.LiveRoomDataManager.RequestListener
    public void onFailure(int i, String str) {
        LogUtil.e(TAG, "获取数据失败:" + str);
        hideInitLoading();
        showEmptyView();
    }

    public void hide() {
        ViewUtils.gone(this);
        update();
    }
}
