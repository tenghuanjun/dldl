package com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory;

import android.graphics.Color;
import com.sqwan.base.L;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.skin.SkinHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ImNotification extends AImMsg {
    @Override // com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory.AImMsg
    protected int getUserColor() {
        return SkinHelper.getColorValue(L.getActivity(), SqR.color.sy37_item_im_chat_tv_msg_im_notification_user_color, Color.parseColor(L.getActivity().getResources().getConfiguration().orientation == 1 ? "#A0A0A0" : "#888BAB"));
    }

    @Override // com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory.AImMsg
    protected int getContentColor() {
        return SkinHelper.getColorValue(L.getActivity(), SqR.color.sy37_item_im_chat_tv_msg_im_notification_content_color, Color.parseColor(L.getActivity().getResources().getConfiguration().orientation == 1 ? "#9d96f3" : "#60C657"));
    }

    @Override // com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory.AImMsg
    protected int getUserSize() {
        return L.getActivity().getResources().getConfiguration().orientation == 1 ? 12 : 9;
    }

    @Override // com.sqwan.liveshow.huya.request.bean.danmu.websocket.factory.AImMsg
    protected int getContentSize() {
        return L.getActivity().getResources().getConfiguration().orientation == 1 ? 12 : 9;
    }
}
