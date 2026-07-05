package com.duowan.kiwi.barrage.report;

import android.text.TextUtils;
import com.duowan.ark.util.DensityUtil;
import com.duowan.kiwi.barrage.config.BarrageContext;
import com.duowan.kiwi.barrage.config.BarrageLog;
import com.duowan.kiwi.barrage.trace.AbsTrace;
import com.duowan.kiwi.barrage.utils.GLCoordinate;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageCacheForReport {
    private static final int CACHE_MAX_SIZE = 100;
    private static final int CACHE_TIME_AFTER_DISAPEARED = 5;
    private static final int CLEAN_SIZE_EVERY_TIME = 50;
    public static final int INVALID = -1;
    private static String TAG = "BarrageCacheForReport";
    private Deque<AbsTrace> mAnimtionList = new LinkedList();
    private boolean mEnable = true;
    private static BarrageCacheForReport Instance = new BarrageCacheForReport();
    private static final float TOUCH_AREA_RANGE = DensityUtil.dip2px(BarrageContext.gContext, 22.0f);
    private static final float TOUCH_AREA_ERROR_RANGE_X = DensityUtil.dip2px(BarrageContext.gContext, 20.0f);

    public static BarrageCacheForReport getInstance() {
        return Instance;
    }

    public void switchReport(boolean z) {
        this.mEnable = z;
    }

    public synchronized void add(AbsTrace absTrace) {
        if (this.mEnable) {
            if (this.mAnimtionList.size() > 100) {
                for (int i = 0; i < 50; i++) {
                    this.mAnimtionList.pollFirst();
                }
            }
            this.mAnimtionList.offer(absTrace);
        }
    }

    public synchronized void clear() {
        this.mAnimtionList.clear();
    }

    private synchronized void filter() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        AbsTrace absTracePeekFirst = this.mAnimtionList.peekFirst();
        while (absTracePeekFirst != null) {
            long j = jCurrentTimeMillis - absTracePeekFirst.mCurrentTimeMillis;
            if (j <= 0 || j <= absTracePeekFirst.getDuration() + 5000.0f) {
                break;
            }
            BarrageLog.debug(TAG, "remove:%s", absTracePeekFirst.mText);
            this.mAnimtionList.pollFirst();
            absTracePeekFirst = this.mAnimtionList.peekFirst();
        }
    }

    public List<BarrageReportItem> getCachedBarrages() {
        return getBarrageByCoordinate(-1.0f, -1.0f);
    }

    public synchronized List<BarrageReportItem> getBarrageByCoordinate(float f, float f2) {
        ArrayList<BarrageReportItem> arrayList;
        BarrageLog.info(TAG, "enter getBarrageByCoordinate, x = %f, y = %f, TOUCH_AREA_RANGE = %f", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(TOUCH_AREA_RANGE));
        arrayList = new ArrayList();
        filter();
        for (AbsTrace absTrace : this.mAnimtionList) {
            if (matchCoordinate(absTrace, f, f2)) {
                arrayList.add(new BarrageReportItem(absTrace.mUid, absTrace.mNickName, absTrace.mText));
            }
        }
        for (BarrageReportItem barrageReportItem : arrayList) {
            BarrageLog.debug(TAG, "touched barrage:  %s, nickName: %s", Long.valueOf(barrageReportItem.getUid()), barrageReportItem.getNickName());
        }
        return arrayList;
    }

    private boolean matchCoordinate(AbsTrace absTrace, float f, float f2) {
        if (absTrace.mUid != 0 && !TextUtils.isEmpty(absTrace.mText)) {
            if (f == -1.0f && f2 == -1.0f) {
                return true;
            }
            if (absTrace.mTextureId != -1 && getDistanceToBarrage(absTrace, f, f2) <= TOUCH_AREA_RANGE) {
                return true;
            }
        }
        return false;
    }

    private float getDistanceToBarrage(AbsTrace absTrace, float f, float f2) {
        float f3 = absTrace.mWidth;
        float f4 = absTrace.mHeight;
        float worldPositionX = GLCoordinate.toWorldPositionX(absTrace.getCurrentFrame().x()) + TOUCH_AREA_ERROR_RANGE_X;
        float worldPositionY = GLCoordinate.toWorldPositionY(absTrace.getCurrentFrame().y());
        BarrageLog.debug(TAG, "text:%s,width:%f, height:%f, barrageX:%f, barrageY:%f, x:%f, y:%f", absTrace.mText, Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(worldPositionX), Float.valueOf(worldPositionY), Float.valueOf(f), Float.valueOf(f2));
        if (f < worldPositionX) {
            return getMaxDistance(worldPositionX - f, getMinDistance(worldPositionY - f2, (worldPositionY + f4) - f2));
        }
        if (f > worldPositionX + f3) {
            return getMaxDistance((f - worldPositionX) - f3, getMinDistance(worldPositionY - f2, (worldPositionY + f4) - f2));
        }
        return getMinDistance(worldPositionY - f2, (worldPositionY + f4) - f2);
    }

    private float getMinDistance(float f, float f2) {
        float fAbs = Math.abs(f);
        float fAbs2 = Math.abs(f2);
        return fAbs < fAbs2 ? fAbs : fAbs2;
    }

    private float getMaxDistance(float f, float f2) {
        float fAbs = Math.abs(f);
        float fAbs2 = Math.abs(f2);
        return fAbs > fAbs2 ? fAbs : fAbs2;
    }

    public static class BarrageReportItem {
        public String mNickName;
        public String mText;
        public long mUid;

        public BarrageReportItem(long j, String str, String str2) {
            this.mUid = j;
            this.mNickName = str;
            this.mText = str2;
        }

        public long getUid() {
            return this.mUid;
        }

        public String getContent() {
            return this.mText;
        }

        public String getNickName() {
            return this.mNickName;
        }
    }
}
