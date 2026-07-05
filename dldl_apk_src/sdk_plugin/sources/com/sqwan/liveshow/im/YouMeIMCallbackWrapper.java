package com.sqwan.liveshow.im;

import com.google.sqgson.Gson;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.task.Task;
import com.sqwan.common.util.task.TaskSubThread;
import com.sqwan.liveshow.bean.LiveShowIMBean;
import com.youme.imsdk.YIMMessage;
import com.youme.imsdk.YIMMessageBodyText;
import java.util.LinkedList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class YouMeIMCallbackWrapper extends YouMeIMCallback {
    private int i;
    private String TAG = "YouMeIMCallbackWrapper";
    private int PUSHIMDURATION = 20;
    private Task task = Task.create();
    private LinkedList<LiveShowIMBean.UserImBean> msgs = new LinkedList<>();
    private TaskSubThread testTask = TaskSubThread.create();

    public abstract void onRecvChatMsg(LiveShowIMBean.UserImBean userImBean);

    public void test() {
    }

    public void release() {
        this.msgs.clear();
        stopTask();
    }

    public void stopTask() {
        this.task.stop();
    }

    public void startTask() {
        this.task.repeat(this.PUSHIMDURATION, new Task.TaskFunc() { // from class: com.sqwan.liveshow.im.YouMeIMCallbackWrapper.2
            @Override // com.sqwan.common.util.task.Task.TaskFunc
            public Task.Result exec() {
                if (!YouMeIMCallbackWrapper.this.msgs.isEmpty()) {
                    LiveShowIMBean.UserImBean userImBean = (LiveShowIMBean.UserImBean) YouMeIMCallbackWrapper.this.msgs.removeFirst();
                    YouMeIMCallbackWrapper.this.onRecvChatMsg(userImBean);
                    LogUtil.i(YouMeIMCallbackWrapper.this.TAG, "onRecvChatMsg msg " + userImBean.toString());
                    if (YouMeIMCallbackWrapper.this.msgs.isEmpty()) {
                        return Task.Result.Stop;
                    }
                }
                return Task.Result.Next;
            }
        });
    }

    public void init() {
        release();
        startTask();
    }

    @Override // com.sqwan.liveshow.im.YouMeIMCallback, com.youme.imsdk.callback.YIMEventCallback.MessageEventCallback
    public void onRecvMessage(YIMMessage yIMMessage) {
        if (yIMMessage != null && 1 == yIMMessage.getMessageType()) {
            YIMMessageBodyText yIMMessageBodyText = (YIMMessageBodyText) yIMMessage.getMessageBody();
            String messageContent = yIMMessageBodyText.getMessageContent();
            LogUtil.i(this.TAG, "接收到一条文本消息： " + yIMMessageBodyText);
            LiveShowIMBean.UserImBean userImBean = new LiveShowIMBean.UserImBean();
            ImMsgChatExtBean imMsgChatExtBean = (ImMsgChatExtBean) new Gson().fromJson(yIMMessageBodyText.getAttachParam(), ImMsgChatExtBean.class);
            if (imMsgChatExtBean != null) {
                userImBean.setUserName(imMsgChatExtBean.username);
            }
            userImBean.setUserChatContent(messageContent);
            this.msgs.add(userImBean);
            if (this.task.isRunning()) {
                return;
            }
            startTask();
        }
    }
}
