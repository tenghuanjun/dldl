package com.huya.berry.sdklive.living.messageboard.entity;

import android.graphics.Bitmap;
import com.huya.component.login.LoginProperties;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ViewerMessage {

    public static class Message {
        public boolean mIsSystem;

        public Message(boolean z) {
            this.mIsSystem = z;
        }
    }

    public static class SystemMessage extends Message {
        public String mMessage;
        public String mName;

        public SystemMessage(String str) {
            super(true);
            this.mMessage = str;
        }

        public SystemMessage(String str, String str2) {
            super(true);
            this.mMessage = str;
            this.mName = str2;
        }
    }

    public static class VipEnterMessage extends Message {
        public final double mDistance;
        public int mIGuardLevel;
        public final boolean mIsFromNearby;
        public final String mLocation;
        public final String mNickName;
        public final int mNobleLevel;
        public final long mUid;

        public VipEnterMessage(long j, String str, int i, int i2, boolean z, double d, String str2) {
            super(false);
            this.mUid = j;
            this.mNickName = str;
            this.mNobleLevel = i;
            this.mIsFromNearby = z;
            this.mDistance = d;
            this.mLocation = str2;
            this.mIGuardLevel = i2;
        }

        public String toString() {
            return "VipEnterMessage{mUid=" + this.mUid + ", mNickName='" + this.mNickName + "', mNobleLevel=" + this.mNobleLevel + ", mIsFromNearby=" + this.mIsFromNearby + ", mDistance=" + this.mDistance + ", mLocation='" + this.mLocation + "', mIGuardLevel=" + this.mIGuardLevel + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public static class NoblePromotionMessage extends Message {
        public final boolean inChannel;
        public final String mAnchorName;
        public final boolean mIsNoble;
        public final String mNickName;
        public final int mNobleLevel;
        public final String mNobleName;
        public final int mOpenFlag;
        public final int mRenewMonth;

        public NoblePromotionMessage(String str, int i, String str2, String str3, boolean z, int i2, int i3, boolean z2) {
            super(false);
            this.mNickName = str;
            this.mNobleLevel = i;
            this.mAnchorName = str2;
            this.mNobleName = str3;
            this.inChannel = z;
            this.mRenewMonth = i2;
            this.mOpenFlag = i3;
            this.mIsNoble = z2;
        }

        public String toString() {
            return "NoblePromotionMessage{mNickName='" + this.mNickName + "', mNobleLevel=" + this.mNobleLevel + ", mAnchorName='" + this.mAnchorName + "', mNobleName='" + this.mNobleName + "', inChannel=" + this.inChannel + ", mRenewMonth=" + this.mRenewMonth + ", mOpenFlag=" + this.mOpenFlag + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public static class ContributeRankChangeMessage extends Message {
        public static final int TYPE_TOTAL_RANK_CHANGE = 1;
        public static final int TYPE_WEEK_RANK_CHANGE = 2;
        public final String mNickName;
        public final int mNobleLevel;
        public final int mRank;
        public final int mType;

        public ContributeRankChangeMessage(int i, String str, int i2, int i3) {
            super(false);
            this.mType = i;
            this.mNickName = str;
            this.mRank = i2;
            this.mNobleLevel = i3;
        }
    }

    public static class PropMessage extends Message {
        public int mCount;
        public boolean mIsOwn;
        public int mItemType;
        public int mNobleLevel;
        public String mPayID;
        public String mReceiverName;
        public String mSenderIcon;
        public String mSenderName;
        public long mSenderUid;
        public long mTotalGoldBean;

        public PropMessage(long j, String str, String str2, int i, int i2, String str3, int i3, String str4, long j2) {
            super(false);
            this.mSenderUid = j;
            this.mSenderName = str;
            this.mReceiverName = str2;
            this.mItemType = i;
            this.mCount = i2;
            this.mIsOwn = j == LoginProperties.uid.get().longValue();
            this.mSenderIcon = str3;
            this.mNobleLevel = i3;
            this.mPayID = str4;
            this.mTotalGoldBean = j2;
        }
    }

    public static class ChatMessage extends Message {
        public String mAvatar;
        public int mFansLevel;
        public String mFansNick;
        public int mGoldHostLevel;
        public boolean mIsCheat;
        public boolean mIsOwn;
        public String mMessage;
        public int mMessageColor;
        public String mNickname;
        public int mNobleLevel;
        public int mRoomAuditLevel;
        public long mTime;
        public long mUid;
        public List<Bitmap> prefixBitmaps;
        public List<Bitmap> suffixBitmaps;

        public ChatMessage(String str, long j, String str2, boolean z, int i, boolean z2, long j2) {
            this(str, "", j, str2, z, i, z2, j2, 0, 0, 0, 0, "", null, null);
        }

        public ChatMessage(String str, String str2, long j, String str3, boolean z, int i, boolean z2, long j2, int i2, int i3, int i4, int i5, String str4, List<Bitmap> list, List<Bitmap> list2) {
            super(false);
            this.prefixBitmaps = null;
            this.suffixBitmaps = null;
            this.mNickname = str;
            this.mAvatar = str2;
            this.mMessage = str3;
            this.mTime = j;
            this.mIsOwn = z;
            this.mMessageColor = i;
            this.mIsCheat = z2;
            this.mUid = j2;
            this.mRoomAuditLevel = i2;
            this.mNobleLevel = i3;
            this.mGoldHostLevel = i4;
            this.mFansLevel = i5;
            this.mFansNick = str4;
            this.prefixBitmaps = list;
            this.suffixBitmaps = list2;
        }

        public boolean isRoomNormalManager() {
            return this.mRoomAuditLevel == 2;
        }

        public boolean isRoomSuperManager() {
            return this.mRoomAuditLevel == 1;
        }

        public boolean isRoomManager() {
            return isRoomNormalManager() || isRoomSuperManager();
        }
    }

    public static class ShareEnterMessage extends Message {
        public final String mExtraMsg;
        public final String mNickName;
        public final int mNobleLevel;
        public final String mSharePlatform;
        public final long mUid;

        public ShareEnterMessage(long j, String str, int i, String str2, String str3) {
            super(false);
            this.mUid = j;
            this.mNickName = str;
            this.mNobleLevel = i;
            this.mSharePlatform = str2;
            this.mExtraMsg = str3;
        }
    }

    public static class NormalEnterMessage extends Message {
        public final double mDistance;
        public final boolean mIsFromNearby;
        public final String mLocation;
        public final String mNickName;
        public final long mUid;

        public NormalEnterMessage(long j, String str, boolean z, double d, String str2) {
            super(false);
            this.mUid = j;
            this.mNickName = str;
            this.mIsFromNearby = z;
            this.mDistance = d;
            this.mLocation = str2;
        }
    }

    public static class TVBarrageMessage extends Message {
        public String mContent;
        public int mFansLevel;
        public String mFansNick;
        public String mNickName;
        public int mNobleLevel;
        public int mTVColor;
        public int mTVType;
        public long mUid;

        public TVBarrageMessage(long j, String str, int i, int i2, String str2, int i3, String str3, int i4) {
            super(false);
            this.mUid = j;
            this.mContent = str;
            this.mTVType = i;
            this.mTVColor = i2;
            this.mNickName = str2;
            this.mNobleLevel = i3;
            this.mFansNick = str3;
            this.mFansLevel = i4;
        }
    }

    public static class ShareMessage extends Message {
        public String mMessage;

        public ShareMessage(String str) {
            super(false);
            this.mMessage = str;
        }
    }
}
