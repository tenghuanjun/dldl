package com.huya.berry.sdklive.living.messageboard;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.duowan.live.common.framework.BasePresenter;
import com.duowan.live.one.util.LUtil;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.module.commonevent.GamePacket;
import com.huya.berry.module.commonevent.HySignalServiceCallback;
import com.huya.berry.module.commonevent.LivePresenterInterface;
import com.huya.berry.module.live.ISdkLiveService;
import com.huya.berry.module.props.PropsMgr;
import com.huya.berry.sdklive.living.messageboard.entity.ViewerMessage;
import com.huya.berry.sdklive.living.messageboard.helper.MessageInterface;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.live.rxutils.SchedulerUtils;
import com.huya.live.service.ServiceCenter;
import com.huya.live.utils.image.IconListLoader;
import com.huya.mtp.utils.FP;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MessagePresenter extends BasePresenter {
    private static final String TAG = "MessagePresenter";
    protected MessageInterface mView;
    private boolean mIsTool = false;
    private boolean mHalfHide = false;

    private boolean isHideMessage() {
        return false;
    }

    public MessagePresenter(MessageInterface messageInterface) {
        this.mView = messageInterface;
    }

    public void setIsTool(boolean z) {
        this.mIsTool = z;
    }

    @Override // com.duowan.live.common.framework.AbsPresenter, com.duowan.live.common.framework.IPresenter
    public void onCreate() {
        super.onCreate();
        if (this.mIsTool) {
            resetMsgNumber();
        }
    }

    @Override // com.duowan.live.common.framework.AbsPresenter, com.duowan.live.common.framework.IPresenter
    public void onDestroy() {
        super.onDestroy();
        resetMsgNumber();
        this.mView = null;
        ArkUtils.unregister(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pubTextImpl(HySignalServiceCallback.PubTextNotice pubTextNotice) {
        LUtil.info(TAG, String.format("onPubText, nickname=%s, text=%s", pubTextNotice.info.nickname, pubTextNotice.info.text));
        HySignalServiceCallback.ChatText chatText = pubTextNotice.info;
        if (chatText.uid == -1) {
            StringBuilder sb = new StringBuilder();
            sb.append(chatText.nickname);
            sb.append(TextUtils.isEmpty(chatText.nickname) ? "" : "：");
            sb.append(chatText.text);
            this.mView.pubMessage(new ViewerMessage.SystemMessage(sb.toString()));
        } else if (chatText.uid == -2) {
            this.mView.pubMessage(new ViewerMessage.ShareMessage(chatText.text));
        } else {
            this.mView.pubMessage(new ViewerMessage.ChatMessage(chatText.nickname, chatText.avaterUrl, chatText.timestamp, chatText.text.replaceAll("(\\u2029|\\n)", " "), false, chatText.color, chatText.isCheat, chatText.uid, chatText.roomAuditLevel, chatText.nobleLevel, chatText.goldHostLevel, chatText.fansLevel, chatText.fansNick, chatText.prefixBitmaps, chatText.suffixBitmaps));
        }
        onUpdateHideNews(1);
    }

    @IASlot(executorID = 1)
    public void onPubText(final HySignalServiceCallback.PubTextNotice pubTextNotice) {
        if (this.mView == null || isHideMessage() || pubTextNotice == null || pubTextNotice.info == null) {
            L.error(TAG, "mView == null || pubTextNotice == null || pubTextNotice.info == null");
        } else if (FP.empty(pubTextNotice.info.prefixIons) && FP.empty(pubTextNotice.info.suffixIons)) {
            pubTextImpl(pubTextNotice);
        } else {
            new IconListLoader(pubTextNotice.info.prefixIons, new IconListLoader.Listener() { // from class: com.huya.berry.sdklive.living.messageboard.MessagePresenter.1
                @Override // com.huya.live.utils.image.IconListLoader.Listener
                public void onEnd(List<Bitmap> list) {
                    pubTextNotice.info.prefixBitmaps = list;
                    new IconListLoader(pubTextNotice.info.suffixIons, new IconListLoader.Listener() { // from class: com.huya.berry.sdklive.living.messageboard.MessagePresenter.1.1
                        @Override // com.huya.live.utils.image.IconListLoader.Listener
                        public void onEnd(List<Bitmap> list2) {
                            if (MessagePresenter.this.mView == null) {
                                return;
                            }
                            pubTextNotice.info.suffixBitmaps = list2;
                            MessagePresenter.this.pubTextImpl(pubTextNotice);
                        }
                    }).load();
                }
            }).load();
        }
    }

    @IASlot(executorID = 1)
    public void onSendGameItemSuccess(HySignalServiceCallback.SendGameItemSuccess sendGameItemSuccess) {
        if (this.mView == null || isHideMessage() || sendGameItemSuccess == null || sendGameItemSuccess.info == null) {
            L.error(TAG, "mView == null || sendGameItemSuccess == null || sendGameItemSuccess.info == null");
            return;
        }
        if (PropsMgr.isSeal(sendGameItemSuccess.info.mItemType)) {
            L.warn(TAG, String.format("prop is seal, so drop out. itemType=%d", Integer.valueOf(sendGameItemSuccess.info.mItemType)));
            return;
        }
        if (PropsMgr.instance().getPropReal(sendGameItemSuccess.info.mItemType, true) == null) {
            L.warn(TAG, "prop is null");
            ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
            if (iSdkLiveService != null) {
                iSdkLiveService.getMobilePropsItem(sendGameItemSuccess.info.mItemType).compose(SchedulerUtils.ioio()).subscribe(new WupObserver());
                return;
            }
            return;
        }
        GamePacket.SendItemSuccess sendItemSuccess = sendGameItemSuccess.info;
        if (sendItemSuccess.mItemType < 301 || sendItemSuccess.mItemType > 322) {
            LUtil.info(TAG, String.format(Locale.CHINA, "onSendGameItemSuccess,mPayID %s, mSenderNick=%s, mItemType=%d, mItemCount=%d", sendItemSuccess.mPayID, sendItemSuccess.mSenderNick, Integer.valueOf(sendItemSuccess.mItemType), Integer.valueOf(sendItemSuccess.mItemCount)));
            this.mView.pubMessage(new ViewerMessage.PropMessage(sendItemSuccess.mSenderUid, sendItemSuccess.mSenderNick, "直播", sendItemSuccess.mItemType, sendItemSuccess.mItemCount, sendItemSuccess.mSenderIcon, sendItemSuccess.mNobleLevel, sendItemSuccess.mPayID, sendItemSuccess.mTotalGoldBean));
            onUpdateHideNews(1);
        }
    }

    @IASlot(executorID = 1)
    public void onSpecialUserEnterMsgNotice(HySignalServiceCallback.SpecialUserEnterMsgNotice specialUserEnterMsgNotice) {
        if (this.mView == null || isHideMessage() || specialUserEnterMsgNotice == null || specialUserEnterMsgNotice.info == null) {
            return;
        }
        if (specialUserEnterMsgNotice.info.iTraceSource == 2) {
            this.mView.pubMessage(new ViewerMessage.ShareEnterMessage(specialUserEnterMsgNotice.info.lUid, specialUserEnterMsgNotice.info.sNickName, specialUserEnterMsgNotice.info.iNobleLevel, specialUserEnterMsgNotice.info.sSharePlatform, specialUserEnterMsgNotice.info.sExtraMsg));
        } else if (specialUserEnterMsgNotice.info.iNobleLevel <= 0 && specialUserEnterMsgNotice.info.iGuardLevel <= 0) {
            this.mView.pubMessage(new ViewerMessage.NormalEnterMessage(specialUserEnterMsgNotice.info.lUid, specialUserEnterMsgNotice.info.sNickName, specialUserEnterMsgNotice.info.iTraceSource == 1, specialUserEnterMsgNotice.info.dDistance, specialUserEnterMsgNotice.info.sLocation));
        } else {
            this.mView.pubMessage(new ViewerMessage.VipEnterMessage(specialUserEnterMsgNotice.info.lUid, specialUserEnterMsgNotice.info.sNickName, specialUserEnterMsgNotice.info.iNobleLevel, specialUserEnterMsgNotice.info.iGuardLevel, specialUserEnterMsgNotice.info.iTraceSource == 1, specialUserEnterMsgNotice.info.dDistance, specialUserEnterMsgNotice.info.sLocation));
        }
        onUpdateHideNews(1);
    }

    @IASlot(executorID = 1)
    public void onToolHalfHide(LivePresenterInterface.ToolHalfHide toolHalfHide) {
        if (this.mView == null || !this.mIsTool) {
            return;
        }
        boolean z = toolHalfHide.mHalfHide;
        this.mHalfHide = z;
        if (z) {
            return;
        }
        SdkProperties.hideMsgNumber.reset();
    }

    private void onUpdateHideNews(int i) {
        L.info(TAG, "onUpdateHideNews:" + i);
        if (this.mIsTool) {
            if (this.mHalfHide) {
                SdkProperties.hideMsgNumber.set(Integer.valueOf(SdkProperties.hideMsgNumber.get().intValue() + i));
            } else {
                SdkProperties.hideMsgNumber.set(0);
            }
        }
    }

    @IASlot(executorID = 1)
    public void onExpandMsgContainer(LivePresenterInterface.ExpandMsgContainer expandMsgContainer) {
        resetMsgNumber();
    }

    private void resetMsgNumber() {
        SdkProperties.hideMsgNumber.set(0);
    }
}
