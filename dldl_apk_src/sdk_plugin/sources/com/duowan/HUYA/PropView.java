package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.liveshow.huya.SqR;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class PropView extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static Map<Long, Short> cache_gameids;
    static Map<Long, Short> cache_uids;
    public int id = 0;
    public String name = "";
    public Map<Long, Short> uids = null;
    public String tips = "";
    public Map<Long, Short> gameids = null;

    public String className() {
        return "HUYA.PropView";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.PropView";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Map<Long, Short> getUids() {
        return this.uids;
    }

    public void setUids(Map<Long, Short> map) {
        this.uids = map;
    }

    public String getTips() {
        return this.tips;
    }

    public void setTips(String str) {
        this.tips = str;
    }

    public Map<Long, Short> getGameids() {
        return this.gameids;
    }

    public void setGameids(Map<Long, Short> map) {
        this.gameids = map;
    }

    public PropView() {
        setId(0);
        setName(this.name);
        setUids(this.uids);
        setTips(this.tips);
        setGameids(this.gameids);
    }

    public PropView(int i, String str, Map<Long, Short> map, String str2, Map<Long, Short> map2) {
        setId(i);
        setName(str);
        setUids(map);
        setTips(str2);
        setGameids(map2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PropView propView = (PropView) obj;
        return JceUtil.equals(this.id, propView.id) && JceUtil.equals(this.name, propView.name) && JceUtil.equals(this.uids, propView.uids) && JceUtil.equals(this.tips, propView.tips) && JceUtil.equals(this.gameids, propView.gameids);
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
        jceOutputStream.write(this.id, 0);
        String str = this.name;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        Map<Long, Short> map = this.uids;
        if (map != null) {
            jceOutputStream.write((Map) map, 2);
        }
        String str2 = this.tips;
        if (str2 != null) {
            jceOutputStream.write(str2, 3);
        }
        Map<Long, Short> map2 = this.gameids;
        if (map2 != null) {
            jceOutputStream.write((Map) map2, 4);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setId(jceInputStream.read(this.id, 0, false));
        setName(jceInputStream.readString(1, false));
        if (cache_uids == null) {
            cache_uids = new HashMap();
            cache_uids.put(0L, (short) 0);
        }
        setUids((Map) jceInputStream.read(cache_uids, 2, false));
        setTips(jceInputStream.readString(3, false));
        if (cache_gameids == null) {
            cache_gameids = new HashMap();
            cache_gameids.put(0L, (short) 0);
        }
        setGameids((Map) jceInputStream.read(cache_gameids, 4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.id, SqTrackCommonKey.id);
        jceDisplayer.display(this.name, "name");
        jceDisplayer.display((Map) this.uids, "uids");
        jceDisplayer.display(this.tips, SqR.string.tips);
        jceDisplayer.display((Map) this.gameids, "gameids");
    }
}
