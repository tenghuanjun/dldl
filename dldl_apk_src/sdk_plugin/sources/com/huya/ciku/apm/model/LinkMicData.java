package com.huya.ciku.apm.model;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LinkMicData {
    public static final String DIMENSION_NAME_LINK_MIC_ID = "linkMicId";
    public static final String LINK_STEM_MVIDEOLINKMIC_INVITE_REJECT = "MVIDEOLINKMIC_INVITE_REJECT";
    public static final String LINK_STEP_MVIDEOLINKMIC_HANGUP = "MVIDEOLINKMIC_HANGUP";
    public static final String LINK_STEP_MVIDEOLINKMIC_INVITE_USER = "MVIDEOLINKMIC_INVITE_USER";
    public static final String LINK_STEP_MVIDEOLINKMIC_OPEN = "MVIDEOLINKMIC_OPEN";
    public static final String LINK_STEP_MVIDEOLINKMIC_SUCCESS = "MVIDEOLINKMIC_SUCCESS";
    public static final String MARK_END = "end";
    public static final String MARK_START = "start";
    public static final String PK_STEP_INVITATION = "PKInvitation";
    public static final String PK_STEP_RESPONSE_INVITATION = "InvitationResponse";
    public static final String PK_STEP_START_MODE = "startPKMode";
    public static final String PK_STEP_START_NOTICE = "PKStartNotice";
    public static final String STAGE_L = "L";
    public static final String STAGE_P = "P";
    public static final String STAGE_V = "V";
    public static final String STATE_FAILURE = "1";
    public static final String STATE_SUCCESS = "0";
    public static final int STATUS_CONFIRM_RECEIVED = 1;
    public static final String VP_STEP_AUDIENCE = "VPAudience";
    public static final String VP_STEP_START = "VPStart";
    public String linkMicId;
    public String mark;
    public String reason;
    public String receiver;
    public String respCode;
    public String scene;
    public String sender;
    public String serverSessionId;
    public String sessionId;
    public String stage;
    public String stateCode;
    public int status;
    public String step;

    public LinkMicData sessionId(String str) {
        this.sessionId = str;
        return this;
    }

    public LinkMicData sender(String str) {
        this.sender = str;
        return this;
    }

    public LinkMicData receiver(String str) {
        this.receiver = str;
        return this;
    }

    public LinkMicData scene(String str) {
        this.scene = str;
        return this;
    }

    public LinkMicData stage(String str) {
        this.stage = str;
        return this;
    }

    public LinkMicData step(String str) {
        this.step = str;
        return this;
    }

    public LinkMicData stateCode(String str) {
        this.stateCode = str;
        return this;
    }

    public LinkMicData respCode(String str) {
        this.respCode = str;
        return this;
    }

    public LinkMicData reason(String str) {
        this.reason = str;
        return this;
    }

    public LinkMicData mark(String str) {
        this.mark = str;
        return this;
    }

    public LinkMicData serverSessionId(String str) {
        this.serverSessionId = str;
        return this;
    }

    public LinkMicData linkMicId(String str) {
        this.linkMicId = str;
        return this;
    }

    public LinkMicData status(int i) {
        this.status = i;
        return this;
    }

    public String toString() {
        return "LinkMicData{sessionId='" + this.sessionId + "', sender='" + this.sender + "', receiver='" + this.receiver + "', scene='" + this.scene + "', stage='" + this.stage + "', step='" + this.step + "', stateCode='" + this.stateCode + "', respCode='" + this.respCode + "', reason='" + this.reason + "', mark='" + this.mark + "', serverSessionId='" + this.serverSessionId + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
