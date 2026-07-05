package com.youme.imsdk.internal;

import com.youme.imsdk.YIMMessage;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class RoomHistoryMsgInfo {
    private ArrayList<YIMMessage> msgInfoList;
    private int remain;
    private String roomID;

    public RoomHistoryMsgInfo(String str, int i, ArrayList<YIMMessage> arrayList) {
        this.roomID = str;
        this.remain = i;
        this.msgInfoList = arrayList;
    }

    public String getRoomID() {
        return this.roomID;
    }

    public void setRoomID(String str) {
        this.roomID = str;
    }

    public int getRemain() {
        return this.remain;
    }

    public void setRemain(int i) {
        this.remain = i;
    }

    public ArrayList<YIMMessage> getMessageList() {
        return this.msgInfoList;
    }

    public void setMessageList(ArrayList<YIMMessage> arrayList) {
        this.msgInfoList = arrayList;
    }
}
