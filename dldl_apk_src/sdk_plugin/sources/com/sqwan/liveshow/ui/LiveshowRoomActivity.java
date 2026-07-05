package com.sqwan.liveshow.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.plugin.standard.BaseActivity;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sqwan.common.util.ClickUtils;
import com.sqwan.common.util.KeyBoardUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.common.util.WindowManagerUtil;
import com.sqwan.common.util.task.Task;
import com.sqwan.liveshow.LiveshowManager;
import com.sqwan.liveshow.SqR;
import com.sqwan.liveshow.bean.LiveShowIMBean;
import com.sqwan.liveshow.bean.LiveshowChannelInfo;
import com.sqwan.liveshow.common.ILiveshowEventWrapper;
import com.sqwan.liveshow.common.ILiveshowEventWrapperAdapter;
import com.sqwan.liveshow.common.ILiveshowViewEvent;
import com.sqwan.liveshow.common.LiveshowViewEventAdapter;
import com.sqwan.liveshow.im.ILiveshowImCallback;
import com.sqwan.liveshow.im.LiveshowImManager;
import com.sqwan.supportview.LimitedEditText;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import com.youme.imsdk.callback.YIMEventCallback;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowRoomActivity extends BaseActivity implements KeyBoardUtils.OnSoftKeyBoardChangeListener {
    private static final int CHAT_LIMITDURATION = 5000;
    private static final int CHAT_NUMBERFILTER = 200;
    private View clickView;
    private LimitedEditText etInputMessage;
    private RoundedImageView ivAvatar;
    private ImageView ivImRoomHome;
    private LiveshowIMAdapter liveshowIMAdapter;
    private LinearLayout llContainer;
    private LinearLayout ll_input_container;
    private LiveShowIMBean mliveShowIMBean;
    private RecyclerView rvChatMessage;
    private TextView tvAnchorName;
    private TextView tvImRoomSend;
    private TextView tvOnlineNumber;
    private TextView tvRadioStationName;
    private final String TAG = getClass().getSimpleName();
    private Task taskRecordInput = Task.create();
    private KeyBoardUtils keyBoardUtils = new KeyBoardUtils();
    private boolean isFirstResume = true;
    private ILiveshowViewEvent iLiveshowViewEvent = new LiveshowViewEventAdapter() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.1
        @Override // com.sqwan.liveshow.common.LiveshowViewEventAdapter, com.sqwan.liveshow.common.ILiveshowViewEvent
        public void checkEmptyRoom() {
        }

        @Override // com.sqwan.liveshow.common.LiveshowViewEventAdapter, com.sqwan.liveshow.common.ILiveshowViewEvent
        public void onReleaseLiveShowView() {
        }

        @Override // com.sqwan.liveshow.common.LiveshowViewEventAdapter, com.sqwan.liveshow.common.ILiveshowViewEvent
        public void onShowLiveShowView() {
        }

        @Override // com.sqwan.liveshow.common.LiveshowViewEventAdapter, com.sqwan.liveshow.common.UpdateChannelInfoCallback
        public void update(final LiveshowChannelInfo.ChannelsBean channelsBean) {
            Task.post(new Runnable() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.1.1
                @Override // java.lang.Runnable
                public void run() {
                    LiveshowRoomActivity.this.ivAvatar.setImageUrl(channelsBean.getAnchor_avatar());
                    LiveshowRoomActivity.this.tvAnchorName.setText(channelsBean.getAnchor_name());
                }
            });
        }
    };
    private ILiveshowImCallback iLiveshowImCallback = new ILiveshowImCallback() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.2
        @Override // com.sqwan.liveshow.im.ILiveshowImCallback
        public void leaveRoom(boolean z) {
        }

        @Override // com.sqwan.liveshow.im.YouMeIMCallbackWrapper
        public void onRecvChatMsg(LiveShowIMBean.UserImBean userImBean) {
            LiveshowRoomActivity.this.postMsg(userImBean);
        }

        @Override // com.sqwan.liveshow.im.ILiveshowImCallback
        public void joinRoom(boolean z) {
            Task.post(new Runnable() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.2.1
                @Override // java.lang.Runnable
                public void run() {
                    LiveshowRoomActivity.this.tvOnlineNumber.setText(LiveshowManager.getInstance().getOnlineCount() + "");
                }
            });
        }
    };
    private ILiveshowEventWrapper iLiveshowEventWrapper = new ILiveshowEventWrapperAdapter() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.3
        @Override // com.sqwan.liveshow.common.ILiveshowEventWrapperAdapter, com.sqwan.liveshow.common.ILiveshowEventWrapper
        public void onMemberChange(final int i) {
            Task.post(new Runnable() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.3.1
                @Override // java.lang.Runnable
                public void run() {
                    LiveshowRoomActivity.this.tvOnlineNumber.setText(i + "");
                }
            });
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void postMsg(LiveShowIMBean.UserImBean userImBean) {
        this.liveshowIMAdapter.updateDataDirect(userImBean);
        this.rvChatMessage.scrollToPosition(this.liveshowIMAdapter.getItemCount() - 1);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getContext().getTheme().applyStyle(SqResUtils.getStyleId(getContext(), "chatRoomDialog"), true);
        setContentView(SqResUtils.getLayoutId(getContext(), SqR.layout.sy37_base_activity_liveshow_im_room));
        getWindow().setSoftInputMode(19);
        getWindow().setLayout(-1, -1);
        WindowManagerUtil.handleNotch(getContext(), true);
        LiveshowImManager.getInstance().joinRoom(getContext(), LiveshowManager.getInstance().getUserId(), LiveshowManager.getInstance().getRoomId(), this.iLiveshowImCallback);
        LiveshowManager.getInstance().register(this.iLiveshowEventWrapper);
        initData();
        initView();
        LiveshowManager.getInstance().addiLiveshowViewEvent(this.iLiveshowViewEvent);
    }

    public void onResume() {
        super.onResume();
        if (this.isFirstResume) {
            WindowManagerUtil.handleHideSystemUI(getContext());
            this.keyBoardUtils.addListener(getContext(), this);
            this.isFirstResume = false;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        LiveshowImManager.getInstance().leaveRoom();
        LiveshowManager.getInstance().unregister(this.iLiveshowEventWrapper);
        this.keyBoardUtils.removeListener();
        this.taskRecordInput.stop();
        this.liveshowIMAdapter.release();
        LiveshowManager.getInstance().removeLiveshowViewEvent(this.iLiveshowViewEvent);
    }

    private void initData() {
        LiveShowIMBean liveShowIMBean = new LiveShowIMBean();
        LiveshowChannelInfo.ChannelsBean channelInfo = LiveshowManager.getInstance().getChannelInfo();
        if (channelInfo != null) {
            liveShowIMBean.setRadioStudioName(channelInfo.getCname());
            LiveShowIMBean.AnchorMessageBean anchorMessageBean = new LiveShowIMBean.AnchorMessageBean();
            anchorMessageBean.setAnchorName(channelInfo.getAnchor_name());
            anchorMessageBean.setAvatarUrl(channelInfo.getAnchor_avatar());
            anchorMessageBean.setOnlineNumber(LiveshowManager.getInstance().getOnlineCount());
            liveShowIMBean.setAnchorMessageBean(anchorMessageBean);
        }
        this.mliveShowIMBean = liveShowIMBean;
    }

    private void initView() {
        this.clickView = findViewById(SqResUtils.getId(getContext(), SqR.id.clickView));
        this.llContainer = (LinearLayout) findViewById(SqResUtils.getId(getContext(), SqR.id.llimroomcontainer));
        this.ivAvatar = (RoundedImageView) findViewById(SqResUtils.getId(getContext(), SqR.id.iv_anchor_avatar));
        this.tvAnchorName = (TextView) findViewById(SqResUtils.getId(getContext(), "tv_anchor_name"));
        this.tvOnlineNumber = (TextView) findViewById(SqResUtils.getId(getContext(), SqR.id.tv_online_numbers));
        this.tvRadioStationName = (TextView) findViewById(SqResUtils.getId(getContext(), SqR.id.tv_radio_studio_name));
        this.rvChatMessage = (RecyclerView) findViewById(SqResUtils.getId(getContext(), "rv_im_chat_message"));
        this.etInputMessage = (LimitedEditText) findViewById(SqResUtils.getId(getContext(), "et_input_message"));
        this.tvImRoomSend = (TextView) findViewById(SqResUtils.getId(getContext(), SqR.id.tv_im_room_send));
        this.ivImRoomHome = (ImageView) findViewById(SqResUtils.getId(getContext(), SqR.id.iv_im_room_home));
        this.ll_input_container = (LinearLayout) findViewById(SqResUtils.getId(getContext(), "ll_input_container"));
        this.ivAvatar.setCornerRadius(TypedValue.applyDimension(1, 18.0f, getContext().getResources().getDisplayMetrics()));
        this.ivImRoomHome.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LiveshowRoomActivity.this.onBackPressedHandle();
            }
        });
        this.clickView.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LiveshowRoomActivity.this.keyBoardUtils.hideKeyboard(LiveshowRoomActivity.this.getContext());
            }
        });
        this.liveshowIMAdapter = new LiveshowIMAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rvChatMessage.setLayoutManager(linearLayoutManager);
        this.rvChatMessage.setAdapter(this.liveshowIMAdapter);
        LiveShowIMBean.AnchorMessageBean anchorMessageBean = this.mliveShowIMBean.getAnchorMessageBean();
        if (anchorMessageBean != null) {
            this.ivAvatar.setImageUrl(anchorMessageBean.getAvatarUrl());
            this.tvAnchorName.setText(anchorMessageBean.getAnchorName());
            this.tvOnlineNumber.setText(String.valueOf(anchorMessageBean.getOnlineNumber()));
        }
        this.tvRadioStationName.setText(this.mliveShowIMBean.getRadioStudioName());
        ViewGroup.LayoutParams layoutParams = this.tvRadioStationName.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = StatusBarUtil.getStatusBarHeight(getContext());
            this.tvRadioStationName.setLayoutParams(layoutParams);
        }
        this.etInputMessage.setNumberFilter(200, new LimitedEditText.TextWatcherAdapter() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.6
            @Override // com.sqwan.supportview.LimitedEditText.TextWatcherAdapter, android.text.TextWatcher
            public void afterTextChanged(final Editable editable) {
                super.afterTextChanged(editable);
                if (LiveshowImManager.getInstance().isRecordInput) {
                    LiveshowRoomActivity.this.taskRecordInput.oneShot(1000L, new Runnable() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            LiveshowImManager.getInstance().setRecordInput(editable.toString());
                        }
                    });
                }
            }

            @Override // com.sqwan.supportview.LimitedEditText.TextWatcherAdapter
            public void maxCharactersCallback() {
                ToastUtil.showToast("内容过长~");
            }

            @Override // com.sqwan.supportview.LimitedEditText.TextWatcherAdapter
            public void emptyChange(Boolean bool) {
                LiveshowRoomActivity.this.tvImRoomSend.setSelected(!bool.booleanValue());
            }
        });
        this.llContainer.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        ClickUtils.stePreviewClickListener(this.tvImRoomSend, OAIDHelper.TIMEOUT, "说话太快啦~~", new View.OnClickListener() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(LiveshowRoomActivity.this.etInputMessage.getText())) {
                    return;
                }
                LiveshowRoomActivity.this.sendMsg();
            }
        });
        if (LiveshowImManager.getInstance().isRecordInput) {
            String recordInput = LiveshowImManager.getInstance().getRecordInput();
            if (TextUtils.isEmpty(recordInput)) {
                return;
            }
            this.etInputMessage.setText(recordInput);
        }
    }

    private void test() {
        LiveshowImManager.getInstance().test();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsg() {
        LiveshowImManager.getInstance().sendTextMessage(handleContent(this.etInputMessage.getText().toString()), new YIMEventCallback.ResultCallback<String>() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.9
            @Override // com.youme.imsdk.callback.YIMEventCallback.ResultCallback
            public void onSuccess(String str) {
                LogUtil.d("发送消息成功！！");
                LiveShowIMBean.UserImBean userImBean = new LiveShowIMBean.UserImBean();
                userImBean.setUserName(LiveshowImManager.getInstance().getUsernick());
                userImBean.setUserChatContent(str);
                LiveshowRoomActivity.this.liveshowIMAdapter.updateDataDirect(userImBean);
                LiveshowRoomActivity.this.rvChatMessage.scrollToPosition(LiveshowRoomActivity.this.liveshowIMAdapter.getItemCount() - 1);
                LiveshowRoomActivity.this.etInputMessage.setText("");
                LiveshowImManager.getInstance().clearRecordInput();
                LiveshowRoomActivity.this.keyBoardUtils.hideKeyboard(LiveshowRoomActivity.this.getContext());
            }

            @Override // com.youme.imsdk.callback.YIMEventCallback.ResultCallback
            public void onFailed(int i, String str) {
                ToastUtil.showToast(str);
                LogUtil.d("发送消息失败，errorCode = " + i + ", msg = " + str);
            }
        });
    }

    private String handleContent(String str) {
        return str.replaceAll("\n+", ShellAdbUtils.COMMAND_LINE_END);
    }

    @Override // com.sqwan.common.util.KeyBoardUtils.OnSoftKeyBoardChangeListener
    public void keyBoardShow(int i) {
        LogUtil.i(this.TAG, "keyBoardShow height " + i);
        ViewUtils.show(this.clickView);
        LinearLayout linearLayout = this.llContainer;
        linearLayout.setPadding(linearLayout.getPaddingLeft(), this.llContainer.getPaddingTop(), this.llContainer.getPaddingRight(), i);
        this.rvChatMessage.scrollToPosition(this.liveshowIMAdapter.getItemCount() - 1);
    }

    @Override // com.sqwan.common.util.KeyBoardUtils.OnSoftKeyBoardChangeListener
    public void keyBoardHide(int i) {
        ViewUtils.gone(this.clickView);
        LogUtil.i(this.TAG, "keyBoardHide height " + i);
        LinearLayout linearLayout = this.llContainer;
        linearLayout.setPadding(linearLayout.getPaddingLeft(), this.llContainer.getPaddingTop(), this.llContainer.getPaddingRight(), 0);
    }

    @Override // com.sqwan.common.util.KeyBoardUtils.OnSoftKeyBoardChangeListener
    public void viewChanged(int i) {
        LogUtil.i(this.TAG, "viewChanged height " + i);
        int paddingBottom = this.llContainer.getPaddingBottom() - i;
        LinearLayout linearLayout = this.llContainer;
        linearLayout.setPadding(linearLayout.getPaddingLeft(), this.llContainer.getPaddingTop(), this.llContainer.getPaddingRight(), paddingBottom);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        onBackPressedHandle();
        return true;
    }

    public void onBackPressedHandle() {
        LiveshowManager.getInstance().minimize(getContext(), new LiveshowManager.MinimizeCallback() { // from class: com.sqwan.liveshow.ui.LiveshowRoomActivity.10
            @Override // com.sqwan.liveshow.LiveshowManager.MinimizeCallback
            public void callbcak(boolean z, boolean z2) {
                if (z2) {
                    LiveshowImManager.getInstance().stopTask();
                } else if (z) {
                    LiveshowImManager.getInstance().stopTask();
                } else {
                    LiveshowImManager.getInstance().startTask();
                }
            }
        });
    }
}
