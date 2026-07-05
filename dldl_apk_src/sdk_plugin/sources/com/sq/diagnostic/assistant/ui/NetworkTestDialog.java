package com.sq.diagnostic.assistant.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sq.diagnostic.assistant.DiagnosticAssistant;
import com.sq.diagnostic.assistant.log.SQLogUtils;
import com.sq.diagnostic.assistant.mtr.MtrManager;
import com.sq.diagnostic.assistant.mtr.MtrResult;
import com.sq.diagnostic.assistant.other.HttpNetworkConfigManager;
import com.sq.diagnostic.assistant.other.NetworkUtils;
import com.sq.diagnostic.assistant.tcping.TcpingManager;
import com.sq.diagnostic.assistant.tcping.TcpingResult;
import com.sq.diagnostic.assistant.ui.BaseDialog;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class NetworkTestDialog {

    public static final class Builder extends BaseDialog.Builder<Builder> implements BaseDialog.OnDismissListener {
        private static final int MESSAGE_TYPE_FINISH = 2;
        private static final int MESSAGE_TYPE_REFRESH = 1;
        private static final String SEGMENT_LINE = "---------------------------------------";
        private static final String TAG = "NetworkTestDialog";
        private final TextView mCopyView;
        private final List<String> mDnsList;
        private final TextView mFlowContentView;
        private final View mGoBackView;
        private final Handler mHandler;
        private long mMtrDelayedTime;
        private int mMtrHostCount;
        private final List<HttpNetworkConfigManager.MtrBean> mMtrList;
        private final Map<String, List<MtrResult>> mMtrResultList;
        private long mMtrStartTime;
        private final TextView mResultView;
        private final TextView mStartView;
        private final List<HttpNetworkConfigManager.TcpingBean> mTcpingList;
        private final Map<String, List<TcpingResult>> mTcpingResultList;
        private ThreadPoolExecutor mThreadPoolExecutor;
        private final TextView mTipsView;

        static /* synthetic */ int access$1208(Builder builder) {
            int i = builder.mMtrHostCount;
            builder.mMtrHostCount = i + 1;
            return i;
        }

        public Builder(Context context) {
            super(context);
            this.mThreadPoolExecutor = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            this.mDnsList = new LinkedList();
            this.mTcpingResultList = new ConcurrentHashMap();
            this.mMtrResultList = new ConcurrentHashMap();
            this.mMtrHostCount = 0;
            this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.sq.diagnostic.assistant.ui.NetworkTestDialog.Builder.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (Builder.this.isShowing()) {
                        if (message.what == 2) {
                            BlockingQueue<Runnable> queue = Builder.this.mThreadPoolExecutor.getQueue();
                            if (queue == null || queue.size() == 0) {
                                Builder.this.mResultView.setVisibility(0);
                                Builder.this.mCopyView.setVisibility(0);
                                Builder.this.mStartView.setVisibility(0);
                                Builder.this.mStartView.setText(Builder.this.getString("sdk_assistant_network_test_dialog_restart_test"));
                                Builder.this.mTipsView.setVisibility(8);
                                SQLogUtils.i(Builder.TAG, "网络诊断结束：\n" + Builder.this.mFlowContentView.getText().toString());
                                return;
                            }
                            return;
                        }
                        if (message.what != 1) {
                            return;
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("DNS Address");
                        sb.append(ShellAdbUtils.COMMAND_LINE_END);
                        for (String str : Builder.this.mDnsList) {
                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                            sb.append(str);
                        }
                        if (!Builder.this.mTcpingResultList.isEmpty()) {
                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                            sb.append(Builder.SEGMENT_LINE);
                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                            sb.append("TCPing Result");
                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                            Iterator it = Builder.this.mTcpingResultList.keySet().iterator();
                            while (it.hasNext()) {
                                List<TcpingResult> list = (List) Builder.this.mTcpingResultList.get((String) it.next());
                                if (list != null) {
                                    for (TcpingResult tcpingResult : list) {
                                        if (tcpingResult != null) {
                                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                                            sb.append(tcpingResult);
                                        }
                                    }
                                }
                            }
                        }
                        if (!Builder.this.mMtrResultList.isEmpty()) {
                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                            sb.append(Builder.SEGMENT_LINE);
                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                            sb.append("MTR Result");
                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                            for (String str2 : Builder.this.mMtrResultList.keySet()) {
                                List list2 = (List) Builder.this.mMtrResultList.get(str2);
                                if (list2 != null) {
                                    for (int i = 0; i < list2.size(); i++) {
                                        MtrResult mtrResult = (MtrResult) list2.get(i);
                                        if (mtrResult != null) {
                                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                                            sb.append("MTR Host = ");
                                            sb.append(str2);
                                            sb.append(", Count = ");
                                            sb.append(i);
                                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                                            sb.append("MTR Cmd = ");
                                            sb.append(mtrResult.cmd);
                                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                                            sb.append(mtrResult);
                                        }
                                    }
                                }
                            }
                        }
                        Builder.this.mFlowContentView.setText(sb);
                    }
                }
            };
            setWidth(-1);
            setHeight(-1);
            setContentView("sdk_assistant_network_test_dialog");
            this.mGoBackView = findViewById("iv_assistant_network_test_go_back");
            this.mStartView = (TextView) findViewById("btn_assistant_network_test_start");
            this.mCopyView = (TextView) findViewById("btn_assistant_network_test_copy");
            this.mTipsView = (TextView) findViewById("btn_assistant_network_test_tips");
            this.mResultView = (TextView) findViewById("btn_assistant_network_test_result");
            this.mFlowContentView = (TextView) findViewById("tv_assistant_network_test_flow_content");
            this.mGoBackView.setOnClickListener(this);
            this.mStartView.setOnClickListener(this);
            this.mCopyView.setOnClickListener(this);
            addOnDismissListener(this);
            this.mTcpingList = HttpNetworkConfigManager.getTcpingList();
            this.mMtrList = HttpNetworkConfigManager.getMtrList();
            if (this.mTcpingList.isEmpty()) {
                this.mTcpingList.addAll(DiagnosticAssistant.getInstance().getDefaultTcpingList());
            }
            if (this.mMtrList.isEmpty()) {
                this.mMtrList.addAll(DiagnosticAssistant.getInstance().getDefaultMtrList());
            }
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.OnDismissListener
        public void onDismiss(BaseDialog baseDialog) {
            this.mHandler.removeCallbacksAndMessages(null);
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.Builder, com.sq.diagnostic.assistant.ui.action.ClickAction, android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == this.mGoBackView) {
                dismiss();
                return;
            }
            if (view == this.mCopyView) {
                ((ClipboardManager) view.getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", this.mFlowContentView.getText().toString()));
                Toast.makeText(getContext(), getString("sdk_assistant_network_test_dialog_copy_tips"), 1).show();
                return;
            }
            if (view != this.mStartView) {
                return;
            }
            if (!NetworkUtils.isNetworkConnected(getContext())) {
                SQLogUtils.i(TAG, "当前没有网络连接，无法进行网络检测");
                Toast.makeText(getContext(), getString("sdk_assistant_network_test_dialog_not_network_tips"), 1).show();
                return;
            }
            if (this.mTcpingList.isEmpty() && this.mMtrList.isEmpty()) {
                SQLogUtils.i(TAG, "网络诊断失败：域名列表为空");
                Toast.makeText(getContext(), getString("sdk_assistant_network_test_dialog_not_domain_tips"), 1).show();
                return;
            }
            this.mStartView.setVisibility(8);
            this.mTipsView.setVisibility(0);
            this.mResultView.setVisibility(8);
            this.mCopyView.setVisibility(8);
            this.mFlowContentView.setText("");
            this.mDnsList.clear();
            this.mTcpingResultList.clear();
            this.mMtrResultList.clear();
            ThreadPoolExecutor threadPoolExecutor = this.mThreadPoolExecutor;
            if (threadPoolExecutor != null) {
                threadPoolExecutor.shutdownNow();
            }
            this.mThreadPoolExecutor = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            this.mDnsList.addAll(NetworkUtils.getDnsList(view.getContext()));
            this.mHandler.obtainMessage(1).sendToTarget();
            for (int i = 0; i < this.mTcpingList.size(); i++) {
                final HttpNetworkConfigManager.TcpingBean tcpingBean = this.mTcpingList.get(i);
                this.mThreadPoolExecutor.execute(new Runnable() { // from class: com.sq.diagnostic.assistant.ui.-$$Lambda$NetworkTestDialog$Builder$gyUFU4DSmyzENrRSI-9-8gyRJls
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onClick$0$NetworkTestDialog$Builder(tcpingBean);
                    }
                });
            }
            this.mMtrStartTime = SystemClock.uptimeMillis();
            for (int i2 = 0; i2 < this.mMtrList.size(); i2++) {
                final HttpNetworkConfigManager.MtrBean mtrBean = this.mMtrList.get(i2);
                final LinkedList linkedList = new LinkedList();
                this.mMtrResultList.put(mtrBean.getHost(), linkedList);
                this.mThreadPoolExecutor.execute(new Runnable() { // from class: com.sq.diagnostic.assistant.ui.-$$Lambda$NetworkTestDialog$Builder$Sy7W22mDit8qM8-TJO2kJx7NKXk
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onClick$1$NetworkTestDialog$Builder(mtrBean, linkedList);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onClick$0$NetworkTestDialog$Builder(HttpNetworkConfigManager.TcpingBean tcpingBean) {
            if (isShowing()) {
                LinkedList linkedList = new LinkedList();
                this.mTcpingResultList.put(tcpingBean.getHost(), linkedList);
                for (int i = 0; i < 3; i++) {
                    linkedList.add(TcpingManager.tcping(tcpingBean.getHost(), tcpingBean.getPort()));
                    this.mHandler.obtainMessage(1).sendToTarget();
                }
            }
        }

        public /* synthetic */ void lambda$onClick$1$NetworkTestDialog$Builder(HttpNetworkConfigManager.MtrBean mtrBean, final List list) {
            if (isShowing()) {
                try {
                    InetAddress byName = InetAddress.getByName(mtrBean.getHost());
                    for (int i = 1; i <= mtrBean.getTtl() && !MtrManager.mtr(byName, i, mtrBean.getTtl(), new MtrManager.OnMtrCallback() { // from class: com.sq.diagnostic.assistant.ui.NetworkTestDialog.Builder.2
                        @Override // com.sq.diagnostic.assistant.mtr.MtrManager.OnMtrCallback
                        public void onResult(MtrResult mtrResult) {
                            list.add(mtrResult);
                            Builder.this.mMtrDelayedTime += 500;
                            Builder.this.mHandler.sendMessageAtTime(Builder.this.mHandler.obtainMessage(1), Builder.this.mMtrStartTime + Builder.this.mMtrDelayedTime);
                        }

                        @Override // com.sq.diagnostic.assistant.mtr.MtrManager.OnMtrCallback
                        public void onComplete() {
                            Builder.access$1208(Builder.this);
                            if (Builder.this.mMtrHostCount != Builder.this.mMtrList.size()) {
                                return;
                            }
                            Builder.this.mHandler.removeMessages(1);
                            Builder.this.mHandler.obtainMessage(1).sendToTarget();
                            Builder.this.mHandler.obtainMessage(2).sendToTarget();
                        }
                    }); i++) {
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    MtrResult mtrResult = new MtrResult();
                    mtrResult.content = e.getMessage();
                    mtrResult.code = 1023;
                    list.add(mtrResult);
                    this.mHandler.obtainMessage(1).sendToTarget();
                    int i2 = this.mMtrHostCount + 1;
                    this.mMtrHostCount = i2;
                    if (i2 != this.mMtrList.size()) {
                        return;
                    }
                    this.mHandler.removeMessages(1);
                    this.mHandler.obtainMessage(1).sendToTarget();
                    this.mHandler.obtainMessage(2).sendToTarget();
                }
            }
        }
    }
}
