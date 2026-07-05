package com.huya.berry.sdklive.living.messageboard;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.widget.TextView;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.sdklive.living.messageboard.entity.ViewerMessage;
import com.huya.berry.sdklive.living.messageboard.ui.ChatListBrowser;
import com.huya.berry.sdklive.living.messageboard.ui.ChatSigTextView;
import com.huya.berry.sdklive.living.messageboard.ui.MessageContainer;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MessageToolContainer extends MessageContainer {
    public static final String TAG = "MessageBoardFragment";
    private ChatSigTextView mSigTextView;

    public MessageToolContainer(Context context) {
        super(context);
    }

    public MessageToolContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MessageToolContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setSigTextView(ChatSigTextView chatSigTextView) {
        this.mSigTextView = chatSigTextView;
    }

    @Override // com.huya.berry.sdklive.living.messageboard.ui.MessageContainer, com.duowan.live.common.framework.BaseViewContainer
    protected void init() {
        BaseHandlerApp.inflate(getContext(), ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_pub_tool_message_list), this, true);
        this.mChannelChatBrowser = (ChatListBrowser) findViewById(ResourceUtil.getIdResIDByName(SqR.id.channel_chat_browser));
        this.mChannelChatNew = (TextView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.channel_chat_new_msg));
        this.mChannelChatBrowser.setSmall(true);
        initLister();
    }

    @Override // com.huya.berry.sdklive.living.messageboard.ui.MessageContainer, com.duowan.live.common.framework.BaseViewContainer
    public MessagePresenter createPresenter() {
        MessagePresenter messagePresenter = new MessagePresenter(this);
        messagePresenter.setIsTool(true);
        return messagePresenter;
    }

    @Override // com.huya.berry.sdklive.living.messageboard.ui.MessageContainer, com.duowan.live.common.framework.BaseViewContainer
    protected void onDestroy() {
        this.mSigTextView = null;
        super.onDestroy();
    }

    @Override // com.huya.berry.sdklive.living.messageboard.ui.MessageContainer, com.huya.berry.sdklive.living.messageboard.helper.MessageInterface
    public void pubMessage(ViewerMessage.Message message) {
        if (isPause()) {
            addToCache(message);
            return;
        }
        this.mChannelChatBrowser.insertMessage(message);
        ChatSigTextView chatSigTextView = this.mSigTextView;
        if (chatSigTextView != null) {
            chatSigTextView.insertMessage(message);
        }
    }

    @Override // com.huya.berry.sdklive.living.messageboard.ui.MessageContainer, com.huya.berry.sdklive.living.messageboard.helper.MessageInterface
    public Context getMContext() {
        return getContext();
    }

    @Override // com.huya.berry.sdklive.living.messageboard.ui.MessageContainer
    protected void addToMessageFromCache() {
        if (this.mCacheQueue != null && this.mCacheQueue.isEmpty()) {
            return;
        }
        while (true) {
            if ((this.mCacheQueue != null ? this.mCacheQueue.peek() : null) == null) {
                return;
            }
            Object objPoll = this.mCacheQueue.poll();
            if (objPoll != null) {
                if (objPoll instanceof Pair) {
                    Pair pair = (Pair) objPoll;
                    this.mChannelChatBrowser.insertOwnMessage((String) pair.first, ((Integer) pair.second).intValue());
                    ChatSigTextView chatSigTextView = this.mSigTextView;
                    if (chatSigTextView != null) {
                        chatSigTextView.insertOwnMessage((String) pair.first, ((Integer) pair.second).intValue());
                    }
                } else if (objPoll instanceof ViewerMessage.Message) {
                    ViewerMessage.Message message = (ViewerMessage.Message) objPoll;
                    this.mChannelChatBrowser.insertMessage(message);
                    ChatSigTextView chatSigTextView2 = this.mSigTextView;
                    if (chatSigTextView2 != null) {
                        chatSigTextView2.insertMessage(message);
                    }
                }
            }
        }
    }
}
