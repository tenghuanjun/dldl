package com.huya.berry.sdklive.liveTool;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.duowan.auk.util.L;
import com.duowan.live.common.framework.BaseViewContainer;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.TaskExecutor;
import com.huya.berry.sdklive.living.messageboard.BaseHandlerApp;
import com.huya.berry.sdklive.living.messageboard.MessagePresenter;
import com.huya.berry.sdklive.living.messageboard.entity.ViewerMessage;
import com.huya.berry.sdklive.living.messageboard.helper.MessageInterface;
import com.huya.berry.sdklive.living.messageboard.ui.ChatSigTextView;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MessagePreviewContainer extends BaseViewContainer<MessagePresenter> implements MessageInterface {
    private static final long ANIM_DURATION = 200;
    private static final int HIDE_DELAY_MILLIS = 5000;
    private static final String TAG = MessagePreviewContainer.class.getSimpleName();
    private LinearLayout mChatLineView;
    private int mHideDuration;
    private Runnable mHideMessagePreviewTask;
    private boolean mIsPreviewShowing;
    private ChatSigTextView mSigTextView;

    @Override // com.huya.berry.sdklive.living.messageboard.helper.MessageInterface
    public void clear() {
    }

    public MessagePreviewContainer(Context context) {
        super(context);
        this.mHideMessagePreviewTask = new Runnable() { // from class: com.huya.berry.sdklive.liveTool.MessagePreviewContainer.1
            @Override // java.lang.Runnable
            public void run() {
                MessagePreviewContainer messagePreviewContainer = MessagePreviewContainer.this;
                messagePreviewContainer.showPreviewAnimator(messagePreviewContainer.mChatLineView, 0, -MessageToolView.VIEW_WIDTH, MessagePreviewContainer.ANIM_DURATION);
                MessagePreviewContainer.this.mIsPreviewShowing = false;
            }
        };
    }

    public MessagePreviewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHideMessagePreviewTask = new Runnable() { // from class: com.huya.berry.sdklive.liveTool.MessagePreviewContainer.1
            @Override // java.lang.Runnable
            public void run() {
                MessagePreviewContainer messagePreviewContainer = MessagePreviewContainer.this;
                messagePreviewContainer.showPreviewAnimator(messagePreviewContainer.mChatLineView, 0, -MessageToolView.VIEW_WIDTH, MessagePreviewContainer.ANIM_DURATION);
                MessagePreviewContainer.this.mIsPreviewShowing = false;
            }
        };
    }

    public MessagePreviewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHideMessagePreviewTask = new Runnable() { // from class: com.huya.berry.sdklive.liveTool.MessagePreviewContainer.1
            @Override // java.lang.Runnable
            public void run() {
                MessagePreviewContainer messagePreviewContainer = MessagePreviewContainer.this;
                messagePreviewContainer.showPreviewAnimator(messagePreviewContainer.mChatLineView, 0, -MessageToolView.VIEW_WIDTH, MessagePreviewContainer.ANIM_DURATION);
                MessagePreviewContainer.this.mIsPreviewShowing = false;
            }
        };
    }

    @Override // com.duowan.live.common.framework.BaseViewContainer
    protected void init() {
        BaseHandlerApp.inflate(getContext(), ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_pub_tool_message_preview), this, true);
        this.mChatLineView = (LinearLayout) findViewById(ResourceUtil.getIdResIDByName(SqR.id.chat_line));
        this.mSigTextView = (ChatSigTextView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.sig_text_view));
        onResume();
    }

    @Override // com.duowan.live.common.framework.BaseViewContainer
    public MessagePresenter createPresenter() {
        return new MessagePresenter(this);
    }

    @Override // com.duowan.live.common.framework.BaseViewContainer
    protected void onDestroy() {
        onStop();
        this.mChatLineView = null;
        this.mSigTextView = null;
    }

    @Override // com.huya.berry.sdklive.living.messageboard.helper.MessageInterface
    public void pubMessage(ViewerMessage.Message message) {
        ChatSigTextView chatSigTextView = this.mSigTextView;
        if (chatSigTextView != null) {
            chatSigTextView.insertMessage(message);
            int scrollDuration = this.mSigTextView.getScrollDuration();
            L.info(TAG, "duration:" + scrollDuration);
            if (scrollDuration == 0) {
                this.mHideDuration = 5000;
            } else {
                this.mHideDuration = scrollDuration;
            }
        }
    }

    @Override // com.huya.berry.sdklive.living.messageboard.helper.MessageInterface
    public Context getMContext() {
        return getContext();
    }

    public void showPreview(boolean z) {
        L.info(MessageToolView.TAG, "showPreview:" + z);
        if (z) {
            if (!this.mIsPreviewShowing) {
                showPreviewAnimator(this.mChatLineView, -MessageToolView.VIEW_WIDTH, 0, ANIM_DURATION);
            }
            this.mIsPreviewShowing = true;
            autoHide();
            return;
        }
        if (this.mIsPreviewShowing) {
            TaskExecutor.uiHandler().removeCallbacks(this.mHideMessagePreviewTask);
            TaskExecutor.uiHandler().post(this.mHideMessagePreviewTask);
            this.mIsPreviewShowing = false;
        }
    }

    public boolean getIsPreviewShowing() {
        return this.mIsPreviewShowing;
    }

    private void autoHide() {
        TaskExecutor.uiHandler().removeCallbacks(this.mHideMessagePreviewTask);
        TaskExecutor.uiHandler().postDelayed(this.mHideMessagePreviewTask, this.mHideDuration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animator showPreviewAnimator(View view, int i, int i2, long j) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "translationX", i, i2);
        objectAnimatorOfFloat.setDuration(j);
        objectAnimatorOfFloat.start();
        return objectAnimatorOfFloat;
    }
}
