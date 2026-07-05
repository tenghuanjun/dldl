package com.huya.live.ns;

import android.app.Application;
import android.content.Context;
import com.duowan.auk.ArkValue;
import com.duowan.jce.wup.UniPacket;
import com.duowan.monitor.MonitorSDK;
import com.duowan.monitor.jce.Dimension;
import com.duowan.monitor.jce.Field;
import com.duowan.monitor.jce.MetricDetail;
import com.duowan.monitor.utility.MonitorThread;
import com.duowan.networkmars.hysignal.HySignalCallback;
import com.duowan.networkmars.hysignal.HySignalProxy;
import com.duowan.networkmars.hysignal.HySignalSDK;
import com.duowan.networkmars.wup.HaWupFunction;
import com.duowan.networkmars.wup.WupHelper;
import com.huya.data.MonitorReqData;
import com.huya.live.common.api.BaseApi;
import com.huya.live.ns.impl.INSDebugCrashListener;
import com.huya.live.ns.impl.NSDebugApiImpl;
import com.huya.live.ns.impl.NSLogApiImpl;
import com.huya.mtp.api.ContextApi;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.api.MonitorApi;
import com.huya.mtp.hyns.NS;
import com.huya.mtp.hyns.NSEasy;
import com.huya.mtp.hyns.NSProtocol;
import com.huya.mtp.hyns.hysignal.HalConfigWrapper;
import com.huya.mtp.hyns.wup.WupProtocol;
import com.sqwan.msdk.api.IMUrl;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSWrapper {
    static final String KEY_CHANNEL = "channel";
    static final String KEY_PLATFORM = "platform";
    static final String KEY_VERSION = "version";

    public static void NSHalInit(boolean z, HalConfigWrapper halConfigWrapper, INSDebugCrashListener iNSDebugCrashListener) {
        MTPApi.setLogger(new NSLogApiImpl());
        MTPApi.setDebugger(new NSDebugApiImpl(iNSDebugCrashListener));
        MTPApi.setContextApi(new ContextApi() { // from class: com.huya.live.ns.NSWrapper.1
            @Override // com.huya.mtp.api.ContextApi
            public Application getApplication() {
                return ArkValue.gContext;
            }

            @Override // com.huya.mtp.api.ContextApi
            public Context getApplicationContext() {
                return ArkValue.gContext.getApplicationContext();
            }
        });
        MTPApi.setMonitorApi(new MonitorApi() { // from class: com.huya.live.ns.NSWrapper.2
            @Override // com.huya.mtp.api.MonitorApi
            public void execute(Runnable runnable) {
                MonitorThread.execute(runnable);
            }

            @Override // com.huya.mtp.api.MonitorApi
            public void executeDelayed(Runnable runnable, long j) {
                MonitorThread.postDelayed(runnable, j);
            }

            @Override // com.huya.mtp.api.MonitorApi
            public void request(MonitorReqData monitorReqData) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                for (MonitorReqData.DimensionWrapper dimensionWrapper : monitorReqData.vDimension) {
                    arrayList.add(new Dimension(dimensionWrapper.sName, dimensionWrapper.sValue));
                }
                for (MonitorReqData.FieldWrapper fieldWrapper : monitorReqData.vField) {
                    arrayList2.add(new Field(fieldWrapper.sName, fieldWrapper.fValue));
                }
                for (MonitorReqData.DimensionWrapper dimensionWrapper2 : monitorReqData.vExLog) {
                    arrayList3.add(new Dimension(dimensionWrapper2.sName, dimensionWrapper2.sValue));
                }
                MonitorSDK.request(new MetricDetail(monitorReqData.sMetricName, monitorReqData.iTS, arrayList, arrayList2, arrayList3));
            }
        });
        NSEasy.initNSSignal(halConfigWrapper, false, z);
        ((WupProtocol) NS.getProtocolImpl(WupProtocol.class)).setUniPacketGetter(new WupProtocol.UniPacketGetter() { // from class: com.huya.live.ns.NSWrapper.3
            @Override // com.huya.mtp.hyns.wup.WupProtocol.UniPacketGetter
            public UniPacket getUnipacket() {
                return NSWrapper.getCommonUniPacket();
            }
        });
        HySignalProxy.getInstance().init();
        HySignalProxy.getInstance().setMaxMessageCount(100000L);
        HySignalProxy.getInstance().setLinkStateListenter(new HySignalProxy.HySignalLinkStateListenter() { // from class: com.huya.live.ns.NSWrapper.4
            @Override // com.duowan.networkmars.hysignal.HySignalProxy.HySignalLinkStateListenter
            public void onLinkStateChange(boolean z2) {
                BaseApi.getSignalCenterApi().send(new HySignalCallback.HySignalLinkState(z2));
            }
        });
        HySignalSDK.getInstance().init(ArkValue.gContext, z, WupHelper.getSHuYaUA());
    }

    public static synchronized UniPacket getCommonUniPacket() {
        UniPacket uniPacket;
        uniPacket = new UniPacket();
        uniPacket.put("platform", IMUrl.OS);
        uniPacket.put("version", HySignalSDK.getInstance().getAppVersion());
        uniPacket.put("channel", HySignalSDK.getInstance().getChannel());
        if (HaWupFunction.mAtomicLong.get() > 2147483647L) {
            HaWupFunction.mAtomicLong.set(0L);
        }
        uniPacket.setRequestId((int) HaWupFunction.mAtomicLong.getAndIncrement());
        return uniPacket;
    }

    public static <T extends NSProtocol> void initProtocol(Class<? super T> cls, T t) {
        NS.initProtocol(cls, t);
    }

    public static void setDefaultProtocol(NSProtocol nSProtocol) {
        NS.setDefaultProtocol(nSProtocol);
    }

    public static <T> T get(Class<T> cls) {
        return (T) NS.get(cls);
    }

    public static <T extends NSProtocol> T getProtocolImpl(Class<T> cls) {
        return (T) NS.getProtocolImpl(cls);
    }
}
