package com.sqwan.liveshow.bean;

import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveShowIMBean {
    AnchorMessageBean anchorMessageBean;
    String radioStudioName;
    List<UserImBean> userIMBean;

    public AnchorMessageBean getAnchorMessageBean() {
        return this.anchorMessageBean;
    }

    public void setAnchorMessageBean(AnchorMessageBean anchorMessageBean) {
        this.anchorMessageBean = anchorMessageBean;
    }

    public List<UserImBean> getUserIMBean() {
        return this.userIMBean;
    }

    public void setUserIMBean(List<UserImBean> list) {
        this.userIMBean = list;
    }

    public String getRadioStudioName() {
        return this.radioStudioName;
    }

    public void setRadioStudioName(String str) {
        this.radioStudioName = str;
    }

    public static class AnchorMessageBean {
        String anchorName;
        String avatarUrl;
        int onlineNumber;

        public String getAnchorName() {
            return this.anchorName;
        }

        public void setAnchorName(String str) {
            this.anchorName = str;
        }

        public String getAvatarUrl() {
            return this.avatarUrl;
        }

        public void setAvatarUrl(String str) {
            this.avatarUrl = str;
        }

        public int getOnlineNumber() {
            return this.onlineNumber;
        }

        public void setOnlineNumber(int i) {
            this.onlineNumber = i;
        }
    }

    public static class UserImBean {
        String userName = "";
        String userChatContent = "";

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String str) {
            this.userName = str;
        }

        public String getUserChatContent() {
            return this.userChatContent;
        }

        public void setUserChatContent(String str) {
            this.userChatContent = str;
        }

        public String toString() {
            return "UserImBean{userName='" + this.userName + "', userChatContent='" + this.userChatContent + '\'' + AbstractJsonLexerKt.END_OBJ;
        }
    }
}
