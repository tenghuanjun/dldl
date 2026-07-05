package com.huya.berry.sdkplayer.common.pubtext;

import android.graphics.Bitmap;
import com.huya.mtp.utils.bind.ViewBinder;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IPubTextModule {
    public static final int ACTION_ADD = 1;
    public static final int ACTION_REMOVE = 2;
    public static final int CODE_FREQUENCY_LIMIT = 1;
    public static final int CODE_SUCCESS = 0;
    public static final int DefaultColorBarrage = -8947849;

    <V> void bindBlockWordsStateAll(V v, ViewBinder<V, Boolean> viewBinder);

    <V> void bindBlockWordsStateSpecific(V v, ViewBinder<V, Boolean> viewBinder);

    <V> void bindMaxFilterKeywordsAll(V v, ViewBinder<V, Integer> viewBinder);

    <V> void bindMaxFilterKeywordsSpecific(V v, ViewBinder<V, Integer> viewBinder);

    <V> void bindMessageHint(V v, ViewBinder<V, String> viewBinder);

    void clearUserDecorations();

    Bitmap getDecorationBitmap(String str);

    void publishMessage(String str);

    SendResult sendPubText(String str, int i);

    boolean shouldBlock(String str);

    boolean tryQueryBlockWords();

    <V> void unbindBlockWordsStateAll(V v);

    <V> void unbindBlockWordsStateSpecific(V v);

    <V> void unbindMaxFilterKeywordsAll(V v);

    <V> void unbindMaxFilterKeywordsSpecific(V v);

    <V> void unbindMessageHint(V v);

    public static class SendResult {
        public final int code;
        public final boolean first;
        public final int leftSec;

        public SendResult(int i) {
            this(i, 0, false);
        }

        public SendResult(int i, int i2, boolean z) {
            this.code = i;
            this.leftSec = i2;
            this.first = z;
        }
    }
}
