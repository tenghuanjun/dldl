package com.huya.berry.module.commonevent;

import com.duowan.HUYA.BulletBorderGroundFormat;
import com.duowan.HUYA.DecorationInfo;
import com.huya.berry.module.pubtext.ChatText;
import com.huya.component.login.LoginProperties;
import com.huya.component.user.UserProperties;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Event_Biz {

    public static class AppLoginKickOut {
    }

    @Deprecated
    public static class ChannelKickOut {
    }

    @Deprecated
    public static class JoinChannelFail {
    }

    @Deprecated
    public static class JoinChannelSuccess {
    }

    @Deprecated
    public static class LeaveChannel {
    }

    @Deprecated
    public static class JoinChannelStart {
        public Long arg0;
        public Long arg1;

        public JoinChannelStart(Long l, Long l2) {
            this.arg0 = l;
            this.arg1 = l2;
        }
    }

    @Deprecated
    public static class SessionEvent {
        public Integer arg0;

        public SessionEvent(Integer num) {
            this.arg0 = num;
        }
    }

    @Deprecated
    public static class ChangeSubChannel {
        public Long arg0;

        public ChangeSubChannel(Long l) {
            this.arg0 = l;
        }
    }

    public static class BaseNobleBarrage {
        public String avatarDecorationUrl;
        public int barrageColor;
        public BulletBorderGroundFormat bulletBorderGroundFormat;
        public int bulletColor;
        public List<DecorationInfo> mBulletSuffixDecorations;
        public long pid;
        public long uid;
        public String avatar = "";
        public String nickname = "";
        public String text = "";
        public int nobleLevel = 0;
        public int speed = 0;

        public boolean isBulletFormatEnableUse() {
            BulletBorderGroundFormat bulletBorderGroundFormat = this.bulletBorderGroundFormat;
            return bulletBorderGroundFormat != null && bulletBorderGroundFormat.getIEnableUse() == 1;
        }

        public String toString() {
            return "uid:" + this.uid + ";avatar:" + this.avatar + ";nickname:" + this.nickname + ";text:" + this.text + ";nobleLevel:" + this.nobleLevel + ";avatarDecorationUrl:" + this.avatarDecorationUrl + ";bulletBorderGroundFormat:" + this.bulletBorderGroundFormat;
        }
    }

    public static class TextAboutToSendV2 extends BaseNobleBarrage {
        public List<DecorationInfo> prefixDecorations;
        public List<DecorationInfo> suffixDecorations;
        public String timeLine;
        public long timeStamp;
        public String xxBarrageCmd;

        public TextAboutToSendV2(String str, Integer num, Integer num2, long j, String str2, List<DecorationInfo> list, List<DecorationInfo> list2, String str3) {
            this.uid = LoginProperties.uid.get().longValue();
            this.nickname = UserProperties.nickName.get();
            this.avatar = UserProperties.avatarUrl.get();
            this.text = str;
            this.barrageColor = num.intValue();
            this.bulletColor = num2.intValue();
            this.timeStamp = j;
            this.xxBarrageCmd = str2;
            this.prefixDecorations = list;
            this.suffixDecorations = list2;
            this.timeLine = str3;
        }
    }

    public static class SendPublicText {
        public ChatText ct;

        public SendPublicText(ChatText chatText) {
            this.ct = chatText;
        }
    }
}
