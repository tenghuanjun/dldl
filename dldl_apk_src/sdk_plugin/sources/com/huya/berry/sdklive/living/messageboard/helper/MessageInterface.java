package com.huya.berry.sdklive.living.messageboard.helper;

import android.content.Context;
import com.huya.berry.sdklive.living.messageboard.entity.ViewerMessage;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface MessageInterface {
    void clear();

    Context getMContext();

    void pubMessage(ViewerMessage.Message message);
}
