package com.huya.berry.sdklive.living.messageboard.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.widgets.ComnTextView;
import com.huya.berry.sdklive.living.messageboard.BaseHandlerApp;
import com.huya.berry.sdklive.living.messageboard.entity.ViewerMessage;
import com.huya.berry.sdklive.living.messageboard.helper.ChatBinder;
import com.huya.component.login.LoginProperties;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ChatListBrowser extends ListView {
    private static final int DELETE_MESSAGE_COUNT = 50;
    private static final int MAX_MESSAGE_COUNT = 200;
    private ChatMessageAdapter mAdapter;
    private boolean mIsBottom;
    private boolean mIsNewMsg;
    private boolean mIsTouching;
    private int mListSize;
    private boolean mLockScroll;
    private ChatBinder.ChatListNewMessgeCallBack mMsgCallBack;
    protected boolean mSmall;

    public ChatListBrowser(Context context) {
        super(context);
        this.mSmall = false;
        this.mLockScroll = false;
        this.mIsTouching = false;
        this.mIsBottom = true;
        this.mIsNewMsg = false;
        this.mListSize = 0;
        this.mAdapter = new ChatMessageAdapter();
        init();
    }

    public ChatListBrowser(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSmall = false;
        this.mLockScroll = false;
        this.mIsTouching = false;
        this.mIsBottom = true;
        this.mIsNewMsg = false;
        this.mListSize = 0;
        this.mAdapter = new ChatMessageAdapter();
        init();
    }

    public ChatListBrowser(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSmall = false;
        this.mLockScroll = false;
        this.mIsTouching = false;
        this.mIsBottom = true;
        this.mIsNewMsg = false;
        this.mListSize = 0;
        this.mAdapter = new ChatMessageAdapter();
        init();
    }

    public void setChatListNewMessgeCallBack(ChatBinder.ChatListNewMessgeCallBack chatListNewMessgeCallBack) {
        this.mMsgCallBack = chatListNewMessgeCallBack;
    }

    public void clear() {
        this.mAdapter.clear();
    }

    public void setIsBottom(boolean z) {
        this.mIsBottom = z;
    }

    public void setSmall(boolean z) {
        this.mSmall = z;
        ChatMessageAdapter chatMessageAdapter = new ChatMessageAdapter();
        this.mAdapter = chatMessageAdapter;
        setAdapter((ListAdapter) chatMessageAdapter);
    }

    public void insertOwnMessage(String str, int i) {
        insertMessage(new ViewerMessage.ChatMessage(null, "", System.currentTimeMillis(), str, true, i, false, LoginProperties.uid.get().longValue(), 4, 0, 0, 0, "", null, null));
    }

    public void insertMessage(ViewerMessage.Message message) {
        if (message == null) {
            return;
        }
        this.mAdapter.notifyDataSetInvalidated();
        this.mAdapter.add(message);
        this.mAdapter.getCount();
    }

    @Override // android.widget.AdapterView
    public int getCount() {
        ChatMessageAdapter chatMessageAdapter = this.mAdapter;
        if (chatMessageAdapter == null) {
            return 0;
        }
        return chatMessageAdapter.getCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean needAutoRollScroll() {
        return (this.mLockScroll || this.mIsTouching) ? false : true;
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        if (motionEvent.getActionMasked() == 1) {
            this.mIsTouching = false;
            if (getLastVisiblePosition() == getCount() - 1 && (getFirstVisiblePosition() != 0 || getLastVisiblePosition() != getCount() - 1)) {
                z = false;
            }
            this.mLockScroll = z;
            this.mAdapter.notifyDataSetChanged();
        } else if (motionEvent.getActionMasked() == 0) {
            this.mIsTouching = true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mIsBottom) {
            scrollToBottom();
        }
    }

    private void scrollToBottom() {
        if (!needAutoRollScroll() || getCount() <= 0) {
            return;
        }
        setSelection(getCount() - 1);
    }

    private void init() {
        BaseHandlerApp.runAsync(new Runnable() { // from class: com.huya.berry.sdklive.living.messageboard.ui.ChatListBrowser.1
            @Override // java.lang.Runnable
            public void run() {
                ChatListBrowser.this.initListener();
                ChatListBrowser chatListBrowser = ChatListBrowser.this;
                chatListBrowser.setAdapter((ListAdapter) chatListBrowser.mAdapter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initListener() {
        setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.huya.berry.sdklive.living.messageboard.ui.ChatListBrowser.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == 2 || i == 1) {
                    ChatListBrowser.this.mLockScroll = true;
                } else {
                    ChatListBrowser.this.mLockScroll = false;
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                int i4 = i2 + i;
                if (i4 == i3 || (ChatListBrowser.this.mIsNewMsg && i4 + 1 == i3)) {
                    ChatListBrowser.this.mIsBottom = true;
                    ChatListBrowser chatListBrowser = ChatListBrowser.this;
                    chatListBrowser.mListSize = chatListBrowser.mAdapter.getCount();
                    if (ChatListBrowser.this.mIsNewMsg) {
                        ChatListBrowser.this.mAdapter.notifyDataSetChanged();
                        return;
                    }
                    return;
                }
                ChatListBrowser.this.mIsBottom = false;
            }
        });
    }

    protected int getLayoutResources() {
        return this.mSmall ? ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_pub_live_message_item_small) : ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_pub_live_message_item);
    }

    protected void bindData(View view, ViewerMessage.Message message, int i) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.messageTV = (ComnTextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.animation_message));
            view.setTag(viewHolder);
        }
        ChatBinder.bindRelativeView(message, viewHolder.messageTV);
    }

    private static class ViewHolder {
        ComnTextView messageTV;

        private ViewHolder() {
        }
    }

    private class ChatMessageAdapter extends HuyaAdapter<ViewerMessage.Message> {
        public ChatMessageAdapter() {
            super(ChatListBrowser.this.getContext(), ChatListBrowser.this.getLayoutResources());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huya.berry.sdklive.living.messageboard.ui.HuyaAdapter
        public void bindView(View view, ViewerMessage.Message message, int i) {
            if (view == null || message == null || i >= getCount()) {
                return;
            }
            ChatListBrowser.this.bindData(view, message, i);
        }

        @Override // com.huya.berry.sdklive.living.messageboard.ui.HuyaAdapter, android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            if (getCount() > 200) {
                setNotifyOnChange(false);
                for (int i = 0; i < 50; i++) {
                    remove(getItem(i));
                }
                setNotifyOnChange(true);
            }
            super.notifyDataSetChanged();
            int count = ChatListBrowser.this.mAdapter.getCount();
            if (ChatListBrowser.this.needAutoRollScroll()) {
                if (ChatListBrowser.this.mIsBottom) {
                    ChatListBrowser.this.setSelection(getCount() - 1);
                }
                int i2 = count - ChatListBrowser.this.mListSize;
                if (ChatListBrowser.this.mMsgCallBack != null) {
                    ChatListBrowser.this.mMsgCallBack.updateNewMsgView(true ^ ChatListBrowser.this.mIsBottom, i2);
                }
            }
        }
    }
}
