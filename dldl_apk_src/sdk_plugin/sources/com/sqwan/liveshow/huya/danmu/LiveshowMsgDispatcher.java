package com.sqwan.liveshow.huya.danmu;

import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.task.TaskSubThread;
import com.sqwan.liveshow.huya.engine.LiveshowManager;
import com.sqwan.liveshow.huya.request.bean.danmu.http.FetchImRspBean;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowMsgDispatcher {
    private static final String TAG = "LiveshowMsgDispatcher";
    private static final LiveshowMsgDispatcher ourInstance = new LiveshowMsgDispatcher();
    private int duration_im = 50;
    private int duration_danmu = 50;
    private TaskSubThread task_im = TaskSubThread.create();
    private TaskSubThread task_danmu = TaskSubThread.create();
    private CopyOnWriteArrayList<FetchImRspBean.ImMsg> imMsgs = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<FetchImRspBean.ImMsg> danmuMsgs = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<ILiveshowMsgDispatch> danmuMsgDispatches = new CopyOnWriteArrayList<>();

    public interface ILiveshowMsgDispatch {
        void dispatchDanmu(FetchImRspBean.ImMsg imMsg);

        void dispatchIm(FetchImRspBean.ImMsg imMsg);
    }

    public static LiveshowMsgDispatcher getInstance() {
        return ourInstance;
    }

    private LiveshowMsgDispatcher() {
    }

    public void register(ILiveshowMsgDispatch iLiveshowMsgDispatch) {
        this.danmuMsgDispatches.add(iLiveshowMsgDispatch);
    }

    public void unRegister(ILiveshowMsgDispatch iLiveshowMsgDispatch) {
        this.danmuMsgDispatches.remove(iLiveshowMsgDispatch);
    }

    public void addLiveshowImMsgs(boolean z, List<FetchImRspBean.ImMsg> list) {
        LogUtil.i(TAG, "addLiveshowImMsgs shouldAddDanmu:" + shouldAddDanmu());
        this.imMsgs.addAll(list);
        if (z) {
            checkStartTaskIm();
        }
    }

    public void addLiveshoDanmuMsgs(boolean z, List<FetchImRspBean.ImMsg> list) {
        boolean zShouldAddDanmu = shouldAddDanmu();
        LogUtil.i(TAG, "addLiveshoDanmuMsgs shouldAddDanmu:" + zShouldAddDanmu);
        if (zShouldAddDanmu) {
            this.danmuMsgs.addAll(list);
        }
        if (z) {
            checkStartTaskDanmu();
        }
    }

    public void addLiveshowMsgs(boolean z, List<FetchImRspBean.ImMsg> list) {
        LogUtil.i(TAG, "addLiveshowMsgs");
        addLiveshowImMsgs(z, list);
        addLiveshoDanmuMsgs(z, list);
    }

    public void addLiveshowMsg(boolean z, boolean z2, FetchImRspBean.ImMsg imMsg) {
        LogUtil.i(TAG, "addLiveshowMsg");
        addLiveshowDanmuMsg(z, z2, imMsg);
        addLiveshowImMsg(z, z2, imMsg);
    }

    public void addLiveshowDanmuMsg(boolean z, boolean z2, FetchImRspBean.ImMsg imMsg) {
        boolean zShouldAddDanmu = shouldAddDanmu();
        LogUtil.i(TAG, "addLiveshowDanmuMsg shouldAddDanmu:" + zShouldAddDanmu);
        if (imMsg == null) {
            LogUtil.i(TAG, "return");
            return;
        }
        if (z) {
            if (zShouldAddDanmu) {
                this.danmuMsgs.add(0, imMsg);
            }
        } else if (zShouldAddDanmu) {
            this.danmuMsgs.add(imMsg);
        }
        if (z2) {
            checkStartTaskDanmu();
        }
    }

    public void addLiveshowImMsg(boolean z, boolean z2, FetchImRspBean.ImMsg imMsg) {
        LogUtil.i(TAG, "addLiveshowImMsg shouldAddDanmu:" + shouldAddDanmu());
        if (imMsg == null) {
            LogUtil.i(TAG, "return");
            return;
        }
        if (z) {
            this.imMsgs.add(0, imMsg);
        } else {
            this.imMsgs.add(imMsg);
        }
        if (z2) {
            checkStartTaskIm();
        }
    }

    private void checkStartTaskIm() {
        if (this.task_im.isRunning()) {
            return;
        }
        startTaskIm();
    }

    private void checkStartTaskDanmu() {
        if (this.task_danmu.isRunning()) {
            return;
        }
        startTaskDanmu();
    }

    private void checkStartTask() {
        checkStartTaskIm();
        checkStartTaskDanmu();
    }

    public void release() {
        this.imMsgs.clear();
        this.danmuMsgs.clear();
        stopTask();
    }

    private void stopTask() {
        this.task_im.stop();
        this.task_danmu.stop();
    }

    private void startTaskIm() {
        this.task_im.repeat(this.duration_im, new TaskSubThread.TaskFunc() { // from class: com.sqwan.liveshow.huya.danmu.LiveshowMsgDispatcher.1
            @Override // com.sqwan.common.util.task.TaskSubThread.TaskFunc
            public TaskSubThread.Result exec() {
                if (LiveshowMsgDispatcher.this.imMsgs.isEmpty()) {
                    return TaskSubThread.Result.Stop;
                }
                LogUtil.i(LiveshowMsgDispatcher.TAG, "danmuMsgDispatches size:" + LiveshowMsgDispatcher.this.danmuMsgDispatches.size());
                FetchImRspBean.ImMsg imMsg = (FetchImRspBean.ImMsg) LiveshowMsgDispatcher.this.imMsgs.remove(0);
                for (ILiveshowMsgDispatch iLiveshowMsgDispatch : LiveshowMsgDispatcher.this.danmuMsgDispatches) {
                    LogUtil.i(LiveshowMsgDispatcher.TAG, "dispatch imMsg:" + imMsg);
                    iLiveshowMsgDispatch.dispatchIm(imMsg);
                }
                return TaskSubThread.Result.Next;
            }
        });
    }

    private void startTaskDanmu() {
        this.task_danmu.repeat(this.duration_danmu, new TaskSubThread.TaskFunc() { // from class: com.sqwan.liveshow.huya.danmu.LiveshowMsgDispatcher.2
            @Override // com.sqwan.common.util.task.TaskSubThread.TaskFunc
            public TaskSubThread.Result exec() {
                if (LiveshowMsgDispatcher.this.danmuMsgs.isEmpty()) {
                    return TaskSubThread.Result.Stop;
                }
                LogUtil.i(LiveshowMsgDispatcher.TAG, "danmuMsgDispatches size:" + LiveshowMsgDispatcher.this.danmuMsgDispatches.size());
                FetchImRspBean.ImMsg imMsg = (FetchImRspBean.ImMsg) LiveshowMsgDispatcher.this.danmuMsgs.remove(0);
                for (ILiveshowMsgDispatch iLiveshowMsgDispatch : LiveshowMsgDispatcher.this.danmuMsgDispatches) {
                    LogUtil.i(LiveshowMsgDispatcher.TAG, "dispatch danmuMsgs:" + imMsg);
                    iLiveshowMsgDispatch.dispatchDanmu(imMsg);
                }
                return TaskSubThread.Result.Next;
            }
        });
    }

    public void init() {
        release();
    }

    public void flushIm() {
        startTaskIm();
    }

    public void flushDanmu() {
        startTaskDanmu();
    }

    public void flush() {
        flushIm();
        flushDanmu();
    }

    private boolean shouldAddDanmu() {
        return LiveshowDanmuManager.getInstance().isDanmuShow() && !LiveshowManager.getInstance().isMinimize();
    }
}
