package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SubscribeInfoNotify extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static Activity cache_tTo;
    public Activity tTo = null;
    public int iToCount = 0;

    public String className() {
        return "HUYA.SubscribeInfoNotify";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SubscribeInfoNotify";
    }

    public Activity getTTo() {
        return this.tTo;
    }

    public void setTTo(Activity activity) {
        this.tTo = activity;
    }

    public int getIToCount() {
        return this.iToCount;
    }

    public void setIToCount(int i) {
        this.iToCount = i;
    }

    public SubscribeInfoNotify() {
        setTTo(null);
        setIToCount(this.iToCount);
    }

    public SubscribeInfoNotify(Activity activity, int i) {
        setTTo(activity);
        setIToCount(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubscribeInfoNotify subscribeInfoNotify = (SubscribeInfoNotify) obj;
        return JceUtil.equals(this.tTo, subscribeInfoNotify.tTo) && JceUtil.equals(this.iToCount, subscribeInfoNotify.iToCount);
    }

    public int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void writeTo(JceOutputStream jceOutputStream) {
        Activity activity = this.tTo;
        if (activity != null) {
            jceOutputStream.write((JceStruct) activity, 0);
        }
        jceOutputStream.write(this.iToCount, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tTo == null) {
            cache_tTo = new Activity();
        }
        setTTo((Activity) jceInputStream.read((JceStruct) cache_tTo, 0, false));
        setIToCount(jceInputStream.read(this.iToCount, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tTo, "tTo");
        jceDisplayer.display(this.iToCount, "iToCount");
    }
}
