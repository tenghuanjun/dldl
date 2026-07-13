package com.bytedance.applog;

/* JADX INFO: loaded from: classes2.dex */
public interface IOaidObserver {

    public static final class Oaid {
        public final String id;

        public Oaid(String str) {
            this.id = str;
        }
    }

    void onOaidLoaded(Oaid oaid);
}
