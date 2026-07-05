package com.youme.imsdk;

import android.util.Base64;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ContactsSessionInfo {

    @SerializedName("CreateTime")
    public Integer iCreateTime;

    @SerializedName("MessageType")
    public Integer iMessageType;

    @SerializedName("NotReadMsgNum")
    public Integer iNotReadMsgNum;

    @SerializedName("ContactID")
    public String strContactID;

    @SerializedName("LocalPath")
    public String strLocalPath;

    @SerializedName("MessageContent")
    public String strMessageContent;

    public byte[] getCustomMesssageContent() {
        if (this.iMessageType.intValue() == 2) {
            return Base64.decode(this.strMessageContent, 0);
        }
        return null;
    }
}
