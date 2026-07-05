package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WSPushMessage_V2 extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<WSMsgItem> cache_vMsgItem;
    public String sGroupId = "";
    public ArrayList<WSMsgItem> vMsgItem = null;

    public String className() {
        return "HUYA.WSPushMessage_V2";
    }

    public String fullClassName() {
        return "WSPushMessage_V2";
    }

    public String getSGroupId() {
        return this.sGroupId;
    }

    public void setSGroupId(String str) {
        this.sGroupId = str;
    }

    public ArrayList<WSMsgItem> getVMsgItem() {
        return this.vMsgItem;
    }

    public void setVMsgItem(ArrayList<WSMsgItem> arrayList) {
        this.vMsgItem = arrayList;
    }

    public WSPushMessage_V2() {
        setSGroupId("");
        setVMsgItem(this.vMsgItem);
    }

    public WSPushMessage_V2(String str, ArrayList<WSMsgItem> arrayList) {
        setSGroupId(str);
        setVMsgItem(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSPushMessage_V2 wSPushMessage_V2 = (WSPushMessage_V2) obj;
        return JceUtil.equals(this.sGroupId, wSPushMessage_V2.sGroupId) && JceUtil.equals(this.vMsgItem, wSPushMessage_V2.vMsgItem);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sGroupId), JceUtil.hashCode(this.vMsgItem)});
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
        String str = this.sGroupId;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        ArrayList<WSMsgItem> arrayList = this.vMsgItem;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSGroupId(jceInputStream.readString(0, false));
        if (cache_vMsgItem == null) {
            cache_vMsgItem = new ArrayList<>();
            cache_vMsgItem.add(new WSMsgItem());
        }
        setVMsgItem((ArrayList) jceInputStream.read(cache_vMsgItem, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sGroupId, "sGroupId");
        jceDisplayer.display((Collection) this.vMsgItem, "vMsgItem");
    }
}
