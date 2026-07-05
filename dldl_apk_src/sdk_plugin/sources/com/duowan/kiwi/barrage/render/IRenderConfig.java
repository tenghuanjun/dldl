package com.duowan.kiwi.barrage.render;

import com.duowan.kiwi.barrage.render.draw.BulletBuilder;
import com.duowan.kiwi.barrage.trace.AbsTrace;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface IRenderConfig<T extends AbsTrace> {
    void addAnimation(T t);

    float getAlpha();

    ArrayList<T> getAnimations();

    int getFixedLine();

    int getLineSpace();

    float getScale();

    BulletBuilder getShellBuilder();

    int getSpaceX();

    boolean isFixedQueue();
}
