package com.huya.berry.module;

import android.text.TextUtils;
import com.duowan.HUYA.MessageNotice;
import com.duowan.HUYA.SendItemSubBroadcastPacket;
import com.duowan.HUYA.SpecialUserEnterMsg;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.util.L;
import com.duowan.taf.jce.JceStruct;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.utils.TaskExecutor;
import com.huya.berry.module.commonevent.Event_Biz;
import com.huya.berry.module.commonevent.GamePacket;
import com.huya.berry.module.commonevent.HySignalServiceCallback;
import com.huya.berry.module.pubtext.ChatText;
import com.huya.component.login.LoginProperties;
import com.huya.mtp.utils.StringUtils;
import com.sqwan.bugless.util.FileUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ChatTextCacheHelper {
    private static final int CACHE_MAX = 200;
    private static final int DELAY_TIME = 100;
    private static final String TAG = "ChatTextCacheHelper";
    private boolean mIsEndTask;
    private JceStruct[] mBufferArr = new JceStruct[200];
    private byte[] lock = new byte[8];
    private int mAddIndex = 0;
    private int mRemoveIndex = 0;
    private Runnable mGetChatTextTask = new Runnable() { // from class: com.huya.berry.module.ChatTextCacheHelper.1
        @Override // java.lang.Runnable
        public void run() {
            if (ChatTextCacheHelper.this.mIsEndTask) {
                return;
            }
            ChatTextCacheHelper.this.getChatText();
            TaskExecutor.proxyHandler().postDelayed(ChatTextCacheHelper.this.mGetChatTextTask, 100L);
        }
    };

    public void addNewNotice(JceStruct jceStruct) {
        int i = this.mAddIndex;
        if (i >= 200) {
            L.error(TAG, "no add  " + this.mAddIndex + "  $$ " + this.mRemoveIndex);
            return;
        }
        this.mBufferArr[i] = jceStruct;
        synchronized (this.lock) {
            int i2 = this.mAddIndex + 1;
            this.mAddIndex = i2;
            if (i2 >= 200) {
                this.mAddIndex = 0;
            }
        }
    }

    public void start() {
        this.mIsEndTask = false;
        TaskExecutor.proxyHandler().postDelayed(this.mGetChatTextTask, 100L);
    }

    public void stop() {
        TaskExecutor.proxyHandler().removeCallbacks(this.mGetChatTextTask);
        this.mIsEndTask = true;
        this.mBufferArr = new JceStruct[200];
        this.mAddIndex = 0;
        this.mRemoveIndex = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getChatText() {
        JceStruct jceStruct;
        int i = this.mRemoveIndex;
        if (i < 200 && (jceStruct = this.mBufferArr[i]) != null) {
            L.info(TAG, "mAddIndex:" + this.mAddIndex + ",mRemoveIndex:" + this.mRemoveIndex);
            if (jceStruct instanceof MessageNotice) {
                handleMessageInfo((MessageNotice) jceStruct);
            } else if (jceStruct instanceof SendItemSubBroadcastPacket) {
                handleGiftInfo((SendItemSubBroadcastPacket) jceStruct);
            } else if (jceStruct instanceof SpecialUserEnterMsg) {
                handleUserEnter((SpecialUserEnterMsg) jceStruct);
            }
            this.mBufferArr[this.mRemoveIndex] = null;
            synchronized (this.lock) {
                int i2 = this.mRemoveIndex + 1;
                this.mRemoveIndex = i2;
                if (i2 >= 200) {
                    this.mRemoveIndex = 0;
                }
            }
        }
    }

    private void handleMessageInfo(MessageNotice messageNotice) {
        long lUid = messageNotice.getTUserInfo().getLUid();
        if (!SdkProperties.isPLayerFloating.get().booleanValue() && !SdkProperties.isCustomReceiveDanmu.get().booleanValue()) {
            HySignalServiceCallback.ChatText chatText = new HySignalServiceCallback.ChatText();
            chatText.timestamp = System.currentTimeMillis();
            chatText.nickname = messageNotice.tUserInfo.sNickName;
            chatText.avaterUrl = messageNotice.tUserInfo.sAvatarUrl;
            chatText.color = Math.max(messageNotice.tFormat.iFontColor, 0);
            chatText.text = messageNotice.sContent;
            chatText.isCheat = false;
            chatText.mXXBarrageCmd = "";
            chatText.uid = messageNotice.tUserInfo.lUid;
            ArkUtils.send(new HySignalServiceCallback.PubTextNotice(chatText));
        } else {
            if (lUid == LoginProperties.uid.get().longValue()) {
                return;
            }
            ChatText chatText2 = new ChatText();
            String sContent = messageNotice.getSContent();
            messageNotice.getIShowMode();
            boolean z = lUid <= 1;
            int iTextSpeed = messageNotice.getTFormat() != null ? messageNotice.getTBulletFormat().getITextSpeed() : 0;
            chatText2.systemStyle = z;
            chatText2.mPrefixDecorations = messageNotice.getVDecorationPrefix();
            chatText2.mSuffixDecorations = messageNotice.getVDecorationSuffix();
            chatText2.showBarrage = true;
            chatText2.showBullet = true;
            chatText2.barrageColor = -1;
            chatText2.bulletColor = -1;
            chatText2.uid = lUid;
            chatText2.timestamp = System.currentTimeMillis();
            chatText2.fromSystem = z;
            chatText2.nickname = messageNotice.getTUserInfo().sNickName;
            chatText2.avatar = messageNotice.getTUserInfo().getSAvatarUrl();
            chatText2.color = -1;
            chatText2.speed = iTextSpeed;
            chatText2.text = sContent;
            chatText2.mDebugInfo = "(Android)";
            chatText2.timeLime = null;
            ArkUtils.send(new Event_Biz.SendPublicText(chatText2));
        }
        L.info(TAG, String.format("onMessageNotice, sNickName=%s, sContent=%s", messageNotice.tUserInfo.sNickName, messageNotice.sContent));
    }

    private void handleGiftInfo(SendItemSubBroadcastPacket sendItemSubBroadcastPacket) {
        L.info(TAG, "[game]onSubChannelConsumeNotify uid:%d type %d count %d ", Long.valueOf(sendItemSubBroadcastPacket.getLSenderUid()), Integer.valueOf(sendItemSubBroadcastPacket.getIItemType()), Integer.valueOf(sendItemSubBroadcastPacket.getIItemCount()));
        GamePacket.SendItemSuccess sendItemSuccess = new GamePacket.SendItemSuccess();
        sendItemSuccess.mItemType = sendItemSubBroadcastPacket.getIItemType();
        sendItemSuccess.mItemCount = sendItemSubBroadcastPacket.getIItemCount();
        sendItemSuccess.mPayID = StringUtils.fromUtf8(sendItemSubBroadcastPacket.getStrPayId());
        sendItemSuccess.mPresenterUid = sendItemSubBroadcastPacket.getLPresenterUid();
        sendItemSuccess.mSendContent = StringUtils.fromUtf8(sendItemSubBroadcastPacket.getSSendContent());
        sendItemSuccess.mSenderUid = sendItemSubBroadcastPacket.getLSenderUid();
        sendItemSuccess.mSenderIcon = sendItemSubBroadcastPacket.getISenderIcon();
        sendItemSuccess.mSenderNick = StringUtils.fromUtf8(sendItemSubBroadcastPacket.getSSenderNick());
        sendItemSuccess.mItemCountByGroup = sendItemSubBroadcastPacket.getIItemCountByGroup();
        sendItemSuccess.mItemGroup = sendItemSubBroadcastPacket.getIItemGroup();
        sendItemSuccess.mSuperPupleLevel = sendItemSubBroadcastPacket.getISuperPupleLevel();
        sendItemSuccess.mComboScore = sendItemSubBroadcastPacket.getIComboScore();
        sendItemSuccess.mDisplayInfo = sendItemSubBroadcastPacket.getIDisplayInfo();
        sendItemSuccess.mExpand = StringUtils.fromUtf8(sendItemSubBroadcastPacket.getSExpand());
        sendItemSuccess.mColorEffectType = sendItemSubBroadcastPacket.getIColorEffectType();
        sendItemSuccess.mSpecialEffect = sendItemSubBroadcastPacket.getIEffectType() == 7;
        ArkUtils.call(new HySignalServiceCallback.SendGameItemSuccess(sendItemSuccess));
    }

    private void handleUserEnter(SpecialUserEnterMsg specialUserEnterMsg) {
        L.info(TAG, "onSpecialUserEnterMsg, %s", specialUserEnterMsg.toString());
        if (specialUserEnterMsg.getLUid() != LoginProperties.uid.get().longValue()) {
            ArkUtils.send(new HySignalServiceCallback.SpecialUserEnterMsgNotice(specialUserEnterMsg));
        }
    }

    private String getRightUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length < 2) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = strArrSplit.length - 2;
        sb.append(strArrSplit[length]);
        sb.append("_8");
        strArrSplit[length] = sb.toString();
        StringBuilder sb2 = new StringBuilder(strArrSplit[0]);
        for (int i = 1; i < strArrSplit.length; i++) {
            sb2.append(FileUtil.FILE_EXTENSION_SEPARATOR);
            sb2.append(strArrSplit[i]);
        }
        return sb2.toString();
    }
}
