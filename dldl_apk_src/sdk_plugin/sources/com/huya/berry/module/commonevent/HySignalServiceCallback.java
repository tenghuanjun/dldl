package com.huya.berry.module.commonevent;

import android.graphics.Bitmap;
import com.duowan.HUYA.SpecialUserEnterMsg;
import com.huya.berry.module.commonevent.GamePacket;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HySignalServiceCallback {

    public static class ChatText {
        public int color;
        public int fansLevel;
        public String fansNick;
        public int goldHostLevel;
        public boolean isCheat;
        public int nobleLevel;
        public int roomAuditLevel;
        public long timestamp;
        public long uid;
        public String nickname = "";
        public String avaterUrl = "";
        public String text = "";
        public String mXXBarrageCmd = null;
        public List<String> prefixIons = null;
        public List<Bitmap> prefixBitmaps = null;
        public List<String> suffixIons = null;
        public List<Bitmap> suffixBitmaps = null;

        public boolean isRoomNormalManager() {
            return this.roomAuditLevel == 2;
        }

        public boolean isRoomSuperManager() {
            return this.roomAuditLevel == 1;
        }

        public boolean isRoomManager() {
            return isRoomNormalManager() || isRoomSuperManager();
        }

        public void addSuffixIcon(String str) {
            if (this.suffixIons == null) {
                this.suffixIons = new ArrayList();
            }
            this.suffixIons.add(str);
        }

        public void addPrefixIcon(String str) {
            if (this.prefixIons == null) {
                this.prefixIons = new ArrayList();
            }
            this.prefixIons.add(str);
        }

        public String toString() {
            return "ChatText{nickname='" + this.nickname + "', avaterUrl='" + this.avaterUrl + "', timestamp=" + this.timestamp + ", color=" + this.color + ", text='" + this.text + "', isCheat=" + this.isCheat + ", mXXBarrageCmd='" + this.mXXBarrageCmd + "', uid=" + this.uid + ", roomAuditLevel=" + this.roomAuditLevel + ", nobleLevel=" + this.nobleLevel + ", goldHostLevel=" + this.goldHostLevel + ", fansLevel=" + this.fansLevel + ", fansNick='" + this.fansNick + "', prefixIons=" + this.prefixIons + ", suffixIons=" + this.suffixIons + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public static class PubTextNotice {
        public final ChatText info;

        public PubTextNotice(ChatText chatText) {
            this.info = chatText;
        }
    }

    public static class SendGameItemSuccess {
        public final GamePacket.SendItemSuccess info;

        public SendGameItemSuccess(GamePacket.SendItemSuccess sendItemSuccess) {
            this.info = sendItemSuccess;
        }
    }

    public static class SpecialUserEnterMsgNotice {
        public final SpecialUserEnterMsg info;

        public SpecialUserEnterMsgNotice(SpecialUserEnterMsg specialUserEnterMsg) {
            this.info = specialUserEnterMsg;
        }
    }
}
