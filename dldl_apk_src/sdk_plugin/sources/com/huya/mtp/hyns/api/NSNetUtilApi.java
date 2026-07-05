package com.huya.mtp.hyns.api;

import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.protocol.NSNetUtilProtocol;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(NSNetUtilProtocol.class)
public interface NSNetUtilApi {

    public interface LinkStatusListener {
        void onLinkStateChange(String str, boolean z);
    }

    boolean addLinkStatusListener(String str, LinkStatusListener linkStatusListener);

    int getLinkStatus(int i);

    int getLinkStatus(String str);

    HySignalIPStack getLocalIPStack();

    boolean isLongLinkConnected(int i);

    boolean isLongLinkConnected(String str);

    boolean removeLinkStatusListener(String str, LinkStatusListener linkStatusListener);

    public enum HySignalIPStack {
        None(0),
        IPv4(1),
        IPv6(2),
        Dual(3);

        private int index;

        HySignalIPStack(int i) {
        }

        public static HySignalIPStack valueOf(int i) {
            if (i == 1) {
                return IPv4;
            }
            if (i == 2) {
                return IPv6;
            }
            if (i == 3) {
                return Dual;
            }
            return None;
        }

        public int value() {
            return this.index;
        }
    }
}
