package com.huya.berry.module.commonevent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GamePacket {

    public static class BaseGamePacketReq extends EmptyGamePacket {
        public int mSubChannelId;
    }

    public enum DisplayType {
        NONE,
        MARQUEE,
        INSIDE_BANNER,
        BROADCAST_BANNER
    }

    public static class EmptyGamePacket {
    }

    public static final class SendItemSuccess extends BaseGamePacketReq {
        public int mColorEffectType;
        public int mComboScore;
        public int mDisplayInfo;
        public DisplayType mDisplayType;
        public String mExpand;
        public int mItemCount;
        public int mItemCountByGroup;
        public int mItemGroup;
        public int mItemType;
        public int mNobleLevel;
        public String mPayID;
        public long mPresenterUid;
        public String mSendContent;
        public String mSenderIcon;
        public String mSenderNick;
        public long mSenderUid;
        public boolean mSpecialEffect;
        public int mSuperPupleLevel;
        public long mTotalGoldBean;
    }
}
