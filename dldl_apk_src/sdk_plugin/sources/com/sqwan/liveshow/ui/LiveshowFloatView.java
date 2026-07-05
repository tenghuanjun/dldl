package com.sqwan.liveshow.ui;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sqwan.common.util.ActivityLifeCycleUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.common.util.task.Task;
import com.sqwan.liveshow.LiveshowManager;
import com.sqwan.liveshow.SqR;
import com.sqwan.liveshow.bean.LiveshowChannelInfo;
import com.sqwan.liveshow.common.ILiveshowEventWrapper;
import com.sqwan.liveshow.common.ILiveshowEventWrapperAdapter;
import com.sqwan.liveshow.common.ILiveshowViewEvent;
import com.sy37sdk.account.floatview.CheckSystemUiViewBase;
import com.sy37sdk.account.floatview.DragViewLayout;
import com.sy37sdk.account.floatview.FloatViewUtils;
import com.sy37sdk.account.floatview.FloatWindow;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowFloatView extends DragViewLayout {
    private ActivityLifeCycleUtils.AppVisibilityCallbackAdapter appVisibilityCallback;
    public int dx;
    private FloatViewStatu floatViewStatu;
    private ILiveshowEventWrapper iLiveshowEventWrapper;
    private ILiveshowViewEvent iLiveshowViewEvent;
    public LiveshowFloatMaskView liveshowFloatMaskView;
    private ImageView syiv_playstatu;
    private ViewGroup syll_playclose;
    private ViewGroup syll_player_drag;
    private ViewGroup syll_player_edge;
    private ViewGroup syll_player_expand;
    private ViewGroup syll_playstatu;
    private TextView sytv_anchor;
    boolean toReverz;

    enum FloatViewStatu {
        Drag,
        Edge,
        Expand
    }

    @Override // com.sy37sdk.account.floatview.DragViewLayout
    public boolean isCheckRecordPos() {
        return false;
    }

    @Override // com.sy37sdk.account.floatview.DragViewLayout
    public boolean isCheckShowCompelete() {
        return false;
    }

    @Override // com.sy37sdk.account.floatview.DragViewLayout
    public boolean isCheckStayEdge() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goChatRoom() {
        Intent intent = new Intent(this.mContext, (Class<?>) LiveshowRoomActivity.class);
        intent.setFlags(805306368);
        intent.putExtra("Ex", true);
        this.mContext.startActivity(intent);
    }

    public LiveshowFloatView(Context context) {
        super(context);
        this.floatViewStatu = FloatViewStatu.Edge;
        View.inflate(context, SqResUtils.getLayoutId(context, SqR.layout.sy37_base_liveshow_floatview), this);
        this.syll_player_expand = (ViewGroup) findViewById(SqResUtils.getId(context, SqR.id.syll_player_expand));
        this.syll_player_edge = (ViewGroup) findViewById(SqResUtils.getId(context, SqR.id.syll_player_edge));
        this.syll_player_drag = (ViewGroup) findViewById(SqResUtils.getId(context, SqR.id.syll_player_drag));
        this.syll_playstatu = (ViewGroup) findViewById(SqResUtils.getId(context, SqR.id.syll_playstatu));
        this.syll_playclose = (ViewGroup) findViewById(SqResUtils.getId(context, SqR.id.syll_playclose));
        this.syiv_playstatu = (ImageView) findViewById(SqResUtils.getId(context, SqR.id.syiv_playstatu));
        this.sytv_anchor = (TextView) findViewById(SqResUtils.getId(context, SqR.id.sytv_anchor));
        this.syll_playclose.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LiveshowManager.getInstance().close(LiveshowFloatView.this.mContext);
            }
        });
        this.syll_playstatu.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LiveshowManager.getInstance().resumeOrPauseChannel();
            }
        });
        this.syll_player_expand.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LiveshowFloatView.this.goChatRoom();
            }
        });
        setFilterDragClickListener(this.syll_player_edge, new FloatWindow.ClickListenerAdapter() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.4
            @Override // com.sy37sdk.account.floatview.FloatWindow.ClickListenerAdapter, com.sy37sdk.account.floatview.FloatWindow.ClickListener
            public void onClick(View view, int i, int i2) {
                if (LiveshowFloatView.this.filterClick(i, i2)) {
                    return;
                }
                LiveshowFloatView.this.updateFloatViewStatu(FloatViewStatu.Expand);
                LiveshowFloatView.this.canDispatchTouchEvent = false;
                LiveshowFloatView.this.liveshowFloatMaskView.show();
            }
        });
        setFilterDragClickListener(this.syll_player_expand, new FloatWindow.ClickListenerAdapter() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.5
            @Override // com.sy37sdk.account.floatview.FloatWindow.ClickListenerAdapter, com.sy37sdk.account.floatview.FloatWindow.ClickListener
            public void onClick(View view, int i, int i2) {
                if (LiveshowFloatView.this.filterClick(i, i2)) {
                }
            }
        });
        setOnDragCallBack(new FloatWindow.OnDragCallBack() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.6
            @Override // com.sy37sdk.account.floatview.FloatWindow.OnDragCallBack
            public void onStayEdge() {
            }

            @Override // com.sy37sdk.account.floatview.FloatWindow.OnDragCallBack
            public void onStartDrag() {
                LiveshowFloatView.this.updateFloatViewStatu(FloatViewStatu.Drag);
            }
        });
        if (this.iLiveshowEventWrapper == null) {
            this.iLiveshowEventWrapper = new ILiveshowEventWrapperAdapter() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.7
                @Override // com.sqwan.liveshow.common.ILiveshowEventWrapperAdapter, com.sqwan.liveshow.common.ILiveshowEventWrapper
                public void othersMicChange(boolean z, String str) {
                }

                @Override // com.sqwan.liveshow.common.ILiveshowEventWrapperAdapter, com.sqwan.liveshow.common.ILiveshowEventWrapper
                public void joinRoomCallback(boolean z) {
                    super.joinRoomCallback(z);
                    if (z) {
                        Task.post(new Runnable() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.7.1
                            @Override // java.lang.Runnable
                            public void run() {
                                LogUtil.i(LiveshowFloatView.this.TAG, "joinRoomCallback ");
                                LiveshowFloatView.this.syiv_playstatu.setSelected(false);
                                LiveshowFloatView.this.sytv_anchor.setText(LiveshowManager.getInstance().getAnchorName());
                                LiveshowFloatView.this.goChatRoom();
                            }
                        });
                    }
                }

                @Override // com.sqwan.liveshow.common.ILiveshowEventWrapperAdapter, com.sqwan.liveshow.common.ILiveshowEventWrapper
                public void channelChange(final boolean z) {
                    Task.post(new Runnable() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.7.2
                        @Override // java.lang.Runnable
                        public void run() {
                            LogUtil.i(LiveshowFloatView.this.TAG, "channelChange " + z);
                            LiveshowFloatView.this.syiv_playstatu.setSelected(z ^ true);
                        }
                    });
                }

                @Override // com.sqwan.liveshow.common.ILiveshowEventWrapperAdapter, com.sqwan.liveshow.common.ILiveshowEventWrapper
                public void onRepeatClickLiveshowIcon() {
                    Task.post(new Runnable() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.7.3
                        @Override // java.lang.Runnable
                        public void run() {
                            LiveshowFloatView.this.goChatRoom();
                        }
                    });
                }
            };
        }
        if (this.iLiveshowViewEvent == null) {
            this.iLiveshowViewEvent = new ILiveshowViewEvent() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.8
                @Override // com.sqwan.liveshow.common.ILiveshowViewEvent
                public void onReleaseLiveShowView() {
                }

                @Override // com.sqwan.liveshow.common.ILiveshowViewEvent
                public void onShowLiveShowView() {
                }

                @Override // com.sqwan.liveshow.common.UpdateChannelInfoCallback
                public void update(LiveshowChannelInfo.ChannelsBean channelsBean) {
                    Task.post(new Runnable() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            LiveshowFloatView.this.sytv_anchor.setText(LiveshowManager.getInstance().getAnchorName());
                        }
                    });
                }

                @Override // com.sqwan.liveshow.common.ILiveshowViewEvent
                public void checkEmptyRoom() {
                    Task.post(new Runnable() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.8.2
                        @Override // java.lang.Runnable
                        public void run() {
                            LiveshowFloatView.this.sytv_anchor.setText("暂无主播开播");
                        }
                    });
                }
            };
            LiveshowManager.getInstance().addiLiveshowViewEvent(this.iLiveshowViewEvent);
        }
        LiveshowFloatMaskView liveshowFloatMaskView = new LiveshowFloatMaskView(context);
        this.liveshowFloatMaskView = liveshowFloatMaskView;
        liveshowFloatMaskView.setLiveshowFloatView(this);
        this.liveshowFloatMaskView.closeCallback = new CheckSystemUiViewBase.CloseCallback() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.9
            @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase.CloseCallback
            public void invoke() {
                LiveshowFloatView.this.canDispatchTouchEvent = true;
                LiveshowFloatView.this.updateFloatViewStatu(FloatViewStatu.Edge);
            }
        };
        LiveshowManager.getInstance().register(this.iLiveshowEventWrapper);
        if (this.appVisibilityCallback == null) {
            this.appVisibilityCallback = new ActivityLifeCycleUtils.AppVisibilityCallbackAdapter() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.10
                @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    if (ActivityLifeCycleUtils.getInstance().equalActivity(activity, LiveshowRoomActivity.class)) {
                        LogUtil.i(LiveshowFloatView.this.TAG, "LiveshowRoomActivity onActivityDestroyed");
                        if (LiveshowFloatView.this.liveshowFloatMaskView != null) {
                            LiveshowFloatView.this.liveshowFloatMaskView.dismiss();
                        }
                    }
                }
            };
            ActivityLifeCycleUtils.getInstance().registerActivityListener(this.appVisibilityCallback);
        }
    }

    public void show() {
        LiveshowFloatMaskView liveshowFloatMaskView = this.liveshowFloatMaskView;
        if (liveshowFloatMaskView != null) {
            liveshowFloatMaskView.show();
        }
        init();
        if (this.config != null) {
            updateY((this.config.regionHeight / 3) * 2);
        }
        addView();
        updateFloatViewStatu(FloatViewStatu.Edge);
    }

    @Override // com.sy37sdk.account.floatview.DragViewLayout
    protected void handleOnAnimationUpdate(ValueAnimator valueAnimator) {
        super.handleOnAnimationUpdate(valueAnimator);
        updateFloatViewStatu(FloatViewStatu.Edge);
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase
    public void release() {
        LiveshowFloatMaskView liveshowFloatMaskView = this.liveshowFloatMaskView;
        if (liveshowFloatMaskView != null) {
            liveshowFloatMaskView.release();
        }
        super.release();
        if (this.iLiveshowViewEvent != null) {
            LiveshowManager.getInstance().removeLiveshowViewEvent(this.iLiveshowViewEvent);
        }
        if (this.iLiveshowEventWrapper != null) {
            LiveshowManager.getInstance().unregister(this.iLiveshowEventWrapper);
        }
        if (this.appVisibilityCallback != null) {
            ActivityLifeCycleUtils.getInstance().unRegisterActivityListener(this.appVisibilityCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goneAll() {
        ViewUtils.gone(this.syll_player_expand);
        ViewUtils.gone(this.syll_player_edge);
        ViewUtils.gone(this.syll_player_drag);
    }

    private boolean isRightExpand() {
        return !this.isLeft && this.floatViewStatu == FloatViewStatu.Expand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFloatViewStatu(final FloatViewStatu floatViewStatu) {
        this.toReverz = false;
        if (!isRightExpand()) {
            goneAll();
        }
        if (floatViewStatu == FloatViewStatu.Edge) {
            this.syll_player_edge.setSelected(this.isLeft);
            if (isRightExpand()) {
                update();
                Task.post(new Runnable() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.11
                    @Override // java.lang.Runnable
                    public void run() {
                        LiveshowFloatView.this.goneAll();
                        ViewUtils.show(LiveshowFloatView.this.syll_player_edge);
                        LiveshowFloatView.this.floatViewStatu = floatViewStatu;
                        LiveshowFloatView.this.update();
                    }
                });
                return;
            }
            ViewUtils.show(this.syll_player_edge);
        } else if (floatViewStatu == FloatViewStatu.Drag) {
            ViewUtils.show(this.syll_player_drag);
        } else if (floatViewStatu == FloatViewStatu.Expand) {
            ViewUtils.show(this.syll_player_expand);
            Task.post(new Runnable() { // from class: com.sqwan.liveshow.ui.LiveshowFloatView.12
                @Override // java.lang.Runnable
                public void run() {
                    LiveshowFloatView.this.syll_player_expand.setSelected(LiveshowFloatView.this.isLeft);
                    if (!LiveshowFloatView.this.isLeft) {
                        LiveshowFloatView.this.update();
                        int width = LiveshowFloatView.this.syll_player_edge.getWidth();
                        int width2 = LiveshowFloatView.this.syll_player_expand.getWidth();
                        LogUtil.i(LiveshowFloatView.this.TAG, String.format("edgeWidth:%d expandWidth:%d", Integer.valueOf(width), Integer.valueOf(width2)));
                        LiveshowFloatView.this.dx = width2 - width;
                        LiveshowFloatView.this.floatLayoutParams.x -= LiveshowFloatView.this.dx;
                        LiveshowFloatView.this.toReverz = true;
                    }
                    LiveshowFloatView.this.syiv_playstatu.setSelected(true ^ LiveshowManager.getInstance().isResume);
                    LiveshowFloatView.this.floatViewStatu = floatViewStatu;
                    LiveshowFloatView.this.update();
                    if (LiveshowFloatView.this.toReverz) {
                        LiveshowFloatView.this.floatLayoutParams.x += LiveshowFloatView.this.dx;
                    }
                }
            });
            return;
        }
        this.floatViewStatu = floatViewStatu;
        update();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean filterClick(int i, int i2) {
        return FloatViewUtils.isTouchPointInView(this.syll_playstatu, i, i2) || FloatViewUtils.isTouchPointInView(this.syll_playclose, i, i2);
    }

    public boolean fixWindowManager(MotionEvent motionEvent) {
        if (!ViewUtils.isShow(this.syll_player_expand)) {
            return false;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (FloatViewUtils.isTouchPointInView(this.syll_playclose, x, y)) {
            LiveshowManager.getInstance().close(this.mContext);
            return true;
        }
        if (FloatViewUtils.isTouchPointInView(this.syll_playstatu, x, y)) {
            LiveshowManager.getInstance().resumeOrPauseChannel();
            return true;
        }
        if (!FloatViewUtils.isTouchPointInView(this.syll_player_expand, x, y)) {
            return false;
        }
        goChatRoom();
        return true;
    }
}
