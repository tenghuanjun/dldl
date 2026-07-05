package com.huya.mtp.hyns.stat;

import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.transporter.Transporter;
import com.huya.mtp.http.monitor.Stat;
import com.huya.mtp.hyns.NSFunction;
import com.huya.mtp.hyns.NSResponse;
import com.huya.mtp.hyns.NSStat;
import java.util.TreeMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HySignalStat implements NSStat {
    private Stat mStat = new Stat();
    private NSStatReporter mStatHelper = new NSStatReporter();

    @Override // com.huya.mtp.hyns.NSStat
    public void onExecute(NSFunction<?> nSFunction) {
        this.mStatHelper.setBeginTime();
        onProduceEvent(nSFunction, 100);
    }

    @Override // com.huya.mtp.hyns.NSStat
    public void onProduceEvent(NSFunction<?> nSFunction, int i) {
        this.mStat.updateState(i);
        if (this.mStat.getStatMap().isEmpty() || !this.mStat.getStatMap().containsKey(112)) {
            return;
        }
        reportProducerEvent(nSFunction);
    }

    private void reportProducerEvent(NSFunction<?> nSFunction) {
        TreeMap treeMap = new TreeMap();
        for (Integer num : this.mStat.getStatMap().keySet()) {
            treeMap.put(this.mStat.getStatKey(num.intValue()), Integer.valueOf((int) (this.mStat.getStatMap().get(num).longValue() - this.mStat.getStatMap().get(100).longValue())));
        }
        this.mStat.getStatMap().clear();
        this.mStatHelper.reportApiDetail(nSFunction, treeMap);
    }

    @Override // com.huya.mtp.hyns.NSStat
    public <T> void onResponse(NSFunction<T> nSFunction, NSResponse<T> nSResponse, Transporter<?, ?> transporter) {
        this.mStatHelper.reportTxApiStatSuccess(nSFunction, transporter, nSResponse);
    }

    @Override // com.huya.mtp.hyns.NSStat
    public void onError(NSFunction<?> nSFunction, DataException dataException, Transporter<?, ?> transporter) {
        this.mStatHelper.reportTxApiStatError(nSFunction, dataException, transporter);
    }
}
