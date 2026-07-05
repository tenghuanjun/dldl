package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import com.sqwan.common.route.FunctionRouter;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class PresenterNotifyPopupRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<PresenterPopData> cache_data;
    public int iRetCode = 0;
    public ArrayList<PresenterPopData> data = null;

    public String className() {
        return "HUYA.PresenterNotifyPopupRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.PresenterNotifyPopupRsp";
    }

    public int getIRetCode() {
        return this.iRetCode;
    }

    public void setIRetCode(int i) {
        this.iRetCode = i;
    }

    public ArrayList<PresenterPopData> getData() {
        return this.data;
    }

    public void setData(ArrayList<PresenterPopData> arrayList) {
        this.data = arrayList;
    }

    public PresenterNotifyPopupRsp() {
        setIRetCode(0);
        setData(this.data);
    }

    public PresenterNotifyPopupRsp(int i, ArrayList<PresenterPopData> arrayList) {
        setIRetCode(i);
        setData(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PresenterNotifyPopupRsp presenterNotifyPopupRsp = (PresenterNotifyPopupRsp) obj;
        return JceUtil.equals(this.iRetCode, presenterNotifyPopupRsp.iRetCode) && JceUtil.equals(this.data, presenterNotifyPopupRsp.data);
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
        jceOutputStream.write(this.iRetCode, 0);
        ArrayList<PresenterPopData> arrayList = this.data;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIRetCode(jceInputStream.read(this.iRetCode, 0, false));
        if (cache_data == null) {
            cache_data = new ArrayList<>();
            cache_data.add(new PresenterPopData());
        }
        setData((ArrayList) jceInputStream.read(cache_data, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iRetCode, "iRetCode");
        jceDisplayer.display((Collection) this.data, FunctionRouter.KEY_DATA);
    }
}
