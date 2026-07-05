package com.huya.berry.module;

import android.text.TextUtils;
import com.duowan.HUYA.AttendeeCountNotice;
import com.duowan.HUYA.EndLiveNotice;
import com.duowan.HUYA.MessageNotice;
import com.duowan.HUYA.SecPackType;
import com.duowan.HUYA.SendItemSubBroadcastPacket;
import com.duowan.HUYA.SpecialUserEnterMsg;
import com.duowan.HUYA.SubscribeInfoNotify;
import com.duowan.HUYA.UploadLogNotice;
import com.duowan.HUYA.ZhixuPopupNotify;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.asignal.notify.PropertySet;
import com.duowan.auk.module.ArkModule;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.uploadLog.FeedBackInterface;
import com.duowan.networkmars.push.IPushWatcher;
import com.duowan.networkmars.push.TransmitService;
import com.duowan.networkmars.wup.WupHelper;
import com.duowan.taf.jce.JceInputStream;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.module.live.LiveCallback;
import com.huya.component.login.LoginProperties;
import com.huya.component.user.UserProperties;
import com.sqwan.liveshow.huya.SqR;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HysignalPushModule extends ArkModule {
    private static final String TAG = "HysignalServiceModule";
    private ChatTextCacheHelper cacheHelper;
    private IPushWatcher mWatcher = new IPushWatcher() { // from class: com.huya.berry.module.HysignalPushModule.1
        @Override // com.duowan.networkmars.push.IPushWatcher
        public void onCastPush(int i, byte[] bArr) {
            L.info(HysignalPushModule.TAG, "msgType:" + i);
            if (i == 1400) {
                HysignalPushModule.this.onMessageNotice(bArr);
                return;
            }
            if (i == 3104) {
                HysignalPushModule.this.onSubscribeInfoNoticeTaf(bArr);
                return;
            }
            if (i == 6114) {
                HysignalPushModule.this.onSpecialUserEnterMsg(bArr);
                return;
            }
            if (i == 6501) {
                HysignalPushModule.this.onSubChannelConsumeNotify(bArr);
                return;
            }
            if (i == 8001) {
                HysignalPushModule.this.onEndLiveNotice(bArr);
                return;
            }
            if (i == 8006) {
                HysignalPushModule.this.onWebTotalCount(bArr);
            } else if (i == 100100) {
                HysignalPushModule.this.onUploadLog(bArr);
            } else {
                if (i != 1023101) {
                    return;
                }
                HysignalPushModule.this.onZhixuPopupNotify(bArr);
            }
        }
    };

    @Override // com.duowan.auk.module.ArkModule
    public void onStart() {
        super.onStart();
        if (this.cacheHelper == null) {
            this.cacheHelper = new ChatTextCacheHelper();
        }
        TransmitService.getInstance().regCastProto(this.mWatcher, SecPackType._kSecPackTypeMessageNotice);
        TransmitService.getInstance().regCastProto(this.mWatcher, SecPackType._kSecPackTypeItemConsumSubNotify);
        TransmitService.getInstance().regCastProto(this.mWatcher, SecPackType._kSecPackSpecialUserEnterMsg);
        TransmitService.getInstance().regCastProto(this.mWatcher, SecPackType._kSecPackTypeUploadLog);
        TransmitService.getInstance().regCastProto(this.mWatcher, SecPackType._kSecPackTypeAttendeeCountNotice);
        TransmitService.getInstance().regCastProto(this.mWatcher, SecPackType._kSecPackTypeSubscribeInfoNoticeTaf);
        TransmitService.getInstance().regCastProto(this.mWatcher, SecPackType._kSecPackTypeEndLiveNotice);
        TransmitService.getInstance().regCastProto(this.mWatcher, SecPackType._kSecPackTypeZhixuPopupNotify);
    }

    @Override // com.duowan.auk.module.ArkModule
    public void onStop() {
        super.onStop();
        TransmitService.getInstance().unRegCastProto(this.mWatcher, SecPackType._kSecPackTypeMessageNotice);
        TransmitService.getInstance().unRegCastProto(this.mWatcher, SecPackType._kSecPackTypeItemConsumSubNotify);
        TransmitService.getInstance().unRegCastProto(this.mWatcher, SecPackType._kSecPackSpecialUserEnterMsg);
        TransmitService.getInstance().unRegCastProto(this.mWatcher, SecPackType._kSecPackTypeUploadLog);
        TransmitService.getInstance().unRegCastProto(this.mWatcher, SecPackType._kSecPackTypeAttendeeCountNotice);
        TransmitService.getInstance().unRegCastProto(this.mWatcher, SecPackType._kSecPackTypeSubscribeInfoNoticeTaf);
        TransmitService.getInstance().unRegCastProto(this.mWatcher, SecPackType._kSecPackTypeEndLiveNotice);
        TransmitService.getInstance().unRegCastProto(this.mWatcher, SecPackType._kSecPackTypeZhixuPopupNotify);
    }

    @IASlot(executorID = 1, mark = {SdkProperties.MarkIsLiving})
    public void onIsLiving(PropertySet<Boolean> propertySet) {
        if (this.cacheHelper == null) {
            return;
        }
        if (SdkProperties.isLiving.get().booleanValue()) {
            this.cacheHelper.start();
        } else {
            this.cacheHelper.stop();
        }
    }

    @IASlot(executorID = 1, mark = {SdkProperties.MarkPLayerFloating})
    public void onIsPLayerFloating(PropertySet<Boolean> propertySet) {
        if (this.cacheHelper == null) {
            return;
        }
        if (SdkProperties.isPLayerFloating.get().booleanValue()) {
            this.cacheHelper.start();
        } else {
            this.cacheHelper.stop();
        }
    }

    @IASlot(executorID = 1, mark = {SdkProperties.MarkCustomReceiveDanmu})
    public void onIsCustomReceiveDanmu(PropertySet<Boolean> propertySet) {
        if (this.cacheHelper == null) {
            return;
        }
        if (SdkProperties.isCustomReceiveDanmu.get().booleanValue()) {
            this.cacheHelper.start();
        } else {
            this.cacheHelper.stop();
        }
    }

    public void onMessageNotice(byte[] bArr) {
        MessageNotice messageNotice = new MessageNotice();
        messageNotice.readFrom(new JceInputStream(bArr));
        this.cacheHelper.addNewNotice(messageNotice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSubChannelConsumeNotify(byte[] bArr) {
        SendItemSubBroadcastPacket sendItemSubBroadcastPacket = new SendItemSubBroadcastPacket();
        sendItemSubBroadcastPacket.readFrom(new JceInputStream(bArr));
        this.cacheHelper.addNewNotice(sendItemSubBroadcastPacket);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSpecialUserEnterMsg(byte[] bArr) {
        SpecialUserEnterMsg specialUserEnterMsg = new SpecialUserEnterMsg();
        specialUserEnterMsg.readFrom(new JceInputStream(bArr));
        this.cacheHelper.addNewNotice(specialUserEnterMsg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUploadLog(byte[] bArr) {
        UploadLogNotice uploadLogNotice = new UploadLogNotice();
        uploadLogNotice.readFrom(new JceInputStream(bArr));
        L.info(TAG, "onUploadLog, %s", uploadLogNotice.toString());
        ArkUtils.send(new FeedBackInterface.AddFeedBack("自动反馈", String.format(Locale.US, "%s[%s|%d|%d]", "拿日志", UserProperties.nickName.get(), UserProperties.roomId.get(), LoginProperties.uid.get())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWebTotalCount(byte[] bArr) {
        AttendeeCountNotice attendeeCountNotice = new AttendeeCountNotice();
        attendeeCountNotice.readFrom(new JceInputStream(bArr));
        ArkUtils.send(new CommonEvent.OnLiveUserCount(attendeeCountNotice.iAttendeeCount));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSubscribeInfoNoticeTaf(byte[] bArr) {
        SubscribeInfoNotify subscribeInfoNotify = (SubscribeInfoNotify) WupHelper.parseJce(bArr, new SubscribeInfoNotify());
        if (subscribeInfoNotify == null || subscribeInfoNotify.getTTo() == null || TextUtils.isEmpty(subscribeInfoNotify.getTTo().getSKey())) {
            L.error(TAG, "notify == null || notify.getTTo() == null || TextUtils.isEmpty(notify.getTTo().getSKey())");
        } else if (subscribeInfoNotify.getTTo().getIType() == 2 && subscribeInfoNotify.getTTo().getSKey().equals(String.valueOf(LoginProperties.uid.get()))) {
            UserProperties.subscribesCount.set(Integer.valueOf(subscribeInfoNotify.iToCount));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEndLiveNotice(byte[] bArr) {
        EndLiveNotice endLiveNotice = new EndLiveNotice();
        endLiveNotice.readFrom(new JceInputStream(bArr));
        L.info(TAG, "_kSecPackTypeEndLiveNotice:%d,%s...", Integer.valueOf(endLiveNotice.getIReason()), String.format(Locale.CHINA, "停播：%d|%s|%d|%s", LoginProperties.uid.get(), new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis())), Integer.valueOf(endLiveNotice.getIReason()), endLiveNotice.getSReason()));
        if (SdkProperties.isLiving.get().booleanValue() && endLiveNotice.getLLiveId() == SdkProperties.liveId.get().longValue()) {
            String string = ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_live_endlive_notice), new Object[]{endLiveNotice.sReason});
            L.info(TAG, "onEndLiveNotice:mEndLiveConfirmMsg->%s", string);
            ArkUtils.send(new LiveCallback.EndLiveNotice(string));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onZhixuPopupNotify(byte[] bArr) {
        ZhixuPopupNotify zhixuPopupNotify = new ZhixuPopupNotify();
        zhixuPopupNotify.readFrom(new JceInputStream(bArr));
        L.info(TAG, "onZhixuPopupNotify, %s", zhixuPopupNotify.toString());
        ArkUtils.send(new LiveCallback.ZhixuPopupNotice(zhixuPopupNotify));
    }
}
