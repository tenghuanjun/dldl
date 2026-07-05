package com.duowan.kiwi.barrage;

import com.duowan.HUYA.DecorationInfo;
import com.duowan.HUYA.MessageNotice;
import com.huya.mtp.utils.FP;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PubTextSenderDecorationStore {
    private static final PubTextSenderDecorationStore INSTANCE = new PubTextSenderDecorationStore();
    private static final String TAG = "PubTextSenderDecorationStore";
    private MessageNotice mOwnMessageNotice;
    private List<DecorationInfo> mPrefixDecorations = new ArrayList();
    private List<DecorationInfo> mSuffixDecorations = new ArrayList();
    private boolean mHasUpdatedDecorations = false;
    private int mBarrageColor = -8947849;
    private int mBulletColor = -8947849;

    private PubTextSenderDecorationStore() {
    }

    public static PubTextSenderDecorationStore getInstance() {
        return INSTANCE;
    }

    public void updateBarrageColor(int i) {
        this.mBarrageColor = i;
    }

    public int getBarrageColor() {
        return this.mBarrageColor;
    }

    public void updateBulletColor(int i) {
        this.mBulletColor = i;
    }

    public int getBulletColor() {
        return this.mBulletColor;
    }

    public void updateOwnMessageNotice(MessageNotice messageNotice) {
        this.mOwnMessageNotice = messageNotice;
    }

    public MessageNotice getOwnMessageNotice() {
        return this.mOwnMessageNotice;
    }

    public void updateDecorations(List<DecorationInfo> list, List<DecorationInfo> list2) {
        this.mHasUpdatedDecorations = true;
        this.mPrefixDecorations.clear();
        if (FP.empty(list)) {
            return;
        }
        this.mPrefixDecorations.addAll(list);
        this.mSuffixDecorations.clear();
        if (FP.empty(list2)) {
            return;
        }
        this.mSuffixDecorations.addAll(list2);
    }

    public boolean hasUpdatedDecorations() {
        return this.mHasUpdatedDecorations;
    }

    public List<DecorationInfo> getPrefixDecorations() {
        return new ArrayList(this.mPrefixDecorations);
    }

    public List<DecorationInfo> getSuffixDecorations() {
        return new ArrayList(this.mSuffixDecorations);
    }

    public void clear() {
        this.mPrefixDecorations.clear();
        this.mSuffixDecorations.clear();
        this.mHasUpdatedDecorations = false;
        this.mBarrageColor = -8947849;
        this.mBulletColor = -8947849;
        this.mOwnMessageNotice = null;
    }
}
