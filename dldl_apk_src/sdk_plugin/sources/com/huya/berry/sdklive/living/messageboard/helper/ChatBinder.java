package com.huya.berry.sdklive.living.messageboard.helper;

import android.widget.RelativeLayout;
import com.huya.berry.gamesdk.widgets.ComnTextView;
import com.huya.berry.sdklive.living.messageboard.entity.ViewerMessage;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ChatBinder {

    public interface ChatListNewMessgeCallBack {
        void updateNewMsgView(boolean z, int i);
    }

    public static void bindRelativeView(ViewerMessage.Message message, ComnTextView comnTextView) {
        if (comnTextView.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) comnTextView.getLayoutParams()).addRule(message.mIsSystem ? 1 : 3);
        }
        bindView(message, comnTextView);
    }

    public static void bindView(ViewerMessage.Message message, ComnTextView comnTextView) {
        if (message instanceof ViewerMessage.SystemMessage) {
            ViewerMessage.SystemMessage systemMessage = (ViewerMessage.SystemMessage) message;
            MessageHelper.appendSystemMessage(systemMessage.mMessage, systemMessage.mName, comnTextView);
            return;
        }
        if (message instanceof ViewerMessage.PropMessage) {
            MessageHelper.appendPropMessage((ViewerMessage.PropMessage) message, comnTextView);
            return;
        }
        if (message instanceof ViewerMessage.VipEnterMessage) {
            MessageHelper.appVipEnterMessage((ViewerMessage.VipEnterMessage) message, comnTextView);
            return;
        }
        if (message instanceof ViewerMessage.ChatMessage) {
            MessageHelper.appendChatMessage((ViewerMessage.ChatMessage) message, comnTextView);
            comnTextView.setClickable(false);
        } else if (message instanceof ViewerMessage.NormalEnterMessage) {
            MessageHelper.appNormalEnterMessage((ViewerMessage.NormalEnterMessage) message, comnTextView);
        } else if (message instanceof ViewerMessage.ShareEnterMessage) {
            MessageHelper.appShareEnterMessage((ViewerMessage.ShareEnterMessage) message, comnTextView);
        } else if (message instanceof ViewerMessage.ShareMessage) {
            MessageHelper.appendShareMessage(((ViewerMessage.ShareMessage) message).mMessage, comnTextView);
        }
    }
}
