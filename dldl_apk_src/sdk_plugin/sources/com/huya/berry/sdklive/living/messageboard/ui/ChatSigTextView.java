package com.huya.berry.sdklive.living.messageboard.ui;

import android.content.Context;
import android.util.AttributeSet;
import com.huya.berry.gamesdk.widgets.MarqueeTextView;
import com.huya.berry.sdklive.living.messageboard.entity.ViewerMessage;
import com.huya.berry.sdklive.living.messageboard.helper.ChatBinder;
import com.huya.component.login.LoginProperties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ChatSigTextView extends MarqueeTextView {
    public ChatSigTextView(Context context) {
        super(context);
    }

    public ChatSigTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChatSigTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void insertMessage(ViewerMessage.Message message) {
        ChatBinder.bindView(message, this);
    }

    public void insertOwnMessage(String str, int i) {
        insertMessage(new ViewerMessage.ChatMessage(null, System.currentTimeMillis(), str, true, i, false, LoginProperties.uid.get().longValue()));
    }
}
