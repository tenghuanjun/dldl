package com.huya.berry.sdklive.living.messageboard.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import com.duowan.live.common.framework.BasePresenter;
import com.duowan.live.common.framework.BaseViewContainer;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.sdklive.living.messageboard.BaseHandlerApp;
import com.huya.berry.sdklive.living.messageboard.MessagePresenter;
import com.huya.berry.sdklive.living.messageboard.entity.ViewerMessage;
import com.huya.berry.sdklive.living.messageboard.helper.ChatBinder;
import com.huya.berry.sdklive.living.messageboard.helper.MessageInterface;
import com.sqwan.liveshow.huya.SqR;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MessageContainer extends BaseViewContainer<BasePresenter> implements MessageInterface {
    protected static final int KMaxCache = 20;
    public static final String TAG = "MessageContainer";
    protected Queue<Object> mCacheQueue;
    protected ChatListBrowser mChannelChatBrowser;
    protected TextView mChannelChatNew;
    protected View mEmpty;

    public Context getMContext() {
        return null;
    }

    protected void reportNew() {
    }

    public MessageContainer(Context context) {
        super(context);
        this.mCacheQueue = new LinkedList();
    }

    public MessageContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCacheQueue = new LinkedList();
    }

    public MessageContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCacheQueue = new LinkedList();
    }

    protected int getNewRes() {
        return ResourceUtil.getStringResIDByName(SqR.string.hyberry_text_new_msg);
    }

    @Override // com.duowan.live.common.framework.BaseViewContainer
    protected void init() {
        if (isInEditMode()) {
            return;
        }
        View viewInflate = BaseHandlerApp.inflate(getContext(), ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_pub_live_message_board), this, true);
        this.mChannelChatBrowser = (ChatListBrowser) viewInflate.findViewById(ResourceUtil.getIdResIDByName(SqR.id.channel_chat_browser));
        this.mChannelChatNew = (TextView) viewInflate.findViewById(ResourceUtil.getIdResIDByName(SqR.id.channel_chat_new_msg));
        this.mEmpty = viewInflate.findViewById(ResourceUtil.getIdResIDByName(SqR.id.empty_ly));
        initLister();
    }

    protected void initLister() {
        this.mChannelChatBrowser.setChatListNewMessgeCallBack(new ChatBinder.ChatListNewMessgeCallBack() { // from class: com.huya.berry.sdklive.living.messageboard.ui.MessageContainer.1
            @Override // com.huya.berry.sdklive.living.messageboard.helper.ChatBinder.ChatListNewMessgeCallBack
            public void updateNewMsgView(boolean z, int i) {
                if (MessageContainer.this.mChannelChatNew == null) {
                    return;
                }
                if (i <= 0) {
                    z = false;
                    i = 0;
                }
                MessageContainer.this.mChannelChatNew.setText(MessageContainer.this.getContext().getString(MessageContainer.this.getNewRes(), Integer.valueOf(i)));
                MessageContainer.this.mChannelChatNew.setVisibility(z ? 0 : 8);
            }
        });
        this.mChannelChatNew.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdklive.living.messageboard.ui.MessageContainer.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MessageContainer.this.mChannelChatBrowser == null) {
                    return;
                }
                MessageContainer.this.mChannelChatBrowser.setIsBottom(true);
                MessageContainer.this.mChannelChatNew.setVisibility(8);
                MessageContainer.this.reportNew();
            }
        });
    }

    @Override // com.duowan.live.common.framework.BaseViewContainer
    public BasePresenter createPresenter() {
        return new MessagePresenter(this);
    }

    @Override // com.duowan.live.common.framework.BaseViewContainer
    protected void onDestroy() {
        this.mChannelChatBrowser = null;
        this.mCacheQueue.clear();
        this.mCacheQueue = null;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (isPause()) {
            return;
        }
        updateFromCache();
    }

    @Override // com.huya.berry.sdklive.living.messageboard.helper.MessageInterface
    public void clear() {
        this.mChannelChatBrowser.clear();
        initEmpty();
    }

    public void pubMessage(ViewerMessage.Message message) {
        if (message instanceof ViewerMessage.PropMessage) {
            return;
        }
        if (isPause()) {
            addToCache(message);
        } else {
            insertMessage(message);
        }
    }

    protected void insertMessage(ViewerMessage.Message message) {
        ChatListBrowser chatListBrowser = this.mChannelChatBrowser;
        if (chatListBrowser == null) {
            return;
        }
        chatListBrowser.insertMessage(message);
        initEmpty();
    }

    private void initEmpty() {
        if (this.mEmpty == null) {
            return;
        }
        if (this.mChannelChatBrowser.getCount() <= 0) {
            this.mEmpty.setVisibility(0);
        } else if (this.mEmpty.getVisibility() == 0) {
            this.mEmpty.setVisibility(8);
        }
    }

    protected boolean isPause() {
        return this.mPause;
    }

    protected void addToCache(Object obj) {
        if (this.mCacheQueue.size() > 20) {
            for (int i = 0; i < 10; i++) {
                this.mCacheQueue.poll();
            }
        }
        this.mCacheQueue.offer(obj);
    }

    private void updateFromCache() {
        BaseHandlerApp.runAsync(new Runnable() { // from class: com.huya.berry.sdklive.living.messageboard.ui.MessageContainer.3
            @Override // java.lang.Runnable
            public void run() {
                MessageContainer.this.addToMessageFromCache();
            }
        });
    }

    protected void addToMessageFromCache() {
        Queue<Object> queue = this.mCacheQueue;
        if (queue != null && queue.isEmpty()) {
            return;
        }
        while (true) {
            Queue<Object> queue2 = this.mCacheQueue;
            if ((queue2 != null ? queue2.peek() : null) == null) {
                return;
            }
            Object objPoll = this.mCacheQueue.poll();
            if (objPoll != null) {
                if (objPoll instanceof Pair) {
                    Pair pair = (Pair) objPoll;
                    this.mChannelChatBrowser.insertOwnMessage((String) pair.first, ((Integer) pair.second).intValue());
                } else if (objPoll instanceof ViewerMessage.Message) {
                    insertMessage((ViewerMessage.Message) objPoll);
                }
            }
        }
    }

    @Override // com.duowan.live.common.framework.BaseViewContainer, com.duowan.live.common.framework.ILifeCycle
    public void onResume() {
        super.onResume();
        updateFromCache();
    }
}
